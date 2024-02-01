package android.car;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SystemApi
/* loaded from: classes.dex */
public final class VehicleAreaType {
    public static final int VEHICLE_AREA_TYPE_DOOR = 4;
    public static final int VEHICLE_AREA_TYPE_GLOBAL = 0;
    public static final int VEHICLE_AREA_TYPE_MIRROR = 5;
    public static final int VEHICLE_AREA_TYPE_SEAT = 3;
    public static final int VEHICLE_AREA_TYPE_WHEEL = 6;
    public static final int VEHICLE_AREA_TYPE_WINDOW = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface VehicleAreaTypeValue {
    }

    private VehicleAreaType() {
    }
}
