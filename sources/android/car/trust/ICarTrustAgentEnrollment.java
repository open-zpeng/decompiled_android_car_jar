package android.car.trust;

import android.bluetooth.BluetoothDevice;
import android.car.trust.ICarTrustAgentBleCallback;
import android.car.trust.ICarTrustAgentEnrollmentCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface ICarTrustAgentEnrollment extends IInterface {
    void enrollmentHandshakeAccepted(BluetoothDevice bluetoothDevice) throws RemoteException;

    List<TrustedDeviceInfo> getEnrolledDeviceInfosForUser(int i) throws RemoteException;

    boolean isEscrowTokenActive(long j, int i) throws RemoteException;

    void registerBleCallback(ICarTrustAgentBleCallback iCarTrustAgentBleCallback) throws RemoteException;

    void registerEnrollmentCallback(ICarTrustAgentEnrollmentCallback iCarTrustAgentEnrollmentCallback) throws RemoteException;

    void removeAllTrustedDevices(int i) throws RemoteException;

    void removeEscrowToken(long j, int i) throws RemoteException;

    void setTrustedDeviceEnrollmentEnabled(boolean z) throws RemoteException;

    void setTrustedDeviceUnlockEnabled(boolean z) throws RemoteException;

    void startEnrollmentAdvertising() throws RemoteException;

    void stopEnrollmentAdvertising() throws RemoteException;

    void terminateEnrollmentHandshake() throws RemoteException;

    void unregisterBleCallback(ICarTrustAgentBleCallback iCarTrustAgentBleCallback) throws RemoteException;

    void unregisterEnrollmentCallback(ICarTrustAgentEnrollmentCallback iCarTrustAgentEnrollmentCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarTrustAgentEnrollment {
        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void startEnrollmentAdvertising() throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void stopEnrollmentAdvertising() throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void enrollmentHandshakeAccepted(BluetoothDevice device) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void terminateEnrollmentHandshake() throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public boolean isEscrowTokenActive(long handle, int uid) throws RemoteException {
            return false;
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void removeEscrowToken(long handle, int uid) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void removeAllTrustedDevices(int uid) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void setTrustedDeviceEnrollmentEnabled(boolean enable) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void setTrustedDeviceUnlockEnabled(boolean enable) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public List<TrustedDeviceInfo> getEnrolledDeviceInfosForUser(int uid) throws RemoteException {
            return null;
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void registerEnrollmentCallback(ICarTrustAgentEnrollmentCallback callback) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void unregisterEnrollmentCallback(ICarTrustAgentEnrollmentCallback callback) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void registerBleCallback(ICarTrustAgentBleCallback callback) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollment
        public void unregisterBleCallback(ICarTrustAgentBleCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarTrustAgentEnrollment {
        private static final String DESCRIPTOR = "android.car.trust.ICarTrustAgentEnrollment";
        static final int TRANSACTION_enrollmentHandshakeAccepted = 3;
        static final int TRANSACTION_getEnrolledDeviceInfosForUser = 10;
        static final int TRANSACTION_isEscrowTokenActive = 5;
        static final int TRANSACTION_registerBleCallback = 13;
        static final int TRANSACTION_registerEnrollmentCallback = 11;
        static final int TRANSACTION_removeAllTrustedDevices = 7;
        static final int TRANSACTION_removeEscrowToken = 6;
        static final int TRANSACTION_setTrustedDeviceEnrollmentEnabled = 8;
        static final int TRANSACTION_setTrustedDeviceUnlockEnabled = 9;
        static final int TRANSACTION_startEnrollmentAdvertising = 1;
        static final int TRANSACTION_stopEnrollmentAdvertising = 2;
        static final int TRANSACTION_terminateEnrollmentHandshake = 4;
        static final int TRANSACTION_unregisterBleCallback = 14;
        static final int TRANSACTION_unregisterEnrollmentCallback = 12;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarTrustAgentEnrollment asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarTrustAgentEnrollment)) {
                return (ICarTrustAgentEnrollment) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BluetoothDevice _arg0;
            boolean _arg02;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    startEnrollmentAdvertising();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    stopEnrollmentAdvertising();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    enrollmentHandshakeAccepted(_arg0);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    terminateEnrollmentHandshake();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg03 = data.readLong();
                    int _arg1 = data.readInt();
                    boolean isEscrowTokenActive = isEscrowTokenActive(_arg03, _arg1);
                    reply.writeNoException();
                    reply.writeInt(isEscrowTokenActive ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg04 = data.readLong();
                    int _arg12 = data.readInt();
                    removeEscrowToken(_arg04, _arg12);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    removeAllTrustedDevices(data.readInt());
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    _arg02 = data.readInt() != 0;
                    setTrustedDeviceEnrollmentEnabled(_arg02);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    _arg02 = data.readInt() != 0;
                    setTrustedDeviceUnlockEnabled(_arg02);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    List<TrustedDeviceInfo> _result = getEnrolledDeviceInfosForUser(data.readInt());
                    reply.writeNoException();
                    reply.writeTypedList(_result);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    registerEnrollmentCallback(ICarTrustAgentEnrollmentCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterEnrollmentCallback(ICarTrustAgentEnrollmentCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    registerBleCallback(ICarTrustAgentBleCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterBleCallback(ICarTrustAgentBleCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarTrustAgentEnrollment {
            public static ICarTrustAgentEnrollment sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void startEnrollmentAdvertising() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startEnrollmentAdvertising();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void stopEnrollmentAdvertising() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopEnrollmentAdvertising();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void enrollmentHandshakeAccepted(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enrollmentHandshakeAccepted(device);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void terminateEnrollmentHandshake() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().terminateEnrollmentHandshake();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public boolean isEscrowTokenActive(long handle, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEscrowTokenActive(handle, uid);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void removeEscrowToken(long handle, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeEscrowToken(handle, uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void removeAllTrustedDevices(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeAllTrustedDevices(uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void setTrustedDeviceEnrollmentEnabled(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTrustedDeviceEnrollmentEnabled(enable);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void setTrustedDeviceUnlockEnabled(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTrustedDeviceUnlockEnabled(enable);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public List<TrustedDeviceInfo> getEnrolledDeviceInfosForUser(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnrolledDeviceInfosForUser(uid);
                    }
                    _reply.readException();
                    List<TrustedDeviceInfo> _result = _reply.createTypedArrayList(TrustedDeviceInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void registerEnrollmentCallback(ICarTrustAgentEnrollmentCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerEnrollmentCallback(callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void unregisterEnrollmentCallback(ICarTrustAgentEnrollmentCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterEnrollmentCallback(callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void registerBleCallback(ICarTrustAgentBleCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerBleCallback(callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollment
            public void unregisterBleCallback(ICarTrustAgentBleCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterBleCallback(callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarTrustAgentEnrollment impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarTrustAgentEnrollment getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
