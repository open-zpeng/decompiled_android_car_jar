package android.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IAppFocusOwnershipCallback extends IInterface {
    void onAppFocusOwnershipGranted(int i) throws RemoteException;

    void onAppFocusOwnershipLost(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAppFocusOwnershipCallback {
        @Override // android.car.IAppFocusOwnershipCallback
        public void onAppFocusOwnershipLost(int appType) throws RemoteException {
        }

        @Override // android.car.IAppFocusOwnershipCallback
        public void onAppFocusOwnershipGranted(int appType) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAppFocusOwnershipCallback {
        private static final String DESCRIPTOR = "android.car.IAppFocusOwnershipCallback";
        static final int TRANSACTION_onAppFocusOwnershipGranted = 2;
        static final int TRANSACTION_onAppFocusOwnershipLost = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppFocusOwnershipCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAppFocusOwnershipCallback)) {
                return (IAppFocusOwnershipCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0 = data.readInt();
                onAppFocusOwnershipLost(_arg0);
                return true;
            } else if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            } else {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                onAppFocusOwnershipGranted(_arg02);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAppFocusOwnershipCallback {
            public static IAppFocusOwnershipCallback sDefaultImpl;
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

            @Override // android.car.IAppFocusOwnershipCallback
            public void onAppFocusOwnershipLost(int appType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(appType);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppFocusOwnershipLost(appType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.IAppFocusOwnershipCallback
            public void onAppFocusOwnershipGranted(int appType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(appType);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppFocusOwnershipGranted(appType);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppFocusOwnershipCallback impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAppFocusOwnershipCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
