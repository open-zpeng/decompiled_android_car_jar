package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class VehicleApPowerStateReq {
    public static final int CANCEL_SHUTDOWN = 2;
    public static final int FINISHED = 3;
    public static final int ON = 0;
    public static final int ON_SCREEN_OFF = 4;
    public static final int SHUTDOWN_PREPARE = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "ON";
        }
        if (o == 1) {
            return "SHUTDOWN_PREPARE";
        }
        if (o == 2) {
            return "CANCEL_SHUTDOWN";
        }
        if (o == 3) {
            return "FINISHED";
        }
        if (o == 4) {
            return "ON_SCREEN_OFF";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("ON");
        if ((o & 1) == 1) {
            list.add("SHUTDOWN_PREPARE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("CANCEL_SHUTDOWN");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("FINISHED");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("ON_SCREEN_OFF");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
