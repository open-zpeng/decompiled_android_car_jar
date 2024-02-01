package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class VmsPublisherInformationIntegerValuesIndex {
    public static final int MESSAGE_TYPE = 0;
    public static final int PUBLISHER_ID = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "MESSAGE_TYPE";
        }
        if (o == 1) {
            return "PUBLISHER_ID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("MESSAGE_TYPE");
        if ((o & 1) == 1) {
            list.add("PUBLISHER_ID");
            flipped = 0 | 1;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
