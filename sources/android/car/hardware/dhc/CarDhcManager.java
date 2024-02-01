package android.car.hardware.dhc;

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
public final class CarDhcManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int DHC_STATUS_ACTIVE = 1;
    public static final int DHC_STATUS_INACTIVE = 0;
    public static final int ID_DHC_ACTIVE_SW = 557849711;
    private static final String TAG = "CarDhcManager";
    private final ArraySet<Integer> mDhcPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarDhcEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarDhcManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mDhcPropertyIds = new ArraySet<>(Arrays.asList(557849711));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public static String getServiceName() {
        return Car.XP_DHC_SERVICE;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mDhcPropertyIds;
    }

    public void setDhcActiveSw(int status) throws Exception {
        this.mService.setDhcDoorknobAutoOpenEnabled(status);
    }

    public int getDhcActiveSw() throws Exception {
        return this.mService.isDhcDoorknobAutoOpenEnabled();
    }
}
