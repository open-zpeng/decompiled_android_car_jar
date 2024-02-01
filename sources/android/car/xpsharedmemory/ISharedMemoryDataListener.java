package android.car.xpsharedmemory;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISharedMemoryDataListener extends IInterface {
    void onDataAvailable(long j, long j2, int i, ParcelFileDescriptor parcelFileDescriptor, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ISharedMemoryDataListener {
        @Override // android.car.xpsharedmemory.ISharedMemoryDataListener
        public void onDataAvailable(long receiveTimestamp, long sendTimestamp, int prop, ParcelFileDescriptor pfd, int size) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISharedMemoryDataListener {
        private static final String DESCRIPTOR = "android.car.xpsharedmemory.ISharedMemoryDataListener";
        static final int TRANSACTION_onDataAvailable = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISharedMemoryDataListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ISharedMemoryDataListener)) {
                return (ISharedMemoryDataListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelFileDescriptor _arg3;
            if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            long _arg0 = data.readLong();
            long _arg1 = data.readLong();
            int _arg2 = data.readInt();
            if (data.readInt() != 0) {
                _arg3 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
            } else {
                _arg3 = null;
            }
            int _arg4 = data.readInt();
            onDataAvailable(_arg0, _arg1, _arg2, _arg3, _arg4);
            reply.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISharedMemoryDataListener {
            public static ISharedMemoryDataListener sDefaultImpl;
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

            @Override // android.car.xpsharedmemory.ISharedMemoryDataListener
            public void onDataAvailable(long receiveTimestamp, long sendTimestamp, int prop, ParcelFileDescriptor pfd, int size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeLong(receiveTimestamp);
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeLong(sendTimestamp);
                    _data.writeInt(prop);
                    if (pfd != null) {
                        _data.writeInt(1);
                        pfd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(size);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDataAvailable(receiveTimestamp, sendTimestamp, prop, pfd, size);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    _reply.readException();
                    _reply.recycle();
                    _data.recycle();
                } catch (Throwable th3) {
                    th = th3;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }
        }

        public static boolean setDefaultImpl(ISharedMemoryDataListener impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ISharedMemoryDataListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
