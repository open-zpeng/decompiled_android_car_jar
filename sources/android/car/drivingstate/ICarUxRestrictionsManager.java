package android.car.drivingstate;

import android.car.drivingstate.ICarUxRestrictionsChangeListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarUxRestrictionsManager extends IInterface {
    CarUxRestrictions getCurrentUxRestrictions() throws RemoteException;

    void registerUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener iCarUxRestrictionsChangeListener) throws RemoteException;

    void unregisterUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener iCarUxRestrictionsChangeListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarUxRestrictionsManager {
        private static final String DESCRIPTOR = "android.car.drivingstate.ICarUxRestrictionsManager";
        static final int TRANSACTION_getCurrentUxRestrictions = 3;
        static final int TRANSACTION_registerUxRestrictionsChangeListener = 1;
        static final int TRANSACTION_unregisterUxRestrictionsChangeListener = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarUxRestrictionsManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarUxRestrictionsManager)) {
                return (ICarUxRestrictionsManager) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    ICarUxRestrictionsChangeListener _arg0 = ICarUxRestrictionsChangeListener.Stub.asInterface(data.readStrongBinder());
                    registerUxRestrictionsChangeListener(_arg0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    ICarUxRestrictionsChangeListener _arg02 = ICarUxRestrictionsChangeListener.Stub.asInterface(data.readStrongBinder());
                    unregisterUxRestrictionsChangeListener(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    CarUxRestrictions _result = getCurrentUxRestrictions();
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarUxRestrictionsManager {
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

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public void registerUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public void unregisterUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public CarUxRestrictions getCurrentUxRestrictions() throws RemoteException {
                CarUxRestrictions _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarUxRestrictions.CREATOR.createFromParcel(_reply);
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
    }
}
