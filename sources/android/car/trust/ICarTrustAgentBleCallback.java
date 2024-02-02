package android.car.trust;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarTrustAgentBleCallback extends IInterface {
    void onBleDeviceConnected(BluetoothDevice bluetoothDevice) throws RemoteException;

    void onBleDeviceDisconnected(BluetoothDevice bluetoothDevice) throws RemoteException;

    void onBleServerStartFailure(int i) throws RemoteException;

    void onBleServerStartSuccess() throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarTrustAgentBleCallback {
        private static final String DESCRIPTOR = "android.car.trust.ICarTrustAgentBleCallback";
        static final int TRANSACTION_onBleDeviceConnected = 3;
        static final int TRANSACTION_onBleDeviceDisconnected = 4;
        static final int TRANSACTION_onBleServerStartFailure = 2;
        static final int TRANSACTION_onBleServerStartSuccess = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarTrustAgentBleCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarTrustAgentBleCallback)) {
                return (ICarTrustAgentBleCallback) iin;
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
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onBleServerStartSuccess();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onBleServerStartFailure(data.readInt());
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt() != 0 ? (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data) : null;
                    onBleDeviceConnected(_arg0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt() != 0 ? (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data) : null;
                    onBleDeviceDisconnected(_arg0);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarTrustAgentBleCallback {
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

            @Override // android.car.trust.ICarTrustAgentBleCallback
            public void onBleServerStartSuccess() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleCallback
            public void onBleServerStartFailure(int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleCallback
            public void onBleDeviceConnected(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleCallback
            public void onBleDeviceDisconnected(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(4, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
