package android.car;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class VehiclePropertyType {
    public static final int BOOLEAN = 2097152;
    public static final int BYTES = 7340032;
    public static final int FLOAT = 6291456;
    public static final int FLOAT_VEC = 6356992;
    public static final int INT32 = 4194304;
    public static final int INT32_VEC = 4259840;
    public static final int INT64 = 5242880;
    public static final int INT64_VEC = 5308416;
    public static final int MASK = 16711680;
    public static final int MIXED = 14680064;
    public static final int STRING = 1048576;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Enum {
    }

    private VehiclePropertyType() {
    }
}
