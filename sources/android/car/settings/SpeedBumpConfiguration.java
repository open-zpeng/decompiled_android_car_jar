package android.car.settings;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* loaded from: classes.dex */
public final class SpeedBumpConfiguration implements Parcelable {
    public static final Parcelable.Creator<SpeedBumpConfiguration> CREATOR = new Parcelable.Creator<SpeedBumpConfiguration>() { // from class: android.car.settings.SpeedBumpConfiguration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpeedBumpConfiguration createFromParcel(Parcel in) {
            return new SpeedBumpConfiguration(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpeedBumpConfiguration[] newArray(int size) {
            return new SpeedBumpConfiguration[size];
        }
    };
    private final double mAcquiredPermitsPerSecond;
    private final double mMaxPermitPool;
    private final long mPermitFillDelay;

    public SpeedBumpConfiguration(double permitsPerSecond, double maxPermitPool, long permitFillDelay) {
        this.mAcquiredPermitsPerSecond = permitsPerSecond;
        this.mMaxPermitPool = maxPermitPool;
        this.mPermitFillDelay = permitFillDelay;
    }

    public double getAcquiredPermitsPerSecond() {
        return this.mAcquiredPermitsPerSecond;
    }

    public double getMaxPermitPool() {
        return this.mMaxPermitPool;
    }

    public long getPermitFillDelay() {
        return this.mPermitFillDelay;
    }

    public String toString() {
        return String.format("[acquired_permits_per_second: %f, max_permit_pool: %f, permit_fill_delay: %d]", Double.valueOf(this.mAcquiredPermitsPerSecond), Double.valueOf(this.mMaxPermitPool), Long.valueOf(this.mPermitFillDelay));
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof SpeedBumpConfiguration) {
            SpeedBumpConfiguration other = (SpeedBumpConfiguration) object;
            return Double.compare(this.mAcquiredPermitsPerSecond, other.getAcquiredPermitsPerSecond()) == 0 && Double.compare(this.mMaxPermitPool, other.getMaxPermitPool()) == 0 && this.mPermitFillDelay == other.getPermitFillDelay();
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.mAcquiredPermitsPerSecond), Double.valueOf(this.mMaxPermitPool), Long.valueOf(this.mPermitFillDelay));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel desk, int flags) {
        desk.writeDouble(this.mAcquiredPermitsPerSecond);
        desk.writeDouble(this.mMaxPermitPool);
        desk.writeLong(this.mPermitFillDelay);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private SpeedBumpConfiguration(Parcel in) {
        this.mAcquiredPermitsPerSecond = in.readDouble();
        this.mMaxPermitPool = in.readDouble();
        this.mPermitFillDelay = in.readLong();
    }
}
