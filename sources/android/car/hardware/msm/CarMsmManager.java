package android.car.hardware.msm;

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
public final class CarMsmManager extends CarEcuManager {
    public static final int ALL_POS_SET_MEMORY_TYPE_NO_REQ = 0;
    public static final int ALL_POS_SET_MEMORY_TYPE_RECALL_POSITION = 2;
    public static final int ALL_POS_SET_MEMORY_TYPE_SAVE_POSITION = 1;
    private static final boolean DBG = false;
    public static final int HEADREST_STATUS_NORMAL = 0;
    public static final int HEADREST_STATUS_REMOVED = 1;
    @Deprecated
    public static final int ID_BCM_SECROW_LTSEAT_STOP_MOVE_REQ = 557849884;
    public static final int ID_BCM_SECROW_LTSEAT_TILT = 557915418;
    public static final int ID_BCM_SECROW_LTSEAT_TILTPOS = 557849890;
    @Deprecated
    public static final int ID_BCM_SECROW_LTSEAT_UNFOLD_REQ = 557849883;
    @Deprecated
    public static final int ID_BCM_SECROW_RTSEAT_STOP_MOVE_REQ = 557849886;
    public static final int ID_BCM_SECROW_RTSEAT_TILT = 557915421;
    public static final int ID_BCM_SECROW_RTSEAT_TILTPOS = 557849891;
    @Deprecated
    public static final int ID_BCM_SECROW_RTSEAT_UNFOLD_REQ = 557849887;
    public static final int ID_MSMD_ALL_POS_SET = 560995450;
    @Deprecated
    public static final int ID_MSMD_ALL_POS_SET_NEW = 560995596;
    public static final int ID_MSMD_HEADREST_ST = 557849813;
    public static final int ID_MSMD_LUMB_CTRL_SW = 557849973;
    public static final int ID_MSMD_LUM_SW_CENTER = 557849935;
    public static final int ID_MSMD_LUM_SW_MODE = 557849934;
    public static final int ID_MSMD_MASSG_ELEM_CMD = 557915459;
    public static final int ID_MSMD_MASSG_ERR_ST = 557849953;
    public static final int ID_MSMD_MASSG_LVL = 557849915;
    public static final int ID_MSMD_MASSG_PROG = 557849914;
    public static final int ID_MSMD_MCU_WELCOME_ACTIVE = 557849965;
    @Deprecated
    public static final int ID_MSMD_MEMORY_REQ = 557849952;
    public static final int ID_MSMD_POS_SAVE = 560995449;
    @Deprecated
    public static final int ID_MSMD_POS_SAVE_NEW = 560995595;
    @Deprecated
    public static final int ID_MSMD_SEATCUSHEXTPOS = 557849892;
    @Deprecated
    public static final int ID_MSMD_SEATCUSHTILTPOS = 557849893;
    public static final int ID_MSMD_SEATHORZCMD = 557915216;
    public static final int ID_MSMD_SEATHORZPOS = 557849686;
    public static final int ID_MSMD_SEATILTINGPOS = 557849688;
    @Deprecated
    public static final int ID_MSMD_SEATLEGHEIGHTCMD = 557915219;
    public static final int ID_MSMD_SEATLEGHEIGHTPOS = 557849689;
    public static final int ID_MSMD_SEATLUMBHORZCMD = 557915220;
    public static final int ID_MSMD_SEATLUMBVERTICALCMD = 557915221;
    public static final int ID_MSMD_SEATTILTINGCMD = 557915217;
    public static final int ID_MSMD_SEATVERTICALCMD = 557915218;
    public static final int ID_MSMD_SEATVERTICALPOS = 557849687;
    public static final int ID_MSMD_SEAT_CUSHION_EXT = 557915415;
    public static final int ID_MSMD_SEAT_CUSHION_TILT_CMD = 557915219;
    @Deprecated
    public static final int ID_MSMD_SEAT_CUSH_TILT = 557915415;
    public static final int ID_MSMD_SEAT_LEG_HORZ_POS = 557849959;
    public static final int ID_MSMD_SEAT_MOVEST = 557850066;
    public static final int ID_MSMD_SEAT_STOPMOVING = 557849817;
    public static final int ID_MSMD_SEAT_TILT_LEVEL_OFF = 557849878;
    public static final int ID_MSMP_ALL_POS_SET = 557915352;
    public static final int ID_MSMP_HEADREST_ST = 557849814;
    public static final int ID_MSMP_LUMB_CTRL_SW = 557849974;
    public static final int ID_MSMP_LUM_SW_CENTER = 557849937;
    public static final int ID_MSMP_LUM_SW_MODE = 557849936;
    public static final int ID_MSMP_MASSG_ELEM_CMD = 557915460;
    public static final int ID_MSMP_MASSG_ERR_ST = 557849954;
    public static final int ID_MSMP_MASSG_LVL = 557849916;
    public static final int ID_MSMP_MASSG_PROG = 557849917;
    public static final int ID_MSMP_MCU_WELCOME = 557847672;
    public static final int ID_MSMP_MCU_WELCOME_ACTIVE = 557849966;
    public static final int ID_MSMP_POS_SAVE = 557915458;
    @Deprecated
    public static final int ID_MSMP_SEATCUSHEXTPOS = 557849894;
    public static final int ID_MSMP_SEATHORZCMD = 557915228;
    public static final int ID_MSMP_SEATHORZPOS = 557849810;
    public static final int ID_MSMP_SEATILTINGPOS = 557849812;
    public static final int ID_MSMP_SEATLUMBHORZCMD = 557915442;
    public static final int ID_MSMP_SEATLUMBVERTICALCMD = 557915441;
    public static final int ID_MSMP_SEATTILTINGCMD = 557915229;
    public static final int ID_MSMP_SEATVERTICALCMD = 557915230;
    public static final int ID_MSMP_SEATVERTICALPOS = 557849811;
    public static final int ID_MSMP_SEAT_CUSH_EXT = 557915416;
    public static final int ID_MSMP_SEAT_LEG_HORZ_POS = 557849960;
    public static final int ID_MSMP_SEAT_MOVEST = 557850067;
    public static final int ID_MSMP_SEAT_STOPMOVING = 557849818;
    public static final int ID_MSMP_SEAT_TILT_LEVEL_OFF = 557849881;
    public static final int ID_MSMT_ALLPOS_SET = 557915533;
    public static final int ID_MSMT_LTSEAT_HEADMOVEST = 557850061;
    public static final int ID_MSMT_LTSEAT_HEADVERTICALCMD = 557915545;
    public static final int ID_MSMT_LTSEAT_HEADVERTICALPOS = 557850041;
    public static final int ID_MSMT_LTSEAT_TILT = 557915544;
    public static final int ID_MSMT_LTSEAT_TILTPOS = 557850040;
    public static final int ID_MSMT_LTSEAT_TILT_FOLD = 557850025;
    public static final int ID_MSMT_LTSEAT_TILT_ST = 557850046;
    public static final int ID_MSMT_MIDSEAT_HEADMOVEST = 557850062;
    public static final int ID_MSMT_MIDSEAT_HEADVERTICALCMD = 557915581;
    public static final int ID_MSMT_MIDSEAT_HEADVERTICALPOS = 557850044;
    public static final int ID_MSMT_MIDSEAT_TILT = 557850012;
    public static final int ID_MSMT_RTSEAT_HEADMOVEST = 557850063;
    public static final int ID_MSMT_RTSEAT_HEADVERTICALCMD = 557915547;
    public static final int ID_MSMT_RTSEAT_HEADVERTICALPOS = 557850043;
    public static final int ID_MSMT_RTSEAT_TILT = 557915546;
    public static final int ID_MSMT_RTSEAT_TILTPOS = 557850042;
    public static final int ID_MSMT_RTSEAT_TILT_FOLD = 557850026;
    public static final int ID_MSMT_RTSEAT_TILT_ST = 557850047;
    public static final int ID_MSMT_SEAT_STOW = 557850027;
    public static final int ID_MSMT_SEAT_STOW_ST = 557850048;
    public static final int ID_MSM_SECROW_LTSEAT_ALLPOS_SET = 557915531;
    public static final int ID_MSM_SECROW_LTSEAT_ANGLEPOS = 557850031;
    public static final int ID_MSM_SECROW_LTSEAT_EASYENTRY = 557850019;
    public static final int ID_MSM_SECROW_LTSEAT_FUNCST = 557850021;
    public static final int ID_MSM_SECROW_LTSEAT_HEADHORZCMD = 557915538;
    public static final int ID_MSM_SECROW_LTSEAT_HEADHORZPOS = 557850034;
    public static final int ID_MSM_SECROW_LTSEAT_HEADVERTICALCMD = 557915537;
    public static final int ID_MSM_SECROW_LTSEAT_HEADVERTICALPOS = 557850033;
    public static final int ID_MSM_SECROW_LTSEAT_HORZPOS = 557850030;
    public static final int ID_MSM_SECROW_LTSEAT_LEGVERTICALCMD = 557915536;
    public static final int ID_MSM_SECROW_LTSEAT_LEGVERTICALPOS = 557850032;
    public static final int ID_MSM_SECROW_LTSEAT_LUMBHORZCMD = 557915550;
    public static final int ID_MSM_SECROW_LTSEAT_LUMBVERTICALCMD = 557915549;
    public static final int ID_MSM_SECROW_LTSEAT_SEATANGLECMD = 557915535;
    public static final int ID_MSM_SECROW_LTSEAT_SEATHORZCMD = 557915534;
    public static final int ID_MSM_SECROW_LTSEAT_STOPMOVING = 557850028;
    public static final int ID_MSM_SECROW_LTSEAT_ZEROGRAV = 557850017;
    public static final int ID_MSM_SECROW_LTSEA_MOVEST = 557850064;
    public static final int ID_MSM_SECROW_LTSEA_ST = 557850059;
    public static final int ID_MSM_SECROW_RTSEAT_ALLPOS_SET = 557915532;
    public static final int ID_MSM_SECROW_RTSEAT_ANGLEPOS = 557850036;
    public static final int ID_MSM_SECROW_RTSEAT_EASYENTRY = 557850020;
    public static final int ID_MSM_SECROW_RTSEAT_FUNCST = 557850022;
    public static final int ID_MSM_SECROW_RTSEAT_HEADHORZCMD = 557915543;
    public static final int ID_MSM_SECROW_RTSEAT_HEADHORZPOS = 557850039;
    public static final int ID_MSM_SECROW_RTSEAT_HEADVERTICALCMD = 557915542;
    public static final int ID_MSM_SECROW_RTSEAT_HEADVERTICALPOS = 557850038;
    public static final int ID_MSM_SECROW_RTSEAT_HORZPOS = 557850035;
    public static final int ID_MSM_SECROW_RTSEAT_LEGVERTICALCMD = 557915541;
    public static final int ID_MSM_SECROW_RTSEAT_LEGVERTICALPOS = 557850037;
    public static final int ID_MSM_SECROW_RTSEAT_LUMBHORZCMD = 557915552;
    public static final int ID_MSM_SECROW_RTSEAT_LUMBVERTICALCMD = 557915551;
    public static final int ID_MSM_SECROW_RTSEAT_SEATANGLECMD = 557915540;
    public static final int ID_MSM_SECROW_RTSEAT_SEATHORZCMD = 557915539;
    public static final int ID_MSM_SECROW_RTSEAT_STOPMOVING = 557850029;
    public static final int ID_MSM_SECROW_RTSEAT_ZEROGRAV = 557850018;
    public static final int ID_MSM_SECROW_RTSEA_MOVEST = 557850065;
    public static final int ID_MSM_SECROW_RTSEA_ST = 557850060;
    @Deprecated
    public static final int ID_SECROW_LTSEAT_CUSHEXTPOS = 557849908;
    @Deprecated
    public static final int ID_SECROW_LTSEAT_CUSHEXT_REQ = 557915443;
    public static final int ID_SECROW_LTSEAT_LEG_HORZ_POS = 557849961;
    public static final int ID_SECROW_LTSEAT_LEG_HORZ_REQ = 557915499;
    public static final int ID_SECROW_LTSEAT_UNLOCK = 557849912;
    public static final int ID_SECROW_LT_ALL_POS_SET = 557915464;
    public static final int ID_SECROW_LT_MASSG_ELEM_CMD = 557915461;
    public static final int ID_SECROW_LT_MASSG_ERR_ST = 557849955;
    public static final int ID_SECROW_LT_MASSG_LVL = 557849919;
    public static final int ID_SECROW_LT_MASSG_PROG = 557849918;
    @Deprecated
    public static final int ID_SECROW_RTSEAT_CUSHEXTPOS = 557849910;
    @Deprecated
    public static final int ID_SECROW_RTSEAT_CUSHEXT_REQ = 557915445;
    public static final int ID_SECROW_RTSEAT_LEG_HORZ_POS = 557849962;
    public static final int ID_SECROW_RTSEAT_LEG_HORZ_REQ = 557915500;
    public static final int ID_SECROW_RTSEAT_UNLOCK = 557849911;
    public static final int ID_SECROW_RT_ALL_POS_SET = 557915465;
    public static final int ID_SECROW_RT_MASSG_ELEM_CMD = 557915462;
    public static final int ID_SECROW_RT_MASSG_ERR_ST = 557849956;
    public static final int ID_SECROW_RT_MASSG_LVL = 557849921;
    public static final int ID_SECROW_RT_MASSG_PROG = 557849920;
    public static final int LUMBER_SWITCH_MODE_CUSHION_EXTENSION = 1;
    public static final int LUMBER_SWITCH_MODE_LUMBER = 0;
    public static final int LUMB_CTRL_SW_CTRL_DISABLE = 0;
    public static final int LUMB_CTRL_SW_CTRL_ENABLE = 1;
    public static final int MASSG_LEVEL_HIGH_INTENSITY = 3;
    public static final int MASSG_LEVEL_NORMAL_INTENSITY = 2;
    public static final int MASSG_LEVEL_NO_COMMAND = 10;
    @Deprecated
    public static final int MASSG_LEVEL_OFF = 0;
    public static final int MASSG_LEVEL_WEAK_INTENSITY = 1;
    public static final int MASSG_PROG_ELEM_DEFLATION = 2;
    public static final int MASSG_PROG_ELEM_INFLATION = 1;
    public static final int MASSG_PROG_ELEM_MAINTAIN_PRESSURE = 3;
    public static final int MASSG_PROG_ELEM_NO_COMMAND = 0;
    public static final int MASSG_PROG_STATUS_MODE_1 = 1;
    public static final int MASSG_PROG_STATUS_MODE_10 = 10;
    public static final int MASSG_PROG_STATUS_MODE_11 = 11;
    public static final int MASSG_PROG_STATUS_MODE_12 = 12;
    public static final int MASSG_PROG_STATUS_MODE_13 = 13;
    public static final int MASSG_PROG_STATUS_MODE_2 = 2;
    public static final int MASSG_PROG_STATUS_MODE_3 = 3;
    public static final int MASSG_PROG_STATUS_MODE_4 = 4;
    public static final int MASSG_PROG_STATUS_MODE_5 = 5;
    public static final int MASSG_PROG_STATUS_MODE_6 = 6;
    public static final int MASSG_PROG_STATUS_MODE_7 = 7;
    public static final int MASSG_PROG_STATUS_MODE_8 = 8;
    public static final int MASSG_PROG_STATUS_MODE_9 = 9;
    public static final int MASSG_PROG_STATUS_MODE_DIRECT_CONTROL = 14;
    public static final int MASSG_PROG_STATUS_MODE_NO_COMMAND = 15;
    public static final int MASSG_PROG_STATUS_OFF = 0;
    @Deprecated
    public static final int MSMD_ALL_POS_SET_MEMORY_TYPE_NO_REQ = 0;
    @Deprecated
    public static final int MSMD_ALL_POS_SET_MEMORY_TYPE_RECALL_POSITION = 2;
    @Deprecated
    public static final int MSMD_ALL_POS_SET_MEMORY_TYPE_SAVE_POSITION = 1;
    public static final int MSMT_CTRL_FOLD = 1;
    public static final int MSMT_CTRL_STOP = 2;
    public static final int MSMT_CTRL_STOW_ENTER = 1;
    public static final int MSMT_CTRL_STOW_RESTORE = 2;
    public static final int MSMT_CTRL_STOW_STOP = 3;
    public static final int MSMT_CTRL_UNFOLD = 2;
    public static final int MSMT_FOLD_ACTIVATED = 2;
    public static final int MSMT_FOLD_PENDING = 1;
    public static final int MSMT_HEAD_AUTO_ADJ = 2;
    public static final int MSMT_HEAD_INITAL_ADJ = 3;
    public static final int MSMT_HEAD_MANUAL_ADJ = 1;
    public static final int MSMT_HEAD_STOP = 0;
    public static final int MSMT_STOWFUN_STOP = 5;
    public static final int MSMT_STOW_ACTIVATED = 2;
    public static final int MSMT_STOW_PENDING = 1;
    public static final int MSMT_STOW_STATE_FULLY_FOLED = 1;
    public static final int MSMT_STOW_STATE_HALF_STOWED = 2;
    public static final int MSMT_STOW_STATE_STOWED = 3;
    public static final int MSMT_STOW_STATE_UNKNOWN = 0;
    public static final int MSMT_TILT_STATE_FULLY_FOLED = 2;
    public static final int MSMT_TILT_STATE_HALF_FOLED = 1;
    public static final int MSMT_TILT_STATE_UNKNOWN = 0;
    public static final int MSMT_TILT_STOP = 5;
    public static final int MSMT_UNFOLD_ACTIVATED = 4;
    public static final int MSMT_UNFOLD_PENDING = 3;
    public static final int MSMT_UNSTOW_ACTIVATED = 4;
    public static final int MSMT_UNSTOW_PENDING = 3;
    public static final int MSM_CONTROL_PENDING = 2;
    public static final int MSM_CONTROL_START = 1;
    public static final int MSM_CONTROL_STOP = 3;
    public static final int MSM_EASYENTRY_ENTERED = 4;
    public static final int MSM_EASYENTRY_PENDING = 3;
    public static final int MSM_FOLD_INACTIVE = 0;
    public static final int MSM_FRONTSEAT_AVOIDANCE_ADJ = 4;
    public static final int MSM_FUNCTION_INACTIVE = 0;
    public static final int MSM_FUNCTION_STOP = 5;
    public static final int MSM_MCU_WELCOME_ACTIVE = 1;
    public static final int MSM_MCU_WELCOME_INACTIVE = 0;
    public static final int MSM_MODE_CTRL_ENTER = 1;
    public static final int MSM_MODE_CTRL_EXIT = 2;
    public static final int MSM_MODE_CTRL_STOP = 3;
    public static final int MSM_MOVE_BACKWARD = 2;
    public static final int MSM_MOVE_DOWN = 2;
    public static final int MSM_MOVE_FORWARD = 1;
    public static final int MSM_MOVE_UP = 1;
    public static final int MSM_REQUEST_NO_COMMAND = 0;
    public static final int MSM_REQUEST_RECLINE = 1;
    public static final int MSM_SEAT_AUTO_ADJ = 2;
    public static final int MSM_SEAT_AVOIDANCE_ADJ = 6;
    public static final int MSM_SEAT_EASYENTRY_ADJ = 4;
    public static final int MSM_SEAT_INITAL_ADJ = 3;
    public static final int MSM_SEAT_MANUAL_ADJ = 1;
    public static final int MSM_SEAT_STOP = 0;
    public static final int MSM_SEAT_ZEROGRAVITY_ADJ = 5;
    public static final int MSM_STATE_DEFAULT = 3;
    public static final int MSM_STATE_EASY_ENTRY = 2;
    public static final int MSM_STATE_OTHER = 0;
    public static final int MSM_STATE_ZERO_GRAVITY = 1;
    public static final int MSM_STATUS_ERROR = 1;
    public static final int MSM_STATUS_NO_COMMAND = 0;
    public static final int MSM_STATUS_NO_ERROR = 0;
    public static final int MSM_STATUS_OFF = 0;
    public static final int MSM_STATUS_ON = 1;
    public static final int MSM_STATUS_UNLOCK = 1;
    public static final int MSM_STOWFUN_INACTIVE = 0;
    public static final int MSM_ZEROGRAV_ENTERED = 2;
    public static final int MSM_ZEROGRAV_PENDING = 1;
    @Deprecated
    public static final int Msm_BlowOrHeat_Level1 = 1;
    @Deprecated
    public static final int Msm_BlowOrHeat_Level2 = 2;
    @Deprecated
    public static final int Msm_BlowOrHeat_Level3 = 3;
    @Deprecated
    public static final int Msm_Move_Backward = 2;
    @Deprecated
    public static final int Msm_Move_Down = 2;
    @Deprecated
    public static final int Msm_Move_Forward = 1;
    @Deprecated
    public static final int Msm_Move_Up = 1;
    public static final int POSITION_MEMORY_NO_REQUEST_STATUS = 0;
    public static final int POSITION_MEMORY_RECALL_STATUS = 2;
    public static final int POSITION_MEMORY_SAVE_STATUS = 1;
    public static final int SEAT_SECROW_FOLD = 1;
    public static final int SEAT_SECROW_UNFOLD = 2;
    @Deprecated
    public static final int SEAT_TILT_LEVEL_OFF = 1;
    public static final int STOP_SEAT_MOVING = 1;
    public static final int SWITCH_STATUS_INVALID = 2;
    public static final int SWITCH_STATUS_NOT_PRESSED = 0;
    public static final int SWITCH_STATUS_PRESSED = 1;
    private static final String TAG = "CarMsmManager";
    private final ArraySet<Integer> mMsmPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarMsmEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarMsmManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mMsmPropertyIds = new ArraySet<>(Arrays.asList(557915216, 557915217, 557915218, 557915219, 557915220, 557915221, 557849686, 557849687, 557849688, 557849689, 557915228, 557915229, 557915230, 560995450, 560995449, 557849810, 557849811, 557849812, 557849813, 557849814, 557915352, 557849817, 557849818, 560995596, 560995595, 557849878, 557915415, 557915416, 557849881, 557915418, 557915421, 557849890, 557849891, 557849959, 557849893, 557849960, 557847672, 557915441, 557915442, 557915499, 557915500, 557849961, 557849962, 557849911, 557849912, 557849914, 557849915, 557849917, 557849916, 557849918, 557849919, 557849920, 557849921, 557915458, 557915459, 557915460, 557915461, 557915462, 557915464, 557915465, 557849934, 557849935, 557849936, 557849937, 557849952, 557849953, 557849954, 557849955, 557849956, 557849965, 557849966, 557849973, 557849974, 557915531, 557915532, 557915533, 557915534, 557915535, 557915536, 557915537, 557915538, 557915539, 557915540, 557915541, 557915542, 557915543, 557915544, 557915545, 557915546, 557915547, 557850012, 557915549, 557915550, 557915551, 557915552, 557850017, 557850018, 557850019, 557850020, 557850021, 557850022, 557850025, 557850026, 557850027, 557850028, 557850029, 557850030, 557850031, 557850032, 557850033, 557850034, 557850035, 557850036, 557850037, 557850038, 557850039, 557850040, 557850041, 557850042, 557850043, 557850044, 557915581, 557850046, 557850047, 557850048, 557850059, 557850060, 557850061, 557850062, 557850063, 557850064, 557850065, 557850066, 557850067, 557915219, 557915415));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public static String getServiceName() {
        return Car.XP_MSM_SERVICE;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mMsmPropertyIds;
    }

    public void setDriverSeatHorizMove(int control, int direction) throws Exception {
        this.mService.setMsmDrvSeatHorizMove(control, direction);
    }

    public void setDriverSeatBackMove(int control, int direction) throws Exception {
        this.mService.setMsmDrvSeatBackMove(control, direction);
    }

    public void setDriverSeatVertiMove(int control, int direction) throws Exception {
        this.mService.setMsmDrvSeatVertiMove(control, direction);
    }

    @Deprecated
    public void setDriverLegVertiMove(int control, int direction) throws Exception {
        this.mService.setMsmDrvLegVertiMove(control, direction);
    }

    public int setDriverSeatCushionTiltMove(int control, int direction) {
        try {
            this.mService.setMsmDrvLegVertiMove(control, direction);
            return 0;
        } catch (RemoteException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "setDriverSeatCushionTiltMove throws RuntimeException | RemoteException e " + e.getMessage());
            return 1;
        }
    }

    public void setDriverLumbHorzMove(int control, int direction) throws Exception {
        this.mService.setMsmDrvLumbHorzMove(control, direction);
    }

    public void setDriverLumbVertiMove(int control, int direction) throws Exception {
        this.mService.setMsmDrvLumbVertiMove(control, direction);
    }

    public int getDriverSeatHorizPosition() throws Exception {
        return this.mService.getMsmDrvSeatHorizPosition();
    }

    public void setDriverSeatHorizPosition(int pos) throws Exception {
        this.mService.setMsmDrvSeatHorizPosition(pos);
    }

    public int getPassengerSeatHorizontalPosition() throws Exception {
        return this.mService.getMsmPassengerSeatHorizontalPosition();
    }

    public void setPassengerSeatHorizontalPosition(int pos) throws Exception {
        this.mService.setMsmPassengerSeatHorizontalPosition(pos);
    }

    public int getDriverSeatVertiPosition() throws Exception {
        return this.mService.getMsmDrvSeatVertiPosition();
    }

    public void setDriverSeatVertiPosition(int pos) throws Exception {
        this.mService.setMsmDrvSeatVertiPosition(pos);
    }

    public int getPassengerSeatVerticalPosition() throws Exception {
        return this.mService.getMsmPassengerSeatVerticalPosition();
    }

    public void setPassengerSeatVerticalPosition(int pos) throws Exception {
        this.mService.setMsmPassengerSeatVerticalPosition(pos);
    }

    public int getDriverSeatBackPosition() throws Exception {
        return this.mService.getMsmDrvSeatBackPosition();
    }

    public void setDriverSeatBackPosition(int pos) throws Exception {
        this.mService.setMsmDrvSeatBackPosition(pos);
    }

    public int getPassengerSeatBackPosition() throws Exception {
        return this.mService.getMsmPassengerSeatBackPosition();
    }

    public void setPassengerSeatBackPosition(int pos) throws Exception {
        this.mService.setMsmPassengerSeatBackPosition(pos);
    }

    public int getDriverSeatLegPosition() throws Exception {
        return this.mService.getMsmDrvSeatLegPosition();
    }

    public void setDriverSeatLegPosition(int pos) throws Exception {
        this.mService.setMsmDrvSeatLegPosition(pos);
    }

    public void setPsnSeatHorizMove(int control, int direction) throws Exception {
        this.mService.setMsmPsnSeatHorizMove(control, direction);
    }

    public void setPsnSeatBackMove(int control, int direction) throws Exception {
        this.mService.setMsmPsnSeatBackMove(control, direction);
    }

    public void setPsnSeatVertiMove(int control, int direction) throws Exception {
        this.mService.setMsmPsnSeatVertiMove(control, direction);
    }

    @Deprecated
    public void setDriverAllPositions(int seatHorizonPos, int seatVerticalPos, int seatTiltingPos, int legHeightPos) throws Exception {
        this.mService.setMsmDriverAllPositions(seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos);
    }

    public void setDriverAllPositions(int seatHorizonPos, int seatVerticalPos, int seatTiltingPos, int legHeightPos, int legHorzPos) throws Exception {
        String cduType = Car.getXpCduType();
        if ("Q1".equals(cduType) || "Q2".equals(cduType) || "Q3".equals(cduType) || "Q5".equals(cduType) || "Q6".equals(cduType)) {
            this.mService.setMsmDriverAllPositions(seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos);
        } else {
            setDriverAllPositions(0, seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos, legHorzPos);
        }
    }

    public void setDriverAllPositions(int memoryReq, int seatHorizonPos, int seatVerticalPos, int seatTiltingPos, int legHeightPos, int legHorzPos) throws Exception {
        String cduType = Car.getXpCduType();
        if ("Q1".equals(cduType) || "Q2".equals(cduType) || "Q3".equals(cduType) || "Q5".equals(cduType) || "Q6".equals(cduType)) {
            this.mService.setMsmDriverAllPositions(seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos);
        } else {
            this.mService.setMsmDriverAllPositionsToLDCU(memoryReq, seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos, legHorzPos);
        }
    }

    public void setPassengerAllPositions(int seatHorizonPos, int seatVerticalPos, int seatTiltingPos, int legHeightPos) throws Exception {
        String cduType = Car.getXpCduType();
        if ("Q3".equals(cduType)) {
            this.mService.setMsmPassengerAllPositions(seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos);
        } else {
            this.mService.setMsmPassengerAllPositionsToLDCU(0, seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos);
        }
    }

    public void setPassengerAllPositions(int memoryReq, int seatHorizonPos, int seatVerticalPos, int seatTiltingPos, int legHeightPos) throws Exception {
        String cduType = Car.getXpCduType();
        if ("Q3".equals(cduType)) {
            this.mService.setMsmPassengerAllPositions(seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos);
        } else {
            this.mService.setMsmPassengerAllPositionsToLDCU(memoryReq, seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos);
        }
    }

    @Deprecated
    public void saveDriverAllPositionsToMcu(int seatHorizonPos, int seatVerticalPos, int seatTiltingPos, int legHeightPos) throws Exception {
        this.mService.saveMsmDriverAllPositionsToMcu(seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos);
    }

    public void saveDriverAllPositionsToMcu(int seatHorizonPos, int seatVerticalPos, int seatTiltingPos, int legHeightPos, int legHorzPos) throws Exception {
        String cduType = Car.getXpCduType();
        if ("Q1".equals(cduType) || "Q2".equals(cduType) || "Q3".equals(cduType) || "Q5".equals(cduType) || "Q6".equals(cduType)) {
            this.mService.saveMsmDriverAllPositionsToMcu(seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos);
        } else {
            this.mService.saveMsmDAllPositionsToMcu(seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos, legHorzPos);
        }
    }

    public void savePassengerAllPositionsToMcu(int seatHorizonPos, int seatVerticalPos, int seatTiltingPos, int legHeightPos) throws Exception {
        this.mService.saveMsmPassengerAllPositionsToMcu(seatHorizonPos, seatVerticalPos, seatTiltingPos, legHeightPos);
    }

    public int getDriverHeadrestStatus() throws Exception {
        return this.mService.getMsmDriverHeadrestStatus();
    }

    public int getPassengerHeadrestStatus() throws Exception {
        return this.mService.getMsmPassengerHeadrestStatus();
    }

    public void stopDriverSeatMoving(int cmd) throws Exception {
        this.mService.stopMsmDriverSeatMoving(cmd);
    }

    public void stopPassengerSeatMoving(int cmd) throws Exception {
        this.mService.stopMsmPassengerSeatMoving(cmd);
    }

    public void setDriverSeatTiltLevelOff(int cmd) throws Exception {
        this.mService.setMsmDriverSeatTiltLevelOff(cmd);
    }

    @Deprecated
    public void setDriverSeatCushTiltPos(int control, int direction) throws Exception {
        this.mService.setMsmDriverSeatCushTiltPos(control, direction);
    }

    public int setDriverSeatCushionExtMove(int control, int direction) {
        try {
            this.mService.setMsmDriverSeatCushTiltPos(control, direction);
            return 0;
        } catch (RemoteException | RuntimeException e) {
            e.printStackTrace();
            Log.e(TAG, "setDriverSeatCushionExtMove throw RuntimeException | RemoteException e " + e.getMessage());
            return 1;
        }
    }

    @Deprecated
    public void setDriverSeatCushTiltPosition(int pos) throws Exception {
    }

    @Deprecated
    public int getDriverSeatCushTiltPosition() throws Exception {
        return 0;
    }

    public void setPassengerSeatCushExt(int control, int direction) throws Exception {
        this.mService.setMsmPassengerSeatCushExt(control, direction);
    }

    @Deprecated
    public void setPassengerSeatCushExtPosition(int pos) throws Exception {
        this.mService.setMsmPassengerSeatCushExtPosition(pos);
    }

    public void setDriverSeatLegHorzPosition(int legHorzPos) throws Exception {
        this.mService.setMsmDriverSeatLegHorzPosition(legHorzPos);
    }

    public void setPassengerSeatLegHorzPosition(int legHorzPos) throws Exception {
        this.mService.setMsmPassengerSeatLegHorzPosition(legHorzPos);
    }

    @Deprecated
    public int getPassengerSeatCushExtPosition() throws Exception {
        return this.mService.getMsmPassengerSeatCushExtPosition();
    }

    public int getDriverSeatLegHorzPosition() throws Exception {
        return this.mService.getMsmDriverSeatLegHorzPosition();
    }

    public int getPassengerSeatLegHorzPosition() throws Exception {
        return this.mService.getMsmPassengerSeatLegHorzPosition();
    }

    public void setPassengerSeatTitlLevelOff(int cmd) throws Exception {
        this.mService.setMsmPassengerSeatTitlLevelOff(cmd);
    }

    public void setSecrowLtSeatTiltReq(int control, int direction) throws Exception {
        this.mService.setMsmSecrowLtSeatTiltReq(control, direction);
    }

    public void setSecrowLtSeatTiltPosition(int pos) throws Exception {
        this.mService.setMsmSecrowLtSeatTiltPosition(pos);
    }

    public int getSecrowLtSeatTiltPosition() throws Exception {
        return this.mService.getMsmSecrowLtSeatTiltPosition();
    }

    @Deprecated
    public void setSecrowLfSeatUnfoldReq(int cmd) throws Exception {
        this.mService.setMsmSecrowLtSeatUnfoldReq(cmd);
    }

    @Deprecated
    public void setSecrowLtSeatSTopMoveReq(int cmd) throws Exception {
        this.mService.setMsmSecrowLtSeatSTopMoveReq(cmd);
    }

    public void setSecrowRtSeatTiltReq(int control, int direction) throws Exception {
        this.mService.setMsmSecrowRtSeatTiltReq(control, direction);
    }

    public void setSecrowRtSeatTiltPosition(int pos) throws Exception {
        this.mService.setMsmSecrowRtSeatTiltPosition(pos);
    }

    public int getSecrowRtSeatTiltPosition() throws Exception {
        return this.mService.getMsmSecrowRtSeatTiltPosition();
    }

    @Deprecated
    public void setSecrowRtSeatSTopMoveReq(int cmd) throws Exception {
        this.mService.setMsmSecrowRtSeatSTopMoveReq(cmd);
    }

    @Deprecated
    public void setSecrowRTSeatUnfoldReq(int cmd) throws Exception {
        this.mService.setMsmSecrowRTSeatUnfoldReq(cmd);
    }

    public void setPassengerWelcomeSwitch(int onOff) throws Exception {
        this.mService.setMsmPassengerWelcomeSwitch(onOff);
    }

    public int getPassengerWelcomeSwitch() throws Exception {
        return this.mService.getMsmPassengerWelcomeSwitch();
    }

    public void setDriverWelcomeActive(int active) throws Exception {
        this.mService.setMsmDriverWelcomeActive(active);
    }

    public void setPassengerWelcomeActive(int active) throws Exception {
        this.mService.setMsmPassengerWelcomeActive(active);
    }

    public void setPassengerSeatLumbVerticalPos(int control, int direction) throws Exception {
        this.mService.setMsmPassengerSeatLumbVerticalPos(control, direction);
    }

    public void setPassengerSeatLumbHorzPos(int control, int direction) throws Exception {
        this.mService.setMsmPassengerSeatLumbHorzPos(control, direction);
    }

    @Deprecated
    public void setSecRowLeftSeatCushExtReq(int control, int direction) throws Exception {
        this.mService.setMsmSecRowLeftSeatCushExtReq(control, direction);
    }

    public void setSecRowLeftSeatLegHorzPosReq(int control, int direction) throws Exception {
        this.mService.setMsmSecRowLeftSeatLegHorzPosReq(control, direction);
    }

    @Deprecated
    public void setSecRowRightSeatCushExtReq(int control, int direction) throws Exception {
        this.mService.setMsmSecRowRightSeatCushExtReq(control, direction);
    }

    public void setSecRowRightSeatLegHorzPosReq(int control, int direction) throws Exception {
        this.mService.setMsmSecRowRightSeatLegHorzPosReq(control, direction);
    }

    @Deprecated
    public void setSecRowLeftSeatCushExtPosition(int legHorzPos) throws Exception {
        this.mService.setMsmSecRowLeftSeatCushExtPosition(legHorzPos);
    }

    public void setSecRowLeftSeatLegHorzPosition(int legHorzPos) throws Exception {
        this.mService.setMsmSecRowLeftSeatLegHorzPosition(legHorzPos);
    }

    @Deprecated
    public int getSecRowLeftSeatCushExtPosition() throws Exception {
        return this.mService.getMsmSecRowLeftSeatCushExtPosition();
    }

    public int getSecRowLeftSeatLegHorzPosition() throws Exception {
        return this.mService.getMsmSecRowLeftSeatLegHorzPosition();
    }

    @Deprecated
    public void setSecRowRightSeatCushExtPosition(int legHorzPos) throws Exception {
        this.mService.setMsmSecRowRightSeatCushExtPosition(legHorzPos);
    }

    @Deprecated
    public void setSecRowRightSeatLegHorzPosition(int legHorzPos) throws Exception {
        this.mService.setMsmSecRowRightSeatLegHorzPosition(legHorzPos);
    }

    @Deprecated
    public int getSecRowRightSeatCushExtPosition() throws Exception {
        return this.mService.getMsmSecRowRightSeatCushExtPosition();
    }

    public int getSecRowRightSeatLegHorzPosition() throws Exception {
        return this.mService.getMsmSecRowRightSeatLegHorzPosition();
    }

    public void setSecrowRightSeatUnlockReq(int cmd) throws Exception {
        this.mService.setMsmSecrowRightSeatUnlockReq(cmd);
    }

    public void setSecrowLeftSeatUnlockReq(int cmd) throws Exception {
        this.mService.setMsmSecrowLeftSeatUnlockReq(cmd);
    }

    public void setDriverSeatMassgProgMode(int mode) throws Exception {
        this.mService.setMsmDriverSeatMassgProgMode(mode);
    }

    public int getDriverSeatMassgProgMode() throws Exception {
        return this.mService.getMsmDriverSeatMassgProgMode();
    }

    public void setDriverSeatMassgIntensity(int level) throws Exception {
        this.mService.setMsmDriverSeatMassgIntensity(level);
    }

    public int getDriverSeatMassgIntensity() throws Exception {
        return this.mService.getMsmDriverSeatMassgIntensity();
    }

    public void setPassengerSeatMassgProgMode(int mode) throws Exception {
        this.mService.setMsmPassengerSeatMassgProgMode(mode);
    }

    public int getPassengerSeatMassgProgMode() throws Exception {
        return this.mService.getMsmPassengerSeatMassgProgMode();
    }

    public void setPassengerSeatMassgIntensity(int level) throws Exception {
        this.mService.setMsmPassengerSeatMassgIntensity(level);
    }

    public int getPassengerSeatMassgIntensity() throws Exception {
        return this.mService.getMsmPassengerSeatMassgIntensity();
    }

    public void setSecRowLeftSeatMassgProgMode(int mode) throws Exception {
        this.mService.setMsmSecRowLeftSeatMassgProgMode(mode);
    }

    public int getSecRowLeftSeatMassgProgMode() throws Exception {
        return this.mService.getMsmSecRowLeftSeatMassgProgMode();
    }

    public void setSecRowLeftSeatMassgIntensity(int level) throws Exception {
        this.mService.setMsmSecRowLeftSeatMassgIntensity(level);
    }

    public int getSecRowLeftSeatMassgIntensity() throws Exception {
        return this.mService.getMsmSecRowLeftSeatMassgIntensity();
    }

    public void setSecRowRightSeatMassgProgMode(int mode) throws Exception {
        this.mService.setMsmSecRowRightSeatMassgProgMode(mode);
    }

    public int getSecRowRightSeatMassgProgMode() throws Exception {
        return this.mService.getMsmSecRowRightSeatMassgProgMode();
    }

    public void setSecRowRightSeatMassgIntensity(int level) throws Exception {
        this.mService.setMsmSecRowRightSeatMassgIntensity(level);
    }

    public int getSecRowRightSeatMassgIntensity() throws Exception {
        return this.mService.getMsmSecRowRightSeatMassgIntensity();
    }

    public void setDriverSeatMassgElem(int[] cmd) throws Exception {
        if (cmd.length != 20) {
            throw new IllegalArgumentException();
        }
        this.mService.setMsmDriverSeatMassgElem(cmd);
    }

    public int[] getDriverSeatMassgElem() throws Exception {
        return this.mService.getMsmDriverSeatMassgElem();
    }

    public void setPassengerSeatMassgElem(int[] cmd) throws Exception {
        if (cmd.length != 20) {
            throw new IllegalArgumentException();
        }
        this.mService.setMsmPassengerSeatMassgElem(cmd);
    }

    public int[] getPassengerSeatMassgElem() throws Exception {
        return this.mService.getMsmPassengerSeatMassgElem();
    }

    public void setSecRowLeftSeatMassgElem(int[] cmd) throws Exception {
        if (cmd.length != 20) {
            throw new IllegalArgumentException();
        }
        this.mService.setMsmSecRowLeftSeatMassgElem(cmd);
    }

    public int[] getSecRowLeftSeatMassgElem() throws Exception {
        return this.mService.getMsmSecRowLeftSeatMassgElem();
    }

    public void setSecRowRightSeatMassgElem(int[] cmd) throws Exception {
        if (cmd.length != 20) {
            throw new IllegalArgumentException();
        }
        this.mService.setMsmSecRowRightSeatMassgElem(cmd);
    }

    public int[] getSecRowRightSeatMassgElem() throws Exception {
        return this.mService.getMsmSecRowRightSeatMassgElem();
    }

    public void setSecRowLeftSeatAllPos(int seatTiltingPos, int legHorzPos) throws Exception {
        this.mService.setMsmSecRowLeftSeatAllPos(seatTiltingPos, legHorzPos);
    }

    public void setSecRowRightSeatAllPos(int seatTiltingPos, int legHorzPos) throws Exception {
        this.mService.setMsmSecRowRightSeatAllPos(seatTiltingPos, legHorzPos);
    }

    public int getDriverSeatLumberSwitchMode() throws Exception {
        return this.mService.getMsmDriverSeatLumberSwitchMode();
    }

    public int getDriverSeatLumberSwitchCenterPressStatus() throws Exception {
        return this.mService.getMsmDriverSeatLumberSwitchCenterPressStatus();
    }

    public int getPassengerSeatLumberSwitchMode() throws Exception {
        return this.mService.getMsmPassengerSeatLumberSwitchMode();
    }

    public int getPassengerSeatLumberSwitchCenterPressStatus() throws Exception {
        return this.mService.getMsmPassengerSeatLumberSwitchCenterPressStatus();
    }

    @Deprecated
    public void setDriverSeatPositionMemoryRequest(int cmd) throws Exception {
        this.mService.setMsmDriverSeatPositionMemoryRequest(cmd);
    }

    public int getDriverSeatMassgErrorStatus() throws Exception {
        return this.mService.getMsmDriverSeatMassgErrorStatus();
    }

    public int getPassengerSeatMassgErrorStatus() throws Exception {
        return this.mService.getMsmPassengerSeatMassgErrorStatus();
    }

    public int getSecRowLeftSeatMassgErrorStatus() throws Exception {
        return this.mService.getMsmSecRowLeftSeatMassgErrorStatus();
    }

    public int getSecRowRightSeatMassgErrorStatus() throws Exception {
        return this.mService.getMsmSecRowRightSeatMassgErrorStatus();
    }

    public void setDriverSeatLumbControlSwitchEnable(int enable) throws Exception {
        this.mService.setMsmDriverSeatLumbControlSwitchEnable(enable);
    }

    public void setPassengerSeatLumbControlSwitchEnable(int enable) throws Exception {
        this.mService.setMsmPassengerSeatLumbControlSwitchEnable(enable);
    }

    public void setSecRowLeftSeatPos(int memoryReq, int seatHorizonPos, int seatAngle, int seatTiltingPos, int legHeightPos, int legHorzPos, int HeadHeightPos, int HeadHorzPos) throws Exception {
        this.mService.setSecRowLeftSeatPos(memoryReq, seatHorizonPos, seatAngle, seatTiltingPos, legHeightPos, legHorzPos, HeadHeightPos, HeadHorzPos);
    }

    public int[] getSecRowLeftSeatPos() throws Exception {
        return this.mService.getSecRowLeftSeatPos();
    }

    public void setSecRowRightSeatPos(int memoryReq, int seatHorizonPos, int seatAngle, int seatTiltingPos, int legHeightPos, int legHorzPos, int HeadHeightPos, int HeadHorzPos) throws Exception {
        this.mService.setSecRowRightSeatPos(memoryReq, seatHorizonPos, seatAngle, seatTiltingPos, legHeightPos, legHorzPos, HeadHeightPos, HeadHorzPos);
    }

    public int[] getSecRowRightSeatPos() throws Exception {
        return this.mService.getSecRowRightSeatPos();
    }

    public void setTrdRowSeatAllPos(int ltSeatTiltingPos, int ltHeadHeightPos, int rtSeatTiltingPos, int rtHeadHeightPos, int midHeadHeightPos) throws Exception {
        this.mService.setTrdRowSeatAllPos(ltSeatTiltingPos, ltHeadHeightPos, rtSeatTiltingPos, rtHeadHeightPos, midHeadHeightPos);
    }

    public int[] getTrdRowSeatAllPos() throws Exception {
        return this.mService.getTrdRowSeatAllPos();
    }

    public void setSecRowLeftHorizMove(int control, int direction) throws Exception {
        this.mService.setSecRowLeftHorizMove(control, direction);
    }

    public void setSecRowLeftAngleMove(int control, int direction) throws Exception {
        this.mService.setSecRowLeftAngleMove(control, direction);
    }

    public void setSecRowLeftLegVertiMove(int control, int direction) throws Exception {
        this.mService.setSecRowLeftLegVertiMove(control, direction);
    }

    public void setSecRowLeftHeadVertiMove(int control, int direction) throws Exception {
        this.mService.setSecRowLeftHeadVertiMove(control, direction);
    }

    public void setSecRowLeftHeadHorizMove(int control, int direction) throws Exception {
        this.mService.setSecRowLeftHeadHorizMove(control, direction);
    }

    public void setSecRowRightHorizMove(int control, int direction) throws Exception {
        this.mService.setSecRowRightHorizMove(control, direction);
    }

    public void setSecRowRighttAngleMove(int control, int direction) throws Exception {
        this.mService.setSecRowRighttAngleMove(control, direction);
    }

    public void setSecRowRightLegVertiMove(int control, int direction) throws Exception {
        this.mService.setSecRowRightLegVertiMove(control, direction);
    }

    public void setSecRowRightHeadVertiMove(int control, int direction) throws Exception {
        this.mService.setSecRowRightHeadVertiMove(control, direction);
    }

    public void setSecRowRightHeadHorizMove(int control, int direction) throws Exception {
        this.mService.setSecRowRightHeadHorizMove(control, direction);
    }

    public void setTrdRowLeftSeatTiltMove(int control, int direction) throws Exception {
        this.mService.setTrdRowLeftSeatTiltMove(control, direction);
    }

    public void setTrdRowLeftHeadVertiMove(int control, int direction) throws Exception {
        this.mService.setTrdRowLeftHeadVertiMove(control, direction);
    }

    public void setTrdRowRightSeatTiltMove(int control, int direction) throws Exception {
        this.mService.setTrdRowRightSeatTiltMove(control, direction);
    }

    public void setTrdRowRightHeadVertiMove(int control, int direction) throws Exception {
        this.mService.setTrdRowRightHeadVertiMove(control, direction);
    }

    public void setTrdRowMiddleSeatTiltMove(int control, int direction) throws Exception {
        this.mService.setTrdRowMiddleSeatTiltMove(control, direction);
    }

    public void setSecRowLeftSeatLumbVertiMove(int control, int direction) throws Exception {
        this.mService.setSecRowLeftSeatLumbVertiMove(control, direction);
    }

    public void setSecRowLeftSeatLumbHorzMove(int control, int direction) throws Exception {
        this.mService.setSecRowLeftSeatLumbHorzMove(control, direction);
    }

    public void setSecRowRightSeatLumbVertiMove(int control, int direction) throws Exception {
        this.mService.setSecRowRightSeatLumbVertiMove(control, direction);
    }

    public void setSecRowRightSeatLumbHorzMove(int control, int direction) throws Exception {
        this.mService.setSecRowRightSeatLumbHorzMove(control, direction);
    }

    public void setSecRowLeftSeatZeroGravReq(int ctrl) throws Exception {
        this.mService.setSecRowLeftSeatZeroGravReq(ctrl);
    }

    public void setSecRowRightSeatZeroGravReq(int ctrl) throws Exception {
        this.mService.setSecRowRightSeatZeroGravReq(ctrl);
    }

    public void setSecRowLeftSeatEasyEntryReq(int ctrl) throws Exception {
        this.mService.setSecRowLeftSeatEasyEntryReq(ctrl);
    }

    public void setSecRowRightSeatEasyEntryReq(int ctrl) throws Exception {
        this.mService.setSecRowRightSeatEasyEntryReq(ctrl);
    }

    public int getSecRowLeftSeatFuncSt() throws Exception {
        return this.mService.getSecRowLeftSeatFuncSt();
    }

    public int getSecRowRightSeatFuncSt() throws Exception {
        return this.mService.getSecRowRightSeatFuncSt();
    }

    public void setMsmtLeftSeatFoldReq(int ctrl) throws Exception {
        this.mService.setMsmtLeftSeatFoldReq(ctrl);
    }

    public int getMsmtLeftSeatFoldFunSt() throws Exception {
        return this.mService.getMsmtLeftSeatFoldFunSt();
    }

    public void setMsmtRightSeatFoldReq(int ctrl) throws Exception {
        this.mService.setMsmtRightSeatFoldReq(ctrl);
    }

    public int getMsmtRightSeatFoldFunSt() throws Exception {
        return this.mService.getMsmtRightSeatFoldFunSt();
    }

    public void setMsmtSeatStowReq(int ctrl) throws Exception {
        this.mService.setMsmtSeatStowReq(ctrl);
    }

    public int getMsmtSeatStowFunSt() throws Exception {
        return this.mService.getMsmtSeatStowFunSt();
    }

    public void stopSecRowLeftSeatMoving(int cmd) throws Exception {
        this.mService.stopSecRowLeftSeatMoving(cmd);
    }

    public void stopSecRowRightSeatMoving(int cmd) throws Exception {
        this.mService.stopSecRowRightSeatMoving(cmd);
    }

    public void setSecRowLtSeatHorzPos(int pos) throws Exception {
        this.mService.setSecRowLtSeatHorzPos(pos);
    }

    public int getSecRowLtSeatHorzPos() throws Exception {
        return this.mService.getSecRowLtSeatHorzPos();
    }

    public void setSecRowLtSeatAnglePos(int pos) throws Exception {
        this.mService.setSecRowLtSeatAnglePos(pos);
    }

    public int getSecRowLtSeatAnglePos() throws Exception {
        return this.mService.getSecRowLtSeatAnglePos();
    }

    public void setSecRowLtSeatLegVerticalPos(int pos) throws Exception {
        this.mService.setSecRowLtSeatLegVerticalPos(pos);
    }

    public int getSecRowLtSeatLegVerticalPos() throws Exception {
        return this.mService.getSecRowLtSeatLegVerticalPos();
    }

    public void setSecRowLtSeatHeadVerticalPos(int pos) throws Exception {
        this.mService.setSecRowLtSeatHeadVerticalPos(pos);
    }

    public int getSecRowLtSeatHeadVerticalPos() throws Exception {
        return this.mService.getSecRowLtSeatHeadVerticalPos();
    }

    public void setSecRowLtSeatHeadHorzPos(int pos) throws Exception {
        this.mService.setSecRowLtSeatHeadHorzPos(pos);
    }

    public int getSecRowLtSeatHeadHorzPos() throws Exception {
        return this.mService.getSecRowLtSeatHeadHorzPos();
    }

    public void setSecRowRtSeatHorzPos(int pos) throws Exception {
        this.mService.setSecRowRtSeatHorzPos(pos);
    }

    public int getSecRowRtSeatHorzPos() throws Exception {
        return this.mService.getSecRowRtSeatHorzPos();
    }

    public void setSecRowRtSeatAnglePos(int pos) throws Exception {
        this.mService.setSecRowRtSeatAnglePos(pos);
    }

    public int getSecRowRtSeatAnglePos() throws Exception {
        return this.mService.getSecRowRtSeatAnglePos();
    }

    public void setSecRowRtSeatLegVerticalPos(int pos) throws Exception {
        this.mService.setSecRowRtSeatLegVerticalPos(pos);
    }

    public int getSecRowRtSeatLegVerticalPos() throws Exception {
        return this.mService.getSecRowRtSeatLegVerticalPos();
    }

    public void setSecRowRtSeatHeadVerticalPos(int pos) throws Exception {
        this.mService.setSecRowRtSeatHeadVerticalPos(pos);
    }

    public int getSecRowRtSeatHeadVerticalPos() throws Exception {
        return this.mService.getSecRowRtSeatHeadVerticalPos();
    }

    public void setSecRowRtSeatHeadHorzPos(int pos) throws Exception {
        this.mService.setSecRowRtSeatHeadHorzPos(pos);
    }

    public int getSecRowRtSeatHeadHorzPos() throws Exception {
        return this.mService.getSecRowRtSeatHeadHorzPos();
    }

    public void setTrdRowLtSeatTiltPos(int pos) throws Exception {
        this.mService.setTrdRowLtSeatTiltPos(pos);
    }

    public int getTrdRowLtSeatTiltPos() throws Exception {
        return this.mService.getTrdRowLtSeatTiltPos();
    }

    public void setTrdRowLtSeatHeadVerticalPos(int pos) throws Exception {
        this.mService.setTrdRowLtSeatHeadVerticalPos(pos);
    }

    public int getTrdRowLtSeatHeadVerticalPos() throws Exception {
        return this.mService.getTrdRowLtSeatHeadVerticalPos();
    }

    public void setTrdRowRtSeatTiltPos(int pos) throws Exception {
        this.mService.setTrdRowRtSeatTiltPos(pos);
    }

    public int getTrdRowRtSeatTiltPos() throws Exception {
        return this.mService.getTrdRowRtSeatTiltPos();
    }

    public void setTrdRowRtSeatHeadVerticalPos(int pos) throws Exception {
        this.mService.setTrdRowRtSeatHeadVerticalPos(pos);
    }

    public int getTrdRowRtSeatHeadVerticalPos() throws Exception {
        return this.mService.getTrdRowRtSeatHeadVerticalPos();
    }

    public void setTrdRowMidSeatHeadVerticalPos(int pos) throws Exception {
        this.mService.setTrdRowMidSeatHeadVerticalPos(pos);
    }

    public int getTrdRowMidSeatHeadVerticalPos() throws Exception {
        return this.mService.getTrdRowMidSeatHeadVerticalPos();
    }

    public void setTrdRowMidHeadVertiMove(int control, int direction) throws Exception {
        this.mService.setTrdRowMidHeadVertiMove(control, direction);
    }

    public int getTrdRowLeftSeatTiltState() throws Exception {
        return this.mService.getTrdRowLeftSeatTiltState();
    }

    public int getTrdRowRightSeatTiltState() throws Exception {
        return this.mService.getTrdRowRightSeatTiltState();
    }

    public int getTrdRowSeatStowState() throws Exception {
        return this.mService.getTrdRowSeatStowState();
    }

    public int getSecRowLtSeatState() throws Exception {
        return this.mService.getSecRowLtSeatState();
    }

    public int getSecRowRtSeatState() throws Exception {
        return this.mService.getSecRowRtSeatState();
    }

    public int getTrdRowLtSeatHeadMoveState() throws Exception {
        return this.mService.getTrdRowLtSeatHeadMoveState();
    }

    public int getTrdRowMidSeatHeadMoveState() throws Exception {
        return this.mService.getTrdRowMidSeatHeadMoveState();
    }

    public int getTrdRowRtSeatHeadMoveState() throws Exception {
        return this.mService.getTrdRowRtSeatHeadMoveState();
    }

    public int getSecRowLtSeatMoveState() throws Exception {
        return this.mService.getSecRowLtSeatMoveState();
    }

    public int getSecRowRtSeatMoveState() throws Exception {
        return this.mService.getSecRowRtSeatMoveState();
    }

    public int getDriverSeatMoveState() throws Exception {
        return this.mService.getDriverSeatMoveState();
    }

    public int getPassengerSeatMoveState() throws Exception {
        return this.mService.getPassengerSeatMoveState();
    }
}
