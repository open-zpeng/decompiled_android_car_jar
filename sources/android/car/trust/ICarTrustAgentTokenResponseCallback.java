package android.car.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarTrustAgentTokenResponseCallback extends IInterface {
    void onEscrowTokenActiveStateChanged(long j, boolean z) throws RemoteException;

    void onEscrowTokenAdded(byte[] bArr, long j, int i) throws RemoteException;

    void onEscrowTokenRemoved(long j, boolean z) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarTrustAgentTokenResponseCallback {
        private static final String DESCRIPTOR = "android.car.trust.ICarTrustAgentTokenResponseCallback";
        static final int TRANSACTION_onEscrowTokenActiveStateChanged = 3;
        static final int TRANSACTION_onEscrowTokenAdded = 1;
        static final int TRANSACTION_onEscrowTokenRemoved = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarTrustAgentTokenResponseCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarTrustAgentTokenResponseCallback)) {
                return (ICarTrustAgentTokenResponseCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            byte[] _arg0;
            boolean _arg1;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0_length = data.readInt();
                    if (_arg0_length < 0) {
                        _arg0 = null;
                    } else {
                        _arg0 = new byte[_arg0_length];
                    }
                    long _arg12 = data.readLong();
                    int _arg2 = data.readInt();
                    onEscrowTokenAdded(_arg0, _arg12, _arg2);
                    reply.writeNoException();
                    reply.writeByteArray(_arg0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg02 = data.readLong();
                    _arg1 = data.readInt() != 0;
                    onEscrowTokenRemoved(_arg02, _arg1);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg03 = data.readLong();
                    _arg1 = data.readInt() != 0;
                    onEscrowTokenActiveStateChanged(_arg03, _arg1);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarTrustAgentTokenResponseCallback {
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

            @Override // android.car.trust.ICarTrustAgentTokenResponseCallback
            public void onEscrowTokenAdded(byte[] token, long handle, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (token == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(token.length);
                    }
                    _data.writeLong(handle);
                    _data.writeInt(uid);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    _reply.readByteArray(token);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentTokenResponseCallback
            public void onEscrowTokenRemoved(long handle, boolean successful) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(successful ? 1 : 0);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentTokenResponseCallback
            public void onEscrowTokenActiveStateChanged(long handle, boolean active) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(active ? 1 : 0);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
