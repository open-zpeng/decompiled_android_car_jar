package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class VehicleUnit {
    public static final int CELSIUS = 48;
    public static final int FAHRENHEIT = 49;
    public static final int HERTZ = 3;
    public static final int KELVIN = 50;
    public static final int KILOMETER = 35;
    public static final int KILOPASCAL = 112;
    public static final int METER = 33;
    public static final int METER_PER_SEC = 1;
    public static final int MILLIAMPERE = 97;
    public static final int MILLILITER = 64;
    public static final int MILLIMETER = 32;
    public static final int MILLIVOLT = 98;
    public static final int MILLIWATTS = 99;
    public static final int NANO_SECS = 80;
    public static final int PERCENTILE = 16;
    public static final int RPM = 2;
    public static final int SECS = 83;
    public static final int SHOULD_NOT_USE = 0;
    public static final int WATT_HOUR = 96;
    public static final int YEAR = 89;

    public static final String toString(int o) {
        if (o == 0) {
            return "SHOULD_NOT_USE";
        }
        if (o == 1) {
            return "METER_PER_SEC";
        }
        if (o == 2) {
            return "RPM";
        }
        if (o == 3) {
            return "HERTZ";
        }
        if (o == 16) {
            return "PERCENTILE";
        }
        if (o == 32) {
            return "MILLIMETER";
        }
        if (o == 33) {
            return "METER";
        }
        if (o == 35) {
            return "KILOMETER";
        }
        if (o == 48) {
            return "CELSIUS";
        }
        if (o == 49) {
            return "FAHRENHEIT";
        }
        if (o == 50) {
            return "KELVIN";
        }
        if (o == 64) {
            return "MILLILITER";
        }
        if (o == 80) {
            return "NANO_SECS";
        }
        if (o == 83) {
            return "SECS";
        }
        if (o == 89) {
            return "YEAR";
        }
        if (o == 112) {
            return "KILOPASCAL";
        }
        if (o == 96) {
            return "WATT_HOUR";
        }
        if (o == 97) {
            return "MILLIAMPERE";
        }
        if (o == 98) {
            return "MILLIVOLT";
        }
        if (o == 99) {
            return "MILLIWATTS";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("SHOULD_NOT_USE");
        if ((o & 1) == 1) {
            list.add("METER_PER_SEC");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("RPM");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("HERTZ");
            flipped |= 3;
        }
        if ((o & 16) == 16) {
            list.add("PERCENTILE");
            flipped |= 16;
        }
        if ((o & 32) == 32) {
            list.add("MILLIMETER");
            flipped |= 32;
        }
        if ((o & 33) == 33) {
            list.add("METER");
            flipped |= 33;
        }
        if ((o & 35) == 35) {
            list.add("KILOMETER");
            flipped |= 35;
        }
        if ((o & 48) == 48) {
            list.add("CELSIUS");
            flipped |= 48;
        }
        if ((o & 49) == 49) {
            list.add("FAHRENHEIT");
            flipped |= 49;
        }
        if ((o & 50) == 50) {
            list.add("KELVIN");
            flipped |= 50;
        }
        if ((o & 64) == 64) {
            list.add("MILLILITER");
            flipped |= 64;
        }
        if ((o & 80) == 80) {
            list.add("NANO_SECS");
            flipped |= 80;
        }
        if ((o & 83) == 83) {
            list.add("SECS");
            flipped |= 83;
        }
        if ((o & 89) == 89) {
            list.add("YEAR");
            flipped |= 89;
        }
        if ((o & 112) == 112) {
            list.add("KILOPASCAL");
            flipped |= 112;
        }
        if ((o & 96) == 96) {
            list.add("WATT_HOUR");
            flipped |= 96;
        }
        if ((o & 97) == 97) {
            list.add("MILLIAMPERE");
            flipped |= 97;
        }
        if ((o & 98) == 98) {
            list.add("MILLIVOLT");
            flipped |= 98;
        }
        if ((o & 99) == 99) {
            list.add("MILLIWATTS");
            flipped |= 99;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
