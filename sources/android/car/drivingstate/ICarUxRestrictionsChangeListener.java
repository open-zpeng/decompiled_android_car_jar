package android.car.drivingstate;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarUxRestrictionsChangeListener extends IInterface {
    void onUxRestrictionsChanged(CarUxRestrictions carUxRestrictions) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarUxRestrictionsChangeListener {
        private static final String DESCRIPTOR = "android.car.drivingstate.ICarUxRestrictionsChangeListener";
        static final int TRANSACTION_onUxRestrictionsChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarUxRestrictionsChangeListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarUxRestrictionsChangeListener)) {
                return (ICarUxRestrictionsChangeListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CarUxRestrictions _arg0;
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = CarUxRestrictions.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            onUxRestrictionsChanged(_arg0);
            return true;
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarUxRestrictionsChangeListener {
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

            @Override // android.car.drivingstate.ICarUxRestrictionsChangeListener
            public void onUxRestrictionsChanged(CarUxRestrictions event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
