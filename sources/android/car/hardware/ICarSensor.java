package android.car.hardware;

import android.car.hardware.ICarSensorEventListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarSensor extends IInterface {
    CarSensorEvent getLatestSensorEvent(int i) throws RemoteException;

    CarSensorConfig getSensorConfig(int i) throws RemoteException;

    int[] getSupportedSensors() throws RemoteException;

    boolean registerOrUpdateSensorListener(int i, int i2, ICarSensorEventListener iCarSensorEventListener) throws RemoteException;

    void unregisterSensorListener(int i, ICarSensorEventListener iCarSensorEventListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarSensor {
        @Override // android.car.hardware.ICarSensor
        public int[] getSupportedSensors() throws RemoteException {
            return null;
        }

        @Override // android.car.hardware.ICarSensor
        public boolean registerOrUpdateSensorListener(int sensorType, int rate, ICarSensorEventListener callback) throws RemoteException {
            return false;
        }

        @Override // android.car.hardware.ICarSensor
        public CarSensorEvent getLatestSensorEvent(int sensorType) throws RemoteException {
            return null;
        }

        @Override // android.car.hardware.ICarSensor
        public void unregisterSensorListener(int sensorType, ICarSensorEventListener callback) throws RemoteException {
        }

        @Override // android.car.hardware.ICarSensor
        public CarSensorConfig getSensorConfig(int sensorType) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarSensor {
        private static final String DESCRIPTOR = "android.car.hardware.ICarSensor";
        static final int TRANSACTION_getLatestSensorEvent = 3;
        static final int TRANSACTION_getSensorConfig = 5;
        static final int TRANSACTION_getSupportedSensors = 1;
        static final int TRANSACTION_registerOrUpdateSensorListener = 2;
        static final int TRANSACTION_unregisterSensorListener = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarSensor asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarSensor)) {
                return (ICarSensor) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int[] _result = getSupportedSensors();
                reply.writeNoException();
                reply.writeIntArray(_result);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0 = data.readInt();
                int _arg1 = data.readInt();
                ICarSensorEventListener _arg2 = ICarSensorEventListener.Stub.asInterface(data.readStrongBinder());
                boolean registerOrUpdateSensorListener = registerOrUpdateSensorListener(_arg0, _arg1, _arg2);
                reply.writeNoException();
                reply.writeInt(registerOrUpdateSensorListener ? 1 : 0);
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                CarSensorEvent _result2 = getLatestSensorEvent(_arg02);
                reply.writeNoException();
                if (_result2 != null) {
                    reply.writeInt(1);
                    _result2.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                int _arg03 = data.readInt();
                ICarSensorEventListener _arg12 = ICarSensorEventListener.Stub.asInterface(data.readStrongBinder());
                unregisterSensorListener(_arg03, _arg12);
                reply.writeNoException();
                return true;
            } else if (code != 5) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            } else {
                data.enforceInterface(DESCRIPTOR);
                int _arg04 = data.readInt();
                CarSensorConfig _result3 = getSensorConfig(_arg04);
                reply.writeNoException();
                if (_result3 != null) {
                    reply.writeInt(1);
                    _result3.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarSensor {
            public static ICarSensor sDefaultImpl;
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

            @Override // android.car.hardware.ICarSensor
            public int[] getSupportedSensors() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedSensors();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.ICarSensor
            public boolean registerOrUpdateSensorListener(int sensorType, int rate, ICarSensorEventListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sensorType);
                    _data.writeInt(rate);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerOrUpdateSensorListener(sensorType, rate, callback);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.ICarSensor
            public CarSensorEvent getLatestSensorEvent(int sensorType) throws RemoteException {
                CarSensorEvent _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sensorType);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLatestSensorEvent(sensorType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarSensorEvent.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.ICarSensor
            public void unregisterSensorListener(int sensorType, ICarSensorEventListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sensorType);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterSensorListener(sensorType, callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.ICarSensor
            public CarSensorConfig getSensorConfig(int sensorType) throws RemoteException {
                CarSensorConfig _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sensorType);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSensorConfig(sensorType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarSensorConfig.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarSensor impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarSensor getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
