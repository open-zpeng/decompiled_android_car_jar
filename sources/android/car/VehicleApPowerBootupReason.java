package android.car;

import android.annotation.SystemApi;
@SystemApi
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
    public static final int TIMER = 2;
    public static final int USER_POWER_ON = 0;
    public static final int USER_UNLOCK = 1;
    public static final int WIFI_KEY_WK = 12;

    private VehicleApPowerBootupReason() {
    }
}
