package android.car.hardware.avas;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.hardware.CarEcuManager;
import android.car.hardware.XpVehicle.IXpVehicle;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class CarAvasManager extends CarEcuManager {
    public static final int AVAS_SOUND_MODE_1 = 1;
    public static final int AVAS_SOUND_MODE_2 = 2;
    public static final int AVAS_SOUND_MODE_3 = 3;
    public static final int AVAS_SOUND_MODE_4 = 4;
    public static final int AVAS_SOUND_MODE_5 = 5;
    public static final int AVAS_SOUND_TYPE_1 = 1;
    public static final int AVAS_SOUND_TYPE_2 = 2;
    public static final int AVAS_SOUND_TYPE_3 = 3;
    public static final int AVAS_SOUND_TYPE_4 = 4;
    public static final int AVAS_SWITCH_CLOSE = 0;
    public static final int AVAS_SWITCH_OPEN = 1;
    private static final boolean DBG = false;
    public static final int ID_AVAS_ACCHRGIN_SETCMD = 557855236;
    public static final int ID_AVAS_ACIVE_ST = 557855248;
    public static final int ID_AVAS_CARSLEEP_SETCMD = 557855235;
    public static final int ID_AVAS_CHARGE_SOUND_SW = 557855253;
    public static final int ID_AVAS_CHARGE_SOUND_VOL = 557855254;
    public static final int ID_AVAS_DCCHRGIN_SETCMD = 557855237;
    public static final int ID_AVAS_DISCONNECTCHRG_SETCMD = 557855238;
    public static final int ID_AVAS_EXTERNALSOUNDFILE_CMD = 557855241;
    public static final int ID_AVAS_EXTERNALSOUNDMODE_CMD = 557855245;
    public static final int ID_AVAS_EXTVOLADJUST_CMD = 557855243;
    public static final int ID_AVAS_FAULT = 557855247;
    public static final int ID_AVAS_FRIENDLY_SAYHICFG = 557855242;
    public static final int ID_AVAS_FULLCHRGWAKEUPSETCMD = 557855234;
    public static final int ID_AVAS_LOCKSOUND_CFG = 557855251;
    public static final int ID_AVAS_LOCK_UNLOCK_SOUND_SW = 557855249;
    public static final int ID_AVAS_LOWSPDVOLADJUST_CFG = 557855244;
    public static final int ID_AVAS_LOWSPEEDSOUND_CFG = 557855240;
    public static final int ID_AVAS_PHOTOSOUND_SW = 557855246;
    public static final int ID_AVAS_SOC_SOUND_SW = 557855255;
    public static final int ID_AVAS_SOC_SOUND_VOL = 557855256;
    public static final int ID_AVAS_SOC_SOUND_VOL_CMD = 557855257;
    public static final int ID_AVAS_SWSTCMD = 557855239;
    public static final int ID_AVAS_UNLOCKSOUND_CFG = 557855250;
    public static final int ID_AVAS_UNLOCKSOUND_VOL = 557855252;
    public static final int ID_AVAS_UNLOCK_SOUND_SW = 557850074;
    public static final int ID_AVAS_WAKEUP_WAITSETCMD = 557855233;
    public static final int MCU_AVAS_STATUS_ACIVE = 1;
    public static final int MCU_AVAS_STATUS_INACIVE = 0;
    private static final String TAG = "CarAvasManager";
    private final ArraySet<Integer> mAvasPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarAvasEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarAvasManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mAvasPropertyIds = new ArraySet<>(Arrays.asList(557855233, 557855234, 557855235, 557855236, 557855237, 557855238, 557855239, 557855240, 557855241, 557855242, 557855243, 557855244, 557855245, 557855246, 557855247, 557855248, 557855249, 557855250, 557855251, 557855253, 557855255, 557855257, 557850074));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mAvasPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_AVAS_SERVICE;
    }

    public void setAvasWakeWaitSwitch(int enable) throws Exception {
        this.mService.setAvasWaitForWakeUpSoundSwitch(enable);
    }

    public int getAvasWakeWaitSwitch() throws Exception {
        return this.mService.getAvasWaitForWakeUpSoundState();
    }

    public int getAvasWakeWaitFullChargeSwitch() throws Exception {
        return this.mService.getAvasFullChargeWaitForWakeUpSoundState();
    }

    public void setAvasWakeWaitFullChargeSwitch(int enable) throws Exception {
        this.mService.setAvasFullChargeWaitForWakeUpSoundSwitch(enable);
    }

    public int getAvasSleepSwitch() throws Exception {
        return this.mService.getAvasSleepSoundState();
    }

    public void setAvasSleepSwitch(int enable) throws Exception {
        this.mService.setAvasSleepSoundSwitch(enable);
    }

    public int getAvasAcChargingSwitch() throws Exception {
        return this.mService.getAvasAcChargingSoundState();
    }

    public void setAvasAcChargingSwitch(int enable) throws Exception {
        this.mService.setAvasAcChargingSoundSwitch(enable);
    }

    public int getAvasDcChargingSwitch() throws Exception {
        return this.mService.getAvasDcChargingSoundState();
    }

    public void setAvasDcChargingSwitch(int enable) throws Exception {
        this.mService.setAvasDcChargingSoundSwitch(enable);
    }

    public int getAvasDisconnectChargingSwitch() throws Exception {
        return this.mService.getAvasDisconnectChargingSoundState();
    }

    public void setAvasDisconnectChargingSwitch(int enable) throws Exception {
        this.mService.setAvasDisconnectChargingSoundSwitch(enable);
    }

    public int getAvasLowSpeedSoundSwitch() throws Exception {
        return this.mService.getAvasLowSpeedSoundSwitch();
    }

    public void setAvasLowSpeedSoundSwitch(int enable) throws Exception {
        this.mService.setAvasLowSpeedSoundSwitch(enable);
    }

    public int getAvasLowSpeedSound() throws Exception {
        return this.mService.getAvasLowSpeedSoundEffect();
    }

    public void setAvasLowSpeedSound(int sound) throws Exception {
        this.mService.setAvasLowSpeedSoundEffect(sound);
    }

    public void setAvasExternalSound(int type) throws Exception {
        this.mService.setAvasExternalSoundCmd(type);
    }

    public int getAvasFriendlySayHiSound() throws Exception {
        return this.mService.getAvasFriendlySayHiSound();
    }

    public void setAvasFriendlySayHiSound(int sound) throws Exception {
        this.mService.setAvasFriendlySayHiSound(sound);
    }

    public int getAvasExternalVolume() throws Exception {
        return this.mService.getAvasExternalVolume();
    }

    public void setAvasExternalVolume(int vol) throws Exception {
        this.mService.setAvasExternalVolume(vol);
    }

    public int getLowSpeedVolume() throws Exception {
        return this.mService.getAvasLowSpeedVolume();
    }

    public void setLowSpeedVolume(int vol) throws Exception {
        this.mService.setAvasLowSpeedVolume(vol);
    }

    public void setExternalSoundModeCmd(int mode) throws Exception {
        this.mService.setAvasExternalSoundModeCmd(mode);
    }

    public void setAvasPhotoSoundSwitch(int enable) throws Exception {
        this.mService.setAvasPhotoSoundSwitch(enable);
    }

    public int getAvasPhotoSoundSwitch() throws Exception {
        return this.mService.getAvasPhotoSoundSwitch();
    }

    public int getAvasFaultStatus() throws Exception {
        return this.mService.getAvasFaultStatus();
    }

    public void setLockUnlockSoundSwitchStatus(int enable) throws Exception {
        this.mService.setAvasLockUnlockSoundSwitchStatus(enable);
    }

    public int getLockUnlockSoundSwitchStatus() throws Exception {
        return this.mService.getAvasLockUnlockSoundSwitchStatus();
    }

    public void setUnlockSoundSwitchStatus(int enable) throws Exception {
        this.mService.setAvasUnlockSoundSwitchStatus(enable);
    }

    public int getUnlockSoundSwitchStatus() throws Exception {
        return this.mService.getAvasUnlockSoundSwitchStatus();
    }

    public void setChargeSoundSwitchStatus(int enable) throws Exception {
        this.mService.setAvasChargeSoundSwitchStatus(enable);
    }

    public int getChargeSoundSwitchStatus() throws Exception {
        return this.mService.getAvasChargeSoundSwitchStatus();
    }

    public void setSocSoundSwitchStatus(int enable) throws Exception {
        this.mService.setAvasSocSoundSwitchStatus(enable);
    }

    public int getSocSoundSwitchStatus() throws Exception {
        return this.mService.getAvasSocSoundSwitchStatus();
    }

    public void setUnlockSoundEffect(int sound) throws Exception {
        this.mService.setAvasUnlockSoundEffect(sound);
    }

    public int getUnlockSoundEffect() throws Exception {
        return this.mService.getAvasUnlockSoundEffect();
    }

    public void setLockSoundEffect(int sound) throws Exception {
        this.mService.setAvasLockSoundEffect(sound);
    }

    public int getLockSoundEffect() throws Exception {
        return this.mService.getAvasLockSoundEffect();
    }

    public int getMcuAvasRunnningStatus() throws Exception {
        return this.mService.getAvasMcuAvasRunnningStatus();
    }

    public int getUnlockSoundSpeedVolume() throws Exception {
        return this.mService.getAvasUnlockSoundSpeedVolume();
    }

    public int getChargeSoundSpeedVolume() throws Exception {
        return this.mService.getAvasChargeSoundSpeedVolume();
    }

    public int getSocSoundSpeedVolume() throws Exception {
        return this.mService.getAvasSocSoundSpeedVolume();
    }

    public void setSocSoundVolumeToMcu(int vol) throws Exception {
        this.mService.setAvasSocSoundVolumeToMcu(vol);
    }
}
