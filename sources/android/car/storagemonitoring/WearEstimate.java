package android.car.storagemonitoring;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonWriter;
import java.io.IOException;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

@SystemApi
/* loaded from: classes.dex */
public final class WearEstimate implements Parcelable {
    public static final int UNKNOWN = -1;
    public final int typeA;
    public final int typeB;
    public static final WearEstimate UNKNOWN_ESTIMATE = new WearEstimate(-1, -1);
    public static final Parcelable.Creator<WearEstimate> CREATOR = new Parcelable.Creator<WearEstimate>() { // from class: android.car.storagemonitoring.WearEstimate.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WearEstimate createFromParcel(Parcel in) {
            return new WearEstimate(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WearEstimate[] newArray(int size) {
            return new WearEstimate[size];
        }
    };

    private static final int validateWearValue(int value) {
        if (value == -1) {
            return value;
        }
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException(value + " is not a valid wear estimate");
        }
        return value;
    }

    public WearEstimate(int typeA, int typeB) {
        this.typeA = validateWearValue(typeA);
        this.typeB = validateWearValue(typeB);
    }

    public WearEstimate(Parcel in) {
        this.typeA = validateWearValue(in.readInt());
        this.typeB = validateWearValue(in.readInt());
    }

    public WearEstimate(JsonReader in) throws IOException {
        int typeA = -1;
        int typeB = -1;
        in.beginObject();
        while (in.hasNext()) {
            String nextName = in.nextName();
            char c = 65535;
            switch (nextName.hashCode()) {
                case -1388170560:
                    if (nextName.equals("wearEstimateTypeA")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1388170559:
                    if (nextName.equals("wearEstimateTypeB")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                typeA = validateWearValue(in.nextInt());
            } else if (c == 1) {
                typeB = validateWearValue(in.nextInt());
            }
        }
        in.endObject();
        this.typeA = typeA;
        this.typeB = typeB;
    }

    public WearEstimate(JSONObject in) throws JSONException {
        this.typeA = in.getInt("wearEstimateTypeA");
        this.typeB = in.getInt("wearEstimateTypeB");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.typeA);
        dest.writeInt(this.typeB);
    }

    public void writeToJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("wearEstimateTypeA").value(this.typeA);
        jsonWriter.name("wearEstimateTypeB").value(this.typeB);
        jsonWriter.endObject();
    }

    public boolean equals(Object other) {
        if (other instanceof WearEstimate) {
            WearEstimate wo = (WearEstimate) other;
            return wo.typeA == this.typeA && wo.typeB == this.typeB;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.typeA), Integer.valueOf(this.typeB));
    }

    private static final String wearValueToString(int value) {
        if (value == -1) {
            return "unknown";
        }
        return value + "%";
    }

    public String toString() {
        return "type A: " + wearValueToString(this.typeA) + ", type B: " + wearValueToString(this.typeB);
    }
}
