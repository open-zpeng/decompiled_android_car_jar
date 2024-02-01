package android.car.vms;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SystemApi
/* loaded from: classes.dex */
public final class VmsLayerDependency implements Parcelable {
    public static final Parcelable.Creator<VmsLayerDependency> CREATOR = new Parcelable.Creator<VmsLayerDependency>() { // from class: android.car.vms.VmsLayerDependency.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsLayerDependency createFromParcel(Parcel in) {
            return new VmsLayerDependency(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsLayerDependency[] newArray(int size) {
            return new VmsLayerDependency[size];
        }
    };
    private final Set<VmsLayer> mDependency;
    private final VmsLayer mLayer;

    public VmsLayerDependency(VmsLayer layer, Set<VmsLayer> dependencies) {
        this.mLayer = (VmsLayer) Preconditions.checkNotNull(layer, "layer cannot be null");
        this.mDependency = Collections.unmodifiableSet(dependencies);
    }

    public VmsLayerDependency(VmsLayer layer) {
        this(layer, Collections.emptySet());
    }

    public VmsLayer getLayer() {
        return this.mLayer;
    }

    public Set<VmsLayer> getDependencies() {
        return this.mDependency;
    }

    public String toString() {
        return "VmsLayerDependency{ Layer: " + this.mLayer + " Dependency: " + this.mDependency + "}";
    }

    public boolean equals(Object o) {
        if (o instanceof VmsLayerDependency) {
            VmsLayerDependency p = (VmsLayerDependency) o;
            return Objects.equals(p.mLayer, this.mLayer) && p.mDependency.equals(this.mDependency);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mLayer, this.mDependency);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.mLayer, flags);
        out.writeParcelableList(new ArrayList(this.mDependency), flags);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private VmsLayerDependency(Parcel in) {
        this.mLayer = (VmsLayer) in.readParcelable(VmsLayer.class.getClassLoader());
        ArrayList arrayList = new ArrayList();
        in.readParcelableList(arrayList, VmsLayer.class.getClassLoader());
        this.mDependency = Collections.unmodifiableSet(new HashSet(arrayList));
    }
}
