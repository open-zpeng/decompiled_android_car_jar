package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class VehiclePropertyStatus {
    public static final int AVAILABLE = 0;
    public static final int ERROR = 2;
    public static final int UNAVAILABLE = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "AVAILABLE";
        }
        if (o == 1) {
            return "UNAVAILABLE";
        }
        if (o == 2) {
            return "ERROR";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("AVAILABLE");
        if ((o & 1) == 1) {
            list.add("UNAVAILABLE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("ERROR");
            flipped |= 2;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
