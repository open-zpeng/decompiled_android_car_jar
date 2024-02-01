package android.car.hardware.esp;

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
public final class CarEspManager extends CarEcuManager {
    public static final int APB_SYSTEM_DISPLAY_MESSAGE_INSUFFICIENT_PARK_REPLACE_PARK = 2;
    public static final int APB_SYSTEM_DISPLAY_MESSAGE_NO_RELEASE_TIP_FOR_P_FILE = 3;
    public static final int APB_SYSTEM_DISPLAY_MESSAGE_NO_RELEASE_TIP_FOR_SHIFT = 1;
    public static final int APB_SYSTEM_DISPLAY_MESSAGE_OFF = 0;
    public static final int APB_SYSTEM_STATUS_CLOSED = 2;
    public static final int APB_SYSTEM_STATUS_DISENGAGED = 5;
    public static final int APB_SYSTEM_STATUS_LOCKING = 4;
    public static final int APB_SYSTEM_STATUS_RELEASED = 1;
    public static final int APB_SYSTEM_STATUS_RELEASING = 3;
    public static final int APB_SYSTEM_STATUS_UNDEFINED = 0;
    public static final int AVH_BUTTON_NOT_TOUCH = 0;
    public static final int AVH_BUTTON_TOUCH = 1;
    public static final int AVH_FAULT = 1;
    public static final int AVH_NOT_FAULT = 0;
    public static final int AVH_STATUS_ACTIVE = 1;
    public static final int AVH_STATUS_INACTIVE = 0;
    public static final int AVH_STATUS_STANDBY = 2;
    private static final boolean DBG = false;
    public static final int EPB_DRIVER_OFF_WARNING_OFF = 0;
    public static final int EPB_DRIVER_OFF_WARNING_RELEASE_PARKING_BRAKE_BY_EPB_BUTTON = 1;
    public static final int EPB_SYSTEM_REQ_APPLY = 2;
    public static final int EPB_SYSTEM_REQ_RELEASED = 1;
    public static final int EPB_WARNING_LAMP_OFF = 0;
    public static final int EPB_WARNING_LAMP_ON = 1;
    public static final int EPS_WARNING_LAMP_OFF = 0;
    public static final int EPS_WARNING_LAMP_ON = 1;
    public static final int ESC_ESP_NORMAL = 1;
    public static final int ESC_ESP_OFF = 3;
    public static final int ESC_ESP_ON = 4;
    public static final int ESC_ESP_SPORT = 2;
    public static final int ESC_IBS_BRAKEMODE_NORMAL = 1;
    public static final int ESC_IBS_BRAKEMODE_SPORT = 2;
    public static final int ESP_BPF_AUTO = 4;
    public static final int ESP_BPF_ECONOMIC = 2;
    public static final int ESP_BPF_NONE = 0;
    public static final int ESP_BPF_NORMAL = 1;
    public static final int ESP_BPF_SPORT = 3;
    public static final int ESP_FAULT = 1;
    public static final int ESP_NOT_FAULT = 0;
    public static final int ESP_SPEED_INVALID = 2;
    public static final int ESP_SPEED_VALID = 1;
    public static final int ESP_STATUS_ACTIVE = 1;
    public static final int ESP_STATUS_INACTIVE = 0;
    public static final int ESP_STATUS_OFF = 0;
    public static final int ESP_STATUS_ON = 1;
    @Deprecated
    public static final int ESP_STATUS_STANDBY = 2;
    @Deprecated
    public static final int ESP_TSM_SWITCH_1 = 1;
    @Deprecated
    public static final int ESP_TSM_SWITCH_2 = 2;
    @Deprecated
    public static final int ESP_TSM_SWITCH_OFF = 0;
    public static final int ESP_WARNING_REQUEST_NO_WARNING = 0;
    public static final int ESP_WARNING_REQUEST_WARNING = 1;
    public static final int HAS_HBC_REQUEST = 1;
    public static final int HDC_FAULT = 1;
    public static final int HDC_NOT_FAULT = 0;
    public static final int HDC_STATUS_ACTIVE = 1;
    public static final int HDC_STATUS_INACTIVE = 0;
    public static final int HDC_STATUS_STANDBY = 2;
    public static final int ID_ESC_IBS_BRAKE_MODE = 557851652;
    public static final int ID_ESP_ABS_ACT_ST = 557851679;
    public static final int ID_ESP_APBSYS_ST = 557851667;
    public static final int ID_ESP_APB_SYSDISP_MSGREQ = 557851666;
    public static final int ID_ESP_AVH = 557851651;
    public static final int ID_ESP_AVH_FAULT = 557851659;
    public static final int ID_ESP_BPF = 557851691;
    public static final int ID_ESP_CDP_INTERVENTION = 557851671;
    public static final int ID_ESP_CST = 557851690;
    public static final int ID_ESP_DTC_FAULT = 557851673;
    public static final int ID_ESP_EPB_DRIVER_OFF_WARNING = 557851692;
    public static final int ID_ESP_ESP = 557851650;
    public static final int ID_ESP_ESP_FAULT = 557851657;
    public static final int ID_ESP_HDC = 557851656;
    public static final int ID_ESP_HDC_FAULT = 557851658;
    public static final int ID_ESP_IPUF_ACT_ROT_SPD = 557851680;
    public static final int ID_ESP_IPUF_ACT_TORQ = 559948836;
    public static final int ID_ESP_IPUF_MOTOR_ACT_TEMP = 557851686;
    public static final int ID_ESP_IPUR_ACT_ROT_SPD = 557851681;
    public static final int ID_ESP_IPUR_ACT_TORQ = 559948837;
    public static final int ID_ESP_IPUR_MOTOR_ACT_TEMP = 557851687;
    public static final int ID_ESP_MASTER_CYLINDER_PRESS = 559948841;
    public static final int ID_ESP_OFFROAD = 557851670;
    public static final int ID_ESP_SYS_WARNIND_REQ = 557851660;
    public static final int ID_ESP_TCS_ACT_ST = 557851682;
    public static final int ID_ESP_TSM_FAULT_ST = 557851674;
    public static final int ID_ESP_TSM_SW = 557851672;
    public static final int ID_ESP_VDC_ACT_ST = 557851683;
    public static final int ID_ESP_VEHSPD = 559948801;
    public static final int ID_ESP_WARN_LAMP = 557851661;
    public static final int ID_ESP_WHEEL_SPD_ALL = 560014366;
    public static final int ID_IBT_BRAKE_TRAVEL = 559948840;
    public static final int ID_IBT_FAILURE_LAMP = 557851665;
    public static final int ID_IBT_HBC_REQ = 557851668;
    public static final int INTERVENTION_STATUS = 1;
    public static final int NO_HBC_REQUEST = 0;
    public static final int NO_INTERVENTION_STATUS = 0;
    private static final String TAG = "CarEspManager";
    private final ArraySet<Integer> mEspPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarEspEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarEspManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mEspPropertyIds = new ArraySet<>(Arrays.asList(557851656, 557851650, 557851651, 557851652, 559948801, 557851657, 557851658, 557851659, 557851660, 557851661, 557851666, 557851667, 557851668, 557851670, 557851671, 557851672, 557851674, 557851673, 557851665, 560014366, 557851679, 557851680, 557851681, 557851682, 557851683, 559948836, 557851686, 559948837, 557851687, 559948840, 559948841, 557851690, 557851691, 557851692));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public static String getServiceName() {
        return Car.XP_ESP_SERVICE;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mEspPropertyIds;
    }

    public int getHdc() throws Exception {
        return this.mService.isEspHdcEnabled();
    }

    public void setHdc(int status) throws Exception {
        this.mService.setEspHdcEnabled(status);
    }

    public int getEsp() throws Exception {
        return this.mService.getEspWorkMode();
    }

    public void setEsp(int type) throws Exception {
        this.mService.setEspWorkMode(type);
    }

    public int getAvh() throws Exception {
        return this.mService.isEspAvhEnabled();
    }

    public void setAvh(int status) throws Exception {
        this.mService.setEspAvhEnabled(status);
    }

    public int getIbsBrakeMode() throws Exception {
        return this.mService.getEspIbsBrakeMode();
    }

    public void setIbsBrakeMode(int type) throws Exception {
        this.mService.setEspIbsBrakeMode(type);
    }

    public float getCarSpeed() throws Exception {
        return this.mService.getEspCarSpeed();
    }

    public int getEspFault() throws Exception {
        return this.mService.hasEspFault();
    }

    public int getHdcFault() throws Exception {
        return this.mService.hasEspHdcFault();
    }

    public int getAvhFault() throws Exception {
        return this.mService.hasEspAvhFault();
    }

    public int getEpbWarningLampStatus() throws Exception {
        return this.mService.getEspEpbWarningLampStatus();
    }

    public int getEpsWarninglampStatus() throws Exception {
        return this.mService.getEspEpsWarninglampStatus();
    }

    public int getApbSystemDisplayMessage() throws Exception {
        return this.mService.getEspApbSystemDisplayMessage();
    }

    public void setEpbSystemSwitch(int status) throws Exception {
        this.mService.setEspEpbSystemSwitch(status);
    }

    public int getApbSystemStatus() throws Exception {
        return this.mService.getEspApbSystemStatus();
    }

    public int getHbcRequestStatus() throws Exception {
        return this.mService.getEspHbcRequestStatus();
    }

    public void setOffRoadSwitch(int onOff) throws Exception {
        this.mService.setEspOffRoadSwitch(onOff);
    }

    public int getOffRoadSwitchStatus() throws Exception {
        return this.mService.getEspOffRoadSwitchStatus();
    }

    public int getEspInterventionStatus() throws Exception {
        return this.mService.getEspInterventionStatus();
    }

    public void setTsmSwitchStatus(int status) throws Exception {
        this.mService.setEspTsmSwitchStatus(status);
    }

    public int getTsmSwitchStatus() throws Exception {
        return this.mService.getEspTsmSwitchStatus();
    }

    public int getTsmFaultStatus() throws Exception {
        return this.mService.getEspTsmFaultStatus();
    }

    public int getDtcFaultStatus() throws Exception {
        return this.mService.getEspDtcFaultStatus();
    }

    public int getIbtFailureLampRequest() throws Exception {
        return this.mService.getEspIbtFailureLampRequest();
    }

    public float[] getAllWheelSpeed() throws Exception {
        return this.mService.getEspAllWheelSpeed();
    }

    public int getAbsWorkStatus() throws Exception {
        return this.mService.getEspAbsWorkStatus();
    }

    public int getTcsWorkStatus() throws Exception {
        return this.mService.getEspTcsWorkStatus();
    }

    public int getVdcWorkStatus() throws Exception {
        return this.mService.getEspVdcWorkStatus();
    }

    public int getIpuFrontActualRotateSpeed() throws Exception {
        return this.mService.getEspIpuFrontActualRotateSpeed();
    }

    public int getIpuRearActualRotateSpeed() throws Exception {
        return this.mService.getEspIpuRearActualRotateSpeed();
    }

    public float getIpuFrontActualTorque() throws Exception {
        return this.mService.getEspIpuFrontActualTorque();
    }

    public int getIpuFrontMotorActualTemperature() throws Exception {
        return this.mService.getEspIpuFrontMotorActualTemperature();
    }

    public float getIpuRearActualTorque() throws Exception {
        return this.mService.getEspIpuRearActualTorque();
    }

    public int getIpuRearMotorActualTemperature() throws Exception {
        return this.mService.getEspIpuRearMotorActualTemperature();
    }

    public float getIbtBrakeTravelDistance() throws Exception {
        return this.mService.getEspIbtBrakeTravelDistance();
    }

    public float getMasterCylinderPressure() throws Exception {
        return this.mService.getEspMasterCylinderPressure();
    }

    public void setCstStatus(int status) throws Exception {
        this.mService.setEspCstStatus(status);
    }

    public void setBpfStatus(int mode) throws Exception {
        this.mService.setEspBpfStatus(mode);
    }

    public int getEpbDriverOffWarningMsg() throws Exception {
        return this.mService.getEspEpbDriverOffWarningMsg();
    }
}
