package android.car.vms;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@SystemApi
/* loaded from: classes.dex */
public final class VmsLayersOffering implements Parcelable {
    public static final Parcelable.Creator<VmsLayersOffering> CREATOR = new Parcelable.Creator<VmsLayersOffering>() { // from class: android.car.vms.VmsLayersOffering.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsLayersOffering createFromParcel(Parcel in) {
            return new VmsLayersOffering(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsLayersOffering[] newArray(int size) {
            return new VmsLayersOffering[size];
        }
    };
    private final Set<VmsLayerDependency> mDependencies;
    private final int mPublisherId;

    public VmsLayersOffering(Set<VmsLayerDependency> dependencies, int publisherId) {
        this.mDependencies = Collections.unmodifiableSet(dependencies);
        this.mPublisherId = publisherId;
    }

    public Set<VmsLayerDependency> getDependencies() {
        return this.mDependencies;
    }

    public int getPublisherId() {
        return this.mPublisherId;
    }

    public String toString() {
        return "VmsLayersOffering{ Publisher: " + this.mPublisherId + " Dependencies: " + this.mDependencies + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelableList(new ArrayList(this.mDependencies), flags);
        out.writeInt(this.mPublisherId);
    }

    public boolean equals(Object o) {
        if (o instanceof VmsLayersOffering) {
            VmsLayersOffering p = (VmsLayersOffering) o;
            return Objects.equals(Integer.valueOf(p.mPublisherId), Integer.valueOf(this.mPublisherId)) && p.mDependencies.equals(this.mDependencies);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mPublisherId), this.mDependencies);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private VmsLayersOffering(Parcel in) {
        ArrayList arrayList = new ArrayList();
        in.readParcelableList(arrayList, VmsLayerDependency.class.getClassLoader());
        this.mDependencies = Collections.unmodifiableSet(new HashSet(arrayList));
        this.mPublisherId = in.readInt();
    }
}
