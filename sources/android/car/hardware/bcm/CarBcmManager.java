package android.car.hardware.bcm;

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
public final class CarBcmManager extends CarEcuManager {
    public static final int ALS_FAILURE_STATE_ACCELERATION_SENSOR_ERROR = 2;
    public static final int ALS_FAILURE_STATE_COMMUNICATION_ERROR = 1;
    public static final int ALS_FAILURE_STATE_INITIAL_DATA_ERROR = 4;
    public static final int ALS_FAILURE_STATE_NOT_INITIALIZED = 3;
    public static final int ALS_FAILURE_STATE_NO_ERROR = 0;
    public static final int ALS_FAILURE_STATE_ROAD_ANGLE_ERROR = 5;
    public static final int ALS_STUDY_INITIALIZATION_FAILED = 1;
    public static final int ALS_STUDY_INITIALIZATION_NOT_FINISHED = 0;
    public static final int ALS_STUDY_INITIALIZATION_SUCCESS = 2;
    public static final int ARS_AUTO = 1;
    public static final int ARS_FAULT = 1;
    public static final int ARS_FOLD = 1;
    public static final int ARS_FOLDING = 1;
    @Deprecated
    public static final int ARS_FOLD_0_TO_10PER = 2;
    @Deprecated
    public static final int ARS_FOLD_100_PER = 1;
    @Deprecated
    public static final int ARS_FOLD_10_TO_20PER = 3;
    @Deprecated
    public static final int ARS_FOLD_20_TO_30PER = 4;
    @Deprecated
    public static final int ARS_FOLD_30_TO_40PER = 5;
    @Deprecated
    public static final int ARS_FOLD_40_TO_50PER = 6;
    @Deprecated
    public static final int ARS_FOLD_50_TO_60PER = 7;
    @Deprecated
    public static final int ARS_FOLD_60_TO_70PER = 8;
    @Deprecated
    public static final int ARS_FOLD_70_TO_80PER = 9;
    @Deprecated
    public static final int ARS_FOLD_80_TO_90PER = 10;
    @Deprecated
    public static final int ARS_FOLD_90_TO_100PER = 11;
    public static final int ARS_FOLD_DURG = 2;
    public static final int ARS_INITED = 1;
    public static final int ARS_INITING = 6;
    public static final int ARS_INIT_ING = 2;
    public static final int ARS_INIT_INVALID = 3;
    public static final int ARS_INIT_LEARN = 1;
    public static final int ARS_MANUAL = 2;
    public static final int ARS_NOT_INITED = 0;
    public static final int ARS_NO_COMMAND = 0;
    public static final int ARS_NO_FAULT = 0;
    public static final int ARS_STOP = 0;
    public static final int ARS_UNFOLD = 2;
    public static final int ARS_UNFOLDING = 3;
    @Deprecated
    public static final int ARS_UNFOLD_100_PER = 12;
    public static final int ARS_UNFOLD_DURG = 4;
    @Deprecated
    public static final int ARS_UNKNOW_POS = 0;
    public static final int ARS_ZERO_POS_LRN = 5;
    public static final int AS_AUTOLEVEL_RESULT_DEFAULT = 0;
    public static final int AS_AUTOLEVEL_RESULT_FAIL = 1;
    public static final int AS_AUTOLEVEL_RESULT_OVERTIME = 3;
    public static final int AS_AUTOLEVEL_RESULT_PASS = 2;
    public static final int AS_AUTO_LEVELING_RESULT_AUTOMATIC_LEVELED = 1;
    public static final int AS_AUTO_LEVELING_RESULT_FAULT = 3;
    public static final int AS_AUTO_LEVELING_RESULT_INVALID_VALUE = 2;
    public static final int AS_AUTO_LEVELING_RESULT_NO_AUTOMATIC_TARGET = 0;
    @Deprecated
    public static final int AS_DRIVING_MODE_STATUS_ADAPTIVE = 5;
    @Deprecated
    public static final int AS_DRIVING_MODE_STATUS_BOOST = 6;
    public static final int AS_DRIVING_MODE_STATUS_COMFORT = 1;
    public static final int AS_DRIVING_MODE_STATUS_CUSTOMER = 6;
    public static final int AS_DRIVING_MODE_STATUS_ECO = 2;
    public static final int AS_DRIVING_MODE_STATUS_NORMAL = 4;
    public static final int AS_DRIVING_MODE_STATUS_NO_COMMAND = 0;
    public static final int AS_DRIVING_MODE_STATUS_OFFROAD = 5;
    public static final int AS_DRIVING_MODE_STATUS_SPORT = 3;
    public static final int AS_LVL_CHG_ST_GOING_DOWN = 1;
    public static final int AS_LVL_CHG_ST_GOING_UP = 2;
    public static final int AS_LVL_CHG_ST_NO_CHANGE = 0;
    public static final int AS_MODALLWD_CAMPING_ALLOWED = 1;
    public static final int AS_MODALLWD_CAMPING_NOT_ALLOWED = 0;
    @Deprecated
    public static final int AS_SPECIAL_DRIVING_MODE_STATUS_MUD = 1;
    @Deprecated
    public static final int AS_SPECIAL_DRIVING_MODE_STATUS_NO_COMMAND = 0;
    @Deprecated
    public static final int AS_SPECIAL_DRIVING_MODE_STATUS_XPOWER = 2;
    public static final int AS_TAR_LVL_HL1 = 1;
    public static final int AS_TAR_LVL_HL2 = 0;
    public static final int AS_TAR_LVL_LL1 = 3;
    public static final int AS_TAR_LVL_LL2 = 4;
    public static final int AS_TAR_LVL_LL3 = 5;
    public static final int AS_TAR_LVL_NL = 2;
    public static final int AS_TAR_LVL_NOT_VALID = 6;
    public static final int AS_VEHICLE_MODE_EXHIBITION = 3;
    public static final int AS_VEHICLE_MODE_FACTORY = 5;
    public static final int AS_VEHICLE_MODE_INVALID = 0;
    public static final int AS_VEHICLE_MODE_MAINTENANCE = 2;
    public static final int AS_VEHICLE_MODE_NORMAL = 1;
    public static final int AS_VEHICLE_MODE_PRODUCTION = 6;
    public static final int AS_VEHICLE_MODE_TRAIL_DRIVE = 7;
    public static final int AS_VEHICLE_MODE_TRANSPORTATION = 4;
    public static final int AS_WELCOME_SW_NO_COMMAND = 2;
    public static final int AS_WELCOME_SW_OFF = 0;
    public static final int AS_WELCOME_SW_ON = 1;
    public static final int ATWS_STATUS_ALARM = 4;
    public static final int ATWS_STATUS_DEFENCE = 0;
    public static final int ATWS_STATUS_PRE_DEFENCE = 1;
    public static final int ATWS_STATUS_REMIND = 3;
    public static final int ATWS_STATUS_UNDEFENCE = 2;
    public static final int AUTOWINDOWCMD_AUTODOWN = 2;
    public static final int AUTOWINDOWCMD_AUTOUP = 1;
    public static final int AUTOWINDOWCMD_STOP = 3;
    public static final int BCM_BONNECT_CLOSED = 0;
    public static final int BCM_BONNECT_COLLIDE = 2;
    public static final int BCM_BONNECT_OPENED = 1;
    public static final int BCM_BONNECT_OPEN_CIRCUIT = 3;
    public static final int BCM_BONNET_CLOSED = 0;
    public static final int BCM_BONNET_LOCK1_OPEN = 2;
    public static final int BCM_BONNET_LOCK2_OPEN = 3;
    public static final int BCM_BONNET_OPENED = 1;
    public static final int BCM_CAMPING_MODE_STATUS = 6;
    public static final int BCM_CDU_DOOR_LOCK = 1;
    public static final int BCM_CDU_DOOR_UNLOCK = 2;
    @SystemApi
    public static final int BCM_CDU_HIGH_BEAM_OFF = 1;
    @SystemApi
    public static final int BCM_CDU_HIGH_BEAM_ON = 2;
    @Deprecated
    public static final int BCM_CDU_TURN_LAMP_ACTIVE = 1;
    @Deprecated
    public static final int BCM_CDU_TURN_LAMP_INACTIVE = 0;
    @Deprecated
    public static final int BCM_CDU_TURN_LAMP_NOTACTIVE = 0;
    public static final int BCM_CHARGE_GUN_LOCKED = 1;
    public static final int BCM_CHARGE_GUN_UNLOCKED = 0;
    public static final int BCM_CHARGE_PORT_CLOSED = 2;
    public static final int BCM_CHARGE_PORT_DEFAULT = 0;
    public static final int BCM_CHARGE_PORT_FAULT = 3;
    public static final int BCM_CHARGE_PORT_LEFT = 1;
    public static final int BCM_CHARGE_PORT_LOCK = 0;
    public static final int BCM_CHARGE_PORT_MIDDLE = 1;
    public static final int BCM_CHARGE_PORT_OPEN = 0;
    public static final int BCM_CHARGE_PORT_RIGHT = 2;
    public static final int BCM_CHARGE_PORT_UNLOCK = 1;
    public static final int BCM_COLUMN_CONTROL_PENDING = 2;
    public static final int BCM_COLUMN_CONTROL_START = 1;
    public static final int BCM_COLUMN_CONTROL_STOP = 3;
    public static final int BCM_COLUMN_MOVE_DOWNWARD = 2;
    public static final int BCM_COLUMN_MOVE_INSIDE = 2;
    public static final int BCM_COLUMN_MOVE_OUTSIDE = 1;
    public static final int BCM_COLUMN_MOVE_UPWARD = 1;
    public static final int BCM_COMMAND_NO_REQUEST = 0;
    public static final int BCM_COMMAND_REQUEST = 1;
    public static final int BCM_COMMON_ACTIVE = 1;
    public static final int BCM_COMMON_INACTIVE = 0;
    public static final int BCM_COMMON_INVALID = 2;
    @Deprecated
    public static final int BCM_CWC_CHARGEDONE = 2;
    public static final int BCM_CWC_CHARGE_DONE = 2;
    @Deprecated
    public static final int BCM_CWC_CHARGE_ERROR = 3;
    public static final int BCM_CWC_CHARGING = 1;
    public static final int BCM_CWC_ERROR_FOD = 3;
    public static final int BCM_CWC_ERROR_LOWVOL_PROTECT = 2;
    public static final int BCM_CWC_ERROR_NOCOMMAND = 0;
    public static final int BCM_CWC_ERROR_OVERVOL_PROTECT = 1;
    public static final int BCM_CWC_ERROR_PEPS_INTERRUPT = 4;
    public static final int BCM_CWC_ERROR_RX_ERROR = 5;
    public static final int BCM_CWC_ERROR_TEMP_HIGH = 6;
    public static final int BCM_CWC_NO_COMMAND = 0;
    public static final int BCM_DAY_LIGHT_OFF = 0;
    public static final int BCM_DAY_LIGHT_ON = 1;
    @Deprecated
    public static final int BCM_DAY_LIHGT_OFF = 0;
    @Deprecated
    public static final int BCM_DAY_LIHGT_ON = 1;
    public static final int BCM_DOMELIGHT_BRIGHT_LEVEL_1 = 1;
    public static final int BCM_DOMELIGHT_BRIGHT_LEVEL_2 = 2;
    public static final int BCM_DOMELIGHT_BRIGHT_LEVEL_3 = 3;
    public static final int BCM_DOMELIGHT_BRIGHT_LEVEL_4 = 4;
    public static final int BCM_DOMELIGHT_BRIGHT_LEVEL_5 = 5;
    public static final int BCM_DOMELIGHT_BRIGHT_LEVEL_6 = 6;
    public static final int BCM_DOMELIGHT_BRIGHT_OFF = 0;
    public static final int BCM_DOMELIGHT_MODE_DOME = 2;
    public static final int BCM_DOMELIGHT_MODE_OFF = 0;
    public static final int BCM_DOMELIGHT_MODE_ON = 1;
    public static final int BCM_DOMELIGHT_SW_FADING = 2;
    public static final int BCM_DOMELIGHT_SW_OFF = 0;
    public static final int BCM_DOMELIGHT_SW_ON = 1;
    public static final int BCM_DOOR_CLOSE = 0;
    public static final int BCM_DOOR_OPEN = 1;
    public static final int BCM_DRIVER_BELT_BUCKLED = 0;
    public static final int BCM_DRIVER_BELT_UNBUCKLED_WARNING = 1;
    @Deprecated
    public static final int BCM_ENGINEERING_MODE_STATUS = 0;
    public static final int BCM_HAZARD_LIGHT_OFF = 0;
    public static final int BCM_HAZARD_LIGHT_ON = 1;
    public static final int BCM_HEAD_LAMP_LEVEL_0 = 0;
    public static final int BCM_HEAD_LAMP_LEVEL_1 = 1;
    public static final int BCM_HEAD_LAMP_LEVEL_2 = 2;
    public static final int BCM_HEAD_LAMP_LEVEL_3 = 3;
    public static final int BCM_HEAD_LAMP_LEVEL_4 = 4;
    public static final int BCM_HEAD_LAMP_LEVEL_AUTO = 5;
    public static final int BCM_HEAD_LAMP_LEVEL_MANUAL = 6;
    @Deprecated
    public static final int BCM_LAMP_LOCATION = 1;
    public static final int BCM_LAMP_STATE_AUTO = 3;
    public static final int BCM_LAMP_STATE_AUTO_AND_LOCATION = 4;
    public static final int BCM_LAMP_STATE_CLOSE = 0;
    public static final int BCM_LAMP_STATE_LOCATION = 1;
    public static final int BCM_LAMP_STATE_NEAR = 2;
    @Deprecated
    public static final int BCM_LIGHT_ALL = 0;
    public static final int BCM_LIGHT_ME_HOME_INACTIVE = 1;
    public static final int BCM_LIGHT_ME_HOME_LOW_BEAM = 2;
    public static final int BCM_LIGHT_ME_HOME_LOW_BEAM_AND_PARKING_LAMP = 3;
    @Deprecated
    public static final int BCM_LIGHT_ME_HOME_LOW_BEAM_PARKING = 3;
    @Deprecated
    public static final int BCM_LIGHT_ME_HOME_NOT_ACTIVE = 1;
    @Deprecated
    public static final int BCM_LIGHT_OUTSIDE = 1;
    public static final int BCM_LOW_BEAM_OFF_DIAG_ACTIVE = 1;
    public static final int BCM_LOW_BEAM_OFF_DIAG_NO = 2;
    public static final int BCM_LOW_BEAM_OFF_DIAG_NOT_ACTIVE = 0;
    public static final int BCM_LOW_BEAM_OFF_DIAG_YES = 1;
    public static final int BCM_MAINTENANCE_MODE_STATUS = 1;
    public static final int BCM_NFC_CARD_AUTH_STATUS_CWC_PASS = 3;
    public static final int BCM_NFC_CARD_AUTH_STATUS_FAIL = 1;
    public static final int BCM_NFC_CARD_AUTH_STATUS_NFC_PASS = 2;
    public static final int BCM_NFC_CARD_AUTH_STATUS_NO_COMMAND = 0;
    public static final int BCM_NO_REQUEST_MODE_STATUS = 0;
    public static final int BCM_POWERMODE_CRANK = 2;
    public static final int BCM_POWERMODE_OFF = 0;
    public static final int BCM_POWERMODE_ON = 1;
    public static final int BCM_POWEROFF_SOURCE_AUTO = 5;
    public static final int BCM_POWEROFF_SOURCE_DOOR_LOCK = 1;
    public static final int BCM_POWEROFF_SOURCE_FORCE = 2;
    public static final int BCM_POWEROFF_SOURCE_OTA_OR_REMOTE_DIAG = 3;
    public static final int BCM_POWEROFF_SOURCE_XPU_REQ = 4;
    @SystemApi
    public static final int BCM_REAR_MIRROR_FOLDING = 1;
    @SystemApi
    public static final int BCM_REAR_MIRROR_STOPPED = 0;
    @SystemApi
    public static final int BCM_REAR_MIRROR_UNFOLDING = 2;
    public static final int BCM_REAR_TRUNK_CLOSE = 3;
    public static final int BCM_REAR_TRUNK_DEFAULT_POSISITION = 6;
    public static final int BCM_REAR_TRUNK_OPEN = 1;
    public static final int BCM_REAR_TRUNK_PREUNLOCK = 4;
    public static final int BCM_REAR_TRUNK_STOP = 2;
    public static final int BCM_REAR_TRUNK_SWS_OPEN = 5;
    public static final int BCM_REAR_VIEW_MIRROR_TYPE_CLOSE = 0;
    public static final int BCM_REAR_VIEW_MIRROR_TYPE_OPEN = 1;
    public static final int BCM_SDC_BRAKE_CLOSE_DOOR_DRIVER = 1;
    public static final int BCM_SDC_BRAKE_CLOSE_DOOR_FRONT = 2;
    public static final int BCM_SDC_BRAKE_CLOSE_DOOR_NOT_ACTIVE = 0;
    public static final int BCM_SEAT_BLOW_LEVEL_1 = 1;
    public static final int BCM_SEAT_BLOW_LEVEL_2 = 2;
    public static final int BCM_SEAT_BLOW_LEVEL_3 = 3;
    public static final int BCM_SEAT_BLOW_LEVEL_OFF = 0;
    public static final int BCM_SEAT_ERROR_GAIN = 1;
    public static final int BCM_SEAT_ERROR_NONE = 0;
    public static final int BCM_SEAT_HEAT_LEVEL_1 = 1;
    public static final int BCM_SEAT_HEAT_LEVEL_2 = 2;
    public static final int BCM_SEAT_HEAT_LEVEL_3 = 3;
    public static final int BCM_SEAT_HEAT_LEVEL_OFF = 0;
    @Deprecated
    public static final int BCM_SEAT_MOVE_DIRECTION_BACKWARD = 2;
    @Deprecated
    public static final int BCM_SEAT_MOVE_DIRECTION_FORWARD = 1;
    @Deprecated
    public static final int BCM_SEAT_MOVE_DIRECTION_NOT_MOVE = 0;
    @Deprecated
    public static final int BCM_SEAT_TYPE_ANGLE = 3;
    @Deprecated
    public static final int BCM_SEAT_TYPE_HEIGHT = 2;
    @Deprecated
    public static final int BCM_SEAT_TYPE_LEVEL_LOCATION = 1;
    public static final int BCM_SHC_DOWN = 2;
    public static final int BCM_SHC_UP = 1;
    public static final int BCM_STATUS_NO_COMMAND = 2;
    public static final int BCM_STATUS_OFF = 0;
    public static final int BCM_STATUS_ON = 1;
    public static final int BCM_SWS_HEAT_STATUS_LEVEL_1 = 1;
    public static final int BCM_SWS_HEAT_STATUS_LEVEL_2 = 2;
    public static final int BCM_SWS_HEAT_STATUS_LEVEL_3 = 3;
    public static final int BCM_SWS_HEAT_STATUS_OFF = 0;
    public static final int BCM_TRAILER_MODE_STATUS = 3;
    public static final int BCM_TRANSPORT_MODE_STATUS = 2;
    public static final int BCM_TRUNK_OPENNER_FULL_CLOSED_STATUS = 1;
    public static final int BCM_TRUNK_OPENNER_FULL_OPENED_STATUS = 5;
    public static final int BCM_TRUNK_OPENNER_HALF_CLOSED_STATUS = 9;
    public static final int BCM_TRUNK_OPENNER_MOVE_DOWN_BRKG_STATUS = 7;
    public static final int BCM_TRUNK_OPENNER_MOVE_DOWN_STATUS = 6;
    public static final int BCM_TRUNK_OPENNER_MOVE_UP_BRKG_STATUS = 3;
    public static final int BCM_TRUNK_OPENNER_MOVE_UP_STATUS = 2;
    public static final int BCM_TRUNK_OPENNER_STOP_DURG_CLOSE_STATUS = 8;
    public static final int BCM_TRUNK_OPENNER_STOP_DURG_OPEN_STATUS = 4;
    public static final int BCM_TRUNK_OPENNER_STOP_MIN_PNT_FOR_CLOSE_STATUS = 10;
    public static final int BCM_TRUNK_OPENNER_UNKOWN_STATUS = 0;
    public static final int BCM_TRUNK_POS_11_20_PERCENT_TOP = 3;
    public static final int BCM_TRUNK_POS_1_10_PERCENT_TOP = 2;
    public static final int BCM_TRUNK_POS_21_30_PERCENT_TOP = 4;
    public static final int BCM_TRUNK_POS_31_40_PERCENT_TOP = 5;
    public static final int BCM_TRUNK_POS_41_50_PERCENT_TOP = 6;
    public static final int BCM_TRUNK_POS_51_60_PERCENT_TOP = 7;
    public static final int BCM_TRUNK_POS_61_70_PERCENT_TOP = 8;
    public static final int BCM_TRUNK_POS_71_80_PERCENT_TOP = 9;
    public static final int BCM_TRUNK_POS_81_90_PERCENT_TOP = 10;
    public static final int BCM_TRUNK_POS_91_100_PERCENT_TOP = 11;
    public static final int BCM_TRUNK_POS_FULL_CLOSED = 1;
    public static final int BCM_TRUNK_POS_UNKNOWN = 0;
    public static final int BCM_TRUNK_SETPOS_DEFAULT_POS_REQ = 253;
    public static final int BCM_TRUNK_SETPOS_HEIGHT_50_60_PERCENT_TOP = 2;
    public static final int BCM_TRUNK_SETPOS_HEIGHT_51_70_PERCENT_TOP = 3;
    public static final int BCM_TRUNK_SETPOS_HEIGHT_71_80_PERCENT_TOP = 4;
    public static final int BCM_TRUNK_SETPOS_HEIGHT_81_90_PERCENT_TOP = 5;
    public static final int BCM_TRUNK_SETPOS_HEIGHT_91_100_PERCENT_TOP = 6;
    public static final int BCM_TRUNK_SETPOS_HEIGHT_FAIL = 1;
    public static final int BCM_TRUNK_SETPOS_HEIGHT_UNKNOWN = 0;
    public static final int BCM_TRUNK_SETPOS_SET_CURRENT_POS = 254;
    public static final int BCM_TTM_SW_CLOSE = 0;
    public static final int BCM_TTM_SW_MIDDLE = 2;
    public static final int BCM_TTM_SW_OPEN = 1;
    public static final int BCM_TTM_SW_STOP = 3;
    public static final int BCM_UNLOCK_LIGHT_AND_HORN = 0;
    public static final int BCM_UNLOCK_RESPONSE_LIGHT = 1;
    public static final int BCM_WELCOME_MODE_BACK_STATUS_ACTIVE = 1;
    public static final int BCM_WELCOME_MODE_BACK_STATUS_NOT_ACTIVE = 0;
    @Deprecated
    public static final int BCM_WHEEL_DEFINED_BUTTON_HIGH = 3;
    @Deprecated
    public static final int BCM_WHEEL_DEFINED_BUTTON_LOW = 1;
    @Deprecated
    public static final int BCM_WHEEL_DEFINED_BUTTON_MIDDLE = 2;
    public static final int BCM_WINDOW_TYPE_DOWN_AUTO = 4;
    public static final int BCM_WINDOW_TYPE_DOWN_MANUALLY = 3;
    public static final int BCM_WINDOW_TYPE_INVALID = 0;
    public static final int BCM_WINDOW_TYPE_STOP_ACTION = 5;
    public static final int BCM_WINDOW_TYPE_UP_AUTO = 2;
    public static final int BCM_WINDOW_TYPE_UP_MANUALLY = 1;
    public static final int BCM_WIN_ACTION_INVALID = 3;
    public static final int BCM_WIN_ACTION_STOPPED = 0;
    public static final int BCM_WIN_ACTION_WINDOW_CLOSING = 1;
    public static final int BCM_WIN_ACTION_WINDOW_OPENNING = 2;
    public static final int BCM_WIN_INI_LOST_NO_REQUEST = 0;
    public static final int BCM_WIN_INI_LOST_REQUEST = 1;
    public static final int BCM_WIPER_INTERNAL_GEAR_1 = 1;
    public static final int BCM_WIPER_INTERNAL_GEAR_2 = 2;
    public static final int BCM_WIPER_INTERNAL_GEAR_3 = 3;
    public static final int BCM_WIPER_INTERNAL_GEAR_4 = 4;
    public static final int BCM_WIPER_RAIN_DETECT_NO_COMMAND = 0;
    public static final int BCM_WIPER_RAIN_DETECT_SENSITIVITY_LEVEL_1 = 1;
    public static final int BCM_WIPER_RAIN_DETECT_SENSITIVITY_LEVEL_2 = 2;
    public static final int BCM_WIPER_RAIN_DETECT_SENSITIVITY_LEVEL_3 = 3;
    public static final int BCM_WIPER_RAIN_DETECT_SENSITIVITY_LEVEL_4 = 4;
    public static final int BCM_WIPER_RAIN_DETECT_SENSITIVITY_LEVEL_5 = 5;
    public static final int BCM_WIPER_RAIN_DETECT_SENSITIVITY_LEVEL_6 = 6;
    public static final int BCM_WIPER_RAIN_DETECT_SENSITIVITY_LEVEL_7 = 7;
    public static final int BCM_WORK_MODE_ST_ANTI_PLAY_MODE = 2;
    public static final int BCM_WORK_MODE_ST_MANUAL_MODE = 3;
    public static final int BCM_WORK_MODE_ST_NORMAL = 0;
    public static final int BCM_WORK_MODE_ST_OTA_PROBITY_MODE = 4;
    public static final int BCM_WORK_MODE_ST_SYSTEM_ERROR = 1;
    public static final int BCM_WORK_MODE_ST_VEHICLE_SPEED_PROBITY_MODE = 5;
    public static final int BREAK_PEDAL_STATUS_NOT_PRESSED = 0;
    public static final int BREAK_PEDAL_STATUS_PRESSED = 1;
    public static final int CF_DOOR_CLOSE = 0;
    public static final int CF_DOOR_OPEN = 1;
    public static final int CF_MODE_COOL = 1;
    public static final int CF_MODE_HEART = 2;
    public static final int CHARGER_PORT_L = 1;
    public static final int CHARGER_PORT_R = 2;
    public static final int CHARGE_PORT_LOCKED = 1;
    public static final int CHARGE_PORT_UNLOCKED = 0;
    public static final int CHILDLOCK_ALL_LOCK = 6;
    public static final int CHILDLOCK_ALL_UNLOCK = 1;
    public static final int CHILDLOCK_L_LOCK = 4;
    public static final int CHILDLOCK_L_UNLOCK = 2;
    public static final int CHILDLOCK_RET_ALL_LOCK = 4;
    public static final int CHILDLOCK_RET_ALL_UNLOCK = 1;
    public static final int CHILDLOCK_RET_L_UNLOCK_R_LOCK = 2;
    public static final int CHILDLOCK_RET_R_UNLOCK_L_LOCK = 3;
    public static final int CHILDLOCK_R_LOCK = 5;
    public static final int CHILDLOCK_R_UNLOCK = 3;
    @Deprecated
    public static final int CHILDLUCK_ALL_LOCK = 6;
    @Deprecated
    public static final int CHILDLUCK_ALL_UNLOCK = 1;
    @Deprecated
    public static final int CHILDLUCK_L_LOCK = 4;
    @Deprecated
    public static final int CHILDLUCK_L_UNLOCK = 2;
    @Deprecated
    public static final int CHILDLUCK_RET_ALL_LOCK = 4;
    @Deprecated
    public static final int CHILDLUCK_RET_ALL_UNLOCK = 1;
    @Deprecated
    public static final int CHILDLUCK_RET_L_UNLOCK_R_LOCK = 2;
    @Deprecated
    public static final int CHILDLUCK_RET_R_UNLOCK_L_LOCK = 3;
    @Deprecated
    public static final int CHILDLUCK_R_LOCK = 5;
    @Deprecated
    public static final int CHILDLUCK_R_UNLOCK = 3;
    public static final int CMS_ANGLE_NO_COMMAND = 0;
    public static final int CMS_ANGLE_STANDARD = 1;
    public static final int CMS_ANGLE_WIDE = 2;
    public static final int CMS_BT_FROM_CDCU = 2;
    public static final int CMS_BT_FROM_CMS = 1;
    public static final int CMS_BT_FROM_NO = 0;
    public static final int CMS_LOG_FINISH_REQ = 2;
    public static final int CMS_LOG_RESP_NOK = 1;
    public static final int CMS_LOG_RESP_OK = 0;
    public static final int CMS_LOG_START_REQ = 1;
    private static final boolean DBG = false;
    public static final int DOMELIGHT_DOME = 1;
    public static final int DOMELIGHT_OFF = 3;
    public static final int DOMELIGHT_ON = 2;
    public static final int DOOR_LOCK = 1;
    public static final int DOOR_UNLOCK = 0;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_4G = 5;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_BLE = 4;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_CDUSW = 7;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_CENTERSW = 6;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_CRASH = 10;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_NFC = 3;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_PARKING = 9;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_PHYSICAL = 8;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_POLLING = 2;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_RKE = 1;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_VCU = 12;
    public static final int DOOR_UNLOCK_REQUEST_SOURCE_XPU = 11;
    public static final int DRIVER_NOT_ON_SEAT = 0;
    public static final int DRIVER_ON_SEAT = 1;
    public static final int ENVIRONMENT_MODE_DAY = 0;
    public static final int ENVIRONMENT_MODE_NIGHT = 2;
    public static final int ENVIRONMENT_MODE_TWILIGHT_DAWN = 1;
    public static final int FOLLOWMEHOME_TIMECFG15 = 1;
    public static final int FOLLOWMEHOME_TIMECFG30 = 2;
    public static final int FOLLOWMEHOME_TIMECFG60 = 3;
    public static final int FOLLOW_ME_HOME_CONFIGURATION_15S = 1;
    public static final int FOLLOW_ME_HOME_CONFIGURATION_30S = 2;
    public static final int FOLLOW_ME_HOME_CONFIGURATION_60S = 3;
    public static final int FOLLOW_ME_HOME_CONFIGURATION_OFF = 0;
    public static final int FRONT_LEFT_PARKING_LAMP_INDEX = 0;
    public static final int FRONT_MIDDLE_PARKING_LAMP_INDEX = 1;
    public static final int FRONT_RIGHT_PARKING_LAMP_INDEX = 2;
    public static final int HEADLAMPS_STATUS_LAMP_AUTO = 3;
    public static final int HEADLAMPS_STATUS_LAMP_FAR = 4;
    public static final int HEADLAMPS_STATUS_LAMP_LOCATION = 1;
    public static final int HEADLAMPS_STATUS_LAMP_NEAR = 2;
    public static final int HEADLAMPS_STATUS_LAMP_OFF = 0;
    public static final int HEATER_ERROR_STATE_HAS_ERROR = 1;
    public static final int HEATER_ERROR_STATE_NO_ERROR = 0;
    @Deprecated
    public static final int HEIGHT_LVL_CONFIG_0 = 3;
    @Deprecated
    public static final int HEIGHT_LVL_CONFIG_BOOT_LOWRING = 9;
    @Deprecated
    public static final int HEIGHT_LVL_CONFIG_EASY_ENTRY = 8;
    public static final int HEIGHT_LVL_CONFIG_HL1 = 2;
    public static final int HEIGHT_LVL_CONFIG_HL2 = 1;
    public static final int HEIGHT_LVL_CONFIG_LL1 = 4;
    public static final int HEIGHT_LVL_CONFIG_LL2 = 5;
    public static final int HEIGHT_LVL_CONFIG_LL3 = 6;
    public static final int HEIGHT_LVL_CONFIG_NL = 3;
    public static final int HEIGHT_LVL_CONFIG_NOT_VALID = 7;
    public static final int HEIGHT_LVL_CONFIG_NO_CMD = 0;
    @Deprecated
    public static final int HEIGHT_LVL_CONFIG_OFF = 0;
    @Deprecated
    public static final int HEIGHT_LVL_CONFIG_RES = 6;
    public static final int HIGH_SPEED_WINDOWS_AUTO_UP = 2;
    public static final int ID_ALS_INIT_STUDY_AND_ERROR_ST = 557914125;
    public static final int ID_ALS_INIT_STUDY_ST = 557848729;
    public static final int ID_ARS_CTRL = 557859845;
    public static final int ID_ARS_FAULT_ST = 557859846;
    public static final int ID_ARS_INIT_ST = 557859844;
    public static final int ID_ARS_MODE = 557859841;
    public static final int ID_ARS_POSITION = 557859843;
    public static final int ID_ARS_STATE = 557859842;
    public static final int ID_BCM_ALL_MIRRORS_CTRL = 560995451;
    public static final int ID_BCM_AS_ACCEL_METER_ALL = 560021526;
    public static final int ID_BCM_AS_ACTUAL_HEIGHT_LVL = 557858843;
    public static final int ID_BCM_AS_AUTOLEVEL_RESULT = 557858841;
    public static final int ID_BCM_AS_AUTO_LEVELING_ST = 557858823;
    public static final int ID_BCM_AS_CAMPING_MODE_SW = 557858819;
    public static final int ID_BCM_AS_CUSTOMERMODE_SW = 557858818;
    public static final int ID_BCM_AS_DRIVE_MODE = 557858825;
    public static final int ID_BCM_AS_ESP_PATA_REQ = 557858829;
    public static final int ID_BCM_AS_HOIST_MODE = 557858820;
    @Deprecated
    public static final int ID_BCM_AS_LEOPARD_MOD_SW = 557858839;
    public static final int ID_BCM_AS_LOCK_MODE_ST = 557858824;
    public static final int ID_BCM_AS_LVL_CHG = 557858834;
    public static final int ID_BCM_AS_MODALLWD_CAMPING = 557858835;
    public static final int ID_BCM_AS_RED_LAMP_REQ = 557858821;
    @Deprecated
    public static final int ID_BCM_AS_SPEC_DRIVE_MODE = 557858826;
    public static final int ID_BCM_AS_SYS_ST1 = 557924372;
    public static final int ID_BCM_AS_TARGET_HEIGHT_LVL = 557858842;
    @Deprecated
    public static final int ID_BCM_AS_TAR_LVL = 557858830;
    public static final int ID_BCM_AS_TRAILER_MODE_SW = 557858831;
    @Deprecated
    public static final int ID_BCM_AS_VEHICLE_MODE = 557858840;
    public static final int ID_BCM_AS_WELCOME_ST = 557858827;
    public static final int ID_BCM_AS_WELCOME_SW = 557849829;
    public static final int ID_BCM_AS_WHEEL_POS_FILT_MM_ALL = 557924373;
    public static final int ID_BCM_AS_YELLOW_LAMP_REQ = 557858822;
    public static final int ID_BCM_ATWS = 557849647;
    public static final int ID_BCM_AUTO_LIGHT = 557849634;
    public static final int ID_BCM_BACK_MIRROR_HEAT = 557849665;
    public static final int ID_BCM_BONNET = 557849641;
    public static final int ID_BCM_BRKPEDAL_ST = 557849787;
    public static final int ID_BCM_CAP_ST = 557849940;
    public static final int ID_BCM_CARPETLIGHT_WELCOM = 557849991;
    public static final int ID_BCM_CDU_SEATBELT_REQ = 557849763;
    public static final int ID_BCM_CHAIR_WELCOME_MODE_ON = 557849601;
    public static final int ID_BCM_CHARG_GUNL_ST = 557849677;
    public static final int ID_BCM_CHILDLOCK_SW = 557849675;
    public static final int ID_BCM_COLUMN_HORIZONAL_CMD = 557915435;
    public static final int ID_BCM_COLUMN_HORIZONAL_POS = 557849901;
    public static final int ID_BCM_COLUMN_POS_CMD = 557915440;
    public static final int ID_BCM_COLUMN_POS_SAVE = 557849927;
    public static final int ID_BCM_COLUMN_VERTICAL_CMD = 557915434;
    public static final int ID_BCM_COLUMN_VERTICAL_POS = 557849900;
    public static final int ID_BCM_COMFORTWND_CMD = 557849656;
    public static final int ID_BCM_CWCCHRG_ABNORMAL_WARNING = 557849714;
    public static final int ID_BCM_CWCCHRG_ST = 557849713;
    public static final int ID_BCM_CWC_SW = 557849797;
    public static final int ID_BCM_DEMOLIGHT_SW = 557849654;
    public static final int ID_BCM_DOMELIGHT_BRIGHT = 557849863;
    public static final int ID_BCM_DOMELIGHT_MODE = 557849862;
    public static final int ID_BCM_DOOR_CENTRAL_LOCK_MODE = 557849609;
    public static final int ID_BCM_DOOR_STATE = 557915161;
    public static final int ID_BCM_DOOR_UNLOCK_REQRES = 557849759;
    public static final int ID_BCM_DRIVERSEAT_OCCUPIED = 557849607;
    public static final int ID_BCM_DRIVER_SBLT_WARNING = 557849648;
    public static final int ID_BCM_DRIVE_AUTO_LOCK = 557849628;
    public static final int ID_BCM_DRL_CFG = 557849651;
    public static final int ID_BCM_EASY_LOADING = 557858828;
    public static final int ID_BCM_ELECTRIC_SEAT_BELT = 557849635;
    public static final int ID_BCM_EMERGENCY_BRAKE_WARNING = 557849631;
    public static final int ID_BCM_ENGINEERINGMODE = 557849895;
    public static final int ID_BCM_ENVR_MODE = 557849794;
    public static final int ID_BCM_FKS_CFG = 557849869;
    public static final int ID_BCM_FLDM_WIN_PST = 559946855;
    public static final int ID_BCM_FLDOMELIGHT_SW = 557849864;
    public static final int ID_BCM_FLSWST = 557849671;
    public static final int ID_BCM_FLWIN_SWST = 557849620;
    public static final int ID_BCM_FOLLOW_ME_HOME_TIME = 557849650;
    public static final int ID_BCM_FOLLOW_ME_HOME_TIME_ST = 557849796;
    public static final int ID_BCM_FRDM_WIN_PST = 559946856;
    public static final int ID_BCM_FRDOMELIGHT_SW = 557849865;
    public static final int ID_BCM_FRONT_LIGHT_GROUP_MODE = 557849640;
    public static final int ID_BCM_FRONT_MIRRORHEAT = 557849846;
    public static final int ID_BCM_FRSWST = 557849672;
    public static final int ID_BCM_FRWIN_SWST = 557849720;
    public static final int ID_BCM_FR_CWCCHRG_ABNORMAL_WARNING = 557849990;
    public static final int ID_BCM_FR_CWCCHRG_ST = 557849989;
    public static final int ID_BCM_FR_CWC_SW = 557849988;
    public static final int ID_BCM_FWIPER_ACTIVE_ST = 557849971;
    public static final int ID_BCM_FWIPER_MOTOR_ERR = 557849938;
    public static final int ID_BCM_GROUP_COLOR = 557915478;
    public static final int ID_BCM_GROUP_FADETIME = 557915479;
    public static final int ID_BCM_GROUP_LUX = 557915481;
    public static final int ID_BCM_GROUP_TEMP = 557915480;
    public static final int ID_BCM_GROUP_WIPERSPDSW_ST = 557915342;
    public static final int ID_BCM_HANDLE_AUTO = 557849830;
    public static final int ID_BCM_HAZARD_LIGHT = 557849653;
    public static final int ID_BCM_HEAD_BEAM = 289410561;
    public static final int ID_BCM_HEAD_LAMP_LVL_MODE = 557849852;
    public static final int ID_BCM_HEAD_LAMP_LVL_REQ = 557849851;
    @Deprecated
    public static final int ID_BCM_HEAD_LAMP_LVL_ST = 557849872;
    @Deprecated
    public static final int ID_BCM_HEIGHT_LVL = 557858832;
    @SystemApi
    public static final int ID_BCM_HIGHBEAM_SW = 557849606;
    public static final int ID_BCM_INTERNAL_LIGHT_ON = 557849712;
    @SystemApi
    public static final int ID_BCM_KEY_AUTH_ST = 557846584;
    public static final int ID_BCM_LCHILDLOCK = 557849858;
    public static final int ID_BCM_LED_GROUP_CTRL = 557915477;
    public static final int ID_BCM_LIGHT_ME_HOME = 557849627;
    public static final int ID_BCM_LIGHT_SABER_REQ = 557849820;
    public static final int ID_BCM_LMIRROR_CTRL = 557915194;
    public static final int ID_BCM_LOCKWIN_CFG = 557849715;
    public static final int ID_BCM_LOCK_AVAS = 557849876;
    public static final int ID_BCM_LOCK_HAZARD_LIGHT = 557849874;
    public static final int ID_BCM_LOCK_HORN = 557849875;
    public static final int ID_BCM_LOW_BEAM = 557849633;
    public static final int ID_BCM_LOW_BEAM_OFF_CONFIRM = 557849987;
    public static final int ID_BCM_LRMIRROR_OUTPUT_ST = 557915391;
    public static final int ID_BCM_LRTURNLAMP_ACTIVE_ST = 557915328;
    public static final int ID_BCM_LRTURNLAMP_ST = 557915292;
    public static final int ID_BCM_LR_DRL_OUTPUT_ST = 557915329;
    public static final int ID_BCM_LSLIDEDOOR_CTRL = 557849980;
    public static final int ID_BCM_LSLIDEDOOR_LOCK = 557849981;
    public static final int ID_BCM_LSLIDEDOOR_MOODE = 557849979;
    public static final int ID_BCM_LTURNLAMP_ST = 557849623;
    public static final int ID_BCM_L_CHARGER_PORT = 557849642;
    public static final int ID_BCM_L_CHARGER_PORT_LOCK_ST = 557849751;
    public static final int ID_BCM_MAINTAINMODE = 557849822;
    public static final int ID_BCM_MIRRORHEAT_FB = 557915376;
    public static final int ID_BCM_MIRROR_AUTOFOLD_SW = 557849948;
    public static final int ID_BCM_NFC_CARD_AUTH_ST = 557849790;
    public static final int ID_BCM_NFC_CARD_ID_INFO = 560995492;
    public static final int ID_BCM_PACKING_LAMP_OUTPUT = 557915294;
    public static final int ID_BCM_PARKING_AUTO_UNLOCK = 557849629;
    public static final int ID_BCM_PARKING_LAMP = 557849626;
    public static final int ID_BCM_PARKLIGHT_FMB_CFG = 557849757;
    public static final int ID_BCM_POLLING_LOCK_CFG = 557849716;
    public static final int ID_BCM_POLLING_OPEN_CFG = 557849646;
    public static final int ID_BCM_POLLING_UNLOCK_CFG = 557849717;
    public static final int ID_BCM_POLLING_WELCOME = 557849992;
    public static final int ID_BCM_POWERMODE = 557849700;
    public static final int ID_BCM_POWEROFF_SOURCE = 557850057;
    public static final int ID_BCM_PSNG_SBSBR_WARNING = 557915304;
    public static final int ID_BCM_PSN_SEAT_HEAT_LEVEL = 557849701;
    public static final int ID_BCM_PSN_SEAT_VENT_LEVEL = 356517140;
    public static final int ID_BCM_RAIN_DETEC_SENCFG = 557849754;
    public static final int ID_BCM_RAIN_DETEC_SEN_DOWN = 557849931;
    public static final int ID_BCM_RAIN_DETEC_SEN_UP = 557849930;
    public static final int ID_BCM_RAIN_SEN_AND_INACTIVE = 557850058;
    public static final int ID_BCM_RAUTODOWN_CFG = 557849676;
    public static final int ID_BCM_RCHILDLOCK = 557849859;
    public static final int ID_BCM_RDM_DOOR_HEIGHT = 557849795;
    public static final int ID_BCM_READY_ENABLE = 557849786;
    public static final int ID_BCM_REARLOGOLIGHT_SW = 557849985;
    public static final int ID_BCM_REAR_DEFROST_ON = 557849637;
    public static final int ID_BCM_REAR_FOG_LAMP_ON = 557849602;
    public static final int ID_BCM_REAR_SEAT_BELT_WARNING_ON = 557849636;
    public static final int ID_BCM_REAR_VIEW_MIRROR = 557849639;
    @Deprecated
    public static final int ID_BCM_REAR_VIEW_MIRROW = 557849639;
    public static final int ID_BCM_REMOTECTRL_CFG = 557849657;
    public static final int ID_BCM_REVERSECAR_MIRROR_CFG = 557849664;
    public static final int ID_BCM_RLDM_WIN_PST = 559946854;
    public static final int ID_BCM_RLDOMELIGHT_SW = 557849866;
    public static final int ID_BCM_RLSWST = 557849673;
    public static final int ID_BCM_RLWIN_SWST = 557849621;
    public static final int ID_BCM_RL_CWCCHRG_ABNORMAL_WARNING = 557850051;
    public static final int ID_BCM_RL_CWCCHRG_ST = 557850050;
    public static final int ID_BCM_RL_CWC_SW = 557850049;
    public static final int ID_BCM_RL_SEAT_HEAT = 557849770;
    public static final int ID_BCM_RL_SEAT_HEAT_ERR = 557849771;
    public static final int ID_BCM_RMAALL_STATE = 557915345;
    public static final int ID_BCM_RMALLR_LOCATION = 557849660;
    public static final int ID_BCM_RMALUD_LOCATION = 557849662;
    public static final int ID_BCM_RMARLR_LOCATION = 557849661;
    public static final int ID_BCM_RMARUD_LOCATION = 557849663;
    public static final int ID_BCM_RMIRROR_CTRL = 557915195;
    public static final int ID_BCM_ROSE_WIN_CMD_MODE = 557915327;
    public static final int ID_BCM_RRDM_WIN_PST = 559946857;
    public static final int ID_BCM_RRDOMELIGHT_SW = 557849861;
    public static final int ID_BCM_RRSWST = 557849674;
    public static final int ID_BCM_RRWIN_SWST = 557849622;
    public static final int ID_BCM_RR_CWCCHRG_ABNORMAL_WARNING = 557850054;
    public static final int ID_BCM_RR_CWCCHRG_ST = 557850053;
    public static final int ID_BCM_RR_CWC_SW = 557850052;
    public static final int ID_BCM_RR_SEAT_HEAT = 557849769;
    public static final int ID_BCM_RR_SEAT_HEAT_ERR = 557849772;
    public static final int ID_BCM_RSLIDEDOOR_CTRL = 557849983;
    public static final int ID_BCM_RSLIDEDOOR_LOCK = 557849984;
    public static final int ID_BCM_RSLIDEDOOR_MOODE = 557849982;
    public static final int ID_BCM_RTURNLAMP_ST = 557849624;
    public static final int ID_BCM_RWIPERSERVICE_SW = 557849860;
    public static final int ID_BCM_R_CHARGER_PORT = 557849643;
    public static final int ID_BCM_R_CHARGER_PORT_LOCK_ST = 557849752;
    public static final int ID_BCM_SC_ANTI_PINCH_STATUS = 557849826;
    public static final int ID_BCM_SC_COMFORT_CMD = 557849803;
    public static final int ID_BCM_SC_ECU_STATUS = 557849824;
    public static final int ID_BCM_SC_ICE_BREAKMODE = 557849827;
    public static final int ID_BCM_SC_INITIALIZED = 557849805;
    public static final int ID_BCM_SC_LIN_STATUS = 557849828;
    public static final int ID_BCM_SC_MOTOR_STATUS = 557849815;
    public static final int ID_BCM_SC_POS = 557849804;
    public static final int ID_BCM_SC_SWITCH_STATUS = 557849823;
    public static final int ID_BCM_SC_THERMAL_PROTECT_ST = 557849825;
    public static final int ID_BCM_SDCL_DENORMALIZE_ST = 557849780;
    public static final int ID_BCM_SDCL_DOORPOS = 557849788;
    public static final int ID_BCM_SDCL_HAZZARD_REQ = 557849776;
    public static final int ID_BCM_SDCL_MOVECMD = 557849798;
    public static final int ID_BCM_SDCL_PSDMOTO_ST = 557849760;
    public static final int ID_BCM_SDCL_SYSTEM_ERROR = 557849778;
    public static final int ID_BCM_SDCL_SYS_RUNING_ST = 557849807;
    public static final int ID_BCM_SDCR_DENORMALIZE_ST = 557849781;
    public static final int ID_BCM_SDCR_DOORPOS = 557849789;
    public static final int ID_BCM_SDCR_HAZZARD_REQ = 557849777;
    public static final int ID_BCM_SDCR_MOVECMD = 557849799;
    public static final int ID_BCM_SDCR_PSDMOTO_ST = 557849761;
    public static final int ID_BCM_SDCR_SYSTEM_ERROR = 557849779;
    public static final int ID_BCM_SDCR_SYS_RUNING_ST = 557849808;
    public static final int ID_BCM_SDC_BRAKE_CLOSE_DOOR = 557849939;
    public static final int ID_BCM_SDC_KEY_CLOSE_MODE_CTRL = 557849774;
    public static final int ID_BCM_SDC_KEY_OPEN_MODE_CTRL = 557849773;
    public static final int ID_BCM_SDC_OPEN_HALL_CFG = 557849775;
    public static final int ID_BCM_SDC_WINS_AUTODOWN_L = 557849784;
    public static final int ID_BCM_SDC_WINS_AUTODOWN_R = 557849785;
    public static final int ID_BCM_SEAT_ERR = 557915214;
    public static final int ID_BCM_SEAT_HEAT_ERR = 557849766;
    public static final int ID_BCM_SEAT_HEAT_LEVEL = 557849638;
    public static final int ID_BCM_SEAT_VENT_LEVEL = 356517139;
    public static final int ID_BCM_SECROW_LTSEAT_VENT_LEVEL = 557850023;
    public static final int ID_BCM_SECROW_RTSEAT_VENT_LEVEL = 557850024;
    public static final int ID_BCM_SHC_REQ = 557849666;
    public static final int ID_BCM_SOFT_LVL = 557858833;
    @SystemApi
    public static final int ID_BCM_STEALTH_GET_MODE = 557849719;
    @SystemApi
    public static final int ID_BCM_STEALTH_MODE = 557849718;
    public static final int ID_BCM_STOPNFCCAR_SW = 557849655;
    public static final int ID_BCM_SWS_CTRL_SCENE_ST = 557851185;
    public static final int ID_BCM_SWS_HEAT = 557849854;
    public static final int ID_BCM_TEMPORARY_LOCK_ST = 557849889;
    public static final int ID_BCM_TLDOMELIGHT_SW = 557850055;
    public static final int ID_BCM_TRAILER_MODE_ST = 557849933;
    @Deprecated
    public static final int ID_BCM_TRAILER_MODE_SW = 557849933;
    public static final int ID_BCM_TRANSPORT_MODE_SW = 557849897;
    public static final int ID_BCM_TRDOMELIGHT_SW = 557850056;
    public static final int ID_BCM_TRUNK = 557849610;
    public static final int ID_BCM_TRUNKPOS_OPEN_REQ = 557849870;
    public static final int ID_BCM_TRUNK_OPENNER_ST = 557849903;
    public static final int ID_BCM_TRUNK_POS_FEEDBACK = 557849946;
    public static final int ID_BCM_TRUNK_SETPOS_REQ = 557849871;
    public static final int ID_BCM_TTM_DENORMALIZE_ST = 557858306;
    public static final int ID_BCM_TTM_SW = 557858305;
    public static final int ID_BCM_TTM_SYS_ERR = 557858307;
    public static final int ID_BCM_UNLOCK_RESPONSE = 557849630;
    public static final int ID_BCM_WASH_CAR_MODE = 557849853;
    public static final int ID_BCM_WASH_FLUID_WARN = 557849765;
    public static final int ID_BCM_WELCOME_ST = 557849644;
    public static final int ID_BCM_WINDOW_ALLPOS = 560012386;
    public static final int ID_BCM_WINDOW_ALLSW = 560995427;
    @Deprecated
    public static final int ID_BCM_WINDOW_MANUAL_AUTO_MODE = 560995427;
    public static final int ID_BCM_WINDOW_POSITION = 560012320;
    public static final int ID_BCM_WIN_ACTION_FB_ALL = 557915508;
    public static final int ID_BCM_WIN_CMD = 557849645;
    public static final int ID_BCM_WIN_INI_LOST_ST = 557915438;
    public static final int ID_BCM_WIN_LOCK_ST = 557848731;
    public static final int ID_BCM_WIPERSERVICE_SW = 557849611;
    public static final int ID_BCM_WIPERSPDSW_ST = 557849762;
    public static final int ID_BCM_WIPER_INTERVAL = 557849649;
    public static final int ID_BCM_WIPER_MOTOR_ST = 557849913;
    public static final int ID_BCM_WORK_MODE_ST = 557849986;
    public static final int ID_BCM_X5_DCINEMA_MODE_ST = 557849957;
    public static final int ID_BCM_XMEDITATION_MODE_ST = 557849958;
    public static final int ID_BCM_XMOVIE_MODE_ST = 557849951;
    public static final int ID_BCM_XSLEEP_MODE_ST = 557849950;
    public static final int ID_CF_CHILD_LOCK = 557860359;
    public static final int ID_CF_DOOR_CTRL = 557860354;
    public static final int ID_CF_KEEPTEMP_APPOINT = 557860361;
    public static final int ID_CF_KEEPTEMP_MEMORY_REQ = 557860363;
    public static final int ID_CF_KEEPTEMP_REMAIN_TIME = 557860362;
    public static final int ID_CF_KEEPTEMP_REQ = 557860360;
    public static final int ID_CF_KEEPTEMP_WORK_ST = 557860364;
    public static final int ID_CF_POWER_ON = 557860353;
    public static final int ID_CF_TEMPERATURE_SET = 557860357;
    public static final int ID_CF_TEMP_DOWN = 557860356;
    public static final int ID_CF_TEMP_UP = 557860355;
    public static final int ID_CF_WORK_MODE = 557860358;
    public static final int ID_CMS_AUTO_BRIGHT_SW = 557859329;
    @Deprecated
    public static final int ID_CMS_BRIGHT_SET = 557859330;
    public static final int ID_CMS_BRIGHT_SET_WITH_FLAG = 557924883;
    public static final int ID_CMS_HIGHSPEED_ZOOM_SW = 557859333;
    public static final int ID_CMS_LOWSPEED_ZOOM_SW = 557859334;
    public static final int ID_CMS_OBJECT_RECOGNIZE_SW = 557859335;
    public static final int ID_CMS_REVERSE_ASSIST_SW = 557859331;
    @Deprecated
    public static final int ID_CMS_STORE_BRIGHT_SOURCE = 557859346;
    public static final int ID_CMS_TURN_EXTN_SW = 557859332;
    public static final int ID_CMS_VIEW_RECOVERY = 557859336;
    public static final int ID_CMS_VISION_ANGLE_CTRL = 557859345;
    @Deprecated
    public static final int ID_CMS_VISION_POINT_CTRL = 557924879;
    public static final int ID_CMS_VISION_POINT_CTRL_FLOAT = 560022032;
    public static final int ID_IMS_AUTO_VISION_SW = 557860866;
    public static final int ID_IMS_BRIGHT_LEVEL = 557860867;
    public static final int ID_IMS_MODE = 557860865;
    public static final int ID_IMS_SYSTEM_ST = 557860871;
    public static final int ID_IMS_VISION_ANGLE_LEVEL = 557860870;
    public static final int ID_IMS_VISION_CTRL = 557926404;
    public static final int ID_IMS_VISION_VERTICAL_LEVEL = 557860869;
    public static final int ID_LCMS_LOG_CTRL = 557859341;
    public static final int ID_RCMS_LOG_CTRL = 557859342;
    @SystemApi
    public static final int ID_SDCL_SW = 557849730;
    public static final int ID_SDCL_SW_AUTO_AND_MANUAL = 557915318;
    @SystemApi
    public static final int ID_SDCR_SW = 557849733;
    public static final int ID_SDCR_SW_AUTO_AND_MANUAL = 557915319;
    @SystemApi
    public static final int ID_SDC_MODE_SW = 557849731;
    public static final int ID_SECROW_EASY_ENTRY_SW = 557849947;
    public static final int ID_SFM_ANGLE_POS = 557849994;
    public static final int ID_SFM_CTRL = 557849993;
    public static final int ID_TTM_HOOK_MOTOR_ST = 557858310;
    public static final int ID_TTM_LAMP_CONNECT_ST = 557858308;
    public static final int ID_TTM_LAMP_FAULT_ST = 557858309;
    @SystemApi
    public static final int ID_TWC_MODE_SW = 557849732;
    @SystemApi
    public static final int ID_TWC_UPDOWN_SW = 557849729;
    public static final int IMS_ANGLE_NARROW = 1;
    public static final int IMS_ANGLE_NORMAL = 0;
    public static final int IMS_ANGLE_WIDEN = 2;
    public static final int IMS_AUTO_VISION_OFF = 0;
    public static final int IMS_AUTO_VISION_ON = 1;
    public static final int IMS_LEVEL1 = 1;
    public static final int IMS_LEVEL2 = 2;
    public static final int IMS_LEVEL3 = 3;
    public static final int IMS_LEVEL4 = 4;
    public static final int IMS_LEVEL5 = 5;
    public static final int IMS_LEVEL6 = 6;
    public static final int IMS_LEVEL7 = 7;
    public static final int IMS_MODE_CAMERA = 1;
    public static final int IMS_MODE_MIRROR = 0;
    public static final int IMS_MOVE_DOWN = 2;
    public static final int IMS_MOVE_NARROW = 4;
    public static final int IMS_MOVE_UP = 1;
    public static final int IMS_MOVE_WIDE = 3;
    public static final int IMS_SYSTEM_FAILURE = 2;
    public static final int IMS_SYSTEM_NORMAL = 1;
    public static final int IMS_SYSTEM_OFF = 0;
    public static final int KEY_AUTH_STATE_FAIL = 3;
    public static final int KEY_AUTH_STATE_INACTIVE = 0;
    public static final int KEY_AUTH_STATE_START_IDENTIFICATION = 1;
    public static final int KEY_AUTH_STATE_SUCCESS = 2;
    public static final int MIRROR_CONTROL_PENDING = 2;
    public static final int MIRROR_CONTROL_START = 1;
    public static final int MIRROR_CONTROL_STOP = 3;
    public static final int MIRROR_MOVE_DOWN = 2;
    public static final int MIRROR_MOVE_LEFT = 3;
    public static final int MIRROR_MOVE_RIGHT = 4;
    public static final int MIRROR_MOVE_UP = 1;
    public static final int MIRROR_REVERSE_BOTHSIDE = 3;
    public static final int MIRROR_REVERSE_LEFTSIDE = 1;
    public static final int MIRROR_REVERSE_OFF = 0;
    public static final int MIRROR_REVERSE_RIGHTSIDE = 2;
    public static final int MOVE_DOWN = 1;
    public static final int MOVE_UP = 0;
    @Deprecated
    public static final int OLED_STATUS_LIGHT_ALL = 0;
    @Deprecated
    public static final int OLED_STATUS_LIGHT_OUTSIDE = 1;
    public static final int PARK_LIGHT_RELATED_FM_B_LIGHT_CONFIG_ACTIVE = 1;
    public static final int PARK_LIGHT_RELATED_FM_B_LIGHT_CONFIG_INACTIVE = 0;
    public static final int PARK_LIGHT_RELATED_FM_B_LIGHT_CONFIG_STOP = 2;
    public static final int PHONE_CONNECT_WINDOWS_AUTO_UP = 1;
    @Deprecated
    public static final int PREARMEDCFG_LIGHT = 2;
    @Deprecated
    public static final int PREARMEDCFG_LIGHT_SOUND = 1;
    public static final int READY_ENABLE_STATE_ACTIVE = 1;
    public static final int READY_ENABLE_STATE_INACTIVE = 0;
    public static final int REARVIEW_AUTODOWN_CFG_CLOSE = 1;
    public static final int REARVIEW_AUTODOWN_CFG_OPEN = 2;
    public static final int REAR_TRUNK_STATUS_CLOSED = 0;
    public static final int REAR_TRUNK_STATUS_CLOSING = 3;
    public static final int REAR_TRUNK_STATUS_OPENED = 2;
    public static final int REAR_TRUNK_STATUS_OPENING = 1;
    public static final int RMA_ADJUST_STATE_LEFT_OR_UP_MOVE = 1;
    public static final int RMA_ADJUST_STATE_NULL = 0;
    public static final int RMA_ADJUST_STATE_RIGHT_OR_DOWN_MOVE = 2;
    public static final int SC_LIN_STATUS_CLOSING = 0;
    public static final int SC_LIN_STATUS_OPENING = 1;
    public static final int SC_STATUS_FAIL = 1;
    public static final int SC_STATUS_INVALID = 255;
    public static final int SC_STATUS_NORMAL = 0;
    public static final int SC_STATUS_NOTOPEN = 2;
    @SystemApi
    public static final int SDC_AUTO_MODE_ONE = 2;
    @SystemApi
    public static final int SDC_AUTO_MODE_TWO = 3;
    public static final int SDC_DENORMALIZE_STATE_INITIALIZED = 1;
    public static final int SDC_DENORMALIZE_STATE_NOT_INITIALIZED = 0;
    public static final int SDC_HAZZARD_REQUEST_1 = 1;
    public static final int SDC_HAZZARD_REQUEST_2 = 2;
    public static final int SDC_HAZZARD_REQUEST_3 = 3;
    public static final int SDC_KEY_CTRL_CFG_DRIVER_DOOR = 0;
    public static final int SDC_KEY_CTRL_CFG_FRONT_DOOR = 1;
    private static final int SDC_MANUAL_CONTROL_INVALID = 0;
    public static final int SDC_MANUAL_CONTROL_PENDING = 2;
    public static final int SDC_MANUAL_CONTROL_START = 1;
    public static final int SDC_MANUAL_CONTROL_STOP = 3;
    @SystemApi
    public static final int SDC_MANUAL_MODE = 1;
    public static final int SDC_MOVE_COMMAND_AUTO_CLOSE = 1;
    public static final int SDC_MOVE_COMMAND_AUTO_OPEN = 2;
    public static final int SDC_MOVE_COMMAND_STEP_CLOSE = 4;
    public static final int SDC_MOVE_COMMAND_STEP_OPEN = 5;
    public static final int SDC_MOVE_COMMAND_STOP = 3;
    public static final int SDC_PSD_MOTOR_STATE_CLOSING = 2;
    public static final int SDC_PSD_MOTOR_STATE_NOT_MOVE = 0;
    public static final int SDC_PSD_MOTOR_STATE_OPENING = 1;
    public static final int SDC_PSD_MOTOR_STATE_PAUSE = 3;
    private static final int SDC_SW_AUTO_CLOSE = 1;
    private static final int SDC_SW_AUTO_OPEN = 2;
    @SystemApi
    public static final int SDC_SW_CLOSE = 1;
    private static final int SDC_SW_MANUAL_CLOSE = 4;
    private static final int SDC_SW_MANUAL_OPEN = 5;
    @SystemApi
    public static final int SDC_SW_OFF = 1;
    @SystemApi
    public static final int SDC_SW_ON = 2;
    @SystemApi
    public static final int SDC_SW_OPEN = 2;
    @SystemApi
    public static final int SDC_SW_STOP = 3;
    public static final int SDC_SYSTEM_ERROR_STATE_HAS_ERROR = 1;
    public static final int SDC_SYSTEM_ERROR_STATE_NO_ERROR = 0;
    public static final int SDC_SYSTEM_RUNNING_STATUS_CLOSED = 1;
    public static final int SDC_SYSTEM_RUNNING_STATUS_CLOSING = 3;
    public static final int SDC_SYSTEM_RUNNING_STATUS_INITIAL = 0;
    public static final int SDC_SYSTEM_RUNNING_STATUS_LOCK_STATUS_ERROR = 7;
    public static final int SDC_SYSTEM_RUNNING_STATUS_OPENING = 2;
    public static final int SDC_SYSTEM_RUNNING_STATUS_OPEN_FINISHED = 5;
    public static final int SDC_SYSTEM_RUNNING_STATUS_PAUSE = 4;
    public static final int SDC_SYSTEM_RUNNING_STATUS_PREVENT_PLAYING_MODE = 6;
    public static final int SDC_WINDOW_AUTO_DOWN_SWITCH_STATUS_OFF = 1;
    public static final int SDC_WINDOW_AUTO_DOWN_SWITCH_STATUS_ON = 2;
    public static final int SEAT_BELT_NO_REQUEST = 0;
    public static final int SEAT_BELT_REQUEST1 = 1;
    public static final int SEAT_BELT_REQUEST2 = 2;
    public static final int SEAT_HEAT_LEVEL_1 = 1;
    public static final int SEAT_HEAT_LEVEL_2 = 2;
    public static final int SEAT_HEAT_LEVEL_3 = 3;
    public static final int SEAT_HEAT_OFF = 0;
    public static final int SFM_CLOSE = 2;
    public static final int SFM_CLOSED_STATUS = 0;
    public static final int SFM_CLOSING_STATUS = 2;
    public static final int SFM_OPEN = 1;
    public static final int SFM_OPENED_STATUS = 1;
    public static final int SFM_OPENING_STATUS = 3;
    public static final int SFM_STOP = 3;
    public static final int SFM_STOP_STATUS = 4;
    public static final int SHADE_CONTROLLER_AUTO_CLOSE_COMMAND = 1;
    public static final int SHADE_CONTROLLER_AUTO_OPEN_COMMAND = 2;
    public static final int SHADE_CONTROLLER_INITIALIZED = 1;
    public static final int SHADE_CONTROLLER_INITIALIZING = 2;
    public static final int SHADE_CONTROLLER_MOTOR_STATUS_BACKWARD = 2;
    public static final int SHADE_CONTROLLER_MOTOR_STATUS_FORWARD = 1;
    public static final int SHADE_CONTROLLER_MOTOR_STATUS_INITIALIZING = 6;
    public static final int SHADE_CONTROLLER_MOTOR_STATUS_STALLED = 4;
    public static final int SHADE_CONTROLLER_MOTOR_STATUS_STOP = 0;
    public static final int SHADE_CONTROLLER_MOTOR_STATUS_ZEROPOSLEARNING = 5;
    public static final int SHADE_CONTROLLER_NOT_INITIALIZED = 0;
    public static final int SHADE_CONTROLLER_STOP_COMMAND = 3;
    public static final int SLIDE_DOOR_BRAKEMODE = 7;
    public static final int SLIDE_DOOR_CLOSE = 2;
    public static final int SLIDE_DOOR_CLOSED = 2;
    public static final int SLIDE_DOOR_CLOSEING = 4;
    public static final int SLIDE_DOOR_DOORFREE = 6;
    public static final int SLIDE_DOOR_FULLOPEN = 1;
    public static final int SLIDE_DOOR_LOCK = 1;
    public static final int SLIDE_DOOR_MANUL_MODE = 1;
    public static final int SLIDE_DOOR_OPEN = 1;
    public static final int SLIDE_DOOR_OPENING = 3;
    public static final int SLIDE_DOOR_POWER_MODE = 0;
    public static final int SLIDE_DOOR_STOP = 3;
    public static final int SLIDE_DOOR_STOPED = 5;
    public static final int SLIDE_DOOR_UNKNOWN = 0;
    public static final int SLIDE_DOOR_UNLOCK = 0;
    public static final int SOFT_LVL_CONFIG_HARD = 3;
    public static final int SOFT_LVL_CONFIG_MEDIUM = 2;
    public static final int SOFT_LVL_CONFIG_SOFT = 1;
    public static final int SWS_CTRL_SCENE_ST_ACC_CONTROL = 3;
    public static final int SWS_CTRL_SCENE_ST_CC_CONTROL = 2;
    public static final int SWS_CTRL_SCENE_ST_COLUMN_CONTROL = 4;
    public static final int SWS_CTRL_SCENE_ST_IVI_CONTROL = 0;
    public static final int SWS_CTRL_SCENE_ST_MIRROR_CONTROL = 1;
    private static final String TAG = "CarBcmManager";
    public static final int TTM_CONNECT = 1;
    public static final int TTM_DENORMALIZE_STATUS_INITIALIZED = 1;
    public static final int TTM_DENORMALIZE_STATUS_NOT_INITIALIZED = 0;
    public static final int TTM_FAULT = 1;
    public static final int TTM_HOOK_MOTOR_STATUS_CLOSING = 2;
    public static final int TTM_HOOK_MOTOR_STATUS_NOT_MOVE = 0;
    public static final int TTM_HOOK_MOTOR_STATUS_OPENING = 1;
    public static final int TTM_NO_CONNECT = 0;
    public static final int TTM_NO_FAULT = 0;
    public static final int TTM_SYS_ERROR_STATUS_ERROR = 1;
    public static final int TTM_SYS_ERROR_STATUS_NO_ERROR = 0;
    public static final int TURN_LAMP_ACTIVE = 1;
    public static final int TURN_LAMP_INACTIVE = 0;
    @SystemApi
    public static final int TWC_AUTO_MODE = 2;
    @SystemApi
    public static final int TWC_MANUAL_MODE = 1;
    @SystemApi
    public static final int TWC_UPDOWN_SW_OFF = 1;
    @SystemApi
    public static final int TWC_UPDOWN_SW_ON = 2;
    public static final int UNLOCK_STATUS_LIGHT = 1;
    public static final int UNLOCK_STATUS_LIGHT_AND_HORN = 0;
    @Deprecated
    public static final int VEHICLE_BCM_SEAT_ACTION_ADD = 1;
    @Deprecated
    public static final int VEHICLE_BCM_SEAT_ACTION_STOP = 0;
    @Deprecated
    public static final int VEHICLE_BCM_SEAT_ACTION_SUB = 2;
    public static final int WINDOW_ALL = 4;
    public static final int WINDOW_BACK_LEFT = 2;
    public static final int WINDOW_BACK_RIGHT = 3;
    public static final int WINDOW_FRONT_LEFT = 0;
    public static final int WINDOW_FRONT_RIGHT = 1;
    public static final int WINDOW_LOCK_ACTIVE = 1;
    public static final int WINDOW_LOCK_INACTIVE = 0;
    public static final int WINDOW_REMOTECTRL_AUTO = 1;
    public static final int WINDOW_REMOTECTRL_MANUAL = 2;
    public static final int WINDOW_REMOTECTRL_OFF = 3;
    public static final int WIPER_HIGH_SPEED_SWITCH = 1;
    public static final int WIPER_INTERMITTENT_SPEED_SWITCH = 2;
    public static final int WIPER_LOW_SPEED_SWITCH = 0;
    public static final int WIPER_SPEED_SW_STATE_AUTO = 4;
    public static final int WIPER_SPEED_SW_STATE_HIGH = 2;
    public static final int WIPER_SPEED_SW_STATE_INTERMITTENT = 3;
    public static final int WIPER_SPEED_SW_STATE_LOW = 1;
    public static final int WIPER_SPEED_SW_STATE_OFF = 0;
    private final ArraySet<Integer> mBcmPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarBcmEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarBcmManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mBcmPropertyIds = new ArraySet<>(Arrays.asList(557849602, 289410561, 557849639, 557849712, 557849631, 560012320, 557849627, 557849628, 557849629, 557849653, 557849609, 557849610, 557849649, 557849601, 557849635, 557849636, 557849630, 557849647, 557849665, 557849638, 356517139, 559946855, 559946856, 559946854, 559946857, 557915161, 557849633, 557849626, 557849637, 557849642, 557849643, 557915214, 557849645, 557849644, 557849640, 557849641, 557849650, 557849651, 557849654, 557849655, 557849656, 557849657, 557849675, 557915194, 557915195, 557849660, 557849661, 557849662, 557849663, 557849664, 557849666, 557849671, 557849672, 557849673, 557849674, 557849620, 557849720, 557849621, 557849622, 557849611, 557849607, 557849606, 557849623, 557849624, 557915292, 557849700, 557849701, 557849646, 557849648, 557849676, 557849677, 557849713, 557849714, 557849715, 557849716, 557849717, 557849718, 557849719, 560995451, 557849751, 557849752, 557848729, 557914125, 557849754, 557848731, 557849757, 557915294, 557849759, 557849760, 557849761, 557846584, 557849762, 557849763, 560995492, 557849634, 557849765, 557849766, 557915304, 557849769, 557849770, 557849771, 557849772, 557849773, 557849774, 557849775, 557849776, 557849777, 557849778, 557849779, 557849780, 557849781, 557915318, 557915319, 557849784, 557849785, 557849786, 557849787, 557849788, 557849789, 557849790, 557915328, 557915327, 557915329, 557849794, 557849795, 557849796, 557849797, 557849798, 557849799, 557849803, 557849804, 557849805, 557915342, 557849807, 557849808, 557915345, 557849815, 557849820, 557849822, 557849823, 557849824, 557849825, 557849826, 557849827, 557849828, 557858833, 557849830, 557858828, 557849829, 557915376, 557849846, 557849860, 557849858, 557849859, 557849874, 557849875, 557849876, 557849862, 557849863, 557849864, 557849865, 557849866, 557849861, 557849869, 557858305, 557849853, 557849852, 557849851, 557849872, 557849870, 557849871, 557849889, 557915391, 356517140, 557849895, 557849897, 557858831, 557915434, 557915435, 557849900, 557849901, 557915438, 557849854, 557849903, 557915440, 557851185, 557849913, 557858818, 557849930, 557849931, 557858823, 557858306, 557858307, 557858819, 557858820, 557858821, 557858822, 557849927, 557849933, 557858824, 557858825, 557858827, 557858829, 557858834, 557858835, 557849938, 557849939, 557849940, 557915477, 557915478, 557915479, 557915480, 557915481, 557924372, 557924373, 560021526, 557849946, 557849947, 557849948, 557858841, 557849950, 557849951, 557849957, 557849958, 557859329, 557859330, 557859333, 557859334, 557859335, 557859331, 557859332, 557859336, 557858842, 557858843, 557859841, 557859842, 557859843, 557859844, 557858308, 557858309, 557858310, 557849971, 557915508, 557859341, 557859342, 560022032, 557859346, 557924883, 557859345, 557859845, 557849979, 557849980, 557849981, 557849982, 557849983, 557849984, 557849985, 557849986, 557849987, 557859846, 557849988, 557849989, 557849990, 557860353, 557860354, 557860355, 557860356, 557860357, 557860358, 557860359, 557860360, 557860361, 557860362, 557860363, 557860364, 557849991, 557849992, 557849993, 557849994, 557850023, 557850024, 557860865, 557860866, 557860867, 557926404, 557860869, 557860870, 557860871, 557850049, 557850050, 557850051, 557850052, 557850053, 557850054, 557850055, 557850056, 557850057, 557850058));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mBcmPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_BCM_SERVICE;
    }

    public int getRearFogLamp() throws Exception {
        return this.mService.isBcmRearFogLampOn();
    }

    public void setRearFogLamp(int status) throws Exception {
        this.mService.setBcmRearFogLampOn(status);
    }

    public void setHeadLampGroup(int groupId) throws Exception {
        this.mService.setBcmFrontLampMode(groupId);
    }

    public int getHeadLampGroup() throws Exception {
        return this.mService.getBcmFrontLampMode();
    }

    public int getFarLampState() throws Exception {
        return this.mService.getBcmFarLampState();
    }

    public int getNearLampState() throws Exception {
        return this.mService.getBcmNearLampState();
    }

    public int getLocationLampState() throws Exception {
        return this.mService.isBcmOutlineMarkerLampsOn();
    }

    public void setRearViewMirror(int type) throws Exception {
        this.mService.setBcmRearViewMirrorPos(type);
    }

    public void setInternalLight(int status) throws Exception {
        this.mService.setBcmInternalLightOn(status);
    }

    public int getInternalLight() throws Exception {
        return this.mService.isBcmInternalLightOn();
    }

    public void setEmergencyBrakeWarning(int status) throws Exception {
        this.mService.setBcmEmergencyBrakeWarning(status);
    }

    public int getEmergencyBrakeWarning() throws Exception {
        return this.mService.isBcmEmergencyBrakeWarningEnabled();
    }

    @Deprecated
    public void setAllWindowManualOrAuto(int type) throws Exception {
        setWindowMoveCmd(4, type);
    }

    public void setWindowMoveCmd(int window, int cmd) throws Exception {
        this.mService.setBcmWindowMoveCmd(window, cmd);
    }

    public void setWindowMovePosition(int window, float position) throws Exception {
        this.mService.setBcmWindowMovePosition(window, position);
    }

    public float getWindowMovePosition(int window) throws Exception {
        return this.mService.getBcmWindowMovePosition(window);
    }

    public int getAtwsState() throws Exception {
        return this.mService.getBcmAtwsState();
    }

    private int getOled() throws Exception {
        return 0;
    }

    private void setOled(int type) throws Exception {
    }

    public float[] getWindowsState() throws Exception {
        return this.mService.getBcmAllWindowsPos();
    }

    private void setDriverWindowAuto(boolean enable) throws Exception {
    }

    private void setCopilotWindowAuto(boolean enable) throws Exception {
    }

    public void setLightMeHome(int type) throws Exception {
        this.mService.setBcmLightMeHomeMode(type);
    }

    public int getLightMeHome() throws Exception {
        return this.mService.getBcmLightMeHomeMode();
    }

    public void setFollowMeHomeCfg(int type) throws Exception {
        this.mService.setBcmFollowMeHomeCfg(type);
    }

    public int getFollowMeHomeCfg() throws Exception {
        return this.mService.getBcmFollowMeHomeCfg();
    }

    public void setDriveAutoLock(int status) throws Exception {
        this.mService.setBcmDrvAutoLockEnabled(status);
    }

    public int getDriveAutoLock() throws Exception {
        return this.mService.isBcmDrvAutoLockEnabled();
    }

    public void setParkingAutoUnlock(int status) throws Exception {
        this.mService.setBcmParkingAutoUnlockEnabled(status);
    }

    public int getParkingAutoUnlock() throws Exception {
        return this.mService.isBcmParkingAutoUnlockEnabled();
    }

    public void setHazardLight(int status) throws Exception {
        this.mService.setBcmHazardLampsFlash(status);
    }

    public void setDoorLock(int status) throws Exception {
        this.mService.setBcmDoorLock(status);
    }

    public int getDoorLockState() throws Exception {
        return this.mService.getBcmDoorLockState();
    }

    public void setWiperInterval(int level) throws Exception {
        this.mService.setBcmWiperInterval(level);
    }

    private void setChairSlowlyAhead(int type) throws Exception {
        this.mService.setChairSlowlyAhead(type);
    }

    private void setChairSlowlyBack(int type) throws Exception {
        this.mService.setChairSlowlyBack(type);
    }

    private void setChairSlowlyEnd(int type) throws Exception {
        this.mService.setChairSlowlyEnd(type);
    }

    private int[] getChairDirection() throws Exception {
        return this.mService.getChairDirection();
    }

    private int[] getChairLocationValue() throws Exception {
        return this.mService.getChairLocationValue();
    }

    private void setChairPositionStart(int level, int height, int angle) throws Exception {
        this.mService.setChairPositionStart(level, height, angle);
    }

    private void setChairPositionEnd() throws Exception {
        this.mService.setChairPositionEnd();
    }

    public int getChairWelcomeMode() throws Exception {
        return this.mService.getBcmChairWelcomeMode();
    }

    public void setChairWelcomeMode(int status) throws Exception {
        this.mService.setBcmChairWelcomeMode(status);
    }

    public void setElectricSeatBelt(int status) throws Exception {
        this.mService.setBcmElectricSeatBeltEnabled(status);
    }

    public int getElectricSeatBelt() throws Exception {
        return this.mService.isBcmElectricSeatBeltEnabled();
    }

    public void setRearSeatBeltWarning(int status) throws Exception {
        this.mService.setBcmRearSeatBeltWarningEnabled(status);
    }

    public int getRearSeatBeltWarning() throws Exception {
        return this.mService.isBcmRearSeatBeltWarningEnabled();
    }

    public void setUnlockResponse(int type) throws Exception {
        this.mService.setBcmUnlockResponseMode(type);
    }

    public int getUnlockResponse() throws Exception {
        return this.mService.getBcmUnlockResponseMode();
    }

    private void setWheelDefinedButton(int type) throws Exception {
    }

    public int[] getDoorsState() throws Exception {
        return this.mService.getBcmDoorsState();
    }

    public void setBcmBackDefrostMode(int status) throws Exception {
        this.mService.setBcmBackDefrostMode(status);
    }

    public int getBcmBackDefrostMode() throws Exception {
        return this.mService.getBcmBackDefrostMode();
    }

    public void setBcmBackMirrorHeatMode(int status) throws Exception {
        this.mService.setBcmBackMirrorHeatMode(status);
    }

    public int getBcmBackMirrorHeatMode() throws Exception {
        return this.mService.getBcmBackMirrorHeatMode();
    }

    public void setBcmSeatHeatLevel(int level) throws Exception {
        this.mService.setBcmSeatHeatLevel(level);
    }

    public int getBcmSeatHeatLevel() throws Exception {
        return this.mService.getBcmSeatHeatLevel();
    }

    public void setBcmSeatBlowLevel(int level) throws Exception {
        this.mService.setBcmSeatBlowLevel(level);
    }

    public int getBcmSeatBlowLevel() throws Exception {
        return this.mService.getBcmSeatBlowLevel();
    }

    public void setPassengerSeatBlowLevel(int level) throws Exception {
        this.mService.setBcmPassengerSeatBlowLevel(level);
    }

    public int getPassengerSeatBlowLevel() throws Exception {
        return this.mService.getBcmPassengerSeatBlowLevel();
    }

    public void setSecRowLeftBlowLevel(int level) throws Exception {
        this.mService.setSecRowLeftBlowLevel(level);
    }

    public int getSecRowLeftBlowLevel() throws Exception {
        return this.mService.getSecRowLeftBlowLevel();
    }

    public void setSecRowRightBlowLevel(int level) throws Exception {
        this.mService.setSecRowRightBlowLevel(level);
    }

    public int getSecRowRightBlowLevel() throws Exception {
        return this.mService.getSecRowRightBlowLevel();
    }

    private int getDriverSeatStatus() throws Exception {
        return 0;
    }

    public float getFldmWinPstState() throws Exception {
        return this.mService.getBcmFrontLeftWinPos();
    }

    public float getFrdmWinPstState() throws Exception {
        return this.mService.getBcmFrontRightWinPos();
    }

    public float getRldmWinPstState() throws Exception {
        return this.mService.getBcmRearLeftWinPos();
    }

    public float getRrdmWinPstState() throws Exception {
        return this.mService.getBcmRearRightWinPos();
    }

    public int getDriverDoorState() throws Exception {
        return this.mService.getDriverDoorState();
    }

    private void setFactoryOledData(byte[] data) throws Exception {
        this.mService.setFactoryOledData(data);
    }

    private void setFactoryOledDisplayMode(int mode) throws Exception {
        this.mService.setFactoryOledDisplayMode(mode);
    }

    public int getSeatErrorState() throws Exception {
        return this.mService.getSeatErrorState();
    }

    public void setVentilate() throws Exception {
        this.mService.setVentilate();
    }

    public int getWelcomeModeBackStatus() throws Exception {
        return this.mService.getWelcomeModeBackStatus();
    }

    public void openBcmBonnet() throws Exception {
        this.mService.openBcmBonnet();
    }

    public int isBcmBonnetOpened() throws Exception {
        return this.mService.isBcmBonnetOpened();
    }

    public int getBonnetStatus() throws Exception {
        return this.mService.getBcmBonnetStatus();
    }

    public void setFollowMeTime(int timeType) throws Exception {
        this.mService.setBcmFollowMeTime(timeType);
    }

    public int getFollowMeTime() throws Exception {
        return this.mService.getBcmFollowMeTime();
    }

    public void setDayLightMode(int status) throws Exception {
        this.mService.setBcmDayLightMode(status);
    }

    public int getDayLightMode() throws Exception {
        return this.mService.getBcmDayLightMode();
    }

    public void setDomeLightCfg(int type) throws Exception {
        this.mService.setBcmDomeLightCfg(type);
    }

    public int getDomeLightCfg() throws Exception {
        return this.mService.getBcmDomeLightCfg();
    }

    public void setStopNfcCardSw(int status) throws Exception {
        this.mService.setBcmNfcCardEnabled(status);
    }

    public int getStopNfcCardSw() throws Exception {
        return this.mService.getBcmNfcCardSwitchState();
    }

    public void setAutoWindowCmd(int cmd) throws Exception {
        this.mService.setBcmAutoWindowCmd(cmd);
    }

    public void setWindowRemoteCtrlCfg(int type) throws Exception {
        this.mService.setBcmWindowRemoteCtrlCfg(type);
    }

    public int getWindowRemoteCtrlCfg() throws Exception {
        return this.mService.getWindowRemoteCtrlCfg();
    }

    public void setChildLockCfg(int type) throws Exception {
        this.mService.setBcmChildLockCfg(type);
    }

    public int getChildLockCfg() throws Exception {
        return this.mService.getBcmChildLockCfg();
    }

    @Deprecated
    public void setLeftMirrorCtrl(int type) throws Exception {
        this.mService.setBcmLeftMirrorCtrlCmd(type);
    }

    public void setLeftMirrorCtrl(int control, int direction) throws Exception {
        this.mService.setBcmLeftMirrorMove(control, direction);
    }

    @Deprecated
    public void setRightMirrorCtrl(int type) throws Exception {
        this.mService.setBcmRightMirrorCtrlCmd(type);
    }

    public void setRightMirrorCtrl(int control, int direction) throws Exception {
        this.mService.setBcmRightMirrorMove(control, direction);
    }

    public void setLeftMirrorLrPos(int pos) throws Exception {
        this.mService.setBcmLeftMirrorHorizPos(pos);
    }

    public int getLeftMirrorLrPos() throws Exception {
        return this.mService.getBcmLeftMirrorHorizPos();
    }

    public void setRightMirrorLrPos(int pos) throws Exception {
        this.mService.setBcmRightMirrorHorizPos(pos);
    }

    public int getRightMirrorLrPos() throws Exception {
        return this.mService.getBcmRightMirrorHorizPos();
    }

    public void setLeftMirrorUdPos(int pos) throws Exception {
        this.mService.setBcmLeftMirrorVerticalPos(pos);
    }

    public int getLeftMirrorUdPos() throws Exception {
        return this.mService.getBcmLeftMirrorVerticalPos();
    }

    public void setRightMirrorUdPos(int pos) throws Exception {
        this.mService.setBcmRightMirrorVerticalPos(pos);
    }

    public int getRightMirrorUdPos() throws Exception {
        return this.mService.getBcmRightMirrorVerticalPos();
    }

    public void setReverseMirrorCfg(int type) throws Exception {
        this.mService.setBcmReverseMirrorCfgCmd(type);
    }

    public int getReverseMirrorCfg() throws Exception {
        return this.mService.getBcmReverseMirrorCfgCmd();
    }

    public void setShcReq(int flag) throws Exception {
        this.mService.setBcmShcReq(flag);
    }

    public void setTrunk(int controlType) throws Exception {
        this.mService.setBcmTrunkOpen(controlType);
    }

    public int getTrunk() throws Exception {
        return this.mService.getBcmTrunkStatus();
    }

    public void setWiperServiceMode(int status) throws Exception {
        this.mService.setBcmWiperServiceMode(status);
    }

    public int getWiperServiceMode() throws Exception {
        return this.mService.getBcmWiperServiceMode();
    }

    public int getManualLfWindowStatus() throws Exception {
        return this.mService.getBcmManualFrontLeftWinStatus();
    }

    public int getManualRfWindowStatus() throws Exception {
        return this.mService.getBcmManualFrontRightWinStatus();
    }

    public int getManualLrWindowStatus() throws Exception {
        return this.mService.getBcmManualRearLeftWinStatus();
    }

    public int getManualRrWindowStatus() throws Exception {
        return this.mService.getBcmManualRearRightWinStatus();
    }

    public int getDriverOnSeat() throws Exception {
        return this.mService.isBcmDriverOnSeat();
    }

    @SystemApi
    public void setHighBeamOnOff(int type) throws Exception {
        this.mService.setBcmHighBeamMode(type);
    }

    public int getLeftTurnLampStatus() throws Exception {
        return this.mService.getBcmLeftTurnLampStatus();
    }

    public int getRightTurnLampStatus() throws Exception {
        return this.mService.getBcmRightTurnLampStatus();
    }

    public int[] getLeftAndRightTurnLampStatus() throws Exception {
        return this.mService.getBcmLeftAndRightTurnLampStatus();
    }

    public int[] getLeftAndRightTurnLampsActiveStatus() throws Exception {
        return this.mService.getLeftAndRightTurnLampsActiveStatus();
    }

    public int getChargePortStatus(int port) throws Exception {
        return this.mService.getBcmChargePortStatus(port);
    }

    public void setChargePortUnlock(int port, int unlock) throws Exception {
        this.mService.setBcmChargePortUnlock(port, unlock);
    }

    public int getWiperInterval() throws Exception {
        return this.mService.getBcmWiperInterval();
    }

    public int getBcmPowerMode() throws Exception {
        return this.mService.getBcmPowerMode();
    }

    public void setBcmPsnSeatHeatLevel(int level) throws Exception {
        this.mService.setBcmPsnSeatHeatLevel(level);
    }

    public int getBcmPsnSeatHeatLevel() throws Exception {
        return this.mService.getBcmPsnSeatHeatLevel();
    }

    public int getPollingOpenCfg() throws Exception {
        return this.mService.getBcmPollingOpenCfg();
    }

    public void setPollingOpenCfg(int status) throws Exception {
        this.mService.setBcmPollingOpenCfg(status);
    }

    public int getDriverBeltWarning() throws Exception {
        return this.mService.getBcmDriverBeltWarning();
    }

    public int getRearViewAutoDownCfg() throws Exception {
        return this.mService.getBcmRearViewAutoDownCfg();
    }

    public void setRearViewAutoDownCfg(int cfg) throws Exception {
        this.mService.setBcmRearViewAutoDownCfg(cfg);
    }

    public int getChargeGunLockSt() throws Exception {
        return this.mService.getBcmChargeGunLockSt();
    }

    public int getCwcChargeSt() throws Exception {
        return this.mService.getCwcChargeSt();
    }

    public int getFRCwcChargeSt() throws Exception {
        return this.mService.getFRCwcChargeSt();
    }

    public int getCwcChargeErrorSt() throws Exception {
        return this.mService.getCwcChargeErrorSt();
    }

    public int getFRCwcChargeErrorSt() throws Exception {
        return this.mService.getFRCwcChargeErrorSt();
    }

    public void setAutoWindowLockSw(int active) throws Exception {
        this.mService.setAutoWindowLockSw(active);
    }

    public int getAutoWindowLockSw() throws Exception {
        return this.mService.getAutoWindowLockSw();
    }

    public void setLeavePollingLockSw(int active) throws Exception {
        this.mService.setLeavePollingLockSw(active);
    }

    public int getLeavePollingLockSw() throws Exception {
        return this.mService.getLeavePollingLockSw();
    }

    public void setNearPollingUnLockSw(int active) throws Exception {
        this.mService.setNearPollingUnLockSw(active);
    }

    public int getNearePollingUnLockSw() throws Exception {
        return this.mService.getNearePollingUnLockSw();
    }

    @SystemApi
    public void setStealthMode(int active) throws Exception {
        this.mService.setStealthMode(active);
    }

    @SystemApi
    public void getStealthMode() throws Exception {
        this.mService.getStealthMode();
    }

    public void setAllExteriorMirrorsPositions(int lMirrorHorizonPos, int lMirrorVerticalPos, int rMirrorHorizonPos, int rMirrorVerticalPos) throws Exception {
        this.mService.setBcmAllExteriorMirrorsPositions(lMirrorHorizonPos, lMirrorVerticalPos, rMirrorHorizonPos, rMirrorVerticalPos);
    }

    public void setCMSAllExteriorMirrorsPositions(float lMirrorHorizonPos, float lMirrorVerticalPos, float rMirrorHorizonPos, float rMirrorVerticalPos) throws Exception {
        this.mService.setLRCMSAllExteriorMirrorsPositions(lMirrorHorizonPos, lMirrorVerticalPos, rMirrorHorizonPos, rMirrorVerticalPos);
    }

    public float[] getCMSAllExteriorMirrorsPositions() throws Exception {
        return this.mService.getLRCMSAllExteriorMirrorsPositions();
    }

    public void setWindowsMovePositions(float flPosition, float frPosition, float rlPosition, float rrPosition) throws Exception {
        this.mService.setBcmWindowsMovePositions(flPosition, frPosition, rlPosition, rrPosition);
    }

    @SystemApi
    public void setSdcMode(int mode) throws Exception {
        this.mService.setBcmSdcMode(mode);
    }

    @SystemApi
    public void setTwcMode(int mode) throws Exception {
        this.mService.setBcmTwcMode(mode);
    }

    @SystemApi
    public void setTwcUpdownSwitch(int state) throws Exception {
        this.mService.setBcmTwcUpdownSwitch(state);
    }

    public void setLeftSdcSwitch(int state) throws Exception {
        setLeftSdcAutoControl(state);
    }

    public void setRightSdcSwitch(int state) throws Exception {
        setRightSdcAutoControl(state);
    }

    public int getLeftChargePortLockState() throws Exception {
        return this.mService.getBcmLeftChargePortLockState();
    }

    public int getRightChargePortLockState() throws Exception {
        return this.mService.getBcmRightChargePortLockState();
    }

    @Deprecated
    public int getAlsStudyInitializationState() throws Exception {
        return this.mService.getAlsInitializationStudyState();
    }

    public int getAlsInitializationStudyState() throws Exception {
        return this.mService.getAlsInitializationStudyState();
    }

    public int[] getAlsInitializationStudyAndErrorState() throws Exception {
        return this.mService.getAlsInitializationStudyAndErrorState();
    }

    public void setWiperRainDetectSensitivity(int level) throws Exception {
        this.mService.setBcmWiperRainDetectSensitivity(level);
    }

    public void setWiperRainDetectSensitivityAndInactive(int level) throws Exception {
        this.mService.setWiperRainDetectSensitivityAndInactive(level);
    }

    public int getWiperRainDetectSensitivity() throws Exception {
        return this.mService.getBcmWiperRainDetectSensitivity();
    }

    public int getWindowLockState() throws Exception {
        return this.mService.getBcmWindowLockState();
    }

    public void setWindowLockState(int onOff) throws Exception {
        this.mService.setBcmWindowLockState(onOff);
    }

    public void setParkLightRelatedFMBLightConfig(int cfg) throws Exception {
        this.mService.setBcmParkLightRelatedFMBLightConfig(cfg);
    }

    public int getParkLightRelatedFMBLightConfigState() throws Exception {
        return this.mService.getBcmParkLightRelatedFMBLightConfigState();
    }

    public int[] getParkingLampsStates() throws Exception {
        return this.mService.getBcmParkingLampsStates();
    }

    public int getDoorUnlockRequestSource() throws Exception {
        return this.mService.getBcmDoorUnlockRequestSource();
    }

    public int getLeftSdcPsdMotorState() throws Exception {
        return this.mService.getBcmLeftSdcPsdMotorState();
    }

    public int getRightSdcPsdMotorState() throws Exception {
        return this.mService.getBcmRightSdcPsdMotorState();
    }

    public int getKeyAuthState() throws Exception {
        return this.mService.getBcmKeyAuthState();
    }

    public int getWiperSpeedSwitchState() throws Exception {
        return this.mService.getBcmWiperSpeedSwitchState();
    }

    public void sendSeatBeltRequest(int req) throws Exception {
        this.mService.sendBcmSeatBeltRequest(req);
    }

    public byte[] getNfcCardIdInfo() throws Exception {
        return this.mService.getBcmNfcCardIdInfo();
    }

    public int getAutoLightState() throws Exception {
        return this.mService.getBcmAutoLightState();
    }

    @Deprecated
    public void setAutoLightSwitch(int onOff) throws Exception {
        this.mService.setAutoLightSwitch(onOff);
    }

    public int getWasherFluidWarning() throws Exception {
        return this.mService.getWasherFluidWarning();
    }

    public int getBcmSeatHeatErrStatus() throws Exception {
        return this.mService.getBcmSeatHeatErrStatus();
    }

    @Deprecated
    public int[] getBcmPassengerSeatBeltSbrWarningSatus() throws Exception {
        return this.mService.getBcmPassengerSeatBeltSbrWarningStatus();
    }

    public int[] getBcmPassengerSeatBeltSbrWarningStatus() throws Exception {
        return this.mService.getBcmPassengerSeatBeltSbrWarningStatus();
    }

    public void setRearLeftSeatHeatSw(int sw) throws Exception {
        this.mService.setBcmRearLeftSeatHeatSw(sw);
    }

    public int getRearLeftSeatHeatState() throws Exception {
        return this.mService.getBcmRearLeftSeatHeatState();
    }

    public void setRearRightSeatHeatSw(int sw) throws Exception {
        this.mService.setBcmRearRightSeatHeatSw(sw);
    }

    public int getRearRightSeatHeatState() throws Exception {
        return this.mService.getBcmRearRightSeatHeatState();
    }

    public int getRearLeftHeaterErrorState() throws Exception {
        return this.mService.getBcmRearLeftHeaterErrorState();
    }

    public int getRearRightHeaterErrorState() throws Exception {
        return this.mService.getBcmRearRightHeaterErrorState();
    }

    public int getSdcKeyOpenCtrlCfg() throws Exception {
        return this.mService.getBcmSdcKeyOpenCtrlCfg();
    }

    public void setSdcKeyOpenCtrlCfg(int cfg) throws Exception {
        this.mService.setBcmSdcKeyOpenCtrlCfg(cfg);
    }

    public int getSdcKeyCloseCtrlCfg() throws Exception {
        return this.mService.getBcmSdcKeyCloseCtrlCfg();
    }

    public void setSdcKeyCloseCtrlCfg(int cfg) throws Exception {
        this.mService.setBcmSdcKeyCloseCtrlCfg(cfg);
    }

    public int getSdcMaxAutoDoorOpeningAngle() throws Exception {
        return this.mService.getBcmSdcMaxAutoDoorOpeningAngle();
    }

    public void setSdcMaxAutoDoorOpeningAngle(int maxAngle) throws Exception {
        this.mService.setBcmSdcMaxAutoDoorOpeningAngle(maxAngle);
    }

    public int getLeftSdcHazzardRequest() throws Exception {
        return this.mService.getBcmLeftSdcHazzardRequest();
    }

    public int getRightSdcHazzardRequest() throws Exception {
        return this.mService.getBcmRightSdcHazzardRequest();
    }

    public int getLeftSdcSystemErrorState() throws Exception {
        return this.mService.getBcmLeftSdcSystemErrorState();
    }

    public int getRightSdcSystemErrorState() throws Exception {
        return this.mService.getBcmRightSdcSystemErrorState();
    }

    public int getLeftSdcDenormalizeState() throws Exception {
        return this.mService.getBcmLeftSdcDenormalizeState();
    }

    public int getRightSdcDenormalizeState() throws Exception {
        return this.mService.getBcmRightSdcDenormalizeState();
    }

    public void setLeftSdcWindowsAutoDownSwitch(int sw) throws Exception {
        this.mService.setBcmLeftSdcWindowsAutoDownSwitch(sw);
    }

    public int getLeftSdcWindowsAutoDownSwitchState() throws Exception {
        return this.mService.getBcmLeftSdcWindowsAutoDownSwitchState();
    }

    public void setRightSdcWindowsAutoDownSwitch(int sw) throws Exception {
        this.mService.setBcmRightSdcWindowsAutoDownSwitch(sw);
    }

    public int getRightSdcWindowsAutoDownSwitchState() throws Exception {
        return this.mService.getBcmRightSdcWindowsAutoDownSwitchState();
    }

    public void setLeftSdcAutoControl(int state) throws Exception {
        this.mService.setBcmLeftSdcAutoOrManualControl(1, state);
    }

    public void setLeftSdcManualControl(int control, int state) throws Exception {
        this.mService.setBcmLeftSdcAutoOrManualControl(control, state + 3);
    }

    public void setRightSdcAutoControl(int state) throws Exception {
        this.mService.setBcmRightSdcAutoOrManualControl(1, state);
    }

    public void setRightSdcManualControl(int control, int state) throws Exception {
        this.mService.setBcmRightSdcAutoOrManualControl(control, state + 3);
    }

    public int getReadyEnableState() throws Exception {
        return this.mService.getBcmReadyEnableState();
    }

    public int getBreakPedalStatus() throws Exception {
        return this.mService.getBcmBreakPedalStatus();
    }

    public void setLeftSdcDoorPosition(int position) throws Exception {
        this.mService.setBcmLeftSdcDoorPosition(position);
    }

    public int getLeftSdcDoorPosition() throws Exception {
        return this.mService.getBcmLeftSdcDoorPosition();
    }

    public void setRightSdcDoorPosition(int position) throws Exception {
        this.mService.setBcmRightSdcDoorPosition(position);
    }

    public int getRightSdcDoorPosition() throws Exception {
        return this.mService.getBcmRightSdcDoorPosition();
    }

    public int getNfcCardAuthStatus() throws Exception {
        return this.mService.getBcmNfcCardAuthStatus();
    }

    public void setWindowsAutoUp(int type) throws Exception {
        this.mService.setBcmAutoWindowsControl(1, type);
    }

    public int[] getDaytimeRunningLightsOutputStatus() throws Exception {
        return this.mService.getBcmDaytimeRunningLightsOutputStatus();
    }

    public int getEnvironmentMode() throws Exception {
        return this.mService.getBcmEnvironmentMode();
    }

    public int getTrunkDoorHeight() throws Exception {
        return this.mService.getBcmTrunkDoorHeight();
    }

    public void setCwcSwitch(int enable) throws Exception {
        this.mService.setBcmCwcSwitch(enable);
    }

    public int getCwcSwitchState() throws Exception {
        return this.mService.getBcmCwcSwitchState();
    }

    public void setFRCwcSwitch(int enable) throws Exception {
        this.mService.setBcmFRCwcSwitch(enable);
    }

    public int getFRCwcSwitchState() throws Exception {
        return this.mService.getBcmFRCwcSwitchState();
    }

    public int getLeftSdcMoveCommand() throws Exception {
        return this.mService.getBcmLeftSdcMoveCommand();
    }

    public int getRightSdcMoveCommand() throws Exception {
        return this.mService.getBcmRightSdcMoveCommand();
    }

    public void setShadeControllerComfortCommand(int cmd) throws Exception {
        this.mService.setBcmShadeControllerComfortCommand(cmd);
    }

    public void setShadeControllerPosition(int position) throws Exception {
        this.mService.setBcmShadeControllerPosition(position);
    }

    public int getShadeControllerPosition() throws Exception {
        return this.mService.getBcmShadeControllerPosition();
    }

    public int getShadeControllerMotorStatus() throws Exception {
        return this.mService.getBcmShadeControllerMotorStatus();
    }

    public void setShadeControllerInitialization(int sw) throws Exception {
        this.mService.setBcmShadeControllerInitialization(sw);
    }

    public int getShadeControllerInitializationSt() throws Exception {
        return this.mService.getBcmShadeControllerInitializationSt();
    }

    public int[] getWiperSpeedSwitchesStatus() throws Exception {
        return this.mService.getBcmWiperSpeedSwitchesStatus();
    }

    public int getLeftSdcSystemRunningState() throws Exception {
        return this.mService.getBcmLeftSdcSystemRunningState();
    }

    public int getRightSdcSystemRunningState() throws Exception {
        return this.mService.getBcmRightSdcSystemRunningState();
    }

    public int[] getRearViewMirrorsAdjustStates() throws Exception {
        return this.mService.getBcmRearViewMirrorsAdjustStates();
    }

    public void setSaberLightSw(int sw) throws Exception {
        this.mService.setBcmSaberLightSw(sw);
    }

    public int getSaberLightSwitchStatus() throws Exception {
        return this.mService.getBcmSaberLightSwitchStatus();
    }

    public int getMaintainModeSwitchStatus() throws Exception {
        return this.mService.getBcmMaintainModeSwitchStatus();
    }

    public void setMaintainModeSw(int sw) throws Exception {
        this.mService.setBcmMaintainModeSw(sw);
    }

    public int getScSwitchStatus() throws Exception {
        return this.mService.getBcmScSwitchStatus();
    }

    public int getScEcuStatus() throws Exception {
        return this.mService.getBcmScEcuStatus();
    }

    public int getScThermalProtectStatus() throws Exception {
        return this.mService.getBcmScThermalProtectSt();
    }

    public int getScAntiPinchStatus() throws Exception {
        return this.mService.getBcmScAntiPinchStatus();
    }

    public int getScIceBreakMode() throws Exception {
        return this.mService.getBcmScIceBreakMode();
    }

    public int getScLinStatus() throws Exception {
        return this.mService.getBcmScLinStatus();
    }

    @Deprecated
    public void setHeightLvlConfigValue(int config) throws Exception {
        this.mService.setBcmHeightLvlConfigValue(config);
    }

    public void setTargetAsHeightLvlConfigValue(int config) throws Exception {
        this.mService.setBcmTargetAsHeightLvlConfigValue(config);
    }

    @Deprecated
    public int getHeightLvlConfigValue() throws Exception {
        return this.mService.getBcmHeightLvlConfigValue();
    }

    public int getTargetAsHeightLvlConfigValue() throws Exception {
        return this.mService.getBcmTargetAsHeightLvlConfigValue();
    }

    public int getActualAsHeightLvlConfigValue() throws Exception {
        return this.mService.getBcmActualAsHeightLvlConfigValue();
    }

    public void setSoftLvlConfigValue(int config) throws Exception {
        this.mService.setBcmSoftLvlConfigValue(config);
    }

    public int getSoftLvlConfigValue() throws Exception {
        return this.mService.getBcmSoftLvlConfigValue();
    }

    public int getHandleAutoState() throws Exception {
        return this.mService.getBcmHandleAutoState();
    }

    public void setHandleAutoSwitch(int onOff) throws Exception {
        this.mService.setBcmHandleAutoSwitch(onOff);
    }

    public int getEasyLoadingState() throws Exception {
        return this.mService.getBcmEasyLoadingState();
    }

    public void setEasyLoadingSwitch(int onOff) throws Exception {
        this.mService.setBcmEasyLoadingSwitch(onOff);
    }

    public int getSuspenWelcomeSwitchState() throws Exception {
        return this.mService.getBcmSuspenWelcomeSwitchState();
    }

    public void setSuspenWelcomeSwitch(int onOff) throws Exception {
        this.mService.setBcmSuspenWelcomeSwitch(onOff);
    }

    public int[] getLRMirrorHeatSwitchStatus() throws Exception {
        return this.mService.getBcmLRMirrorHeatSwitchStatus();
    }

    public void setFrontMirrorHeatSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmFrontMirrorHeatSwitchStatus(onOff);
    }

    public int getFrontMirrorHeatSwitchStatus() throws Exception {
        return this.mService.getBcmFrontMirrorHeatSwitchStatus();
    }

    public void setRearWiperServiceSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmRearWiperServiceSwitchStatus(onOff);
    }

    public int getRearWiperServiceSwitchStatus() throws Exception {
        return this.mService.getBcmRearWiperServiceSwitchStatus();
    }

    public void setSteeringWheelHeatingStatus(int status) throws Exception {
        this.mService.setBcmSteeringWheelHeatingStatus(status);
    }

    public int getSteeringWheelHeatingStatus() throws Exception {
        return this.mService.getBcmSteeringWheelHeatingStatus();
    }

    public void setLeftChildLockSwitchStatus(int status) throws Exception {
        this.mService.setBcmLeftChildLockSwitchStatus(status);
    }

    public int getLeftChildLockSwitchStatus() throws Exception {
        return this.mService.getBcmLeftChildLockSwitchStatus();
    }

    public void setRightChildLockSwitchStatus(int status) throws Exception {
        this.mService.setBcmRightChildLockSwitchStatus(status);
    }

    public int getRightChildLockSwitchStatus() throws Exception {
        return this.mService.getBcmRightChildLockSwitchStatus();
    }

    public void setLockHazardLightSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmLockHazardLightSwitchStatus(onOff);
    }

    public int getLockHazardLightSwitchStatus() throws Exception {
        return this.mService.getBcmLockHazardLightSwitchStatus();
    }

    public void setLockHornSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmLockHornSwitchStatus(onOff);
    }

    public int getLockHornSwitchStatus() throws Exception {
        return this.mService.getBcmLockHornSwitchStatus();
    }

    public void setLockAvasSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmLockAvasSwitchStatus(onOff);
    }

    public int getLockAvasSwitchStatus() throws Exception {
        return this.mService.getBcmLockAvasSwitchStatus();
    }

    public void setDomeLightModeStatus(int status) throws Exception {
        this.mService.setBcmDomeLightModeStatus(status);
    }

    public int getDomeLightModeStatus() throws Exception {
        return this.mService.getBcmDomeLightModeStatus();
    }

    public void setDomeLightBrightLevel(int level) throws Exception {
        this.mService.setBcmDomeLightBrightLevel(level);
    }

    public int getDomeLightBrightLevel() throws Exception {
        return this.mService.getBcmDomeLightBrightLevel();
    }

    public void setFrontLeftDomeLightSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmFrontLeftDomeLightSwitchStatus(onOff);
    }

    public int getFrontLeftDomeLightSwitchStatus() throws Exception {
        return this.mService.getBcmFrontLeftDomeLightSwitchStatus();
    }

    public void setFrontRightDomeLightSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmFrontRightDomeLightSwitchStatus(onOff);
    }

    public int getFrontRightDomeLightSwitchStatus() throws Exception {
        return this.mService.getBcmFrontRightDomeLightSwitchStatus();
    }

    public void setRearLeftDomeLightSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmRearLeftDomeLightSwitchStatus(onOff);
    }

    public int getRearLeftDomeLightSwitchStatus() throws Exception {
        return this.mService.getBcmRearLeftDomeLightSwitchStatus();
    }

    public void setRearRightDomeLightSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmRearRightDomeLightSwitchStatus(onOff);
    }

    public int getRearRightDomeLightSwitchStatus() throws Exception {
        return this.mService.getBcmRearRightDomeLightSwitchStatus();
    }

    public void setTrdLeftDomeLightSwitchStatus(int onOff) throws Exception {
        this.mService.setTrdLeftDomeLightSwitchStatus(onOff);
    }

    public int getTrdLeftDomeLightSwitchStatus() throws Exception {
        return this.mService.getTrdLeftDomeLightSwitchStatus();
    }

    public void setTrdRightDomeLightSwitchStatus(int onOff) throws Exception {
        this.mService.setTrdRightDomeLightSwitchStatus(onOff);
    }

    public int getTrdRightDomeLightSwitchStatus() throws Exception {
        return this.mService.getTrdRightDomeLightSwitchStatus();
    }

    public void setFootKickSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmFootKickSwitchStatus(onOff);
    }

    public int getFootKickSwitchStatus() throws Exception {
        return this.mService.getBcmFootKickSwitchStatus();
    }

    public void setWashCarModeSwitchStatus(int onOff) throws Exception {
        this.mService.setBcmWashCarModeSwitchStatus(onOff);
    }

    public void setHeadLampLevelingReqValue(int level) throws Exception {
        this.mService.setBcmHeadLampLevelingReqValue(level);
    }

    public int getHeadLampLevelingReqValue() throws Exception {
        return this.mService.getBcmHeadLampLevelingReqValue();
    }

    public int getHeadLampLevelingCtrlMode() throws Exception {
        return this.mService.getBcmHeadLampLevelingCtrlMode();
    }

    @Deprecated
    public int getHeadLampCtrlLevel() throws Exception {
        return this.mService.getBcmHeadLampCtrlLevel();
    }

    public void setTrunkOpenRequestPosition(int position) throws Exception {
        this.mService.setBcmTrunkOpenRequestPosition(position);
    }

    public void setTrunkSetPositionRequest(int position) throws Exception {
        this.mService.setBcmTrunkSetPositionRequest(position);
    }

    public int getTrunkSetPositionResponcePosition() throws Exception {
        return this.mService.getBcmTrunkSetPositionResponcePosition();
    }

    public int getTemporaryStopLockActivateStatus() throws Exception {
        return this.mService.getBcmTemporaryStopLockActivateStatus();
    }

    public void setTrailerHitchSwitchStatus(int status) throws Exception {
        this.mService.setBcmTrailerHitchSwitchStatus(status);
    }

    public int getTrailerHitchSwitchStatus() throws Exception {
        return this.mService.getBcmTrailerHitchSwitchStatus();
    }

    public int[] getLeftRightRearMirrorFoldOutputStatus() throws Exception {
        return this.mService.getBcmLeftRightRearMirrorFoldOutputStatus();
    }

    public void setEngineeringModeStatus(int status) throws Exception {
        this.mService.setBcmEngineeringModeStatus(status);
    }

    public int getEngineeringModeStatus() throws Exception {
        return this.mService.getBcmEngineeringModeStatus();
    }

    public void setTransportModeSwitchStatus(int status) throws Exception {
        this.mService.setBcmTransportModeSwitchStatus(status);
    }

    public void setAsTrailerModeSwitchStatus(int status) throws Exception {
        this.mService.setBcmAsTrailerModeSwitchStatus(status);
    }

    public void setColumnVerticalMove(int control, int direction) throws Exception {
        this.mService.setBcmColumnVerticalMove(control, direction);
    }

    @Deprecated
    public void setColumnVerticalPosition(int pos) throws Exception {
        this.mService.setBcmColumnVerticalPosition(pos);
    }

    public int getColumnVerticalPosition() throws Exception {
        return this.mService.getBcmColumnVerticalPosition();
    }

    public void setColumnHorizonalMove(int control, int direction) throws Exception {
        this.mService.setBcmColumnHorizonalMove(control, direction);
    }

    @Deprecated
    public void setColumnHorizonalPosition(int pos) throws Exception {
        this.mService.setBcmColumnHorizonalPosition(pos);
    }

    public int getColumnHorizonalPosition() throws Exception {
        return this.mService.getBcmColumnHorizonalPosition();
    }

    public int[] getWindowsInitSignalLostRequestStatus() throws Exception {
        return this.mService.getBcmWindowsInitSignalLostRequestStatus();
    }

    public int getTrunkOpennerStatus() throws Exception {
        return this.mService.getBcmTrunkOpennerStatus();
    }

    public void setColumnPositionMove(int vertpos, int horpos) throws Exception {
        this.mService.setBcmColumnPositionMove(vertpos, horpos);
    }

    public void setSwsControlSceneStatus(int status) throws Exception {
        this.mService.setBcmSwsControlSceneStatus(status);
    }

    public int getRearWiperMotorStatus() throws Exception {
        return this.mService.getBcmRearWiperMotorStatus();
    }

    public void setCustomerModeFlagSwitchStatus(int status) throws Exception {
        this.mService.setBcmCustomerModeFlagSwitchStatus(status);
    }

    public int getAsAutoLevelingResult() throws Exception {
        return this.mService.getBcmAsAutoLevelingResult();
    }

    public int getTtmDenormalizeStatus() throws Exception {
        return this.mService.getBcmTtmDenormalizeStatus();
    }

    public int getTtmSystemErrorStatus() throws Exception {
        return this.mService.getBcmTtmSystemErrorStatus();
    }

    public void setAsCampingModeSwitchStatus(int status) throws Exception {
        this.mService.setBcmAsCampingModeSwitchStatus(status);
    }

    public int getAsCampingModeSwitchStatus() throws Exception {
        return this.mService.getBcmAsCampingModeSwitchStatus();
    }

    public int getAsHoistModeSwitchStatus() throws Exception {
        return this.mService.getBcmHoistModeSwitchStatus();
    }

    public int getAsYellowLampRequest() throws Exception {
        return this.mService.getBcmAsYellowLampRequest();
    }

    public int getAsRedLampRequest() throws Exception {
        return this.mService.getBcmAsRedLampRequest();
    }

    public void setColumnPositionSaveToMcu(int vertpos, int horpos) throws Exception {
        this.mService.setBcmColumnPositionSaveToMcu(vertpos, horpos);
    }

    @Deprecated
    public void setTrailerModeSwitchStatus(int status) throws Exception {
        this.mService.setBcmTrailerModeSwitchStatus(status);
    }

    public void setTrailerModeStatus(int status) throws Exception {
        this.mService.setBcmTrailerModeStatus(status);
    }

    public int getAsLockModeStatus() throws Exception {
        return this.mService.getBcmAsLockModeStatus();
    }

    public void setAsDrivingMode(int mode) throws Exception {
        this.mService.setBcmAsDrivingMode(mode);
    }

    public int getAsDrivingMode() throws Exception {
        return this.mService.getBcmAsDrivingMode();
    }

    @Deprecated
    public void setAsSpecialDrivingMode(int mode) throws Exception {
        this.mService.setBcmAsSpecialDrivingMode(mode);
    }

    public int getAsWelcomeModeStatus() throws Exception {
        return this.mService.getBcmAsWelcomeModeStatus();
    }

    public int getAsEspPataRequestStatus() throws Exception {
        return this.mService.getBcmAsEspPataRequestStatus();
    }

    @Deprecated
    public int getAsTargetHeight() throws Exception {
        return this.mService.getBcmAsTargetHeight();
    }

    public int getAsHeightChangingStatus() throws Exception {
        return this.mService.getBcmAsHeightChangingStatus();
    }

    public int getAsModeAllowedCampingStatus() throws Exception {
        return this.mService.getBcmAsModeAllowedCampingStatus();
    }

    public int getFWiperMotorErr() throws Exception {
        return this.mService.getBcmFWiperMotorErr();
    }

    public int getsdcBrakeCloseDoorCfg() throws Exception {
        return this.mService.getBcmSdcBrakeCloseDoorCfg();
    }

    public void setsdcBrakeCloseDoorCfg(int cfg) throws Exception {
        this.mService.setBcmSdcBrakeCloseDoorCfg(cfg);
    }

    public int getCoverPlateStatus() throws Exception {
        return this.mService.getBcmCoverPlateStatus();
    }

    public void setGroupLedControlStatus(int upStatus, int downStatus, int leftStatus, int rightStatus) throws Exception {
        this.mService.setBcmGroupLedControlStatus(upStatus, downStatus, leftStatus, rightStatus);
    }

    public int[] getGroupLedControlStatus() throws Exception {
        return this.mService.getBcmGroupLedControlStatus();
    }

    public void setGroupLedColor(int upColor, int downColor, int leftColor, int rightColor) throws Exception {
        this.mService.setBcmGroupLedColor(upColor, downColor, leftColor, rightColor);
    }

    public int[] getGroupLedColor() throws Exception {
        return this.mService.getBcmGroupLedColor();
    }

    public void setGroupLedFadeTime(int upTime, int downTime, int leftTime, int rightTime) throws Exception {
        this.mService.setBcmGroupLedFadeTime(upTime, downTime, leftTime, rightTime);
    }

    public void setGroupLedTemperature(int upTemp, int downTemp, int leftTemp, int rightTemp) throws Exception {
        this.mService.setBcmGroupLedTemperature(upTemp, downTemp, leftTemp, rightTemp);
    }

    public int[] getGroupLedTemperature() throws Exception {
        return this.mService.getBcmGroupLedTemperature();
    }

    public void setGroupLedBrigntness(int upLux, int downLux, int leftLux, int rightLux) throws Exception {
        this.mService.setBcmGroupLedBrigntness(upLux, downLux, leftLux, rightLux);
    }

    public int[] getGroupLedBrigntness() throws Exception {
        return this.mService.getBcmGroupLedBrigntness();
    }

    public int[] getXPortAsSystemAllStatus() throws Exception {
        return this.mService.getBcmXPortAsSystemAllStatus();
    }

    public int[] getAsWheelPositionHeightAll() throws Exception {
        return this.mService.getBcmAsWheelPositionHeightAll();
    }

    public float[] getAsAcceleratedSpeed() throws Exception {
        return this.mService.getBcmAsAcceleratedSpeed();
    }

    @Deprecated
    public void setAsLeopardModeSwitchStatus(int sw) throws Exception {
        this.mService.setBcmAsLeopardModeSwitchStatus(sw);
    }

    @Deprecated
    public int getAsLeopardModeSwitchStatus() throws Exception {
        return this.mService.getBcmAsLeopardModeSwitchStatus();
    }

    @Deprecated
    public void setAsVehicleMode(int mode) throws Exception {
        this.mService.setBcmAsVehicleMode(mode);
    }

    public int getTrunkActualPosition() throws Exception {
        return this.mService.getBcmTrunkActualPosition();
    }

    public void setSecRowSeatEasyEntrySwitchStatus(int sw) throws Exception {
        this.mService.setBcmSecRowSeatEasyEntrySwitchStatus(sw);
    }

    public void setMirrorAutoFoldSwitchStatus(int sw) throws Exception {
        this.mService.setBcmMirrorAutoFoldSwitchStatus(sw);
    }

    public int getMirrorAutoFoldSwitchStatus() throws Exception {
        return this.mService.getBcmMirrorAutoFoldSwitchStatus();
    }

    public int getAsAutoLevelingResultValue() throws Exception {
        return this.mService.getBcmAsAutoLevelingResultValue();
    }

    public void setXsleepModeStatus(int active) throws Exception {
        this.mService.setBcmXsleepModeStatus(active);
    }

    public void setXmovieModeStatus(int active) throws Exception {
        this.mService.setBcmXmovieModeStatus(active);
    }

    public void setX5dCinemaModeStatus(int active) throws Exception {
        this.mService.setBcmX5dCinemaModeStatus(active);
    }

    public void setXmeditationModeStatus(int active) throws Exception {
        this.mService.setBcmXmeditationModeStatus(active);
    }

    public void setCMSAutoBrightSw(int sw) throws Exception {
        this.mService.setLCMSAutoBrightSw(sw);
    }

    public int getCMSAutoBrightSwSt() throws Exception {
        return this.mService.getLCMSAutoBrightSwSt();
    }

    @Deprecated
    public void setCMSBright(int level) throws Exception {
        this.mService.setLCMSBright(level);
    }

    public void setCMSBrightWithFlag(int value, int flag) throws Exception {
        int[] data = {value, flag};
        this.mService.setLCMSBrightWithStoreflag(data);
    }

    public int[] getCMSBrightWithSource() throws Exception {
        return this.mService.getLCMSBrightWithSource();
    }

    public void setCMSHighSpeedViewSw(int sw) throws Exception {
        this.mService.setLCMSHighSpeedViewSw(sw);
    }

    public int getCMSHighSpeedViewSwSt() throws Exception {
        return this.mService.getLCMSHighSpeedViewSwSt();
    }

    public void setCMSLowSpeedViewSw(int sw) throws Exception {
        this.mService.setLCMSLowSpeedViewSw(sw);
    }

    public int getCMSLowSpeedViewSwSt() throws Exception {
        return this.mService.getLCMSLowSpeedViewSwSt();
    }

    public void setCMSDanObjectRecSw(int sw) throws Exception {
        this.mService.setLCMSDanObjectRecSw(sw);
    }

    public int getCMSDanObjectRecSwSt() throws Exception {
        return this.mService.getLCMSDanObjectRecSwSt();
    }

    public void setCMSReverseAssitSw(int sw) throws Exception {
        this.mService.setLCMSReverseAssitSw(sw);
    }

    public int getCMSReverseAssitSwSt() throws Exception {
        return this.mService.getLCMSReverseAssitSwSt();
    }

    public void setCMSTurnExtSw(int sw) throws Exception {
        this.mService.setLCMSTurnExtSw(sw);
    }

    public int getCMSTurnExtSwSt() throws Exception {
        return this.mService.getLCMSTurnExtSwSt();
    }

    public void setCMSViewRecovery(int enable) throws Exception {
        this.mService.setLCMSViewRecovery(enable);
    }

    public int getCMSViewRecoverySt() throws Exception {
        return this.mService.getLCMSViewRecoverySt();
    }

    public void setCMSViewAngle(int type) throws Exception {
        this.mService.setLRCMSViewAngle(type);
    }

    public int getCMSViewAngle() throws Exception {
        return this.mService.getLRCMSViewAngle();
    }

    public void sendCMSLogCtrlReq(int channel, int req) throws Exception {
        if (channel == 1) {
            this.mService.sendLCMSLogCtrlReq(req);
        } else if (channel == 2) {
            this.mService.sendRCMSLogCtrlReq(req);
        } else if (channel == 3) {
            this.mService.sendLCMSLogCtrlReq(req);
            this.mService.sendRCMSLogCtrlReq(req);
        }
    }

    public void setARSWorkingMode(int mode) throws Exception {
        this.mService.setArsWorkingMode(mode);
    }

    public int getARSWorkingMode() throws Exception {
        return this.mService.getArsWorkingMode();
    }

    public void setARSFoldOrUnfold(int type) throws Exception {
        this.mService.setArsFoldOrUnfold(type);
    }

    public int getARSWorkingState() throws Exception {
        return this.mService.getArsWorkingState();
    }

    public int getARSPosition() throws Exception {
        return this.mService.getArsPosition();
    }

    public int getARSInitState() throws Exception {
        return this.mService.getArsInitState();
    }

    public void setARSInitState(int st) throws Exception {
        this.mService.setArsInitState(st);
    }

    public int getARSFaultState() throws Exception {
        return this.mService.getArsFaultState();
    }

    public int getTtmLampConnectStatus() throws Exception {
        return this.mService.getBcmTtmLampConnectStatus();
    }

    public int getTtmLampFaultStatus() throws Exception {
        return this.mService.getBcmTtmLampFaultStatus();
    }

    public int getTtmHookMotorStatus() throws Exception {
        return this.mService.getBcmTtmHookMotorStatus();
    }

    public int getFrontWiperActiveStatus() throws Exception {
        return this.mService.getBcmFrontWiperActiveStatus();
    }

    public int[] getAllWindowsActionFeedbackStatus() throws Exception {
        return this.mService.getBcmAllWindowsActionFeedbackStatus();
    }

    public void setLeftSlideDoorMode(int mode) throws Exception {
        this.mService.setLeftSlideDoorMode(mode);
    }

    public int getLeftSlideDoorMoode() throws Exception {
        return this.mService.getLeftSlideDoorMoode();
    }

    public int getLeftSlideDoorStatus() throws Exception {
        return this.mService.getLeftSlideDoorStatus();
    }

    public void setLeftSlideDoorCtrl(int Ctrl) throws Exception {
        this.mService.setLeftSlideDoorCtrl(Ctrl);
    }

    public int getLeftSlideDoorLockSt() throws Exception {
        return this.mService.getLeftSlideDoorLockSt();
    }

    public void setRightSlideDoorMode(int mode) throws Exception {
        this.mService.setRightSlideDoorMode(mode);
    }

    public int getRightSlideDoorMoode() throws Exception {
        return this.mService.getRightSlideDoorMoode();
    }

    public int getRightSlideDoorStatus() throws Exception {
        return this.mService.getRightSlideDoorStatus();
    }

    public void setRightSlideDoorCtrl(int Ctrl) throws Exception {
        this.mService.setRightSlideDoorCtrl(Ctrl);
    }

    public int getRightSlideDoorLockSt() throws Exception {
        return this.mService.getRightSlideDoorLockSt();
    }

    public int getRearLogLight() throws Exception {
        return this.mService.getRearLogLight();
    }

    public void setRearLogLight(int onOff) throws Exception {
        this.mService.setRearLogLight(onOff);
    }

    public int getTrunkWorkModeStatus() throws Exception {
        return this.mService.getBcmTrunkWorkModeStatus();
    }

    public int getLowBeamOffConfirmSt() throws Exception {
        return this.mService.getBcmLowBeamOffConfirmSt();
    }

    public void setLowBeamOffConfirmSt(int st) throws Exception {
        this.mService.setBcmLowBeamOffConfirmSt(st);
    }

    public void setCFPowerSwitch(int onOff) throws Exception {
        this.mService.setCFPowerSwitch(onOff);
    }

    public int getCFPowerState() throws Exception {
        return this.mService.getCFPowerState();
    }

    public void setCarFridgeDoorCtrl(int onOff) throws Exception {
        this.mService.setCarFridgeDoorCtrl(onOff);
    }

    public int getCarFridgeDoorState() throws Exception {
        return this.mService.getCarFridgeDoorState();
    }

    public void setCFTempInc() throws Exception {
        this.mService.setCFTempInc();
    }

    public void setFCTempDec() throws Exception {
        this.mService.setFCTempDec();
    }

    public void setCFTempValue(int value) throws Exception {
        this.mService.setCFTempValue(value);
    }

    public int getCFTempValue() throws Exception {
        return this.mService.getCFTempValue();
    }

    public void setCFWorkMode(int mode) throws Exception {
        this.mService.setCFWorkMode(mode);
    }

    public int getCFWorkMode() throws Exception {
        return this.mService.getCFWorkMode();
    }

    public void setCFChildLock(int onOff) throws Exception {
        this.mService.setCFChildLock(onOff);
    }

    public int getCFChildLockState() throws Exception {
        return this.mService.getCFChildLockState();
    }

    public void setCFKeepTempSwitch(int onOff) throws Exception {
        this.mService.setCFKeepTempSwitch(onOff);
    }

    public int getCFKeepTempState() throws Exception {
        return this.mService.getCFKeepTempState();
    }

    public void setCFKeepTempTime(int value) throws Exception {
        this.mService.setCFKeepTempTime(value);
    }

    public int getCFKeepTempTime() throws Exception {
        return this.mService.getCFKeepTempTime();
    }

    public int getCFKeepTempRemainTime() throws Exception {
        return this.mService.getCFKeepTempRemainTime();
    }

    public void setCFKeepTempTimeMemoryRequest(int onOff) throws Exception {
        this.mService.setCFKeepTempTimeMemoryRequest(onOff);
    }

    public int getCFKeepTempWorkState() throws Exception {
        return this.mService.getCFKeepTempWorkState();
    }

    public void setCarpetLightWelcomeSw(int onOff) throws Exception {
        this.mService.setCarpetLightWelcomeSw(onOff);
    }

    public int getCarpetLightWelcomeState() throws Exception {
        return this.mService.getCarpetLightWelcomeState();
    }

    public void setPollingWelcomeSW(int onOff) throws Exception {
        this.mService.setPollingWelcomeSW(onOff);
    }

    public int getPollingWelcomeState() throws Exception {
        return this.mService.getPollingWelcomeState();
    }

    public void setSfmCtrl(int ctrl) throws Exception {
        this.mService.setSfmCtrl(ctrl);
    }

    public int getSfmCtrlState() throws Exception {
        return this.mService.getSfmCtrlState();
    }

    public void setSfmAnglePos(int angel) throws Exception {
        this.mService.setSfmAnglePos(angel);
    }

    public int getSfmAnglePos() throws Exception {
        return this.mService.getSfmAnglePos();
    }

    public void setImsModeReq(int mode) throws Exception {
        this.mService.setImsModeReq(mode);
    }

    public int getImsModeState() throws Exception {
        return this.mService.getImsModeState();
    }

    public void setImsAutoVisionSw(int onOff) throws Exception {
        this.mService.setImsAutoVisionSw(onOff);
    }

    public int getImsAutoVisionSt() throws Exception {
        return this.mService.getImsAutoVisionSt();
    }

    public void setImsBrightLevel(int level) throws Exception {
        this.mService.setImsBrightLevel(level);
    }

    public int getImsBrightLevel() throws Exception {
        return this.mService.getImsBrightLevel();
    }

    public void setImsVisionCtrl(int control, int direction) throws Exception {
        this.mService.setImsVisionCtrl(control, direction);
    }

    public int getImsVisionVerticalLevel() throws Exception {
        return this.mService.getImsVisionVerticalLevel();
    }

    public int getImsVisionAngleLevl() throws Exception {
        return this.mService.getImsVisionAngleLevl();
    }

    public int getImsSystemSt() throws Exception {
        return this.mService.getImsSystemSt();
    }

    public void setRLCwcSwitch(int enable) throws Exception {
        this.mService.setBcmRLCwcSwitch(enable);
    }

    public int getRLCwcSwitchState() throws Exception {
        return this.mService.getBcmRLCwcSwitchState();
    }

    public int getRLCwcChargeSt() throws Exception {
        return this.mService.getRLCwcChargeSt();
    }

    public int getRLCwcChargeErrorSt() throws Exception {
        return this.mService.getRLCwcChargeErrorSt();
    }

    public void setRRCwcSwitch(int enable) throws Exception {
        this.mService.setBcmRRCwcSwitch(enable);
    }

    public int getRRCwcSwitchState() throws Exception {
        return this.mService.getBcmRRCwcSwitchState();
    }

    public int getRRCwcChargeSt() throws Exception {
        return this.mService.getRRCwcChargeSt();
    }

    public int getRRCwcChargeErrorSt() throws Exception {
        return this.mService.getRRCwcChargeErrorSt();
    }

    public int getBcmPowerOffSource() throws Exception {
        return this.mService.getBcmPowerOffSource();
    }
}
