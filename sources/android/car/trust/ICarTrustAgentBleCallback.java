package android.car.trust;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarTrustAgentBleCallback extends IInterface {
    void onBleEnrollmentDeviceConnected(BluetoothDevice bluetoothDevice) throws RemoteException;

    void onBleEnrollmentDeviceDisconnected(BluetoothDevice bluetoothDevice) throws RemoteException;

    void onEnrollmentAdvertisingFailed() throws RemoteException;

    void onEnrollmentAdvertisingStarted() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarTrustAgentBleCallback {
        @Override // android.car.trust.ICarTrustAgentBleCallback
        public void onEnrollmentAdvertisingStarted() throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentBleCallback
        public void onEnrollmentAdvertisingFailed() throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentBleCallback
        public void onBleEnrollmentDeviceConnected(BluetoothDevice device) throws RemoteException {
        }

        @Override // android.car.trust.ICarTrustAgentBleCallback
        public void onBleEnrollmentDeviceDisconnected(BluetoothDevice device) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarTrustAgentBleCallback {
        private static final String DESCRIPTOR = "android.car.trust.ICarTrustAgentBleCallback";
        static final int TRANSACTION_onBleEnrollmentDeviceConnected = 3;
        static final int TRANSACTION_onBleEnrollmentDeviceDisconnected = 4;
        static final int TRANSACTION_onEnrollmentAdvertisingFailed = 2;
        static final int TRANSACTION_onEnrollmentAdvertisingStarted = 1;

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
            BluetoothDevice _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onEnrollmentAdvertisingStarted();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onEnrollmentAdvertisingFailed();
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                onBleEnrollmentDeviceConnected(_arg0);
                return true;
            } else if (code != 4) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            } else {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg02 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                } else {
                    _arg02 = null;
                }
                onBleEnrollmentDeviceDisconnected(_arg02);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarTrustAgentBleCallback {
            public static ICarTrustAgentBleCallback sDefaultImpl;
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
            public void onEnrollmentAdvertisingStarted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnrollmentAdvertisingStarted();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleCallback
            public void onEnrollmentAdvertisingFailed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnrollmentAdvertisingFailed();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleCallback
            public void onBleEnrollmentDeviceConnected(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBleEnrollmentDeviceConnected(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.trust.ICarTrustAgentBleCallback
            public void onBleEnrollmentDeviceDisconnected(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBleEnrollmentDeviceDisconnected(device);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarTrustAgentBleCallback impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarTrustAgentBleCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
