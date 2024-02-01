package android.car.hardware.condition;

import android.annotation.TargetApi;
import android.car.XpDebugLog;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.util.ArraySet;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@TargetApi(24)
/* loaded from: classes.dex */
public class CarConditionInfo implements Parcelable {
    public static final Parcelable.Creator<CarConditionInfo> CREATOR = new Parcelable.Creator<CarConditionInfo>() { // from class: android.car.hardware.condition.CarConditionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarConditionInfo createFromParcel(Parcel in) {
            return new CarConditionInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarConditionInfo[] newArray(int size) {
            return new CarConditionInfo[size];
        }
    };
    private final SparseArray<Class> mClassArray;
    private final SparseArray<List<? extends Number>> mLimitsArray;

    private CarConditionInfo(Parcel in) {
        int size = in.readInt();
        this.mLimitsArray = new SparseArray<>(size);
        this.mClassArray = new SparseArray<>(size);
        for (int i = 0; i < size; i++) {
            int key = in.readInt();
            Class c = (Class) in.readSerializable();
            List value = in.readArrayList(c.getClassLoader());
            this.mLimitsArray.put(key, value);
            this.mClassArray.put(key, c);
        }
    }

    private CarConditionInfo(Builder b) {
        this.mLimitsArray = b.mLimitsArray;
        this.mClassArray = b.mClassArray;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        int size = this.mLimitsArray.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            int key = this.mLimitsArray.keyAt(i);
            dest.writeInt(key);
            dest.writeSerializable(this.mClassArray.get(key));
            dest.writeList(this.mLimitsArray.get(key));
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        SparseArray<List<? extends Number>> sparseArray = this.mLimitsArray;
        if (sparseArray != null) {
            int size = sparseArray.size();
            builder.append("\n");
            for (int i = 0; i < size; i++) {
                int key = this.mLimitsArray.keyAt(i);
                builder.append("\t\t");
                builder.append(XpDebugLog.getPropertyName(key));
                builder.append(": ");
                builder.append(this.mLimitsArray.get(key));
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public SparseArray<List<? extends Number>> getLimitsArray() {
        return this.mLimitsArray;
    }

    public SparseArray<Class> getClassArray() {
        return this.mClassArray;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarConditionInfo that = (CarConditionInfo) o;
        if (Objects.equals(this.mLimitsArray, that.mLimitsArray) && Objects.equals(this.mClassArray, that.mClassArray)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mLimitsArray, this.mClassArray);
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private final SparseArray<List<? extends Number>> mLimitsArray = new SparseArray<>();
        private final SparseArray<Class> mClassArray = new SparseArray<>();

        @Deprecated
        public Builder setCarSpeedLimit(Float... speedLimit) {
            if (speedLimit == null || speedLimit.length == 0) {
                throw new IllegalArgumentException("speed limit can not be null or empty");
            }
            setLimit(559944229, speedLimit);
            return this;
        }

        @Deprecated
        public Builder setCarPedalLimit(Integer... pedalLimit) {
            if (pedalLimit == null || pedalLimit.length == 0) {
                throw new IllegalArgumentException("pedal limit can not be null or empty");
            }
            if (SystemProperties.getInt("ro.boot.xp_product_major", -1) == 0) {
                setLimit(557847064, pedalLimit);
            } else {
                setLimit(557847159, pedalLimit);
            }
            return this;
        }

        public <T extends Number & Comparable<? super T>> Builder setLimit(int propId, T... limits) {
            if (limits == null || limits.length == 0) {
                throw new IllegalArgumentException("float limit can not be null or empty");
            }
            List<T> list = Arrays.asList(limits);
            Collections.sort(list);
            ArraySet<T> arraySet = new ArraySet<>();
            arraySet.addAll(list);
            this.mLimitsArray.put(propId, new ArrayList(arraySet));
            this.mClassArray.put(propId, limits[0].getClass());
            return this;
        }

        public CarConditionInfo build() {
            return new CarConditionInfo(this);
        }
    }
}
