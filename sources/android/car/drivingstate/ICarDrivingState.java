package android.car.drivingstate;

import android.car.drivingstate.ICarDrivingStateChangeListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarDrivingState extends IInterface {
    CarDrivingStateEvent getCurrentDrivingState() throws RemoteException;

    void registerDrivingStateChangeListener(ICarDrivingStateChangeListener iCarDrivingStateChangeListener) throws RemoteException;

    void unregisterDrivingStateChangeListener(ICarDrivingStateChangeListener iCarDrivingStateChangeListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarDrivingState {
        private static final String DESCRIPTOR = "android.car.drivingstate.ICarDrivingState";
        static final int TRANSACTION_getCurrentDrivingState = 3;
        static final int TRANSACTION_registerDrivingStateChangeListener = 1;
        static final int TRANSACTION_unregisterDrivingStateChangeListener = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarDrivingState asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarDrivingState)) {
                return (ICarDrivingState) iin;
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
                    ICarDrivingStateChangeListener _arg0 = ICarDrivingStateChangeListener.Stub.asInterface(data.readStrongBinder());
                    registerDrivingStateChangeListener(_arg0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    ICarDrivingStateChangeListener _arg02 = ICarDrivingStateChangeListener.Stub.asInterface(data.readStrongBinder());
                    unregisterDrivingStateChangeListener(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    CarDrivingStateEvent _result = getCurrentDrivingState();
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
        private static class Proxy implements ICarDrivingState {
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

            @Override // android.car.drivingstate.ICarDrivingState
            public void registerDrivingStateChangeListener(ICarDrivingStateChangeListener listener) throws RemoteException {
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

            @Override // android.car.drivingstate.ICarDrivingState
            public void unregisterDrivingStateChangeListener(ICarDrivingStateChangeListener listener) throws RemoteException {
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

            @Override // android.car.drivingstate.ICarDrivingState
            public CarDrivingStateEvent getCurrentDrivingState() throws RemoteException {
                CarDrivingStateEvent _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarDrivingStateEvent.CREATOR.createFromParcel(_reply);
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
