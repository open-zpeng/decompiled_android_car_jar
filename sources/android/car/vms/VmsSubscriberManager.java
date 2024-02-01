package android.car.vms;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.vms.IVmsSubscriberClient;
import android.car.vms.IVmsSubscriberService;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.util.Preconditions;
import java.util.concurrent.Executor;
@SystemApi
/* loaded from: classes.dex */
public final class VmsSubscriberManager implements CarManagerBase {
    private static final boolean DBG = true;
    private static final String TAG = "VmsSubscriberManager";
    @GuardedBy("mClientCallbackLock")
    private VmsSubscriberClientCallback mClientCallback;
    @GuardedBy("mClientCallbackLock")
    private Executor mExecutor;
    private final IVmsSubscriberService mVmsSubscriberService;
    private final Object mClientCallbackLock = new Object();
    private final IVmsSubscriberClient mSubscriberManagerClient = new AnonymousClass1();

    /* loaded from: classes.dex */
    public interface VmsSubscriberClientCallback {
        void onLayersAvailabilityChanged(VmsAvailableLayers vmsAvailableLayers);

        void onVmsMessageReceived(VmsLayer vmsLayer, byte[] bArr);
    }

    public VmsSubscriberManager(IBinder service) {
        this.mVmsSubscriberService = IVmsSubscriberService.Stub.asInterface(service);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.car.vms.VmsSubscriberManager$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends IVmsSubscriberClient.Stub {
        AnonymousClass1() {
        }

        @Override // android.car.vms.IVmsSubscriberClient
        public void onVmsMessageReceived(final VmsLayer layer, final byte[] payload) {
            Executor executor;
            synchronized (VmsSubscriberManager.this.mClientCallbackLock) {
                executor = VmsSubscriberManager.this.mExecutor;
            }
            if (executor == null) {
                Log.d(VmsSubscriberManager.TAG, "Executor is null in onVmsMessageReceived");
                return;
            }
            Binder.clearCallingIdentity();
            executor.execute(new Runnable() { // from class: android.car.vms.-$$Lambda$VmsSubscriberManager$1$afqRUqICTW_Bv-9KKLr-b1VHpUA
                @Override // java.lang.Runnable
                public final void run() {
                    VmsSubscriberManager.this.dispatchOnReceiveMessage(layer, payload);
                }
            });
        }

        @Override // android.car.vms.IVmsSubscriberClient
        public void onLayersAvailabilityChanged(final VmsAvailableLayers availableLayers) {
            Executor executor;
            synchronized (VmsSubscriberManager.this.mClientCallbackLock) {
                executor = VmsSubscriberManager.this.mExecutor;
            }
            if (executor == null) {
                Log.d(VmsSubscriberManager.TAG, "Executor is null in onLayersAvailabilityChanged");
                return;
            }
            Binder.clearCallingIdentity();
            executor.execute(new Runnable() { // from class: android.car.vms.-$$Lambda$VmsSubscriberManager$1$YFkXlCwCneVvMYfeu4olB3-8X0o
                @Override // java.lang.Runnable
                public final void run() {
                    VmsSubscriberManager.this.dispatchOnAvailabilityChangeMessage(availableLayers);
                }
            });
        }
    }

    public void setVmsSubscriberClientCallback(Executor executor, VmsSubscriberClientCallback clientCallback) throws CarNotConnectedException {
        Preconditions.checkNotNull(clientCallback);
        Preconditions.checkNotNull(executor);
        synchronized (this.mClientCallbackLock) {
            if (this.mClientCallback != null) {
                throw new IllegalStateException("Client callback is already configured.");
            }
            this.mClientCallback = clientCallback;
            this.mExecutor = executor;
        }
        try {
            this.mVmsSubscriberService.addVmsSubscriberToNotifications(this.mSubscriberManagerClient);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: ", e);
            throw new CarNotConnectedException(e);
        }
    }

    public void clearVmsSubscriberClientCallback() throws CarNotConnectedException {
        synchronized (this.mClientCallbackLock) {
            if (this.mExecutor == null) {
                return;
            }
            try {
                this.mVmsSubscriberService.removeVmsSubscriberToNotifications(this.mSubscriberManagerClient);
                synchronized (this.mClientCallbackLock) {
                    this.mClientCallback = null;
                    this.mExecutor = null;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Could not connect: ", e);
                throw new CarNotConnectedException(e);
            }
        }
    }

    public byte[] getPublisherInfo(int publisherId) throws CarNotConnectedException, IllegalStateException {
        try {
            return this.mVmsSubscriberService.getPublisherInfo(publisherId);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: ", e);
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException ex) {
            Car.checkCarNotConnectedExceptionFromCarService(ex);
            throw new IllegalStateException(ex);
        }
    }

    public VmsAvailableLayers getAvailableLayers() throws CarNotConnectedException, IllegalStateException {
        try {
            return this.mVmsSubscriberService.getAvailableLayers();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: ", e);
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException ex) {
            Car.checkCarNotConnectedExceptionFromCarService(ex);
            throw new IllegalStateException(ex);
        }
    }

    public void subscribe(VmsLayer layer) throws CarNotConnectedException {
        verifySubscriptionIsAllowed();
        try {
            this.mVmsSubscriberService.addVmsSubscriber(this.mSubscriberManagerClient, layer);
            VmsOperationRecorder.get().subscribe(layer);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: ", e);
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException ex) {
            Car.checkCarNotConnectedExceptionFromCarService(ex);
        }
    }

    public void subscribe(VmsLayer layer, int publisherId) throws CarNotConnectedException {
        verifySubscriptionIsAllowed();
        try {
            this.mVmsSubscriberService.addVmsSubscriberToPublisher(this.mSubscriberManagerClient, layer, publisherId);
            VmsOperationRecorder.get().subscribe(layer, publisherId);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: ", e);
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException ex) {
            Car.checkCarNotConnectedExceptionFromCarService(ex);
        }
    }

    public void startMonitoring() throws CarNotConnectedException {
        verifySubscriptionIsAllowed();
        try {
            this.mVmsSubscriberService.addVmsSubscriberPassive(this.mSubscriberManagerClient);
            VmsOperationRecorder.get().startMonitoring();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not connect: ", e);
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException ex) {
            Car.checkCarNotConnectedExceptionFromCarService(ex);
        }
    }

    public void unsubscribe(VmsLayer layer) {
        verifySubscriptionIsAllowed();
        try {
            this.mVmsSubscriberService.removeVmsSubscriber(this.mSubscriberManagerClient, layer);
            VmsOperationRecorder.get().unsubscribe(layer);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to clear subscriber", e);
        } catch (IllegalStateException ex) {
            Car.hideCarNotConnectedExceptionFromCarService(ex);
        }
    }

    public void unsubscribe(VmsLayer layer, int publisherId) {
        try {
            this.mVmsSubscriberService.removeVmsSubscriberToPublisher(this.mSubscriberManagerClient, layer, publisherId);
            VmsOperationRecorder.get().unsubscribe(layer, publisherId);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to clear subscriber", e);
        } catch (IllegalStateException ex) {
            Car.hideCarNotConnectedExceptionFromCarService(ex);
        }
    }

    public void stopMonitoring() {
        try {
            this.mVmsSubscriberService.removeVmsSubscriberPassive(this.mSubscriberManagerClient);
            VmsOperationRecorder.get().stopMonitoring();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to clear subscriber ", e);
        } catch (IllegalStateException ex) {
            Car.hideCarNotConnectedExceptionFromCarService(ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnReceiveMessage(VmsLayer layer, byte[] payload) {
        VmsSubscriberClientCallback clientCallback = getClientCallbackThreadSafe();
        if (clientCallback == null) {
            Log.e(TAG, "Cannot dispatch received message.");
        } else {
            clientCallback.onVmsMessageReceived(layer, payload);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnAvailabilityChangeMessage(VmsAvailableLayers availableLayers) {
        VmsSubscriberClientCallback clientCallback = getClientCallbackThreadSafe();
        if (clientCallback == null) {
            Log.e(TAG, "Cannot dispatch availability change message.");
        } else {
            clientCallback.onLayersAvailabilityChanged(availableLayers);
        }
    }

    private VmsSubscriberClientCallback getClientCallbackThreadSafe() {
        VmsSubscriberClientCallback clientCallback;
        synchronized (this.mClientCallbackLock) {
            clientCallback = this.mClientCallback;
        }
        if (clientCallback == null) {
            Log.e(TAG, "client callback not set.");
        }
        return clientCallback;
    }

    private void verifySubscriptionIsAllowed() {
        VmsSubscriberClientCallback clientCallback = getClientCallbackThreadSafe();
        if (clientCallback == null) {
            throw new IllegalStateException("Cannot subscribe.");
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.VMS_SUBSCRIBER_SERVICE;
    }

    /* loaded from: classes.dex */
    private static final class VmsDataMessage {
        private final VmsLayer mLayer;
        private final byte[] mPayload;

        public VmsDataMessage(VmsLayer layer, byte[] payload) {
            this.mLayer = layer;
            this.mPayload = payload;
        }

        public VmsLayer getLayer() {
            return this.mLayer;
        }

        public byte[] getPayload() {
            return this.mPayload;
        }
    }
}
