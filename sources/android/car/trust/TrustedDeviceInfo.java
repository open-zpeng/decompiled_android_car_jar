package android.car.trust;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

@SystemApi
/* loaded from: classes.dex */
public final class TrustedDeviceInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: android.car.trust.TrustedDeviceInfo.1
        @Override // android.os.Parcelable.Creator
        public TrustedDeviceInfo createFromParcel(Parcel in) {
            return new TrustedDeviceInfo(in);
        }

        @Override // android.os.Parcelable.Creator
        public TrustedDeviceInfo[] newArray(int size) {
            return new TrustedDeviceInfo[size];
        }
    };
    public static final String DEFAULT_NAME = "Default";
    private static final String DEVICE_INFO_DELIMITER = ",";
    private final String mAddress;
    private final long mHandle;
    private final String mName;

    public TrustedDeviceInfo(long handle, String address, String name) {
        this.mHandle = handle;
        this.mAddress = address;
        this.mName = name;
    }

    public long getHandle() {
        return this.mHandle;
    }

    public String getName() {
        return this.mName;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public TrustedDeviceInfo(Parcel in) {
        this.mHandle = in.readLong();
        this.mName = in.readString();
        this.mAddress = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mHandle);
        dest.writeString(this.mName);
        dest.writeString(this.mAddress);
    }

    public String toString() {
        return String.format("TrustedDevice{ handle=%d. address=%s, name=%s }", Long.valueOf(this.mHandle), this.mAddress, this.mName);
    }

    public boolean equals(Object obj) {
        if (obj instanceof TrustedDeviceInfo) {
            TrustedDeviceInfo secondDevice = (TrustedDeviceInfo) obj;
            return this.mHandle == secondDevice.getHandle();
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mHandle));
    }

    public String serialize() {
        return String.join(DEVICE_INFO_DELIMITER, String.valueOf(this.mHandle), this.mAddress, this.mName);
    }

    public static TrustedDeviceInfo deserialize(String deviceInfo) {
        String[] res = deviceInfo.split(DEVICE_INFO_DELIMITER);
        return new TrustedDeviceInfo(Long.valueOf(res[0]).longValue(), res[1], res[2]);
    }
}
