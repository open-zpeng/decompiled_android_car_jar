package android.car.vms;

import android.annotation.SystemApi;
import android.app.Service;
import android.car.vms.IVmsPublisherClient;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.lang.ref.WeakReference;
@SystemApi
/* loaded from: classes.dex */
public abstract class VmsPublisherClientService extends Service {
    private static final boolean DBG = true;
    private static final String TAG = "VmsPublisherClient";
    private final Object mLock = new Object();
    private Handler mHandler = new VmsEventHandler(this);
    private final VmsPublisherClientBinder mVmsPublisherClient = new VmsPublisherClientBinder(this);
    private volatile IVmsPublisherService mVmsPublisherService = null;
    @GuardedBy("mLock")
    private IBinder mToken = null;

    protected abstract void onVmsPublisherServiceReady();

    public abstract void onVmsSubscriptionChange(VmsSubscriptionState vmsSubscriptionState);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind, intent: " + intent);
        return this.mVmsPublisherClient.asBinder();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind, intent: " + intent);
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
        Log.d(TAG, "Publishing for layer : " + layer);
        IBinder token = getTokenForPublisherServiceThreadSafe();
        try {
            this.mVmsPublisherService.publish(token, layer, publisherId, payload);
        } catch (RemoteException e) {
            Log.e(TAG, "unable to publish message: " + payload, e);
        }
    }

    public final void setLayersOffering(VmsLayersOffering offering) {
        Log.d(TAG, "Setting layers offering : " + offering);
        IBinder token = getTokenForPublisherServiceThreadSafe();
        try {
            this.mVmsPublisherService.setLayersOffering(token, offering);
            VmsOperationRecorder.get().setLayersOffering(offering);
        } catch (RemoteException e) {
            Log.e(TAG, "unable to set layers offering: " + offering, e);
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
        Integer publisherId = null;
        try {
            Log.i(TAG, "Getting publisher static ID");
            publisherId = Integer.valueOf(this.mVmsPublisherService.getPublisherId(publisherInfo));
        } catch (RemoteException e) {
            Log.e(TAG, "unable to invoke binder method.", e);
        }
        if (publisherId == null) {
            throw new IllegalStateException("VmsPublisherService cannot get a publisher static ID.");
        }
        VmsOperationRecorder.get().getPublisherId(publisherId.intValue());
        return publisherId.intValue();
    }

    public final VmsSubscriptionState getSubscriptions() {
        if (this.mVmsPublisherService == null) {
            throw new IllegalStateException("VmsPublisherService not set.");
        }
        try {
            return this.mVmsPublisherService.getSubscriptions();
        } catch (RemoteException e) {
            Log.e(TAG, "unable to invoke binder method.", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVmsPublisherService(IVmsPublisherService service) {
        this.mVmsPublisherService = service;
        onVmsPublisherServiceReady();
    }

    /* loaded from: classes.dex */
    private static class VmsPublisherClientBinder extends IVmsPublisherClient.Stub {
        @GuardedBy("mSequenceLock")
        private long mSequence = -1;
        private final Object mSequenceLock = new Object();
        private final WeakReference<VmsPublisherClientService> mVmsPublisherClientService;

        public VmsPublisherClientBinder(VmsPublisherClientService vmsPublisherClientService) {
            this.mVmsPublisherClientService = new WeakReference<>(vmsPublisherClientService);
        }

        @Override // android.car.vms.IVmsPublisherClient
        public void setVmsPublisherService(IBinder token, IVmsPublisherService service) throws RemoteException {
            VmsPublisherClientService vmsPublisherClientService = this.mVmsPublisherClientService.get();
            if (vmsPublisherClientService == null) {
                return;
            }
            Log.d(VmsPublisherClientService.TAG, "setting VmsPublisherService.");
            Handler handler = vmsPublisherClientService.mHandler;
            handler.sendMessage(handler.obtainMessage(1, service));
            vmsPublisherClientService.setToken(token);
        }

        @Override // android.car.vms.IVmsPublisherClient
        public void onVmsSubscriptionChange(VmsSubscriptionState subscriptionState) throws RemoteException {
            VmsPublisherClientService vmsPublisherClientService = this.mVmsPublisherClientService.get();
            if (vmsPublisherClientService == null) {
                return;
            }
            Log.d(VmsPublisherClientService.TAG, "subscription event: " + subscriptionState);
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
            switch (msg.what) {
                case 0:
                    VmsSubscriptionState subscriptionState = (VmsSubscriptionState) msg.obj;
                    service.onVmsSubscriptionChange(subscriptionState);
                    return;
                case 1:
                    service.setVmsPublisherService((IVmsPublisherService) msg.obj);
                    return;
                default:
                    Log.e(VmsPublisherClientService.TAG, "Event type not handled:  " + msg.what);
                    return;
            }
        }
    }
}
