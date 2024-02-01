package android.car;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ILocationManagerProxy extends IInterface {
    Location getLastKnownLocation(String str) throws RemoteException;

    boolean injectLocation(Location location) throws RemoteException;

    boolean isLocationEnabled() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ILocationManagerProxy {
        @Override // android.car.ILocationManagerProxy
        public boolean isLocationEnabled() throws RemoteException {
            return false;
        }

        @Override // android.car.ILocationManagerProxy
        public boolean injectLocation(Location location) throws RemoteException {
            return false;
        }

        @Override // android.car.ILocationManagerProxy
        public Location getLastKnownLocation(String provider) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ILocationManagerProxy {
        private static final String DESCRIPTOR = "android.car.ILocationManagerProxy";
        static final int TRANSACTION_getLastKnownLocation = 3;
        static final int TRANSACTION_injectLocation = 2;
        static final int TRANSACTION_isLocationEnabled = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationManagerProxy asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ILocationManagerProxy)) {
                return (ILocationManagerProxy) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Location _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                boolean isLocationEnabled = isLocationEnabled();
                reply.writeNoException();
                reply.writeInt(isLocationEnabled ? 1 : 0);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = (Location) Location.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                boolean injectLocation = injectLocation(_arg0);
                reply.writeNoException();
                reply.writeInt(injectLocation ? 1 : 0);
                return true;
            } else if (code != 3) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            } else {
                data.enforceInterface(DESCRIPTOR);
                String _arg02 = data.readString();
                Location _result = getLastKnownLocation(_arg02);
                reply.writeNoException();
                if (_result != null) {
                    reply.writeInt(1);
                    _result.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ILocationManagerProxy {
            public static ILocationManagerProxy sDefaultImpl;
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

            @Override // android.car.ILocationManagerProxy
            public boolean isLocationEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLocationEnabled();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ILocationManagerProxy
            public boolean injectLocation(Location location) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (location != null) {
                        _data.writeInt(1);
                        location.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().injectLocation(location);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ILocationManagerProxy
            public Location getLastKnownLocation(String provider) throws RemoteException {
                Location _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastKnownLocation(provider);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Location) Location.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationManagerProxy impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ILocationManagerProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
