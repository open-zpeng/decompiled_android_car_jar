package android.car.hardware.scu;

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

@SystemApi
/* loaded from: classes.dex */
public final class CarScuManager extends CarEcuManager {
    public static final int AEB_STATUS_ACTIVE_TO_CAR = 1;
    public static final int AEB_STATUS_ACTIVE_TO_PED = 2;
    public static final int AEB_STATUS_INACTIVE = 0;
    public static final int ALC_BANNED_DUE_TO_VEHICLE_SPEED = 5;
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
    public static final int CAM_FAILURE_STATUS_BLOCKED = 1;
    public static final int CAM_FAILURE_STATUS_FAILURE = 2;
    public static final int CAM_FAILURE_STATUS_MISALIGNMENT = 3;
    public static final int CAM_FAILURE_STATUS_NO_FAILURE = 0;
    private static final boolean DBG = false;
    public static final int DISABLE_PARK_BY_MEMORY = 1;
    public static final int DISABLE_PARK_BY_MEMORY_WITH_LICENSE = 2;
    public static final int DISABLE_PARK_BY_RADAR = 5;
    public static final int DISABLE_PARK_BY_TTM = 4;
    public static final int DMS_MODE_EXTENDED = 1;
    public static final int DMS_MODE_NORMAL = 0;
    public static final int DOOR_OBSTACLE_DETECTION_STATUS_DANGER = 1;
    public static final int DOOR_OBSTACLE_DETECTION_STATUS_SAFETY = 2;
    public static final int DOOR_OBSTACLE_DETECTION_STATUS_UNKNOWN = 3;
    public static final int DSM_NO_PROMPT = 0;
    public static final int DSM_PROMPT_1 = 1;
    public static final int DSM_PROMPT_10 = 10;
    public static final int DSM_PROMPT_11 = 11;
    public static final int DSM_PROMPT_12 = 12;
    public static final int DSM_PROMPT_13 = 13;
    public static final int DSM_PROMPT_14 = 14;
    public static final int DSM_PROMPT_15 = 15;
    public static final int DSM_PROMPT_2 = 2;
    public static final int DSM_PROMPT_3 = 3;
    public static final int DSM_PROMPT_4 = 4;
    public static final int DSM_PROMPT_5 = 5;
    public static final int DSM_PROMPT_6 = 6;
    public static final int DSM_PROMPT_7 = 7;
    public static final int DSM_PROMPT_8 = 8;
    public static final int DSM_PROMPT_9 = 9;
    public static final int DSM_ST_BLOCK = 5;
    public static final int DSM_ST_CNOK = 2;
    public static final int DSM_ST_FAULT = 4;
    public static final int DSM_ST_NORMAL = 1;
    public static final int DSM_ST_NO_DRI = 3;
    public static final int DSM_ST_OFF = 0;
    public static final int DSM_ST_STARTING = 6;
    public static final int DSM_SWITCH_OFF = 0;
    public static final int DSM_SWITCH_ON = 1;
    public static final int EBA_STATUS_ACTIVE_TO_CAR = 1;
    public static final int EBA_STATUS_ACTIVE_TO_PED = 2;
    public static final int EBA_STATUS_INACTIVE = 0;
    public static final int ENABLE_PARK_BY_MEMORY = 2;
    public static final int ENABLE_PARK_BY_MEMORY_WITH_LICENSE = 1;
    public static final int EPS_POWER_ASSISTED_COMFORT = 2;
    public static final int EPS_POWER_ASSISTED_SPORT = 3;
    public static final int EPS_POWER_ASSISTED_STANDARD = 1;
    public static final int EXTRA_LAT_CTRL_REMIND_NO_DISPLAY = 0;
    public static final int FCW_STATUS_ACUTE_WARNING_TO_CAR = 4;
    public static final int FCW_STATUS_LATENT_WARNING = 1;
    public static final int FCW_STATUS_NO_WARNING = 0;
    public static final int FCW_STATUS_PREWARNING_TO_CAR = 2;
    public static final int FCW_STATUS_PREWARNING_TO_PED = 3;
    public static final int FIND_CARPORT = 1;
    public static final int HANDS_OFF_CALIBRATION_FAIL = 15;
    public static final int HANDS_OFF_CALIBRATION_FINISH = 14;
    public static final int HANDS_OFF_CALIBRATION_ING = 13;
    public static final int ID_CDU_NAVI_ROAD_LINK_TYPE = 557852396;
    @Deprecated
    public static final int ID_DOOR_OPEN_WARING = 557852168;
    public static final int ID_DOOR_OPEN_WARNING = 557852168;
    public static final int ID_HIGH_SPEED_NAVGATION = 557852175;
    public static final int ID_INTELLIGENT_SPEED_LIMIT = 557852173;
    public static final int ID_LANE_ALIGNMENT_ASSIST = 557852174;
    public static final int ID_REAR_COLLISION_SECURITY = 557852167;
    public static final int ID_SCU_ACC_EXIT_REASON = 557852332;
    public static final int ID_SCU_ACC_LKA_WARNING = 557852288;
    public static final int ID_SCU_ACC_RD_VOICE_TIPS = 557852300;
    public static final int ID_SCU_ACC_ST = 557852212;
    public static final int ID_SCU_ADAS_MAPINFO = 560997922;
    public static final int ID_SCU_ADAS_PROFSHORT_PROFTYPE = 557917909;
    public static final int ID_SCU_ADAS_SEG_SPEED_LIMIT = 557852328;
    public static final int ID_SCU_AEB_ALARM_SWST = 557852368;
    public static final int ID_SCU_ALARM_FAULT_ST = 557852289;
    public static final int ID_SCU_ALCCTRL_REMIND = 557852274;
    public static final int ID_SCU_ASS_LCHG_SWST = 557852265;
    public static final int ID_SCU_AUTO_PARK_CMD = 557852210;
    public static final int ID_SCU_BLIND_AREA_DETECTION_WARNING = 557852165;
    public static final int ID_SCU_BOX_DATA_1 = 560014938;
    public static final int ID_SCU_BOX_DATA_2 = 560014892;
    public static final int ID_SCU_BOX_DATA_3D7 = 560014972;
    public static final int ID_SCU_BOX_DATA_3D8 = 560014973;
    public static final int ID_SCU_BSD_LEFT_WARNING = 557852196;
    public static final int ID_SCU_BSD_RIGHT_WARNING = 557852195;
    public static final int ID_SCU_BSD_ST = 557852296;
    @Deprecated
    public static final int ID_SCU_BSD_WARNING = 557917779;
    public static final int ID_SCU_CHOSE_ID = 557852179;
    @Deprecated
    public static final int ID_SCU_CIP_WARNING_ST = 557852299;
    public static final int ID_SCU_CRUISESPD_SET_DISP = 557852309;
    public static final int ID_SCU_CUR_HUMI = 560997946;
    public static final int ID_SCU_CUR_TEMP = 560997947;
    public static final int ID_SCU_CUTLINE_PREVENT_SW = 557852164;
    public static final int ID_SCU_DISTRACTION_SW_ST = 557852298;
    public static final int ID_SCU_DMS_MODE_ST = 557852267;
    public static final int ID_SCU_DMS_SEBLRQ = 557852266;
    public static final int ID_SCU_DOW_LWARNING = 557852286;
    public static final int ID_SCU_DOW_RWARNING = 557852287;
    public static final int ID_SCU_DSM_REMIND = 557852370;
    public static final int ID_SCU_DSM_SWITCH = 557852395;
    public static final int ID_SCU_ELK_STATE = 557852333;
    @Deprecated
    public static final int ID_SCU_ENV_CHARACTER_INFO = 560014881;
    public static final int ID_SCU_ERR_TIPS = 557852255;
    public static final int ID_SCU_EVENT_INFO_RD = 557852408;
    public static final int ID_SCU_FARLAMP_AUTO_SW = 557852163;
    public static final int ID_SCU_FATIGUE_DETECTION = 557852170;
    public static final int ID_SCU_FCWAEB_SEN_SW = 557852413;
    public static final int ID_SCU_FEATURE_DATA = 560014942;
    public static final int ID_SCU_FEATURE_DATA_1 = 557917739;
    public static final int ID_SCU_FEATURE_DATA_2 = 557917750;
    public static final int ID_SCU_FISHEYECAM_FAILURE_ST = 557852305;
    public static final int ID_SCU_FORMWAY_RD = 557856794;
    public static final int ID_SCU_FRONT_COLLISION_SECURITY = 557852161;
    public static final int ID_SCU_FRONT_MINIDIST_EN = 557852232;
    public static final int ID_SCU_FRONT_RADAR_DATA = 560014876;
    public static final int ID_SCU_FRONT_RADAR_FAULT_ST = 557917769;
    public static final int ID_SCU_FRONT_RADAR_LVL = 557917770;
    public static final int ID_SCU_FSD_BUTTON = 557852272;
    public static final int ID_SCU_GPS_LOCATION = 560014897;
    public static final int ID_SCU_GPS_MESSAGE = 560014896;
    public static final int ID_SCU_HMI_DOP_REMIND = 557852409;
    public static final int ID_SCU_HOME_SLOT_ID = 557852216;
    public static final int ID_SCU_ICM_CDU_AP_ST = 557852386;
    public static final int ID_SCU_ISL_SPD = 557852382;
    public static final int ID_SCU_KEYPARK_SWST = 557852225;
    public static final int ID_SCU_LANE_CHANGE_ASSIST = 557852169;
    public static final int ID_SCU_LANE_DEPARTURE_WARNING = 557852162;
    public static final int ID_SCU_LATCTRL_REMIND = 557852201;
    public static final int ID_SCU_LATCTRL_REMIND_2 = 557852295;
    public static final int ID_SCU_LCC_EXIT_REASON = 557852331;
    public static final int ID_SCU_LDW_LKA_SW = 557852371;
    public static final int ID_SCU_LDW_ST = 557852292;
    @Deprecated
    public static final int ID_SCU_LDW_WARNING = 557917782;
    public static final int ID_SCU_LKA_ST = 557852372;
    public static final int ID_SCU_LKA_STATE = 557852302;
    public static final int ID_SCU_LKA_WARNING = 557917781;
    public static final int ID_SCU_LOCAT_DATA = 560014911;
    public static final int ID_SCU_LOCAT_DATA_11 = 560014917;
    public static final int ID_SCU_LOCAT_DATA_2 = 560014912;
    public static final int ID_SCU_LOCAT_WEATH = 560997948;
    public static final int ID_SCU_LOG_DATA_322 = 557917783;
    public static final int ID_SCU_LOG_DATA_322_D20 = 557917826;
    public static final int ID_SCU_LOG_DATA_3FD = 557917784;
    public static final int ID_SCU_LOG_DATA_3FE = 557917785;
    public static final int ID_SCU_LONGCTRL_REMIND = 557852199;
    public static final int ID_SCU_LRADAR_DIST = 557852406;
    public static final int ID_SCU_LRADAR_DIS_LEVEL = 557852404;
    public static final int ID_SCU_LR_BSD_SW = 557917935;
    public static final int ID_SCU_LR_DOW_SW = 557917937;
    public static final int ID_SCU_LR_RCTA_SW = 557917938;
    public static final int ID_SCU_LR_RCW_SW = 557917936;
    public static final int ID_SCU_LSS_SWST = 557852412;
    public static final int ID_SCU_L_AVM_SLOT = 560014946;
    public static final int ID_SCU_MAINCAM_FAILURE_ST = 557852306;
    public static final int ID_SCU_MODE_INDEX = 557852235;
    public static final int ID_SCU_MRR_ASSIST_SYSST = 557917818;
    public static final int ID_SCU_MRR_FAILURE_ST = 557852304;
    public static final int ID_SCU_MRR_RADAR_EMISS_ST = 557852410;
    public static final int ID_SCU_NARROWCAM_FAILURE_ST = 557852307;
    public static final int ID_SCU_NAVI_DANGER_AREA_RD_INFO = 557917939;
    public static final int ID_SCU_NGP_DRIVERCONFIRM_LC_SW = 557852321;
    public static final int ID_SCU_NGP_INFO_TIPS1 = 557852313;
    @Deprecated
    public static final int ID_SCU_NGP_LCMODE_SW = 557852322;
    public static final int ID_SCU_NGP_LC_TIPS1 = 557852312;
    public static final int ID_SCU_NGP_MODE_STATUS = 557852367;
    public static final int ID_SCU_NGP_OPEBUTTON_ST = 557852311;
    public static final int ID_SCU_NGP_QUICKLANE_SW = 557852319;
    public static final int ID_SCU_NGP_REMIND_MODE_SW = 557852323;
    @Deprecated
    public static final int ID_SCU_NGP_SCU_NGP_TRUCKOFFSET_SW = 557852320;
    public static final int ID_SCU_NGP_TIPS_WINDOWS_ST = 557852314;
    public static final int ID_SCU_NGP_TRUCKOFFSET_SW = 557852320;
    public static final int ID_SCU_NGP_XPILOTST = 557852318;
    public static final int ID_SCU_OPERATION_TIPS = 557852187;
    public static final int ID_SCU_OTA_TAG = 557852327;
    public static final int ID_SCU_PAK_PERCENT = 560014945;
    public static final int ID_SCU_PARKINGGROUND_STATE = 557852345;
    public static final int ID_SCU_PARKING_STATUS = 557852231;
    public static final int ID_SCU_PARKING_TSK_TIME = 557917764;
    public static final int ID_SCU_PARKING_VER = 560014914;
    public static final int ID_SCU_PARK_AP_SW = 557852369;
    public static final int ID_SCU_PARK_DATA = 560014956;
    public static final int ID_SCU_PARK_DATA_1 = 560014910;
    public static final int ID_SCU_PARK_DATA_2 = 560014894;
    public static final int ID_SCU_PARK_DATA_3 = 560014903;
    public static final int ID_SCU_PARK_DATA_4 = 560014899;
    public static final int ID_SCU_PARK_DATA_5 = 560014909;
    @Deprecated
    public static final int ID_SCU_PARK_ERR = 557852205;
    @Deprecated
    public static final int ID_SCU_PARK_SLOT_INFO = 560014878;
    @Deprecated
    public static final int ID_SCU_PHONE_AP_BTN = 557852216;
    @Deprecated
    public static final int ID_SCU_PHONE_CTRL_BTN = 557852185;
    public static final int ID_SCU_PHONE_PK_BTN = 557852183;
    public static final int ID_SCU_PHONE_SM_BUTTON = 557852217;
    public static final int ID_SCU_PK_BTN = 557852184;
    @Deprecated
    public static final int ID_SCU_POSITION_INFO = 560014880;
    public static final int ID_SCU_QUIT_NGP_ODD = 557852310;
    public static final int ID_SCU_RADAR_DIST = 557917852;
    public static final int ID_SCU_RADAR_DISTANCE_EN = 557852230;
    public static final int ID_SCU_RADAR_DIS_ACTIVE = 557852239;
    public static final int ID_SCU_RADAR_DIS_LEVEL = 557917853;
    public static final int ID_SCU_RADAR_WARNING_VOICE_STATUS = 557852177;
    public static final int ID_SCU_RCTA_LEFT_WARNING = 557852197;
    public static final int ID_SCU_RCTA_RIGHT_WARNING = 557852198;
    public static final int ID_SCU_RCTA_ST = 557852297;
    @Deprecated
    public static final int ID_SCU_RCTA_WARNING = 557917780;
    public static final int ID_SCU_RCW_WARNING = 557852283;
    public static final int ID_SCU_RDATTR_DETAIL_RD = 557852270;
    public static final int ID_SCU_REAR_CROSS_EMERGENCY = 557852166;
    public static final int ID_SCU_REAR_MINIDIST_EN = 557852236;
    public static final int ID_SCU_RECV_ID = 557852178;
    public static final int ID_SCU_REMOTE_CTRL_BTN = 557852181;
    public static final int ID_SCU_REMOTE_DRIVE_BTN = 557852182;
    public static final int ID_SCU_REMOTE_FLAG = 557852377;
    public static final int ID_SCU_REMOTE_PK_BTN = 557852186;
    public static final int ID_SCU_RMIRROR_CTRL = 557852240;
    @Deprecated
    public static final int ID_SCU_ROAD_ATTR = 557852264;
    public static final int ID_SCU_ROAD_ATTR_PK_RD = 557917807;
    public static final int ID_SCU_ROAD_ATTR_RD = 557852379;
    public static final int ID_SCU_RRADAR_DIST = 557852407;
    public static final int ID_SCU_RRADAR_DIS_LEVEL = 557852405;
    public static final int ID_SCU_RVIEWMIRROR_SW_ST = 557852303;
    public static final int ID_SCU_R_AVM_SLOT = 560014947;
    public static final int ID_SCU_SDC_AUTOMODE_STATUS = 557852340;
    public static final int ID_SCU_SDC_CTRL_CDU_TIPS = 557852329;
    public static final int ID_SCU_SDC_CTRL_CDU_TTS = 557852330;
    public static final int ID_SCU_SDC_CTRL_INDEX1 = 557852341;
    public static final int ID_SCU_SDC_CTRL_INDEX2 = 557852342;
    public static final int ID_SCU_SDC_CTRL_URADARVOICE_TONE = 557852326;
    public static final int ID_SCU_SDC_DOOR_DETECTION = 557917851;
    public static final int ID_SCU_SDC_L1_INDEX_N = 557852361;
    public static final int ID_SCU_SDC_LBLIND_STATUS = 557852336;
    public static final int ID_SCU_SDC_LRADAR_STATUS = 557852335;
    public static final int ID_SCU_SDC_LSCENE_STATUS = 557852337;
    public static final int ID_SCU_SDC_R1_INDEX_N = 557852360;
    public static final int ID_SCU_SDC_RBLIND_STATUS = 557852338;
    public static final int ID_SCU_SDC_RRADAR_STATUS = 557852334;
    public static final int ID_SCU_SDC_RSCENE_STATUS = 557852339;
    public static final int ID_SCU_SDC_URADAR_FUSION = 557917898;
    public static final int ID_SCU_SDC_URADAR_L1_DIST = 557852351;
    public static final int ID_SCU_SDC_URADAR_L1_ERROR_ST = 557852355;
    public static final int ID_SCU_SDC_URADAR_L1_PEAKLEVEL = 557852353;
    public static final int ID_SCU_SDC_URADAR_L1_RT1 = 557852358;
    public static final int ID_SCU_SDC_URADAR_L1_ST = 557852354;
    public static final int ID_SCU_SDC_URADAR_L1_TOF = 557852352;
    public static final int ID_SCU_SDC_URADAR_L1_WAVEWIDTH = 557852359;
    public static final int ID_SCU_SDC_URADAR_LDOOR_DIST = 557852343;
    public static final int ID_SCU_SDC_URADAR_LHALL_COUNTER = 557852363;
    public static final int ID_SCU_SDC_URADAR_R1_DIST = 557852346;
    public static final int ID_SCU_SDC_URADAR_R1_ERROR_ST = 557852350;
    public static final int ID_SCU_SDC_URADAR_R1_PEAKLEVEL = 557852348;
    public static final int ID_SCU_SDC_URADAR_R1_RT1 = 557852356;
    public static final int ID_SCU_SDC_URADAR_R1_ST = 557852349;
    public static final int ID_SCU_SDC_URADAR_R1_TOF = 557852347;
    public static final int ID_SCU_SDC_URADAR_R1_WAVEWIDTH = 557852357;
    public static final int ID_SCU_SDC_URADAR_RDOOR_DIST = 557852344;
    public static final int ID_SCU_SDC_URADAR_RHALL_COUNTER = 557852364;
    public static final int ID_SCU_SIDECAM_FAILURE_ST = 557917844;
    public static final int ID_SCU_SIDE_REVERSION_WARNING = 557852202;
    public static final int ID_SCU_SLA_ALARM_SWST = 557852301;
    public static final int ID_SCU_SLA_ST = 557852291;
    public static final int ID_SCU_SLA_WARNING_TYPE = 557852172;
    public static final int ID_SCU_SLIF_ST = 557852385;
    public static final int ID_SCU_SLOTS_NUM = 557852269;
    public static final int ID_SCU_SLOT_FOR_PARK = 560014948;
    public static final int ID_SCU_SLOT_THETA = 557917763;
    public static final int ID_SCU_SLOT_VOICE = 557852207;
    public static final int ID_SCU_SL_DRV_SET = 557852380;
    public static final int ID_SCU_SL_SPD_RANGE = 557852381;
    public static final int ID_SCU_SPDLIT_VOCREMIND = 557852394;
    public static final int ID_SCU_SPD_LIMIT_ST = 557852378;
    public static final int ID_SCU_SRR_RADAR_EMISS_ST = 557917947;
    public static final int ID_SCU_SS_LSYS_ST = 560998052;
    public static final int ID_SCU_SS_RSYS_ST = 560998053;
    public static final int ID_SCU_STEERING_WHEEL_EPS = 557852365;
    public static final int ID_SCU_SUPER_PARK_ACTIVE = 557852180;
    public static final int ID_SCU_TAIL_RADAR_DATA = 560014877;
    public static final int ID_SCU_TAIL_RADAR_FAULT_ST = 557917773;
    public static final int ID_SCU_TAIL_RADAR_LVL = 557917774;
    public static final int ID_SCU_TEST = 557852213;
    public static final int ID_SCU_URADAR_DATA_INFO = 560015062;
    public static final int ID_SCU_URADAR_FDIST = 557852241;
    @Deprecated
    public static final int ID_SCU_URADAR_INFO = 560014879;
    public static final int ID_SCU_URADAR_RDIST = 557852242;
    public static final int ID_SCU_URADAR_VOICE_ACTIVE = 557852261;
    public static final int ID_SCU_URADAR_VOICE_TONE = 557852262;
    public static final int ID_SCU_VOICE_LANE_CHANGE = 557852366;
    public static final int ID_SCU_XPILOT_ST = 557852200;
    public static final int ID_TRAFFIC_SIGN_RECOGNITION = 557852171;
    public static final int L2_INACTIVATED_FOR_DANGER_AREA = 7;
    @Deprecated
    public static final int LANE_CHANGE_MODE_NORMAL = 1;
    @Deprecated
    public static final int LANE_CHANGE_MODE_OFF = 2;
    @Deprecated
    public static final int LANE_CHANGE_MODE_ON = 0;
    @Deprecated
    public static final int LANE_CHANGE_MODE_SOFT = 0;
    public static final int LCC_BANNED_DUE_TO_ROAD_ATTRIBUTE = 4;
    public static final int LCC_BANNED_DUE_TO_ROAD_CURVE = 2;
    public static final int LCC_BANNED_DUE_TO_SWITCH_OFF = 6;
    public static final int LCC_BANNED_DUE_TO_UNCLEAR_LANE = 3;
    public static final int LCC_BANNED_DUE_TO_WIPER_SPD = 1;
    public static final int LEFT_SIDE_WARNING_INDEX = 0;
    public static final int LSS_SWST_ALARM_ACTIVE = 1;
    public static final int LSS_SWST_DEVIATION_ACTIVE = 2;
    public static final int LSS_SWST_GLOBAL_ACTIVE = 3;
    public static final int LSS_SWST_OFF = 0;
    public static final int MEMORY_PARKING_EXECUTE = 3;
    public static final int MEMORY_PARKING_INITIAL = 0;
    public static final int MEMORY_PARKING_READY = 2;
    public static final int MEMORY_PARKING_SAFE_STOP = 4;
    public static final int MEMORY_PARKING_STANDBY = 1;
    public static final int MRR_FAILURE_STATUS_BLINDNESS = 2;
    public static final int MRR_FAILURE_STATUS_FAILURE = 1;
    public static final int MRR_FAILURE_STATUS_MISALIGNMENT = 3;
    public static final int MRR_FAILURE_STATUS_NO_FAILURE = 0;
    public static final int NAVI_LINK_TYPE_BRIDGE = 4;
    public static final int NAVI_LINK_TYPE_COMMON = 1;
    public static final int NAVI_LINK_TYPE_ELEVATED_RD = 5;
    public static final int NAVI_LINK_TYPE_FERRY = 2;
    public static final int NAVI_LINK_TYPE_NULL = 0;
    public static final int NAVI_LINK_TYPE_TUNNEL = 3;
    public static final int NGP_INFO_TIPS_ATTENTION_BY_LONG_TIME_NOT_LC = 4;
    public static final int NGP_INFO_TIPS_ATTENTION_BY_WORK_ROAD = 7;
    public static final int NGP_INFO_TIPS_BLOCKED_ROAD_AND_PLS_MANUAL_LC = 6;
    public static final int NGP_INFO_TIPS_END_POINT_AND_PLS_MANUAL_LC = 5;
    public static final int NGP_INFO_TIPS_HD_MAP_IS_READY = 9;
    public static final int NGP_INFO_TIPS_NGP_WILL_QUIT = 8;
    public static final int NGP_INFO_TIPS_NO_DISPLAY = 0;
    public static final int NGP_INFO_TIPS_PLS_EYES_ON = 2;
    public static final int NGP_INFO_TIPS_PLS_HANDS_ON = 1;
    public static final int NGP_INFO_TIPS_PLS_TAKE_OVER_CAR = 3;
    public static final int NGP_INFO_TIPS_TIPS_A = 10;
    public static final int NGP_INFO_TIPS_TIPS_B = 11;
    public static final int NGP_INFO_TIPS_TIPS_C = 12;
    public static final int NGP_INFO_TIPS_TIPS_D = 13;
    public static final int NGP_INFO_TIPS_TIPS_E = 14;
    public static final int NGP_INFO_TIPS_TIPS_F = 15;
    @Deprecated
    public static final int NGP_LANE_CHANGE_MODE_MODERATE = 1;
    @Deprecated
    public static final int NGP_LANE_CHANGE_MODE_RADICAL = 2;
    public static final int NGP_LC_TIPS_3S_LATER_WILL_UPDATE_TO_NGP = 4;
    public static final int NGP_LC_TIPS_ENTER_NGP_ODD = 2;
    public static final int NGP_LC_TIPS_LANE_CHANGE_CANCEL = 28;
    public static final int NGP_LC_TIPS_LC_ACTIVE_TO_LEFT_BY_MERGE = 23;
    public static final int NGP_LC_TIPS_LC_ACTIVE_TO_LEFT_BY_SLOW_CAR = 11;
    public static final int NGP_LC_TIPS_LC_ACTIVE_TO_LEFT_FAST_LANE = 6;
    public static final int NGP_LC_TIPS_LC_ACTIVE_TO_RIGHT_BY_MERGE = 24;
    public static final int NGP_LC_TIPS_LC_ACTIVE_TO_RIGHT_BY_RAMP = 17;
    public static final int NGP_LC_TIPS_LC_ACTIVE_TO_RIGHT_BY_SLOW_CAR = 12;
    public static final int NGP_LC_TIPS_LC_NEED_CFM_2_LEFT_BY_SLOW_CAR = 9;
    public static final int NGP_LC_TIPS_LC_NEED_CFM_TO_LEFT_BY_MERGE = 21;
    public static final int NGP_LC_TIPS_LC_NEED_CFM_TO_LEFT_FAST_LANE = 5;
    public static final int NGP_LC_TIPS_LC_NEED_CFM_TO_RIGHT_BY_MERGE = 22;
    public static final int NGP_LC_TIPS_LC_NEED_CFM_TO_RIGHT_BY_RAMP = 16;
    public static final int NGP_LC_TIPS_LC_NEED_CFM_TO_RIGHT_BY_SLOW_CAR = 10;
    public static final int NGP_LC_TIPS_LC_TRIGGER_BY_2KM_FAST_LANE = 30;
    public static final int NGP_LC_TIPS_LC_WAIT_SIDE_CAR_2_LEFT_FAST_LANE = 7;
    public static final int NGP_LC_TIPS_LC_WAIT_SIDE_CAR_BY_MERGE = 25;
    public static final int NGP_LC_TIPS_LC_WAIT_SIDE_CAR_BY_RAMP = 18;
    public static final int NGP_LC_TIPS_LC_WAIT_SIDE_CAR_BY_SLOW_CAR = 13;
    public static final int NGP_LC_TIPS_LC_WAIT_SOLID_LANE_BY_MERGE = 26;
    public static final int NGP_LC_TIPS_LC_WAIT_SOLID_LANE_BY_RAMP = 19;
    public static final int NGP_LC_TIPS_LC_WAIT_SOLID_LANE_BY_SLOW_CAR = 14;
    public static final int NGP_LC_TIPS_LC_WAIT_SOLID_LANE_LEFT_FAST_LANE = 8;
    public static final int NGP_LC_TIPS_LC_WILL_TRIGGER_BY_2KM_RAMP = 15;
    public static final int NGP_LC_TIPS_LC_WILL_TRIGGER_BY_2KM_TUNNEL = 29;
    public static final int NGP_LC_TIPS_NEED_TAKEOVER_BY_NOT_ENTER_RAMP = 20;
    public static final int NGP_LC_TIPS_NGP_IS_ACTIVE = 3;
    public static final int NGP_LC_TIPS_NGP_STANDBY = 1;
    public static final int NGP_LC_TIPS_NO_DISPLAY = 0;
    public static final int NGP_LC_TIPS_WILL_EXIST_ODD_BY_200M = 27;
    public static final int NGP_MODE_STATUS_ACC_MODE = 2;
    public static final int NGP_MODE_STATUS_DRIVER_MODE = 1;
    public static final int NGP_MODE_STATUS_LCC_MODE = 3;
    public static final int NGP_MODE_STATUS_LCC_MODE_2 = 4;
    public static final int NGP_MODE_STATUS_NGP_MODE = 5;
    public static final int NGP_MODE_STATUS_NGP_MODE_2 = 6;
    public static final int NGP_OPERATION_BUTTON_STATUS_CANCEL_LANE_CHANGE = 3;
    public static final int NGP_OPERATION_BUTTON_STATUS_CONFIRM_LANE_CHANGE = 4;
    public static final int NGP_OPERATION_BUTTON_STATUS_NO_DISPLAY = 0;
    public static final int NGP_OPERATION_BUTTON_STATUS_OPERATION_5 = 5;
    public static final int NGP_OPERATION_BUTTON_STATUS_OPERATION_6 = 6;
    public static final int NGP_OPERATION_BUTTON_STATUS_OPERATION_7 = 7;
    public static final int NGP_OPERATION_BUTTON_STATUS_QUIT_NGP = 2;
    public static final int NGP_OPERATION_BUTTON_STATUS_START_NGP = 1;
    public static final int NGP_REMIND_MODE_A = 2;
    public static final int NGP_REMIND_MODE_AUDIO = 1;
    public static final int NGP_REMIND_MODE_VOICE = 0;
    public static final int NGP_SW_ST_ACC_STANDBY = 2;
    public static final int NGP_SW_ST_ALC_ACTIVE = 14;
    public static final int NGP_SW_ST_ALC_WAIT = 13;
    public static final int NGP_SW_ST_BRAKE_ONLY = 6;
    public static final int NGP_SW_ST_HOLD = 9;
    public static final int NGP_SW_ST_LCC_ACTIVE = 11;
    public static final int NGP_SW_ST_LCC_STANDBY = 3;
    public static final int NGP_SW_ST_LC_ACTIVE = 17;
    public static final int NGP_SW_ST_LC_CONFIRM = 15;
    public static final int NGP_SW_ST_LC_WAIT = 16;
    public static final int NGP_SW_ST_NGP_ACTIVE = 12;
    public static final int NGP_SW_ST_NGP_BAN_2_RADAR = 25;
    public static final int NGP_SW_ST_NGP_BAN_2_TRAILER_MODE = 24;
    public static final int NGP_SW_ST_NGP_STANDBY = 4;
    public static final int NGP_SW_ST_NGP_STATE_19 = 19;
    public static final int NGP_SW_ST_NGP_STATE_20 = 20;
    public static final int NGP_SW_ST_NGP_STATE_21 = 21;
    public static final int NGP_SW_ST_OFF = 0;
    public static final int NGP_SW_ST_OFF_BY_RADAR_OFF = 3;
    public static final int NGP_SW_ST_OFF_BY_TRAILER_MODE = 2;
    public static final int NGP_SW_ST_ON = 1;
    public static final int NGP_SW_ST_OVERRIDE = 7;
    public static final int NGP_SW_ST_PASSIVE = 1;
    public static final int NGP_SW_ST_PERMANENT_FAILURE = 23;
    public static final int NGP_SW_ST_SACC_ACTIVE = 5;
    public static final int NGP_SW_ST_SAFE_STOP = 18;
    public static final int NGP_SW_ST_STAND = 8;
    public static final int NGP_SW_ST_TEMPORARY_FAILURE = 22;
    public static final int NGP_SW_ST_TJA_ACTIVE = 10;
    public static final int NOT_DEFINED = 0;
    public static final int NO_CARPORT = 0;
    public static final int NO_REMIND_VOICE = 0;
    public static final int PARK_BY_MEMORY_IN_INITIALIZATION = 0;
    public static final int QUIT_NGP_ODD_STATUS_NO_DISPLAY = 0;
    public static final int QUIT_NGP_ODD_STATUS_WILL_EXIST_ODD_BY_100M = 3;
    public static final int QUIT_NGP_ODD_STATUS_WILL_EXIST_ODD_BY_150M = 2;
    public static final int QUIT_NGP_ODD_STATUS_WILL_EXIST_ODD_BY_200M = 1;
    public static final int QUIT_NGP_ODD_STATUS_WILL_EXIST_ODD_BY_25M = 6;
    public static final int QUIT_NGP_ODD_STATUS_WILL_EXIST_ODD_BY_50M = 5;
    public static final int QUIT_NGP_ODD_STATUS_WILL_EXIST_ODD_BY_75M = 4;
    public static final int RADAR_BLOCK_ST = 2;
    public static final int RADAR_DISPLAY_DISABLE = 0;
    public static final int RADAR_DISPLAY_ENABLE = 1;
    public static final int RADAR_DISPLAY_LEVEL_1 = 1;
    public static final int RADAR_DISPLAY_LEVEL_2 = 2;
    public static final int RADAR_EMISS_ST_FIAL = 2;
    public static final int RADAR_EMISS_ST_OFF = 0;
    public static final int RADAR_EMISS_ST_ON = 1;
    public static final int RADAR_FAULT_ST = 1;
    public static final int RADAR_NORMAL_ST = 0;
    public static final int RADAR_TONE_1 = 1;
    public static final int RADAR_TONE_2 = 2;
    public static final int RADAR_TONE_3 = 3;
    public static final int RADAR_TONE_4 = 4;
    public static final int RADAR_TONE_5 = 5;
    public static final int RADAR_TONE_6 = 6;
    public static final int RADAR_TONE_NONE = 0;
    public static final int RADAR_VOICE_DISABLE = 0;
    public static final int RADAR_VOICE_ENABLE = 1;
    public static final int REAR_MIRROR_FOLD_STATE = 1;
    public static final int REAR_MIRROR_NO_OPERATION = 0;
    public static final int REAR_MIRROR_UNFOLD_STATE = 2;
    public static final int REAR_VIEW_MIRROR_ADJUSTMENT_PAGE_CLOSED = 0;
    public static final int REAR_VIEW_MIRROR_ADJUSTMENT_PAGE_OPENED = 1;
    public static final int REMIND_VOICE_1 = 1;
    public static final int REMIND_VOICE_2 = 2;
    public static final int REMIND_VOICE_3 = 3;
    public static final int REMIND_VOICE_4 = 4;
    public static final int REMIND_VOICE_5 = 5;
    public static final int REMIND_VOICE_6 = 6;
    public static final int REMIND_VOICE_7 = 7;
    public static final int REMOTE_FLAG_NO = 0;
    public static final int REMOTE_FLAG_YES = 1;
    public static final int RESPONSE_SCU_ACTIVE = 3;
    public static final int RESPONSE_SCU_NO_FAULT = 0;
    public static final int RESPONSE_SCU_PERMANENT_ERROR = 2;
    public static final int RESPONSE_SCU_TEMPORARY_ERROR = 1;
    public static final int RIGHT_SIDE_WARNING_INDEX = 1;
    public static final int ROAD_ATTR_NOT_DEFINED = 0;
    public static final int ROAD_ATTR_ROAD_FASTWAY_IN_CITY = 2;
    public static final int ROAD_ATTR_ROAD_HIGHWAY = 1;
    public static final int ROAD_CLASS_CITY_SPEED_WAY = 7;
    public static final int ROAD_CLASS_COMMON_ROAD = 10;
    public static final int ROAD_CLASS_COUNTRY_ROAD = 4;
    public static final int ROAD_CLASS_FREE_WAY = 1;
    public static final int ROAD_CLASS_IN_COUNTRY_ROAD = 6;
    public static final int ROAD_CLASS_MAIN_ROAD = 8;
    public static final int ROAD_CLASS_NATIONAL_ROAD = 2;
    public static final int ROAD_CLASS_NON_NAVI_ROAD = 11;
    public static final int ROAD_CLASS_NOT_DEFINED = 0;
    public static final int ROAD_CLASS_PROVINCE_ROAD = 3;
    public static final int ROAD_CLASS_RURAL_ROAD = 5;
    public static final int ROAD_CLASS_SECONDARY_ROAD = 9;
    public static final int ROAD_VOICE_1 = 1;
    public static final int ROAD_VOICE_17 = 23;
    public static final int ROAD_VOICE_18 = 24;
    public static final int ROAD_VOICE_19 = 25;
    public static final int ROAD_VOICE_1A = 26;
    public static final int ROAD_VOICE_1B = 27;
    public static final int ROAD_VOICE_1C = 28;
    public static final int ROAD_VOICE_1D = 29;
    public static final int ROAD_VOICE_1E = 30;
    public static final int ROAD_VOICE_1F = 31;
    public static final int ROAD_VOICE_2 = 2;
    public static final int ROAD_VOICE_20 = 32;
    public static final int ROAD_VOICE_23 = 35;
    public static final int ROAD_VOICE_3 = 3;
    public static final int ROAD_VOICE_4 = 4;
    public static final int ROAD_VOICE_5 = 5;
    public static final int ROAD_VOICE_6 = 6;
    public static final int ROAD_VOICE_7 = 7;
    public static final int ROAD_VOICE_8 = 8;
    public static final int ROAD_VOICE_9 = 9;
    public static final int ROAD_VOICE_A = 10;
    public static final int ROAD_VOICE_B = 11;
    public static final int ROAD_VOICE_C = 12;
    public static final int ROAD_VOICE_D = 13;
    public static final int ROAD_VOICE_DEFAULT = 0;
    public static final int SCU_ACC_ACTIVE = 3;
    public static final int SCU_ACC_BRAKE_ONLY = 4;
    public static final int SCU_ACC_FAILURE = 9;
    public static final int SCU_ACC_OFF = 0;
    public static final int SCU_ACC_OVERRIDE = 5;
    public static final int SCU_ACC_PASSIVE = 1;
    public static final int SCU_ACC_PERMANENT_FAILURE = 9;
    public static final int SCU_ACC_STANDACTIVE = 6;
    public static final int SCU_ACC_STANDBY = 2;
    public static final int SCU_ACC_STANDWAIT = 7;
    public static final int SCU_ACC_TEMPORARY_FAILURE = 8;
    public static final int SCU_ALC_ACTIVE = 1;
    @Deprecated
    public static final int SCU_ALC_BAN_2_TRAILER_MODE = 10;
    public static final int SCU_ALC_CANCEL = 4;
    public static final int SCU_ALC_FAILURE = 7;
    public static final int SCU_ALC_FINISH = 5;
    public static final int SCU_ALC_INACTIVE = 0;
    public static final int SCU_ALC_OFF = 6;
    public static final int SCU_ALC_OFF_BY_RADAR_OFF = 10;
    public static final int SCU_ALC_OFF_BY_TRAILER_MODE = 9;
    @Deprecated
    public static final int SCU_ALC_PASSIVE = 9;
    public static final int SCU_ALC_WAIT = 2;
    public static final int SCU_ALC_WAIT_TIMEOUT = 3;
    public static final int SCU_AUTO_PARK_CMD_RESET = 0;
    public static final int SCU_AUTO_PARK_IN_CANCLE = 2;
    public static final int SCU_AUTO_PARK_IN_CONTINUE = 4;
    public static final int SCU_AUTO_PARK_IN_START = 1;
    public static final int SCU_AUTO_PARK_OUT_CANCLE = 2;
    public static final int SCU_AUTO_PARK_OUT_CONTINUE = 4;
    public static final int SCU_AUTO_PARK_OUT_START = 3;
    public static final int SCU_BUTTON_CLOSE_BY_TTM = 2;
    public static final int SCU_BUTTON_OFF = 0;
    public static final int SCU_BUTTON_ON = 1;
    public static final int SCU_CLICK_TYPE_DOUBLE = 1;
    public static final int SCU_CLICK_TYPE_LONGPRESS = 2;
    public static final int SCU_COMMONFB2_SW_FAIL = 2;
    public static final int SCU_COMMONFB2_SW_NO_FAULT = 5;
    public static final int SCU_COMMONFB2_SW_OFF = 0;
    public static final int SCU_COMMONFB2_SW_ON = 1;
    public static final int SCU_COMMONFB2_SW_PERMANENT_ERROR = 4;
    public static final int SCU_COMMONFB2_SW_TEMPORARY_ERROR = 3;
    public static final int SCU_COMMONFB_SW_ACTIVE = 3;
    public static final int SCU_COMMONFB_SW_FUNC_LIMITED = 7;
    @Deprecated
    public static final int SCU_COMMONFB_SW_LCC_BAN_2_TRAILER_MODE = 8;
    @Deprecated
    public static final int SCU_COMMONFB_SW_LCC_OFF_BY_RADAR_OFF = 10;
    @Deprecated
    public static final int SCU_COMMONFB_SW_NO_FAULT = 6;
    public static final int SCU_COMMONFB_SW_OFF = 0;
    public static final int SCU_COMMONFB_SW_PASSIVE = 1;
    public static final int SCU_COMMONFB_SW_PERMANENT_FAIL = 5;
    public static final int SCU_COMMONFB_SW_STANDBY = 2;
    public static final int SCU_COMMONFB_SW_TEMPORARY_FAIL = 4;
    public static final int SCU_CONTINUE_AUTO_PARK = 4;
    public static final int SCU_CRUISE_CTRL_ACTIVE_FAILED = 1;
    public static final int SCU_CRUISE_CTRL_CANCELLED_ACTIVELY = 2;
    public static final int SCU_CRUISE_CTRL_CANCELLED_PASSIVELY = 8;
    public static final int SCU_EVENT_INFO_RD_CONSTRUCTION = 2;
    public static final int SCU_EVENT_INFO_RD_NOT_DEFINED = 0;
    public static final int SCU_EVENT_INFO_RD_TRAFFIC_ACCIDENT = 1;
    public static final int SCU_EXIT_AUTO_PARK = 2;
    public static final int SCU_FB_SW_LCC_OFF_BY_RADAR_OFF = 2;
    @Deprecated
    public static final int SCU_FB_SW_LCC_OFF_BY_TRAILER_MODE = 6;
    public static final int SCU_FB_SW_OFF_BY_TRAILER_MODE = 7;
    public static final int SCU_FB_SW_UNABLE_ACTIVATE_LCC = 6;
    public static final int SCU_FCWAEB_BLINDNESS = 7;
    public static final int SCU_FCWAEB_FUNC_LIMITED = 10;
    public static final int SCU_FCWAEB_INITIALIZATION_STATE = 8;
    public static final int SCU_FCWAEB_NO_FAULT = 5;
    public static final int SCU_FCWAEB_OFF_BY_RADAR_EMISSION_OFF = 9;
    public static final int SCU_FCWAEB_PERMANENT_BLOCK = 4;
    public static final int SCU_FCWAEB_PERMANENT_FAIL = 3;
    public static final int SCU_FCWAEB_SEN_ST_HIGH = 3;
    public static final int SCU_FCWAEB_SEN_ST_LOW = 2;
    public static final int SCU_FCWAEB_SEN_ST_MEDIUM = 1;
    public static final int SCU_FCWAEB_SEN_ST_UNAVAILABLE = 0;
    public static final int SCU_FCWAEB_SW_OFF = 0;
    public static final int SCU_FCWAEB_SW_ON = 1;
    public static final int SCU_FCWAEB_TEMPORARY_FAIL = 2;
    public static final int SCU_FCWAEB_VRU_FAILURE = 6;
    public static final int SCU_FORWARD_PARKING = 2;
    public static final int SCU_FRONT_COLLISION_DISABLE = 0;
    public static final int SCU_FRONT_COLLISION_ENABLE = 1;
    public static final int SCU_FSD_SW_OFF = 0;
    public static final int SCU_FSD_SW_ON = 1;
    public static final int SCU_Formway_RD_COMMON_LINK = 15;
    public static final int SCU_Formway_RD_CROSS_LINK = 2;
    public static final int SCU_Formway_RD_ENTRANCE = 10;
    public static final int SCU_Formway_RD_EXITLINK = 9;
    public static final int SCU_Formway_RD_JCT = 3;
    public static final int SCU_Formway_RD_MAIN_ROAD = 1;
    public static final int SCU_Formway_RD_NULL = 0;
    public static final int SCU_Formway_RD_ROUND_ABOUT = 4;
    public static final int SCU_Formway_RD_SERVICE = 5;
    public static final int SCU_Formway_RD_SERVICE_JCT_ROAD = 17;
    public static final int SCU_Formway_RD_SERVICE_SLIP_JCT_ROAD = 19;
    public static final int SCU_Formway_RD_SERVICE_SLIP_ROAD = 18;
    public static final int SCU_Formway_RD_SLIDE_ROAD = 7;
    public static final int SCU_Formway_RD_SLIPJCT = 8;
    public static final int SCU_Formway_RD_SLIP_ROAD = 6;
    public static final int SCU_Formway_RD_TURN_LEFT_LINE_A = 13;
    public static final int SCU_Formway_RD_TURN_LEFT_LINE_B = 14;
    public static final int SCU_Formway_RD_TURN_LEFT_RIGHT_LINE = 16;
    public static final int SCU_Formway_RD_TURN_RIGHT_LINE_A = 11;
    public static final int SCU_Formway_RD_TURN_RIGHT_LINE_B = 12;
    @Deprecated
    public static final int SCU_HIC_SW_ACTIVE = 3;
    @Deprecated
    public static final int SCU_HIC_SW_BRAKE_ONLY = 4;
    @Deprecated
    public static final int SCU_HIC_SW_OFF = 0;
    @Deprecated
    public static final int SCU_HIC_SW_OVERRIDE = 5;
    @Deprecated
    public static final int SCU_HIC_SW_PASSIVE = 1;
    @Deprecated
    public static final int SCU_HIC_SW_PERMANENT_FAILURE = 9;
    @Deprecated
    public static final int SCU_HIC_SW_STANDACTIVE = 6;
    @Deprecated
    public static final int SCU_HIC_SW_STANDBY = 2;
    @Deprecated
    public static final int SCU_HIC_SW_STANDWAIT = 7;
    @Deprecated
    public static final int SCU_HIC_SW_TEMPORARY_FAILURE = 8;
    public static final int SCU_INACTIVE_FOR_DOORS_UNLOCK = 5;
    public static final int SCU_INACTIVE_FOR_EPB_ACTIVE = 7;
    public static final int SCU_INACTIVE_FOR_NOT_D_SHIFT = 6;
    public static final int SCU_INACTIVE_FOR_SETBELT_UNLOCK = 4;
    public static final int SCU_INTELLIGENT_SPEED_LIMIT_DISABLE = 0;
    public static final int SCU_INTELLIGENT_SPEED_LIMIT_ENABLE = 1;
    public static final int SCU_ISLC_SW_ACTIVE = 3;
    public static final int SCU_ISLC_SW_OFF = 0;
    public static final int SCU_ISLC_SW_OVERRIDE = 5;
    public static final int SCU_ISLC_SW_PASSIVE = 1;
    public static final int SCU_ISLC_SW_PERMANENT_FAIL = 7;
    public static final int SCU_ISLC_SW_PERMANENT_NO_FAULT = 8;
    public static final int SCU_ISLC_SW_STANDBY = 2;
    public static final int SCU_ISLC_SW_STANDWAIT = 4;
    public static final int SCU_ISLC_SW_ST_CONTROL = 3;
    public static final int SCU_ISLC_SW_ST_DISPLAY = 1;
    public static final int SCU_ISLC_SW_ST_ISLC_ON = 1;
    public static final int SCU_ISLC_SW_ST_OFF = 0;
    public static final int SCU_ISLC_SW_ST_SLIF_ON = 2;
    public static final int SCU_ISLC_SW_ST_WARNING = 2;
    public static final int SCU_ISLC_SW_TEMPORARY_FAIL = 6;
    public static final int SCU_LANE_CHANGE_ASSIST_DISABLE = 0;
    public static final int SCU_LANE_CHANGE_ASSIST_ENABLE = 1;
    public static final int SCU_LATCTRL_NO_DISPLAY = 0;
    public static final int SCU_LCC_ACTIVATED = 9;
    public static final int SCU_LCC_BANNED_DUE_TO_NO_TAKEOVER = 8;
    public static final int SCU_LCC_DELAY_QUIT = 5;
    public static final int SCU_LCC_DELAY_QUIT_COMPLETED = 10;
    public static final int SCU_LCC_DISTRACTION_LEVEL1 = 12;
    public static final int SCU_LCC_DISTRACTION_LEVEL2 = 13;
    public static final int SCU_LCC_FAILURE = 11;
    public static final int SCU_LCC_QUICK_PASSIVE_QUIT = 14;
    public static final int SCU_LCC_QUICK_QUIT = 6;
    @Deprecated
    public static final int SCU_LCS_SW_ACTIVE = 3;
    @Deprecated
    public static final int SCU_LCS_SW_OFF = 0;
    @Deprecated
    public static final int SCU_LCS_SW_PASSIVE = 1;
    @Deprecated
    public static final int SCU_LCS_SW_PERMANENT_FAIL = 6;
    @Deprecated
    public static final int SCU_LCS_SW_STANDACTIVE = 4;
    @Deprecated
    public static final int SCU_LCS_SW_STANDBY = 2;
    @Deprecated
    public static final int SCU_LCS_SW_TEMPORARY_FAIL = 5;
    public static final int SCU_LDW_BUTTON_ON = 1;
    public static final int SCU_LKAINTERVENTION_LONGLY = 1;
    public static final int SCU_LKA_BUTTON_ON = 2;
    public static final int SCU_LKA_STATE_ACTIVE = 7;
    public static final int SCU_LKA_STATE_DEFAULT = 0;
    public static final int SCU_LKA_STATE_ENTER = 1;
    public static final int SCU_LKA_STATE_EXIT = 2;
    public static final int SCU_LKA_STATE_FUNCTION_LIMITED = 11;
    public static final int SCU_LKA_STATE_IN_USE = 3;
    public static final int SCU_LKA_STATE_OFF = 4;
    public static final int SCU_LKA_STATE_OFF_BY_TRAILER_MODE = 10;
    public static final int SCU_LKA_STATE_PASSIVE = 5;
    public static final int SCU_LKA_STATE_PERMANENT_FAIL = 9;
    public static final int SCU_LKA_STATE_STANDBY = 6;
    public static final int SCU_LKA_STATE_TEMPORARY_FAIL = 8;
    public static final int SCU_LONGCTRL_PLEASE_TAKE_OVER = 3;
    public static final int SCU_LONGCTRL_REMIND_NONE = 0;
    public static final int SCU_MODE_INDEX_ACC = 4;
    public static final int SCU_MODE_INDEX_ALC = 8;
    public static final int SCU_MODE_INDEX_CNGP = 10;
    public static final int SCU_MODE_INDEX_HWNGP = 9;
    public static final int SCU_MODE_INDEX_LCC = 5;
    public static final int SCU_MODE_INDEX_LCC_TJA_CAR = 7;
    public static final int SCU_MODE_INDEX_LCC_TJA_LINE = 6;
    public static final int SCU_MODE_INDEX_OFF = 0;
    public static final int SCU_NO_WARNING = 0;
    public static final int SCU_OTA_TAG_0 = 0;
    public static final int SCU_OTA_TAG_1 = 1;
    public static final int SCU_OTA_TAG_2 = 2;
    public static final int SCU_OTA_TAG_3 = 3;
    public static final int SCU_PHONEPKBUTTON_APIN = 1;
    public static final int SCU_PHONEPKBUTTON_APOUT = 2;
    public static final int SCU_PHONEPKBUTTON_CANCEL = 5;
    public static final int SCU_PHONEPKBUTTON_CONTINUE = 4;
    public static final int SCU_PHONEPKBUTTON_INVALID = 0;
    public static final int SCU_PHONEPKBUTTON_SUSPENG = 3;
    public static final int SCU_PHONESMBUTTON_CALLBACKWORK = 3;
    public static final int SCU_PHONESMBUTTON_CALLENTER = 1;
    public static final int SCU_PHONESMBUTTON_CALLFORWARD = 2;
    public static final int SCU_PHONESMBUTTON_EXITMODE = 4;
    public static final int SCU_PHONESMBUTTON_INVALID = 0;
    public static final int SCU_RADAR_WARNING_TYPE_DISPLAY = 2;
    public static final int SCU_RADAR_WARNING_TYPE_DISPLAY_VOICE = 1;
    public static final int SCU_REMOTE_CTRL_BTN_DOUBLE_CLICK = 1;
    public static final int SCU_REMOTE_CTRL_BTN_LONG_PRESS = 2;
    public static final int SCU_REVERSE_PARKING = 1;
    public static final int SCU_ROAD_ATTR_HOME_PARKING = 6;
    public static final int SCU_ROAD_ATTR_NOT_DEFINED = 0;
    public static final int SCU_ROAD_ATTR_PARKING_TOWER = 4;
    public static final int SCU_ROAD_ATTR_PRIVATE_ROAD = 2;
    public static final int SCU_ROAD_ATTR_PUBLIC_ROAD = 1;
    public static final int SCU_ROAD_ATTR_ROAD_PARKING = 5;
    public static final int SCU_ROAD_ATTR_UNDERGROUND_PARKING = 3;
    public static final int SCU_SIDE_REVERSING_WARNING_DISABLE = 0;
    public static final int SCU_SIDE_REVERSING_WARNING_ENABLE = 1;
    public static final int SCU_SLIF_ACTIVE = 3;
    public static final int SCU_SLIF_OFF = 0;
    public static final int SCU_SLIF_OVERRIDE = 5;
    public static final int SCU_SLIF_PASSIVE = 1;
    public static final int SCU_SLIF_PERMANENT_FAILURE = 7;
    public static final int SCU_SLIF_STANDBY = 2;
    public static final int SCU_SLIF_STANDWAIT = 4;
    public static final int SCU_SLIF_TEMPORARY_FAILURE = 6;
    public static final int SCU_SPEED_LIMIT_NORMAL = 1;
    public static final int SCU_SPEED_LIMIT_OVER_10PERCENT = 2;
    public static final int SCU_START_AUTO_PARK_IN = 1;
    public static final int SCU_START_AUTO_PARK_OUT = 3;
    public static final int SCU_START_VOICE_PARK_IN = 5;
    public static final int SCU_START_VOICE_PARK_OUT = 6;
    public static final int SCU_SW_FAIL = 2;
    public static final int SCU_SW_OFF = 0;
    public static final int SCU_SW_OFF_BY_TRAILER_MODE = 6;
    public static final int SCU_SW_OFF_RADAR_EMISSION_OFF = 3;
    public static final int SCU_SW_OFF_TRAILER_MODE = 4;
    public static final int SCU_SW_ON = 1;
    public static final int SCU_TAKE_OVER_LEVEL1 = 2;
    public static final int SCU_TAKE_OVER_LEVEL2 = 3;
    public static final int SCU_TAKE_OVER_LEVEL3 = 4;
    public static final int SCU_TRAFFIC_RECG_FAIL = 4;
    public static final int SCU_TRAFFIC_RECG_FUSION = 1;
    public static final int SCU_TRAFFIC_RECG_NAVIGATION = 3;
    public static final int SCU_TRAFFIC_RECG_OFF = 0;
    public static final int SCU_TRAFFIC_RECG_VIDEO = 2;
    public static final int SCU_UNABLE_TO_ACTIVATE_ALC = 8;
    public static final int SCU_UNABLE_TO_ACTIVATE_LCC = 7;
    public static final int SCU_WARNING_LEVEL1 = 1;
    public static final int SCU_WARNING_LEVEL2 = 2;
    public static final int SCU_WARNING_LEVEL3 = 3;
    @Deprecated
    public static final int SCU_WARNNING_LEVEL1 = 1;
    @Deprecated
    public static final int SCU_WARNNING_LEVEL2 = 2;
    public static final int SCU_XPILOT_ACTIVE_BY_ALC_DIR_LEFT = 10;
    public static final int SCU_XPILOT_ACTIVE_BY_ALC_DIR_RIGHT = 11;
    public static final int SCU_XPILOT_ACTIVE_BY_CAR = 5;
    public static final int SCU_XPILOT_ACTIVE_BY_LINE = 4;
    public static final int SCU_XPILOT_ACTIVE_ONLY_ACC = 3;
    public static final int SCU_XPILOT_LCC_DELAY_QUIT = 9;
    public static final int SCU_XPILOT_LCC_SUSPEND = 8;
    public static final int SCU_XPILOT_OFF = 0;
    public static final int SCU_XPILOT_PASSIVE = 1;
    public static final int SCU_XPILOT_PERMANENT_FAILURE = 7;
    public static final int SCU_XPILOT_STANDBY = 2;
    public static final int SCU_XPILOT_TEMPORARY_FAILURE = 6;
    public static final int SDC_CONTROL_AEB_ALARM_DISPLAY = 2;
    public static final int SDC_CONTROL_AEB_ALARM_DISPLAY_VOICE = 1;
    public static final int SDC_CONTROL_AEB_ALARM_OFF = 0;
    public static final int SDC_CONTROL_INDEX_0 = 0;
    public static final int SDC_CONTROL_INDEX_1 = 1;
    public static final int SDC_CONTROL_INDEX_2 = 2;
    public static final int SDC_CONTROL_INDEX_3 = 3;
    public static final int SDC_DESIGN_SCENE = 0;
    public static final int SDC_NOT_DESIGN_SCENE = 1;
    public static final int SDC_NOT_RADAR_BLIND_AREA = 0;
    public static final int SDC_RADAR_BLIND_AREA = 1;
    public static final int SDC_RADAR_STATUS_FAULT = 1;
    public static final int SDC_RADAR_STATUS_NORMAL = 0;
    public static final int SDC_TIPS_1 = 1;
    public static final int SDC_TIPS_2 = 2;
    public static final int SDC_TIPS_3 = 3;
    public static final int SDC_TIPS_NONE = 0;
    public static final int SDC_TTS_1 = 1;
    public static final int SDC_TTS_2 = 2;
    public static final int SDC_TTS_3 = 3;
    public static final int SDC_TTS_NONE = 0;
    public static final int SEATBELTREQ_FASTEN = 1;
    public static final int SEATBELTREQ_NONE = 0;
    public static final int SPEED_LIMIT_ACTIVE = 3;
    public static final int SPEED_LIMIT_DRIVER_CONFIRM_ERROR = 0;
    public static final int SPEED_LIMIT_DRIVER_CONFIRM_NO = 1;
    public static final int SPEED_LIMIT_DRIVER_CONFIRM_OK = 2;
    public static final int SPEED_LIMIT_NO_FAULT = 0;
    public static final int SPEED_LIMIT_PERMANENT_ERROR = 2;
    public static final int SPEED_LIMIT_TEMPORARY_ERROR = 1;
    public static final int STATE_50_METERS_AWAY_FROM_THE_PARKING_LOT_ENTRANCE = 0;
    public static final int STATE_WITHIN_50_METERS_OF_THE_PARKING_LOT_ENTRANCE = 1;
    public static final int SWITCH_PARK_BY_MEMORY_WITHOUT_LICENSE = 3;
    private static final String TAG = "CarScuManager";
    public static final int VOICE_CHANGE_TO_LEFT_LANE = 1;
    public static final int VOICE_CHANGE_TO_RIGHT_LANE = 2;
    public static final int XPILOT3_STATUS_INITIALIZATION = 0;
    public static final int XPILOT3_STATUS_OFF = 2;
    public static final int XPILOT3_STATUS_ON = 1;
    public static final int XPILOT3_STATUS_POWER_ON_AGAIN = 3;
    public static final int XPU_SCU_LSS_ALARM_ENABLED = 1;
    public static final int XPU_SCU_LSS_ALL_ENABLED = 3;
    public static final int XPU_SCU_LSS_BREAK_DOWN = 4;
    public static final int XPU_SCU_LSS_CLOSED = 0;
    public static final int XPU_SCU_LSS_DEVIATION_ENABLED = 2;
    private final ArraySet<Integer> mScuPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarScuEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarScuManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mScuPropertyIds = new ArraySet<>(Arrays.asList(557852161, 557852169, 557852202, 557852162, 557852165, 557852231, 560014897, 560014896, 557852213, 557917783, 557917784, 557917785, 557852187, 557852179, 557852178, 557852180, 557852210, 557852207, 560014910, 560014894, 560014903, 560014899, 560014909, 557917739, 557917750, 560014938, 560014892, 560014911, 557917770, 557917774, 557917773, 557917769, 557852230, 557852239, 560014912, 557852177, 557852163, 557852164, 557852166, 557852167, 557852168, 557852170, 557852171, 557852172, 557852173, 557852174, 557852175, 557852181, 557852182, 557852183, 557852184, 557852185, 557852186, 560997922, 557852196, 557852195, 557852197, 557852198, 557852199, 557852200, 557852201, 557852274, 557852212, 557917779, 560997946, 560997947, 557852232, 557852225, 557917782, 557917781, 560997948, 557852235, 557917764, 560014914, 557917780, 557852236, 557852240, 557917763, 557852241, 557852242, 557852217, 557852265, 557852266, 557852267, 557852255, 560014945, 560014942, 560014946, 560014947, 560014948, 557852261, 557852262, 560014876, 560014877, 560014956, 557852269, 557917818, 557852283, 557852286, 557852287, 557852216, 560014972, 560014973, 557852295, 557852289, 557917826, 557852291, 557852292, 557852296, 557852297, 557852298, 557852299, 557852288, 557852300, 557852301, 560014917, 557852272, 557852302, 557852303, 557852304, 557852305, 557852306, 557852307, 557917844, 557852309, 557852310, 557852311, 557852312, 557852313, 557852314, 557917851, 557917852, 557917853, 557852318, 557852319, 557852320, 557852321, 557852322, 557852323, 560998052, 560998053, 557852326, 557852327, 557852329, 557852330, 557852328, 557852331, 557852332, 557852333, 557852334, 557852335, 557852336, 557852337, 557852338, 557852339, 557852340, 557852341, 557852342, 557852343, 557852344, 557852345, 557852346, 557852347, 557852348, 557852349, 557852350, 557852351, 557852352, 557852353, 557852354, 557852355, 557852356, 557852357, 557852358, 557852359, 557852360, 557852361, 557917898, 557852363, 557852364, 557852368, 557852365, 557852366, 557852367, 557852369, 557852370, 557852371, 557852372, 557917909, 560015062, 557852377, 557852379, 557852380, 557852381, 557852378, 557852382, 557852385, 557852386, 557852394, 557852395, 557852396, 557917935, 557917936, 557917937, 557917938, 557856794, 557917939, 557852404, 557852405, 557852408, 557852409, 557852410, 557917947, 557852412, 557852413));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mScuPropertyIds;
    }

    public final int getSideReversingWarning() throws Exception {
        return this.mService.getSideReversingWarning();
    }

    public final void setSideReversingWarning(int enable) throws Exception {
        this.mService.setSideReversingWarning(enable);
    }

    public final int getParkingStatus() throws Exception {
        return this.mService.getParkingStatus();
    }

    public final void setLocationInfo(float latitude, float longitude, float altitude, float bearing, float accuracy, long gpsTime) throws Exception {
        this.mService.setLocationInfo(latitude, longitude, altitude, bearing, accuracy, gpsTime);
    }

    public final void setAutoPilotLocationInfo(float latitude, float longitude, float altitude, float bearing, float accuracy, float gpsSpeed, long gpsTime) throws Exception {
        this.mService.setAutoPilotLocationInfo(latitude, longitude, altitude, bearing, accuracy, gpsSpeed, gpsTime);
    }

    public final void setFactoryScuTest(int cmd) throws Exception {
        this.mService.setScuTest(cmd);
    }

    public final int[] getScu322LogData() throws Exception {
        return this.mService.getScu322LogData();
    }

    public final int[] getScu3FDLogData() throws Exception {
        return this.mService.getScu3FDLogData();
    }

    public final int[] getScu3FELogData() throws Exception {
        return this.mService.getScu3FELogData();
    }

    public final int getScuOperationTips() throws Exception {
        return this.mService.getScuOperationTips();
    }

    public final void setAutoParkInState(int state) throws Exception {
        this.mService.setAutoParkInState(state);
    }

    public final void setAutoParkOutState(int state) throws Exception {
        this.mService.setAutoParkOutState(state);
    }

    public int getNearestEnableRadar() throws Exception {
        return this.mService.getNearestEnableRadar();
    }

    private int bytesToInt(byte[] src, int offset) {
        int value = (src[offset] & 255) | ((src[offset + 1] & 255) << 8) | ((src[offset + 2] & 255) << 16) | ((src[offset + 3] & 255) << 24);
        return value;
    }

    public int[] getFrontRadarLevel() throws Exception {
        return this.mService.getFrontRadarLevel();
    }

    public int[] getTailRadarLevel() throws Exception {
        return this.mService.getTailRadarLevel();
    }

    public int[] getFrontRadarFaultSt() throws Exception {
        return this.mService.getFrontRadarFaultSt();
    }

    public int[] getTailRadarFaultSt() throws Exception {
        return this.mService.getTailRadarFaultSt();
    }

    public float[] getMileageExtraParams() throws Exception {
        return this.mService.getMileageExtraParams();
    }

    private float getGpsSpeed() throws Exception {
        return this.mService.getGpsSpeed();
    }

    @Deprecated
    public void setPhoneApButton(int action) throws Exception {
        this.mService.setPhoneAPButton(action);
    }

    public void setCommonHomeSlotID(int id) throws Exception {
        this.mService.setScuCommonHomeSlotID(id);
    }

    @Deprecated
    public int getAutoParkErrorCode() throws Exception {
        return this.mService.getAutoParkErrorCode();
    }

    public final void setParkLotChoseIndex2Scu(int index) throws Exception {
        this.mService.setParkLotChoseIndex2Scu(index);
    }

    public final void setParkLotRecvIndex2Scu(int index) throws Exception {
        this.mService.setParkLotRecvIndex2Scu(index);
    }

    public final void setRadarWarningVoiceStatus(int type) throws Exception {
        this.mService.setRadarWarningVoiceStatus(type);
    }

    public int getRadarWarningVoiceStatus() throws Exception {
        return this.mService.getRadarWarningVoiceStatus();
    }

    public void setSpdLimitWarnType(int type) throws Exception {
        this.mService.setSpdLimitWarnType(type);
    }

    public int getSpdLimitWarnType() throws Exception {
        return this.mService.getSpdLimitWarnType();
    }

    public void setAutoParkReq(int cmd) throws Exception {
        this.mService.setAutoParkReq(cmd);
    }

    public void setKeyRemoteParkType(int type) throws Exception {
        this.mService.setKeyRemoteParkType(type);
    }

    public int getKeyRemoteType() throws Exception {
        return this.mService.getKeyRemoteType();
    }

    @Deprecated
    public void setPhoneParkType(int type) throws Exception {
        this.mService.setPhoneParkType(type);
    }

    @Deprecated
    public int getPhoneParkType() throws Exception {
        return this.mService.getPhoneParkType();
    }

    public void setAdasMapInfo(byte[] info) throws Exception {
        this.mService.setAdasMapInfo(info);
    }

    @Deprecated
    public float[] getParkSlotInfo() throws Exception {
        return this.mService.getParkSlotInfo();
    }

    @Deprecated
    public float[] getEnvCharacterInfo() throws Exception {
        return this.mService.getEnvCharacterInfo();
    }

    @Deprecated
    public float[] getCarPositionInfo() throws Exception {
        return this.mService.getCarPositionInfo();
    }

    @Deprecated
    public float[] getRadarDataInfo() throws Exception {
        return this.mService.getRadarDataInfo();
    }

    public int getBlindAreaLeftWarning() throws Exception {
        return this.mService.getBlindAreaLeftWarning();
    }

    public int getBlindAreaRightWarning() throws Exception {
        return this.mService.getBlindAreaRightWarning();
    }

    public int getRearCrossLeftWarning() throws Exception {
        return this.mService.getRearCrossLeftWarning();
    }

    public int getRearCrossRightWarning() throws Exception {
        return this.mService.getRearCrossRightWarning();
    }

    public int getXpuLongCtrlRemind() throws Exception {
        return this.mService.getXpuLongCtrlRemind();
    }

    public int getXpilotStatus() throws Exception {
        return this.mService.getXpilotStatus();
    }

    public int getXpuLatCtrlRemind() throws Exception {
        return this.mService.getXpuLatCtrlRemind();
    }

    public int getAccStatus() throws Exception {
        return this.mService.getAccStatus();
    }

    public void setFrontCollisionSecurity(int enable) throws Exception {
        this.mService.setFrontCollisionSecurity(enable);
    }

    public int getFrontCollisionSecurity() throws Exception {
        return this.mService.getFrontCollisionSecurity();
    }

    public void setLaneDepartureWarning(int enable) throws Exception {
        this.mService.setLaneDepartureWarning(enable);
    }

    public int getLaneDepartureWarning() throws Exception {
        return this.mService.getLaneDepartureWarning();
    }

    public void setLaneSupportSystemStateAndWarning(int enable) throws Exception {
        this.mService.setLaneSupportSystemStateAndWarning(enable);
    }

    public int getLaneSupportSystemStateAndWarning() throws Exception {
        return this.mService.getLaneSupportSystemStateAndWarning();
    }

    public void setFarLampAutoSwitch(int enable) throws Exception {
        this.mService.setFarLampAutoSwitch(enable);
    }

    public int getFarLampAutoSwitch() throws Exception {
        return this.mService.getFarLampAutoSwitch();
    }

    public void setCutLinePreventSw(int enable) throws Exception {
        this.mService.setCutLinePreventSw(enable);
    }

    public int getCutLinePreventSw() throws Exception {
        return this.mService.getCutLinePreventSw();
    }

    public void setBlindAreaDetectionWarning(int enable) throws Exception {
        this.mService.setBlindAreaDetectionWarning(enable);
    }

    public int getBlindAreaDetectionWarning() throws Exception {
        return this.mService.getBlindAreaDetectionWarning();
    }

    public void setRearCrossEmergencyWarning(int enable) throws Exception {
        this.mService.setRearCrossEmergencyWarning(enable);
    }

    public int getRearCrossEmergencyWarning() throws Exception {
        return this.mService.getRearCrossEmergencyWarning();
    }

    public void setRearCollisionSecurity(int enable) throws Exception {
        this.mService.setRearCollisionSecurity(enable);
    }

    public int getRearCollisionSecurity() throws Exception {
        return this.mService.getRearCollisionSecurity();
    }

    public void setDoorOpenWarning(int enable) throws Exception {
        this.mService.setDoorOpenWarning(enable);
    }

    public int getDoorOpenWarning() throws Exception {
        return this.mService.getDoorOpenWarning();
    }

    public void setLaneChangeAssist(int enable) throws Exception {
        this.mService.setLaneChangeAssist(enable);
    }

    public int getLaneChangeAssist() throws Exception {
        return this.mService.getLaneChangeAssist();
    }

    @Deprecated
    public void setFatigueDetectionSw(int enable) throws Exception {
        this.mService.setFatigueDetectionSw(enable);
    }

    @Deprecated
    public int getFatigueDetectionSw() throws Exception {
        return this.mService.getFatigueDetectionSw();
    }

    public void setTrafficSignRecognition(int enable) throws Exception {
        this.mService.setTrafficSignRecognition(enable);
    }

    public int getTrafficSignRecognition() throws Exception {
        return this.mService.getTrafficSignRecognition();
    }

    public void setIntelligentSpeedLimit(int mode) throws Exception {
        this.mService.setIntelligentSpeedLimit(mode);
    }

    public int getIntelligentSpeedLimit() throws Exception {
        return this.mService.getIntelligentSpeedLimit();
    }

    public void setLaneAlignmentAssist(int enable) throws Exception {
        this.mService.setLaneAlignmentAssist(enable);
    }

    public int getLaneAlignmentAssist() throws Exception {
        return this.mService.getLaneAlignmentAssist();
    }

    @Deprecated
    public void setHighSpeedNavgation(int enable) throws Exception {
        this.mService.setHighSpeedNavigation(enable);
    }

    @Deprecated
    public int getHighSpeedNavgation() throws Exception {
        return this.mService.getHighSpeedNavigation();
    }

    public void setHighSpeedNavigation(int enable) throws Exception {
        this.mService.setHighSpeedNavigation(enable);
    }

    public int getHighSpeedNavigation() throws Exception {
        return this.mService.getHighSpeedNavigation();
    }

    public void setSuperParkMode(int active) throws Exception {
        this.mService.setSuperParkMode(active);
    }

    public void setIntelligentCallButton(int enable) throws Exception {
        this.mService.setIntelligentCallButton(enable);
    }

    public int getIntelligentCallButton() throws Exception {
        return this.mService.getIntelligentCallButton();
    }

    public void setPhoneSmButton(int enable) throws Exception {
        this.mService.setPhoneSMButton(enable);
    }

    public int getPhoneSmButton() throws Exception {
        return this.mService.getPhoneSMButton();
    }

    public void setAutoParkSwitch(int enable) throws Exception {
        this.mService.setAutoParkSwitch(enable);
    }

    public int getAutoParkSwitch() throws Exception {
        return this.mService.getAutoParkSwitch();
    }

    public void setKeyRemoteSmButton(int enable) throws Exception {
        this.mService.setKeyRemoteSMButton(enable);
    }

    public int getKeyRemoteSmButton() throws Exception {
        return this.mService.getKeyRemoteSMButton();
    }

    public void setKeyPark(int enable) throws Exception {
        this.mService.setScuKeyPark(enable);
    }

    public int getKeyPark() throws Exception {
        return this.mService.getScuKeyPark();
    }

    public float[] getAltimeter() throws Exception {
        return this.mService.getScuAltimeter();
    }

    public int[] getSlotTheta() throws Exception {
        return this.mService.getScuSlotTheta();
    }

    public int[] getTargetParkingPosition() throws Exception {
        return this.mService.getScuTargetParkingPosition();
    }

    public int getFrontRadarDistance() throws Exception {
        return this.mService.getScuFrontMinDistance();
    }

    public int getTailRadarDistance() throws Exception {
        return this.mService.getScuRearMinDistance();
    }

    public int getScuModeIndex() throws Exception {
        return this.mService.getScuModeIndex();
    }

    public void setLocalWeather(int enable, int temperature, int humidity, int weather) throws Exception {
        this.mService.setScuLocalWeather(enable, temperature, humidity, weather);
    }

    public void setPhoneSmMode(int mode) throws Exception {
        this.mService.setScuPhoneSmMode(mode);
    }

    private int getPhoneSmMode() throws Exception {
        return this.mService.getScuPhoneSmMode();
    }

    @Deprecated
    public void setScuRoadAttr(int attr) throws Exception {
        this.mService.setScuRoadAttr(attr);
    }

    public void setRoadAttributes(int parking, int road) throws Exception {
        this.mService.setScuRoadAttributes(parking, road);
    }

    public void setDetailRoadClass(int roadClass) throws Exception {
        this.mService.setScuDetailRoadClass(roadClass);
    }

    public void setAssLineChanged(int type) throws Exception {
        this.mService.setScuAssLineChanged(type);
    }

    public int getAssLineChanged() throws Exception {
        return this.mService.getScuAssLineChanged();
    }

    public void setScuDmsMode(int mode) throws Exception {
        this.mService.setScuDmsMode(mode);
    }

    public void setSeatBeltReq(int req) throws Exception {
        this.mService.setScuSeatBeltReq(req);
    }

    public int getRadarDisplayActive() throws Exception {
        return this.mService.getScuRadarDisplayActive();
    }

    public int getErrorTips() throws Exception {
        return this.mService.getScuErrorTips();
    }

    public int getSuperParkMode() throws Exception {
        return this.mService.getScuSuperParkMode();
    }

    public float[] getLocatData() throws Exception {
        return this.mService.getScuLocatData();
    }

    public float[] getParkingProgress() throws Exception {
        return this.mService.getScuParkingProgress();
    }

    public float[] getSensorData() throws Exception {
        return this.mService.getScuSensorData();
    }

    public float[] getLAvmData() throws Exception {
        return this.mService.getScuLAvmData();
    }

    public float[] getRAvmData() throws Exception {
        return this.mService.getScuRAvmData();
    }

    public float[] getSlotForPark() throws Exception {
        return this.mService.getScuSlotForPark();
    }

    public int getRadarVoiceActive() throws Exception {
        return this.mService.getScuRadarVoiceActive();
    }

    public int getRadarVoiceTone() throws Exception {
        return this.mService.getScuRadarVoiceTone();
    }

    public float[] getFrontRadarData() throws Exception {
        return this.mService.getFrontRadarData();
    }

    public float[] getTailRadarData() throws Exception {
        return this.mService.getTailRadarData();
    }

    public float[] getSlotData() throws Exception {
        return this.mService.getScuSlotData();
    }

    public int getSlotsNumber() throws Exception {
        return this.mService.getScuSlotsNumber();
    }

    public int[] getMrrAssistSystemStates() throws Exception {
        return this.mService.getScuMrrAssistSystemStates();
    }

    public static String getServiceName() {
        return Car.XP_SCU_SERVICE;
    }

    public int getRearCollisionWarning() throws Exception {
        return this.mService.getScuRearCollisionWarning();
    }

    public int getLeftDoorOpenWarning() throws Exception {
        return this.mService.getScuLeftDoorOpenWarning();
    }

    public int getRightDoorOpenWarning() throws Exception {
        return this.mService.getScuRightDoorOpenWarning();
    }

    public void setFreeParking1Data(float rx, float ry, float rtheta, int state, int attr, float ds, float r) throws Exception {
        this.mService.setScuFreeParking1Data(rx, ry, rtheta, state, attr, ds, r);
    }

    public void setFreeParking2Data(float rx, float ry, float rtheta, int state, int attr, float ds, float r) throws Exception {
        this.mService.setScuFreeParking2Data(rx, ry, rtheta, state, attr, ds, r);
    }

    public int getScuRearMirrorControlState() throws Exception {
        return this.mService.getScuRearMirrorControlState();
    }

    public int getExtraLatCtrlRemindInfo() throws Exception {
        return this.mService.getScuExtraLatCtrlRemindInfo();
    }

    public int getScuAlarmFaultStatus() throws Exception {
        return this.mService.getScuAlarmFaultStatus();
    }

    public int[] getScu322LogDataD20() throws Exception {
        return this.mService.getScu322LogDataD20();
    }

    public int getScuSlaStatus() throws Exception {
        return this.mService.getScuSlaStatus();
    }

    public int getScuLdwStatus() throws Exception {
        return this.mService.getScuLdwStatus();
    }

    public int getScuBsdStatus() throws Exception {
        return this.mService.getScuBsdStatus();
    }

    public int getScuRctaStatus() throws Exception {
        return this.mService.getScuRctaStatus();
    }

    public void setDistractionSwitch(int enable) throws Exception {
        this.mService.setScuDistractionSwitch(enable);
    }

    @Deprecated
    public int getCutInPreventionWarning() throws Exception {
        return this.mService.getScuCutInPreventionWarning();
    }

    public int getScuLkaState() throws Exception {
        return this.mService.getScuLkaState();
    }

    public int getAccLkaWarning() throws Exception {
        return this.mService.getScuAccLkaWarning();
    }

    public int getRoadVoiceTips() throws Exception {
        return this.mService.getScuRoadVoiceTips();
    }

    public void setSlaAlarmSwitch(int state) throws Exception {
        this.mService.setScuSlaAlarmSwitch(state);
    }

    public int getSlaAlarmSwitchState() throws Exception {
        return this.mService.getScuSlaAlarmSwitchState();
    }

    public float[] getLocatDataWithZ() throws Exception {
        return this.mService.getScuLocatDataWithZ();
    }

    public void setFsdSwitch(int onOff) throws Exception {
        this.mService.setScuFsdSwitch(onOff);
    }

    public int getFsdSwitchState() throws Exception {
        return this.mService.getScuFsdSwitchState();
    }

    public void notifyRearViewMirrorAdjustmentPageState(int state) throws Exception {
        this.mService.notifyScuRearViewMirrorAdjustmentPageState(state);
    }

    public int getMrrFailureSt() throws Exception {
        return this.mService.getScuMrrFailureSt();
    }

    public int getFishEyeCamFailureSt() throws Exception {
        return this.mService.getScuFishEyeCamFailureSt();
    }

    public int getMainCamFailureSt() throws Exception {
        return this.mService.getScuMainCamFailureSt();
    }

    public int getNarrowCamFailureSt() throws Exception {
        return this.mService.getScuNarrowCamFailureSt();
    }

    public int[] getSideCamsFailureSt() throws Exception {
        return this.mService.getScuSideCamsFailureSt();
    }

    public int getDisplayCruiseSpeed() throws Exception {
        return this.mService.getScuDisplayCruiseSpeed();
    }

    public int getQuitNgpOddSt() throws Exception {
        return this.mService.getQuitNgpOddSt();
    }

    public void setNgpOperationButton(int state) throws Exception {
        this.mService.setScuNgpOperationButton(state);
    }

    public int getNgpOperationButtonSt() throws Exception {
        return this.mService.getScuNgpOperationButtonSt();
    }

    public int getNgpLcTips1() throws Exception {
        return this.mService.getScuNgpLcTips1();
    }

    public int getNgpInfoTips1() throws Exception {
        return this.mService.getScuNgpInfoTips1();
    }

    public void setNgpTipsWindowsSw(int sw) throws Exception {
        this.mService.setScuNgpTipsWindowsSw(sw);
    }

    public int getNgpTipsWindowsSwSt() throws Exception {
        return this.mService.getScuNgpTipsWindowsSwSt();
    }

    public int[] getDoorsObstacleDetectionSt() throws Exception {
        return this.mService.getScuDoorsObstacleDetectionSt();
    }

    public int[] getDoorsRadarDistance() throws Exception {
        return this.mService.getScuDoorsRadarDistance();
    }

    @Deprecated
    public int[] getDoorsRadarDisplayLevel() throws Exception {
        return this.mService.getScuDoorsRadarDisplayLevel();
    }

    public int getXpilot3St() throws Exception {
        return this.mService.getScuXpilot3Status();
    }

    public void setNgpPreferFastLaneSw(int sw) throws Exception {
        this.mService.setScuNgpPreferFastLaneSw(sw);
    }

    public int getNgpPreferFastLaneSwSt() throws Exception {
        return this.mService.getScuNgpPreferFastLaneSwSt();
    }

    public void setNgpAvoidTruckSw(int sw) throws Exception {
        this.mService.setScuNgpAvoidTruckSw(sw);
    }

    public int getNgpAvoidTruckSwSt() throws Exception {
        return this.mService.getScuNgpAvoidTruckSwSt();
    }

    public void setNgpDriverConfirmLaneChangeSw(int sw) throws Exception {
        this.mService.setScuNgpDriverConfirmLaneChangeSw(sw);
    }

    public int getNgpDriverConfirmLaneChangeSwSt() throws Exception {
        return this.mService.getScuNgpDriverConfirmLaneChangeSwSt();
    }

    @Deprecated
    public void setNgpLaneChangeMode(int mode) throws Exception {
        this.mService.setScuNgpLaneChangeMode(mode);
    }

    @Deprecated
    public int getNgpLaneChangeMode() throws Exception {
        return this.mService.getScuNgpLaneChangeMode();
    }

    public void setNgpRemindMode(int mode) throws Exception {
        this.mService.setScuNgpRemindMode(mode);
    }

    public int getNgpRemindMode() throws Exception {
        return this.mService.getScuNgpRemindMode();
    }

    public void setSsLeftSystemStatus(byte[] data) throws Exception {
        this.mService.setScuSsLeftSystemStatus(data);
    }

    public void setSsRightSystemStatus(byte[] data) throws Exception {
        this.mService.setScuSsRightSystemStatus(data);
    }

    @Deprecated
    public int getSdCUltrasonicRadarVoiceTone() throws Exception {
        return this.mService.getScuSdcUltrasonicRadarVoiceTone();
    }

    public int getSdcUltrasonicRadarVoiceTone() throws Exception {
        return this.mService.getScuSdcUltrasonicRadarVoiceTone();
    }

    public void setScuOtaTagStatus(int tag) throws Exception {
        this.mService.setScuOtaTagStatus(tag);
    }

    public int getSdcTips() throws Exception {
        return this.mService.getScuSdcTips();
    }

    public int getSdcTts() throws Exception {
        return this.mService.getScuSdcTts();
    }

    public void setCurrentRoadSpeedLimit(int speedLimit) throws Exception {
        this.mService.setScuCurrentRoadSpeedLimit(speedLimit);
    }

    public int getScuLccExitReason() throws Exception {
        return this.mService.getScuLccExitReason();
    }

    public int getScuAccExitReason() throws Exception {
        return this.mService.getScuAccExitReason();
    }

    public void setElkSwitch(int onOff) throws Exception {
        this.mService.setScuElkSwitch(onOff);
    }

    public int getElkSwitchState() throws Exception {
        return this.mService.getScuElkSwitchState();
    }

    public int getRightSdcRadarStatus() throws Exception {
        return this.mService.getScuRightSdcRadarStatus();
    }

    public int getLeftSdcRadarStatus() throws Exception {
        return this.mService.getScuLeftSdcRadarStatus();
    }

    public int getLeftSdcBlindStatus() throws Exception {
        return this.mService.getScuLeftSdcBlindStatus();
    }

    public int getLeftSdcSceneStatus() throws Exception {
        return this.mService.getScuLeftSdcSceneStatus();
    }

    public int getRightSdcBlindStatus() throws Exception {
        return this.mService.getScuRightSdcBlindStatus();
    }

    public int getRightSdcSceneStatus() throws Exception {
        return this.mService.getScuRightSdcSceneStatus();
    }

    public int getSdcAutoModeStatus() throws Exception {
        return this.mService.getScuSdcAutoModeStatus();
    }

    public int getSdcCtrlIndex1() throws Exception {
        return this.mService.getScuSdcCtrlIndex1();
    }

    public int getSdcCtrlIndex2() throws Exception {
        return this.mService.getScuSdcCtrlIndex2();
    }

    public int getLeftSdcURadarDistance() throws Exception {
        return this.mService.getScuLeftSdcURadarDistance();
    }

    public int getRightSdcURadarDistance() throws Exception {
        return this.mService.getScuRightSdcURadarDistance();
    }

    public void setParkingGroundState(int state) throws Exception {
        this.mService.setScuParkingGroundState(state);
    }

    public int getRightSdcRadarDistance() throws Exception {
        return this.mService.getScuRightSdcRadarDistance();
    }

    public int getRightSdcRadarTof() throws Exception {
        return this.mService.getScuRightSdcRadarTof();
    }

    public int getRightSdcRadarPeakLevel() throws Exception {
        return this.mService.getScuRightSdcRadarPeakLevel();
    }

    public int getRightSdcRadarStatusCcp() throws Exception {
        return this.mService.getScuRightSdcRadarStatusCcp();
    }

    public int getRightSdcRadarErrorStatus() throws Exception {
        return this.mService.getScuRightSdcRadarErrorStatus();
    }

    public int getLeftSdcRadarDistance() throws Exception {
        return this.mService.getScuLeftSdcRadarDistance();
    }

    public int getLeftSdcRadarTof() throws Exception {
        return this.mService.getScuLeftSdcRadarTof();
    }

    public int getLeftSdcRadarPeakLevel() throws Exception {
        return this.mService.getScuLeftSdcRadarPeakLevel();
    }

    public int getLeftSdcRadarStatusCcp() throws Exception {
        return this.mService.getScuLeftSdcRadarStatusCcp();
    }

    public int getLeftSdcRadarErrorStatus() throws Exception {
        return this.mService.getScuLeftSdcRadarErrorStatus();
    }

    public int getRightSdcRadarRt() throws Exception {
        return this.mService.getScuRightSdcRadarRt();
    }

    public int getRightSdcRadarWaveWidth() throws Exception {
        return this.mService.getScuRightSdcRadarWaveWidth();
    }

    public int getLeftSdcRadarRt() throws Exception {
        return this.mService.getScuLeftSdcRadarRt();
    }

    public int getLeftSdcRadarWaveWidth() throws Exception {
        return this.mService.getScuLeftSdcRadarWaveWidth();
    }

    public int getRightSdcIndexN() throws Exception {
        return this.mService.getRightRightSdcIndexN();
    }

    public int getLeftSdcIndexN() throws Exception {
        return this.mService.getLeftSdcIndexN();
    }

    public int[] getSdcRadarFusion() throws Exception {
        return this.mService.getScuSdcRadarFusion();
    }

    public int getLeftSdcRadarHallCounter() throws Exception {
        return this.mService.getScuLeftSdcRadarHallCounter();
    }

    public int getRightSdcRadarHallCounter() throws Exception {
        return this.mService.getScuRightSdcRadarHallCounter();
    }

    public int getAebAlarmSwitchState() throws Exception {
        return this.mService.getScuAebAlarmSwitchState();
    }

    public int getSteeringWheelEps() throws Exception {
        return this.mService.getScuSteeringWheelEps();
    }

    public void setVoiceLaneChangeCommand(int cmd) throws Exception {
        this.mService.setScuVoiceLaneChangeCommand(cmd);
    }

    public int getNgpModeStatus() throws Exception {
        return this.mService.getScuNgpModeStatus();
    }

    public int getParkByMemorySwSt() throws Exception {
        return this.mService.getScuParkByMemorySwSt();
    }

    public void setParkByMemorySw(int sw) throws Exception {
        this.mService.setScuParkByMemorySw(sw);
    }

    public int getDsmPrompt() throws Exception {
        return this.mService.getScuDsmPrompt();
    }

    public int getScuLdwLkaSwitchStatus() throws Exception {
        return this.mService.getScuLdwLkaSwitchStatus();
    }

    public void setScuLdwLkaSwitchStatus(int enable) throws Exception {
        this.mService.setScuLdwLkaSwitchStatus(enable);
    }

    public int getScuLkaSwitchState() throws Exception {
        return this.mService.getScuLkaSwitchState();
    }

    public void setCurrentElectronicEyeSpeedLimitAndDistance(int speedLimit, int distance) throws Exception {
        this.mService.setScuCurrentElectronicEyeSpeedLimitAndDistance(speedLimit, distance);
    }

    public float[] getURadarDataInfo() throws Exception {
        return this.mService.getScuURadarDataInfo();
    }

    public int getAlcCtrlRemindInfo() throws Exception {
        return this.mService.getScuAlcCtrlRemindInfo();
    }

    public int getRemoteFlag() throws Exception {
        return this.mService.getScuRemoteFlag();
    }

    public void setRoadAttribType(int road) throws Exception {
        this.mService.setScuRoadAttribType(road);
    }

    public void setSpeedLimitDriverConfirmSwitch(int enable) throws Exception {
        this.mService.setScuSpeedLimitDriverConfirmSwitch(enable);
    }

    public int getSpeedLimitDriverConfirmStatus() throws Exception {
        return this.mService.getScuSpeedLimitDriverConfirmStatus();
    }

    public void setSpeedLimitRange(int range) throws Exception {
        this.mService.setScuSpeedLimitRange(range);
    }

    public int getSpeedLimitRange() throws Exception {
        return this.mService.getScuSpeedLimitRange();
    }

    public void setSpeedLimitSwitchState(int enable) throws Exception {
        this.mService.setScuSpeedLimitSwitchState(enable);
    }

    public int getSpeedLimitSwitchState() throws Exception {
        return this.mService.getScuSpeedLimitSwitchState();
    }

    public void setIntelligentSpeedLimitValue(int value) throws Exception {
        this.mService.setScuIntelligentSpeedLimitValue(value);
    }

    public int getSpeedLimitControlSystemState() throws Exception {
        return this.mService.getScuSpeedLimitControlSystemState();
    }

    public int getMemoryParkingState() throws Exception {
        return this.mService.getScuMemoryParkingState();
    }

    public int getSpeedLimitRemindVoice() throws Exception {
        return this.mService.getScuSpeedLimitRemindVoice();
    }

    public int getDsmStatus() throws Exception {
        return this.mService.getScuDsmStatus();
    }

    public void setDsmStatus(int status) throws Exception {
        this.mService.setScuDsmStatus(status);
    }

    public void sendNaviLoadLinkType(int type) throws Exception {
        this.mService.sendScuNaviLoadLinkType(type);
    }

    public int[] getLeftRightBlindSpotDetectionSwitchStatus() throws Exception {
        return this.mService.getScuLeftRightBlindSpotDetectionSwitchStatus();
    }

    public int[] getLeftRightRearCollisionSwitchStatus() throws Exception {
        return this.mService.getScuLeftRightRearCollisionSwitchStatus();
    }

    public int[] getLeftRightDoorOpenWarningSwitchStatus() throws Exception {
        return this.mService.getScuLeftRightDoorOpenWarningSwitchStatus();
    }

    public int[] getLeftRightRearCrossTrafficAlertStatus() throws Exception {
        return this.mService.getScuLeftRightRearCrossTrafficAlertStatus();
    }

    public void setNaviRoadConnectAttrib(int attrib) throws Exception {
        this.mService.setScuNaviRoadConnectAttrib(attrib);
    }

    public void setNaviDangerAreaRDInfo(int dangerAreaLoc, int dangerLane, int dangerLaneNum, int dangerType, int dangerLevel, int dangerPro, int dangerAct) throws Exception {
        this.mService.setScuNaviDangerAreaRDInfo(dangerAreaLoc, dangerLane, dangerLaneNum, dangerType, dangerLevel, dangerPro, dangerAct);
    }

    public int getDoorsLRadarDisplayLevel() throws Exception {
        return this.mService.getScuDoorsLRadarDisplayLevel();
    }

    public int getDoorsRRadarDisplayLevel() throws Exception {
        return this.mService.getScuDoorsRRadarDisplayLevel();
    }

    public int getDoorsLRadarDistance() throws Exception {
        return this.mService.getScuDoorsLRadarDistance();
    }

    public int getDoorsRRadarDistance() throws Exception {
        return this.mService.getScuDoorsRRadarDistance();
    }

    public void setEventInfoRD(int type) throws Exception {
        this.mService.setScuEventInfoRD(type);
    }

    public int getHmiDopRemind() throws Exception {
        return this.mService.getScuHmiDopRemind();
    }

    public int getMrrRadarEmissStatus() throws Exception {
        return this.mService.getScuMrrRadarEmissStatus();
    }

    public int[] getAllSrrRadarEmissStatus() throws Exception {
        return this.mService.getScuAllSrrRadarEmissStatus();
    }

    public void SetFcwAebSensitivitySwitchStatus(int level) throws Exception {
        this.mService.SetFcwAebSensitivitySwitchStatus(level);
    }

    public int getFcwAebSensitivitySwitchStatus() throws Exception {
        return this.mService.getFcwAebSensitivitySwitchStatus();
    }
}
