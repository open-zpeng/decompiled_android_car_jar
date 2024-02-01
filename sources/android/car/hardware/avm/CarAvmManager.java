package android.car.hardware.avm;

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
public final class CarAvmManager extends CarEcuManager {
    public static final int AVM_STATUS_ALL_ON = 1;
    public static final int AVM_STATUS_DISPLAY_ON = 3;
    public static final int AVM_STATUS_ERROR = 3;
    public static final int AVM_STATUS_INVALID = 2;
    public static final int AVM_STATUS_NO_OPERATION = 255;
    public static final int AVM_STATUS_OFF = 0;
    public static final int AVM_STATUS_ON = 1;
    public static final int AVM_STATUS_SAS_NO_CALIBRATION = 2;
    public static final int AVM_STATUS_SYSTEM_ERROR = 2;
    public static final int CAMERA_ANGLE_START_UP = 360;
    public static final int CAMERA_ANGLE_START_UP_D2 = 180;
    @SystemApi
    public static final int CAMERA_CALIBRATION_CHANGECAMERA_CHART = 5;
    @SystemApi
    public static final int CAMERA_CALIBRATION_CHANGECAMERA_ONROAD = 6;
    @SystemApi
    public static final int CAMERA_CALIBRATION_OFF = 1;
    @SystemApi
    public static final int CAMERA_CALIBRATION_ONEKEY = 2;
    @SystemApi
    public static final int CAMERA_CALIBRATION_ONLOAD_CHART = 3;
    @SystemApi
    public static final int CAMERA_CALIBRATION_ONLOAD_ONROAD = 4;
    @SystemApi
    public static final int CAMERA_CALIBRATION_STATUS_FAILED = 2;
    @SystemApi
    public static final int CAMERA_CALIBRATION_STATUS_SUCCESS = 0;
    @SystemApi
    public static final int CAMERA_CALIBRATION_STAUTS_IN_PROCESS = 1;
    public static final int CAMERA_CHANNEL_360_OR_BACK = 1;
    public static final int CAMERA_CHANNEL_TOP = 0;
    public static final int CAMERA_DOWN = 0;
    public static final int CAMERA_FAULT_STATE_BLOCK = 2;
    public static final int CAMERA_FAULT_STATE_FAILED = 1;
    public static final int CAMERA_FAULT_STATE_NORMAL = 0;
    public static final int CAMERA_INDEX_FRONT = 3;
    public static final int CAMERA_INDEX_LEFT = 1;
    public static final int CAMERA_INDEX_REAR = 2;
    public static final int CAMERA_INDEX_RIGHT = 0;
    public static final int CAMERA_INVALID = 0;
    @SystemApi
    public static final int CAMERA_STATUS_UNCALIBRATION = 3;
    public static final int CAMERA_TOP_MOVE_STATE_INITALIZE = 2;
    public static final int CAMERA_TOP_MOVE_STATE_NORMAL = 0;
    public static final int CAMERA_TOP_MOVE_STATE_SELF_LEARNING = 1;
    public static final int CAMERA_TOP_STATE_DOWN = 1;
    public static final int CAMERA_TOP_STATE_ERROR = 7;
    public static final int CAMERA_TOP_STATE_FALLING_DOWN = 4;
    public static final int CAMERA_TOP_STATE_RISED_UP = 3;
    public static final int CAMERA_TOP_STATE_RISING_UP = 2;
    public static final int CAMERA_TOP_STATE_ROTATED = 6;
    public static final int CAMERA_TOP_STATE_ROTATING = 5;
    @Deprecated
    public static final int CAMERA_TYPE_2DAVM = 14;
    @Deprecated
    public static final int CAMERA_TYPE_2DAVM_2D_FL_SINGLE = 9;
    public static final int CAMERA_TYPE_2DAVM_2D_FRONT_SINGLE = 0;
    public static final int CAMERA_TYPE_2DAVM_2D_LEFT_SINGLE = 2;
    public static final int CAMERA_TYPE_2DAVM_2D_REAR_SINGLE = 1;
    public static final int CAMERA_TYPE_2DAVM_2D_RIGHT_SINGLE = 3;
    @Deprecated
    public static final int CAMERA_TYPE_2DAVM_2D_RL_SINGLE = 10;
    @Deprecated
    public static final int CAMERA_TYPE_2DAVM_2D_R_L_SINGLE = 11;
    @Deprecated
    public static final int CAMERA_TYPE_2DAVM_FRONT_OVERLOOK = 12;
    public static final int CAMERA_TYPE_2DAVM_LR_IMAGE = 21;
    public static final int CAMERA_TYPE_2DAVM_LR_IMAGE_USS = 20;
    @Deprecated
    public static final int CAMERA_TYPE_2DAVM_REAR_OVERLOOK = 13;
    public static final int CAMERA_TYPE_2D_BIRDVIEW_L = 47;
    public static final int CAMERA_TYPE_2D_FRONT_CAMERA = 16;
    public static final int CAMERA_TYPE_2D_LEFT_CAMERA = 18;
    public static final int CAMERA_TYPE_2D_REAR_CAMERA = 17;
    public static final int CAMERA_TYPE_2D_RIGHT_CAMERA = 19;
    public static final int CAMERA_TYPE_360_DEG_3D = 22;
    public static final int CAMERA_TYPE_3D_FRONT_SINGLE = 4;
    public static final int CAMERA_TYPE_3D_LEFT_SINGLE = 6;
    public static final int CAMERA_TYPE_3D_REAR_SINGLE = 5;
    public static final int CAMERA_TYPE_3D_RIGHT_SINGLE = 7;
    public static final int CAMERA_TYPE_4_CAMERA = 8;
    @Deprecated
    public static final int CAMERA_TYPE_4_CARAMER = 8;
    public static final int CAMERA_TYPE_APA_DEBUG_MODE = 30;
    public static final int CAMERA_TYPE_BEV_2D_FRONT = 11;
    public static final int CAMERA_TYPE_BEV_2D_LEFT = 13;
    public static final int CAMERA_TYPE_BEV_2D_REAR = 12;
    public static final int CAMERA_TYPE_BEV_2D_RIGHT = 14;
    public static final int CAMERA_TYPE_FRONT_L_2D_S = 40;
    public static final int CAMERA_TYPE_HIGH_TEMPERATURE_MODE = 31;
    public static final int CAMERA_TYPE_LEFT_L_2D_S = 42;
    public static final int CAMERA_TYPE_NRA_VIEW_MODE = 10;
    public static final int CAMERA_TYPE_OFF = 15;
    public static final int CAMERA_TYPE_PARALLEL_VERTICAL_PARKING_LEFT = 46;
    public static final int CAMERA_TYPE_PARALLEL_VERTICAL_PARKING_RIGHT = 45;
    public static final int CAMERA_TYPE_REAR_CAMERA_VIEW = 50;
    public static final int CAMERA_TYPE_REAR_L_NO_TRAIL_LINE_2D_S = 49;
    public static final int CAMERA_TYPE_REAR_L_WITH_TRAIL_LINE_2D_S = 41;
    public static final int CAMERA_TYPE_REAR_S_NO_TRAIL_LINE_2D_L = 51;
    public static final int CAMERA_TYPE_RIGHT_L_2D_S = 43;
    public static final int CAMERA_TYPE_ROOF_CAMERA = 44;
    public static final int CAMERA_TYPE_TRANSPARENT_CHASIS_L = 52;
    public static final int CAMERA_UP_90_DEGREE = 1;
    private static final boolean DBG = false;
    public static final int HAS_CAMERA = 2;
    public static final int ID_AVM_360_3D_ANGLE = 557855762;
    @SystemApi
    public static final int ID_AVM_CALIBRATION = 557855746;
    public static final int ID_AVM_CAMERA_ANGLE = 557855753;
    public static final int ID_AVM_CAMERA_CAM_POS = 557855758;
    public static final int ID_AVM_CAMERA_CAM_STATE = 557855756;
    public static final int ID_AVM_CAMERA_FAULT_ST = 557921297;
    @SystemApi
    public static final int ID_AVM_CAMERA_FRONT_RADAR = 560014876;
    public static final int ID_AVM_CAMERA_HEIGHT = 557855755;
    public static final int ID_AVM_CAMERA_ROOF_CAMERA = 557855752;
    @Deprecated
    public static final int ID_AVM_CAMERA_STEER_ROTATE_ANGLE = 559948806;
    @SystemApi
    public static final int ID_AVM_CAMERA_TAIL_RADAR = 560014877;
    public static final int ID_AVM_CAM_MOVE_STATE = 557855754;
    public static final int ID_AVM_DISPLAY_MODE = 557855745;
    @SystemApi
    public static final int ID_AVM_DISPLAY_MODE_MULSIGNAL = 557921295;
    @SystemApi
    public static final int ID_AVM_FINETUNE_MODE = 557855749;
    public static final int ID_AVM_OVERLAY_WORK = 557855747;
    public static final int ID_AVM_TRANSPARENT_CHASISST = 557855748;
    public static final int ID_AVM_TRANS_BODY_SW = 557855763;
    public static final int ID_AVM_WORK_ST = 557855760;
    public static final int NO_CAMERA = 1;
    private static final String TAG = "CarAvmManager";
    private final ArraySet<Integer> mAvmPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarAvmEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarAvmManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mAvmPropertyIds = new ArraySet<>(Arrays.asList(557855753, 557855755, 557855745, 557855752, 560014876, 560014877, 559948806, 557855756, 557855758, 557855754, 557855746, 557855747, 557855748, 557855749, 557921295, 557855760, 557921297, 557855762, 557855763));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public static String getServiceName() {
        return Car.XP_AVM_SERVICE;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mAvmPropertyIds;
    }

    public int getCameraAngle() throws Exception {
        return this.mService.getAvmCameraAngle();
    }

    public void setCameraAngle(int targetAngle) throws Exception {
        this.mService.setAvmCameraAngle(targetAngle);
    }

    public void setCameraHeight(int status) throws Exception {
        this.mService.setAvmRoofCameraRaise(status);
    }

    public int getCameraDisplayMode() throws Exception {
        return this.mService.getAvmCameraDisplayMode();
    }

    public void setCameraDisplayMode(int type) throws Exception {
        this.mService.setAvmCameraDisplayMode(type);
    }

    public final int getHasRoofCamera() throws Exception {
        return this.mService.hasRoofCamera();
    }

    @SystemApi
    public float[] getFrontRadarData() throws Exception {
        return this.mService.getFrontRadarData();
    }

    @SystemApi
    public float[] getTailRadarData() throws Exception {
        return this.mService.getTailRadarData();
    }

    @Deprecated
    public float getSteerWheelRotationAngle() throws Exception {
        return this.mService.getSteerWheelRotationAngle();
    }

    public int getRoofCameraState() throws Exception {
        return this.mService.getAvmRoofCameraState();
    }

    public int getRoofCameraPosition() throws Exception {
        return this.mService.getAvmRoofCameraPosition();
    }

    public int getRoofMoveCameraState() throws Exception {
        return this.mService.getAvmCameraInitState();
    }

    @SystemApi
    public void setAvmCalibration(int type) throws Exception {
        this.mService.setAvmCalibrationMode(type);
    }

    @SystemApi
    public int getAvmCalibrationStatus() throws Exception {
        return this.mService.getAvmCalibrationMode();
    }

    public int getOverlayWorkSt() throws Exception {
        return this.mService.getAvmOverlayWorkSt();
    }

    public void setOverlayWorkSt(int status) throws Exception {
        this.mService.setAvmOverlayWorkSt(status);
    }

    public int getTransparentChassis() throws Exception {
        return this.mService.getAvmTransparentChassisState();
    }

    public void setTransparentChassis(int status) throws Exception {
        this.mService.setAvmTransparentChassisState(status);
    }

    @SystemApi
    public void setFineTuneMode(int status) throws Exception {
        this.mService.setAvmFineTuneMode(status);
    }

    @SystemApi
    public void setMultipleDisplayProperties(int displayMode, int calibration, int overlayWorkSt, int transparentChassisWorkSt, int fineTuneMode) throws Exception {
        this.mService.setAvmMultipleDisplayProperties(displayMode, calibration, overlayWorkSt, transparentChassisWorkSt, fineTuneMode);
    }

    public int getAvmWorkState() throws Exception {
        return this.mService.getAvmWorkState();
    }

    public int[] getAvmCamerasFaultStates() throws Exception {
        return this.mService.getAvmCamerasFaultStates();
    }

    @SystemApi
    public void set3603dAngle(int angle) throws Exception {
        this.mService.setAvm3603dAngle(angle);
    }

    public int get3603dAngle() throws Exception {
        return this.mService.getAvm3603dAngle();
    }

    @SystemApi
    public void setTransBodySwitchStatus(int status) throws Exception {
        this.mService.setAvmTransBodySwitchStatus(status);
    }

    public int getTransBodySwitchStatus() throws Exception {
        return this.mService.getAvmTransBodySwitchStatus();
    }
}
