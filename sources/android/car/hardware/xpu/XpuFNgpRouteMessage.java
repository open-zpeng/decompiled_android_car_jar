package android.car.hardware.xpu;

import android.car.CarBluetoothManager;
import com.android.carsdk.protobuf.AbstractMessageLite;
import com.android.carsdk.protobuf.ByteString;
import com.android.carsdk.protobuf.CodedInputStream;
import com.android.carsdk.protobuf.CodedOutputStream;
import com.android.carsdk.protobuf.ExtensionRegistryLite;
import com.android.carsdk.protobuf.GeneratedMessageLite;
import com.android.carsdk.protobuf.Internal;
import com.android.carsdk.protobuf.InvalidProtocolBufferException;
import com.android.carsdk.protobuf.MessageLiteOrBuilder;
import com.android.carsdk.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class XpuFNgpRouteMessage {

    /* loaded from: classes.dex */
    public interface FNgpEmulatorNavigationOrBuilder extends MessageLiteOrBuilder {
        int getArriveTime();

        Point getCurrentLonlatPoint();

        int getCurrentPointIndexInV2List();

        ManeuverType getManeuverType();

        int getManeuverTypeValue();

        int getPassedMileage();

        long getPredictTimeDuration();

        int getRemainMileage();

        long getRouteId();

        boolean hasCurrentLonlatPoint();
    }

    /* loaded from: classes.dex */
    public interface FNgpRouteBriefListOrBuilder extends MessageLiteOrBuilder {
        FNgpRouteBrief getFngpRouteBriefList(int i);

        int getFngpRouteBriefListCount();

        List<FNgpRouteBrief> getFngpRouteBriefListList();
    }

    /* loaded from: classes.dex */
    public interface FNgpRouteBriefOrBuilder extends MessageLiteOrBuilder {
        Point getBeginLonlatPoint();

        long getCreateTime();

        Point getEndLonlatPoint();

        FNgpStatus getFngpStatus();

        int getFngpStatusValue();

        boolean getIsLock();

        boolean getIsNewPath();

        long getLastUseTime();

        String getName();

        ByteString getNameBytes();

        PathType getPathType();

        int getPathTypeValue();

        long getPredictTimeDuration();

        int getPriority();

        long getRouteId();

        int getTotalMileage();

        boolean hasBeginLonlatPoint();

        boolean hasEndLonlatPoint();
    }

    /* loaded from: classes.dex */
    public interface FNgpRouteDetailListOrBuilder extends MessageLiteOrBuilder {
        FNgpRouteDetail getFngpRouteDefailList(int i);

        int getFngpRouteDefailListCount();

        List<FNgpRouteDetail> getFngpRouteDefailListList();
    }

    /* loaded from: classes.dex */
    public interface FNgpRouteDetailOrBuilder extends MessageLiteOrBuilder {
        FNgpRouteBrief getFngpRouteBrief();

        SegmentInfo getSegmentList(int i);

        int getSegmentListCount();

        List<SegmentInfo> getSegmentListList();

        Point getV2PointList(int i);

        int getV2PointListCount();

        List<Point> getV2PointListList();

        boolean hasFngpRouteBrief();
    }

    /* loaded from: classes.dex */
    public interface FNgpRouteWayPointsSpecifiedOrBuilder extends MessageLiteOrBuilder {
        Point getV2PointListSpecified(int i);

        int getV2PointListSpecifiedCount();

        List<Point> getV2PointListSpecifiedList();
    }

    /* loaded from: classes.dex */
    public interface PointOrBuilder extends MessageLiteOrBuilder {
        double getX();

        double getY();

        double getZ();
    }

    /* loaded from: classes.dex */
    public interface SegmentInfoOrBuilder extends MessageLiteOrBuilder {
        int getBeginIndex();

        int getDesc();

        int getEndIndex();

        int getMileage();

        SegmentAvailableStatus getStatus();

        int getStatusValue();
    }

    private XpuFNgpRouteMessage() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    /* loaded from: classes.dex */
    public enum SegmentAvailableStatus implements Internal.EnumLite {
        AVAILABLE(0),
        NOT_AVAILABLE(1),
        UNRECOGNIZED(-1);
        
        public static final int AVAILABLE_VALUE = 0;
        public static final int NOT_AVAILABLE_VALUE = 1;
        private static final Internal.EnumLiteMap<SegmentAvailableStatus> internalValueMap = new Internal.EnumLiteMap<SegmentAvailableStatus>() { // from class: android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentAvailableStatus.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public SegmentAvailableStatus findValueByNumber(int number) {
                return SegmentAvailableStatus.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static SegmentAvailableStatus valueOf(int value) {
            return forNumber(value);
        }

        public static SegmentAvailableStatus forNumber(int value) {
            switch (value) {
                case 0:
                    return AVAILABLE;
                case 1:
                    return NOT_AVAILABLE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<SegmentAvailableStatus> internalGetValueMap() {
            return internalValueMap;
        }

        SegmentAvailableStatus(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public enum PathType implements Internal.EnumLite {
        RESERVED(0),
        WORKING_DAY_PATH(1),
        WEEKEND_DAY_PATH(2),
        CUSTOMIZE(3),
        UNRECOGNIZED(-1);
        
        public static final int CUSTOMIZE_VALUE = 3;
        public static final int RESERVED_VALUE = 0;
        public static final int WEEKEND_DAY_PATH_VALUE = 2;
        public static final int WORKING_DAY_PATH_VALUE = 1;
        private static final Internal.EnumLiteMap<PathType> internalValueMap = new Internal.EnumLiteMap<PathType>() { // from class: android.car.hardware.xpu.XpuFNgpRouteMessage.PathType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public PathType findValueByNumber(int number) {
                return PathType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static PathType valueOf(int value) {
            return forNumber(value);
        }

        public static PathType forNumber(int value) {
            switch (value) {
                case 0:
                    return RESERVED;
                case 1:
                    return WORKING_DAY_PATH;
                case 2:
                    return WEEKEND_DAY_PATH;
                case 3:
                    return CUSTOMIZE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<PathType> internalGetValueMap() {
            return internalValueMap;
        }

        PathType(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public enum ManeuverType implements Internal.EnumLite {
        TURN_LEFT_2km(0),
        TURN_LEFT_1km(1),
        TURN_LEFT_500m(2),
        TURN_LEFT_200m(3),
        TURN_LEFT_NOW(4),
        TURN_RIGHT_2km(5),
        TURN_RIGHT_1km(6),
        TURN_RIGHT_500m(7),
        TURN_RIGHT_200m(8),
        TURN_RIGHT_NOW(9),
        TURN_BACK_2km(10),
        TURN_BACK_1km(11),
        TURN_BACK_500m(12),
        TURN_BACK_200m(13),
        TURN_BACK_NOW(14),
        STRAIGHT_2km(15),
        STRAIGHT_1km(16),
        STRAIGHT_500m(17),
        STRAIGHT_200m(18),
        STRAIGHT_NOW(19),
        PASS_ROUNDABOUT_2km(20),
        PASS_ROUNDABOUT_1km(21),
        PASS_ROUNDABOUT_500m(22),
        PASS_ROUNDABOUT_200m(23),
        PASS_ROUNDABOUT_NOW(24),
        UNRECOGNIZED(-1);
        
        public static final int PASS_ROUNDABOUT_1km_VALUE = 21;
        public static final int PASS_ROUNDABOUT_200m_VALUE = 23;
        public static final int PASS_ROUNDABOUT_2km_VALUE = 20;
        public static final int PASS_ROUNDABOUT_500m_VALUE = 22;
        public static final int PASS_ROUNDABOUT_NOW_VALUE = 24;
        public static final int STRAIGHT_1km_VALUE = 16;
        public static final int STRAIGHT_200m_VALUE = 18;
        public static final int STRAIGHT_2km_VALUE = 15;
        public static final int STRAIGHT_500m_VALUE = 17;
        public static final int STRAIGHT_NOW_VALUE = 19;
        public static final int TURN_BACK_1km_VALUE = 11;
        public static final int TURN_BACK_200m_VALUE = 13;
        public static final int TURN_BACK_2km_VALUE = 10;
        public static final int TURN_BACK_500m_VALUE = 12;
        public static final int TURN_BACK_NOW_VALUE = 14;
        public static final int TURN_LEFT_1km_VALUE = 1;
        public static final int TURN_LEFT_200m_VALUE = 3;
        public static final int TURN_LEFT_2km_VALUE = 0;
        public static final int TURN_LEFT_500m_VALUE = 2;
        public static final int TURN_LEFT_NOW_VALUE = 4;
        public static final int TURN_RIGHT_1km_VALUE = 6;
        public static final int TURN_RIGHT_200m_VALUE = 8;
        public static final int TURN_RIGHT_2km_VALUE = 5;
        public static final int TURN_RIGHT_500m_VALUE = 7;
        public static final int TURN_RIGHT_NOW_VALUE = 9;
        private static final Internal.EnumLiteMap<ManeuverType> internalValueMap = new Internal.EnumLiteMap<ManeuverType>() { // from class: android.car.hardware.xpu.XpuFNgpRouteMessage.ManeuverType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public ManeuverType findValueByNumber(int number) {
                return ManeuverType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ManeuverType valueOf(int value) {
            return forNumber(value);
        }

        public static ManeuverType forNumber(int value) {
            switch (value) {
                case 0:
                    return TURN_LEFT_2km;
                case 1:
                    return TURN_LEFT_1km;
                case 2:
                    return TURN_LEFT_500m;
                case 3:
                    return TURN_LEFT_200m;
                case 4:
                    return TURN_LEFT_NOW;
                case 5:
                    return TURN_RIGHT_2km;
                case 6:
                    return TURN_RIGHT_1km;
                case 7:
                    return TURN_RIGHT_500m;
                case 8:
                    return TURN_RIGHT_200m;
                case 9:
                    return TURN_RIGHT_NOW;
                case 10:
                    return TURN_BACK_2km;
                case 11:
                    return TURN_BACK_1km;
                case 12:
                    return TURN_BACK_500m;
                case 13:
                    return TURN_BACK_200m;
                case 14:
                    return TURN_BACK_NOW;
                case 15:
                    return STRAIGHT_2km;
                case 16:
                    return STRAIGHT_1km;
                case 17:
                    return STRAIGHT_500m;
                case 18:
                    return STRAIGHT_200m;
                case 19:
                    return STRAIGHT_NOW;
                case 20:
                    return PASS_ROUNDABOUT_2km;
                case 21:
                    return PASS_ROUNDABOUT_1km;
                case 22:
                    return PASS_ROUNDABOUT_500m;
                case 23:
                    return PASS_ROUNDABOUT_200m;
                case 24:
                    return PASS_ROUNDABOUT_NOW;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ManeuverType> internalGetValueMap() {
            return internalValueMap;
        }

        ManeuverType(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public enum FNgpStatus implements Internal.EnumLite {
        FNGP_STATUS_NOT_AVAILABLE(0),
        FNGP_STATUS_OUT_OF_RANGE(1),
        FNGP_STATUS_IN_RANGE(2),
        FNGP_STATUS_DRIVING(3),
        FNGP_STATUS_GRAY_WAY(4),
        FNGP_STATUS_DRIVING_END(5),
        UNRECOGNIZED(-1);
        
        public static final int FNGP_STATUS_DRIVING_END_VALUE = 5;
        public static final int FNGP_STATUS_DRIVING_VALUE = 3;
        public static final int FNGP_STATUS_GRAY_WAY_VALUE = 4;
        public static final int FNGP_STATUS_IN_RANGE_VALUE = 2;
        public static final int FNGP_STATUS_NOT_AVAILABLE_VALUE = 0;
        public static final int FNGP_STATUS_OUT_OF_RANGE_VALUE = 1;
        private static final Internal.EnumLiteMap<FNgpStatus> internalValueMap = new Internal.EnumLiteMap<FNgpStatus>() { // from class: android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpStatus.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public FNgpStatus findValueByNumber(int number) {
                return FNgpStatus.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static FNgpStatus valueOf(int value) {
            return forNumber(value);
        }

        public static FNgpStatus forNumber(int value) {
            switch (value) {
                case 0:
                    return FNGP_STATUS_NOT_AVAILABLE;
                case 1:
                    return FNGP_STATUS_OUT_OF_RANGE;
                case 2:
                    return FNGP_STATUS_IN_RANGE;
                case 3:
                    return FNGP_STATUS_DRIVING;
                case 4:
                    return FNGP_STATUS_GRAY_WAY;
                case 5:
                    return FNGP_STATUS_DRIVING_END;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<FNgpStatus> internalGetValueMap() {
            return internalValueMap;
        }

        FNgpStatus(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public static final class Point extends GeneratedMessageLite<Point, Builder> implements PointOrBuilder {
        private static final Point DEFAULT_INSTANCE = new Point();
        private static volatile Parser<Point> PARSER = null;
        public static final int X_FIELD_NUMBER = 1;
        public static final int Y_FIELD_NUMBER = 2;
        public static final int Z_FIELD_NUMBER = 3;
        private double x_ = 0.0d;
        private double y_ = 0.0d;
        private double z_ = 0.0d;

        private Point() {
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.PointOrBuilder
        public double getX() {
            return this.x_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setX(double value) {
            this.x_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearX() {
            this.x_ = 0.0d;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.PointOrBuilder
        public double getY() {
            return this.y_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setY(double value) {
            this.y_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearY() {
            this.y_ = 0.0d;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.PointOrBuilder
        public double getZ() {
            return this.z_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setZ(double value) {
            this.z_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearZ() {
            this.z_ = 0.0d;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.x_ != 0.0d) {
                output.writeDouble(1, this.x_);
            }
            if (this.y_ != 0.0d) {
                output.writeDouble(2, this.y_);
            }
            if (this.z_ != 0.0d) {
                output.writeDouble(3, this.z_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.x_ != 0.0d ? 0 + CodedOutputStream.computeDoubleSize(1, this.x_) : 0;
            if (this.y_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(2, this.y_);
            }
            if (this.z_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(3, this.z_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static Point parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Point parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Point parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Point parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Point parseFrom(InputStream input) throws IOException {
            return (Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Point parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Point parseDelimitedFrom(InputStream input) throws IOException {
            return (Point) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Point parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Point) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Point parseFrom(CodedInputStream input) throws IOException {
            return (Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Point parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Point prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Point, Builder> implements PointOrBuilder {
            private Builder() {
                super(Point.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.PointOrBuilder
            public double getX() {
                return ((Point) this.instance).getX();
            }

            public Builder setX(double value) {
                copyOnWrite();
                ((Point) this.instance).setX(value);
                return this;
            }

            public Builder clearX() {
                copyOnWrite();
                ((Point) this.instance).clearX();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.PointOrBuilder
            public double getY() {
                return ((Point) this.instance).getY();
            }

            public Builder setY(double value) {
                copyOnWrite();
                ((Point) this.instance).setY(value);
                return this;
            }

            public Builder clearY() {
                copyOnWrite();
                ((Point) this.instance).clearY();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.PointOrBuilder
            public double getZ() {
                return ((Point) this.instance).getZ();
            }

            public Builder setZ(double value) {
                copyOnWrite();
                ((Point) this.instance).setZ(value);
                return this;
            }

            public Builder clearZ() {
                copyOnWrite();
                ((Point) this.instance).clearZ();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Point();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Point other = (Point) arg1;
                    this.x_ = visitor.visitDouble(this.x_ != 0.0d, this.x_, other.x_ != 0.0d, other.x_);
                    this.y_ = visitor.visitDouble(this.y_ != 0.0d, this.y_, other.y_ != 0.0d, other.y_);
                    this.z_ = visitor.visitDouble(this.z_ != 0.0d, this.z_, other.z_ != 0.0d, other.z_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                    while (true) {
                        boolean done2 = done;
                        if (done2) {
                            break;
                        } else {
                            try {
                                try {
                                    int tag = input.readTag();
                                    if (tag == 0) {
                                        done2 = true;
                                    } else if (tag == 9) {
                                        this.x_ = input.readDouble();
                                    } else if (tag == 17) {
                                        this.y_ = input.readDouble();
                                    } else if (tag != 25) {
                                        if (!input.skipField(tag)) {
                                            done2 = true;
                                        }
                                    } else {
                                        this.z_ = input.readDouble();
                                    }
                                    done = done2;
                                } catch (InvalidProtocolBufferException e) {
                                    throw new RuntimeException(e.setUnfinishedMessage(this));
                                }
                            } catch (IOException e2) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                            }
                        }
                    }
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (Point.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static Point getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Point> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class SegmentInfo extends GeneratedMessageLite<SegmentInfo, Builder> implements SegmentInfoOrBuilder {
        public static final int BEGIN_INDEX_FIELD_NUMBER = 3;
        private static final SegmentInfo DEFAULT_INSTANCE = new SegmentInfo();
        public static final int DESC_FIELD_NUMBER = 5;
        public static final int END_INDEX_FIELD_NUMBER = 4;
        public static final int MILEAGE_FIELD_NUMBER = 1;
        private static volatile Parser<SegmentInfo> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        private int mileage_ = 0;
        private int status_ = 0;
        private int beginIndex_ = 0;
        private int endIndex_ = 0;
        private int desc_ = 0;

        private SegmentInfo() {
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
        public int getMileage() {
            return this.mileage_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMileage(int value) {
            this.mileage_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMileage() {
            this.mileage_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
        public int getStatusValue() {
            return this.status_;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
        public SegmentAvailableStatus getStatus() {
            SegmentAvailableStatus result = SegmentAvailableStatus.forNumber(this.status_);
            return result == null ? SegmentAvailableStatus.UNRECOGNIZED : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatusValue(int value) {
            this.status_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(SegmentAvailableStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.status_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.status_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
        public int getBeginIndex() {
            return this.beginIndex_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBeginIndex(int value) {
            this.beginIndex_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBeginIndex() {
            this.beginIndex_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
        public int getEndIndex() {
            return this.endIndex_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndIndex(int value) {
            this.endIndex_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEndIndex() {
            this.endIndex_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
        public int getDesc() {
            return this.desc_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDesc(int value) {
            this.desc_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDesc() {
            this.desc_ = 0;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.mileage_ != 0) {
                output.writeInt32(1, this.mileage_);
            }
            if (this.status_ != SegmentAvailableStatus.AVAILABLE.getNumber()) {
                output.writeEnum(2, this.status_);
            }
            if (this.beginIndex_ != 0) {
                output.writeInt32(3, this.beginIndex_);
            }
            if (this.endIndex_ != 0) {
                output.writeInt32(4, this.endIndex_);
            }
            if (this.desc_ != 0) {
                output.writeInt32(5, this.desc_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.mileage_ != 0 ? 0 + CodedOutputStream.computeInt32Size(1, this.mileage_) : 0;
            if (this.status_ != SegmentAvailableStatus.AVAILABLE.getNumber()) {
                size2 += CodedOutputStream.computeEnumSize(2, this.status_);
            }
            if (this.beginIndex_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(3, this.beginIndex_);
            }
            if (this.endIndex_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(4, this.endIndex_);
            }
            if (this.desc_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(5, this.desc_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static SegmentInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SegmentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SegmentInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SegmentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SegmentInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SegmentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SegmentInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SegmentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SegmentInfo parseFrom(InputStream input) throws IOException {
            return (SegmentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SegmentInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SegmentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SegmentInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (SegmentInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SegmentInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SegmentInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SegmentInfo parseFrom(CodedInputStream input) throws IOException {
            return (SegmentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SegmentInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SegmentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SegmentInfo prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SegmentInfo, Builder> implements SegmentInfoOrBuilder {
            private Builder() {
                super(SegmentInfo.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
            public int getMileage() {
                return ((SegmentInfo) this.instance).getMileage();
            }

            public Builder setMileage(int value) {
                copyOnWrite();
                ((SegmentInfo) this.instance).setMileage(value);
                return this;
            }

            public Builder clearMileage() {
                copyOnWrite();
                ((SegmentInfo) this.instance).clearMileage();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
            public int getStatusValue() {
                return ((SegmentInfo) this.instance).getStatusValue();
            }

            public Builder setStatusValue(int value) {
                copyOnWrite();
                ((SegmentInfo) this.instance).setStatusValue(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
            public SegmentAvailableStatus getStatus() {
                return ((SegmentInfo) this.instance).getStatus();
            }

            public Builder setStatus(SegmentAvailableStatus value) {
                copyOnWrite();
                ((SegmentInfo) this.instance).setStatus(value);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((SegmentInfo) this.instance).clearStatus();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
            public int getBeginIndex() {
                return ((SegmentInfo) this.instance).getBeginIndex();
            }

            public Builder setBeginIndex(int value) {
                copyOnWrite();
                ((SegmentInfo) this.instance).setBeginIndex(value);
                return this;
            }

            public Builder clearBeginIndex() {
                copyOnWrite();
                ((SegmentInfo) this.instance).clearBeginIndex();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
            public int getEndIndex() {
                return ((SegmentInfo) this.instance).getEndIndex();
            }

            public Builder setEndIndex(int value) {
                copyOnWrite();
                ((SegmentInfo) this.instance).setEndIndex(value);
                return this;
            }

            public Builder clearEndIndex() {
                copyOnWrite();
                ((SegmentInfo) this.instance).clearEndIndex();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.SegmentInfoOrBuilder
            public int getDesc() {
                return ((SegmentInfo) this.instance).getDesc();
            }

            public Builder setDesc(int value) {
                copyOnWrite();
                ((SegmentInfo) this.instance).setDesc(value);
                return this;
            }

            public Builder clearDesc() {
                copyOnWrite();
                ((SegmentInfo) this.instance).clearDesc();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new SegmentInfo();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    SegmentInfo other = (SegmentInfo) arg1;
                    this.mileage_ = visitor.visitInt(this.mileage_ != 0, this.mileage_, other.mileage_ != 0, other.mileage_);
                    this.status_ = visitor.visitInt(this.status_ != 0, this.status_, other.status_ != 0, other.status_);
                    this.beginIndex_ = visitor.visitInt(this.beginIndex_ != 0, this.beginIndex_, other.beginIndex_ != 0, other.beginIndex_);
                    this.endIndex_ = visitor.visitInt(this.endIndex_ != 0, this.endIndex_, other.endIndex_ != 0, other.endIndex_);
                    boolean z = this.desc_ != 0;
                    int i = this.desc_;
                    done = other.desc_ != 0;
                    this.desc_ = visitor.visitInt(z, i, done, other.desc_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                    while (!done) {
                        try {
                            try {
                                int tag = input.readTag();
                                if (tag == 0) {
                                    done = true;
                                } else if (tag == 8) {
                                    int rawValue = input.readInt32();
                                    this.mileage_ = rawValue;
                                } else if (tag == 16) {
                                    int rawValue2 = input.readEnum();
                                    this.status_ = rawValue2;
                                } else if (tag == 24) {
                                    this.beginIndex_ = input.readInt32();
                                } else if (tag == 32) {
                                    this.endIndex_ = input.readInt32();
                                } else if (tag != 40) {
                                    if (!input.skipField(tag)) {
                                        done = true;
                                    }
                                } else {
                                    this.desc_ = input.readInt32();
                                }
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            }
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (SegmentInfo.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static SegmentInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SegmentInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class FNgpRouteBrief extends GeneratedMessageLite<FNgpRouteBrief, Builder> implements FNgpRouteBriefOrBuilder {
        public static final int BEGIN_LONLAT_POINT_FIELD_NUMBER = 3;
        public static final int CREATE_TIME_FIELD_NUMBER = 6;
        private static final FNgpRouteBrief DEFAULT_INSTANCE = new FNgpRouteBrief();
        public static final int END_LONLAT_POINT_FIELD_NUMBER = 4;
        public static final int FNGP_STATUS_FIELD_NUMBER = 10;
        public static final int IS_LOCK_FIELD_NUMBER = 9;
        public static final int IS_NEW_PATH_FIELD_NUMBER = 7;
        public static final int LAST_USE_TIME_FIELD_NUMBER = 8;
        public static final int NAME_FIELD_NUMBER = 13;
        private static volatile Parser<FNgpRouteBrief> PARSER = null;
        public static final int PATH_TYPE_FIELD_NUMBER = 11;
        public static final int PREDICT_TIME_DURATION_FIELD_NUMBER = 5;
        public static final int PRIORITY_FIELD_NUMBER = 12;
        public static final int ROUTE_ID_FIELD_NUMBER = 1;
        public static final int TOTAL_MILEAGE_FIELD_NUMBER = 2;
        private Point beginLonlatPoint_;
        private Point endLonlatPoint_;
        private long routeId_ = 0;
        private int totalMileage_ = 0;
        private long predictTimeDuration_ = 0;
        private long createTime_ = 0;
        private boolean isNewPath_ = false;
        private long lastUseTime_ = 0;
        private boolean isLock_ = false;
        private int fngpStatus_ = 0;
        private int pathType_ = 0;
        private int priority_ = 0;
        private String name_ = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;

        private FNgpRouteBrief() {
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public long getRouteId() {
            return this.routeId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRouteId(long value) {
            this.routeId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRouteId() {
            this.routeId_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public int getTotalMileage() {
            return this.totalMileage_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTotalMileage(int value) {
            this.totalMileage_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTotalMileage() {
            this.totalMileage_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public boolean hasBeginLonlatPoint() {
            return this.beginLonlatPoint_ != null;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public Point getBeginLonlatPoint() {
            return this.beginLonlatPoint_ == null ? Point.getDefaultInstance() : this.beginLonlatPoint_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBeginLonlatPoint(Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.beginLonlatPoint_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBeginLonlatPoint(Point.Builder builderForValue) {
            this.beginLonlatPoint_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeBeginLonlatPoint(Point value) {
            if (this.beginLonlatPoint_ != null && this.beginLonlatPoint_ != Point.getDefaultInstance()) {
                this.beginLonlatPoint_ = Point.newBuilder(this.beginLonlatPoint_).mergeFrom((Point.Builder) value).buildPartial();
            } else {
                this.beginLonlatPoint_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBeginLonlatPoint() {
            this.beginLonlatPoint_ = null;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public boolean hasEndLonlatPoint() {
            return this.endLonlatPoint_ != null;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public Point getEndLonlatPoint() {
            return this.endLonlatPoint_ == null ? Point.getDefaultInstance() : this.endLonlatPoint_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndLonlatPoint(Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.endLonlatPoint_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndLonlatPoint(Point.Builder builderForValue) {
            this.endLonlatPoint_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeEndLonlatPoint(Point value) {
            if (this.endLonlatPoint_ != null && this.endLonlatPoint_ != Point.getDefaultInstance()) {
                this.endLonlatPoint_ = Point.newBuilder(this.endLonlatPoint_).mergeFrom((Point.Builder) value).buildPartial();
            } else {
                this.endLonlatPoint_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEndLonlatPoint() {
            this.endLonlatPoint_ = null;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public long getPredictTimeDuration() {
            return this.predictTimeDuration_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPredictTimeDuration(long value) {
            this.predictTimeDuration_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPredictTimeDuration() {
            this.predictTimeDuration_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public long getCreateTime() {
            return this.createTime_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCreateTime(long value) {
            this.createTime_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCreateTime() {
            this.createTime_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public boolean getIsNewPath() {
            return this.isNewPath_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsNewPath(boolean value) {
            this.isNewPath_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsNewPath() {
            this.isNewPath_ = false;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public long getLastUseTime() {
            return this.lastUseTime_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLastUseTime(long value) {
            this.lastUseTime_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLastUseTime() {
            this.lastUseTime_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public boolean getIsLock() {
            return this.isLock_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsLock(boolean value) {
            this.isLock_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsLock() {
            this.isLock_ = false;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public int getFngpStatusValue() {
            return this.fngpStatus_;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public FNgpStatus getFngpStatus() {
            FNgpStatus result = FNgpStatus.forNumber(this.fngpStatus_);
            return result == null ? FNgpStatus.UNRECOGNIZED : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFngpStatusValue(int value) {
            this.fngpStatus_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFngpStatus(FNgpStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.fngpStatus_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFngpStatus() {
            this.fngpStatus_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public int getPathTypeValue() {
            return this.pathType_;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public PathType getPathType() {
            PathType result = PathType.forNumber(this.pathType_);
            return result == null ? PathType.UNRECOGNIZED : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPathTypeValue(int value) {
            this.pathType_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPathType(PathType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.pathType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPathType() {
            this.pathType_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public int getPriority() {
            return this.priority_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPriority(int value) {
            this.priority_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPriority() {
            this.priority_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.name_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            this.name_ = value.toStringUtf8();
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.routeId_ != 0) {
                output.writeInt64(1, this.routeId_);
            }
            if (this.totalMileage_ != 0) {
                output.writeInt32(2, this.totalMileage_);
            }
            if (this.beginLonlatPoint_ != null) {
                output.writeMessage(3, getBeginLonlatPoint());
            }
            if (this.endLonlatPoint_ != null) {
                output.writeMessage(4, getEndLonlatPoint());
            }
            if (this.predictTimeDuration_ != 0) {
                output.writeInt64(5, this.predictTimeDuration_);
            }
            if (this.createTime_ != 0) {
                output.writeInt64(6, this.createTime_);
            }
            if (this.isNewPath_) {
                output.writeBool(7, this.isNewPath_);
            }
            if (this.lastUseTime_ != 0) {
                output.writeInt64(8, this.lastUseTime_);
            }
            if (this.isLock_) {
                output.writeBool(9, this.isLock_);
            }
            if (this.fngpStatus_ != FNgpStatus.FNGP_STATUS_NOT_AVAILABLE.getNumber()) {
                output.writeEnum(10, this.fngpStatus_);
            }
            if (this.pathType_ != PathType.RESERVED.getNumber()) {
                output.writeEnum(11, this.pathType_);
            }
            if (this.priority_ != 0) {
                output.writeInt32(12, this.priority_);
            }
            if (!this.name_.isEmpty()) {
                output.writeString(13, getName());
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.routeId_ != 0 ? 0 + CodedOutputStream.computeInt64Size(1, this.routeId_) : 0;
            if (this.totalMileage_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(2, this.totalMileage_);
            }
            if (this.beginLonlatPoint_ != null) {
                size2 += CodedOutputStream.computeMessageSize(3, getBeginLonlatPoint());
            }
            if (this.endLonlatPoint_ != null) {
                size2 += CodedOutputStream.computeMessageSize(4, getEndLonlatPoint());
            }
            if (this.predictTimeDuration_ != 0) {
                size2 += CodedOutputStream.computeInt64Size(5, this.predictTimeDuration_);
            }
            if (this.createTime_ != 0) {
                size2 += CodedOutputStream.computeInt64Size(6, this.createTime_);
            }
            if (this.isNewPath_) {
                size2 += CodedOutputStream.computeBoolSize(7, this.isNewPath_);
            }
            if (this.lastUseTime_ != 0) {
                size2 += CodedOutputStream.computeInt64Size(8, this.lastUseTime_);
            }
            if (this.isLock_) {
                size2 += CodedOutputStream.computeBoolSize(9, this.isLock_);
            }
            if (this.fngpStatus_ != FNgpStatus.FNGP_STATUS_NOT_AVAILABLE.getNumber()) {
                size2 += CodedOutputStream.computeEnumSize(10, this.fngpStatus_);
            }
            if (this.pathType_ != PathType.RESERVED.getNumber()) {
                size2 += CodedOutputStream.computeEnumSize(11, this.pathType_);
            }
            if (this.priority_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(12, this.priority_);
            }
            if (!this.name_.isEmpty()) {
                size2 += CodedOutputStream.computeStringSize(13, getName());
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static FNgpRouteBrief parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FNgpRouteBrief) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpRouteBrief parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpRouteBrief) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpRouteBrief parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FNgpRouteBrief) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpRouteBrief parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpRouteBrief) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpRouteBrief parseFrom(InputStream input) throws IOException {
            return (FNgpRouteBrief) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteBrief parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteBrief) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpRouteBrief parseDelimitedFrom(InputStream input) throws IOException {
            return (FNgpRouteBrief) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteBrief parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteBrief) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpRouteBrief parseFrom(CodedInputStream input) throws IOException {
            return (FNgpRouteBrief) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteBrief parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteBrief) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FNgpRouteBrief prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FNgpRouteBrief, Builder> implements FNgpRouteBriefOrBuilder {
            private Builder() {
                super(FNgpRouteBrief.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public long getRouteId() {
                return ((FNgpRouteBrief) this.instance).getRouteId();
            }

            public Builder setRouteId(long value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setRouteId(value);
                return this;
            }

            public Builder clearRouteId() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearRouteId();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public int getTotalMileage() {
                return ((FNgpRouteBrief) this.instance).getTotalMileage();
            }

            public Builder setTotalMileage(int value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setTotalMileage(value);
                return this;
            }

            public Builder clearTotalMileage() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearTotalMileage();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public boolean hasBeginLonlatPoint() {
                return ((FNgpRouteBrief) this.instance).hasBeginLonlatPoint();
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public Point getBeginLonlatPoint() {
                return ((FNgpRouteBrief) this.instance).getBeginLonlatPoint();
            }

            public Builder setBeginLonlatPoint(Point value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setBeginLonlatPoint(value);
                return this;
            }

            public Builder setBeginLonlatPoint(Point.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setBeginLonlatPoint(builderForValue);
                return this;
            }

            public Builder mergeBeginLonlatPoint(Point value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).mergeBeginLonlatPoint(value);
                return this;
            }

            public Builder clearBeginLonlatPoint() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearBeginLonlatPoint();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public boolean hasEndLonlatPoint() {
                return ((FNgpRouteBrief) this.instance).hasEndLonlatPoint();
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public Point getEndLonlatPoint() {
                return ((FNgpRouteBrief) this.instance).getEndLonlatPoint();
            }

            public Builder setEndLonlatPoint(Point value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setEndLonlatPoint(value);
                return this;
            }

            public Builder setEndLonlatPoint(Point.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setEndLonlatPoint(builderForValue);
                return this;
            }

            public Builder mergeEndLonlatPoint(Point value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).mergeEndLonlatPoint(value);
                return this;
            }

            public Builder clearEndLonlatPoint() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearEndLonlatPoint();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public long getPredictTimeDuration() {
                return ((FNgpRouteBrief) this.instance).getPredictTimeDuration();
            }

            public Builder setPredictTimeDuration(long value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setPredictTimeDuration(value);
                return this;
            }

            public Builder clearPredictTimeDuration() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearPredictTimeDuration();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public long getCreateTime() {
                return ((FNgpRouteBrief) this.instance).getCreateTime();
            }

            public Builder setCreateTime(long value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setCreateTime(value);
                return this;
            }

            public Builder clearCreateTime() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearCreateTime();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public boolean getIsNewPath() {
                return ((FNgpRouteBrief) this.instance).getIsNewPath();
            }

            public Builder setIsNewPath(boolean value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setIsNewPath(value);
                return this;
            }

            public Builder clearIsNewPath() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearIsNewPath();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public long getLastUseTime() {
                return ((FNgpRouteBrief) this.instance).getLastUseTime();
            }

            public Builder setLastUseTime(long value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setLastUseTime(value);
                return this;
            }

            public Builder clearLastUseTime() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearLastUseTime();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public boolean getIsLock() {
                return ((FNgpRouteBrief) this.instance).getIsLock();
            }

            public Builder setIsLock(boolean value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setIsLock(value);
                return this;
            }

            public Builder clearIsLock() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearIsLock();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public int getFngpStatusValue() {
                return ((FNgpRouteBrief) this.instance).getFngpStatusValue();
            }

            public Builder setFngpStatusValue(int value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setFngpStatusValue(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public FNgpStatus getFngpStatus() {
                return ((FNgpRouteBrief) this.instance).getFngpStatus();
            }

            public Builder setFngpStatus(FNgpStatus value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setFngpStatus(value);
                return this;
            }

            public Builder clearFngpStatus() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearFngpStatus();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public int getPathTypeValue() {
                return ((FNgpRouteBrief) this.instance).getPathTypeValue();
            }

            public Builder setPathTypeValue(int value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setPathTypeValue(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public PathType getPathType() {
                return ((FNgpRouteBrief) this.instance).getPathType();
            }

            public Builder setPathType(PathType value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setPathType(value);
                return this;
            }

            public Builder clearPathType() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearPathType();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public int getPriority() {
                return ((FNgpRouteBrief) this.instance).getPriority();
            }

            public Builder setPriority(int value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setPriority(value);
                return this;
            }

            public Builder clearPriority() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearPriority();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public String getName() {
                return ((FNgpRouteBrief) this.instance).getName();
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefOrBuilder
            public ByteString getNameBytes() {
                return ((FNgpRouteBrief) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((FNgpRouteBrief) this.instance).setNameBytes(value);
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new FNgpRouteBrief();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    FNgpRouteBrief other = (FNgpRouteBrief) arg1;
                    this.routeId_ = visitor.visitLong(this.routeId_ != 0, this.routeId_, other.routeId_ != 0, other.routeId_);
                    this.totalMileage_ = visitor.visitInt(this.totalMileage_ != 0, this.totalMileage_, other.totalMileage_ != 0, other.totalMileage_);
                    this.beginLonlatPoint_ = (Point) visitor.visitMessage(this.beginLonlatPoint_, other.beginLonlatPoint_);
                    this.endLonlatPoint_ = (Point) visitor.visitMessage(this.endLonlatPoint_, other.endLonlatPoint_);
                    this.predictTimeDuration_ = visitor.visitLong(this.predictTimeDuration_ != 0, this.predictTimeDuration_, other.predictTimeDuration_ != 0, other.predictTimeDuration_);
                    this.createTime_ = visitor.visitLong(this.createTime_ != 0, this.createTime_, other.createTime_ != 0, other.createTime_);
                    this.isNewPath_ = visitor.visitBoolean(this.isNewPath_, this.isNewPath_, other.isNewPath_, other.isNewPath_);
                    this.lastUseTime_ = visitor.visitLong(this.lastUseTime_ != 0, this.lastUseTime_, other.lastUseTime_ != 0, other.lastUseTime_);
                    this.isLock_ = visitor.visitBoolean(this.isLock_, this.isLock_, other.isLock_, other.isLock_);
                    this.fngpStatus_ = visitor.visitInt(this.fngpStatus_ != 0, this.fngpStatus_, other.fngpStatus_ != 0, other.fngpStatus_);
                    this.pathType_ = visitor.visitInt(this.pathType_ != 0, this.pathType_, other.pathType_ != 0, other.pathType_);
                    this.priority_ = visitor.visitInt(this.priority_ != 0, this.priority_, other.priority_ != 0, other.priority_);
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !other.name_.isEmpty(), other.name_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    while (true) {
                        boolean done = done;
                        if (done) {
                            break;
                        } else {
                            try {
                                int tag = input.readTag();
                                switch (tag) {
                                    case 0:
                                        done = true;
                                        break;
                                    case 8:
                                        this.routeId_ = input.readInt64();
                                        break;
                                    case 16:
                                        this.totalMileage_ = input.readInt32();
                                        break;
                                    case 26:
                                        Point.Builder subBuilder = null;
                                        if (this.beginLonlatPoint_ != null) {
                                            subBuilder = this.beginLonlatPoint_.toBuilder();
                                        }
                                        this.beginLonlatPoint_ = (Point) input.readMessage(Point.parser(), extensionRegistry);
                                        if (subBuilder == null) {
                                            break;
                                        } else {
                                            subBuilder.mergeFrom((Point.Builder) this.beginLonlatPoint_);
                                            this.beginLonlatPoint_ = (Point) subBuilder.buildPartial();
                                            break;
                                        }
                                    case 34:
                                        Point.Builder subBuilder2 = null;
                                        if (this.endLonlatPoint_ != null) {
                                            subBuilder2 = this.endLonlatPoint_.toBuilder();
                                        }
                                        this.endLonlatPoint_ = (Point) input.readMessage(Point.parser(), extensionRegistry);
                                        if (subBuilder2 == null) {
                                            break;
                                        } else {
                                            subBuilder2.mergeFrom((Point.Builder) this.endLonlatPoint_);
                                            this.endLonlatPoint_ = (Point) subBuilder2.buildPartial();
                                            break;
                                        }
                                    case 40:
                                        this.predictTimeDuration_ = input.readInt64();
                                        break;
                                    case 48:
                                        this.createTime_ = input.readInt64();
                                        break;
                                    case 56:
                                        this.isNewPath_ = input.readBool();
                                        break;
                                    case 64:
                                        this.lastUseTime_ = input.readInt64();
                                        break;
                                    case 72:
                                        this.isLock_ = input.readBool();
                                        break;
                                    case 80:
                                        int rawValue = input.readEnum();
                                        this.fngpStatus_ = rawValue;
                                        break;
                                    case 88:
                                        int rawValue2 = input.readEnum();
                                        this.pathType_ = rawValue2;
                                        break;
                                    case 96:
                                        this.priority_ = input.readInt32();
                                        break;
                                    case 106:
                                        String s = input.readStringRequireUtf8();
                                        this.name_ = s;
                                        break;
                                    default:
                                        if (!input.skipField(tag)) {
                                            done = true;
                                            break;
                                        } else {
                                            break;
                                        }
                                }
                                done = done;
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            } catch (IOException e2) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                            }
                        }
                    }
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (FNgpRouteBrief.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static FNgpRouteBrief getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FNgpRouteBrief> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class FNgpRouteBriefList extends GeneratedMessageLite<FNgpRouteBriefList, Builder> implements FNgpRouteBriefListOrBuilder {
        private static final FNgpRouteBriefList DEFAULT_INSTANCE = new FNgpRouteBriefList();
        public static final int FNGP_ROUTE_BRIEF_LIST_FIELD_NUMBER = 1;
        private static volatile Parser<FNgpRouteBriefList> PARSER;
        private Internal.ProtobufList<FNgpRouteBrief> fngpRouteBriefList_ = emptyProtobufList();

        private FNgpRouteBriefList() {
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefListOrBuilder
        public List<FNgpRouteBrief> getFngpRouteBriefListList() {
            return this.fngpRouteBriefList_;
        }

        public List<? extends FNgpRouteBriefOrBuilder> getFngpRouteBriefListOrBuilderList() {
            return this.fngpRouteBriefList_;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefListOrBuilder
        public int getFngpRouteBriefListCount() {
            return this.fngpRouteBriefList_.size();
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefListOrBuilder
        public FNgpRouteBrief getFngpRouteBriefList(int index) {
            return this.fngpRouteBriefList_.get(index);
        }

        public FNgpRouteBriefOrBuilder getFngpRouteBriefListOrBuilder(int index) {
            return this.fngpRouteBriefList_.get(index);
        }

        private void ensureFngpRouteBriefListIsMutable() {
            if (!this.fngpRouteBriefList_.isModifiable()) {
                this.fngpRouteBriefList_ = GeneratedMessageLite.mutableCopy(this.fngpRouteBriefList_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFngpRouteBriefList(int index, FNgpRouteBrief value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureFngpRouteBriefListIsMutable();
            this.fngpRouteBriefList_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFngpRouteBriefList(int index, FNgpRouteBrief.Builder builderForValue) {
            ensureFngpRouteBriefListIsMutable();
            this.fngpRouteBriefList_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFngpRouteBriefList(FNgpRouteBrief value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureFngpRouteBriefListIsMutable();
            this.fngpRouteBriefList_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFngpRouteBriefList(int index, FNgpRouteBrief value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureFngpRouteBriefListIsMutable();
            this.fngpRouteBriefList_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFngpRouteBriefList(FNgpRouteBrief.Builder builderForValue) {
            ensureFngpRouteBriefListIsMutable();
            this.fngpRouteBriefList_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFngpRouteBriefList(int index, FNgpRouteBrief.Builder builderForValue) {
            ensureFngpRouteBriefListIsMutable();
            this.fngpRouteBriefList_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFngpRouteBriefList(Iterable<? extends FNgpRouteBrief> values) {
            ensureFngpRouteBriefListIsMutable();
            AbstractMessageLite.addAll(values, this.fngpRouteBriefList_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFngpRouteBriefList() {
            this.fngpRouteBriefList_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeFngpRouteBriefList(int index) {
            ensureFngpRouteBriefListIsMutable();
            this.fngpRouteBriefList_.remove(index);
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.fngpRouteBriefList_.size(); i++) {
                output.writeMessage(1, this.fngpRouteBriefList_.get(i));
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.fngpRouteBriefList_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.fngpRouteBriefList_.get(i));
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static FNgpRouteBriefList parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FNgpRouteBriefList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpRouteBriefList parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpRouteBriefList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpRouteBriefList parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FNgpRouteBriefList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpRouteBriefList parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpRouteBriefList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpRouteBriefList parseFrom(InputStream input) throws IOException {
            return (FNgpRouteBriefList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteBriefList parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteBriefList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpRouteBriefList parseDelimitedFrom(InputStream input) throws IOException {
            return (FNgpRouteBriefList) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteBriefList parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteBriefList) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpRouteBriefList parseFrom(CodedInputStream input) throws IOException {
            return (FNgpRouteBriefList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteBriefList parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteBriefList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FNgpRouteBriefList prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FNgpRouteBriefList, Builder> implements FNgpRouteBriefListOrBuilder {
            private Builder() {
                super(FNgpRouteBriefList.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefListOrBuilder
            public List<FNgpRouteBrief> getFngpRouteBriefListList() {
                return Collections.unmodifiableList(((FNgpRouteBriefList) this.instance).getFngpRouteBriefListList());
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefListOrBuilder
            public int getFngpRouteBriefListCount() {
                return ((FNgpRouteBriefList) this.instance).getFngpRouteBriefListCount();
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteBriefListOrBuilder
            public FNgpRouteBrief getFngpRouteBriefList(int index) {
                return ((FNgpRouteBriefList) this.instance).getFngpRouteBriefList(index);
            }

            public Builder setFngpRouteBriefList(int index, FNgpRouteBrief value) {
                copyOnWrite();
                ((FNgpRouteBriefList) this.instance).setFngpRouteBriefList(index, value);
                return this;
            }

            public Builder setFngpRouteBriefList(int index, FNgpRouteBrief.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteBriefList) this.instance).setFngpRouteBriefList(index, builderForValue);
                return this;
            }

            public Builder addFngpRouteBriefList(FNgpRouteBrief value) {
                copyOnWrite();
                ((FNgpRouteBriefList) this.instance).addFngpRouteBriefList(value);
                return this;
            }

            public Builder addFngpRouteBriefList(int index, FNgpRouteBrief value) {
                copyOnWrite();
                ((FNgpRouteBriefList) this.instance).addFngpRouteBriefList(index, value);
                return this;
            }

            public Builder addFngpRouteBriefList(FNgpRouteBrief.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteBriefList) this.instance).addFngpRouteBriefList(builderForValue);
                return this;
            }

            public Builder addFngpRouteBriefList(int index, FNgpRouteBrief.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteBriefList) this.instance).addFngpRouteBriefList(index, builderForValue);
                return this;
            }

            public Builder addAllFngpRouteBriefList(Iterable<? extends FNgpRouteBrief> values) {
                copyOnWrite();
                ((FNgpRouteBriefList) this.instance).addAllFngpRouteBriefList(values);
                return this;
            }

            public Builder clearFngpRouteBriefList() {
                copyOnWrite();
                ((FNgpRouteBriefList) this.instance).clearFngpRouteBriefList();
                return this;
            }

            public Builder removeFngpRouteBriefList(int index) {
                copyOnWrite();
                ((FNgpRouteBriefList) this.instance).removeFngpRouteBriefList(index);
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new FNgpRouteBriefList();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.fngpRouteBriefList_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    FNgpRouteBriefList other = (FNgpRouteBriefList) arg1;
                    this.fngpRouteBriefList_ = visitor.visitList(this.fngpRouteBriefList_, other.fngpRouteBriefList_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag != 10) {
                                if (!input.skipField(tag)) {
                                    done = true;
                                }
                            } else {
                                if (!this.fngpRouteBriefList_.isModifiable()) {
                                    this.fngpRouteBriefList_ = GeneratedMessageLite.mutableCopy(this.fngpRouteBriefList_);
                                }
                                this.fngpRouteBriefList_.add((FNgpRouteBrief) input.readMessage(FNgpRouteBrief.parser(), extensionRegistry));
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (FNgpRouteBriefList.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static FNgpRouteBriefList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FNgpRouteBriefList> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class FNgpRouteDetailList extends GeneratedMessageLite<FNgpRouteDetailList, Builder> implements FNgpRouteDetailListOrBuilder {
        private static final FNgpRouteDetailList DEFAULT_INSTANCE = new FNgpRouteDetailList();
        public static final int FNGP_ROUTE_DEFAIL_LIST_FIELD_NUMBER = 1;
        private static volatile Parser<FNgpRouteDetailList> PARSER;
        private Internal.ProtobufList<FNgpRouteDetail> fngpRouteDefailList_ = emptyProtobufList();

        private FNgpRouteDetailList() {
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailListOrBuilder
        public List<FNgpRouteDetail> getFngpRouteDefailListList() {
            return this.fngpRouteDefailList_;
        }

        public List<? extends FNgpRouteDetailOrBuilder> getFngpRouteDefailListOrBuilderList() {
            return this.fngpRouteDefailList_;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailListOrBuilder
        public int getFngpRouteDefailListCount() {
            return this.fngpRouteDefailList_.size();
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailListOrBuilder
        public FNgpRouteDetail getFngpRouteDefailList(int index) {
            return this.fngpRouteDefailList_.get(index);
        }

        public FNgpRouteDetailOrBuilder getFngpRouteDefailListOrBuilder(int index) {
            return this.fngpRouteDefailList_.get(index);
        }

        private void ensureFngpRouteDefailListIsMutable() {
            if (!this.fngpRouteDefailList_.isModifiable()) {
                this.fngpRouteDefailList_ = GeneratedMessageLite.mutableCopy(this.fngpRouteDefailList_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFngpRouteDefailList(int index, FNgpRouteDetail value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureFngpRouteDefailListIsMutable();
            this.fngpRouteDefailList_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFngpRouteDefailList(int index, FNgpRouteDetail.Builder builderForValue) {
            ensureFngpRouteDefailListIsMutable();
            this.fngpRouteDefailList_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFngpRouteDefailList(FNgpRouteDetail value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureFngpRouteDefailListIsMutable();
            this.fngpRouteDefailList_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFngpRouteDefailList(int index, FNgpRouteDetail value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureFngpRouteDefailListIsMutable();
            this.fngpRouteDefailList_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFngpRouteDefailList(FNgpRouteDetail.Builder builderForValue) {
            ensureFngpRouteDefailListIsMutable();
            this.fngpRouteDefailList_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFngpRouteDefailList(int index, FNgpRouteDetail.Builder builderForValue) {
            ensureFngpRouteDefailListIsMutable();
            this.fngpRouteDefailList_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFngpRouteDefailList(Iterable<? extends FNgpRouteDetail> values) {
            ensureFngpRouteDefailListIsMutable();
            AbstractMessageLite.addAll(values, this.fngpRouteDefailList_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFngpRouteDefailList() {
            this.fngpRouteDefailList_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeFngpRouteDefailList(int index) {
            ensureFngpRouteDefailListIsMutable();
            this.fngpRouteDefailList_.remove(index);
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.fngpRouteDefailList_.size(); i++) {
                output.writeMessage(1, this.fngpRouteDefailList_.get(i));
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.fngpRouteDefailList_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.fngpRouteDefailList_.get(i));
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static FNgpRouteDetailList parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FNgpRouteDetailList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpRouteDetailList parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpRouteDetailList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpRouteDetailList parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FNgpRouteDetailList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpRouteDetailList parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpRouteDetailList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpRouteDetailList parseFrom(InputStream input) throws IOException {
            return (FNgpRouteDetailList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteDetailList parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteDetailList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpRouteDetailList parseDelimitedFrom(InputStream input) throws IOException {
            return (FNgpRouteDetailList) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteDetailList parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteDetailList) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpRouteDetailList parseFrom(CodedInputStream input) throws IOException {
            return (FNgpRouteDetailList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteDetailList parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteDetailList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FNgpRouteDetailList prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FNgpRouteDetailList, Builder> implements FNgpRouteDetailListOrBuilder {
            private Builder() {
                super(FNgpRouteDetailList.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailListOrBuilder
            public List<FNgpRouteDetail> getFngpRouteDefailListList() {
                return Collections.unmodifiableList(((FNgpRouteDetailList) this.instance).getFngpRouteDefailListList());
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailListOrBuilder
            public int getFngpRouteDefailListCount() {
                return ((FNgpRouteDetailList) this.instance).getFngpRouteDefailListCount();
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailListOrBuilder
            public FNgpRouteDetail getFngpRouteDefailList(int index) {
                return ((FNgpRouteDetailList) this.instance).getFngpRouteDefailList(index);
            }

            public Builder setFngpRouteDefailList(int index, FNgpRouteDetail value) {
                copyOnWrite();
                ((FNgpRouteDetailList) this.instance).setFngpRouteDefailList(index, value);
                return this;
            }

            public Builder setFngpRouteDefailList(int index, FNgpRouteDetail.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteDetailList) this.instance).setFngpRouteDefailList(index, builderForValue);
                return this;
            }

            public Builder addFngpRouteDefailList(FNgpRouteDetail value) {
                copyOnWrite();
                ((FNgpRouteDetailList) this.instance).addFngpRouteDefailList(value);
                return this;
            }

            public Builder addFngpRouteDefailList(int index, FNgpRouteDetail value) {
                copyOnWrite();
                ((FNgpRouteDetailList) this.instance).addFngpRouteDefailList(index, value);
                return this;
            }

            public Builder addFngpRouteDefailList(FNgpRouteDetail.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteDetailList) this.instance).addFngpRouteDefailList(builderForValue);
                return this;
            }

            public Builder addFngpRouteDefailList(int index, FNgpRouteDetail.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteDetailList) this.instance).addFngpRouteDefailList(index, builderForValue);
                return this;
            }

            public Builder addAllFngpRouteDefailList(Iterable<? extends FNgpRouteDetail> values) {
                copyOnWrite();
                ((FNgpRouteDetailList) this.instance).addAllFngpRouteDefailList(values);
                return this;
            }

            public Builder clearFngpRouteDefailList() {
                copyOnWrite();
                ((FNgpRouteDetailList) this.instance).clearFngpRouteDefailList();
                return this;
            }

            public Builder removeFngpRouteDefailList(int index) {
                copyOnWrite();
                ((FNgpRouteDetailList) this.instance).removeFngpRouteDefailList(index);
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new FNgpRouteDetailList();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.fngpRouteDefailList_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    FNgpRouteDetailList other = (FNgpRouteDetailList) arg1;
                    this.fngpRouteDefailList_ = visitor.visitList(this.fngpRouteDefailList_, other.fngpRouteDefailList_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag != 10) {
                                if (!input.skipField(tag)) {
                                    done = true;
                                }
                            } else {
                                if (!this.fngpRouteDefailList_.isModifiable()) {
                                    this.fngpRouteDefailList_ = GeneratedMessageLite.mutableCopy(this.fngpRouteDefailList_);
                                }
                                this.fngpRouteDefailList_.add((FNgpRouteDetail) input.readMessage(FNgpRouteDetail.parser(), extensionRegistry));
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (FNgpRouteDetailList.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static FNgpRouteDetailList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FNgpRouteDetailList> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class FNgpRouteDetail extends GeneratedMessageLite<FNgpRouteDetail, Builder> implements FNgpRouteDetailOrBuilder {
        private static final FNgpRouteDetail DEFAULT_INSTANCE = new FNgpRouteDetail();
        public static final int FNGP_ROUTE_BRIEF_FIELD_NUMBER = 1;
        private static volatile Parser<FNgpRouteDetail> PARSER = null;
        public static final int SEGMENT_LIST_FIELD_NUMBER = 3;
        public static final int V2_POINT_LIST_FIELD_NUMBER = 2;
        private int bitField0_;
        private FNgpRouteBrief fngpRouteBrief_;
        private Internal.ProtobufList<Point> v2PointList_ = emptyProtobufList();
        private Internal.ProtobufList<SegmentInfo> segmentList_ = emptyProtobufList();

        private FNgpRouteDetail() {
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
        public boolean hasFngpRouteBrief() {
            return this.fngpRouteBrief_ != null;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
        public FNgpRouteBrief getFngpRouteBrief() {
            return this.fngpRouteBrief_ == null ? FNgpRouteBrief.getDefaultInstance() : this.fngpRouteBrief_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFngpRouteBrief(FNgpRouteBrief value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.fngpRouteBrief_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFngpRouteBrief(FNgpRouteBrief.Builder builderForValue) {
            this.fngpRouteBrief_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFngpRouteBrief(FNgpRouteBrief value) {
            if (this.fngpRouteBrief_ != null && this.fngpRouteBrief_ != FNgpRouteBrief.getDefaultInstance()) {
                this.fngpRouteBrief_ = FNgpRouteBrief.newBuilder(this.fngpRouteBrief_).mergeFrom((FNgpRouteBrief.Builder) value).buildPartial();
            } else {
                this.fngpRouteBrief_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFngpRouteBrief() {
            this.fngpRouteBrief_ = null;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
        public List<Point> getV2PointListList() {
            return this.v2PointList_;
        }

        public List<? extends PointOrBuilder> getV2PointListOrBuilderList() {
            return this.v2PointList_;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
        public int getV2PointListCount() {
            return this.v2PointList_.size();
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
        public Point getV2PointList(int index) {
            return this.v2PointList_.get(index);
        }

        public PointOrBuilder getV2PointListOrBuilder(int index) {
            return this.v2PointList_.get(index);
        }

        private void ensureV2PointListIsMutable() {
            if (!this.v2PointList_.isModifiable()) {
                this.v2PointList_ = GeneratedMessageLite.mutableCopy(this.v2PointList_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setV2PointList(int index, Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointListIsMutable();
            this.v2PointList_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setV2PointList(int index, Point.Builder builderForValue) {
            ensureV2PointListIsMutable();
            this.v2PointList_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2PointList(Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointListIsMutable();
            this.v2PointList_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2PointList(int index, Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointListIsMutable();
            this.v2PointList_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2PointList(Point.Builder builderForValue) {
            ensureV2PointListIsMutable();
            this.v2PointList_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2PointList(int index, Point.Builder builderForValue) {
            ensureV2PointListIsMutable();
            this.v2PointList_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllV2PointList(Iterable<? extends Point> values) {
            ensureV2PointListIsMutable();
            AbstractMessageLite.addAll(values, this.v2PointList_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearV2PointList() {
            this.v2PointList_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeV2PointList(int index) {
            ensureV2PointListIsMutable();
            this.v2PointList_.remove(index);
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
        public List<SegmentInfo> getSegmentListList() {
            return this.segmentList_;
        }

        public List<? extends SegmentInfoOrBuilder> getSegmentListOrBuilderList() {
            return this.segmentList_;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
        public int getSegmentListCount() {
            return this.segmentList_.size();
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
        public SegmentInfo getSegmentList(int index) {
            return this.segmentList_.get(index);
        }

        public SegmentInfoOrBuilder getSegmentListOrBuilder(int index) {
            return this.segmentList_.get(index);
        }

        private void ensureSegmentListIsMutable() {
            if (!this.segmentList_.isModifiable()) {
                this.segmentList_ = GeneratedMessageLite.mutableCopy(this.segmentList_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentList(int index, SegmentInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureSegmentListIsMutable();
            this.segmentList_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentList(int index, SegmentInfo.Builder builderForValue) {
            ensureSegmentListIsMutable();
            this.segmentList_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSegmentList(SegmentInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureSegmentListIsMutable();
            this.segmentList_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSegmentList(int index, SegmentInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureSegmentListIsMutable();
            this.segmentList_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSegmentList(SegmentInfo.Builder builderForValue) {
            ensureSegmentListIsMutable();
            this.segmentList_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSegmentList(int index, SegmentInfo.Builder builderForValue) {
            ensureSegmentListIsMutable();
            this.segmentList_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSegmentList(Iterable<? extends SegmentInfo> values) {
            ensureSegmentListIsMutable();
            AbstractMessageLite.addAll(values, this.segmentList_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSegmentList() {
            this.segmentList_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeSegmentList(int index) {
            ensureSegmentListIsMutable();
            this.segmentList_.remove(index);
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.fngpRouteBrief_ != null) {
                output.writeMessage(1, getFngpRouteBrief());
            }
            for (int i = 0; i < this.v2PointList_.size(); i++) {
                output.writeMessage(2, this.v2PointList_.get(i));
            }
            for (int i2 = 0; i2 < this.segmentList_.size(); i2++) {
                output.writeMessage(3, this.segmentList_.get(i2));
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int i = 0;
            int size2 = this.fngpRouteBrief_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getFngpRouteBrief()) : 0;
            for (int size3 = 0; size3 < this.v2PointList_.size(); size3++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.v2PointList_.get(size3));
            }
            while (true) {
                int i2 = i;
                if (i2 < this.segmentList_.size()) {
                    size2 += CodedOutputStream.computeMessageSize(3, this.segmentList_.get(i2));
                    i = i2 + 1;
                } else {
                    this.memoizedSerializedSize = size2;
                    return size2;
                }
            }
        }

        public static FNgpRouteDetail parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FNgpRouteDetail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpRouteDetail parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpRouteDetail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpRouteDetail parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FNgpRouteDetail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpRouteDetail parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpRouteDetail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpRouteDetail parseFrom(InputStream input) throws IOException {
            return (FNgpRouteDetail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteDetail parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteDetail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpRouteDetail parseDelimitedFrom(InputStream input) throws IOException {
            return (FNgpRouteDetail) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteDetail parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteDetail) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpRouteDetail parseFrom(CodedInputStream input) throws IOException {
            return (FNgpRouteDetail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteDetail parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteDetail) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FNgpRouteDetail prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FNgpRouteDetail, Builder> implements FNgpRouteDetailOrBuilder {
            private Builder() {
                super(FNgpRouteDetail.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
            public boolean hasFngpRouteBrief() {
                return ((FNgpRouteDetail) this.instance).hasFngpRouteBrief();
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
            public FNgpRouteBrief getFngpRouteBrief() {
                return ((FNgpRouteDetail) this.instance).getFngpRouteBrief();
            }

            public Builder setFngpRouteBrief(FNgpRouteBrief value) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).setFngpRouteBrief(value);
                return this;
            }

            public Builder setFngpRouteBrief(FNgpRouteBrief.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).setFngpRouteBrief(builderForValue);
                return this;
            }

            public Builder mergeFngpRouteBrief(FNgpRouteBrief value) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).mergeFngpRouteBrief(value);
                return this;
            }

            public Builder clearFngpRouteBrief() {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).clearFngpRouteBrief();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
            public List<Point> getV2PointListList() {
                return Collections.unmodifiableList(((FNgpRouteDetail) this.instance).getV2PointListList());
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
            public int getV2PointListCount() {
                return ((FNgpRouteDetail) this.instance).getV2PointListCount();
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
            public Point getV2PointList(int index) {
                return ((FNgpRouteDetail) this.instance).getV2PointList(index);
            }

            public Builder setV2PointList(int index, Point value) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).setV2PointList(index, value);
                return this;
            }

            public Builder setV2PointList(int index, Point.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).setV2PointList(index, builderForValue);
                return this;
            }

            public Builder addV2PointList(Point value) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).addV2PointList(value);
                return this;
            }

            public Builder addV2PointList(int index, Point value) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).addV2PointList(index, value);
                return this;
            }

            public Builder addV2PointList(Point.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).addV2PointList(builderForValue);
                return this;
            }

            public Builder addV2PointList(int index, Point.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).addV2PointList(index, builderForValue);
                return this;
            }

            public Builder addAllV2PointList(Iterable<? extends Point> values) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).addAllV2PointList(values);
                return this;
            }

            public Builder clearV2PointList() {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).clearV2PointList();
                return this;
            }

            public Builder removeV2PointList(int index) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).removeV2PointList(index);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
            public List<SegmentInfo> getSegmentListList() {
                return Collections.unmodifiableList(((FNgpRouteDetail) this.instance).getSegmentListList());
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
            public int getSegmentListCount() {
                return ((FNgpRouteDetail) this.instance).getSegmentListCount();
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteDetailOrBuilder
            public SegmentInfo getSegmentList(int index) {
                return ((FNgpRouteDetail) this.instance).getSegmentList(index);
            }

            public Builder setSegmentList(int index, SegmentInfo value) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).setSegmentList(index, value);
                return this;
            }

            public Builder setSegmentList(int index, SegmentInfo.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).setSegmentList(index, builderForValue);
                return this;
            }

            public Builder addSegmentList(SegmentInfo value) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).addSegmentList(value);
                return this;
            }

            public Builder addSegmentList(int index, SegmentInfo value) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).addSegmentList(index, value);
                return this;
            }

            public Builder addSegmentList(SegmentInfo.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).addSegmentList(builderForValue);
                return this;
            }

            public Builder addSegmentList(int index, SegmentInfo.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).addSegmentList(index, builderForValue);
                return this;
            }

            public Builder addAllSegmentList(Iterable<? extends SegmentInfo> values) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).addAllSegmentList(values);
                return this;
            }

            public Builder clearSegmentList() {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).clearSegmentList();
                return this;
            }

            public Builder removeSegmentList(int index) {
                copyOnWrite();
                ((FNgpRouteDetail) this.instance).removeSegmentList(index);
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new FNgpRouteDetail();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.v2PointList_.makeImmutable();
                    this.segmentList_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    FNgpRouteDetail other = (FNgpRouteDetail) arg1;
                    this.fngpRouteBrief_ = (FNgpRouteBrief) visitor.visitMessage(this.fngpRouteBrief_, other.fngpRouteBrief_);
                    this.v2PointList_ = visitor.visitList(this.v2PointList_, other.v2PointList_);
                    this.segmentList_ = visitor.visitList(this.segmentList_, other.segmentList_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                FNgpRouteBrief.Builder subBuilder = null;
                                if (this.fngpRouteBrief_ != null) {
                                    subBuilder = this.fngpRouteBrief_.toBuilder();
                                }
                                this.fngpRouteBrief_ = (FNgpRouteBrief) input.readMessage(FNgpRouteBrief.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((FNgpRouteBrief.Builder) this.fngpRouteBrief_);
                                    this.fngpRouteBrief_ = (FNgpRouteBrief) subBuilder.buildPartial();
                                }
                            } else if (tag == 18) {
                                if (!this.v2PointList_.isModifiable()) {
                                    this.v2PointList_ = GeneratedMessageLite.mutableCopy(this.v2PointList_);
                                }
                                this.v2PointList_.add((Point) input.readMessage(Point.parser(), extensionRegistry));
                            } else if (tag != 26) {
                                if (!input.skipField(tag)) {
                                    done = true;
                                }
                            } else {
                                if (!this.segmentList_.isModifiable()) {
                                    this.segmentList_ = GeneratedMessageLite.mutableCopy(this.segmentList_);
                                }
                                this.segmentList_.add((SegmentInfo) input.readMessage(SegmentInfo.parser(), extensionRegistry));
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (FNgpRouteDetail.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static FNgpRouteDetail getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FNgpRouteDetail> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class FNgpRouteWayPointsSpecified extends GeneratedMessageLite<FNgpRouteWayPointsSpecified, Builder> implements FNgpRouteWayPointsSpecifiedOrBuilder {
        private static final FNgpRouteWayPointsSpecified DEFAULT_INSTANCE = new FNgpRouteWayPointsSpecified();
        private static volatile Parser<FNgpRouteWayPointsSpecified> PARSER = null;
        public static final int V2_POINT_LIST_SPECIFIED_FIELD_NUMBER = 1;
        private Internal.ProtobufList<Point> v2PointListSpecified_ = emptyProtobufList();

        private FNgpRouteWayPointsSpecified() {
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteWayPointsSpecifiedOrBuilder
        public List<Point> getV2PointListSpecifiedList() {
            return this.v2PointListSpecified_;
        }

        public List<? extends PointOrBuilder> getV2PointListSpecifiedOrBuilderList() {
            return this.v2PointListSpecified_;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteWayPointsSpecifiedOrBuilder
        public int getV2PointListSpecifiedCount() {
            return this.v2PointListSpecified_.size();
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteWayPointsSpecifiedOrBuilder
        public Point getV2PointListSpecified(int index) {
            return this.v2PointListSpecified_.get(index);
        }

        public PointOrBuilder getV2PointListSpecifiedOrBuilder(int index) {
            return this.v2PointListSpecified_.get(index);
        }

        private void ensureV2PointListSpecifiedIsMutable() {
            if (!this.v2PointListSpecified_.isModifiable()) {
                this.v2PointListSpecified_ = GeneratedMessageLite.mutableCopy(this.v2PointListSpecified_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setV2PointListSpecified(int index, Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointListSpecifiedIsMutable();
            this.v2PointListSpecified_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setV2PointListSpecified(int index, Point.Builder builderForValue) {
            ensureV2PointListSpecifiedIsMutable();
            this.v2PointListSpecified_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2PointListSpecified(Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointListSpecifiedIsMutable();
            this.v2PointListSpecified_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2PointListSpecified(int index, Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointListSpecifiedIsMutable();
            this.v2PointListSpecified_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2PointListSpecified(Point.Builder builderForValue) {
            ensureV2PointListSpecifiedIsMutable();
            this.v2PointListSpecified_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2PointListSpecified(int index, Point.Builder builderForValue) {
            ensureV2PointListSpecifiedIsMutable();
            this.v2PointListSpecified_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllV2PointListSpecified(Iterable<? extends Point> values) {
            ensureV2PointListSpecifiedIsMutable();
            AbstractMessageLite.addAll(values, this.v2PointListSpecified_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearV2PointListSpecified() {
            this.v2PointListSpecified_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeV2PointListSpecified(int index) {
            ensureV2PointListSpecifiedIsMutable();
            this.v2PointListSpecified_.remove(index);
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.v2PointListSpecified_.size(); i++) {
                output.writeMessage(1, this.v2PointListSpecified_.get(i));
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.v2PointListSpecified_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.v2PointListSpecified_.get(i));
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static FNgpRouteWayPointsSpecified parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FNgpRouteWayPointsSpecified) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpRouteWayPointsSpecified parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpRouteWayPointsSpecified) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpRouteWayPointsSpecified parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FNgpRouteWayPointsSpecified) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpRouteWayPointsSpecified parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpRouteWayPointsSpecified) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpRouteWayPointsSpecified parseFrom(InputStream input) throws IOException {
            return (FNgpRouteWayPointsSpecified) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteWayPointsSpecified parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteWayPointsSpecified) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpRouteWayPointsSpecified parseDelimitedFrom(InputStream input) throws IOException {
            return (FNgpRouteWayPointsSpecified) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteWayPointsSpecified parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteWayPointsSpecified) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpRouteWayPointsSpecified parseFrom(CodedInputStream input) throws IOException {
            return (FNgpRouteWayPointsSpecified) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpRouteWayPointsSpecified parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpRouteWayPointsSpecified) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FNgpRouteWayPointsSpecified prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FNgpRouteWayPointsSpecified, Builder> implements FNgpRouteWayPointsSpecifiedOrBuilder {
            private Builder() {
                super(FNgpRouteWayPointsSpecified.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteWayPointsSpecifiedOrBuilder
            public List<Point> getV2PointListSpecifiedList() {
                return Collections.unmodifiableList(((FNgpRouteWayPointsSpecified) this.instance).getV2PointListSpecifiedList());
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteWayPointsSpecifiedOrBuilder
            public int getV2PointListSpecifiedCount() {
                return ((FNgpRouteWayPointsSpecified) this.instance).getV2PointListSpecifiedCount();
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpRouteWayPointsSpecifiedOrBuilder
            public Point getV2PointListSpecified(int index) {
                return ((FNgpRouteWayPointsSpecified) this.instance).getV2PointListSpecified(index);
            }

            public Builder setV2PointListSpecified(int index, Point value) {
                copyOnWrite();
                ((FNgpRouteWayPointsSpecified) this.instance).setV2PointListSpecified(index, value);
                return this;
            }

            public Builder setV2PointListSpecified(int index, Point.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteWayPointsSpecified) this.instance).setV2PointListSpecified(index, builderForValue);
                return this;
            }

            public Builder addV2PointListSpecified(Point value) {
                copyOnWrite();
                ((FNgpRouteWayPointsSpecified) this.instance).addV2PointListSpecified(value);
                return this;
            }

            public Builder addV2PointListSpecified(int index, Point value) {
                copyOnWrite();
                ((FNgpRouteWayPointsSpecified) this.instance).addV2PointListSpecified(index, value);
                return this;
            }

            public Builder addV2PointListSpecified(Point.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteWayPointsSpecified) this.instance).addV2PointListSpecified(builderForValue);
                return this;
            }

            public Builder addV2PointListSpecified(int index, Point.Builder builderForValue) {
                copyOnWrite();
                ((FNgpRouteWayPointsSpecified) this.instance).addV2PointListSpecified(index, builderForValue);
                return this;
            }

            public Builder addAllV2PointListSpecified(Iterable<? extends Point> values) {
                copyOnWrite();
                ((FNgpRouteWayPointsSpecified) this.instance).addAllV2PointListSpecified(values);
                return this;
            }

            public Builder clearV2PointListSpecified() {
                copyOnWrite();
                ((FNgpRouteWayPointsSpecified) this.instance).clearV2PointListSpecified();
                return this;
            }

            public Builder removeV2PointListSpecified(int index) {
                copyOnWrite();
                ((FNgpRouteWayPointsSpecified) this.instance).removeV2PointListSpecified(index);
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new FNgpRouteWayPointsSpecified();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.v2PointListSpecified_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    FNgpRouteWayPointsSpecified other = (FNgpRouteWayPointsSpecified) arg1;
                    this.v2PointListSpecified_ = visitor.visitList(this.v2PointListSpecified_, other.v2PointListSpecified_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag != 10) {
                                if (!input.skipField(tag)) {
                                    done = true;
                                }
                            } else {
                                if (!this.v2PointListSpecified_.isModifiable()) {
                                    this.v2PointListSpecified_ = GeneratedMessageLite.mutableCopy(this.v2PointListSpecified_);
                                }
                                this.v2PointListSpecified_.add((Point) input.readMessage(Point.parser(), extensionRegistry));
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (FNgpRouteWayPointsSpecified.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static FNgpRouteWayPointsSpecified getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FNgpRouteWayPointsSpecified> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class FNgpEmulatorNavigation extends GeneratedMessageLite<FNgpEmulatorNavigation, Builder> implements FNgpEmulatorNavigationOrBuilder {
        public static final int ARRIVE_TIME_FIELD_NUMBER = 7;
        public static final int CURRENT_LONLAT_POINT_FIELD_NUMBER = 3;
        public static final int CURRENT_POINT_INDEX_IN_V2LIST_FIELD_NUMBER = 2;
        private static final FNgpEmulatorNavigation DEFAULT_INSTANCE = new FNgpEmulatorNavigation();
        public static final int MANEUVER_TYPE_FIELD_NUMBER = 8;
        private static volatile Parser<FNgpEmulatorNavigation> PARSER = null;
        public static final int PASSED_MILEAGE_FIELD_NUMBER = 5;
        public static final int PREDICT_TIME_DURATION_FIELD_NUMBER = 6;
        public static final int REMAIN_MILEAGE_FIELD_NUMBER = 4;
        public static final int ROUTE_ID_FIELD_NUMBER = 1;
        private Point currentLonlatPoint_;
        private long routeId_ = 0;
        private int currentPointIndexInV2List_ = 0;
        private int remainMileage_ = 0;
        private int passedMileage_ = 0;
        private long predictTimeDuration_ = 0;
        private int arriveTime_ = 0;
        private int maneuverType_ = 0;

        private FNgpEmulatorNavigation() {
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
        public long getRouteId() {
            return this.routeId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRouteId(long value) {
            this.routeId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRouteId() {
            this.routeId_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
        public int getCurrentPointIndexInV2List() {
            return this.currentPointIndexInV2List_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurrentPointIndexInV2List(int value) {
            this.currentPointIndexInV2List_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCurrentPointIndexInV2List() {
            this.currentPointIndexInV2List_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
        public boolean hasCurrentLonlatPoint() {
            return this.currentLonlatPoint_ != null;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
        public Point getCurrentLonlatPoint() {
            return this.currentLonlatPoint_ == null ? Point.getDefaultInstance() : this.currentLonlatPoint_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurrentLonlatPoint(Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.currentLonlatPoint_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurrentLonlatPoint(Point.Builder builderForValue) {
            this.currentLonlatPoint_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCurrentLonlatPoint(Point value) {
            if (this.currentLonlatPoint_ != null && this.currentLonlatPoint_ != Point.getDefaultInstance()) {
                this.currentLonlatPoint_ = Point.newBuilder(this.currentLonlatPoint_).mergeFrom((Point.Builder) value).buildPartial();
            } else {
                this.currentLonlatPoint_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCurrentLonlatPoint() {
            this.currentLonlatPoint_ = null;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
        public int getRemainMileage() {
            return this.remainMileage_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRemainMileage(int value) {
            this.remainMileage_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRemainMileage() {
            this.remainMileage_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
        public int getPassedMileage() {
            return this.passedMileage_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPassedMileage(int value) {
            this.passedMileage_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPassedMileage() {
            this.passedMileage_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
        public long getPredictTimeDuration() {
            return this.predictTimeDuration_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPredictTimeDuration(long value) {
            this.predictTimeDuration_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPredictTimeDuration() {
            this.predictTimeDuration_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
        public int getArriveTime() {
            return this.arriveTime_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArriveTime(int value) {
            this.arriveTime_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArriveTime() {
            this.arriveTime_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
        public int getManeuverTypeValue() {
            return this.maneuverType_;
        }

        @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
        public ManeuverType getManeuverType() {
            ManeuverType result = ManeuverType.forNumber(this.maneuverType_);
            return result == null ? ManeuverType.UNRECOGNIZED : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManeuverTypeValue(int value) {
            this.maneuverType_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManeuverType(ManeuverType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.maneuverType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearManeuverType() {
            this.maneuverType_ = 0;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.routeId_ != 0) {
                output.writeInt64(1, this.routeId_);
            }
            if (this.currentPointIndexInV2List_ != 0) {
                output.writeInt32(2, this.currentPointIndexInV2List_);
            }
            if (this.currentLonlatPoint_ != null) {
                output.writeMessage(3, getCurrentLonlatPoint());
            }
            if (this.remainMileage_ != 0) {
                output.writeInt32(4, this.remainMileage_);
            }
            if (this.passedMileage_ != 0) {
                output.writeInt32(5, this.passedMileage_);
            }
            if (this.predictTimeDuration_ != 0) {
                output.writeInt64(6, this.predictTimeDuration_);
            }
            if (this.arriveTime_ != 0) {
                output.writeInt32(7, this.arriveTime_);
            }
            if (this.maneuverType_ != ManeuverType.TURN_LEFT_2km.getNumber()) {
                output.writeEnum(8, this.maneuverType_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.routeId_ != 0 ? 0 + CodedOutputStream.computeInt64Size(1, this.routeId_) : 0;
            if (this.currentPointIndexInV2List_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(2, this.currentPointIndexInV2List_);
            }
            if (this.currentLonlatPoint_ != null) {
                size2 += CodedOutputStream.computeMessageSize(3, getCurrentLonlatPoint());
            }
            if (this.remainMileage_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(4, this.remainMileage_);
            }
            if (this.passedMileage_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(5, this.passedMileage_);
            }
            if (this.predictTimeDuration_ != 0) {
                size2 += CodedOutputStream.computeInt64Size(6, this.predictTimeDuration_);
            }
            if (this.arriveTime_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(7, this.arriveTime_);
            }
            if (this.maneuverType_ != ManeuverType.TURN_LEFT_2km.getNumber()) {
                size2 += CodedOutputStream.computeEnumSize(8, this.maneuverType_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static FNgpEmulatorNavigation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FNgpEmulatorNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpEmulatorNavigation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpEmulatorNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpEmulatorNavigation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FNgpEmulatorNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FNgpEmulatorNavigation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FNgpEmulatorNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FNgpEmulatorNavigation parseFrom(InputStream input) throws IOException {
            return (FNgpEmulatorNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpEmulatorNavigation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpEmulatorNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpEmulatorNavigation parseDelimitedFrom(InputStream input) throws IOException {
            return (FNgpEmulatorNavigation) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpEmulatorNavigation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpEmulatorNavigation) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FNgpEmulatorNavigation parseFrom(CodedInputStream input) throws IOException {
            return (FNgpEmulatorNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FNgpEmulatorNavigation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FNgpEmulatorNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FNgpEmulatorNavigation prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FNgpEmulatorNavigation, Builder> implements FNgpEmulatorNavigationOrBuilder {
            private Builder() {
                super(FNgpEmulatorNavigation.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
            public long getRouteId() {
                return ((FNgpEmulatorNavigation) this.instance).getRouteId();
            }

            public Builder setRouteId(long value) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).setRouteId(value);
                return this;
            }

            public Builder clearRouteId() {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).clearRouteId();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
            public int getCurrentPointIndexInV2List() {
                return ((FNgpEmulatorNavigation) this.instance).getCurrentPointIndexInV2List();
            }

            public Builder setCurrentPointIndexInV2List(int value) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).setCurrentPointIndexInV2List(value);
                return this;
            }

            public Builder clearCurrentPointIndexInV2List() {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).clearCurrentPointIndexInV2List();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
            public boolean hasCurrentLonlatPoint() {
                return ((FNgpEmulatorNavigation) this.instance).hasCurrentLonlatPoint();
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
            public Point getCurrentLonlatPoint() {
                return ((FNgpEmulatorNavigation) this.instance).getCurrentLonlatPoint();
            }

            public Builder setCurrentLonlatPoint(Point value) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).setCurrentLonlatPoint(value);
                return this;
            }

            public Builder setCurrentLonlatPoint(Point.Builder builderForValue) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).setCurrentLonlatPoint(builderForValue);
                return this;
            }

            public Builder mergeCurrentLonlatPoint(Point value) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).mergeCurrentLonlatPoint(value);
                return this;
            }

            public Builder clearCurrentLonlatPoint() {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).clearCurrentLonlatPoint();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
            public int getRemainMileage() {
                return ((FNgpEmulatorNavigation) this.instance).getRemainMileage();
            }

            public Builder setRemainMileage(int value) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).setRemainMileage(value);
                return this;
            }

            public Builder clearRemainMileage() {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).clearRemainMileage();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
            public int getPassedMileage() {
                return ((FNgpEmulatorNavigation) this.instance).getPassedMileage();
            }

            public Builder setPassedMileage(int value) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).setPassedMileage(value);
                return this;
            }

            public Builder clearPassedMileage() {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).clearPassedMileage();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
            public long getPredictTimeDuration() {
                return ((FNgpEmulatorNavigation) this.instance).getPredictTimeDuration();
            }

            public Builder setPredictTimeDuration(long value) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).setPredictTimeDuration(value);
                return this;
            }

            public Builder clearPredictTimeDuration() {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).clearPredictTimeDuration();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
            public int getArriveTime() {
                return ((FNgpEmulatorNavigation) this.instance).getArriveTime();
            }

            public Builder setArriveTime(int value) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).setArriveTime(value);
                return this;
            }

            public Builder clearArriveTime() {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).clearArriveTime();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
            public int getManeuverTypeValue() {
                return ((FNgpEmulatorNavigation) this.instance).getManeuverTypeValue();
            }

            public Builder setManeuverTypeValue(int value) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).setManeuverTypeValue(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuFNgpRouteMessage.FNgpEmulatorNavigationOrBuilder
            public ManeuverType getManeuverType() {
                return ((FNgpEmulatorNavigation) this.instance).getManeuverType();
            }

            public Builder setManeuverType(ManeuverType value) {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).setManeuverType(value);
                return this;
            }

            public Builder clearManeuverType() {
                copyOnWrite();
                ((FNgpEmulatorNavigation) this.instance).clearManeuverType();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new FNgpEmulatorNavigation();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    FNgpEmulatorNavigation other = (FNgpEmulatorNavigation) arg1;
                    this.routeId_ = visitor.visitLong(this.routeId_ != 0, this.routeId_, other.routeId_ != 0, other.routeId_);
                    this.currentPointIndexInV2List_ = visitor.visitInt(this.currentPointIndexInV2List_ != 0, this.currentPointIndexInV2List_, other.currentPointIndexInV2List_ != 0, other.currentPointIndexInV2List_);
                    this.currentLonlatPoint_ = (Point) visitor.visitMessage(this.currentLonlatPoint_, other.currentLonlatPoint_);
                    this.remainMileage_ = visitor.visitInt(this.remainMileage_ != 0, this.remainMileage_, other.remainMileage_ != 0, other.remainMileage_);
                    this.passedMileage_ = visitor.visitInt(this.passedMileage_ != 0, this.passedMileage_, other.passedMileage_ != 0, other.passedMileage_);
                    this.predictTimeDuration_ = visitor.visitLong(this.predictTimeDuration_ != 0, this.predictTimeDuration_, other.predictTimeDuration_ != 0, other.predictTimeDuration_);
                    this.arriveTime_ = visitor.visitInt(this.arriveTime_ != 0, this.arriveTime_, other.arriveTime_ != 0, other.arriveTime_);
                    this.maneuverType_ = visitor.visitInt(this.maneuverType_ != 0, this.maneuverType_, other.maneuverType_ != 0, other.maneuverType_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    while (true) {
                        boolean done2 = done;
                        if (done2) {
                            break;
                        } else {
                            try {
                                int tag = input.readTag();
                                if (tag == 0) {
                                    done2 = true;
                                } else if (tag == 8) {
                                    this.routeId_ = input.readInt64();
                                } else if (tag == 16) {
                                    this.currentPointIndexInV2List_ = input.readInt32();
                                } else if (tag == 26) {
                                    Point.Builder subBuilder = null;
                                    if (this.currentLonlatPoint_ != null) {
                                        subBuilder = this.currentLonlatPoint_.toBuilder();
                                    }
                                    this.currentLonlatPoint_ = (Point) input.readMessage(Point.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((Point.Builder) this.currentLonlatPoint_);
                                        this.currentLonlatPoint_ = (Point) subBuilder.buildPartial();
                                    }
                                } else if (tag == 32) {
                                    this.remainMileage_ = input.readInt32();
                                } else if (tag == 40) {
                                    this.passedMileage_ = input.readInt32();
                                } else if (tag == 48) {
                                    this.predictTimeDuration_ = input.readInt64();
                                } else if (tag == 56) {
                                    int rawValue = input.readInt32();
                                    this.arriveTime_ = rawValue;
                                } else if (tag != 64) {
                                    if (!input.skipField(tag)) {
                                        done2 = true;
                                    }
                                } else {
                                    int rawValue2 = input.readEnum();
                                    this.maneuverType_ = rawValue2;
                                }
                                done = done2;
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            } catch (IOException e2) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                            }
                        }
                    }
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (FNgpEmulatorNavigation.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        public static FNgpEmulatorNavigation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FNgpEmulatorNavigation> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
