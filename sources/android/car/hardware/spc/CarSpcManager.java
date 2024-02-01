package android.car.hardware.spc;

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
public class CarSpcManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int ID_SPC_GROSS_GENERATION = 559954946;
    public static final int ID_SPC_RECENT_GENERATED_ENERGY = 559954947;
    public static final int ID_SPC_SOLAR_POWER = 559954948;
    public static final int ID_SPC_SOLAR_WORK_ST = 557857793;
    public static final int SPC_STATUS_OFF = 0;
    public static final int SPC_STATUS_ON = 1;
    private static final String TAG = "CarSpcManager";
    private final IXpVehicle mService;
    private final ArraySet<Integer> mSpcPropertyIds;

    /* loaded from: classes.dex */
    public interface CarSpcEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarSpcManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mSpcPropertyIds = new ArraySet<>(Arrays.asList(557857793, 559954946, 559954947, 559954948));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public static String getServiceName() {
        return Car.XP_SPC_SERVICE;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mSpcPropertyIds;
    }

    public int getSolarWorkSt() throws Exception {
        return this.mService.getSpcSolarWorkSt();
    }

    public float getGrossEnergyGeneration() throws Exception {
        return this.mService.getSpcGrossEnergyGeneration();
    }

    public float getRecentEnergyGeneration() throws Exception {
        return this.mService.getSpcRecentEnergyGeneration();
    }

    public float getSolarPower() throws Exception {
        return this.mService.getSpcSolarPower();
    }
}
