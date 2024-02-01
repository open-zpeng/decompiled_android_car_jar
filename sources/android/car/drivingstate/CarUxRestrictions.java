package android.car.drivingstate;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SystemApi
/* loaded from: classes.dex */
public class CarUxRestrictions implements Parcelable {
    public static final Parcelable.Creator<CarUxRestrictions> CREATOR = new Parcelable.Creator<CarUxRestrictions>() { // from class: android.car.drivingstate.CarUxRestrictions.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarUxRestrictions createFromParcel(Parcel in) {
            return new CarUxRestrictions(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarUxRestrictions[] newArray(int size) {
            return new CarUxRestrictions[size];
        }
    };
    private static final int DEFAULT_MAX_CONTENT_DEPTH = 3;
    private static final int DEFAULT_MAX_CUMULATIVE_ITEMS = 21;
    private static final int DEFAULT_MAX_LENGTH = 120;
    public static final int UX_RESTRICTIONS_BASELINE = 0;
    public static final int UX_RESTRICTIONS_FULLY_RESTRICTED = 511;
    public static final int UX_RESTRICTIONS_LIMIT_CONTENT = 32;
    public static final int UX_RESTRICTIONS_LIMIT_STRING_LENGTH = 4;
    public static final int UX_RESTRICTIONS_NO_DIALPAD = 1;
    public static final int UX_RESTRICTIONS_NO_FILTERING = 2;
    public static final int UX_RESTRICTIONS_NO_KEYBOARD = 8;
    public static final int UX_RESTRICTIONS_NO_SETUP = 64;
    public static final int UX_RESTRICTIONS_NO_TEXT_MESSAGE = 128;
    public static final int UX_RESTRICTIONS_NO_VIDEO = 16;
    public static final int UX_RESTRICTIONS_NO_VOICE_TRANSCRIPTION = 256;
    private final int mActiveRestrictions;
    private final int mMaxContentDepth;
    private final int mMaxCumulativeContentItems;
    private final int mMaxStringLength;
    private final boolean mRequiresDistractionOptimization;
    private final long mTimeStamp;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CarUxRestrictionsInfo {
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private final int mActiveRestrictions;
        private final boolean mRequiresDistractionOptimization;
        private final long mTimeStamp;
        private int mMaxStringLength = CarUxRestrictions.DEFAULT_MAX_LENGTH;
        private int mMaxCumulativeContentItems = 21;
        private int mMaxContentDepth = 3;

        public Builder(boolean reqOpt, int restrictions, long time) {
            this.mRequiresDistractionOptimization = reqOpt;
            this.mActiveRestrictions = restrictions;
            this.mTimeStamp = time;
        }

        public Builder setMaxStringLength(int length) {
            this.mMaxStringLength = length;
            return this;
        }

        public Builder setMaxCumulativeContentItems(int number) {
            this.mMaxCumulativeContentItems = number;
            return this;
        }

        public Builder setMaxContentDepth(int depth) {
            this.mMaxContentDepth = depth;
            return this;
        }

        public CarUxRestrictions build() {
            return new CarUxRestrictions(this);
        }
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public boolean isRequiresDistractionOptimization() {
        return this.mRequiresDistractionOptimization;
    }

    public int getActiveRestrictions() {
        return this.mActiveRestrictions;
    }

    public int getMaxRestrictedStringLength() {
        return this.mMaxStringLength;
    }

    public int getMaxCumulativeContentItems() {
        return this.mMaxCumulativeContentItems;
    }

    public int getMaxContentDepth() {
        return this.mMaxContentDepth;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mActiveRestrictions);
        dest.writeLong(this.mTimeStamp);
        dest.writeInt(this.mRequiresDistractionOptimization ? 1 : 0);
        dest.writeInt(this.mMaxStringLength);
        dest.writeInt(this.mMaxCumulativeContentItems);
        dest.writeInt(this.mMaxContentDepth);
    }

    public CarUxRestrictions(CarUxRestrictions uxRestrictions) {
        this.mTimeStamp = uxRestrictions.getTimeStamp();
        this.mRequiresDistractionOptimization = uxRestrictions.isRequiresDistractionOptimization();
        this.mActiveRestrictions = uxRestrictions.getActiveRestrictions();
        this.mMaxStringLength = uxRestrictions.mMaxStringLength;
        this.mMaxCumulativeContentItems = uxRestrictions.mMaxCumulativeContentItems;
        this.mMaxContentDepth = uxRestrictions.mMaxContentDepth;
    }

    private CarUxRestrictions(Builder builder) {
        this.mTimeStamp = builder.mTimeStamp;
        this.mActiveRestrictions = builder.mActiveRestrictions;
        this.mRequiresDistractionOptimization = builder.mRequiresDistractionOptimization;
        this.mMaxStringLength = builder.mMaxStringLength;
        this.mMaxCumulativeContentItems = builder.mMaxCumulativeContentItems;
        this.mMaxContentDepth = builder.mMaxContentDepth;
    }

    private CarUxRestrictions(Parcel in) {
        this.mActiveRestrictions = in.readInt();
        this.mTimeStamp = in.readLong();
        this.mRequiresDistractionOptimization = in.readInt() != 0;
        this.mMaxStringLength = in.readInt();
        this.mMaxCumulativeContentItems = in.readInt();
        this.mMaxContentDepth = in.readInt();
    }

    public String toString() {
        return "DO: " + this.mRequiresDistractionOptimization + " UxR: " + this.mActiveRestrictions + " time: " + this.mTimeStamp;
    }

    public boolean isSameRestrictions(CarUxRestrictions other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.mRequiresDistractionOptimization != this.mRequiresDistractionOptimization || other.mActiveRestrictions != this.mActiveRestrictions) {
            return false;
        }
        return true;
    }
}
