package android.car;
/* loaded from: classes.dex */
public class ValueUnavailableException extends IllegalArgumentException {
    public static final int HAL_DIED = 2;
    public static final int NOT_SUPPORTED = 3;
    public static final int NO_DATA = 0;
    public static final int SIGNAL_LOST = 1;

    public ValueUnavailableException() {
        this(0);
    }

    public ValueUnavailableException(int reason) {
        super("Failed to get the value " + getDescription(reason));
    }

    private static String getDescription(int reason) {
        switch (reason) {
            case 0:
                return "due to the ECU not sending related data";
            case 1:
                return "due to the signal lost";
            case 2:
                return "due to the the vehicle hal is dead";
            case 3:
                return "due to the property is unsupported";
            default:
                return "Unknown";
        }
    }
}
