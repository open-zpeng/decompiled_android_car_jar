package android.car.hardware.srs;

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
public final class CarSrsManager extends CarEcuManager {
    public static final int AIRBAG_FAULT = 1;
    public static final int AIRBAG_NO_FAULT = 0;
    public static final int CRASH_OUTPUT_ACTIVE = 1;
    public static final int CRASH_OUTPUT_NOT_ACTIVE = 0;
    private static final boolean DBG = false;
    public static final int ID_BCM_2NDLEFTSEAT_BELTSBR_WARNING = 557849614;
    public static final int ID_BCM_2NDMIDSEAT_BELTSBR_WARNING = 557849615;
    public static final int ID_BCM_2NDRIGHTSEAT_BELTSBR_WARNING = 557849616;
    public static final int ID_BCM_2ND_BELT_WARNING = 557849636;
    public static final int ID_BCM_AIRBAG_FAULT_ST = 557849618;
    public static final int ID_BCM_ALL_CRASH_OUTPUT_ST = 557915485;
    public static final int ID_BCM_CRASH_OUTPUT_ST = 557849617;
    public static final int ID_BCM_DRVSEAT_BELTSBR_WARNING = 557849612;
    public static final int ID_BCM_PSNGRSEAT_BELTSBR_WARNING = 557849613;
    public static final int ID_BCM_SELF_CHECK = 557849619;
    public static final int ID_BCM_SRS_PSNGR_CRASH_OCCUR = 557849819;
    public static final int ID_SRS_PSNGR_OCCUPANCY_ST = 557849679;
    public static final int ID_SRS_RLSEAT_OCCUPANCY_ST = 557849800;
    public static final int ID_SRS_RMSEAT_OCCUPANCY_ST = 557849801;
    public static final int ID_SRS_RRSEAT_OCCUPANCY_ST = 557849802;
    public static final int SEAT_NOT_OCCUPIED = 0;
    public static final int SEAT_OCCUPIED = 1;
    public static final int SRS_BACK_BELT_WARNING_ACTIVE = 1;
    public static final int SRS_BACK_BELT_WARNING_NOT_ACTIVE = 0;
    public static final int SRS_NO_WARNING = 0;
    public static final int SRS_PASSENGER_NOT_ON_SEAT = 0;
    public static final int SRS_PASSENGER_ON_SEAT = 1;
    public static final int SRS_SELF_CHECK_CHECKING = 1;
    public static final int SRS_SELF_CHECK_DEFAULT = 0;
    public static final int SRS_SELF_CHECK_NOPASS = 3;
    public static final int SRS_SELF_CHECK_PASS = 2;
    public static final int SRS_WARNING = 1;
    public static final int SWITCH_OFF = 0;
    public static final int SWITCH_ON = 1;
    private static final String TAG = "CarSrsManager";
    private final IXpVehicle mService;
    private final ArraySet<Integer> mSrsPropertyIds;

    /* loaded from: classes.dex */
    public interface CarSrsEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarSrsManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mSrsPropertyIds = new ArraySet<>(Arrays.asList(557849679, 557849636, 557849612, 557849613, 557849614, 557849615, 557849616, 557849617, 557849618, 557849619, 557849800, 557849801, 557849802, 557849819, 557915485));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public static String getServiceName() {
        return Car.XP_SRS_SERVICE;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mSrsPropertyIds;
    }

    public void setBackBeltSw(int status) throws Exception {
        this.mService.setSrsBackBeltWarningEnabled(status);
    }

    public int getBackBeltSwStatus() throws Exception {
        return this.mService.isSrsBackBeltWarningEnabled();
    }

    public int getPsnOnSeat() throws Exception {
        return this.mService.isSrsPsnOnSeat();
    }

    public int getDriverBeltStatus() throws Exception {
        return this.mService.getSrsDrvBeltFastenStatus();
    }

    public int getPsnBeltStatus() throws Exception {
        return this.mService.getSrsPsnBeltFastenStatus();
    }

    public int getBackLeftBeltStatus() throws Exception {
        return this.mService.getSrsBackLeftBeltFastenStatus();
    }

    public int getBackMiddleBeltStatus() throws Exception {
        return this.mService.getSrsBackMiddleBeltFastenStatus();
    }

    public int getBackRightBeltStatus() throws Exception {
        return this.mService.getSrsBackRightBeltFastenStatus();
    }

    public int getCrashOutputSt() throws Exception {
        return this.mService.getSrsCrashOutputStatus();
    }

    public int[] getAllCrashOutputSt() throws Exception {
        return this.mService.getAllSrsCrashOutputStatus();
    }

    public int getAirbagFaultSt() throws Exception {
        return this.mService.getSrsAirbagFaultStatus();
    }

    public int getSelfCheckStatus() throws Exception {
        return this.mService.getSrsSelfCheckStatus();
    }

    public int getRearLeftSeatOccupancyStatus() throws Exception {
        return this.mService.getSrsRearLeftSeatOccupancyStatus();
    }

    public int getRearMiddleSeatOccupancyStatus() throws Exception {
        return this.mService.getSrsRearMiddleSeatOccupancyStatus();
    }

    public int getRearRightSeatOccupancyStatus() throws Exception {
        return this.mService.getSrsRearRightSeatOccupancyStatus();
    }

    public int getPassengerCrashOccurSwSt() throws Exception {
        return this.mService.getSrsPassengerCrashOccurSwSt();
    }

    public void setPassengerCrashOccurSw(int sw) throws Exception {
        this.mService.setSrsPassengerCrashOccurSw(sw);
    }
}
