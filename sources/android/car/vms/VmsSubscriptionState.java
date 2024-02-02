package android.car.vms;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public final class VmsSubscriptionState implements Parcelable {
    public static final Parcelable.Creator<VmsSubscriptionState> CREATOR = new Parcelable.Creator<VmsSubscriptionState>() { // from class: android.car.vms.VmsSubscriptionState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsSubscriptionState createFromParcel(Parcel in) {
            return new VmsSubscriptionState(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsSubscriptionState[] newArray(int size) {
            return new VmsSubscriptionState[size];
        }
    };
    private final Set<VmsLayer> mLayers;
    private final int mSequenceNumber;
    private final Set<VmsAssociatedLayer> mSubscribedLayersFromPublishers;

    public VmsSubscriptionState(int sequenceNumber, Set<VmsLayer> subscribedLayers, Set<VmsAssociatedLayer> layersFromPublishers) {
        this.mSequenceNumber = sequenceNumber;
        this.mLayers = Collections.unmodifiableSet(subscribedLayers);
        this.mSubscribedLayersFromPublishers = Collections.unmodifiableSet(layersFromPublishers);
    }

    public int getSequenceNumber() {
        return this.mSequenceNumber;
    }

    public Set<VmsLayer> getLayers() {
        return this.mLayers;
    }

    public Set<VmsAssociatedLayer> getAssociatedLayers() {
        return this.mSubscribedLayersFromPublishers;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("sequence number=");
        sb.append(this.mSequenceNumber);
        sb.append("; layers={");
        for (VmsLayer layer : this.mLayers) {
            sb.append(layer);
            sb.append(",");
        }
        sb.append("}");
        sb.append("; associatedLayers={");
        for (VmsAssociatedLayer layer2 : this.mSubscribedLayersFromPublishers) {
            sb.append(layer2);
            sb.append(",");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mSequenceNumber);
        out.writeParcelableList(new ArrayList(this.mLayers), flags);
        out.writeParcelableList(new ArrayList(this.mSubscribedLayersFromPublishers), flags);
    }

    public boolean equals(Object o) {
        if (o instanceof VmsSubscriptionState) {
            VmsSubscriptionState p = (VmsSubscriptionState) o;
            return Objects.equals(Integer.valueOf(p.mSequenceNumber), Integer.valueOf(this.mSequenceNumber)) && p.mLayers.equals(this.mLayers) && p.mSubscribedLayersFromPublishers.equals(this.mSubscribedLayersFromPublishers);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mSequenceNumber), this.mLayers, this.mSubscribedLayersFromPublishers);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private VmsSubscriptionState(Parcel in) {
        this.mSequenceNumber = in.readInt();
        ArrayList arrayList = new ArrayList();
        in.readParcelableList(arrayList, VmsLayer.class.getClassLoader());
        this.mLayers = Collections.unmodifiableSet(new HashSet(arrayList));
        ArrayList arrayList2 = new ArrayList();
        in.readParcelableList(arrayList2, VmsAssociatedLayer.class.getClassLoader());
        this.mSubscribedLayersFromPublishers = Collections.unmodifiableSet(new HashSet(arrayList2));
    }
}
