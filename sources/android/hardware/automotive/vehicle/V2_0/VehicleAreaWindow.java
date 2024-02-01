package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class VehicleAreaWindow {
    public static final int FRONT_WINDSHIELD = 1;
    public static final int REAR_WINDSHIELD = 2;
    public static final int ROOF_TOP_1 = 65536;
    public static final int ROOF_TOP_2 = 131072;
    public static final int ROW_1_LEFT = 16;
    public static final int ROW_1_RIGHT = 64;
    public static final int ROW_2_LEFT = 256;
    public static final int ROW_2_RIGHT = 1024;
    public static final int ROW_3_LEFT = 4096;
    public static final int ROW_3_RIGHT = 16384;

    public static final String toString(int o) {
        if (o == 1) {
            return "FRONT_WINDSHIELD";
        }
        if (o == 2) {
            return "REAR_WINDSHIELD";
        }
        if (o == 16) {
            return "ROW_1_LEFT";
        }
        if (o == 64) {
            return "ROW_1_RIGHT";
        }
        if (o == 256) {
            return "ROW_2_LEFT";
        }
        if (o == 1024) {
            return "ROW_2_RIGHT";
        }
        if (o == 4096) {
            return "ROW_3_LEFT";
        }
        if (o == 16384) {
            return "ROW_3_RIGHT";
        }
        if (o == 65536) {
            return "ROOF_TOP_1";
        }
        if (o == 131072) {
            return "ROOF_TOP_2";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("FRONT_WINDSHIELD");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("REAR_WINDSHIELD");
            flipped |= 2;
        }
        if ((o & 16) == 16) {
            list.add("ROW_1_LEFT");
            flipped |= 16;
        }
        if ((o & 64) == 64) {
            list.add("ROW_1_RIGHT");
            flipped |= 64;
        }
        if ((o & 256) == 256) {
            list.add("ROW_2_LEFT");
            flipped |= 256;
        }
        if ((o & 1024) == 1024) {
            list.add("ROW_2_RIGHT");
            flipped |= 1024;
        }
        if ((o & 4096) == 4096) {
            list.add("ROW_3_LEFT");
            flipped |= 4096;
        }
        if ((o & 16384) == 16384) {
            list.add("ROW_3_RIGHT");
            flipped |= 16384;
        }
        if ((o & 65536) == 65536) {
            list.add("ROOF_TOP_1");
            flipped |= 65536;
        }
        if ((o & 131072) == 131072) {
            list.add("ROOF_TOP_2");
            flipped |= 131072;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
