package android.car;

import android.car.ICarBluetoothUserService;
import android.car.ILocationManagerProxy;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarUserService extends IInterface {
    ICarBluetoothUserService getBluetoothUserService() throws RemoteException;

    ILocationManagerProxy getLocationManagerProxy() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarUserService {
        @Override // android.car.ICarUserService
        public ICarBluetoothUserService getBluetoothUserService() throws RemoteException {
            return null;
        }

        @Override // android.car.ICarUserService
        public ILocationManagerProxy getLocationManagerProxy() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarUserService {
        private static final String DESCRIPTOR = "android.car.ICarUserService";
        static final int TRANSACTION_getBluetoothUserService = 1;
        static final int TRANSACTION_getLocationManagerProxy = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarUserService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarUserService)) {
                return (ICarUserService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                ICarBluetoothUserService _result = getBluetoothUserService();
                reply.writeNoException();
                reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                return true;
            } else if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            } else {
                data.enforceInterface(DESCRIPTOR);
                ILocationManagerProxy _result2 = getLocationManagerProxy();
                reply.writeNoException();
                reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarUserService {
            public static ICarUserService sDefaultImpl;
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

            @Override // android.car.ICarUserService
            public ICarBluetoothUserService getBluetoothUserService() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBluetoothUserService();
                    }
                    _reply.readException();
                    ICarBluetoothUserService _result = ICarBluetoothUserService.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarUserService
            public ILocationManagerProxy getLocationManagerProxy() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLocationManagerProxy();
                    }
                    _reply.readException();
                    ILocationManagerProxy _result = ILocationManagerProxy.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarUserService impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarUserService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
