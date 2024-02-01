package android.car;

import android.annotation.SystemApi;
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
import android.car.navigation.CarNavigationStatusManager;
import android.car.settings.CarConfigurationManager;
import android.car.storagemonitoring.CarStorageMonitoringManager;
import android.car.test.CarTestManagerBinderWrapper;
import android.car.vms.VmsSubscriberManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.TransactionTooLargeException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
/* loaded from: classes.dex */
public final class Car {
    private static final String AOSP_DIAGNOSTIC_SERVICE = "diagnostic";
    public static final String APP_FOCUS_SERVICE = "app_focus";
    public static final String AUDIO_SERVICE = "audio";
    public static final String BLUETOOTH_SERVICE = "car_bluetooth";
    public static final String CABIN_SERVICE = "cabin";
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
    public static final String CAR_EXTRA_MEDIA_PACKAGE = "android.car.intent.extra.MEDIA_PACKAGE";
    private static final int CAR_FACELIFT_ONE = 1;
    public static final String CAR_INSTRUMENT_CLUSTER_SERVICE = "cluster_service";
    @SystemApi
    public static final String CAR_INTELLIGENT_SERVICE = "intelligent";
    public static final String CAR_INTENT_ACTION_MEDIA_TEMPLATE = "android.car.intent.action.MEDIA_TEMPLATE";
    public static final String CAR_NAVIGATION_SERVICE = "car_navigation_service";
    public static final String CAR_NOT_CONNECTED_EXCEPTION_MSG = "CarNotConnected";
    private static final int CAR_ORIGIN = 0;
    public static final String CAR_REGION_EU = "EU";
    public static final String CAR_REGION_ZH = "ZH";
    private static final long CAR_SERVICE_BIND_MAX_RETRY = 20;
    private static final long CAR_SERVICE_BIND_RETRY_INTERVAL_MS = 500;
    private static final String CAR_SERVICE_CLASS = "com.android.car.CarService";
    public static final String CAR_SERVICE_INTERFACE_NAME = "android.car.ICar";
    private static final String CAR_SERVICE_PACKAGE = "com.android.car";
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
    @Deprecated
    public static final String CAR_TYPE_D21A = "D21A";
    @SystemApi
    @Deprecated
    public static final String CAR_TYPE_D21B = "D21B";
    @SystemApi
    public static final String CAR_TYPE_D21V = "D21V";
    public static final String CAR_TYPE_D22 = "D22";
    public static final String CAR_TYPE_D22V = "D22V";
    @Deprecated
    public static final String CAR_TYPE_D25 = "D25";
    public static final String CAR_TYPE_D55 = "D55";
    public static final String CAR_TYPE_D55V = "D55V";
    public static final String CAR_TYPE_E28 = "E28";
    public static final String CAR_TYPE_E28V = "E28V";
    @Deprecated
    public static final String CAR_TYPE_E36 = "E36";
    @SystemApi
    public static final String CAR_TYPE_E38 = "E38";
    @SystemApi
    public static final String CAR_TYPE_E38V = "E38V";
    @SystemApi
    public static final String CAR_TYPE_F30 = "F30";
    @SystemApi
    public static final String CAR_TYPE_F30V = "F30V";
    @SystemApi
    public static final String CAR_UX_RESTRICTION_SERVICE = "uxrestriction";
    public static final int CONNECTION_TYPE_EMBEDDED = 5;
    private static final String EXTENDED_CAR_TYPE = "persist.sys.xiaopeng.extendedCarType";
    private static final String HARDWARE_SOFTWARE_VERSION_PROPERTY = "ro.xiaopeng.software";
    private static final String HARDWARE_VERSION_PROPERTY = "ro.boot.hw_version";
    public static final String HVAC_SERVICE = "hvac";
    public static final String INFO_SERVICE = "info";
    public static final String PACKAGE_SERVICE = "package";
    @SystemApi
    public static final String PERMISSION_ADJUST_CAR_CABIN = "android.car.permission.ADJUST_CAR_CABIN";
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
    public static final String PERMISSION_CAR_TEST_SERVICE = "android.car.permission.CAR_TEST_SERVICE";
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
    @SystemApi
    public static final String PERMISSION_CONTROL_EXTERIOR_LIGHTS = "android.car.permission.CONTROL_CAR_EXTERIOR_LIGHTS";
    @SystemApi
    public static final String PERMISSION_ENERGY = "android.car.permission.CAR_ENERGY";
    @SystemApi
    public static final String PERMISSION_ENERGY_PORTS = "android.car.permission.CAR_ENERGY_PORTS";
    @SystemApi
    public static final String PERMISSION_EXTERIOR_ENVIRONMENT = "android.car.permission.CAR_EXTERIOR_ENVIRONMENT";
    @SystemApi
    public static final String PERMISSION_EXTERIOR_LIGHTS = "android.car.permission.CAR_EXTERIOR_LIGHTS";
    @SystemApi
    public static final String PERMISSION_IDENTIFICATION = "android.car.permission.CAR_IDENTIFICATION";
    @SystemApi
    public static final String PERMISSION_MILEAGE = "android.car.permission.CAR_MILEAGE";
    @SystemApi
    public static final String PERMISSION_MOCK_VEHICLE_HAL = "android.car.permission.CAR_MOCK_VEHICLE_HAL";
    @SystemApi
    public static final String PERMISSION_POWERTRAIN = "android.car.permission.CAR_POWERTRAIN";
    @SystemApi
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
    public static final String POWER_SERVICE = "power";
    private static final String PRIVATE_UUID_CODE_PROPERTY = "persist.sys.hardware.uuid";
    public static final String PROJECTION_SERVICE = "projection";
    public static final String PROPERTY_SERVICE = "property";
    public static final String REGION_EU = "EU";
    public static final String SENSOR_SERVICE = "sensor";
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_DISCONNECTED = 0;
    @SystemApi
    public static final String STORAGE_MONITORING_SERVICE = "storage_monitoring";
    private static final String SYSTEM_BOOT_FIRST_PROPERTY = "persist.sys.boot.first";
    @SystemApi
    public static final String TEST_SERVICE = "car-service-test";
    public static final String VENDOR_EXTENSION_SERVICE = "vendor_extension";
    public static final int VERSION = 3;
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
    public static final String XP_TPMS_SERVICE = "xp_tpms";
    public static final String XP_VCU_SERVICE = "xp_vcu";
    public static final String XP_VEHICLE_SERVICE = "xp_vehicle";
    @SystemApi
    public static final String XP_VPM_SERVICE = "xp_vpm";
    @SystemApi
    public static final String XP_XPU_SERVICE = "xp_xpu";
    private final Object mCarManagerLock;
    @GuardedBy("this")
    private int mConnectionRetryCount;
    private final Runnable mConnectionRetryFailedRunnable;
    private final Runnable mConnectionRetryRunnable;
    @GuardedBy("this")
    private int mConnectionState;
    private final Context mContext;
    private IBinder.DeathRecipient mDeathRecipient;
    private final Handler mEventHandler;
    @GuardedBy("this")
    private IBinder mIXpService;
    private final Handler mMainThreadEventHandler;
    private final boolean mOwnsService;
    private String mPackageName;
    @GuardedBy("this")
    private ICar mService;
    private final ServiceConnection mServiceConnectionListener;
    private final ServiceConnection mServiceConnectionListenerClient;
    @GuardedBy("mCarManagerLock")
    private final HashMap<String, CarManagerBase> mServiceMap;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CarRegion {
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

    public static Car createCar(Context context, ServiceConnection serviceConnectionListener, Handler handler) {
        if (!context.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
            Log.e(CarLibLog.TAG_CAR, "FEATURE_AUTOMOTIVE not declared while android.car is used");
            return null;
        }
        try {
            return new Car(context, serviceConnectionListener, handler);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static Car createCar(Context context, ServiceConnection serviceConnectionListener) {
        return createCar(context, serviceConnectionListener, null);
    }

    private Car(Context context, ServiceConnection serviceConnectionListener, Handler handler) {
        this.mPackageName = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
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
        this.mDeathRecipient = new IBinder.DeathRecipient() { // from class: android.car.Car.3
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                Log.e(CarLibLog.TAG_CAR, "binderDied: ");
                if (Car.this.mService != null) {
                    Car.this.mService.asBinder().unlinkToDeath(this, 0);
                    Car.this.mService = null;
                }
            }
        };
        this.mServiceConnectionListener = new ServiceConnection() { // from class: android.car.Car.4
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                synchronized (Car.this) {
                    ICar newService = ICar.Stub.asInterface(service);
                    if (newService != null) {
                        if (Car.this.mService == null || !Car.this.mService.asBinder().equals(newService.asBinder())) {
                            Car.this.mConnectionState = 2;
                            Car.this.mService = newService;
                            if (!service.isBinderAlive()) {
                                Log.e(CarLibLog.TAG_CAR, "the binder is not alive !");
                            }
                            try {
                                service.linkToDeath(Car.this.mDeathRecipient, 0);
                                Car.this.mIXpService = Car.this.mService.getCarService(Car.XP_VEHICLE_SERVICE);
                            } catch (RemoteException e) {
                                Car.this.handleRemoteExceptionFromCarService(e);
                                Log.e(CarLibLog.TAG_CAR, "get car_service onServiceConnected " + Car.this.mPackageName + " failed! " + e);
                            }
                            Log.i(CarLibLog.TAG_CAR, "get car_service onServiceConnected " + Car.this.mPackageName + " state : " + Car.this.mConnectionState);
                            Car.this.mServiceConnectionListenerClient.onServiceConnected(name, service);
                            return;
                        }
                        return;
                    }
                    Log.wtf(CarLibLog.TAG_CAR, "null binder service", new RuntimeException());
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                synchronized (Car.this) {
                    Car.this.mService = null;
                    Car.this.mIXpService = null;
                    if (Car.this.mConnectionState == 0) {
                        return;
                    }
                    Car.this.mConnectionState = 0;
                    Car.this.mServiceMap.clear();
                    Log.i(CarLibLog.TAG_CAR, "get car_service onServiceDisconnected " + Car.this.mPackageName + " state : " + Car.this.mConnectionState);
                    Car.this.mServiceConnectionListenerClient.onServiceDisconnected(name);
                }
            }
        };
        this.mCarManagerLock = new Object();
        this.mServiceMap = new HashMap<>();
        this.mContext = context;
        if (context != null) {
            this.mPackageName = context.getPackageName();
        }
        this.mEventHandler = determineEventHandler(handler);
        this.mMainThreadEventHandler = determineMainThreadEventHandler(this.mEventHandler);
        this.mService = null;
        this.mOwnsService = true;
        this.mServiceConnectionListenerClient = serviceConnectionListener;
    }

    public Car(Context context, ICar service, Handler handler) {
        this.mPackageName = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
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
        this.mDeathRecipient = new IBinder.DeathRecipient() { // from class: android.car.Car.3
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                Log.e(CarLibLog.TAG_CAR, "binderDied: ");
                if (Car.this.mService != null) {
                    Car.this.mService.asBinder().unlinkToDeath(this, 0);
                    Car.this.mService = null;
                }
            }
        };
        this.mServiceConnectionListener = new ServiceConnection() { // from class: android.car.Car.4
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service2) {
                synchronized (Car.this) {
                    ICar newService = ICar.Stub.asInterface(service2);
                    if (newService != null) {
                        if (Car.this.mService == null || !Car.this.mService.asBinder().equals(newService.asBinder())) {
                            Car.this.mConnectionState = 2;
                            Car.this.mService = newService;
                            if (!service2.isBinderAlive()) {
                                Log.e(CarLibLog.TAG_CAR, "the binder is not alive !");
                            }
                            try {
                                service2.linkToDeath(Car.this.mDeathRecipient, 0);
                                Car.this.mIXpService = Car.this.mService.getCarService(Car.XP_VEHICLE_SERVICE);
                            } catch (RemoteException e) {
                                Car.this.handleRemoteExceptionFromCarService(e);
                                Log.e(CarLibLog.TAG_CAR, "get car_service onServiceConnected " + Car.this.mPackageName + " failed! " + e);
                            }
                            Log.i(CarLibLog.TAG_CAR, "get car_service onServiceConnected " + Car.this.mPackageName + " state : " + Car.this.mConnectionState);
                            Car.this.mServiceConnectionListenerClient.onServiceConnected(name, service2);
                            return;
                        }
                        return;
                    }
                    Log.wtf(CarLibLog.TAG_CAR, "null binder service", new RuntimeException());
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                synchronized (Car.this) {
                    Car.this.mService = null;
                    Car.this.mIXpService = null;
                    if (Car.this.mConnectionState == 0) {
                        return;
                    }
                    Car.this.mConnectionState = 0;
                    Car.this.mServiceMap.clear();
                    Log.i(CarLibLog.TAG_CAR, "get car_service onServiceDisconnected " + Car.this.mPackageName + " state : " + Car.this.mConnectionState);
                    Car.this.mServiceConnectionListenerClient.onServiceDisconnected(name);
                }
            }
        };
        this.mCarManagerLock = new Object();
        this.mServiceMap = new HashMap<>();
        this.mContext = context;
        this.mEventHandler = determineEventHandler(handler);
        this.mMainThreadEventHandler = determineMainThreadEventHandler(this.mEventHandler);
        this.mService = service;
        this.mOwnsService = false;
        this.mConnectionState = 2;
        this.mServiceConnectionListenerClient = null;
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

    public void connect() throws IllegalStateException {
        synchronized (this) {
            if (this.mConnectionState != 0) {
                throw new IllegalStateException("already connected or connecting");
            }
            this.mConnectionState = 1;
            startCarService();
        }
    }

    public void disconnect() {
        synchronized (this) {
            if (this.mConnectionState == 0) {
                return;
            }
            this.mEventHandler.removeCallbacks(this.mConnectionRetryRunnable);
            this.mMainThreadEventHandler.removeCallbacks(this.mConnectionRetryFailedRunnable);
            this.mConnectionRetryCount = 0;
            tearDownCarManagers();
            this.mService = null;
            this.mConnectionState = 0;
            if (this.mOwnsService) {
                this.mContext.unbindService(this.mServiceConnectionListener);
            }
        }
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this) {
            z = this.mService != null;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this) {
            z = true;
            if (this.mConnectionState != 1) {
                z = false;
            }
        }
        return z;
    }

    public Object getCarManager(String serviceName) throws CarNotConnectedException {
        ICar service = getICarOrThrow();
        synchronized (this.mCarManagerLock) {
            CarManagerBase manager = this.mServiceMap.get(serviceName);
            if (manager == null) {
                try {
                    IBinder binder = service.getCarService(serviceName);
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

    public static void checkCarNotConnectedExceptionFromCarService(IllegalStateException e) throws CarNotConnectedException, IllegalStateException {
        String message = e.getMessage();
        if ("CarNotConnected".equals(message)) {
            throw new CarNotConnectedException();
        }
        throw e;
    }

    public static void hideCarNotConnectedExceptionFromCarService(IllegalStateException e) throws IllegalStateException {
        String message = e.getMessage();
        if ("CarNotConnected".equals(message)) {
            return;
        }
        throw e;
    }

    void handleRemoteExceptionFromCarService(RemoteException e) {
        if (e instanceof DeadObjectException) {
            throw new CarTransactionException(e, "Car service threw DeadObjectException", new Object[0]);
        }
        if (e instanceof TransactionTooLargeException) {
            throw new CarTransactionException(e, "Car service threw TransactionTooLargException", new Object[0]);
        }
        Log.w(CarLibLog.TAG_CAR, "Car service has crashed", e);
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
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -1871431131:
                if (serviceName.equals(XP_DCDC_SERVICE)) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -1870955074:
                if (serviceName.equals(XP_TBOX_SERVICE)) {
                    c = '-';
                    break;
                }
                c = 65535;
                break;
            case -1870941687:
                if (serviceName.equals(XP_TPMS_SERVICE)) {
                    c = ',';
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
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case -753107758:
                if (serviceName.equals(XP_ATL_SERVICE)) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case -753107695:
                if (serviceName.equals(XP_AVM_SERVICE)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -753107323:
                if (serviceName.equals(XP_BCM_SERVICE)) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -753107007:
                if (serviceName.equals(XP_BMS_SERVICE)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case -753106423:
                if (serviceName.equals(XP_CAN_SERVICE)) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -753106356:
                if (serviceName.equals(XP_CCS_SERVICE)) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case -753106341:
                if (serviceName.equals(XP_CDC_SERVICE)) {
                    c = '\'';
                    break;
                }
                c = 65535;
                break;
            case -753106168:
                if (serviceName.equals(XP_CIU_SERVICE)) {
                    c = '0';
                    break;
                }
                c = 65535;
                break;
            case -753105256:
                if (serviceName.equals(XP_DHC_SERVICE)) {
                    c = '(';
                    break;
                }
                c = 65535;
                break;
            case -753104031:
                if (serviceName.equals(XP_EPS_SERVICE)) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case -753103941:
                if (serviceName.equals(XP_ESP_SERVICE)) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case -753100596:
                if (serviceName.equals(XP_ICM_SERVICE)) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case -753100278:
                if (serviceName.equals(XP_IMU_SERVICE)) {
                    c = '2';
                    break;
                }
                c = 65535;
                break;
            case -753100185:
                if (serviceName.equals(XP_IPU_SERVICE)) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case -753097426:
                if (serviceName.equals(XP_LLU_SERVICE)) {
                    c = '\"';
                    break;
                }
                c = 65535;
                break;
            case -753096744:
                if (serviceName.equals(XP_MCU_SERVICE)) {
                    c = ')';
                    break;
                }
                c = 65535;
                break;
            case -753096267:
                if (serviceName.equals(XP_MSB_SERVICE)) {
                    c = '&';
                    break;
                }
                c = 65535;
                break;
            case -753096256:
                if (serviceName.equals(XP_MSM_SERVICE)) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case -753090978:
                if (serviceName.equals(XP_SCU_SERVICE)) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case -753090593:
                if (serviceName.equals(XP_SPC_SERVICE)) {
                    c = '3';
                    break;
                }
                c = 65535;
                break;
            case -753090515:
                if (serviceName.equals(XP_SRS_SERVICE)) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case -753088095:
                if (serviceName.equals(XP_VCU_SERVICE)) {
                    c = '.';
                    break;
                }
                c = 65535;
                break;
            case -753087700:
                if (serviceName.equals(XP_VPM_SERVICE)) {
                    c = '$';
                    break;
                }
                c = 65535;
                break;
            case -753085770:
                if (serviceName.equals(XP_XPU_SERVICE)) {
                    c = '4';
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
                    c = '/';
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
                    c = '5';
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
                    c = '6';
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
            case 1830376762:
                if (serviceName.equals(APP_FOCUS_SERVICE)) {
                    c = 3;
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
                    c = '1';
                    break;
                }
                c = 65535;
                break;
            case 2128047092:
                if (serviceName.equals(XP_RADIO_SERVICE)) {
                    c = '*';
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
                CarManagerBase manager = new CarAudioManager(binder, this.mContext, this.mEventHandler);
                return manager;
            case 1:
                CarManagerBase manager2 = new CarSensorManager(binder, this.mContext, this.mEventHandler);
                return manager2;
            case 2:
                CarManagerBase manager3 = new CarInfoManager(binder);
                return manager3;
            case 3:
                CarManagerBase manager4 = new CarAppFocusManager(binder, this.mEventHandler);
                return manager4;
            case 4:
                CarManagerBase manager5 = new CarPackageManager(binder, this.mContext);
                return manager5;
            case 5:
                CarManagerBase manager6 = new CarNavigationStatusManager(binder);
                return manager6;
            case 6:
                CarManagerBase manager7 = new CarCabinManager(binder, this.mContext, this.mEventHandler);
                return manager7;
            case 7:
                CarManagerBase manager8 = new AospCarDiagnosticManager(binder, this.mContext, this.mEventHandler);
                return manager8;
            case '\b':
                CarManagerBase manager9 = new CarHvacManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager9;
            case '\t':
                CarManagerBase manager10 = new CarPowerManager(binder, this.mContext, this.mEventHandler);
                return manager10;
            case '\n':
                CarManagerBase manager11 = new CarProjectionManager(binder, this.mEventHandler);
                return manager11;
            case 11:
                CarManagerBase manager12 = new CarPropertyManager(binder, this.mEventHandler, false, "CarPropertyManager");
                return manager12;
            case '\f':
                CarManagerBase manager13 = new CarVendorExtensionManager(binder, this.mEventHandler);
                return manager13;
            case '\r':
                CarManagerBase manager14 = new CarInstrumentClusterManager(binder, this.mEventHandler);
                return manager14;
            case 14:
                CarManagerBase manager15 = new CarTestManagerBinderWrapper(binder);
                return manager15;
            case 15:
                CarManagerBase manager16 = new VmsSubscriberManager(binder);
                return manager16;
            case 16:
                CarManagerBase manager17 = new CarBluetoothManager(binder, this.mContext);
                return manager17;
            case 17:
                CarManagerBase manager18 = new CarStorageMonitoringManager(binder, this.mEventHandler);
                return manager18;
            case 18:
                CarManagerBase manager19 = new CarDrivingStateManager(binder, this.mContext, this.mEventHandler);
                return manager19;
            case 19:
                CarManagerBase manager20 = new CarUxRestrictionsManager(binder, this.mContext, this.mEventHandler);
                return manager20;
            case 20:
                CarManagerBase manager21 = new CarConfigurationManager(binder);
                return manager21;
            case 21:
                CarManagerBase manager22 = new CarAvasManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager22;
            case 22:
                CarManagerBase manager23 = new CarAvmManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager23;
            case 23:
                CarManagerBase manager24 = new CarBcmManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager24;
            case 24:
                CarManagerBase manager25 = new CarBmsManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager25;
            case 25:
                CarManagerBase manager26 = new CarCanManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager26;
            case 26:
                CarManagerBase manager27 = new CarCcsManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager27;
            case 27:
                CarManagerBase manager28 = new CarDcdcManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager28;
            case 28:
                CarManagerBase manager29 = new CarEpsManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager29;
            case 29:
                CarManagerBase manager30 = new CarEspManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager30;
            case 30:
                try {
                    ICar service = getICarOrThrow();
                    IBinder sharedMemoryService = service.getCarService(XP_SHARED_MEMORY_SERVICE);
                    CarManagerBase manager31 = new CarIcmManager(binder, xpService, sharedMemoryService, this.mContext, this.mEventHandler);
                    return manager31;
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                    return null;
                }
            case 31:
                CarManagerBase manager32 = new CarIpuManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager32;
            case ' ':
                CarManagerBase manager33 = new CarAtlManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager33;
            case '!':
                CarManagerBase manager34 = new CarAmpManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager34;
            case '\"':
                CarManagerBase manager35 = new CarLluManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager35;
            case '#':
                CarManagerBase manager36 = new CarSrsManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager36;
            case '$':
                CarManagerBase manager37 = new CarVpmManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager37;
            case '%':
                CarManagerBase manager38 = new CarMsmManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager38;
            case '&':
                CarManagerBase manager39 = new CarMsbManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager39;
            case '\'':
                CarManagerBase manager40 = new CarCdcManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager40;
            case '(':
                CarManagerBase manager41 = new CarDhcManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager41;
            case ')':
                CarManagerBase manager42 = new CarMcuManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager42;
            case '*':
                try {
                    ICar service2 = getICarOrThrow();
                    IBinder audioService = service2.getCarService(AUDIO_SERVICE);
                    CarManagerBase manager43 = new CarRadioManager(binder, audioService, xpService, this.mContext, this.mEventHandler);
                    return manager43;
                } catch (RemoteException e2) {
                    handleRemoteExceptionFromCarService(e2);
                    return null;
                }
            case '+':
                CarManagerBase manager44 = new CarScuManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager44;
            case ',':
                CarManagerBase manager45 = new CarTpmsManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager45;
            case '-':
                CarManagerBase manager46 = new CarTboxManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager46;
            case '.':
                CarManagerBase manager47 = new CarVcuManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager47;
            case '/':
                try {
                    ICar service3 = getICarOrThrow();
                    IBinder propertyService = service3.getCarService(PROPERTY_SERVICE);
                    CarManagerBase manager48 = new XpDiagnosticManager(propertyService, binder, this.mContext, this.mEventHandler);
                    return manager48;
                } catch (RemoteException e3) {
                    handleRemoteExceptionFromCarService(e3);
                    return null;
                }
            case '0':
                CarManagerBase manager49 = new CarCiuManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager49;
            case '1':
                CarManagerBase manager50 = new CarInputManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager50;
            case '2':
                CarManagerBase manager51 = new CarImuManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager51;
            case '3':
                CarManagerBase manager52 = new CarSpcManager(binder, xpService, this.mContext, this.mEventHandler);
                return manager52;
            case '4':
                try {
                    ICar service4 = getICarOrThrow();
                    IBinder sharedMemoryService2 = service4.getCarService(XP_SHARED_MEMORY_SERVICE);
                    CarManagerBase manager53 = new CarXpuManager(binder, xpService, sharedMemoryService2, this.mContext, this.mEventHandler);
                    return manager53;
                } catch (RemoteException e4) {
                    handleRemoteExceptionFromCarService(e4);
                    return null;
                }
            case '5':
                CarManagerBase manager54 = new CarConditionManager(binder);
                return manager54;
            case '6':
                CarManagerBase manager55 = new CarIntelligentEngineManager(this, binder);
                return manager55;
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
        if (!bound) {
            this.mConnectionRetryCount++;
            if (this.mConnectionRetryCount > CAR_SERVICE_BIND_MAX_RETRY) {
                Log.w(CarLibLog.TAG_CAR, "cannot bind to car service after max retry");
                this.mMainThreadEventHandler.post(this.mConnectionRetryFailedRunnable);
                return;
            }
            this.mEventHandler.postDelayed(this.mConnectionRetryRunnable, CAR_SERVICE_BIND_RETRY_INTERVAL_MS);
            return;
        }
        this.mConnectionRetryCount = 0;
    }

    private synchronized ICar getICarOrThrow() throws IllegalStateException {
        if (this.mService == null) {
            throw new IllegalStateException("not connected");
        }
        return this.mService;
    }

    private synchronized IBinder getIXpVehicleServiceOrThrow() throws IllegalStateException {
        if (this.mIXpService == null) {
            throw new IllegalStateException("Xp vehicle service not connected");
        }
        return this.mIXpService;
    }

    private void handleRemoteException(RemoteException e) {
        Log.w(CarLibLog.TAG_CAR, "RemoteException", e);
        disconnect();
    }

    private void tearDownCarManagers() {
        synchronized (this.mCarManagerLock) {
            for (CarManagerBase manager : this.mServiceMap.values()) {
                manager.onCarDisconnected();
            }
            this.mServiceMap.clear();
        }
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
        String versionFinger = SystemProperties.get(HARDWARE_SOFTWARE_VERSION_PROPERTY, CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE);
        if (CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE.equals(versionFinger)) {
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
        String versionFinger = SystemProperties.get(HARDWARE_SOFTWARE_VERSION_PROPERTY, CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE);
        if (CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE.equals(versionFinger)) {
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

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003f, code lost:
        if (r1.equals("Q3") != false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0047 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004a A[RETURN] */
    @android.annotation.SystemApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getXpCduType() {
        /*
            java.lang.String r0 = "ro.xiaopeng.software"
            java.lang.String r1 = ""
            java.lang.String r0 = android.os.SystemProperties.get(r0, r1)
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L11
            return r0
        L11:
            r1 = 5
            r2 = 7
            java.lang.String r1 = r0.substring(r1, r2)
            java.lang.String r2 = "persist.sys.xiaopeng.extendedCarType"
            r3 = 0
            int r2 = android.os.SystemProperties.getInt(r2, r3)
            r4 = 1
            if (r2 != r4) goto L4d
            r5 = -1
            int r6 = r1.hashCode()
            r7 = 2562(0xa02, float:3.59E-42)
            if (r6 == r7) goto L39
            r3 = 2566(0xa06, float:3.596E-42)
            if (r6 == r3) goto L2f
            goto L42
        L2f:
            java.lang.String r3 = "Q7"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L42
            r3 = r4
            goto L43
        L39:
            java.lang.String r4 = "Q3"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L42
            goto L43
        L42:
            r3 = r5
        L43:
            switch(r3) {
                case 0: goto L4a;
                case 1: goto L47;
                default: goto L46;
            }
        L46:
            goto L4d
        L47:
            java.lang.String r3 = "Q7A"
            return r3
        L4a:
            java.lang.String r3 = "Q3A"
            return r3
        L4d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.car.Car.getXpCduType():java.lang.String");
    }

    @SystemApi
    public static String getHardwareCarStage() {
        String versionFinger = SystemProperties.get(HARDWARE_SOFTWARE_VERSION_PROPERTY, CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE);
        if (CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE.equals(versionFinger)) {
            return versionFinger;
        }
        return versionFinger.substring(12, 13);
    }

    @SystemApi
    public static String getUuidCode() {
        return SystemProperties.get(PRIVATE_UUID_CODE_PROPERTY, CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE);
    }

    @SystemApi
    public static boolean isSystemFirstBoot() {
        String bootProperty = SystemProperties.get(SYSTEM_BOOT_FIRST_PROPERTY, CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE);
        return "1".equals(bootProperty);
    }
}
