package android.car.hardware.property;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarApiUtil;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.ValueUnavailableException;
import android.car.XpDebugLog;
import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.ICarPropertyEventListener;
import android.os.Build;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.ArraySet;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import com.android.car.internal.CarRatedFloatListeners;
import com.android.car.internal.SingleMessageHandler;
import com.android.internal.annotations.GuardedBy;
import java.lang.ref.WeakReference;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class CarPropertyManager extends CarManagerBase {
    private static final boolean DBG = false;
    private static final long MAX_RECEIVE_PROPERTY_FROM_CARSERVICE_INTERVAL_MS;
    private static final long MAX_RECEIVE_PROPERTY_FROM_HANDLER_INTERVAL_MS;
    private static final int MSG_GENERIC_EVENT = 0;
    public static final float SENSOR_RATE_FAST = 10.0f;
    public static final float SENSOR_RATE_FASTEST = 100.0f;
    public static final float SENSOR_RATE_NORMAL = 1.0f;
    public static final float SENSOR_RATE_ONCHANGE = 0.0f;
    public static final float SENSOR_RATE_UI = 5.0f;
    private static final String TAG = "CarPropertyManager";
    private final SparseArray<CarPropertyListeners> mActivePropertyListener;
    private CarPropertyEventListenerToService mCarPropertyEventToService;
    private final boolean mDbg;
    private final SingleMessageHandler<CarPropertyEvent> mHandler;
    private final ICarProperty mService;
    private final String mTag;
    private static final Object mLock = new Object();
    @GuardedBy({"mLock"})
    private static volatile List<CarPropertyConfig> sPropertyList = null;
    @GuardedBy({"mLock"})
    private static final Map<Integer, CarPropertyConfig> sConfigMap = new ConcurrentHashMap();
    private static volatile boolean sConfigFetch = false;

    /* loaded from: classes.dex */
    public interface CarPropertyEventCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    static {
        MAX_RECEIVE_PROPERTY_FROM_CARSERVICE_INTERVAL_MS = Duration.ofMillis(Build.IS_USER ? 2000L : 1000L).toMillis();
        MAX_RECEIVE_PROPERTY_FROM_HANDLER_INTERVAL_MS = Duration.ofMillis(Build.IS_USER ? 2500L : 1250L).toMillis();
    }

    public CarPropertyManager(Car car, ICarProperty service) {
        this(car, service, false, TAG);
    }

    public CarPropertyManager(Car car, ICarProperty service, boolean dbg, String tag) {
        super(car);
        this.mActivePropertyListener = new SparseArray<>();
        this.mService = service;
        this.mDbg = dbg;
        this.mTag = tag;
        if (!sConfigFetch) {
            synchronized (mLock) {
                try {
                    fetchPropertyListFromCarServiceLocked();
                } catch (Exception e) {
                    String str = this.mTag;
                    Slog.e(str, "getPropertyList exception: " + e.getMessage());
                }
            }
        }
        Handler eventHandler = getEventHandler();
        if (eventHandler == null) {
            this.mHandler = null;
        } else {
            this.mHandler = new SingleMessageHandler<CarPropertyEvent>(eventHandler.getLooper(), 0) { // from class: android.car.hardware.property.CarPropertyManager.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.android.car.internal.SingleMessageHandler
                public void handleEvent(CarPropertyEvent event) {
                    CarPropertyListeners listeners;
                    synchronized (CarPropertyManager.this.mActivePropertyListener) {
                        listeners = (CarPropertyListeners) CarPropertyManager.this.mActivePropertyListener.get(event.getCarPropertyValue().getPropertyId());
                    }
                    if (listeners != null) {
                        int eventType = event.getEventType();
                        if (eventType == 0) {
                            listeners.onPropertyChanged(event);
                        } else if (eventType == 1) {
                            listeners.onErrorEvent(event);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                }
            };
        }
    }

    public boolean registerCallback(CarPropertyEventCallback callback, int propertyId, float rate) {
        if (XpDebugLog.CAR_DEBUG) {
            String str = this.mTag;
            Log.i(str, "register listener: " + callback + " for " + XpDebugLog.getPropertyDescription(propertyId) + " rate:" + rate);
        }
        synchronized (this.mActivePropertyListener) {
            if (this.mCarPropertyEventToService == null) {
                this.mCarPropertyEventToService = new CarPropertyEventListenerToService(this);
            }
            CarPropertyConfig config = sConfigMap.get(Integer.valueOf(propertyId));
            if (config == null) {
                String str2 = this.mTag;
                Log.e(str2, "registerListener:  propId is not in config list:  " + propertyId + "(0x" + Integer.toHexString(propertyId) + ")");
                return false;
            }
            if (config.getChangeMode() == 1 && rate > SENSOR_RATE_ONCHANGE) {
                rate = SENSOR_RATE_ONCHANGE;
            }
            boolean needsServerUpdate = false;
            CarPropertyListeners listeners = this.mActivePropertyListener.get(propertyId);
            if (listeners == null) {
                listeners = new CarPropertyListeners(rate);
                this.mActivePropertyListener.put(propertyId, listeners);
                needsServerUpdate = true;
            }
            if (listeners.addAndUpdateRate(callback, rate)) {
                needsServerUpdate = true;
            }
            if (needsServerUpdate) {
                try {
                    if (!registerOrUpdatePropertyListener(propertyId, rate)) {
                        return false;
                    }
                } catch (CarNotConnectedException e) {
                }
            }
            return true;
        }
    }

    private boolean registerOrUpdatePropertyListener(int propertyId, float rate) throws CarNotConnectedException {
        try {
            this.mService.registerListener(propertyId, rate, this.mCarPropertyEventToService);
            return true;
        } catch (RemoteException e) {
            String str = this.mTag;
            Slog.w(str, "call registerListener failed with RemoteException for " + XpDebugLog.getPropertyDescription(propertyId), e);
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        } catch (IllegalStateException e2) {
            String str2 = this.mTag;
            Slog.w(str2, "call registerListener failed with IllegalStateException for " + XpDebugLog.getPropertyDescription(propertyId), e2);
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CarPropertyEventListenerToService extends ICarPropertyEventListener.Stub {
        private final WeakReference<CarPropertyManager> mMgr;

        CarPropertyEventListenerToService(CarPropertyManager mgr) {
            this.mMgr = new WeakReference<>(mgr);
        }

        @Override // android.car.hardware.property.ICarPropertyEventListener
        public void onEvent(List<CarPropertyEvent> events) throws RemoteException {
            if (events == null || events.isEmpty()) {
                return;
            }
            CarPropertyValue<?> carPropertyValue = events.get(0).getCarPropertyValue();
            long updateTime = carPropertyValue.getTimestamp();
            if (updateTime > 0) {
                long begin = SystemClock.uptimeMillis();
                long timeCost = begin - (updateTime / 1000000);
                if (timeCost > CarPropertyManager.MAX_RECEIVE_PROPERTY_FROM_CARSERVICE_INTERVAL_MS) {
                    Log.w(CarPropertyManager.TAG, "receive: " + XpDebugLog.getPropertyDescription(carPropertyValue.getPropertyId()) + ", timestamp:" + updateTime + " from carservice cost too much time:" + timeCost + " ms");
                }
            }
            CarPropertyManager manager = this.mMgr.get();
            if (manager != null) {
                manager.handleEvent(events);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEvent(List<CarPropertyEvent> events) {
        SingleMessageHandler<CarPropertyEvent> singleMessageHandler = this.mHandler;
        if (singleMessageHandler != null) {
            singleMessageHandler.sendEvents(events);
        }
    }

    public void unregisterCallback(CarPropertyEventCallback callback) {
        if (XpDebugLog.CAR_DEBUG) {
            Log.i(this.mTag, "unregister listener:" + callback);
        }
        synchronized (this.mActivePropertyListener) {
            int[] propertyIds = new int[this.mActivePropertyListener.size()];
            for (int i = 0; i < this.mActivePropertyListener.size(); i++) {
                propertyIds[i] = this.mActivePropertyListener.keyAt(i);
            }
            for (int prop : propertyIds) {
                doUnregisterListenerLocked(callback, prop);
            }
        }
    }

    public void unregisterCallback(CarPropertyEventCallback callback, int propertyId) {
        synchronized (this.mActivePropertyListener) {
            doUnregisterListenerLocked(callback, propertyId);
        }
    }

    private void doUnregisterListenerLocked(CarPropertyEventCallback listener, int propertyId) {
        CarPropertyListeners listeners = this.mActivePropertyListener.get(propertyId);
        if (listeners != null) {
            boolean needsServerUpdate = false;
            if (listeners.contains(listener)) {
                needsServerUpdate = listeners.remove(listener);
            }
            if (listeners.isEmpty()) {
                try {
                    this.mService.unregisterListener(propertyId, this.mCarPropertyEventToService);
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                }
                this.mActivePropertyListener.remove(propertyId);
            } else if (needsServerUpdate) {
                try {
                    registerOrUpdatePropertyListener(propertyId, listeners.getRate());
                } catch (CarNotConnectedException e2) {
                }
            }
        }
    }

    @SystemApi
    public List<CarPropertyConfig> getPropertyList() throws CarNotConnectedException {
        if (!sConfigFetch) {
            synchronized (mLock) {
                fetchPropertyListFromCarServiceLocked();
            }
        }
        if (sPropertyList == null) {
            return null;
        }
        return Collections.unmodifiableList(sPropertyList);
    }

    private void fetchPropertyListFromCarServiceLocked() throws CarNotConnectedException {
        int size;
        if (!sConfigFetch) {
            try {
                sPropertyList = this.mService.getPropertyList();
                if (sPropertyList != null && (size = sPropertyList.size()) > 0) {
                    String str = this.mTag;
                    Slog.i(str, "getPropertyList size " + size);
                    for (CarPropertyConfig carPropertyConfig : sPropertyList) {
                        sConfigMap.put(Integer.valueOf(carPropertyConfig.getPropertyId()), carPropertyConfig);
                    }
                    sConfigFetch = true;
                }
            } catch (RemoteException e) {
                Slog.e(this.mTag, "getPropertyList exception ", e);
                throw new CarNotConnectedException(e);
            }
        }
    }

    public List<CarPropertyConfig> getPropertyList(ArraySet<Integer> propertyIds) throws CarNotConnectedException {
        List<CarPropertyConfig> configs = new ArrayList<>();
        if (!sConfigFetch) {
            synchronized (mLock) {
                fetchPropertyListFromCarServiceLocked();
            }
        }
        if (sConfigFetch) {
            Iterator<Integer> it = propertyIds.iterator();
            while (it.hasNext()) {
                int propId = it.next().intValue();
                CarPropertyConfig config = sConfigMap.get(Integer.valueOf(propId));
                if (config != null) {
                    configs.add(config);
                }
            }
        }
        return configs;
    }

    @SystemApi
    public boolean isPropertySupported(int propId) throws CarNotConnectedException {
        if (!sConfigFetch) {
            synchronized (mLock) {
                fetchPropertyListFromCarServiceLocked();
            }
        }
        return sConfigMap.containsKey(Integer.valueOf(propId));
    }

    public String getReadPermission(int propId) {
        if (this.mDbg) {
            String str = this.mTag;
            Log.i(str, "getReadPermission, propId: 0x" + Integer.toHexString(propId));
        }
        try {
            return this.mService.getReadPermission(propId);
        } catch (RemoteException e) {
            return (String) handleRemoteExceptionFromCarService(e, "");
        }
    }

    public String getWritePermission(int propId) {
        if (this.mDbg) {
            String str = this.mTag;
            Log.i(str, "getWritePermission, propId: 0x" + Integer.toHexString(propId));
        }
        try {
            return this.mService.getWritePermission(propId);
        } catch (RemoteException e) {
            return (String) handleRemoteExceptionFromCarService(e, "");
        }
    }

    @SystemApi
    public boolean isPropertyAvailable(int propId, int area) throws CarNotConnectedException {
        try {
            CarPropertyValue propValue = this.mService.getProperty(propId, area);
            if (propValue != null) {
                if (propValue.getStatus() == 0) {
                    return true;
                }
            }
            return false;
        } catch (RemoteException e) {
            String str = this.mTag;
            Log.e(str, "isPropertyAvailable failed with " + e.toString() + ", propId: 0x" + Integer.toHexString(propId) + ", area: 0x" + Integer.toHexString(area), e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public boolean getBooleanProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<Boolean> carProp = getProperty(Boolean.class, prop, area);
        if (carProp == null) {
            throw new ValueUnavailableException();
        }
        return carProp.getValue().booleanValue();
    }

    @SystemApi
    public float getFloatProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<Float> carProp = getProperty(Float.class, prop, area);
        if (carProp == null) {
            throw new ValueUnavailableException();
        }
        return carProp.getValue().floatValue();
    }

    @SystemApi
    public int getIntProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<Integer> carProp = getProperty(Integer.class, prop, area);
        if (carProp == null) {
            throw new ValueUnavailableException();
        }
        return carProp.getValue().intValue();
    }

    @SystemApi
    public long getLongProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<Long> carProp = getProperty(Long.class, prop, area);
        if (carProp == null) {
            throw new ValueUnavailableException();
        }
        return carProp.getValue().longValue();
    }

    @SystemApi
    public String getStringProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<String> carProp = getProperty(String.class, prop, area);
        if (carProp == null) {
            throw new ValueUnavailableException();
        }
        return carProp.getValue();
    }

    @SystemApi
    public int[] getIntArrayProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<Integer[]> carProp = getProperty(Integer[].class, prop, area);
        if (carProp == null) {
            throw new ValueUnavailableException();
        }
        return toIntArray(carProp.getValue());
    }

    private static int[] toIntArray(Integer[] input) {
        int len = input.length;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = input[i].intValue();
        }
        return arr;
    }

    @SystemApi
    public int[] getIntVectorProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<Integer[]> carProp = getProperty(Integer[].class, prop, area);
        if (carProp == null) {
            throw new ValueUnavailableException();
        }
        return toIntArray(carProp.getValue());
    }

    @SystemApi
    public byte[] getByteVectorProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<byte[]> carProp = getProperty(byte[].class, prop, area);
        if (carProp == null) {
            throw new ValueUnavailableException();
        }
        return carProp.getValue();
    }

    @SystemApi
    public float[] getFloatVectorProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<Float[]> carProp = getProperty(Float[].class, prop, area);
        if (carProp == null) {
            throw new ValueUnavailableException();
        }
        return toFloatArray(carProp.getValue());
    }

    private static float[] toFloatArray(Float[] input) {
        int len = input.length;
        float[] arr = new float[len];
        for (int i = 0; i < len; i++) {
            arr[i] = input[i].floatValue();
        }
        return arr;
    }

    @SystemApi
    public <E> CarPropertyValue<E> getProperty(Class<E> clazz, int propId, int areaId) throws CarNotConnectedException {
        Class<?> actualClass;
        if (this.mDbg) {
            String str = this.mTag;
            Log.i(str, "getProperty " + XpDebugLog.getPropertyDescription(propId) + ", area: 0x" + Integer.toHexString(areaId) + ", class: " + clazz);
        }
        try {
            CarPropertyValue<E> propVal = this.mService.getProperty(propId, areaId);
            if (propVal != null && propVal.getValue() != null && (actualClass = propVal.getValue().getClass()) != clazz) {
                throw new IllegalArgumentException("Invalid property type. Expected: " + clazz + ", but was: " + actualClass);
            }
            return propVal;
        } catch (RemoteException e) {
            String str2 = this.mTag;
            Log.e(str2, "getProperty " + XpDebugLog.getPropertyDescription(propId) + ", area: 0x" + Integer.toHexString(areaId) + " failed with " + e.toString());
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public <E> CarPropertyValue<E> getProperty(int propId, int areaId) throws CarNotConnectedException {
        try {
            CarPropertyValue<E> propVal = this.mService.getProperty(propId, areaId);
            return propVal;
        } catch (RemoteException e) {
            String str = this.mTag;
            Log.e(str, "getProperty " + XpDebugLog.getPropertyDescription(propId) + ", area: 0x" + Integer.toHexString(areaId) + " failed with " + e.toString());
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public <E> void setProperty(Class<E> clazz, int propId, int areaId, E val) throws CarNotConnectedException {
        if (this.mDbg) {
            String str = this.mTag;
            Log.i(str, "setProperty " + XpDebugLog.getPropertyDescription(propId) + ", area: 0x" + Integer.toHexString(areaId) + ", class: " + clazz + ", val: " + val);
        }
        try {
            this.mService.setProperty(new CarPropertyValue(propId, areaId, val));
        } catch (RemoteException e) {
            String str2 = this.mTag;
            Log.e(str2, "setProperty " + XpDebugLog.getPropertyDescription(propId) + ", area: 0x" + Integer.toHexString(areaId) + ", class: " + clazz + ", val: " + val + " failed with " + e.toString());
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public void setMultiProperties(List<CarPropertyValue> propertyValues) throws CarNotConnectedException {
        try {
            this.mService.setMultiProperties(propertyValues);
        } catch (RemoteException e) {
            String str = this.mTag;
            Log.e(str, "setProperty failed with " + e.toString(), e);
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public void setBooleanProperty(int prop, int areaId, boolean val) throws CarNotConnectedException {
        setProperty(Boolean.class, prop, areaId, Boolean.valueOf(val));
    }

    @SystemApi
    public void setFloatProperty(int prop, int areaId, float val) throws CarNotConnectedException {
        setProperty(Float.class, prop, areaId, Float.valueOf(val));
    }

    @SystemApi
    public void setIntProperty(int prop, int areaId, int val) throws CarNotConnectedException {
        setProperty(Integer.class, prop, areaId, Integer.valueOf(val));
    }

    @SystemApi
    public void setLongProperty(int prop, int area, long val) throws CarNotConnectedException {
        setProperty(Long.class, prop, area, Long.valueOf(val));
    }

    @SystemApi
    public void setStringProperty(int prop, int area, String val) throws CarNotConnectedException {
        setProperty(String.class, prop, area, val);
    }

    @SystemApi
    public void setByteVectorProperty(int prop, int area, byte[] values) throws CarNotConnectedException {
        setProperty(byte[].class, prop, area, values);
    }

    @SystemApi
    public void setIntVectorProperty(int prop, int area, int[] values) throws CarNotConnectedException {
        setProperty(int[].class, prop, area, values);
    }

    @SystemApi
    public void setFloatVectorProperty(int prop, int area, float[] values) throws CarNotConnectedException {
        setProperty(float[].class, prop, area, values);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CarPropertyListeners extends CarRatedFloatListeners<CarPropertyEventCallback> {
        CarPropertyListeners(float rate) {
            super(rate);
        }

        void onPropertyChanged(final CarPropertyEvent event) {
            List<CarPropertyEventCallback> listeners;
            final long updateTime = event.getCarPropertyValue().getTimestamp();
            int areaId = event.getCarPropertyValue().getAreaId();
            if (!needUpdateForAreaId(areaId, updateTime)) {
                String str = CarPropertyManager.this.mTag;
                Log.w(str, "dropping old property data: " + event.getCarPropertyValue());
                return;
            }
            if (!Build.IS_USER) {
                long timeCost = SystemClock.uptimeMillis() - (updateTime / 1000000);
                if (timeCost > CarPropertyManager.MAX_RECEIVE_PROPERTY_FROM_HANDLER_INTERVAL_MS) {
                    String str2 = CarPropertyManager.this.mTag;
                    Log.w(str2, "receive: " + XpDebugLog.getPropertyDescription(event.getCarPropertyValue().getPropertyId()) + ", timestamp:" + updateTime + " callback cost too much time:" + timeCost + " ms");
                }
            }
            synchronized (CarPropertyManager.this.mActivePropertyListener) {
                listeners = new ArrayList<>(getListeners());
            }
            listeners.forEach(new Consumer<CarPropertyEventCallback>() { // from class: android.car.hardware.property.CarPropertyManager.CarPropertyListeners.1
                @Override // java.util.function.Consumer
                public void accept(CarPropertyEventCallback listener) {
                    if (!CarPropertyListeners.this.needUpdateForSelectedListener(listener, updateTime)) {
                        String str3 = CarPropertyManager.this.mTag;
                        Log.w(str3, "ignore property data: " + event.getCarPropertyValue());
                        return;
                    }
                    listener.onChangeEvent(event.getCarPropertyValue());
                }
            });
        }

        void onErrorEvent(CarPropertyEvent event) {
            List<CarPropertyEventCallback> listeners;
            final CarPropertyValue value = event.getCarPropertyValue();
            synchronized (CarPropertyManager.this.mActivePropertyListener) {
                listeners = new ArrayList<>(getListeners());
            }
            listeners.forEach(new Consumer<CarPropertyEventCallback>() { // from class: android.car.hardware.property.CarPropertyManager.CarPropertyListeners.2
                @Override // java.util.function.Consumer
                public void accept(CarPropertyEventCallback listener) {
                    Integer errorCode = (Integer) value.getValue();
                    if (CarPropertyManager.this.mDbg) {
                        String str = CarPropertyManager.this.mTag;
                        Log.i(str, "onErrorEvent for " + XpDebugLog.getPropertyDescription(value.getPropertyId()) + " errorCode: " + errorCode);
                    }
                    listener.onErrorEvent(value.getPropertyId(), errorCode.intValue());
                }
            });
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        synchronized (this.mActivePropertyListener) {
            this.mActivePropertyListener.clear();
            this.mCarPropertyEventToService = null;
        }
        synchronized (mLock) {
            sConfigFetch = false;
            sPropertyList = null;
            sConfigMap.clear();
        }
    }

    public static String getServiceName() {
        return Car.PROPERTY_SERVICE;
    }
}
