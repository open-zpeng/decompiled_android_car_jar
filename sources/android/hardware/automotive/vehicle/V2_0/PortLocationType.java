package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class PortLocationType {
    public static final int FRONT = 5;
    public static final int FRONT_LEFT = 1;
    public static final int FRONT_RIGHT = 2;
    public static final int REAR = 6;
    public static final int REAR_LEFT = 4;
    public static final int REAR_RIGHT = 3;
    public static final int UNKNOWN = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "UNKNOWN";
        }
        if (o == 1) {
            return "FRONT_LEFT";
        }
        if (o == 2) {
            return "FRONT_RIGHT";
        }
        if (o == 3) {
            return "REAR_RIGHT";
        }
        if (o == 4) {
            return "REAR_LEFT";
        }
        if (o == 5) {
            return "FRONT";
        }
        if (o == 6) {
            return "REAR";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("UNKNOWN");
        if ((o & 1) == 1) {
            list.add("FRONT_LEFT");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("FRONT_RIGHT");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("REAR_RIGHT");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("REAR_LEFT");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("FRONT");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("REAR");
            flipped |= 6;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
