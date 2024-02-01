package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class VmsMessageType {
    public static final int AVAILABILITY_CHANGE = 9;
    public static final int AVAILABILITY_REQUEST = 6;
    public static final int AVAILABILITY_RESPONSE = 8;
    public static final int DATA = 12;
    public static final int LAST_VMS_MESSAGE_TYPE = 17;
    public static final int OFFERING = 5;
    public static final int PUBLISHER_ID_REQUEST = 13;
    public static final int PUBLISHER_ID_RESPONSE = 14;
    public static final int PUBLISHER_INFORMATION_REQUEST = 15;
    public static final int PUBLISHER_INFORMATION_RESPONSE = 16;
    public static final int START_SESSION = 17;
    public static final int SUBSCRIBE = 1;
    public static final int SUBSCRIBE_TO_PUBLISHER = 2;
    public static final int SUBSCRIPTIONS_CHANGE = 11;
    public static final int SUBSCRIPTIONS_REQUEST = 7;
    public static final int SUBSCRIPTIONS_RESPONSE = 10;
    public static final int UNSUBSCRIBE = 3;
    public static final int UNSUBSCRIBE_TO_PUBLISHER = 4;

    public static final String toString(int o) {
        if (o == 1) {
            return "SUBSCRIBE";
        }
        if (o == 2) {
            return "SUBSCRIBE_TO_PUBLISHER";
        }
        if (o == 3) {
            return "UNSUBSCRIBE";
        }
        if (o == 4) {
            return "UNSUBSCRIBE_TO_PUBLISHER";
        }
        if (o == 5) {
            return "OFFERING";
        }
        if (o == 6) {
            return "AVAILABILITY_REQUEST";
        }
        if (o == 7) {
            return "SUBSCRIPTIONS_REQUEST";
        }
        if (o == 8) {
            return "AVAILABILITY_RESPONSE";
        }
        if (o == 9) {
            return "AVAILABILITY_CHANGE";
        }
        if (o == 10) {
            return "SUBSCRIPTIONS_RESPONSE";
        }
        if (o == 11) {
            return "SUBSCRIPTIONS_CHANGE";
        }
        if (o == 12) {
            return "DATA";
        }
        if (o == 13) {
            return "PUBLISHER_ID_REQUEST";
        }
        if (o == 14) {
            return "PUBLISHER_ID_RESPONSE";
        }
        if (o == 15) {
            return "PUBLISHER_INFORMATION_REQUEST";
        }
        if (o == 16) {
            return "PUBLISHER_INFORMATION_RESPONSE";
        }
        if (o == 17) {
            return "START_SESSION";
        }
        if (o == 17) {
            return "LAST_VMS_MESSAGE_TYPE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("SUBSCRIBE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("SUBSCRIBE_TO_PUBLISHER");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("UNSUBSCRIBE");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("UNSUBSCRIBE_TO_PUBLISHER");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("OFFERING");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("AVAILABILITY_REQUEST");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("SUBSCRIPTIONS_REQUEST");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("AVAILABILITY_RESPONSE");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("AVAILABILITY_CHANGE");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("SUBSCRIPTIONS_RESPONSE");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("SUBSCRIPTIONS_CHANGE");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("DATA");
            flipped |= 12;
        }
        if ((o & 13) == 13) {
            list.add("PUBLISHER_ID_REQUEST");
            flipped |= 13;
        }
        if ((o & 14) == 14) {
            list.add("PUBLISHER_ID_RESPONSE");
            flipped |= 14;
        }
        if ((o & 15) == 15) {
            list.add("PUBLISHER_INFORMATION_REQUEST");
            flipped |= 15;
        }
        if ((o & 16) == 16) {
            list.add("PUBLISHER_INFORMATION_RESPONSE");
            flipped |= 16;
        }
        if ((o & 17) == 17) {
            list.add("START_SESSION");
            flipped |= 17;
        }
        if ((o & 17) == 17) {
            list.add("LAST_VMS_MESSAGE_TYPE");
            flipped |= 17;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
