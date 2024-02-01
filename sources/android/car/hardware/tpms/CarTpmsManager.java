package android.car.hardware.tpms;

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
public final class CarTpmsManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int ID_TPMS_ABNORMAL_TIRE_PRESSURE_WARN = 557850119;
    public static final int ID_TPMS_ALL_SENSOR_ST = 557915664;
    public static final int ID_TPMS_PRFL = 559947266;
    public static final int ID_TPMS_PRFR = 559947267;
    public static final int ID_TPMS_PRRL = 559947268;
    public static final int ID_TPMS_PRRR = 559947269;
    @Deprecated
    public static final int ID_TPMS_PR_WARN_FL = 557850120;
    @Deprecated
    public static final int ID_TPMS_PR_WARN_FR = 557850121;
    @Deprecated
    public static final int ID_TPMS_PR_WARN_RL = 557850122;
    @Deprecated
    public static final int ID_TPMS_PR_WARN_RR = 557850123;
    public static final int ID_TPMS_SYS_FAULT_WARN = 557850118;
    public static final int ID_TPMS_TEMP_ALL = 557915663;
    public static final int ID_TPMS_TIRE_PRESSURE = 557850113;
    public static final int ID_TPMS_TIRE_PRESSURE_SENSOR_STATUS_ALL = 557915661;
    public static final int ID_TPMS_WARNING_TIRE_PRESSURE_ALL = 557915660;
    public static final int ID_TPMS_WARNING_TIRE_TEMPERATURE_ALL = 557915662;
    private static final String TAG = "CarTpmsManager";
    public static final int TPMS_HAS_WARNING = 1;
    public static final int TPMS_HIGH_PRESSURE_WARNING = 4;
    public static final int TPMS_LEFT_FRONT = 1;
    public static final int TPMS_LEFT_REAR = 3;
    public static final int TPMS_LITTLE_LOW_PRESSURE_WARNING = 1;
    public static final int TPMS_LOW_PRESSURE_WARNING = 2;
    public static final int TPMS_NO_WARNING = 0;
    public static final int TPMS_RIGHT_FRONT = 2;
    public static final int TPMS_RIGHT_REAR = 4;
    public static final int TPMS_SENSOR_HAS_FAULT = 1;
    public static final int TPMS_SENSOR_NORMAL = 0;
    public static final int TPMS_TIRE_PRESSURE_FAIL = 3;
    public static final int TPMS_TIRE_PRESSURE_FIXED = 2;
    public static final int TPMS_TIRE_PRESSURE_FIXING = 1;
    public static final int TPMS_TIRE_PRESSURE_NOT_FIX = 0;
    @Deprecated
    public static final int TPMS_TIRE_TYPE_NOT_FIX = 0;
    @Deprecated
    public static final int TPMS_TIRE_TYRE_FAIL = 3;
    @Deprecated
    public static final int TPMS_TIRE_TYRE_FIXED = 2;
    @Deprecated
    public static final int TPMS_TIRE_TYRE_FIXING = 1;
    public static final int TPMS_WARNING_INVALID = 7;
    public static final int TPMS_WARN_LAMP_OFF = 0;
    public static final int TPMS_WARN_LAMP_ON = 1;
    public static final int WHEEL_SENSOR_BATTERY_VOLTAGE_LOW = 1;
    public static final int WHEEL_SENSOR_BATTERY_VOLTAGE_NORMAL = 0;
    private final IXpVehicle mService;
    private final ArraySet<Integer> mTpmsPropertyIds;

    /* loaded from: classes.dex */
    public interface CarTpmsEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarTpmsManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mTpmsPropertyIds = new ArraySet<>(Arrays.asList(557850113, 559947266, 559947267, 559947268, 559947269, 557850118, 557850119, 557915660, 557915662, 557915661, 557915663, 557915664));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mTpmsPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_TPMS_SERVICE;
    }

    public void calibrateTirePressure() throws Exception {
        this.mService.calibrateTpmsTirePressure();
    }

    public int getTirePressureStatus() throws Exception {
        return this.mService.getTpmsTirePressureStatus();
    }

    public float getTirePressureValue(int position) throws Exception {
        return this.mService.getTpmsTirePressureValue(position);
    }

    public int getSystemFaultWarnLampStatus() throws Exception {
        return this.mService.getTpmsSystemFaultWarnLampStatus();
    }

    public int getAbnormalTirePressureWarnLampStatus() throws Exception {
        return this.mService.getTpmsAbnormalTirePressureWarnLampStatus();
    }

    @Deprecated
    public int getTirePressureWarningInfo(int position) throws Exception {
        return this.mService.getTpmsTirePressureWarningInfo(position);
    }

    public int[] getAllTirePressureWarnings() throws Exception {
        return this.mService.getTpmsAllTirePressureWarnings();
    }

    public int[] getAllTireTemperatureWarnings() throws Exception {
        return this.mService.getTpmsAllTireTemperatureWarnings();
    }

    public int[] getAllTirePerssureSensorStatus() throws Exception {
        return this.mService.getTpmsllTirePerssureSensorStatus();
    }

    public int[] getAllTireTemperature() throws Exception {
        return this.mService.getTpmsAllTireTemperature();
    }

    public int[] getAllSensorStatus() throws Exception {
        return this.mService.getTpmsAllSensorStatus();
    }
}
