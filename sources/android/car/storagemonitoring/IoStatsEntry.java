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
public final class IoStatsEntry implements Parcelable {
    public static final Parcelable.Creator<IoStatsEntry> CREATOR = new Parcelable.Creator<IoStatsEntry>() { // from class: android.car.storagemonitoring.IoStatsEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IoStatsEntry createFromParcel(Parcel in) {
            return new IoStatsEntry(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IoStatsEntry[] newArray(int size) {
            return new IoStatsEntry[size];
        }
    };
    public final Metrics background;
    public final Metrics foreground;
    public final long runtimeMillis;
    public final int uid;

    public IoStatsEntry(int uid, long runtimeMillis, Metrics foreground, Metrics background) {
        this.uid = uid;
        this.runtimeMillis = runtimeMillis;
        this.foreground = (Metrics) Objects.requireNonNull(foreground);
        this.background = (Metrics) Objects.requireNonNull(background);
    }

    public IoStatsEntry(Parcel in) {
        this.uid = in.readInt();
        this.runtimeMillis = in.readLong();
        this.foreground = (Metrics) in.readParcelable(Metrics.class.getClassLoader());
        this.background = (Metrics) in.readParcelable(Metrics.class.getClassLoader());
    }

    public IoStatsEntry(UidIoRecord record, long runtimeMillis) {
        this.uid = record.uid;
        this.runtimeMillis = runtimeMillis;
        this.foreground = new Metrics(record.foreground_rchar, record.foreground_wchar, record.foreground_read_bytes, record.foreground_write_bytes, record.foreground_fsync);
        this.background = new Metrics(record.background_rchar, record.background_wchar, record.background_read_bytes, record.background_write_bytes, record.background_fsync);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeLong(this.runtimeMillis);
        dest.writeParcelable(this.foreground, flags);
        dest.writeParcelable(this.background, flags);
    }

    public void writeToJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("uid").value(this.uid);
        jsonWriter.name("runtimeMillis").value(this.runtimeMillis);
        jsonWriter.name("foreground");
        this.foreground.writeToJson(jsonWriter);
        jsonWriter.name("background");
        this.background.writeToJson(jsonWriter);
        jsonWriter.endObject();
    }

    public IoStatsEntry(JSONObject in) throws JSONException {
        this.uid = in.getInt("uid");
        this.runtimeMillis = in.getLong("runtimeMillis");
        this.foreground = new Metrics(in.getJSONObject("foreground"));
        this.background = new Metrics(in.getJSONObject("background"));
    }

    public IoStatsEntry delta(IoStatsEntry other) {
        int i = this.uid;
        if (i != other.uid) {
            throw new IllegalArgumentException("cannot calculate delta between different user IDs");
        }
        return new IoStatsEntry(i, this.runtimeMillis - other.runtimeMillis, this.foreground.delta(other.foreground), this.background.delta(other.background));
    }

    public boolean equals(Object other) {
        if (other instanceof IoStatsEntry) {
            IoStatsEntry uidIoStatEntry = (IoStatsEntry) other;
            return this.uid == uidIoStatEntry.uid && this.runtimeMillis == uidIoStatEntry.runtimeMillis && this.foreground.equals(uidIoStatEntry.foreground) && this.background.equals(uidIoStatEntry.background);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.uid), Long.valueOf(this.runtimeMillis), this.foreground, this.background);
    }

    public String toString() {
        return String.format("uid = %d, runtime = %d, foreground = %s, background = %s", Integer.valueOf(this.uid), Long.valueOf(this.runtimeMillis), this.foreground, this.background);
    }

    public boolean representsSameMetrics(UidIoRecord record) {
        return record.uid == this.uid && record.foreground_rchar == this.foreground.bytesRead && record.foreground_wchar == this.foreground.bytesWritten && record.foreground_read_bytes == this.foreground.bytesReadFromStorage && record.foreground_write_bytes == this.foreground.bytesWrittenToStorage && record.foreground_fsync == this.foreground.fsyncCalls && record.background_rchar == this.background.bytesRead && record.background_wchar == this.background.bytesWritten && record.background_read_bytes == this.background.bytesReadFromStorage && record.background_write_bytes == this.background.bytesWrittenToStorage && record.background_fsync == this.background.fsyncCalls;
    }

    /* loaded from: classes.dex */
    public static final class Metrics implements Parcelable {
        public static final Parcelable.Creator<Metrics> CREATOR = new Parcelable.Creator<Metrics>() { // from class: android.car.storagemonitoring.IoStatsEntry.Metrics.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Metrics createFromParcel(Parcel in) {
                return new Metrics(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Metrics[] newArray(int size) {
                return new Metrics[size];
            }
        };
        public final long bytesRead;
        public final long bytesReadFromStorage;
        public final long bytesWritten;
        public final long bytesWrittenToStorage;
        public final long fsyncCalls;

        public Metrics(long bytesRead, long bytesWritten, long bytesReadFromStorage, long bytesWrittenToStorage, long fsyncCalls) {
            this.bytesRead = bytesRead;
            this.bytesWritten = bytesWritten;
            this.bytesReadFromStorage = bytesReadFromStorage;
            this.bytesWrittenToStorage = bytesWrittenToStorage;
            this.fsyncCalls = fsyncCalls;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(this.bytesRead);
            dest.writeLong(this.bytesWritten);
            dest.writeLong(this.bytesReadFromStorage);
            dest.writeLong(this.bytesWrittenToStorage);
            dest.writeLong(this.fsyncCalls);
        }

        public void writeToJson(JsonWriter jsonWriter) throws IOException {
            jsonWriter.beginObject();
            jsonWriter.name("bytesRead").value(this.bytesRead);
            jsonWriter.name("bytesWritten").value(this.bytesWritten);
            jsonWriter.name("bytesReadFromStorage").value(this.bytesReadFromStorage);
            jsonWriter.name("bytesWrittenToStorage").value(this.bytesWrittenToStorage);
            jsonWriter.name("fsyncCalls").value(this.fsyncCalls);
            jsonWriter.endObject();
        }

        public Metrics(Parcel in) {
            this.bytesRead = in.readLong();
            this.bytesWritten = in.readLong();
            this.bytesReadFromStorage = in.readLong();
            this.bytesWrittenToStorage = in.readLong();
            this.fsyncCalls = in.readLong();
        }

        public Metrics(JSONObject in) throws JSONException {
            this.bytesRead = in.getLong("bytesRead");
            this.bytesWritten = in.getLong("bytesWritten");
            this.bytesReadFromStorage = in.getLong("bytesReadFromStorage");
            this.bytesWrittenToStorage = in.getLong("bytesWrittenToStorage");
            this.fsyncCalls = in.getLong("fsyncCalls");
        }

        public Metrics delta(Metrics other) {
            return new Metrics(this.bytesRead - other.bytesRead, this.bytesWritten - other.bytesWritten, this.bytesReadFromStorage - other.bytesReadFromStorage, this.bytesWrittenToStorage - other.bytesWrittenToStorage, this.fsyncCalls - other.fsyncCalls);
        }

        public boolean equals(Object other) {
            if (other instanceof Metrics) {
                Metrics metrics = (Metrics) other;
                return this.bytesRead == metrics.bytesRead && this.bytesWritten == metrics.bytesWritten && this.bytesReadFromStorage == metrics.bytesReadFromStorage && this.bytesWrittenToStorage == metrics.bytesWrittenToStorage && this.fsyncCalls == metrics.fsyncCalls;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.bytesRead), Long.valueOf(this.bytesWritten), Long.valueOf(this.bytesReadFromStorage), Long.valueOf(this.bytesWrittenToStorage), Long.valueOf(this.fsyncCalls));
        }

        public String toString() {
            return String.format("bytesRead=%d, bytesWritten=%d, bytesReadFromStorage=%d, bytesWrittenToStorage=%d, fsyncCalls=%d", Long.valueOf(this.bytesRead), Long.valueOf(this.bytesWritten), Long.valueOf(this.bytesReadFromStorage), Long.valueOf(this.bytesWrittenToStorage), Long.valueOf(this.fsyncCalls));
        }
    }
}
