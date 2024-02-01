package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class VehicleUnit {
    public static final int AMPERE_HOURS = 100;
    public static final int BAR = 114;
    public static final int CELSIUS = 48;
    public static final int DEGREES = 128;
    public static final int FAHRENHEIT = 49;
    public static final int GALLON = 66;
    public static final int HERTZ = 3;
    public static final int IMPERIAL_GALLON = 67;
    public static final int KELVIN = 50;
    public static final int KILOMETER = 35;
    public static final int KILOMETERS_PER_HOUR = 145;
    public static final int KILOPASCAL = 112;
    public static final int KILOWATT_HOUR = 101;
    public static final int LITER = 65;
    public static final int METER = 33;
    public static final int METER_PER_SEC = 1;
    public static final int MILE = 36;
    public static final int MILES_PER_HOUR = 144;
    public static final int MILLIAMPERE = 97;
    public static final int MILLILITER = 64;
    public static final int MILLIMETER = 32;
    public static final int MILLIVOLT = 98;
    public static final int MILLIWATTS = 99;
    public static final int NANO_SECS = 80;
    public static final int PERCENTILE = 16;
    public static final int PSI = 113;
    public static final int RPM = 2;
    public static final int SECS = 83;
    public static final int SHOULD_NOT_USE = 0;
    public static final int US_GALLON = 66;
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
        if (o == 36) {
            return "MILE";
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
        if (o == 65) {
            return "LITER";
        }
        if (o == 66) {
            return "GALLON";
        }
        if (o == 66) {
            return "US_GALLON";
        }
        if (o == 67) {
            return "IMPERIAL_GALLON";
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
        if (o == 100) {
            return "AMPERE_HOURS";
        }
        if (o == 101) {
            return "KILOWATT_HOUR";
        }
        if (o == 112) {
            return "KILOPASCAL";
        }
        if (o == 113) {
            return "PSI";
        }
        if (o == 114) {
            return "BAR";
        }
        if (o == 128) {
            return "DEGREES";
        }
        if (o == 144) {
            return "MILES_PER_HOUR";
        }
        if (o == 145) {
            return "KILOMETERS_PER_HOUR";
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
        if ((o & 36) == 36) {
            list.add("MILE");
            flipped |= 36;
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
        if ((o & 65) == 65) {
            list.add("LITER");
            flipped |= 65;
        }
        if ((o & 66) == 66) {
            list.add("GALLON");
            flipped |= 66;
        }
        if ((o & 66) == 66) {
            list.add("US_GALLON");
            flipped |= 66;
        }
        if ((o & 67) == 67) {
            list.add("IMPERIAL_GALLON");
            flipped |= 67;
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
        if ((o & 100) == 100) {
            list.add("AMPERE_HOURS");
            flipped |= 100;
        }
        if ((o & 101) == 101) {
            list.add("KILOWATT_HOUR");
            flipped |= 101;
        }
        if ((o & 112) == 112) {
            list.add("KILOPASCAL");
            flipped |= 112;
        }
        if ((o & 113) == 113) {
            list.add("PSI");
            flipped |= 113;
        }
        if ((o & 114) == 114) {
            list.add("BAR");
            flipped |= 114;
        }
        if ((o & 128) == 128) {
            list.add("DEGREES");
            flipped |= 128;
        }
        if ((o & 144) == 144) {
            list.add("MILES_PER_HOUR");
            flipped |= 144;
        }
        if ((o & 145) == 145) {
            list.add("KILOMETERS_PER_HOUR");
            flipped |= 145;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
