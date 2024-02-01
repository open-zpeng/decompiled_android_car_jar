package android.car.settings;

import android.annotation.SystemApi;
@SystemApi
/* loaded from: classes.dex */
public class CarSettings {
    public static final int DEFAULT_GARAGE_MODE_MAINTENANCE_WINDOW = 2000;
    public static final int[] DEFAULT_GARAGE_MODE_WAKE_UP_TIME = {0, 0};
    public static final int DEFAULT_USER_ID_TO_BOOT_INTO = 10;
    public static final int LAST_ACTIVE_USER_ID = 10;

    /* loaded from: classes.dex */
    public static final class Global {
        public static final String KEY_GARAGE_MODE_ENABLED = "android.car.GARAGE_MODE_ENABLED";
        public static final String KEY_GARAGE_MODE_MAINTENANCE_WINDOW = "android.car.GARAGE_MODE_MAINTENANCE_WINDOW";
        public static final String KEY_GARAGE_MODE_WAKE_UP_TIME = "android.car.GARAGE_MODE_WAKE_UP_TIME";
        public static final String VALUE_USER_SWITCH_CAR_DRIVING_SCENE_NRA_LEVEL = "android.car.VALUE_USER_SWITCH_CAR_DRIVING_SCENE_NRA_LEVEL";
    }

    /* loaded from: classes.dex */
    public static final class Secure {
        public static final String KEY_BLUETOOTH_AUTOCONNECT_MESSAGING_DEVICES = "android.car.BLUETOOTH_AUTOCONNECT_MESSAGING_DEVICES";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_MESSAGING_DEVICE_PRIORITY_0 = "android.car.BLUETOOTH_AUTOCONNECT_MESSAGING_DEVICE_PRIORITY_0";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_MESSAGING_DEVICE_PRIORITY_1 = "android.car.BLUETOOTH_AUTOCONNECT_MESSAGING_DEVICE_PRIORITY_1";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_MUSIC_DEVICES = "android.car.BLUETOOTH_AUTOCONNECT_MUSIC_DEVICES";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_MUSIC_DEVICE_PRIORITY_0 = "android.car.BLUETOOTH_AUTOCONNECT_MUSIC_DEVICE_PRIORITY_0";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_MUSIC_DEVICE_PRIORITY_1 = "android.car.BLUETOOTH_AUTOCONNECT_MUSIC_DEVICE_PRIORITY_1";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_NETWORK_DEVICES = "android.car.BLUETOOTH_AUTOCONNECT_NETWORK_DEVICES";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_NETWORK_DEVICE_PRIORITY_0 = "android.car.BLUETOOTH_AUTOCONNECT_NETWORK_DEVICE_PRIORITY_0";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_NETWORK_DEVICE_PRIORITY_1 = "android.car.BLUETOOTH_AUTOCONNECT_NETWORK_DEVICE_PRIORITY_1";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_PHONE_DEVICES = "android.car.BLUETOOTH_AUTOCONNECT_PHONE_DEVICES";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_PHONE_DEVICE_PRIORITY_0 = "android.car.BLUETOOTH_AUTOCONNECT_PHONE_DEVICE_PRIORITY_0";
        public static final String KEY_BLUETOOTH_AUTOCONNECT_PHONE_DEVICE_PRIORITY_1 = "android.car.BLUETOOTH_AUTOCONNECT_PHONE_DEVICE_PRIORITY_1";
    }
}
