package android.car.hardware.input;

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
public class CarInputManager extends CarEcuManager {
    public static final int ADJ_DOWN = 1;
    public static final int ADJ_UP = 0;
    public static final int AUTO_MODE = 3;
    public static final int DAY_MODE = 1;
    private static final boolean DBG = false;
    public static final int DOUBLE_CLICK = 3;
    public static final int DRIVING_RECORD_PHOTO = 2;
    public static final int DRIVING_RECORD_VIDEO = 3;
    public static final int FACE_FOOT_MODE = 2;
    public static final int FACE_MODE = 1;
    public static final int FDEFROST_MODE = 5;
    public static final int FOOT_DEFROST_MODE = 4;
    public static final int FOOT_MODE = 3;
    public static final int HAS_REQUEST = 1;
    public static final int ID_AM_REQUEST = 557851170;
    public static final int ID_BACK_LIGHT_ADJ_REQUEST = 557916704;
    public static final int ID_BT_MUSIC_REQUEST = 557851172;
    public static final int ID_DAY_NIGHT_MODE_REQUEST = 557851162;
    public static final int ID_FM_REQUEST = 557851171;
    public static final int ID_ICM_USER_DEF_REQUEST = 557851164;
    public static final int ID_KNOB_TALKING_BOOK_REQUSET = 557851178;
    public static final int ID_LOCAL_MUSIC_REQUEST = 557851174;
    public static final int ID_NAVI_REQUEST = 557851161;
    public static final int ID_SWS_BUTTON_RAWDATA = 557916716;
    public static final int ID_SWS_LL_BUTTON_RAW = 557851186;
    public static final int ID_SWS_LL_RR_BUTTON_RAW_NOT_PRESS = 0;
    public static final int ID_SWS_LL_RR_BUTTON_RAW_PRESS = 1;
    public static final int ID_SWS_LR_BUTTON_RAW = 557851187;
    public static final int ID_SWS_RL_BUTTON_RAW = 557851188;
    public static final int ID_SWS_RR_BUTTON_RAW = 557851189;
    public static final int ID_TEMP_ADJ_REQUEST = 557916705;
    public static final int ID_UPLOAD_CAN_LOG_REQUEST = 557851176;
    public static final int ID_USB_MUSIC_REQUEST = 557851177;
    public static final int ID_VOICE_RESET_REQUEST = 557851179;
    public static final int ID_WEB_RADIO_REQUEST = 557851173;
    public static final int ID_WIND_EXIT_MODE_REQUEST = 557851163;
    public static final int ID_WIND_SPD_ADJ_REQUEST = 557916703;
    public static final int KNOB_TWIRL = 4;
    public static final int LONG_CLICK = 2;
    public static final int NAVI_COMPANY = 2;
    public static final int NAVI_HOME = 1;
    public static final int NIGHT_MODE = 2;
    public static final int NO_REQUEST = 0;
    public static final int SHORT_CLICK = 1;
    private static final String TAG = "CarInputManager";
    public static final int TOP_CAMERA_PHOTO = 1;
    private final ArraySet<Integer> mInputPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarInputEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarInputManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mInputPropertyIds = new ArraySet<>(Arrays.asList(557916703, 557916704, 557916705, 557851170, 557851171, 557851172, 557851173, 557851174, 557851161, 557851162, 557851163, 557851164, 557851177, 557851178, 557851179, 557851176, 557916716, 557851186, 557851187, 557851188, 557851189));
        this.mService = IXpVehicle.Stub.asInterface(service);
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mInputPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_INPUT_SERVICE;
    }

    public int[] getSwsButtonsRawData() throws Exception {
        return this.mService.getSwsButtonsRawData();
    }
}
