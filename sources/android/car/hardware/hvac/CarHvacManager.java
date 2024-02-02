package android.car.hardware.hvac;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.hardware.CarEcuManager;
import android.car.hardware.XpVehicle.IXpVehicle;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class CarHvacManager extends CarEcuManager {
    public static final int CO_CONCENTRATION_HIGH = 2;
    public static final int CO_CONCENTRATION_LOW = 1;
    public static final int CO_CONCENTRATION_NO_VALUE = 0;
    private static final boolean DBG = false;
    @SystemApi
    public static final int FAN_DIRECTION_DEFROST = 4;
    @SystemApi
    public static final int FAN_DIRECTION_FACE = 1;
    @SystemApi
    public static final int FAN_DIRECTION_FLOOR = 2;
    @SystemApi
    public static final int FAN_POSITION_DEFROST = 4;
    @SystemApi
    public static final int FAN_POSITION_DEFROST_AND_FLOOR = 5;
    @SystemApi
    public static final int FAN_POSITION_FACE = 1;
    @SystemApi
    public static final int FAN_POSITION_FACE_AND_FLOOR = 3;
    @SystemApi
    public static final int FAN_POSITION_FLOOR = 2;
    public static final int HVAC_AC_ON = 1;
    public static final int HVAC_AIR_QUALITY_LEVEL_0_PERCENT = 0;
    public static final int HVAC_AIR_QUALITY_LEVEL_100_PERCENT = 10;
    public static final int HVAC_AIR_QUALITY_LEVEL_10_PERCENT = 1;
    public static final int HVAC_AIR_QUALITY_LEVEL_20_PERCENT = 2;
    public static final int HVAC_AIR_QUALITY_LEVEL_30_PERCENT = 3;
    public static final int HVAC_AIR_QUALITY_LEVEL_40_PERCENT = 5;
    public static final int HVAC_AIR_QUALITY_LEVEL_50_PERCENT = 5;
    public static final int HVAC_AIR_QUALITY_LEVEL_60_PERCENT = 6;
    public static final int HVAC_AIR_QUALITY_LEVEL_70_PERCENT = 7;
    public static final int HVAC_AIR_QUALITY_LEVEL_80_PERCENT = 8;
    public static final int HVAC_AIR_QUALITY_LEVEL_90_PERCENT = 9;
    public static final int HVAC_AIR_QUALITY_LEVEL_ERROR = 15;
    public static final int HVAC_AIR_QUALITY_LEVEL_INIT = 14;
    public static final int HVAC_AIR_QUALITY_LEVEL_NOT_MOUNTED = 13;
    public static final int HVAC_AIR_QUALITY_OUTSIDE_BAD = 1;
    public static final int HVAC_AIR_QUALITY_OUTSIDE_GOOD = 0;
    public static final int HVAC_AQS_SENSITIVITY_HIGHT = 3;
    public static final int HVAC_AQS_SENSITIVITY_LOW = 1;
    public static final int HVAC_AQS_SENSITIVITY_MIDDLE = 2;
    public static final int HVAC_AUTO_AC_HEAT_NATURE_ON = 4;
    public static final int HVAC_AUTO_BLOW_LEVEL_1 = 1;
    public static final int HVAC_AUTO_BLOW_LEVEL_2 = 2;
    public static final int HVAC_AUTO_BLOW_LEVEL_3 = 3;
    @Deprecated
    public static final int HVAC_BLOW_WIND_LEVEL_MAX = 10;
    @Deprecated
    public static final int HVAC_BLOW_WIND_LEVEL_MIN = 1;
    public static final int HVAC_BLOW_WIND_MODE_FACE = 2;
    public static final int HVAC_BLOW_WIND_MODE_FOOT = 3;
    public static final int HVAC_BLOW_WIND_MODE_WINDSHIELD = 1;
    public static final int HVAC_CIRCULATION_10M = 1;
    public static final int HVAC_CIRCULATION_15M = 2;
    public static final int HVAC_CIRCULATION_20M = 3;
    public static final int HVAC_CIRCULATION_ERROR = 7;
    public static final int HVAC_CIRCULATION_OFF = 6;
    public static final int HVAC_CIRCULATION_STATUS_AUTO = 6;
    public static final int HVAC_CIRCULATION_STATUS_ERROR = 7;
    public static final int HVAC_CIRCULATION_STATUS_INNER = 1;
    public static final int HVAC_CIRCULATION_STATUS_INVALID = 0;
    public static final int HVAC_CIRCULATION_STATUS_OUTSIDE = 2;
    public static final int HVAC_EAV_POSITION_1 = 1;
    public static final int HVAC_EAV_POSITION_2 = 2;
    public static final int HVAC_EAV_POSITION_3 = 3;
    public static final int HVAC_EAV_POSITION_4 = 4;
    public static final int HVAC_EAV_POSITION_5 = 5;
    public static final int HVAC_EAV_POSITION_6 = 6;
    public static final int HVAC_EAV_POSITION_7 = 7;
    public static final int HVAC_EAV_POSITION_ERROR = 15;
    public static final int HVAC_EAV_POSITION_OFF = 14;
    public static final int HVAC_EAV_POSITION_OPEN = 13;
    public static final int HVAC_EAV_WINMODE_AVOID = 4;
    public static final int HVAC_EAV_WINMODE_FREE = 3;
    public static final int HVAC_EAV_WINMODE_INTELLIGENT = 5;
    public static final int HVAC_EAV_WINMODE_MIRROR = 2;
    public static final int HVAC_EAV_WINMODE_NODISPLAY = 6;
    public static final int HVAC_EAV_WINMODE_SINGLE = 1;
    public static final int HVAC_FROGING_RISK_HIGH = 1;
    public static final int HVAC_FROGING_RISK_NORMAL = 0;
    public static final int HVAC_GET_BLOW_WIND_MODE_AUTOMODE = 14;
    public static final int HVAC_GET_BLOW_WIND_MODE_AUTO_FDEFROST = 7;
    @Deprecated
    public static final int HVAC_GET_BLOW_WIND_MODE_AUTO_FOOT_DEFROST = 7;
    public static final int HVAC_GET_BLOW_WIND_MODE_ERROR = 15;
    public static final int HVAC_GET_BLOW_WIND_MODE_FACE = 1;
    public static final int HVAC_GET_BLOW_WIND_MODE_FACE_FOOT = 2;
    public static final int HVAC_GET_BLOW_WIND_MODE_FACE_FOOT_WINDSHIELD = 9;
    public static final int HVAC_GET_BLOW_WIND_MODE_FACE_WINDSHIELD = 8;
    public static final int HVAC_GET_BLOW_WIND_MODE_FDEFROST = 5;
    public static final int HVAC_GET_BLOW_WIND_MODE_FOOT = 3;
    @Deprecated
    public static final int HVAC_GET_BLOW_WIND_MODE_FOOT_DEFROST = 5;
    public static final int HVAC_GET_BLOW_WIND_MODE_FOOT_WINDSHIELD = 4;
    public static final int HVAC_GET_BLOW_WIND_MODE_WINDSHIELD = 6;
    public static final int HVAC_HEAT_ON = 2;
    public static final int HVAC_LEFT_SYNC_ON = 1;
    public static final int HVAC_MACHINE_STATE_SWITCH_BUSY = 3;
    public static final int HVAC_MACHINE_STATE_SWITCH_IDEL = 0;
    public static final int HVAC_MACHINE_STATE_SWITCH_LOCAL = 1;
    public static final int HVAC_MACHINE_STATE_SWITCH_REMOTE = 2;
    public static final int HVAC_NATURE_WIND_ON = 3;
    public static final int HVAC_PM25_LEVEL_BAD = 4;
    public static final int HVAC_PM25_LEVEL_BAD_SERIOUS = 6;
    public static final int HVAC_PM25_LEVEL_EXCELLENT = 1;
    public static final int HVAC_PM25_LEVEL_INVALID = 0;
    public static final int HVAC_PM25_LEVEL_MIDDLE = 3;
    public static final int HVAC_PM25_LEVEL_SERIOUS = 5;
    public static final int HVAC_PM25_LEVEL_WELL = 2;
    public static final int HVAC_RIGHT_SYNC_ON = 2;
    public static final int HVAC_SET_BLOW_WIND_MODE_FACE = 1;
    public static final int HVAC_SET_BLOW_WIND_MODE_FACE_FOOT = 2;
    public static final int HVAC_SET_BLOW_WIND_MODE_FACE_FOOT_WINDSHIELD = 7;
    public static final int HVAC_SET_BLOW_WIND_MODE_FACE_WINDSHIELD = 5;
    public static final int HVAC_SET_BLOW_WIND_MODE_FOOT = 3;
    public static final int HVAC_SET_BLOW_WIND_MODE_FOOT_WINDSHIELD = 4;
    public static final int HVAC_SET_BLOW_WIND_MODE_WINDSHIELD = 6;
    public static final int HVAC_SFS_CHANNEL_RESET_NO_ACTION = 0;
    public static final int HVAC_SFS_CHANNEL_RESET_REQUEST = 1;
    public static final int HVAC_STATUS_AC_OFF = 0;
    public static final int HVAC_STATUS_AC_ON = 1;
    public static final int HVAC_STATUS_AUTO = 1;
    public static final int HVAC_STATUS_AUTO_AC_HEAT_NATURE_OFF = 6;
    public static final int HVAC_STATUS_AUTO_AC_HEAT_NATURE_ON = 7;
    public static final int HVAC_STATUS_ERROR = 2;
    public static final int HVAC_STATUS_HEAT_OFF = 2;
    public static final int HVAC_STATUS_HEAT_ON = 3;
    public static final int HVAC_STATUS_MANUAL = 0;
    public static final int HVAC_STATUS_NATURE_WIND_OFF = 4;
    public static final int HVAC_STATUS_NATURE_WIND_ON = 5;
    public static final int HVAC_STATUS_OFF = 0;
    public static final int HVAC_STATUS_ON = 1;
    public static final int HVAC_STATUS_PTC_OFF = 1;
    public static final int HVAC_STATUS_PTC_ON = 2;
    public static final int HVAC_STATUS_SELF_DRY_OFF = 1;
    public static final int HVAC_STATUS_SELF_DRY_ON = 2;
    public static final int HVAC_SYNC_MODE_OFF = 3;
    public static final int HVAC_SYNC_NO_OPERATION = 0;
    public static final long HVAC_VEHICLE_SERVICE_REQ_CDU = 16777217;
    public static final int HVAC_WINDCOLOR_COLD = 2;
    public static final int HVAC_WINDCOLOR_ERROR = 7;
    public static final int HVAC_WINDCOLOR_HOT = 3;
    public static final int HVAC_WINDCOLOR_INVALID = 0;
    public static final int HVAC_WINDCOLOR_NATURE = 1;
    public static final int HVAC_WIND_MODE_INVALID = 0;
    public static final int HVAC_WIND_OFF = 224;
    public static final int HVAC_WIND_ON = 225;
    public static final int HVAC_WIND_SPEED_1 = 1;
    public static final int HVAC_WIND_SPEED_10 = 10;
    public static final int HVAC_WIND_SPEED_2 = 2;
    public static final int HVAC_WIND_SPEED_3 = 3;
    public static final int HVAC_WIND_SPEED_4 = 4;
    public static final int HVAC_WIND_SPEED_5 = 5;
    public static final int HVAC_WIND_SPEED_6 = 6;
    public static final int HVAC_WIND_SPEED_7 = 7;
    public static final int HVAC_WIND_SPEED_8 = 8;
    public static final int HVAC_WIND_SPEED_9 = 9;
    public static final int HVAC_WIND_SPEED_AUTO = 14;
    public static final int HVAC_WIND_SPEED_ERROR = 15;
    public static final int HVAC_WIND_SPEED_OFF = 0;
    public static final int ID_ENV_OUTSIDE_TEMPERATURE = 291505923;
    public static final int ID_HAVC_COMPRESSOR_COMSUME_PWR = 557849158;
    public static final int ID_HAVC_HVH_COMSUME_PWR = 557849159;
    @SystemApi
    public static final int ID_HVAC_820A_COOLING_REQTEMP = 559946274;
    public static final int ID_HVAC_AC_CTRL_TYPE = 557849140;
    public static final int ID_HVAC_AFTER_BLOW = 557849132;
    public static final int ID_HVAC_AIRDISTRIBUTION_AUTO_ST = 557849139;
    public static final int ID_HVAC_AIRINTAKE_AUTO_ST = 557849137;
    public static final int ID_HVAC_AIR_CIRCULATION_TYPE = 557849142;
    @SystemApi
    public static final int ID_HVAC_AMP_COOLING_REQTEMP = 559946275;
    @SystemApi
    public static final int ID_HVAC_AMP_TEMPRISE_SPEED = 557849124;
    public static final int ID_HVAC_AQS = 557849112;
    public static final int ID_HVAC_AQSSENSITIVITY = 557849113;
    public static final int ID_HVAC_AUTO_DEFOG_SET = 557849149;
    public static final int ID_HVAC_AUTO_DEFOG_WORK_ST = 557849162;
    public static final int ID_HVAC_BLOWER_CTRL_TYPE = 557849141;
    public static final int ID_HVAC_BLOWER_MODE = 557849121;
    public static final int ID_HVAC_CIRCULATION_PERIOD_SET = 557849119;
    public static final int ID_HVAC_CO_CONST = 557849151;
    public static final int ID_HVAC_DEODORIZE = 557849146;
    public static final int ID_HVAC_DISINF_SW = 557849152;
    public static final int ID_HVAC_DRIVERSEAT_TEMP_DOWN = 557849098;
    public static final int ID_HVAC_DRIVERSEAT_TEMP_UP = 557849097;
    public static final int ID_HVAC_DRV_LEFTHORZ = 557849104;
    public static final int ID_HVAC_DRV_LEFTVERT = 557849105;
    public static final int ID_HVAC_DRV_RIGHTHORZ = 557849106;
    public static final int ID_HVAC_DRV_RIGHTVERT = 557849107;
    public static final int ID_HVAC_DRV_WINDMODE = 557849116;
    public static final int ID_HVAC_EAVSWEEP_WIND = 557849114;
    public static final int ID_HVAC_ECON = 557849115;
    public static final int ID_HVAC_ERROR = 557849136;
    public static final int ID_HVAC_FAN_AUTO_CONCTRL_ST = 557849138;
    public static final int ID_HVAC_FAN_SPEED_DOWN = 557849096;
    public static final int ID_HVAC_FAN_SPEED_UP = 557849095;
    public static final int ID_HVAC_FROGING_RISK = 557849153;
    public static final int ID_HVAC_INNER_TEMPERATURE = 559946285;
    public static final int ID_HVAC_IONIZER = 557849118;
    public static final int ID_HVAC_LEFT_SYNC = 557849102;
    public static final int ID_HVAC_MACHINE_STATE_SWITCH = 557849182;
    public static final int ID_HVAC_MAX_COOLING = 557849148;
    public static final int ID_HVAC_MAX_WARMING = 557849147;
    public static final int ID_HVAC_NEW_FRESH_SW = 557849180;
    public static final int ID_HVAC_PM25 = 557849092;
    public static final int ID_HVAC_PSNSEAT_TEMP_DOWN = 557849100;
    public static final int ID_HVAC_PSNSEAT_TEMP_UP = 557849099;
    public static final int ID_HVAC_PSN_LEFTHORZ = 557849108;
    public static final int ID_HVAC_PSN_LEFTVERT = 557849109;
    public static final int ID_HVAC_PSN_RIGHTHORZ = 557849110;
    public static final int ID_HVAC_PSN_RIGHTVERT = 557849111;
    public static final int ID_HVAC_PSN_WINDMODE = 557849117;
    public static final int ID_HVAC_PTC_ST = 557849131;
    public static final int ID_HVAC_REAR_AIRDISTRIBUTION_AUTO_ST = 557849172;
    public static final int ID_HVAC_REAR_AUTO_ON_XP = 557849170;
    public static final int ID_HVAC_REAR_BLOWER_MODE = 557849160;
    public static final int ID_HVAC_REAR_FAN_AUTO_CONCTRL_ST = 557849171;
    public static final int ID_HVAC_REAR_FAN_DIRECTION = 557849161;
    public static final int ID_HVAC_REAR_FAN_INSENVENT = 557849181;
    public static final int ID_HVAC_REAR_FAN_SPEED = 557849175;
    public static final int ID_HVAC_REAR_FAN_SPEED_DOWN = 557849174;
    public static final int ID_HVAC_REAR_FAN_SPEED_UP = 557849173;
    public static final int ID_HVAC_REAR_POWER_ON_XP = 557849163;
    public static final int ID_HVAC_RIGHT_SYNC = 557849103;
    public static final int ID_HVAC_SECROWL_TEMPE_SET = 559946320;
    public static final int ID_HVAC_SECROWL_TEMP_DOWN = 557849165;
    public static final int ID_HVAC_SECROWL_TEMP_UP = 557849164;
    public static final int ID_HVAC_SECROWR_TEMPE_SET = 559946321;
    public static final int ID_HVAC_SECROWR_TEMP_DOWN = 557849167;
    public static final int ID_HVAC_SECROWR_TEMP_UP = 557849166;
    public static final int ID_HVAC_SETTEMP_SYNCST = 557849101;
    @Deprecated
    public static final int ID_HVAC_SFS_CH1_RESET_REQ = 557849155;
    @Deprecated
    public static final int ID_HVAC_SFS_CH2_RESET_REQ = 557849156;
    @Deprecated
    public static final int ID_HVAC_SFS_CH3_RESET_REQ = 557849157;
    public static final int ID_HVAC_SFS_CH_ALL = 557914680;
    public static final int ID_HVAC_SFS_CON_ST = 557849150;
    @Deprecated
    public static final int ID_HVAC_SFS_MODE = 557849154;
    public static final int ID_HVAC_SFS_SW_ST = 557849143;
    public static final int ID_HVAC_SFS_TASTE_SET = 557849145;
    public static final int ID_HVAC_THIROW_BLOWER_MODE = 557849179;
    public static final int ID_HVAC_THIROW_TEMPE_SET = 559946330;
    public static final int ID_HVAC_THIROW_TEMP_DOWN = 557849177;
    public static final int ID_HVAC_THIROW_TEMP_UP = 557849176;
    public static final int ID_HVAC_VEHICLE_SERVICE_USERID = 558897759;
    public static final int ID_HVAC_WINDMODE_COLOUR = 557849120;
    @SystemApi
    public static final int ID_MIRROR_DEFROSTER_ON = 339739916;
    @SystemApi
    public static final int ID_OUTSIDE_AIR_TEMP = 291505923;
    @SystemApi
    public static final int ID_STEERING_WHEEL_HEAT = 289408269;
    @SystemApi
    public static final int ID_TEMPERATURE_DISPLAY_UNITS = 289408270;
    public static final int ID_WINDOW_DEFROSTER_ON = 557849126;
    public static final int ID_ZONED_AC_ON = 557849127;
    public static final int ID_ZONED_AIR_RECIRCULATION_ON = 356517128;
    public static final int ID_ZONED_AUTOMATIC_MODE_ON = 557849129;
    @SystemApi
    public static final int ID_ZONED_DUAL_ZONE_ON = 354419977;
    @SystemApi
    public static final int ID_ZONED_FAN_DIRECTION = 356517121;
    @SystemApi
    public static final int ID_ZONED_FAN_DIRECTION_AVAILABLE = 356582673;
    public static final int ID_ZONED_FAN_POSITION = 356517121;
    @SystemApi
    public static final int ID_ZONED_FAN_SPEED_RPM = 356517135;
    public static final int ID_ZONED_FAN_SPEED_SETPOINT = 356517120;
    @SystemApi
    public static final int ID_ZONED_HVAC_AUTO_RECIRC_ON = 354419986;
    public static final int ID_ZONED_HVAC_POWER_ON = 557849130;
    @SystemApi
    public static final int ID_ZONED_MAX_AC_ON = 354419974;
    @SystemApi
    public static final int ID_ZONED_MAX_DEFROST_ON = 354419975;
    @SystemApi
    public static final int ID_ZONED_SEAT_TEMP = 356517131;
    @SystemApi
    public static final int ID_ZONED_TEMP_ACTUAL = 358614274;
    public static final int ID_ZONED_TEMP_SETPOINT = 358614275;
    public static final int ID_ZONE_HVAC_AIR_QUALITY_OUTSIDE = 557849094;
    public static final int ID_ZONE_HVAC_AIR_QUALITY_OUTSIDE_LEVEL = 557849093;
    public static final int ID_ZONE_HVAC_BLW_COMFORT_CFG = 557849091;
    public static final int ID_ZONE_HVAC_PM25_AIR_PURGE = 557849089;
    public static final int ID_ZONE_HVAC_TEMPERATURE_PSN_SET = 559946242;
    public static final int SFS_CHANNEL_1 = 0;
    public static final int SFS_CHANNEL_2 = 1;
    public static final int SFS_CHANNEL_3 = 2;
    public static final int SFS_CHANNEL_CLOSE = 3;
    public static final int SFS_CONCENTRATION_CLOSED = 0;
    public static final int SFS_CONCENTRATION_HIGH = 2;
    public static final int SFS_CONCENTRATION_LOW = 1;
    public static final int SFS_TYPE_1 = 1;
    public static final int SFS_TYPE_10 = 10;
    public static final int SFS_TYPE_11 = 11;
    public static final int SFS_TYPE_2 = 2;
    public static final int SFS_TYPE_3 = 3;
    public static final int SFS_TYPE_4 = 4;
    public static final int SFS_TYPE_5 = 5;
    public static final int SFS_TYPE_6 = 6;
    public static final int SFS_TYPE_7 = 7;
    public static final int SFS_TYPE_8 = 8;
    public static final int SFS_TYPE_9 = 9;
    public static final int SFS_TYPE_INVALID = 0;
    @Deprecated
    public static final int SFS_TYPE_NULL = 0;
    private static final String TAG = "CarHvacManager";
    private final ArraySet<Integer> mHvacPropertyIds;
    private IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarHvacEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarHvacManager(IBinder service, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mService = null;
        this.mHvacPropertyIds = new ArraySet<>(Arrays.asList(339739916, 289408269, 291505923, 289408270, 358614275, 358614274, 557849130, 356517120, 356517135, 356582673, 356517121, 356517131, 356517121, 557849127, 557849129, 356517128, 354419974, 354419977, 354419975, 557849130, 354419986, 557849126, 559946242, 557849089, 557849091, 557849094, 557849093, 559946285, 557849095, 557849096, 557849097, 557849098, 557849099, 557849100, 557849102, 557849103, 557849104, 557849105, 557849106, 557849107, 557849108, 557849109, 557849110, 557849111, 557849112, 557849113, 557849114, 557849115, 557849116, 557849117, 557849118, 557849119, 557849120, 557849121, 291505923, 557849092, 559946274, 559946275, 557849124, 557849101, 557849131, 557849132, 557849136, 557849137, 557849138, 557849139, 557849140, 557849141, 557849142, 557849143, 557914680, 557849145, 557849146, 557849147, 557849148, 557849149, 557849162, 557849150, 557849151, 557849152, 557849153, 557849154, 557849155, 557849156, 557849157, 557849158, 557849159, 557849163, 557849164, 557849165, 557849166, 557849167, 559946320, 559946321, 557849170, 557849171, 557849173, 557849174, 557849175, 557849176, 557849177, 559946330, 557849179, 557849160, 557849161, 557849180, 557849181, 557849182, 558897759));
    }

    public CarHvacManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mService = null;
        this.mHvacPropertyIds = new ArraySet<>(Arrays.asList(339739916, 289408269, 291505923, 289408270, 358614275, 358614274, 557849130, 356517120, 356517135, 356582673, 356517121, 356517131, 356517121, 557849127, 557849129, 356517128, 354419974, 354419977, 354419975, 557849130, 354419986, 557849126, 559946242, 557849089, 557849091, 557849094, 557849093, 559946285, 557849095, 557849096, 557849097, 557849098, 557849099, 557849100, 557849102, 557849103, 557849104, 557849105, 557849106, 557849107, 557849108, 557849109, 557849110, 557849111, 557849112, 557849113, 557849114, 557849115, 557849116, 557849117, 557849118, 557849119, 557849120, 557849121, 291505923, 557849092, 559946274, 559946275, 557849124, 557849101, 557849131, 557849132, 557849136, 557849137, 557849138, 557849139, 557849140, 557849141, 557849142, 557849143, 557914680, 557849145, 557849146, 557849147, 557849148, 557849149, 557849162, 557849150, 557849151, 557849152, 557849153, 557849154, 557849155, 557849156, 557849157, 557849158, 557849159, 557849163, 557849164, 557849165, 557849166, 557849167, 559946320, 559946321, 557849170, 557849171, 557849173, 557849174, 557849175, 557849176, 557849177, 559946330, 557849179, 557849160, 557849161, 557849180, 557849181, 557849182, 558897759));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    @Override // android.car.hardware.CarEcuManager
    public synchronized void unregisterCallback(CarEcuManager.CarEcuEventCallback callback) {
        try {
            super.unregisterCallback(callback);
        } catch (Exception e) {
            Log.e(TAG, "getPropertyList exception ", e);
        }
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mHvacPropertyIds;
    }

    public static String getServiceName() {
        return Car.HVAC_SERVICE;
    }

    public void setHvacPowerMode(int enable) throws Exception {
        this.mService.setHvacPowerEnabled(enable);
    }

    public int getHvacPowerMode() throws Exception {
        return this.mService.getHvacPowerState();
    }

    public void setHvacTempAcMode(int enable) throws Exception {
        this.mService.setHvacAcEnabled(enable);
    }

    public int getHvacTempAcMode() throws Exception {
        return this.mService.getHvacAcState();
    }

    public void setAcHeatNatureButtonSt(int status) throws Exception {
        this.mService.setHvacAcEnabled(status);
    }

    public int getAcHeatNatureSt() throws Exception {
        return this.mService.getHvacAcState();
    }

    public void setHvacTempLeftSyncMode(int enable) throws Exception {
        this.mService.setHvacTempLeftSyncEnabled(enable);
    }

    public void setHvacTempDriverValue(float level) throws Exception {
        this.mService.setHvacDrvSeatTempValue(level);
    }

    public void setHvacTempDriverUp(float value) throws Exception {
        this.mService.setHvacDrvSeatTempUp(value);
    }

    public void setHvacTempDriverDown(float value) throws Exception {
        this.mService.setHvacDrvSeatTempDown(value);
    }

    public float getHvacTempDriverValue() throws Exception {
        return this.mService.getHvacDrvSeatTempValue();
    }

    public void setHvacTempPsnValue(float level) throws Exception {
        this.mService.setHvacPsnSeatTempValue(level);
    }

    public void setHvacTempPsnUp(float value) throws Exception {
        this.mService.setHvacPsnSeatTempUp(value);
    }

    public void setHvacTempPsnDown(float value) throws Exception {
        this.mService.setHvacPsnSeatTempDown(value);
    }

    public float getHvacTempPsnValue() throws Exception {
        return this.mService.getHvacPsnSeatTempValue();
    }

    public void setHvacAutoMode(int enable) throws Exception {
        this.mService.setHvacAutoModeEnabled(enable);
    }

    public int getHvacAutoMode() throws Exception {
        return this.mService.getHvacAutoModeState();
    }

    public void setHvacAutoModeBlowLevel(int level) throws Exception {
        this.mService.setHvacAutoModePreference(level);
    }

    public int getHvacAutoModeBlowLevel() throws Exception {
        return this.mService.getHvacAutoModePreference();
    }

    public void setHvacCirculationMode(int mode) throws Exception {
        this.mService.setHvacAirCycleMode(mode);
    }

    public int getHvacCirculationMode() throws Exception {
        return this.mService.getHvacAirCycleMode();
    }

    public void setHvacFrontDefrostMode(int enable) throws Exception {
        this.mService.setHvacDefrostMode(enable);
    }

    public int getHVACFrontDefrostMode() throws Exception {
        return this.mService.getHvacDefrostMode();
    }

    public void setHvacWindBlowMode(int mode) throws Exception {
        this.mService.setHvacFanPosition(mode);
    }

    public int getHvacWindBlowMode() throws Exception {
        return this.mService.getHvacFanPosition();
    }

    public void setHvacWindSpeedLevel(int level) throws Exception {
        this.mService.setHvacFanSpeedLevel(level);
    }

    public void setHvacWindSpeedUp(int value) throws Exception {
        this.mService.setHvacFanSpeedUp(value);
    }

    public void setHvacWindSpeedDown(int value) throws Exception {
        this.mService.setHvacFanSpeedDown(value);
    }

    public int getHvacWindSpeedLevel() throws Exception {
        return this.mService.getHvacFanSpeedLevel();
    }

    public int getHvacQualityPurgeMode() throws Exception {
        return this.mService.getHvacAirPurgeSwitchState();
    }

    public void setHvacQualityPurgeMode(int enable) throws Exception {
        this.mService.setHvacAirPurgeEnabed(enable);
    }

    public int getHvacQualityInnerPm25Value() throws Exception {
        return this.mService.getHvacPm25Value();
    }

    public int getHvacQualityInnerPm25Level() throws Exception {
        return getHvacQualityInnerPm25Level(this.mService.getHvacPm25Value());
    }

    public static int getHvacQualityInnerPm25Level(int value) {
        if (value == 1023 || value < 0) {
            return 0;
        }
        if (value <= 50) {
            return 1;
        }
        if (value <= 100) {
            return 2;
        }
        if (value <= 150) {
            return 3;
        }
        if (value <= 200) {
            return 4;
        }
        if (value <= 300) {
            return 5;
        }
        return 6;
    }

    public int getHvacQualityOutsideStatus() throws Exception {
        if ("Q5".equals(this.mCduType) || "Q2".equals(this.mCduType) || "Q6".equals(this.mCduType)) {
            return this.mService.getHvacOutsideAirQualityStatus();
        }
        int level = this.mService.getHvacOutsideAirQualityLevel();
        if (level >= 3 && level <= 10) {
            return 1;
        }
        return 0;
    }

    public int getHvacQualityOutsideLevel() throws Exception {
        return this.mService.getHvacOutsideAirQualityLevel();
    }

    public float getHvacInnerTemp() throws Exception {
        return this.mService.getHvacInnerTemp();
    }

    public void setHvacFanSpeedInc() throws Exception {
        this.mService.setHvacFanSpeedInc();
    }

    public void setHvacFanSpeedDec() throws Exception {
        this.mService.setHvacFanSpeedDec();
    }

    public void setHvacDrvseatTempInc() throws Exception {
        this.mService.setHvacDrvSeatTempInc();
    }

    public void setHvacDrvseatTempDec() throws Exception {
        this.mService.setHvacDrvSeatTempDec();
    }

    public void setHvacPsnseatTempInc() throws Exception {
        this.mService.setHvacPsnSeatTempInc();
    }

    public void setHvacPsnseatTempDec() throws Exception {
        this.mService.setHvacPsnSeatTempDec();
    }

    public void setHvacTempRightSyncMode(int enable) throws Exception {
        this.mService.setHvacTempRightSyncEnabled(enable);
    }

    public void setDriverLeftWindHorPos(int position) throws Exception {
        this.mService.setHvacDrvLeftFanHorPos(position);
    }

    public int getDriverLeftWindHorPos() throws Exception {
        return this.mService.getHvacDrvLeftFanHorPos();
    }

    public void setDriverLeftWindVerPos(int position) throws Exception {
        this.mService.setHvacDrvLeftFanVerPos(position);
    }

    public int getDriverLeftWindVerPos() throws Exception {
        return this.mService.getHvacDrvLeftFanVerPos();
    }

    public void setDriverRightWindHorPos(int position) throws Exception {
        this.mService.setHvacDrvRightFanHorPos(position);
    }

    public int getDriverRightWindHorPos() throws Exception {
        return this.mService.getHvacDrvRightFanHorPos();
    }

    public void setDriverRightWindVerPos(int position) throws Exception {
        this.mService.setHvacDrvRightFanVerPos(position);
    }

    public int getDriverRightWindVerPos() throws Exception {
        return this.mService.getHvacDrvRightFanVerPos();
    }

    public void setPsnLeftWindHorPos(int position) throws Exception {
        this.mService.setHvacPsnLeftFanHorPos(position);
    }

    public int getPsnLeftWindHorPos() throws Exception {
        return this.mService.getHvacPsnLeftFanHorPos();
    }

    public void setPsnLeftWindVerPos(int position) throws Exception {
        this.mService.setHvacPsnLeftFanVerPos(position);
    }

    public int getPsnLeftWindVerPos() throws Exception {
        return this.mService.getHvacPsnLeftFanVerPos();
    }

    public void setPsnRightWindHorPos(int position) throws Exception {
        this.mService.setHvacPsnRightFanHorPos(position);
    }

    public int getPsnRightWindHorPos() throws Exception {
        return this.mService.getHvacPsnRightFanHorPos();
    }

    public void setPsnRightWindVerPos(int position) throws Exception {
        this.mService.setHvacPsnRightFanVerPos(position);
    }

    public int getPsnRightWindVerPos() throws Exception {
        return this.mService.getHvacPsnRightFanVerPos();
    }

    public void setAqsSensitivity(int level) throws Exception {
        this.mService.setHvacAqsSensitivity(level);
    }

    public int getAqsSensitivity() throws Exception {
        return this.mService.getHvacAqsSensitivity();
    }

    public void setEavDriverWindMode(int type) throws Exception {
        this.mService.setHvacEavDrvWindMode(type);
    }

    public int getEavDriverWindMode() throws Exception {
        return this.mService.getHvacEavDrvWindMode();
    }

    public void setEavPsnWindMode(int type) throws Exception {
        this.mService.setHvacEavPsnWindMode(type);
    }

    public int getEavPsnWindMode() throws Exception {
        return this.mService.getHvacEavPsnWindMode();
    }

    public void setCirculationPeriod(int type) throws Exception {
        this.mService.setHvacAirCirculationPeriod(type);
    }

    public int getCirculationPeriod() throws Exception {
        return this.mService.getHvacAirCirculationPeriod();
    }

    public int getWindModColor() throws Exception {
        return this.mService.getHvacTempColor();
    }

    public void setAirDistributionMode(int type) throws Exception {
        this.mService.setHvacAirDistributionMode(type);
    }

    public float getHvacOutterTemp() throws Exception {
        return this.mService.getHvacExternalTemp();
    }

    @SystemApi
    public void setSocCoolingRequestTemp(float temp) throws Exception {
        this.mService.setSocCoolingRequestTemp(temp);
    }

    @SystemApi
    public void setAmpCoolingRequestTemp(float temp) throws Exception {
        this.mService.setAmpCoolingRequestTemp(temp);
    }

    @SystemApi
    public void setAmpTempRiseSpeedSt(int temp) throws Exception {
        this.mService.setAmpTempRiseSpeedState(temp);
    }

    public int getHvacTempSyncMode() throws Exception {
        return this.mService.getHvacTempSyncMode();
    }

    public void setAqsMode(int enable) throws Exception {
        this.mService.setHvacAqsEnabled(enable);
    }

    public int getAqsModeSt() throws Exception {
        return this.mService.getHvacAqsSwitchState();
    }

    public void setSweepWindStatus(int enable) throws Exception {
        this.mService.setHvacSweepWindStatus(enable);
    }

    public int getSweepWindStatusSt() throws Exception {
        return this.mService.getHvacSweepWindStatus();
    }

    public void setEconMode(int enable) throws Exception {
        this.mService.setHvacEconEnabled(enable);
    }

    public int getEconModeSt() throws Exception {
        return this.mService.getHvacEconState();
    }

    public int getLonizerModeSt() throws Exception {
        return this.mService.getHvacLonizerState();
    }

    public void setTempPtcStatus(int status) throws Exception {
        this.mService.setHvacTempPtcStatus(status);
    }

    public void setSelfDryStatus(int status) throws Exception {
        this.mService.setHvacSelfDrySwStatus(status);
    }

    public int getSelfDryStatus() throws Exception {
        return this.mService.getHvacSelfDrySwStatus();
    }

    public int getMinWindSpeedLevel() throws Exception {
        return this.mService.getHvacMinWindSpeedLevel();
    }

    public int getMaxWindSpeedLevel() throws Exception {
        return this.mService.getHvacMaxWindSpeedLevel();
    }

    public int getMinHavcTemperature() throws Exception {
        return this.mService.getMinHavcTemperature();
    }

    public int getMaxHavcTemperature() throws Exception {
        return this.mService.getMaxHavcTemperature();
    }

    public int getHvacErrorStatus() throws Exception {
        return this.mService.getHvacErrorStatus();
    }

    public int getAirInTakeAutoControlStatus() throws Exception {
        return this.mService.getHvacAirInTakeAutoControlStatus();
    }

    public int getWindSpeedAutoControlStatus() throws Exception {
        return this.mService.getHvacWindSpeedAutoControlStatus();
    }

    public int getAirDistributionAutoControlStatus() throws Exception {
        return this.mService.getHvacAirDistributionAutoControlStatus();
    }

    public int getHvacAcCtrlType() throws Exception {
        return this.mService.getHvacAcCtrlType();
    }

    public int getHvacBlowerCtrlType() throws Exception {
        return this.mService.getHvacBlowerCtrlType();
    }

    public int getHvacAirCirculationType() throws Exception {
        return this.mService.getHvacAirCirculationType();
    }

    public void setSfsSwitch(int enable) throws Exception {
        this.mService.setHvacSfsSwitch(enable);
    }

    public int getSfsSwitchStatus() throws Exception {
        return this.mService.getHvacSfsSwitchStatus();
    }

    public int[] getSfsTypeInChannels() throws Exception {
        return this.mService.getHvacSfsTypeInChannels();
    }

    public void setSfsChannel(int channel) throws Exception {
        this.mService.setHavacSfsChannel(channel);
    }

    public int getSfsChannel() throws Exception {
        return this.mService.getHvacSfsChannel();
    }

    public void setDeodorizeSwitch(int enable) throws Exception {
        this.mService.setHvacDeodorizeSwitch(enable);
    }

    public int getDeodorizeSwitchStatus() throws Exception {
        return this.mService.getHvacDeodorizeSwitchStatus();
    }

    public void setWarpSpeedWarmingSwitch(int enable) throws Exception {
        this.mService.setHvacWarpSpeedWarmingSwitch(enable);
    }

    public int getWarpSpeedWarmingSwitchStatus() throws Exception {
        return this.mService.getHvacWarpSpeedWarmingSwitchStatus();
    }

    public void setWarpSpeedCoolingSwitch(int enable) throws Exception {
        this.mService.setHvacWarpSpeedCoolingSwitch(enable);
    }

    public int getWarpSpeedCoolingSwitchStatus() throws Exception {
        return this.mService.getHvacWarpSpeedCoolingSwitchStatus();
    }

    public void setAutoDefogSwitch(int enable) throws Exception {
        this.mService.setHvacAutoDefogSwitch(enable);
    }

    public int getAutoDefogSwitchStatus() throws Exception {
        return this.mService.getHvacAutoDefogSwitchStatus();
    }

    public int getAutoDefogWorkSt() throws Exception {
        return this.mService.getHvacAutoDefogWorkSt();
    }

    public void setSfsConcentration(int value) throws Exception {
        this.mService.setHvacSfsConcentration(value);
    }

    public int getSfsConcentrationStatus() throws Exception {
        return this.mService.getHvacSfsConcentrationStatus();
    }

    public int getCoConcentrationStatus() throws Exception {
        return this.mService.getHvacCoConcentrationStatus();
    }

    public int getDisinfSwitchStatus() throws Exception {
        return this.mService.getHvacDisinfSwitchStatus();
    }

    public int getFrogingRiskStatus() throws Exception {
        return this.mService.getHvacFrogingRiskStatus();
    }

    @Deprecated
    public void setSfsTypeMode(int mode) throws Exception {
        this.mService.setHvacSfsTypeMode(mode);
    }

    @Deprecated
    public void setSfsChannelResetRequest(int channel, int request) throws Exception {
        this.mService.setHvacSfsChannelResetRequest(channel, request);
    }

    public int getCompressorConsumePower() throws Exception {
        return this.mService.getHavcCompressorConsumePower();
    }

    public int getHvhConsumePower() throws Exception {
        return this.mService.getHavcHvhConsumePower();
    }

    public void setRearAirDistributionMode(int mode) throws Exception {
        this.mService.setRearHvacAirDistributionMode(mode);
    }

    public void setRearHvacWindBlowMode(int mode) throws Exception {
        this.mService.setRearHvacFanPosition(mode);
    }

    public int getRearHvacWindBlowMode() throws Exception {
        return this.mService.getRearHvacFanPosition();
    }

    public void setRearHvacPowerMode(int enable) throws Exception {
        this.mService.setRearHvacPowerEnabled(enable);
    }

    public int getRearHvacPowerMode() throws Exception {
        return this.mService.getRearHvacPowerState();
    }

    public void setHvacSecRowLeftTempInc() throws Exception {
        this.mService.setHvacSecRowLeftTempInc();
    }

    public void setHvacSecRowLeftTempDec() throws Exception {
        this.mService.setHvacSecRowLeftTempDec();
    }

    public void setHvacSecRowRightTempInc() throws Exception {
        this.mService.setHvacSecRowRightTempInc();
    }

    public void setHvacSecRowRightTempDec() throws Exception {
        this.mService.setHvacSecRowRightTempDec();
    }

    public void setHvacTempSecRowLeftValue(float level) throws Exception {
        this.mService.setHvacTempSecRowLeftValue(level);
    }

    public float getHvacTempSecRowLeftValue() throws Exception {
        return this.mService.getHvacTempSecRowLeftValue();
    }

    public void setHvacTempSecRowRightValue(float level) throws Exception {
        this.mService.setHvacTempSecRowRightValue(level);
    }

    public float getHvacTempSecRowRightValue() throws Exception {
        return this.mService.getHvacTempSecRowRightValue();
    }

    public void setHvacThirdRowTempInc() throws Exception {
        this.mService.setHvacThirdRowTempInc();
    }

    public void setHvacThirdRowTempDec() throws Exception {
        this.mService.setHvacThirdRowTempDec();
    }

    public void setHvacTempThirdRowtValue(float level) throws Exception {
        this.mService.setHvacTempThirdRowtValue(level);
    }

    public float getHvacTempThirdRowValue() throws Exception {
        return this.mService.getHvacTempThirdRowValue();
    }

    public void setHvacRearAutoMode(int enable) throws Exception {
        this.mService.setHvacRearAutoModeEnabled(enable);
    }

    public int getHvacRearAutoMode() throws Exception {
        return this.mService.getHvacRearAutoModeState();
    }

    public int getRearWindSpeedAutoControlStatus() throws Exception {
        return this.mService.getHvacRearWindSpeedAutoControlStatus();
    }

    public int getRearAirDistributionAutoControlStatus() throws Exception {
        return this.mService.getHvacRearAirDistributionAutoControlStatus();
    }

    public void setHvacRearFanSpeedInc() throws Exception {
        this.mService.setHvacRearFanSpeedInc();
    }

    public void setHvacRearFanSpeedDec() throws Exception {
        this.mService.setHvacRearFanSpeedDec();
    }

    public void setHvacRearWindSpeedLevel(int level) throws Exception {
        this.mService.setHvacRearFanSpeedLevel(level);
    }

    public int getHvacRearWindSpeedLevel() throws Exception {
        return this.mService.getHvacRearFanSpeedLevel();
    }

    public void setHvacThirdRowWindBlowMode(int mode) throws Exception {
        this.mService.setHvacThirdRowWindBlowMode(mode);
    }

    public int getHvacThirdRowWindBlowMode() throws Exception {
        return this.mService.getHvacThirdRowWindBlowMode();
    }

    public void setNewFreshSwitchStatus(int onOff) throws Exception {
        this.mService.setHvacNewFreshSwitchStatus(onOff);
    }

    public int getNewFreshSwitchStatus() throws Exception {
        return this.mService.getHvacNewFreshSwitchStatus();
    }

    public void setHvacRearWindLessSwitch(int onOff) throws Exception {
        this.mService.setHvacRearWindLessSwitch(onOff);
    }

    public int getHvacRearWindLessSwitch() throws Exception {
        return this.mService.getHvacRearWindLessSwitch();
    }

    public void setHvacMachineStateSwitch(int enable) {
        try {
            this.mService.setHvacMachineStateSwitch(enable);
        } catch (RemoteException | RuntimeException e) {
            Log.e(TAG, "throw RemoteException | RuntimeException e:" + e.getMessage());
        }
    }

    public int getHvacMachineStateSwitch() {
        try {
            return this.mService.getHvacMachineStateSwitch();
        } catch (RemoteException | RuntimeException e) {
            Log.e(TAG, "throw RemoteException | RuntimeException e:" + e.getMessage());
            return 1;
        }
    }

    public long getHvacVehicleServiceUserId() throws Exception {
        return this.mService.getHvacVehicleServiceUserId();
    }
}
