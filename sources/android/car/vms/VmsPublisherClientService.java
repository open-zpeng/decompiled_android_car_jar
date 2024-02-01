package android.car.vms;

import android.annotation.SystemApi;
import android.app.Service;
import android.car.Car;
import android.car.vms.IVmsPublisherClient;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.Collections;

@SystemApi
/* loaded from: classes.dex */
public abstract class VmsPublisherClientService extends Service {
    private static final boolean DBG = false;
    private static final VmsSubscriptionState DEFAULT_SUBSCRIPTIONS = new VmsSubscriptionState(0, Collections.emptySet(), Collections.emptySet());
    private static final String TAG = "VmsPublisherClientService";
    private final Object mLock = new Object();
    private Handler mHandler = new VmsEventHandler(this);
    private final VmsPublisherClientBinder mVmsPublisherClient = new VmsPublisherClientBinder(this);
    private volatile IVmsPublisherService mVmsPublisherService = null;
    @GuardedBy({"mLock"})
    private IBinder mToken = null;

    protected abstract void onVmsPublisherServiceReady();

    public abstract void onVmsSubscriptionChange(VmsSubscriptionState vmsSubscriptionState);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mVmsPublisherClient.asBinder();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        stopSelf();
        return super.onUnbind(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setToken(IBinder token) {
        synchronized (this.mLock) {
            this.mToken = token;
        }
    }

    public final void publish(VmsLayer layer, int publisherId, byte[] payload) {
        Preconditions.checkNotNull(layer, "layer cannot be null");
        IBinder token = getTokenForPublisherServiceThreadSafe();
        try {
            this.mVmsPublisherService.publish(token, layer, publisherId, payload);
        } catch (RemoteException e) {
            Car.handleRemoteExceptionFromCarService(this, e);
        }
    }

    public final void setLayersOffering(VmsLayersOffering offering) {
        Preconditions.checkNotNull(offering, "offering cannot be null");
        IBinder token = getTokenForPublisherServiceThreadSafe();
        try {
            this.mVmsPublisherService.setLayersOffering(token, offering);
            VmsOperationRecorder.get().setLayersOffering(offering);
        } catch (RemoteException e) {
            Car.handleRemoteExceptionFromCarService(this, e);
        }
    }

    private IBinder getTokenForPublisherServiceThreadSafe() {
        IBinder token;
        if (this.mVmsPublisherService == null) {
            throw new IllegalStateException("VmsPublisherService not set.");
        }
        synchronized (this.mLock) {
            token = this.mToken;
        }
        if (token == null) {
            throw new IllegalStateException("VmsPublisherService does not have a valid token.");
        }
        return token;
    }

    public final int getPublisherId(byte[] publisherInfo) {
        if (this.mVmsPublisherService == null) {
            throw new IllegalStateException("VmsPublisherService not set.");
        }
        try {
            int publisherId = this.mVmsPublisherService.getPublisherId(publisherInfo);
            Log.i(TAG, "Assigned publisher ID: " + publisherId);
            VmsOperationRecorder.get().getPublisherId(publisherId);
            return publisherId;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public final VmsSubscriptionState getSubscriptions() {
        if (this.mVmsPublisherService == null) {
            throw new IllegalStateException("VmsPublisherService not set.");
        }
        try {
            return this.mVmsPublisherService.getSubscriptions();
        } catch (RemoteException e) {
            return (VmsSubscriptionState) Car.handleRemoteExceptionFromCarService(this, e, DEFAULT_SUBSCRIPTIONS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVmsPublisherService(IVmsPublisherService service) {
        this.mVmsPublisherService = service;
        onVmsPublisherServiceReady();
    }

    /* loaded from: classes.dex */
    private static class VmsPublisherClientBinder extends IVmsPublisherClient.Stub {
        @GuardedBy({"mSequenceLock"})
        private long mSequence = -1;
        private final Object mSequenceLock = new Object();
        private final WeakReference<VmsPublisherClientService> mVmsPublisherClientService;

        VmsPublisherClientBinder(VmsPublisherClientService vmsPublisherClientService) {
            this.mVmsPublisherClientService = new WeakReference<>(vmsPublisherClientService);
        }

        @Override // android.car.vms.IVmsPublisherClient
        public void setVmsPublisherService(IBinder token, IVmsPublisherService service) {
            assertSystemOrSelf();
            VmsPublisherClientService vmsPublisherClientService = this.mVmsPublisherClientService.get();
            if (vmsPublisherClientService == null) {
                return;
            }
            Handler handler = vmsPublisherClientService.mHandler;
            handler.sendMessage(handler.obtainMessage(1, service));
            vmsPublisherClientService.setToken(token);
        }

        @Override // android.car.vms.IVmsPublisherClient
        public void onVmsSubscriptionChange(VmsSubscriptionState subscriptionState) {
            assertSystemOrSelf();
            VmsPublisherClientService vmsPublisherClientService = this.mVmsPublisherClientService.get();
            if (vmsPublisherClientService == null) {
                return;
            }
            synchronized (this.mSequenceLock) {
                if (subscriptionState.getSequenceNumber() <= this.mSequence) {
                    Log.w(VmsPublisherClientService.TAG, "Sequence out of order. Current sequence = " + this.mSequence + "; expected new sequence = " + subscriptionState.getSequenceNumber());
                    return;
                }
                this.mSequence = subscriptionState.getSequenceNumber();
                Handler handler = vmsPublisherClientService.mHandler;
                handler.sendMessage(handler.obtainMessage(0, subscriptionState));
            }
        }

        private void assertSystemOrSelf() {
            if (Build.VERSION.SDK_INT < 29 && Binder.getCallingUid() != 1000 && Binder.getCallingPid() != Process.myPid()) {
                throw new SecurityException("Caller must be system user or same process");
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class VmsEventHandler extends Handler {
        private static final int ON_SUBSCRIPTION_CHANGE_EVENT = 0;
        private static final int SET_SERVICE_CALLBACK = 1;
        private final WeakReference<VmsPublisherClientService> mVmsPublisherClientService;

        VmsEventHandler(VmsPublisherClientService service) {
            super(Looper.getMainLooper());
            this.mVmsPublisherClientService = new WeakReference<>(service);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            VmsPublisherClientService service = this.mVmsPublisherClientService.get();
            if (service == null) {
                return;
            }
            int i = msg.what;
            if (i == 0) {
                VmsSubscriptionState subscriptionState = (VmsSubscriptionState) msg.obj;
                service.onVmsSubscriptionChange(subscriptionState);
            } else if (i == 1) {
                service.setVmsPublisherService((IVmsPublisherService) msg.obj);
            } else {
                Log.e(VmsPublisherClientService.TAG, "Event type not handled:  " + msg.what);
            }
        }
    }
}
