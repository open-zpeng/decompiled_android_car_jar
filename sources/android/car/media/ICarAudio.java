package android.car.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICarAudio extends IInterface {
    boolean clearZoneIdForUid(int i) throws RemoteException;

    CarAudioPatchHandle createAudioPatch(String str, int i, int i2) throws RemoteException;

    int[] getAudioZoneIds() throws RemoteException;

    String[] getExternalSources() throws RemoteException;

    int getGroupMaxVolume(int i, int i2) throws RemoteException;

    int getGroupMinVolume(int i, int i2) throws RemoteException;

    int getGroupVolume(int i, int i2) throws RemoteException;

    int[] getUsagesForVolumeGroupId(int i, int i2) throws RemoteException;

    int getVolumeGroupCount(int i) throws RemoteException;

    int getVolumeGroupIdForStreamType(int i) throws RemoteException;

    int getVolumeGroupIdForUsage(int i, int i2) throws RemoteException;

    int getZoneIdForDisplayPortId(byte b) throws RemoteException;

    int getZoneIdForUid(int i) throws RemoteException;

    boolean isDynamicRoutingEnabled() throws RemoteException;

    void registerVolumeCallback(IBinder iBinder) throws RemoteException;

    void releaseAudioPatch(CarAudioPatchHandle carAudioPatchHandle) throws RemoteException;

    void setBalanceTowardRight(float f) throws RemoteException;

    void setFadeTowardFront(float f) throws RemoteException;

    void setGroupVolume(int i, int i2, int i3, int i4) throws RemoteException;

    boolean setZoneIdForUid(int i, int i2) throws RemoteException;

    void unregisterVolumeCallback(IBinder iBinder) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICarAudio {
        @Override // android.car.media.ICarAudio
        public boolean isDynamicRoutingEnabled() throws RemoteException {
            return false;
        }

        @Override // android.car.media.ICarAudio
        public void setGroupVolume(int zoneId, int groupId, int index, int flags) throws RemoteException {
        }

        @Override // android.car.media.ICarAudio
        public int getGroupMaxVolume(int zoneId, int groupId) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarAudio
        public int getGroupMinVolume(int zoneId, int groupId) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarAudio
        public int getGroupVolume(int zoneId, int groupId) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarAudio
        public void setFadeTowardFront(float value) throws RemoteException {
        }

        @Override // android.car.media.ICarAudio
        public void setBalanceTowardRight(float value) throws RemoteException {
        }

        @Override // android.car.media.ICarAudio
        public String[] getExternalSources() throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarAudio
        public CarAudioPatchHandle createAudioPatch(String sourceAddress, int usage, int gainInMillibels) throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarAudio
        public void releaseAudioPatch(CarAudioPatchHandle patch) throws RemoteException {
        }

        @Override // android.car.media.ICarAudio
        public int getVolumeGroupCount(int zoneId) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarAudio
        public int getVolumeGroupIdForUsage(int zoneId, int usage) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarAudio
        public int[] getUsagesForVolumeGroupId(int zoneId, int groupId) throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarAudio
        public int getVolumeGroupIdForStreamType(int streamType) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarAudio
        public int[] getAudioZoneIds() throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarAudio
        public int getZoneIdForUid(int uid) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarAudio
        public boolean setZoneIdForUid(int zoneId, int uid) throws RemoteException {
            return false;
        }

        @Override // android.car.media.ICarAudio
        public boolean clearZoneIdForUid(int uid) throws RemoteException {
            return false;
        }

        @Override // android.car.media.ICarAudio
        public int getZoneIdForDisplayPortId(byte displayPortId) throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarAudio
        public void registerVolumeCallback(IBinder binder) throws RemoteException {
        }

        @Override // android.car.media.ICarAudio
        public void unregisterVolumeCallback(IBinder binder) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarAudio {
        private static final String DESCRIPTOR = "android.car.media.ICarAudio";
        static final int TRANSACTION_clearZoneIdForUid = 18;
        static final int TRANSACTION_createAudioPatch = 9;
        static final int TRANSACTION_getAudioZoneIds = 15;
        static final int TRANSACTION_getExternalSources = 8;
        static final int TRANSACTION_getGroupMaxVolume = 3;
        static final int TRANSACTION_getGroupMinVolume = 4;
        static final int TRANSACTION_getGroupVolume = 5;
        static final int TRANSACTION_getUsagesForVolumeGroupId = 13;
        static final int TRANSACTION_getVolumeGroupCount = 11;
        static final int TRANSACTION_getVolumeGroupIdForStreamType = 14;
        static final int TRANSACTION_getVolumeGroupIdForUsage = 12;
        static final int TRANSACTION_getZoneIdForDisplayPortId = 19;
        static final int TRANSACTION_getZoneIdForUid = 16;
        static final int TRANSACTION_isDynamicRoutingEnabled = 1;
        static final int TRANSACTION_registerVolumeCallback = 20;
        static final int TRANSACTION_releaseAudioPatch = 10;
        static final int TRANSACTION_setBalanceTowardRight = 7;
        static final int TRANSACTION_setFadeTowardFront = 6;
        static final int TRANSACTION_setGroupVolume = 2;
        static final int TRANSACTION_setZoneIdForUid = 17;
        static final int TRANSACTION_unregisterVolumeCallback = 21;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarAudio asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarAudio)) {
                return (ICarAudio) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CarAudioPatchHandle _arg0;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    boolean isDynamicRoutingEnabled = isDynamicRoutingEnabled();
                    reply.writeNoException();
                    reply.writeInt(isDynamicRoutingEnabled ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    setGroupVolume(_arg02, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    int _arg12 = data.readInt();
                    int _result = getGroupMaxVolume(_arg03, _arg12);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    int _arg13 = data.readInt();
                    int _result2 = getGroupMinVolume(_arg04, _arg13);
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    int _arg14 = data.readInt();
                    int _result3 = getGroupVolume(_arg05, _arg14);
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    float _arg06 = data.readFloat();
                    setFadeTowardFront(_arg06);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    float _arg07 = data.readFloat();
                    setBalanceTowardRight(_arg07);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String[] _result4 = getExternalSources();
                    reply.writeNoException();
                    reply.writeStringArray(_result4);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    int _arg15 = data.readInt();
                    int _arg22 = data.readInt();
                    CarAudioPatchHandle _result5 = createAudioPatch(_arg08, _arg15, _arg22);
                    reply.writeNoException();
                    if (_result5 != null) {
                        reply.writeInt(1);
                        _result5.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = CarAudioPatchHandle.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    releaseAudioPatch(_arg0);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    int _result6 = getVolumeGroupCount(_arg09);
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    int _arg16 = data.readInt();
                    int _result7 = getVolumeGroupIdForUsage(_arg010, _arg16);
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg011 = data.readInt();
                    int _arg17 = data.readInt();
                    int[] _result8 = getUsagesForVolumeGroupId(_arg011, _arg17);
                    reply.writeNoException();
                    reply.writeIntArray(_result8);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg012 = data.readInt();
                    int _result9 = getVolumeGroupIdForStreamType(_arg012);
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result10 = getAudioZoneIds();
                    reply.writeNoException();
                    reply.writeIntArray(_result10);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg013 = data.readInt();
                    int _result11 = getZoneIdForUid(_arg013);
                    reply.writeNoException();
                    reply.writeInt(_result11);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg014 = data.readInt();
                    int _arg18 = data.readInt();
                    boolean zoneIdForUid = setZoneIdForUid(_arg014, _arg18);
                    reply.writeNoException();
                    reply.writeInt(zoneIdForUid ? 1 : 0);
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg015 = data.readInt();
                    boolean clearZoneIdForUid = clearZoneIdForUid(_arg015);
                    reply.writeNoException();
                    reply.writeInt(clearZoneIdForUid ? 1 : 0);
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg016 = data.readByte();
                    int _result12 = getZoneIdForDisplayPortId(_arg016);
                    reply.writeNoException();
                    reply.writeInt(_result12);
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    IBinder _arg017 = data.readStrongBinder();
                    registerVolumeCallback(_arg017);
                    reply.writeNoException();
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    IBinder _arg018 = data.readStrongBinder();
                    unregisterVolumeCallback(_arg018);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICarAudio {
            public static ICarAudio sDefaultImpl;
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

            @Override // android.car.media.ICarAudio
            public boolean isDynamicRoutingEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDynamicRoutingEnabled();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void setGroupVolume(int zoneId, int groupId, int index, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(zoneId);
                    _data.writeInt(groupId);
                    _data.writeInt(index);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setGroupVolume(zoneId, groupId, index, flags);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getGroupMaxVolume(int zoneId, int groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(zoneId);
                    _data.writeInt(groupId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGroupMaxVolume(zoneId, groupId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getGroupMinVolume(int zoneId, int groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(zoneId);
                    _data.writeInt(groupId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGroupMinVolume(zoneId, groupId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getGroupVolume(int zoneId, int groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(zoneId);
                    _data.writeInt(groupId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGroupVolume(zoneId, groupId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void setFadeTowardFront(float value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(value);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFadeTowardFront(value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void setBalanceTowardRight(float value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(value);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBalanceTowardRight(value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public String[] getExternalSources() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getExternalSources();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public CarAudioPatchHandle createAudioPatch(String sourceAddress, int usage, int gainInMillibels) throws RemoteException {
                CarAudioPatchHandle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(sourceAddress);
                    _data.writeInt(usage);
                    _data.writeInt(gainInMillibels);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createAudioPatch(sourceAddress, usage, gainInMillibels);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarAudioPatchHandle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void releaseAudioPatch(CarAudioPatchHandle patch) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (patch != null) {
                        _data.writeInt(1);
                        patch.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseAudioPatch(patch);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getVolumeGroupCount(int zoneId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(zoneId);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVolumeGroupCount(zoneId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getVolumeGroupIdForUsage(int zoneId, int usage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(zoneId);
                    _data.writeInt(usage);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVolumeGroupIdForUsage(zoneId, usage);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int[] getUsagesForVolumeGroupId(int zoneId, int groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(zoneId);
                    _data.writeInt(groupId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUsagesForVolumeGroupId(zoneId, groupId);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getVolumeGroupIdForStreamType(int streamType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(streamType);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVolumeGroupIdForStreamType(streamType);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int[] getAudioZoneIds() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioZoneIds();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getZoneIdForUid(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getZoneIdForUid(uid);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public boolean setZoneIdForUid(int zoneId, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(zoneId);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setZoneIdForUid(zoneId, uid);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public boolean clearZoneIdForUid(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearZoneIdForUid(uid);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getZoneIdForDisplayPortId(byte displayPortId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(displayPortId);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getZoneIdForDisplayPortId(displayPortId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void registerVolumeCallback(IBinder binder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(binder);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerVolumeCallback(binder);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void unregisterVolumeCallback(IBinder binder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(binder);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterVolumeCallback(binder);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarAudio impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarAudio getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
