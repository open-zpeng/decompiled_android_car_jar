package android.car.hardware;

import android.car.Car;
import android.car.CarManagerBase;
import android.car.hardware.property.CarPropertyManager;
import android.car.hardware.property.ICarProperty;
import android.os.Bundle;
import android.os.IBinder;
import android.util.ArraySet;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Deprecated
/* loaded from: classes.dex */
public final class CarSensorManager extends CarManagerBase {
    private static final int INDEX_WHEEL_DISTANCE_ENABLE_FLAG = 0;
    private static final int INDEX_WHEEL_DISTANCE_FRONT_LEFT = 1;
    private static final int INDEX_WHEEL_DISTANCE_FRONT_RIGHT = 2;
    private static final int INDEX_WHEEL_DISTANCE_REAR_LEFT = 4;
    private static final int INDEX_WHEEL_DISTANCE_REAR_RIGHT = 3;
    public static final int SENSOR_RATE_FAST = 10;
    public static final int SENSOR_RATE_FASTEST = 100;
    public static final int SENSOR_RATE_NORMAL = 1;
    public static final int SENSOR_RATE_ONCHANGE = 0;
    public static final int SENSOR_RATE_UI = 5;
    public static final int SENSOR_TYPE_ABS_ACTIVE = 287310858;
    public static final int SENSOR_TYPE_CAR_SPEED = 291504647;
    public static final int SENSOR_TYPE_ENGINE_OIL_LEVEL = 289407747;
    public static final int SENSOR_TYPE_ENV_OUTSIDE_TEMPERATURE = 291505923;
    public static final int SENSOR_TYPE_EV_BATTERY_CHARGE_RATE = 291504908;
    public static final int SENSOR_TYPE_EV_BATTERY_LEVEL = 291504905;
    public static final int SENSOR_TYPE_EV_CHARGE_PORT_CONNECTED = 287310603;
    public static final int SENSOR_TYPE_EV_CHARGE_PORT_OPEN = 287310602;
    public static final int SENSOR_TYPE_FUEL_DOOR_OPEN = 287310600;
    public static final int SENSOR_TYPE_FUEL_LEVEL = 291504903;
    public static final int SENSOR_TYPE_GEAR = 289408000;
    public static final int SENSOR_TYPE_IGNITION_STATE = 289408009;
    public static final int SENSOR_TYPE_NIGHT = 287310855;
    public static final int SENSOR_TYPE_ODOMETER = 291504644;
    public static final int SENSOR_TYPE_PARKING_BRAKE = 287310850;
    public static final int SENSOR_TYPE_RESERVED1 = 1;
    public static final int SENSOR_TYPE_RESERVED10 = 10;
    public static final int SENSOR_TYPE_RESERVED11 = 11;
    public static final int SENSOR_TYPE_RESERVED12 = 12;
    public static final int SENSOR_TYPE_RESERVED13 = 13;
    public static final int SENSOR_TYPE_RESERVED14 = 14;
    public static final int SENSOR_TYPE_RESERVED15 = 15;
    public static final int SENSOR_TYPE_RESERVED16 = 16;
    public static final int SENSOR_TYPE_RESERVED17 = 17;
    public static final int SENSOR_TYPE_RESERVED18 = 18;
    public static final int SENSOR_TYPE_RESERVED19 = 19;
    public static final int SENSOR_TYPE_RESERVED20 = 20;
    public static final int SENSOR_TYPE_RESERVED21 = 21;
    public static final int SENSOR_TYPE_RESERVED26 = 26;
    public static final int SENSOR_TYPE_RESERVED8 = 8;
    public static final int SENSOR_TYPE_RPM = 291504901;
    public static final int SENSOR_TYPE_TRACTION_CONTROL_ACTIVE = 287310859;
    public static final int SENSOR_TYPE_WHEEL_TICK_DISTANCE = 290521862;
    private static final String TAG = "CarSensorManager";
    private static final int WHEEL_TICK_DISTANCE_BUNDLE_SIZE = 6;
    private CarPropertyEventListenerToBase mCarPropertyEventListener;
    private final CarPropertyManager mCarPropertyMgr;
    private final HashMap<OnSensorChangedListener, CarPropertyEventListenerToBase> mListenerMap;
    private final ArraySet<Integer> mSensorConfigIds;

    /* loaded from: classes.dex */
    public interface OnSensorChangedListener {
        void onSensorChanged(CarSensorEvent carSensorEvent);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SensorRate {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SensorType {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CarPropertyEventListenerToBase implements CarPropertyManager.CarPropertyEventCallback {
        private final OnSensorChangedListener mListener;
        private final WeakReference<CarSensorManager> mManager;

        CarPropertyEventListenerToBase(CarSensorManager manager, OnSensorChangedListener listener) {
            this.mManager = new WeakReference<>(manager);
            this.mListener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            CarSensorManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnChangeEvent(value, this.mListener);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnChangeEvent(CarPropertyValue value, OnSensorChangedListener listener) {
        synchronized (this.mListenerMap) {
            CarSensorEvent event = createCarSensorEvent(value);
            listener.onSensorChanged(event);
        }
    }

    private void handleOnErrorEvent(int propertyId, int zone) {
    }

    public CarSensorManager(Car car, IBinder service) {
        super(car);
        this.mSensorConfigIds = new ArraySet<>(Arrays.asList(291504647, 291504901, 291504644, 291504903, 287310850, 289408000, 287310855, 291505923, 289408009, 290521862, 287310858, 287310859, 287310600, 291504905, 287310602, 287310603, 291504908, 289407747));
        this.mCarPropertyEventListener = null;
        this.mListenerMap = new HashMap<>();
        ICarProperty mCarPropertyService = ICarProperty.Stub.asInterface(service);
        this.mCarPropertyMgr = new CarPropertyManager(car, mCarPropertyService);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        synchronized (this.mListenerMap) {
            this.mListenerMap.clear();
        }
        this.mCarPropertyMgr.onCarDisconnected();
    }

    public static String getServiceName() {
        return Car.SENSOR_SERVICE;
    }

    public int[] getSupportedSensors() {
        List<CarPropertyConfig> carPropertyConfigList = getPropertyList();
        int[] supportedSensors = new int[carPropertyConfigList.size()];
        for (int i = 0; i < supportedSensors.length; i++) {
            supportedSensors[i] = carPropertyConfigList.get(i).getPropertyId();
        }
        return supportedSensors;
    }

    public List<CarPropertyConfig> getPropertyList() {
        return this.mCarPropertyMgr.getPropertyList(this.mSensorConfigIds);
    }

    public boolean isSensorSupported(int sensorType) {
        int[] sensors = getSupportedSensors();
        for (int sensorSupported : sensors) {
            if (sensorType == sensorSupported) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSensorSupported(int[] sensorList, int sensorType) {
        for (int sensorSupported : sensorList) {
            if (sensorType == sensorSupported) {
                return true;
            }
        }
        return false;
    }

    public boolean registerListener(OnSensorChangedListener listener, int sensorType, int rate) {
        if (rate != 100 && rate != 1 && rate != 5 && rate != 10 && rate != 0) {
            throw new IllegalArgumentException("wrong rate " + rate);
        }
        if (this.mListenerMap.get(listener) == null) {
            this.mCarPropertyEventListener = new CarPropertyEventListenerToBase(this, listener);
        } else {
            this.mCarPropertyEventListener = this.mListenerMap.get(listener);
        }
        if (this.mCarPropertyMgr.registerCallback(this.mCarPropertyEventListener, sensorType, rate)) {
            this.mListenerMap.put(listener, this.mCarPropertyEventListener);
            return true;
        }
        return false;
    }

    public void unregisterListener(OnSensorChangedListener listener) {
        synchronized (this.mListenerMap) {
            this.mCarPropertyEventListener = this.mListenerMap.get(listener);
            this.mCarPropertyMgr.unregisterCallback(this.mCarPropertyEventListener);
            this.mListenerMap.remove(listener);
        }
    }

    public void unregisterListener(OnSensorChangedListener listener, int sensorType) {
        synchronized (this.mListenerMap) {
            this.mCarPropertyEventListener = this.mListenerMap.get(listener);
        }
        this.mCarPropertyMgr.unregisterCallback(this.mCarPropertyEventListener, sensorType);
    }

    public CarSensorEvent getLatestSensorEvent(int type) {
        CarPropertyValue propertyValue = this.mCarPropertyMgr.getProperty(type, 0);
        return createCarSensorEvent(propertyValue);
    }

    private CarSensorEvent createCarSensorEvent(CarPropertyValue propertyValue) {
        int propertyId = propertyValue.getPropertyId() & 16711680;
        if (propertyId == 2097152) {
            CarSensorEvent event = new CarSensorEvent(propertyValue.getPropertyId(), propertyValue.getTimestamp(), 0, 1, 0);
            event.intValues[0] = ((Boolean) propertyValue.getValue()).booleanValue() ? 1 : 0;
            return event;
        } else if (propertyId == 4194304) {
            CarSensorEvent event2 = new CarSensorEvent(propertyValue.getPropertyId(), propertyValue.getTimestamp(), 0, 1, 0);
            event2.intValues[0] = ((Integer) propertyValue.getValue()).intValue();
            return event2;
        } else if (propertyId == 5308416) {
            Object[] value = (Object[]) propertyValue.getValue();
            CarSensorEvent event3 = new CarSensorEvent(propertyValue.getPropertyId(), propertyValue.getTimestamp(), 0, 0, value.length);
            for (int i = 0; i < value.length; i++) {
                event3.longValues[i] = ((Long) value[i]).longValue();
            }
            return event3;
        } else if (propertyId == 6291456) {
            CarSensorEvent event4 = new CarSensorEvent(propertyValue.getPropertyId(), propertyValue.getTimestamp(), 1, 0, 0);
            event4.floatValues[0] = ((Float) propertyValue.getValue()).floatValue();
            return event4;
        } else {
            Log.e(TAG, "unhandled VehiclePropertyType for propId=" + propertyValue.getPropertyId());
            return null;
        }
    }

    public CarSensorConfig getSensorConfig(int type) {
        Bundle b = null;
        if (type == 290521862) {
            List<CarPropertyConfig> propertyConfigs = this.mCarPropertyMgr.getPropertyList();
            Iterator<CarPropertyConfig> it = propertyConfigs.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                CarPropertyConfig p = it.next();
                if (p.getPropertyId() == type) {
                    b = createWheelDistanceTickBundle(p.getConfigArray());
                    break;
                }
            }
        } else {
            b = Bundle.EMPTY;
        }
        return new CarSensorConfig(type, b);
    }

    private Bundle createWheelDistanceTickBundle(List<Integer> configArray) {
        Bundle b = new Bundle(6);
        b.putInt(CarSensorConfig.WHEEL_TICK_DISTANCE_SUPPORTED_WHEELS, configArray.get(0).intValue());
        b.putInt(CarSensorConfig.WHEEL_TICK_DISTANCE_FRONT_LEFT_UM_PER_TICK, configArray.get(1).intValue());
        b.putInt(CarSensorConfig.WHEEL_TICK_DISTANCE_FRONT_RIGHT_UM_PER_TICK, configArray.get(2).intValue());
        b.putInt(CarSensorConfig.WHEEL_TICK_DISTANCE_REAR_RIGHT_UM_PER_TICK, configArray.get(3).intValue());
        b.putInt(CarSensorConfig.WHEEL_TICK_DISTANCE_REAR_LEFT_UM_PER_TICK, configArray.get(4).intValue());
        return b;
    }
}
