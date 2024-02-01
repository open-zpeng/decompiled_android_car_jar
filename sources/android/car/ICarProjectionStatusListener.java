package android.car;

import android.car.projection.ProjectionStatus;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface ICarProjectionStatusListener extends IInterface {
    void onProjectionStatusChanged(int i, String str, List<ProjectionStatus> list) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarProjectionStatusListener {
        @Override // android.car.ICarProjectionStatusListener
        public void onProjectionStatusChanged(int projectionState, String activeProjectionPackageName, List<ProjectionStatus> details) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarProjectionStatusListener {
        private static final String DESCRIPTOR = "android.car.ICarProjectionStatusListener";
        static final int TRANSACTION_onProjectionStatusChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarProjectionStatusListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarProjectionStatusListener)) {
                return (ICarProjectionStatusListener) iin;
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
            String _arg1 = data.readString();
            List<ProjectionStatus> _arg2 = data.createTypedArrayList(ProjectionStatus.CREATOR);
            onProjectionStatusChanged(_arg0, _arg1, _arg2);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarProjectionStatusListener {
            public static ICarProjectionStatusListener sDefaultImpl;
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

            @Override // android.car.ICarProjectionStatusListener
            public void onProjectionStatusChanged(int projectionState, String activeProjectionPackageName, List<ProjectionStatus> details) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(projectionState);
                    _data.writeString(activeProjectionPackageName);
                    _data.writeTypedList(details);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProjectionStatusChanged(projectionState, activeProjectionPackageName, details);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarProjectionStatusListener impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarProjectionStatusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
