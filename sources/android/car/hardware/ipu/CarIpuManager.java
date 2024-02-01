package android.car.hardware.ipu;

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
public final class CarIpuManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int ID_IPU_ACTHV_CUR = 557853185;
    public static final int ID_IPU_ACTHV_VOLT = 557853191;
    public static final int ID_IPU_ACT_ROT_SPD = 557853187;
    public static final int ID_IPU_ACT_TORQ = 559950341;
    public static final int ID_IPU_FAIL_ST = 557847103;
    public static final int ID_IPU_INVERTER_ACT_TEMP = 557853190;
    public static final int ID_IPU_MOTOR_ACT_TEMP = 557853188;
    public static final int ID_IPU_ST_MODE = 557853186;
    private static final String TAG = "CarIpuManager";
    private final ArraySet<Integer> mIpuPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarIpuEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarIpuManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mIpuPropertyIds = new ArraySet<>(Arrays.asList(557847103, 557853191, 557853185, 557853190, 557853188, 559950341, 557853187, 557853186));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mIpuPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_IPU_SERVICE;
    }

    public int getIpuFailStInfo() throws Exception {
        return this.mService.getIpuFailStInfo();
    }

    public int getCtrlVolt() throws Exception {
        return this.mService.getCtrlVolt();
    }

    public int getCtrlCurr() throws Exception {
        return this.mService.getCtrlCurr();
    }

    public int getCtrlTemp() throws Exception {
        return this.mService.getCtrlTemp();
    }

    public int getMotorTemp() throws Exception {
        return this.mService.getMotorTemp();
    }

    public float getTorque() throws Exception {
        return this.mService.getTorque();
    }

    public int getRollSpeed() throws Exception {
        return this.mService.getRollSpeed();
    }

    public int getMotorStatus() throws Exception {
        return this.mService.getMotorStatus();
    }
}
