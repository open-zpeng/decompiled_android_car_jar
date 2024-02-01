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
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    VmsLayer _arg0 = data.readInt() != 0 ? VmsLayer.CREATOR.createFromParcel(data) : null;
                    byte[] _arg1 = data.createByteArray();
                    onVmsMessageReceived(_arg0, _arg1);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    VmsAvailableLayers _arg02 = data.readInt() != 0 ? VmsAvailableLayers.CREATOR.createFromParcel(data) : null;
                    onLayersAvailabilityChanged(_arg02);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IVmsSubscriberClient {
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
                    this.mRemote.transact(1, _data, null, 1);
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
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
