package android.car.hardware.vpm;

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
public final class CarVpmManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int ID_VPM_LDW_LEFT_WARNING_ST = 557852253;
    public static final int ID_VPM_LDW_RIGHT_WARNING_ST = 557852252;
    public static final int ID_VPM_RDP_LEFT_WARNING_ST = 557852376;
    public static final int ID_VPM_RDP_RIGHT_WARNING_ST = 557852375;
    private static final String TAG = "CarVpmManager";
    public static final int VPM_LDW_NODISPLAY = 0;
    public static final int VPM_LDW_TRACKING = 1;
    public static final int VPM_LDW_WARNING = 2;
    public static final int VPM_RDP_ELK_LDW_WARNING = 2;
    public static final int VPM_RDP_ELK_WARNING = 4;
    public static final int VPM_RDP_LKA_WARNING_1 = 3;
    public static final int VPM_RDP_LKA_WARNING_2 = 1;
    public static final int VPM_RDP_NODISPLAY = 0;
    private final IXpVehicle mService;
    private final ArraySet<Integer> mVpmPropertyIds;

    /* loaded from: classes.dex */
    public interface CarVpmEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarVpmManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mVpmPropertyIds = new ArraySet<>(Arrays.asList(557852253, 557852252, 557852376, 557852375));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mVpmPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_VPM_SERVICE;
    }

    public int getLdwLeftWarningStatus() throws Exception {
        return this.mService.getVpmLdwLeftWarningStatus();
    }

    public int getLdwRightWarningStatus() throws Exception {
        return this.mService.getVpmLdwRightWarningStatus();
    }

    public int getRdpLeftWarningStatus() throws Exception {
        return this.mService.getVpmRdpLeftWarningStatus();
    }

    public int getRdpRightWarningStatus() throws Exception {
        return this.mService.getVpmRdpRightWarningStatus();
    }
}
