package android.car.storagemonitoring;

import android.annotation.SystemApi;
import android.car.storagemonitoring.IoStatsEntry;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SystemApi
/* loaded from: classes.dex */
public final class IoStats implements Parcelable {
    public static final Parcelable.Creator<IoStats> CREATOR = new Parcelable.Creator<IoStats>() { // from class: android.car.storagemonitoring.IoStats.1
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
    private final List<IoStatsEntry> mStats;
    private final long mUptimeTimestamp;

    public IoStats(List<IoStatsEntry> stats, long timestamp) {
        this.mStats = stats;
        this.mUptimeTimestamp = timestamp;
    }

    public IoStats(Parcel in) {
        this.mStats = in.createTypedArrayList(IoStatsEntry.CREATOR);
        this.mUptimeTimestamp = in.readLong();
    }

    public IoStats(JSONObject in) throws JSONException {
        this.mUptimeTimestamp = in.getInt("uptime");
        JSONArray statsArray = in.getJSONArray("stats");
        this.mStats = new ArrayList();
        for (int i = 0; i < statsArray.length(); i++) {
            this.mStats.add(new IoStatsEntry(statsArray.getJSONObject(i)));
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mStats);
        dest.writeLong(this.mUptimeTimestamp);
    }

    public void writeToJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("uptime").value(this.mUptimeTimestamp);
        jsonWriter.name("stats").beginArray();
        for (IoStatsEntry stat : this.mStats) {
            stat.writeToJson(jsonWriter);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getTimestamp() {
        return this.mUptimeTimestamp;
    }

    public List<IoStatsEntry> getStats() {
        return this.mStats;
    }

    public int hashCode() {
        return Objects.hash(this.mStats, Long.valueOf(this.mUptimeTimestamp));
    }

    public IoStatsEntry getUserIdStats(int uid) {
        for (IoStatsEntry stats : getStats()) {
            if (stats.uid == uid) {
                return stats;
            }
        }
        return null;
    }

    public IoStatsEntry.Metrics getForegroundTotals() {
        long bytesRead = 0;
        long bytesWritten = 0;
        long bytesReadFromStorage = 0;
        long bytesWrittenToStorage = 0;
        long fsyncCalls = 0;
        for (IoStatsEntry stats : getStats()) {
            bytesRead += stats.foreground.bytesRead;
            bytesWritten += stats.foreground.bytesWritten;
            bytesReadFromStorage += stats.foreground.bytesReadFromStorage;
            bytesWrittenToStorage += stats.foreground.bytesWrittenToStorage;
            fsyncCalls += stats.foreground.fsyncCalls;
        }
        return new IoStatsEntry.Metrics(bytesRead, bytesWritten, bytesReadFromStorage, bytesWrittenToStorage, fsyncCalls);
    }

    public IoStatsEntry.Metrics getBackgroundTotals() {
        long bytesRead = 0;
        long bytesWritten = 0;
        long bytesReadFromStorage = 0;
        long bytesWrittenToStorage = 0;
        long fsyncCalls = 0;
        for (IoStatsEntry stats : getStats()) {
            bytesRead += stats.background.bytesRead;
            bytesWritten += stats.background.bytesWritten;
            bytesReadFromStorage += stats.background.bytesReadFromStorage;
            bytesWrittenToStorage += stats.background.bytesWrittenToStorage;
            fsyncCalls += stats.background.fsyncCalls;
        }
        return new IoStatsEntry.Metrics(bytesRead, bytesWritten, bytesReadFromStorage, bytesWrittenToStorage, fsyncCalls);
    }

    public IoStatsEntry.Metrics getTotals() {
        IoStatsEntry.Metrics foreground = getForegroundTotals();
        IoStatsEntry.Metrics background = getBackgroundTotals();
        return new IoStatsEntry.Metrics(foreground.bytesRead + background.bytesRead, foreground.bytesWritten + background.bytesWritten, foreground.bytesReadFromStorage + background.bytesReadFromStorage, foreground.bytesWrittenToStorage + background.bytesWrittenToStorage, foreground.fsyncCalls + background.fsyncCalls);
    }

    public boolean equals(Object other) {
        if (other instanceof IoStats) {
            IoStats delta = (IoStats) other;
            return delta.getTimestamp() == getTimestamp() && delta.getStats().equals(getStats());
        }
        return false;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (IoStatsEntry stats : getStats()) {
            stringJoiner.add(stats.toString());
        }
        return "timestamp = " + getTimestamp() + ", stats = " + stringJoiner.toString();
    }
}
