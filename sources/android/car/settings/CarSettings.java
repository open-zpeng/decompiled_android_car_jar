package android.car.settings;

import android.annotation.SystemApi;

@SystemApi
/* loaded from: classes.dex */
public class CarSettings {
    public static final int DEFAULT_GARAGE_MODE_MAINTENANCE_WINDOW = 600000;
    public static final int[] DEFAULT_GARAGE_MODE_WAKE_UP_TIME = {0, 0};

    /* loaded from: classes.dex */
    public static final class Global {
        public static final String DEFAULT_USER_ID_TO_BOOT_INTO = "android.car.DEFAULT_BOOT_INTO_USER_ID";
        public static final String DEFAULT_USER_RESTRICTIONS_SET = "android.car.DEFAULT_USER_RESTRICTIONS_SET";
        public static final String ENABLE_USER_SWITCH_DEVELOPER_MESSAGE = "android.car.ENABLE_USER_SWITCH_DEVELOPER_MESSAGE";
        @Deprecated
        public static final String KEY_GARAGE_MODE_ENABLED = "android.car.GARAGE_MODE_ENABLED";
        @Deprecated
        public static final String KEY_GARAGE_MODE_MAINTENANCE_WINDOW = "android.car.GARAGE_MODE_MAINTENANCE_WINDOW";
        @Deprecated
        public static final String KEY_GARAGE_MODE_WAKE_UP_TIME = "android.car.GARAGE_MODE_WAKE_UP_TIME";
        public static final String LAST_ACTIVE_USER_ID = "android.car.LAST_ACTIVE_USER_ID";
        public static final String VALUE_USER_SWITCH_CAR_DRIVING_SCENE_NRA_LEVEL = "android.car.VALUE_USER_SWITCH_CAR_DRIVING_SCENE_NRA_LEVEL";
    }

    /* loaded from: classes.dex */
    public static final class Secure {
        public static final String KEY_BLUETOOTH_A2DP_SINK_DEVICES = "android.car.KEY_BLUETOOTH_A2DP_SINK_DEVICES";
        public static final String KEY_BLUETOOTH_HFP_CLIENT_DEVICES = "android.car.KEY_BLUETOOTH_HFP_CLIENT_DEVICES";
        public static final String KEY_BLUETOOTH_MAP_CLIENT_DEVICES = "android.car.KEY_BLUETOOTH_MAP_CLIENT_DEVICES";
        public static final String KEY_BLUETOOTH_PAN_DEVICES = "android.car.KEY_BLUETOOTH_PAN_DEVICES";
        public static final String KEY_BLUETOOTH_PBAP_CLIENT_DEVICES = "android.car.KEY_BLUETOOTH_PBAP_CLIENT_DEVICES";
        public static final String KEY_BLUETOOTH_PROFILES_INHIBITED = "android.car.BLUETOOTH_PROFILES_INHIBITED";
        public static final String KEY_ENABLE_INITIAL_NOTICE_SCREEN_TO_USER = "android.car.ENABLE_INITIAL_NOTICE_SCREEN_TO_USER";
        public static final String KEY_SETUP_WIZARD_IN_PROGRESS = "android.car.SETUP_WIZARD_IN_PROGRESS";
    }
}
