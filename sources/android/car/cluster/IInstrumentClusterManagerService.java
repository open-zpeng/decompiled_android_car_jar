package android.car.cluster;

import android.car.cluster.IInstrumentClusterManagerCallback;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IInstrumentClusterManagerService extends IInterface {
    void registerCallback(IInstrumentClusterManagerCallback iInstrumentClusterManagerCallback) throws RemoteException;

    void startClusterActivity(Intent intent) throws RemoteException;

    void unregisterCallback(IInstrumentClusterManagerCallback iInstrumentClusterManagerCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IInstrumentClusterManagerService {
        @Override // android.car.cluster.IInstrumentClusterManagerService
        public void startClusterActivity(Intent intent) throws RemoteException {
        }

        @Override // android.car.cluster.IInstrumentClusterManagerService
        public void registerCallback(IInstrumentClusterManagerCallback callback) throws RemoteException {
        }

        @Override // android.car.cluster.IInstrumentClusterManagerService
        public void unregisterCallback(IInstrumentClusterManagerCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IInstrumentClusterManagerService {
        private static final String DESCRIPTOR = "android.car.cluster.IInstrumentClusterManagerService";
        static final int TRANSACTION_registerCallback = 2;
        static final int TRANSACTION_startClusterActivity = 1;
        static final int TRANSACTION_unregisterCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInstrumentClusterManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IInstrumentClusterManagerService)) {
                return (IInstrumentClusterManagerService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Intent _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = (Intent) Intent.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                startClusterActivity(_arg0);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                IInstrumentClusterManagerCallback _arg02 = IInstrumentClusterManagerCallback.Stub.asInterface(data.readStrongBinder());
                registerCallback(_arg02);
                return true;
            } else if (code != 3) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            } else {
                data.enforceInterface(DESCRIPTOR);
                IInstrumentClusterManagerCallback _arg03 = IInstrumentClusterManagerCallback.Stub.asInterface(data.readStrongBinder());
                unregisterCallback(_arg03);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IInstrumentClusterManagerService {
            public static IInstrumentClusterManagerService sDefaultImpl;
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

            @Override // android.car.cluster.IInstrumentClusterManagerService
            public void startClusterActivity(Intent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startClusterActivity(intent);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.IInstrumentClusterManagerService
            public void registerCallback(IInstrumentClusterManagerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.IInstrumentClusterManagerService
            public void unregisterCallback(IInstrumentClusterManagerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInstrumentClusterManagerService impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IInstrumentClusterManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
