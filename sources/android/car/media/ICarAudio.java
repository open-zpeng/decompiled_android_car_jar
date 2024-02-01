package android.car.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICarAudio extends IInterface {
    CarAudioPatchHandle createAudioPatch(String str, int i, int i2) throws RemoteException;

    int getCurrentAudioFocusUsage() throws RemoteException;

    String[] getExternalSources() throws RemoteException;

    int getGroupMaxVolume(int i) throws RemoteException;

    int getGroupMinVolume(int i) throws RemoteException;

    int getGroupVolume(int i) throws RemoteException;

    int[] getUsagesForVolumeGroupId(int i) throws RemoteException;

    int getVolumeGroupCount() throws RemoteException;

    int getVolumeGroupIdForStreamType(int i) throws RemoteException;

    int getVolumeGroupIdForUsage(int i) throws RemoteException;

    void registerVolumeCallback(IBinder iBinder) throws RemoteException;

    void releaseAudioPatch(CarAudioPatchHandle carAudioPatchHandle) throws RemoteException;

    void setBalanceTowardRight(float f) throws RemoteException;

    void setFadeTowardFront(float f) throws RemoteException;

    void setGroupVolume(int i, int i2, int i3) throws RemoteException;

    void unregisterVolumeCallback(IBinder iBinder) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICarAudio {
        private static final String DESCRIPTOR = "android.car.media.ICarAudio";
        static final int TRANSACTION_createAudioPatch = 8;
        static final int TRANSACTION_getCurrentAudioFocusUsage = 16;
        static final int TRANSACTION_getExternalSources = 7;
        static final int TRANSACTION_getGroupMaxVolume = 2;
        static final int TRANSACTION_getGroupMinVolume = 3;
        static final int TRANSACTION_getGroupVolume = 4;
        static final int TRANSACTION_getUsagesForVolumeGroupId = 12;
        static final int TRANSACTION_getVolumeGroupCount = 10;
        static final int TRANSACTION_getVolumeGroupIdForStreamType = 13;
        static final int TRANSACTION_getVolumeGroupIdForUsage = 11;
        static final int TRANSACTION_registerVolumeCallback = 14;
        static final int TRANSACTION_releaseAudioPatch = 9;
        static final int TRANSACTION_setBalanceTowardRight = 6;
        static final int TRANSACTION_setFadeTowardFront = 5;
        static final int TRANSACTION_setGroupVolume = 1;
        static final int TRANSACTION_unregisterVolumeCallback = 15;

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
                    int _arg02 = data.readInt();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    setGroupVolume(_arg02, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    int _result = getGroupMaxVolume(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    int _result2 = getGroupMinVolume(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    int _result3 = getGroupVolume(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    float _arg06 = data.readFloat();
                    setFadeTowardFront(_arg06);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    float _arg07 = data.readFloat();
                    setBalanceTowardRight(_arg07);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String[] _result4 = getExternalSources();
                    reply.writeNoException();
                    reply.writeStringArray(_result4);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    int _arg12 = data.readInt();
                    int _arg22 = data.readInt();
                    CarAudioPatchHandle _result5 = createAudioPatch(_arg08, _arg12, _arg22);
                    reply.writeNoException();
                    if (_result5 != null) {
                        reply.writeInt(1);
                        _result5.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = CarAudioPatchHandle.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    releaseAudioPatch(_arg0);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = getVolumeGroupCount();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    int _result7 = getVolumeGroupIdForUsage(_arg09);
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    int[] _result8 = getUsagesForVolumeGroupId(_arg010);
                    reply.writeNoException();
                    reply.writeIntArray(_result8);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg011 = data.readInt();
                    int _result9 = getVolumeGroupIdForStreamType(_arg011);
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    IBinder _arg012 = data.readStrongBinder();
                    registerVolumeCallback(_arg012);
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    IBinder _arg013 = data.readStrongBinder();
                    unregisterVolumeCallback(_arg013);
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    int _result10 = getCurrentAudioFocusUsage();
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ICarAudio {
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
            public void setGroupVolume(int groupId, int index, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(groupId);
                    _data.writeInt(index);
                    _data.writeInt(flags);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getGroupMaxVolume(int groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(groupId);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getGroupMinVolume(int groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(groupId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getGroupVolume(int groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(groupId);
                    this.mRemote.transact(4, _data, _reply, 0);
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
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
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
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
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
                    this.mRemote.transact(7, _data, _reply, 0);
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
                    this.mRemote.transact(8, _data, _reply, 0);
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
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getVolumeGroupCount() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getVolumeGroupIdForUsage(int usage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(usage);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int[] getUsagesForVolumeGroupId(int groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(groupId);
                    this.mRemote.transact(12, _data, _reply, 0);
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
                    this.mRemote.transact(13, _data, _reply, 0);
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
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
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
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getCurrentAudioFocusUsage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
