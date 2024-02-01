package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class FuelType {
    public static final int FUEL_TYPE_BIODIESEL = 5;
    public static final int FUEL_TYPE_CNG = 8;
    public static final int FUEL_TYPE_DIESEL_1 = 3;
    public static final int FUEL_TYPE_DIESEL_2 = 4;
    public static final int FUEL_TYPE_E85 = 6;
    public static final int FUEL_TYPE_ELECTRIC = 10;
    public static final int FUEL_TYPE_HYDROGEN = 11;
    public static final int FUEL_TYPE_LEADED = 2;
    public static final int FUEL_TYPE_LNG = 9;
    public static final int FUEL_TYPE_LPG = 7;
    public static final int FUEL_TYPE_OTHER = 12;
    public static final int FUEL_TYPE_UNKNOWN = 0;
    public static final int FUEL_TYPE_UNLEADED = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "FUEL_TYPE_UNKNOWN";
        }
        if (o == 1) {
            return "FUEL_TYPE_UNLEADED";
        }
        if (o == 2) {
            return "FUEL_TYPE_LEADED";
        }
        if (o == 3) {
            return "FUEL_TYPE_DIESEL_1";
        }
        if (o == 4) {
            return "FUEL_TYPE_DIESEL_2";
        }
        if (o == 5) {
            return "FUEL_TYPE_BIODIESEL";
        }
        if (o == 6) {
            return "FUEL_TYPE_E85";
        }
        if (o == 7) {
            return "FUEL_TYPE_LPG";
        }
        if (o == 8) {
            return "FUEL_TYPE_CNG";
        }
        if (o == 9) {
            return "FUEL_TYPE_LNG";
        }
        if (o == 10) {
            return "FUEL_TYPE_ELECTRIC";
        }
        if (o == 11) {
            return "FUEL_TYPE_HYDROGEN";
        }
        if (o == 12) {
            return "FUEL_TYPE_OTHER";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("FUEL_TYPE_UNKNOWN");
        if ((o & 1) == 1) {
            list.add("FUEL_TYPE_UNLEADED");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("FUEL_TYPE_LEADED");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("FUEL_TYPE_DIESEL_1");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("FUEL_TYPE_DIESEL_2");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("FUEL_TYPE_BIODIESEL");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("FUEL_TYPE_E85");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("FUEL_TYPE_LPG");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("FUEL_TYPE_CNG");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("FUEL_TYPE_LNG");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("FUEL_TYPE_ELECTRIC");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("FUEL_TYPE_HYDROGEN");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("FUEL_TYPE_OTHER");
            flipped |= 12;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
