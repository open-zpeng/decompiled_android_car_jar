package android.car.hardware.property;

import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.ICarPropertyEventListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface ICarProperty extends IInterface {
    CarPropertyValue getProperty(int i, int i2) throws RemoteException;

    CarPropertyConfig[] getPropertyArray() throws RemoteException;

    List<CarPropertyConfig> getPropertyList() throws RemoteException;

    void registerListener(int i, float f, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException;

    void setMultiProperties(List<CarPropertyValue> list) throws RemoteException;

    void setProperty(CarPropertyValue carPropertyValue) throws RemoteException;

    void unregisterListener(int i, ICarPropertyEventListener iCarPropertyEventListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarProperty {
        private static final String DESCRIPTOR = "android.car.hardware.property.ICarProperty";
        static final int TRANSACTION_getProperty = 4;
        static final int TRANSACTION_getPropertyArray = 7;
        static final int TRANSACTION_getPropertyList = 3;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_setMultiProperties = 6;
        static final int TRANSACTION_setProperty = 5;
        static final int TRANSACTION_unregisterListener = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarProperty asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarProperty)) {
                return (ICarProperty) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CarPropertyValue _arg0;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    float _arg1 = data.readFloat();
                    ICarPropertyEventListener _arg2 = ICarPropertyEventListener.Stub.asInterface(data.readStrongBinder());
                    registerListener(_arg02, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    ICarPropertyEventListener _arg12 = ICarPropertyEventListener.Stub.asInterface(data.readStrongBinder());
                    unregisterListener(_arg03, _arg12);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    List<CarPropertyConfig> _result = getPropertyList();
                    reply.writeNoException();
                    reply.writeTypedList(_result);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    int _arg13 = data.readInt();
                    CarPropertyValue _result2 = getProperty(_arg04, _arg13);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = CarPropertyValue.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    setProperty(_arg0);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    List<CarPropertyValue> _arg05 = data.createTypedArrayList(CarPropertyValue.CREATOR);
                    setMultiProperties(_arg05);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    CarPropertyConfig[] _result3 = getPropertyArray();
                    reply.writeNoException();
                    reply.writeTypedArray(_result3, 1);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarProperty {
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

            @Override // android.car.hardware.property.ICarProperty
            public void registerListener(int propId, float rate, ICarPropertyEventListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(propId);
                    _data.writeFloat(rate);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public void unregisterListener(int propId, ICarPropertyEventListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(propId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public List<CarPropertyConfig> getPropertyList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    List<CarPropertyConfig> _result = _reply.createTypedArrayList(CarPropertyConfig.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public CarPropertyValue getProperty(int prop, int zone) throws RemoteException {
                CarPropertyValue _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(prop);
                    _data.writeInt(zone);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarPropertyValue.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public void setProperty(CarPropertyValue prop) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (prop != null) {
                        _data.writeInt(1);
                        prop.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public void setMultiProperties(List<CarPropertyValue> props) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(props);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.hardware.property.ICarProperty
            public CarPropertyConfig[] getPropertyArray() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    CarPropertyConfig[] _result = (CarPropertyConfig[]) _reply.createTypedArray(CarPropertyConfig.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
