package android.car;

import android.annotation.SystemApi;
import android.app.Activity;
import android.app.Service;
import android.car.ICar;
import android.car.cluster.CarInstrumentClusterManager;
import android.car.content.pm.CarPackageManager;
import android.car.diagnostic.AospCarDiagnosticManager;
import android.car.diagnostic.XpDiagnosticManager;
import android.car.drivingstate.CarDrivingStateManager;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.car.hardware.CarSensorManager;
import android.car.hardware.CarVendorExtensionManager;
import android.car.hardware.amp.CarAmpManager;
import android.car.hardware.atl.CarAtlManager;
import android.car.hardware.avas.CarAvasManager;
import android.car.hardware.avm.CarAvmManager;
import android.car.hardware.bcm.CarBcmManager;
import android.car.hardware.bms.CarBmsManager;
import android.car.hardware.cabin.CarCabinManager;
import android.car.hardware.can.CarCanManager;
import android.car.hardware.ccs.CarCcsManager;
import android.car.hardware.cdc.CarCdcManager;
import android.car.hardware.ciu.CarCiuManager;
import android.car.hardware.condition.CarConditionManager;
import android.car.hardware.dcdc.CarDcdcManager;
import android.car.hardware.dhc.CarDhcManager;
import android.car.hardware.eps.CarEpsManager;
import android.car.hardware.esp.CarEspManager;
import android.car.hardware.hvac.CarHvacManager;
import android.car.hardware.icm.CarIcmManager;
import android.car.hardware.imu.CarImuManager;
import android.car.hardware.input.CarInputManager;
import android.car.hardware.ipu.CarIpuManager;
import android.car.hardware.llu.CarLluManager;
import android.car.hardware.mcu.CarMcuManager;
import android.car.hardware.msb.CarMsbManager;
import android.car.hardware.msm.CarMsmManager;
import android.car.hardware.power.CarPowerManager;
import android.car.hardware.property.CarPropertyManager;
import android.car.hardware.property.ICarProperty;
import android.car.hardware.radio.CarRadioManager;
import android.car.hardware.scu.CarScuManager;
import android.car.hardware.spc.CarSpcManager;
import android.car.hardware.srs.CarSrsManager;
import android.car.hardware.tbox.CarTboxManager;
import android.car.hardware.tpms.CarTpmsManager;
import android.car.hardware.vcu.CarVcuManager;
import android.car.hardware.vpm.CarVpmManager;
import android.car.hardware.xpu.CarXpuManager;
import android.car.intelligent.CarIntelligentEngineManager;
import android.car.media.CarAudioManager;
import android.car.media.CarMediaManager;
import android.car.navigation.CarNavigationStatusManager;
import android.car.settings.CarConfigurationManager;
import android.car.storagemonitoring.CarStorageMonitoringManager;
import android.car.test.CarTestManagerBinderWrapper;
import android.car.trust.CarTrustAgentEnrollmentManager;
import android.car.vms.VmsSubscriberManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.TransactionTooLargeException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.Preconditions;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class Car {
    private static final String AOSP_DIAGNOSTIC_SERVICE = "diagnostic";
    public static final String APP_FOCUS_SERVICE = "app_focus";
    public static final String AUDIO_SERVICE = "audio";
    public static final String BLUETOOTH_SERVICE = "car_bluetooth";
    @SystemApi
    @Deprecated
    public static final String CABIN_SERVICE = "cabin";
    public static final String CAR_BUGREPORT_SERVICE = "car_bugreport";
    public static final String CAR_CATEGORY_NAVIGATION = "android.car.cluster.NAVIGATION";
    @Deprecated
    public static final String CAR_CDU_A1 = "A1";
    public static final String CAR_CDU_A2 = "A2";
    public static final String CAR_CDU_A3 = "A3";
    public static final String CAR_CDU_Q1 = "Q1";
    public static final String CAR_CDU_Q2 = "Q2";
    public static final String CAR_CDU_Q3 = "Q3";
    public static final String CAR_CDU_Q4 = "Q4";
    public static final String CAR_CDU_Q5 = "Q5";
    public static final String CAR_CDU_Q6 = "Q6";
    public static final String CAR_CDU_Q7 = "Q7";
    public static final String CAR_CDU_Q8_E28A = "Q8";
    public static final String CAR_CDU_Q9_F30 = "Q9";
    public static final String CAR_CDU_TYPE_A1_D20 = "A1";
    public static final String CAR_CDU_TYPE_A2_D20P = "A2";
    public static final String CAR_CDU_TYPE_A3_D21 = "A3";
    public static final String CAR_CDU_TYPE_Q1_E28 = "Q1";
    public static final String CAR_CDU_TYPE_Q2_D21 = "Q2";
    public static final String CAR_CDU_TYPE_Q3_D55 = "Q3";
    public static final String CAR_CDU_TYPE_Q3_D55A = "Q3A";
    public static final String CAR_CDU_TYPE_Q5_D20 = "Q5";
    public static final String CAR_CDU_TYPE_Q6_D22 = "Q6";
    public static final String CAR_CDU_TYPE_Q7_E38 = "Q7";
    public static final String CAR_CDU_TYPE_Q7_E38A = "Q7A";
    public static final String CAR_CDU_TYPE_Q8_E28A = "Q8";
    public static final String CAR_CDU_TYPE_Q9_F30 = "Q9";
    public static final String CAR_CDU_TYPE_QB_H93 = "QB";
    @SystemApi
    public static final String CAR_CONFIGURATION_SERVICE = "configuration";
    @SystemApi
    public static final String CAR_DRIVING_STATE_SERVICE = "drivingstate";
    public static final String CAR_EXTRA_BROWSE_SERVICE_FOR_SESSION = "android.media.session.BROWSE_SERVICE";
    @SystemApi
    public static final String CAR_EXTRA_CLUSTER_ACTIVITY_STATE = "android.car.cluster.ClusterActivityState";
    public static final String CAR_EXTRA_MEDIA_COMPONENT = "android.car.intent.extra.MEDIA_COMPONENT";
    public static final String CAR_EXTRA_MEDIA_PACKAGE = "android.car.intent.extra.MEDIA_PACKAGE";
    private static final int CAR_FACELIFT_ONE = 1;
    @Deprecated
    public static final String CAR_INSTRUMENT_CLUSTER_SERVICE = "cluster_service";
    @SystemApi
    public static final String CAR_INTELLIGENT_SERVICE = "intelligent";
    public static final String CAR_INTENT_ACTION_MEDIA_TEMPLATE = "android.car.intent.action.MEDIA_TEMPLATE";
    public static final String CAR_MEDIA_SERVICE = "car_media";
    public static final String CAR_NAVIGATION_SERVICE = "car_navigation_service";
    public static final String CAR_NOT_CONNECTED_EXCEPTION_MSG = "CarNotConnected";
    private static final int CAR_ORIGIN = 0;
    public static final String CAR_REGION_EU = "EU";
    public static final String CAR_REGION_ZH = "ZH";
    private static final long CAR_SERVICE_BINDER_POLLING_INTERVAL_MS = 50;
    private static final long CAR_SERVICE_BINDER_POLLING_MAX_RETRY = 100;
    public static final String CAR_SERVICE_BINDER_SERVICE_NAME = "car_service";
    private static final long CAR_SERVICE_BIND_MAX_RETRY = 20;
    private static final long CAR_SERVICE_BIND_RETRY_INTERVAL_MS = 500;
    private static final String CAR_SERVICE_CLASS = "com.android.car.CarService";
    public static final String CAR_SERVICE_INTERFACE_NAME = "android.car.ICar";
    private static final String CAR_SERVICE_PACKAGE = "com.android.car";
    public static final String CAR_STAGE_A = "A";
    public static final String CAR_STAGE_B = "B";
    public static final String CAR_STAGE_C = "C";
    public static final String CAR_STAGE_D = "D";
    public static final String CAR_STAGE_E = "E";
    public static final String CAR_STAGE_F = "F";
    public static final String CAR_STAGE_P = "P";
    public static final String CAR_STAGE_V = "V";
    public static final String CAR_STAGE_X = "X";
    @SystemApi
    public static final String CAR_TRUST_AGENT_ENROLLMENT_SERVICE = "trust_enroll";
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
    @Deprecated
    public static final String CAR_TYPE_D21A = "D21A";
    @SystemApi
    @Deprecated
    public static final String CAR_TYPE_D21B = "D21B";
    @SystemApi
    public static final String CAR_TYPE_D21V = "D21V";
    @Deprecated
    public static final String CAR_TYPE_D22 = "D22";
    @Deprecated
    public static final String CAR_TYPE_D22V = "D22V";
    @Deprecated
    public static final String CAR_TYPE_D25 = "D25";
    @Deprecated
    public static final String CAR_TYPE_D55 = "D55";
    @Deprecated
    public static final String CAR_TYPE_D55V = "D55V";
    public static final String CAR_TYPE_E28 = "E28";
    public static final String CAR_TYPE_E28V = "E28V";
    @Deprecated
    public static final String CAR_TYPE_E36 = "E36";
    @Deprecated
    public static final String CAR_TYPE_E38 = "E38";
    @Deprecated
    public static final String CAR_TYPE_E38V = "E38V";
    @Deprecated
    public static final String CAR_TYPE_F30 = "F30";
    @Deprecated
    public static final String CAR_TYPE_F30V = "F30V";
    @SystemApi
    public static final String CAR_UX_RESTRICTION_SERVICE = "uxrestriction";
    public static final long CAR_WAIT_TIMEOUT_DO_NOT_WAIT = 0;
    public static final long CAR_WAIT_TIMEOUT_WAIT_FOREVER = -1;
    public static final int CONNECTION_TYPE_EMBEDDED = 5;
    private static final boolean DBG = false;
    private static final String EXTENDED_CAR_TYPE = "persist.sys.xiaopeng.extendedCarType";
    private static final String HARDWARE_SOFTWARE_VERSION_PROPERTY = "ro.xiaopeng.software";
    private static final String HARDWARE_VERSION_PROPERTY = "ro.boot.hw_version";
    public static final String HVAC_SERVICE = "hvac";
    public static final String INFO_SERVICE = "info";
    public static final String PACKAGE_SERVICE = "package";
    public static final String PERMISSION_BIND_VMS_CLIENT = "android.car.permission.BIND_VMS_CLIENT";
    @SystemApi
    public static final String PERMISSION_CAR_CONTROL_AUDIO_SETTINGS = "android.car.permission.CAR_CONTROL_AUDIO_SETTINGS";
    @SystemApi
    public static final String PERMISSION_CAR_CONTROL_AUDIO_VOLUME = "android.car.permission.CAR_CONTROL_AUDIO_VOLUME";
    @SystemApi
    public static final String PERMISSION_CAR_DIAGNOSTIC_CLEAR = "android.car.permission.CLEAR_CAR_DIAGNOSTICS";
    @SystemApi
    public static final String PERMISSION_CAR_DIAGNOSTIC_READ_ALL = "android.car.permission.CAR_DIAGNOSTICS";
    public static final String PERMISSION_CAR_DISPLAY_IN_CLUSTER = "android.car.permission.CAR_DISPLAY_IN_CLUSTER";
    @SystemApi
    public static final String PERMISSION_CAR_DRIVING_STATE = "android.car.permission.CAR_DRIVING_STATE";
    @SystemApi
    public static final String PERMISSION_CAR_DYNAMICS_STATE = "android.car.permission.CAR_DYNAMICS_STATE";
    @SystemApi
    public static final String PERMISSION_CAR_ENGINE_DETAILED = "android.car.permission.CAR_ENGINE_DETAILED";
    @SystemApi
    public static final String PERMISSION_CAR_ENROLL_TRUST = "android.car.permission.CAR_ENROLL_TRUST";
    @SystemApi
    public static final String PERMISSION_CAR_INFO = "android.car.permission.CAR_INFO";
    @SystemApi
    public static final String PERMISSION_CAR_INSTRUMENT_CLUSTER_CONTROL = "android.car.permission.CAR_INSTRUMENT_CLUSTER_CONTROL";
    @SystemApi
    public static final String PERMISSION_CAR_NAVIGATION_MANAGER = "android.car.permission.CAR_NAVIGATION_MANAGER";
    @SystemApi
    public static final String PERMISSION_CAR_POWER = "android.car.permission.CAR_POWER";
    @SystemApi
    public static final String PERMISSION_CAR_PROJECTION = "android.car.permission.CAR_PROJECTION";
    @SystemApi
    public static final String PERMISSION_CAR_PROJECTION_STATUS = "android.car.permission.ACCESS_CAR_PROJECTION_STATUS";
    @SystemApi
    public static final String PERMISSION_CAR_TEST_SERVICE = "android.car.permission.CAR_TEST_SERVICE";
    public static final String PERMISSION_CAR_UX_RESTRICTIONS_CONFIGURATION = "android.car.permission.CAR_UX_RESTRICTIONS_CONFIGURATION";
    @SystemApi
    public static final String PERMISSION_CONTROL_APP_BLOCKING = "android.car.permission.CONTROL_APP_BLOCKING";
    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_CLIMATE = "android.car.permission.CONTROL_CAR_CLIMATE";
    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_DOORS = "android.car.permission.CONTROL_CAR_DOORS";
    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_MIRRORS = "android.car.permission.CONTROL_CAR_MIRRORS";
    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_SEATS = "android.car.permission.CONTROL_CAR_SEATS";
    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_WINDOWS = "android.car.permission.CONTROL_CAR_WINDOWS";
    public static final String PERMISSION_CONTROL_DISPLAY_UNITS = "android.car.permission.CONTROL_CAR_DISPLAY_UNITS";
    @SystemApi
    public static final String PERMISSION_CONTROL_EXTERIOR_LIGHTS = "android.car.permission.CONTROL_CAR_EXTERIOR_LIGHTS";
    public static final String PERMISSION_CONTROL_INTERIOR_LIGHTS = "android.car.permission.CONTROL_CAR_INTERIOR_LIGHTS";
    public static final String PERMISSION_ENERGY = "android.car.permission.CAR_ENERGY";
    public static final String PERMISSION_ENERGY_PORTS = "android.car.permission.CAR_ENERGY_PORTS";
    @SystemApi
    public static final String PERMISSION_EXTERIOR_ENVIRONMENT = "android.car.permission.CAR_EXTERIOR_ENVIRONMENT";
    @SystemApi
    public static final String PERMISSION_EXTERIOR_LIGHTS = "android.car.permission.CAR_EXTERIOR_LIGHTS";
    public static final String PERMISSION_IDENTIFICATION = "android.car.permission.CAR_IDENTIFICATION";
    @SystemApi
    public static final String PERMISSION_MILEAGE = "android.car.permission.CAR_MILEAGE";
    @SystemApi
    public static final String PERMISSION_MOCK_VEHICLE_HAL = "android.car.permission.CAR_MOCK_VEHICLE_HAL";
    @SystemApi
    public static final String PERMISSION_POWERTRAIN = "android.car.permission.CAR_POWERTRAIN";
    public static final String PERMISSION_READ_DISPLAY_UNITS = "android.car.permission.READ_CAR_DISPLAY_UNITS";
    public static final String PERMISSION_READ_INTERIOR_LIGHTS = "android.car.permission.READ_CAR_INTERIOR_LIGHTS";
    public static final String PERMISSION_READ_STEERING_STATE = "android.car.permission.READ_CAR_STEERING";
    @SystemApi
    public static final String PERMISSION_RECEIVE_CAR_AUDIO_DUCKING_EVENTS = "android.car.permission.RECEIVE_CAR_AUDIO_DUCKING_EVENTS";
    public static final String PERMISSION_SPEED = "android.car.permission.CAR_SPEED";
    @SystemApi
    public static final String PERMISSION_STORAGE_MONITORING = "android.car.permission.STORAGE_MONITORING";
    @SystemApi
    public static final String PERMISSION_TIRES = "android.car.permission.CAR_TIRES";
    @SystemApi
    public static final String PERMISSION_VENDOR_EXTENSION = "android.car.permission.CAR_VENDOR_EXTENSION";
    @SystemApi
    public static final String PERMISSION_VMS_PUBLISHER = "android.car.permission.VMS_PUBLISHER";
    @SystemApi
    public static final String PERMISSION_VMS_SUBSCRIBER = "android.car.permission.VMS_SUBSCRIBER";
    @SystemApi
    public static final String POWER_SERVICE = "power";
    private static final String PRIVATE_UUID_CODE_PROPERTY = "persist.sys.hardware.uuid";
    @SystemApi
    public static final String PROJECTION_SERVICE = "projection";
    @SystemApi
    public static final String PROPERTY_SERVICE = "property";
    public static final String REGION_EU = "EU";
    @Deprecated
    public static final String SENSOR_SERVICE = "sensor";
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_DISCONNECTED = 0;
    @SystemApi
    public static final String STORAGE_MONITORING_SERVICE = "storage_monitoring";
    private static final String SYSTEM_BOOT_FIRST_PROPERTY = "persist.sys.boot.first";
    @SystemApi
    public static final String TEST_SERVICE = "car-service-test";
    @SystemApi
    @Deprecated
    public static final String VENDOR_EXTENSION_SERVICE = "vendor_extension";
    @SystemApi
    public static final String VMS_SUBSCRIBER_SERVICE = "vehicle_map_subscriber_service";
    @SystemApi
    public static final String XP_AMP_SERVICE = "xp_amp";
    @SystemApi
    public static final String XP_ATL_SERVICE = "xp_atl";
    public static final String XP_AVAS_SERVICE = "xp_avas";
    public static final String XP_AVM_SERVICE = "xp_avm";
    public static final String XP_BCM_SERVICE = "xp_bcm";
    public static final String XP_BMS_SERVICE = "xp_bms";
    @SystemApi
    public static final String XP_CAN_SERVICE = "xp_can";
    public static final String XP_CAR_API_VERSION = "1.0_20230301";
    public static final String XP_CCS_SERVICE = "xp_ccs";
    public static final String XP_CDC_SERVICE = "xp_cdc";
    @SystemApi
    public static final String XP_CIU_SERVICE = "xp_ciu";
    public static final String XP_CONDITION_SERVICE = "xp_condition";
    public static final String XP_DCDC_SERVICE = "xp_dcdc";
    public static final String XP_DHC_SERVICE = "xp_dhc";
    @SystemApi
    public static final String XP_DIAGNOSTIC_SERVICE = "xp_diagnostic";
    public static final String XP_EPS_SERVICE = "xp_eps";
    public static final String XP_ESP_SERVICE = "xp_esp";
    @SystemApi
    public static final String XP_ICM_SERVICE = "xp_icm";
    @SystemApi
    public static final String XP_IMU_SERVICE = "xp_imu";
    @SystemApi
    public static final String XP_INPUT_SERVICE = "xp_input";
    @SystemApi
    public static final String XP_IPU_SERVICE = "xp_ipu";
    @SystemApi
    public static final String XP_LLU_SERVICE = "xp_llu";
    public static final String XP_MCU_SERVICE = "xp_mcu";
    public static final String XP_MSB_SERVICE = "xp_msb";
    public static final String XP_MSM_SERVICE = "xp_msm";
    @SystemApi
    public static final String XP_RADIO_SERVICE = "xp_radio";
    @SystemApi
    public static final String XP_SCU_SERVICE = "xp_scu";
    public static final String XP_SHARED_MEMORY_SERVICE = "xp_shared_memory";
    @SystemApi
    public static final String XP_SPC_SERVICE = "xp_spc";
    public static final String XP_SRS_SERVICE = "xp_srs";
    public static final String XP_TBOX_SERVICE = "xp_tbox";
    public static final String XP_TIME_SERVICE = "xp_time";
    public static final String XP_TPMS_SERVICE = "xp_tpms";
    public static final String XP_VCU_SERVICE = "xp_vcu";
    public static final String XP_VEHICLE_SERVICE = "xp_vehicle";
    @SystemApi
    public static final String XP_VPM_SERVICE = "xp_vpm";
    @SystemApi
    public static final String XP_XPU_SERVICE = "xp_xpu";
    @GuardedBy({"mLock"})
    private int mConnectionRetryCount;
    private final Runnable mConnectionRetryFailedRunnable;
    private final Runnable mConnectionRetryRunnable;
    @GuardedBy({"mLock"})
    private int mConnectionState;
    private final Exception mConstructionStack;
    private final Context mContext;
    private final Handler mEventHandler;
    @GuardedBy({"mLock"})
    private IBinder mIXpService;
    private final Object mLock;
    private final Handler mMainThreadEventHandler;
    private String mPackageName;
    @GuardedBy({"mLock"})
    private ICar mService;
    @GuardedBy({"mLock"})
    private boolean mServiceBound;
    private final ServiceConnection mServiceConnectionListener;
    private final ServiceConnection mServiceConnectionListenerClient;
    @GuardedBy({"mLock"})
    private final HashMap<String, CarManagerBase> mServiceMap;
    private final CarServiceLifecycleListener mStatusChangeCallback;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CarRegion {
    }

    /* loaded from: classes.dex */
    public interface CarServiceLifecycleListener {
        void onLifecycleChanged(Car car, boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CarStage {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CduType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ConnectionType {
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface StateTypeEnum {
    }

    @Deprecated
    public static Car createCar(Context context, ServiceConnection serviceConnectionListener, Handler handler) {
        assertNonNullContext(context);
        if (!context.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
            Log.e(CarLibLog.TAG_CAR, "FEATURE_AUTOMOTIVE not declared while android.car is used");
            return null;
        }
        try {
            return new Car(context, null, serviceConnectionListener, null, handler);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Deprecated
    public static Car createCar(Context context, ServiceConnection serviceConnectionListener) {
        return createCar(context, serviceConnectionListener, null);
    }

    public static Car createCar(Context context) {
        return createCar(context, (Handler) null);
    }

    public static Car createCar(Context context, Handler handler) {
        assertNonNullContext(context);
        Car car = null;
        boolean started = false;
        int retryCount = 0;
        while (true) {
            IBinder service = ServiceManager.getService(CAR_SERVICE_BINDER_SERVICE_NAME);
            if (car == null) {
                car = new Car(context, ICar.Stub.asInterface(service), null, null, handler);
            }
            if (service != null) {
                if (!started) {
                    car.startCarService();
                    return car;
                }
                synchronized (car) {
                    if (car.mService == null) {
                        car.mService = ICar.Stub.asInterface(service);
                        Log.w(CarLibLog.TAG_CAR, "waited for car_service (ms):" + (retryCount * CAR_SERVICE_BINDER_POLLING_INTERVAL_MS), new RuntimeException());
                        setIXpService(car);
                    }
                    car.mConnectionState = 2;
                }
                return car;
            }
            if (!started) {
                car.startCarService();
                started = true;
            }
            retryCount++;
            if (retryCount > CAR_SERVICE_BINDER_POLLING_MAX_RETRY) {
                Log.e(CarLibLog.TAG_CAR, "cannot get car_service, waited for car service (ms):5000", new RuntimeException());
                return null;
            }
            try {
                Thread.sleep(CAR_SERVICE_BINDER_POLLING_INTERVAL_MS);
            } catch (InterruptedException e) {
                Log.e(CarLibLog.TAG_CAR, "interrupted while waiting for car_service", new RuntimeException());
                return null;
            }
        }
    }

    public static Car createCar(Context context, Handler handler, long waitTimeoutMs, CarServiceLifecycleListener statusChangeListener) {
        Car car;
        assertNonNullContext(context);
        Preconditions.checkNotNull(statusChangeListener);
        Car car2 = null;
        boolean started = false;
        int retryCount = 0;
        long maxRetryCount = 0;
        if (waitTimeoutMs > 0) {
            maxRetryCount = waitTimeoutMs / CAR_SERVICE_BINDER_POLLING_INTERVAL_MS;
            if (maxRetryCount == 0) {
                maxRetryCount = 1;
            }
        }
        boolean isMainThread = Looper.myLooper() == Looper.getMainLooper();
        while (true) {
            IBinder service = ServiceManager.getService(CAR_SERVICE_BINDER_SERVICE_NAME);
            if (car2 != null) {
                car = car2;
            } else {
                Car car3 = new Car(context, ICar.Stub.asInterface(service), null, statusChangeListener, handler);
                car = car3;
            }
            if (service != null) {
                if (!started) {
                    car.dispatchCarReadyToMainThread(isMainThread);
                    car.startCarService();
                    return car;
                }
                synchronized (car.mLock) {
                    try {
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append("waited for car_service (ms):");
                            long maxRetryCount2 = retryCount;
                            sb.append(maxRetryCount2 * CAR_SERVICE_BINDER_POLLING_INTERVAL_MS);
                            Log.w(CarLibLog.TAG_CAR, sb.toString(), new RuntimeException());
                            if (car.mService != null) {
                                return car;
                            }
                            car.mService = ICar.Stub.asInterface(service);
                            car.mConnectionState = 2;
                            setIXpService(car);
                            car.dispatchCarReadyToMainThread(isMainThread);
                            return car;
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
            }
            long maxRetryCount3 = maxRetryCount;
            if (!started) {
                car.startCarService();
                started = true;
            }
            retryCount++;
            if (waitTimeoutMs < 0 && retryCount >= CAR_SERVICE_BINDER_POLLING_MAX_RETRY && retryCount % CAR_SERVICE_BINDER_POLLING_MAX_RETRY == 0) {
                Log.w(CarLibLog.TAG_CAR, "car_service not ready, waited for car service (ms):" + (retryCount * CAR_SERVICE_BINDER_POLLING_INTERVAL_MS), new RuntimeException());
            } else if (waitTimeoutMs >= 0 && retryCount > maxRetryCount3) {
                if (waitTimeoutMs > 0) {
                    Log.w(CarLibLog.TAG_CAR, "car_service not ready, waited for car service (ms):" + waitTimeoutMs, new RuntimeException());
                }
                return car;
            }
            try {
                Thread.sleep(CAR_SERVICE_BINDER_POLLING_INTERVAL_MS);
                car2 = car;
                maxRetryCount = maxRetryCount3;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Log.w(CarLibLog.TAG_CAR, "interrupted", new RuntimeException());
                return car;
            }
        }
    }

    private static void setIXpService(Car car) {
        ICar iCar = car.mService;
        if (iCar != null) {
            try {
                car.mIXpService = iCar.getCarService(XP_VEHICLE_SERVICE);
            } catch (RemoteException e) {
                car.handleRemoteExceptionFromCarService(e);
                Log.e(CarLibLog.TAG_CAR, "get xp service failed! " + e);
            }
        }
    }

    private static void assertNonNullContext(Context context) {
        Preconditions.checkNotNull(context);
        if ((context instanceof ContextWrapper) && ((ContextWrapper) context).getBaseContext() == null) {
            throw new NullPointerException("ContextWrapper with null base passed as Context, forgot to set base Context?");
        }
    }

    private void dispatchCarReadyToMainThread(boolean isMainThread) {
        if (isMainThread) {
            this.mStatusChangeCallback.onLifecycleChanged(this, true);
        } else {
            this.mMainThreadEventHandler.post(new Runnable() { // from class: android.car.-$$Lambda$Car$Y2U0t8cCv-IWFLzW1mxSSFa_QVc
                @Override // java.lang.Runnable
                public final void run() {
                    Car.this.lambda$dispatchCarReadyToMainThread$0$Car();
                }
            });
        }
    }

    public /* synthetic */ void lambda$dispatchCarReadyToMainThread$0$Car() {
        this.mStatusChangeCallback.onLifecycleChanged(this, true);
    }

    private Car(Context context, ICar service, ServiceConnection serviceConnectionListener, CarServiceLifecycleListener statusChangeListener, Handler handler) {
        this.mPackageName = "";
        this.mLock = new Object();
        this.mConnectionRetryRunnable = new Runnable() { // from class: android.car.Car.1
            @Override // java.lang.Runnable
            public void run() {
                Car.this.startCarService();
            }
        };
        this.mConnectionRetryFailedRunnable = new Runnable() { // from class: android.car.Car.2
            @Override // java.lang.Runnable
            public void run() {
                Car.this.mServiceConnectionListener.onServiceDisconnected(new ComponentName(Car.CAR_SERVICE_PACKAGE, Car.CAR_SERVICE_CLASS));
            }
        };
        this.mServiceConnectionListener = new ServiceConnection() { // from class: android.car.Car.3
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service2) {
                synchronized (Car.this.mLock) {
                    ICar newService = ICar.Stub.asInterface(service2);
                    if (newService != null) {
                        if (Car.this.mService == null || !Car.this.mService.asBinder().equals(newService.asBinder())) {
                            Car.this.mConnectionState = 2;
                            Car.this.mService = newService;
                            try {
                                Car.this.mIXpService = Car.this.mService.getCarService(Car.XP_VEHICLE_SERVICE);
                            } catch (RemoteException e) {
                                Car.this.handleRemoteExceptionFromCarService(e);
                                Log.e(CarLibLog.TAG_CAR, "get car_service onServiceConnected " + Car.this.mPackageName + " failed! " + e);
                            }
                            Log.i(CarLibLog.TAG_CAR, "get car_service onServiceConnected " + Car.this.mPackageName + " state : " + Car.this.mConnectionState);
                            if (Car.this.mStatusChangeCallback != null) {
                                Car.this.mStatusChangeCallback.onLifecycleChanged(Car.this, true);
                                return;
                            } else if (Car.this.mServiceConnectionListenerClient != null) {
                                Car.this.mServiceConnectionListenerClient.onServiceConnected(name, service2);
                                return;
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                    Log.wtf(CarLibLog.TAG_CAR, "null binder service", new RuntimeException());
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                synchronized (Car.this.mLock) {
                    if (Car.this.mConnectionState == 0) {
                        return;
                    }
                    Car.this.handleCarDisconnectLocked();
                    Log.i(CarLibLog.TAG_CAR, "get car_service onServiceDisconnected " + Car.this.mPackageName + " state : " + Car.this.mConnectionState);
                    if (Car.this.mStatusChangeCallback != null) {
                        Car.this.mStatusChangeCallback.onLifecycleChanged(Car.this, false);
                    } else if (Car.this.mServiceConnectionListenerClient != null) {
                        Car.this.mServiceConnectionListenerClient.onServiceDisconnected(name);
                    } else {
                        Car.this.finishClient();
                    }
                }
            }
        };
        this.mServiceMap = new HashMap<>();
        this.mContext = context;
        if (context != null) {
            this.mPackageName = context.getPackageName();
        }
        this.mEventHandler = determineEventHandler(handler);
        this.mMainThreadEventHandler = determineMainThreadEventHandler(this.mEventHandler);
        this.mService = service;
        if (service != null) {
            this.mConnectionState = 2;
            try {
                this.mIXpService = this.mService.getCarService(XP_VEHICLE_SERVICE);
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
                Log.e(CarLibLog.TAG_CAR, "get xp service failed! " + e);
            }
        } else {
            this.mConnectionState = 0;
        }
        this.mServiceConnectionListenerClient = serviceConnectionListener;
        this.mStatusChangeCallback = statusChangeListener;
        if (serviceConnectionListener == null && statusChangeListener == null) {
            this.mConstructionStack = new RuntimeException();
        } else {
            this.mConstructionStack = null;
        }
    }

    public Car(Context context, ICar service, Handler handler) {
        this(context, service, null, null, handler);
    }

    private static Handler determineMainThreadEventHandler(Handler eventHandler) {
        Looper mainLooper = Looper.getMainLooper();
        return eventHandler.getLooper() == mainLooper ? eventHandler : new Handler(mainLooper);
    }

    private static Handler determineEventHandler(Handler handler) {
        if (handler == null) {
            Looper looper = Looper.getMainLooper();
            return new Handler(looper);
        }
        return handler;
    }

    @Deprecated
    public void connect() throws IllegalStateException {
        synchronized (this.mLock) {
            if (this.mConnectionState != 0) {
                throw new IllegalStateException("already connected or connecting");
            }
            this.mConnectionState = 1;
            startCarService();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCarDisconnectLocked() {
        if (this.mConnectionState == 0) {
            return;
        }
        this.mEventHandler.removeCallbacks(this.mConnectionRetryRunnable);
        this.mMainThreadEventHandler.removeCallbacks(this.mConnectionRetryFailedRunnable);
        this.mConnectionRetryCount = 0;
        tearDownCarManagersLocked();
        this.mService = null;
        this.mIXpService = null;
        this.mConnectionState = 0;
    }

    public void disconnect() {
        synchronized (this.mLock) {
            handleCarDisconnectLocked();
            if (this.mServiceBound) {
                this.mContext.unbindService(this.mServiceConnectionListener);
                this.mServiceBound = false;
            }
        }
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mService != null;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            z = true;
            if (this.mConnectionState != 1) {
                z = false;
            }
        }
        return z;
    }

    @VisibleForTesting
    public ServiceConnection getServiceConnectionListener() {
        return this.mServiceConnectionListener;
    }

    public Object getCarManager(String serviceName) throws CarNotConnectedException {
        getICarOrThrow();
        synchronized (this.mLock) {
            CarManagerBase manager = this.mServiceMap.get(serviceName);
            if (manager == null) {
                try {
                    IBinder binder = this.mService.getCarService(serviceName);
                    if (binder == null) {
                        Log.w(CarLibLog.TAG_CAR, "getCarManager could not get binder for service:" + serviceName);
                        return null;
                    }
                    manager = createCarManager(serviceName, binder);
                    if (manager == null) {
                        Log.w(CarLibLog.TAG_CAR, "getCarManager could not create manager for service:" + serviceName);
                        return null;
                    }
                    this.mServiceMap.put(serviceName, manager);
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                }
            }
            return manager;
        }
    }

    @SystemApi
    public XpDiagnosticManager getCarDiagnosticManager() throws CarNotConnectedException {
        return (XpDiagnosticManager) getCarManager(XP_DIAGNOSTIC_SERVICE);
    }

    public int getCarConnectionType() {
        return 5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Handler getEventHandler() {
        return this.mEventHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> T handleRemoteExceptionFromCarService(RemoteException e, T returnValue) {
        handleRemoteExceptionFromCarService(e);
        return returnValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleRemoteExceptionFromCarService(RemoteException e) {
        if (e instanceof TransactionTooLargeException) {
            Log.w(CarLibLog.TAG_CAR, "Car service threw TransactionTooLargeException", e);
            throw new CarTransactionException(e, "Car service threw TransactionTooLargException", new Object[0]);
        } else {
            Log.w(CarLibLog.TAG_CAR, "Car service has crashed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishClient() {
        Context context = this.mContext;
        if (context == null) {
            throw new IllegalStateException("Car service has crashed, null Context");
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {
                Log.w(CarLibLog.TAG_CAR, "Car service crashed, client not handling it, finish Activity, created from " + this.mConstructionStack);
                activity.finish();
            }
        } else if (context instanceof Service) {
            Service service = (Service) context;
            killClient(service.getPackageName() + "," + service.getClass().getSimpleName());
        } else {
            killClient(null);
        }
    }

    private void killClient(String clientInfo) {
        Log.w(CarLibLog.TAG_CAR, "**Car service has crashed. Client(" + clientInfo + ") is not handling it. Client should use Car.createCar(..., CarServiceLifecycleListener, ...) to handle it properly. Check pritned callstack to check where other version of Car.createCar() was called. Killing the client process**", this.mConstructionStack);
        Process.killProcess(Process.myPid());
    }

    public static <T> T handleRemoteExceptionFromCarService(Service service, RemoteException e, T returnValue) {
        handleRemoteExceptionFromCarService(service, e);
        return returnValue;
    }

    public static void handleRemoteExceptionFromCarService(Service service, RemoteException e) {
        if (e instanceof TransactionTooLargeException) {
            Log.w(CarLibLog.TAG_CAR, "Car service threw TransactionTooLargeException, client:" + service.getPackageName() + "," + service.getClass().getSimpleName(), e);
            throw new CarTransactionException(e, "Car service threw TransactionTooLargeException, client: %s, %s", service.getPackageName(), service.getClass().getSimpleName());
        }
        Log.w(CarLibLog.TAG_CAR, "Car service has crashed, client:" + service.getPackageName() + "," + service.getClass().getSimpleName(), e);
        service.stopSelf();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private CarManagerBase createCarManager(String serviceName, IBinder binder) throws CarNotConnectedException {
        char c;
        IBinder xpService = getIXpVehicleServiceOrThrow();
        switch (serviceName.hashCode()) {
            case -1969960369:
                if (serviceName.equals(PROJECTION_SERVICE)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1871502322:
                if (serviceName.equals(XP_AVAS_SERVICE)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case -1871431131:
                if (serviceName.equals(XP_DCDC_SERVICE)) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case -1870955074:
                if (serviceName.equals(XP_TBOX_SERVICE)) {
                    c = '0';
                    break;
                }
                c = 65535;
                break;
            case -1870941687:
                if (serviceName.equals(XP_TPMS_SERVICE)) {
                    c = '/';
                    break;
                }
                c = 65535;
                break;
            case -1855028221:
                if (serviceName.equals(BLUETOOTH_SERVICE)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -1853877803:
                if (serviceName.equals(CAR_NAVIGATION_SERVICE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1547904089:
                if (serviceName.equals(AOSP_DIAGNOSTIC_SERVICE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -993141291:
                if (serviceName.equals(PROPERTY_SERVICE)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -905948230:
                if (serviceName.equals(SENSOR_SERVICE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -874200568:
                if (serviceName.equals(VENDOR_EXTENSION_SERVICE)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -807062458:
                if (serviceName.equals(PACKAGE_SERVICE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -753107971:
                if (serviceName.equals(XP_AMP_SERVICE)) {
                    c = '$';
                    break;
                }
                c = 65535;
                break;
            case -753107758:
                if (serviceName.equals(XP_ATL_SERVICE)) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case -753107695:
                if (serviceName.equals(XP_AVM_SERVICE)) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -753107323:
                if (serviceName.equals(XP_BCM_SERVICE)) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case -753107007:
                if (serviceName.equals(XP_BMS_SERVICE)) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -753106423:
                if (serviceName.equals(XP_CAN_SERVICE)) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case -753106356:
                if (serviceName.equals(XP_CCS_SERVICE)) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case -753106341:
                if (serviceName.equals(XP_CDC_SERVICE)) {
                    c = '*';
                    break;
                }
                c = 65535;
                break;
            case -753106168:
                if (serviceName.equals(XP_CIU_SERVICE)) {
                    c = '3';
                    break;
                }
                c = 65535;
                break;
            case -753105256:
                if (serviceName.equals(XP_DHC_SERVICE)) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case -753104031:
                if (serviceName.equals(XP_EPS_SERVICE)) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case -753103941:
                if (serviceName.equals(XP_ESP_SERVICE)) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case -753100596:
                if (serviceName.equals(XP_ICM_SERVICE)) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case -753100278:
                if (serviceName.equals(XP_IMU_SERVICE)) {
                    c = '5';
                    break;
                }
                c = 65535;
                break;
            case -753100185:
                if (serviceName.equals(XP_IPU_SERVICE)) {
                    c = '\"';
                    break;
                }
                c = 65535;
                break;
            case -753097426:
                if (serviceName.equals(XP_LLU_SERVICE)) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case -753096744:
                if (serviceName.equals(XP_MCU_SERVICE)) {
                    c = ',';
                    break;
                }
                c = 65535;
                break;
            case -753096267:
                if (serviceName.equals(XP_MSB_SERVICE)) {
                    c = ')';
                    break;
                }
                c = 65535;
                break;
            case -753096256:
                if (serviceName.equals(XP_MSM_SERVICE)) {
                    c = '(';
                    break;
                }
                c = 65535;
                break;
            case -753090978:
                if (serviceName.equals(XP_SCU_SERVICE)) {
                    c = '.';
                    break;
                }
                c = 65535;
                break;
            case -753090593:
                if (serviceName.equals(XP_SPC_SERVICE)) {
                    c = '6';
                    break;
                }
                c = 65535;
                break;
            case -753090515:
                if (serviceName.equals(XP_SRS_SERVICE)) {
                    c = '&';
                    break;
                }
                c = 65535;
                break;
            case -753088095:
                if (serviceName.equals(XP_VCU_SERVICE)) {
                    c = '1';
                    break;
                }
                c = 65535;
                break;
            case -753087700:
                if (serviceName.equals(XP_VPM_SERVICE)) {
                    c = '\'';
                    break;
                }
                c = 65535;
                break;
            case -753085770:
                if (serviceName.equals(XP_XPU_SERVICE)) {
                    c = '7';
                    break;
                }
                c = 65535;
                break;
            case -603093501:
                if (serviceName.equals(TEST_SERVICE)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -444756694:
                if (serviceName.equals(CAR_DRIVING_STATE_SERVICE)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -375708743:
                if (serviceName.equals(CAR_MEDIA_SERVICE)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -259003252:
                if (serviceName.equals(STORAGE_MONITORING_SERVICE)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 3214768:
                if (serviceName.equals(HVAC_SERVICE)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 3237038:
                if (serviceName.equals(INFO_SERVICE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 93166550:
                if (serviceName.equals(AUDIO_SERVICE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 94415849:
                if (serviceName.equals(CABIN_SERVICE)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 106858757:
                if (serviceName.equals(POWER_SERVICE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 264148814:
                if (serviceName.equals(XP_DIAGNOSTIC_SERVICE)) {
                    c = '2';
                    break;
                }
                c = 65535;
                break;
            case 486923284:
                if (serviceName.equals(VMS_SUBSCRIBER_SERVICE)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 859709588:
                if (serviceName.equals(XP_CONDITION_SERVICE)) {
                    c = '8';
                    break;
                }
                c = 65535;
                break;
            case 1075548489:
                if (serviceName.equals(CAR_UX_RESTRICTION_SERVICE)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 1134120567:
                if (serviceName.equals(CAR_INTELLIGENT_SERVICE)) {
                    c = '9';
                    break;
                }
                c = 65535;
                break;
            case 1644291440:
                if (serviceName.equals(CAR_INSTRUMENT_CLUSTER_SERVICE)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 1763569149:
                if (serviceName.equals(CAR_BUGREPORT_SERVICE)) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 1830376762:
                if (serviceName.equals(APP_FOCUS_SERVICE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1891269741:
                if (serviceName.equals(CAR_TRUST_AGENT_ENROLLMENT_SERVICE)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 1932752118:
                if (serviceName.equals(CAR_CONFIGURATION_SERVICE)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 2120134595:
                if (serviceName.equals(XP_INPUT_SERVICE)) {
                    c = '4';
                    break;
                }
                c = 65535;
                break;
            case 2128047092:
                if (serviceName.equals(XP_RADIO_SERVICE)) {
                    c = '-';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                CarManagerBase manager = new CarAudioManager(this, binder);
                return manager;
            case 1:
                CarManagerBase manager2 = new CarSensorManager(this, binder);
                return manager2;
            case 2:
                CarManagerBase manager3 = new CarInfoManager(this, binder);
                return manager3;
            case 3:
                CarManagerBase manager4 = new CarAppFocusManager(this, binder);
                return manager4;
            case 4:
                CarManagerBase manager5 = new CarPackageManager(this, binder);
                return manager5;
            case 5:
                CarManagerBase manager6 = new CarNavigationStatusManager(this, binder);
                return manager6;
            case 6:
                CarManagerBase manager7 = new CarCabinManager(this, binder);
                return manager7;
            case 7:
                CarManagerBase manager8 = new AospCarDiagnosticManager(this, binder);
                return manager8;
            case '\b':
                CarManagerBase manager9 = new CarHvacManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager9;
            case '\t':
                CarManagerBase manager10 = new CarPowerManager(this, binder);
                return manager10;
            case '\n':
                CarManagerBase manager11 = new CarProjectionManager(this, binder);
                return manager11;
            case 11:
                CarManagerBase manager12 = new CarPropertyManager(this, ICarProperty.Stub.asInterface(binder));
                return manager12;
            case '\f':
                CarManagerBase manager13 = new CarVendorExtensionManager(this, binder);
                return manager13;
            case '\r':
                CarManagerBase manager14 = new CarInstrumentClusterManager(this, binder);
                return manager14;
            case 14:
                CarManagerBase manager15 = new CarTestManagerBinderWrapper(this, binder);
                return manager15;
            case 15:
                CarManagerBase manager16 = new VmsSubscriberManager(this, binder);
                return manager16;
            case 16:
                CarManagerBase manager17 = new CarBluetoothManager(this, binder);
                return manager17;
            case 17:
                CarManagerBase manager18 = new CarStorageMonitoringManager(this, binder);
                return manager18;
            case 18:
                CarManagerBase manager19 = new CarDrivingStateManager(this, binder);
                return manager19;
            case 19:
                CarManagerBase manager20 = new CarUxRestrictionsManager(this, binder);
                return manager20;
            case 20:
                CarManagerBase manager21 = new CarConfigurationManager(this, binder);
                return manager21;
            case 21:
                CarManagerBase manager22 = new CarTrustAgentEnrollmentManager(this, binder);
                return manager22;
            case 22:
                CarManagerBase manager23 = new CarMediaManager(this, binder);
                return manager23;
            case 23:
                CarManagerBase manager24 = new CarBugreportManager(this, binder);
                return manager24;
            case 24:
                CarManagerBase manager25 = new CarAvasManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager25;
            case 25:
                CarManagerBase manager26 = new CarAvmManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager26;
            case 26:
                CarManagerBase manager27 = new CarBcmManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager27;
            case 27:
                CarManagerBase manager28 = new CarBmsManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager28;
            case 28:
                CarManagerBase manager29 = new CarCanManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager29;
            case 29:
                CarManagerBase manager30 = new CarCcsManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager30;
            case 30:
                CarManagerBase manager31 = new CarDcdcManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager31;
            case 31:
                CarManagerBase manager32 = new CarEpsManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager32;
            case ' ':
                CarManagerBase manager33 = new CarEspManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager33;
            case '!':
                try {
                    ICar service = getICarOrThrow();
                    IBinder sharedMemoryService = service.getCarService(XP_SHARED_MEMORY_SERVICE);
                    CarManagerBase manager34 = new CarIcmManager(this, binder, xpService, sharedMemoryService, this.mContext, this.mEventHandler);
                    return manager34;
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                    return null;
                }
            case '\"':
                CarManagerBase manager35 = new CarIpuManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager35;
            case '#':
                CarManagerBase manager36 = new CarAtlManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager36;
            case '$':
                CarManagerBase manager37 = new CarAmpManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager37;
            case '%':
                CarManagerBase manager38 = new CarLluManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager38;
            case '&':
                CarManagerBase manager39 = new CarSrsManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager39;
            case '\'':
                CarManagerBase manager40 = new CarVpmManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager40;
            case '(':
                CarManagerBase manager41 = new CarMsmManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager41;
            case ')':
                CarManagerBase manager42 = new CarMsbManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager42;
            case '*':
                CarManagerBase manager43 = new CarCdcManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager43;
            case '+':
                CarManagerBase manager44 = new CarDhcManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager44;
            case ',':
                CarManagerBase manager45 = new CarMcuManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager45;
            case '-':
                try {
                    ICar service2 = getICarOrThrow();
                    IBinder audioService = service2.getCarService(AUDIO_SERVICE);
                    CarManagerBase manager46 = new CarRadioManager(this, binder, audioService, xpService, this.mContext, this.mEventHandler);
                    return manager46;
                } catch (RemoteException e2) {
                    handleRemoteExceptionFromCarService(e2);
                    return null;
                }
            case '.':
                CarManagerBase manager47 = new CarScuManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager47;
            case '/':
                CarManagerBase manager48 = new CarTpmsManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager48;
            case '0':
                try {
                    ICar service3 = getICarOrThrow();
                    IBinder timeService = service3.getCarService(XP_TIME_SERVICE);
                    CarManagerBase manager49 = new CarTboxManager(this, binder, xpService, timeService, this.mContext, this.mEventHandler);
                    return manager49;
                } catch (RemoteException e3) {
                    handleRemoteExceptionFromCarService(e3);
                    return null;
                }
            case '1':
                CarManagerBase manager50 = new CarVcuManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager50;
            case '2':
                try {
                    ICar service4 = getICarOrThrow();
                    IBinder propertyService = service4.getCarService(PROPERTY_SERVICE);
                    CarManagerBase manager51 = new XpDiagnosticManager(this, propertyService, binder, this.mContext, this.mEventHandler);
                    return manager51;
                } catch (RemoteException e4) {
                    handleRemoteExceptionFromCarService(e4);
                    return null;
                }
            case '3':
                CarManagerBase manager52 = new CarCiuManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager52;
            case '4':
                CarManagerBase manager53 = new CarInputManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager53;
            case '5':
                CarManagerBase manager54 = new CarImuManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager54;
            case '6':
                CarManagerBase manager55 = new CarSpcManager(this, binder, xpService, this.mContext, this.mEventHandler);
                return manager55;
            case '7':
                try {
                    ICar service5 = getICarOrThrow();
                    IBinder sharedMemoryService2 = service5.getCarService(XP_SHARED_MEMORY_SERVICE);
                    CarManagerBase manager56 = new CarXpuManager(this, binder, xpService, sharedMemoryService2, this.mContext, this.mEventHandler);
                    return manager56;
                } catch (RemoteException e5) {
                    handleRemoteExceptionFromCarService(e5);
                    return null;
                }
            case '8':
                CarManagerBase manager57 = new CarConditionManager(this, binder);
                return manager57;
            case '9':
                CarManagerBase manager58 = new CarIntelligentEngineManager(this, binder);
                return manager58;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCarService() {
        Intent intent = new Intent();
        intent.setPackage(CAR_SERVICE_PACKAGE);
        intent.setAction(CAR_SERVICE_INTERFACE_NAME);
        boolean bound = this.mContext.bindServiceAsUser(intent, this.mServiceConnectionListener, 1, UserHandle.CURRENT_OR_SELF);
        synchronized (this.mLock) {
            if (!bound) {
                this.mConnectionRetryCount++;
                if (this.mConnectionRetryCount > CAR_SERVICE_BIND_MAX_RETRY) {
                    Log.w(CarLibLog.TAG_CAR, "cannot bind to car service after max retry");
                    this.mMainThreadEventHandler.post(this.mConnectionRetryFailedRunnable);
                } else {
                    this.mEventHandler.postDelayed(this.mConnectionRetryRunnable, CAR_SERVICE_BIND_RETRY_INTERVAL_MS);
                }
            } else {
                this.mEventHandler.removeCallbacks(this.mConnectionRetryRunnable);
                this.mMainThreadEventHandler.removeCallbacks(this.mConnectionRetryFailedRunnable);
                this.mConnectionRetryCount = 0;
                this.mServiceBound = true;
            }
        }
    }

    private ICar getICarOrThrow() throws IllegalStateException {
        ICar iCar;
        synchronized (this.mLock) {
            if (this.mService == null) {
                Log.w(CarLibLog.TAG_CAR, "not working while car service not ready");
                throw new IllegalStateException("not connected");
            }
            iCar = this.mService;
        }
        return iCar;
    }

    private IBinder getIXpVehicleServiceOrThrow() throws IllegalStateException {
        IBinder iBinder;
        synchronized (this.mLock) {
            if (this.mIXpService == null) {
                Log.w(CarLibLog.TAG_CAR, "not working while Xp vehicle service not ready");
                throw new IllegalStateException("Xp vehicle service not connected");
            }
            iBinder = this.mIXpService;
        }
        return iBinder;
    }

    private void tearDownCarManagersLocked() {
        for (CarManagerBase manager : this.mServiceMap.values()) {
            manager.onCarDisconnected();
        }
        this.mServiceMap.clear();
    }

    @SystemApi
    public static int getHardwareVersion() {
        String version = SystemProperties.get(HARDWARE_VERSION_PROPERTY, "0x00");
        try {
            String parsedVersion = version.substring(2);
            int hardwareVersion = Integer.parseInt(parsedVersion, 16);
            return hardwareVersion;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            Log.e(CarLibLog.TAG_CAR, "parse " + version + " failed");
            e.printStackTrace();
            return 0;
        }
    }

    @SystemApi
    public static boolean isExportVersion() {
        String region = getVersionInCountryCode();
        if (TextUtils.isEmpty(region)) {
            return false;
        }
        return !CAR_REGION_ZH.equals(region) || "V".equals(getHardwareCarStage());
    }

    @SystemApi
    public static String getVersionInCountryCode() {
        String versionFinger = SystemProperties.get(HARDWARE_SOFTWARE_VERSION_PROPERTY, "");
        if ("".equals(versionFinger)) {
            return versionFinger;
        }
        return versionFinger.substring(7, 9);
    }

    @Deprecated
    public static String getHardwareCarType() {
        if ("Q8".equals(getXpCduType())) {
            Log.e(CarLibLog.TAG_CAR, "throw CarUnSupportCduTypeException. Please use Car#getXpCduType() instead.");
            throw new CarUnSupportCduTypeException(" UnSupport cduType : " + getXpCduType());
        }
        String versionFinger = SystemProperties.get(HARDWARE_SOFTWARE_VERSION_PROPERTY, "");
        if ("".equals(versionFinger)) {
            return versionFinger;
        }
        return versionFinger.substring(9, 12);
    }

    @Deprecated
    public static String getHardwareCarTypeExt() {
        String carType = getHardwareCarType();
        if (isExportVersion()) {
            return carType + "V";
        }
        return carType;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003d, code lost:
        if (r0.equals("Q3") != false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0049 A[RETURN] */
    @android.annotation.SystemApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getXpCduType() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "ro.xiaopeng.software"
            java.lang.String r1 = android.os.SystemProperties.get(r1, r0)
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto Lf
            return r1
        Lf:
            r0 = 5
            r2 = 7
            java.lang.String r0 = r1.substring(r0, r2)
            r2 = 0
            java.lang.String r3 = "persist.sys.xiaopeng.extendedCarType"
            int r3 = android.os.SystemProperties.getInt(r3, r2)
            r4 = 1
            if (r3 != r4) goto L4c
            r5 = -1
            int r6 = r0.hashCode()
            r7 = 2562(0xa02, float:3.59E-42)
            if (r6 == r7) goto L37
            r2 = 2566(0xa06, float:3.596E-42)
            if (r6 == r2) goto L2d
        L2c:
            goto L40
        L2d:
            java.lang.String r2 = "Q7"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L2c
            r2 = r4
            goto L41
        L37:
            java.lang.String r6 = "Q3"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L2c
            goto L41
        L40:
            r2 = r5
        L41:
            if (r2 == 0) goto L49
            if (r2 == r4) goto L46
            goto L4c
        L46:
            java.lang.String r2 = "Q7A"
            return r2
        L49:
            java.lang.String r2 = "Q3A"
            return r2
        L4c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.car.Car.getXpCduType():java.lang.String");
    }

    @SystemApi
    public static String getHardwareCarStage() {
        String versionFinger = SystemProperties.get(HARDWARE_SOFTWARE_VERSION_PROPERTY, "");
        if ("".equals(versionFinger)) {
            return versionFinger;
        }
        return versionFinger.substring(12, 13);
    }

    @SystemApi
    public static String getUuidCode() {
        return SystemProperties.get(PRIVATE_UUID_CODE_PROPERTY, "");
    }

    @SystemApi
    public static boolean isSystemFirstBoot() {
        String bootProperty = SystemProperties.get(SYSTEM_BOOT_FIRST_PROPERTY, "");
        return "1".equals(bootProperty);
    }
}
