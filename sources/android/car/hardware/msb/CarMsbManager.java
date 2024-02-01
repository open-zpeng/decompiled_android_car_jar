package android.car.hardware.msb;

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
public final class CarMsbManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int ID_MSB_SWST = 557849635;
    public static final int MSB_STATUS_OFF = 0;
    public static final int MSB_STATUS_ON = 1;
    private static final String TAG = "CarMsbManager";
    private final ArraySet<Integer> mMsbPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarMsbEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarMsbManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mMsbPropertyIds = new ArraySet<>(Arrays.asList(557849635));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mMsbPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_MSB_SERVICE;
    }

    public void setMsbStatus(int enable) throws Exception {
        this.mService.setMsbEnabled(enable);
    }

    public int getMsbStatus() throws Exception {
        return this.mService.isMsbEnabled();
    }
}
