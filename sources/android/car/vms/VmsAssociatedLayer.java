package android.car.vms;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public final class VmsAssociatedLayer implements Parcelable {
    public static final Parcelable.Creator<VmsAssociatedLayer> CREATOR = new Parcelable.Creator<VmsAssociatedLayer>() { // from class: android.car.vms.VmsAssociatedLayer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsAssociatedLayer createFromParcel(Parcel in) {
            return new VmsAssociatedLayer(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsAssociatedLayer[] newArray(int size) {
            return new VmsAssociatedLayer[size];
        }
    };
    private final VmsLayer mLayer;
    private final Set<Integer> mPublisherIds;

    public VmsAssociatedLayer(VmsLayer layer, Set<Integer> publisherIds) {
        this.mLayer = layer;
        this.mPublisherIds = Collections.unmodifiableSet(publisherIds);
    }

    public VmsLayer getVmsLayer() {
        return this.mLayer;
    }

    public Set<Integer> getPublisherIds() {
        return this.mPublisherIds;
    }

    public String toString() {
        return "VmsAssociatedLayer{ VmsLayer: " + this.mLayer + ", Publishers: " + this.mPublisherIds + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.mLayer, flags);
        out.writeArray(this.mPublisherIds.toArray());
    }

    public boolean equals(Object o) {
        if (o instanceof VmsAssociatedLayer) {
            VmsAssociatedLayer p = (VmsAssociatedLayer) o;
            return Objects.equals(p.mLayer, this.mLayer) && p.mPublisherIds.equals(this.mPublisherIds);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mLayer, this.mPublisherIds);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private VmsAssociatedLayer(Parcel in) {
        this.mLayer = (VmsLayer) in.readParcelable(VmsLayer.class.getClassLoader());
        Object[] objects = in.readArray(Integer.class.getClassLoader());
        Integer[] integers = (Integer[]) Arrays.copyOf(objects, objects.length, Integer[].class);
        this.mPublisherIds = Collections.unmodifiableSet(new HashSet(Arrays.asList(integers)));
    }
}
