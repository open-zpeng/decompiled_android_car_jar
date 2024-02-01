package android.car.hardware.ccs;

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
public final class CarCcsManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int ID_CCS_FAULT_INFO = 557854723;
    private static final String TAG = "CarCcsManager";
    private final ArraySet<Integer> mCcsPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarCcsEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarCcsManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mCcsPropertyIds = new ArraySet<>(Arrays.asList(557854723));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mCcsPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_CCS_SERVICE;
    }

    public int getCcsFaultInfo() throws Exception {
        return this.mService.getCcsFaultInfo();
    }
}
