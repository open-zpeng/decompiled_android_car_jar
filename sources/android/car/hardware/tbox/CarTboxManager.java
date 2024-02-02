package android.car.hardware.tbox;

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
public final class CarTboxManager extends CarEcuManager {
    @SystemApi
    public static final int BLE_ACCOUNT_FB_NO_CODE_DATA = 1;
    @SystemApi
    public static final int BLE_ACCOUNT_FB_REGISTER_FAILURE = 3;
    @SystemApi
    public static final int BLE_ACCOUNT_FB_REGISTER_SUCCESS = 2;
    public static final int CAP_MODEM_OFF = 0;
    public static final int CAP_MODEM_ON = 1;
    public static final int CAP_MODEM_QUERY = 2;
    public static final int CDU_TBOX_OTA_WOKING_ST_ACTIVE = 1;
    public static final int CDU_TBOX_OTA_WOKING_ST_INACTIVE = 0;
    @SystemApi
    public static final int CERF_VERIFY_SUCCESS = 0;
    @SystemApi
    public static final int CERT_INSTALL_FAILED_GET_ICCID = 2;
    @SystemApi
    public static final int CERT_INSTALL_FAILED_PARSE_CERF = 4;
    @SystemApi
    public static final int CERT_INSTALL_FAILED_REQUEST_CERF = 3;
    @SystemApi
    public static final int CERT_INSTALL_FAILED_UNKNOWN = 1;
    @SystemApi
    public static final int CERT_INSTALL_FAILED_VERIFY_CERF = 5;
    @SystemApi
    public static final int CERT_INSTALL_SUCCESS = 0;
    @SystemApi
    public static final int CONNECT_AVP_WIFI_NOT_STARTED = 0;
    @SystemApi
    public static final int CONNECT_AVP_WIFI_STARTED = 1;
    private static final boolean DBG = false;
    public static final int ECALL_STATE_ECALL_CALLBACK_WAIT = 4;
    public static final int ECALL_STATE_ECALL_ESTABLISHED = 5;
    public static final int ECALL_STATE_ECALL_ESTABLISHMENT = 2;
    public static final int ECALL_STATE_ECALL_FAILURE = 3;
    public static final int ECALL_STATE_IDLE = 1;
    public static final int ECALL_STATE_NO_CALL = 0;
    @SystemApi
    public static final int ENTER_DV_TEST = 1;
    @SystemApi
    public static final int EXIT_DV_TEST = 0;
    @SystemApi
    public static final int GPS_ANT_STATE_NORMAL = 0;
    @SystemApi
    public static final int GPS_ANT_STATE_OPEN = 1;
    @SystemApi
    public static final int GPS_ANT_STATE_SHORT = 2;
    @SystemApi
    public static final int GPS_ANT_STATE_UNKNOWN = 3;
    @SystemApi
    public static final int GPS_DEBUG_FUNCTION_CLOSE = 1;
    @SystemApi
    public static final int GPS_DEBUG_FUNCTION_OPEN = 0;
    @SystemApi
    public static final int GPS_POLLING_NOT_WRITEABLE = 1;
    @SystemApi
    public static final int GPS_POLLING_WRITEABLE = 0;
    @SystemApi
    public static final int GPS_POWER_STATE_OFF = 0;
    @SystemApi
    public static final int GPS_POWER_STATE_ON = 1;
    @SystemApi
    public static final int GPS_RESET_COLD = 2;
    @SystemApi
    public static final int GPS_RESET_FAILED = -1;
    @SystemApi
    public static final int GPS_RESET_HOT = 0;
    @SystemApi
    public static final int GPS_RESET_SUCCESS = 0;
    @SystemApi
    public static final int GPS_RESET_WARM = 1;
    @SystemApi
    public static final int ID_CDU_NET_ST = 557846565;
    @SystemApi
    public static final int ID_CDU_REMOTE_DIAG_CAPTURE_RESP = 554700806;
    @SystemApi
    public static final int ID_CDU_REMOTE_DIAG_PARK_LOC = 554700804;
    public static final int ID_CDU_TBOX_APN_TRAFFIC = 558895200;
    @SystemApi
    public static final int ID_CDU_TBOX_BAND_MODEM_REQ = 557846548;
    @SystemApi
    public static final int ID_CDU_TBOX_MODEM_STATUS_REQ = 557846544;
    public static final int ID_CDU_TBOX_OTA_WOKING_ST = 557846621;
    @SystemApi
    public static final int ID_CDU_WIFI_GW = 554700803;
    @SystemApi
    public static final int ID_CDU_WIFI_STATE = 557846530;
    @SystemApi
    public static final int ID_MCU_RESET_TBOX = 557847640;
    @SystemApi
    public static final int ID_MQTT_BACKEND_AND_PIGEONAPP = 554700882;
    public static final int ID_TBOX_AC_CHARGE_UNLOCK_ST = 557846618;
    @SystemApi
    public static final int ID_TBOX_ANDROID_GPS_LOCATION = 554700872;
    public static final int ID_TBOX_APN_NETM_CONFIG = 554700897;
    public static final int ID_TBOX_APPOINT_CHG_SET = 557912099;
    @Deprecated
    public static final int ID_TBOX_APPOINT_CHG_SET_ONLY = 560992294;
    @SystemApi
    public static final int ID_TBOX_APPOINT_KEEP_TEMP = 557912138;
    @SystemApi
    public static final int ID_TBOX_APP_ACCOUNT_FB = 557846599;
    @SystemApi
    public static final int ID_TBOX_AUTO_POWER_OFF_SW = 557846575;
    @SystemApi
    public static final int ID_TBOX_AVP_START = 557846552;
    @SystemApi
    public static final int ID_TBOX_BAND_MODEM = 554700821;
    @SystemApi
    public static final int ID_TBOX_CAMERA_REMOTE_CTRL = 554700825;
    @SystemApi
    public static final int ID_TBOX_CANCEL_POWER_OFF_SW = 557846576;
    @SystemApi
    public static final int ID_TBOX_CAN_CONTROLLER = 554700822;
    @SystemApi
    public static final int ID_TBOX_CDU_APN = 554700817;
    @SystemApi
    public static final int ID_TBOX_CDU_BATTERY_KEEP_TEMP_REQ = 557847124;
    @SystemApi
    public static final int ID_TBOX_CDU_MODEM_STATUS_RESP = 554700819;
    @SystemApi
    public static final int ID_TBOX_CDU_SELF_MODE = 557846597;
    @SystemApi
    public static final int ID_TBOX_CDU_SET_BAND_MODEM_RESP = 557846551;
    @SystemApi
    public static final int ID_TBOX_CDU_WIFI_BLE_EMER = 554700878;
    @SystemApi
    public static final int ID_TBOX_CERT_INSTALL = 557846538;
    @SystemApi
    public static final int ID_TBOX_CERT_VERIFY = 557846539;
    public static final int ID_TBOX_CHARGEGUN_UNLOCK_CMD = 557846564;
    public static final int ID_TBOX_CHARGER_REQ = 557846562;
    public static final int ID_TBOX_CHARGE_STOP_SOC = 557846561;
    public static final int ID_TBOX_CHRG_GUN_LOCK_REQ = 557846567;
    @SystemApi
    public static final int ID_TBOX_CONNECT_STATE = 557846543;
    @SystemApi
    public static final int ID_TBOX_DV_BATT = 554700812;
    @SystemApi
    public static final int ID_TBOX_DV_ON_OFF = 557846570;
    @SystemApi
    public static final int ID_TBOX_DV_TEMP = 554700840;
    @SystemApi
    public static final int ID_TBOX_DV_TEMP_REQ = 557846569;
    public static final int ID_TBOX_ECALL_MUTE_REQ = 557846611;
    public static final int ID_TBOX_ECALL_STATE = 557846612;
    @SystemApi
    public static final int ID_TBOX_FACTORY_PRE_CERT = 554700880;
    @SystemApi
    public static final int ID_TBOX_GPS_ANT_POWER = 557912124;
    @SystemApi
    public static final int ID_TBOX_GPS_DEBUG = 557846591;
    @SystemApi
    public static final int ID_TBOX_GPS_HW_RESET = 557846589;
    @SystemApi
    public static final int ID_TBOX_GPS_MGA = 557846592;
    @SystemApi
    public static final int ID_TBOX_GPS_POLLING_CTRL = 557846590;
    @SystemApi
    public static final int ID_TBOX_GPS_RESET = 557846560;
    @SystemApi
    public static final int ID_TBOX_GUARD = 557912117;
    public static final int ID_TBOX_IOT_BUS_TYPE = 557846619;
    public static final int ID_TBOX_LOG_DOWNLOAD_FINISH = 557846616;
    public static final int ID_TBOX_LOG_DOWNLOAD_START = 557846615;
    public static final int ID_TBOX_MODEM_CAPTURE_CTRL = 557846614;
    @SystemApi
    public static final int ID_TBOX_MODEM_INFO = 554700814;
    @SystemApi
    public static final int ID_TBOX_MUL_BLE_REPLACE = 554700879;
    @SystemApi
    public static final int ID_TBOX_OTA_COMMIT = 554700809;
    @SystemApi
    public static final int ID_TBOX_OTA_PROGRESS = 557846541;
    @SystemApi
    public static final int ID_TBOX_OTA_UDISK = 554700845;
    @SystemApi
    public static final int ID_TBOX_OTA_UPDATE = 554700808;
    @SystemApi
    public static final int ID_TBOX_POWER_OFF_COUNTDOWN = 557912113;
    @SystemApi
    public static final int ID_TBOX_PSU_MSG = 554700831;
    @SystemApi
    public static final int ID_TBOX_REMOTE_BATTERY_KEEP_TEMP_REQ = 557846603;
    @SystemApi
    public static final int ID_TBOX_REMOTE_DIAG_CAPTURE_REQ = 557846533;
    @SystemApi
    public static final int ID_TBOX_REPAIR_MODE = 557846593;
    @SystemApi
    public static final int ID_TBOX_REPLACE_REQ = 554700870;
    @SystemApi
    public static final int ID_TBOX_RTC = 558895105;
    @SystemApi
    public static final int ID_TBOX_SIM_ST = 557846546;
    @SystemApi
    public static final int ID_TBOX_SLOW_CHARGE_REQ = 557846574;
    @SystemApi
    public static final int ID_TBOX_SOLDIER_CAMERASW = 557846594;
    @SystemApi
    public static final int ID_TBOX_SOLDIER_ENABLE = 557846596;
    @SystemApi
    public static final int ID_TBOX_SOLDIER_SW = 557846578;
    @SystemApi
    public static final int ID_TBOX_SOLDIER_WORK_ST = 557846579;
    @SystemApi
    public static final int ID_TBOX_THRESHOLD = 557912131;
    public static final int ID_TBOX_TIMEZONE_REQ = 557912153;
    @SystemApi
    public static final int ID_TBOX_UBLOX_LOG = 557846604;
    @SystemApi
    public static final int ID_TBOX_UPGRADE_4G = 557846572;
    @SystemApi
    public static final int ID_TBOX_UPGRADE_PREPARE = 554700877;
    @SystemApi
    public static final int ID_TBOX_UPGRADE_TMCU = 557846571;
    @SystemApi
    public static final int ID_TBOX_VERSION = 554700807;
    public static final int ID_TBOX_WAKE_ORDER_RTC = 554700895;
    public static final int ID_TBOX_WIFI_DEBUG_CTRL = 557846613;
    @SystemApi
    public static final int MGA_RESPONSE_FAIL = 0;
    @SystemApi
    public static final int MGA_RESPONSE_SUCCESS = 1;
    @SystemApi
    public static final int NETWORK_TYPE_4G = 1;
    @SystemApi
    public static final int NETWORK_TYPE_WIFI = 2;
    public static final int POWER_OFF_COUNTDOWN_INVALID_VALUE = 63;
    @SystemApi
    public static final int REQUEST_GET_GPS_ANT_STATE = 0;
    @SystemApi
    public static final int REQUEST_SET_GPS_ANT_POWER_OFF = 1;
    @SystemApi
    public static final int REQUEST_SET_GPS_ANT_POWER_ON = 2;
    @SystemApi
    public static final int SOLDIER_MODE_DISABLE = 0;
    @SystemApi
    public static final int SOLDIER_MODE_ENABLE = 1;
    @SystemApi
    public static final int SOLDIER_SW_LEVEL_1 = 1;
    @SystemApi
    public static final int SOLDIER_SW_LEVEL_2 = 2;
    @SystemApi
    public static final int SOLDIER_SW_LEVEL_3 = 3;
    @SystemApi
    public static final int SOLDIER_SW_OFF = 0;
    public static final int START_BATTERY_KEEP_TEMP = 1;
    public static final int START_SLOW_CHARGE = 0;
    public static final int STOP_BATTERY_KEEP_TEMP = 2;
    public static final int STOP_SLOW_CHARGE = 1;
    private static final String TAG = "CarTboxManager";
    public static final int TBOX_AC_CHARGE_UNLOCK_ST_APPOINTMENT = 2;
    public static final int TBOX_AC_CHARGE_UNLOCK_ST_UNLOCK = 1;
    public static final int TBOX_BATTERY_KEEP_TEMP_APPOINT_CANCEL = 3;
    public static final int TBOX_BATTERY_KEEP_TEMP_APPOINT_EVERYDAY = 2;
    public static final int TBOX_BATTERY_KEEP_TEMP_APPOINT_NO_COMMAND = 0;
    public static final int TBOX_BATTERY_KEEP_TEMP_APPOINT_ONCE = 1;
    public static final int TBOX_CHARGE_APPOINT_EVERYDAY = 2;
    public static final int TBOX_CHARGE_APPOINT_ONCE = 1;
    public static final int TBOX_CHARGE_CANCEL = 3;
    @SystemApi
    public static final int TBOX_CHARGE_GUN_UNLOCK_ACTIVE = 1;
    @SystemApi
    public static final int TBOX_CHARGE_GUN_UNLOCK_INACTIVE = 0;
    @SystemApi
    public static final int TBOX_CHARGE_NOCOMMAND = 0;
    public static final int TBOX_COMMAND_NO_REQUEST = 0;
    public static final int TBOX_COMMAND_REQUEST = 1;
    @SystemApi
    public static final int TBOX_CONNECTED = 1;
    public static final int TBOX_DLT_LOG = 0;
    public static final int TBOX_DLT_MODEM_LOG = 1;
    public static final int TBOX_IOT_BUS_TYPE_AC_CHARGER = 1;
    public static final int TBOX_LOG_RESP_OK = 200;
    @SystemApi
    public static final int TBOX_NOT_CONNECTED = 0;
    public static final int TBOX_ROUTE_OFF = 0;
    public static final int TBOX_ROUTE_ON = 1;
    public static final int TBOX_ROUTE_QUERY = 2;
    @SystemApi
    public static final int TBOX_START_CHARGE = 1;
    public static final int TBOX_STATUS_ACTIVE = 1;
    public static final int TBOX_STATUS_INACTIVE = 0;
    public static final int TBOX_STATUS_OFF = 0;
    public static final int TBOX_STATUS_ON = 1;
    @SystemApi
    public static final int TBOX_STOP_CHARGE = 0;
    @SystemApi
    public static final int TOGGLE_GPS_LOG_SWITCH_FAIL = -1;
    @SystemApi
    public static final int TOGGLE_GPS_LOG_SWITCH_SUCCESS = 0;
    @SystemApi
    public static final int WIFI_STATUS_OFF = 0;
    @SystemApi
    public static final int WIFI_STATUS_ON = 1;
    private final IXpVehicle mService;
    private final ArraySet<Integer> mTboxPropertyIds;

    /* loaded from: classes.dex */
    public interface CarTboxEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarTboxManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mTboxPropertyIds = new ArraySet<>(Arrays.asList(558895105, 557846530, 554700803, 554700804, 557846533, 554700806, 554700807, 554700808, 554700809, 557846541, 554700814, 557846543, 557846561, 557846562, 557846544, 554700817, 554700819, 557846548, 554700821, 557846551, 557846565, 557846546, 557846560, 554700831, 557846564, 557912099, 560992294, 554700822, 557846552, 557846538, 557846539, 554700812, 557846567, 557846570, 557846569, 554700840, 557846571, 557846572, 554700825, 554700845, 557846574, 557846575, 557846576, 557912113, 557846578, 557846579, 557912117, 557912124, 557846592, 557847640, 557846593, 557846594, 557912131, 557846596, 554700870, 557846597, 557846599, 554700872, 557847124, 557846603, 557912138, 557846604, 554700877, 554700878, 554700879, 554700880, 554700882, 557846611, 557846612, 557846613, 557846614, 557846615, 557846616, 557912153, 557846619, 557846618, 557846621, 554700895, 558895200, 554700897));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mTboxPropertyIds;
    }

    @SystemApi
    public long getRtcFromTbox() throws Exception {
        return this.mService.getTboxRtcTimeStamp();
    }

    @SystemApi
    public void setWifiStatus(int status) throws Exception {
        this.mService.setTboxWifiStatus(status);
    }

    @SystemApi
    public void setWifiGwInfo(String info) throws Exception {
        this.mService.setTboxWifiGatewayInfo(info);
    }

    @Deprecated
    public void setRemoteDiagParkLocationInfo(String locationInfo) throws Exception {
        this.mService.sendTboxRemoteDiagInfo(locationInfo);
    }

    @SystemApi
    public int getRemoteDiagCaptureRequest() throws Exception {
        return this.mService.getRemoteDiagCaptureRequest();
    }

    @SystemApi
    public void setRemoteDiagCaptureResponse(String response) throws Exception {
        this.mService.setRemoteDiagCaptureResponse(response);
    }

    @SystemApi
    public void stopTboxOta() throws Exception {
        this.mService.stopTboxOTA();
    }

    @SystemApi
    public int getOtaProgress() throws Exception {
        return this.mService.getOTAProgress();
    }

    @SystemApi
    public String getTboxModemInfo() throws Exception {
        return this.mService.getTBoxModemInfo();
    }

    @SystemApi
    public int getTboxConnectionStatus() throws Exception {
        return this.mService.getTBoxConnectionStatus();
    }

    @Deprecated
    public int getTboxChargeLimiteValue() throws Exception {
        return this.mService.getTBoxChargeLimitValue();
    }

    @Deprecated
    public void setTboxChargeLimiteValue(int value) throws Exception {
        this.mService.setTBoxChargeLimitValue(value);
    }

    public int getTboxChargeLimitValue() throws Exception {
        return this.mService.getTBoxChargeLimitValue();
    }

    public void setTboxChargeLimitValue(int value) throws Exception {
        this.mService.setTBoxChargeLimitValue(value);
    }

    public void stopCharge() throws Exception {
        this.mService.stopCharge();
    }

    public void stopFastCharge() throws Exception {
        this.mService.stopCharge();
    }

    public void startCharge() throws Exception {
        this.mService.startCharge();
    }

    public void startSlowCharge() throws Exception {
        this.mService.startTboxSlowCharge();
    }

    public void stopSlowCharge() throws Exception {
        this.mService.stopTboxSlowCharge();
    }

    @SystemApi
    public void requestTboxModemStatus() throws Exception {
        this.mService.requestTBoxModemStatus();
    }

    @SystemApi
    public String getTboxLastApnMsg() throws Exception {
        return this.mService.getTBoxLastApnMsg();
    }

    @SystemApi
    public String getTboxLastModemMsg() throws Exception {
        return this.mService.getTBoxLastModemMsg();
    }

    @SystemApi
    public void requestTboxBandModemStatus() throws Exception {
        this.mService.requestTBoxBandModemStatus();
    }

    @SystemApi
    public int getTboxLastBandModemMsg() throws Exception {
        return this.mService.getTBoxLastBandModemMsg();
    }

    @SystemApi
    public void setTboxBandModem(String value) throws Exception {
        this.mService.setTBoxBandModem(value);
    }

    @SystemApi
    public String getTboxBandModem() throws Exception {
        return this.mService.getTBoxBandModem();
    }

    public void setChargeGunUnlock() throws Exception {
        this.mService.setChargeGunUnlock();
    }

    public void setChargeGunLock() throws Exception {
        this.mService.setTboxChargeGunLock();
    }

    public void setChargeAppointTime(int[] data) throws Exception {
        this.mService.setChargeAppointTime(data);
    }

    public int[] getChargeAppointTime() throws Exception {
        return this.mService.getChargeAppointTime();
    }

    @SystemApi
    public void getSimStatusAsync() throws Exception {
        this.mService.getSimStatusAsync();
    }

    @SystemApi
    public void setNetWorkType(int type) throws Exception {
        this.mService.setNetWorkType(type);
    }

    @SystemApi
    public void setTboxVersionInfoRequest() throws Exception {
        this.mService.setTboxVersionInfoRequest();
    }

    @SystemApi
    public String getTboxVersionInfoResponse() throws Exception {
        return this.mService.getTboxVersionInfoResponse();
    }

    @SystemApi
    public void startTboxOta(String file) throws Exception {
        this.mService.startTboxOTA(file);
    }

    @SystemApi
    public String getStartTboxOtaResponse() throws Exception {
        return this.mService.getStartTboxOTAResponse();
    }

    @SystemApi
    public String getStopTboxOtaResponse() throws Exception {
        return this.mService.getStopTboxOTAResponse();
    }

    @SystemApi
    public void setGpsReset(int type) throws Exception {
        this.mService.setGpsReset(type);
    }

    @SystemApi
    public int getGpsResetResp() throws Exception {
        return this.mService.getGpsResetResp();
    }

    @SystemApi
    public void setTboxPsuMsg(String msg) throws Exception {
        this.mService.setTboxPsuMsg(msg);
    }

    @SystemApi
    public String getTboxPsuMsg() throws Exception {
        return this.mService.getTboxPsuMsg();
    }

    @SystemApi
    public void setTboxCanControlMsg(String msg) throws Exception {
        this.mService.setTboxCanControlMsg(msg);
    }

    @Deprecated
    public String getTboxCanControlMsg(String msg) throws Exception {
        return this.mService.getTboxCanControlMsg();
    }

    @SystemApi
    public String getTboxCanControlMsg() throws Exception {
        return this.mService.getTboxCanControlMsg();
    }

    @SystemApi
    public int getAvpStartStatus() throws Exception {
        return this.mService.getTboxAvpStartStatus();
    }

    @SystemApi
    public void startTboxCertInstall() throws Exception {
        this.mService.startTboxCertInstall();
    }

    @SystemApi
    public void startTboxCertVerify() throws Exception {
        this.mService.startTboxCertVerify();
    }

    @SystemApi
    public String getDvBattMsg() throws Exception {
        return this.mService.getTboxDvBattMsg();
    }

    @SystemApi
    public void setDvTestReq(int req) throws Exception {
        this.mService.setTboxDvTestReq(req);
    }

    @SystemApi
    public void setDvTempSamplingPeriod(int second) throws Exception {
        this.mService.setTboxDvTempSamplingPeriod(second);
    }

    @SystemApi
    public String getDvTempMsg() throws Exception {
        return this.mService.getTboxDvTempMsg();
    }

    @SystemApi
    public void startUpgradingTmcu() throws Exception {
        this.mService.startTboxUpgradingTmcu();
    }

    @SystemApi
    public int getTmcuUpgradingProgress() throws Exception {
        return this.mService.getTboxTmcuUpgradingProgress();
    }

    @SystemApi
    public void startUpgrading4G() throws Exception {
        this.mService.startTboxUpgrading4G();
    }

    @SystemApi
    public int get4GUpgradingProgress() throws Exception {
        return this.mService.getTbox4GUpgradingProgress();
    }

    @SystemApi
    public void setCameraRemoteControlFeedback(String msg) throws Exception {
        this.mService.setTboxCameraRemoteControlFeedback(msg);
    }

    @SystemApi
    public void sendUpgradingTboxByUdiskReq(String msg) throws Exception {
        this.mService.sendUpgradingTboxByUdiskReq(msg);
    }

    @SystemApi
    public String getUpgradingTboxByUdiskResponse() throws Exception {
        return this.mService.getUpgradingTboxByUdiskResponse();
    }

    @SystemApi
    public void setAutoPowerOffConfig(int enable) throws Exception {
        this.mService.setTboxAutoPowerOffConfig(enable);
    }

    @SystemApi
    public int getAutoPowerOffSt() throws Exception {
        return this.mService.getTboxAutoPowerOffSt();
    }

    @SystemApi
    public void setCancelPowerOffConfig(int enable) throws Exception {
        this.mService.setTboxCancelPowerOffConfig(enable);
    }

    @SystemApi
    public int getCancelPowerOffSt() throws Exception {
        return this.mService.getTboxCancelPowerOffSt();
    }

    @SystemApi
    public int[] getPowerOffCountdown() throws Exception {
        return this.mService.getTboxPowerOffCountdown();
    }

    @SystemApi
    public void setSoldierSw(int status) throws Exception {
        this.mService.setTboxSoldierSw(status);
    }

    @SystemApi
    public int getSoldierSwState() throws Exception {
        return this.mService.getTboxSoldierSwState();
    }

    @SystemApi
    public int getSoldierWorkState() throws Exception {
        return this.mService.getTboxSoldierWorkState();
    }

    @SystemApi
    public int[] getSoldierGsensorData() throws Exception {
        return this.mService.getTboxSoldierGsensorData();
    }

    @SystemApi
    public void sendSoldierTickMsg() throws Exception {
        this.mService.sendTboxSoldierTick();
    }

    @SystemApi
    public void sendRemoteDiagInfo(String diagInfo) throws Exception {
        this.mService.sendTboxRemoteDiagInfo(diagInfo);
    }

    @SystemApi
    public void sendGpsAntPowerControlReq(int req) throws Exception {
        this.mService.sendTboxGpsAntPowerControlReq(req);
    }

    @SystemApi
    public int[] getGpsAntPowerControlResponse() throws Exception {
        return this.mService.getGpsAntPowerControlResponse();
    }

    @SystemApi
    public void sendGpsHwResetRequest() throws Exception {
        this.mService.sendTboxGpsHwResetRequest();
    }

    @SystemApi
    public void setGpsPollingType(int type) throws Exception {
        this.mService.setGpsPollingType(type);
    }

    @SystemApi
    public void setGpsDebugSwitch(int enable) throws Exception {
        this.mService.setTboxGpsDebugSwitch(enable);
    }

    @SystemApi
    public void sendGpsMgaRequest() throws Exception {
        this.mService.sendTboxGpsMgaRequest();
    }

    @SystemApi
    public int getGpsMgaResponse() throws Exception {
        return this.mService.getTboxGpsMgaResponse();
    }

    public static String getServiceName() {
        return Car.XP_TBOX_SERVICE;
    }

    @SystemApi
    public void resetTbox() throws Exception {
        this.mService.resetTbox();
    }

    @SystemApi
    public void setRepairMode(int status) throws Exception {
        this.mService.setTboxRepairMode(status);
    }

    @SystemApi
    public int getRepairModeState() throws Exception {
        return this.mService.getTboxRepairModeState();
    }

    @SystemApi
    public void setTboxSoliderCameraSwitch(int onOff) throws Exception {
        this.mService.setTboxSoliderCameraSwitch(onOff);
    }

    @SystemApi
    public int getTboxSoliderCameraState() throws Exception {
        return this.mService.getTboxSoliderCameraState();
    }

    @SystemApi
    public void setTboxThresholdSwitch(int highLevel, int middleLevel, int lowLevel) throws Exception {
        this.mService.setTboxThresholdSwitch(highLevel, middleLevel, lowLevel);
    }

    @SystemApi
    public int getSoliderEnableState() throws Exception {
        return this.mService.getTboxSoliderEnableState();
    }

    @SystemApi
    public void sendRenewalPartsRequest(String req) throws Exception {
        this.mService.sendTboxRenewalPartsRequest(req);
    }

    @SystemApi
    public String getRenewalPartsResponse() throws Exception {
        return this.mService.getTboxRenewalPartsResponse();
    }

    @SystemApi
    public void sendMultiBleRenewalRequest(String req) throws Exception {
        this.mService.sendTboxMultiBleRenewalRequest(req);
    }

    @SystemApi
    public String getMultiBleRenewalResponse() throws Exception {
        return this.mService.getTboxMultiBleRenewalResponse();
    }

    @SystemApi
    public int getRemoteLluSelfMode() throws Exception {
        return this.mService.getTboxRemoteLluMode();
    }

    @SystemApi
    public void sendBleAccountLoginFeedback(int feedback) throws Exception {
        this.mService.sendTboxBleAccountLoginFeedback(feedback);
    }

    @SystemApi
    public void sendLocationInfo(String location) throws Exception {
        this.mService.sendTboxLocationInfo(location);
    }

    public void setBatteryKeepTempSwitch(int cmd) throws Exception {
        this.mService.setTboxBatteryKeepTempSwitch(cmd);
    }

    public int getRemoteBatteryKeepTempReq() throws Exception {
        return this.mService.getTboxRemoteBatteryKeepTempReq();
    }

    public void setBatteryKeepTempAppointTime(int appointFlag, int appointHour, int appointMin) throws Exception {
        this.mService.setTboxBatteryKeepTempAppointTime(appointFlag, appointHour, appointMin);
    }

    public int[] getBatteryKeepTempAppointTime() throws Exception {
        return this.mService.getTboxBatteryKeepTempAppointTime();
    }

    @SystemApi
    public void setGpsLogSwitch(int enable) throws Exception {
        this.mService.setTboxGpsLogSwitch(enable);
    }

    @SystemApi
    public int getToggleGpsLogSwitchResult() throws Exception {
        return this.mService.getTboxToggleGpsLogSwitchResult();
    }

    @SystemApi
    public void sendUpgradePrepareRequest(String req) throws Exception {
        this.mService.setTboxUpgradePrepareRequest(req);
    }

    @SystemApi
    public String getUpgradePrepareResponse() throws Exception {
        return this.mService.getTboxUpgradePrepareResponse();
    }

    @SystemApi
    public void sendEmergencyWifiBleMessage(String msg) throws Exception {
        this.mService.sendTboxEmergencyWifiBleMessage(msg);
    }

    @SystemApi
    public String getFactoryPreCert() throws Exception {
        return this.mService.getTboxFactoryPreCert();
    }

    @SystemApi
    public void sendFactoryPreCert(String cert) throws Exception {
        this.mService.sendTboxFactoryPreCert(cert);
    }

    @SystemApi
    public String getPigeonNotification() throws Exception {
        return this.mService.getTboxPigeonNotification();
    }

    public int getEcallMuteRequest() throws Exception {
        return this.mService.getTboxEcallMuteRequest();
    }

    public int getEcallState() throws Exception {
        return this.mService.getTboxEcallState();
    }

    public void setRoutingForTbox(int cmd) throws Exception {
        this.mService.sendRoutingForTboxRequest(cmd);
    }

    public int getRoutingForTbox() throws Exception {
        return this.mService.getRoutingForTboxResponse();
    }

    public void setTboxModemCapture(int cmd) throws Exception {
        this.mService.sendTboxModemCaptureRequest(cmd);
    }

    public int getTboxModemCapture() throws Exception {
        return this.mService.getTboxModemCaptureResponse();
    }

    public void sendCopyTboxLogRequest(int type) throws Exception {
        this.mService.sendStartCopyTboxLogRequest(type);
    }

    public int getCopyTboxLogResponse() throws Exception {
        return this.mService.getStartCopyTboxLogResponse();
    }

    public void sendFinishTboxLogRequest() throws Exception {
        this.mService.sendFinishCopyTboxLogRequest();
    }

    public int getFinishTboxLogResponse() throws Exception {
        return this.mService.getFinishCopyTboxLogResponse();
    }

    public void setNitzTimezone(int type, String timezoneArea) throws Exception {
    }

    public int getIOTBusinessType() throws Exception {
        return this.mService.getTboxIOTBusinessType();
    }

    public int getACChargeUnlockST() throws Exception {
        return this.mService.getTboxACChargeUnlockST();
    }

    public void sendTboxOtaWorkingStatus(int status) throws Exception {
        this.mService.sendTboxOtaWorkingStatus(status);
    }

    public void sendTboxWakeOrderRTC(String order) throws Exception {
        this.mService.sendTboxWakeOrderRTC(order);
    }

    public void sendTboxApnTrafficInfo(long apn0_traffic, long apn0_block, long apn1_traffic, long apn1_block) throws Exception {
        this.mService.sendTboxApnTrafficInfo(apn0_traffic, apn0_block, apn1_traffic, apn1_block);
    }

    public String getTboxNetmConfInfo() throws Exception {
        return this.mService.getTboxNetmConfInfo();
    }
}
