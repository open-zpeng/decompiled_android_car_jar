package android.car.hardware.power;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarPowerStateListener extends IInterface {
    void onStateChanged(int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarPowerStateListener {
        private static final String DESCRIPTOR = "android.car.hardware.power.ICarPowerStateListener";
        static final int TRANSACTION_onStateChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPowerStateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarPowerStateListener)) {
                return (ICarPowerStateListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            onStateChanged(_arg0, _arg1);
            return true;
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarPowerStateListener {
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

            @Override // android.car.hardware.power.ICarPowerStateListener
            public void onStateChanged(int state, int token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeInt(token);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
