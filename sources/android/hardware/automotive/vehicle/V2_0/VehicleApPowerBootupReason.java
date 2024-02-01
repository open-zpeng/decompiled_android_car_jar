package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class VehicleApPowerBootupReason {
    public static final int BAT_CHARGE_ST = 7;
    public static final int INVALID = -1;
    public static final int LOCAL_IGON = 4;
    public static final int LOCAL_LIGHT = 8;
    public static final int REMOTE_CAMERA = 5;
    public static final int REMOTE_LIGHT = 6;
    public static final int REMOTE_OTA = 9;
    public static final int REMOTE_SCU = 10;
    public static final int SOLDIER_MODE = 11;
    public static final int THERMAL_RUNAWAY = 13;
    public static final int TIMER = 2;
    public static final int USER_POWER_ON = 0;
    public static final int USER_UNLOCK = 1;
    public static final int WIFI_KEY_WK = 12;

    public static final String toString(int o) {
        if (o == -1) {
            return "INVALID";
        }
        if (o == 0) {
            return "USER_POWER_ON";
        }
        if (o == 1) {
            return "USER_UNLOCK";
        }
        if (o == 2) {
            return "TIMER";
        }
        if (o == 4) {
            return "LOCAL_IGON";
        }
        if (o == 5) {
            return "REMOTE_CAMERA";
        }
        if (o == 6) {
            return "REMOTE_LIGHT";
        }
        if (o == 7) {
            return "BAT_CHARGE_ST";
        }
        if (o == 8) {
            return "LOCAL_LIGHT";
        }
        if (o == 9) {
            return "REMOTE_OTA";
        }
        if (o == 10) {
            return "REMOTE_SCU";
        }
        if (o == 11) {
            return "SOLDIER_MODE";
        }
        if (o == 12) {
            return "WIFI_KEY_WK";
        }
        if (o == 13) {
            return "THERMAL_RUNAWAY";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & (-1)) == -1) {
            list.add("INVALID");
            flipped = 0 | (-1);
        }
        list.add("USER_POWER_ON");
        if ((o & 1) == 1) {
            list.add("USER_UNLOCK");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("TIMER");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("LOCAL_IGON");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("REMOTE_CAMERA");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("REMOTE_LIGHT");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("BAT_CHARGE_ST");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("LOCAL_LIGHT");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("REMOTE_OTA");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("REMOTE_SCU");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("SOLDIER_MODE");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("WIFI_KEY_WK");
            flipped |= 12;
        }
        if ((o & 13) == 13) {
            list.add("THERMAL_RUNAWAY");
            flipped |= 13;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
