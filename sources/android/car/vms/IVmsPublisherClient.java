package android.car.vms;

import android.car.vms.IVmsPublisherService;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IVmsPublisherClient extends IInterface {
    void onVmsSubscriptionChange(VmsSubscriptionState vmsSubscriptionState) throws RemoteException;

    void setVmsPublisherService(IBinder iBinder, IVmsPublisherService iVmsPublisherService) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IVmsPublisherClient {
        @Override // android.car.vms.IVmsPublisherClient
        public void setVmsPublisherService(IBinder token, IVmsPublisherService service) throws RemoteException {
        }

        @Override // android.car.vms.IVmsPublisherClient
        public void onVmsSubscriptionChange(VmsSubscriptionState subscriptionState) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IVmsPublisherClient {
        private static final String DESCRIPTOR = "android.car.vms.IVmsPublisherClient";
        static final int TRANSACTION_onVmsSubscriptionChange = 2;
        static final int TRANSACTION_setVmsPublisherService = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVmsPublisherClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IVmsPublisherClient)) {
                return (IVmsPublisherClient) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            VmsSubscriptionState _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                IBinder _arg02 = data.readStrongBinder();
                IVmsPublisherService _arg1 = IVmsPublisherService.Stub.asInterface(data.readStrongBinder());
                setVmsPublisherService(_arg02, _arg1);
                return true;
            } else if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            } else {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = VmsSubscriptionState.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                onVmsSubscriptionChange(_arg0);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IVmsPublisherClient {
            public static IVmsPublisherClient sDefaultImpl;
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

            @Override // android.car.vms.IVmsPublisherClient
            public void setVmsPublisherService(IBinder token, IVmsPublisherService service) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeStrongBinder(service != null ? service.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVmsPublisherService(token, service);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsPublisherClient
            public void onVmsSubscriptionChange(VmsSubscriptionState subscriptionState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (subscriptionState != null) {
                        _data.writeInt(1);
                        subscriptionState.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVmsSubscriptionChange(subscriptionState);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVmsPublisherClient impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IVmsPublisherClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
