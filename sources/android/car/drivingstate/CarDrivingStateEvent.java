package android.car.drivingstate;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SystemApi
/* loaded from: classes.dex */
public class CarDrivingStateEvent implements Parcelable {
    public static final Parcelable.Creator<CarDrivingStateEvent> CREATOR = new Parcelable.Creator<CarDrivingStateEvent>() { // from class: android.car.drivingstate.CarDrivingStateEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarDrivingStateEvent createFromParcel(Parcel in) {
            return new CarDrivingStateEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarDrivingStateEvent[] newArray(int size) {
            return new CarDrivingStateEvent[size];
        }
    };
    public static final int DRIVING_STATE_IDLING = 1;
    public static final int DRIVING_STATE_MOVING = 2;
    public static final int DRIVING_STATE_PARKED = 0;
    public static final int DRIVING_STATE_UNKNOWN = -1;
    public final int eventValue;
    public final long timeStamp;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CarDrivingState {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.eventValue);
        dest.writeLong(this.timeStamp);
    }

    public CarDrivingStateEvent(int value, long time) {
        this.eventValue = value;
        this.timeStamp = time;
    }

    private CarDrivingStateEvent(Parcel in) {
        this.eventValue = in.readInt();
        this.timeStamp = in.readLong();
    }

    public String toString() {
        return this.eventValue + " " + this.timeStamp;
    }
}
