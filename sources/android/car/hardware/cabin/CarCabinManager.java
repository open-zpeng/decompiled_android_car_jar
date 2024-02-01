package android.car.hardware.cabin;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.car.hardware.property.ICarProperty;
import android.os.IBinder;
import android.util.ArraySet;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SystemApi
@Deprecated
/* loaded from: classes.dex */
public final class CarCabinManager extends CarManagerBase {
    private static final boolean DBG = false;
    public static final int ID_DOOR_LOCK = 371198722;
    public static final int ID_DOOR_MOVE = 373295873;
    public static final int ID_DOOR_POS = 373295872;
    public static final int ID_MIRROR_FOLD = 287312709;
    public static final int ID_MIRROR_LOCK = 287312708;
    public static final int ID_MIRROR_Y_MOVE = 339741507;
    public static final int ID_MIRROR_Y_POS = 339741506;
    public static final int ID_MIRROR_Z_MOVE = 339741505;
    public static final int ID_MIRROR_Z_POS = 339741504;
    public static final int ID_SEAT_BACKREST_ANGLE_1_MOVE = 356518792;
    public static final int ID_SEAT_BACKREST_ANGLE_1_POS = 356518791;
    public static final int ID_SEAT_BACKREST_ANGLE_2_MOVE = 356518794;
    public static final int ID_SEAT_BACKREST_ANGLE_2_POS = 356518793;
    public static final int ID_SEAT_BELT_BUCKLED = 354421634;
    public static final int ID_SEAT_BELT_HEIGHT_MOVE = 356518788;
    public static final int ID_SEAT_BELT_HEIGHT_POS = 356518787;
    public static final int ID_SEAT_DEPTH_MOVE = 356518798;
    public static final int ID_SEAT_DEPTH_POS = 356518797;
    public static final int ID_SEAT_FORE_AFT_MOVE = 356518790;
    public static final int ID_SEAT_FORE_AFT_POS = 356518789;
    public static final int ID_SEAT_HEADREST_ANGLE_MOVE = 356518808;
    public static final int ID_SEAT_HEADREST_ANGLE_POS = 356518807;
    public static final int ID_SEAT_HEADREST_FORE_AFT_MOVE = 356518810;
    public static final int ID_SEAT_HEADREST_FORE_AFT_POS = 356518809;
    public static final int ID_SEAT_HEADREST_HEIGHT_MOVE = 356518806;
    public static final int ID_SEAT_HEADREST_HEIGHT_POS = 356518805;
    public static final int ID_SEAT_HEIGHT_MOVE = 356518796;
    public static final int ID_SEAT_HEIGHT_POS = 356518795;
    public static final int ID_SEAT_LUMBAR_FORE_AFT_MOVE = 356518802;
    public static final int ID_SEAT_LUMBAR_FORE_AFT_POS = 356518801;
    public static final int ID_SEAT_LUMBAR_SIDE_SUPPORT_MOVE = 356518804;
    public static final int ID_SEAT_LUMBAR_SIDE_SUPPORT_POS = 356518803;
    public static final int ID_SEAT_MEMORY_SELECT = 356518784;
    public static final int ID_SEAT_MEMORY_SET = 356518785;
    public static final int ID_SEAT_TILT_MOVE = 356518800;
    public static final int ID_SEAT_TILT_POS = 356518799;
    public static final int ID_WINDOW_LOCK = 322964420;
    public static final int ID_WINDOW_MOVE = 322964417;
    public static final int ID_WINDOW_POS = 322964416;
    private static final String TAG = "CarCabinManager";
    private final ArraySet<Integer> mCabinPropertyIds;
    private final ArraySet<CarCabinEventCallback> mCallbacks;
    private final CarPropertyManager mCarPropertyMgr;
    private CarPropertyEventListenerToBase mListenerToBase;

    /* loaded from: classes.dex */
    public interface CarCabinEventCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    /* loaded from: classes.dex */
    private static class CarPropertyEventListenerToBase implements CarPropertyManager.CarPropertyEventCallback {
        private final WeakReference<CarCabinManager> mManager;

        public CarPropertyEventListenerToBase(CarCabinManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            CarCabinManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnChangeEvent(value);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            CarCabinManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnErrorEvent(propertyId, zone);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnChangeEvent(CarPropertyValue value) {
        Collection<CarCabinEventCallback> callbacks;
        synchronized (this) {
            callbacks = new ArraySet<>(this.mCallbacks);
        }
        for (CarCabinEventCallback l : callbacks) {
            l.onChangeEvent(value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnErrorEvent(int propertyId, int zone) {
        Collection<CarCabinEventCallback> listeners;
        synchronized (this) {
            listeners = new ArraySet<>(this.mCallbacks);
        }
        if (!listeners.isEmpty()) {
            for (CarCabinEventCallback l : listeners) {
                l.onErrorEvent(propertyId, zone);
            }
        }
    }

    public CarCabinManager(Car car, IBinder service) {
        super(car);
        this.mCallbacks = new ArraySet<>();
        this.mListenerToBase = null;
        this.mCabinPropertyIds = new ArraySet<>(Arrays.asList(373295872, 373295873, 371198722, 339741504, 339741505, 339741506, 339741507, 287312708, 287312709, 356518784, 356518785, 354421634, 356518787, 356518788, 356518789, 356518790, 356518791, 356518792, 356518793, 356518794, 356518795, 356518796, 356518797, 356518798, 356518799, 356518800, 356518801, 356518802, 356518803, 356518804, Integer.valueOf((int) ID_SEAT_HEADREST_HEIGHT_POS), 356518806, 356518807, 356518808, 356518809, 356518810, 322964416, 322964417, Integer.valueOf((int) ID_WINDOW_LOCK)));
        ICarProperty mCarPropertyService = ICarProperty.Stub.asInterface(service);
        this.mCarPropertyMgr = new CarPropertyManager(car, mCarPropertyService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public synchronized void registerCallback(CarCabinEventCallback callback) {
        if (this.mCallbacks.isEmpty()) {
            this.mListenerToBase = new CarPropertyEventListenerToBase(this);
        }
        List<CarPropertyConfig> configs = getPropertyList();
        for (CarPropertyConfig c : configs) {
            this.mCarPropertyMgr.registerCallback(this.mListenerToBase, c.getPropertyId(), CarPropertyManager.SENSOR_RATE_ONCHANGE);
        }
        this.mCallbacks.add(callback);
    }

    public synchronized void unregisterCallback(CarCabinEventCallback callback) {
        this.mCallbacks.remove(callback);
        List<CarPropertyConfig> configs = getPropertyList();
        for (CarPropertyConfig c : configs) {
            this.mCarPropertyMgr.unregisterCallback(this.mListenerToBase, c.getPropertyId());
        }
        if (this.mCallbacks.isEmpty()) {
            this.mListenerToBase = null;
        }
    }

    public List<CarPropertyConfig> getPropertyList() {
        return this.mCarPropertyMgr.getPropertyList(this.mCabinPropertyIds);
    }

    public boolean getBooleanProperty(int propertyId, int area) {
        return this.mCarPropertyMgr.getBooleanProperty(propertyId, area);
    }

    public float getFloatProperty(int propertyId, int area) {
        return this.mCarPropertyMgr.getFloatProperty(propertyId, area);
    }

    public int getIntProperty(int propertyId, int area) {
        return this.mCarPropertyMgr.getIntProperty(propertyId, area);
    }

    public void setBooleanProperty(int propertyId, int area, boolean val) {
        if (this.mCabinPropertyIds.contains(Integer.valueOf(propertyId))) {
            this.mCarPropertyMgr.setBooleanProperty(propertyId, area, val);
        }
    }

    public void setFloatProperty(int propertyId, int area, float val) {
        if (this.mCabinPropertyIds.contains(Integer.valueOf(propertyId))) {
            this.mCarPropertyMgr.setFloatProperty(propertyId, area, val);
        }
    }

    public void setIntProperty(int propertyId, int area, int val) {
        if (this.mCabinPropertyIds.contains(Integer.valueOf(propertyId))) {
            this.mCarPropertyMgr.setIntProperty(propertyId, area, val);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        synchronized (this) {
            this.mCallbacks.clear();
        }
        this.mCarPropertyMgr.onCarDisconnected();
    }

    public static String getServiceName() {
        return Car.CABIN_SERVICE;
    }
}
