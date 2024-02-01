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

    void injectDrivingState(CarDrivingStateEvent carDrivingStateEvent) throws RemoteException;

    void registerDrivingStateChangeListener(ICarDrivingStateChangeListener iCarDrivingStateChangeListener) throws RemoteException;

    void unregisterDrivingStateChangeListener(ICarDrivingStateChangeListener iCarDrivingStateChangeListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarDrivingState {
        @Override // android.car.drivingstate.ICarDrivingState
        public void registerDrivingStateChangeListener(ICarDrivingStateChangeListener listener) throws RemoteException {
        }

        @Override // android.car.drivingstate.ICarDrivingState
        public void unregisterDrivingStateChangeListener(ICarDrivingStateChangeListener listener) throws RemoteException {
        }

        @Override // android.car.drivingstate.ICarDrivingState
        public CarDrivingStateEvent getCurrentDrivingState() throws RemoteException {
            return null;
        }

        @Override // android.car.drivingstate.ICarDrivingState
        public void injectDrivingState(CarDrivingStateEvent event) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarDrivingState {
        private static final String DESCRIPTOR = "android.car.drivingstate.ICarDrivingState";
        static final int TRANSACTION_getCurrentDrivingState = 3;
        static final int TRANSACTION_injectDrivingState = 4;
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
            CarDrivingStateEvent _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                ICarDrivingStateChangeListener _arg02 = ICarDrivingStateChangeListener.Stub.asInterface(data.readStrongBinder());
                registerDrivingStateChangeListener(_arg02);
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                ICarDrivingStateChangeListener _arg03 = ICarDrivingStateChangeListener.Stub.asInterface(data.readStrongBinder());
                unregisterDrivingStateChangeListener(_arg03);
                reply.writeNoException();
                return true;
            } else if (code == 3) {
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
            } else if (code != 4) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            } else {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = CarDrivingStateEvent.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                injectDrivingState(_arg0);
                reply.writeNoException();
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarDrivingState {
            public static ICarDrivingState sDefaultImpl;
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
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerDrivingStateChangeListener(listener);
                    } else {
                        _reply.readException();
                    }
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
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterDrivingStateChangeListener(listener);
                    } else {
                        _reply.readException();
                    }
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
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentDrivingState();
                    }
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

            @Override // android.car.drivingstate.ICarDrivingState
            public void injectDrivingState(CarDrivingStateEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().injectDrivingState(event);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarDrivingState impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarDrivingState getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
