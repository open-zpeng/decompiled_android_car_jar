package android.car.storagemonitoring;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonWriter;
import java.io.IOException;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

@SystemApi
/* loaded from: classes.dex */
public final class LifetimeWriteInfo implements Parcelable {
    public static final Parcelable.Creator<IoStats> CREATOR = new Parcelable.Creator<IoStats>() { // from class: android.car.storagemonitoring.LifetimeWriteInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IoStats createFromParcel(Parcel in) {
            return new IoStats(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IoStats[] newArray(int size) {
            return new IoStats[size];
        }
    };
    public final String fstype;
    public final String partition;
    public final long writtenBytes;

    public LifetimeWriteInfo(String partition, String fstype, long writtenBytes) {
        this.partition = (String) Objects.requireNonNull(partition);
        this.fstype = (String) Objects.requireNonNull(fstype);
        if (writtenBytes < 0) {
            throw new IllegalArgumentException("writtenBytes must be non-negative");
        }
        this.writtenBytes = writtenBytes;
    }

    public LifetimeWriteInfo(Parcel in) {
        this.partition = in.readString();
        this.fstype = in.readString();
        this.writtenBytes = in.readLong();
    }

    public LifetimeWriteInfo(JSONObject in) throws JSONException {
        this.partition = in.getString("partition");
        this.fstype = in.getString("fstype");
        this.writtenBytes = in.getLong("writtenBytes");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.partition);
        dest.writeString(this.fstype);
        dest.writeLong(this.writtenBytes);
    }

    public void writeToJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("partition").value(this.partition);
        jsonWriter.name("fstype").value(this.fstype);
        jsonWriter.name("writtenBytes").value(this.writtenBytes);
        jsonWriter.endObject();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (other instanceof LifetimeWriteInfo) {
            LifetimeWriteInfo lifetime = (LifetimeWriteInfo) other;
            return this.partition.equals(lifetime.partition) && this.fstype.equals(lifetime.fstype) && this.writtenBytes == lifetime.writtenBytes;
        }
        return false;
    }

    public String toString() {
        return String.format("for partition %s of type %s, %d bytes were written", this.partition, this.fstype, Long.valueOf(this.writtenBytes));
    }
}
