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
public final class VmsAvailableLayers implements Parcelable {
    public static final Parcelable.Creator<VmsAvailableLayers> CREATOR = new Parcelable.Creator<VmsAvailableLayers>() { // from class: android.car.vms.VmsAvailableLayers.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsAvailableLayers createFromParcel(Parcel in) {
            return new VmsAvailableLayers(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsAvailableLayers[] newArray(int size) {
            return new VmsAvailableLayers[size];
        }
    };
    private final Set<VmsAssociatedLayer> mAssociatedLayers;
    private final int mSeq;

    public VmsAvailableLayers(Set<VmsAssociatedLayer> associatedLayers, int sequence) {
        this.mSeq = sequence;
        this.mAssociatedLayers = Collections.unmodifiableSet(associatedLayers);
    }

    public int getSequence() {
        return this.mSeq;
    }

    public Set<VmsAssociatedLayer> getAssociatedLayers() {
        return this.mAssociatedLayers;
    }

    public String toString() {
        return "VmsAvailableLayers{ seq: " + this.mSeq + ", AssociatedLayers: " + this.mAssociatedLayers + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mSeq);
        out.writeParcelableList(new ArrayList(this.mAssociatedLayers), flags);
    }

    public boolean equals(Object o) {
        if (o instanceof VmsAvailableLayers) {
            VmsAvailableLayers p = (VmsAvailableLayers) o;
            return Objects.equals(p.mAssociatedLayers, this.mAssociatedLayers) && p.mSeq == this.mSeq;
        }
        return false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private VmsAvailableLayers(Parcel in) {
        this.mSeq = in.readInt();
        ArrayList arrayList = new ArrayList();
        in.readParcelableList(arrayList, VmsAssociatedLayer.class.getClassLoader());
        this.mAssociatedLayers = Collections.unmodifiableSet(new HashSet(arrayList));
    }
}
