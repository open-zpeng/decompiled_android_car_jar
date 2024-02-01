package android.car.drivingstate;

import android.car.drivingstate.ICarUxRestrictionsChangeListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface ICarUxRestrictionsManager extends IInterface {
    List<CarUxRestrictionsConfiguration> getConfigs() throws RemoteException;

    CarUxRestrictions getCurrentUxRestrictions(int i) throws RemoteException;

    String getRestrictionMode() throws RemoteException;

    List<CarUxRestrictionsConfiguration> getStagedConfigs() throws RemoteException;

    void registerUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener iCarUxRestrictionsChangeListener, int i) throws RemoteException;

    boolean saveUxRestrictionsConfigurationForNextBoot(List<CarUxRestrictionsConfiguration> list) throws RemoteException;

    boolean setRestrictionMode(String str) throws RemoteException;

    void unregisterUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener iCarUxRestrictionsChangeListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarUxRestrictionsManager {
        @Override // android.car.drivingstate.ICarUxRestrictionsManager
        public void registerUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener listener, int displayId) throws RemoteException {
        }

        @Override // android.car.drivingstate.ICarUxRestrictionsManager
        public void unregisterUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener listener) throws RemoteException {
        }

        @Override // android.car.drivingstate.ICarUxRestrictionsManager
        public CarUxRestrictions getCurrentUxRestrictions(int displayId) throws RemoteException {
            return null;
        }

        @Override // android.car.drivingstate.ICarUxRestrictionsManager
        public boolean saveUxRestrictionsConfigurationForNextBoot(List<CarUxRestrictionsConfiguration> configs) throws RemoteException {
            return false;
        }

        @Override // android.car.drivingstate.ICarUxRestrictionsManager
        public List<CarUxRestrictionsConfiguration> getStagedConfigs() throws RemoteException {
            return null;
        }

        @Override // android.car.drivingstate.ICarUxRestrictionsManager
        public List<CarUxRestrictionsConfiguration> getConfigs() throws RemoteException {
            return null;
        }

        @Override // android.car.drivingstate.ICarUxRestrictionsManager
        public boolean setRestrictionMode(String mode) throws RemoteException {
            return false;
        }

        @Override // android.car.drivingstate.ICarUxRestrictionsManager
        public String getRestrictionMode() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarUxRestrictionsManager {
        private static final String DESCRIPTOR = "android.car.drivingstate.ICarUxRestrictionsManager";
        static final int TRANSACTION_getConfigs = 6;
        static final int TRANSACTION_getCurrentUxRestrictions = 3;
        static final int TRANSACTION_getRestrictionMode = 12;
        static final int TRANSACTION_getStagedConfigs = 5;
        static final int TRANSACTION_registerUxRestrictionsChangeListener = 1;
        static final int TRANSACTION_saveUxRestrictionsConfigurationForNextBoot = 4;
        static final int TRANSACTION_setRestrictionMode = 11;
        static final int TRANSACTION_unregisterUxRestrictionsChangeListener = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarUxRestrictionsManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarUxRestrictionsManager)) {
                return (ICarUxRestrictionsManager) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 11) {
                data.enforceInterface(DESCRIPTOR);
                String _arg0 = data.readString();
                boolean restrictionMode = setRestrictionMode(_arg0);
                reply.writeNoException();
                reply.writeInt(restrictionMode ? 1 : 0);
                return true;
            } else if (code == 12) {
                data.enforceInterface(DESCRIPTOR);
                String _result = getRestrictionMode();
                reply.writeNoException();
                reply.writeString(_result);
                return true;
            } else if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            } else {
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        ICarUxRestrictionsChangeListener _arg02 = ICarUxRestrictionsChangeListener.Stub.asInterface(data.readStrongBinder());
                        int _arg1 = data.readInt();
                        registerUxRestrictionsChangeListener(_arg02, _arg1);
                        reply.writeNoException();
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        ICarUxRestrictionsChangeListener _arg03 = ICarUxRestrictionsChangeListener.Stub.asInterface(data.readStrongBinder());
                        unregisterUxRestrictionsChangeListener(_arg03);
                        reply.writeNoException();
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        int _arg04 = data.readInt();
                        CarUxRestrictions _result2 = getCurrentUxRestrictions(_arg04);
                        reply.writeNoException();
                        if (_result2 != null) {
                            reply.writeInt(1);
                            _result2.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        List<CarUxRestrictionsConfiguration> _arg05 = data.createTypedArrayList(CarUxRestrictionsConfiguration.CREATOR);
                        boolean saveUxRestrictionsConfigurationForNextBoot = saveUxRestrictionsConfigurationForNextBoot(_arg05);
                        reply.writeNoException();
                        reply.writeInt(saveUxRestrictionsConfigurationForNextBoot ? 1 : 0);
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        List<CarUxRestrictionsConfiguration> _result3 = getStagedConfigs();
                        reply.writeNoException();
                        reply.writeTypedList(_result3);
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        List<CarUxRestrictionsConfiguration> _result4 = getConfigs();
                        reply.writeNoException();
                        reply.writeTypedList(_result4);
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarUxRestrictionsManager {
            public static ICarUxRestrictionsManager sDefaultImpl;
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

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public void registerUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener listener, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerUxRestrictionsChangeListener(listener, displayId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public void unregisterUxRestrictionsChangeListener(ICarUxRestrictionsChangeListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterUxRestrictionsChangeListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public CarUxRestrictions getCurrentUxRestrictions(int displayId) throws RemoteException {
                CarUxRestrictions _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentUxRestrictions(displayId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarUxRestrictions.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public boolean saveUxRestrictionsConfigurationForNextBoot(List<CarUxRestrictionsConfiguration> configs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(configs);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().saveUxRestrictionsConfigurationForNextBoot(configs);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public List<CarUxRestrictionsConfiguration> getStagedConfigs() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStagedConfigs();
                    }
                    _reply.readException();
                    List<CarUxRestrictionsConfiguration> _result = _reply.createTypedArrayList(CarUxRestrictionsConfiguration.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public List<CarUxRestrictionsConfiguration> getConfigs() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigs();
                    }
                    _reply.readException();
                    List<CarUxRestrictionsConfiguration> _result = _reply.createTypedArrayList(CarUxRestrictionsConfiguration.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public boolean setRestrictionMode(String mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mode);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRestrictionMode(mode);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.drivingstate.ICarUxRestrictionsManager
            public String getRestrictionMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRestrictionMode();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarUxRestrictionsManager impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarUxRestrictionsManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
