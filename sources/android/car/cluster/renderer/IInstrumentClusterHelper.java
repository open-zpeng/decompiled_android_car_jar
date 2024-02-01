package android.car.cluster.renderer;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IInstrumentClusterHelper extends IInterface {
    boolean startFixedActivityModeForDisplayAndUser(Intent intent, Bundle bundle, int i) throws RemoteException;

    void stopFixedActivityMode(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IInstrumentClusterHelper {
        @Override // android.car.cluster.renderer.IInstrumentClusterHelper
        public boolean startFixedActivityModeForDisplayAndUser(Intent intent, Bundle activityOptionsBundle, int userId) throws RemoteException {
            return false;
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterHelper
        public void stopFixedActivityMode(int displayId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IInstrumentClusterHelper {
        private static final String DESCRIPTOR = "android.car.cluster.renderer.IInstrumentClusterHelper";
        static final int TRANSACTION_startFixedActivityModeForDisplayAndUser = 1;
        static final int TRANSACTION_stopFixedActivityMode = 2;

        public Stub() {
            attachInterface(this, "android.car.cluster.renderer.IInstrumentClusterHelper");
        }

        public static IInstrumentClusterHelper asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("android.car.cluster.renderer.IInstrumentClusterHelper");
            if (iin != null && (iin instanceof IInstrumentClusterHelper)) {
                return (IInstrumentClusterHelper) iin;
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
            Bundle _arg1;
            if (code != 1) {
                if (code != 2) {
                    if (code == 1598968902) {
                        reply.writeString("android.car.cluster.renderer.IInstrumentClusterHelper");
                        return true;
                    }
                    return super.onTransact(code, data, reply, flags);
                }
                data.enforceInterface("android.car.cluster.renderer.IInstrumentClusterHelper");
                int _arg02 = data.readInt();
                stopFixedActivityMode(_arg02);
                reply.writeNoException();
                return true;
            }
            data.enforceInterface("android.car.cluster.renderer.IInstrumentClusterHelper");
            if (data.readInt() != 0) {
                _arg0 = (Intent) Intent.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            if (data.readInt() != 0) {
                _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            int _arg2 = data.readInt();
            boolean startFixedActivityModeForDisplayAndUser = startFixedActivityModeForDisplayAndUser(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(startFixedActivityModeForDisplayAndUser ? 1 : 0);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IInstrumentClusterHelper {
            public static IInstrumentClusterHelper sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.car.cluster.renderer.IInstrumentClusterHelper";
            }

            @Override // android.car.cluster.renderer.IInstrumentClusterHelper
            public boolean startFixedActivityModeForDisplayAndUser(Intent intent, Bundle activityOptionsBundle, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.car.cluster.renderer.IInstrumentClusterHelper");
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (activityOptionsBundle != null) {
                        _data.writeInt(1);
                        activityOptionsBundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startFixedActivityModeForDisplayAndUser(intent, activityOptionsBundle, userId);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.renderer.IInstrumentClusterHelper
            public void stopFixedActivityMode(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.car.cluster.renderer.IInstrumentClusterHelper");
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopFixedActivityMode(displayId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInstrumentClusterHelper impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IInstrumentClusterHelper getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
