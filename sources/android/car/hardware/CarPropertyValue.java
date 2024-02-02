package android.car.hardware;

import android.car.XpDebugLog;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.Charset;
import java.util.Arrays;
/* loaded from: classes.dex */
public class CarPropertyValue<T> implements Parcelable {
    public static final int STATUS_AVAILABLE = 0;
    public static final int STATUS_ERROR = 2;
    public static final int STATUS_UNAVAILABLE = 1;
    private final int mAreaId;
    private long mExtraTimestamp;
    private final int mPropertyId;
    private final int mStatus;
    private final long mTimestamp;
    private int mTxPid;
    private final T mValue;
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public static final Parcelable.Creator<CarPropertyValue> CREATOR = new Parcelable.Creator<CarPropertyValue>() { // from class: android.car.hardware.CarPropertyValue.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyValue createFromParcel(Parcel in) {
            return new CarPropertyValue(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyValue[] newArray(int size) {
            return new CarPropertyValue[size];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyStatus {
    }

    public CarPropertyValue(int propertyId, T value) {
        this(propertyId, 0, 0, 0L, value);
    }

    public CarPropertyValue(int propertyId, int areaId, T value) {
        this(propertyId, areaId, 0, 0L, value);
    }

    public CarPropertyValue(int propertyId, int areaId, T value, int txPid) {
        this(propertyId, areaId, 0, 0L, value);
        this.mTxPid = txPid;
    }

    public CarPropertyValue(int propertyId, int areaId, int status, long timestamp, T value) {
        this.mPropertyId = propertyId;
        this.mAreaId = areaId;
        this.mStatus = status;
        this.mTimestamp = timestamp;
        this.mValue = value;
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Long[]] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Integer[]] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.Float[], T] */
    public CarPropertyValue(Parcel in) {
        this.mPropertyId = in.readInt();
        this.mAreaId = in.readInt();
        this.mStatus = in.readInt();
        this.mTimestamp = in.readLong();
        this.mTxPid = in.readInt();
        String valueClassName = in.readString();
        try {
            Class<?> valueClass = Class.forName(valueClassName);
            if (String.class.equals(valueClass)) {
                byte[] bytes = in.readBlob();
                this.mValue = (T) new String(bytes, DEFAULT_CHARSET);
            } else if (byte[].class.equals(valueClass)) {
                this.mValue = (T) in.readBlob();
            } else {
                int i = 0;
                if (Float[].class.equals(valueClass)) {
                    int N = in.readInt();
                    if (N >= 0 && N <= (in.dataAvail() >> 2)) {
                        ?? r3 = (T) new Float[N];
                        while (i < N) {
                            r3[i] = Float.valueOf(in.readFloat());
                            i++;
                        }
                        this.mValue = r3;
                        return;
                    }
                    this.mValue = null;
                } else if (Integer[].class.equals(valueClass)) {
                    int N2 = in.readInt();
                    if (N2 >= 0 && N2 <= (in.dataAvail() >> 2)) {
                        ?? r32 = (T) new Integer[N2];
                        while (i < N2) {
                            r32[i] = Integer.valueOf(in.readInt());
                            i++;
                        }
                        this.mValue = r32;
                        return;
                    }
                    this.mValue = null;
                } else if (Long[].class.equals(valueClass)) {
                    int N3 = in.readInt();
                    if (N3 >= 0 && N3 <= (in.dataAvail() >> 3)) {
                        ?? r33 = (T) new Long[N3];
                        while (i < N3) {
                            r33[i] = Long.valueOf(in.readLong());
                            i++;
                        }
                        this.mValue = r33;
                        return;
                    }
                    this.mValue = null;
                } else {
                    this.mValue = (T) in.readValue(valueClass.getClassLoader());
                }
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class not found: " + valueClassName);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mPropertyId);
        dest.writeInt(this.mAreaId);
        dest.writeInt(this.mStatus);
        dest.writeLong(this.mTimestamp);
        dest.writeInt(this.mTxPid);
        Class<?> valueClass = this.mValue == null ? null : this.mValue.getClass();
        dest.writeString(valueClass != null ? valueClass.getName() : null);
        if (String.class.equals(valueClass)) {
            dest.writeBlob(((String) this.mValue).getBytes(DEFAULT_CHARSET));
        } else if (byte[].class.equals(valueClass)) {
            dest.writeBlob((byte[]) this.mValue);
        } else {
            int i = 0;
            if (Float[].class.equals(valueClass)) {
                Float[] val = (Float[]) this.mValue;
                if (val != null) {
                    int N = val.length;
                    dest.writeInt(N);
                    while (i < N) {
                        dest.writeFloat(val[i].floatValue());
                        i++;
                    }
                    return;
                }
                dest.writeInt(-1);
            } else if (Integer[].class.equals(valueClass)) {
                Integer[] val2 = (Integer[]) this.mValue;
                if (val2 != null) {
                    int N2 = val2.length;
                    dest.writeInt(N2);
                    while (i < N2) {
                        dest.writeInt(val2[i].intValue());
                        i++;
                    }
                    return;
                }
                dest.writeInt(-1);
            } else if (Long[].class.equals(valueClass)) {
                Long[] val3 = (Long[]) this.mValue;
                if (val3 != null) {
                    int N3 = val3.length;
                    dest.writeInt(N3);
                    while (i < N3) {
                        dest.writeLong(val3[i].longValue());
                        i++;
                    }
                    return;
                }
                dest.writeInt(-1);
            } else {
                dest.writeValue(this.mValue);
            }
        }
    }

    public int getPropertyId() {
        return this.mPropertyId;
    }

    public int getAreaId() {
        return this.mAreaId;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setExtraTimestamp(long timestamp) {
        this.mExtraTimestamp = timestamp;
    }

    public long getExtraTimestamp() {
        return this.mExtraTimestamp;
    }

    public int getTxPid() {
        return this.mTxPid;
    }

    public void setTxPid(int txPid) {
        this.mTxPid = txPid;
    }

    public T getValue() {
        return this.mValue;
    }

    public String toString() {
        String value;
        Class<?> valueClass = this.mValue.getClass();
        if (valueClass.isArray()) {
            if (byte[].class.equals(valueClass)) {
                value = Arrays.toString((byte[]) this.mValue);
            } else {
                value = Arrays.toString((Object[]) this.mValue);
            }
        } else {
            value = this.mValue.toString();
        }
        return "CarPropertyValue{PropertyName=" + XpDebugLog.getPropertyName(this.mPropertyId) + ", mPropertyId=" + this.mPropertyId + "(0x" + Integer.toHexString(this.mPropertyId) + "), mAreaId=0x" + Integer.toHexString(this.mAreaId) + ", mStatus=" + this.mStatus + ", mTimestamp=" + this.mTimestamp + ", mTxPid=" + this.mTxPid + ", mValue=" + value + '}';
    }
}
