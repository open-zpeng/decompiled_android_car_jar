package android.car;

import android.annotation.SystemApi;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.ICarProperty;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
@SystemApi
/* loaded from: classes.dex */
public final class CarInfoManager implements CarManagerBase {
    public static final int BASIC_INFO_EV_BATTERY_CAPACITY = 291504390;
    public static final int BASIC_INFO_EV_CONNECTOR_TYPES = 289472775;
    public static final int BASIC_INFO_FUEL_CAPACITY = 291504388;
    public static final int BASIC_INFO_FUEL_TYPES = 289472773;
    public static final int BASIC_INFO_KEY_MANUFACTURER = 286261505;
    public static final int BASIC_INFO_KEY_MODEL = 286261506;
    public static final int BASIC_INFO_KEY_MODEL_YEAR = 289407235;
    public static final String BASIC_INFO_KEY_VEHICLE_ID = "android.car.vehicle-id";
    private static final boolean DBG = false;
    public static final String INFO_KEY_PRODUCT_CONFIGURATION = "android.car.product-config";
    private static final String TAG = "CarInfoManager";
    private final ICarProperty mService;

    public String getManufacturer() throws CarNotConnectedException {
        CarPropertyValue<String> carProp = getProperty(String.class, 286261505, 0);
        if (carProp != null) {
            return carProp.getValue();
        }
        return null;
    }

    public String getModel() throws CarNotConnectedException {
        CarPropertyValue<String> carProp = getProperty(String.class, 286261506, 0);
        if (carProp != null) {
            return carProp.getValue();
        }
        return null;
    }

    public String getModelYear() throws CarNotConnectedException {
        CarPropertyValue<String> carProp = getProperty(String.class, 289407235, 0);
        if (carProp != null) {
            return carProp.getValue();
        }
        return null;
    }

    public String getVehicleId() throws CarNotConnectedException {
        return CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
    }

    public float getFuelCapacity() throws CarNotConnectedException {
        CarPropertyValue<Float> carProp = getProperty(Float.class, 291504388, 0);
        if (carProp != null) {
            return carProp.getValue().floatValue();
        }
        return 0.0f;
    }

    public int[] getFuelTypes() throws CarNotConnectedException {
        CarPropertyValue<int[]> carProp = getProperty(int[].class, 289472773, 0);
        return carProp != null ? carProp.getValue() : new int[0];
    }

    public float getEvBatteryCapacity() throws CarNotConnectedException {
        CarPropertyValue<Float> carProp = getProperty(Float.class, 291504390, 0);
        if (carProp != null) {
            return carProp.getValue().floatValue();
        }
        return 0.0f;
    }

    public int[] getEvConnectorTypes() throws CarNotConnectedException {
        CarPropertyValue<int[]> carProp = getProperty(int[].class, 289472775, 0);
        return carProp != null ? carProp.getValue() : new int[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CarInfoManager(IBinder service) {
        this.mService = ICarProperty.Stub.asInterface(service);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.INFO_SERVICE;
    }

    private <E> CarPropertyValue<E> getProperty(Class<E> clazz, int propId, int area) throws CarNotConnectedException {
        Class<?> actualClass;
        try {
            CarPropertyValue<E> propVal = this.mService.getProperty(propId, area);
            if (propVal != null && propVal.getValue() != null && (actualClass = propVal.getValue().getClass()) != clazz) {
                throw new IllegalArgumentException("Invalid property type. Expected: " + clazz + ", but was: " + actualClass);
            }
            return propVal;
        } catch (RemoteException e) {
            Log.e(TAG, "getProperty failed with " + e.toString() + ", propId: 0x" + Integer.toHexString(propId) + ", area: 0x" + Integer.toHexString(area), e);
            throw new CarNotConnectedException(e);
        } catch (IllegalArgumentException e2) {
            return null;
        }
    }
}
