package android.car.hardware.vcu;

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
public final class CarVcuManager extends CarEcuManager {
    public static final int AC_VOLTAGE_STATUS_FAULT = 4;
    public static final int AC_VOLTAGE_STATUS_INVALID = 1;
    public static final int AC_VOLTAGE_STATUS_SINGLE_PHASE = 2;
    public static final int AC_VOLTAGE_STATUS_THREE_PHASE = 3;
    public static final int AC_VOLT_ERROR = 2;
    public static final int AS_DRVIVING_MODE_COMFORT_STATUS = 1;
    public static final int AS_DRVIVING_MODE_CUSTOMER_MODE_STATUS = 4;
    public static final int AS_DRVIVING_MODE_ECO_STATUS = 0;
    public static final int AS_DRVIVING_MODE_NORMAL_STATUS = 2;
    public static final int AS_DRVIVING_MODE_NOT_VALID_STATUS = 6;
    public static final int AS_DRVIVING_MODE_OFF_ROAD_STATUS = 5;
    public static final int AS_DRVIVING_MODE_SPORT_STATUS = 3;
    public static final int AWD_MODE_F1R9 = 9;
    public static final int AWD_MODE_F2R8 = 8;
    public static final int AWD_MODE_F3R7 = 7;
    public static final int AWD_MODE_F4R6 = 6;
    public static final int AWD_MODE_F5R5 = 5;
    public static final int AWD_MODE_F6R4 = 4;
    public static final int AWD_MODE_F7R3 = 3;
    public static final int AWD_MODE_F8R2 = 2;
    public static final int AWD_MODE_F9R1 = 1;
    public static final int AWD_MODE_FWD = 0;
    public static final int AWD_MODE_RWD = 10;
    public static final int BATTERY_COLDING = 1;
    public static final int BATTERY_KEEP_TEMP_MODE_DEFAULT = 0;
    public static final int BATTERY_KEEP_TEMP_MODE_EXIT = 7;
    public static final int BATTERY_KEEP_TEMP_MODE_FAILURE = 6;
    public static final int BATTERY_KEEP_TEMP_MODE_FINISH = 2;
    public static final int BATTERY_KEEP_TEMP_MODE_NO_NEEDS = 3;
    public static final int BATTERY_KEEP_TEMP_MODE_ONGOING = 1;
    public static final int BATTERY_KEEP_TEMP_MODE_STOP_BY_SOC = 5;
    public static final int BATTERY_KEEP_TEMP_MODE_STOP_BY_UNPLUG = 4;
    public static final int BATTERY_NORMAL = 0;
    public static final int BATTERY_NOT_OVERHEATING = 0;
    public static final int BATTERY_OVERHEATING = 1;
    public static final int BATTERY_TOO_COLD = 1;
    public static final int BATTERY_WARMING = 1;
    @Deprecated
    public static final int BATTERY_WARMING_START = 1;
    @Deprecated
    public static final int BATTERY_WARMING_STOP = 0;
    @Deprecated
    public static final int BMS_CHARGE_STATUS_BMS_ABNORMAL_STOP = 4;
    @Deprecated
    public static final int BMS_CHARGE_STATUS_BMS_ERROR_STOP = 3;
    @Deprecated
    public static final int BMS_CHARGE_STATUS_CHARGE_FULL = 2;
    @Deprecated
    public static final int BMS_CHARGE_STATUS_CHARGING = 1;
    @Deprecated
    public static final int BMS_CHARGE_STATUS_DC_CHARGER_ERROR_STOP = 6;
    @Deprecated
    public static final int BMS_CHARGE_STATUS_DC_CHARGER_NORMAL_STOP = 5;
    @Deprecated
    public static final int BMS_CHARGE_STATUS_IPU_ERR = 9;
    @Deprecated
    public static final int BMS_CHARGE_STATUS_LDCU_STOP = 7;
    @Deprecated
    public static final int BMS_CHARGE_STATUS_NO_CHARGE = 0;
    @Deprecated
    public static final int BMS_CHARGE_STATUS_OBC_STOP = 8;
    public static final int CAR_MOVING = 0;
    public static final int CAR_STATIONARY = 1;
    @Deprecated
    public static final int CHANGE_STOP_REASON_BMS_ERROR = 2;
    @Deprecated
    public static final int CHANGE_STOP_REASON_DC_ERROR_STOP = 4;
    @Deprecated
    public static final int CHANGE_STOP_REASON_DC_NORMAL_STOP = 3;
    @Deprecated
    public static final int CHANGE_STOP_REASON_DEFAULT = 0;
    @Deprecated
    public static final int CHANGE_STOP_REASON_FULL = 1;
    public static final int CHARGE_ERROR_GAIN = 1;
    public static final int CHARGE_ERROR_NONE = 0;
    public static final int CHARGE_GUN_AC_AND_DC_LINK = 3;
    public static final int CHARGE_GUN_AC_LINK = 1;
    public static final int CHARGE_GUN_DC_LINK = 2;
    public static final int CHARGE_GUN_EVCC_LINK = 6;
    public static final int CHARGE_GUN_INVALID = 7;
    @Deprecated
    public static final int CHARGE_GUN_MODE_COLLISION = 3;
    @Deprecated
    public static final int CHARGE_GUN_Mode_Collision = 3;
    public static final int CHARGE_GUN_NO_LINK = 0;
    public static final int CHARGE_GUN_V2L_LINK = 4;
    public static final int CHARGE_GUN_V2L_LINK_LOW_POWER_OBC = 5;
    @Deprecated
    public static final int CHARGE_MODE_DELAY = 3;
    @Deprecated
    public static final int CHARGE_MODE_FIXED_TIME = 2;
    @Deprecated
    public static final int CHARGE_MODE_IMMEDIATELY = 1;
    @Deprecated
    public static final int CHARGE_MODE_NOT_DEFINE = 0;
    @Deprecated
    public static final int CHARGE_MODE_SECTION = 4;
    @Deprecated
    public static final int CHARGE_MODE_STOP = 5;
    public static final int CHARGE_SPEED_SLOW = 1;
    public static final int CHARGE_SPEED_SLOW_INVALID = 0;
    public static final int CHARGE_STATUS_APPOINTMENT = 1;
    public static final int CHARGE_STATUS_CHARGER_ERROR = 20;
    public static final int CHARGE_STATUS_CHARGE_DONE = 4;
    public static final int CHARGE_STATUS_CHARGE_ERROR = 3;
    public static final int CHARGE_STATUS_CHARGE_STOPPING = 19;
    public static final int CHARGE_STATUS_CHARGING = 2;
    public static final int CHARGE_STATUS_DISCHARGE_DONE = 6;
    public static final int CHARGE_STATUS_DISCHARGE_ERROR = 7;
    public static final int CHARGE_STATUS_DISCHARGING = 5;
    public static final int CHARGE_STATUS_FULLY_CHARGED = 16;
    public static final int CHARGE_STATUS_INVALID = 15;
    public static final int CHARGE_STATUS_PREPARE = 0;
    @Deprecated
    public static final int CHARGE_STATUS_REMOVE_CHAGRE_CONNECTOR = 17;
    public static final int CHARGE_STATUS_REMOVE_CHARGE_CONNECTOR = 17;
    public static final int CHARGE_STATUS_WRONG_OPERATION = 18;
    public static final int CHARGE_STOP_REASON_BMS_ERROR = 2;
    public static final int CHARGE_STOP_REASON_DC_ERROR_STOP = 4;
    public static final int CHARGE_STOP_REASON_DC_NORMAL_STOP = 3;
    public static final int CHARGE_STOP_REASON_DEFAULT = 0;
    public static final int CHARGE_STOP_REASON_FULL = 1;
    public static final int CHARGE_STOP_REASON_VCU_STOP = 5;
    public static final int CLTC_MODE = 2;
    public static final int CRUISE_CONTROL_STATUS_CONTROLLING = 2;
    public static final int CRUISE_CONTROL_STATUS_ERROR = 3;
    public static final int CRUISE_CONTROL_STATUS_OFF = 0;
    public static final int CRUISE_CONTROL_STATUS_ON = 1;
    private static final boolean DBG = false;
    public static final int DC_CHARGER_VOL_LOW_500V = 1;
    public static final int DC_CHARGER_VOL_LOW_750V = 2;
    public static final int DC_CHARGER_VOL_LOW_CONFIRM = 1;
    public static final int DC_CHARGER_VOL_LOW_NO_CONFIRM = 0;
    public static final int DC_CHARGER_VOL_NORMAL = 0;
    public static final int DISABLE_EXTREME_FAST_CHARGING = 2;
    public static final int DISCHARGE_ERROR_APPLIANCE = 2;
    public static final int DISCHARGE_ERROR_APPLI_AND_VEHICLE = 1;
    public static final int DISCHARGE_ERROR_NORMAL = 0;
    public static final int DISCHARGE_ERROR_VEHICLE = 3;
    public static final int DRIVE_MILE_INCREASE_OFF = 0;
    public static final int DRIVE_MILE_INCREASE_ON = 1;
    public static final int DRIVE_MODE_FB_ACC = 1;
    public static final int DRIVE_MODE_FB_AP_MODE = 6;
    @Deprecated
    public static final int DRIVE_MODE_FB_AUTO = 1;
    public static final int DRIVE_MODE_FB_AUTO_DRIVE = 5;
    public static final int DRIVE_MODE_FB_AUTO_PARK = 3;
    public static final int DRIVE_MODE_FB_CIP = 4;
    public static final int DRIVE_MODE_FB_MANUAL = 0;
    @Deprecated
    public static final int DRIVE_MODE_FB_NOT_MANUAL = 3;
    public static final int DRIVE_MODE_FB_REMOTE_CONTROL = 2;
    @Deprecated
    public static final int DRIVING_MODE_STATUS_ADAPTIVE = 14;
    public static final int DRIVING_MODE_STATUS_ANTISICKNESS_OFF = 8;
    public static final int DRIVING_MODE_STATUS_ANTISICKNESS_ON = 7;
    public static final int DRIVING_MODE_STATUS_COMFORT = 0;
    public static final int DRIVING_MODE_STATUS_COMFORT_V2 = 7;
    public static final int DRIVING_MODE_STATUS_ECO = 1;
    public static final int DRIVING_MODE_STATUS_ECO_PLUS_OFF = 6;
    public static final int DRIVING_MODE_STATUS_ECO_PLUS_ON = 5;
    @Deprecated
    public static final int DRIVING_MODE_STATUS_GEEK = 15;
    public static final int DRIVING_MODE_STATUS_NORMAL = 10;
    public static final int DRIVING_MODE_STATUS_NO_COMMAND = 12;
    public static final int DRIVING_MODE_STATUS_OFFROAD = 16;
    @Deprecated
    public static final int DRIVING_MODE_STATUS_RACING = 13;
    public static final int DRIVING_MODE_STATUS_SHOW_MODE_1 = 3;
    public static final int DRIVING_MODE_STATUS_SHOW_MODE_2 = 4;
    public static final int DRIVING_MODE_STATUS_SIMULATE_R_WHEEL_DRIVE = 9;
    public static final int DRIVING_MODE_STATUS_SPORT = 2;
    @Deprecated
    public static final int DRIVING_MODE_STATUS_USERDEFINE = 11;
    public static final int DRIVING_MODE_STATUS_XPEDAL_MODE = 5;
    @Deprecated
    public static final int DRIVING_STATUS_MODE_COMFORT = 0;
    @Deprecated
    public static final int DRIVING_STATUS_MODE_ECO = 1;
    @Deprecated
    public static final int DRIVING_STATUS_MODE_SPORT = 2;
    @Deprecated
    public static final int DRIVING_STATUS_SHOW_MODE_1 = 3;
    @Deprecated
    public static final int DRIVING_STATUS_SHOW_MODE_2 = 4;
    public static final int DYNAMIC_MODE = 3;
    public static final int ENABLE_EXTREME_FAST_CHARGING = 1;
    public static final int ENERGY_STATUS_RECOVERY_DEFAULT = 0;
    public static final int ENERGY_STATUS_RECOVERY_HIGH = 3;
    @Deprecated
    public static final int ENERGY_STATUS_RECOVERY_INTELLIGENT = 4;
    public static final int ENERGY_STATUS_RECOVERY_LOW = 1;
    public static final int ENERGY_STATUS_RECOVERY_MIDDLE = 2;
    private static final double EPSINON = 1.0E-6d;
    public static final int EV_SYSTEM_ERROR_LAMP_OFF = 0;
    public static final int EV_SYSTEM_ERROR_LAMP_ON = 1;
    public static final int EXHIBITION_MODE_STATUS_OFF = 0;
    public static final int EXHIBITION_MODE_STATUS_ON = 1;
    public static final int EXTREME_FAST_CHARGING_STATUS_ENTER_FAILURE = 3;
    public static final int EXTREME_FAST_CHARGING_STATUS_HEATING = 1;
    public static final int EXTREME_FAST_CHARGING_STATUS_PATTERNS_MAINTAINING = 2;
    public static final int EXTREME_FAST_CHARGING_STATUS_SIGN_OUT = 4;
    public static final int GEAR_LEVEL_D = 1;
    public static final int GEAR_LEVEL_INVALID = 0;
    public static final int GEAR_LEVEL_N = 2;
    public static final int GEAR_LEVEL_P = 4;
    public static final int GEAR_LEVEL_R = 3;
    public static final int ID_OBC_FAULT_PHASE_LOSS = 557847141;
    public static final int ID_VCU_2ND_OFF_RES = 557847144;
    public static final int ID_VCU_ACCCS_CUR = 559944268;
    public static final int ID_VCU_ACCCS_VOLT = 559944269;
    public static final int ID_VCU_ACCPEDAL_SIG = 557847064;
    public static final int ID_VCU_AC_CUR_MAX_CHG_CON = 557847195;
    public static final int ID_VCU_AC_INPUT_STATUS = 557847071;
    @Deprecated
    public static final int ID_VCU_AS_DRV_MODE_ST = 557858817;
    public static final int ID_VCU_AUTO_EASY_LOADING_SW = 557847193;
    public static final int ID_VCU_AVERAGE_VEHICLE_CONSUME = 559944219;
    public static final int ID_VCU_BATCOLD_DISP = 557847068;
    public static final int ID_VCU_BATTERY_MIN_TEMPERATURE = 559944228;
    public static final int ID_VCU_BATTERY_WARMING_STATUS = 557847050;
    public static final int ID_VCU_BATTSOC_SATE = 557847060;
    public static final int ID_VCU_BAT_BUMP_RECRD = 557847161;
    public static final int ID_VCU_BAT_CHRG = 557847053;
    public static final int ID_VCU_BAT_KEEP_TEMP_MODE = 557847123;
    public static final int ID_VCU_BAT_KEEP_TEMP_REQ = 557847124;
    public static final int ID_VCU_BMS_ACT_SOC = 559944332;
    public static final int ID_VCU_BMS_BATT_CURR = 559944327;
    public static final int ID_VCU_BMS_BATT_SOC_DISP = 559944336;
    public static final int ID_VCU_BMS_BATT_TEMP_AVG = 557847177;
    public static final int ID_VCU_BMS_BATT_VOLT = 557847176;
    @Deprecated
    public static final int ID_VCU_BMS_CHRG_ST = 557847178;
    public static final int ID_VCU_BMS_ERR = 557847120;
    public static final int ID_VCU_BMS_MAX_AVAIL_CHRG_PWR = 559944333;
    public static final int ID_VCU_BMS_MAX_AVAIL_DISCHRG_PWR = 559944334;
    public static final int ID_VCU_BMS_SOC_SLOW = 557847119;
    @SystemApi
    public static final int ID_VCU_BRAKE_SIG = 557847058;
    public static final int ID_VCU_BRKPEDAL_ST = 557847063;
    public static final int ID_VCU_CAR_STATIONARY_ST = 557847132;
    public static final int ID_VCU_CHARGE_BATTCAP = 557847055;
    public static final int ID_VCU_CHARGE_BATTCAP_FLOAT = 559944301;
    public static final int ID_VCU_CHARGE_COMPLETE_TIME = 557847084;
    public static final int ID_VCU_CHARGE_ERROR = 557847072;
    public static final int ID_VCU_CHARGE_GUN_STATUS = 557847080;
    @Deprecated
    public static final int ID_VCU_CHARGE_MODE = 1;
    public static final int ID_VCU_CHARGE_STATUS = 557847081;
    public static final int ID_VCU_CHGSPEED_SLOW_DSP = 557847069;
    public static final int ID_VCU_CHRG_DIFF = 557847187;
    public static final int ID_VCU_CHRG_GUN_CMD = 557847145;
    public static final int ID_VCU_CHRG_HV_LOAD_PWR = 559944338;
    public static final int ID_VCU_CHRG_PWR = 559944337;
    public static final int ID_VCU_COLD_WARNING_TIPS = 557847049;
    public static final int ID_VCU_CRUISECONTROL_STDISP = 557847118;
    @SystemApi
    public static final int ID_VCU_DBG_ERH_STSYSERRLVL = 557847070;
    @SystemApi
    public static final int ID_VCU_DBG_SUP_STSUP = 557847083;
    public static final int ID_VCU_DCCCS_CURR = 559944198;
    public static final int ID_VCU_DCCCS_SUMU = 559944199;
    public static final int ID_VCU_DCCH_VOL_LOW = 557847188;
    public static final int ID_VCU_DCC_CHRGSTOP_REA = 557847059;
    @Deprecated
    public static final int ID_VCU_DC_PRE_WARM_REQ = 557847147;
    public static final int ID_VCU_DC_PRE_WARM_ST = 557847148;
    public static final int ID_VCU_DC_PRE_WARM_SW = 557847146;
    public static final int ID_VCU_DEPOLARIZE_ST = 557847160;
    public static final int ID_VCU_DISCHARGE_BATTCAP = 557847054;
    public static final int ID_VCU_DISCHARGE_BATTCAP_FLOAT = 559944302;
    public static final int ID_VCU_DISCHARGE_ERROR_REASON = 557847199;
    public static final int ID_VCU_DRIMODE_FB = 557847079;
    public static final int ID_VCU_DRIVE_MILE_INCREASE = 557847126;
    public static final int ID_VCU_DRIVE_MODE_GET = 557847082;
    @Deprecated
    public static final int ID_VCU_DSTBAT_DISP_CLTC = 557847140;
    public static final int ID_VCU_DSTBAT_DISP_CLTC_FLOAT = 559944314;
    public static final int ID_VCU_DSTBAT_DISP_DYNAMIC = 559944335;
    public static final int ID_VCU_DSTBAT_DISP_FLOAT = 559944341;
    public static final int ID_VCU_DSTBAT_DISP_NEDC_FLOAT = 559944326;
    public static final int ID_VCU_DSTBAT_DISP_WLTP_FLOAT = 559944315;
    public static final int ID_VCU_EBSBATT_CURR = 559944214;
    public static final int ID_VCU_EBSBATT_VOLT = 559944213;
    public static final int ID_VCU_EBS_BATT_SOC = 557847078;
    public static final int ID_VCU_ELECTRICIT_PERCENT = 557847042;
    public static final int ID_VCU_ENERGY_RECYCLE = 557847041;
    public static final int ID_VCU_EVERR_LAMP_DSP = 557847086;
    public static final int ID_VCU_EVERR_MSG_DSP = 557847087;
    public static final int ID_VCU_EVSYS_READYST = 557847056;
    public static final int ID_VCU_EXHIB_MODE_SW = 557847137;
    public static final int ID_VCU_GEAR_LEVEL = 557847045;
    @Deprecated
    public static final int ID_VCU_GEAR_LEVER = 557847045;
    @Deprecated
    public static final int ID_VCU_HVAC_CONSUME = 559944226;
    public static final int ID_VCU_HV_CUTOFF = 557847121;
    public static final int ID_VCU_INTELLIGENT_HEAT_MANAGE = 557847088;
    public static final int ID_VCU_KEY_BATT_ST = 557847173;
    @Deprecated
    public static final int ID_VCU_MILEAGE_NUMBER = 557847057;
    @Deprecated
    public static final int ID_VCU_MOTOR_POWERMODE = 557847135;
    public static final int ID_VCU_NAVI_DEST_INFO = 554701424;
    public static final int ID_VCU_NAVI_DEST_INFO_REQ = 557847151;
    public static final int ID_VCU_NAVI_DEST_TYPE = 557847153;
    public static final int ID_VCU_NAVI_K_VALUE = 559944309;
    public static final int ID_VCU_NAVI_PATH_ID = 557847155;
    public static final int ID_VCU_NAVI_REMAIN_DISTANCE = 557847154;
    public static final int ID_VCU_NAVI_REMAIN_TIME = 557847158;
    public static final int ID_VCU_NAVI_TYPE = 557847156;
    public static final int ID_VCU_N_GEAR_WARNING_SW = 557847101;
    public static final int ID_VCU_OBC_ACCUR = 559944196;
    public static final int ID_VCU_OBC_ACVOLT = 559944195;
    @Deprecated
    public static final int ID_VCU_OBC_DCCUR = 559944196;
    @Deprecated
    public static final int ID_VCU_OBC_DCVOLT = 559944195;
    public static final int ID_VCU_OBC_SIDE_AC_CURRENT = 559944282;
    public static final int ID_VCU_OBC_SIDE_AC_VOLT = 559944281;
    public static final int ID_VCU_OBC_SIDE_AC_VOLT_ST = 557847131;
    public static final int ID_VCU_PGEAR_LIM_OFF_SW = 557847165;
    public static final int ID_VCU_PGEAR_VD = 557847065;
    public static final int ID_VCU_POWER_RESPONCE = 557847134;
    @Deprecated
    public static final int ID_VCU_QUICK_CHARGE = 2;
    public static final int ID_VCU_RANDIS_MODE = 557847127;
    public static final int ID_VCU_RAW_CAR_SPEED = 559944229;
    public static final int ID_VCU_REAL_GEAR_LEVEL = 557847100;
    public static final int ID_VCU_RESHEAT_MANATIME = 557847051;
    public static final int ID_VCU_SNOW_MODE_SW = 557847085;
    public static final int ID_VCU_SOH = 559944225;
    public static final int ID_VCU_SPD_UP_CHG_MODE = 557847122;
    public static final int ID_VCU_SPD_UP_CHG_REQ = 557847125;
    public static final int ID_VCU_SPECIAL_CARBIN_MODE = 557847143;
    public static final int ID_VCU_SPEC_DRIVE_MODE = 557847133;
    public static final int ID_VCU_SSA_SW = 557847164;
    public static final int ID_VCU_SUPER_CHRG_FLG = 557847166;
    public static final int ID_VCU_TRAILER_MODE_SW = 557847142;
    public static final int ID_VCU_V2L_POWERLIMIT = 557847052;
    public static final int ID_VCU_VEHELC_CONSP_100M = 559944218;
    public static final int ID_VCU_VEHELC_CONSP_AVG_100 = 559944344;
    public static final int ID_VCU_VEHELC_CONSP_AVG_20 = 559944343;
    public static final int ID_VCU_VEHELC_CONSP_AVG_2_5 = 559944342;
    public static final int ID_VCU_VIRTUAL_ACCPEDAL_SIG = 557847159;
    public static final int ID_VCU_VMC_RWS_SW = 557847196;
    public static final int ID_VCU_VMC_SYSTEM_ST = 557847198;
    public static final int ID_VCU_VMC_ZWALK_SW = 557847197;
    @Deprecated
    public static final int ID_VCU_WLTP_AVAILABLE_MILEAGE = 557847128;
    public static final int ID_VCU_XPEDAL_CTRL_MODE = 557847167;
    public static final int ID_VCU_XPEDAL_MODE_SW = 557847136;
    public static final int ID_VCU_XPORT_INTELL_CALC_20HZ = 560009859;
    public static final int ID_VCU_XPORT_INTELL_CALC_50HZ = 560009860;
    public static final int ID_VCU_XPORT_INTELL_CALC_CFG = 560009857;
    public static final int ID_VCU_XPORT_INTELL_CALC_FB = 560009858;
    public static final int ID_VCU_XSPORT_AWD_MODE_SW = 557847194;
    public static final int ID_VCU_XSPORT_MODE = 557847179;
    public static final int KEY_BATTERY_STATUS_EXCHANGE_KEY_BATTERY = 1;
    public static final int KEY_BATTERY_STATUS_INACTIVE = 0;
    @Deprecated
    public static final int MOTOR_POWER_NORMAL_MODE = 1;
    @Deprecated
    public static final int MOTOR_POWER_NO_COMMAND = 0;
    @Deprecated
    public static final int MOTOR_POWER_XPOWER_MODE = 2;
    public static final int NAVI_DEST_TYPE_EXTERNAL = 2;
    public static final int NAVI_DEST_TYPE_UNKNOWN = 0;
    public static final int NAVI_DEST_TYPE_XPENG = 1;
    public static final int NAVI_TYPE_CRUISE = 0;
    public static final int NAVI_TYPE_EXPLORE = 2;
    public static final int NAVI_TYPE_GUIDE = 1;
    public static final int NEDC_MODE = 0;
    public static final int NO_AC_INPUT = 0;
    public static final int N_GEAR_WARNING_SWITCH_OFF = 1;
    public static final int N_GEAR_WARNING_SWITCH_ON = 2;
    public static final int OBC_FAULT_PHASE_LOSS_STATUS_ERROR = 1;
    @Deprecated
    public static final int OBC_FAULT_PHASE_LOSS_STATUS_MISS_A_AND_B_OR_C_NOT_WORK = 4;
    @Deprecated
    public static final int OBC_FAULT_PHASE_LOSS_STATUS_MISS_A_NOT_WORK = 3;
    @Deprecated
    public static final int OBC_FAULT_PHASE_LOSS_STATUS_MISS_B_OR_C_3_3KW_WORK = 2;
    public static final int OBC_FAULT_PHASE_LOSS_STATUS_NO_ERROR = 0;
    public static final int PGEAR_INVALID = 0;
    public static final int PGEAR_VALID = 1;
    public static final int POWER_RESPONCE_FAST_MODE = 3;
    @Deprecated
    public static final int POWER_RESPONCE_INTELLIGENT_MODE = 6;
    public static final int POWER_RESPONCE_NO_COMMAND = 0;
    public static final int POWER_RESPONCE_SLOW_MODE = 2;
    public static final int POWER_RESPONCE_STANDARD_MODE = 1;
    public static final int POWER_RESPONCE_SUPERFAST_MODE = 5;
    public static final int POWER_RESPONCE_SUPERSLOW_MODE = 4;
    public static final int RESPONSE_ENERGY_RECOVERY_HIGH = 2;
    public static final int RESPONSE_ENERGY_RECOVERY_LOW = 0;
    public static final int RESPONSE_ENERGY_RECOVERY_MIDDLE = 1;
    public static final int SPECIAL_DRIVING_ADAPTIVE_MODE = 4;
    public static final int SPECIAL_DRIVING_GEEK_MODE = 5;
    public static final int SPECIAL_DRIVING_MUD_MODE = 1;
    public static final int SPECIAL_DRIVING_NO_COMMAND = 0;
    public static final int SPECIAL_DRIVING_RACING_MODE = 3;
    public static final int SPECIAL_DRIVING_XPOWER_MODE = 2;
    private static final String TAG = "CarVcuManager";
    public static final int TRAILER_MODE_STATUS_OFF = 0;
    public static final int TRAILER_MODE_STATUS_ON = 1;
    public static final int VCU_2ND_OFF_REQUEST_CANCEL = 0;
    public static final int VCU_2ND_OFF_REQUEST_OK = 1;
    public static final int VCU_2ND_OFF_RESPONCE_ACTIVE = 1;
    public static final int VCU_2ND_OFF_RESPONCE_INACTIVE = 0;
    public static final int VCU_BAT_BUMP_RECRD_ST_RECORD_FAILED = 2;
    public static final int VCU_BAT_BUMP_RECRD_ST_RECORD_OK = 1;
    public static final int VCU_BAT_BUMP_RECRD_ST_UPLOAD_FAILED = 4;
    public static final int VCU_BAT_BUMP_RECRD_ST_UPLOAD_OK = 3;
    @SystemApi
    public static final int VCU_BRAKE_LIGHT_OFF = 0;
    @SystemApi
    public static final int VCU_BRAKE_LIGHT_ON = 1;
    public static final int VCU_CHRG_GUN_LOCK = 1;
    public static final int VCU_CHRG_GUN_UNLOCK = 0;
    public static final int VCU_DC_PRE_WARM_COOL_EXIT1 = 19;
    public static final int VCU_DC_PRE_WARM_COOL_EXIT2 = 20;
    public static final int VCU_DC_PRE_WARM_COOL_EXIT3 = 21;
    public static final int VCU_DC_PRE_WARM_COOL_FAILURE = 31;
    public static final int VCU_DC_PRE_WARM_COOL_FINISH = 18;
    public static final int VCU_DC_PRE_WARM_DC_COOL_ONGOING = 17;
    public static final int VCU_DC_PRE_WARM_DC_HEAT_ONGOING = 1;
    public static final int VCU_DC_PRE_WARM_DEFAULT = 0;
    public static final int VCU_DC_PRE_WARM_HEAT_EXIT1 = 3;
    public static final int VCU_DC_PRE_WARM_HEAT_EXIT2 = 4;
    public static final int VCU_DC_PRE_WARM_HEAT_EXIT3 = 5;
    public static final int VCU_DC_PRE_WARM_HEAT_FAILURE = 15;
    public static final int VCU_DC_PRE_WARM_HEAT_FINISH = 2;
    @Deprecated
    public static final int VCU_DC_PRE_WARM_REQ_DISABLE = 0;
    @Deprecated
    public static final int VCU_DC_PRE_WARM_REQ_ENABLE = 1;
    @Deprecated
    public static final int VCU_DC_PRE_WARM_REQ_OFF = 0;
    @Deprecated
    public static final int VCU_DC_PRE_WARM_REQ_ON = 1;
    public static final int VCU_DC_PRE_WARM_SW_OFF = 0;
    public static final int VCU_DC_PRE_WARM_SW_ON = 1;
    public static final int VCU_DEPOLARIZE_STATUS_DEFAULT = 2;
    public static final int VCU_DEPOLARIZE_STATUS_OFF = 0;
    public static final int VCU_DEPOLARIZE_STATUS_ON = 1;
    public static final int VCU_EV_HIGH_VOL_ON = 1;
    public static final int VCU_EV_NOT_READY = 0;
    public static final int VCU_EV_READY = 2;
    public static final int VCU_SNOWMODE_OFF = 0;
    public static final int VCU_SNOWMODE_ON = 1;
    public static final int VCU_SPECIAL_CARBIN_MODE_ACTIVE = 1;
    public static final int VCU_SPECIAL_CARBIN_MODE_INACTIVE = 0;
    public static final int VCU_SSA_SW_NO_COMMAND = 2;
    public static final int VCU_SSA_SW_OFF = 0;
    public static final int VCU_SSA_SW_ON = 1;
    public static final int VCU_START_DISCHARGE = 1;
    public static final int VCU_STATUS_OFF = 0;
    public static final int VCU_STATUS_ON = 1;
    public static final int VCU_STOP_DISCHARGE = 2;
    public static final int VCU_SUPER_CHARGE_FLAG_DEFAULT = 0;
    public static final int VCU_SUPER_CHARGE_FLAG_SUPER_CHARGE = 1;
    public static final int VCU_XPEDAL_CTRL_MODE_CREEP = 0;
    public static final int VCU_XPEDAL_CTRL_MODE_HOLD = 2;
    public static final int VCU_XPEDAL_CTRL_MODE_NO_COMMAND = 3;
    public static final int VCU_XPEDAL_CTRL_MODE_ROLL = 1;
    public static final int VMS_STATUS_FAILED = 1;
    public static final int VMS_STATUS_NORMAL = 0;
    public static final int WLTP_MODE = 1;
    public static final int XPEDAL_MODE_STATUS_OFF = 0;
    public static final int XPEDAL_MODE_STATUS_ON = 1;
    public static final int XSPORT_MODE_STATUS_AI = 2;
    public static final int XSPORT_MODE_STATUS_BOOST = 3;
    public static final int XSPORT_MODE_STATUS_NO_COMMAND = 0;
    public static final int XSPORT_MODE_STATUS_RACER = 4;
    public static final int XSPORT_MODE_STATUS_TUNNING = 1;
    private final IXpVehicle mService;
    private final ArraySet<Integer> mVcuPropertyIds;

    /* loaded from: classes.dex */
    public interface CarVcuEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarVcuManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mVcuPropertyIds = new ArraySet<>(Arrays.asList(557847072, 557847080, 557847081, 557847057, 557847042, 557847049, 559944226, 557847050, 557847084, 557847071, 559944219, 559944225, 559944228, 557847082, 557847041, 557847045, 557847078, 557847083, 557847070, 557847051, 559944195, 559944196, 559944198, 559944199, 557847052, 557847053, 557847054, 557847055, 557847056, 557847060, 559944213, 559944214, 557847059, 557847058, 557847064, 557847063, 557847065, 559944218, 557847068, 557847069, 557847079, 559944229, 557847085, 557847086, 557847087, 557847088, 557847100, 557847101, 559944268, 559944269, 557847118, 557847119, 557847120, 557847121, 557847122, 557847123, 557847124, 557847125, 557847126, 557847127, 557847128, 559944281, 559944282, 557847131, 557847132, 557847133, 557847134, 557847136, 557847137, 557847140, 557847141, 557847142, 557847143, 557847145, 557847146, 557847148, 559944302, 559944301, 557847151, 554701424, 557847153, 557847154, 557847158, 557847155, 557847156, 559944309, 557847159, 557847160, 557847161, 559944314, 559944315, 559944335, 557847164, 557847165, 557847166, 557847167, 560009857, 560009858, 560009859, 560009860, 557847173, 559944326, 559944327, 557847176, 557847177, 557847179, 559944332, 559944333, 559944334, 559944336, 559944337, 559944338, 557847187, 557847188, 559944341, 559944342, 559944343, 559944344, 557847193, 557847194, 557847195, 557847196, 557847197, 557847198, 557847199));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mVcuPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_VCU_SERVICE;
    }

    @Deprecated
    private void startCharge(int chargeSoc) throws Exception {
        this.mService.startVcuCharge(chargeSoc);
    }

    @Deprecated
    private void stopAcCharge(int chargeSoc) throws Exception {
        this.mService.stopVcuAcCharge(chargeSoc);
    }

    @Deprecated
    private void stopDcCharge() throws Exception {
        this.mService.stopVcuDcCharge();
    }

    @Deprecated
    private void setBestCharge() throws Exception {
        this.mService.setVcuBestCharge();
    }

    @Deprecated
    private void setFullyCharge() throws Exception {
        this.mService.setVcuFullyCharge();
    }

    @Deprecated
    private void setChargeLimit(int limit) throws Exception {
        this.mService.setVcuChargeLimit(limit);
    }

    public int getChargingError() throws Exception {
        return this.mService.getVcuChargeError();
    }

    public int getChargingGunStatus() throws Exception {
        return this.mService.getVcuChargingPlugStatus();
    }

    public int getElectricityPercent() throws Exception {
        return this.mService.getVcuElectricQuantityPercent();
    }

    public int getColdWarningTips() throws Exception {
        return this.mService.getVcuBatteryCoolingState();
    }

    @Deprecated
    public float getHvacConsume() throws Exception {
        return this.mService.getVcuAcPowerConsume();
    }

    public int getBatteryWarmingStatus() throws Exception {
        return this.mService.getVcuBatteryWarmingStatus();
    }

    public int getChargeCompleteTime() throws Exception {
        return this.mService.getVcuChargeRemainingTime();
    }

    @Deprecated
    public int getAcInputStatus() throws Exception {
        return this.mService.getVcuAcInputStatus();
    }

    public float getAverageVehConsume() throws Exception {
        return this.mService.getVcuAvgVehiclePowerConsume();
    }

    @Deprecated
    private int getChargeMaxSoc() throws Exception {
        return this.mService.getVcuChargeMode();
    }

    public float getSoh() throws Exception {
        return this.mService.getVcuBatteryWastageStatus();
    }

    private int getSocLimitMaxMileage(int socLimit) throws Exception {
        return 0;
    }

    public float getBatteryMinTemperature() throws Exception {
        return this.mService.getVcuBatteryMinTemperature();
    }

    public int getDrivingMode() throws Exception {
        return this.mService.getVcuDrivingMode();
    }

    public void setDrivingMode(int mode) throws Exception {
        this.mService.setDrivingMode(mode);
    }

    public int getEnergyRecycleLevel() throws Exception {
        return this.mService.getVcuEnergyRecycleLevel();
    }

    public void setEnergyRecycleLevel(int level) throws Exception {
        this.mService.setVcuEnergyRecycleLevel(level);
    }

    @Deprecated
    public int getStallState() throws Exception {
        return this.mService.getVcuGearState();
    }

    public int getDisplayGearLevel() throws Exception {
        return this.mService.getVcuGearState();
    }

    public int getRealGearLevel() throws Exception {
        return this.mService.getVcuRealGearLevel();
    }

    @Deprecated
    public int getAvalibleDrivingDistance() throws Exception {
        return this.mService.getVcuAvalibleDrivingDistance();
    }

    public float getAvalibleDrivingDistanceFloat() throws Exception {
        return this.mService.getVcuAvalibleDrivingDistanceFloat();
    }

    public float getNedcAvalibleDrivingDistanceFloat() throws Exception {
        return this.mService.getVcuNedcAvalibleDrivingDistanceFloat();
    }

    public int getEbsBatterySoc() throws Exception {
        return this.mService.getVcuEbsBatterySoc();
    }

    @SystemApi
    public int getVcuSupDebugInfo() throws Exception {
        return this.mService.getVcuSupDebugInfo();
    }

    @SystemApi
    public int getVcuErhDebugInfo() throws Exception {
        return this.mService.getVcuErhDebugInfo();
    }

    public int getResHeatManaTime() throws Exception {
        return this.mService.getVcuResHeatManaTime();
    }

    public float getSlowChargeAcVolt() throws Exception {
        return this.mService.getVcuAcChargeVolt();
    }

    public float getSlowChargeAcVoltAfterVoltBoosted() throws Exception {
        return this.mService.getVcuAcChargeVoltAfterVoltBoosted();
    }

    public float getSlowChargeAcCur() throws Exception {
        return this.mService.getVcuAcChargeCur();
    }

    public float getFastChargeDcCur() throws Exception {
        return this.mService.getVcuDcChargeCur();
    }

    public float getSlowChargeAcCurAfterVoltBoosted() throws Exception {
        return this.mService.getVcuAcChargeCurAfterVoltBoosted();
    }

    public float getFastChargeDcVolt() throws Exception {
        return this.mService.getVcuDcChargeVolt();
    }

    public void setDisChargeLimit(int percent) throws Exception {
        this.mService.setVcuDisChargeLimit(percent);
    }

    public int getDisCargeLimit() throws Exception {
        return this.mService.getVcuDisCargeLimit();
    }

    public void setDisChargeSw(int status) throws Exception {
        this.mService.setVcuDisChargeEnabled(status);
    }

    public int getChargeStatus() throws Exception {
        return this.mService.getVcuChargeStatus();
    }

    public int getDischargeSoc() throws Exception {
        return this.mService.getVcuDischargeQuantity();
    }

    public int getChargeSoc() throws Exception {
        return this.mService.getVcuChargeSocQuantity();
    }

    public int getEvSysReady() throws Exception {
        return this.mService.getVcuEvsysReadyState();
    }

    public int getBatteryPercent() throws Exception {
        return this.mService.getVcuBatteryLevelPercent();
    }

    public float getBatteryVolt() throws Exception {
        return this.mService.getVcuBatteryVolt();
    }

    public float getBatteryCurr() throws Exception {
        return this.mService.getVcuBatteryCur();
    }

    public int getChargeStopReason() throws Exception {
        return this.mService.getVcuStopChargeReason();
    }

    @SystemApi
    public void setBrakeLightOnOff(int status) throws Exception {
        this.mService.setVcuBrakeLightOn(status);
    }

    public int getBrakeLightOnOff() throws Exception {
        return this.mService.getVcuBrakeLightOnOffStatus();
    }

    public int getBreakPedalStatus() throws Exception {
        return this.mService.getVcuBreakPedalStatus();
    }

    public int getAccPedalStatus() throws Exception {
        return this.mService.getVcuAccPedalStatus();
    }

    public int getPGearValid() throws Exception {
        return this.mService.isVcuParkingGearValid();
    }

    public float getVehLast100mConsume() throws Exception {
        return this.mService.getVcuVehLast100mConsume();
    }

    public int getBatTooCold() throws Exception {
        return this.mService.isVcuBatteryCold();
    }

    public int getChgSpeedSlow() throws Exception {
        return this.mService.isVcuChargeSpeedSlow();
    }

    public float getRawCarSpeed() throws Exception {
        return this.mService.getVcuRawCarSpeed();
    }

    public int getPureDriveModeFeedback() throws Exception {
        return this.mService.getVcuPureDriveModeFeedback();
    }

    public int getVcuSnowMode() throws Exception {
        return this.mService.getVcuSnowMode();
    }

    public void setVcuSnowMode(int mode) throws Exception {
        this.mService.setVcuSnowMode(mode);
    }

    public int getEvSystemErrorLampStatus() throws Exception {
        return this.mService.getVcuEvErrLampDsp();
    }

    public int getBatteryOverheatingStatus() throws Exception {
        return this.mService.getVcuEvErrMsgDsp();
    }

    public void setExtremeFastChargingMode(int enable) throws Exception {
        this.mService.setVcuExtremeFastChargingMode(enable);
    }

    public int getExtremeFastChargingSt() throws Exception {
        return this.mService.getVcuExtremeFastChargingSt();
    }

    public void setNGearWarningSwitch(int enable) throws Exception {
        this.mService.setVcuNGearWarningSwitch(enable);
    }

    public int getNGearWarningSwitchStatus() throws Exception {
        return this.mService.getVcuNGearWarningSwitchStatus();
    }

    public int getCruiseControlStatus() throws Exception {
        return this.mService.getVcuCruiseControlStatus();
    }

    public int getBmsScoIsLowStatus() throws Exception {
        return this.mService.getBmsScoIsLowStatus();
    }

    public int getBmsIsErrorStatus() throws Exception {
        return this.mService.getBmsIsErrorStatus();
    }

    public int getIsHvCutOffStatus() throws Exception {
        return this.mService.getIsHvCutOffStatus();
    }

    public void setBatteryKeepTempSwitch(int status) throws Exception {
        setIntProperty(557847124, 0, status);
    }

    public int getBatteryKeepTempMode() throws Exception {
        return getIntProperty(557847123, 0);
    }

    public void setSpeedUpChargeSwitch(int status) throws Exception {
        setIntProperty(557847125, 0, status);
    }

    public int getSpeedUpChargeMode() throws Exception {
        return getIntProperty(557847122, 0);
    }

    public void setDriveMileIncreaseSwitch(int sw) throws Exception {
        this.mService.setVcuDriveMileIncreaseSwitch(sw);
    }

    public int getDriveMileIncreaseStatus() throws Exception {
        return this.mService.getVcuDriveMileIncreaseStatus();
    }

    public void setEnduranceMileageMode(int mode) throws Exception {
        this.mService.setVcuEnduranceMileageMode(mode);
    }

    public int getEnduranceMileageMode() throws Exception {
        return this.mService.getVcuEnduranceMileageMode();
    }

    @Deprecated
    public int getWltpAvailableDrivingDistance() throws Exception {
        return this.mService.getVcuWltpAvailableDrivingDistance();
    }

    public float getWltpAvailableDrivingDistanceFloat() throws Exception {
        return this.mService.getVcuWltpAvailableDrivingDistanceFloat();
    }

    @Deprecated
    public int getCltcAvailableDrivingDistance() throws Exception {
        return this.mService.getVcuCltcAvailableDrivingDistance();
    }

    public float getCltcAvailableDrivingDistanceFloat() throws Exception {
        return this.mService.getVcuCltcAvailableDrivingDistanceFloat();
    }

    public float getDynamicAvailableDrivingDistance() throws Exception {
        return this.mService.getVcuDynamicAvailableDrivingDistance();
    }

    public float getObcAcVoltage() throws Exception {
        return this.mService.getVcuObcAcVoltage();
    }

    public float getObcAcCurrent() throws Exception {
        return this.mService.getVcuObcAcCurrent();
    }

    public int getObcAcVoltageStatus() throws Exception {
        return this.mService.getVcuObcAcVoltageStatus();
    }

    public int getCarStationaryStatus() throws Exception {
        return this.mService.getVcuCarStationaryStatus();
    }

    public int getSpecialDrivingMode() throws Exception {
        return this.mService.getVcuSpecialDrivingMode();
    }

    public void setSpecialDrivingMode(int mode) throws Exception {
        this.mService.setVcuSpecialDrivingMode(mode);
    }

    public int getPowerResponseMode() throws Exception {
        return this.mService.getVcuPowerResponseMode();
    }

    public void setPowerResponseMode(int mode) throws Exception {
        this.mService.setVcuPowerResponseMode(mode);
    }

    @Deprecated
    public int getMotorPowerMode() throws Exception {
        return this.mService.getVcuMotorPowerMode();
    }

    @Deprecated
    public void setMotorPowerMode(int mode) throws Exception {
        this.mService.setVcuMotorPowerMode(mode);
    }

    public int getXpedalModeSwitchStatus() throws Exception {
        return this.mService.getVcuXpedalModeSwitchStatus();
    }

    public void setXpedalModeSwitchStatus(int enable) throws Exception {
        this.mService.setVcuXpedalModeSwitchStatus(enable);
    }

    public int getExhibModeSwitchStatus() throws Exception {
        return this.mService.getVcuExhibModeSwitchStatus();
    }

    public int getObcFaultPhaseLossStatus() throws Exception {
        return this.mService.getVcuObcFaultPhaseLossStatus();
    }

    public void setTrailerModeSwitchStatus(int enable) throws Exception {
        this.mService.setVcuTrailerModeSwitchStatus(enable);
    }

    public int getTrailerModeSwitchStatus() throws Exception {
        return this.mService.getVcuTrailerModeSwitchStatus();
    }

    public void setSpecialCarbinModeSwitch(int enable) throws Exception {
        this.mService.setVcuSpecialCarbinModeSwitch(enable);
    }

    public void setSecondaryPowerOffRequest(int enable) throws Exception {
        this.mService.setVcuSecondaryPowerOffRequest(enable);
    }

    public int getSecondaryPowerOffResponce() throws Exception {
        return this.mService.getVcuSecondaryPowerOffResponce();
    }

    public void setCdcuChargeGunCommand(int command) throws Exception {
        this.mService.setVcuCdcuChargeGunCommand(command);
    }

    public int getCdcuChargeGunStatus() throws Exception {
        return this.mService.getVcuCdcuChargeGunStatus();
    }

    public void setDcPreWarmSwitchStatus(int enable) throws Exception {
        this.mService.setVcuDcPreWarmSwitchStatus(enable);
    }

    public int getDcPreWarmSwitchStatus() throws Exception {
        return this.mService.getVcuDcPreWarmSwitchStatus();
    }

    @Deprecated
    public void setDcPreWarmRequestStatus(int enable) throws Exception {
    }

    @Deprecated
    public int getDcPreWarmRequestStatus() throws Exception {
        return 0;
    }

    public int getDcPreWarmInStatus() throws Exception {
        return this.mService.getVcuDcPreWarmInStatus();
    }

    public float getDischargeSocFloat() throws Exception {
        return this.mService.getVcuDischargeQuantityFloat();
    }

    public float getChargeSocFloat() throws Exception {
        return this.mService.getVcuChargeSocQuantityFloat();
    }

    public void setNaviDestInfo(String info) throws Exception {
        this.mService.setVcuNaviDestInfo(info);
    }

    public void setNaviDestType(int type) throws Exception {
        this.mService.setVcuNaviDestType(type);
    }

    public void setNaviRemainDistance(int distance) throws Exception {
        this.mService.setVcuNaviRemainDistance(distance);
    }

    public void setNaviRemainTime(int minutes) throws Exception {
        this.mService.setVcuNaviRemainTime(minutes);
    }

    public void setNaviPathId(int id) throws Exception {
        this.mService.setVcuNaviPathId(id);
    }

    public void setNaviType(int type) throws Exception {
        this.mService.setVcuNaviType(type);
    }

    public void setNaviKValue(float k) throws Exception {
        this.mService.setVcuNaviKValue(k);
    }

    public int getVirtualAccPedalStatus() throws Exception {
        return this.mService.getVcuVirtualAccPedalStatus();
    }

    @Deprecated
    public int getAsDriveModeStatus() throws Exception {
        return this.mService.getVcuAsDriveModeStatus();
    }

    public int getDepolarizeStatus() throws Exception {
        return this.mService.getVcuDepolarizeStatus();
    }

    public void setBatBumpRecrdStatus(int status) throws Exception {
        this.mService.setVcuBatBumpRecrdStatus(status);
    }

    public int getBatBumpRecrdRequest() throws Exception {
        return this.mService.getVcuBatBumpRecrdRequest();
    }

    public void setSsaSwitchStatus(int sw) throws Exception {
        this.mService.setVcuSsaSwitchStatus(sw);
    }

    public int getSsaSwitchStatus() throws Exception {
        return this.mService.getVcuSsaSwitchStatus();
    }

    public void setPGearLimOffSwitchStatus(int sw) throws Exception {
        this.mService.setVcuPGearLimOffSwitchStatus(sw);
    }

    public int getSuperChargeFlag() throws Exception {
        return this.mService.getVcuSuperChargeFlag();
    }

    public void setXpedalCtrlMode(int mode) throws Exception {
        this.mService.setVcuXpedalCtrlMode(mode);
    }

    public int getXpedalCtrlMode() throws Exception {
        return this.mService.getVcuXpedalCtrlMode();
    }

    public void setXPortIntellCalcCfg(float[] cfg) throws Exception {
        this.mService.setVcuXPortIntellCalcCfg(cfg);
    }

    public float[] getXPortIntellCalcCfg() throws Exception {
        return this.mService.getVcuXPortIntellCalcCfg();
    }

    @Deprecated
    public float[] getXPortIntellCalcCfg20Hz() throws Exception {
        return this.mService.getVcuXPortIntellCalcCfg20Hz();
    }

    public float[] getXPortIntellCalcCfg50Hz() throws Exception {
        return this.mService.getVcuXPortIntellCalcCfg50Hz();
    }

    public int getKeyBatteryStatus() throws Exception {
        return this.mService.getVcuKeyBatteryStatus();
    }

    public float getBmsBatteryCurrent() throws Exception {
        return this.mService.getVcuBmsBatteryCurrent();
    }

    public int getBmsBatteryVoltage() throws Exception {
        return this.mService.getVcuBmsBatteryVoltage();
    }

    public int getBmsBatteryAverageTemperature() throws Exception {
        return this.mService.getVcuBmsBatteryAverageTemperature();
    }

    @Deprecated
    public int getBmsChargeStatus() throws Exception {
        return this.mService.getVcuBmsChargeStatus();
    }

    public void setXsportMode(int mode) throws Exception {
        this.mService.setVcuXsportMode(mode);
    }

    public int getXsportMode() throws Exception {
        return this.mService.getVcuXsportMode();
    }

    public float getBmsActualSocValue() throws Exception {
        return this.mService.getVcuBmsActualSocValue();
    }

    public float getBmsMaximumAvailChargePower() throws Exception {
        return this.mService.getVcuBmsMaximumAvailChargePower();
    }

    public float getBmsMaximumAvailDischargePower() throws Exception {
        return this.mService.getVcuBmsMaximumAvailDischargePower();
    }

    public float getBmsCurrentBatterySocDisp() throws Exception {
        return this.mService.getVcuBmsCurrentBatterySocDisp();
    }

    public float getChargeDischargePower() throws Exception {
        return this.mService.getVcuChargeDischargePower();
    }

    public float getChargeHighVoltageLoadPower() throws Exception {
        return this.mService.getVcuChargeHighVoltageLoadPower();
    }

    public int getSuperChargeDiffIncreaseRange() throws Exception {
        return this.mService.getVcuSuperChargeDiffIncreaseRange();
    }

    public int getChargerLowVolSt() throws Exception {
        return this.mService.getVcuChargerLowVolSt();
    }

    public void setDCChargerLowVolDiag(int ack) throws Exception {
        this.mService.setVcuChargerLowVolDiag(ack);
    }

    public float getLastTwoPointFiveKmAverageVehConsume() throws Exception {
        return this.mService.getVcuLastTwoPointFiveKmAverageVehConsume();
    }

    public float getLastTwentyKmAverageVehConsume() throws Exception {
        return this.mService.getVcuLastTwentyKmAverageVehConsume();
    }

    public float getLastHundredKmAverageVehConsume() throws Exception {
        return this.mService.getVcuLastHundredKmAverageVehConsume();
    }

    public void setAutoEasyLoadingSwitchStatus(int sw) throws Exception {
        this.mService.setVcuAutoEasyLoadingSwitchStatus(sw);
    }

    public int getAutoEasyLoadingSwitchStatus() throws Exception {
        return this.mService.getVcuAutoEasyLoadingSwitchStatus();
    }

    public void setAWDModeSw(int mode) throws Exception {
        this.mService.setVCUAWDModeSw(mode);
    }

    public int getAWDModeSw() throws Exception {
        return this.mService.getVCUAWDModeSw();
    }

    public void setAcChargCurrentMaxLimitedValue(int value) throws Exception {
        this.mService.setVcuAcChargCurrentMaxLimitedValue(value);
    }

    public float getAcChargCurrentMaxLimitedValue() throws Exception {
        return this.mService.getVcuAcChargCurrentMaxLimitedValue();
    }

    public void setVMCRwsSwitch(int sw) throws Exception {
        this.mService.setVMCRwsSwitch(sw);
    }

    public int getVMCRwsSwitchState() throws Exception {
        return this.mService.getVMCRwsSwitchState();
    }

    public void setVMCZWalkModeSwitch(int sw) throws Exception {
        this.mService.setVMCZWalkModeSwitch(sw);
    }

    public int getVMCZWalkModeState() throws Exception {
        return this.mService.getVMCZWalkModeState();
    }

    public int getVMCSystemState() throws Exception {
        return this.mService.getVMCSystemState();
    }

    public int getV2LDischargeErrorReason() throws Exception {
        return this.mService.getV2LDischargeErrorReason();
    }
}
