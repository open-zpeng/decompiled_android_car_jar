package android.car;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarBluetoothUserService extends IInterface {
    boolean bluetoothConnectToProfile(int i, BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean bluetoothDisconnectFromProfile(int i, BluetoothDevice bluetoothDevice) throws RemoteException;

    void closeBluetoothConnectionProxies() throws RemoteException;

    int getProfilePriority(int i, BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean isBluetoothConnectionProxyAvailable(int i) throws RemoteException;

    void setProfilePriority(int i, BluetoothDevice bluetoothDevice, int i2) throws RemoteException;

    void setupBluetoothConnectionProxies() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarBluetoothUserService {
        @Override // android.car.ICarBluetoothUserService
        public void setupBluetoothConnectionProxies() throws RemoteException {
        }

        @Override // android.car.ICarBluetoothUserService
        public void closeBluetoothConnectionProxies() throws RemoteException {
        }

        @Override // android.car.ICarBluetoothUserService
        public boolean isBluetoothConnectionProxyAvailable(int profile) throws RemoteException {
            return false;
        }

        @Override // android.car.ICarBluetoothUserService
        public boolean bluetoothConnectToProfile(int profile, BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // android.car.ICarBluetoothUserService
        public boolean bluetoothDisconnectFromProfile(int profile, BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // android.car.ICarBluetoothUserService
        public int getProfilePriority(int profile, BluetoothDevice device) throws RemoteException {
            return 0;
        }

        @Override // android.car.ICarBluetoothUserService
        public void setProfilePriority(int profile, BluetoothDevice device, int priority) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarBluetoothUserService {
        private static final String DESCRIPTOR = "android.car.ICarBluetoothUserService";
        static final int TRANSACTION_bluetoothConnectToProfile = 4;
        static final int TRANSACTION_bluetoothDisconnectFromProfile = 5;
        static final int TRANSACTION_closeBluetoothConnectionProxies = 2;
        static final int TRANSACTION_getProfilePriority = 6;
        static final int TRANSACTION_isBluetoothConnectionProxyAvailable = 3;
        static final int TRANSACTION_setProfilePriority = 7;
        static final int TRANSACTION_setupBluetoothConnectionProxies = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarBluetoothUserService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarBluetoothUserService)) {
                return (ICarBluetoothUserService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BluetoothDevice _arg1;
            BluetoothDevice _arg12;
            BluetoothDevice _arg13;
            BluetoothDevice _arg14;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    setupBluetoothConnectionProxies();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    closeBluetoothConnectionProxies();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    boolean isBluetoothConnectionProxyAvailable = isBluetoothConnectionProxyAvailable(_arg0);
                    reply.writeNoException();
                    reply.writeInt(isBluetoothConnectionProxyAvailable ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg1 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    boolean bluetoothConnectToProfile = bluetoothConnectToProfile(_arg02, _arg1);
                    reply.writeNoException();
                    reply.writeInt(bluetoothConnectToProfile ? 1 : 0);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg12 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    boolean bluetoothDisconnectFromProfile = bluetoothDisconnectFromProfile(_arg03, _arg12);
                    reply.writeNoException();
                    reply.writeInt(bluetoothDisconnectFromProfile ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg13 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }
                    int _result = getProfilePriority(_arg04, _arg13);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg14 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg14 = null;
                    }
                    int _arg2 = data.readInt();
                    setProfilePriority(_arg05, _arg14, _arg2);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarBluetoothUserService {
            public static ICarBluetoothUserService sDefaultImpl;
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

            @Override // android.car.ICarBluetoothUserService
            public void setupBluetoothConnectionProxies() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setupBluetoothConnectionProxies();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetoothUserService
            public void closeBluetoothConnectionProxies() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeBluetoothConnectionProxies();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetoothUserService
            public boolean isBluetoothConnectionProxyAvailable(int profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profile);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBluetoothConnectionProxyAvailable(profile);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetoothUserService
            public boolean bluetoothConnectToProfile(int profile, BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profile);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().bluetoothConnectToProfile(profile, device);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetoothUserService
            public boolean bluetoothDisconnectFromProfile(int profile, BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profile);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().bluetoothDisconnectFromProfile(profile, device);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetoothUserService
            public int getProfilePriority(int profile, BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profile);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfilePriority(profile, device);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetoothUserService
            public void setProfilePriority(int profile, BluetoothDevice device, int priority) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profile);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(priority);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setProfilePriority(profile, device, priority);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarBluetoothUserService impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarBluetoothUserService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
