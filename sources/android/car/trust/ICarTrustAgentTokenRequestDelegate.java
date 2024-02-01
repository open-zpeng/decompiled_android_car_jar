package android.car.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarTrustAgentTokenRequestDelegate extends IInterface {
    void addEscrowToken(byte[] bArr, int i) throws RemoteException;

    void isEscrowTokenActive(long j, int i) throws RemoteException;

    void removeEscrowToken(long j, int i) throws RemoteException;

    void revokeTrust() throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarTrustAgentTokenRequestDelegate {
        private static final String DESCRIPTOR = "android.car.trust.ICarTrustAgentTokenRequestDelegate";
        static final int TRANSACTION_addEscrowToken = 2;
        static final int TRANSACTION_isEscrowTokenActive = 4;
        static final int TRANSACTION_removeEscrowToken = 3;
        static final int TRANSACTION_revokeTrust = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarTrustAgentTokenRequestDelegate asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarTrustAgentTokenRequestDelegate)) {
                return (ICarTrustAgentTokenRequestDelegate) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    revokeTrust();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg0 = data.createByteArray();
                    int _arg1 = data.readInt();
                    addEscrowToken(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg02 = data.readLong();
                    int _arg12 = data.readInt();
                    removeEscrowToken(_arg02, _arg12);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg03 = data.readLong();
                    int _arg13 = data.readInt();
                    isEscrowTokenActive(_arg03, _arg13);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarTrustAgentTokenRequestDelegate {
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

            @Override // android.car.trust.ICarTrustAgentTokenRequestDelegate
            public void revokeTrust() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentTokenRequestDelegate
            public void addEscrowToken(byte[] token, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(token);
                    _data.writeInt(uid);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentTokenRequestDelegate
            public void removeEscrowToken(long handle, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(uid);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentTokenRequestDelegate
            public void isEscrowTokenActive(long handle, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(uid);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
