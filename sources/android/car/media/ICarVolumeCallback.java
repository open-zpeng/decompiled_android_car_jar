package android.car.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarVolumeCallback extends IInterface {
    void onGroupVolumeChanged(int i, int i2, int i3) throws RemoteException;

    void onMasterMuteChanged(int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarVolumeCallback {
        @Override // android.car.media.ICarVolumeCallback
        public void onGroupVolumeChanged(int zoneId, int groupId, int flags) throws RemoteException {
        }

        @Override // android.car.media.ICarVolumeCallback
        public void onMasterMuteChanged(int zoneId, int flags) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarVolumeCallback {
        private static final String DESCRIPTOR = "android.car.media.ICarVolumeCallback";
        static final int TRANSACTION_onGroupVolumeChanged = 1;
        static final int TRANSACTION_onMasterMuteChanged = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarVolumeCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarVolumeCallback)) {
                return (ICarVolumeCallback) iin;
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
                int _arg1 = data.readInt();
                int _arg2 = data.readInt();
                onGroupVolumeChanged(_arg0, _arg1, _arg2);
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
                int _arg12 = data.readInt();
                onMasterMuteChanged(_arg02, _arg12);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarVolumeCallback {
            public static ICarVolumeCallback sDefaultImpl;
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

            @Override // android.car.media.ICarVolumeCallback
            public void onGroupVolumeChanged(int zoneId, int groupId, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(zoneId);
                    _data.writeInt(groupId);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGroupVolumeChanged(zoneId, groupId, flags);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarVolumeCallback
            public void onMasterMuteChanged(int zoneId, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(zoneId);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMasterMuteChanged(zoneId, flags);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarVolumeCallback impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarVolumeCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
