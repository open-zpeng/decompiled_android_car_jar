package android.car.hardware.eps;

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
public final class CarEpsManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int EPS_ANGLE_INVALID = 0;
    public static final int EPS_ANGLE_VALID = 1;
    public static final int EPS_POWER_ASSISTED_SOFT = 1;
    public static final int EPS_POWER_ASSISTED_SPORT = 2;
    public static final int EPS_POWER_ASSISTED_STANDARD = 0;
    public static final int ID_EPS_STEERING_ANGLE = 559948806;
    public static final int ID_EPS_STEERING_ANGLE_SPD = 557851655;
    public static final int ID_EPS_STEER_WHEEL_EPS = 557851653;
    public static final int ID_EPS_TORQ_CTRL_ST = 557851676;
    public static final int ID_EPS_TORSION_BAR_TORQ = 559948821;
    public static final int POWER_STATUS_ASSISTED_INVALID = 3;
    public static final int POWER_STATUS_ASSISTED_SOFT = 1;
    public static final int POWER_STATUS_ASSISTED_SPORT = 2;
    public static final int POWER_STATUS_ASSISTED_STANDARD = 0;
    private static final String TAG = "CarEpsManager";
    public static final int TORQ_CTRL_ST_CONTROL_REQUEST_NOT_ALLOWED_PERMANET = 3;
    public static final int TORQ_CTRL_ST_CONTROL_REQUEST_NOT_ALLOWED_TEMPORARILY = 2;
    public static final int TORQ_CTRL_ST_NO_REQUEST = 0;
    public static final int TORQ_CTRL_ST_REQUEST_HONORED = 1;
    private final ArraySet<Integer> mEpsPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarEpsEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarEpsManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mEpsPropertyIds = new ArraySet<>(Arrays.asList(557851653, 559948806, 557851655, 559948821, 557851676));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mEpsPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_EPS_SERVICE;
    }

    public void setSteeringWheelEPS(int type) throws Exception {
        this.mService.setEpsWorkMode(type);
    }

    public int getSteeringWheelEPS() throws Exception {
        return this.mService.getEpsWorkMode();
    }

    public float getSteeringAngle() throws Exception {
        return this.mService.getEpsSteeringAngle();
    }

    public float getSteeringAngleSpd() throws Exception {
        return this.mService.getEpsSteeringAngleSpeed();
    }

    public float getTorsionBarTorque() throws Exception {
        return this.mService.getEpsTorsionBarTorque();
    }

    public int getTorqControlStatus() throws Exception {
        return this.mService.getEpsTorqControlStatus();
    }
}
