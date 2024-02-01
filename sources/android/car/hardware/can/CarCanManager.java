package android.car.hardware.can;

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
public final class CarCanManager extends CarEcuManager {
    private static final boolean DBG = false;
    public static final int ID_ADAS_META = 560993313;
    public static final int ID_ADAS_POSITION = 560993314;
    public static final int ID_ADAS_PROFLONG = 560993315;
    public static final int ID_ADAS_PROFSHORT = 560993316;
    public static final int ID_ADAS_SEGMENT = 560993317;
    public static final int ID_ADAS_STUB = 560993318;
    public static final int ID_CAN_RAW_DATA = 560993325;
    private static final String TAG = "CarCanManager";
    private final ArraySet<Integer> mCanPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarCanEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarCanManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mCanPropertyIds = new ArraySet<>(Arrays.asList(560993325, 560993313, 560993314, 560993315, 560993316, 560993317, 560993318));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mCanPropertyIds;
    }

    public void setAdasMeta(byte[] metaValues) throws Exception {
        this.mService.setAdasMeta(metaValues);
    }

    public void setAdasPosition(byte[] positionValues) throws Exception {
        this.mService.setAdasPosition(positionValues);
    }

    public void setAdasProfLong(byte[] profLongValues) throws Exception {
        this.mService.setAdasProfLong(profLongValues);
    }

    public void setAdasProfShort(byte[] profShortValues) throws Exception {
        this.mService.setAdasProfShort(profShortValues);
    }

    public void setAdasSegment(byte[] segmentValues) throws Exception {
        this.mService.setAdasSegment(segmentValues);
    }

    public void setAdasStub(byte[] stubValues) throws Exception {
        this.mService.setAdasStub(stubValues);
    }

    public static String getServiceName() {
        return Car.XP_CAN_SERVICE;
    }
}
