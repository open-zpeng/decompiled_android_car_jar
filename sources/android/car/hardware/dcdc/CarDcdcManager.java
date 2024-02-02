package android.car.hardware.dcdc;

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
public final class CarDcdcManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int ID_DCDC_FAIL_ST = 557854721;
    public static final int ID_DCDC_INPUT_CURRENT = 559951878;
    public static final int ID_DCDC_INPUT_VOLTAGE = 557854725;
    public static final int ID_DCDC_OPERATING_MODE = 557854722;
    private static final String TAG = "CarDcdcManager";
    private final ArraySet<Integer> mDcdcPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarDcdcEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarDcdcManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mDcdcPropertyIds = new ArraySet<>(Arrays.asList(557854721, 557854722, 557854725, 559951878));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mDcdcPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_DCDC_SERVICE;
    }

    public int getDcdcFailStInfo() throws Exception {
        return this.mService.getDcdcFailStInfo();
    }

    public int getDcdcStatus() throws Exception {
        return this.mService.getDcdcStatus();
    }

    public int getDcdcInputVoltage() throws Exception {
        return this.mService.getDcdcInputVoltage();
    }

    public float getDcdcInputCurrent() throws Exception {
        return this.mService.getDcdcInputCurrent();
    }
}
