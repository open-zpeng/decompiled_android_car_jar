package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class BATT_VOLTAGE_STATUS {
    public static final int BATT_NORMAL = 4;
    public static final int BATT_ONE_LEVEL_LOW = 3;
    public static final int BATT_ONE_LEVEL_OVER = 5;
    public static final int BATT_THREE_LEVEL_LOW = 1;
    public static final int BATT_THREE_LEVEL_OVER = 7;
    public static final int BATT_TWO_LEVEL_LOW = 2;
    public static final int BATT_TWO_LEVEL_OVER = 6;
    public static final int Not_ready = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "Not_ready";
        }
        if (o == 1) {
            return "BATT_THREE_LEVEL_LOW";
        }
        if (o == 2) {
            return "BATT_TWO_LEVEL_LOW";
        }
        if (o == 3) {
            return "BATT_ONE_LEVEL_LOW";
        }
        if (o == 4) {
            return "BATT_NORMAL";
        }
        if (o == 5) {
            return "BATT_ONE_LEVEL_OVER";
        }
        if (o == 6) {
            return "BATT_TWO_LEVEL_OVER";
        }
        if (o == 7) {
            return "BATT_THREE_LEVEL_OVER";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("Not_ready");
        if ((o & 1) == 1) {
            list.add("BATT_THREE_LEVEL_LOW");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("BATT_TWO_LEVEL_LOW");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("BATT_ONE_LEVEL_LOW");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("BATT_NORMAL");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("BATT_ONE_LEVEL_OVER");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("BATT_TWO_LEVEL_OVER");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("BATT_THREE_LEVEL_OVER");
            flipped |= 7;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
