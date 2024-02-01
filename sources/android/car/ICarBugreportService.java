package android.car;

import android.car.ICarBugreportCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarBugreportService extends IInterface {
    void requestBugreport(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, ICarBugreportCallback iCarBugreportCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarBugreportService {
        @Override // android.car.ICarBugreportService
        public void requestBugreport(ParcelFileDescriptor output, ParcelFileDescriptor extraOutput, ICarBugreportCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarBugreportService {
        private static final String DESCRIPTOR = "android.car.ICarBugreportService";
        static final int TRANSACTION_requestBugreport = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarBugreportService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarBugreportService)) {
                return (ICarBugreportService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelFileDescriptor _arg0;
            ParcelFileDescriptor _arg1;
            if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            if (data.readInt() != 0) {
                _arg1 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            ICarBugreportCallback _arg2 = ICarBugreportCallback.Stub.asInterface(data.readStrongBinder());
            requestBugreport(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarBugreportService {
            public static ICarBugreportService sDefaultImpl;
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

            @Override // android.car.ICarBugreportService
            public void requestBugreport(ParcelFileDescriptor output, ParcelFileDescriptor extraOutput, ICarBugreportCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (output != null) {
                        _data.writeInt(1);
                        output.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extraOutput != null) {
                        _data.writeInt(1);
                        extraOutput.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestBugreport(output, extraOutput, callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarBugreportService impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarBugreportService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
