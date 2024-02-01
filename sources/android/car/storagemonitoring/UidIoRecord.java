package android.car.storagemonitoring;

import android.annotation.SystemApi;
@SystemApi
/* loaded from: classes.dex */
public final class UidIoRecord {
    public final long background_fsync;
    public final long background_rchar;
    public final long background_read_bytes;
    public final long background_wchar;
    public final long background_write_bytes;
    public final long foreground_fsync;
    public final long foreground_rchar;
    public final long foreground_read_bytes;
    public final long foreground_wchar;
    public final long foreground_write_bytes;
    public final int uid;

    public UidIoRecord(int uid, long foreground_rchar, long foreground_wchar, long foreground_read_bytes, long foreground_write_bytes, long foreground_fsync, long background_rchar, long background_wchar, long background_read_bytes, long background_write_bytes, long background_fsync) {
        this.uid = uid;
        this.foreground_rchar = foreground_rchar;
        this.foreground_wchar = foreground_wchar;
        this.foreground_read_bytes = foreground_read_bytes;
        this.foreground_write_bytes = foreground_write_bytes;
        this.foreground_fsync = foreground_fsync;
        this.background_rchar = background_rchar;
        this.background_wchar = background_wchar;
        this.background_read_bytes = background_read_bytes;
        this.background_write_bytes = background_write_bytes;
        this.background_fsync = background_fsync;
    }

    public UidIoRecord delta(IoStatsEntry other) {
        if (this.uid != other.uid) {
            throw new IllegalArgumentException("cannot calculate delta between different user IDs");
        }
        return new UidIoRecord(this.uid, this.foreground_rchar - other.foreground.bytesRead, this.foreground_wchar - other.foreground.bytesWritten, this.foreground_read_bytes - other.foreground.bytesReadFromStorage, this.foreground_write_bytes - other.foreground.bytesWrittenToStorage, this.foreground_fsync - other.foreground.fsyncCalls, this.background_rchar - other.background.bytesRead, this.background_wchar - other.background.bytesWritten, this.background_read_bytes - other.background.bytesReadFromStorage, this.background_write_bytes - other.background.bytesWrittenToStorage, this.background_fsync - other.background.fsyncCalls);
    }

    public UidIoRecord delta(UidIoRecord other) {
        if (this.uid != other.uid) {
            throw new IllegalArgumentException("cannot calculate delta between different user IDs");
        }
        return new UidIoRecord(this.uid, this.foreground_rchar - other.foreground_rchar, this.foreground_wchar - other.foreground_wchar, this.foreground_read_bytes - other.foreground_read_bytes, this.foreground_write_bytes - other.foreground_write_bytes, this.foreground_fsync - other.foreground_fsync, this.background_rchar - other.background_rchar, this.background_wchar - other.background_wchar, this.background_read_bytes - other.background_read_bytes, this.background_write_bytes - other.background_write_bytes, this.background_fsync - other.background_fsync);
    }
}
