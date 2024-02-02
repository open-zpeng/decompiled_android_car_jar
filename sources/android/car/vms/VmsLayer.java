package android.car.vms;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
@SystemApi
/* loaded from: classes.dex */
public final class VmsLayer implements Parcelable {
    public static final Parcelable.Creator<VmsLayer> CREATOR = new Parcelable.Creator<VmsLayer>() { // from class: android.car.vms.VmsLayer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsLayer createFromParcel(Parcel in) {
            return new VmsLayer(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsLayer[] newArray(int size) {
            return new VmsLayer[size];
        }
    };
    private int mSubtype;
    private int mType;
    private int mVersion;

    public VmsLayer(int type, int subtype, int version) {
        this.mType = type;
        this.mSubtype = subtype;
        this.mVersion = version;
    }

    public int getType() {
        return this.mType;
    }

    public int getSubtype() {
        return this.mSubtype;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public boolean equals(Object o) {
        if (o instanceof VmsLayer) {
            VmsLayer p = (VmsLayer) o;
            return Objects.equals(Integer.valueOf(p.mType), Integer.valueOf(this.mType)) && Objects.equals(Integer.valueOf(p.mSubtype), Integer.valueOf(this.mSubtype)) && Objects.equals(Integer.valueOf(p.mVersion), Integer.valueOf(this.mVersion));
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mType), Integer.valueOf(this.mSubtype), Integer.valueOf(this.mVersion));
    }

    public String toString() {
        return "VmsLayer{ Type: " + this.mType + ", Sub type: " + this.mSubtype + ", Version: " + this.mVersion + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mType);
        out.writeInt(this.mSubtype);
        out.writeInt(this.mVersion);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private VmsLayer(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        this.mType = in.readInt();
        this.mSubtype = in.readInt();
        this.mVersion = in.readInt();
    }
}
