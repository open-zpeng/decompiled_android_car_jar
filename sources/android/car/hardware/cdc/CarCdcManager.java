package android.car.hardware.cdc;

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
public final class CarCdcManager extends CarEcuManager {
    public static final int CDC_FUNCTION_MODE_COMFORT = 2;
    public static final int CDC_FUNCTION_MODE_SPORT = 3;
    public static final int CDC_FUNCTION_MODE_STANDARD = 1;
    private static final boolean DBG = false;
    public static final int ID_CDC_FUNC_STYLE = 557854724;
    private static final String TAG = "CarCdcManager";
    private final ArraySet<Integer> mCdcPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarCdcEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarCdcManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mCdcPropertyIds = new ArraySet<>(Arrays.asList(557854724));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mCdcPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_CDC_SERVICE;
    }

    public void setCdcFunctionStyle(int style) throws Exception {
        this.mService.setCdcFunctionMode(style);
    }

    public int getCdcFunctionStyle() throws Exception {
        return this.mService.getCdcFunctionMode();
    }
}
