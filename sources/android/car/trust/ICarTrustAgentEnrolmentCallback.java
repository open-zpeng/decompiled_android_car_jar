package android.car.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarTrustAgentEnrolmentCallback extends IInterface {
    void onEnrolmentDataReceived(byte[] bArr) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarTrustAgentEnrolmentCallback {
        private static final String DESCRIPTOR = "android.car.trust.ICarTrustAgentEnrolmentCallback";
        static final int TRANSACTION_onEnrolmentDataReceived = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarTrustAgentEnrolmentCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarTrustAgentEnrolmentCallback)) {
                return (ICarTrustAgentEnrolmentCallback) iin;
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
            byte[] _arg0 = data.createByteArray();
            onEnrolmentDataReceived(_arg0);
            return true;
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarTrustAgentEnrolmentCallback {
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

            @Override // android.car.trust.ICarTrustAgentEnrolmentCallback
            public void onEnrolmentDataReceived(byte[] token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(token);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
