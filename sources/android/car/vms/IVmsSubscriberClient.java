package android.car.vms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IVmsSubscriberClient extends IInterface {
    void onLayersAvailabilityChanged(VmsAvailableLayers vmsAvailableLayers) throws RemoteException;

    void onVmsMessageReceived(VmsLayer vmsLayer, byte[] bArr) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IVmsSubscriberClient {
        @Override // android.car.vms.IVmsSubscriberClient
        public void onVmsMessageReceived(VmsLayer layer, byte[] payload) throws RemoteException {
        }

        @Override // android.car.vms.IVmsSubscriberClient
        public void onLayersAvailabilityChanged(VmsAvailableLayers availableLayers) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IVmsSubscriberClient {
        private static final String DESCRIPTOR = "android.car.vms.IVmsSubscriberClient";
        static final int TRANSACTION_onLayersAvailabilityChanged = 2;
        static final int TRANSACTION_onVmsMessageReceived = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVmsSubscriberClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IVmsSubscriberClient)) {
                return (IVmsSubscriberClient) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            VmsLayer _arg0;
            VmsAvailableLayers _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = VmsLayer.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                byte[] _arg1 = data.createByteArray();
                onVmsMessageReceived(_arg0, _arg1);
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
                    _arg02 = VmsAvailableLayers.CREATOR.createFromParcel(data);
                } else {
                    _arg02 = null;
                }
                onLayersAvailabilityChanged(_arg02);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IVmsSubscriberClient {
            public static IVmsSubscriberClient sDefaultImpl;
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

            @Override // android.car.vms.IVmsSubscriberClient
            public void onVmsMessageReceived(VmsLayer layer, byte[] payload) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (layer != null) {
                        _data.writeInt(1);
                        layer.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByteArray(payload);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVmsMessageReceived(layer, payload);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.vms.IVmsSubscriberClient
            public void onLayersAvailabilityChanged(VmsAvailableLayers availableLayers) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (availableLayers != null) {
                        _data.writeInt(1);
                        availableLayers.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLayersAvailabilityChanged(availableLayers);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVmsSubscriberClient impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IVmsSubscriberClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
