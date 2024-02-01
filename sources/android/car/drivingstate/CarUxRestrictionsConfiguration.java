package android.car.drivingstate;

import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictionsConfiguration;
import android.car.hardware.icm.CarIcmManager;
import android.car.hardware.property.CarPropertyManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.ArrayMap;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import android.util.Log;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/* loaded from: classes.dex */
public final class CarUxRestrictionsConfiguration implements Parcelable {
    private static final String JSON_NAME_IDLING_RESTRICTIONS = "idling_restrictions";
    private static final String JSON_NAME_MAX_CONTENT_DEPTH = "max_content_depth";
    private static final String JSON_NAME_MAX_CUMULATIVE_CONTENT_ITEMS = "max_cumulative_content_items";
    private static final String JSON_NAME_MAX_SPEED = "max_speed";
    private static final String JSON_NAME_MAX_STRING_LENGTH = "max_string_length";
    private static final String JSON_NAME_MIN_SPEED = "min_speed";
    private static final String JSON_NAME_MOVING_RESTRICTIONS = "moving_restrictions";
    private static final String JSON_NAME_PARKED_RESTRICTIONS = "parked_restrictions";
    private static final String JSON_NAME_PHYSICAL_PORT = "physical_port";
    private static final String JSON_NAME_REQ_OPT = "req_opt";
    private static final String JSON_NAME_RESTRICTIONS = "restrictions";
    private static final String JSON_NAME_SPEED_RANGE = "speed_range";
    private static final String JSON_NAME_UNKNOWN_RESTRICTIONS = "unknown_restrictions";
    private static final String TAG = "CarUxRConfig";
    private final int mMaxContentDepth;
    private final int mMaxCumulativeContentItems;
    private final int mMaxStringLength;
    private final Byte mPhysicalPort;
    private final Map<String, RestrictionModeContainer> mRestrictionModes;
    private static final int[] DRIVING_STATES = {-1, 0, 1, 2};
    public static final Parcelable.Creator<CarUxRestrictionsConfiguration> CREATOR = new Parcelable.Creator<CarUxRestrictionsConfiguration>() { // from class: android.car.drivingstate.CarUxRestrictionsConfiguration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarUxRestrictionsConfiguration createFromParcel(Parcel source) {
            return new CarUxRestrictionsConfiguration(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarUxRestrictionsConfiguration[] newArray(int size) {
            return new CarUxRestrictionsConfiguration[size];
        }
    };

    /* loaded from: classes.dex */
    private interface RestrictionConfigurationParser {
        void readJson(JsonReader jsonReader, String str, Builder builder) throws IOException;
    }

    private CarUxRestrictionsConfiguration(Builder builder) {
        int[] iArr;
        this.mRestrictionModes = new ArrayMap();
        this.mPhysicalPort = builder.mPhysicalPort;
        this.mMaxContentDepth = builder.mMaxContentDepth;
        this.mMaxCumulativeContentItems = builder.mMaxCumulativeContentItems;
        this.mMaxStringLength = builder.mMaxStringLength;
        for (Map.Entry<String, RestrictionModeContainer> entry : builder.mRestrictionModes.entrySet()) {
            String mode = entry.getKey();
            RestrictionModeContainer container = new RestrictionModeContainer();
            for (int drivingState : DRIVING_STATES) {
                container.setRestrictionsForDriveState(drivingState, Collections.unmodifiableList(entry.getValue().getRestrictionsForDriveState(drivingState)));
            }
            this.mRestrictionModes.put(mode, container);
        }
    }

    public CarUxRestrictions getUxRestrictions(int drivingState, float currentSpeed) {
        return getUxRestrictions(drivingState, currentSpeed, CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE);
    }

    public CarUxRestrictions getUxRestrictions(int drivingState, float currentSpeed, String mode) {
        Objects.requireNonNull(mode, "mode must not be null");
        RestrictionsPerSpeedRange restriction = null;
        if (this.mRestrictionModes.containsKey(mode)) {
            restriction = findUxRestrictionsInList(currentSpeed, this.mRestrictionModes.get(mode).getRestrictionsForDriveState(drivingState));
        }
        if (restriction == null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, String.format("No restrictions specified for (mode: %s, drive state: %s)", mode, Integer.valueOf(drivingState)));
            }
            restriction = findUxRestrictionsInList(currentSpeed, this.mRestrictionModes.get(CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE).getRestrictionsForDriveState(drivingState));
        }
        if (restriction == null) {
            if (Build.IS_ENG || Build.IS_USERDEBUG) {
                throw new IllegalStateException("No restrictions for driving state " + getDrivingStateName(drivingState));
            }
            return createDefaultUxRestrictionsEvent();
        }
        return createUxRestrictionsEvent(restriction.mReqOpt, restriction.mRestrictions);
    }

    public Byte getPhysicalPort() {
        return this.mPhysicalPort;
    }

    private static RestrictionsPerSpeedRange findUxRestrictionsInList(float currentSpeed, List<RestrictionsPerSpeedRange> restrictions) {
        if (restrictions.isEmpty()) {
            return null;
        }
        if (restrictions.size() == 1 && restrictions.get(0).mSpeedRange == null) {
            return restrictions.get(0);
        }
        for (RestrictionsPerSpeedRange r : restrictions) {
            if (r.mSpeedRange != null && r.mSpeedRange.includes(currentSpeed)) {
                return r;
            }
        }
        return null;
    }

    private CarUxRestrictions createDefaultUxRestrictionsEvent() {
        return createUxRestrictionsEvent(true, CarUxRestrictions.UX_RESTRICTIONS_FULLY_RESTRICTED);
    }

    private CarUxRestrictions createUxRestrictionsEvent(boolean requiresOpt, int uxr) {
        if (uxr != 0) {
            requiresOpt = true;
        }
        CarUxRestrictions.Builder builder = new CarUxRestrictions.Builder(requiresOpt, uxr, SystemClock.elapsedRealtimeNanos());
        int i = this.mMaxStringLength;
        if (i != -1) {
            builder.setMaxStringLength(i);
        }
        int i2 = this.mMaxCumulativeContentItems;
        if (i2 != -1) {
            builder.setMaxCumulativeContentItems(i2);
        }
        int i3 = this.mMaxContentDepth;
        if (i3 != -1) {
            builder.setMaxContentDepth(i3);
        }
        return builder.build();
    }

    public void writeJson(JsonWriter writer) throws IOException {
        Objects.requireNonNull(writer, "writer must not be null");
        writer.setLenient(true);
        writer.beginObject();
        if (this.mPhysicalPort == null) {
            writer.name(JSON_NAME_PHYSICAL_PORT).nullValue();
        } else {
            writer.name(JSON_NAME_PHYSICAL_PORT).value(this.mPhysicalPort.byteValue());
        }
        writer.name(JSON_NAME_MAX_CONTENT_DEPTH).value(this.mMaxContentDepth);
        writer.name(JSON_NAME_MAX_CUMULATIVE_CONTENT_ITEMS).value(this.mMaxCumulativeContentItems);
        writer.name(JSON_NAME_MAX_STRING_LENGTH).value(this.mMaxStringLength);
        for (Map.Entry<String, RestrictionModeContainer> entry : this.mRestrictionModes.entrySet()) {
            writer.name(entry.getKey());
            writeRestrictionMode(writer, entry.getValue());
        }
        writer.endObject();
    }

    private void writeRestrictionMode(JsonWriter writer, RestrictionModeContainer container) throws IOException {
        writer.beginObject();
        writer.name(JSON_NAME_PARKED_RESTRICTIONS);
        writeRestrictionsList(writer, container.getRestrictionsForDriveState(0));
        writer.name(JSON_NAME_IDLING_RESTRICTIONS);
        writeRestrictionsList(writer, container.getRestrictionsForDriveState(1));
        writer.name(JSON_NAME_MOVING_RESTRICTIONS);
        writeRestrictionsList(writer, container.getRestrictionsForDriveState(2));
        writer.name(JSON_NAME_UNKNOWN_RESTRICTIONS);
        writeRestrictionsList(writer, container.getRestrictionsForDriveState(-1));
        writer.endObject();
    }

    private void writeRestrictionsList(JsonWriter writer, List<RestrictionsPerSpeedRange> messages) throws IOException {
        writer.beginArray();
        for (RestrictionsPerSpeedRange restrictions : messages) {
            writeRestrictions(writer, restrictions);
        }
        writer.endArray();
    }

    private void writeRestrictions(JsonWriter writer, RestrictionsPerSpeedRange restrictions) throws IOException {
        writer.beginObject();
        writer.name(JSON_NAME_REQ_OPT).value(restrictions.mReqOpt);
        writer.name(JSON_NAME_RESTRICTIONS).value(restrictions.mRestrictions);
        if (restrictions.mSpeedRange != null) {
            writer.name(JSON_NAME_SPEED_RANGE);
            writer.beginObject();
            writer.name(JSON_NAME_MIN_SPEED).value(restrictions.mSpeedRange.mMinSpeed);
            writer.name(JSON_NAME_MAX_SPEED).value(restrictions.mSpeedRange.mMaxSpeed);
            writer.endObject();
        }
        writer.endObject();
    }

    public String toString() {
        CharArrayWriter charWriter = new CharArrayWriter();
        JsonWriter writer = new JsonWriter(charWriter);
        writer.setIndent("\t");
        try {
            writeJson(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charWriter.toString();
    }

    public static CarUxRestrictionsConfiguration readJson(JsonReader reader, int schemaVersion) throws IOException {
        Objects.requireNonNull(reader, "reader must not be null");
        reader.setLenient(true);
        RestrictionConfigurationParser parser = createConfigurationParser(schemaVersion);
        Builder builder = new Builder();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            char c = 65535;
            switch (name.hashCode()) {
                case -1631290199:
                    if (name.equals(JSON_NAME_MAX_CUMULATIVE_CONTENT_ITEMS)) {
                        c = 2;
                        break;
                    }
                    break;
                case -1059087390:
                    if (name.equals(JSON_NAME_MAX_CONTENT_DEPTH)) {
                        c = 1;
                        break;
                    }
                    break;
                case -1030681799:
                    if (name.equals(JSON_NAME_MAX_STRING_LENGTH)) {
                        c = 3;
                        break;
                    }
                    break;
                case 1538350825:
                    if (name.equals(JSON_NAME_PHYSICAL_PORT)) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c != 0) {
                if (c == 1) {
                    builder.setMaxContentDepth(reader.nextInt());
                } else if (c == 2) {
                    builder.setMaxCumulativeContentItems(reader.nextInt());
                } else if (c == 3) {
                    builder.setMaxStringLength(reader.nextInt());
                } else {
                    parser.readJson(reader, name, builder);
                }
            } else if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
            } else {
                builder.setPhysicalPort(Builder.validatePort(reader.nextInt()));
            }
        }
        reader.endObject();
        return builder.build();
    }

    private static RestrictionConfigurationParser createConfigurationParser(int schemaVersion) {
        if (schemaVersion != 1) {
            if (schemaVersion == 2) {
                return new V2RestrictionConfigurationParser();
            }
            throw new IllegalArgumentException("No parser supported for schemaVersion " + schemaVersion);
        }
        return new V1RestrictionConfigurationParser();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class V2RestrictionConfigurationParser implements RestrictionConfigurationParser {
        private V2RestrictionConfigurationParser() {
        }

        @Override // android.car.drivingstate.CarUxRestrictionsConfiguration.RestrictionConfigurationParser
        public void readJson(JsonReader reader, String name, Builder builder) throws IOException {
            CarUxRestrictionsConfiguration.readRestrictionsMode(reader, name, builder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class V1RestrictionConfigurationParser implements RestrictionConfigurationParser {
        private static final String JSON_NAME_PASSENGER_IDLING_RESTRICTIONS = "passenger_idling_restrictions";
        private static final String JSON_NAME_PASSENGER_MOVING_RESTRICTIONS = "passenger_moving_restrictions";
        private static final String JSON_NAME_PASSENGER_PARKED_RESTRICTIONS = "passenger_parked_restrictions";
        private static final String JSON_NAME_PASSENGER_UNKNOWN_RESTRICTIONS = "passenger_unknown_restrictions";
        private static final String PASSENGER_MODE_NAME_FOR_MIGRATION = "passenger";

        private V1RestrictionConfigurationParser() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.car.drivingstate.CarUxRestrictionsConfiguration.RestrictionConfigurationParser
        public void readJson(JsonReader reader, String name, Builder builder) throws IOException {
            char c;
            switch (name.hashCode()) {
                case -1828817279:
                    if (name.equals(JSON_NAME_PASSENGER_UNKNOWN_RESTRICTIONS)) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -1781561187:
                    if (name.equals(CarUxRestrictionsConfiguration.JSON_NAME_PARKED_RESTRICTIONS)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -321131524:
                    if (name.equals(CarUxRestrictionsConfiguration.JSON_NAME_UNKNOWN_RESTRICTIONS)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 242100051:
                    if (name.equals(JSON_NAME_PASSENGER_MOVING_RESTRICTIONS)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 983471736:
                    if (name.equals(CarUxRestrictionsConfiguration.JSON_NAME_MOVING_RESTRICTIONS)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1054686448:
                    if (name.equals(JSON_NAME_PASSENGER_IDLING_RESTRICTIONS)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1772034424:
                    if (name.equals(JSON_NAME_PASSENGER_PARKED_RESTRICTIONS)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1796058133:
                    if (name.equals(CarUxRestrictionsConfiguration.JSON_NAME_IDLING_RESTRICTIONS)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    CarUxRestrictionsConfiguration.readRestrictionsList(reader, 0, CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE, builder);
                    return;
                case 1:
                    CarUxRestrictionsConfiguration.readRestrictionsList(reader, 1, CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE, builder);
                    return;
                case 2:
                    CarUxRestrictionsConfiguration.readRestrictionsList(reader, 2, CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE, builder);
                    return;
                case 3:
                    CarUxRestrictionsConfiguration.readRestrictionsList(reader, -1, CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE, builder);
                    return;
                case 4:
                    CarUxRestrictionsConfiguration.readRestrictionsList(reader, 0, PASSENGER_MODE_NAME_FOR_MIGRATION, builder);
                    return;
                case 5:
                    CarUxRestrictionsConfiguration.readRestrictionsList(reader, 1, PASSENGER_MODE_NAME_FOR_MIGRATION, builder);
                    return;
                case 6:
                    CarUxRestrictionsConfiguration.readRestrictionsList(reader, 2, PASSENGER_MODE_NAME_FOR_MIGRATION, builder);
                    return;
                case 7:
                    CarUxRestrictionsConfiguration.readRestrictionsList(reader, -1, PASSENGER_MODE_NAME_FOR_MIGRATION, builder);
                    return;
                default:
                    Log.e(CarUxRestrictionsConfiguration.TAG, "Unknown name parsing json config: " + name);
                    reader.skipValue();
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void readRestrictionsMode(JsonReader reader, String mode, Builder builder) throws IOException {
        boolean z;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name.hashCode()) {
                case -1781561187:
                    if (name.equals(JSON_NAME_PARKED_RESTRICTIONS)) {
                        z = false;
                        break;
                    }
                    z = true;
                    break;
                case -321131524:
                    if (name.equals(JSON_NAME_UNKNOWN_RESTRICTIONS)) {
                        z = true;
                        break;
                    }
                    z = true;
                    break;
                case 983471736:
                    if (name.equals(JSON_NAME_MOVING_RESTRICTIONS)) {
                        z = true;
                        break;
                    }
                    z = true;
                    break;
                case 1796058133:
                    if (name.equals(JSON_NAME_IDLING_RESTRICTIONS)) {
                        z = true;
                        break;
                    }
                    z = true;
                    break;
                default:
                    z = true;
                    break;
            }
            if (!z) {
                readRestrictionsList(reader, 0, mode, builder);
            } else if (z) {
                readRestrictionsList(reader, 1, mode, builder);
            } else if (z) {
                readRestrictionsList(reader, 2, mode, builder);
            } else if (z) {
                readRestrictionsList(reader, -1, mode, builder);
            } else {
                Log.e(TAG, "Unknown name parsing restriction mode json config: " + name);
            }
        }
        reader.endObject();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readRestrictionsList(JsonReader reader, int drivingState, String mode, Builder builder) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            DrivingStateRestrictions drivingStateRestrictions = readRestrictions(reader);
            drivingStateRestrictions.setMode(mode);
            builder.setUxRestrictions(drivingState, drivingStateRestrictions);
        }
        reader.endArray();
    }

    private static DrivingStateRestrictions readRestrictions(JsonReader reader) throws IOException {
        reader.beginObject();
        boolean reqOpt = false;
        int restrictions = 0;
        Builder.SpeedRange speedRange = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(JSON_NAME_REQ_OPT)) {
                reqOpt = reader.nextBoolean();
            } else if (name.equals(JSON_NAME_RESTRICTIONS)) {
                restrictions = reader.nextInt();
            } else if (name.equals(JSON_NAME_SPEED_RANGE)) {
                reader.beginObject();
                float minSpeed = Float.POSITIVE_INFINITY;
                float maxSpeed = Float.POSITIVE_INFINITY;
                while (reader.hasNext()) {
                    String n = reader.nextName();
                    if (n.equals(JSON_NAME_MIN_SPEED)) {
                        minSpeed = Double.valueOf(reader.nextDouble()).floatValue();
                    } else if (n.equals(JSON_NAME_MAX_SPEED)) {
                        maxSpeed = Double.valueOf(reader.nextDouble()).floatValue();
                    } else {
                        Log.e(TAG, "Unknown name parsing json config: " + n);
                        reader.skipValue();
                    }
                }
                speedRange = new Builder.SpeedRange(minSpeed, maxSpeed);
                reader.endObject();
            }
        }
        reader.endObject();
        DrivingStateRestrictions drivingStateRestrictions = new DrivingStateRestrictions().setDistractionOptimizationRequired(reqOpt).setRestrictions(restrictions);
        if (speedRange != null) {
            drivingStateRestrictions.setSpeedRange(speedRange);
        }
        return drivingStateRestrictions;
    }

    public int hashCode() {
        return Objects.hash(this.mPhysicalPort, Integer.valueOf(this.mMaxStringLength), Integer.valueOf(this.mMaxCumulativeContentItems), Integer.valueOf(this.mMaxContentDepth), this.mRestrictionModes);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CarUxRestrictionsConfiguration) {
            CarUxRestrictionsConfiguration other = (CarUxRestrictionsConfiguration) obj;
            return this.mPhysicalPort == other.mPhysicalPort && hasSameParameters(other) && this.mRestrictionModes.equals(other.mRestrictionModes);
        }
        return false;
    }

    public boolean hasSameParameters(CarUxRestrictionsConfiguration other) {
        Objects.requireNonNull(other, "other must not be null");
        return this.mMaxContentDepth == other.mMaxContentDepth && this.mMaxCumulativeContentItems == other.mMaxCumulativeContentItems && this.mMaxStringLength == other.mMaxStringLength;
    }

    public void dump(PrintWriter writer) {
        Objects.requireNonNull(writer, "writer must not be null");
        writer.println("Physical display port: " + this.mPhysicalPort);
        for (Map.Entry<String, RestrictionModeContainer> entry : this.mRestrictionModes.entrySet()) {
            writer.println("===========================================");
            writer.println(entry.getKey() + " mode UXR:");
            writer.println("-------------------------------------------");
            dumpRestrictions(writer, entry.getValue().mDriveStateUxRestrictions);
        }
        writer.println("Max String length: " + this.mMaxStringLength);
        writer.println("Max Cumulative Content Items: " + this.mMaxCumulativeContentItems);
        writer.println("Max Content depth: " + this.mMaxContentDepth);
        writer.println("===========================================");
    }

    private void dumpRestrictions(PrintWriter writer, Map<Integer, List<RestrictionsPerSpeedRange>> restrictions) {
        for (Integer state : restrictions.keySet()) {
            List<RestrictionsPerSpeedRange> list = restrictions.get(state);
            writer.println("State:" + getDrivingStateName(state.intValue()) + " num restrictions:" + list.size());
            for (RestrictionsPerSpeedRange r : list) {
                StringBuilder sb = new StringBuilder();
                sb.append("Requires DO? ");
                sb.append(r.mReqOpt);
                sb.append("\nRestrictions: 0x");
                sb.append(Integer.toHexString(r.mRestrictions));
                sb.append("\nSpeed Range: ");
                sb.append(r.mSpeedRange == null ? CarIcmManager.MSG_NONE : r.mSpeedRange.mMinSpeed + " - " + r.mSpeedRange.mMaxSpeed);
                writer.println(sb.toString());
                writer.println("-------------------------------------------");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getDrivingStateName(int state) {
        if (state != -1) {
            if (state != 0) {
                if (state != 1) {
                    if (state == 2) {
                        return "moving";
                    }
                    throw new IllegalArgumentException("Unrecognized state value: " + state);
                }
                return "idling";
            }
            return "parked";
        }
        return "unknown";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private CarUxRestrictionsConfiguration(Parcel in) {
        int[] iArr;
        this.mRestrictionModes = new ArrayMap();
        int modesCount = in.readInt();
        for (int i = 0; i < modesCount; i++) {
            String modeName = in.readString();
            RestrictionModeContainer container = new RestrictionModeContainer();
            for (int drivingState : DRIVING_STATES) {
                ArrayList arrayList = new ArrayList();
                in.readTypedList(arrayList, RestrictionsPerSpeedRange.CREATOR);
                container.setRestrictionsForDriveState(drivingState, arrayList);
            }
            this.mRestrictionModes.put(modeName, container);
        }
        boolean nullPhysicalPort = in.readBoolean();
        byte physicalPort = in.readByte();
        this.mPhysicalPort = nullPhysicalPort ? null : Byte.valueOf(physicalPort);
        this.mMaxContentDepth = in.readInt();
        this.mMaxCumulativeContentItems = in.readInt();
        this.mMaxStringLength = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        int[] iArr;
        dest.writeInt(this.mRestrictionModes.size());
        Iterator<Map.Entry<String, RestrictionModeContainer>> it = this.mRestrictionModes.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, RestrictionModeContainer> entry = it.next();
            dest.writeString(entry.getKey());
            for (int drivingState : DRIVING_STATES) {
                dest.writeTypedList(entry.getValue().getRestrictionsForDriveState(drivingState));
            }
        }
        boolean nullPhysicalPort = this.mPhysicalPort == null;
        dest.writeBoolean(nullPhysicalPort);
        dest.writeByte(nullPhysicalPort ? (byte) 0 : this.mPhysicalPort.byteValue());
        dest.writeInt(this.mMaxContentDepth);
        dest.writeInt(this.mMaxCumulativeContentItems);
        dest.writeInt(this.mMaxStringLength);
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private static final int UX_RESTRICTIONS_UNKNOWN = -1;
        private Byte mPhysicalPort;
        private int mMaxContentDepth = -1;
        private int mMaxCumulativeContentItems = -1;
        private int mMaxStringLength = -1;
        public final Map<String, RestrictionModeContainer> mRestrictionModes = new ArrayMap();

        public static byte validatePort(int port) {
            if (-128 <= port && port <= 127) {
                return (byte) port;
            }
            throw new IllegalArgumentException("Port value should be within the range of a byte. Input is " + port);
        }

        public Builder() {
            this.mRestrictionModes.put(CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE, new RestrictionModeContainer());
        }

        public Builder setPhysicalPort(byte port) {
            this.mPhysicalPort = Byte.valueOf(port);
            return this;
        }

        public Builder setUxRestrictions(int drivingState, boolean requiresOptimization, int restrictions) {
            return setUxRestrictions(drivingState, new DrivingStateRestrictions().setDistractionOptimizationRequired(requiresOptimization).setRestrictions(restrictions));
        }

        @Deprecated
        public Builder setUxRestrictions(int drivingState, SpeedRange speedRange, boolean requiresOptimization, int restrictions) {
            return setUxRestrictions(drivingState, new DrivingStateRestrictions().setDistractionOptimizationRequired(requiresOptimization).setRestrictions(restrictions).setSpeedRange(speedRange));
        }

        public Builder setUxRestrictions(int drivingState, DrivingStateRestrictions drivingStateRestrictions) {
            SpeedRange speedRange = drivingStateRestrictions.mSpeedRange;
            if (drivingState != 2 && speedRange != null) {
                throw new IllegalArgumentException("Non-moving driving state should not specify speed range.");
            }
            RestrictionModeContainer container = this.mRestrictionModes.computeIfAbsent(drivingStateRestrictions.mMode, new Function() { // from class: android.car.drivingstate.-$$Lambda$CarUxRestrictionsConfiguration$Builder$6Fx39eJCf20Lx4VIZlNyvNj9tf0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CarUxRestrictionsConfiguration.Builder.lambda$setUxRestrictions$0((String) obj);
                }
            });
            container.getRestrictionsForDriveState(drivingState).add(new RestrictionsPerSpeedRange(drivingStateRestrictions.mMode, drivingStateRestrictions.mReqOpt, drivingStateRestrictions.mRestrictions, speedRange));
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ RestrictionModeContainer lambda$setUxRestrictions$0(String mode) {
            return new RestrictionModeContainer();
        }

        public Builder setMaxStringLength(int maxStringLength) {
            this.mMaxStringLength = maxStringLength;
            return this;
        }

        public Builder setMaxCumulativeContentItems(int maxCumulativeContentItems) {
            this.mMaxCumulativeContentItems = maxCumulativeContentItems;
            return this;
        }

        public Builder setMaxContentDepth(int maxContentDepth) {
            this.mMaxContentDepth = maxContentDepth;
            return this;
        }

        public CarUxRestrictionsConfiguration build() {
            addDefaultRestrictionsToBaseline();
            validateBaselineModeRestrictions();
            for (String mode : this.mRestrictionModes.keySet()) {
                if (!CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE.equals(mode)) {
                    validateModeRestrictions(mode);
                }
            }
            return new CarUxRestrictionsConfiguration(this);
        }

        private void addDefaultRestrictionsToBaseline() {
            int[] iArr;
            RestrictionModeContainer container = this.mRestrictionModes.get(CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE);
            for (int drivingState : CarUxRestrictionsConfiguration.DRIVING_STATES) {
                List<RestrictionsPerSpeedRange> restrictions = container.getRestrictionsForDriveState(drivingState);
                if (restrictions.size() == 0) {
                    Log.i(CarUxRestrictionsConfiguration.TAG, "Using default restrictions for driving state: " + CarUxRestrictionsConfiguration.getDrivingStateName(drivingState));
                    restrictions.add(new RestrictionsPerSpeedRange(true, CarUxRestrictions.UX_RESTRICTIONS_FULLY_RESTRICTED));
                }
            }
        }

        private void validateBaselineModeRestrictions() {
            int[] iArr;
            RestrictionModeContainer container = this.mRestrictionModes.get(CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE);
            for (int drivingState : CarUxRestrictionsConfiguration.DRIVING_STATES) {
                List<RestrictionsPerSpeedRange> restrictions = container.getRestrictionsForDriveState(drivingState);
                if (drivingState != 2 && restrictions.size() != 1) {
                    throw new IllegalStateException("Non-moving driving state should contain one set of restriction rules.");
                }
                if (restrictions.size() > 1 && restrictions.stream().anyMatch(new Predicate() { // from class: android.car.drivingstate.-$$Lambda$CarUxRestrictionsConfiguration$Builder$036s65bc2fN9OgEOD70jtM9wKFc
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return CarUxRestrictionsConfiguration.Builder.lambda$validateBaselineModeRestrictions$1((CarUxRestrictionsConfiguration.RestrictionsPerSpeedRange) obj);
                    }
                })) {
                    StringBuilder error = new StringBuilder();
                    for (RestrictionsPerSpeedRange restriction : restrictions) {
                        error.append(restriction.toString());
                        error.append('\n');
                    }
                    throw new IllegalStateException("Every restriction in MOVING state should contain driving state.\n" + error.toString());
                }
                Collections.sort(restrictions, Comparator.comparing($$Lambda$YBSNvgpLXg5IqqXs9FKuvoKXc24.INSTANCE));
                validateRangeOfSpeed(restrictions);
                validateContinuousSpeedRange(restrictions);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean lambda$validateBaselineModeRestrictions$1(RestrictionsPerSpeedRange restriction) {
            return restriction.mSpeedRange == null;
        }

        private void validateModeRestrictions(String mode) {
            if (!this.mRestrictionModes.containsKey(mode)) {
                return;
            }
            RestrictionModeContainer container = this.mRestrictionModes.get(mode);
            List<RestrictionsPerSpeedRange> movingRestrictions = container.getRestrictionsForDriveState(2);
            Collections.sort(movingRestrictions, Comparator.comparing($$Lambda$YBSNvgpLXg5IqqXs9FKuvoKXc24.INSTANCE));
            validateContinuousSpeedRange(movingRestrictions);
        }

        private void validateRangeOfSpeed(List<RestrictionsPerSpeedRange> restrictions) {
            if (restrictions.size() == 1) {
                SpeedRange speedRange = restrictions.get(0).mSpeedRange;
                if (speedRange == null) {
                    return;
                }
            }
            if (Float.compare(restrictions.get(0).mSpeedRange.mMinSpeed, CarPropertyManager.SENSOR_RATE_ONCHANGE) != 0) {
                throw new IllegalStateException("Speed range min speed should start at 0.");
            }
            float lastMaxSpeed = restrictions.get(restrictions.size() - 1).mSpeedRange.mMaxSpeed;
            if (Float.compare(lastMaxSpeed, Float.POSITIVE_INFINITY) != 0) {
                throw new IllegalStateException("Max speed of last restriction should be MAX_SPEED.");
            }
        }

        private void validateContinuousSpeedRange(List<RestrictionsPerSpeedRange> restrictions) {
            for (int i = 1; i < restrictions.size(); i++) {
                RestrictionsPerSpeedRange prev = restrictions.get(i - 1);
                RestrictionsPerSpeedRange curr = restrictions.get(i);
                if (Float.compare(curr.mSpeedRange.mMinSpeed, prev.mSpeedRange.mMaxSpeed) != 0) {
                    throw new IllegalArgumentException("Mis-configured speed range. Possibly speed range overlap or gap.");
                }
            }
        }

        /* loaded from: classes.dex */
        public static final class SpeedRange implements Comparable<SpeedRange> {
            public static final float MAX_SPEED = Float.POSITIVE_INFINITY;
            private float mMaxSpeed;
            private float mMinSpeed;

            public SpeedRange(float minSpeed) {
                this(minSpeed, Float.POSITIVE_INFINITY);
            }

            public SpeedRange(float minSpeed, float maxSpeed) {
                if (Float.compare(minSpeed, CarPropertyManager.SENSOR_RATE_ONCHANGE) < 0 || Float.compare(maxSpeed, CarPropertyManager.SENSOR_RATE_ONCHANGE) < 0) {
                    throw new IllegalArgumentException("Speed cannot be negative.");
                }
                if (minSpeed == Float.POSITIVE_INFINITY) {
                    throw new IllegalArgumentException("Min speed cannot be MAX_SPEED.");
                }
                if (minSpeed > maxSpeed) {
                    throw new IllegalArgumentException("Min speed " + minSpeed + " should not be greater than max speed " + maxSpeed);
                }
                this.mMinSpeed = minSpeed;
                this.mMaxSpeed = maxSpeed;
            }

            public boolean includes(float speed) {
                return this.mMinSpeed <= speed && speed < this.mMaxSpeed;
            }

            @Override // java.lang.Comparable
            public int compareTo(SpeedRange other) {
                int minSpeedComparison = Float.compare(this.mMinSpeed, other.mMinSpeed);
                if (minSpeedComparison != 0) {
                    return minSpeedComparison;
                }
                return Float.compare(this.mMaxSpeed, other.mMaxSpeed);
            }

            public int hashCode() {
                return Objects.hash(Float.valueOf(this.mMinSpeed), Float.valueOf(this.mMaxSpeed));
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof SpeedRange) {
                    SpeedRange other = (SpeedRange) obj;
                    return compareTo(other) == 0;
                }
                return false;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("[min: ");
                sb.append(this.mMinSpeed);
                sb.append("; max: ");
                float f = this.mMaxSpeed;
                sb.append(f == Float.POSITIVE_INFINITY ? CarUxRestrictionsConfiguration.JSON_NAME_MAX_SPEED : Float.valueOf(f));
                sb.append("]");
                return sb.toString();
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class DrivingStateRestrictions {
        private String mMode = CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE;
        private boolean mReqOpt = true;
        private int mRestrictions = CarUxRestrictions.UX_RESTRICTIONS_FULLY_RESTRICTED;
        private Builder.SpeedRange mSpeedRange;

        public DrivingStateRestrictions setDistractionOptimizationRequired(boolean distractionOptimizationRequired) {
            this.mReqOpt = distractionOptimizationRequired;
            return this;
        }

        public DrivingStateRestrictions setRestrictions(int restrictions) {
            this.mRestrictions = restrictions;
            return this;
        }

        public DrivingStateRestrictions setMode(String mode) {
            this.mMode = (String) Objects.requireNonNull(mode, "mode must not be null");
            return this;
        }

        public DrivingStateRestrictions setSpeedRange(Builder.SpeedRange speedRange) {
            this.mSpeedRange = speedRange;
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Mode: ");
            sb.append(this.mMode);
            sb.append(". Requires DO? ");
            sb.append(this.mReqOpt);
            sb.append(". Restrictions: ");
            sb.append(Integer.toBinaryString(this.mRestrictions));
            sb.append(". SpeedRange: ");
            Builder.SpeedRange speedRange = this.mSpeedRange;
            sb.append(speedRange == null ? "null" : speedRange.toString());
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class RestrictionsPerSpeedRange implements Parcelable {
        public static final Parcelable.Creator<RestrictionsPerSpeedRange> CREATOR = new Parcelable.Creator<RestrictionsPerSpeedRange>() { // from class: android.car.drivingstate.CarUxRestrictionsConfiguration.RestrictionsPerSpeedRange.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public RestrictionsPerSpeedRange createFromParcel(Parcel in) {
                return new RestrictionsPerSpeedRange(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public RestrictionsPerSpeedRange[] newArray(int size) {
                return new RestrictionsPerSpeedRange[size];
            }
        };
        final String mMode;
        final boolean mReqOpt;
        final int mRestrictions;
        final Builder.SpeedRange mSpeedRange;

        RestrictionsPerSpeedRange(boolean reqOpt, int restrictions) {
            this(CarUxRestrictionsManager.UX_RESTRICTION_MODE_BASELINE, reqOpt, restrictions, null);
        }

        RestrictionsPerSpeedRange(String mode, boolean reqOpt, int restrictions, Builder.SpeedRange speedRange) {
            if (!reqOpt && restrictions != 0) {
                throw new IllegalArgumentException("Driving optimization is not required but UX restrictions is required.");
            }
            this.mMode = (String) Objects.requireNonNull(mode, "mode must not be null");
            this.mReqOpt = reqOpt;
            this.mRestrictions = restrictions;
            this.mSpeedRange = speedRange;
        }

        public Builder.SpeedRange getSpeedRange() {
            return this.mSpeedRange;
        }

        public int hashCode() {
            return Objects.hash(this.mMode, Boolean.valueOf(this.mReqOpt), Integer.valueOf(this.mRestrictions), this.mSpeedRange);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof RestrictionsPerSpeedRange)) {
                return false;
            }
            RestrictionsPerSpeedRange other = (RestrictionsPerSpeedRange) obj;
            if (Objects.equals(this.mMode, other.mMode) && this.mReqOpt == other.mReqOpt && this.mRestrictions == other.mRestrictions && Objects.equals(this.mSpeedRange, other.mSpeedRange)) {
                return true;
            }
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[Mode is ");
            sb.append(this.mMode);
            sb.append("; Requires DO? ");
            sb.append(this.mReqOpt);
            sb.append("; Restrictions: ");
            sb.append(Integer.toBinaryString(this.mRestrictions));
            sb.append("; Speed range: ");
            Builder.SpeedRange speedRange = this.mSpeedRange;
            sb.append(speedRange == null ? "null" : speedRange.toString());
            sb.append(']');
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        protected RestrictionsPerSpeedRange(Parcel in) {
            this.mMode = in.readString();
            this.mReqOpt = in.readBoolean();
            this.mRestrictions = in.readInt();
            Builder.SpeedRange speedRange = null;
            if (in.readBoolean()) {
                float minSpeed = in.readFloat();
                float maxSpeed = in.readFloat();
                speedRange = new Builder.SpeedRange(minSpeed, maxSpeed);
            }
            this.mSpeedRange = speedRange;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.mMode);
            dest.writeBoolean(this.mReqOpt);
            dest.writeInt(this.mRestrictions);
            dest.writeBoolean(this.mSpeedRange != null);
            Builder.SpeedRange speedRange = this.mSpeedRange;
            if (speedRange != null) {
                dest.writeFloat(speedRange.mMinSpeed);
                dest.writeFloat(this.mSpeedRange.mMaxSpeed);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class RestrictionModeContainer {
        private final Map<Integer, List<RestrictionsPerSpeedRange>> mDriveStateUxRestrictions = new ArrayMap(CarUxRestrictionsConfiguration.DRIVING_STATES.length);

        RestrictionModeContainer() {
            int[] iArr;
            for (int drivingState : CarUxRestrictionsConfiguration.DRIVING_STATES) {
                this.mDriveStateUxRestrictions.put(Integer.valueOf(drivingState), new ArrayList());
            }
        }

        List<RestrictionsPerSpeedRange> getRestrictionsForDriveState(int driveState) {
            return this.mDriveStateUxRestrictions.get(Integer.valueOf(driveState));
        }

        void setRestrictionsForDriveState(int driveState, List<RestrictionsPerSpeedRange> restrictions) {
            Objects.requireNonNull(restrictions, "null restrictions are not allows");
            this.mDriveStateUxRestrictions.put(Integer.valueOf(driveState), restrictions);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RestrictionModeContainer)) {
                return false;
            }
            RestrictionModeContainer container = (RestrictionModeContainer) obj;
            return Objects.equals(this.mDriveStateUxRestrictions, container.mDriveStateUxRestrictions);
        }

        public int hashCode() {
            return Objects.hash(this.mDriveStateUxRestrictions);
        }
    }
}
