package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class VehicleSeatOccupancyState {
    public static final int OCCUPIED = 2;
    public static final int UNKNOWN = 0;
    public static final int VACANT = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "UNKNOWN";
        }
        if (o == 1) {
            return "VACANT";
        }
        if (o == 2) {
            return "OCCUPIED";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("UNKNOWN");
        if ((o & 1) == 1) {
            list.add("VACANT");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("OCCUPIED");
            flipped |= 2;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
