package android.car.trust;

import android.annotation.SystemApi;
import android.bluetooth.BluetoothDevice;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.trust.ICarTrustAgentBleCallback;
import android.car.trust.ICarTrustAgentEnrollment;
import android.car.trust.ICarTrustAgentEnrollmentCallback;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

@SystemApi
/* loaded from: classes.dex */
public final class CarTrustAgentEnrollmentManager extends CarManagerBase {
    public static final int ENROLLMENT_HANDSHAKE_FAILURE = 1;
    public static final int ENROLLMENT_NOT_ALLOWED = 2;
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_HANDLE = "handle";
    private static final int MSG_ENROLL_ADVERTISING_FAILED = 1;
    private static final int MSG_ENROLL_ADVERTISING_STARTED = 0;
    private static final int MSG_ENROLL_AUTH_STRING_AVAILABLE = 5;
    private static final int MSG_ENROLL_DEVICE_CONNECTED = 2;
    private static final int MSG_ENROLL_DEVICE_DISCONNECTED = 3;
    private static final int MSG_ENROLL_HANDSHAKE_FAILURE = 4;
    private static final int MSG_ENROLL_TOKEN_ADDED = 6;
    private static final int MSG_ENROLL_TOKEN_REMOVED = 8;
    private static final int MSG_ENROLL_TOKEN_STATE_CHANGED = 7;
    private static final String TAG = "CarTrustEnrollMgr";
    @GuardedBy({"mListenerLock"})
    private CarTrustAgentBleCallback mBleCallback;
    @GuardedBy({"mListenerLock"})
    private CarTrustAgentEnrollmentCallback mEnrollmentCallback;
    private final ICarTrustAgentEnrollment mEnrollmentService;
    private final EventCallbackHandler mEventCallbackHandler;
    private Object mListenerLock;
    private final ListenerToBleService mListenerToBleService;
    @GuardedBy({"mListenerLock"})
    private final ListenerToEnrollmentService mListenerToEnrollmentService;

    /* loaded from: classes.dex */
    public interface CarTrustAgentBleCallback {
        void onBleEnrollmentDeviceConnected(BluetoothDevice bluetoothDevice);

        void onBleEnrollmentDeviceDisconnected(BluetoothDevice bluetoothDevice);

        void onEnrollmentAdvertisingFailed();

        void onEnrollmentAdvertisingStarted();
    }

    /* loaded from: classes.dex */
    public interface CarTrustAgentEnrollmentCallback {
        void onAuthStringAvailable(BluetoothDevice bluetoothDevice, String str);

        void onEnrollmentHandshakeFailure(BluetoothDevice bluetoothDevice, int i);

        void onEscrowTokenActiveStateChanged(long j, boolean z);

        void onEscrowTokenAdded(long j);

        void onEscrowTokenRemoved(long j);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TrustedDeviceEnrollmentError {
    }

    public CarTrustAgentEnrollmentManager(Car car, IBinder service) {
        super(car);
        this.mListenerLock = new Object();
        this.mListenerToEnrollmentService = new ListenerToEnrollmentService(this);
        this.mListenerToBleService = new ListenerToBleService(this);
        this.mEnrollmentService = ICarTrustAgentEnrollment.Stub.asInterface(service);
        this.mEventCallbackHandler = new EventCallbackHandler(this, getEventHandler().getLooper());
    }

    @Override // android.car.CarManagerBase
    public synchronized void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.CAR_TRUST_AGENT_ENROLLMENT_SERVICE;
    }

    public void startEnrollmentAdvertising() {
        try {
            this.mEnrollmentService.startEnrollmentAdvertising();
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void stopEnrollmentAdvertising() {
        try {
            this.mEnrollmentService.stopEnrollmentAdvertising();
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void enrollmentHandshakeAccepted(BluetoothDevice device) {
        try {
            this.mEnrollmentService.enrollmentHandshakeAccepted(device);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void terminateEnrollmentHandshake() {
        try {
            this.mEnrollmentService.terminateEnrollmentHandshake();
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public boolean isEscrowTokenActive(long handle, int uid) {
        try {
            return this.mEnrollmentService.isEscrowTokenActive(handle, uid);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public void removeEscrowToken(long handle, int uid) {
        try {
            this.mEnrollmentService.removeEscrowToken(handle, uid);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void removeAllTrustedDevices(int uid) {
        try {
            this.mEnrollmentService.removeAllTrustedDevices(uid);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void setTrustedDeviceEnrollmentEnabled(boolean isEnabled) {
        try {
            this.mEnrollmentService.setTrustedDeviceEnrollmentEnabled(isEnabled);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void setTrustedDeviceUnlockEnabled(boolean isEnabled) {
        try {
            this.mEnrollmentService.setTrustedDeviceUnlockEnabled(isEnabled);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void setEnrollmentCallback(CarTrustAgentEnrollmentCallback callback) {
        if (callback == null) {
            unregisterEnrollmentCallback();
        } else {
            registerEnrollmentCallback(callback);
        }
    }

    private void registerEnrollmentCallback(CarTrustAgentEnrollmentCallback callback) {
        synchronized (this.mListenerLock) {
            if (callback != null) {
                if (this.mEnrollmentCallback == null) {
                    try {
                        this.mEnrollmentService.registerEnrollmentCallback(this.mListenerToEnrollmentService);
                        this.mEnrollmentCallback = callback;
                    } catch (RemoteException e) {
                        handleRemoteExceptionFromCarService(e);
                    }
                }
            }
        }
    }

    private void unregisterEnrollmentCallback() {
        synchronized (this.mListenerLock) {
            if (this.mEnrollmentCallback != null) {
                try {
                    this.mEnrollmentService.unregisterEnrollmentCallback(this.mListenerToEnrollmentService);
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                }
                this.mEnrollmentCallback = null;
            }
        }
    }

    public void setBleCallback(CarTrustAgentBleCallback callback) {
        if (callback == null) {
            unregisterBleCallback();
        } else {
            registerBleCallback(callback);
        }
    }

    private void registerBleCallback(CarTrustAgentBleCallback callback) {
        synchronized (this.mListenerLock) {
            if (callback != null) {
                if (this.mBleCallback == null) {
                    try {
                        this.mEnrollmentService.registerBleCallback(this.mListenerToBleService);
                        this.mBleCallback = callback;
                    } catch (RemoteException e) {
                        handleRemoteExceptionFromCarService(e);
                    }
                }
            }
        }
    }

    private void unregisterBleCallback() {
        synchronized (this.mListenerLock) {
            if (this.mBleCallback != null) {
                try {
                    this.mEnrollmentService.unregisterBleCallback(this.mListenerToBleService);
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                }
                this.mBleCallback = null;
            }
        }
    }

    public List<TrustedDeviceInfo> getEnrolledDeviceInfoForUser(int uid) {
        try {
            return this.mEnrollmentService.getEnrolledDeviceInfosForUser(uid);
        } catch (RemoteException e) {
            return (List) handleRemoteExceptionFromCarService(e, Collections.emptyList());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Handler getEventCallbackHandler() {
        return this.mEventCallbackHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ListenerToEnrollmentService extends ICarTrustAgentEnrollmentCallback.Stub {
        private final WeakReference<CarTrustAgentEnrollmentManager> mMgr;

        ListenerToEnrollmentService(CarTrustAgentEnrollmentManager mgr) {
            this.mMgr = new WeakReference<>(mgr);
        }

        @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
        public void onEnrollmentHandshakeFailure(BluetoothDevice device, int errorCode) {
            CarTrustAgentEnrollmentManager enrollmentManager = this.mMgr.get();
            if (enrollmentManager == null) {
                return;
            }
            enrollmentManager.getEventCallbackHandler().sendMessage(enrollmentManager.getEventCallbackHandler().obtainMessage(4, new AuthInfo(device, null, errorCode)));
        }

        @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
        public void onAuthStringAvailable(BluetoothDevice device, String authString) {
            CarTrustAgentEnrollmentManager enrollmentManager = this.mMgr.get();
            if (enrollmentManager == null) {
                return;
            }
            enrollmentManager.getEventCallbackHandler().sendMessage(enrollmentManager.getEventCallbackHandler().obtainMessage(5, new AuthInfo(device, authString, 0)));
        }

        @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
        public void onEscrowTokenAdded(long handle) {
            CarTrustAgentEnrollmentManager enrollmentManager = this.mMgr.get();
            if (enrollmentManager != null) {
                Message message = enrollmentManager.getEventCallbackHandler().obtainMessage(6);
                Bundle data = new Bundle();
                data.putLong(CarTrustAgentEnrollmentManager.KEY_HANDLE, handle);
                message.setData(data);
                enrollmentManager.getEventCallbackHandler().sendMessage(message);
            }
        }

        @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
        public void onEscrowTokenRemoved(long handle) {
            CarTrustAgentEnrollmentManager enrollmentManager = this.mMgr.get();
            if (enrollmentManager != null) {
                Message message = enrollmentManager.getEventCallbackHandler().obtainMessage(8);
                Bundle data = new Bundle();
                data.putLong(CarTrustAgentEnrollmentManager.KEY_HANDLE, handle);
                message.setData(data);
                enrollmentManager.getEventCallbackHandler().sendMessage(message);
            }
        }

        @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
        public void onEscrowTokenActiveStateChanged(long handle, boolean active) {
            CarTrustAgentEnrollmentManager enrollmentManager = this.mMgr.get();
            if (enrollmentManager != null) {
                Message message = enrollmentManager.getEventCallbackHandler().obtainMessage(7);
                Bundle data = new Bundle();
                data.putLong(CarTrustAgentEnrollmentManager.KEY_HANDLE, handle);
                data.putBoolean(CarTrustAgentEnrollmentManager.KEY_ACTIVE, active);
                message.setData(data);
                enrollmentManager.getEventCallbackHandler().sendMessage(message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ListenerToBleService extends ICarTrustAgentBleCallback.Stub {
        private final WeakReference<CarTrustAgentEnrollmentManager> mMgr;

        ListenerToBleService(CarTrustAgentEnrollmentManager mgr) {
            this.mMgr = new WeakReference<>(mgr);
        }

        @Override // android.car.trust.ICarTrustAgentBleCallback
        public void onEnrollmentAdvertisingStarted() {
            CarTrustAgentEnrollmentManager enrollmentManager = this.mMgr.get();
            if (enrollmentManager == null) {
                return;
            }
            enrollmentManager.getEventCallbackHandler().sendMessage(enrollmentManager.getEventCallbackHandler().obtainMessage(0));
        }

        @Override // android.car.trust.ICarTrustAgentBleCallback
        public void onEnrollmentAdvertisingFailed() {
            CarTrustAgentEnrollmentManager enrollmentManager = this.mMgr.get();
            if (enrollmentManager == null) {
                return;
            }
            enrollmentManager.getEventCallbackHandler().sendMessage(enrollmentManager.getEventCallbackHandler().obtainMessage(1));
        }

        @Override // android.car.trust.ICarTrustAgentBleCallback
        public void onBleEnrollmentDeviceConnected(BluetoothDevice device) {
            CarTrustAgentEnrollmentManager enrollmentManager = this.mMgr.get();
            if (enrollmentManager == null) {
                return;
            }
            enrollmentManager.getEventCallbackHandler().sendMessage(enrollmentManager.getEventCallbackHandler().obtainMessage(2, device));
        }

        @Override // android.car.trust.ICarTrustAgentBleCallback
        public void onBleEnrollmentDeviceDisconnected(BluetoothDevice device) {
            CarTrustAgentEnrollmentManager enrollmentManager = this.mMgr.get();
            if (enrollmentManager == null) {
                return;
            }
            enrollmentManager.getEventCallbackHandler().sendMessage(enrollmentManager.getEventCallbackHandler().obtainMessage(3, device));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class EventCallbackHandler extends Handler {
        private final WeakReference<CarTrustAgentEnrollmentManager> mEnrollmentManager;

        EventCallbackHandler(CarTrustAgentEnrollmentManager manager, Looper looper) {
            super(looper);
            this.mEnrollmentManager = new WeakReference<>(manager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            CarTrustAgentEnrollmentManager enrollmentManager = this.mEnrollmentManager.get();
            if (enrollmentManager != null) {
                switch (message.what) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        enrollmentManager.dispatchBleCallback(message);
                        return;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        enrollmentManager.dispatchEnrollmentCallback(message);
                        return;
                    default:
                        Log.e(CarTrustAgentEnrollmentManager.TAG, "Unknown message:" + message.what);
                        return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchBleCallback(Message message) {
        CarTrustAgentBleCallback bleCallback;
        synchronized (this.mListenerLock) {
            bleCallback = this.mBleCallback;
        }
        if (bleCallback == null) {
            return;
        }
        int i = message.what;
        if (i == 0) {
            bleCallback.onEnrollmentAdvertisingStarted();
        } else if (i == 1) {
            bleCallback.onEnrollmentAdvertisingFailed();
        } else if (i == 2) {
            bleCallback.onBleEnrollmentDeviceConnected((BluetoothDevice) message.obj);
        } else if (i == 3) {
            bleCallback.onBleEnrollmentDeviceDisconnected((BluetoothDevice) message.obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchEnrollmentCallback(Message message) {
        CarTrustAgentEnrollmentCallback enrollmentCallback;
        synchronized (this.mListenerLock) {
            enrollmentCallback = this.mEnrollmentCallback;
        }
        if (enrollmentCallback == null) {
            return;
        }
        switch (message.what) {
            case 4:
                AuthInfo auth = (AuthInfo) message.obj;
                enrollmentCallback.onEnrollmentHandshakeFailure(auth.mDevice, auth.mErrorCode);
                return;
            case 5:
                AuthInfo auth2 = (AuthInfo) message.obj;
                if (auth2.mDevice != null && auth2.mAuthString != null) {
                    enrollmentCallback.onAuthStringAvailable(auth2.mDevice, auth2.mAuthString);
                    return;
                }
                return;
            case 6:
                Bundle data = message.getData();
                if (data != null) {
                    enrollmentCallback.onEscrowTokenAdded(data.getLong(KEY_HANDLE));
                    return;
                }
                return;
            case 7:
                Bundle data2 = message.getData();
                if (data2 != null) {
                    enrollmentCallback.onEscrowTokenActiveStateChanged(data2.getLong(KEY_HANDLE), data2.getBoolean(KEY_ACTIVE));
                    return;
                }
                return;
            case 8:
                Bundle data3 = message.getData();
                if (data3 != null) {
                    enrollmentCallback.onEscrowTokenRemoved(data3.getLong(KEY_HANDLE));
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class AuthInfo {
        final String mAuthString;
        final BluetoothDevice mDevice;
        final int mErrorCode;

        AuthInfo(BluetoothDevice device, String authString, int errorCode) {
            this.mDevice = device;
            this.mAuthString = authString;
            this.mErrorCode = errorCode;
        }
    }
}
