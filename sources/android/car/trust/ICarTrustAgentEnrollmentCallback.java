package android.car.trust;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarTrustAgentEnrollmentCallback extends IInterface {
    void onAuthStringAvailable(BluetoothDevice bluetoothDevice, String str) throws RemoteException;

    void onEnrollmentHandshakeFailure(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    void onEscrowTokenActiveStateChanged(long j, boolean z) throws RemoteException;

    void onEscrowTokenAdded(long j) throws RemoteException;

    void onEscrowTokenRemoved(long j) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarTrustAgentEnrollmentCallback {
        @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
        public void onEnrollmentHandshakeFailure(BluetoothDevice device, int errorCode) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
        public void onAuthStringAvailable(BluetoothDevice device, String authString) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
        public void onEscrowTokenAdded(long handle) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
        public void onEscrowTokenRemoved(long handle) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
        public void onEscrowTokenActiveStateChanged(long handle, boolean active) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarTrustAgentEnrollmentCallback {
        private static final String DESCRIPTOR = "android.car.trust.ICarTrustAgentEnrollmentCallback";
        static final int TRANSACTION_onAuthStringAvailable = 2;
        static final int TRANSACTION_onEnrollmentHandshakeFailure = 1;
        static final int TRANSACTION_onEscrowTokenActiveStateChanged = 5;
        static final int TRANSACTION_onEscrowTokenAdded = 3;
        static final int TRANSACTION_onEscrowTokenRemoved = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarTrustAgentEnrollmentCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarTrustAgentEnrollmentCallback)) {
                return (ICarTrustAgentEnrollmentCallback) iin;
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
            BluetoothDevice _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                int _arg1 = data.readInt();
                onEnrollmentHandshakeFailure(_arg0, _arg1);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg02 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                } else {
                    _arg02 = null;
                }
                String _arg12 = data.readString();
                onAuthStringAvailable(_arg02, _arg12);
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                long _arg03 = data.readLong();
                onEscrowTokenAdded(_arg03);
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                long _arg04 = data.readLong();
                onEscrowTokenRemoved(_arg04);
                return true;
            } else if (code != 5) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            } else {
                data.enforceInterface(DESCRIPTOR);
                long _arg05 = data.readLong();
                boolean _arg13 = data.readInt() != 0;
                onEscrowTokenActiveStateChanged(_arg05, _arg13);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarTrustAgentEnrollmentCallback {
            public static ICarTrustAgentEnrollmentCallback sDefaultImpl;
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

            @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
            public void onEnrollmentHandshakeFailure(BluetoothDevice device, int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(errorCode);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnrollmentHandshakeFailure(device, errorCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
            public void onAuthStringAvailable(BluetoothDevice device, String authString) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(authString);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAuthStringAvailable(device, authString);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
            public void onEscrowTokenAdded(long handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEscrowTokenAdded(handle);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
            public void onEscrowTokenRemoved(long handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEscrowTokenRemoved(handle);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentEnrollmentCallback
            public void onEscrowTokenActiveStateChanged(long handle, boolean active) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(active ? 1 : 0);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEscrowTokenActiveStateChanged(handle, active);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarTrustAgentEnrollmentCallback impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarTrustAgentEnrollmentCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
