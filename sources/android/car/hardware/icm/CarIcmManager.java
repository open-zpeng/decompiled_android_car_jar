package android.car.hardware.icm;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.hardware.CarEcuManager;
import android.car.hardware.XpVehicle.IXpVehicle;
import android.car.xpsharedmemory.IXpSharedMemory;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.ArraySet;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

@SystemApi
/* loaded from: classes.dex */
public final class CarIcmManager extends CarEcuManager {
    public static final int BRAKE_FLUID_LEVEL_LOW = 1;
    public static final int BRAKE_FLUID_LEVEL_NORMAL = 0;
    public static final String CARSETTING_ICM_ALARMVOLUMEL = "setIcmAlarmVolume";
    public static final String CARSETTING_ICM_BIRGHTNESS = "setIcmBrightness";
    public static final String CARSETTING_ICM_HVAC_BLOW_MODE = "setHvacWindBlowMode";
    public static final String CARSETTING_ICM_HVAC_DRIVER_TEMPVALUE = "setHvacTempDriverValue";
    public static final String CARSETTING_ICM_HVAC_FRONT_DEFROST = "setHvacFrontDefrostMode";
    public static final String CARSETTING_ICM_HVAC_SWEEP_WIND = "setSweepWindStatus";
    public static final String CARSETTING_ICM_HVAC_WIND_SPEED = "setHvacWindSpeedLevel";
    public static final String CARSETTING_ICM_RESETMETERMILEAGEA = "resetMeterMileageA";
    public static final String CARSETTING_ICM_RESETMETERMILEAGEB = "resetMeterMileageB";
    public static final String CARSETTING_ICM_SEAT_BLOW_LEVEL = "setBCMSeatBlowLevel";
    public static final String CARSETTING_ICM_SEAT_HEAT_LEVEL = "setBCMSeatHeatLevel";
    public static final String CARSETTING_ICM_TIMEFORMAT = "setIcmTimeFormat";
    public static final String CARSETTING_SPEEDLIMIT_WARNINGSWITCH = "setSpeedLimitWarningSwitch";
    public static final String CARSETTING_SPEEDLIMIT_WARNINGVALUE = "setSpeedLimitWarningValue";
    private static final boolean DBG = false;
    public static final int DISTRACTION_LEVEL_L1 = 1;
    public static final int DISTRACTION_LEVEL_NONE = 0;
    public static final int DMS_MODE_EXTENDED = 1;
    public static final int DMS_MODE_NORMAL = 0;
    public static final int FATIGUE_LEVEL_L1 = 1;
    public static final int FATIGUE_LEVEL_L2 = 2;
    public static final int FATIGUE_LEVEL_NONE = 0;
    public static final int FILE_TRANSFER_FAILURE = 1;
    public static final int FILE_TRANSFER_FINISHED = 0;
    public static final int ICM_ALARM_STATUS_VOLUME_POWER = 2;
    public static final int ICM_ALARM_STATUS_VOLUME_SOFT = 0;
    public static final int ICM_ALARM_STATUS_VOLUME_STANDARD = 1;
    public static final int ICM_ALART_VOLUME_HIGH = 3;
    public static final int ICM_ALART_VOLUME_LOW = 1;
    public static final int ICM_ALART_VOLUME_MEDIUM = 2;
    public static final int ICM_CONNECTED = 1;
    public static final int ICM_DAY_NIGHT_MODE_DAY = 0;
    public static final int ICM_DAY_NIGHT_MODE_NIGHT = 1;
    public static final int ICM_METER_ALARM_VOLUME_POWER = 2;
    public static final int ICM_METER_ALARM_VOLUME_SOFT = 0;
    public static final int ICM_METER_ALARM_VOLUME_STANDARD = 1;
    public static final int ICM_METER_SOUND_STREAM_COMMUNICATION = 0;
    public static final int ICM_METER_SOUND_STREAM_MEDIA = 3;
    public static final int ICM_METER_SOUND_STREAM_SYSTEM = 1;
    public static final int ICM_METER_SOUND_STREAM_VOICE = 4;
    public static final int ICM_METER_TIME_FORMAT_12_HOUR = 1;
    public static final int ICM_METER_TIME_FORMAT_24_HOUR = 0;
    public static final int ICM_NOT_CONNECTED = 0;
    public static final int ICM_ODOMETER_INVALID = 0;
    public static final int ICM_ODOMETER_VALID = 1;
    public static final int ICM_SWITCH_CLOSE = 0;
    public static final int ICM_SWITCH_OPEN = 1;
    public static final int ICM_UPDATE_FAILED_DUE_TO_SOFTWARE_VERSION_MISMATCH = 1;
    public static final int ICM_UPDATE_SUCCESS = 0;
    public static final int ID_ICM_ACCOUNT = 554702363;
    public static final int ID_ICM_ACCOUT_EVENT = 560993804;
    public static final int ID_ICM_ACK_EVENT = 557913610;
    public static final int ID_ICM_ALARM_VOLUME = 557848069;
    public static final int ID_ICM_ALARM_VOLUME_SP = 557848125;
    public static final int ID_ICM_ALLCARD_REFRSH = 554702358;
    public static final int ID_ICM_ALL_LAMP_STATE_INFO = 554702433;
    public static final int ID_ICM_ALL_LAMP_STATE_REQ = 557848160;
    public static final int ID_ICM_BFLWARNING = 557848137;
    public static final int ID_ICM_BRIGHTNESS_SP = 557848124;
    public static final int ID_ICM_BTPHONE_CALL = 554702400;
    public static final int ID_ICM_BT_EVENT = 560993803;
    public static final int ID_ICM_CARSETTING = 554702360;
    public static final int ID_ICM_CONNECTED = 557848078;
    public static final int ID_ICM_CONTROL_EVENT = 560993805;
    public static final int ID_ICM_CRASH_INFO = 554702429;
    public static final int ID_ICM_DAYNIGHT_SWITCH = 557848106;
    public static final int ID_ICM_DIAGNOSIS_INFO = 554702431;
    public static final int ID_ICM_DIST_LVL = 557848122;
    public static final int ID_ICM_DMS_MODE_ST = 557848120;
    public static final int ID_ICM_DRIVER_TEMPERATURE = 559945269;
    public static final int ID_ICM_FACE_INFO = 554702408;
    public static final int ID_ICM_FACTORY_RESET = 557848107;
    public static final int ID_ICM_FATG_LVL = 557848121;
    public static final int ID_ICM_FEEDBACK = 557848142;
    public static final int ID_ICM_FEEDBACK_SIGNAL = 554702352;
    public static final int ID_ICM_INFOCARD_ADD = 554702355;
    public static final int ID_ICM_INFOCARD_REMOVE = 554702357;
    public static final int ID_ICM_INFOCARD_UPDATE = 554702356;
    public static final int ID_ICM_INFOFLOW_MSG = 554702359;
    public static final int ID_ICM_LEFT_MENU_CONTROL = 557848162;
    public static final int ID_ICM_LIGHT_LEVER_ADJ = 557848104;
    public static final int ID_ICM_LOG_COMPRESS = 554702438;
    public static final int ID_ICM_MEDIA_SOURCE = 557848118;
    public static final int ID_ICM_METER_SOUND_STATE = 557913638;
    @Deprecated
    public static final int ID_ICM_MUSIC_INFO = 554702403;
    public static final int ID_ICM_MUSIC_TIME_INFO = 554702404;
    public static final int ID_ICM_NAVIGATION = 560993821;
    public static final int ID_ICM_NAVIGATION_INFO = 560993793;
    public static final int ID_ICM_NEEDNAVI = 554702405;
    public static final int ID_ICM_OSDSHOW = 554702354;
    public static final int ID_ICM_RADIO_TYPE = 557848135;
    public static final int ID_ICM_RAIN_DETEC_SENCFG = 557848165;
    public static final int ID_ICM_RANDIS_DISPLAY_TYPE = 554702444;
    public static final int ID_ICM_RESET_MM_A = 557848115;
    public static final int ID_ICM_RESET_MM_A_SP = 557848129;
    public static final int ID_ICM_RESET_MM_B = 557848116;
    public static final int ID_ICM_RESET_MM_B_SP = 557848130;
    public static final int ID_ICM_RIGHT_MENU_CONTROL = 557848163;
    public static final int ID_ICM_SCREEN_LIGHT = 557848119;
    public static final int ID_ICM_SEND_ACCOUNT_MSG = 560993796;
    public static final int ID_ICM_SEND_BT_MSG = 560993794;
    public static final int ID_ICM_SEND_MODE = 557913703;
    public static final int ID_ICM_SEND_MUSIC_ICM = 560993800;
    public static final int ID_ICM_SEND_NAVIGATION_ICM = 560993793;
    public static final int ID_ICM_SEND_NET_RADIO_ICM = 560993799;
    public static final int ID_ICM_SEND_NOTIFY_MSG = 560993798;
    public static final int ID_ICM_SEND_RADIO_ICM = 560993807;
    public static final int ID_ICM_SEND_ROM_BIN = 560993801;
    public static final int ID_ICM_SEND_WEATHER_ICM = 560993795;
    public static final int ID_ICM_SET_TIME = 557913644;
    public static final int ID_ICM_SOUND_THEME_TYPE = 557848169;
    public static final int ID_ICM_SPEED_LIMIT_WARNING_SWITCH = 557848098;
    public static final int ID_ICM_SPEED_LIMIT_WARNING_SWITCH_SP = 557848127;
    public static final int ID_ICM_SPEED_LIMIT_WARNING_VALUE = 557848109;
    public static final int ID_ICM_SPEED_LIMIT_WARNING_VALUE_SP = 557848126;
    public static final int ID_ICM_SYNC_DAY_NIGHT_MODE = 557848094;
    public static final int ID_ICM_SYNC_SIGNAL = 554702353;
    public static final int ID_ICM_SYNC_TIME = 554702364;
    public static final int ID_ICM_TEMPERATURE = 557848101;
    public static final int ID_ICM_TIME_FORMAT = 557848096;
    public static final int ID_ICM_TIME_FORMAT_SP = 557848123;
    public static final int ID_ICM_TOTAL_ODO_METER = 559945262;
    public static final int ID_ICM_TRIPA = 559945265;
    public static final int ID_ICM_TRIPB = 559945266;
    public static final int ID_ICM_TRIP_SINCE_CHARGING = 559945264;
    public static final int ID_ICM_TRIP_SINCE_IGON = 559945263;
    public static final int ID_ICM_UPDATE_PARTITION_PROGRESS = 554702427;
    public static final int ID_ICM_UPDATE_PARTITION_RESULT = 554702428;
    public static final int ID_ICM_UPDATE_PROGRESS = 557848141;
    public static final int ID_ICM_UPDATE_REQ = 554702410;
    public static final int ID_ICM_UPDATE_RESULT = 557848139;
    public static final int ID_ICM_UPDATE_TRANS = 557848140;
    @Deprecated
    public static final int ID_ICM_USER_SCENARIO_EXIT_DIALOG = 557848171;
    public static final int ID_ICM_USER_SCENARIO_INFO = 557913706;
    public static final int ID_ICM_WEATHER = 554702361;
    public static final int ID_ICM_WHEEL_KEY = 554702362;
    public static final int ID_ICM_WIND_BLOW_LEVEL = 557848095;
    public static final int ID_ICM_WIND_BLOW_MODE = 557848100;
    public static final int ID_ICM_WIND_MODE = 557848097;
    public static final int ID_ICM_WIND_POWER = 557848103;
    public static final int KEYCODE_BACK = 13;
    public static final int KEYCODE_CAR_ENTER = 12;
    public static final int KEYCODE_CAR_LONG_ENTER = 26;
    public static final int KEYCODE_CAR_LONG_MEDIA_NEXT = 25;
    public static final int KEYCODE_CAR_LONG_MEDIA_PREVIOUS = 24;
    public static final int KEYCODE_CAR_LONG_VOLUME_DOWN = 23;
    public static final int KEYCODE_CAR_LONG_VOLUME_UP = 22;
    public static final int KEYCODE_CAR_LT_CUSTOM = 49;
    public static final int KEYCODE_CAR_LT_ENTER = 54;
    public static final int KEYCODE_CAR_LT_MEDIA_NEXT = 53;
    public static final int KEYCODE_CAR_LT_MEDIA_PREVIOUS = 52;
    public static final int KEYCODE_CAR_LT_SPEECH = 48;
    public static final int KEYCODE_CAR_LT_VOLUME_DOWN = 51;
    public static final int KEYCODE_CAR_LT_VOLUME_UP = 50;
    public static final int KEYCODE_CAR_MEDIA_NEXT = 11;
    public static final int KEYCODE_CAR_MEDIA_PREVIOUS = 10;
    public static final int KEYCODE_CAR_SLIDE_DOWN = 60;
    public static final int KEYCODE_CAR_SLIDE_UP = 59;
    public static final int KEYCODE_CAR_ST_CUSTOM = 35;
    public static final int KEYCODE_CAR_ST_ENTER = 40;
    public static final int KEYCODE_CAR_ST_MEDIA_NEXT = 39;
    public static final int KEYCODE_CAR_ST_MEDIA_PREVIOUS = 38;
    public static final int KEYCODE_CAR_ST_SPEECH = 34;
    public static final int KEYCODE_CAR_ST_VOLUME_DOWN = 37;
    public static final int KEYCODE_CAR_ST_VOLUME_UP = 36;
    public static final int KEYCODE_CAR_VOLUME_DOWN = 9;
    public static final int KEYCODE_CAR_VOLUME_UP = 8;
    public static final int KEYCODE_CUSTOM = 7;
    public static final int KEYCODE_ICM_DOWN = 2;
    public static final int KEYCODE_ICM_ENTER = 5;
    public static final int KEYCODE_ICM_LEFT = 3;
    public static final int KEYCODE_ICM_LONG_DOWN = 16;
    public static final int KEYCODE_ICM_LONG_ENTER = 19;
    public static final int KEYCODE_ICM_LONG_LEFT = 17;
    public static final int KEYCODE_ICM_LONG_RIGHT = 18;
    public static final int KEYCODE_ICM_LONG_UP = 15;
    public static final int KEYCODE_ICM_LT_DOWN = 44;
    public static final int KEYCODE_ICM_LT_ENTER = 47;
    public static final int KEYCODE_ICM_LT_LEFT = 45;
    public static final int KEYCODE_ICM_LT_RIGHT = 46;
    public static final int KEYCODE_ICM_LT_UP = 43;
    public static final int KEYCODE_ICM_RIGHT = 4;
    public static final int KEYCODE_ICM_SLIDE_DOWN = 58;
    public static final int KEYCODE_ICM_SLIDE_UP = 57;
    public static final int KEYCODE_ICM_ST_DOWN = 30;
    public static final int KEYCODE_ICM_ST_ENTER = 33;
    public static final int KEYCODE_ICM_ST_LEFT = 31;
    public static final int KEYCODE_ICM_ST_RIGHT = 32;
    public static final int KEYCODE_ICM_ST_UP = 29;
    public static final int KEYCODE_ICM_UP = 1;
    public static final int KEYCODE_LONG_BACK = 27;
    public static final int KEYCODE_LONG_CUSTOM = 21;
    public static final int KEYCODE_LONG_MUTE = 28;
    public static final int KEYCODE_LONG_SPEECH = 20;
    public static final int KEYCODE_LT_BACK = 55;
    public static final int KEYCODE_LT_MUTE = 56;
    public static final int KEYCODE_MUTE = 14;
    public static final int KEYCODE_SPEECH = 6;
    public static final int KEYCODE_ST_BACK = 41;
    public static final int KEYCODE_ST_MUTE = 42;
    public static final int LOCAL_RADIO = 1;
    public static final int MAX_NAVI_BMP_SIZE = 5242880;
    public static final String MSG_CONTENT_CONNECT = "Connect";
    public static final String MSG_CONTENT_DIALOUT = "Dialout";
    public static final String MSG_CONTENT_DISCONNECT = "Disconnect";
    public static final String MSG_CONTENT_NEXT = "Next";
    public static final String MSG_CONTENT_OPEN = "Open";
    public static final String MSG_CONTENT_PAUSE = "Pause";
    public static final String MSG_CONTENT_PLAY = "Play";
    public static final String MSG_NONE = "None";
    public static final String MSG_REQCARDSBACK = "reqCardsBack";
    public static final int NAVBMPPAGESIZE = 200000;
    public static final int NET_RADIO = 2;
    public static final int NONE_RADIO = 3;
    public static final String OSDMODE_MEDIAVOLUME = "Media Volume";
    public static final String OSDMODE_PHONEVOLUME = "Phone Volume";
    public static final String OSDMODE_VOICEVOLUME = "Voice Volume";
    public static final String SYNCMODE_ANIMATIONREADY = "AnimReady";
    public static final String SYNCMODE_DAYMODE = "DayMode";
    public static final String SYNCMODE_NIGHTMODE = "NightMode";
    public static final String SYNCMODE_OTA = "OTA";
    public static final String SYNCMODE_SYSTEMREADY = "SysReady";
    private static final String TAG = "CarIcmManager";
    private final ArraySet<Integer> mIcmPropertyIds;
    private final IXpVehicle mService;
    private final IXpSharedMemory mSharedMemoryService;

    /* loaded from: classes.dex */
    public interface CarIcmEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarIcmManager(Car car, IBinder service, IBinder vehicleService, IBinder sharedMemoryService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mIcmPropertyIds = new ArraySet<>(Arrays.asList(557848115, 557848116, 557848069, 557848096, 557848101, 557848103, 557848097, 557848118, 557848119, 560993821, 557848106, 557848098, 557848109, 557848100, 557848095, 559945269, 557913638, 560993795, 560993793, 560993800, 560993799, 560993807, 560993794, 557913644, 557913610, 560993803, 560993804, 560993805, 559945266, 559945265, 559945262, 559945264, 559945263, 557848104, 560993798, 557848107, 560993796, 557848078, 560993801, 554702352, 554702353, 554702354, 554702355, 554702356, 554702357, 554702358, 554702359, 554702360, 554702361, 554702362, 554702363, 554702364, 560993821, 560993793, 557848124, 557848122, 557848121, 557848120, 557848123, 557848125, 557848127, 557848126, 557848129, 557848130, 554702405, 557848135, 554702408, 557848137, 554702410, 557848140, 557848141, 557848139, 557848142, 557848094, 554702427, 554702428, 554702429, 554702431, 557848160, 554702433, 557848162, 557848163, 557848165, 554702438, 557913703, 557848169, 554702444));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
        this.mSharedMemoryService = IXpSharedMemory.Stub.asInterface(sharedMemoryService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mIcmPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_ICM_SERVICE;
    }

    public void setIcmTemperature(int status) throws Exception {
        this.mService.setIcmTemperature(status);
    }

    public int getIcmTemperature() throws Exception {
        return this.mService.getIcmTemperature();
    }

    public void setIcmWindPower(int status) throws Exception {
        this.mService.setIcmWindPower(status);
    }

    public int getIcmWindPower() throws Exception {
        return this.mService.getIcmWindPower();
    }

    public void setIcmWindMode(int status) throws Exception {
        this.mService.setIcmWindMode(status);
    }

    public int getIcmWindMode() throws Exception {
        return this.mService.getIcmWindMode();
    }

    public void setIcmMediaSource(int status) throws Exception {
        this.mService.setIcmMediaSource(status);
    }

    public int getIcmMediaSource() throws Exception {
        return this.mService.getIcmMediaSource();
    }

    public void setIcmScreenLight(int status) throws Exception {
        this.mService.setIcmScreenLight(status);
    }

    public int getIcmScreenLight() throws Exception {
        return this.mService.getIcmScreenLight();
    }

    private void setIcmNavigation(int status) throws Exception {
        this.mService.setIcmNavigation(status);
    }

    private int getIcmNavigation() throws Exception {
        return this.mService.getIcmNavigation();
    }

    public void setIcmDayNightSwitch(int status) throws Exception {
        this.mService.setIcmDayNightSwitch(status);
    }

    public int getIcmDayNightSwitch() throws Exception {
        return this.mService.getIcmDayNightSwitch();
    }

    public void setIcmDayNightMode(int mode) throws Exception {
        this.mService.setIcmDayNightMode(mode);
    }

    public void setIcmWindBlowMode(int mode) throws Exception {
        this.mService.setIcmWindBlowMode(mode);
    }

    public int getIcmWindBlowMode() throws Exception {
        return this.mService.getIcmWindBlowMode();
    }

    public void setIcmWindLevel(int level) throws Exception {
        this.mService.setIcmWindLevel(level);
    }

    public int getIcmWindLevel() throws Exception {
        return this.mService.getIcmWindLevel();
    }

    public void setIcmDriverTempValue(float value) throws Exception {
        this.mService.setIcmDriverTempValue(value);
    }

    public float getIcmDriverTempValue() throws Exception {
        return this.mService.getIcmDriverTempValue();
    }

    public void setMeterSoundState(int type, int volume, int mute) throws Exception {
        this.mService.setMeterSoundState(type, volume, mute);
    }

    public void sendContacts(byte[] json) throws Exception {
        this.mService.sendContacts(json);
    }

    public void setWeatherInfo(byte[] json) throws Exception {
        this.mService.setWeatherInfo(json);
    }

    public void setNavigationInfo(byte[] json) throws Exception {
        this.mService.setNavigationInfo(json);
    }

    public void setMusicInfo(byte[] json, byte[] image) throws Exception {
        sendData(560993800, json, image);
    }

    private void sendData(int prop, byte[] data1, byte[] data2) throws Exception {
        try {
            ParcelFileDescriptor pfd = ParcelFileDescriptor.fromData(data1, data2, null);
            if (pfd == null) {
                Log.e(TAG, "Cannot fetch pfd");
            } else {
                int dataLength2 = 0;
                int dataLength1 = data1 == null ? 0 : data1.length;
                if (data2 != null) {
                    dataLength2 = data2.length;
                }
                this.mSharedMemoryService.queueMemoryBuffer(prop, pfd, dataLength1 + 4 + dataLength2);
            }
            if (pfd != null) {
                $closeResource(null, pfd);
            }
        } catch (Exception e) {
            Log.e(TAG, "send data using shared memory with exception" + e.toString());
            throw new IllegalStateException(e);
        }
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 == null) {
            x1.close();
            return;
        }
        try {
            x1.close();
        } catch (Throwable th) {
            x0.addSuppressed(th);
        }
    }

    public void setNetRadioInfo(byte[] json, byte[] image) throws Exception {
        sendData(560993799, json, image);
    }

    public void setRadioInfo(byte[] json) throws Exception {
        this.mService.setRadioInfo(json);
    }

    public void setBtMusicState(byte[] json) throws Exception {
        this.mService.setBtMusicState(json);
    }

    public void setIcmSystemTimeValue(int hour, int minutes) throws Exception {
        this.mService.setIcmSystemTimeValue(hour, minutes);
    }

    private byte[] intToByteArray(int intData) {
        byte[] byteArray = {(byte) (intData & 255), (byte) ((intData >> 8) & 255), (byte) ((intData >> 16) & 255), (byte) ((intData >> 24) & 255)};
        return byteArray;
    }

    private byte[] byteMerge(byte[] byte_1, byte[] byte_2) {
        if (byte_1 == null && byte_2 == null) {
            return null;
        }
        if (byte_1 == null) {
            byte[] byte1_length = intToByteArray(0);
            byte[] byteArray = new byte[byte_2.length + byte1_length.length];
            System.arraycopy(byte1_length, 0, byteArray, 0, byte1_length.length);
            System.arraycopy(byte_2, 0, byteArray, byte1_length.length, byte_2.length);
            return byteArray;
        } else if (byte_2 == null) {
            byte[] byte1_length2 = intToByteArray(byte_1.length);
            byte[] byteArray2 = new byte[byte_1.length + byte1_length2.length];
            System.arraycopy(byte1_length2, 0, byteArray2, 0, byte1_length2.length);
            System.arraycopy(byte_1, 0, byteArray2, byte1_length2.length, byte_1.length);
            return byteArray2;
        } else {
            byte[] byte1_length3 = intToByteArray(byte_1.length);
            byte[] byteArray3 = new byte[byte_1.length + byte_2.length + byte1_length3.length];
            System.arraycopy(byte1_length3, 0, byteArray3, 0, byte1_length3.length);
            System.arraycopy(byte_1, 0, byteArray3, byte1_length3.length, byte_1.length);
            System.arraycopy(byte_2, 0, byteArray3, byte1_length3.length + byte_1.length, byte_2.length);
            return byteArray3;
        }
    }

    @Deprecated
    public void setMeterBackLightLevel(int level) throws Exception {
        this.mService.setMeterBackLightLevel(level);
    }

    public void setSpeechStateInfo(byte[] info) throws Exception {
        this.mService.setSpeechStateInfo(info);
    }

    public int getIcmConnectionState() throws Exception {
        return this.mService.getIcmConnectionState();
    }

    public void sendRomBinMsg(int rpcNum, byte[] bjson, byte[] bin) throws Exception {
        this.mService.sendRomBinMsg(rpcNum, bjson, bin);
    }

    public void setNotifyMessage(byte[] info) throws Exception {
        this.mService.setNotifyMessage(info);
    }

    public float getMeterMileageA() throws Exception {
        return this.mService.getMeterMileageA();
    }

    public float getMeterMileageB() throws Exception {
        return this.mService.getMeterMileageB();
    }

    public float getDriveTotalMileage() throws Exception {
        return this.mService.getDriveTotalMileage();
    }

    public float getLastChargeMileage() throws Exception {
        return this.mService.getLastChargeMileage();
    }

    public float getLastStartUpMileage() throws Exception {
        return this.mService.getLastStartUpMileage();
    }

    public void setIcmDmsMode(int mode) throws Exception {
        this.mService.setIcmDmsMode(mode);
    }

    public void setIcmFatigueLevel(int level) throws Exception {
        this.mService.setIcmFatigueLevel(level);
    }

    public void setIcmDistractionLevel(int level) throws Exception {
        this.mService.setIcmDistractionLevel(level);
    }

    /* loaded from: classes.dex */
    public class FeedBack_Msg {
        public String back_msgId;
        public String msgType;
        public String result;

        public FeedBack_Msg() {
        }
    }

    /* loaded from: classes.dex */
    public class SyncSinal {
        public String SyncMode;
        public int SyncProgress;
        public String msgId;

        public SyncSinal() {
        }
    }

    public void setIcmSyncSignal(String mSyncSinal) throws Exception {
        this.mService.setIcmSyncSignal(mSyncSinal);
    }

    /* loaded from: classes.dex */
    public class OsdShow {
        public String OSDMode;
        public int OSDValue;

        public OsdShow() {
        }
    }

    public void setIcmOsdShow(String mOsdShow) throws Exception {
        this.mService.setIcmOsdShow(mOsdShow);
    }

    /* loaded from: classes.dex */
    public class InfoCard_A1 {
        public Object cardContent;
        public String cardId;
        public String cardType;
        public boolean hasPic;
        public String mainContent;
        public String picture;
        public String sContent1;
        public String sContent2;
        public String subContent;
        public String title;

        public InfoCard_A1() {
        }
    }

    /* loaded from: classes.dex */
    public class InfoCard_03 {
        public InfoCard_A1 card;
        public String msgId;

        public InfoCard_03() {
        }
    }

    /* loaded from: classes.dex */
    public class InfoCard_msgData {
        public String cardId;
        public String msgCmd;
        public String msgContent;

        public InfoCard_msgData() {
        }
    }

    /* loaded from: classes.dex */
    public class InfoCard_05_cmd {
        public InfoCard_msgData msgData;
        public String msgId;
        public String msgType;

        public InfoCard_05_cmd() {
        }
    }

    /* loaded from: classes.dex */
    public class InfoCard_07_msgList {
        public String Action0;
        public String Action1;
        public String Action2;

        public InfoCard_07_msgList() {
        }
    }

    /* loaded from: classes.dex */
    public class InfoCard_06 {
        public InfoCard_A1 card;
        public int seqNum;

        public InfoCard_06() {
        }
    }

    /* loaded from: classes.dex */
    public class InfoCard_04 {
        public InfoCard_06[] cardList;
        public int cardListCount;
        public int cardListNum;
        public int cardTotalNum;

        public InfoCard_04() {
        }
    }

    /* loaded from: classes.dex */
    public class InfoCard_C01 {
        public InfoCard_07_msgList msgList;
        public int progress;
        public int totalTime;

        public InfoCard_C01() {
        }
    }

    /* loaded from: classes.dex */
    public class InfoCard_C02 {
        public InfoCard_07_msgList msgList;

        public InfoCard_C02() {
        }
    }

    public void setIcmInfoCardAdd(String card) throws Exception {
        this.mService.setIcmInfoCardAdd(card);
    }

    public void setIcmInfoCardUpdate(String card) throws Exception {
        this.mService.setIcmInfoCardUpdate(card);
    }

    public void setIcmInfoCardRemove(String card) throws Exception {
        this.mService.setIcmInfoCardRemove(card);
    }

    public void setIcmAllCardsRefresh(String cards) throws Exception {
        this.mService.setIcmAllCardsRefresh(cards);
    }

    public void setIcmInfoFlowMsg(String msg) throws Exception {
        this.mService.setIcmInfoFlowMsg(msg);
    }

    /* loaded from: classes.dex */
    public class CarCtl_msgData {
        public String msgCmd;
        public float param;

        public CarCtl_msgData() {
        }
    }

    /* loaded from: classes.dex */
    public class CarControl_20 {
        public CarCtl_msgData msgData;
        public String msgId;
        public String msgType;

        public CarControl_20() {
        }
    }

    public void setIcmCarSetting(String cmd) throws Exception {
        this.mService.setIcmCarSetting(cmd);
    }

    public void resetMeterMileageA() throws Exception {
        this.mService.resetIcmMeterMileageA();
    }

    public void resetMeterMileageB() throws Exception {
        this.mService.resetIcmMeterMileageB();
    }

    public void setIcmAlarmVolume(int volumeType) throws Exception {
        this.mService.setIcmAlarmVolume(volumeType);
    }

    public int getIcmAlarmVolume() throws Exception {
        return this.mService.getIcmAlarmVolume();
    }

    public void setIcmTimeFormat(int index) throws Exception {
        this.mService.setIcmTimeFormat(index);
    }

    public int getIcmTimeFormat() throws Exception {
        return this.mService.getIcmTimeFormat();
    }

    public int getIcmBrightness() throws Exception {
        return this.mService.getIcmBrightness();
    }

    public void setIcmBrightness(int level) throws Exception {
        this.mService.setIcmBrightness(level);
    }

    public int getSpeedLimitWarningValue() throws Exception {
        return this.mService.getSpeedLimitWarningValue();
    }

    public void setSpeedLimitWarningValue(int level) throws Exception {
        this.mService.setSpeedLimitWarningValue(level);
    }

    public int getSpeedLimitWarningSwitch() throws Exception {
        return this.mService.getSpeedLimitWarningSwitch();
    }

    public void setSpeedLimitWarningSwitch(int status) throws Exception {
        this.mService.setSpeedLimitWarningSwitch(status);
    }

    public void setRadioType(int type) throws Exception {
        this.mService.setIcmRadioType(type);
    }

    public void sendUpdateRequest(String req) throws Exception {
        this.mService.sendIcmUpdateRequest(req);
    }

    public String getUpdateResponse() throws Exception {
        return this.mService.getIcmUpdateResponse();
    }

    public void setUpdateFileTransferStatus(int status) throws Exception {
        this.mService.setIcmUpdateFileTransferStatus(status);
    }

    public int getUpdateResult() throws Exception {
        return this.mService.getIcmUpdateResult();
    }

    public String getEcuUpdateResult() throws Exception {
        return this.mService.getIcmEcuUpdateResult();
    }

    public int getUpdateProgress() throws Exception {
        return this.mService.getIcmUpdateProgress();
    }

    public String getUpdatingPartitionAndProgress() throws Exception {
        return this.mService.getIcmUpdatingPartitionAndProgress();
    }

    /* loaded from: classes.dex */
    public class Icm_WeatherInfo_21 {
        public String city;
        public String date;
        public float highTemp;
        public float lowTemp;
        public String msgId;
        public float temperature;
        public int timeStamp;
        public int weatherType;

        public Icm_WeatherInfo_21() {
        }
    }

    public void setIcmWeather(String data) throws Exception {
        this.mService.setIcmWeather(data);
    }

    /* loaded from: classes.dex */
    public class Icm_Wheelkey_22 {
        public int wheelKey;

        public Icm_Wheelkey_22() {
        }
    }

    public void setIcmWheelkey(int key) throws Exception {
        this.mService.setIcmWheelkey(key);
    }

    /* loaded from: classes.dex */
    public class IcmAccount_23 {
        public String msgId;
        public String name;

        public IcmAccount_23() {
        }
    }

    public void setIcmAccount(String account) throws Exception {
        this.mService.setIcmAccount(account);
    }

    /* loaded from: classes.dex */
    public class Icm_TimeInfo_24 {
        public int AmOrPm;
        public String Date;
        public String Time;
        public int TimeFormat;
        public String WeekDay;

        public Icm_TimeInfo_24() {
        }
    }

    public void setIcmSyncTime(String tinfo) throws Exception {
        this.mService.setIcmSyncTime(tinfo);
    }

    public void setIcmNavigationBmp(byte[] data) throws Exception {
        if (data == null) {
            Log.w(TAG, "data is null");
            throw new IllegalArgumentException("data is null");
        }
        int length = data.length;
        if (length == 0) {
            Log.w(TAG, "data size is 0");
            throw new IllegalArgumentException("data size is 0");
        } else if (length > 5242880) {
            Log.w(TAG, "data is too large");
            throw new IllegalArgumentException("data is too large");
        } else {
            try {
                ParcelFileDescriptor pfd = ParcelFileDescriptor.fromData(data, null);
                if (pfd == null) {
                    Log.e(TAG, "Cannot fetch pfd");
                } else {
                    this.mSharedMemoryService.queueMemoryBuffer(560993821, pfd, length);
                }
                if (pfd != null) {
                    $closeResource(null, pfd);
                }
            } catch (Exception e) {
                Log.e(TAG, "send the navigation bmp with exception: " + e);
            }
        }
    }

    /* loaded from: classes.dex */
    public class Icm_NaviInfo_30 {
        public String XXX;

        public Icm_NaviInfo_30() {
        }
    }

    public void setIcmNavigationInfo(String navInfo) throws Exception {
        this.mService.setIcmNavigationInfo(navInfo);
    }

    /* loaded from: classes.dex */
    public class Icm_BtPhone_25 {
        public String attribution;
        public boolean hasPic;
        public String name;
        public String operator;
        public String phoneNum;
        public String picture;
        public int status;

        public Icm_BtPhone_25() {
        }
    }

    public void setBtPhoneCall(String info) throws Exception {
        this.mService.setBtPhoneCall(info);
    }

    @Deprecated
    public void setMusicInfo(String musicInfo) throws Exception {
        this.mService.setIcmMusicInfo(musicInfo);
    }

    public void setMusicPlaybackTimeInfo(String timeInfo) throws Exception {
        this.mService.setIcmMusicPlaybackTimeInfo(timeInfo);
    }

    public String getNaviBmpInfoRequiredByIcm() throws Exception {
        return this.mService.getNaviBmpInfoRequiredByIcm();
    }

    public void setFaceInfo(String faceInfo) throws Exception {
        this.mService.setIcmFaceInfo(faceInfo);
    }

    public int getBrakeFluidLevelWarningMessage() throws Exception {
        return this.mService.getIcmBrakeFluidLevelWarningMessage();
    }

    public int getIcmCabinAiFeedback() throws Exception {
        return this.mService.getIcmCabinAiFeedback();
    }

    public String getCrashInfo() throws Exception {
        return this.mService.getIcmCrashInfo();
    }

    public String getDiagnosisInfo() throws Exception {
        return this.mService.getIcmDiagnosisInfo();
    }

    public void requestDashboardLightsStatus() throws Exception {
        this.mService.requestIcmDashboardLightsStatus();
    }

    public String getDashboardLightsStatus() throws Exception {
        return this.mService.getIcmDashboardLightsStatus();
    }

    public void setLeftCard(int index) throws Exception {
        this.mService.setIcmLeftCard(index);
    }

    public void setRightCard(int index) throws Exception {
        this.mService.setIcmRightCard(index);
    }

    public void setModeInfo(int name, int status) throws Exception {
        this.mService.setIcmModeInfoArray(name, status);
    }

    public void setUserScenarioInfo(int[] info) throws Exception {
        if (info == null || info.length != 3) {
            throw new IllegalArgumentException("setUserScenarioModeInfo param count is not 3");
        }
        this.mService.setIcmUserScenarioInfo(info);
    }

    public void setWiperRainDetectSensitivity(int level) throws Exception {
        this.mService.setIcmWiperRainDetectSensitivity(level);
    }

    public void sendLogCompressRequest(String req) throws Exception {
        this.mService.sendIcmLogCompressRequest(req);
    }

    public String getLogCompressInformation() throws Exception {
        return this.mService.getIcmLogCompressInformation();
    }

    public void setSoundThemeType(int type) throws Exception {
        this.mService.setIcmSoundThemeType(type);
    }

    @Deprecated
    public void setUserScenarioExitDialog(int status) throws Exception {
    }

    public void sendRandisDisplayType(String info) throws Exception {
        this.mService.sendIcmRandisDisplayType(info);
    }
}
