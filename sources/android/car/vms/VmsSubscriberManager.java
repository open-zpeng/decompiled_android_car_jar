package android.car.vms;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.vms.IVmsSubscriberClient;
import android.car.vms.IVmsSubscriberService;
import android.car.vms.VmsSubscriberManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.util.Preconditions;
import java.util.Collections;
import java.util.concurrent.Executor;

@SystemApi
/* loaded from: classes.dex */
public final class VmsSubscriberManager extends CarManagerBase {
    private static final String TAG = "VmsSubscriberManager";
    @GuardedBy({"mClientCallbackLock"})
    private VmsSubscriberClientCallback mClientCallback;
    private final Object mClientCallbackLock;
    @GuardedBy({"mClientCallbackLock"})
    private Executor mExecutor;
    private final IVmsSubscriberClient mSubscriberManagerClient;
    private final IVmsSubscriberService mVmsSubscriberService;
    private static final byte[] DEFAULT_PUBLISHER_INFO = new byte[0];
    private static final VmsAvailableLayers DEFAULT_AVAILABLE_LAYERS = new VmsAvailableLayers(Collections.emptySet(), 0);

    /* loaded from: classes.dex */
    public interface VmsSubscriberClientCallback {
        void onLayersAvailabilityChanged(VmsAvailableLayers vmsAvailableLayers);

        void onVmsMessageReceived(VmsLayer vmsLayer, byte[] bArr);
    }

    public VmsSubscriberManager(Car car, IBinder service) {
        super(car);
        this.mClientCallbackLock = new Object();
        this.mVmsSubscriberService = IVmsSubscriberService.Stub.asInterface(service);
        this.mSubscriberManagerClient = new AnonymousClass1();
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
                Log.w(VmsSubscriberManager.TAG, "Executor is unset in onVmsMessageReceived");
                return;
            }
            Binder.clearCallingIdentity();
            executor.execute(new Runnable() { // from class: android.car.vms.-$$Lambda$VmsSubscriberManager$1$afqRUqICTW_Bv-9KKLr-b1VHpUA
                @Override // java.lang.Runnable
                public final void run() {
                    VmsSubscriberManager.AnonymousClass1.this.lambda$onVmsMessageReceived$0$VmsSubscriberManager$1(layer, payload);
                }
            });
        }

        public /* synthetic */ void lambda$onVmsMessageReceived$0$VmsSubscriberManager$1(VmsLayer layer, byte[] payload) {
            VmsSubscriberManager.this.dispatchOnReceiveMessage(layer, payload);
        }

        @Override // android.car.vms.IVmsSubscriberClient
        public void onLayersAvailabilityChanged(final VmsAvailableLayers availableLayers) {
            Executor executor;
            synchronized (VmsSubscriberManager.this.mClientCallbackLock) {
                executor = VmsSubscriberManager.this.mExecutor;
            }
            if (executor == null) {
                Log.w(VmsSubscriberManager.TAG, "Executor is unset in onLayersAvailabilityChanged");
                return;
            }
            Binder.clearCallingIdentity();
            executor.execute(new Runnable() { // from class: android.car.vms.-$$Lambda$VmsSubscriberManager$1$YFkXlCwCneVvMYfeu4olB3-8X0o
                @Override // java.lang.Runnable
                public final void run() {
                    VmsSubscriberManager.AnonymousClass1.this.lambda$onLayersAvailabilityChanged$1$VmsSubscriberManager$1(availableLayers);
                }
            });
        }

        public /* synthetic */ void lambda$onLayersAvailabilityChanged$1$VmsSubscriberManager$1(VmsAvailableLayers availableLayers) {
            VmsSubscriberManager.this.dispatchOnAvailabilityChangeMessage(availableLayers);
        }
    }

    public void setVmsSubscriberClientCallback(Executor executor, VmsSubscriberClientCallback clientCallback) {
        synchronized (this.mClientCallbackLock) {
            if (this.mClientCallback != null) {
                throw new IllegalStateException("Client callback is already configured.");
            }
            this.mClientCallback = (VmsSubscriberClientCallback) Preconditions.checkNotNull(clientCallback, "clientCallback cannot be null");
            this.mExecutor = (Executor) Preconditions.checkNotNull(executor, "executor cannot be null");
        }
        try {
            this.mVmsSubscriberService.addVmsSubscriberToNotifications(this.mSubscriberManagerClient);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void clearVmsSubscriberClientCallback() {
        synchronized (this.mClientCallbackLock) {
            if (this.mExecutor == null) {
                return;
            }
            try {
                try {
                    this.mVmsSubscriberService.removeVmsSubscriberToNotifications(this.mSubscriberManagerClient);
                    synchronized (this.mClientCallbackLock) {
                        this.mClientCallback = null;
                        this.mExecutor = null;
                    }
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                    synchronized (this.mClientCallbackLock) {
                        this.mClientCallback = null;
                        this.mExecutor = null;
                    }
                }
            } catch (Throwable th) {
                synchronized (this.mClientCallbackLock) {
                    this.mClientCallback = null;
                    this.mExecutor = null;
                    throw th;
                }
            }
        }
    }

    public byte[] getPublisherInfo(int publisherId) {
        try {
            return this.mVmsSubscriberService.getPublisherInfo(publisherId);
        } catch (RemoteException e) {
            return (byte[]) handleRemoteExceptionFromCarService(e, DEFAULT_PUBLISHER_INFO);
        }
    }

    public VmsAvailableLayers getAvailableLayers() {
        try {
            return this.mVmsSubscriberService.getAvailableLayers();
        } catch (RemoteException e) {
            return (VmsAvailableLayers) handleRemoteExceptionFromCarService(e, DEFAULT_AVAILABLE_LAYERS);
        }
    }

    public void subscribe(VmsLayer layer) {
        verifySubscriptionIsAllowed();
        try {
            this.mVmsSubscriberService.addVmsSubscriber(this.mSubscriberManagerClient, layer);
            VmsOperationRecorder.get().subscribe(layer);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void subscribe(VmsLayer layer, int publisherId) {
        verifySubscriptionIsAllowed();
        try {
            this.mVmsSubscriberService.addVmsSubscriberToPublisher(this.mSubscriberManagerClient, layer, publisherId);
            VmsOperationRecorder.get().subscribe(layer, publisherId);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void startMonitoring() {
        verifySubscriptionIsAllowed();
        try {
            this.mVmsSubscriberService.addVmsSubscriberPassive(this.mSubscriberManagerClient);
            VmsOperationRecorder.get().startMonitoring();
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void unsubscribe(VmsLayer layer) {
        verifySubscriptionIsAllowed();
        try {
            this.mVmsSubscriberService.removeVmsSubscriber(this.mSubscriberManagerClient, layer);
            VmsOperationRecorder.get().unsubscribe(layer);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void unsubscribe(VmsLayer layer, int publisherId) {
        try {
            this.mVmsSubscriberService.removeVmsSubscriberToPublisher(this.mSubscriberManagerClient, layer, publisherId);
            VmsOperationRecorder.get().unsubscribe(layer, publisherId);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void stopMonitoring() {
        try {
            this.mVmsSubscriberService.removeVmsSubscriberPassive(this.mSubscriberManagerClient);
            VmsOperationRecorder.get().stopMonitoring();
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
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
        synchronized (this.mClientCallbackLock) {
            this.mClientCallback = null;
            this.mExecutor = null;
        }
    }

    public static String getServiceName() {
        return Car.VMS_SUBSCRIBER_SERVICE;
    }
}
