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
import android.car.hardware.property.ICarProperty;
import android.car.hardware.property.ICarPropertyEventListener;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.telephony.Rlog;
import android.util.ArraySet;
import android.util.Log;
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
public class CarPropertyManager implements CarManagerBase {
    private static final long MAX_RECEIVE_PROPERTY_FROM_CARSERVICE_INTERVAL_MS;
    private static final long MAX_RECEIVE_PROPERTY_FROM_HANDLER_INTERVAL_MS;
    private static final int MSG_GENERIC_EVENT = 0;
    private final SparseArray<CarPropertyListeners> mActivePropertyListener = new SparseArray<>();
    private CarPropertyEventListenerToService mCarPropertyEventToService;
    private final boolean mDbg;
    private final SingleMessageHandler<CarPropertyEvent> mHandler;
    private final ICarProperty mService;
    private final String mTag;
    private static final Object mLock = new Object();
    @GuardedBy("mLock")
    private static volatile List<CarPropertyConfig> sPropertyList = null;
    @GuardedBy("mLock")
    private static final Map<Integer, CarPropertyConfig> sConfigMap = new ConcurrentHashMap();
    private static volatile boolean sConfigFetch = false;

    /* loaded from: classes.dex */
    public interface CarPropertyEventListener {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    static {
        MAX_RECEIVE_PROPERTY_FROM_CARSERVICE_INTERVAL_MS = Duration.ofMillis(Build.IS_USER ? 2000L : 1000L).toMillis();
        MAX_RECEIVE_PROPERTY_FROM_HANDLER_INTERVAL_MS = Duration.ofMillis(Build.IS_USER ? 2500L : 1250L).toMillis();
    }

    @SystemApi
    public CarPropertyManager(IBinder service, Handler handler, boolean dbg, String tag) {
        this.mDbg = dbg;
        this.mTag = tag;
        this.mService = ICarProperty.Stub.asInterface(service);
        if (!sConfigFetch) {
            synchronized (mLock) {
                try {
                    fetchPropertyListFromCarServiceLocked();
                } catch (Exception e) {
                    String str = this.mTag;
                    Rlog.e(str, "getPropertyList exception: " + e.getMessage());
                }
            }
        }
        this.mHandler = new SingleMessageHandler<CarPropertyEvent>(handler.getLooper(), 0) { // from class: android.car.hardware.property.CarPropertyManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.android.car.internal.SingleMessageHandler
            public void handleEvent(CarPropertyEvent event) {
                CarPropertyListeners listeners;
                synchronized (CarPropertyManager.this.mActivePropertyListener) {
                    listeners = (CarPropertyListeners) CarPropertyManager.this.mActivePropertyListener.get(event.getCarPropertyValue().getPropertyId());
                }
                if (listeners != null) {
                    switch (event.getEventType()) {
                        case 0:
                            listeners.onPropertyChanged(event);
                            return;
                        case 1:
                            listeners.onErrorEvent(event);
                            return;
                        default:
                            throw new IllegalArgumentException();
                    }
                }
            }
        };
    }

    @SystemApi
    public boolean registerListener(CarPropertyEventListener listener, int propertyId, float rate) throws CarNotConnectedException {
        if (XpDebugLog.CAR_DEBUG) {
            String str = this.mTag;
            Log.i(str, "register listener: " + listener + " for " + XpDebugLog.getPropertyDescription(propertyId) + " rate:" + rate);
        }
        synchronized (this.mActivePropertyListener) {
            if (this.mCarPropertyEventToService == null) {
                this.mCarPropertyEventToService = new CarPropertyEventListenerToService(this);
            }
            boolean needsServerUpdate = false;
            CarPropertyListeners listeners = this.mActivePropertyListener.get(propertyId);
            if (listeners == null) {
                listeners = new CarPropertyListeners(rate);
                this.mActivePropertyListener.put(propertyId, listeners);
                needsServerUpdate = true;
            }
            if (listeners.addAndUpdateRate(listener, rate)) {
                needsServerUpdate = true;
            }
            if (needsServerUpdate && !registerOrUpdatePropertyListener(propertyId, rate)) {
                return false;
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
            Rlog.w(str, "call registerListener failed with RemoteException for " + XpDebugLog.getPropertyDescription(propertyId), e);
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException e2) {
            String str2 = this.mTag;
            Rlog.w(str2, "call registerListener failed with IllegalStateException for " + XpDebugLog.getPropertyDescription(propertyId), e2);
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CarPropertyEventListenerToService extends ICarPropertyEventListener.Stub {
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
                    String str = CarPropertyManager.this.mTag;
                    Log.w(str, "receive: " + XpDebugLog.getPropertyDescription(carPropertyValue.getPropertyId()) + ", timestamp:" + updateTime + " from carservice cost too much time:" + timeCost + " ms");
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
        this.mHandler.sendEvents(events);
    }

    @SystemApi
    public void unregisterListener(CarPropertyEventListener listener) {
        if (XpDebugLog.CAR_DEBUG) {
            String str = this.mTag;
            Log.i(str, "unregister listener:" + listener);
        }
        synchronized (this.mActivePropertyListener) {
            for (int i = 0; i < this.mActivePropertyListener.size(); i++) {
                doUnregisterListenerLocked(listener, this.mActivePropertyListener.keyAt(i));
            }
        }
    }

    @SystemApi
    public void unregisterListener(CarPropertyEventListener listener, int propertyId) {
        synchronized (this.mActivePropertyListener) {
            doUnregisterListenerLocked(listener, propertyId);
        }
    }

    private void doUnregisterListenerLocked(CarPropertyEventListener listener, int propertyId) {
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
                    Rlog.i(str, "getPropertyList size " + size);
                    for (CarPropertyConfig carPropertyConfig : sPropertyList) {
                        sConfigMap.put(Integer.valueOf(carPropertyConfig.getPropertyId()), carPropertyConfig);
                    }
                    sConfigFetch = true;
                }
            } catch (RemoteException e) {
                Rlog.e(this.mTag, "getPropertyList exception ", e);
                throw new CarNotConnectedException(e);
            }
        }
    }

    @SystemApi
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
    public int[] getIntVectorProperty(int prop, int area) throws CarNotConnectedException {
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
    public <E> CarPropertyValue<E> getProperty(Class<E> clazz, int propId, int area) throws CarNotConnectedException {
        Class<?> actualClass;
        if (this.mDbg) {
            String str = this.mTag;
            Log.i(str, "getProperty " + XpDebugLog.getPropertyDescription(propId) + ", area: 0x" + Integer.toHexString(area) + ", class: " + clazz);
        }
        try {
            CarPropertyValue<E> propVal = this.mService.getProperty(propId, area);
            if (propVal != null && propVal.getValue() != null && (actualClass = propVal.getValue().getClass()) != clazz) {
                throw new IllegalArgumentException("Invalid property type. Expected: " + clazz + ", but was: " + actualClass);
            }
            return propVal;
        } catch (RemoteException e) {
            String str2 = this.mTag;
            Log.e(str2, "getProperty " + XpDebugLog.getPropertyDescription(propId) + ", area: 0x" + Integer.toHexString(area) + " failed with " + e.toString());
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public <E> CarPropertyValue<E> getProperty(int propId, int area) throws CarNotConnectedException {
        try {
            CarPropertyValue<E> propVal = this.mService.getProperty(propId, area);
            return propVal;
        } catch (RemoteException e) {
            String str = this.mTag;
            Log.e(str, "getProperty " + XpDebugLog.getPropertyDescription(propId) + ", area: 0x" + Integer.toHexString(area) + " failed with " + e.toString());
            throw new CarNotConnectedException(e);
        }
    }

    @SystemApi
    public <E> void setProperty(Class<E> clazz, int propId, int area, E val) throws CarNotConnectedException {
        if (this.mDbg) {
            String str = this.mTag;
            Log.i(str, "setProperty " + XpDebugLog.getPropertyDescription(propId) + ", area: 0x" + Integer.toHexString(area) + ", class: " + clazz + ", val: " + val);
        }
        try {
            this.mService.setProperty(new CarPropertyValue(propId, area, val));
        } catch (RemoteException e) {
            String str2 = this.mTag;
            Log.e(str2, "setProperty " + XpDebugLog.getPropertyDescription(propId) + ", area: 0x" + Integer.toHexString(area) + ", class: " + clazz + ", val: " + val + " failed with " + e.toString());
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
    public void setBooleanProperty(int prop, int area, boolean val) throws CarNotConnectedException {
        setProperty(Boolean.class, prop, area, Boolean.valueOf(val));
    }

    @SystemApi
    public void setFloatProperty(int prop, int area, float val) throws CarNotConnectedException {
        setProperty(Float.class, prop, area, Float.valueOf(val));
    }

    @SystemApi
    public void setIntProperty(int prop, int area, int val) throws CarNotConnectedException {
        setProperty(Integer.class, prop, area, Integer.valueOf(val));
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
    public class CarPropertyListeners extends CarRatedFloatListeners<CarPropertyEventListener> {
        CarPropertyListeners(float rate) {
            super(rate);
        }

        void onPropertyChanged(final CarPropertyEvent event) {
            List<CarPropertyEventListener> listeners;
            long updateTime = event.getCarPropertyValue().getTimestamp();
            if (updateTime < this.mLastUpdateTime) {
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
            this.mLastUpdateTime = updateTime;
            synchronized (CarPropertyManager.this.mActivePropertyListener) {
                listeners = new ArrayList<>(getListeners());
            }
            listeners.forEach(new Consumer<CarPropertyEventListener>() { // from class: android.car.hardware.property.CarPropertyManager.CarPropertyListeners.1
                @Override // java.util.function.Consumer
                public void accept(CarPropertyEventListener listener) {
                    listener.onChangeEvent(event.getCarPropertyValue());
                }
            });
        }

        void onErrorEvent(CarPropertyEvent event) {
            List<CarPropertyEventListener> listeners;
            final CarPropertyValue value = event.getCarPropertyValue();
            synchronized (CarPropertyManager.this.mActivePropertyListener) {
                listeners = new ArrayList<>(getListeners());
            }
            listeners.forEach(new Consumer<CarPropertyEventListener>() { // from class: android.car.hardware.property.CarPropertyManager.CarPropertyListeners.2
                @Override // java.util.function.Consumer
                public void accept(CarPropertyEventListener listener) {
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
