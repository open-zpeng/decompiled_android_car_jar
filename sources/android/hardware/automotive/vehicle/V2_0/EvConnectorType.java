package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class EvConnectorType {
    public static final int GBT_AC = 10;
    public static final int GBT_DC = 11;
    public static final int IEC_TYPE_1_AC = 1;
    public static final int IEC_TYPE_1_CCS_DC = 5;
    public static final int IEC_TYPE_2_AC = 2;
    public static final int IEC_TYPE_2_CCS_DC = 6;
    public static final int IEC_TYPE_3_AC = 3;
    public static final int IEC_TYPE_4_DC = 4;
    public static final int OTHER = 101;
    public static final int TESLA_HPWC = 8;
    public static final int TESLA_ROADSTER = 7;
    public static final int TESLA_SUPERCHARGER = 9;
    public static final int UNKNOWN = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "UNKNOWN";
        }
        if (o == 1) {
            return "IEC_TYPE_1_AC";
        }
        if (o == 2) {
            return "IEC_TYPE_2_AC";
        }
        if (o == 3) {
            return "IEC_TYPE_3_AC";
        }
        if (o == 4) {
            return "IEC_TYPE_4_DC";
        }
        if (o == 5) {
            return "IEC_TYPE_1_CCS_DC";
        }
        if (o == 6) {
            return "IEC_TYPE_2_CCS_DC";
        }
        if (o == 7) {
            return "TESLA_ROADSTER";
        }
        if (o == 8) {
            return "TESLA_HPWC";
        }
        if (o == 9) {
            return "TESLA_SUPERCHARGER";
        }
        if (o == 10) {
            return "GBT_AC";
        }
        if (o == 11) {
            return "GBT_DC";
        }
        if (o == 101) {
            return "OTHER";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("UNKNOWN");
        if ((o & 1) == 1) {
            list.add("IEC_TYPE_1_AC");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("IEC_TYPE_2_AC");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("IEC_TYPE_3_AC");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("IEC_TYPE_4_DC");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("IEC_TYPE_1_CCS_DC");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("IEC_TYPE_2_CCS_DC");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("TESLA_ROADSTER");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("TESLA_HPWC");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("TESLA_SUPERCHARGER");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("GBT_AC");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("GBT_DC");
            flipped |= 11;
        }
        if ((o & 101) == 101) {
            list.add("OTHER");
            flipped |= 101;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
