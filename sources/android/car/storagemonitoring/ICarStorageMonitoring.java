package android.car.storagemonitoring;

import android.car.storagemonitoring.IIoStatsListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface ICarStorageMonitoring extends IInterface {
    List<IoStatsEntry> getAggregateIoStats() throws RemoteException;

    List<IoStatsEntry> getBootIoStats() throws RemoteException;

    List<IoStats> getIoStatsDeltas() throws RemoteException;

    int getPreEolIndicatorStatus() throws RemoteException;

    long getShutdownDiskWriteAmount() throws RemoteException;

    WearEstimate getWearEstimate() throws RemoteException;

    List<WearEstimateChange> getWearEstimateHistory() throws RemoteException;

    void registerListener(IIoStatsListener iIoStatsListener) throws RemoteException;

    void unregisterListener(IIoStatsListener iIoStatsListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarStorageMonitoring {
        @Override // android.car.storagemonitoring.ICarStorageMonitoring
        public int getPreEolIndicatorStatus() throws RemoteException {
            return 0;
        }

        @Override // android.car.storagemonitoring.ICarStorageMonitoring
        public WearEstimate getWearEstimate() throws RemoteException {
            return null;
        }

        @Override // android.car.storagemonitoring.ICarStorageMonitoring
        public List<WearEstimateChange> getWearEstimateHistory() throws RemoteException {
            return null;
        }

        @Override // android.car.storagemonitoring.ICarStorageMonitoring
        public List<IoStatsEntry> getBootIoStats() throws RemoteException {
            return null;
        }

        @Override // android.car.storagemonitoring.ICarStorageMonitoring
        public List<IoStatsEntry> getAggregateIoStats() throws RemoteException {
            return null;
        }

        @Override // android.car.storagemonitoring.ICarStorageMonitoring
        public List<IoStats> getIoStatsDeltas() throws RemoteException {
            return null;
        }

        @Override // android.car.storagemonitoring.ICarStorageMonitoring
        public void registerListener(IIoStatsListener listener) throws RemoteException {
        }

        @Override // android.car.storagemonitoring.ICarStorageMonitoring
        public void unregisterListener(IIoStatsListener listener) throws RemoteException {
        }

        @Override // android.car.storagemonitoring.ICarStorageMonitoring
        public long getShutdownDiskWriteAmount() throws RemoteException {
            return 0L;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarStorageMonitoring {
        private static final String DESCRIPTOR = "android.car.storagemonitoring.ICarStorageMonitoring";
        static final int TRANSACTION_getAggregateIoStats = 6;
        static final int TRANSACTION_getBootIoStats = 5;
        static final int TRANSACTION_getIoStatsDeltas = 7;
        static final int TRANSACTION_getPreEolIndicatorStatus = 2;
        static final int TRANSACTION_getShutdownDiskWriteAmount = 10;
        static final int TRANSACTION_getWearEstimate = 3;
        static final int TRANSACTION_getWearEstimateHistory = 4;
        static final int TRANSACTION_registerListener = 8;
        static final int TRANSACTION_unregisterListener = 9;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarStorageMonitoring asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarStorageMonitoring)) {
                return (ICarStorageMonitoring) iin;
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
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getPreEolIndicatorStatus();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    WearEstimate _result2 = getWearEstimate();
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
                    List<WearEstimateChange> _result3 = getWearEstimateHistory();
                    reply.writeNoException();
                    reply.writeTypedList(_result3);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    List<IoStatsEntry> _result4 = getBootIoStats();
                    reply.writeNoException();
                    reply.writeTypedList(_result4);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    List<IoStatsEntry> _result5 = getAggregateIoStats();
                    reply.writeNoException();
                    reply.writeTypedList(_result5);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    List<IoStats> _result6 = getIoStatsDeltas();
                    reply.writeNoException();
                    reply.writeTypedList(_result6);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    IIoStatsListener _arg0 = IIoStatsListener.Stub.asInterface(data.readStrongBinder());
                    registerListener(_arg0);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    IIoStatsListener _arg02 = IIoStatsListener.Stub.asInterface(data.readStrongBinder());
                    unregisterListener(_arg02);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    long _result7 = getShutdownDiskWriteAmount();
                    reply.writeNoException();
                    reply.writeLong(_result7);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarStorageMonitoring {
            public static ICarStorageMonitoring sDefaultImpl;
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

            @Override // android.car.storagemonitoring.ICarStorageMonitoring
            public int getPreEolIndicatorStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreEolIndicatorStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.storagemonitoring.ICarStorageMonitoring
            public WearEstimate getWearEstimate() throws RemoteException {
                WearEstimate _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWearEstimate();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = WearEstimate.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.storagemonitoring.ICarStorageMonitoring
            public List<WearEstimateChange> getWearEstimateHistory() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWearEstimateHistory();
                    }
                    _reply.readException();
                    List<WearEstimateChange> _result = _reply.createTypedArrayList(WearEstimateChange.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.storagemonitoring.ICarStorageMonitoring
            public List<IoStatsEntry> getBootIoStats() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBootIoStats();
                    }
                    _reply.readException();
                    List<IoStatsEntry> _result = _reply.createTypedArrayList(IoStatsEntry.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.storagemonitoring.ICarStorageMonitoring
            public List<IoStatsEntry> getAggregateIoStats() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAggregateIoStats();
                    }
                    _reply.readException();
                    List<IoStatsEntry> _result = _reply.createTypedArrayList(IoStatsEntry.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.storagemonitoring.ICarStorageMonitoring
            public List<IoStats> getIoStatsDeltas() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIoStatsDeltas();
                    }
                    _reply.readException();
                    List<IoStats> _result = _reply.createTypedArrayList(IoStats.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.storagemonitoring.ICarStorageMonitoring
            public void registerListener(IIoStatsListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.storagemonitoring.ICarStorageMonitoring
            public void unregisterListener(IIoStatsListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.storagemonitoring.ICarStorageMonitoring
            public long getShutdownDiskWriteAmount() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShutdownDiskWriteAmount();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarStorageMonitoring impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarStorageMonitoring getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
