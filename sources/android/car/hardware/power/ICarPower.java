package android.car.hardware.power;

import android.car.hardware.power.ICarPowerStateListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarPower extends IInterface {
    void finished(ICarPowerStateListener iCarPowerStateListener) throws RemoteException;

    int getBootReason() throws RemoteException;

    void registerListener(ICarPowerStateListener iCarPowerStateListener) throws RemoteException;

    void registerListenerWithCompletion(ICarPowerStateListener iCarPowerStateListener) throws RemoteException;

    void requestShutdownOnNextSuspend() throws RemoteException;

    void scheduleNextWakeupTime(int i) throws RemoteException;

    void unregisterListener(ICarPowerStateListener iCarPowerStateListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarPower {
        @Override // android.car.hardware.power.ICarPower
        public void registerListener(ICarPowerStateListener listener) throws RemoteException {
        }

        @Override // android.car.hardware.power.ICarPower
        public void unregisterListener(ICarPowerStateListener listener) throws RemoteException {
        }

        @Override // android.car.hardware.power.ICarPower
        public void requestShutdownOnNextSuspend() throws RemoteException {
        }

        @Override // android.car.hardware.power.ICarPower
        public void finished(ICarPowerStateListener listener) throws RemoteException {
        }

        @Override // android.car.hardware.power.ICarPower
        public void scheduleNextWakeupTime(int seconds) throws RemoteException {
        }

        @Override // android.car.hardware.power.ICarPower
        public void registerListenerWithCompletion(ICarPowerStateListener listener) throws RemoteException {
        }

        @Override // android.car.hardware.power.ICarPower
        public int getBootReason() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarPower {
        private static final String DESCRIPTOR = "android.car.hardware.power.ICarPower";
        static final int TRANSACTION_finished = 4;
        static final int TRANSACTION_getBootReason = 7;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_registerListenerWithCompletion = 6;
        static final int TRANSACTION_requestShutdownOnNextSuspend = 3;
        static final int TRANSACTION_scheduleNextWakeupTime = 5;
        static final int TRANSACTION_unregisterListener = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPower asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarPower)) {
                return (ICarPower) iin;
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
                    ICarPowerStateListener _arg0 = ICarPowerStateListener.Stub.asInterface(data.readStrongBinder());
                    registerListener(_arg0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    ICarPowerStateListener _arg02 = ICarPowerStateListener.Stub.asInterface(data.readStrongBinder());
                    unregisterListener(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    requestShutdownOnNextSuspend();
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    ICarPowerStateListener _arg03 = ICarPowerStateListener.Stub.asInterface(data.readStrongBinder());
                    finished(_arg03);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    scheduleNextWakeupTime(_arg04);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    ICarPowerStateListener _arg05 = ICarPowerStateListener.Stub.asInterface(data.readStrongBinder());
                    registerListenerWithCompletion(_arg05);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getBootReason();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarPower {
            public static ICarPower sDefaultImpl;
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

            @Override // android.car.hardware.power.ICarPower
            public void registerListener(ICarPowerStateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.power.ICarPower
            public void unregisterListener(ICarPowerStateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.power.ICarPower
            public void requestShutdownOnNextSuspend() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestShutdownOnNextSuspend();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.power.ICarPower
            public void finished(ICarPowerStateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finished(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.power.ICarPower
            public void scheduleNextWakeupTime(int seconds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(seconds);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().scheduleNextWakeupTime(seconds);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.power.ICarPower
            public void registerListenerWithCompletion(ICarPowerStateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListenerWithCompletion(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.power.ICarPower
            public int getBootReason() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBootReason();
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

        public static boolean setDefaultImpl(ICarPower impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarPower getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
