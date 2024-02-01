package android.car.hardware;

import android.annotation.SystemApi;
import android.car.XpDebugLog;
import android.car.hardware.property.CarPropertyManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class CarPropertyConfig<T> implements Parcelable {
    public static final Parcelable.Creator<CarPropertyConfig> CREATOR = new Parcelable.Creator<CarPropertyConfig>() { // from class: android.car.hardware.CarPropertyConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyConfig createFromParcel(Parcel in) {
            return new CarPropertyConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyConfig[] newArray(int size) {
            return new CarPropertyConfig[size];
        }
    };
    public static final int VEHICLE_PROPERTY_ACCESS_NONE = 0;
    public static final int VEHICLE_PROPERTY_ACCESS_READ = 1;
    public static final int VEHICLE_PROPERTY_ACCESS_READ_WRITE = 3;
    public static final int VEHICLE_PROPERTY_ACCESS_WRITE = 2;
    public static final int VEHICLE_PROPERTY_CHANGE_MODE_CONTINUOUS = 2;
    public static final int VEHICLE_PROPERTY_CHANGE_MODE_ONCHANGE = 1;
    public static final int VEHICLE_PROPERTY_CHANGE_MODE_STATIC = 0;
    private final int mAccess;
    private final int mAreaType;
    private final int mChangeMode;
    private final ArrayList<Integer> mConfigArray;
    private final String mConfigString;
    private final float mMaxSampleRate;
    private final float mMinSampleRate;
    private final int mPropertyId;
    private final SparseArray<AreaConfig<T>> mSupportedAreas;
    private final Class<T> mType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface VehiclePropertyAccessType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface VehiclePropertyChangeModeType {
    }

    private CarPropertyConfig(int access, int areaType, int changeMode, ArrayList<Integer> configArray, String configString, float maxSampleRate, float minSampleRate, int propertyId, SparseArray<AreaConfig<T>> supportedAreas, Class<T> type) {
        this.mAccess = access;
        this.mAreaType = areaType;
        this.mChangeMode = changeMode;
        this.mConfigArray = configArray;
        this.mConfigString = configString;
        this.mMaxSampleRate = maxSampleRate;
        this.mMinSampleRate = minSampleRate;
        this.mPropertyId = propertyId;
        this.mSupportedAreas = supportedAreas;
        this.mType = type;
    }

    public int getAccess() {
        return this.mAccess;
    }

    public int getAreaType() {
        return this.mAreaType;
    }

    public int getChangeMode() {
        return this.mChangeMode;
    }

    public List<Integer> getConfigArray() {
        return Collections.unmodifiableList(this.mConfigArray);
    }

    public String getConfigString() {
        return this.mConfigString;
    }

    public float getMaxSampleRate() {
        return this.mMaxSampleRate;
    }

    public float getMinSampleRate() {
        return this.mMinSampleRate;
    }

    public int getPropertyId() {
        return this.mPropertyId;
    }

    @SystemApi
    public Class<T> getPropertyType() {
        return this.mType;
    }

    public boolean isGlobalProperty() {
        return this.mAreaType == 0;
    }

    public int getAreaCount() {
        return this.mSupportedAreas.size();
    }

    public int[] getAreaIds() {
        int[] areaIds = new int[this.mSupportedAreas.size()];
        for (int i = 0; i < areaIds.length; i++) {
            areaIds[i] = this.mSupportedAreas.keyAt(i);
        }
        return areaIds;
    }

    public int getFirstAndOnlyAreaId() {
        if (this.mSupportedAreas.size() != 1) {
            throw new IllegalStateException("Expected one and only area in this property. Prop: 0x" + Integer.toHexString(this.mPropertyId));
        }
        return this.mSupportedAreas.keyAt(0);
    }

    public boolean hasArea(int areaId) {
        return this.mSupportedAreas.indexOfKey(areaId) >= 0;
    }

    public T getMinValue(int areaId) {
        AreaConfig<T> area = this.mSupportedAreas.get(areaId);
        if (area == null) {
            return null;
        }
        return area.getMinValue();
    }

    public T getMaxValue(int areaId) {
        AreaConfig<T> area = this.mSupportedAreas.get(areaId);
        if (area == null) {
            return null;
        }
        return area.getMaxValue();
    }

    public T getMinValue() {
        AreaConfig<T> area = this.mSupportedAreas.get(0);
        if (area == null) {
            return null;
        }
        return area.getMinValue();
    }

    public T getMaxValue() {
        AreaConfig<T> area = this.mSupportedAreas.get(0);
        if (area == null) {
            return null;
        }
        return area.getMaxValue();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mAccess);
        dest.writeInt(this.mAreaType);
        dest.writeInt(this.mChangeMode);
        dest.writeInt(this.mConfigArray.size());
        for (int i = 0; i < this.mConfigArray.size(); i++) {
            dest.writeInt(this.mConfigArray.get(i).intValue());
        }
        dest.writeString(this.mConfigString);
        dest.writeFloat(this.mMaxSampleRate);
        dest.writeFloat(this.mMinSampleRate);
        dest.writeInt(this.mPropertyId);
        dest.writeInt(this.mSupportedAreas.size());
        for (int i2 = 0; i2 < this.mSupportedAreas.size(); i2++) {
            dest.writeInt(this.mSupportedAreas.keyAt(i2));
            dest.writeParcelable(this.mSupportedAreas.valueAt(i2), flags);
        }
        dest.writeString(this.mType.getName());
    }

    private CarPropertyConfig(Parcel in) {
        this.mAccess = in.readInt();
        this.mAreaType = in.readInt();
        this.mChangeMode = in.readInt();
        int configArraySize = in.readInt();
        this.mConfigArray = new ArrayList<>(configArraySize);
        for (int i = 0; i < configArraySize; i++) {
            this.mConfigArray.add(Integer.valueOf(in.readInt()));
        }
        this.mConfigString = in.readString();
        this.mMaxSampleRate = in.readFloat();
        this.mMinSampleRate = in.readFloat();
        this.mPropertyId = in.readInt();
        int areaSize = in.readInt();
        this.mSupportedAreas = new SparseArray<>(areaSize);
        for (int i2 = 0; i2 < areaSize; i2++) {
            int areaId = in.readInt();
            AreaConfig<T> area = (AreaConfig) in.readParcelable(getClass().getClassLoader());
            this.mSupportedAreas.put(areaId, area);
        }
        String className = in.readString();
        try {
            this.mType = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class not found: " + className);
        }
    }

    public String toString() {
        return "CarPropertyConfig{PropertyName=" + XpDebugLog.getPropertyName(this.mPropertyId) + ", mPropertyId=" + this.mPropertyId + "(0x" + Integer.toHexString(this.mPropertyId) + "), mAccess=" + this.mAccess + ", mAreaType=" + this.mAreaType + ", mChangeMode=" + this.mChangeMode + ", mConfigArray=" + this.mConfigArray + ", mConfigString=" + this.mConfigString + ", mMaxSampleRate=" + this.mMaxSampleRate + ", mMinSampleRate=" + this.mMinSampleRate + ", mSupportedAreas=" + this.mSupportedAreas + ", mType=" + this.mType + '}';
    }

    /* loaded from: classes.dex */
    public static class AreaConfig<T> implements Parcelable {
        public static final Parcelable.Creator<AreaConfig<Object>> CREATOR = getCreator(Object.class);
        private final T mMaxValue;
        private final T mMinValue;

        private AreaConfig(T minValue, T maxValue) {
            this.mMinValue = minValue;
            this.mMaxValue = maxValue;
        }

        private static <E> Parcelable.Creator<AreaConfig<E>> getCreator(final Class<E> clazz) {
            return new Parcelable.Creator<AreaConfig<E>>() { // from class: android.car.hardware.CarPropertyConfig.AreaConfig.1
                @Override // android.os.Parcelable.Creator
                public AreaConfig<E> createFromParcel(Parcel source) {
                    return new AreaConfig<>(source);
                }

                @Override // android.os.Parcelable.Creator
                public AreaConfig<E>[] newArray(int size) {
                    return (AreaConfig[]) Array.newInstance(clazz, size);
                }
            };
        }

        private AreaConfig(Parcel in) {
            this.mMinValue = (T) in.readValue(getClass().getClassLoader());
            this.mMaxValue = (T) in.readValue(getClass().getClassLoader());
        }

        public T getMinValue() {
            return this.mMinValue;
        }

        public T getMaxValue() {
            return this.mMaxValue;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.mMinValue);
            dest.writeValue(this.mMaxValue);
        }

        public String toString() {
            return "CarAreaConfig{mMinValue=" + this.mMinValue + ", mMaxValue=" + this.mMaxValue + '}';
        }
    }

    @SystemApi
    public static <T> Builder<T> newBuilder(Class<T> type, int propertyId, int areaType, int areaCapacity) {
        return new Builder<>(areaCapacity, areaType, propertyId, type);
    }

    public static <T> Builder<T> newBuilder(Class<T> type, int propertyId, int areaType) {
        return new Builder<>(0, areaType, propertyId, type);
    }

    @SystemApi
    /* loaded from: classes.dex */
    public static class Builder<T> {
        private int mAccess;
        private final int mAreaType;
        private int mChangeMode;
        private final ArrayList<Integer> mConfigArray;
        private String mConfigString;
        private float mMaxSampleRate;
        private float mMinSampleRate;
        private final int mPropertyId;
        private final SparseArray<AreaConfig<T>> mSupportedAreas;
        private final Class<T> mType;

        private Builder(int areaCapacity, int areaType, int propertyId, Class<T> type) {
            this.mAreaType = areaType;
            this.mConfigArray = new ArrayList<>();
            this.mPropertyId = propertyId;
            if (areaCapacity != 0) {
                this.mSupportedAreas = new SparseArray<>(areaCapacity);
            } else {
                this.mSupportedAreas = new SparseArray<>();
            }
            this.mType = type;
        }

        public Builder<T> addAreas(int[] areaIds) {
            for (int id : areaIds) {
                this.mSupportedAreas.put(id, null);
            }
            return this;
        }

        public Builder<T> addArea(int areaId) {
            return addAreaConfig(areaId, null, null);
        }

        public Builder<T> addAreaConfig(int areaId, T min, T max) {
            if (!isRangeAvailable(min, max)) {
                this.mSupportedAreas.put(areaId, null);
            } else {
                this.mSupportedAreas.put(areaId, new AreaConfig<>(min, max));
            }
            return this;
        }

        public Builder<T> setAccess(int access) {
            this.mAccess = access;
            return this;
        }

        public Builder<T> setChangeMode(int changeMode) {
            this.mChangeMode = changeMode;
            return this;
        }

        public Builder<T> setConfigArray(ArrayList<Integer> configArray) {
            this.mConfigArray.clear();
            this.mConfigArray.addAll(configArray);
            return this;
        }

        public Builder<T> setConfigString(String configString) {
            this.mConfigString = configString;
            return this;
        }

        public Builder<T> setMaxSampleRate(float maxSampleRate) {
            this.mMaxSampleRate = maxSampleRate;
            return this;
        }

        public Builder<T> setMinSampleRate(float minSampleRate) {
            this.mMinSampleRate = minSampleRate;
            return this;
        }

        public CarPropertyConfig<T> build() {
            return new CarPropertyConfig<>(this.mAccess, this.mAreaType, this.mChangeMode, this.mConfigArray, this.mConfigString, this.mMaxSampleRate, this.mMinSampleRate, this.mPropertyId, this.mSupportedAreas, this.mType);
        }

        private boolean isRangeAvailable(T min, T max) {
            if (min == null || max == null) {
                return false;
            }
            int propertyType = this.mPropertyId & 16711680;
            if (propertyType == 4194304) {
                return (((Integer) min).intValue() == 0 && ((Integer) max).intValue() == 0) ? false : true;
            } else if (propertyType == 5242880) {
                return (((Long) min).longValue() == 0 && ((Long) max).longValue() == 0) ? false : true;
            } else if (propertyType != 6291456) {
                return false;
            } else {
                return (((Float) min).floatValue() == CarPropertyManager.SENSOR_RATE_ONCHANGE && ((Float) max).floatValue() == CarPropertyManager.SENSOR_RATE_ONCHANGE) ? false : true;
            }
        }
    }
}
