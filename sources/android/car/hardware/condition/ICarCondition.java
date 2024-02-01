package android.car.hardware.condition;

import android.car.hardware.condition.ICarConditionEventListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface ICarCondition extends IInterface {
    void registerCondition(List list, CarConditionInfo carConditionInfo, ICarConditionEventListener iCarConditionEventListener) throws RemoteException;

    void unregisterCondition(ICarConditionEventListener iCarConditionEventListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarCondition {
        @Override // android.car.hardware.condition.ICarCondition
        public void registerCondition(List propId, CarConditionInfo condition, ICarConditionEventListener listener) throws RemoteException {
        }

        @Override // android.car.hardware.condition.ICarCondition
        public void unregisterCondition(ICarConditionEventListener listener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarCondition {
        private static final String DESCRIPTOR = "android.car.hardware.condition.ICarCondition";
        static final int TRANSACTION_registerCondition = 1;
        static final int TRANSACTION_unregisterCondition = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarCondition asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarCondition)) {
                return (ICarCondition) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CarConditionInfo _arg1;
            if (code != 1) {
                if (code != 2) {
                    if (code == 1598968902) {
                        reply.writeString(DESCRIPTOR);
                        return true;
                    }
                    return super.onTransact(code, data, reply, flags);
                }
                data.enforceInterface(DESCRIPTOR);
                ICarConditionEventListener _arg0 = ICarConditionEventListener.Stub.asInterface(data.readStrongBinder());
                unregisterCondition(_arg0);
                return true;
            }
            data.enforceInterface(DESCRIPTOR);
            ClassLoader cl = getClass().getClassLoader();
            List _arg02 = data.readArrayList(cl);
            if (data.readInt() != 0) {
                _arg1 = CarConditionInfo.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            ICarConditionEventListener _arg2 = ICarConditionEventListener.Stub.asInterface(data.readStrongBinder());
            registerCondition(_arg02, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarCondition {
            public static ICarCondition sDefaultImpl;
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

            @Override // android.car.hardware.condition.ICarCondition
            public void registerCondition(List propId, CarConditionInfo condition, ICarConditionEventListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeList(propId);
                    if (condition != null) {
                        _data.writeInt(1);
                        condition.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCondition(propId, condition, listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.condition.ICarCondition
            public void unregisterCondition(ICarConditionEventListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCondition(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarCondition impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarCondition getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
