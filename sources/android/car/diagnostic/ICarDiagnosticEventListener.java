package android.car.diagnostic;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface ICarDiagnosticEventListener extends IInterface {
    void onDiagnosticEvents(List<CarDiagnosticEvent> list) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarDiagnosticEventListener {
        @Override // android.car.diagnostic.ICarDiagnosticEventListener
        public void onDiagnosticEvents(List<CarDiagnosticEvent> events) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarDiagnosticEventListener {
        private static final String DESCRIPTOR = "android.car.diagnostic.ICarDiagnosticEventListener";
        static final int TRANSACTION_onDiagnosticEvents = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarDiagnosticEventListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarDiagnosticEventListener)) {
                return (ICarDiagnosticEventListener) iin;
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
            List<CarDiagnosticEvent> _arg0 = data.createTypedArrayList(CarDiagnosticEvent.CREATOR);
            onDiagnosticEvents(_arg0);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarDiagnosticEventListener {
            public static ICarDiagnosticEventListener sDefaultImpl;
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

            @Override // android.car.diagnostic.ICarDiagnosticEventListener
            public void onDiagnosticEvents(List<CarDiagnosticEvent> events) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(events);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDiagnosticEvents(events);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarDiagnosticEventListener impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarDiagnosticEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
