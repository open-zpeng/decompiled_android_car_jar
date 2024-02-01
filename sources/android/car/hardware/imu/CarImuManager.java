package android.car.hardware.imu;

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
public class CarImuManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int ID_IMU_ADDDATA = 558970375;
    public static final int ID_IMU_DIAGMSG = 557921800;
    public static final int ID_IMU_NAVDATA = 560018953;
    public static final int ID_IMU_NAVDATA_CAN = 560018960;
    public static final int ID_IMU_QUAT = 560018946;
    public static final int ID_IMU_SCULOCAT_CARSPEED = 560018957;
    public static final int ID_IMU_SCULOCAT_CARSPEED_CAN = 560018959;
    public static final int ID_IMU_SYSST = 560018945;
    public static final int ID_IMU_SYSST_CAN = 560018958;
    public static final int ID_IMU_TBOX_PACKGGA = 557921798;
    public static final int ID_IMU_UBXPVT1 = 560018947;
    public static final int ID_IMU_UBXPVT1_NUM_SV = 557856267;
    public static final int ID_IMU_UBXPVT2 = 560018948;
    public static final int ID_IMU_UBXRAWX = 560018949;
    public static final int ID_IMU_UBXSFRBX = 560018954;
    public static final int ID_IMU_UBX_PVT2_RSSI = 557856268;
    private static final String TAG = "CarImuManager";
    private final ArraySet<Integer> mImuPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarImuEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarImuManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mImuPropertyIds = new ArraySet<>(Arrays.asList(560018945, 560018946, 560018947, 560018948, 560018949, 557921798, 558970375, 557921800, 560018953, 560018954, 557856267, 557856268, 560018957, 560018958, 560018960, 560018959));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mImuPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_IMU_SERVICE;
    }

    public float[] getSystemState() throws Exception {
        return this.mService.getImuSystemState();
    }

    public float[] getQuatData() throws Exception {
        return this.mService.getImuQuatData();
    }

    public float[] getUbxPvtData1() throws Exception {
        return this.mService.getImuUbxPvtData1();
    }

    public float[] getUbxPvtData2() throws Exception {
        return this.mService.getImuUbxPvtData2();
    }

    public float[] getUbxRawXData() throws Exception {
        return this.mService.getImuUbxRawXData();
    }

    public int[] getTboxPackGgaData() throws Exception {
        return this.mService.getImuTboxPackGgaData();
    }

    public long[] getAddData() throws Exception {
        return this.mService.getImuAddData();
    }

    public int[] getDiagMessage() throws Exception {
        return this.mService.getImuDiagMessage();
    }

    public float[] getNavigationData() throws Exception {
        return this.mService.getImuNavigationData();
    }

    public float[] getUbxSfrbxData() throws Exception {
        return this.mService.getImuUbxSfrbxData();
    }

    public int getNavigationSatellitesNumber() throws Exception {
        return this.mService.getImuSatellitesNumber();
    }

    public int getNavigationSatellitesRssi() throws Exception {
        return this.mService.getImuNavigationSatellitesRssi();
    }

    public float[] getSystemStateAndSpeed() throws Exception {
        return this.mService.getImuSystemStateAndSpeed();
    }

    public float[] getSystemStateFromCan() throws Exception {
        return this.mService.getImuSystemStateFromCan();
    }

    public float[] getNavigationDataFromCan() throws Exception {
        return this.mService.getImuNavigationDataFromCan();
    }

    public float[] getSystemStateAndSpeedFromCan() throws Exception {
        return this.mService.getImuSystemStateAndSpeedFromCan();
    }
}
