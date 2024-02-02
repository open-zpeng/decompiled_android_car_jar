package android.car.hardware;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class CarSensorEvent implements Parcelable {
    public static final Parcelable.Creator<CarSensorEvent> CREATOR = new Parcelable.Creator<CarSensorEvent>() { // from class: android.car.hardware.CarSensorEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarSensorEvent createFromParcel(Parcel in) {
            return new CarSensorEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarSensorEvent[] newArray(int size) {
            return new CarSensorEvent[size];
        }
    };
    public static final int GEAR_DRIVE = 8;
    public static final int GEAR_EIGHTH = 2048;
    public static final int GEAR_FIFTH = 256;
    public static final int GEAR_FIRST = 16;
    public static final int GEAR_FOURTH = 128;
    public static final int GEAR_NEUTRAL = 1;
    public static final int GEAR_NINTH = 4096;
    public static final int GEAR_PARK = 4;
    public static final int GEAR_REVERSE = 2;
    public static final int GEAR_SECOND = 32;
    public static final int GEAR_SEVENTH = 1024;
    public static final int GEAR_SIXTH = 512;
    public static final int GEAR_TENTH = 8192;
    public static final int GEAR_THIRD = 64;
    public static final int IGNITION_STATE_ACC = 3;
    public static final int IGNITION_STATE_LOCK = 1;
    public static final int IGNITION_STATE_OFF = 2;
    public static final int IGNITION_STATE_ON = 4;
    public static final int IGNITION_STATE_START = 5;
    public static final int IGNITION_STATE_UNDEFINED = 0;
    public static final int INDEX_ENVIRONMENT_PRESSURE = 1;
    public static final int INDEX_ENVIRONMENT_TEMPERATURE = 0;
    public static final int INDEX_WHEEL_DISTANCE_FRONT_LEFT = 1;
    public static final int INDEX_WHEEL_DISTANCE_FRONT_RIGHT = 2;
    public static final int INDEX_WHEEL_DISTANCE_REAR_LEFT = 4;
    public static final int INDEX_WHEEL_DISTANCE_REAR_RIGHT = 3;
    public static final int INDEX_WHEEL_DISTANCE_RESET_COUNT = 0;
    private static final long MILLI_IN_NANOS = 1000000;
    public final float[] floatValues;
    public final int[] intValues;
    public final long[] longValues;
    public int sensorType;
    public long timestamp;

    public CarSensorEvent(Parcel in) {
        this.sensorType = in.readInt();
        this.timestamp = in.readLong();
        int len = in.readInt();
        this.floatValues = new float[len];
        in.readFloatArray(this.floatValues);
        int len2 = in.readInt();
        this.intValues = new int[len2];
        in.readIntArray(this.intValues);
        int len3 = in.readInt();
        this.longValues = new long[len3];
        in.readLongArray(this.longValues);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sensorType);
        dest.writeLong(this.timestamp);
        dest.writeInt(this.floatValues.length);
        dest.writeFloatArray(this.floatValues);
        dest.writeInt(this.intValues.length);
        dest.writeIntArray(this.intValues);
        dest.writeInt(this.longValues.length);
        dest.writeLongArray(this.longValues);
    }

    public CarSensorEvent(int sensorType, long timestamp, int floatValueSize, int intValueSize, int longValueSize) {
        this.sensorType = sensorType;
        this.timestamp = timestamp;
        this.floatValues = new float[floatValueSize];
        this.intValues = new int[intValueSize];
        this.longValues = new long[longValueSize];
    }

    CarSensorEvent(int sensorType, long timestamp, float[] floatValues, int[] intValues, long[] longValues) {
        this.sensorType = sensorType;
        this.timestamp = timestamp;
        this.floatValues = floatValues;
        this.intValues = intValues;
        this.longValues = longValues;
    }

    private void checkType(int type) {
        if (this.sensorType == type) {
            return;
        }
        throw new UnsupportedOperationException(String.format("Invalid sensor type: expected %d, got %d", Integer.valueOf(type), Integer.valueOf(this.sensorType)));
    }

    /* loaded from: classes.dex */
    public static class EnvironmentData {
        public float pressure;
        public float temperature;
        public long timestamp;

        private EnvironmentData() {
        }
    }

    public EnvironmentData getEnvironmentData(EnvironmentData data) {
        checkType(12);
        if (data == null) {
            data = new EnvironmentData();
        }
        data.timestamp = this.timestamp;
        data.temperature = this.floatValues[0];
        data.pressure = this.floatValues[1];
        return data;
    }

    /* loaded from: classes.dex */
    public static class NightData {
        public boolean isNightMode;
        public long timestamp;

        private NightData() {
        }
    }

    public NightData getNightData(NightData data) {
        checkType(287310855);
        if (data == null) {
            data = new NightData();
        }
        data.timestamp = this.timestamp;
        data.isNightMode = this.intValues[0] == 1;
        return data;
    }

    /* loaded from: classes.dex */
    public static class GearData {
        public int gear;
        public long timestamp;

        private GearData() {
        }
    }

    public GearData getGearData(GearData data) {
        checkType(289408000);
        if (data == null) {
            data = new GearData();
        }
        data.timestamp = this.timestamp;
        data.gear = this.intValues[0];
        return data;
    }

    /* loaded from: classes.dex */
    public static class ParkingBrakeData {
        public boolean isEngaged;
        public long timestamp;

        private ParkingBrakeData() {
        }
    }

    public ParkingBrakeData getParkingBrakeData(ParkingBrakeData data) {
        checkType(287310850);
        if (data == null) {
            data = new ParkingBrakeData();
        }
        data.timestamp = this.timestamp;
        data.isEngaged = this.intValues[0] == 1;
        return data;
    }

    /* loaded from: classes.dex */
    public static class FuelLevelData {
        public float level;
        public long timestamp;

        private FuelLevelData() {
        }
    }

    public FuelLevelData getFuelLevelData(FuelLevelData data) {
        checkType(291504903);
        if (data == null) {
            data = new FuelLevelData();
        }
        data.timestamp = this.timestamp;
        if (this.floatValues == null) {
            data.level = -1.0f;
        } else if (this.floatValues[0] < 0.0f) {
            data.level = -1.0f;
        } else {
            data.level = this.floatValues[0];
        }
        return data;
    }

    /* loaded from: classes.dex */
    public static class OdometerData {
        public float kms;
        public long timestamp;

        private OdometerData() {
        }
    }

    public OdometerData getOdometerData(OdometerData data) {
        checkType(291504644);
        if (data == null) {
            data = new OdometerData();
        }
        data.timestamp = this.timestamp;
        data.kms = this.floatValues[0];
        return data;
    }

    /* loaded from: classes.dex */
    public static class RpmData {
        public float rpm;
        public long timestamp;

        private RpmData() {
        }
    }

    public RpmData getRpmData(RpmData data) {
        checkType(291504901);
        if (data == null) {
            data = new RpmData();
        }
        data.timestamp = this.timestamp;
        data.rpm = this.floatValues[0];
        return data;
    }

    /* loaded from: classes.dex */
    public static class CarSpeedData {
        public float carSpeed;
        public long timestamp;

        private CarSpeedData() {
        }
    }

    public CarSpeedData getCarSpeedData(CarSpeedData data) {
        checkType(291504647);
        if (data == null) {
            data = new CarSpeedData();
        }
        data.timestamp = this.timestamp;
        data.carSpeed = this.floatValues[0];
        return data;
    }

    /* loaded from: classes.dex */
    public static class CarWheelTickDistanceData {
        public long frontLeftWheelDistanceMm;
        public long frontRightWheelDistanceMm;
        public long rearLeftWheelDistanceMm;
        public long rearRightWheelDistanceMm;
        public long sensorResetCount;
        public long timestamp;

        private CarWheelTickDistanceData() {
        }
    }

    public CarWheelTickDistanceData getCarWheelTickDistanceData(CarWheelTickDistanceData data) {
        checkType(290521862);
        if (data == null) {
            data = new CarWheelTickDistanceData();
        }
        data.timestamp = this.timestamp;
        data.sensorResetCount = this.longValues[0];
        data.frontLeftWheelDistanceMm = this.longValues[1];
        data.frontRightWheelDistanceMm = this.longValues[2];
        data.rearRightWheelDistanceMm = this.longValues[3];
        data.rearLeftWheelDistanceMm = this.longValues[4];
        return data;
    }

    /* loaded from: classes.dex */
    public static class CarAbsActiveData {
        public boolean absIsActive;
        public long timestamp;

        private CarAbsActiveData() {
        }
    }

    public CarAbsActiveData getCarAbsActiveData(CarAbsActiveData data) {
        checkType(287310858);
        if (data == null) {
            data = new CarAbsActiveData();
        }
        data.timestamp = this.timestamp;
        data.absIsActive = this.intValues[0] == 1;
        return data;
    }

    /* loaded from: classes.dex */
    public static class CarTractionControlActiveData {
        public long timestamp;
        public boolean tractionControlIsActive;

        private CarTractionControlActiveData() {
        }
    }

    public CarTractionControlActiveData getCarTractionControlActiveData(CarTractionControlActiveData data) {
        checkType(287310859);
        if (data == null) {
            data = new CarTractionControlActiveData();
        }
        data.timestamp = this.timestamp;
        data.tractionControlIsActive = this.intValues[0] == 1;
        return data;
    }

    /* loaded from: classes.dex */
    public static class CarFuelDoorOpenData {
        public boolean fuelDoorIsOpen;
        public long timestamp;

        private CarFuelDoorOpenData() {
        }
    }

    public CarFuelDoorOpenData getCarFuelDoorOpenData(CarFuelDoorOpenData data) {
        checkType(287310600);
        if (data == null) {
            data = new CarFuelDoorOpenData();
        }
        data.timestamp = this.timestamp;
        data.fuelDoorIsOpen = this.intValues[0] == 1;
        return data;
    }

    /* loaded from: classes.dex */
    public static class CarEvBatteryLevelData {
        public float evBatteryLevel;
        public long timestamp;

        private CarEvBatteryLevelData() {
        }
    }

    public CarEvBatteryLevelData getCarEvBatteryLevelData(CarEvBatteryLevelData data) {
        checkType(291504905);
        if (data == null) {
            data = new CarEvBatteryLevelData();
        }
        data.timestamp = this.timestamp;
        if (this.floatValues == null) {
            data.evBatteryLevel = -1.0f;
        } else if (this.floatValues[0] < 0.0f) {
            data.evBatteryLevel = -1.0f;
        } else {
            data.evBatteryLevel = this.floatValues[0];
        }
        return data;
    }

    /* loaded from: classes.dex */
    public static class CarEvChargePortOpenData {
        public boolean evChargePortIsOpen;
        public long timestamp;

        private CarEvChargePortOpenData() {
        }
    }

    public CarEvChargePortOpenData getCarEvChargePortOpenData(CarEvChargePortOpenData data) {
        checkType(287310602);
        if (data == null) {
            data = new CarEvChargePortOpenData();
        }
        data.timestamp = this.timestamp;
        data.evChargePortIsOpen = this.intValues[0] == 1;
        return data;
    }

    /* loaded from: classes.dex */
    public static class CarEvChargePortConnectedData {
        public boolean evChargePortIsConnected;
        public long timestamp;

        private CarEvChargePortConnectedData() {
        }
    }

    public CarEvChargePortConnectedData getCarEvChargePortConnectedData(CarEvChargePortConnectedData data) {
        checkType(287310603);
        if (data == null) {
            data = new CarEvChargePortConnectedData();
        }
        data.timestamp = this.timestamp;
        data.evChargePortIsConnected = this.intValues[0] == 1;
        return data;
    }

    /* loaded from: classes.dex */
    public static class CarEvBatteryChargeRateData {
        public float evChargeRate;
        public long timestamp;

        private CarEvBatteryChargeRateData() {
        }
    }

    public CarEvBatteryChargeRateData getCarEvBatteryChargeRateData(CarEvBatteryChargeRateData data) {
        checkType(291504908);
        if (data == null) {
            data = new CarEvBatteryChargeRateData();
        }
        data.timestamp = this.timestamp;
        data.evChargeRate = this.floatValues[0];
        return data;
    }

    /* loaded from: classes.dex */
    public static class CarEngineOilLevelData {
        public int engineOilLevel;
        public long timestamp;

        private CarEngineOilLevelData() {
        }
    }

    public CarEngineOilLevelData getCarEngineOilLevelData(CarEngineOilLevelData data) {
        checkType(289407747);
        if (data == null) {
            data = new CarEngineOilLevelData();
        }
        data.timestamp = this.timestamp;
        data.engineOilLevel = this.intValues[0];
        return data;
    }

    public String toString() {
        long[] jArr;
        int[] iArr;
        float[] fArr;
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName() + "[");
        sb.append("type:" + Integer.toHexString(this.sensorType));
        if (this.floatValues != null && this.floatValues.length > 0) {
            sb.append(" float values:");
            for (float v : this.floatValues) {
                sb.append(" " + v);
            }
        }
        if (this.intValues != null && this.intValues.length > 0) {
            sb.append(" int values:");
            for (int v2 : this.intValues) {
                sb.append(" " + v2);
            }
        }
        if (this.longValues != null && this.longValues.length > 0) {
            sb.append(" long values:");
            for (long v3 : this.longValues) {
                sb.append(" " + v3);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
