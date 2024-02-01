package android.car;

import android.bluetooth.BluetoothDevice;
import android.car.ICarProjectionKeyEventHandler;
import android.car.ICarProjectionStatusListener;
import android.car.projection.ProjectionStatus;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarProjection extends IInterface {
    int[] getAvailableWifiChannels(int i) throws RemoteException;

    Bundle getProjectionOptions() throws RemoteException;

    void registerKeyEventHandler(ICarProjectionKeyEventHandler iCarProjectionKeyEventHandler, byte[] bArr) throws RemoteException;

    void registerProjectionRunner(Intent intent) throws RemoteException;

    void registerProjectionStatusListener(ICarProjectionStatusListener iCarProjectionStatusListener) throws RemoteException;

    boolean releaseBluetoothProfileInhibit(BluetoothDevice bluetoothDevice, int i, IBinder iBinder) throws RemoteException;

    boolean requestBluetoothProfileInhibit(BluetoothDevice bluetoothDevice, int i, IBinder iBinder) throws RemoteException;

    void startProjectionAccessPoint(Messenger messenger, IBinder iBinder) throws RemoteException;

    void stopProjectionAccessPoint(IBinder iBinder) throws RemoteException;

    void unregisterKeyEventHandler(ICarProjectionKeyEventHandler iCarProjectionKeyEventHandler) throws RemoteException;

    void unregisterProjectionRunner(Intent intent) throws RemoteException;

    void unregisterProjectionStatusListener(ICarProjectionStatusListener iCarProjectionStatusListener) throws RemoteException;

    void updateProjectionStatus(ProjectionStatus projectionStatus, IBinder iBinder) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarProjection {
        @Override // android.car.ICarProjection
        public void registerProjectionRunner(Intent serviceIntent) throws RemoteException {
        }

        @Override // android.car.ICarProjection
        public void unregisterProjectionRunner(Intent serviceIntent) throws RemoteException {
        }

        @Override // android.car.ICarProjection
        public void registerKeyEventHandler(ICarProjectionKeyEventHandler eventHandler, byte[] eventMask) throws RemoteException {
        }

        @Override // android.car.ICarProjection
        public void unregisterKeyEventHandler(ICarProjectionKeyEventHandler eventHandler) throws RemoteException {
        }

        @Override // android.car.ICarProjection
        public void startProjectionAccessPoint(Messenger messenger, IBinder binder) throws RemoteException {
        }

        @Override // android.car.ICarProjection
        public void stopProjectionAccessPoint(IBinder binder) throws RemoteException {
        }

        @Override // android.car.ICarProjection
        public boolean requestBluetoothProfileInhibit(BluetoothDevice device, int profile, IBinder token) throws RemoteException {
            return false;
        }

        @Override // android.car.ICarProjection
        public boolean releaseBluetoothProfileInhibit(BluetoothDevice device, int profile, IBinder token) throws RemoteException {
            return false;
        }

        @Override // android.car.ICarProjection
        public void updateProjectionStatus(ProjectionStatus status, IBinder token) throws RemoteException {
        }

        @Override // android.car.ICarProjection
        public void registerProjectionStatusListener(ICarProjectionStatusListener listener) throws RemoteException {
        }

        @Override // android.car.ICarProjection
        public void unregisterProjectionStatusListener(ICarProjectionStatusListener listener) throws RemoteException {
        }

        @Override // android.car.ICarProjection
        public Bundle getProjectionOptions() throws RemoteException {
            return null;
        }

        @Override // android.car.ICarProjection
        public int[] getAvailableWifiChannels(int band) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarProjection {
        private static final String DESCRIPTOR = "android.car.ICarProjection";
        static final int TRANSACTION_getAvailableWifiChannels = 13;
        static final int TRANSACTION_getProjectionOptions = 12;
        static final int TRANSACTION_registerKeyEventHandler = 3;
        static final int TRANSACTION_registerProjectionRunner = 1;
        static final int TRANSACTION_registerProjectionStatusListener = 10;
        static final int TRANSACTION_releaseBluetoothProfileInhibit = 8;
        static final int TRANSACTION_requestBluetoothProfileInhibit = 7;
        static final int TRANSACTION_startProjectionAccessPoint = 5;
        static final int TRANSACTION_stopProjectionAccessPoint = 6;
        static final int TRANSACTION_unregisterKeyEventHandler = 4;
        static final int TRANSACTION_unregisterProjectionRunner = 2;
        static final int TRANSACTION_unregisterProjectionStatusListener = 11;
        static final int TRANSACTION_updateProjectionStatus = 9;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarProjection asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarProjection)) {
                return (ICarProjection) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Intent _arg0;
            Intent _arg02;
            Messenger _arg03;
            BluetoothDevice _arg04;
            BluetoothDevice _arg05;
            ProjectionStatus _arg06;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Intent) Intent.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    registerProjectionRunner(_arg0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (Intent) Intent.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    unregisterProjectionRunner(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    ICarProjectionKeyEventHandler _arg07 = ICarProjectionKeyEventHandler.Stub.asInterface(data.readStrongBinder());
                    byte[] _arg1 = data.createByteArray();
                    registerKeyEventHandler(_arg07, _arg1);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    ICarProjectionKeyEventHandler _arg08 = ICarProjectionKeyEventHandler.Stub.asInterface(data.readStrongBinder());
                    unregisterKeyEventHandler(_arg08);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = (Messenger) Messenger.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    IBinder _arg12 = data.readStrongBinder();
                    startProjectionAccessPoint(_arg03, _arg12);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    IBinder _arg09 = data.readStrongBinder();
                    stopProjectionAccessPoint(_arg09);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    int _arg13 = data.readInt();
                    IBinder _arg2 = data.readStrongBinder();
                    boolean requestBluetoothProfileInhibit = requestBluetoothProfileInhibit(_arg04, _arg13, _arg2);
                    reply.writeNoException();
                    reply.writeInt(requestBluetoothProfileInhibit ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg05 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    int _arg14 = data.readInt();
                    IBinder _arg22 = data.readStrongBinder();
                    boolean releaseBluetoothProfileInhibit = releaseBluetoothProfileInhibit(_arg05, _arg14, _arg22);
                    reply.writeNoException();
                    reply.writeInt(releaseBluetoothProfileInhibit ? 1 : 0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg06 = ProjectionStatus.CREATOR.createFromParcel(data);
                    } else {
                        _arg06 = null;
                    }
                    IBinder _arg15 = data.readStrongBinder();
                    updateProjectionStatus(_arg06, _arg15);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    ICarProjectionStatusListener _arg010 = ICarProjectionStatusListener.Stub.asInterface(data.readStrongBinder());
                    registerProjectionStatusListener(_arg010);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    ICarProjectionStatusListener _arg011 = ICarProjectionStatusListener.Stub.asInterface(data.readStrongBinder());
                    unregisterProjectionStatusListener(_arg011);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    Bundle _result = getProjectionOptions();
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg012 = data.readInt();
                    int[] _result2 = getAvailableWifiChannels(_arg012);
                    reply.writeNoException();
                    reply.writeIntArray(_result2);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarProjection {
            public static ICarProjection sDefaultImpl;
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

            @Override // android.car.ICarProjection
            public void registerProjectionRunner(Intent serviceIntent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (serviceIntent != null) {
                        _data.writeInt(1);
                        serviceIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerProjectionRunner(serviceIntent);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public void unregisterProjectionRunner(Intent serviceIntent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (serviceIntent != null) {
                        _data.writeInt(1);
                        serviceIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterProjectionRunner(serviceIntent);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public void registerKeyEventHandler(ICarProjectionKeyEventHandler eventHandler, byte[] eventMask) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(eventHandler != null ? eventHandler.asBinder() : null);
                    _data.writeByteArray(eventMask);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerKeyEventHandler(eventHandler, eventMask);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public void unregisterKeyEventHandler(ICarProjectionKeyEventHandler eventHandler) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(eventHandler != null ? eventHandler.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterKeyEventHandler(eventHandler);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public void startProjectionAccessPoint(Messenger messenger, IBinder binder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (messenger != null) {
                        _data.writeInt(1);
                        messenger.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(binder);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startProjectionAccessPoint(messenger, binder);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public void stopProjectionAccessPoint(IBinder binder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(binder);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopProjectionAccessPoint(binder);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public boolean requestBluetoothProfileInhibit(BluetoothDevice device, int profile, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(profile);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestBluetoothProfileInhibit(device, profile, token);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public boolean releaseBluetoothProfileInhibit(BluetoothDevice device, int profile, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(profile);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().releaseBluetoothProfileInhibit(device, profile, token);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public void updateProjectionStatus(ProjectionStatus status, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        _data.writeInt(1);
                        status.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateProjectionStatus(status, token);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public void registerProjectionStatusListener(ICarProjectionStatusListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerProjectionStatusListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public void unregisterProjectionStatusListener(ICarProjectionStatusListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterProjectionStatusListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public Bundle getProjectionOptions() throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProjectionOptions();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarProjection
            public int[] getAvailableWifiChannels(int band) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(band);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableWifiChannels(band);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarProjection impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarProjection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
