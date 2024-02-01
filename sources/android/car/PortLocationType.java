package android.car;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class PortLocationType {
    public static final int FRONT = 5;
    public static final int FRONT_LEFT = 1;
    public static final int FRONT_RIGHT = 2;
    public static final int REAR = 6;
    public static final int REAR_LEFT = 4;
    public static final int REAR_RIGHT = 3;
    public static final int UNKNOWN = 0;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Enum {
    }

    private PortLocationType() {
    }
}
