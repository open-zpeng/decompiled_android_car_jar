package android.car.hardware;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.property.CarPropertyManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import com.android.internal.annotations.GuardedBy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@SystemApi
/* loaded from: classes.dex */
public final class CarVendorExtensionManager implements CarManagerBase {
    private static final boolean DBG = false;
    private static final String TAG = CarVendorExtensionManager.class.getSimpleName();
    private final CarPropertyManager mPropertyManager;
    @GuardedBy("mLock")
    private final ArraySet<CarVendorExtensionCallback> mCallbacks = new ArraySet<>();
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private CarPropertyEventListenerToBase mListenerToBase = null;

    /* loaded from: classes.dex */
    public interface CarVendorExtensionCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnChangeEvent(CarPropertyValue value) {
        Collection<CarVendorExtensionCallback> callbacks;
        synchronized (this.mLock) {
            callbacks = new ArrayList<>(this.mCallbacks);
        }
        for (CarVendorExtensionCallback l : callbacks) {
            l.onChangeEvent(value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnErrorEvent(int propertyId, int zone) {
        Collection<CarVendorExtensionCallback> listeners;
        synchronized (this.mLock) {
            listeners = new ArrayList<>(this.mCallbacks);
        }
        for (CarVendorExtensionCallback l : listeners) {
            l.onErrorEvent(propertyId, zone);
        }
    }

    public CarVendorExtensionManager(IBinder service, Handler handler) {
        this.mPropertyManager = new CarPropertyManager(service, handler, false, TAG);
    }

    public void registerCallback(CarVendorExtensionCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mCallbacks.isEmpty()) {
                this.mListenerToBase = new CarPropertyEventListenerToBase(this);
            }
            List<CarPropertyConfig> configs = this.mPropertyManager.getPropertyList();
            for (CarPropertyConfig c : configs) {
                this.mPropertyManager.registerListener(this.mListenerToBase, c.getPropertyId(), 0.0f);
            }
            this.mCallbacks.add(callback);
        }
    }

    public void unregisterCallback(CarVendorExtensionCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            this.mCallbacks.remove(callback);
            List<CarPropertyConfig> configs = this.mPropertyManager.getPropertyList();
            for (CarPropertyConfig c : configs) {
                this.mPropertyManager.unregisterListener(this.mListenerToBase, c.getPropertyId());
            }
            if (this.mCallbacks.isEmpty()) {
                this.mListenerToBase = null;
            }
        }
    }

    public List<CarPropertyConfig> getProperties() throws CarNotConnectedException {
        return this.mPropertyManager.getPropertyList();
    }

    public boolean isPropertyAvailable(int propertyId, int area) throws CarNotConnectedException {
        return this.mPropertyManager.isPropertyAvailable(propertyId, area);
    }

    public <E> E getGlobalProperty(Class<E> propertyClass, int propId) throws CarNotConnectedException {
        return (E) getProperty(propertyClass, propId, 0);
    }

    public <E> E getProperty(Class<E> propertyClass, int propId, int area) throws CarNotConnectedException {
        return this.mPropertyManager.getProperty(propertyClass, propId, area).getValue();
    }

    public <E> void setGlobalProperty(Class<E> propertyClass, int propId, E value) throws CarNotConnectedException {
        this.mPropertyManager.setProperty(propertyClass, propId, 0, value);
    }

    public <E> void setProperty(Class<E> propertyClass, int propId, int area, E value) throws CarNotConnectedException {
        this.mPropertyManager.setProperty(propertyClass, propId, area, value);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mPropertyManager.onCarDisconnected();
    }

    public static String getServiceName() {
        return Car.VENDOR_EXTENSION_SERVICE;
    }

    /* loaded from: classes.dex */
    private static class CarPropertyEventListenerToBase implements CarPropertyManager.CarPropertyEventListener {
        private final WeakReference<CarVendorExtensionManager> mManager;

        CarPropertyEventListenerToBase(CarVendorExtensionManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventListener
        public void onChangeEvent(CarPropertyValue value) {
            CarVendorExtensionManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnChangeEvent(value);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventListener
        public void onErrorEvent(int propertyId, int zone) {
            CarVendorExtensionManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnErrorEvent(propertyId, zone);
            }
        }
    }
}
