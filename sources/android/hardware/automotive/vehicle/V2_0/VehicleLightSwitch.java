package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class VehicleLightSwitch {
    public static final int AUTOMATIC = 256;
    public static final int DAYTIME_RUNNING = 2;
    public static final int OFF = 0;
    public static final int ON = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "OFF";
        }
        if (o == 1) {
            return "ON";
        }
        if (o == 2) {
            return "DAYTIME_RUNNING";
        }
        if (o == 256) {
            return "AUTOMATIC";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("OFF");
        if ((o & 1) == 1) {
            list.add("ON");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("DAYTIME_RUNNING");
            flipped |= 2;
        }
        if ((o & 256) == 256) {
            list.add("AUTOMATIC");
            flipped |= 256;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
