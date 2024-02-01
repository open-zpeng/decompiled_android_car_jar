package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class McuPmState {
    public static final int NORMAL = 1;
    public static final int OFF = 3;
    public static final int SLEEP = 2;
    public static final int SLEEP_READY = 21;

    public static final String toString(int o) {
        if (o == 1) {
            return "NORMAL";
        }
        if (o == 2) {
            return "SLEEP";
        }
        if (o == 21) {
            return "SLEEP_READY";
        }
        if (o == 3) {
            return "OFF";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("NORMAL");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("SLEEP");
            flipped |= 2;
        }
        if ((o & 21) == 21) {
            list.add("SLEEP_READY");
            flipped |= 21;
        }
        if ((o & 3) == 3) {
            list.add("OFF");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
