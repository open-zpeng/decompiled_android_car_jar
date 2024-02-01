package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class VehicleOilLevel {
    public static final int CRITICALLY_LOW = 0;
    public static final int ERROR = 4;
    public static final int HIGH = 3;
    public static final int LOW = 1;
    public static final int NORMAL = 2;

    public static final String toString(int o) {
        if (o == 0) {
            return "CRITICALLY_LOW";
        }
        if (o == 1) {
            return "LOW";
        }
        if (o == 2) {
            return "NORMAL";
        }
        if (o == 3) {
            return "HIGH";
        }
        if (o == 4) {
            return "ERROR";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("CRITICALLY_LOW");
        if ((o & 1) == 1) {
            list.add("LOW");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("NORMAL");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("HIGH");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("ERROR");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
