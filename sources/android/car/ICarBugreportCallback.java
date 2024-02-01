package android.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarBugreportCallback extends IInterface {
    void onError(int i) throws RemoteException;

    void onFinished() throws RemoteException;

    void onProgress(float f) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarBugreportCallback {
        @Override // android.car.ICarBugreportCallback
        public void onError(int errorCode) throws RemoteException {
        }

        @Override // android.car.ICarBugreportCallback
        public void onProgress(float progress) throws RemoteException {
        }

        @Override // android.car.ICarBugreportCallback
        public void onFinished() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarBugreportCallback {
        private static final String DESCRIPTOR = "android.car.ICarBugreportCallback";
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onFinished = 3;
        static final int TRANSACTION_onProgress = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarBugreportCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarBugreportCallback)) {
                return (ICarBugreportCallback) iin;
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
                onError(_arg0);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                float _arg02 = data.readFloat();
                onProgress(_arg02);
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                onFinished();
                return true;
            } else if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            } else {
                return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarBugreportCallback {
            public static ICarBugreportCallback sDefaultImpl;
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

            @Override // android.car.ICarBugreportCallback
            public void onError(int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(errorCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBugreportCallback
            public void onProgress(float progress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(progress);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProgress(progress);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBugreportCallback
            public void onFinished() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFinished();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarBugreportCallback impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarBugreportCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
