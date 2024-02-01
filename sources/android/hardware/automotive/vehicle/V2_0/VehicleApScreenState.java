package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class VehicleApScreenState {
    public static final int OFF = 2;
    public static final int ON = 1;

    public static final String toString(int o) {
        if (o == 1) {
            return "ON";
        }
        if (o == 2) {
            return "OFF";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("ON");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("OFF");
            flipped |= 2;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
