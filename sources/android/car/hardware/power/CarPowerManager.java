package android.car.hardware.power;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.power.ICarPower;
import android.car.hardware.power.ICarPowerStateListener;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;

@SystemApi
/* loaded from: classes.dex */
public class CarPowerManager extends CarManagerBase {
    private static final boolean DBG = false;
    private static final String TAG = "CarPowerManager";
    @GuardedBy({"mLock"})
    private CompletableFuture<Void> mFuture;
    @GuardedBy({"mLock"})
    private CarPowerStateListener mListener;
    @GuardedBy({"mLock"})
    private ICarPowerStateListener mListenerToService;
    @GuardedBy({"mLock"})
    private CarPowerStateListenerWithCompletion mListenerWithCompletion;
    private final Object mLock;
    private final ICarPower mService;

    /* loaded from: classes.dex */
    public interface CarPowerStateListener {
        public static final int ON = 6;
        public static final int ON_SCREEN_OFF = 9;
        public static final int SHUTDOWN_CANCELLED = 8;
        public static final int SHUTDOWN_ENTER = 5;
        public static final int SHUTDOWN_PREPARE = 7;
        public static final int SUSPEND_ENTER = 2;
        public static final int SUSPEND_EXIT = 3;
        public static final int WAIT_FOR_VHAL = 1;

        void onStateChanged(int i);
    }

    /* loaded from: classes.dex */
    public interface CarPowerStateListenerWithCompletion {
        void onStateChanged(int i, CompletableFuture<Void> completableFuture);
    }

    public CarPowerManager(Car car, IBinder service) {
        super(car);
        this.mLock = new Object();
        this.mService = ICarPower.Stub.asInterface(service);
    }

    public void requestShutdownOnNextSuspend() {
        try {
            this.mService.requestShutdownOnNextSuspend();
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void scheduleNextWakeupTime(int seconds) {
        try {
            this.mService.scheduleNextWakeupTime(seconds);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void setListener(CarPowerStateListener listener) {
        synchronized (this.mLock) {
            if (this.mListener != null || this.mListenerWithCompletion != null) {
                throw new IllegalStateException("Listener must be cleared first");
            }
            this.mListener = listener;
            setServiceForListenerLocked(false);
        }
    }

    public int getBootReason() throws CarNotConnectedException {
        try {
            return this.mService.getBootReason();
        } catch (RemoteException e) {
            Log.e(TAG, "Exception in getBootReason", e);
            throw new CarNotConnectedException(e);
        }
    }

    public void setListener(CarPowerStateListener listener, Executor executor) {
        setListener(listener);
    }

    public void setListenerWithCompletion(CarPowerStateListenerWithCompletion listener) {
        synchronized (this.mLock) {
            if (this.mListener != null || this.mListenerWithCompletion != null) {
                throw new IllegalStateException("Listener must be cleared first");
            }
            this.mListenerWithCompletion = listener;
            setServiceForListenerLocked(true);
        }
    }

    private void setServiceForListenerLocked(final boolean useCompletion) {
        if (this.mListenerToService == null) {
            ICarPowerStateListener listenerToService = new ICarPowerStateListener.Stub() { // from class: android.car.hardware.power.CarPowerManager.1
                @Override // android.car.hardware.power.ICarPowerStateListener
                public void onStateChanged(int state) throws RemoteException {
                    CarPowerStateListener listener;
                    CarPowerStateListenerWithCompletion listenerWithCompletion;
                    CompletableFuture<Void> future;
                    if (useCompletion) {
                        synchronized (CarPowerManager.this.mLock) {
                            CarPowerManager.this.updateFutureLocked(state);
                            listenerWithCompletion = CarPowerManager.this.mListenerWithCompletion;
                            future = CarPowerManager.this.mFuture;
                        }
                        listenerWithCompletion.onStateChanged(state, future);
                        return;
                    }
                    synchronized (CarPowerManager.this.mLock) {
                        listener = CarPowerManager.this.mListener;
                    }
                    listener.onStateChanged(state);
                }
            };
            try {
                if (useCompletion) {
                    this.mService.registerListenerWithCompletion(listenerToService);
                } else {
                    this.mService.registerListener(listenerToService);
                }
                this.mListenerToService = listenerToService;
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public void clearListener() {
        ICarPowerStateListener listenerToService;
        synchronized (this.mLock) {
            listenerToService = this.mListenerToService;
            this.mListenerToService = null;
            this.mListener = null;
            this.mListenerWithCompletion = null;
            cleanupFutureLocked();
        }
        if (listenerToService == null) {
            Log.w(TAG, "unregisterListener: listener was not registered");
            return;
        }
        try {
            this.mService.unregisterListener(listenerToService);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFutureLocked(int state) {
        cleanupFutureLocked();
        if (state == 7) {
            this.mFuture = new CompletableFuture<>();
            this.mFuture.whenComplete(new BiConsumer() { // from class: android.car.hardware.power.-$$Lambda$CarPowerManager$OcodOGJnKRrwqzJK2haZpw0lWow
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CarPowerManager.this.lambda$updateFutureLocked$0$CarPowerManager((Void) obj, (Throwable) obj2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateFutureLocked$0$CarPowerManager(Void result, Throwable exception) {
        ICarPowerStateListener listenerToService;
        if (exception != null && !(exception instanceof CancellationException)) {
            Log.e(TAG, "Exception occurred while waiting for future", exception);
        }
        synchronized (this.mLock) {
            listenerToService = this.mListenerToService;
        }
        try {
            this.mService.finished(listenerToService);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    private void cleanupFutureLocked() {
        CompletableFuture<Void> completableFuture = this.mFuture;
        if (completableFuture != null) {
            if (!completableFuture.isDone()) {
                this.mFuture.cancel(false);
            }
            this.mFuture = null;
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        synchronized (this.mLock) {
            this.mListener = null;
            this.mListenerWithCompletion = null;
        }
    }

    public static String getServiceName() {
        return Car.POWER_SERVICE;
    }
}
