package android.car.hardware.mcu;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.hardware.CarEcuManager;
import android.car.hardware.XpVehicle.IXpVehicle;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemProperties;
import android.util.ArraySet;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class CarMcuManager extends CarEcuManager {
    @SystemApi
    public static final int ACTION_POWER_OFF_COUNTDOWN_CANCLE = 1;
    @SystemApi
    public static final int ACTION_POWER_OFF_COUNTDOWN_END = 0;
    public static final int ACTION_START_WIFI_HOTSPOT = 1;
    public static final int ANDROID_OTA_STATUS_ANDROID_RESET = 1;
    public static final int BACKLIGHT_IC_DRIVER_ST_ERROR = 1;
    public static final int BACKLIGHT_IC_DRIVER_ST_NORMAL = 0;
    public static final int BLE_START_FAILED = 2;
    @SystemApi
    public static final int BOOT_REASON_USER_LOCAL_IGON = 4;
    @SystemApi
    public static final String CAR_CDU_A1 = "A1";
    @SystemApi
    public static final String CAR_CDU_A2 = "A2";
    @SystemApi
    public static final String CAR_CDU_A3 = "A3";
    @SystemApi
    public static final String CAR_CDU_Q1 = "Q1";
    @SystemApi
    public static final String CAR_CDU_Q2 = "Q2";
    @SystemApi
    public static final String CAR_CDU_Q3 = "Q3";
    @SystemApi
    public static final String CAR_CDU_Q5 = "Q5";
    @SystemApi
    public static final String CAR_CDU_Q6 = "Q6";
    @SystemApi
    public static final String CAR_STAGE_A = "A";
    @SystemApi
    public static final String CAR_STAGE_B = "B";
    @SystemApi
    public static final String CAR_STAGE_C = "C";
    @SystemApi
    public static final String CAR_STAGE_D = "D";
    @SystemApi
    public static final String CAR_STAGE_E = "E";
    @SystemApi
    public static final String CAR_STAGE_F = "F";
    @SystemApi
    public static final String CAR_STAGE_P = "P";
    @SystemApi
    public static final String CAR_STAGE_V = "V";
    @SystemApi
    public static final String CAR_STAGE_X = "X";
    @SystemApi
    public static final String CAR_TYPE_D10 = "D10";
    @SystemApi
    public static final String CAR_TYPE_D20 = "D20";
    @SystemApi
    public static final String CAR_TYPE_D20P = "D20P";
    @SystemApi
    public static final String CAR_TYPE_D20X = "D20X";
    @SystemApi
    public static final String CAR_TYPE_D21 = "D21";
    @SystemApi
    public static final String CAR_TYPE_D21A = "D21A";
    @SystemApi
    public static final String CAR_TYPE_D21B = "D21B";
    @SystemApi
    public static final String CAR_TYPE_D21V = "D21V";
    public static final String CAR_TYPE_D22 = "D22";
    public static final String CAR_TYPE_D22V = "D22V";
    @SystemApi
    public static final String CAR_TYPE_D25 = "D25";
    public static final String CAR_TYPE_D55 = "D55";
    public static final String CAR_TYPE_D55V = "D55V";
    @SystemApi
    public static final String CAR_TYPE_E28 = "E28";
    @SystemApi
    public static final String CAR_TYPE_E36 = "E36";
    public static final int CID_STATE_ERROR = 1;
    public static final int CID_STATE_OK = 0;
    public static final int CIU_EXIST = 1;
    @Deprecated
    public static final int CIU_EXSIT = 1;
    public static final int CIU_NOT_EXIST = 2;
    @Deprecated
    public static final int CIU_NOT_EXSIT = 2;
    private static final boolean DBG = false;
    @SystemApi
    public static final int ENTER_DV_TEST = 1;
    public static final int ENTER_GEOFENCE = 1;
    @SystemApi
    public static final int ENTER_REPAIR_MODE = 1;
    @SystemApi
    public static final int EXIT_DV_TEST = 0;
    public static final int EXIT_GEOFENCE = 0;
    @SystemApi
    public static final int EXIT_REPAIR_MODE = 0;
    public static final int HORN_STATE_NORMAL = 3;
    public static final int HORN_STATE_OPEN_CIRCUIT = 1;
    public static final int HORN_STATE_SHORT_CIRCUIT = 2;
    public static final int ID_ANDROID_CPU_TEMPERATURE = 559944801;
    public static final int ID_MCU_3S_REPORT_EV = 356516106;
    public static final int ID_MCU_ACK_PWR_DEBUG = 356516106;
    public static final int ID_MCU_ACK_TEST = 356516106;
    @SystemApi
    public static final int ID_MCU_ATLS_STATUS = 557847614;
    @SystemApi
    public static final int ID_MCU_BATCHARG_STATUS = 560993292;
    @SystemApi
    public static final int ID_MCU_BATTERY_STATUS = 560993288;
    @SystemApi
    public static final int ID_MCU_BLE_ID_DATA = 560993375;
    @SystemApi
    public static final int ID_MCU_BLE_ID_DATA_RSP = 557847648;
    @SystemApi
    public static final int ID_MCU_BL_IC_DRIVER_ST = 557847652;
    @SystemApi
    public static final int ID_MCU_BL_TEMP = 557847651;
    public static final int ID_MCU_BMS_INFO = 356516106;
    public static final int ID_MCU_BURN_MCU_DONE = 356516106;
    public static final int ID_MCU_BURN_MCU_ERROR = 356516106;
    public static final int ID_MCU_BURN_MCU_PROCESS = 356516106;
    public static final int ID_MCU_CAN_DEBUG_RAW_DATA = 356516106;
    @SystemApi
    public static final int ID_MCU_CHAIR_WELCOME_MODE = 557849724;
    public static final int ID_MCU_CHARGE_MODE = 356516106;
    @SystemApi
    public static final int ID_MCU_CID_FB = 557847629;
    public static final int ID_MCU_CMD_BMS = 356516106;
    public static final int ID_MCU_CMD_DIAGNOSE = 356516106;
    public static final int ID_MCU_CMD_DISPLAY = 356516106;
    public static final int ID_MCU_CMD_DUG_REQ = 356516106;
    public static final int ID_MCU_CMD_GPS_INFO = 356516106;
    public static final int ID_MCU_CMD_IRDETO_KEY = 356516106;
    public static final int ID_MCU_CMD_OTA1 = 356516106;
    public static final int ID_MCU_CMD_PM_SILENT = 356516106;
    public static final int ID_MCU_CMD_POWER_DEBUG = 356516106;
    public static final int ID_MCU_CMD_PSU_OTA = 560993329;
    public static final int ID_MCU_CMD_RESET_4G = 356516106;
    public static final int ID_MCU_CMD_TEST = 356516106;
    @Deprecated
    public static final int ID_MCU_CPU_TEMPERATURE = 559944801;
    @SystemApi
    public static final int ID_MCU_DELAY_SLEEP = 557913182;
    @SystemApi
    public static final int ID_MCU_DIAG_HORN_INFO = 557913173;
    @SystemApi
    public static final int ID_MCU_DISPLAY = 554701854;
    @Deprecated
    public static final int ID_MCU_DTC_REPORT_EV = 560993312;
    @SystemApi
    public static final int ID_MCU_DVTEST_BAT_TM = 559944757;
    @SystemApi
    public static final int ID_MCU_DVTEST_BBAT = 560993331;
    @SystemApi
    public static final int ID_MCU_DVTEST_MCU_TM = 559944756;
    @SystemApi
    public static final int ID_MCU_DVTEST_PCB_TM = 559944758;
    @SystemApi
    public static final int ID_MCU_DVTEST_SW = 557847608;
    @SystemApi
    public static final int ID_MCU_DVTEST_TMP_CYCLE = 557847609;
    @SystemApi
    public static final int ID_MCU_ENTER_REPAIR_MODE = 557847600;
    @SystemApi
    public static final int ID_MCU_FACE_ID_MODE = 557849728;
    @SystemApi
    public static final int ID_MCU_FACE_ID_SW = 557849727;
    @SystemApi
    public static final int ID_MCU_FACTORY_MODE = 557847658;
    public static final int ID_MCU_FAILED_POP_UP_ALARM = 557847661;
    @SystemApi
    public static final int ID_MCU_FAULT_INFO = 560993287;
    public static final int ID_MCU_GET_INFO = 356516106;
    @SystemApi
    public static final int ID_MCU_GET_INFO_MSG = 557847638;
    @SystemApi
    public static final int ID_MCU_G_SENSOR_EV = 560993311;
    public static final int ID_MCU_ICM_GEOFENCE = 557848168;
    @Deprecated
    public static final int ID_MCU_IG_STATE = 356516106;
    @SystemApi
    public static final int ID_MCU_IG_STATUS = 557847561;
    public static final int ID_MCU_IG_THEFT_HEART_BEAT = 356516106;
    public static final int ID_MCU_MAP_INF_SYNC = 560993397;
    @SystemApi
    public static final int ID_MCU_MONITOR_ST = 557847643;
    public static final int ID_MCU_MQTT_LOGIN_INFO_CLIENT = 356516106;
    public static final int ID_MCU_MQTT_LOGIN_INFO_USER_NAME = 356516106;
    public static final int ID_MCU_MQTT_LOGIN_INFO_USER_PASSWORD = 356516106;
    public static final int ID_MCU_MQTT_LOGIN_INFO_USER_SSL_ADDR = 356516106;
    public static final int ID_MCU_OTA_1 = 356516106;
    @SystemApi
    public static final int ID_MCU_OTA_RESP_VER = 554701908;
    public static final int ID_MCU_PM_STATUS = 560993283;
    @SystemApi
    public static final int ID_MCU_POWERDOWN_ACTION = 557847635;
    @SystemApi
    public static final int ID_MCU_POWERDOWN_SWITCH = 557847634;
    public static final int ID_MCU_POWER_REQ = 356516106;
    public static final int ID_MCU_PSU_OTA_EV = 557913151;
    @SystemApi
    public static final int ID_MCU_PSU_TEST = 557847607;
    public static final int ID_MCU_REMIND_WARNING = 557847680;
    public static final int ID_MCU_REMOTE_FLAG = 560993309;
    public static final int ID_MCU_REPORT_CIU_ST = 557847613;
    public static final int ID_MCU_REPORT_OCU_ST = 557847612;
    @SystemApi
    public static final int ID_MCU_REQ_TRKP_MODE = 557847655;
    @SystemApi
    public static final int ID_MCU_REQ_TRKP_TIMEON = 557847656;
    public static final int ID_MCU_RESET_4G_EV = 356516106;
    public static final int ID_MCU_RTC = 356516106;
    public static final int ID_MCU_RVC_EN = 557847670;
    @SystemApi
    public static final int ID_MCU_RVC_EN_CONTROL = 557847650;
    public static final int ID_MCU_RVC_VERSION = 557847671;
    @SystemApi
    public static final int ID_MCU_R_CAMERA_STATUS = 560993290;
    @SystemApi
    public static final int ID_MCU_R_LIGHT_STATUS = 560993291;
    public static final int ID_MCU_SHOCK_VALUE = 356516106;
    public static final int ID_MCU_SOC_RESP_DTC_INFO = 557913218;
    public static final int ID_MCU_SPEED_PWM = 356516106;
    @SystemApi
    public static final int ID_MCU_STOP_MONITOR = 557913132;
    public static final int ID_MCU_TBOX_USB_VBUS_CTRL = 557847668;
    @SystemApi
    public static final int ID_MCU_TEMP_FACTORY = 557847659;
    public static final int ID_MCU_THEFT_STATE = 356516106;
    @SystemApi
    public static final int ID_MCU_TIME_TO_COMPLETE_CHARGE = 557847639;
    public static final int ID_MCU_TIME_ZONE = 356516106;
    public static final int ID_MCU_TRUNK_POWERON_TO_APP = 557847662;
    public static final int ID_MCU_VECH_MODE = 557913178;
    public static final int ID_MCU_VENTILATION_STATE = 356516106;
    public static final int ID_MCU_WAKE_UP_BY_PHONE = 356516106;
    @SystemApi
    public static final int ID_MCU_WIFI_HOTSPOT_REQ = 557847660;
    @SystemApi
    public static final int ID_OS_OTA_STATUS = 557847617;
    @SystemApi
    public static final int ID_OTA_MCU_REQ_STATUS = 557847592;
    @SystemApi
    public static final int ID_OTA_MCU_REQ_UPDATEFILE = 557847593;
    @SystemApi
    public static final int ID_OTA_MCU_SEND_UPDATEFILE = 554701866;
    @SystemApi
    public static final int ID_OTA_MCU_UPDATE_STATUS = 557847595;
    public static final int ID_OTA_SEND_UPDATE_FILE = 554701955;
    public static final int ID_QNX_CRASH_INFO = 554701957;
    public static final int MCU_ATLS_STATUS_FINISH = 3;
    public static final int MCU_ATLS_STATUS_PENDING = 2;
    public static final int MCU_ATLS_STATUS_START = 1;
    public static final int MCU_DELAY_SLEEP_AUTO_WAKE = 2;
    public static final int MCU_DELAY_SLEEP_DATA_DOWNLOAD = 1;
    public static final int MCU_DRIVING_MODE_COMFORT = 1;
    public static final int MCU_DRIVING_MODE_ECO = 2;
    public static final int MCU_DRIVING_MODE_ECO_PLUS = 3;
    public static final int MCU_DRIVING_MODE_SHOW = 5;
    public static final int MCU_DRIVING_MODE_SPORT = 4;
    public static final int MCU_FACE_ID_MODE_FACE_ACTIVE = 1;
    public static final int MCU_FACE_ID_MODE_FACE_ID_FAIL = 2;
    public static final int MCU_FACE_ID_MODE_FACE_ID_PENDING = 3;
    public static final int MCU_FACE_ID_MODE_INACTIVE_ALL = 0;
    public static final int MCU_FACE_ID_MODE_PIN_ACTIVE = 2;
    public static final int MCU_FACE_ID_MODE_PIN_FAIL = 5;
    public static final int MCU_FACE_ID_MODE_PIN_PENDING = 6;
    public static final int MCU_FACE_ID_STATE_FACE_ID_PASS = 1;
    public static final int MCU_FACE_ID_STATE_PIN_PASS = 4;
    public static final int MCU_FAILED_POP_UP_ALARM_4G = 4;
    public static final int MCU_FAILED_POP_UP_ALARM_BLE_WATCH = 4;
    public static final int MCU_FAILED_POP_UP_ALARM_NO_REQUEST = 10;
    private static final String MCU_HARDWARE_ID_PROPERTY = "persist.sys.mcu.hardwareId";
    public static final int MCU_IG_STATUS_IG_OFF = 0;
    public static final int MCU_IG_STATUS_LOCAL_IG_ON = 1;
    public static final int MCU_IG_STATUS_REMOTE_IG_ON = 2;
    public static final int MCU_IS_NOT_WAKE_UP_BY_PHONE = 0;
    public static final int MCU_IS_WAKE_UP_BY_PHONE = 1;
    public static final int MCU_MONITOR_ACTIVE = 0;
    public static final int MCU_MONITOR_INACTIVE = 1;
    public static final int MCU_MONITOR_MODE_PERMANENT_OFF = 1;
    public static final int MCU_MONITOR_MODE_PERMANENT_ON = 0;
    public static final int MCU_MONITOR_MODE_TEMPORARY_OFF = 2;
    public static final int MCU_MONITOR_TEMPORARY_INACTIVE = 2;
    public static final int MCU_REQUEST_GET_ACCOUNT_INFO = 2;
    public static final int MCU_REQUEST_GET_CHARGE_COMPLETE_TIME = 3;
    public static final int MCU_REQUEST_GET_GPS_INFO = 1;
    public static final int MCU_STATUS_OFF = 0;
    public static final int MCU_STATUS_ON = 1;
    private static final String MCU_VIN_CODE_PROPERTY = "sys.xiaopeng.vin";
    public static final int NFC_START_FAILED = 3;
    @SystemApi
    public static final int NOTICE_POWER_OFF_COUNTDOWN_END = 1;
    @SystemApi
    public static final int NOTICE_POWER_OFF_COUNTDOWN_START = 0;
    public static final int NO_ACTION = 0;
    public static final int OCU_EXIST = 1;
    @Deprecated
    public static final int OCU_EXSIT = 1;
    public static final int OCU_NOT_EXIST = 2;
    @Deprecated
    public static final int OCU_NOT_EXSIT = 2;
    public static final int OPEN_WIFI_HOTSPOT_RESULT_FAILED = 0;
    public static final int OPEN_WIFI_HOTSPOT_RESULT_SUCCESSFUL = 1;
    @SystemApi
    public static final int OTAM_4G_REQ_FIRMWARE_ROLLBACK = 5;
    @SystemApi
    public static final int OTAM_4G_REQ_FIRMWARE_SWITCH = 3;
    @SystemApi
    public static final int OTAM_4G_REQ_MCU_RESET = 4;
    @SystemApi
    public static final int OTAM_4G_REQ_MCU_VERSION = 6;
    @SystemApi
    public static final int OTAM_MCU_RESP_UPDATE_BUSY = 2;
    @SystemApi
    public static final int OTAM_MCU_RSPD_DRIVER_ADDR_ERR = 4;
    @SystemApi
    public static final int OTAM_MCU_RSPD_DRIVER_CHECK_ERR = 3;
    @SystemApi
    public static final int OTAM_MCU_RSPD_DRIVER_CHECK_OK = 9;
    @SystemApi
    public static final int OTAM_MCU_RSPD_FIRMWARE_ADDR_ERR = 6;
    @SystemApi
    public static final int OTAM_MCU_RSPD_FIRMWARE_CHECK_ERR = 7;
    @SystemApi
    public static final int OTAM_MCU_RSPD_FIRMWARE_CHECK_OK = 10;
    @SystemApi
    public static final int OTAM_MCU_RSPD_FIRMWARE_SWITCH_FINISH = 5;
    @SystemApi
    public static final int OTAM_MCU_RSPD_NOK = 1;
    @SystemApi
    public static final int OTAM_MCU_RSPD_OK = 0;
    @SystemApi
    public static final int OTAM_MCU_RSPD_WRITE_FLAG_ERR = 8;
    @SystemApi
    public static final int OTA_4G_ACK_NOK = 1;
    @SystemApi
    public static final int OTA_4G_ACK_OK = 0;
    @SystemApi
    public static final int OTA_4G_NO_REQ = 0;
    @SystemApi
    public static final int OTA_4G_REQ_RESULT = 2;
    @SystemApi
    public static final int OTA_4G_REQ_UPDATE = 1;
    @SystemApi
    @Deprecated
    public static final int OTA_MCU_ACK_NOK = 1;
    @SystemApi
    @Deprecated
    public static final int OTA_MCU_ACK_OK = 0;
    @SystemApi
    @Deprecated
    public static final int OTA_MCU_FAIL_CC_ERR = 2;
    @SystemApi
    @Deprecated
    public static final int OTA_MCU_FAIL_DATA_TIMEOUT = 4;
    @SystemApi
    @Deprecated
    public static final int OTA_MCU_FAIL_REPROMGRAM_ERR = 3;
    @SystemApi
    public static final int OTA_MCU_REQ_FIRMWARE_A = 1;
    @SystemApi
    public static final int OTA_MCU_REQ_FIRMWARE_B = 2;
    @SystemApi
    public static final int OTA_MCU_REQ_FLASH_DRIVER = 0;
    @SystemApi
    @Deprecated
    public static final int OTA_MCU_RSPD_UPDATE_SUCCESS_FINISH = 5;
    @SystemApi
    public static final int PSU_TEST_FAIL = 1;
    @SystemApi
    public static final int PSU_TEST_OK = 2;
    public static final int RECEIVE_BLE_ACCOUNT_DATA_FAIL = 1;
    public static final int RECEIVE_BLE_ACCOUNT_DATA_SUCCESS = 0;
    public static final int REMIND_WARNING_NO = 0;
    public static final int REMIND_WARNING_YES = 1;
    @SystemApi
    public static final int REMOTE_CONTROL_COMPLETED = 2;
    public static final int REMOTE_CONTROL_KEY_START_FAILED = 1;
    @SystemApi
    public static final int REMOTE_CONTROL_PROCESSING = 1;
    public static final int REQUEST_TRUNK_POWER_OFF = 1;
    public static final int REQUEST_TRUNK_POWER_ON = 0;
    public static final int RESET_TBOX_USB_VBUS = 1;
    public static final int RVC_STATE_ERROR = 1;
    public static final int RVC_STATE_OK = 0;
    @SystemApi
    public static final int SHUTDOWN_PARAM_SLEEP = 2;
    @SystemApi
    public static final int START_PSU_TEST = 1;
    public static final int START_SUCCESSFUL = 0;
    public static final int STATE_ON_FULL = 1;
    public static final int STATE_ON_SLEEP = 2;
    public static final int STATE_SHUTDOWN_PREPARE = 3;
    @SystemApi
    public static final int STOP_PSU_TEST = 0;
    private static final String TAG = "CarMcuManager";
    public static final int TRUNK_POWER_STATUS_FAULT = 3;
    public static final int TRUNK_POWER_STATUS_OFF = 0;
    public static final int TRUNK_POWER_STATUS_ON = 1;
    public static final int TRUNK_POWER_STATUS_WORKING = 2;
    private final ArraySet<Integer> mMcuPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarMcuEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarMcuManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mMcuPropertyIds = new ArraySet<>(Arrays.asList(559944801, 560993287, 554701854, 560993283, 557913151, 560993329, 560993311, 557847592, 557847593, 554701866, 557847595, 557847600, 560993331, 559944756, 559944757, 559944758, 557847607, 557847561, 557849724, 560993309, 557847612, 557847613, 557847614, 557849727, 557849728, 557847634, 557847635, 554701908, 557913173, 557847638, 557847639, 557913132, 557847643, 557913182, 560993375, 557847648, 557847629, 557847650, 557847651, 557847652, 557847655, 557847656, 557847658, 557847659, 557847660, 557847661, 557847662, 560993397, 557847668, 557847670, 557847671, 557847680, 557913218, 557848168, 554701955, 554701957));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return false;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mMcuPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_MCU_SERVICE;
    }

    @Deprecated
    public int getMcuTheftState() throws Exception {
        return this.mService.getMcuBurglarAlarmState();
    }

    @Deprecated
    public void setIgOn() throws Exception {
        this.mService.setMcuIgOn();
    }

    @Deprecated
    public void setIgOff() throws Exception {
        this.mService.setMcuIgOff();
    }

    @Deprecated
    public void setIgHeartBeat() throws Exception {
        this.mService.setIgHeartBeat();
    }

    @Deprecated
    public void setTheftHeartBeatOn() throws Exception {
        this.mService.setTheftHeartBeatOn();
    }

    @Deprecated
    public void setTheftHeartBeatOff() throws Exception {
        this.mService.setTheftHeartBeatOff();
    }

    @Deprecated
    public void setMcuIsWakeUpByPhone(int status) throws Exception {
        this.mService.setMcuIsWakeUpByPhone(status);
    }

    @SystemApi
    public String getMcuHardwareId() throws Exception {
        String mHardWareIdPro = SystemProperties.get(MCU_HARDWARE_ID_PROPERTY, "");
        return mHardWareIdPro;
    }

    @SystemApi
    public String getVinCode() throws Exception {
        String mVinCodePro = SystemProperties.get(MCU_VIN_CODE_PROPERTY, "");
        return mVinCodePro;
    }

    @Deprecated
    public void setMqttLogInfo(String clientId, String userName, String passWord, String sslAddr) throws Exception {
        this.mService.setMqttLogInfo(clientId, userName, passWord, sslAddr);
    }

    public float getCpuTemperature() throws Exception {
        return this.mService.getCpuTemperature();
    }

    @SystemApi
    public void setHorn(int status) throws Exception {
        this.mService.setMcuHorn(status);
    }

    @SystemApi
    public void setFlash(int status) throws Exception {
        this.mService.setMcuFlash(status);
    }

    @Deprecated
    public void setDriveMode(int mode) throws Exception {
    }

    @Deprecated
    public void sendFactoryTestMsgToMcu(int[] msg) throws Exception {
        this.mService.sendTestMsgToMcu(msg);
    }

    @Deprecated
    public void sendFactoryPwrDebugMsgToMcu(int[] msg) throws Exception {
        this.mService.sendPwrDebugMsgToMcu(msg);
    }

    @Deprecated
    public void sendFactoryDugReqMsgToMcu(int[] msg) throws Exception {
        this.mService.sendDugReqMsgToMcu(msg);
    }

    @Deprecated
    public void sendFactoryDisplayTypeMsgToMcu(int msg) throws Exception {
        this.mService.sendDisplayTypeMsgToMcu(msg);
    }

    @SystemApi
    public String getFactoryDisplayTypeMsgToMcu() throws Exception {
        return this.mService.getMcuFactoryDisplayTypeMsgToMcu();
    }

    @Deprecated
    public void sendFactoryPmSilentMsgToMcu(int msg) throws Exception {
        this.mService.sendPmSilentMsgToMcu(msg);
    }

    @Deprecated
    public void sendFactoryMcuBmsMsgToMcu(int msg) throws Exception {
        this.mService.sendMcuBmsMsgToMcu(msg);
    }

    @Deprecated
    public int getFactoryMcuBmsMsgToMcu() throws Exception {
        return 0;
    }

    @Deprecated
    public void sendOta1MsgToMcu(int[] msg) throws Exception {
    }

    public void sendPsuOtaMsgToMcu(int[] msg) throws Exception {
        this.mService.sendPsuOtaMsgToMcu(msg);
    }

    @Deprecated
    public void sendFactorySecretKeyToMcu(byte[] msg) throws Exception {
    }

    @Deprecated
    public void sendRequestWakeToMcu(int event) throws Exception {
    }

    @Deprecated
    public void sendDiagnoseMsgToMcu(int[] cmd) throws Exception {
    }

    @Deprecated
    public void sendReset4gMsgToMcu(int[] msg) throws Exception {
    }

    @Deprecated
    public void sendResetModemMsgToMcu(int msg) throws Exception {
    }

    @Deprecated
    public void sendGpsInfoMsgToMcu(int[] msg) throws Exception {
    }

    @Deprecated
    public void updateMcuBin(String path) throws Exception {
    }

    @Deprecated
    public void setMcuRtcTime(long rtcTime) throws Exception {
    }

    @Deprecated
    public long getMcuRtcTime() throws Exception {
        return 0L;
    }

    @Deprecated
    public void setMcuTimeZone(int timeZoneValue) throws Exception {
    }

    @SystemApi
    public byte[] getPmStatusWithParameter() throws Exception {
        return this.mService.getPmStatusWithParameter();
    }

    public int getPmStatus() throws Exception {
        return this.mService.getPmStatus();
    }

    @SystemApi
    @Deprecated
    public int getHardwareVersion() throws Exception {
        return Car.getHardwareVersion();
    }

    @SystemApi
    @Deprecated
    public boolean isExportVersion() throws Exception {
        return Car.isExportVersion();
    }

    @SystemApi
    @Deprecated
    public String getVersionInCountryCode() throws Exception {
        return Car.getVersionInCountryCode();
    }

    @SystemApi
    @Deprecated
    public String getHardwareCarTypeExt() throws Exception {
        return Car.getHardwareCarTypeExt();
    }

    @SystemApi
    @Deprecated
    public String getHardwareCarType() throws Exception {
        return Car.getHardwareCarType();
    }

    @SystemApi
    @Deprecated
    public String getXpCduType() {
        return Car.getXpCduType();
    }

    @SystemApi
    @Deprecated
    public String getHardwareCarStage() throws Exception {
        return Car.getHardwareCarStage();
    }

    @SystemApi
    @Deprecated
    public String getUuidCode() throws Exception {
        return Car.getUuidCode();
    }

    @SystemApi
    @Deprecated
    public boolean isSystemFirstBoot() {
        return Car.isSystemFirstBoot();
    }

    @SystemApi
    public int[] getGSensorOffset() throws Exception {
        return this.mService.getGSensorOffset();
    }

    @Deprecated
    public int[] getScreenTempValue() throws Exception {
        return this.mService.getScreenTempValue();
    }

    @SystemApi
    public int getBatteryStatusFromMcu() throws Exception {
        return this.mService.getMcuBatteryStatus();
    }

    @SystemApi
    public int getIgStatusFromMcu() throws Exception {
        return this.mService.getMcuIgState();
    }

    @SystemApi
    public int getCameraStatusFromMcu() throws Exception {
        return this.mService.getMcuCameraStatus();
    }

    @SystemApi
    public int getLampStatusFromMcu() throws Exception {
        return this.mService.getMcuLampStatus();
    }

    @SystemApi
    public int getChargeStatusFromMcu() throws Exception {
        return this.mService.getMcuChargeStatus();
    }

    @SystemApi
    public int getOtaMcuReqStatus() throws Exception {
        return this.mService.getMcuUpdateReqStatus();
    }

    @SystemApi
    public void setOtaMcuReqStatus(int data) throws Exception {
        this.mService.setMcuUpdateReqStatus(data);
    }

    @SystemApi
    public int getOtaMcuReqUpdatefile() throws Exception {
        return this.mService.getOtaMcuReqUpdatefile();
    }

    @SystemApi
    public void setOtaMcuReqUpdatefile(int data) throws Exception {
        this.mService.setOtaMcuReqUpdatefile(data);
    }

    @SystemApi
    public void setOtaMcuSendUpdatefile(String file) throws Exception {
        this.mService.setOtaMcuSendUpdatefile(file);
    }

    @SystemApi
    public int getOtaMcuUpdateStatus() throws Exception {
        return this.mService.getOtaMcuUpdateStatus();
    }

    @Deprecated
    public void sendChargeCompleteTime2Mcu(int min) throws Exception {
        this.mService.sendChargeCompleteTimeToMcu(min);
    }

    @SystemApi
    public void sendChargeCompleteTimeToMcu(int min) throws Exception {
        this.mService.sendChargeCompleteTimeToMcu(min);
    }

    @SystemApi
    public byte[] getMcuFaultInfo() throws Exception {
        return this.mService.getMcuFaultInfo();
    }

    @Deprecated
    public int getMcu4gErrorStatus() throws Exception {
        return 0;
    }

    @Deprecated
    public int getMcuVentilateState() throws Exception {
        return 0;
    }

    @Deprecated
    public int[] getDtcReportEv() throws Exception {
        return this.mService.getMcuDtcReportEv();
    }

    @Deprecated
    public int[] getIgStatus() throws Exception {
        int[] ret = {0};
        return ret;
    }

    @Deprecated
    public void setShockValue2Mcu(int shockValue2Mcu) throws Exception {
    }

    @SystemApi
    public void setRepairMode(int mode) throws Exception {
        this.mService.setMcuRepairMode(mode);
    }

    @SystemApi
    public void setPsuTestReq(int req) throws Exception {
        this.mService.setMcuPsuTestReq(req);
    }

    @SystemApi
    public int getPsuTestResult() throws Exception {
        return this.mService.getMcuPsuTestResult();
    }

    @SystemApi
    public float getDvMcuTemp() throws Exception {
        return this.mService.getDvTestMcuTemp();
    }

    @SystemApi
    public float getDvBatTemp() throws Exception {
        return this.mService.getDvTestBatTemp();
    }

    @SystemApi
    public float getDvPcbTemp() throws Exception {
        return this.mService.getDvTestPcbTemp();
    }

    @SystemApi
    public byte[] getDvBattMsg() throws Exception {
        return this.mService.getMcuDvBattMsg();
    }

    @SystemApi
    public void setDvTestReq(int req) throws Exception {
        this.mService.setMcuDvTestReq(req);
    }

    @SystemApi
    public void setDvTempSamplingPeriod(int second) throws Exception {
        this.mService.setMcuDvTempSamplingPeriod(second);
    }

    public int getChairWelcomeMode() throws Exception {
        return this.mService.getMcuChairWelcomeMode();
    }

    public void setChairWelcomeMode(int status) throws Exception {
        this.mService.setMcuChairWelcomeMode(status);
    }

    @SystemApi
    public void setRemoteControlFeedback(int status) throws Exception {
        this.mService.setMcuRemoteControlFeedback(status);
    }

    public int getOcuState() throws Exception {
        return this.mService.getMcuOcuState();
    }

    public int getCiuState() throws Exception {
        return this.mService.getMcuCiuState();
    }

    @SystemApi
    public int getMcuAtlsStatus() throws Exception {
        return this.mService.getMcuAtlsState();
    }

    @SystemApi
    public int[] getPsuOtaFeedbackMsg() throws Exception {
        return this.mService.getMcuPsuOtaFeedbackMsg();
    }

    @SystemApi
    public void setFaceIdSwitch(int enable) throws Exception {
        this.mService.setMcuFaceIdSw(enable);
    }

    @SystemApi
    public int getFaceIdSwitchState() throws Exception {
        return this.mService.getMcuFaceIdSwState();
    }

    @SystemApi
    public int getFaceIdMode() throws Exception {
        return this.mService.getMcuFaceIdMode();
    }

    @SystemApi
    public void setFaceIdModeState(int state) throws Exception {
        this.mService.setFaceIdModeState(state);
    }

    @SystemApi
    public void setAndroidOtaStatus(int status) throws Exception {
        this.mService.setMcuAndroidOtaStatus(status);
    }

    @SystemApi
    public void setAutoPowerOffSwitch(int enable) throws Exception {
        this.mService.setMcuAutoPowerOffSw(enable);
    }

    @SystemApi
    public int getAutoPowerOffSwitchState() throws Exception {
        return this.mService.getMcuAutoPowerOffSwitchState();
    }

    @SystemApi
    public void setPowerOffCountdownAction(int action) throws Exception {
        this.mService.setMcuPowerOffCountdownAction(action);
    }

    @SystemApi
    public int getPowerOffCountdownNotice() throws Exception {
        return this.mService.getMcuPowerOffCountdownNotice();
    }

    @SystemApi
    public String getMcuVersion() throws Exception {
        return this.mService.getMcuVersion();
    }

    @SystemApi
    public void setHornsStates(int lfHornSt, int lrHornSt, int rfHornSt, int rrHornSt) throws Exception {
        this.mService.setMcuHornsStates(lfHornSt, lrHornSt, rfHornSt, rrHornSt);
    }

    @SystemApi
    public int getMcuRequestedMessage() throws Exception {
        return this.mService.getMcuRequestedMessage();
    }

    @SystemApi
    public void setMcuMonitorSwitch(int mode, int timeInMinutes) throws Exception {
        this.mService.setMcuMonitorSwitch(mode, timeInMinutes);
    }

    @SystemApi
    public int getMcuMonitorState() throws Exception {
        return this.mService.getMcuMonitorState();
    }

    @SystemApi
    public void setMcuDelaySleep(int heartBeat) throws Exception {
        this.mService.setMcuDelaySleep(heartBeat, 0);
    }

    @SystemApi
    public byte[] getBleAccountData() throws Exception {
        return this.mService.getMcuBleAccountData();
    }

    @SystemApi
    public void sendBleAccountDataFeedback(int feedback) throws Exception {
        this.mService.sendMcuBleAccountDataFeedback(feedback);
    }

    @SystemApi
    public int getCidState() throws Exception {
        return this.mService.getMcuCidState();
    }

    @SystemApi
    public void setRvcState(int state) throws Exception {
        this.mService.setMcuRvcState(state);
    }

    @SystemApi
    public int getBacklightTemperature() throws Exception {
        return this.mService.getMcuBacklightTemperature();
    }

    @SystemApi
    public int getBacklightIcDriverState() throws Exception {
        return this.mService.getMcuBacklightIcDriverState();
    }

    @SystemApi
    public void setTrunkPowerSw(int sw) throws Exception {
        this.mService.setMcuTrunkPowerSw(sw);
    }

    @SystemApi
    public int getTrunkPowerStatus() throws Exception {
        return this.mService.getMcuTrunkPowerStatus();
    }

    @SystemApi
    public void setTrunkPowerOffDelay(int delay) throws Exception {
        this.mService.setMcuTrunkPowerOffDelay(delay);
    }

    @SystemApi
    public int getTrunkPowerOffDelay() throws Exception {
        return this.mService.getMcuTrunkPowerOffDelay();
    }

    @SystemApi
    public void setFactoryModeSwitch(int enable) throws Exception {
        this.mService.setMcuFactoryModeSwitch(enable);
    }

    @SystemApi
    public int getFactoryModeSwitchStatus() throws Exception {
        return this.mService.getMcuFactoryModeSwitchStatus();
    }

    @SystemApi
    public int getTemporaryFactoryStatus() throws Exception {
        return this.mService.getMcuTemporaryFactoryStatus();
    }

    @SystemApi
    public int getWifiHotspotRequest() throws Exception {
        return this.mService.getMcuWifiHotspotRequest();
    }

    @SystemApi
    public void sendOpenWifiHotspotResponse(int response) throws Exception {
        this.mService.sendMcuOpenWifiHotspotResponse(response);
    }

    public int getKeyStartStatus() throws Exception {
        return this.mService.getMcuKeyStartStatus();
    }

    public int getTrunkPowerOnRequest() throws Exception {
        return this.mService.getMcuTrunkPowerOnRequest();
    }

    @SystemApi
    public void sendMapVersion(String version) throws Exception {
        this.mService.sendMcuMapVersion(version);
    }

    @SystemApi
    public void sendTboxVBusControlCommand(int cmd) throws Exception {
        this.mService.sendMcuTboxVBusControlCommand(cmd);
    }

    @SystemApi
    public int getRvcEnable() throws Exception {
        return this.mService.getMcuRvcEnable();
    }

    @SystemApi
    public void setRvcEnable(int enable) throws Exception {
        this.mService.setMcuRvcEnable(enable);
    }

    @SystemApi
    public void setRvcVersion(int version) throws Exception {
        this.mService.setMcuRvcVersion(version);
    }

    public int getRemindWarningStatus() throws Exception {
        return this.mService.getMcuRemindWarningStatus();
    }

    public void setSocRespDTCInfo(int module, int errCode, int errCodeSt) throws Exception {
        this.mService.setMcuSocRespDTCInfo(module, errCode, errCodeSt);
    }

    public void setGeofenceStatus(int status) throws Exception {
        this.mService.sendMcuGeofenceStatus(status);
    }

    public void sendOtaUpdateFile(String filePath) throws Exception {
        this.mService.sendMcuOtaUpdateFile(filePath);
    }
}
