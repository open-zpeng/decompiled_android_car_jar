package android.car.storagemonitoring;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IIoStatsListener extends IInterface {
    void onSnapshot(IoStats ioStats) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IIoStatsListener {
        private static final String DESCRIPTOR = "android.car.storagemonitoring.IIoStatsListener";
        static final int TRANSACTION_onSnapshot = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIoStatsListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IIoStatsListener)) {
                return (IIoStatsListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            IoStats _arg0;
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = IoStats.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            onSnapshot(_arg0);
            return true;
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IIoStatsListener {
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

            @Override // android.car.storagemonitoring.IIoStatsListener
            public void onSnapshot(IoStats snapshot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (snapshot != null) {
                        _data.writeInt(1);
                        snapshot.writeToParcel(_data, 0);
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