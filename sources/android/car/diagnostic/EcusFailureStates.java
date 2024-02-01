package android.car.diagnostic;

import android.annotation.SystemApi;
import android.car.ValueUnavailableException;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
@SystemApi
/* loaded from: classes.dex */
public class EcusFailureStates implements Parcelable {
    public static final int AEB_STATE_BLOCK = 4;
    public static final int AEB_STATE_NO_FAULT = 5;
    public static final int AEB_STATE_OFF = 0;
    public static final int AEB_STATE_ON = 1;
    public static final int AEB_STATE_PERMANENT_FAILURE = 3;
    public static final int AEB_STATE_TEMPORARY_FAILURE = 2;
    public static final int ALC_CTRL_REMIND_ACTIVE_BY_ALC_DIR_LEFT = 1;
    public static final int ALC_CTRL_REMIND_ACTIVE_BY_ALC_DIR_RIGHT = 2;
    public static final int ALC_CTRL_REMIND_ALC_CANCEL = 6;
    public static final int ALC_CTRL_REMIND_ALC_FAILURE = 10;
    public static final int ALC_CTRL_REMIND_ALC_FINISH = 7;
    public static final int ALC_CTRL_REMIND_ALC_FRONT_CAM_BLOCKED = 17;
    public static final int ALC_CTRL_REMIND_ALC_FRONT_CAM_FAILURE = 19;
    public static final int ALC_CTRL_REMIND_ALC_REAR_CAM_BLOCKED = 18;
    public static final int ALC_CTRL_REMIND_ALC_REAR_CAM_FAILURE = 20;
    public static final int ALC_CTRL_REMIND_ALC_WAIT_DIR_LEFT = 3;
    public static final int ALC_CTRL_REMIND_ALC_WAIT_DIR_RIGHT = 4;
    public static final int ALC_CTRL_REMIND_ALC_WAIT_TIMEOUT_DIR_LEFT = 8;
    public static final int ALC_CTRL_REMIND_ALC_WAIT_TIMEOUT_DIR_RIGHT = 9;
    public static final int ALC_CTRL_REMIND_INACTIVE_FOR_SRR_R_BLINDNESS = 11;
    public static final int ALC_CTRL_REMIND_NO_DISPLAY = 0;
    public static final int ALC_CTRL_REMIND_UNABLE_TO_ACTIVATE_ALC = 5;
    public static final int ALS_FAILURE_STATE_ACCELERATION_SENSOR_ERROR = 2;
    public static final int ALS_FAILURE_STATE_COMMUNICATION_ERROR = 1;
    public static final int ALS_FAILURE_STATE_INITIAL_DATA_ERROR = 4;
    public static final int ALS_FAILURE_STATE_NOT_INITIALIZED = 3;
    public static final int ALS_FAILURE_STATE_NO_ERROR = 0;
    public static final int ALS_FAILURE_STATE_ROAD_ANGLE_ERROR = 5;
    public static final int APA_FAILURE = 2;
    public static final int APA_NORMAL = 1;
    public static final int APA_NOT_DEFINED = 0;
    public static final int BATTERY_DEAD = 1;
    public static final int BATTERY_NORMAL = 0;
    public static final int BATTERY_PTC_FAILURE_INDEX_ELEMENT = 1;
    public static final int BATTERY_PTC_FAILURE_INDEX_INTERNAL = 0;
    public static final int BATTERY_VOLTAGE_LOW = 1;
    public static final int BATTERY_VOLTAGE_NORMAL = 0;
    public static final int BSD_STATE_FAIL = 2;
    public static final int BSD_STATE_NO_FAULT = 3;
    public static final int BSD_STATE_OFF = 0;
    public static final int BSD_STATE_ON = 1;
    public static final int CCS_ERROR = 2;
    public static final int CCS_PREWARM = 3;
    public static final int CCS_STANDBY = 0;
    public static final int CCS_WORK = 1;
    public static final int CHARGE_PORT_HOT = 1;
    public static final int CHARGE_PORT_NORMAL = 0;
    public static final int COMPRESSOR_FAILURE_INDEX_CAUSE = 1;
    public static final int COMPRESSOR_FAILURE_INDEX_STT = 0;
    public static final int DOW_STATE_FAIL = 2;
    public static final int DOW_STATE_OFF = 0;
    public static final int DOW_STATE_ON = 1;
    public static final int ECU_FAILURE = 1;
    public static final int ECU_NORMAL = 0;
    public static final int ELECTRIC_MOTOR_SYSTEM_HOT = 1;
    public static final int ELECTRIC_MOTOR_SYSTEM_NORMAL = 0;
    public static final int GEAR_WARNING_BATTERY_CHARGING = 1;
    public static final int GEAR_WARNING_CLOSE_DOOR_AND_FASTEN_SEATBELT = 7;
    public static final int GEAR_WARNING_DRIVING = 2;
    public static final int GEAR_WARNING_NO_BRAKE = 4;
    public static final int GEAR_WARNING_NO_READY = 3;
    public static final int GEAR_WARNING_NO_WARNING = 0;
    public static final int GEAR_WARNING_SHIFT_SWITCH_TO_N_GEAR = 6;
    public static final int GEAR_WARNING_SPEED_DOWN = 5;
    public static final int HIGH_VOLTAGE_RELAY_ADHESION = 1;
    public static final int HIGH_VOLTAGE_RELAY_NORMAL = 0;
    public static final int HVAC_PTC_EXTERNAL_ERROR = 2;
    public static final int HVAC_PTC_INTERNAL_ERROR = 1;
    public static final int HVAC_PTC_NORMAL = 0;
    public static final int IHB_STATE_ACTIVE = 3;
    public static final int IHB_STATE_OFF = 0;
    public static final int IHB_STATE_PASSIVE = 1;
    public static final int IHB_STATE_PERMANENT_FAILURE = 5;
    public static final int IHB_STATE_STANDBY = 2;
    public static final int IHB_STATE_TEMPORARY_FAILURE = 4;
    public static final int INT_DEFAULT_STATE = -1;
    public static final int IPU_DISABLED = 2;
    public static final int IPU_NORMAL = 0;
    public static final int IPU_REDUCE_POWER = 1;
    public static final int IPU_SYSTEM_SHUTDOWN = 3;
    public static final int ISLC_STATE_ACTIVE = 3;
    public static final int ISLC_STATE_NO_FAULT = 8;
    public static final int ISLC_STATE_OFF = 0;
    public static final int ISLC_STATE_OVERRIDE = 5;
    public static final int ISLC_STATE_PASSIVE = 1;
    public static final int ISLC_STATE_PERMANENT_FAILURE = 7;
    public static final int ISLC_STATE_STANDBY = 2;
    public static final int ISLC_STATE_STANDWAIT = 4;
    public static final int ISLC_STATE_TEMPORARY_FAILURE = 6;
    public static final int LDW_STATE_ACTIVE = 3;
    public static final int LDW_STATE_NO_FAULT = 6;
    public static final int LDW_STATE_OFF = 0;
    public static final int LDW_STATE_PASSIVE = 1;
    public static final int LDW_STATE_PERMANENT_FAILURE = 5;
    public static final int LDW_STATE_STANDBY = 2;
    public static final int LDW_STATE_TEMPORARY_FAILURE = 4;
    public static final int LIQUID_TEMP_HIGH = 1;
    public static final int LIQUID_TEMP_NORMAL = 0;
    public static final int MSM_INDEX_ECU = 12;
    public static final int MSM_INDEX_HORIZONAL_BACKWARD = 3;
    public static final int MSM_INDEX_HORIZONAL_FORWARD = 2;
    public static final int MSM_INDEX_HORIZONAL_HALL = 9;
    public static final int MSM_INDEX_HORIZONAL_MOTOR = 6;
    public static final int MSM_INDEX_TILTING_BACKWARD = 1;
    public static final int MSM_INDEX_TILTING_FORWARD = 0;
    public static final int MSM_INDEX_TILTING_HALL = 11;
    public static final int MSM_INDEX_TILTING_MOTOR = 8;
    public static final int MSM_INDEX_VERTICAL_DOWNWARD = 5;
    public static final int MSM_INDEX_VERTICAL_HALL = 10;
    public static final int MSM_INDEX_VERTICAL_MOTOR = 7;
    public static final int MSM_INDEX_VERTICAL_UPWARD = 4;
    public static final int OBC_MESSAGE_LOST = 1;
    public static final int OBC_MESSAGE_NORMAL = 0;
    public static final int POWER_LIMIT = 1;
    public static final int POWER_NORMAL = 0;
    public static final int RCTA_STATE_FAIL = 2;
    public static final int RCTA_STATE_OFF = 0;
    public static final int RCTA_STATE_ON = 1;
    public static final int RCW_STATE_FAIL = 2;
    public static final int RCW_STATE_OFF = 0;
    public static final int RCW_STATE_ON = 1;
    public static final int SCU_ACC_FAILURE = 9;
    public static final int SCU_CRUISE_CTRL_ACTIVE_FAILED = 1;
    public static final int SCU_CRUISE_CTRL_CANCELLED_ACTIVELY = 2;
    public static final int SCU_CRUISE_CTRL_CANCELLED_PASSIVELY = 8;
    public static final int SCU_INACTIVE_FOR_DOORS_UNLOCK = 5;
    public static final int SCU_INACTIVE_FOR_EPB_ACTIVE = 7;
    public static final int SCU_INACTIVE_FOR_NOT_D_SHIFT = 6;
    public static final int SCU_INACTIVE_FOR_SETBELT_UNLOCK = 4;
    public static final int SCU_LONG_CTRL_PLEASE_TAKE_OVER = 3;
    public static final int SCU_LONG_CTRL_REMIND_NONE = 0;
    public static final int SIDE_REVERSING_STATE_ACTIVE = 3;
    public static final int SIDE_REVERSING_STATE_NO_FAULT = 0;
    public static final int SIDE_REVERSING_STATE_PERMANENT_FAILURE = 2;
    public static final int SIDE_REVERSING_STATE_TEMPORARY_FAILURE = 1;
    public static final int SRR_BLOCKED = 1;
    public static final int SRR_DYNAMIC_CALIBRATION_RUNNING = 3;
    public static final int SRR_FAILURE = 2;
    public static final int SRR_NORMAL = 0;
    public static final int TCM_INDEX_OPEN_CIRCUIT = 3;
    public static final int TCM_INDEX_POSITION_SHORT_TO_GROUND = 0;
    public static final int TCM_INDEX_SHORT_TO_BATTERY = 1;
    public static final int TCM_INDEX_SHORT_TO_GROUND = 2;
    public static final int THERMAL_NORMAL = 0;
    public static final int THERMAL_RUNAWAY = 1;
    public static final int TIRE_INDEX_FRONT_LEFT = 0;
    public static final int TIRE_INDEX_FRONT_RIGHT = 1;
    public static final int TIRE_INDEX_REAR_LEFT = 2;
    public static final int TIRE_INDEX_REAR_RIGHT = 3;
    public static final int TIRE_PRESSURE_SENSOR_STATUS_FAILURE = 1;
    public static final int TIRE_PRESSURE_SENSOR_STATUS_NORMAL = 0;
    public static final int TIRE_TEMPERATURE_NORMAL = 0;
    public static final int TIRE_TEMPERATURE_WARNING = 1;
    public static final int TRACTION_BATTERY_SYSTEM_HOT = 1;
    private int abnormalTirePressureState;
    private int absFailureState;
    private int aebState;
    private int agsFailureState;
    private int airbagFailureState;
    private int alcCtrlRemindInfo;
    private int[] allMsmModulesFailureStates;
    private int[] allTirePerssureSensorsFailureStates;
    private int[] allTireTemperatureWarnings;
    private int alsFailureState;
    private int apaFailureState;
    private int apbFailureState;
    private int atlsFailureSate;
    private int avasFailureState;
    private int avhFailureState;
    private int bCruiseFailureState;
    private int batteryDeadState;
    private int batteryOverheatingState;
    private int[] batteryPtcFailureStates;
    private int batteryVoltageLowState;
    private int bcmSystemFailureState;
    private int bsdState;
    private int ccsWorkState;
    private int cdcFailureState;
    private int chargePortHotState;
    private int ciuSdcardFailureState;
    private int dcdcFailureState;
    private int dhcFailureState;
    private int dowState;
    private int driverMsmFailureState;
    private int driverMsmHeatSysFailureState;
    private int driverMsmVentilationMotorFailureState;
    private int dvrFailureState;
    private int ebsFailureState;
    private int electricMotorSystemHotState;
    private int electricVacuumPumpFailureState;
    private int epbSysFailureState;
    private int epsFailureState;
    private int espFailureState;
    private int evSysFailureState;
    private int flSrrFailureState;
    private int frSrrFailureState;
    private int frontIpuFailureState;
    private int gearWarningInfo;
    private int hdcFailureState;
    private int highBeamFailureState;
    private int highVoltageRelayAdhesionState;
    private int[] hvacCompressorFailureStates;
    private int hvacPtcFailureState;
    private int ibtFailureState;
    private int ihbState;
    private int islcState;
    private int ldwState;
    private int leftDaytimeRunningLightFailureState;
    private int leftTurnLampFailureState;
    private int liquidHighTempState;
    private int lluFailureState;
    private int lowBeamFailureState;
    private int obcMsgLostState;
    private int parkingLampFailureState;
    private int passengerMsmFailureState;
    private int passengerMsmHeatSysFailureState;
    private int powerLimitationState;
    private int rctaState;
    private int rcwState;
    private int rearFogLampFailureState;
    private int rearIpuFailureState;
    private int rightDaytimeRunningLightFailureState;
    private int rightTurnLampFailureState;
    private int rlSrrFailureState;
    private int rrSrrFailureState;
    private int scuLongCtrlRemindInfo;
    private int sideReversingState;
    private int[] tcmMotorFailureStates;
    private int thermalRunawayState;
    private int tpmsSysFailureState;
    private int waterSensorFailureState;
    private int xpuFailureState;
    public static final int[] INT_VECTOR_DEFAULT_STATE = null;
    public static final Integer[] INTEGER_VECTOR_DEFAULT_STATE = null;
    public static final Parcelable.Creator<EcusFailureStates> CREATOR = new Parcelable.Creator<EcusFailureStates>() { // from class: android.car.diagnostic.EcusFailureStates.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EcusFailureStates createFromParcel(Parcel source) {
            return new EcusFailureStates(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EcusFailureStates[] newArray(int size) {
            return new EcusFailureStates[size];
        }
    };

    public EcusFailureStates() {
        this.highBeamFailureState = -1;
        this.lowBeamFailureState = -1;
        this.leftTurnLampFailureState = -1;
        this.rightTurnLampFailureState = -1;
        this.rearFogLampFailureState = -1;
        this.rightDaytimeRunningLightFailureState = -1;
        this.leftDaytimeRunningLightFailureState = -1;
        this.parkingLampFailureState = -1;
        this.bcmSystemFailureState = -1;
        this.lluFailureState = -1;
        this.atlsFailureSate = -1;
        this.driverMsmFailureState = -1;
        this.driverMsmVentilationMotorFailureState = -1;
        this.driverMsmHeatSysFailureState = -1;
        this.passengerMsmFailureState = -1;
        this.passengerMsmHeatSysFailureState = -1;
        this.avasFailureState = -1;
        this.tpmsSysFailureState = -1;
        this.abnormalTirePressureState = -1;
        this.airbagFailureState = -1;
        this.alsFailureState = -1;
        this.dhcFailureState = -1;
        this.evSysFailureState = -1;
        this.gearWarningInfo = -1;
        this.ebsFailureState = -1;
        this.liquidHighTempState = -1;
        this.waterSensorFailureState = -1;
        this.bCruiseFailureState = -1;
        this.thermalRunawayState = -1;
        this.powerLimitationState = -1;
        this.chargePortHotState = -1;
        this.obcMsgLostState = -1;
        this.batteryDeadState = -1;
        this.rearIpuFailureState = -1;
        this.frontIpuFailureState = -1;
        this.espFailureState = -1;
        this.apbFailureState = -1;
        this.absFailureState = -1;
        this.avhFailureState = -1;
        this.ibtFailureState = -1;
        this.epsFailureState = -1;
        this.cdcFailureState = -1;
        this.xpuFailureState = -1;
        this.ihbState = -1;
        this.bsdState = -1;
        this.dowState = -1;
        this.rctaState = -1;
        this.rcwState = -1;
        this.flSrrFailureState = -1;
        this.frSrrFailureState = -1;
        this.rlSrrFailureState = -1;
        this.rrSrrFailureState = -1;
        this.scuLongCtrlRemindInfo = -1;
        this.islcState = -1;
        this.alcCtrlRemindInfo = -1;
        this.apaFailureState = -1;
        this.epbSysFailureState = -1;
        this.hdcFailureState = -1;
        this.allTireTemperatureWarnings = INT_VECTOR_DEFAULT_STATE;
        this.allTirePerssureSensorsFailureStates = INT_VECTOR_DEFAULT_STATE;
        this.batteryOverheatingState = -1;
        this.ccsWorkState = -1;
        this.dcdcFailureState = -1;
        this.batteryVoltageLowState = -1;
        this.electricMotorSystemHotState = -1;
        this.electricVacuumPumpFailureState = -1;
        this.highVoltageRelayAdhesionState = -1;
        this.agsFailureState = -1;
        this.tcmMotorFailureStates = INT_VECTOR_DEFAULT_STATE;
        this.allMsmModulesFailureStates = INT_VECTOR_DEFAULT_STATE;
        this.dvrFailureState = -1;
        this.ciuSdcardFailureState = -1;
        this.ldwState = -1;
        this.aebState = -1;
        this.sideReversingState = -1;
        this.hvacPtcFailureState = -1;
        this.hvacCompressorFailureStates = INT_VECTOR_DEFAULT_STATE;
        this.batteryPtcFailureStates = INT_VECTOR_DEFAULT_STATE;
    }

    protected EcusFailureStates(Parcel in) {
        this.highBeamFailureState = -1;
        this.lowBeamFailureState = -1;
        this.leftTurnLampFailureState = -1;
        this.rightTurnLampFailureState = -1;
        this.rearFogLampFailureState = -1;
        this.rightDaytimeRunningLightFailureState = -1;
        this.leftDaytimeRunningLightFailureState = -1;
        this.parkingLampFailureState = -1;
        this.bcmSystemFailureState = -1;
        this.lluFailureState = -1;
        this.atlsFailureSate = -1;
        this.driverMsmFailureState = -1;
        this.driverMsmVentilationMotorFailureState = -1;
        this.driverMsmHeatSysFailureState = -1;
        this.passengerMsmFailureState = -1;
        this.passengerMsmHeatSysFailureState = -1;
        this.avasFailureState = -1;
        this.tpmsSysFailureState = -1;
        this.abnormalTirePressureState = -1;
        this.airbagFailureState = -1;
        this.alsFailureState = -1;
        this.dhcFailureState = -1;
        this.evSysFailureState = -1;
        this.gearWarningInfo = -1;
        this.ebsFailureState = -1;
        this.liquidHighTempState = -1;
        this.waterSensorFailureState = -1;
        this.bCruiseFailureState = -1;
        this.thermalRunawayState = -1;
        this.powerLimitationState = -1;
        this.chargePortHotState = -1;
        this.obcMsgLostState = -1;
        this.batteryDeadState = -1;
        this.rearIpuFailureState = -1;
        this.frontIpuFailureState = -1;
        this.espFailureState = -1;
        this.apbFailureState = -1;
        this.absFailureState = -1;
        this.avhFailureState = -1;
        this.ibtFailureState = -1;
        this.epsFailureState = -1;
        this.cdcFailureState = -1;
        this.xpuFailureState = -1;
        this.ihbState = -1;
        this.bsdState = -1;
        this.dowState = -1;
        this.rctaState = -1;
        this.rcwState = -1;
        this.flSrrFailureState = -1;
        this.frSrrFailureState = -1;
        this.rlSrrFailureState = -1;
        this.rrSrrFailureState = -1;
        this.scuLongCtrlRemindInfo = -1;
        this.islcState = -1;
        this.alcCtrlRemindInfo = -1;
        this.apaFailureState = -1;
        this.epbSysFailureState = -1;
        this.hdcFailureState = -1;
        this.allTireTemperatureWarnings = INT_VECTOR_DEFAULT_STATE;
        this.allTirePerssureSensorsFailureStates = INT_VECTOR_DEFAULT_STATE;
        this.batteryOverheatingState = -1;
        this.ccsWorkState = -1;
        this.dcdcFailureState = -1;
        this.batteryVoltageLowState = -1;
        this.electricMotorSystemHotState = -1;
        this.electricVacuumPumpFailureState = -1;
        this.highVoltageRelayAdhesionState = -1;
        this.agsFailureState = -1;
        this.tcmMotorFailureStates = INT_VECTOR_DEFAULT_STATE;
        this.allMsmModulesFailureStates = INT_VECTOR_DEFAULT_STATE;
        this.dvrFailureState = -1;
        this.ciuSdcardFailureState = -1;
        this.ldwState = -1;
        this.aebState = -1;
        this.sideReversingState = -1;
        this.hvacPtcFailureState = -1;
        this.hvacCompressorFailureStates = INT_VECTOR_DEFAULT_STATE;
        this.batteryPtcFailureStates = INT_VECTOR_DEFAULT_STATE;
        this.highBeamFailureState = in.readInt();
        this.lowBeamFailureState = in.readInt();
        this.leftTurnLampFailureState = in.readInt();
        this.rightTurnLampFailureState = in.readInt();
        this.rearFogLampFailureState = in.readInt();
        this.rightDaytimeRunningLightFailureState = in.readInt();
        this.leftDaytimeRunningLightFailureState = in.readInt();
        this.parkingLampFailureState = in.readInt();
        this.bcmSystemFailureState = in.readInt();
        this.lluFailureState = in.readInt();
        this.atlsFailureSate = in.readInt();
        this.driverMsmFailureState = in.readInt();
        this.driverMsmVentilationMotorFailureState = in.readInt();
        this.driverMsmHeatSysFailureState = in.readInt();
        this.passengerMsmFailureState = in.readInt();
        this.passengerMsmHeatSysFailureState = in.readInt();
        this.avasFailureState = in.readInt();
        this.tpmsSysFailureState = in.readInt();
        this.abnormalTirePressureState = in.readInt();
        this.airbagFailureState = in.readInt();
        this.alsFailureState = in.readInt();
        this.dhcFailureState = in.readInt();
        this.evSysFailureState = in.readInt();
        this.gearWarningInfo = in.readInt();
        this.ebsFailureState = in.readInt();
        this.liquidHighTempState = in.readInt();
        this.waterSensorFailureState = in.readInt();
        this.bCruiseFailureState = in.readInt();
        this.thermalRunawayState = in.readInt();
        this.powerLimitationState = in.readInt();
        this.chargePortHotState = in.readInt();
        this.obcMsgLostState = in.readInt();
        this.batteryDeadState = in.readInt();
        this.rearIpuFailureState = in.readInt();
        this.frontIpuFailureState = in.readInt();
        this.espFailureState = in.readInt();
        this.apbFailureState = in.readInt();
        this.absFailureState = in.readInt();
        this.avhFailureState = in.readInt();
        this.ibtFailureState = in.readInt();
        this.epsFailureState = in.readInt();
        this.cdcFailureState = in.readInt();
        this.xpuFailureState = in.readInt();
        this.ihbState = in.readInt();
        this.bsdState = in.readInt();
        this.dowState = in.readInt();
        this.rctaState = in.readInt();
        this.rcwState = in.readInt();
        this.flSrrFailureState = in.readInt();
        this.frSrrFailureState = in.readInt();
        this.rlSrrFailureState = in.readInt();
        this.rrSrrFailureState = in.readInt();
        this.scuLongCtrlRemindInfo = in.readInt();
        this.islcState = in.readInt();
        this.alcCtrlRemindInfo = in.readInt();
        this.apaFailureState = in.readInt();
        this.epbSysFailureState = in.readInt();
        this.hdcFailureState = in.readInt();
        this.allTireTemperatureWarnings = in.createIntArray();
        this.allTirePerssureSensorsFailureStates = in.createIntArray();
        this.batteryOverheatingState = in.readInt();
        this.ccsWorkState = in.readInt();
        this.dcdcFailureState = in.readInt();
        this.batteryVoltageLowState = in.readInt();
        this.electricMotorSystemHotState = in.readInt();
        this.electricVacuumPumpFailureState = in.readInt();
        this.highVoltageRelayAdhesionState = in.readInt();
        this.agsFailureState = in.readInt();
        this.tcmMotorFailureStates = in.createIntArray();
        this.allMsmModulesFailureStates = in.createIntArray();
        this.dvrFailureState = in.readInt();
        this.ciuSdcardFailureState = in.readInt();
        this.ldwState = in.readInt();
        this.aebState = in.readInt();
        this.sideReversingState = in.readInt();
        this.hvacPtcFailureState = in.readInt();
        this.hvacCompressorFailureStates = in.createIntArray();
        this.batteryPtcFailureStates = in.createIntArray();
    }

    public String toString() {
        return "EcusFailureStates{highBeamFailureState=" + this.highBeamFailureState + ", lowBeamFailureState=" + this.lowBeamFailureState + ", leftTurnLampFailureState=" + this.leftTurnLampFailureState + ", rightTurnLampFailureState=" + this.rightTurnLampFailureState + ", rearFogLampFailureState=" + this.rearFogLampFailureState + ", rightDaytimeRunningLightFailureState=" + this.rightDaytimeRunningLightFailureState + ", leftDaytimeRunningLightFailureState=" + this.leftDaytimeRunningLightFailureState + ", parkingLampFailureState=" + this.parkingLampFailureState + ", bcmSystemFailureState=" + this.bcmSystemFailureState + ", lluFailureState=" + this.lluFailureState + ", atlsFailureSate=" + this.atlsFailureSate + ", driverMsmFailureState=" + this.driverMsmFailureState + ", driverMsmVentilationMotorFailureState=" + this.driverMsmVentilationMotorFailureState + ", driverMsmHeatSysFailureState=" + this.driverMsmHeatSysFailureState + ", passengerMsmFailureState=" + this.passengerMsmFailureState + ", passengerMsmHeatSysFailureState=" + this.passengerMsmHeatSysFailureState + ", avasFailureState=" + this.avasFailureState + ", tpmsSysFailureState=" + this.tpmsSysFailureState + ", abnormalTirePressureState=" + this.abnormalTirePressureState + ", airbagFailureState=" + this.airbagFailureState + ", alsFailureState=" + this.alsFailureState + ", dhcFailureState=" + this.dhcFailureState + ", evSysFailureState=" + this.evSysFailureState + ", gearWarningInfo=" + this.gearWarningInfo + ", ebsFailureState=" + this.ebsFailureState + ", liquidHighTempState=" + this.liquidHighTempState + ", waterSensorFailureState=" + this.waterSensorFailureState + ", bCruiseFailureState=" + this.bCruiseFailureState + ", thermalRunawayState=" + this.thermalRunawayState + ", powerLimitationState=" + this.powerLimitationState + ", chargePortHotState=" + this.chargePortHotState + ", obcMsgLostState=" + this.obcMsgLostState + ", batteryDeadState=" + this.batteryDeadState + ", rearIpuFailureState=" + this.rearIpuFailureState + ", frontIpuFailureState=" + this.frontIpuFailureState + ", espFailureState=" + this.espFailureState + ", apbFailureState=" + this.apbFailureState + ", absFailureState=" + this.absFailureState + ", avhFailureState=" + this.avhFailureState + ", ibtFailureState=" + this.ibtFailureState + ", epsFailureState=" + this.epsFailureState + ", cdcFailureState=" + this.cdcFailureState + ", xpuFailureState=" + this.xpuFailureState + ", ihbState=" + this.ihbState + ", bsdState=" + this.bsdState + ", dowState=" + this.dowState + ", rctaState=" + this.rctaState + ", rcwState=" + this.rcwState + ", flSrrFailureState=" + this.flSrrFailureState + ", frSrrFailureState=" + this.frSrrFailureState + ", rlSrrFailureState=" + this.rlSrrFailureState + ", rrSrrFailureState=" + this.rrSrrFailureState + ", scuLongCtrlRemindInfo=" + this.scuLongCtrlRemindInfo + ", islcState=" + this.islcState + ", alcCtrlRemindInfo=" + this.alcCtrlRemindInfo + ", apaFailureState=" + this.apaFailureState + ", epbSysFailureState=" + this.epbSysFailureState + ", hdcFailureState=" + this.hdcFailureState + ", allTireTemperatureWarnings=" + Arrays.toString(this.allTireTemperatureWarnings) + ", allTirePerssureSensorsFailureStates=" + Arrays.toString(this.allTirePerssureSensorsFailureStates) + ", batteryOverheatingState=" + this.batteryOverheatingState + ", ccsWorkState=" + this.ccsWorkState + ", dcdcFailureState=" + this.dcdcFailureState + ", batteryVoltageLowState=" + this.batteryVoltageLowState + ", electricMotorSystemHotState=" + this.electricMotorSystemHotState + ", electricVacuumPumpFailureState=" + this.electricVacuumPumpFailureState + ", highVoltageRelayAdhesionState=" + this.highVoltageRelayAdhesionState + ", agsFailureState=" + this.agsFailureState + ", tcmMotorFailureStates=" + Arrays.toString(this.tcmMotorFailureStates) + ", allMsmModulesFailureStates=" + Arrays.toString(this.allMsmModulesFailureStates) + ", dvrFailureState=" + this.dvrFailureState + ", ciuSdcardFailureState=" + this.ciuSdcardFailureState + ", ldwState=" + this.ldwState + ", aebState=" + this.aebState + ", sideReversingState=" + this.sideReversingState + ", hvacPtcFailureState=" + this.hvacPtcFailureState + ", hvacCompressorFailureStates=" + Arrays.toString(this.hvacCompressorFailureStates) + ", batteryPtcFailureStates=" + Arrays.toString(this.batteryPtcFailureStates) + '}';
    }

    public int getEpbSysFailureState() {
        return getIntValueOrThrow(this.epbSysFailureState);
    }

    public void setEpbSysFailureState(int epbSysFailureState) {
        this.epbSysFailureState = epbSysFailureState;
    }

    public int getHdcFailureState() {
        return getIntValueOrThrow(this.hdcFailureState);
    }

    public void setHdcFailureState(int hdcFailureState) {
        this.hdcFailureState = hdcFailureState;
    }

    public int[] getAllTireTemperatureWarnings() {
        return getIntVectorValueOrThrow(this.allTireTemperatureWarnings);
    }

    public void setAllTireTemperatureWarnings(int[] allTireTemperatureWarnings) {
        this.allTireTemperatureWarnings = allTireTemperatureWarnings;
    }

    public int[] getAllTirePerssureSensorsFailureStates() {
        return getIntVectorValueOrThrow(this.allTirePerssureSensorsFailureStates);
    }

    public void setAllTirePerssureSensorsFailureStates(int[] allTirePerssureSensorsFailureStates) {
        this.allTirePerssureSensorsFailureStates = allTirePerssureSensorsFailureStates;
    }

    public int getBatteryOverheatingState() {
        return getIntValueOrThrow(this.batteryOverheatingState);
    }

    public void setBatteryOverheatingState(int batteryOverheatingState) {
        this.batteryOverheatingState = batteryOverheatingState;
    }

    public int getCcsWorkState() {
        return getIntValueOrThrow(this.ccsWorkState);
    }

    public void setCcsWorkState(int ccsWorkState) {
        this.ccsWorkState = ccsWorkState;
    }

    public int getDcdcFailureState() {
        return getIntValueOrThrow(this.dcdcFailureState);
    }

    public void setDcdcFailureState(int dcdcFailureState) {
        this.dcdcFailureState = dcdcFailureState;
    }

    public int getBatteryVoltageLowState() {
        return getIntValueOrThrow(this.batteryVoltageLowState);
    }

    public void setBatteryVoltageLowState(int batteryVoltageLowState) {
        this.batteryVoltageLowState = batteryVoltageLowState;
    }

    public int getElectricMotorSystemHotState() {
        return getIntValueOrThrow(this.electricMotorSystemHotState);
    }

    public void setElectricMotorSystemHotState(int electricMotorSystemHotState) {
        this.electricMotorSystemHotState = electricMotorSystemHotState;
    }

    public int getElectricVacuumPumpFailureState() {
        return getIntValueOrThrow(this.electricVacuumPumpFailureState);
    }

    public void setElectricVacuumPumpFailureState(int electricVacuumPumpFailureState) {
        this.electricVacuumPumpFailureState = electricVacuumPumpFailureState;
    }

    public int getHighVoltageRelayAdhesionState() {
        return getIntValueOrThrow(this.highVoltageRelayAdhesionState);
    }

    public int getAgsFailureState() {
        return getIntValueOrThrow(this.agsFailureState);
    }

    public void setHighVoltageRelayAdhesionState(int highVoltageRelayAdhesionState) {
        this.highVoltageRelayAdhesionState = highVoltageRelayAdhesionState;
    }

    public void setAgsFailureState(int agsFailureState) {
        this.agsFailureState = agsFailureState;
    }

    public int[] getTcmMotorFailureStates() {
        return getIntVectorValueOrThrow(this.tcmMotorFailureStates);
    }

    public void setTcmMotorFailureStates(int[] tcmMotorFailureStates) {
        this.tcmMotorFailureStates = tcmMotorFailureStates;
    }

    public int[] getAllMsmModulesFailureStates() {
        return getIntVectorValueOrThrow(this.allMsmModulesFailureStates);
    }

    public void setAllMsmModulesFailureStates(int[] allMsmModulesFailureStates) {
        this.allMsmModulesFailureStates = allMsmModulesFailureStates;
    }

    public int getDvrFailureState() {
        return getIntValueOrThrow(this.dvrFailureState);
    }

    public void setDvrFailureState(int dvrFailureState) {
        this.dvrFailureState = dvrFailureState;
    }

    public int getCiuSdcardFailureState() {
        return getIntValueOrThrow(this.ciuSdcardFailureState);
    }

    public void setCiuSdcardFailureState(int ciuSdcardFailureState) {
        this.ciuSdcardFailureState = ciuSdcardFailureState;
    }

    public int getLdwState() {
        return getIntValueOrThrow(this.ldwState);
    }

    public void setLdwState(int ldwState) {
        this.ldwState = ldwState;
    }

    public int getAebState() {
        return getIntValueOrThrow(this.aebState);
    }

    public void setAebState(int aebState) {
        this.aebState = aebState;
    }

    public int getSideReversingState() {
        return getIntValueOrThrow(this.sideReversingState);
    }

    public void setSideReversingState(int sideReversingState) {
        this.sideReversingState = sideReversingState;
    }

    public int getHvacPtcFailureState() {
        return getIntValueOrThrow(this.hvacPtcFailureState);
    }

    public void setHvacPtcFailureState(int hvacPtcFailureState) {
        this.hvacPtcFailureState = hvacPtcFailureState;
    }

    public int[] getHvacCompressorFailureStates() {
        return getIntVectorValueOrThrow(this.hvacCompressorFailureStates);
    }

    public void setHvacCompressorFailureStates(int[] hvacCompressorFailureStates) {
        this.hvacCompressorFailureStates = hvacCompressorFailureStates;
    }

    public int[] getBatteryPtcFailureStates() {
        return getIntVectorValueOrThrow(this.batteryPtcFailureStates);
    }

    public void setBatteryPtcFailureStates(int[] batteryPtcFailureStates) {
        this.batteryPtcFailureStates = batteryPtcFailureStates;
    }

    public int getRearIpuFailureState() throws Exception {
        return getIntValueOrThrow(this.rearIpuFailureState);
    }

    public void setRearIpuFailureState(int rearIpuFailureState) {
        this.rearIpuFailureState = rearIpuFailureState;
    }

    public int getFrontIpuFailureState() throws Exception {
        return getIntValueOrThrow(this.frontIpuFailureState);
    }

    public void setFrontIpuFailureState(int frontIpuFailureState) {
        this.frontIpuFailureState = frontIpuFailureState;
    }

    public int getHighBeamFailureState() throws Exception {
        return getIntValueOrThrow(this.highBeamFailureState);
    }

    public void setHighBeamFailureState(int highBeamFailureState) {
        this.highBeamFailureState = highBeamFailureState;
    }

    public int getLowBeamFailureState() throws Exception {
        return getIntValueOrThrow(this.lowBeamFailureState);
    }

    public void setLowBeamFailureState(int lowBeamFailureState) {
        this.lowBeamFailureState = lowBeamFailureState;
    }

    public int getLeftTurnLampFailureState() throws Exception {
        return getIntValueOrThrow(this.leftTurnLampFailureState);
    }

    public void setLeftTurnLampFailureState(int leftTurnLampFailureState) {
        this.leftTurnLampFailureState = leftTurnLampFailureState;
    }

    public int getRightTurnLampFailureState() throws Exception {
        return getIntValueOrThrow(this.rightTurnLampFailureState);
    }

    public void setRightTurnLampFailureState(int rightTurnLampFailureState) {
        this.rightTurnLampFailureState = rightTurnLampFailureState;
    }

    public int getRearFogLampFailureState() throws Exception {
        return getIntValueOrThrow(this.rearFogLampFailureState);
    }

    public void setRearFogLampFailureState(int rearFogLampFailureState) {
        this.rearFogLampFailureState = rearFogLampFailureState;
    }

    public int getRightDaytimeRunningLightFailureState() throws Exception {
        return getIntValueOrThrow(this.rightDaytimeRunningLightFailureState);
    }

    public void setRightDaytimeRunningLightFailureState(int rightDaytimeRunningLightFailureState) {
        this.rightDaytimeRunningLightFailureState = rightDaytimeRunningLightFailureState;
    }

    public int getLeftDaytimeRunningLightFailureState() throws Exception {
        return getIntValueOrThrow(this.leftDaytimeRunningLightFailureState);
    }

    public void setLeftDaytimeRunningLightFailureState(int leftDaytimeRunningLightFailureState) {
        this.leftDaytimeRunningLightFailureState = leftDaytimeRunningLightFailureState;
    }

    public int getParkingLampFailureState() throws Exception {
        return getIntValueOrThrow(this.parkingLampFailureState);
    }

    public void setParkingLampFailureState(int parkingLampFailureState) {
        this.parkingLampFailureState = parkingLampFailureState;
    }

    public int getBcmSystemFailureState() throws Exception {
        return getIntValueOrThrow(this.bcmSystemFailureState);
    }

    public void setBcmSystemFailureState(int bcmSystemFailureState) {
        this.bcmSystemFailureState = bcmSystemFailureState;
    }

    public int getLluFailureState() throws Exception {
        return getIntValueOrThrow(this.lluFailureState);
    }

    public void setLluFailureState(int lluFailureState) {
        this.lluFailureState = lluFailureState;
    }

    public int getAtlsFailureSate() throws Exception {
        return getIntValueOrThrow(this.atlsFailureSate);
    }

    public void setAtlsFailureSate(int atlsFailureSate) {
        this.atlsFailureSate = atlsFailureSate;
    }

    public int getDriverMsmFailureState() throws Exception {
        return getIntValueOrThrow(this.driverMsmFailureState);
    }

    public void setDriverMsmFailureState(int driverMsmFailureState) {
        this.driverMsmFailureState = driverMsmFailureState;
    }

    public int getDriverMsmVentilationMotorFailureState() throws Exception {
        return getIntValueOrThrow(this.driverMsmVentilationMotorFailureState);
    }

    public void setDriverMsmVentilationMotorFailureState(int driverMsmVentilationMotorFailureState) {
        this.driverMsmVentilationMotorFailureState = driverMsmVentilationMotorFailureState;
    }

    public int getDriverMsmHeatSysFailureState() throws Exception {
        return getIntValueOrThrow(this.driverMsmHeatSysFailureState);
    }

    public void setDriverMsmHeatSysFailureState(int driverMsmHeatSysFailureState) {
        this.driverMsmHeatSysFailureState = driverMsmHeatSysFailureState;
    }

    public int getPassengerMsmFailureState() throws Exception {
        return getIntValueOrThrow(this.passengerMsmFailureState);
    }

    public void setPassengerMsmFailureState(int passengerMsmFailureState) {
        this.passengerMsmFailureState = passengerMsmFailureState;
    }

    public int getPassengerMsmHeatSysFailureState() throws Exception {
        return getIntValueOrThrow(this.passengerMsmHeatSysFailureState);
    }

    public void setPassengerMsmHeatSysFailureState(int passengerMsmHeatSysFailureState) {
        this.passengerMsmHeatSysFailureState = passengerMsmHeatSysFailureState;
    }

    public int getAvasFailureState() throws Exception {
        return getIntValueOrThrow(this.avasFailureState);
    }

    public void setAvasFailureState(int avasFailureState) {
        this.avasFailureState = avasFailureState;
    }

    public int getTpmsSysFailureState() throws Exception {
        return getIntValueOrThrow(this.tpmsSysFailureState);
    }

    public void setTpmsSysFailureState(int tpmsSysFailureState) {
        this.tpmsSysFailureState = tpmsSysFailureState;
    }

    public int getAbnormalTirePressureState() throws Exception {
        return getIntValueOrThrow(this.abnormalTirePressureState);
    }

    public void setAbnormalTirePressureState(int abnormalTirePressureState) {
        this.abnormalTirePressureState = abnormalTirePressureState;
    }

    public int getAirbagFailureState() throws Exception {
        return getIntValueOrThrow(this.airbagFailureState);
    }

    public void setAirbagFailureState(int airbagFailureState) {
        this.airbagFailureState = airbagFailureState;
    }

    public int getAlsFailureState() throws Exception {
        return getIntValueOrThrow(this.alsFailureState);
    }

    public void setAlsFailureState(int alsFailureState) {
        this.alsFailureState = alsFailureState;
    }

    public int getDhcFailureState() throws Exception {
        return getIntValueOrThrow(this.dhcFailureState);
    }

    public void setDhcFailureState(int dhcFailureState) {
        this.dhcFailureState = dhcFailureState;
    }

    public int getEvSysFailureState() throws Exception {
        return getIntValueOrThrow(this.evSysFailureState);
    }

    public void setEvSysFailureState(int evSysFailureState) {
        this.evSysFailureState = evSysFailureState;
    }

    public int getGearWarningInfo() throws Exception {
        return getIntValueOrThrow(this.gearWarningInfo);
    }

    public void setGearWarningInfo(int gearWarningInfo) {
        this.gearWarningInfo = gearWarningInfo;
    }

    public int getEbsFailureState() throws Exception {
        return getIntValueOrThrow(this.ebsFailureState);
    }

    public void setEbsFailureState(int ebsFailureState) {
        this.ebsFailureState = ebsFailureState;
    }

    public int getLiquidHighTempState() throws Exception {
        return getIntValueOrThrow(this.liquidHighTempState);
    }

    public void setLiquidHighTempState(int liquidHighTempState) {
        this.liquidHighTempState = liquidHighTempState;
    }

    public int getWaterSensorFailureState() throws Exception {
        return getIntValueOrThrow(this.waterSensorFailureState);
    }

    public void setWaterSensorFailureState(int waterSensorFailureState) {
        this.waterSensorFailureState = waterSensorFailureState;
    }

    public int getBCruiseFailureState() throws Exception {
        return getIntValueOrThrow(this.bCruiseFailureState);
    }

    public void setBCruiseFailureState(int bCruiseFailureState) {
        this.bCruiseFailureState = bCruiseFailureState;
    }

    public int getThermalRunawayState() throws Exception {
        return getIntValueOrThrow(this.thermalRunawayState);
    }

    public void setThermalRunawayState(int thermalRunawayState) {
        this.thermalRunawayState = thermalRunawayState;
    }

    public int getPowerLimitationState() throws Exception {
        return getIntValueOrThrow(this.powerLimitationState);
    }

    public void setPowerLimitationState(int powerLimitationState) {
        this.powerLimitationState = powerLimitationState;
    }

    public int getChargePortHotState() throws Exception {
        return getIntValueOrThrow(this.chargePortHotState);
    }

    public void setChargePortHotState(int chargePortHotState) {
        this.chargePortHotState = chargePortHotState;
    }

    public int getObcMsgLostState() throws Exception {
        return getIntValueOrThrow(this.obcMsgLostState);
    }

    public void setObcMsgLostState(int obcMsgLostState) {
        this.obcMsgLostState = obcMsgLostState;
    }

    public int getBatteryDeadState() throws Exception {
        return getIntValueOrThrow(this.batteryDeadState);
    }

    public void setBatteryDeadState(int batteryDeadState) {
        this.batteryDeadState = batteryDeadState;
    }

    public int getEspFailureState() throws Exception {
        return getIntValueOrThrow(this.espFailureState);
    }

    public void setEspFailureState(int espFailureState) {
        this.espFailureState = espFailureState;
    }

    public int getApbFailureState() throws Exception {
        return getIntValueOrThrow(this.apbFailureState);
    }

    public void setApbFailureState(int apbFailureState) {
        this.apbFailureState = apbFailureState;
    }

    public int getAbsFailureState() throws Exception {
        return getIntValueOrThrow(this.absFailureState);
    }

    public void setAbsFailureState(int absFailureState) {
        this.absFailureState = absFailureState;
    }

    public int getAvhFailureState() throws Exception {
        return getIntValueOrThrow(this.avhFailureState);
    }

    public void setAvhFailureState(int avhFailureState) {
        this.avhFailureState = avhFailureState;
    }

    public int getIbtFailureState() throws Exception {
        return getIntValueOrThrow(this.ibtFailureState);
    }

    public void setIbtFailureState(int ibtFailureState) {
        this.ibtFailureState = ibtFailureState;
    }

    public int getEpsFailureState() throws Exception {
        return getIntValueOrThrow(this.epsFailureState);
    }

    public void setEpsFailureState(int epsFailureState) {
        this.epsFailureState = epsFailureState;
    }

    public int getCdcFailureState() throws Exception {
        return getIntValueOrThrow(this.cdcFailureState);
    }

    public void setCdcFailureState(int cdcFailureState) {
        this.cdcFailureState = cdcFailureState;
    }

    public int getXpuFailureState() throws Exception {
        return getIntValueOrThrow(this.xpuFailureState);
    }

    public void setXpuFailureState(int xpuFailureState) {
        this.xpuFailureState = xpuFailureState;
    }

    public int getIhbState() throws Exception {
        return getIntValueOrThrow(this.ihbState);
    }

    public void setIhbState(int ihbState) {
        this.ihbState = ihbState;
    }

    public int getBsdState() throws Exception {
        return getIntValueOrThrow(this.bsdState);
    }

    public void setBsdState(int bsdState) {
        this.bsdState = bsdState;
    }

    public int getDowState() throws Exception {
        return getIntValueOrThrow(this.dowState);
    }

    public void setDowState(int dowState) {
        this.dowState = dowState;
    }

    public int getRctaState() throws Exception {
        return getIntValueOrThrow(this.rctaState);
    }

    public void setRctaState(int rctaState) {
        this.rctaState = rctaState;
    }

    public int getRcwState() throws Exception {
        return getIntValueOrThrow(this.rcwState);
    }

    public void setRcwState(int rcwState) {
        this.rcwState = rcwState;
    }

    public int getFlSrrFailureState() throws Exception {
        return getIntValueOrThrow(this.flSrrFailureState);
    }

    public void setFlSrrFailureState(int flSrrFailureState) {
        this.flSrrFailureState = flSrrFailureState;
    }

    public int getFrSrrFailureState() throws Exception {
        return getIntValueOrThrow(this.frSrrFailureState);
    }

    public void setFrSrrFailureState(int frSrrFailureState) {
        this.frSrrFailureState = frSrrFailureState;
    }

    public int getRlSrrFailureState() throws Exception {
        return getIntValueOrThrow(this.rlSrrFailureState);
    }

    public void setRlSrrFailureState(int rlSrrFailureState) {
        this.rlSrrFailureState = rlSrrFailureState;
    }

    public int getRrSrrFailureState() throws Exception {
        return getIntValueOrThrow(this.rrSrrFailureState);
    }

    public void setRrSrrFailureState(int rrSrrFailureState) {
        this.rrSrrFailureState = rrSrrFailureState;
    }

    public int getScuLongCtrlRemindInfo() throws Exception {
        return getIntValueOrThrow(this.scuLongCtrlRemindInfo);
    }

    public void setScuLongCtrlRemindInfo(int scuLongCtrlRemindInfo) {
        this.scuLongCtrlRemindInfo = scuLongCtrlRemindInfo;
    }

    public int getIslcState() throws Exception {
        return getIntValueOrThrow(this.islcState);
    }

    public void setIslcState(int islcState) {
        this.islcState = islcState;
    }

    public int getAlcCtrlRemindInfo() throws Exception {
        return getIntValueOrThrow(this.alcCtrlRemindInfo);
    }

    public void setAlcCtrlRemindInfo(int alcCtrlRemindInfo) {
        this.alcCtrlRemindInfo = alcCtrlRemindInfo;
    }

    private int getIntValueOrThrow(int value) throws IllegalArgumentException {
        if (-1 == value) {
            throw new ValueUnavailableException();
        }
        return value;
    }

    private int[] getIntVectorValueOrThrow(int[] value) throws IllegalArgumentException {
        if (INT_VECTOR_DEFAULT_STATE == value) {
            throw new ValueUnavailableException();
        }
        return value;
    }

    public int getApaFailureState() throws Exception {
        return getIntValueOrThrow(this.apaFailureState);
    }

    public void setApaFailureState(int apaFailureState) {
        this.apaFailureState = apaFailureState;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.highBeamFailureState);
        dest.writeInt(this.lowBeamFailureState);
        dest.writeInt(this.leftTurnLampFailureState);
        dest.writeInt(this.rightTurnLampFailureState);
        dest.writeInt(this.rearFogLampFailureState);
        dest.writeInt(this.rightDaytimeRunningLightFailureState);
        dest.writeInt(this.leftDaytimeRunningLightFailureState);
        dest.writeInt(this.parkingLampFailureState);
        dest.writeInt(this.bcmSystemFailureState);
        dest.writeInt(this.lluFailureState);
        dest.writeInt(this.atlsFailureSate);
        dest.writeInt(this.driverMsmFailureState);
        dest.writeInt(this.driverMsmVentilationMotorFailureState);
        dest.writeInt(this.driverMsmHeatSysFailureState);
        dest.writeInt(this.passengerMsmFailureState);
        dest.writeInt(this.passengerMsmHeatSysFailureState);
        dest.writeInt(this.avasFailureState);
        dest.writeInt(this.tpmsSysFailureState);
        dest.writeInt(this.abnormalTirePressureState);
        dest.writeInt(this.airbagFailureState);
        dest.writeInt(this.alsFailureState);
        dest.writeInt(this.dhcFailureState);
        dest.writeInt(this.evSysFailureState);
        dest.writeInt(this.gearWarningInfo);
        dest.writeInt(this.ebsFailureState);
        dest.writeInt(this.liquidHighTempState);
        dest.writeInt(this.waterSensorFailureState);
        dest.writeInt(this.bCruiseFailureState);
        dest.writeInt(this.thermalRunawayState);
        dest.writeInt(this.powerLimitationState);
        dest.writeInt(this.chargePortHotState);
        dest.writeInt(this.obcMsgLostState);
        dest.writeInt(this.batteryDeadState);
        dest.writeInt(this.rearIpuFailureState);
        dest.writeInt(this.frontIpuFailureState);
        dest.writeInt(this.espFailureState);
        dest.writeInt(this.apbFailureState);
        dest.writeInt(this.absFailureState);
        dest.writeInt(this.avhFailureState);
        dest.writeInt(this.ibtFailureState);
        dest.writeInt(this.epsFailureState);
        dest.writeInt(this.cdcFailureState);
        dest.writeInt(this.xpuFailureState);
        dest.writeInt(this.ihbState);
        dest.writeInt(this.bsdState);
        dest.writeInt(this.dowState);
        dest.writeInt(this.rctaState);
        dest.writeInt(this.rcwState);
        dest.writeInt(this.flSrrFailureState);
        dest.writeInt(this.frSrrFailureState);
        dest.writeInt(this.rlSrrFailureState);
        dest.writeInt(this.rrSrrFailureState);
        dest.writeInt(this.scuLongCtrlRemindInfo);
        dest.writeInt(this.islcState);
        dest.writeInt(this.alcCtrlRemindInfo);
        dest.writeInt(this.apaFailureState);
        dest.writeInt(this.epbSysFailureState);
        dest.writeInt(this.hdcFailureState);
        dest.writeIntArray(this.allTireTemperatureWarnings);
        dest.writeIntArray(this.allTirePerssureSensorsFailureStates);
        dest.writeInt(this.batteryOverheatingState);
        dest.writeInt(this.ccsWorkState);
        dest.writeInt(this.dcdcFailureState);
        dest.writeInt(this.batteryVoltageLowState);
        dest.writeInt(this.electricMotorSystemHotState);
        dest.writeInt(this.electricVacuumPumpFailureState);
        dest.writeInt(this.highVoltageRelayAdhesionState);
        dest.writeInt(this.agsFailureState);
        dest.writeIntArray(this.tcmMotorFailureStates);
        dest.writeIntArray(this.allMsmModulesFailureStates);
        dest.writeInt(this.dvrFailureState);
        dest.writeInt(this.ciuSdcardFailureState);
        dest.writeInt(this.ldwState);
        dest.writeInt(this.aebState);
        dest.writeInt(this.sideReversingState);
        dest.writeInt(this.hvacPtcFailureState);
        dest.writeIntArray(this.hvacCompressorFailureStates);
        dest.writeIntArray(this.batteryPtcFailureStates);
    }
}
