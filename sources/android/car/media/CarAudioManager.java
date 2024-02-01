package android.car.media;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarLibLog;
import android.car.CarManagerBase;
import android.car.media.ICarAudio;
import android.car.media.ICarVolumeCallback;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Display;
import android.view.DisplayAddress;
import java.util.ArrayList;
import java.util.List;

@SystemApi
/* loaded from: classes.dex */
public final class CarAudioManager extends CarManagerBase {
    @SystemApi
    public static final String AUDIOFOCUS_EXTRA_RECEIVE_DUCKING_EVENTS = "android.car.media.AUDIOFOCUS_EXTRA_RECEIVE_DUCKING_EVENTS";
    public static final String AUDIOFOCUS_EXTRA_REQUEST_ZONE_ID = "android.car.media.AUDIOFOCUS_EXTRA_REQUEST_ZONE_ID";
    @SystemApi
    public static final int PRIMARY_AUDIO_ZONE = 0;
    private final ICarVolumeCallback mCarVolumeCallbackImpl;
    private final List<CarVolumeCallback> mCarVolumeCallbacks;
    private final ICarAudio mService;

    public boolean isDynamicRoutingEnabled() {
        try {
            return this.mService.isDynamicRoutingEnabled();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    @SystemApi
    public void setGroupVolume(int groupId, int index, int flags) {
        setGroupVolume(0, groupId, index, flags);
    }

    @SystemApi
    public void setGroupVolume(int zoneId, int groupId, int index, int flags) {
        try {
            this.mService.setGroupVolume(zoneId, groupId, index, flags);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    @SystemApi
    public int getGroupMaxVolume(int groupId) {
        return getGroupMaxVolume(0, groupId);
    }

    @SystemApi
    public int getGroupMaxVolume(int zoneId, int groupId) {
        try {
            return this.mService.getGroupMaxVolume(zoneId, groupId);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    @SystemApi
    public int getGroupMinVolume(int groupId) {
        return getGroupMinVolume(0, groupId);
    }

    @SystemApi
    public int getGroupMinVolume(int zoneId, int groupId) {
        try {
            return this.mService.getGroupMinVolume(zoneId, groupId);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    @SystemApi
    public int getGroupVolume(int groupId) {
        return getGroupVolume(0, groupId);
    }

    @SystemApi
    public int getGroupVolume(int zoneId, int groupId) {
        try {
            return this.mService.getGroupVolume(zoneId, groupId);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    @SystemApi
    public void setFadeTowardFront(float value) {
        try {
            this.mService.setFadeTowardFront(value);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    @SystemApi
    public void setBalanceTowardRight(float value) {
        try {
            this.mService.setBalanceTowardRight(value);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    @SystemApi
    public String[] getExternalSources() {
        try {
            return this.mService.getExternalSources();
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
            return new String[0];
        }
    }

    @SystemApi
    public CarAudioPatchHandle createAudioPatch(String sourceAddress, int usage, int gainInMillibels) {
        Log.d(CarLibLog.TAG_CAR, "createAudioPatch  ");
        try {
            return this.mService.createAudioPatch(sourceAddress, usage, gainInMillibels);
        } catch (RemoteException e) {
            return (CarAudioPatchHandle) handleRemoteExceptionFromCarService(e, null);
        }
    }

    @SystemApi
    public void releaseAudioPatch(CarAudioPatchHandle patch) {
        Log.d(CarLibLog.TAG_CAR, "releaseAudioPatch  ");
        try {
            this.mService.releaseAudioPatch(patch);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    @SystemApi
    public int getVolumeGroupCount() {
        return getVolumeGroupCount(0);
    }

    @SystemApi
    public int getVolumeGroupCount(int zoneId) {
        try {
            return this.mService.getVolumeGroupCount(zoneId);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    @SystemApi
    public int getVolumeGroupIdForUsage(int usage) {
        return getVolumeGroupIdForUsage(0, usage);
    }

    @SystemApi
    public int getVolumeGroupIdForUsage(int zoneId, int usage) {
        try {
            return this.mService.getVolumeGroupIdForUsage(zoneId, usage);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    @SystemApi
    public int[] getUsagesForVolumeGroupId(int groupId) {
        return getUsagesForVolumeGroupId(0, groupId);
    }

    public int[] getAudioZoneIds() {
        try {
            return this.mService.getAudioZoneIds();
        } catch (RemoteException e) {
            return (int[]) handleRemoteExceptionFromCarService(e, new int[0]);
        }
    }

    public int getZoneIdForUid(int uid) {
        try {
            return this.mService.getZoneIdForUid(uid);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public boolean setZoneIdForUid(int zoneId, int uid) {
        try {
            return this.mService.setZoneIdForUid(zoneId, uid);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean clearZoneIdForUid(int uid) {
        try {
            return this.mService.clearZoneIdForUid(uid);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public int getZoneIdForDisplay(Display display) {
        DisplayAddress.Physical physicalAddress;
        DisplayAddress.Physical address = display.getAddress();
        if ((address instanceof DisplayAddress.Physical) && (physicalAddress = address) != null) {
            return getZoneIdForDisplayPortId(physicalAddress.getPort());
        }
        return 0;
    }

    public int getZoneIdForDisplayPortId(byte displayPortId) {
        try {
            return this.mService.getZoneIdForDisplayPortId(displayPortId);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    @SystemApi
    public int[] getUsagesForVolumeGroupId(int zoneId, int groupId) {
        try {
            return this.mService.getUsagesForVolumeGroupId(zoneId, groupId);
        } catch (RemoteException e) {
            return (int[]) handleRemoteExceptionFromCarService(e, new int[0]);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        ICarAudio iCarAudio = this.mService;
        if (iCarAudio != null) {
            try {
                iCarAudio.unregisterVolumeCallback(this.mCarVolumeCallbackImpl.asBinder());
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public static String getServiceName() {
        return Car.AUDIO_SERVICE;
    }

    public CarAudioManager(Car car, IBinder service) {
        super(car);
        this.mCarVolumeCallbackImpl = new ICarVolumeCallback.Stub() { // from class: android.car.media.CarAudioManager.1
            @Override // android.car.media.ICarVolumeCallback
            public void onGroupVolumeChanged(int zoneId, int groupId, int flags) {
                for (CarVolumeCallback callback : CarAudioManager.this.mCarVolumeCallbacks) {
                    callback.onGroupVolumeChanged(zoneId, groupId, flags);
                }
            }

            @Override // android.car.media.ICarVolumeCallback
            public void onMasterMuteChanged(int zoneId, int flags) {
                for (CarVolumeCallback callback : CarAudioManager.this.mCarVolumeCallbacks) {
                    callback.onMasterMuteChanged(zoneId, flags);
                }
            }
        };
        this.mService = ICarAudio.Stub.asInterface(service);
        this.mCarVolumeCallbacks = new ArrayList();
        try {
            this.mService.registerVolumeCallback(this.mCarVolumeCallbackImpl.asBinder());
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "registerVolumeCallback failed", e);
        }
    }

    public void registerCarVolumeCallback(CarVolumeCallback callback) {
        this.mCarVolumeCallbacks.add(callback);
    }

    public void unregisterCarVolumeCallback(CarVolumeCallback callback) {
        this.mCarVolumeCallbacks.remove(callback);
    }

    /* loaded from: classes.dex */
    public static abstract class CarVolumeCallback {
        public void onGroupVolumeChanged(int zoneId, int groupId, int flags) {
        }

        public void onMasterMuteChanged(int zoneId, int flags) {
        }
    }
}
