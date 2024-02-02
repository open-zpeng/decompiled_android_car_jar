package android.car.xpsharedmemory;

import android.car.hardware.CarPropertyConfig;
import android.car.xpsharedmemory.ISharedMemoryDataListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IXpSharedMemory extends IInterface {
    CarPropertyConfig[] getPropertyArray() throws RemoteException;

    void queueMemoryBuffer(int i, ParcelFileDescriptor parcelFileDescriptor, int i2) throws RemoteException;

    void registerListener(int i, ISharedMemoryDataListener iSharedMemoryDataListener) throws RemoteException;

    void unregisterListener(int i, ISharedMemoryDataListener iSharedMemoryDataListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IXpSharedMemory {
        private static final String DESCRIPTOR = "android.car.xpsharedmemory.IXpSharedMemory";
        static final int TRANSACTION_getPropertyArray = 5;
        static final int TRANSACTION_queueMemoryBuffer = 2;
        static final int TRANSACTION_registerListener = 3;
        static final int TRANSACTION_unregisterListener = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXpSharedMemory asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IXpSharedMemory)) {
                return (IXpSharedMemory) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelFileDescriptor _arg1;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg1 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    int _arg2 = data.readInt();
                    queueMemoryBuffer(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    ISharedMemoryDataListener _arg12 = ISharedMemoryDataListener.Stub.asInterface(data.readStrongBinder());
                    registerListener(_arg02, _arg12);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    ISharedMemoryDataListener _arg13 = ISharedMemoryDataListener.Stub.asInterface(data.readStrongBinder());
                    unregisterListener(_arg03, _arg13);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    CarPropertyConfig[] _result = getPropertyArray();
                    reply.writeNoException();
                    reply.writeTypedArray(_result, 1);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IXpSharedMemory {
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

            @Override // android.car.xpsharedmemory.IXpSharedMemory
            public void queueMemoryBuffer(int prop, ParcelFileDescriptor pfd, int size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(prop);
                    if (pfd != null) {
                        _data.writeInt(1);
                        pfd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(size);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.xpsharedmemory.IXpSharedMemory
            public void registerListener(int prop, ISharedMemoryDataListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(prop);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.xpsharedmemory.IXpSharedMemory
            public void unregisterListener(int propId, ISharedMemoryDataListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(propId);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.xpsharedmemory.IXpSharedMemory
            public CarPropertyConfig[] getPropertyArray() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    CarPropertyConfig[] _result = (CarPropertyConfig[]) _reply.createTypedArray(CarPropertyConfig.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
