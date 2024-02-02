package android.car.hardware.ciu;

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
public final class CarCiuManager extends CarEcuManager {
    public static final int AUTO_LOCK_ST_FAIL = 3;
    @Deprecated
    public static final int AUTO_LOCK_ST_NO_REQUES = 0;
    public static final int AUTO_LOCK_ST_NO_REQUEST = 0;
    public static final int AUTO_LOCK_ST_PROCESS = 1;
    public static final int AUTO_LOCK_ST_SUCESS = 2;
    public static final int CAR_WASH_OFF = 0;
    public static final int CAR_WASH_ON = 1;
    public static final int CAR_WASH_STATUS_OFF = 0;
    public static final int CAR_WASH_STATUS_ON = 1;
    public static final int CDU_LIGHT_CHANGE = 1;
    public static final int CDU_LIGHT_NO_CHANGE = 0;
    public static final int CIU_ACTIVATE_DOING = 1;
    public static final int CIU_ACTIVATE_FAIL = 2;
    public static final int CIU_ACTIVATE_NONE = 0;
    public static final int CIU_ACTIVATE_SUCCESS = 3;
    public static final int CIU_DELAY_OFF_CONFIGURE = 2;
    public static final int CIU_DELAY_OFF_NONE = 0;
    public static final int CIU_DELAY_OFF_UPLOAD = 1;
    public static final int CIU_INVALID = 0;
    public static final int CIU_MODE_DATA_UPLOAD = 3;
    public static final int CIU_MODE_DELIVERY_CONFIGURATION = 1;
    public static final int CIU_MODE_DELIVERY_CONFIGURATION_SUCCESS = 2;
    public static final int CIU_MODE_NONE = 0;
    public static final int CIU_RAIN_SW_AUTO = 0;
    public static final int CIU_RAIN_SW_LEVEL1 = 1;
    public static final int CIU_RAIN_SW_LEVEL2 = 2;
    public static final int CIU_RAIN_SW_LEVEL3 = 3;
    public static final int CIU_RAIN_SW_LEVEL4 = 4;
    public static final int CIU_VALID = 1;
    private static final boolean DBG = false;
    public static final int DELETE_ALL = 0;
    public static final int DELETE_RESULT_FAIL = 1;
    public static final int DELETE_RESULT_NOT_ACTIVE = 0;
    public static final int DELETE_RESULT_SUCCESS = 2;
    public static final int DELETE_UID = 1;
    public static final int DISTRACTION_OFF = 0;
    public static final int DISTRACTION_ON = 1;
    public static final int DISTRACT_STATUS_FAULT = 3;
    public static final int DISTRACT_STATUS_NOT_ACTIVE = 0;
    public static final int DISTRACT_STATUS_OFF = 2;
    public static final int DISTRACT_STATUS_ON = 1;
    public static final int DMS_MODE_EXTENDED = 1;
    public static final int DMS_MODE_NORMAL = 0;
    public static final int DMS_OFF = 0;
    public static final int DMS_ON = 1;
    public static final int DMS_STATUS_FAULT = 3;
    public static final int DMS_STATUS_NOT_ACTIVE = 0;
    public static final int DMS_STATUS_OFF = 1;
    public static final int DMS_STATUS_ON = 2;
    public static final int DVR_OFF = 0;
    public static final int DVR_ON = 1;
    public static final int DVR_STATUS_FAULT = 1;
    public static final int DVR_STATUS_NORMAL = 0;
    public static final int FACE_ACTION_REQUEST_DOWN = 5;
    public static final int FACE_ACTION_REQUEST_FRONT = 1;
    public static final int FACE_ACTION_REQUEST_LEFT = 2;
    public static final int FACE_ACTION_REQUEST_NO_REQUEST = 0;
    public static final int FACE_ACTION_REQUEST_RIGHT = 3;
    public static final int FACE_ACTION_REQUEST_UP = 4;
    public static final int FACE_ID_ERROR_TYPE_NO_ERROR = 0;
    public static final int FACE_ID_ERROR_TYPE_REPEAT_FACE = 2;
    public static final int FACE_ID_ERROR_TYPE_REPEAT_UID = 1;
    public static final int FACE_ID_MODE_NORMAL = 1;
    public static final int FACE_ID_MODE_PRIMAL = 0;
    public static final int FACE_ID_MODE_XP_PAY = 2;
    public static final int FACE_ID_NO_ACTION = 0;
    public static final int FACE_ID_STATUS_FAIL = 2;
    public static final int FACE_ID_STATUS_OVERTIME = 3;
    public static final int FACE_ID_STATUS_SUCCESS = 1;
    public static final int FACE_ID_SW_STATUS_FAULT = 2;
    public static final int FACE_ID_SW_STATUS_OFF = 0;
    public static final int FACE_ID_SW_STATUS_ON = 1;
    public static final int FACE_NOT_SHIELD = 0;
    public static final int FACE_PRIMAL_NOT_ACTIVE = 0;
    public static final int FACE_SHIELD = 1;
    public static final int FACE_STORAGE_OVERTIME = 3;
    public static final int FACE_STORAGE_PROCESS = 1;
    public static final int FACE_STORAGE_SUCCEED = 2;
    public static final int FATIGUE_OFF = 0;
    public static final int FATIGUE_ON = 1;
    public static final int FATIGUE_STATUS_FAULT = 3;
    public static final int FATIGUE_STATUS_NOT_ACTIVE = 0;
    public static final int FATIGUE_STATUS_OFF = 2;
    public static final int FATIGUE_STATUS_ON = 1;
    public static final int ID_CIU_AUTOLK_ST = 557852703;
    public static final int ID_CIU_CAR_WASH = 557852705;
    public static final int ID_CIU_CONFIG_ACTIVATE = 557852714;
    public static final int ID_CIU_DELAY_OFF = 557852713;
    public static final int ID_CIU_DELETE_FACE_ID = 557852674;
    public static final int ID_CIU_DELIVERY_CONFIG = 557852715;
    public static final int ID_CIU_DISTRACTION_ST = 557852706;
    public static final int ID_CIU_DIST_LVL = 557852710;
    public static final int ID_CIU_DMS_MODE_ST = 557852708;
    public static final int ID_CIU_DMS_SW = 557852687;
    public static final int ID_CIU_DVR_LOCK = 557852694;
    public static final int ID_CIU_DVR_LOCK_FB = 557852686;
    public static final int ID_CIU_DVR_MODE = 557852673;
    public static final int ID_CIU_DVR_STATUS = 557852696;
    public static final int ID_CIU_DVR_SWITCH = 557852711;
    public static final int ID_CIU_ERROR_TYPE = 557852695;
    public static final int ID_CIU_FACEACTION = 557852681;
    public static final int ID_CIU_FACE_ID_MODE = 557852688;
    public static final int ID_CIU_FACE_ID_PRIMAL_STATUS = 557852684;
    public static final int ID_CIU_FACE_ID_STATUS = 557852683;
    public static final int ID_CIU_FACE_ID_SW = 557852699;
    public static final int ID_CIU_FACE_SHIELD = 557852676;
    public static final int ID_CIU_FATG_LVL = 557852709;
    public static final int ID_CIU_FATIG_ST = 557852707;
    public static final int ID_CIU_FORMAT_SD = 557852701;
    public static final int ID_CIU_LIGHT_CHANGE = 557852712;
    public static final int ID_CIU_LIGHT_INTENSITY = 557852700;
    public static final int ID_CIU_PHOTO_PROCESS = 557852693;
    public static final int ID_CIU_PRIM_FACE_CANCEL = 557852678;
    public static final int ID_CIU_RAIN_SW = 557852704;
    public static final int ID_CIU_REG_HINT = 557852698;
    public static final int ID_CIU_SD_ST = 557852675;
    public static final int ID_CIU_START_REG_FLAG = 557852679;
    public static final int ID_CIU_START_REG_FLOW = 557852677;
    public static final int ID_CIU_STATUS = 557852716;
    public static final int ID_CIU_UID = 557852685;
    public static final int ID_CIU_VALID = 557852702;
    public static final int ID_CIU_VIDEO_OUTPUT = 557852689;
    public static final int SDCARD_STATUS_ABNORMAL = 1;
    public static final int SDCARD_STATUS_NORMAL = 0;
    private static final String TAG = "CarCiuManager";
    private final ArraySet<Integer> mCiuPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarCiuEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarCiuManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mCiuPropertyIds = new ArraySet<>(Arrays.asList(557852673, 557852693, 557852694, 557852696, 557852689, 557852701, 557852675, 557852685, 557852699, 557852688, 557852674, 557852683, 557852684, 557852676, 557852695, 557852700, 557852686, 557852681, 557852678, 557852698, 557852677, 557852679, 557852702, 557852703, 557852687, 557852707, 557852706, 557852704, 557852705, 557852708, 557852710, 557852709, 557852711, 557852713, 557852714, 557852715, 557852716));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mCiuPropertyIds;
    }

    public int getDmsStatus() throws Exception {
        return this.mService.getCiuDmsStatus();
    }

    public void setDmsStatus(int dmsStatus) throws Exception {
        this.mService.setCiuDmsStatus(dmsStatus);
    }

    public void setDmsStatus(int dmsStatus, int faceIdStatus, int fatigueStatus, int distractionStatus) throws Exception {
        this.mService.setMultipleDmsStatus(dmsStatus, faceIdStatus, fatigueStatus, distractionStatus);
    }

    public void setFaceIdMode(int mode) throws Exception {
        this.mService.setCiuFaceIdMode(mode);
    }

    public int getUid() throws Exception {
        return this.mService.getCiuUid();
    }

    public void setUid(int uid) throws Exception {
        this.mService.setCiuUid(uid);
    }

    public int getFaceIdStatus() throws Exception {
        return this.mService.getCiuFaceIdStatus();
    }

    public int getFaceIdPrimalStatus() throws Exception {
        return this.mService.getCiuFaceIdPrimalStatus();
    }

    public int getFaceShield() throws Exception {
        return this.mService.getCiuFaceShieldStatus();
    }

    public int getErrorType() throws Exception {
        return this.mService.getCiuErrorType();
    }

    public int getLightIntensity() throws Exception {
        return this.mService.getCiuLightIntensity();
    }

    public int getFaceIdSw() throws Exception {
        return this.mService.getCiuFaceIDSwitchStatus();
    }

    public void setFaceIdSw(int value) throws Exception {
        this.mService.setCiuFaceIdSwitch(value);
    }

    public void setDeleteFaceId(int delete) throws Exception {
        this.mService.setCiuDeleteFaceId(delete);
    }

    public int getDeleteResult() throws Exception {
        return this.mService.getCiuDeleteFaceIdResult();
    }

    public void setRegHint(int hint) throws Exception {
        this.mService.setCiuRegHint(hint);
    }

    public void setStartRegFlow(int flow) throws Exception {
        this.mService.setCiuStartRegFlow(flow);
    }

    public void setStartRegFlag(int flag) throws Exception {
        this.mService.setCiuStartRegFlag(flag);
    }

    public int getFaceAction() throws Exception {
        return this.mService.getCiuFaceAction();
    }

    public void setFaceActionRequest(int action) throws Exception {
        this.mService.setCiuFaceActionRequest(action);
    }

    public void setFirmFaceCancel(int value) throws Exception {
        this.mService.setCiuFirmFaceCancel(value);
    }

    public void setRegisterRequestMulti(int uid, int faceActionRequest, int faceIdMode) throws Exception {
        this.mService.setCiuRegisterRequestMulti(uid, faceActionRequest, faceIdMode);
    }

    public void setDeleteMulti(int uid, int deleteFaceId) throws Exception {
        this.mService.setCiuDeleteMulti(uid, deleteFaceId);
    }

    public int getCiuValid() throws Exception {
        return this.mService.getCiuValid();
    }

    public int getCiuAutoLockSt() throws Exception {
        return this.mService.getCiuAutoLockSt();
    }

    public int getDvrMode() throws Exception {
        return this.mService.getCiuDvrMode();
    }

    public void setDvrMode(int mode) throws Exception {
        this.mService.setCiuDvrMode(mode);
    }

    public void photoProcess() throws Exception {
        this.mService.setCiuPhotoProcess();
    }

    public void setDvrLockMode(int mode) throws Exception {
        this.mService.setCiuDvrLockMode(mode);
    }

    public void setVideoOutputMode(int mode) throws Exception {
        this.mService.setCiuVideoOutputMode(mode);
    }

    public int getSdStatus() throws Exception {
        return this.mService.getCiuSdStatus();
    }

    public int getDvrStatus() throws Exception {
        return this.mService.getCiuDvrStatus();
    }

    public void setFormatMode(int mode) throws Exception {
        this.mService.setCiuFormatMode(mode);
    }

    public int getDvrFormatStatus() throws Exception {
        return this.mService.getCiuDvrFormatStatus();
    }

    public int getDvrLockFb() throws Exception {
        return this.mService.getCiuDvrLockFb();
    }

    public void setCiuRainSw(int level) throws Exception {
        this.mService.setCiuRainSw(level);
    }

    public int getCiuRainSw() throws Exception {
        return this.mService.getCiuRainSw();
    }

    public int getCiuCarWash() throws Exception {
        return this.mService.getCiuCarWash();
    }

    public void setCiuCarWash(int status) throws Exception {
        this.mService.setCiuCarWash(status);
    }

    public int getDistractionStatus() throws Exception {
        return this.mService.getCiuDistractionStatus();
    }

    public void setDistractionStatus(int status) throws Exception {
        this.mService.setCiuDistractionStatus(status);
    }

    public int getFatigueStatus() throws Exception {
        return this.mService.getCiuFatigueStatus();
    }

    public void setFatigueStatus(int status) throws Exception {
        this.mService.setCiuFatigueStatus(status);
    }

    public void setDmsMode(int mode) throws Exception {
        this.mService.setCiuDmsMode(mode);
    }

    public int getFatigueLevel() throws Exception {
        return this.mService.getCiuFatigueLevel();
    }

    public int getDistractionLevel() throws Exception {
        return this.mService.getCiuDistractionLevel();
    }

    public void setCiuDelayOff(int value) throws Exception {
        this.mService.setCiuDelayOff(value);
    }

    public int getCiuDelayOff() throws Exception {
        return this.mService.getCiuDelayOff();
    }

    public void setCiuDeliveryUploadMode(int mode) throws Exception {
        this.mService.setCiuDeliveryUploadMode(mode);
    }

    public int getCiuDeliveryUploadMode() throws Exception {
        return this.mService.getCiuDeliveryUploadMode();
    }

    public void setCiuConfigurationActive(int version) throws Exception {
        this.mService.setCiuConfigurationActive(version);
    }

    public int getCiuConfigurationActive() throws Exception {
        return this.mService.getCiuConfigurationActive();
    }

    public void setDvrEnable(int enable) throws Exception {
        this.mService.setDvrEnable(enable);
    }

    public int getDvrEnableState() throws Exception {
        return this.mService.getDvrEnableState();
    }

    public static String getServiceName() {
        return Car.XP_CIU_SERVICE;
    }

    public void setNotifyCiuAutoLightStatus(int status) throws Exception {
        this.mService.setNotifyCiuAutoLightStatus(status);
    }

    public int getCiuStatus() throws Exception {
        return this.mService.getCiuStatus();
    }
}
