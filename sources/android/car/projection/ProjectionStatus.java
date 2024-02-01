package android.car.projection;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.IntArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SystemApi
/* loaded from: classes.dex */
public final class ProjectionStatus implements Parcelable {
    public static final Parcelable.Creator<ProjectionStatus> CREATOR = new Parcelable.Creator<ProjectionStatus>() { // from class: android.car.projection.ProjectionStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProjectionStatus createFromParcel(Parcel source) {
            return new ProjectionStatus(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProjectionStatus[] newArray(int size) {
            return new ProjectionStatus[size];
        }
    };
    public static final int PROJECTION_STATE_ACTIVE_BACKGROUND = 3;
    public static final int PROJECTION_STATE_ACTIVE_FOREGROUND = 2;
    public static final int PROJECTION_STATE_INACTIVE = 0;
    private static final int PROJECTION_STATE_MAX = 3;
    public static final int PROJECTION_STATE_READY_TO_PROJECT = 1;
    private static final int PROJECTION_TRANSPORT_MAX = 2;
    public static final int PROJECTION_TRANSPORT_NONE = 0;
    public static final int PROJECTION_TRANSPORT_USB = 1;
    public static final int PROJECTION_TRANSPORT_WIFI = 2;
    private final List<MobileDevice> mConnectedMobileDevices;
    private final Bundle mExtras;
    private final String mPackageName;
    private final int mState;
    private final int mTransport;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ProjectionState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ProjectionTransport {
    }

    private ProjectionStatus(Builder builder) {
        this.mPackageName = builder.mPackageName;
        this.mState = builder.mState;
        this.mTransport = builder.mTransport;
        this.mConnectedMobileDevices = new ArrayList(builder.mMobileDevices);
        this.mExtras = builder.mExtras == null ? null : new Bundle(builder.mExtras);
    }

    private ProjectionStatus(Parcel source) {
        this.mPackageName = source.readString();
        this.mState = source.readInt();
        this.mTransport = source.readInt();
        this.mExtras = source.readBundle(getClass().getClassLoader());
        this.mConnectedMobileDevices = source.createTypedArrayList(MobileDevice.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPackageName);
        dest.writeInt(this.mState);
        dest.writeInt(this.mTransport);
        dest.writeBundle(this.mExtras);
        dest.writeTypedList(this.mConnectedMobileDevices);
    }

    public int getState() {
        return this.mState;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public Bundle getExtras() {
        Bundle bundle = this.mExtras;
        return bundle == null ? new Bundle() : new Bundle(bundle);
    }

    public boolean isActive() {
        int i = this.mState;
        return i == 3 || i == 2;
    }

    public int getTransport() {
        return this.mTransport;
    }

    public List<MobileDevice> getConnectedMobileDevices() {
        return new ArrayList(this.mConnectedMobileDevices);
    }

    public static Builder builder(String packageName, int state) {
        return new Builder(packageName, state);
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private Bundle mExtras;
        private List<MobileDevice> mMobileDevices;
        private final String mPackageName;
        private final int mState;
        private int mTransport;

        private Builder(String packageName, int state) {
            this.mTransport = 0;
            this.mMobileDevices = new ArrayList();
            if (packageName == null) {
                throw new IllegalArgumentException("Package name can't be null");
            }
            if (state < 0 || state > 3) {
                throw new IllegalArgumentException("Invalid projection state: " + state);
            }
            this.mPackageName = packageName;
            this.mState = state;
        }

        public Builder setProjectionTransport(int transport) {
            ProjectionStatus.checkProjectionTransport(transport);
            this.mTransport = transport;
            return this;
        }

        public Builder addMobileDevice(MobileDevice mobileDevice) {
            this.mMobileDevices.add(mobileDevice);
            return this;
        }

        public Builder setExtras(Bundle extras) {
            this.mExtras = extras;
            return this;
        }

        public ProjectionStatus build() {
            return new ProjectionStatus(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkProjectionTransport(int transport) {
        if (transport < 0 || transport > 2) {
            throw new IllegalArgumentException("Invalid projection transport: " + transport);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProjectionStatus{mPackageName='");
        sb.append(this.mPackageName);
        sb.append('\'');
        sb.append(", mState=");
        sb.append(this.mState);
        sb.append(", mTransport=");
        sb.append(this.mTransport);
        sb.append(", mConnectedMobileDevices=");
        sb.append(this.mConnectedMobileDevices);
        sb.append(this.mExtras != null ? " (has extras)" : "");
        sb.append('}');
        return sb.toString();
    }

    /* loaded from: classes.dex */
    public static final class MobileDevice implements Parcelable {
        public static final Parcelable.Creator<MobileDevice> CREATOR = new Parcelable.Creator<MobileDevice>() { // from class: android.car.projection.ProjectionStatus.MobileDevice.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MobileDevice createFromParcel(Parcel source) {
                return new MobileDevice(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MobileDevice[] newArray(int size) {
                return new MobileDevice[size];
            }
        };
        private final int[] mAvailableTransports;
        private final Bundle mExtras;
        private final int mId;
        private final String mName;
        private final boolean mProjecting;

        private MobileDevice(Builder builder) {
            this.mId = builder.mId;
            this.mName = builder.mName;
            this.mAvailableTransports = builder.mAvailableTransports.toArray();
            this.mProjecting = builder.mProjecting;
            this.mExtras = builder.mExtras == null ? null : new Bundle(builder.mExtras);
        }

        private MobileDevice(Parcel source) {
            this.mId = source.readInt();
            this.mName = source.readString();
            this.mAvailableTransports = source.createIntArray();
            this.mProjecting = source.readBoolean();
            this.mExtras = source.readBundle(getClass().getClassLoader());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mId);
            dest.writeString(this.mName);
            dest.writeIntArray(this.mAvailableTransports);
            dest.writeBoolean(this.mProjecting);
            dest.writeBundle(this.mExtras);
        }

        public int getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public List<Integer> getAvailableTransports() {
            int[] iArr;
            List<Integer> transports = new ArrayList<>(this.mAvailableTransports.length);
            for (int transport : this.mAvailableTransports) {
                transports.add(Integer.valueOf(transport));
            }
            return transports;
        }

        public boolean isProjecting() {
            return this.mProjecting;
        }

        public Bundle getExtras() {
            Bundle bundle = this.mExtras;
            return bundle == null ? new Bundle() : new Bundle(bundle);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public static Builder builder(int id, String name) {
            return new Builder(id, name);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MobileDevice{mId=");
            sb.append(this.mId);
            sb.append(", mName='");
            sb.append(this.mName);
            sb.append('\'');
            sb.append(", mAvailableTransports=");
            sb.append(Arrays.toString(this.mAvailableTransports));
            sb.append(", mProjecting=");
            sb.append(this.mProjecting);
            sb.append(this.mExtras != null ? ", (has extras)" : "");
            sb.append('}');
            return sb.toString();
        }

        /* loaded from: classes.dex */
        public static final class Builder {
            private IntArray mAvailableTransports;
            private Bundle mExtras;
            private int mId;
            private String mName;
            private boolean mProjecting;

            private Builder(int id, String name) {
                this.mAvailableTransports = new IntArray();
                this.mId = id;
                if (name == null) {
                    throw new IllegalArgumentException("Name of the device can't be null");
                }
                this.mName = name;
            }

            public Builder addTransport(int transport) {
                ProjectionStatus.checkProjectionTransport(transport);
                this.mAvailableTransports.add(transport);
                return this;
            }

            public Builder setProjecting(boolean projecting) {
                this.mProjecting = projecting;
                return this;
            }

            public Builder setExtras(Bundle extras) {
                this.mExtras = extras;
                return this;
            }

            public MobileDevice build() {
                return new MobileDevice(this);
            }
        }
    }
}
