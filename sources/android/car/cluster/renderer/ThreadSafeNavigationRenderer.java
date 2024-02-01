package android.car.cluster.renderer;

import android.car.navigation.CarNavigationInstrumentCluster;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ThreadSafeNavigationRenderer extends NavigationRenderer {
    private static final int MSG_EVENT = 1;
    private final Handler mHandler;
    private final NavigationRenderer mRenderer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NavigationRenderer createFor(Looper looper, NavigationRenderer renderer) {
        if (renderer == null) {
            return null;
        }
        return new ThreadSafeNavigationRenderer(looper, renderer);
    }

    private ThreadSafeNavigationRenderer(Looper looper, NavigationRenderer renderer) {
        this.mRenderer = renderer;
        this.mHandler = new NavigationRendererHandler(looper, renderer);
    }

    @Override // android.car.cluster.renderer.NavigationRenderer
    public CarNavigationInstrumentCluster getNavigationProperties() {
        if (this.mHandler.getLooper() == Looper.myLooper()) {
            return this.mRenderer.getNavigationProperties();
        }
        return (CarNavigationInstrumentCluster) runAndWaitResult(this.mHandler, new RunnableWithResult<CarNavigationInstrumentCluster>() { // from class: android.car.cluster.renderer.ThreadSafeNavigationRenderer.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.car.cluster.renderer.ThreadSafeNavigationRenderer.RunnableWithResult
            public CarNavigationInstrumentCluster createResult() {
                return ThreadSafeNavigationRenderer.this.mRenderer.getNavigationProperties();
            }
        });
    }

    @Override // android.car.cluster.renderer.NavigationRenderer
    public void onEvent(int eventType, Bundle bundle) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, eventType, 0, bundle));
    }

    /* loaded from: classes.dex */
    private static class NavigationRendererHandler extends RendererHandler<NavigationRenderer> {
        NavigationRendererHandler(Looper looper, NavigationRenderer renderer) {
            super(looper, renderer);
        }

        @Override // android.car.cluster.renderer.ThreadSafeNavigationRenderer.RendererHandler
        public void handleMessage(Message msg, NavigationRenderer renderer) {
            if (msg.what == 1) {
                Bundle bundle = (Bundle) msg.obj;
                renderer.onEvent(msg.arg1, bundle);
                return;
            }
            throw new IllegalArgumentException("Msg: " + msg.what);
        }
    }

    private static <E> E runAndWaitResult(Handler handler, final RunnableWithResult<E> runnable) {
        final CountDownLatch latch = new CountDownLatch(1);
        Runnable wrappedRunnable = new Runnable() { // from class: android.car.cluster.renderer.ThreadSafeNavigationRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                RunnableWithResult.this.run();
                latch.countDown();
            }
        };
        handler.post(wrappedRunnable);
        try {
            latch.await();
            return runnable.getResult();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class RunnableWithResult<T> implements Runnable {
        private volatile T result;

        protected abstract T createResult();

        private RunnableWithResult() {
        }

        @Override // java.lang.Runnable
        public void run() {
            this.result = createResult();
        }

        public T getResult() {
            return this.result;
        }
    }

    /* loaded from: classes.dex */
    private static abstract class RendererHandler<T> extends Handler {
        private final WeakReference<T> mRendererRef;

        public abstract void handleMessage(Message message, T t);

        RendererHandler(Looper looper, T renderer) {
            super(looper);
            this.mRendererRef = new WeakReference<>(renderer);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            T renderer = this.mRendererRef.get();
            if (renderer != null) {
                handleMessage(msg, renderer);
            }
        }
    }
}
