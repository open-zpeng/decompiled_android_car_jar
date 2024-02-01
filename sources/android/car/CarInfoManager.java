package android.car;

import android.annotation.SystemApi;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.car.hardware.property.ICarProperty;
import android.os.IBinder;

@SystemApi
/* loaded from: classes.dex */
public final class CarInfoManager extends CarManagerBase {
    public static final int BASIC_INFO_DRIVER_SEAT = 356516106;
    public static final int BASIC_INFO_EV_BATTERY_CAPACITY = 291504390;
    public static final int BASIC_INFO_EV_CONNECTOR_TYPES = 289472775;
    public static final int BASIC_INFO_EV_PORT_LOCATION = 289407241;
    public static final int BASIC_INFO_FUEL_CAPACITY = 291504388;
    public static final int BASIC_INFO_FUEL_DOOR_LOCATION = 289407240;
    public static final int BASIC_INFO_FUEL_TYPES = 289472773;
    public static final int BASIC_INFO_KEY_MANUFACTURER = 286261505;
    public static final int BASIC_INFO_KEY_MODEL = 286261506;
    public static final int BASIC_INFO_KEY_MODEL_YEAR = 289407235;
    public static final String BASIC_INFO_KEY_VEHICLE_ID = "android.car.vehicle-id";
    public static final String INFO_KEY_PRODUCT_CONFIGURATION = "android.car.product-config";
    private final CarPropertyManager mCarPropertyMgr;

    public String getManufacturer() {
        CarPropertyValue<String> carProp = this.mCarPropertyMgr.getProperty(String.class, 286261505, 0);
        return carProp != null ? carProp.getValue() : "";
    }

    public String getModel() {
        CarPropertyValue<String> carProp = this.mCarPropertyMgr.getProperty(String.class, 286261506, 0);
        return carProp != null ? carProp.getValue() : "";
    }

    @Deprecated
    public String getModelYear() {
        int year = this.mCarPropertyMgr.getIntProperty(289407235, 0);
        return year == 0 ? "" : Integer.toString(year);
    }

    public int getModelYearInInteger() {
        return this.mCarPropertyMgr.getIntProperty(289407235, 0);
    }

    @Deprecated
    public String getVehicleId() {
        return "";
    }

    public float getFuelCapacity() {
        return this.mCarPropertyMgr.getFloatProperty(291504388, 0);
    }

    public int[] getFuelTypes() {
        return this.mCarPropertyMgr.getIntArrayProperty(289472773, 0);
    }

    public float getEvBatteryCapacity() {
        CarPropertyValue<Float> carProp = this.mCarPropertyMgr.getProperty(Float.class, 291504390, 0);
        return carProp != null ? carProp.getValue().floatValue() : CarPropertyManager.SENSOR_RATE_ONCHANGE;
    }

    public int[] getEvConnectorTypes() {
        int[] valueInHal = this.mCarPropertyMgr.getIntArrayProperty(289472775, 0);
        int[] connectorTypes = new int[valueInHal.length];
        for (int i = 0; i < valueInHal.length; i++) {
            int i2 = valueInHal[i];
            if (i2 != 101) {
                switch (i2) {
                    case 1:
                        connectorTypes[i] = 1;
                        continue;
                    case 2:
                        connectorTypes[i] = 2;
                        continue;
                    case 3:
                        connectorTypes[i] = 11;
                        continue;
                    case 4:
                        connectorTypes[i] = 3;
                        continue;
                    case 5:
                        connectorTypes[i] = 4;
                        continue;
                    case 6:
                        connectorTypes[i] = 5;
                        continue;
                    case 7:
                        connectorTypes[i] = 6;
                        continue;
                    case 8:
                        connectorTypes[i] = 7;
                        continue;
                    case 9:
                        connectorTypes[i] = 8;
                        continue;
                    case 10:
                        connectorTypes[i] = 9;
                        continue;
                    case 11:
                        connectorTypes[i] = 10;
                        continue;
                    default:
                        connectorTypes[i] = 0;
                        continue;
                }
            } else {
                connectorTypes[i] = 101;
            }
        }
        return connectorTypes;
    }

    public int getDriverSeat() {
        return this.mCarPropertyMgr.getIntProperty(356516106, 0);
    }

    public int getEvPortLocation() {
        return this.mCarPropertyMgr.getIntProperty(289407241, 0);
    }

    public int getFuelDoorLocation() {
        return this.mCarPropertyMgr.getIntProperty(289407240, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CarInfoManager(Car car, IBinder service) {
        super(car);
        ICarProperty mCarPropertyService = ICarProperty.Stub.asInterface(service);
        this.mCarPropertyMgr = new CarPropertyManager(car, mCarPropertyService);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mCarPropertyMgr.onCarDisconnected();
    }

    public static String getServiceName() {
        return Car.INFO_SERVICE;
    }
}
