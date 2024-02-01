package android.car.media;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarLibLog;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.media.ICarAudio;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
@SystemApi
/* loaded from: classes.dex */
public final class CarAudioManager implements CarManagerBase {
    private static final String VOLUME_SETTINGS_KEY_FOR_GROUP_PREFIX = "android.car.VOLUME_GROUP/";
    private final ContentResolver mContentResolver;
    private final ICarAudio mService;

    public static String getVolumeSettingsKeyForGroup(int groupId) {
        return VOLUME_SETTINGS_KEY_FOR_GROUP_PREFIX + groupId;
    }

    @SystemApi
    public void registerVolumeChangeObserver(ContentObserver observer) {
        this.mContentResolver.registerContentObserver(Settings.Global.getUriFor(VOLUME_SETTINGS_KEY_FOR_GROUP_PREFIX), true, observer);
    }

    @SystemApi
    public void unregisterVolumeChangeObserver(ContentObserver observer) {
        this.mContentResolver.unregisterContentObserver(observer);
    }

    @SystemApi
    public void setGroupVolume(int groupId, int index, int flags) throws CarNotConnectedException {
        try {
            this.mService.setGroupVolume(groupId, index, flags);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "setGroupVolume failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public int getGroupMaxVolume(int groupId) throws CarNotConnectedException {
        try {
            return this.mService.getGroupMaxVolume(groupId);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getUsageMaxVolume failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public int getGroupMinVolume(int groupId) throws CarNotConnectedException {
        try {
            return this.mService.getGroupMinVolume(groupId);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getUsageMinVolume failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public int getGroupVolume(int groupId) throws CarNotConnectedException {
        try {
            return this.mService.getGroupVolume(groupId);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getUsageVolume failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public void setFadeTowardFront(float value) throws CarNotConnectedException {
        try {
            this.mService.setFadeTowardFront(value);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "setFadeTowardFront failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public void setBalanceTowardRight(float value) throws CarNotConnectedException {
        try {
            this.mService.setBalanceTowardRight(value);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "setBalanceTowardRight failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public String[] getExternalSources() throws CarNotConnectedException {
        try {
            return this.mService.getExternalSources();
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getExternalSources failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public CarAudioPatchHandle createAudioPatch(String sourceAddress, int usage, int gainInMillibels) throws CarNotConnectedException {
        try {
            return this.mService.createAudioPatch(sourceAddress, usage, gainInMillibels);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "createAudioPatch failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public void releaseAudioPatch(CarAudioPatchHandle patch) throws CarNotConnectedException {
        try {
            this.mService.releaseAudioPatch(patch);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "releaseAudioPatch failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public int getVolumeGroupCount() throws CarNotConnectedException {
        try {
            return this.mService.getVolumeGroupCount();
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getVolumeGroupCount failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public int getVolumeGroupIdForUsage(int usage) throws CarNotConnectedException {
        try {
            return this.mService.getVolumeGroupIdForUsage(usage);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getVolumeGroupIdForUsage failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public int[] getUsagesForVolumeGroupId(int groupId) throws CarNotConnectedException {
        try {
            return this.mService.getUsagesForVolumeGroupId(groupId);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getUsagesForVolumeGroupId failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public void registerVolumeCallback(IBinder binder) throws CarNotConnectedException {
        try {
            this.mService.registerVolumeCallback(binder);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "registerVolumeCallback failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public void unregisterVolumeCallback(IBinder binder) throws CarNotConnectedException {
        try {
            this.mService.unregisterVolumeCallback(binder);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "unregisterVolumeCallback failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.AUDIO_SERVICE;
    }

    public CarAudioManager(IBinder service, Context context, Handler handler) {
        this.mContentResolver = context.getContentResolver();
        this.mService = ICarAudio.Stub.asInterface(service);
    }
}
