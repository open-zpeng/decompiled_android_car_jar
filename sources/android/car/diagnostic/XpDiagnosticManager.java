package android.car.diagnostic;

import android.annotation.SuppressLint;
import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarApiUtil;
import android.car.CarNotConnectedException;
import android.car.XpDebugLog;
import android.car.diagnostic.XpDiagnosticManager;
import android.car.hardware.CarEcuManager;
import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.XpVehicle.IXpDiagnostic;
import android.car.hardware.property.CarPropertyEvent;
import android.car.hardware.property.CarPropertyManager;
import android.car.hardware.property.ICarPropertyEventListener;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.internal.SingleMessageHandler;
import com.android.internal.annotations.GuardedBy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
@SystemApi
/* loaded from: classes.dex */
public final class XpDiagnosticManager extends CarEcuManager {
    public static final int AEB_STATE_BLOCK = 4;
    public static final int AEB_STATE_NO_FAULT = 5;
    public static final int AEB_STATE_OFF = 0;
    public static final int AEB_STATE_ON = 1;
    public static final int AEB_STATE_PERMANENT_FAILURE = 3;
    public static final int AEB_STATE_TEMPORARY_FAILURE = 2;
    public static final int ALC_CTRL_REMIND_ACTIVE_BY_ALC_DIR_LEFT = 1;
    public static final int ALC_CTRL_REMIND_ACTIVE_BY_ALC_DIR_RIGHT = 2;
    public static final int ALC_CTRL_REMIND_ALC_CANCEL = 6;
    public static final int ALC_CTRL_REMIND_ALC_FAILURE = 10;
    public static final int ALC_CTRL_REMIND_ALC_FINISH = 7;
    public static final int ALC_CTRL_REMIND_ALC_WAIT_DIR_LEFT = 3;
    public static final int ALC_CTRL_REMIND_ALC_WAIT_DIR_RIGHT = 4;
    public static final int ALC_CTRL_REMIND_ALC_WAIT_TIMEOUT_DIR_LEFT = 8;
    public static final int ALC_CTRL_REMIND_ALC_WAIT_TIMEOUT_DIR_RIGHT = 9;
    public static final int ALC_CTRL_REMIND_NO_DISPLAY = 0;
    public static final int ALC_CTRL_REMIND_UNABLE_TO_ACTIVATE_ALC = 5;
    public static final int ALS_FAILURE_STATE_ACCELERATION_SENSOR_ERROR = 2;
    public static final int ALS_FAILURE_STATE_COMMUNICATION_ERROR = 1;
    public static final int ALS_FAILURE_STATE_INITIAL_DATA_ERROR = 4;
    public static final int ALS_FAILURE_STATE_NOT_INITIALIZED = 3;
    public static final int ALS_FAILURE_STATE_NO_ERROR = 0;
    public static final int ALS_FAILURE_STATE_ROAD_ANGLE_ERROR = 5;
    public static final int APA_FAILURE = 2;
    public static final int APA_NORMAL = 1;
    public static final int APA_NOT_DEFINED = 0;
    public static final int BATTERY_DEAD = 1;
    public static final int BATTERY_NORMAL = 0;
    public static final int BATTERY_PTC_FAILURE_INDEX_ELEMENT = 1;
    public static final int BATTERY_PTC_FAILURE_INDEX_INTERNAL = 0;
    public static final int BATTERY_VOLTAGE_LOW = 1;
    public static final int BATTERY_VOLTAGE_NORMAL = 0;
    public static final int BSD_STATE_FAIL = 2;
    public static final int BSD_STATE_NO_FAULT = 3;
    public static final int BSD_STATE_OFF = 0;
    public static final int BSD_STATE_ON = 1;
    public static final int CCS_ERROR = 2;
    public static final int CCS_PREWARM = 3;
    public static final int CCS_STANDBY = 0;
    public static final int CCS_WORK = 1;
    public static final int CHARGE_PORT_HOT = 1;
    public static final int CHARGE_PORT_NORMAL = 0;
    public static final int COMPRESSOR_FAILURE_INDEX_CAUSE = 1;
    public static final int COMPRESSOR_FAILURE_INDEX_STT = 0;
    private static final boolean DBG = false;
    public static final int DOW_STATE_FAIL = 2;
    public static final int DOW_STATE_OFF = 0;
    public static final int DOW_STATE_ON = 1;
    public static final int ECU_FAILURE = 1;
    public static final int ECU_NORMAL = 0;
    public static final int ELECTRIC_MOTOR_SYSTEM_HOT = 1;
    public static final int ELECTRIC_MOTOR_SYSTEM_NORMAL = 0;
    public static final int EV_COLLISION_AND_CUT_OFF_THE_POWER = 7;
    public static final int EV_LIMIT_SPEED = 2;
    public static final int EV_LIMP_AND_CONTACT_MAINTENANCE = 3;
    public static final int EV_NO_ERROR = 0;
    public static final int EV_PARK_AND_BE_ABOUT_TO_LOSE_POWER = 6;
    public static final int EV_PARK_AND_IMMEDIATELY_LOST_POWER = 4;
    public static final int EV_REDUCE_POWER = 1;
    public static final int GEAR_WARNING_BATTERY_CHARGING = 1;
    public static final int GEAR_WARNING_CLOSE_DOOR_AND_FASTEN_SEATBELT = 7;
    public static final int GEAR_WARNING_DRIVING = 2;
    public static final int GEAR_WARNING_NO_BRAKE = 4;
    public static final int GEAR_WARNING_NO_READY = 3;
    public static final int GEAR_WARNING_NO_WARNING = 0;
    public static final int GEAR_WARNING_SHIFT_SWITCH_TO_N_GEAR = 6;
    public static final int GEAR_WARNING_SPEED_DOWN = 5;
    public static final int HIGH_VOLTAGE_RELAY_ADHESION = 1;
    public static final int HIGH_VOLTAGE_RELAY_NORMAL = 0;
    public static final int HVAC_PTC_EXTERNAL_ERROR = 2;
    public static final int HVAC_PTC_INTERNAL_ERROR = 1;
    public static final int HVAC_PTC_NORMAL = 0;
    public static final int ID_ALS_ERROR_ST = 557848588;
    public static final int ID_AND_DIAG_REQUEST = 560993285;
    public static final int ID_ATLS_ERR_ST = 557848587;
    public static final int ID_AVAS_FAULT = 557855247;
    public static final int ID_BCM_AIRBAG_FAULT_ST = 557849618;
    public static final int ID_BCM_HIGHTBEAM_FAIL = 557849734;
    public static final int ID_BCM_LDTROUTPUT_FAIL = 557849740;
    public static final int ID_BCM_LOWBEAM_FAIL = 557849735;
    public static final int ID_BCM_LTURNLAMP_FAIL = 557849736;
    public static final int ID_BCM_MSM_ERROR_INFO = 557915286;
    public static final int ID_BCM_PARKINGLAMP_FAIL = 557849741;
    public static final int ID_BCM_RDTROUTPUT_FAIL = 557849739;
    public static final int ID_BCM_REARFOG_FAIL = 557849738;
    public static final int ID_BCM_RTURNLAMP_FAIL = 557849737;
    public static final int ID_BCM_SYSERROR_WARN = 557849742;
    public static final int ID_CDC_DATAUPLOAD_ST = 557852280;
    public static final int ID_CIU_DVR_STATUS = 557852696;
    public static final int ID_CIU_SD_ST = 557852675;
    public static final int ID_DHC_ERR = 557849748;
    public static final int ID_EPS_WARN_LAMP = 557851661;
    public static final int ID_ESP_ABS_FAULT = 557851663;
    public static final int ID_ESP_APBERR_ST = 557851662;
    public static final int ID_ESP_AVH_FAULT = 557851659;
    public static final int ID_ESP_ESP_FAULT = 557851657;
    public static final int ID_ESP_HDC_FAULT = 557851658;
    public static final int ID_ESP_SYS_WARNIND_REQ = 557851660;
    public static final int ID_HVAC_COMPRESSOR_ERROR_INFO = 557914671;
    public static final int ID_HVAC_PTC_ERROR = 557849134;
    public static final int ID_IBT_FAILURE_LAMP = 557851665;
    public static final int ID_IPUF_FAILST = 557847104;
    public static final int ID_IPUR_FAILST = 557847103;
    public static final int ID_LLU_ERR_ST = 557854230;
    public static final int ID_MCU_DIAG_REQUEST = 560993284;
    public static final int ID_MCU_DID_LINK_STATUS = 560993302;
    public static final int ID_MCU_DID_PHY_SETTING = 560993339;
    public static final int ID_MCU_DID_RECEIVED_PAK = 560993305;
    public static final int ID_MCU_DID_SQI_1000BASE = 560993300;
    public static final int ID_MCU_DID_SQI_100BASE = 560993299;
    public static final int ID_MCU_DID_SQI_MAX = 560993301;
    public static final int ID_MCU_DID_SQI_VALUE = 560993303;
    public static final int ID_MCU_DID_TRANSMITTED_PAK = 560993304;
    public static final int ID_MCU_DTC_ETH_UNEXPECTED_LOSS = 560993306;
    public static final int ID_MCU_DTC_INSUFFICIENT_SQI = 560993307;
    public static final int ID_MCU_DTC_IP_ERROR = 560993308;
    public static final int ID_MCU_RID_CABLE_DIAGNOSTICS = 560993298;
    public static final int ID_MCU_RID_MASTERSLAVE_CONTROL = 560993338;
    public static final int ID_MCU_RID_RESET_PHY = 560993296;
    public static final int ID_MCU_RID_TEST_MODE = 560993297;
    public static final int ID_MSMD_ECU_ERR = 557849743;
    public static final int ID_MSMD_HEATSYS_ERR = 557849745;
    public static final int ID_MSMD_VENTILATIONMOTOR_ERR = 557849744;
    public static final int ID_MSMP_ECU_ERR = 557849746;
    public static final int ID_MSMP_HEATSYS_ERR = 557849747;
    public static final int ID_SCU_ALCCTRL_REMIND = 557852274;
    public static final int ID_SCU_BSD_SW = 557852165;
    public static final int ID_SCU_DOW_SW = 557852168;
    public static final int ID_SCU_FCWAEB_SW = 557852161;
    public static final int ID_SCU_ICM_APAERR_TIPS = 557852275;
    public static final int ID_SCU_IHB_SW = 557852163;
    public static final int ID_SCU_ISLC_SW = 557852173;
    public static final int ID_SCU_LDW_SW = 557852162;
    public static final int ID_SCU_LONGCTRL_REMIND = 557852199;
    public static final int ID_SCU_RCTA_SW = 557852166;
    public static final int ID_SCU_RCW_SW = 557852167;
    public static final int ID_SCU_SIDE_REVERSION_WARNING = 557852202;
    public static final int ID_SRR_FLFAIL_ST = 557852276;
    public static final int ID_SRR_FRFAIL_ST = 557852277;
    public static final int ID_SRR_RLFAIL_ST = 557852278;
    public static final int ID_SRR_RRFAIL_ST = 557852279;
    public static final int ID_TPMS_ABNORMALPRWARN = 557850119;
    public static final int ID_TPMS_SYSFAULTWARN = 557850118;
    public static final int ID_TPMS_TIRE_PRESSURE_SENSOR_STATUS_ALL = 557915664;
    public static final int ID_TPMS_WARNING_TIRE_TEMPERATURE_ALL = 557915662;
    public static final int ID_VCU_AGS_ERROR = 557847113;
    public static final int ID_VCU_BATTERY_PTC_ERROR_INFO = 557912651;
    public static final int ID_VCU_BATTERY_VOLTAGE_LOW = 557847109;
    public static final int ID_VCU_BCRUISE_ERR = 557847094;
    public static final int ID_VCU_CCS_WORK_STATE = 557847106;
    public static final int ID_VCU_CHGPORTHOT_DSP = 557847097;
    public static final int ID_VCU_DCDC_ERROR = 557847107;
    public static final int ID_VCU_DEADBATTERY_FLAG = 557847099;
    public static final int ID_VCU_EBS_ERR_DSP = 557847091;
    public static final int ID_VCU_ELECTRIC_MOTOR_SYSTEM_OVERHEATING = 557847110;
    public static final int ID_VCU_ELECTRIC_VACUUM_PUMP_ERROR = 557847111;
    public static final int ID_VCU_EVERRLAMP_DSP = 557847089;
    public static final int ID_VCU_EVERR_MSGDISP = 557847102;
    public static final int ID_VCU_GEAR_WARNING = 557847090;
    public static final int ID_VCU_HVRLY_ADHESION_ST = 557847112;
    public static final int ID_VCU_LIQUIDHIGHTEMP_ERR = 557847092;
    public static final int ID_VCU_OBCMSG_LOST = 557847098;
    public static final int ID_VCU_POWERLIMITATION_DSP = 557847096;
    public static final int ID_VCU_TCM_P_GEAR_FAIL_ST = 557912650;
    public static final int ID_VCU_THERMORUNAWAY_ST = 557847095;
    public static final int ID_VCU_WARNING_BATTERY_OVERHEATING = 557847105;
    public static final int ID_VCU_WATERSENSOR_ERR = 557847093;
    public static final int ID_XPU_XPU_FAIL_ST = 557852281;
    public static final int IHB_STATE_ACTIVE = 3;
    public static final int IHB_STATE_OFF = 0;
    public static final int IHB_STATE_PASSIVE = 1;
    public static final int IHB_STATE_PERMANENT_FAILURE = 5;
    public static final int IHB_STATE_STANDBY = 2;
    public static final int IHB_STATE_TEMPORARY_FAILURE = 4;
    public static final int IPU_DISABLED = 2;
    public static final int IPU_NORMAL = 0;
    public static final int IPU_REDUCE_POWER = 1;
    public static final int IPU_SYSTEM_SHUTDOWN = 3;
    public static final int ISLC_STATE_ACTIVE = 3;
    public static final int ISLC_STATE_NO_FAULT = 8;
    public static final int ISLC_STATE_OFF = 0;
    public static final int ISLC_STATE_OVERRIDE = 5;
    public static final int ISLC_STATE_PASSIVE = 1;
    public static final int ISLC_STATE_PERMANENT_FAILURE = 7;
    public static final int ISLC_STATE_STANDBY = 2;
    public static final int ISLC_STATE_STANDWAIT = 4;
    public static final int ISLC_STATE_TEMPORARY_FAILURE = 6;
    public static final int LDW_STATE_ACTIVE = 3;
    public static final int LDW_STATE_NO_FAULT = 6;
    public static final int LDW_STATE_OFF = 0;
    public static final int LDW_STATE_PASSIVE = 1;
    public static final int LDW_STATE_PERMANENT_FAILURE = 5;
    public static final int LDW_STATE_STANDBY = 2;
    public static final int LDW_STATE_TEMPORARY_FAILURE = 4;
    public static final int LIQUID_TEMP_HIGH = 1;
    public static final int LIQUID_TEMP_NORMAL = 0;
    private static final int MSG_GENERIC_EVENT = 0;
    public static final int MSM_INDEX_ECU = 12;
    public static final int MSM_INDEX_HORIZONAL_BACKWARD = 3;
    public static final int MSM_INDEX_HORIZONAL_FORWARD = 2;
    public static final int MSM_INDEX_HORIZONAL_HALL = 9;
    public static final int MSM_INDEX_HORIZONAL_MOTOR = 6;
    public static final int MSM_INDEX_TILTING_BACKWARD = 1;
    public static final int MSM_INDEX_TILTING_FORWARD = 0;
    public static final int MSM_INDEX_TILTING_HALL = 11;
    public static final int MSM_INDEX_TILTING_MOTOR = 8;
    public static final int MSM_INDEX_VERTICAL_DOWNWARD = 5;
    public static final int MSM_INDEX_VERTICAL_HALL = 10;
    public static final int MSM_INDEX_VERTICAL_MOTOR = 7;
    public static final int MSM_INDEX_VERTICAL_UPWARD = 4;
    public static final int OBC_MESSAGE_LOST = 1;
    public static final int OBC_MESSAGE_NORMAL = 0;
    public static final int POWER_LIMIT = 1;
    public static final int POWER_NORMAL = 0;
    public static final int RCTA_STATE_FAIL = 2;
    public static final int RCTA_STATE_OFF = 0;
    public static final int RCTA_STATE_ON = 1;
    public static final int RCW_STATE_FAIL = 2;
    public static final int RCW_STATE_OFF = 0;
    public static final int RCW_STATE_ON = 1;
    public static final int SCU_ACC_FAILURE = 9;
    public static final int SCU_CRUISE_CTRL_ACTIVE_FAILED = 1;
    public static final int SCU_CRUISE_CTRL_CANCELLED_ACTIVELY = 2;
    public static final int SCU_CRUISE_CTRL_CANCELLED_PASSIVELY = 8;
    public static final int SCU_INACTIVE_FOR_DOORS_UNLOCK = 5;
    public static final int SCU_INACTIVE_FOR_EPB_ACTIVE = 7;
    public static final int SCU_INACTIVE_FOR_NOT_D_SHIFT = 6;
    public static final int SCU_INACTIVE_FOR_SETBELT_UNLOCK = 4;
    public static final int SCU_LONG_CTRL_PLEASE_TAKE_OVER = 3;
    public static final int SCU_LONG_CTRL_REMIND_NONE = 0;
    public static final int SIDE_REVERSING_STATE_ACTIVE = 3;
    public static final int SIDE_REVERSING_STATE_NO_FAULT = 0;
    public static final int SIDE_REVERSING_STATE_PERMANENT_FAILURE = 2;
    public static final int SIDE_REVERSING_STATE_TEMPORARY_FAILURE = 1;
    public static final int SRR_BLOCKED = 1;
    public static final int SRR_DYNAMIC_CALIBRATION_RUNNING = 3;
    public static final int SRR_FAILURE = 2;
    public static final int SRR_NORMAL = 0;
    private static final String TAG = "XpDiagnosticManager";
    public static final int TCM_INDEX_OPEN_CIRCUIT = 3;
    public static final int TCM_INDEX_POSITION_SHORT_TO_GROUND = 0;
    public static final int TCM_INDEX_SHORT_TO_BATTERY = 1;
    public static final int TCM_INDEX_SHORT_TO_GROUND = 2;
    public static final int THERMAL_NORMAL = 0;
    public static final int THERMAL_RUNAWAY = 1;
    public static final int TIRE_INDEX_FRONT_LEFT = 0;
    public static final int TIRE_INDEX_FRONT_RIGHT = 1;
    public static final int TIRE_INDEX_REAR_LEFT = 2;
    public static final int TIRE_INDEX_REAR_RIGHT = 3;
    public static final int TIRE_PRESSURE_SENSOR_STATUS_FAILURE = 1;
    public static final int TIRE_PRESSURE_SENSOR_STATUS_NORMAL = 0;
    public static final int TIRE_TEMPERATURE_NORMAL = 0;
    public static final int TIRE_TEMPERATURE_WARNING = 1;
    public static final int TRACTION_BATTERY_SYSTEM_HOT = 1;
    private final CarPropertyManager mCarPropertyMgr;
    private final ArraySet<Integer> mD21CheckedEcusFailureStateIds;
    private final ArraySet<Integer> mDiagPropertyIds;
    private final ArraySet<Integer> mE28CheckedEcusFailureStateIds;
    private final SingleMessageHandler<CarPropertyEvent> mHandler;
    @GuardedBy("this")
    private final SparseArray<CarEcuEventCallbacks> mIdToCallbacksMap;
    private CarPropertyEventListenerToService mListenerToBase;
    private final Object mLock;
    private final IXpDiagnostic mService;
    @GuardedBy("mLock")
    private Map<Integer, CarPropertyConfig> mSupportedPropConfigs;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    /* loaded from: classes.dex */
    public interface XpDiagnosticEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CarPropertyEventListenerToService extends ICarPropertyEventListener.Stub {
        private final WeakReference<XpDiagnosticManager> mMgr;

        CarPropertyEventListenerToService(XpDiagnosticManager mgr) {
            this.mMgr = new WeakReference<>(mgr);
        }

        @Override // android.car.hardware.property.ICarPropertyEventListener
        public void onEvent(List<CarPropertyEvent> events) throws RemoteException {
            XpDiagnosticManager manager = this.mMgr.get();
            if (manager != null) {
                manager.handleEvent(events);
            }
        }
    }

    private static boolean isE28() {
        try {
            boolean ret = "E28".equals(Car.getHardwareCarType());
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public XpDiagnosticManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mLock = new Object();
        this.mListenerToBase = null;
        this.mIdToCallbacksMap = new SparseArray<>();
        this.mDiagPropertyIds = new ArraySet<>(Arrays.asList(560993284, 560993285, 560993296, 560993297, 560993298, 560993299, 560993300, 560993302, 560993303, 560993304, 560993305, 560993306, 560993307, 560993308, 560993301, 560993338, 557849734, 557849735, 557849736, 557849737, 557849738, 557849739, 557849740, 557849741, 557849742, 557854230, 557848587, 557849743, 557849744, 557849745, 557849746, 557849747, 557855247, 557850118, 557850119, 557849618, 557848588, 557849748, 557847089, 557847090, 557847091, 557847092, 557847093, 557847094, 557847095, 557847096, 557847097, 557847098, 557847099, 557847103, 557847104, 557851657, 557851662, 557851663, 557851659, 557851665, 557851661, 557852280, 557852281, 557852163, 557852165, 557852168, 557852166, 557852167, 557852276, 557852277, 557852278, 557852279, 557852199, 557852173, 557852274, 557852275, 557847102, 557851660, 557851658, 557915662, 557915664, 557847105, 557847106, 557847107, 557847109, 557847110, 557847111, 557847112, 557847113, 557912650, 557915286, 557852696, 557852675, 557852162, 557852161, 557852202, 557849134, 557914671, 557912651));
        this.mE28CheckedEcusFailureStateIds = new ArraySet<>(Arrays.asList(557849734, 557849735, 557849736, 557849737, 557849738, 557849739, 557849740, 557849741, 557849742, 557854230, 557848587, 557849743, 557849744, 557849745, 557849746, 557849747, 557855247, 557850118, 557850119, 557849618, 557848588, 557849748, 557847089, 557847090, 557847091, 557847092, 557847093, 557847094, 557847095, 557847096, 557847097, 557847098, 557847099, 557847103, 557847104, 557851657, 557851662, 557851663, 557851659, 557851665, 557851661, 557852280, 557852281, 557852163, 557852165, 557852168, 557852166, 557852167, 557852276, 557852277, 557852278, 557852279, 557852199, 557852173, 557852274, 557852275));
        this.mD21CheckedEcusFailureStateIds = new ArraySet<>(Arrays.asList(557851660, 557851663, 557851658, 557850118, 557850119, 557915662, 557915664, 557847105, 557847106, 557847107, 557847091, 557847109, 557847110, 557847103, 557847111, 557847090, 557847112, 557847113, 557912650, 557849734, 557849735, 557849736, 557849737, 557849742, 557915286, 557852696, 557852675, 557852173, 557852162, 557852161, 557852165, 557852202, 557849134, 557914671, 557912651));
        this.mService = IXpDiagnostic.Stub.asInterface(vehicleService);
        this.mCarPropertyMgr = new CarPropertyManager(service, handler, false, TAG);
        this.mHandler = new SingleMessageHandler<CarPropertyEvent>(handler.getLooper(), 0) { // from class: android.car.diagnostic.XpDiagnosticManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.android.car.internal.SingleMessageHandler
            public void handleEvent(CarPropertyEvent event) {
                CarEcuEventCallbacks listeners;
                CarPropertyValue value = event.getCarPropertyValue();
                if (value == null) {
                    return;
                }
                synchronized (XpDiagnosticManager.this) {
                    listeners = (CarEcuEventCallbacks) XpDiagnosticManager.this.mIdToCallbacksMap.get(event.getCarPropertyValue().getPropertyId());
                }
                if (listeners != null) {
                    switch (event.getEventType()) {
                        case 0:
                            listeners.onPropertyChanged(event);
                            return;
                        case 1:
                            listeners.onErrorEvent(value.getPropertyId(), ((Integer) value.getValue()).intValue());
                            return;
                        default:
                            throw new IllegalArgumentException();
                    }
                }
            }
        };
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEvent(List<CarPropertyEvent> events) {
        this.mHandler.sendEvents(events);
    }

    @Override // android.car.hardware.CarEcuManager
    public synchronized void registerCallback(CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        registerPropCallback(getSupportedPropIds(), callback);
    }

    private boolean isPropertyAllowRegister(CarPropertyConfig c) {
        return ((c.getAccess() & 1) == 0 || c.getChangeMode() == 0) ? false : true;
    }

    @Override // android.car.hardware.CarEcuManager
    public synchronized void unregisterCallback(CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        unregisterPropCallback(getSupportedPropIds(), callback);
    }

    @Override // android.car.hardware.CarEcuManager
    public synchronized void registerPropCallback(Collection<Integer> ids, CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        if (ids == null) {
            Log.e(TAG, "ids is null");
            throw new IllegalArgumentException("input data cannot be null");
        } else if (callback == null) {
            Log.e(TAG, "callback is null");
            throw new IllegalArgumentException("input data cannot be null");
        } else {
            if (XpDebugLog.CAR_DEBUG) {
                Log.i(TAG, "registerPropCallback: " + callback);
            }
            if (this.mIdToCallbacksMap.size() == 0) {
                this.mListenerToBase = new CarPropertyEventListenerToService(this);
            }
            initSupportedPropConfigs();
            for (Integer id : ids) {
                if (XpDebugLog.CAR_DEBUG) {
                    Log.i(TAG, "registerPropCallback id: " + id);
                }
                CarPropertyConfig config = this.mSupportedPropConfigs.get(id);
                if (config != null && isPropertyAllowRegister(config)) {
                    CarEcuEventCallbacks callbacks = this.mIdToCallbacksMap.get(id.intValue());
                    boolean shouldRegister = false;
                    if (callbacks == null) {
                        callbacks = new CarEcuEventCallbacks();
                        this.mIdToCallbacksMap.put(id.intValue(), callbacks);
                        shouldRegister = true;
                    }
                    if (!callbacks.contains(callback)) {
                        callbacks.add(callback);
                    }
                    if (shouldRegister) {
                        try {
                            try {
                                this.mService.registerListener(id.intValue(), 0.0f, this.mListenerToBase);
                            } catch (IllegalStateException e) {
                                CarApiUtil.checkCarNotConnectedExceptionFromCarService(e);
                            }
                        } catch (RemoteException e2) {
                            throw new CarNotConnectedException(e2);
                        }
                    }
                }
            }
        }
    }

    @Override // android.car.hardware.CarEcuManager
    public synchronized void unregisterPropCallback(Collection<Integer> ids, CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        if (XpDebugLog.CAR_DEBUG) {
            Log.i(TAG, "unregisterPropCallback: " + callback);
        }
        if (ids == null) {
            Log.e(TAG, "ids is null");
            throw new IllegalArgumentException("input data cannot be null");
        } else if (callback == null) {
            Log.e(TAG, "callback is null");
            throw new IllegalArgumentException("input data cannot be null");
        } else {
            for (Integer id : ids) {
                if (XpDebugLog.CAR_DEBUG) {
                    Log.i(TAG, "unregisterPropCallback id: " + id);
                }
                CarEcuEventCallbacks callbacks = this.mIdToCallbacksMap.get(id.intValue());
                if (callbacks != null) {
                    callbacks.remove(callback);
                    if (callbacks.isEmpty()) {
                        this.mIdToCallbacksMap.remove(id.intValue());
                        try {
                            try {
                                this.mService.unregisterListener(id.intValue(), this.mListenerToBase);
                            } catch (IllegalStateException e) {
                                CarApiUtil.checkCarNotConnectedExceptionFromCarService(e);
                            }
                        } catch (RemoteException e2) {
                            throw new CarNotConnectedException(e2);
                        }
                    }
                }
            }
            if (this.mIdToCallbacksMap.size() == 0) {
                this.mListenerToBase = null;
            }
        }
    }

    private void initSupportedPropConfigs() throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mSupportedPropConfigs == null) {
                this.mSupportedPropConfigs = new ArrayMap();
                List<CarPropertyConfig> configs = this.mCarPropertyMgr.getPropertyList(getPropertyIds());
                for (CarPropertyConfig c : configs) {
                    this.mSupportedPropConfigs.put(Integer.valueOf(c.getPropertyId()), c);
                }
            }
        }
    }

    private Set<Integer> getSupportedPropIds() throws CarNotConnectedException {
        initSupportedPropConfigs();
        synchronized (this.mLock) {
            if (this.mSupportedPropConfigs != null) {
                return this.mSupportedPropConfigs.keySet();
            }
            return null;
        }
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mDiagPropertyIds;
    }

    public Set<Integer> getCheckedEcusFailureStatesPropertyIds() {
        if (isE28()) {
            return this.mE28CheckedEcusFailureStateIds;
        }
        return this.mD21CheckedEcusFailureStateIds;
    }

    public byte[] getMcuLastDiagCmd() throws Exception {
        return this.mService.getMcuLastDiagCmd();
    }

    public void sendMcuDiagCmdAck(byte[] cmd) throws Exception {
        this.mService.sendMcuDiagCmdAck(cmd);
    }

    public byte[] getAndLastDiagCmd() throws Exception {
        return this.mService.getAndLastDiagCmd();
    }

    public void sendAndDiagCmd(byte[] cmd) throws Exception {
        this.mService.sendAndDiagCmd(cmd);
    }

    public int getSystemDiagContent() throws Exception {
        return this.mService.getSystemDiagContent();
    }

    public void setCarServiceDebugEnabled(boolean on) {
        try {
            this.mService.setCarServiceDebugEnabled(on);
        } catch (RemoteException e) {
            Log.e(TAG, "setCarServiceDebugEnabled failed.");
        }
    }

    public void sendResetPhyToMcu(byte[] cmd) throws Exception {
        this.mService.sendResetPhyToMcu(cmd);
    }

    public void sendTestModeToMcu(byte[] cmd) throws Exception {
        this.mService.sendTestModeToMcu(cmd);
    }

    public void sendCableDiagToMcu(byte[] cmd) throws Exception {
        this.mService.sendCableDiagToMcu(cmd);
    }

    public void sendLinkStatus(byte[] cmd) throws Exception {
        this.mService.sendLinkStatus(cmd);
    }

    public void sendSQIValue(byte[] cmd) throws Exception {
        this.mService.sendSQIValue(cmd);
    }

    public void sendTransmittedPak(byte[] cmd) throws Exception {
        this.mService.sendTransmittedPak(cmd);
    }

    public void sendReceivedPak(byte[] cmd) throws Exception {
        this.mService.sendReceivedPak(cmd);
    }

    public void sendUnexpectedLnkLoss(byte[] cmd) throws Exception {
        this.mService.sendUnexpectedLnkLoss(cmd);
    }

    public void sendInsufficientSQI(byte[] cmd) throws Exception {
        this.mService.sendInsufficientSQI(cmd);
    }

    public void sendIpErr(byte[] cmd) throws Exception {
        this.mService.sendIpErr(cmd);
    }

    public void sendSQIMax(byte[] cmd) throws Exception {
        this.mService.sendSQIMax(cmd);
    }

    public void sendSQI100Base(byte[] cmd) throws Exception {
        this.mService.sendSQI100Base(cmd);
    }

    public void sendSQI1000Base(byte[] cmd) throws Exception {
        this.mService.sendSQI1000Base(cmd);
    }

    public void sendMaterSlaveMode(byte[] cmd) throws Exception {
        this.mService.sendMaterSlaveMode(cmd);
    }

    public byte[] getMaterSlaveSettings() throws Exception {
        return this.mService.getMaterSlaveSettings();
    }

    public void sendMaterSlaveSettingsResponse(byte[] cmd) throws Exception {
        this.mService.sendMaterSlaveSettingsResponse(cmd);
    }

    @Deprecated
    public int getHighBeamFailState() throws Exception {
        return this.mService.getHighBeamFailState();
    }

    public int getHighBeamFailureState() throws Exception {
        return this.mService.getHighBeamFailState();
    }

    public int getLowBeamFailureState() throws Exception {
        return this.mService.getLowBeamFailState();
    }

    public int getLeftTurnLampFailureState() throws Exception {
        return this.mService.getLTurnLampFailState();
    }

    public int getRightTurnLampFailureState() throws Exception {
        return this.mService.getRTurnLampFailState();
    }

    public int getRearFogLampFailureState() throws Exception {
        return this.mService.getRearFogFailState();
    }

    public int getRightDaytimeRunningLightFailureState() throws Exception {
        return this.mService.getRDtrOutputFailState();
    }

    public int getLeftDaytimeRunningLightFailureState() throws Exception {
        return this.mService.getLDtrOutputFailState();
    }

    public int getParkingLampFailureState() throws Exception {
        return this.mService.getParkingLampFailState();
    }

    public int getBcmSystemFailureState() throws Exception {
        return this.mService.getSysErrorWarn();
    }

    public int getLluFailureState() throws Exception {
        return this.mService.getLluErrSt();
    }

    public int getAtlsFailureSate() throws Exception {
        return this.mService.getAtlsErrSt();
    }

    public int getDriverMsmFailureState() throws Exception {
        return this.mService.getMsmdEcuErr();
    }

    public int getDriverMsmVentilationMotorFailureState() throws Exception {
        return this.mService.getMsmdVentilationMotorErr();
    }

    public int getDriverMsmHeatSysFailureState() throws Exception {
        return this.mService.getMsmdHeatSysErr();
    }

    public int getPassengerMsmFailureState() throws Exception {
        return this.mService.getMsmpEcuErr();
    }

    public int getPassengerMsmHeatSysFailureState() throws Exception {
        return this.mService.getMsmpHeatSysErr();
    }

    public int getAvasFailureState() throws Exception {
        return this.mService.getAvasFault();
    }

    public int getTpmsSysFailureState() throws Exception {
        return this.mService.getTpmsSysFaultWarn();
    }

    public int getAbnormalTirePressureState() throws Exception {
        return this.mService.getTpmsAbnormalPrWarn();
    }

    public int getAirbagFailureState() throws Exception {
        return this.mService.getAirbagFaultSt();
    }

    public int getAlsFailureState() throws Exception {
        return this.mService.getAlsErrorSt();
    }

    public int getDhcFailureState() throws Exception {
        return this.mService.getDhcErr();
    }

    public int getEvSysFailureState() throws Exception {
        return this.mService.getVcuEvErrLampDsp();
    }

    public int getEvSysDetailedErrorMessage() throws Exception {
        return this.mService.getVcuEvErrorMessage();
    }

    public int getGearWarningInfo() throws Exception {
        return this.mService.getVcuGearWarning();
    }

    public int getEbsFailureState() throws Exception {
        return this.mService.getVcuEbsErrDsp();
    }

    public int getLiquidHighTempState() throws Exception {
        return this.mService.getVcuLiquidHighTempErr();
    }

    public int getWaterSensorFailureState() throws Exception {
        return this.mService.getVcuWaterSensorErr();
    }

    public int getBCruiseFailureState() throws Exception {
        return this.mService.getVcuBCruiseErr();
    }

    public int getThermalRunawayState() throws Exception {
        return this.mService.getVcuThermoRunawaySt();
    }

    public int getPowerLimitationState() throws Exception {
        return this.mService.getVcuPowerLimitationDsp();
    }

    public int getChargePortHotState() throws Exception {
        return this.mService.getVcuChgPortHotDsp();
    }

    public int getObcMsgLostState() throws Exception {
        return this.mService.getVcuObcMsgLost();
    }

    public int getBatteryDeadState() throws Exception {
        return this.mService.getVcuDeadBatteryFlag();
    }

    public int getEspFailureState() throws Exception {
        return this.mService.getEspEspFault();
    }

    public int getApbFailureState() throws Exception {
        return this.mService.getEspApbErrSt();
    }

    public int getAbsFailureState() throws Exception {
        return this.mService.getEspAbsFault();
    }

    public int getAvhFailureState() throws Exception {
        return this.mService.getEspAvhFault();
    }

    public int getIbtFailureState() throws Exception {
        return this.mService.getIbtFailureLamp();
    }

    public int getEpsFailureState() throws Exception {
        return this.mService.getEpsWarnLamp();
    }

    public int getCdcFailureState() throws Exception {
        return this.mService.getCdcDataUploadSt();
    }

    public int getXpuFailureState() throws Exception {
        return this.mService.getXpuXpuFailState();
    }

    public int getIhbState() throws Exception {
        return this.mService.getScuIhbSw();
    }

    public int getBsdState() throws Exception {
        return this.mService.getScuBsdSw();
    }

    public int getDowState() throws Exception {
        return this.mService.getScuDowSw();
    }

    public int getRctaState() throws Exception {
        return this.mService.getScuRctaSw();
    }

    public int getRcwState() throws Exception {
        return this.mService.getScuRcwSw();
    }

    public int getFlSrrFailureState() throws Exception {
        return this.mService.getSrrFlFailState();
    }

    public int getFrSrrFailureState() throws Exception {
        return this.mService.getSrrFrFailState();
    }

    public int getRlSrrFailureState() throws Exception {
        return this.mService.getSrrRlFailState();
    }

    public int getRrSrrFailureState() throws Exception {
        return this.mService.getSrrRrFailState();
    }

    public int getScuLongCtrlRemindInfo() throws Exception {
        return this.mService.getScuLongCtrlRemind();
    }

    public int getIslcState() throws Exception {
        return this.mService.getScuIslcSw();
    }

    public int getAlcCtrlRemindInfo() throws Exception {
        return this.mService.getScuAlcCtrlRemind();
    }

    public int getApaFailureState() throws Exception {
        return this.mService.getScuIcmApaErrTips();
    }

    public int getFrontIpuFailureState() throws Exception {
        return this.mService.getFrontIpuFailureState();
    }

    public int getRearIpuFailureState() throws Exception {
        return this.mService.getRearIpuFailureState();
    }

    public int getEpbSysFailureState() throws Exception {
        return this.mService.getEpbSysFailureState();
    }

    public int getHdcFailureState() throws Exception {
        return this.mService.getHdcFailureState();
    }

    public int[] getAllTireTemperatureWarnings() throws Exception {
        return this.mService.getAllTireTemperatureWarnings();
    }

    public int[] getAllTirePerssureSensorsFailureStates() throws Exception {
        return this.mService.getAllTirePerssureSensorsFailureStates();
    }

    public int getBatteryOverheatingState() throws Exception {
        return this.mService.getBatteryOverheatingState();
    }

    public int getCcsWorkState() throws Exception {
        return this.mService.getCcsWorkState();
    }

    public int getDcdcFailureState() throws Exception {
        return this.mService.getDcdcFailureState();
    }

    public int getBatteryVoltageLowState() throws Exception {
        return this.mService.getBatteryVoltageLowState();
    }

    public int getElectricMotorSystemHotState() throws Exception {
        return this.mService.getElectricMotorSystemHotState();
    }

    public int getElectricVacuumPumpFailureState() throws Exception {
        return this.mService.getElectricVacuumPumpFailureState();
    }

    public int getHighVoltageRelayAdhesionState() throws Exception {
        return this.mService.getHighVoltageRelayAdhesionState();
    }

    public int getAgsFailureState() throws Exception {
        return this.mService.getAgsFailureState();
    }

    public int[] getTcmMotorFailureStates() throws Exception {
        return this.mService.getTcmMotorFailureStates();
    }

    public int[] getAllMsmModulesFailureStates() throws Exception {
        return this.mService.getAllMsmModulesFailureStates();
    }

    public int getDvrFailureState() throws Exception {
        return this.mService.getDvrFailureState();
    }

    public int getCiuSdcardFailureState() throws Exception {
        return this.mService.getCiuSdcardFailureState();
    }

    public int getLdwState() throws Exception {
        return this.mService.getLdwState();
    }

    public int getAebState() throws Exception {
        return this.mService.getAebState();
    }

    public int getSideReversingState() throws Exception {
        return this.mService.getSideReversingState();
    }

    public int getHvacPtcFailureState() throws Exception {
        return this.mService.getHvacPtcFailureState();
    }

    public int[] getHvacCompressorFailureStates() throws Exception {
        return this.mService.getHvacCompressorFailureStates();
    }

    public int[] getBatteryPtcFailureStates() throws Exception {
        return this.mService.getBatteryPtcFailureStates();
    }

    public static String getServiceName() {
        return Car.XP_DIAGNOSTIC_SERVICE;
    }

    public EcusFailureStates getAllCheckedEcusFailureStates() throws Exception {
        return this.mService.getAllEcusFailureStates();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CarEcuEventCallbacks {
        List<CarEcuManager.CarEcuEventCallback> mCallbacks;
        private long mLastUpdateTime;

        private CarEcuEventCallbacks() {
            this.mLastUpdateTime = -1L;
            this.mCallbacks = new CopyOnWriteArrayList();
        }

        @SuppressLint({"NewApi"})
        void onPropertyChanged(CarPropertyEvent event) {
            if (XpDebugLog.CAR_DEBUG) {
                Log.d(XpDiagnosticManager.TAG, "++onPropertyChanged: " + event);
            }
            long updateTime = event.getCarPropertyValue().getTimestamp();
            final CarPropertyValue<?> value = event.getCarPropertyValue();
            if (updateTime < this.mLastUpdateTime) {
                Log.w(XpDiagnosticManager.TAG, "dropping old property data");
            } else {
                this.mLastUpdateTime = updateTime;
                this.mCallbacks.forEach(new Consumer() { // from class: android.car.diagnostic.-$$Lambda$XpDiagnosticManager$CarEcuEventCallbacks$5wY5S3e0QdwmZuW_-u7tSRvduXE
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        XpDiagnosticManager.CarEcuEventCallbacks.lambda$onPropertyChanged$0(CarPropertyValue.this, (CarEcuManager.CarEcuEventCallback) obj);
                    }
                });
            }
            if (XpDebugLog.CAR_DEBUG) {
                Log.d(XpDiagnosticManager.TAG, "--onPropertyChanged: " + event);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onPropertyChanged$0(CarPropertyValue value, CarEcuManager.CarEcuEventCallback listener) {
            if (XpDebugLog.CAR_DEBUG) {
                Log.d(XpDiagnosticManager.TAG, "++++onPropertyChanged: " + value + " callback:" + listener);
            }
            listener.onChangeEvent(value);
            if (XpDebugLog.CAR_DEBUG) {
                Log.d(XpDiagnosticManager.TAG, "----onPropertyChanged: " + value + " callback:" + listener);
            }
        }

        @SuppressLint({"NewApi"})
        void onErrorEvent(final int propertyId, final int errorCode) {
            if (XpDebugLog.CAR_DEBUG) {
                Log.d(XpDiagnosticManager.TAG, "++onErrorEvent propertyId: " + propertyId + " errorCode:" + errorCode);
            }
            this.mCallbacks.forEach(new Consumer() { // from class: android.car.diagnostic.-$$Lambda$XpDiagnosticManager$CarEcuEventCallbacks$mkSkM-UdQExiPTBJNyhmxw-tS1Y
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    XpDiagnosticManager.CarEcuEventCallbacks.lambda$onErrorEvent$1(propertyId, errorCode, (CarEcuManager.CarEcuEventCallback) obj);
                }
            });
            if (XpDebugLog.CAR_DEBUG) {
                Log.d(XpDiagnosticManager.TAG, "--onErrorEvent propertyId: " + propertyId + " errorCode:" + errorCode);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onErrorEvent$1(int propertyId, int errorCode, CarEcuManager.CarEcuEventCallback listener) {
            if (XpDebugLog.CAR_DEBUG) {
                Log.d(XpDiagnosticManager.TAG, "++++onErrorEvent propertyId: " + propertyId + " errorCode:" + errorCode + " callback: " + listener);
            }
            listener.onErrorEvent(propertyId, errorCode);
            if (XpDebugLog.CAR_DEBUG) {
                Log.d(XpDiagnosticManager.TAG, "----onErrorEvent propertyId: " + propertyId + " errorCode:" + errorCode + " callback: " + listener);
            }
        }

        public boolean contains(CarEcuManager.CarEcuEventCallback callback) {
            return this.mCallbacks.contains(callback);
        }

        public void add(CarEcuManager.CarEcuEventCallback callback) {
            this.mCallbacks.add(callback);
        }

        public void remove(CarEcuManager.CarEcuEventCallback callback) {
            this.mCallbacks.remove(callback);
        }

        public boolean isEmpty() {
            return this.mCallbacks.isEmpty();
        }
    }
}
