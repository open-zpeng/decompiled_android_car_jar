package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class VehicleApPowerStateReport {
    public static final int DEEP_SLEEP_ENTRY = 2;
    public static final int DEEP_SLEEP_EXIT = 3;
    public static final int ON = 6;
    public static final int SHUTDOWN_CANCELLED = 8;
    public static final int SHUTDOWN_POSTPONE = 4;
    public static final int SHUTDOWN_PREPARE = 7;
    public static final int SHUTDOWN_START = 5;
    public static final int WAIT_FOR_VHAL = 1;

    public static final String toString(int o) {
        if (o == 1) {
            return "WAIT_FOR_VHAL";
        }
        if (o == 2) {
            return "DEEP_SLEEP_ENTRY";
        }
        if (o == 3) {
            return "DEEP_SLEEP_EXIT";
        }
        if (o == 4) {
            return "SHUTDOWN_POSTPONE";
        }
        if (o == 5) {
            return "SHUTDOWN_START";
        }
        if (o == 6) {
            return "ON";
        }
        if (o == 7) {
            return "SHUTDOWN_PREPARE";
        }
        if (o == 8) {
            return "SHUTDOWN_CANCELLED";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("WAIT_FOR_VHAL");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("DEEP_SLEEP_ENTRY");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("DEEP_SLEEP_EXIT");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("SHUTDOWN_POSTPONE");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("SHUTDOWN_START");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("ON");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("SHUTDOWN_PREPARE");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("SHUTDOWN_CANCELLED");
            flipped |= 8;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
