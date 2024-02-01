package android.car.trust;

import android.bluetooth.BluetoothDevice;
import android.car.trust.ICarTrustAgentBleCallback;
import android.car.trust.ICarTrustAgentEnrolmentCallback;
import android.car.trust.ICarTrustAgentTokenRequestDelegate;
import android.car.trust.ICarTrustAgentTokenResponseCallback;
import android.car.trust.ICarTrustAgentUnlockCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarTrustAgentBleService extends IInterface {
    void addEscrowToken(byte[] bArr, int i) throws RemoteException;

    void isEscrowTokenActive(long j, int i) throws RemoteException;

    void onEscrowTokenActiveStateChanged(long j, boolean z) throws RemoteException;

    void onEscrowTokenAdded(byte[] bArr, long j, int i) throws RemoteException;

    void onEscrowTokenRemoved(long j, boolean z) throws RemoteException;

    void registerBleCallback(ICarTrustAgentBleCallback iCarTrustAgentBleCallback) throws RemoteException;

    void registerEnrolmentCallback(ICarTrustAgentEnrolmentCallback iCarTrustAgentEnrolmentCallback) throws RemoteException;

    void registerUnlockCallback(ICarTrustAgentUnlockCallback iCarTrustAgentUnlockCallback) throws RemoteException;

    void removeEscrowToken(long j, int i) throws RemoteException;

    void revokeTrust() throws RemoteException;

    void sendEnrolmentHandle(BluetoothDevice bluetoothDevice, long j) throws RemoteException;

    void setTokenRequestDelegate(ICarTrustAgentTokenRequestDelegate iCarTrustAgentTokenRequestDelegate) throws RemoteException;

    void setTokenResponseCallback(ICarTrustAgentTokenResponseCallback iCarTrustAgentTokenResponseCallback) throws RemoteException;

    void startEnrolmentAdvertising() throws RemoteException;

    void startUnlockAdvertising() throws RemoteException;

    void stopEnrolmentAdvertising() throws RemoteException;

    void stopUnlockAdvertising() throws RemoteException;

    void unregisterBleCallback(ICarTrustAgentBleCallback iCarTrustAgentBleCallback) throws RemoteException;

    void unregisterEnrolmentCallback(ICarTrustAgentEnrolmentCallback iCarTrustAgentEnrolmentCallback) throws RemoteException;

    void unregisterUnlockCallback(ICarTrustAgentUnlockCallback iCarTrustAgentUnlockCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarTrustAgentBleService {
        private static final String DESCRIPTOR = "android.car.trust.ICarTrustAgentBleService";
        static final int TRANSACTION_addEscrowToken = 14;
        static final int TRANSACTION_isEscrowTokenActive = 16;
        static final int TRANSACTION_onEscrowTokenActiveStateChanged = 20;
        static final int TRANSACTION_onEscrowTokenAdded = 18;
        static final int TRANSACTION_onEscrowTokenRemoved = 19;
        static final int TRANSACTION_registerBleCallback = 1;
        static final int TRANSACTION_registerEnrolmentCallback = 6;
        static final int TRANSACTION_registerUnlockCallback = 10;
        static final int TRANSACTION_removeEscrowToken = 15;
        static final int TRANSACTION_revokeTrust = 13;
        static final int TRANSACTION_sendEnrolmentHandle = 5;
        static final int TRANSACTION_setTokenRequestDelegate = 12;
        static final int TRANSACTION_setTokenResponseCallback = 17;
        static final int TRANSACTION_startEnrolmentAdvertising = 3;
        static final int TRANSACTION_startUnlockAdvertising = 8;
        static final int TRANSACTION_stopEnrolmentAdvertising = 4;
        static final int TRANSACTION_stopUnlockAdvertising = 9;
        static final int TRANSACTION_unregisterBleCallback = 2;
        static final int TRANSACTION_unregisterEnrolmentCallback = 7;
        static final int TRANSACTION_unregisterUnlockCallback = 11;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarTrustAgentBleService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarTrustAgentBleService)) {
                return (ICarTrustAgentBleService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BluetoothDevice _arg0;
            boolean _arg1;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    ICarTrustAgentBleCallback _arg02 = ICarTrustAgentBleCallback.Stub.asInterface(data.readStrongBinder());
                    registerBleCallback(_arg02);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    ICarTrustAgentBleCallback _arg03 = ICarTrustAgentBleCallback.Stub.asInterface(data.readStrongBinder());
                    unregisterBleCallback(_arg03);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    startEnrolmentAdvertising();
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    stopEnrolmentAdvertising();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    sendEnrolmentHandle(_arg0, data.readLong());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    ICarTrustAgentEnrolmentCallback _arg04 = ICarTrustAgentEnrolmentCallback.Stub.asInterface(data.readStrongBinder());
                    registerEnrolmentCallback(_arg04);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    ICarTrustAgentEnrolmentCallback _arg05 = ICarTrustAgentEnrolmentCallback.Stub.asInterface(data.readStrongBinder());
                    unregisterEnrolmentCallback(_arg05);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    startUnlockAdvertising();
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    stopUnlockAdvertising();
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    ICarTrustAgentUnlockCallback _arg06 = ICarTrustAgentUnlockCallback.Stub.asInterface(data.readStrongBinder());
                    registerUnlockCallback(_arg06);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    ICarTrustAgentUnlockCallback _arg07 = ICarTrustAgentUnlockCallback.Stub.asInterface(data.readStrongBinder());
                    unregisterUnlockCallback(_arg07);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    ICarTrustAgentTokenRequestDelegate _arg08 = ICarTrustAgentTokenRequestDelegate.Stub.asInterface(data.readStrongBinder());
                    setTokenRequestDelegate(_arg08);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    revokeTrust();
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg09 = data.createByteArray();
                    addEscrowToken(_arg09, data.readInt());
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg010 = data.readLong();
                    removeEscrowToken(_arg010, data.readInt());
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg011 = data.readLong();
                    isEscrowTokenActive(_arg011, data.readInt());
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    ICarTrustAgentTokenResponseCallback _arg012 = ICarTrustAgentTokenResponseCallback.Stub.asInterface(data.readStrongBinder());
                    setTokenResponseCallback(_arg012);
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg013 = data.createByteArray();
                    long _arg12 = data.readLong();
                    int _arg2 = data.readInt();
                    onEscrowTokenAdded(_arg013, _arg12, _arg2);
                    reply.writeNoException();
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg014 = data.readLong();
                    _arg1 = data.readInt() != 0;
                    onEscrowTokenRemoved(_arg014, _arg1);
                    reply.writeNoException();
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg015 = data.readLong();
                    _arg1 = data.readInt() != 0;
                    onEscrowTokenActiveStateChanged(_arg015, _arg1);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarTrustAgentBleService {
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

            @Override // android.car.trust.ICarTrustAgentBleService
            public void registerBleCallback(ICarTrustAgentBleCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void unregisterBleCallback(ICarTrustAgentBleCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void startEnrolmentAdvertising() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void stopEnrolmentAdvertising() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void sendEnrolmentHandle(BluetoothDevice device, long handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(handle);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void registerEnrolmentCallback(ICarTrustAgentEnrolmentCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void unregisterEnrolmentCallback(ICarTrustAgentEnrolmentCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void startUnlockAdvertising() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void stopUnlockAdvertising() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void registerUnlockCallback(ICarTrustAgentUnlockCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void unregisterUnlockCallback(ICarTrustAgentUnlockCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void setTokenRequestDelegate(ICarTrustAgentTokenRequestDelegate delegate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(delegate != null ? delegate.asBinder() : null);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void revokeTrust() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void addEscrowToken(byte[] token, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(token);
                    _data.writeInt(uid);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void removeEscrowToken(long handle, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(uid);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void isEscrowTokenActive(long handle, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(uid);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void setTokenResponseCallback(ICarTrustAgentTokenResponseCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void onEscrowTokenAdded(byte[] token, long handle, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(token);
                    _data.writeLong(handle);
                    _data.writeInt(uid);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void onEscrowTokenRemoved(long handle, boolean successful) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(successful ? 1 : 0);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleService
            public void onEscrowTokenActiveStateChanged(long handle, boolean active) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(active ? 1 : 0);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
