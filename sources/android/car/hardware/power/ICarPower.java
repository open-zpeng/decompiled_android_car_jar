package android.car.hardware.power;

import android.car.hardware.power.ICarPowerStateListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarPower extends IInterface {
    void finished(ICarPowerStateListener iCarPowerStateListener, int i) throws RemoteException;

    int getBootReason() throws RemoteException;

    int getCarBatteryState() throws RemoteException;

    void registerListener(ICarPowerStateListener iCarPowerStateListener) throws RemoteException;

    void requestShutdownOnNextSuspend() throws RemoteException;

    void unregisterListener(ICarPowerStateListener iCarPowerStateListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarPower {
        private static final String DESCRIPTOR = "android.car.hardware.power.ICarPower";
        static final int TRANSACTION_finished = 5;
        static final int TRANSACTION_getBootReason = 4;
        static final int TRANSACTION_getCarBatteryState = 6;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_requestShutdownOnNextSuspend = 3;
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
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    ICarPowerStateListener _arg02 = ICarPowerStateListener.Stub.asInterface(data.readStrongBinder());
                    unregisterListener(_arg02);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    requestShutdownOnNextSuspend();
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getBootReason();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    ICarPowerStateListener _arg03 = ICarPowerStateListener.Stub.asInterface(data.readStrongBinder());
                    int _arg1 = data.readInt();
                    finished(_arg03, _arg1);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = getCarBatteryState();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarPower {
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
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.power.ICarPower
            public void unregisterListener(ICarPowerStateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.power.ICarPower
            public void requestShutdownOnNextSuspend() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
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
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.power.ICarPower
            public void finished(ICarPowerStateListener listener, int token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeInt(token);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.power.ICarPower
            public int getCarBatteryState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
