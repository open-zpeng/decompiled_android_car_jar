package android.car.intelligent;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes.dex */
public class CarSceneEvent implements Parcelable {
    public static final int ACTION_ENTER = 0;
    public static final int ACTION_EXIT = 1;
    public static final int ACTION_NONE = -1;
    public static final Parcelable.Creator<CarSceneEvent> CREATOR = new Parcelable.Creator<CarSceneEvent>() { // from class: android.car.intelligent.CarSceneEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarSceneEvent createFromParcel(Parcel in) {
            return new CarSceneEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarSceneEvent[] newArray(int size) {
            return new CarSceneEvent[size];
        }
    };
    private static final int INVALID = -1;
    public static final int POSITION_FRONT_LEFT = 1;
    public static final int POSITION_FRONT_RIGHT = 2;
    public static final int POSITION_NONE = 0;
    public static final int POSITION_SECOND_LEFT = 3;
    public static final int POSITION_SECOND_RIGHT = 4;
    public static final int TYPE_DRIVING = 2;
    public static final int TYPE_WELCOME = 1;
    private int mExitReason;
    private int mSceneAction;
    private int mScenePosition;
    private int mSceneType;
    private long mTimeStamp;

    @Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Action {
    }

    @Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Position {
    }

    @Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Type {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mTimeStamp);
        dest.writeInt(this.mSceneType);
        dest.writeInt(this.mSceneAction);
        dest.writeInt(this.mScenePosition);
        dest.writeInt(this.mExitReason);
    }

    public CarSceneEvent() {
        this.mTimeStamp = -1L;
        this.mSceneType = 1;
        this.mSceneAction = -1;
        this.mScenePosition = 0;
        this.mExitReason = 0;
    }

    public CarSceneEvent(long time, int type, int action, int location) {
        this.mTimeStamp = -1L;
        this.mSceneType = 1;
        this.mSceneAction = -1;
        this.mScenePosition = 0;
        this.mExitReason = 0;
        this.mTimeStamp = time;
        this.mSceneType = type;
        this.mSceneAction = action;
        this.mScenePosition = location;
    }

    protected CarSceneEvent(Parcel in) {
        this.mTimeStamp = -1L;
        this.mSceneType = 1;
        this.mSceneAction = -1;
        this.mScenePosition = 0;
        this.mExitReason = 0;
        this.mTimeStamp = in.readLong();
        this.mSceneType = in.readInt();
        this.mSceneAction = in.readInt();
        this.mScenePosition = in.readInt();
        this.mExitReason = in.readInt();
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public void setTimeStamp(long value) {
        this.mTimeStamp = value;
    }

    public int getSceneType() {
        return this.mSceneType;
    }

    public void setSceneType(int value) {
        this.mSceneType = value;
    }

    public int getSceneAction() {
        return this.mSceneAction;
    }

    public void setSceneAction(int value) {
        this.mSceneAction = value;
    }

    public int getScenePosition() {
        return this.mScenePosition;
    }

    public void setScenePosition(int mSceneLocation) {
        this.mScenePosition = mSceneLocation;
    }

    public int getExitReason() {
        return this.mExitReason;
    }

    public CarSceneEvent setExitReason(int exitReason) {
        this.mExitReason = exitReason;
        return this;
    }

    public String toString() {
        return "CarSceneEvent{mTimeStamp=" + this.mTimeStamp + ", mSceneType=" + this.mSceneType + ", mSceneAction=" + this.mSceneAction + ", mScenePosition=" + this.mScenePosition + ", mExitReason=" + this.mExitReason + '}';
    }
}
