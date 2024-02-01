package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class SubscribeFlags {
    public static final int EVENTS_FROM_ANDROID = 2;
    public static final int EVENTS_FROM_CAR = 1;
    public static final int UNDEFINED = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "UNDEFINED";
        }
        if (o == 1) {
            return "EVENTS_FROM_CAR";
        }
        if (o == 2) {
            return "EVENTS_FROM_ANDROID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("UNDEFINED");
        if ((o & 1) == 1) {
            list.add("EVENTS_FROM_CAR");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("EVENTS_FROM_ANDROID");
            flipped |= 2;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
