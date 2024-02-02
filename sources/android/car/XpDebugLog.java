package android.car;

import android.hardware.automotive.vehicle.V2_0.VehicleProperty;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Pair;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
/* loaded from: classes.dex */
public class XpDebugLog {
    private static final String SYS_CAR_CALLBACK_DEBUG_PROPERTY = "sys.car.callback_debug";
    private static final String SYS_CAR_PERF_LOG_DEBUG_PROPERTY = "sys.car.perf_debug";
    private static final String SYS_CAR_PROP_CALLBACK_DEBUG_PROPERTY = "sys.car.callback_debug_prop";
    private static final String SYS_CAR_SHARED_MEMORY_PERF_LOG_DEBUG_PROPERTY = "persist.sys.car.shm_perf_debug";
    private static boolean isPerfDebugLogEnable = false;
    private static boolean isCallBackDebugLogEnable = false;
    private static boolean isSharedMemoryPerfDebugLogEnable = false;
    private static final Map<Integer, Pair<String, String>> sPropDescriptions = new ConcurrentHashMap(1024);
    private static final String SYS_CAR_NORMAL_DEBUG_PROPERTY = "sys.car.log_debug";
    public static final boolean CAR_DEBUG = SystemProperties.getBoolean(SYS_CAR_NORMAL_DEBUG_PROPERTY, false);

    public static boolean isCallBackDebugLogEnable(int prop) {
        if (!isCallBackDebugLogEnable) {
            boolean z = SystemProperties.getBoolean(SYS_CAR_CALLBACK_DEBUG_PROPERTY, false);
            isCallBackDebugLogEnable = z;
            if (!z) {
                return false;
            }
        }
        if (prop <= 0) {
            return true;
        }
        String debugProp = SystemProperties.get(SYS_CAR_PROP_CALLBACK_DEBUG_PROPERTY, CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE);
        String propName = getPropertyName(prop);
        return TextUtils.isEmpty(propName) || TextUtils.isEmpty(debugProp) || debugProp.equals(propName);
    }

    public static boolean isCallBackDebugLogEnable(String propName) {
        if (!isCallBackDebugLogEnable) {
            boolean z = SystemProperties.getBoolean(SYS_CAR_CALLBACK_DEBUG_PROPERTY, false);
            isCallBackDebugLogEnable = z;
            if (!z) {
                return false;
            }
        }
        String debugProp = SystemProperties.get(SYS_CAR_PROP_CALLBACK_DEBUG_PROPERTY, CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE);
        return TextUtils.isEmpty(propName) || TextUtils.isEmpty(debugProp) || debugProp.equals(propName);
    }

    public static boolean isPerfLogEnable() {
        if (!isPerfDebugLogEnable) {
            boolean z = SystemProperties.getBoolean(SYS_CAR_PERF_LOG_DEBUG_PROPERTY, false);
            isPerfDebugLogEnable = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSharedMemoryPerfLogEnable() {
        if (!isSharedMemoryPerfDebugLogEnable) {
            boolean z = SystemProperties.getBoolean(SYS_CAR_SHARED_MEMORY_PERF_LOG_DEBUG_PROPERTY, false);
            isSharedMemoryPerfDebugLogEnable = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public static String getPropertyDescription(int propId) {
        return (String) getStringNameAndDescriptionPair(propId).second;
    }

    private static Pair<String, String> getStringNameAndDescriptionPair(int propId) {
        return sPropDescriptions.computeIfAbsent(Integer.valueOf(propId), new Function() { // from class: android.car.-$$Lambda$XpDebugLog$vTbUwCCzUYjUq_EkmZ6-31Zx7AQ
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XpDebugLog.lambda$getStringNameAndDescriptionPair$0((Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Pair lambda$getStringNameAndDescriptionPair$0(Integer p) {
        String propertyName = VehicleProperty.toString(p.intValue());
        String propertyDescription = "Property=" + propertyName + "(" + p + ", 0x" + Integer.toHexString(p.intValue()) + ")";
        return new Pair(propertyName, propertyDescription);
    }

    public static String getPropertyName(int propId) {
        return (String) getStringNameAndDescriptionPair(propId).first;
    }
}
