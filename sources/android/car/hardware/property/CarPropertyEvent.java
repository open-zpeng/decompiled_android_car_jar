package android.car.hardware.property;

import android.car.hardware.CarPropertyValue;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class CarPropertyEvent implements Parcelable {
    public static final Parcelable.Creator<CarPropertyEvent> CREATOR = new Parcelable.Creator<CarPropertyEvent>() { // from class: android.car.hardware.property.CarPropertyEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyEvent createFromParcel(Parcel in) {
            return new CarPropertyEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyEvent[] newArray(int size) {
            return new CarPropertyEvent[size];
        }
    };
    private static final int ERROR_EVENT_VALUE = -1;
    public static final int PROPERTY_EVENT_ERROR = 1;
    public static final int PROPERTY_EVENT_PROPERTY_CHANGE = 0;
    private final CarPropertyValue<?> mCarPropertyValue;
    private final int mEventType;

    public int getEventType() {
        return this.mEventType;
    }

    public CarPropertyValue<?> getCarPropertyValue() {
        return this.mCarPropertyValue;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mEventType);
        dest.writeParcelable(this.mCarPropertyValue, flags);
    }

    public CarPropertyEvent(int eventType, CarPropertyValue<?> carPropertyValue) {
        this.mEventType = eventType;
        this.mCarPropertyValue = carPropertyValue;
    }

    public static CarPropertyEvent createErrorEvent(int propertyId, int errorCode) {
        CarPropertyValue<Integer> valueWithErrorCode = new CarPropertyValue<>(propertyId, 0, 2, 0L, Integer.valueOf(errorCode));
        return new CarPropertyEvent(1, valueWithErrorCode);
    }

    public static CarPropertyEvent createErrorEventWithTimestamp(int propertyId, long timestamp, int errorCode) {
        CarPropertyValue<Integer> valueWithErrorCode = new CarPropertyValue<>(propertyId, 0, 2, timestamp, Integer.valueOf(errorCode));
        return new CarPropertyEvent(1, valueWithErrorCode);
    }

    private CarPropertyEvent(Parcel in) {
        this.mEventType = in.readInt();
        this.mCarPropertyValue = (CarPropertyValue) in.readParcelable(CarPropertyValue.class.getClassLoader());
    }

    public String toString() {
        return "CarPropertyEvent{mEventType=" + this.mEventType + ", mCarPropertyValue=" + this.mCarPropertyValue + '}';
    }
}
