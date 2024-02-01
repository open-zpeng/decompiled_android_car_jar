package android.car.vms;

import android.car.vms.IVmsSubscriberClient;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IVmsSubscriberService extends IInterface {
    void addVmsSubscriber(IVmsSubscriberClient iVmsSubscriberClient, VmsLayer vmsLayer) throws RemoteException;

    void addVmsSubscriberPassive(IVmsSubscriberClient iVmsSubscriberClient) throws RemoteException;

    void addVmsSubscriberToNotifications(IVmsSubscriberClient iVmsSubscriberClient) throws RemoteException;

    void addVmsSubscriberToPublisher(IVmsSubscriberClient iVmsSubscriberClient, VmsLayer vmsLayer, int i) throws RemoteException;

    VmsAvailableLayers getAvailableLayers() throws RemoteException;

    byte[] getPublisherInfo(int i) throws RemoteException;

    void removeVmsSubscriber(IVmsSubscriberClient iVmsSubscriberClient, VmsLayer vmsLayer) throws RemoteException;

    void removeVmsSubscriberPassive(IVmsSubscriberClient iVmsSubscriberClient) throws RemoteException;

    void removeVmsSubscriberToNotifications(IVmsSubscriberClient iVmsSubscriberClient) throws RemoteException;

    void removeVmsSubscriberToPublisher(IVmsSubscriberClient iVmsSubscriberClient, VmsLayer vmsLayer, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IVmsSubscriberService {
        @Override // android.car.vms.IVmsSubscriberService
        public void addVmsSubscriberToNotifications(IVmsSubscriberClient subscriber) throws RemoteException {
        }

        @Override // android.car.vms.IVmsSubscriberService
        public void addVmsSubscriber(IVmsSubscriberClient subscriber, VmsLayer layer) throws RemoteException {
        }

        @Override // android.car.vms.IVmsSubscriberService
        public void addVmsSubscriberPassive(IVmsSubscriberClient subscriber) throws RemoteException {
        }

        @Override // android.car.vms.IVmsSubscriberService
        public void addVmsSubscriberToPublisher(IVmsSubscriberClient subscriber, VmsLayer layer, int publisherId) throws RemoteException {
        }

        @Override // android.car.vms.IVmsSubscriberService
        public void removeVmsSubscriberToNotifications(IVmsSubscriberClient subscriber) throws RemoteException {
        }

        @Override // android.car.vms.IVmsSubscriberService
        public void removeVmsSubscriber(IVmsSubscriberClient subscriber, VmsLayer layer) throws RemoteException {
        }

        @Override // android.car.vms.IVmsSubscriberService
        public void removeVmsSubscriberPassive(IVmsSubscriberClient subscriber) throws RemoteException {
        }

        @Override // android.car.vms.IVmsSubscriberService
        public void removeVmsSubscriberToPublisher(IVmsSubscriberClient subscriber, VmsLayer layer, int publisherId) throws RemoteException {
        }

        @Override // android.car.vms.IVmsSubscriberService
        public VmsAvailableLayers getAvailableLayers() throws RemoteException {
            return null;
        }

        @Override // android.car.vms.IVmsSubscriberService
        public byte[] getPublisherInfo(int publisherId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IVmsSubscriberService {
        private static final String DESCRIPTOR = "android.car.vms.IVmsSubscriberService";
        static final int TRANSACTION_addVmsSubscriber = 2;
        static final int TRANSACTION_addVmsSubscriberPassive = 3;
        static final int TRANSACTION_addVmsSubscriberToNotifications = 1;
        static final int TRANSACTION_addVmsSubscriberToPublisher = 4;
        static final int TRANSACTION_getAvailableLayers = 9;
        static final int TRANSACTION_getPublisherInfo = 10;
        static final int TRANSACTION_removeVmsSubscriber = 6;
        static final int TRANSACTION_removeVmsSubscriberPassive = 7;
        static final int TRANSACTION_removeVmsSubscriberToNotifications = 5;
        static final int TRANSACTION_removeVmsSubscriberToPublisher = 8;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVmsSubscriberService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IVmsSubscriberService)) {
                return (IVmsSubscriberService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            VmsLayer _arg1;
            VmsLayer _arg12;
            VmsLayer _arg13;
            VmsLayer _arg14;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    IVmsSubscriberClient _arg0 = IVmsSubscriberClient.Stub.asInterface(data.readStrongBinder());
                    addVmsSubscriberToNotifications(_arg0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    IVmsSubscriberClient _arg02 = IVmsSubscriberClient.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = VmsLayer.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    addVmsSubscriber(_arg02, _arg1);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    IVmsSubscriberClient _arg03 = IVmsSubscriberClient.Stub.asInterface(data.readStrongBinder());
                    addVmsSubscriberPassive(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    IVmsSubscriberClient _arg04 = IVmsSubscriberClient.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg12 = VmsLayer.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    int _arg2 = data.readInt();
                    addVmsSubscriberToPublisher(_arg04, _arg12, _arg2);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    IVmsSubscriberClient _arg05 = IVmsSubscriberClient.Stub.asInterface(data.readStrongBinder());
                    removeVmsSubscriberToNotifications(_arg05);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    IVmsSubscriberClient _arg06 = IVmsSubscriberClient.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg13 = VmsLayer.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }
                    removeVmsSubscriber(_arg06, _arg13);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    IVmsSubscriberClient _arg07 = IVmsSubscriberClient.Stub.asInterface(data.readStrongBinder());
                    removeVmsSubscriberPassive(_arg07);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    IVmsSubscriberClient _arg08 = IVmsSubscriberClient.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg14 = VmsLayer.CREATOR.createFromParcel(data);
                    } else {
                        _arg14 = null;
                    }
                    int _arg22 = data.readInt();
                    removeVmsSubscriberToPublisher(_arg08, _arg14, _arg22);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    VmsAvailableLayers _result = getAvailableLayers();
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    byte[] _result2 = getPublisherInfo(_arg09);
                    reply.writeNoException();
                    reply.writeByteArray(_result2);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IVmsSubscriberService {
            public static IVmsSubscriberService sDefaultImpl;
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

            @Override // android.car.vms.IVmsSubscriberService
            public void addVmsSubscriberToNotifications(IVmsSubscriberClient subscriber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addVmsSubscriberToNotifications(subscriber);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsSubscriberService
            public void addVmsSubscriber(IVmsSubscriberClient subscriber, VmsLayer layer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    if (layer != null) {
                        _data.writeInt(1);
                        layer.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addVmsSubscriber(subscriber, layer);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsSubscriberService
            public void addVmsSubscriberPassive(IVmsSubscriberClient subscriber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addVmsSubscriberPassive(subscriber);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsSubscriberService
            public void addVmsSubscriberToPublisher(IVmsSubscriberClient subscriber, VmsLayer layer, int publisherId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    if (layer != null) {
                        _data.writeInt(1);
                        layer.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(publisherId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addVmsSubscriberToPublisher(subscriber, layer, publisherId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsSubscriberService
            public void removeVmsSubscriberToNotifications(IVmsSubscriberClient subscriber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeVmsSubscriberToNotifications(subscriber);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsSubscriberService
            public void removeVmsSubscriber(IVmsSubscriberClient subscriber, VmsLayer layer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    if (layer != null) {
                        _data.writeInt(1);
                        layer.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeVmsSubscriber(subscriber, layer);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsSubscriberService
            public void removeVmsSubscriberPassive(IVmsSubscriberClient subscriber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeVmsSubscriberPassive(subscriber);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsSubscriberService
            public void removeVmsSubscriberToPublisher(IVmsSubscriberClient subscriber, VmsLayer layer, int publisherId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    if (layer != null) {
                        _data.writeInt(1);
                        layer.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(publisherId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeVmsSubscriberToPublisher(subscriber, layer, publisherId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsSubscriberService
            public VmsAvailableLayers getAvailableLayers() throws RemoteException {
                VmsAvailableLayers _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableLayers();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VmsAvailableLayers.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsSubscriberService
            public byte[] getPublisherInfo(int publisherId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(publisherId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPublisherInfo(publisherId);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVmsSubscriberService impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IVmsSubscriberService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
