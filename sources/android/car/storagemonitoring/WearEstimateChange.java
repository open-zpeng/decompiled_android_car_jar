package android.car.storagemonitoring;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.time.Instant;
import java.util.Objects;

@SystemApi
/* loaded from: classes.dex */
public final class WearEstimateChange implements Parcelable {
    public static final Parcelable.Creator<WearEstimateChange> CREATOR = new Parcelable.Creator<WearEstimateChange>() { // from class: android.car.storagemonitoring.WearEstimateChange.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WearEstimateChange createFromParcel(Parcel in) {
            return new WearEstimateChange(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WearEstimateChange[] newArray(int size) {
            return new WearEstimateChange[size];
        }
    };
    public final Instant dateAtChange;
    public final boolean isAcceptableDegradation;
    public final WearEstimate newEstimate;
    public final WearEstimate oldEstimate;
    public final long uptimeAtChange;

    public WearEstimateChange(WearEstimate oldEstimate, WearEstimate newEstimate, long uptimeAtChange, Instant dateAtChange, boolean isAcceptableDegradation) {
        if (uptimeAtChange < 0) {
            throw new IllegalArgumentException("uptimeAtChange must be >= 0");
        }
        this.oldEstimate = (WearEstimate) Objects.requireNonNull(oldEstimate);
        this.newEstimate = (WearEstimate) Objects.requireNonNull(newEstimate);
        this.uptimeAtChange = uptimeAtChange;
        this.dateAtChange = (Instant) Objects.requireNonNull(dateAtChange);
        this.isAcceptableDegradation = isAcceptableDegradation;
    }

    public WearEstimateChange(Parcel in) {
        this.oldEstimate = (WearEstimate) in.readParcelable(WearEstimate.class.getClassLoader());
        this.newEstimate = (WearEstimate) in.readParcelable(WearEstimate.class.getClassLoader());
        this.uptimeAtChange = in.readLong();
        this.dateAtChange = Instant.ofEpochMilli(in.readLong());
        this.isAcceptableDegradation = in.readInt() == 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.oldEstimate, flags);
        dest.writeParcelable(this.newEstimate, flags);
        dest.writeLong(this.uptimeAtChange);
        dest.writeLong(this.dateAtChange.toEpochMilli());
        dest.writeInt(this.isAcceptableDegradation ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (other instanceof WearEstimateChange) {
            WearEstimateChange wo = (WearEstimateChange) other;
            return wo.isAcceptableDegradation == this.isAcceptableDegradation && wo.uptimeAtChange == this.uptimeAtChange && wo.dateAtChange.equals(this.dateAtChange) && wo.oldEstimate.equals(this.oldEstimate) && wo.newEstimate.equals(this.newEstimate);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.oldEstimate, this.newEstimate, Long.valueOf(this.uptimeAtChange), this.dateAtChange, Boolean.valueOf(this.isAcceptableDegradation));
    }

    public String toString() {
        Object[] objArr = new Object[5];
        objArr[0] = this.oldEstimate;
        objArr[1] = this.newEstimate;
        objArr[2] = Long.valueOf(this.uptimeAtChange);
        objArr[3] = this.dateAtChange;
        objArr[4] = this.isAcceptableDegradation ? "yes" : "no";
        return String.format("wear change{old level=%s, new level=%s, uptime=%d, date=%s, acceptable=%s}", objArr);
    }
}
