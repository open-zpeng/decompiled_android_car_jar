package android.car.hardware.llu;

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
import java.util.Objects;

@SystemApi
/* loaded from: classes.dex */
public final class CarLluManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int FINISH_LLU_SCRIPT_UPDATED = 2;
    public static final int ID_BCM_ANDROID_LL_ST = 557849821;
    public static final int ID_CDU_LLU_CTRL = 560999944;
    @Deprecated
    public static final int ID_LLU_ACCHARGING_CFG = 557854221;
    @Deprecated
    public static final int ID_LLU_BREATHMODESW = 557854215;
    public static final int ID_LLU_CHARGING_SW = 557854213;
    @Deprecated
    public static final int ID_LLU_DCCHARGING_CFG = 557854222;
    public static final int ID_LLU_FUNCTIONST = 557854217;
    public static final int ID_LLU_LOCK_SOC_DSP_SW = 557854225;
    @Deprecated
    public static final int ID_LLU_PHOTO_CFG = 557854223;
    public static final int ID_LLU_PHOTO_SW = 557854214;
    @Deprecated
    public static final int ID_LLU_POWER_REQ = 557854231;
    public static final int ID_LLU_SCRIPT_DATA = 557919764;
    public static final int ID_LLU_SCRIPT_PARAM = 557919763;
    public static final int ID_LLU_SCRIPT_ST = 557854229;
    public static final int ID_LLU_SELF_ACTIVE = 557854224;
    public static final int ID_LLU_SHOWOFF_CFG = 557854219;
    public static final int ID_LLU_SHOWOFF_SW = 557854211;
    public static final int ID_LLU_SLEEP_CFG = 557854220;
    public static final int ID_LLU_SLEEP_SW = 557854212;
    public static final int ID_LLU_SW = 557854209;
    public static final int ID_LLU_UNLOCK_SOC_DSP_SW = 557854226;
    public static final int ID_LLU_WAKEWAIT_CFG = 557854218;
    public static final int ID_LLU_WAKEWAIT_SW = 557854210;
    public static final int ID_MCU_FB_LLU_ACTIVE = 557847645;
    public static final int ID_MCU_LLU_ACCHARGINGCFG = 557847620;
    public static final int ID_MCU_LLU_ACTIVE = 557847644;
    public static final int ID_MCU_LLU_CHARGING_SW = 557847631;
    public static final int ID_MCU_LLU_DCCHARGINGCFG = 557847621;
    public static final int ID_MCU_LLU_FINDCAR = 557847618;
    public static final int ID_MCU_LLU_LOCK_SOCDSP = 557847619;
    public static final int ID_MCU_LLU_MODECTRL = 557913189;
    public static final int ID_MCU_LLU_PERSONANGLE = 557847623;
    public static final int ID_MCU_LLU_PERSONWALK_DIRECTION = 557847624;
    public static final int ID_MCU_LLU_PHOTO_SW = 557847632;
    public static final int ID_MCU_LLU_SHOWOFF_SW = 557847627;
    public static final int ID_MCU_LLU_SLEEP_SW = 557847630;
    public static final int ID_MCU_LLU_SPEEDLIMIT_CFG = 557847622;
    public static final int ID_MCU_LLU_SW = 557847625;
    public static final int ID_MCU_LLU_WAKEWAIT_SW = 557847626;
    public static final int ID_MCU_SELF_CONTROL_DATA = 560993382;
    public static final int INVALID_PERSON_ANGLE = 255;
    @Deprecated
    public static final int LLU_ACCHARGING_MODE = 6;
    public static final int LLU_ACTIVE_MODE_GENERAL = 1;
    public static final int LLU_ACTIVE_MODE_INACTIVE = 0;
    public static final int LLU_ACTIVE_MODE_SPECIAL_1 = 2;
    public static final int LLU_ACTIVE_MODE_SPECIAL_2 = 3;
    public static final int LLU_AC_CHARGING_MODE = 6;
    public static final int LLU_AWAKEN_AI_MODE = 3;
    public static final int LLU_AWAKEN_FIXED_LOADING_MODE = 12;
    public static final int LLU_AWAKEN_FIXED_MODE = 2;
    public static final int LLU_CDU_SELF_CTRL_MODE = 13;
    @Deprecated
    public static final int LLU_DCCHARGING_MODE = 7;
    public static final int LLU_DC_CHARGING_MODE = 7;
    public static final int LLU_FIND_CAR_MODE = 1;
    public static final int LLU_FRONT = 0;
    public static final int LLU_FULL_CHARGED_AWAKEN_MODE = 4;
    public static final int LLU_GESTURE_PHOTO_MODE = 9;
    public static final int LLU_LOCKED_LEAVING_CAR_MODE = 5;
    public static final int LLU_MODE1 = 1;
    public static final int LLU_MODE10 = 10;
    public static final int LLU_MODE11 = 11;
    public static final int LLU_MODE12 = 12;
    public static final int LLU_MODE13 = 13;
    public static final int LLU_MODE14 = 14;
    public static final int LLU_MODE15 = 15;
    public static final int LLU_MODE16 = 16;
    public static final int LLU_MODE17 = 17;
    public static final int LLU_MODE18 = 18;
    public static final int LLU_MODE19 = 19;
    public static final int LLU_MODE2 = 2;
    public static final int LLU_MODE20 = 20;
    public static final int LLU_MODE21 = 21;
    public static final int LLU_MODE22 = 22;
    public static final int LLU_MODE23 = 23;
    public static final int LLU_MODE24 = 24;
    public static final int LLU_MODE25 = 25;
    public static final int LLU_MODE26 = 26;
    public static final int LLU_MODE27 = 27;
    public static final int LLU_MODE28 = 28;
    public static final int LLU_MODE29 = 29;
    public static final int LLU_MODE3 = 3;
    public static final int LLU_MODE30 = 30;
    public static final int LLU_MODE4 = 4;
    public static final int LLU_MODE5 = 5;
    public static final int LLU_MODE6 = 6;
    public static final int LLU_MODE7 = 7;
    public static final int LLU_MODE8 = 8;
    public static final int LLU_MODE9 = 9;
    public static final int LLU_NO_OPERATION = 0;
    @Deprecated
    public static final int LLU_PHOTO_MODE = 8;
    public static final int LLU_POWER_REQ_NO_REQUEST = 0;
    public static final int LLU_POWER_REQ_POWERON = 1;
    public static final int LLU_REAR = 1;
    public static final int LLU_SCRIPT_DATA_FRONT_FINISH = 6;
    public static final int LLU_SCRIPT_DATA_NOT_OK = 21;
    public static final int LLU_SCRIPT_DATA_OK = 5;
    public static final int LLU_SCRIPT_DATA_REAR_FINISH = 7;
    public static final int LLU_SCRIPT_DATA_RECEIVE_TIMEOUT = 33;
    public static final int LLU_SCRIPT_PARAMETER_NOT_OK = 20;
    public static final int LLU_SCRIPT_PARAMETER_OK = 4;
    public static final int LLU_SCRIPT_UPDATED_FINISH_NOT_OK = 18;
    public static final int LLU_SCRIPT_UPDATED_FINISH_OK = 2;
    public static final int LLU_SCRIPT_UPDATED_RESET_NOT_OK = 19;
    public static final int LLU_SCRIPT_UPDATED_RESET_OK = 3;
    public static final int LLU_SCRIPT_UPDATED_START_NOT_OK = 17;
    public static final int LLU_SCRIPT_UPDATED_START_OK = 1;
    @Deprecated
    public static final int LLU_SEARCH_VEHICLE_MODE = 1;
    @Deprecated
    public static final int LLU_SHOWOFF_MODE = 5;
    public static final int LLU_SHOWOFF_ON_ROAD_AI_MODE = 11;
    public static final int LLU_SHOWOFF_ON_ROAD_MODE = 10;
    @Deprecated
    public static final int LLU_SLEEP_MODE = 3;
    public static final int LLU_SOC_PERCENTAGE_MODE = 8;
    public static final int LLU_SWITCH_ACTIVE = 1;
    public static final int LLU_SWITCH_AC_RESET_TO_DEFAULT = 2;
    public static final int LLU_SWITCH_DC_RESET_TO_DEFAULT = 3;
    public static final int LLU_SWITCH_INACTIVE = 0;
    public static final int LLU_SWITCH_RESET_TO_DEFAULT = 2;
    @Deprecated
    public static final int LLU_USER_DEFINED_MODE = 9;
    @Deprecated
    public static final int LLU_WAKEUP_WAITING_MODE = 2;
    public static final int LL_STATUS_APP_SELF_CTRL = 3;
    public static final int LL_STATUS_LIGHT_SABER = 4;
    public static final int LL_STATUS_NONE = 0;
    public static final int LL_STATUS_PREVIEW_AND_COLOR_EGG = 2;
    public static final int LL_STATUS_SAY_HI = 1;
    public static final int MCU_LLU_ACTIVE = 1;
    public static final int MCU_LLU_COMPLETED = 2;
    public static final int PERSON_WALK_DIRECTION_LEFT_TO_RIGHT = 1;
    public static final int PERSON_WALK_DIRECTION_NOT_MOVE = 0;
    public static final int PERSON_WALK_DIRECTION_RIGHT_TO_LEFT = 2;
    public static final int RESET_LLU_SCRIPT_UPDATED = 3;
    public static final int START_LLU_SCRIPT_UPDATED = 1;
    private static final String TAG = "CarLluManager";
    private final ArraySet<Integer> mLluPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarLluEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarLluManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mLluPropertyIds = new ArraySet<>(Arrays.asList(557854209, 557854210, 557854211, 557854212, 557854213, 557854214, 560999944, 557854217, 557854218, 557854219, 557854220, 557854224, 557854225, 557854226, 557854229, 557847618, 557847619, 557847620, 557847621, 557847622, 557847625, 557847626, 557847627, 557847630, 557847631, 557847632, 557847644, 557847645, 557913189, 560993382, 557849821, 557854231));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mLluPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_LLU_SERVICE;
    }

    public void setLluEnableStatus(int enable) throws Exception {
        this.mService.setLluEnableStatus(enable);
    }

    public int getLluEnableStatus() throws Exception {
        return this.mService.getLluEnableStatus();
    }

    public void setLluWakeWaitSwitch(int enable) throws Exception {
        this.mService.setLluWakeWaitSwitch(enable);
    }

    public int getLluWakeWaitSwitch() throws Exception {
        return this.mService.getLluWakeWaitSwitch();
    }

    public void setLluShowOffSwitch(int enable) throws Exception {
        this.mService.setLluShowOffSwitch(enable);
    }

    public int getLluShowOffSwitch() throws Exception {
        return this.mService.getLluShowOffSwitch();
    }

    public void setLluSleepSwitch(int enable) throws Exception {
        this.mService.setLluSleepSwitch(enable);
    }

    public int getLluSleepSwitch() throws Exception {
        return this.mService.getLluSleepSwitch();
    }

    public void setLluChargingSwitch(int enable) throws Exception {
        this.mService.setLluChargingSwitch(enable);
    }

    public int getLluChargingSwitch() throws Exception {
        return this.mService.getLluChargingSwitch();
    }

    public void setLluPhotoSwitch(int enable) throws Exception {
        this.mService.setLluPhotoSwitch(enable);
    }

    public int getLluPhotoSwitch() throws Exception {
        return this.mService.getLluPhotoSwitch();
    }

    @Deprecated
    public void setLluPrivateCtrl(boolean start, int fType, int fTick, int rType, int rTick) throws Exception {
        this.mService.setLluPrivateCtrl(start, fType, fTick, rType, rTick, 255, 0);
    }

    public void setLluPrivateCtrl(boolean start, int fType, int fTick, int rType, int rTick, int personAngle, int personWalkDirection) throws Exception {
        this.mService.setLluPrivateCtrl(start, fType, fTick, rType, rTick, personAngle, personWalkDirection);
    }

    @Deprecated
    public void setLluBreathMode(int type) throws Exception {
        this.mService.setLluBreathMode(type);
    }

    @Deprecated
    public int getLluBreathMode() throws Exception {
        return this.mService.getLluBreathMode();
    }

    public int getLluCurrentFunction() throws Exception {
        return this.mService.getLluCurrentFunction();
    }

    @Deprecated
    public void setLluCurrentFunction(int function) throws Exception {
        this.mService.setLluCurrentFunction(function);
    }

    public void setLluWakeWaitMode(int type) throws Exception {
        this.mService.setLluWakeWaitMode(type);
    }

    public int getLluWakeWaitMode() throws Exception {
        return this.mService.getLluWakeWaitMode();
    }

    public void setLluShowOffMode(int type) throws Exception {
        this.mService.setLluShowOffMode(type);
    }

    public int getLluShowOffMode() throws Exception {
        return this.mService.getLluShowOffMode();
    }

    public void setLluSleepMode(int type) throws Exception {
        this.mService.setLluSleepMode(type);
    }

    public int getLluSleepMode() throws Exception {
        return this.mService.getLluSleepMode();
    }

    @Deprecated
    public void setLluAcChargeMode(int type) throws Exception {
        this.mService.setLluAcChargeMode(type);
    }

    @Deprecated
    public int getLluAcChargeMode() throws Exception {
        return this.mService.getLluAcChargeMode();
    }

    @Deprecated
    public void setLluDcChargeMode(int type) throws Exception {
        this.mService.setLluDcChargeMode(type);
    }

    @Deprecated
    public int getLluDcChargeMode() throws Exception {
        return this.mService.getLluDcChargeMode();
    }

    @Deprecated
    public void setLluPhotoMode(int type) throws Exception {
        this.mService.setLluPhotoMode(type);
    }

    @Deprecated
    public int getLluPhotoMode() throws Exception {
        return this.mService.getLluPhotoMode();
    }

    public void setLluSelfActive(int active) throws Exception {
        this.mService.setLluSelfActive(active);
    }

    public void setLluLockSocDspSwitch(int active) throws Exception {
        this.mService.setLluLockSocDspSwitch(active);
    }

    public int getLluLockSocDspSwitch() throws Exception {
        return this.mService.getLluLockSocDspSwitch();
    }

    public void setLluUnLockSocDspSwitch(int active) throws Exception {
        this.mService.setLluUnLockSocDspSwitch(active);
    }

    public int getLluUnLockSocDspSwitch() throws Exception {
        return this.mService.getLluUnLockSocDspSwitch();
    }

    public void setLluScriptStRequest(int request) throws Exception {
        this.mService.setLluScriptStRequest(request);
    }

    public int getLluScriptStResponse() throws Exception {
        return this.mService.getLluScriptStResponse();
    }

    public void setLluScriptParameter(LluScriptParameter parameter) throws Exception {
        Objects.requireNonNull(parameter);
        this.mService.setLluScriptParameter(parameter.getData());
    }

    public void sendLluScriptData(int index, int pos, int length, int[] data) throws Exception {
        Objects.requireNonNull(data);
        this.mService.setLluScriptData(index, pos, length, data);
    }

    public void setLluFindCarSwitch(int enable) throws Exception {
        this.mService.setLluFindCarSwitch(enable);
    }

    public int getLluFindCarSwitchState() throws Exception {
        return this.mService.getLluFindCarSwitchState();
    }

    public void setLluLockUnlockSocDspSwitch(int active) throws Exception {
        this.mService.setLluLockUnlockSocDspSwitch(active);
    }

    public int getLluLockUnlockSocDspSwitchState() throws Exception {
        return this.mService.getLluLockUnlockSocDspSwitchState();
    }

    public int getLluDcChargingCfg() throws Exception {
        return this.mService.getLluDcChargingCfg();
    }

    public int getLluAcChargingCfg() throws Exception {
        return this.mService.getLluAcChargingCfg();
    }

    public void setLluSpeedLimitCfg(int speed) throws Exception {
        this.mService.setLluSpeedLimitCfg(speed);
    }

    public int getLluSpeedLimitCfg() throws Exception {
        return this.mService.getLluSpeedLimitCfg();
    }

    public void setPersonAngle(int angle) throws Exception {
        this.mService.setLluPersonAngle(angle);
    }

    public void setPersonWalkDirection(int direction) throws Exception {
        this.mService.setLluPersonWalkDirection(direction);
    }

    public void setMcuLluEnableStatus(int enable) throws Exception {
        this.mService.setMcuLluEnableStatus(enable);
    }

    public int getMcuLluEnableStatus() throws Exception {
        return this.mService.getMcuLluEnableStatus();
    }

    public void setMcuLluWakeWaitSwitch(int enable) throws Exception {
        this.mService.setMcuLluWakeWaitSwitch(enable);
    }

    public int getMcuLluWakeWaitSwitch() throws Exception {
        return this.mService.getMcuLluWakeWaitSwitch();
    }

    public void setMcuLluShowOffSwitch(int enable) throws Exception {
        this.mService.setMcuLluShowOffSwitch(enable);
    }

    public int getMcuLluShowOffSwitch() throws Exception {
        return this.mService.getMcuLluShowOffSwitch();
    }

    public void setMcuLluSleepSwitch(int enable) throws Exception {
        this.mService.setMcuLluSleepSwitch(enable);
    }

    public int getMcuLluSleepSwitch() throws Exception {
        return this.mService.getMcuLluSleepSwitch();
    }

    public void setMcuLluChargingSwitch(int enable) throws Exception {
        this.mService.setMcuLluChargingSwitch(enable);
    }

    public int getMcuLluChargingSwitch() throws Exception {
        return this.mService.getMcuLluChargingSwitch();
    }

    public void setMcuLluPhotoSwitch(int enable) throws Exception {
        this.mService.setMcuLluPhotoSwitch(enable);
    }

    public int getMcuLluPhotoSwitch() throws Exception {
        return this.mService.getMcuLluPhotoSwitch();
    }

    public void activateAndroidLluControl() throws Exception {
        this.mService.activateAndroidLluControl();
    }

    public void deactivateAndroidLluControl() throws Exception {
        this.mService.deactivateAndroidLluControl();
    }

    @Deprecated
    public int getMcuActivatedLluSignal() throws Exception {
        return this.mService.getMcuLluWorkStatus();
    }

    public int getMcuLluWorkStatus() throws Exception {
        return this.mService.getMcuLluWorkStatus();
    }

    public void setMcuLluModeCtrl(int mhlActiveMode, int lhlActiveMode, int rhlActiveMode, int mrlActiveMode, int lrlActiveMode, int rrlActiveMode) throws Exception {
        this.mService.setMcuLluModeCtrl(mhlActiveMode, lhlActiveMode, rhlActiveMode, mrlActiveMode, lrlActiveMode, rrlActiveMode);
    }

    public void setMcuLLuSelfControlData(byte[] data) throws Exception {
        this.mService.setMcuLLuSelfControlData(data);
    }

    public void setAndroidLlSt(int st) throws Exception {
        this.mService.setLluAndroidLlSt(st);
    }

    @Deprecated
    public void setMcuLluPowerRequestSwitchStatus(int enable) throws Exception {
        this.mService.setLluPowerRequestSwitchStatus(enable);
    }

    public int getMcuLluPowerRequestSwitchStatus() throws Exception {
        return this.mService.getMcuLluPowerRequestSwitchStatus();
    }
}
