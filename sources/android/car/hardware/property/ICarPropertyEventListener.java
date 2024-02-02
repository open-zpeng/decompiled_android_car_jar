package android.car.hardware.property;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface ICarPropertyEventListener extends IInterface {
    void onEvent(List<CarPropertyEvent> list) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarPropertyEventListener {
        private static final String DESCRIPTOR = "android.car.hardware.property.ICarPropertyEventListener";
        static final int TRANSACTION_onEvent = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPropertyEventListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarPropertyEventListener)) {
                return (ICarPropertyEventListener) iin;
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
            List<CarPropertyEvent> _arg0 = data.createTypedArrayList(CarPropertyEvent.CREATOR);
            onEvent(_arg0);
            return true;
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarPropertyEventListener {
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

            @Override // android.car.hardware.property.ICarPropertyEventListener
            public void onEvent(List<CarPropertyEvent> events) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(events);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
