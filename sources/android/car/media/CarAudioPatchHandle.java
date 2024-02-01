package android.car.media;

import android.annotation.SystemApi;
import android.media.AudioDevicePort;
import android.media.AudioPatch;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
@SystemApi
/* loaded from: classes.dex */
public final class CarAudioPatchHandle implements Parcelable {
    public static final Parcelable.Creator<CarAudioPatchHandle> CREATOR = new Parcelable.Creator<CarAudioPatchHandle>() { // from class: android.car.media.CarAudioPatchHandle.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarAudioPatchHandle createFromParcel(Parcel in) {
            return new CarAudioPatchHandle(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarAudioPatchHandle[] newArray(int size) {
            return new CarAudioPatchHandle[size];
        }
    };
    private final int mHandleId;
    private final String mSinkAddress;
    private final String mSourceAddress;

    public CarAudioPatchHandle(AudioPatch patch) {
        boolean z = true;
        Preconditions.checkArgument(patch.sources().length == 1 && (patch.sources()[0].port() instanceof AudioDevicePort), "Accepts exactly one device port as source");
        Preconditions.checkArgument((patch.sinks().length == 1 && (patch.sinks()[0].port() instanceof AudioDevicePort)) ? z : false, "Accepts exactly one device port as sink");
        this.mHandleId = patch.id();
        this.mSourceAddress = patch.sources()[0].port().address();
        this.mSinkAddress = patch.sinks()[0].port().address();
    }

    public boolean represents(AudioPatch patch) {
        return patch.sources().length == 1 && patch.sinks().length == 1 && patch.id() == this.mHandleId;
    }

    public String toString() {
        return "Patch (mHandleId=" + this.mHandleId + "): " + this.mSourceAddress + " => " + this.mSinkAddress;
    }

    private CarAudioPatchHandle(Parcel in) {
        this.mHandleId = in.readInt();
        this.mSourceAddress = in.readString();
        this.mSinkAddress = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mHandleId);
        out.writeString(this.mSourceAddress);
        out.writeString(this.mSinkAddress);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
