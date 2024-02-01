package android.car.hardware.xpu;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.hardware.CarEcuManager;
import android.car.hardware.XpVehicle.IXpVehicle;
import android.car.hardware.xpu.XpuFNgpRouteMessage;
import android.car.hardware.xpu.XpuProtoMessage;
import android.car.hardware.xpu.XpuXNgpNaviInfo;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import com.android.carsdk.protobuf.InvalidProtocolBufferException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
@SystemApi
/* loaded from: classes.dex */
public class CarXpuManager extends CarEcuManager {
    public static final int ADAS_TOP_SPEED_LIMITED = 0;
    public static final int ADAS_TOP_SPEED_UNLIMITED = 1;
    public static final int AS_LOCK_SCENARIO_CAM_CALIB_REQUEST = 1;
    public static final int AS_LOCK_SCENARIO_LIDAR_CALIB_REQUEST = 2;
    public static final int AS_LOCK_SCENARIO_MRR_CALIB_REQUEST = 3;
    public static final int AS_LOCK_SCENARIO_NO_REQUEST = 0;
    public static final int AS_TAR_LVL_HL1 = 2;
    public static final int AS_TAR_LVL_HL2 = 1;
    public static final int AS_TAR_LVL_LL1 = 4;
    public static final int AS_TAR_LVL_LL2 = 5;
    public static final int AS_TAR_LVL_LL3 = 6;
    public static final int AS_TAR_LVL_NL = 3;
    public static final int AS_TAR_LVL_NO_REQUEST = 0;
    private static final boolean DBG = false;
    public static final int FILE_TRANSFER_FAILURE = 1;
    public static final int FILE_TRANSFER_FINISHED = 0;
    public static final int ID_CDU_BRIGHTNESS_ST = 557856778;
    public static final int ID_ICM_XPU_SR_HEART_BEAT = 554738280;
    public static final int ID_SCU_RAEB_SW = 557852398;
    public static final int ID_SCU_XPU_CMD = 557852397;
    public static final int ID_XMART_PIGEON_MODE = 557856822;
    public static final int ID_XPU_ACCOUNT_INFO = 554711145;
    public static final int ID_XPU_ADAS_TOP_SPEED = 557856833;
    public static final int ID_XPU_AP_REMOTE_SW = 557856788;
    public static final int ID_XPU_AP_TIPS = 557856792;
    public static final int ID_XPU_AS_LOCK_SCENARIO = 557856818;
    public static final int ID_XPU_AS_TAR_LVL_MAX_REQ = 557856825;
    public static final int ID_XPU_AS_TAR_LVL_MIN_REQ = 557856824;
    public static final int ID_XPU_CAMERA_DATA = 561002514;
    public static final int ID_XPU_CDU_BRIGHTNESS_ST = 557856777;
    public static final int ID_XPU_CNGP_CITY_MAP_CTRL = 561002569;
    public static final int ID_XPU_CNGP_CITY_MAP_FIN = 561002572;
    public static final int ID_XPU_CNGP_SW = 557856803;
    public static final int ID_XPU_CONNECTED = 557856773;
    public static final int ID_XPU_COUNTRY_CODE_INFO = 554711077;
    public static final int ID_XPU_DRIVING_STATE_REMIND = 557856858;
    public static final int ID_XPU_EFF_SPD_LMTTYPE = 557856831;
    public static final int ID_XPU_ELEEYE_RANGE_SPD_LMT = 557856823;
    public static final int ID_XPU_ELEEYE_SPD_DIS = 557856826;
    public static final int ID_XPU_FNGP_EMULATOR_NAVI_INFO = 561002600;
    public static final int ID_XPU_FNGP_ROUTE_CMD = 554711140;
    public static final int ID_XPU_FNGP_ROUTE_DETAIL = 561002598;
    public static final int ID_XPU_FNGP_ROUTE_LIST = 561002597;
    public static final int ID_XPU_FNGP_ROUTE_PUSH_SW = 557856867;
    public static final int ID_XPU_FNGP_ROUTE_WAY_POINTS = 561002599;
    public static final int ID_XPU_GEO_FENCING_CONFIG = 554711076;
    public static final int ID_XPU_HD_MAP_DATA = 561002511;
    public static final int ID_XPU_HD_MAP_PERIOD_DATA = 561002515;
    public static final int ID_XPU_HEADPOSE_DAT = 557922346;
    public static final int ID_XPU_HEADPOSE_FACE_AREA = 557856813;
    public static final int ID_XPU_HMI_DOP_REMIND = 557856809;
    public static final int ID_XPU_HMI_ODD_REMIND = 557856856;
    public static final int ID_XPU_HMI_REMIND = 557856855;
    public static final int ID_XPU_HMI_VOICE = 557856857;
    public static final int ID_XPU_ISLC_DRIVER_SET = 557856816;
    public static final int ID_XPU_LCC_L_DETOUR_SW = 557856842;
    public static final int ID_XPU_LCC_L_STRAIGHT_SW = 557856845;
    public static final int ID_XPU_LIGHTCHANGE = 557856776;
    public static final int ID_XPU_LONG_LAT_PERIOD_DATA = 561002562;
    public static final int ID_XPU_LSS_SEN_SW = 557856835;
    @Deprecated
    public static final int ID_XPU_META_COUNTRYCODE = 557856827;
    public static final int ID_XPU_META_SPD_UNITS = 557856832;
    public static final int ID_XPU_MODE_INDX = 557856814;
    public static final int ID_XPU_NAVI_CONFIRM_PROTO = 561002576;
    public static final int ID_XPU_NAVI_REM_DIST = 557856846;
    public static final int ID_XPU_NAVI_ROUTING_INFO = 561002575;
    public static final int ID_XPU_NAVI_TYPE = 557856795;
    public static final int ID_XPU_NEDC_STATUS = 557856775;
    public static final int ID_XPU_NGP_CUSTOM_ETC_SW = 557856864;
    public static final int ID_XPU_NGP_CUSTOM_SPD_COUNT1 = 557856852;
    public static final int ID_XPU_NGP_CUSTOM_SPD_COUNT2 = 557856853;
    public static final int ID_XPU_NGP_CUSTOM_SPD_SW = 557856851;
    public static final int ID_XPU_NGP_MODE_INDX = 557856815;
    public static final int ID_XPU_NGP_MODE_SWITCH_REMIND = 557856863;
    public static final int ID_XPU_NGP_OPTIMAL_LANE_SW = 557856812;
    public static final int ID_XPU_NGP_SWITCH_TRANSITION = 557856808;
    public static final int ID_XPU_NGP_TRAJECTORY_DATA = 561002512;
    public static final int ID_XPU_NGP_TRIGGER_EVENT_DATA = 561002513;
    public static final int ID_XPU_NGP_ULC_SW = 557856811;
    public static final int ID_XPU_NRA_BTN = 557856801;
    public static final int ID_XPU_NRA_CTRL = 557856802;
    public static final int ID_XPU_PHONE_REMOTE_AP_EVENT = 554711101;
    public static final int ID_XPU_PHONE_REMOTE_AP_HEART_BEAT = 554711100;
    public static final int ID_XPU_PHONE_REMOTE_AP_INFO = 561002558;
    public static final int ID_XPU_RADAR_EMISSION_SW = 557856838;
    public static final int ID_XPU_RAEB_ACTIVE_ST = 557856796;
    public static final int ID_XPU_RD_PK_HMI_MODE = 557856865;
    public static final int ID_XPU_SCP_CHRG_PORT_CMD = 557856821;
    public static final int ID_XPU_SCP_GEO_INFO = 554711112;
    public static final int ID_XPU_SCP_GEO_INFO_REQUEST = 557856839;
    public static final int ID_XPU_SCP_ST = 557856819;
    public static final int ID_XPU_SCP_SW = 557856789;
    public static final int ID_XPU_SCP_TIPS = 557856790;
    public static final int ID_XPU_SCP_TONE = 557856791;
    public static final int ID_XPU_SCP_TTS = 557856817;
    public static final int ID_XPU_SD_PERIOD_DATA = 561029735;
    public static final int ID_XPU_SLA_WARNING_ST = 557856820;
    public static final int ID_XPU_SLIF_SOUND_SW = 557856806;
    public static final int ID_XPU_SLWF_VOICE_SW = 557856807;
    public static final int ID_XPU_SPEED_LIMIT_DISP = 557856859;
    public static final int ID_XPU_SR_HEART_BEAT = 554711121;
    public static final int ID_XPU_SR_PK_EVENT_DATA = 561002508;
    public static final int ID_XPU_SR_PK_PERIOD_DATA = 561002507;
    public static final int ID_XPU_SR_RD_EVENT_DATA = 561002510;
    public static final int ID_XPU_SR_RD_PERIOD_DATA = 561002509;
    public static final int ID_XPU_TRAFFIC_LIGHTS_COLOR = 557856861;
    public static final int ID_XPU_TRAFFIC_LIGHTS_COUNTER = 557856862;
    public static final int ID_XPU_TRANSFER_CAR_INFO = 561002528;
    public static final int ID_XPU_TRANSFER_MAP_DATA = 561002527;
    public static final int ID_XPU_TRANSFER_VPA_AB_INFO = 554711070;
    public static final int ID_XPU_TRANSFER_VPA_CMD = 554711069;
    public static final int ID_XPU_TURN_LAMP_ST = 557856860;
    public static final int ID_XPU_UPDATE_PROGRESS = 557856772;
    public static final int ID_XPU_UPDATE_REQ = 554711042;
    public static final int ID_XPU_UPDATE_RESULT = 557856774;
    public static final int ID_XPU_UPDATE_TRANS = 557856771;
    public static final int ID_XPU_VEH_LOCATION_PROTO = 561002571;
    public static final int ID_XPU_XMINER_AVAILABLE_ST = 557856854;
    public static final int ID_XPU_XNGP_NAVI_INFO = 561002594;
    public static final int ID_XPU_ZG_EVENT_MSG = 561002564;
    public static final int ID_XPU_ZG_PERIOD_MSG = 561002565;
    public static final int LSS_SEN_ST_HIGH = 2;
    public static final int LSS_SEN_ST_LOW = 1;
    public static final int LSS_SEN_ST_MEDIUM = 0;
    public static final int META_SPD_UNITS_KILOMETER_PER_HOUR = 0;
    public static final int META_SPD_UNITS_METER_PER_HOUR = 1;
    public static final int SCU_RAEB_SW_BLOCK = 4;
    public static final int SCU_RAEB_SW_INITIATION = 5;
    public static final int SCU_RAEB_SW_OFF = 0;
    public static final int SCU_RAEB_SW_OFF_BY_TRAILER_MODE = 6;
    public static final int SCU_RAEB_SW_ON = 1;
    public static final int SCU_RAEB_SW_PERMANENT_FAILURE = 3;
    public static final int SCU_RAEB_SW_TEMPORARY_FAILURE = 2;
    public static final int SLA_WARNING_STATUS_ACOUSTIC_WARNING = 2;
    public static final int SLA_WARNING_STATUS_FLASH_WARNING = 1;
    public static final int SLA_WARNING_STATUS_NO_WARNING = 0;
    private static final String TAG = "CarXpuManager";
    public static final int XMART_PIGEON_MODE_ACTIVE = 1;
    public static final int XMART_PIGEON_MODE_OFF = 0;
    public static final int XPU_AP_REMOTE_SW_OFF = 0;
    public static final int XPU_AP_REMOTE_SW_ON = 1;
    public static final int XPU_CITY_TO_HIGHWAY = 3;
    public static final int XPU_CNGP_MODE = 8;
    public static final int XPU_CNGP_SW_OFF_BY_RADAR_FUNCTION_OFF = 3;
    public static final int XPU_CNGP_SW_OFF_BY_TRAILER_FUNCTION_ON = 2;
    public static final int XPU_CONNECTED = 1;
    public static final int XPU_DRIVING_STATE_REMIND_CNGP_ACTIVE = 6;
    public static final int XPU_DRIVING_STATE_REMIND_CNGP_AVAILABLE = 5;
    public static final int XPU_DRIVING_STATE_REMIND_CNGP_PAUSE = 9;
    public static final int XPU_DRIVING_STATE_REMIND_DSP_PAUSE = 7;
    public static final int XPU_DRIVING_STATE_REMIND_FNGP_ACTIVE = 11;
    public static final int XPU_DRIVING_STATE_REMIND_FNGP_AVAILABLE = 10;
    public static final int XPU_DRIVING_STATE_REMIND_FNGP_PAUSE = 12;
    public static final int XPU_DRIVING_STATE_REMIND_HNGP_ACTIVE = 4;
    public static final int XPU_DRIVING_STATE_REMIND_HNGP_AVAILABLE = 3;
    public static final int XPU_DRIVING_STATE_REMIND_HNGP_PAUSE = 8;
    public static final int XPU_DRIVING_STATE_REMIND_LCC_ACTIVE = 2;
    public static final int XPU_DRIVING_STATE_REMIND_LCC_AVAILABLE = 1;
    public static final int XPU_DRIVING_STATE_REMIND_MANUAL = 0;
    public static final int XPU_HEADPOSE_FACE_AREA_BELOW_ICM = 7;
    public static final int XPU_HEADPOSE_FACE_AREA_CDU = 6;
    public static final int XPU_HEADPOSE_FACE_AREA_DRIVER_SIDE_MIRROR = 3;
    public static final int XPU_HEADPOSE_FACE_AREA_ICM = 5;
    public static final int XPU_HEADPOSE_FACE_AREA_LEFT_WINDSHIELD_CENTER = 0;
    public static final int XPU_HEADPOSE_FACE_AREA_NO_EYE_TRACKING = 10;
    public static final int XPU_HEADPOSE_FACE_AREA_OTHER = 9;
    public static final int XPU_HEADPOSE_FACE_AREA_PASSENGER_SIDE_MIRROR = 4;
    public static final int XPU_HEADPOSE_FACE_AREA_REAR = 8;
    public static final int XPU_HEADPOSE_FACE_AREA_REAR_VIEW_MIRROR = 2;
    public static final int XPU_HEADPOSE_FACE_AREA_RIGHT_WINDSHIELD = 1;
    public static final int XPU_HIGHWAY_TO_CITY = 4;
    public static final int XPU_ISLC_DRIVER_SET_AUTOMATIC = 3;
    public static final int XPU_ISLC_DRIVER_SET_ERROR = 0;
    public static final int XPU_ISLC_DRIVER_SET_MANUAL = 2;
    public static final int XPU_ISLC_DRIVER_SET_OFF = 1;
    public static final int XPU_L2_TO_L4_MODE = 7;
    public static final int XPU_L4_TO_L2_MODE = 9;
    public static final int XPU_MODE_INDX_ACC = 4;
    public static final int XPU_MODE_INDX_ALC = 8;
    public static final int XPU_MODE_INDX_ICA = 5;
    public static final int XPU_MODE_INDX_MANUAL = 16;
    public static final int XPU_MODE_INDX_TJA_LANE_CENTERING = 6;
    public static final int XPU_MODE_INDX_TJA_TARGET_PURSUIT = 7;
    public static final int XPU_NAVI_TYPE_CRUISE_ST = 0;
    public static final int XPU_NAVI_TYPE_DEFAULT_ST = 3;
    public static final int XPU_NAVI_TYPE_EXPLORE_ST = 2;
    public static final int XPU_NAVI_TYPE_NAVI_ST = 1;
    public static final int XPU_NGP_CUSTOM_SPD_COUNT1_FIVE_KPH = 4;
    public static final int XPU_NGP_CUSTOM_SPD_COUNT1_FIVE_PERCENT = 4;
    public static final int XPU_NGP_CUSTOM_SPD_COUNT1_NEGATIVE_FIVE_KPH = 2;
    public static final int XPU_NGP_CUSTOM_SPD_COUNT1_NEGATIVE_FIVE_PERCENT = 2;
    public static final int XPU_NGP_CUSTOM_SPD_COUNT1_NEGATIVE_TEN_KPH = 1;
    public static final int XPU_NGP_CUSTOM_SPD_COUNT1_NEGATIVE_TEN_PERCENT = 1;
    public static final int XPU_NGP_CUSTOM_SPD_COUNT1_TEN_KPH = 5;
    public static final int XPU_NGP_CUSTOM_SPD_COUNT1_TEN_PERCENT = 5;
    public static final int XPU_NGP_CUSTOM_SPD_COUNT1_ZERO_KPH = 3;
    public static final int XPU_NGP_CUSTOM_SPD_COUNT1_ZERO_PERCENT = 3;
    public static final int XPU_NGP_CUSTOM_SPD_SW_FIXED_VALUE = 1;
    public static final int XPU_NGP_CUSTOM_SPD_SW_PERCENT = 2;
    public static final int XPU_NGP_MODE_INDX_ACC_MODE = 2;
    public static final int XPU_NGP_MODE_INDX_DRIVER_MODE = 1;
    public static final int XPU_NGP_MODE_INDX_LCC_MODE = 3;
    public static final int XPU_NGP_MODE_INDX_LCC_MODE_2 = 4;
    public static final int XPU_NGP_MODE_INDX_NGP_MODE = 5;
    public static final int XPU_NGP_MODE_INDX_NGP_MODE_2 = 6;
    public static final int XPU_NGP_MODE_INDX_NO_COMMENT = 0;
    public static final int XPU_NGP_OPTIMAL_LANE_FIRST_LEFT = 0;
    public static final int XPU_NGP_OPTIMAL_LANE_SECOND_LEFT = 1;
    @Deprecated
    public static final int XPU_NGP_SWITCH_CNGP_HWNGP = 9;
    @Deprecated
    public static final int XPU_NGP_SWITCH_HWNGP_CNGP = 8;
    @Deprecated
    public static final int XPU_NGP_SWITCH_L2 = 1;
    @Deprecated
    public static final int XPU_NGP_SWITCH_L2_L3 = 4;
    @Deprecated
    public static final int XPU_NGP_SWITCH_L2_L4 = 7;
    @Deprecated
    public static final int XPU_NGP_SWITCH_L3 = 3;
    @Deprecated
    public static final int XPU_NGP_SWITCH_L3_L2 = 2;
    @Deprecated
    public static final int XPU_NGP_SWITCH_L4 = 6;
    @Deprecated
    public static final int XPU_NGP_SWITCH_L4_L2 = 5;
    public static final int XPU_NGP_SWITCH_NONE = 0;
    public static final int XPU_NGP_ULC_SW_CONSERVATIVE = 1;
    public static final int XPU_NGP_ULC_SW_NORMAL = 0;
    public static final int XPU_NOT_CONNECTED = 0;
    public static final int XPU_NRA_CTRL_STATUS_NOT_AVAILIABLE = 2;
    public static final int XPU_NRA_CTRL_STATUS_OFF = 0;
    public static final int XPU_NRA_CTRL_STATUS_ON = 1;
    public static final int XPU_RAEB_ACTIVE_ST_NOT_ACTIVE = 0;
    public static final int XPU_RAEB_ACTIVE_ST_RAEB_TO_CAR = 1;
    public static final int XPU_RAEB_ACTIVE_ST_RAEB_TO_PED = 2;
    public static final int XPU_RD_PK_HMI_MODE_DRIVING = 1;
    public static final int XPU_RD_PK_HMI_MODE_INIT = 0;
    public static final int XPU_RD_PK_HMI_MODE_PARKING = 2;
    public static final int XPU_SCP_SW_ACTIVE = 3;
    public static final int XPU_SCP_SW_OFF = 0;
    public static final int XPU_SCP_SW_PASSIVE = 1;
    public static final int XPU_SCP_SW_PERMANENT_FAILURE = 5;
    public static final int XPU_SCP_SW_STANDBY = 2;
    public static final int XPU_SCP_SW_TEMPORARY_FAILURE = 4;
    public static final int XPU_SCP_TIPS_1 = 1;
    public static final int XPU_SCP_TIPS_2 = 2;
    public static final int XPU_SCP_TIPS_3 = 3;
    public static final int XPU_SCP_TIPS_NONE = 0;
    public static final int XPU_SCP_TONE_1 = 1;
    public static final int XPU_SCP_TONE_2 = 2;
    public static final int XPU_SCP_TONE_3 = 3;
    public static final int XPU_SCP_TONE_NONE = 0;
    public static final int XPU_SCP_TTS_1 = 1;
    public static final int XPU_SCP_TTS_2 = 2;
    public static final int XPU_SCP_TTS_3 = 3;
    public static final int XPU_SCP_TTS_NONE = 0;
    public static final int XPU_SLWF_SLIF_NOT_AVAILABLE_FOR_DRIVER = 2;
    public static final int XPU_SLWF_SLIF_OFF = 0;
    public static final int XPU_SLWF_SLIF_ON = 1;
    public static final int XPU_STATUS_FAILURE = 2;
    public static final int XPU_STATUS_NEDC_OFF = 0;
    public static final int XPU_STATUS_NEDC_ON = 1;
    public static final int XPU_STATUS_NO_OPERATION = 2;
    public static final int XPU_STATUS_OFF = 0;
    public static final int XPU_STATUS_ON = 1;
    public static final int XPU_STATUS_START_UP_FAILURE = 5;
    public static final int XPU_STATUS_START_UP_IN_PROGRESS = 3;
    public static final int XPU_STATUS_TURN_OFF_IN_PROGRESS = 4;
    public static final int XPU_SWITCH_OFF = 0;
    public static final int XPU_SWITCH_ON = 1;
    public static final int XPU_TRANS_TO_CITY = 1;
    public static final int XPU_TRANS_TO_HIGHWAY = 2;
    public static final int XPU_TURN_LAMP_ST_INVALID = 0;
    public static final int XPU_TURN_LAMP_ST_LEFT = 1;
    public static final int XPU_TURN_LAMP_ST_OFF = 4;
    public static final int XPU_TURN_LAMP_ST_RIGHT = 2;
    public static final int XPU_UPDATE_SUCCESS = 0;
    private static final ArraySet<Integer> mXpuSharedMemoryPropertyIds = new ArraySet<>(Arrays.asList(561002507, 561002508, 561002509, 561002510, 561002511, 561002512, 561002514, 561002515, 561002527, 561002575, 561029735, 561002594, 561002597, 561002598, 561002599, 561002600));
    private final IXpVehicle mService;
    private final ArraySet<Integer> mXpuPropertyIds;

    /* loaded from: classes.dex */
    public interface CarXpuEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface XpuISLCDriverSet {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface XpuNgpOptimalLane {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface XpuNgpULCSw {
    }

    public CarXpuManager(IBinder service, IBinder vehicleService, IBinder sharedMemoryService, Context context, Handler handler) {
        super(service, sharedMemoryService, context, handler, false, TAG);
        this.mXpuPropertyIds = new ArraySet<>(Arrays.asList(554711042, 557856772, 557856774, 557856773, 557856775, 557856776, 557856777, 557856778, 561002513, 557856788, 557856789, 557852398, 557852397, 557856795, 557856796, 554711069, 554711070, 561002528, 557856801, 557856802, 557856803, 554711076, 554711077, 557856807, 557856806, 557856808, 557922346, 557856809, 557856811, 557856812, 557856816, 557856815, 557856814, 557856790, 557856817, 557856791, 557856792, 557856818, 557856819, 557856820, 557856821, 557856822, 557856823, 557856824, 557856825, 557856826, 557856827, 554711100, 554711101, 561002558, 557856832, 557856831, 561002562, 557856833, 557856835, 561002564, 561002565, 557856838, 554711112, 557856839, 557856813, 557856842, 561002569, 561029735, 561002572, 561002571, 557856845, 557856846, 561002575, 561002576, 554711121, 554738280, 557856851, 557856852, 557856853, 557856855, 557856856, 557856857, 557856858, 557856859, 557856860, 557856861, 557856862, 557856863, 557856865, 557856864, 561002594, 557856867, 554711140, 561002597, 561002598, 561002599, 561002600, 554711145));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public static String getServiceName() {
        return Car.XP_XPU_SERVICE;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mXpuPropertyIds;
    }

    @Override // android.car.hardware.CarEcuManager
    protected Set<Integer> getSharedMemoryIds() {
        return mXpuSharedMemoryPropertyIds;
    }

    public void sendUpdateRequest(String req) throws Exception {
        this.mService.sendXpuUpdateRequest(req);
    }

    public String getUpdateResponse() throws Exception {
        return this.mService.getXpuUpdateResponse();
    }

    public void setUpdateFileTransferStatus(int status) throws Exception {
        this.mService.setXpuUpdateFileTransferStatus(status);
    }

    public int getUpdateResult() throws Exception {
        return this.mService.getXpuUpdateResult();
    }

    public int getUpdateProgress() throws Exception {
        return this.mService.getXpuUpdateProgress();
    }

    public int getXpuConnectionStatus() throws Exception {
        return this.mService.getXpuConnectionStatus();
    }

    public void setNedcSwitch(int onOff) throws Exception {
        this.mService.setXpuNedcSwitch(onOff);
    }

    public int getNedcSwitchStatus() throws Exception {
        return this.mService.getXpuNedcSwitchStatus();
    }

    public void enableXpuLightChange() throws Exception {
        this.mService.setXpuLightChange(1);
    }

    public void setCduBrightness(int brightness) throws Exception {
        this.mService.setXpuCduBrightness(brightness);
    }

    public void setScpSwitchStatus(int sw) throws Exception {
        this.mService.setXpuScpSwitchStatus(sw);
    }

    public int getScpSwitchStatus() throws Exception {
        return this.mService.getXpuScpSwitchStatus();
    }

    public void setRaebSwitchStatus(int sw) throws Exception {
        this.mService.setXpuRaebSwitchStatus(sw);
    }

    public int getRaebSwitchStatus() throws Exception {
        return this.mService.getXpuRaebSwitchStatus();
    }

    public int sendSrPkCdu2XpuPeriodData(byte[] data) {
        try {
            sendDataWithSharedMemory(561002507, data);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int sendSrPkCdu2XpuEventData(byte[] data) {
        try {
            sendDataWithSharedMemory(561002508, data);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int sendSrRdCdu2XpuPeriodData(byte[] data) {
        try {
            sendDataWithSharedMemory(561002509, data);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int sendSrRdCdu2XpuEventData(byte[] data) {
        try {
            sendDataWithSharedMemory(561002510, data);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int sendSrRdCdu2XpuNgpTrajectoryV2Data(byte[] data) {
        try {
            sendDataWithSharedMemory(561002512, data);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int sendHdMapData(byte[] data) {
        try {
            sendDataWithSharedMemory(561002511, data);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int getXpuBrightness() throws Exception {
        return this.mService.getXpuBrightness();
    }

    public int getApRemoteSw() throws Exception {
        return this.mService.getXpuApRemoteSw();
    }

    public void setApRemoteSw(int sw) throws Exception {
        this.mService.setXpuApRemoteSw(sw);
    }

    public void setNaviTypeStatus(int status) throws Exception {
        this.mService.setXpuNaviTypeStatus(status);
    }

    public int getRaebActiveStatus() throws Exception {
        return this.mService.getXpuRaebActiveStatus();
    }

    public void setNraSwitchStatus(int sw) throws Exception {
        this.mService.setXpuNraSwitchStatus(sw);
    }

    public int getNraSwitchStatus() throws Exception {
        return this.mService.getXpuNraSwitchStatus();
    }

    public int getNraControlStatus() throws Exception {
        return this.mService.getXpuNraControlStatus();
    }

    public void sendTransferVpaCmd(String cmd) throws Exception {
        this.mService.sendXpuTransferVpaCmd(cmd);
    }

    public void sendTransferVpaAbInfo(String info) throws Exception {
        this.mService.sendXpuTransferVpaAbInfo(info);
    }

    public void setCityNgpSwitchStatus(int sw) throws Exception {
        this.mService.setXpuCityNgpSwitchStatus(sw);
    }

    public int getCityNgpSwitchStatus() throws Exception {
        return this.mService.getXpuCityNgpSwitchStatus();
    }

    public int sendTransferMapData(byte[] data) {
        try {
            sendDataWithSharedMemory(561002527, data);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public void sendGeoFencingConfig(String config) throws Exception {
        this.mService.sendXpuGeoFencingConfig(config);
    }

    public void sendCountryCodeInfo(String info) throws Exception {
        this.mService.sendXpuCountryCodeInfo(info);
    }

    public void setSlifSoundStatus(int status) throws Exception {
        this.mService.setXpuSlifSoundStatus(status);
    }

    public int getSlifSoundStatus() throws Exception {
        return this.mService.getXpuSlifSoundStatus();
    }

    public void setSlwfVoiceStatus(int status) throws Exception {
        this.mService.setXpuSlwfVoiceStatus(status);
    }

    public int getSlwfVoiceStatus() throws Exception {
        return this.mService.getXpuSlwfVoiceStatus();
    }

    public int getNgpSwitchTransitionStatus() throws Exception {
        return this.mService.getXpuNgpSwitchTransitionStatus();
    }

    public int[] getHeadPoseData() throws Exception {
        return this.mService.getXpuHeadPoseData();
    }

    public int getHmiDopRemind() throws Exception {
        return this.mService.getXpuHmiDopRemind();
    }

    public void setNgpULCSwMode(int mode) throws Exception {
        this.mService.setXpuNgpULCSwMode(mode);
    }

    public int getNgpULCSwMode() throws Exception {
        return this.mService.getXpuNgpULCSwMode();
    }

    public void setNgpOptimalLaneSw(int lane) throws Exception {
        this.mService.setXpuNgpOptimalLaneSw(lane);
    }

    public int getNgpOptimalLaneSw() throws Exception {
        return this.mService.getXpuNgpOptimalLaneSw();
    }

    public void setISLCDriverSet(int mode) throws Exception {
        this.mService.setXpuISLCDriverSet(mode);
    }

    public int getISLCDriverSet() throws Exception {
        return this.mService.getXpuISLCDriverSet();
    }

    public int getNgpModeIndexMode() throws Exception {
        return this.mService.getXpuNgpModeIndexMode();
    }

    public int getModeIndexDefine() throws Exception {
        return this.mService.getXpuModeIndexDefine();
    }

    public int getIntelligentChargePortTipsType() throws Exception {
        return this.mService.getXpuIntelligentChargePortTipsType();
    }

    public int getIntelligentChargePortTtsBroadcastType() throws Exception {
        return this.mService.getXpuIntelligentChargePortTtsBroadcastType();
    }

    public int getIntelligentChargePortSystemToneType() throws Exception {
        return this.mService.getXpuIntelligentChargePortSystemToneType();
    }

    public int getAutoParkingTipsType() throws Exception {
        return this.mService.getXpuAutoParkingTipsType();
    }

    public int getAsLockScenario() throws Exception {
        return this.mService.getXpuAsLockScenario();
    }

    public int getIntelligentChargePortSystemStatus() throws Exception {
        return this.mService.getXpuIntelligentChargePortSystemStatus();
    }

    public int getSlaSpeedWarningStatus() throws Exception {
        return this.mService.getXpuSlaSpeedWarningStatus();
    }

    public int getScpChargePortCommandStatus() throws Exception {
        return this.mService.getXpuScpChargePortCommandStatus();
    }

    public int getXmartPigeonMode() throws Exception {
        return this.mService.getXpuXmartPigeonMode();
    }

    public void setElectricEyeSpeedLimit(int speedlimit) throws Exception {
        this.mService.setXpuElectricEyeSpeedLimit(speedlimit);
    }

    public void setElectricEyeSpeedDistance(int distance) throws Exception {
        this.mService.setXpuElectricEyeSpeedDistance(distance);
    }

    @Deprecated
    public void setMetaCountryCode(int code) throws Exception {
        this.mService.setXpuMetaCountryCode(code);
    }

    public void setMetaSpeedUnits(int unit) throws Exception {
        this.mService.setXpuMetaSpeedUnits(unit);
    }

    public void setEffectiveSpeedLimitType(int type) throws Exception {
        this.mService.setXpuEffectiveSpeedLimitType(type);
    }

    public int getAsTargetMinimumHeightRequest() throws Exception {
        return this.mService.getXpuAsTargetMinimumHeightRequest();
    }

    public int getAsTargetMaximumHeightRequest() throws Exception {
        return this.mService.getXpuAsTargetMaximumHeightRequest();
    }

    public void setPhoneRemoteAPHeartBeat(String beat) throws Exception {
        this.mService.sendPigeonAndXPURemoteAPHeartBeat(beat);
    }

    public void setPhoneRemoteAPEvent(String event) throws Exception {
        this.mService.sendPhoneRemoteAPEvent(event);
    }

    public byte[] getPhoneRemoteAPInfo() throws Exception {
        return this.mService.getPhoneRemoteAPInformation();
    }

    public byte[] getLongLatPeriodData() throws Exception {
        return this.mService.getXpuLongLatPeriodData();
    }

    public void setAdasTopSpeedLimitedValue(int sw) throws Exception {
        this.mService.setXpuAdasTopSpeedLimitedValue(sw);
    }

    public void setLssSensitivitySwitchStatus(int level) throws Exception {
        this.mService.setXpuLssSensitivitySwitchStatus(level);
    }

    public int getLssSensitivitySwitchStatus() throws Exception {
        return this.mService.getXpuLssSensitivitySwitchStatus();
    }

    public void sendZgEventMessage(byte[] message) throws Exception {
        this.mService.sendXpuZgEventMessage(message);
    }

    public void sendZgPeriodMessage(byte[] message) throws Exception {
        this.mService.sendXpuZgPeriodMessage(message);
    }

    public void setRadarEmissionSwitchStatus(int sw) throws Exception {
        this.mService.setXpuRadarEmissionSwitchStatus(sw);
    }

    public void sendScpGeoInfo(String info) throws Exception {
        this.mService.sendXpuScpGeoInfo(info);
    }

    public int getDriverHeadFaceArea() throws Exception {
        return this.mService.getXpuDriverHeadFaceArea();
    }

    public void setLLCCDetourSw(int sw) throws Exception {
        this.mService.setXpuLLCCDetourSw(sw);
    }

    public int getLLCCDetourSw() throws Exception {
        return this.mService.getXpuLLCCDetourSw();
    }

    public void CNGPCityMapCtrlRequest(int city_code, int req_type) throws Exception {
        XpuProtoMessage.CNGPCityMapRequest.Builder builders = XpuProtoMessage.CNGPCityMapRequest.newBuilder();
        XpuProtoMessage.CNGPCityMapRequest.CityRequest.Builder city = XpuProtoMessage.CNGPCityMapRequest.CityRequest.newBuilder();
        city.setCityCode(city_code);
        city.setReqType(req_type);
        builders.setCity(city);
        this.mService.sendCNGPCityMapCtrlReq(builders.build().toByteArray());
    }

    public static XpuProtoMessage.CNGPCityMapResponse CNGPCityMapCtrlResponse(byte[] callback_data) throws Exception {
        try {
            XpuProtoMessage.CNGPCityMapResponse resp = XpuProtoMessage.CNGPCityMapResponse.parseFrom(callback_data);
            return resp;
        } catch (InvalidProtocolBufferException e) {
            Log.e(TAG, "CNGPCityMapResponse parse error with exception" + e.toString());
            return null;
        }
    }

    public static XpuProtoMessage.CNGPCityMapFinNotify CNGPCityMapFinishNotify(byte[] callback_data) throws Exception {
        try {
            XpuProtoMessage.CNGPCityMapFinNotify notify = XpuProtoMessage.CNGPCityMapFinNotify.parseFrom(callback_data);
            return notify;
        } catch (InvalidProtocolBufferException e) {
            Log.e(TAG, "CNGPCityMapFinNotify parse error with exception" + e.toString());
            return null;
        }
    }

    public XpuProtoMessage.VehLocationInfoResp getVehLocationProto() throws Exception {
        return getVehLocationProto(this.mService.getXpuVehLocationProto());
    }

    public static XpuProtoMessage.VehLocationInfoResp getVehLocationProto(byte[] data) throws Exception {
        try {
            XpuProtoMessage.VehLocationInfoResp resp = XpuProtoMessage.VehLocationInfoResp.parseFrom(data);
            return resp;
        } catch (InvalidProtocolBufferException e) {
            Log.e(TAG, "getVehLocationProto parse error with exception" + e);
            return null;
        }
    }

    public void setLccLStraightSw(int sw) throws Exception {
        this.mService.setXpuLccLStraightSw(sw);
    }

    public int getLccLStraightSw() throws Exception {
        return this.mService.getXpuLccLStraightSw();
    }

    public void setNaviRemainingDistance(int distance) throws Exception {
        this.mService.setXpuNaviRemainingDistance(distance);
    }

    public static XpuProtoMessage.NaviConfirm getNaviConfirmProto(byte[] data) throws Exception {
        try {
            XpuProtoMessage.NaviConfirm resp = XpuProtoMessage.NaviConfirm.parseFrom(data);
            return resp;
        } catch (InvalidProtocolBufferException e) {
            Log.e(TAG, "getNaviConfirmProto parse error with exception" + e);
            return null;
        }
    }

    public XpuProtoMessage.NaviConfirm getNaviConfirmProto() throws Exception {
        return getNaviConfirmProto(this.mService.getXpuNaviConfirmProto());
    }

    public void setNaviRoutingInfoRequest(XpuProtoMessage.NaviStatus status, long navigation_id, List<XpuProtoMessage.V2_Point> points) throws Exception {
        XpuProtoMessage.NaviRoutingInfo data = XpuProtoMessage.NaviRoutingInfo.newBuilder().setNavigationStatus(status).setNavigationId(navigation_id).addAllV2Points(points).build();
        try {
            sendDataWithSharedMemory(561002575, data.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNaviRoutingInfo(XpuProtoMessage.NaviRoutingInfoV2 info) throws Exception {
        try {
            sendDataWithSharedMemory(561002575, info.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendSRHeartBeatInfoRequest(String beat) throws Exception {
        this.mService.sendSRHeartBeatInfoRequest(beat);
    }

    public String getSRHeartBeatInfo() throws Exception {
        return this.mService.getSRHeartBeatInfo();
    }

    public void sendSRLagLogHeartBeatFeedBackRequest(String info) throws Exception {
        this.mService.sendSRLagLogHeartBeatFeedBackRequest(info);
    }

    public void setNgpCustomSpeedSwitchStatus(int sw) throws Exception {
        this.mService.setXpuNgpCustomSpeedSwitchStatus(sw);
    }

    public int getNgpCustomSpeedSwitchStatus() throws Exception {
        return this.mService.getXpuNgpCustomSpeedSwitchStatus();
    }

    public void setNgpCustomSpeedCountLever(int lever) throws Exception {
        this.mService.setXpuNgpCustomSpeedCountLever(lever);
    }

    public int getNgpCustomSpeedCountLever() throws Exception {
        return this.mService.getXpuNgpCustomSpeedCountLever();
    }

    public void setNgpCustomSpeedCountPercent(int percent) throws Exception {
        this.mService.setXpuNgpCustomSpeedCountPercent(percent);
    }

    public int getNgpCustomSpeedCountPercent() throws Exception {
        return this.mService.getXpuNgpCustomSpeedCountPercent();
    }

    public int setXpuXminerAvailableSt(int status) {
        try {
            return this.mService.setXpuXminerAvailableSt(status);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public int getHmiRemind() throws RemoteException {
        return this.mService.getHmiRemind();
    }

    public int getHmiOddRemind() throws RemoteException {
        return this.mService.getHmiOddRemind();
    }

    public int setHmiOddRemind(int remind) throws RemoteException {
        return this.mService.setHmiOddRemind(remind);
    }

    public int getHmiVoice() throws RemoteException {
        return this.mService.getHmiVoice();
    }

    public int setHmiVoice(int voice) throws RemoteException {
        return this.mService.setHmiVoice(voice);
    }

    public int getDrivingStateRemind() throws RemoteException {
        return this.mService.getDrivingStateRemind();
    }

    public int getSpeedLimitDisp() throws RemoteException {
        return this.mService.getSpeedLimitDisp();
    }

    public int getTurnLampSt() throws RemoteException {
        return this.mService.getTurnLampSt();
    }

    public int getTrafficLightsColor() throws RemoteException {
        return this.mService.getTrafficLightsColor();
    }

    public int getTrafficLightsCounter() throws RemoteException {
        return this.mService.getTrafficLightsCounter();
    }

    public int getNgpModeSwitchRemind() throws RemoteException {
        return this.mService.getNgpModeSwitchRemind();
    }

    public int getRdPkHmiMode() throws RemoteException {
        return this.mService.getRdPkHmiMode();
    }

    public int getNgpCustomEtcSw() throws RemoteException {
        return this.mService.getNgpCustomEtcSw();
    }

    public int setNgpCustomEtcSw(int sw) {
        try {
            return this.mService.setNgpCustomEtcSw(sw);
        } catch (RemoteException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "setNgpCustomEtcSw throw RemoteException | RuntimeException e " + e.getMessage());
            return 1;
        }
    }

    public int sendXNgpNaviInfo(XpuXNgpNaviInfo.SDNavigation data) throws RemoteException {
        try {
            sendDataWithSharedMemory(561002594, data.toByteArray());
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "sendXNgpNaviInfo error" + e.getMessage());
            return 1;
        }
    }

    public int setFNgpRoutePushSw(int sw) {
        try {
            return this.mService.setFNgpRoutePushSw(sw);
        } catch (RemoteException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "setFNgpRoutePushSw throw RemoteException | RuntimeException e " + e.getMessage());
            return 1;
        }
    }

    public int getFNgpRoutePushSw() {
        try {
            return this.mService.getFNgpRoutePush();
        } catch (RemoteException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "getFNgpRoutePush throw RemoteException | RuntimeException e " + e.getMessage());
            return 0;
        }
    }

    public int setFNgpRouteCmd(String cmdJson) {
        try {
            return this.mService.setFNgpRouteCmd(cmdJson);
        } catch (RemoteException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "setFNgpRouteCmd throw RemoteException | RuntimeException e " + e.getMessage());
            return 1;
        }
    }

    public static List<XpuFNgpRouteMessage.FNgpRouteDetail> getFNgpRouteList(byte[] callback_data) {
        try {
            return XpuFNgpRouteMessage.FNgpRouteDetailList.parseFrom(callback_data).getFngpRouteDefailListList();
        } catch (InvalidProtocolBufferException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "getFNgpRouteList throw InvalidProtocolBufferException | RuntimeException e " + e.getMessage());
            return new ArrayList();
        }
    }

    public static XpuFNgpRouteMessage.FNgpRouteDetail getFNgpRouteDetail(byte[] callback_data) {
        try {
            return XpuFNgpRouteMessage.FNgpRouteDetail.parseFrom(callback_data);
        } catch (InvalidProtocolBufferException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "getFNgpRouteDetail throw InvalidProtocolBufferException | RuntimeException e " + e.getMessage());
            return null;
        }
    }

    public static List<XpuFNgpRouteMessage.Point> getFNgpRouteWayPoints(byte[] callback_data) {
        try {
            return XpuFNgpRouteMessage.FNgpRouteWayPointsSpecified.parseFrom(callback_data).getV2PointListSpecifiedList();
        } catch (InvalidProtocolBufferException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "getFNgpRouteWayPoints throw InvalidProtocolBufferException | RuntimeException e " + e.getMessage());
            return new ArrayList();
        }
    }

    public static XpuFNgpRouteMessage.FNgpEmulatorNavigation getFNgpEmulatorNaviInfo(byte[] callback_data) {
        try {
            return XpuFNgpRouteMessage.FNgpEmulatorNavigation.parseFrom(callback_data);
        } catch (InvalidProtocolBufferException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "getFNgpEmulatorNaviInfo throw InvalidProtocolBufferException | RuntimeException e " + e.getMessage());
            return null;
        }
    }

    public int setAccountInfo(String infoJson) {
        try {
            return this.mService.setAccountInfo(infoJson);
        } catch (RemoteException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "setAccountUid throw RemoteException | RuntimeException e " + e.getMessage());
            return 1;
        }
    }
}
