package android.car.hardware.condition;

import android.car.hardware.CarPropertyValue;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarConditionEventListener extends IInterface {
    void onChangeEvent(CarPropertyValue carPropertyValue) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarConditionEventListener {
        @Override // android.car.hardware.condition.ICarConditionEventListener
        public void onChangeEvent(CarPropertyValue value) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarConditionEventListener {
        private static final String DESCRIPTOR = "android.car.hardware.condition.ICarConditionEventListener";
        static final int TRANSACTION_onChangeEvent = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarConditionEventListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarConditionEventListener)) {
                return (ICarConditionEventListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CarPropertyValue _arg0;
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = CarPropertyValue.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            onChangeEvent(_arg0);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarConditionEventListener {
            public static ICarConditionEventListener sDefaultImpl;
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

            @Override // android.car.hardware.condition.ICarConditionEventListener
            public void onChangeEvent(CarPropertyValue value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (value != null) {
                        _data.writeInt(1);
                        value.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onChangeEvent(value);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarConditionEventListener impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarConditionEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
