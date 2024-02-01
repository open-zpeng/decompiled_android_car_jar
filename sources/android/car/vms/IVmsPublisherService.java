package android.car.vms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IVmsPublisherService extends IInterface {
    int getPublisherId(byte[] bArr) throws RemoteException;

    VmsSubscriptionState getSubscriptions() throws RemoteException;

    void publish(IBinder iBinder, VmsLayer vmsLayer, int i, byte[] bArr) throws RemoteException;

    void setLayersOffering(IBinder iBinder, VmsLayersOffering vmsLayersOffering) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IVmsPublisherService {
        private static final String DESCRIPTOR = "android.car.vms.IVmsPublisherService";
        static final int TRANSACTION_getPublisherId = 4;
        static final int TRANSACTION_getSubscriptions = 2;
        static final int TRANSACTION_publish = 1;
        static final int TRANSACTION_setLayersOffering = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVmsPublisherService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IVmsPublisherService)) {
                return (IVmsPublisherService) iin;
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
                    IBinder _arg0 = data.readStrongBinder();
                    VmsLayer _arg1 = data.readInt() != 0 ? VmsLayer.CREATOR.createFromParcel(data) : null;
                    int _arg2 = data.readInt();
                    byte[] _arg3 = data.createByteArray();
                    publish(_arg0, _arg1, _arg2, _arg3);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    VmsSubscriptionState _result = getSubscriptions();
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    IBinder _arg02 = data.readStrongBinder();
                    VmsLayersOffering _arg12 = data.readInt() != 0 ? VmsLayersOffering.CREATOR.createFromParcel(data) : null;
                    setLayersOffering(_arg02, _arg12);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg03 = data.createByteArray();
                    int _result2 = getPublisherId(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IVmsPublisherService {
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

            @Override // android.car.vms.IVmsPublisherService
            public void publish(IBinder token, VmsLayer layer, int publisherId, byte[] message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (layer != null) {
                        _data.writeInt(1);
                        layer.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(publisherId);
                    _data.writeByteArray(message);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsPublisherService
            public VmsSubscriptionState getSubscriptions() throws RemoteException {
                VmsSubscriptionState _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VmsSubscriptionState.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsPublisherService
            public void setLayersOffering(IBinder token, VmsLayersOffering offering) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (offering != null) {
                        _data.writeInt(1);
                        offering.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsPublisherService
            public int getPublisherId(byte[] publisherInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(publisherInfo);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
