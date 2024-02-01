package android.car.intelligent;

import android.car.intelligent.ICarDrivingSceneListener;
import android.car.intelligent.ICarSceneListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarIntelligentEngine extends IInterface {
    int getCarDrivingSceneNRALevel() throws RemoteException;

    void registerCarDrivingSceneListener(ICarDrivingSceneListener iCarDrivingSceneListener) throws RemoteException;

    void registerCarSceneListener(ICarSceneListener iCarSceneListener) throws RemoteException;

    void setCarDrivingSceneNRALevel(int i) throws RemoteException;

    void unregisterCarDrivingSceneListener(ICarDrivingSceneListener iCarDrivingSceneListener) throws RemoteException;

    void unregisterCarSceneListener(ICarSceneListener iCarSceneListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarIntelligentEngine {
        @Override // android.car.intelligent.ICarIntelligentEngine
        public void registerCarSceneListener(ICarSceneListener listener) throws RemoteException {
        }

        @Override // android.car.intelligent.ICarIntelligentEngine
        public void unregisterCarSceneListener(ICarSceneListener listener) throws RemoteException {
        }

        @Override // android.car.intelligent.ICarIntelligentEngine
        public void registerCarDrivingSceneListener(ICarDrivingSceneListener listener) throws RemoteException {
        }

        @Override // android.car.intelligent.ICarIntelligentEngine
        public void unregisterCarDrivingSceneListener(ICarDrivingSceneListener listener) throws RemoteException {
        }

        @Override // android.car.intelligent.ICarIntelligentEngine
        public void setCarDrivingSceneNRALevel(int level) throws RemoteException {
        }

        @Override // android.car.intelligent.ICarIntelligentEngine
        public int getCarDrivingSceneNRALevel() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarIntelligentEngine {
        private static final String DESCRIPTOR = "android.car.intelligent.ICarIntelligentEngine";
        static final int TRANSACTION_getCarDrivingSceneNRALevel = 7;
        static final int TRANSACTION_registerCarDrivingSceneListener = 4;
        static final int TRANSACTION_registerCarSceneListener = 2;
        static final int TRANSACTION_setCarDrivingSceneNRALevel = 6;
        static final int TRANSACTION_unregisterCarDrivingSceneListener = 5;
        static final int TRANSACTION_unregisterCarSceneListener = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarIntelligentEngine asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarIntelligentEngine)) {
                return (ICarIntelligentEngine) iin;
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
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    ICarSceneListener _arg0 = ICarSceneListener.Stub.asInterface(data.readStrongBinder());
                    registerCarSceneListener(_arg0);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    ICarSceneListener _arg02 = ICarSceneListener.Stub.asInterface(data.readStrongBinder());
                    unregisterCarSceneListener(_arg02);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    ICarDrivingSceneListener _arg03 = ICarDrivingSceneListener.Stub.asInterface(data.readStrongBinder());
                    registerCarDrivingSceneListener(_arg03);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    ICarDrivingSceneListener _arg04 = ICarDrivingSceneListener.Stub.asInterface(data.readStrongBinder());
                    unregisterCarDrivingSceneListener(_arg04);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    setCarDrivingSceneNRALevel(_arg05);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getCarDrivingSceneNRALevel();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarIntelligentEngine {
            public static ICarIntelligentEngine sDefaultImpl;
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

            @Override // android.car.intelligent.ICarIntelligentEngine
            public void registerCarSceneListener(ICarSceneListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCarSceneListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.intelligent.ICarIntelligentEngine
            public void unregisterCarSceneListener(ICarSceneListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCarSceneListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.intelligent.ICarIntelligentEngine
            public void registerCarDrivingSceneListener(ICarDrivingSceneListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCarDrivingSceneListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.intelligent.ICarIntelligentEngine
            public void unregisterCarDrivingSceneListener(ICarDrivingSceneListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCarDrivingSceneListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.intelligent.ICarIntelligentEngine
            public void setCarDrivingSceneNRALevel(int level) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(level);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCarDrivingSceneNRALevel(level);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.intelligent.ICarIntelligentEngine
            public int getCarDrivingSceneNRALevel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarDrivingSceneNRALevel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarIntelligentEngine impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarIntelligentEngine getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
