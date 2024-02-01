package android.car.user;

import android.car.user.IUserNotice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IUserNoticeUI extends IInterface {
    void setCallbackBinder(IUserNotice iUserNotice) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IUserNoticeUI {
        @Override // android.car.user.IUserNoticeUI
        public void setCallbackBinder(IUserNotice binder) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IUserNoticeUI {
        private static final String DESCRIPTOR = "android.car.user.IUserNoticeUI";
        static final int TRANSACTION_setCallbackBinder = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUserNoticeUI asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IUserNoticeUI)) {
                return (IUserNoticeUI) iin;
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
            IUserNotice _arg0 = IUserNotice.Stub.asInterface(data.readStrongBinder());
            setCallbackBinder(_arg0);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IUserNoticeUI {
            public static IUserNoticeUI sDefaultImpl;
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

            @Override // android.car.user.IUserNoticeUI
            public void setCallbackBinder(IUserNotice binder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(binder != null ? binder.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCallbackBinder(binder);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserNoticeUI impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IUserNoticeUI getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
