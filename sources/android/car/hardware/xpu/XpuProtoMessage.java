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
public final class XpuProtoMessage {

    /* loaded from: classes.dex */
    public interface CNGPCityMapFinNotifyOrBuilder extends MessageLiteOrBuilder {
        int getCityCode();

        int getResult();
    }

    /* loaded from: classes.dex */
    public interface CNGPCityMapRequestOrBuilder extends MessageLiteOrBuilder {
        CNGPCityMapRequest.CityRequest getCity();

        boolean hasCity();
    }

    /* loaded from: classes.dex */
    public interface CNGPCityMapResponseOrBuilder extends MessageLiteOrBuilder {
        CNGPCityMapResponse.CityResponse getCities(int i);

        int getCitiesCount();

        List<CNGPCityMapResponse.CityResponse> getCitiesList();

        int getErrorCode();
    }

    /* loaded from: classes.dex */
    public interface CameraOrBuilder extends MessageLiteOrBuilder {
        long getDistance();

        V2_Point getPosition();

        int getSpeed();

        int getType();

        boolean hasPosition();
    }

    /* loaded from: classes.dex */
    public interface HeightDiffDataOrBuilder extends MessageLiteOrBuilder {
        int getDistToLinkStart();

        int getHeightDiffDist();

        int getHeightDiffParellel();

        int getHeightDiffStraight();
    }

    /* loaded from: classes.dex */
    public interface LinkOrBuilder extends MessageLiteOrBuilder {
        int getFormwayType();

        boolean getHasParallelRoad();

        HeightDiffData getHeightDiffData();

        long getId();

        boolean getIsOverHead();

        int getLaneNum();

        double getLength();

        int getLinkDirection();

        int getLinkType();

        int getRoadClass();

        int getRoadDirection();

        V2_Point getV2Points(int i);

        int getV2PointsCount();

        List<V2_Point> getV2PointsList();

        boolean hasHeightDiffData();
    }

    /* loaded from: classes.dex */
    public interface NaviConfirmOrBuilder extends MessageLiteOrBuilder {
        long getNavigationId();
    }

    /* loaded from: classes.dex */
    public interface NaviRoutingInfoOrBuilder extends MessageLiteOrBuilder {
        long getNavigationId();

        NaviStatus getNavigationStatus();

        int getNavigationStatusValue();

        V2_Point getV2Points(int i);

        int getV2PointsCount();

        List<V2_Point> getV2PointsList();
    }

    /* loaded from: classes.dex */
    public interface NaviRoutingInfoV2OrBuilder extends MessageLiteOrBuilder {
        long getNavigationId();

        NaviStatus getNavigationStatus();

        int getNavigationStatusValue();

        Path getPath();

        String getSdkMajorVersion();

        ByteString getSdkMajorVersionBytes();

        String getSdkMinorVersion();

        ByteString getSdkMinorVersionBytes();

        boolean hasPath();
    }

    /* loaded from: classes.dex */
    public interface PathOrBuilder extends MessageLiteOrBuilder {
        Camera getCameras(int i);

        int getCamerasCount();

        List<Camera> getCamerasList();

        long getDataVersion();

        double getLength();

        Segment getSegments(int i);

        int getSegmentsCount();

        List<Segment> getSegmentsList();

        V2_Point getTrafficLights(int i);

        int getTrafficLightsCount();

        List<V2_Point> getTrafficLightsList();

        ViaPointInfo getViaPointInfos(int i);

        int getViaPointInfosCount();

        List<ViaPointInfo> getViaPointInfosList();
    }

    /* loaded from: classes.dex */
    public interface SegmentOrBuilder extends MessageLiteOrBuilder {
        double getLength();

        Link getLinks(int i);

        int getLinksCount();

        List<Link> getLinksList();
    }

    /* loaded from: classes.dex */
    public interface V2_PointOrBuilder extends MessageLiteOrBuilder {
        float getLat();

        float getLon();
    }

    /* loaded from: classes.dex */
    public interface VehLocationInfoRespOrBuilder extends MessageLiteOrBuilder {
        float getXPULocatCurSpd();

        float getXPULocatPitch();

        float getXPULocatRoll();

        float getXPULocatS();

        float getXPULocatTheta();

        float getXPULocatX();

        float getXPULocatY();

        float getXPULocatZ();

        long getXPUTimestamp();
    }

    /* loaded from: classes.dex */
    public interface ViaPointInfoOrBuilder extends MessageLiteOrBuilder {
        int getDirection();

        V2_Point getProjective();

        V2_Point getShow();

        boolean hasProjective();

        boolean hasShow();
    }

    private XpuProtoMessage() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    /* loaded from: classes.dex */
    public enum NaviStatus implements Internal.EnumLite {
        NAVI_STATUS_INVALID(0),
        NAVI_STATUS_START(1),
        NAVI_STATUS_FINISH(2),
        UNRECOGNIZED(-1);
        
        public static final int NAVI_STATUS_FINISH_VALUE = 2;
        public static final int NAVI_STATUS_INVALID_VALUE = 0;
        public static final int NAVI_STATUS_START_VALUE = 1;
        private static final Internal.EnumLiteMap<NaviStatus> internalValueMap = new Internal.EnumLiteMap<NaviStatus>() { // from class: android.car.hardware.xpu.XpuProtoMessage.NaviStatus.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public NaviStatus findValueByNumber(int number) {
                return NaviStatus.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static NaviStatus valueOf(int value) {
            return forNumber(value);
        }

        public static NaviStatus forNumber(int value) {
            switch (value) {
                case 0:
                    return NAVI_STATUS_INVALID;
                case 1:
                    return NAVI_STATUS_START;
                case 2:
                    return NAVI_STATUS_FINISH;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<NaviStatus> internalGetValueMap() {
            return internalValueMap;
        }

        NaviStatus(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public static final class CNGPCityMapRequest extends GeneratedMessageLite<CNGPCityMapRequest, Builder> implements CNGPCityMapRequestOrBuilder {
        public static final int CITY_FIELD_NUMBER = 1;
        private static final CNGPCityMapRequest DEFAULT_INSTANCE = new CNGPCityMapRequest();
        private static volatile Parser<CNGPCityMapRequest> PARSER;
        private CityRequest city_;

        /* loaded from: classes.dex */
        public interface CityRequestOrBuilder extends MessageLiteOrBuilder {
            int getCityCode();

            int getReqType();
        }

        private CNGPCityMapRequest() {
        }

        /* loaded from: classes.dex */
        public static final class CityRequest extends GeneratedMessageLite<CityRequest, Builder> implements CityRequestOrBuilder {
            public static final int CITY_CODE_FIELD_NUMBER = 1;
            private static final CityRequest DEFAULT_INSTANCE = new CityRequest();
            private static volatile Parser<CityRequest> PARSER = null;
            public static final int REQ_TYPE_FIELD_NUMBER = 2;
            private int cityCode_ = 0;
            private int reqType_ = 0;

            private CityRequest() {
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapRequest.CityRequestOrBuilder
            public int getCityCode() {
                return this.cityCode_;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setCityCode(int value) {
                this.cityCode_ = value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearCityCode() {
                this.cityCode_ = 0;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapRequest.CityRequestOrBuilder
            public int getReqType() {
                return this.reqType_;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setReqType(int value) {
                this.reqType_ = value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearReqType() {
                this.reqType_ = 0;
            }

            @Override // com.android.carsdk.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if (this.cityCode_ != 0) {
                    output.writeInt32(1, this.cityCode_);
                }
                if (this.reqType_ != 0) {
                    output.writeInt32(2, this.reqType_);
                }
            }

            @Override // com.android.carsdk.protobuf.MessageLite
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                int size2 = this.cityCode_ != 0 ? 0 + CodedOutputStream.computeInt32Size(1, this.cityCode_) : 0;
                if (this.reqType_ != 0) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.reqType_);
                }
                this.memoizedSerializedSize = size2;
                return size2;
            }

            public static CityRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (CityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CityRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CityRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (CityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CityRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CityRequest parseFrom(InputStream input) throws IOException {
                return (CityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static CityRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static CityRequest parseDelimitedFrom(InputStream input) throws IOException {
                return (CityRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static CityRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CityRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static CityRequest parseFrom(CodedInputStream input) throws IOException {
                return (CityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static CityRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(CityRequest prototype) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
            }

            /* loaded from: classes.dex */
            public static final class Builder extends GeneratedMessageLite.Builder<CityRequest, Builder> implements CityRequestOrBuilder {
                private Builder() {
                    super(CityRequest.DEFAULT_INSTANCE);
                }

                @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapRequest.CityRequestOrBuilder
                public int getCityCode() {
                    return ((CityRequest) this.instance).getCityCode();
                }

                public Builder setCityCode(int value) {
                    copyOnWrite();
                    ((CityRequest) this.instance).setCityCode(value);
                    return this;
                }

                public Builder clearCityCode() {
                    copyOnWrite();
                    ((CityRequest) this.instance).clearCityCode();
                    return this;
                }

                @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapRequest.CityRequestOrBuilder
                public int getReqType() {
                    return ((CityRequest) this.instance).getReqType();
                }

                public Builder setReqType(int value) {
                    copyOnWrite();
                    ((CityRequest) this.instance).setReqType(value);
                    return this;
                }

                public Builder clearReqType() {
                    copyOnWrite();
                    ((CityRequest) this.instance).clearReqType();
                    return this;
                }
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // com.android.carsdk.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new CityRequest();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        CityRequest other = (CityRequest) arg1;
                        this.cityCode_ = visitor.visitInt(this.cityCode_ != 0, this.cityCode_, other.cityCode_ != 0, other.cityCode_);
                        boolean z = this.reqType_ != 0;
                        int i = this.reqType_;
                        done = other.reqType_ != 0;
                        this.reqType_ = visitor.visitInt(z, i, done, other.reqType_);
                        GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                        return this;
                    case MERGE_FROM_STREAM:
                        CodedInputStream input = (CodedInputStream) arg0;
                        ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                        while (!done) {
                            try {
                                int tag = input.readTag();
                                if (tag == 0) {
                                    done = true;
                                } else if (tag == 8) {
                                    this.cityCode_ = input.readInt32();
                                } else if (tag != 16) {
                                    if (!input.skipField(tag)) {
                                        done = true;
                                    }
                                } else {
                                    this.reqType_ = input.readInt32();
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
                            synchronized (CityRequest.class) {
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

            public static CityRequest getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<CityRequest> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapRequestOrBuilder
        public boolean hasCity() {
            return this.city_ != null;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapRequestOrBuilder
        public CityRequest getCity() {
            return this.city_ == null ? CityRequest.getDefaultInstance() : this.city_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCity(CityRequest value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.city_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCity(CityRequest.Builder builderForValue) {
            this.city_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCity(CityRequest value) {
            if (this.city_ != null && this.city_ != CityRequest.getDefaultInstance()) {
                this.city_ = CityRequest.newBuilder(this.city_).mergeFrom((CityRequest.Builder) value).buildPartial();
            } else {
                this.city_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCity() {
            this.city_ = null;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.city_ != null) {
                output.writeMessage(1, getCity());
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.city_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getCity()) : 0;
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static CNGPCityMapRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CNGPCityMapRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CNGPCityMapRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CNGPCityMapRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CNGPCityMapRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CNGPCityMapRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CNGPCityMapRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CNGPCityMapRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CNGPCityMapRequest parseFrom(InputStream input) throws IOException {
            return (CNGPCityMapRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CNGPCityMapRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CNGPCityMapRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CNGPCityMapRequest parseDelimitedFrom(InputStream input) throws IOException {
            return (CNGPCityMapRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CNGPCityMapRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CNGPCityMapRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CNGPCityMapRequest parseFrom(CodedInputStream input) throws IOException {
            return (CNGPCityMapRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CNGPCityMapRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CNGPCityMapRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CNGPCityMapRequest prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CNGPCityMapRequest, Builder> implements CNGPCityMapRequestOrBuilder {
            private Builder() {
                super(CNGPCityMapRequest.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapRequestOrBuilder
            public boolean hasCity() {
                return ((CNGPCityMapRequest) this.instance).hasCity();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapRequestOrBuilder
            public CityRequest getCity() {
                return ((CNGPCityMapRequest) this.instance).getCity();
            }

            public Builder setCity(CityRequest value) {
                copyOnWrite();
                ((CNGPCityMapRequest) this.instance).setCity(value);
                return this;
            }

            public Builder setCity(CityRequest.Builder builderForValue) {
                copyOnWrite();
                ((CNGPCityMapRequest) this.instance).setCity(builderForValue);
                return this;
            }

            public Builder mergeCity(CityRequest value) {
                copyOnWrite();
                ((CNGPCityMapRequest) this.instance).mergeCity(value);
                return this;
            }

            public Builder clearCity() {
                copyOnWrite();
                ((CNGPCityMapRequest) this.instance).clearCity();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new CNGPCityMapRequest();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    CNGPCityMapRequest other = (CNGPCityMapRequest) arg1;
                    this.city_ = (CityRequest) visitor.visitMessage(this.city_, other.city_);
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
                                CityRequest.Builder subBuilder = null;
                                if (this.city_ != null) {
                                    subBuilder = this.city_.toBuilder();
                                }
                                this.city_ = (CityRequest) input.readMessage(CityRequest.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((CityRequest.Builder) this.city_);
                                    this.city_ = (CityRequest) subBuilder.buildPartial();
                                }
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
                        synchronized (CNGPCityMapRequest.class) {
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

        public static CNGPCityMapRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CNGPCityMapRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class CNGPCityMapResponse extends GeneratedMessageLite<CNGPCityMapResponse, Builder> implements CNGPCityMapResponseOrBuilder {
        public static final int CITIES_FIELD_NUMBER = 1;
        private static final CNGPCityMapResponse DEFAULT_INSTANCE = new CNGPCityMapResponse();
        public static final int ERROR_CODE_FIELD_NUMBER = 2;
        private static volatile Parser<CNGPCityMapResponse> PARSER;
        private int bitField0_;
        private Internal.ProtobufList<CityResponse> cities_ = emptyProtobufList();
        private int errorCode_ = 0;

        /* loaded from: classes.dex */
        public interface CityResponseOrBuilder extends MessageLiteOrBuilder {
            int getCityCode();

            int getMapProgress();

            int getMapStatus();
        }

        private CNGPCityMapResponse() {
        }

        /* loaded from: classes.dex */
        public static final class CityResponse extends GeneratedMessageLite<CityResponse, Builder> implements CityResponseOrBuilder {
            public static final int CITY_CODE_FIELD_NUMBER = 1;
            private static final CityResponse DEFAULT_INSTANCE = new CityResponse();
            public static final int MAP_PROGRESS_FIELD_NUMBER = 3;
            public static final int MAP_STATUS_FIELD_NUMBER = 2;
            private static volatile Parser<CityResponse> PARSER;
            private int cityCode_ = 0;
            private int mapStatus_ = 0;
            private int mapProgress_ = 0;

            private CityResponse() {
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponse.CityResponseOrBuilder
            public int getCityCode() {
                return this.cityCode_;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setCityCode(int value) {
                this.cityCode_ = value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearCityCode() {
                this.cityCode_ = 0;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponse.CityResponseOrBuilder
            public int getMapStatus() {
                return this.mapStatus_;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setMapStatus(int value) {
                this.mapStatus_ = value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearMapStatus() {
                this.mapStatus_ = 0;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponse.CityResponseOrBuilder
            public int getMapProgress() {
                return this.mapProgress_;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setMapProgress(int value) {
                this.mapProgress_ = value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearMapProgress() {
                this.mapProgress_ = 0;
            }

            @Override // com.android.carsdk.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if (this.cityCode_ != 0) {
                    output.writeInt32(1, this.cityCode_);
                }
                if (this.mapStatus_ != 0) {
                    output.writeInt32(2, this.mapStatus_);
                }
                if (this.mapProgress_ != 0) {
                    output.writeInt32(3, this.mapProgress_);
                }
            }

            @Override // com.android.carsdk.protobuf.MessageLite
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                int size2 = this.cityCode_ != 0 ? 0 + CodedOutputStream.computeInt32Size(1, this.cityCode_) : 0;
                if (this.mapStatus_ != 0) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.mapStatus_);
                }
                if (this.mapProgress_ != 0) {
                    size2 += CodedOutputStream.computeInt32Size(3, this.mapProgress_);
                }
                this.memoizedSerializedSize = size2;
                return size2;
            }

            public static CityResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (CityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CityResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CityResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (CityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CityResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CityResponse parseFrom(InputStream input) throws IOException {
                return (CityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static CityResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static CityResponse parseDelimitedFrom(InputStream input) throws IOException {
                return (CityResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static CityResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CityResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static CityResponse parseFrom(CodedInputStream input) throws IOException {
                return (CityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static CityResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(CityResponse prototype) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
            }

            /* loaded from: classes.dex */
            public static final class Builder extends GeneratedMessageLite.Builder<CityResponse, Builder> implements CityResponseOrBuilder {
                private Builder() {
                    super(CityResponse.DEFAULT_INSTANCE);
                }

                @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponse.CityResponseOrBuilder
                public int getCityCode() {
                    return ((CityResponse) this.instance).getCityCode();
                }

                public Builder setCityCode(int value) {
                    copyOnWrite();
                    ((CityResponse) this.instance).setCityCode(value);
                    return this;
                }

                public Builder clearCityCode() {
                    copyOnWrite();
                    ((CityResponse) this.instance).clearCityCode();
                    return this;
                }

                @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponse.CityResponseOrBuilder
                public int getMapStatus() {
                    return ((CityResponse) this.instance).getMapStatus();
                }

                public Builder setMapStatus(int value) {
                    copyOnWrite();
                    ((CityResponse) this.instance).setMapStatus(value);
                    return this;
                }

                public Builder clearMapStatus() {
                    copyOnWrite();
                    ((CityResponse) this.instance).clearMapStatus();
                    return this;
                }

                @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponse.CityResponseOrBuilder
                public int getMapProgress() {
                    return ((CityResponse) this.instance).getMapProgress();
                }

                public Builder setMapProgress(int value) {
                    copyOnWrite();
                    ((CityResponse) this.instance).setMapProgress(value);
                    return this;
                }

                public Builder clearMapProgress() {
                    copyOnWrite();
                    ((CityResponse) this.instance).clearMapProgress();
                    return this;
                }
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // com.android.carsdk.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new CityResponse();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        CityResponse other = (CityResponse) arg1;
                        this.cityCode_ = visitor.visitInt(this.cityCode_ != 0, this.cityCode_, other.cityCode_ != 0, other.cityCode_);
                        this.mapStatus_ = visitor.visitInt(this.mapStatus_ != 0, this.mapStatus_, other.mapStatus_ != 0, other.mapStatus_);
                        boolean z = this.mapProgress_ != 0;
                        int i = this.mapProgress_;
                        done = other.mapProgress_ != 0;
                        this.mapProgress_ = visitor.visitInt(z, i, done, other.mapProgress_);
                        GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                        return this;
                    case MERGE_FROM_STREAM:
                        CodedInputStream input = (CodedInputStream) arg0;
                        ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                        while (!done) {
                            try {
                                int tag = input.readTag();
                                if (tag == 0) {
                                    done = true;
                                } else if (tag == 8) {
                                    this.cityCode_ = input.readInt32();
                                } else if (tag == 16) {
                                    this.mapStatus_ = input.readInt32();
                                } else if (tag != 24) {
                                    if (!input.skipField(tag)) {
                                        done = true;
                                    }
                                } else {
                                    this.mapProgress_ = input.readInt32();
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
                            synchronized (CityResponse.class) {
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

            public static CityResponse getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<CityResponse> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponseOrBuilder
        public List<CityResponse> getCitiesList() {
            return this.cities_;
        }

        public List<? extends CityResponseOrBuilder> getCitiesOrBuilderList() {
            return this.cities_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponseOrBuilder
        public int getCitiesCount() {
            return this.cities_.size();
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponseOrBuilder
        public CityResponse getCities(int index) {
            return this.cities_.get(index);
        }

        public CityResponseOrBuilder getCitiesOrBuilder(int index) {
            return this.cities_.get(index);
        }

        private void ensureCitiesIsMutable() {
            if (!this.cities_.isModifiable()) {
                this.cities_ = GeneratedMessageLite.mutableCopy(this.cities_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCities(int index, CityResponse value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureCitiesIsMutable();
            this.cities_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCities(int index, CityResponse.Builder builderForValue) {
            ensureCitiesIsMutable();
            this.cities_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCities(CityResponse value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureCitiesIsMutable();
            this.cities_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCities(int index, CityResponse value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureCitiesIsMutable();
            this.cities_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCities(CityResponse.Builder builderForValue) {
            ensureCitiesIsMutable();
            this.cities_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCities(int index, CityResponse.Builder builderForValue) {
            ensureCitiesIsMutable();
            this.cities_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllCities(Iterable<? extends CityResponse> values) {
            ensureCitiesIsMutable();
            AbstractMessageLite.addAll(values, this.cities_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCities() {
            this.cities_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeCities(int index) {
            ensureCitiesIsMutable();
            this.cities_.remove(index);
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponseOrBuilder
        public int getErrorCode() {
            return this.errorCode_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorCode(int value) {
            this.errorCode_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearErrorCode() {
            this.errorCode_ = 0;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.cities_.size(); i++) {
                output.writeMessage(1, this.cities_.get(i));
            }
            int i2 = this.errorCode_;
            if (i2 != 0) {
                output.writeInt32(2, this.errorCode_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.cities_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.cities_.get(i));
            }
            int i2 = this.errorCode_;
            if (i2 != 0) {
                size2 += CodedOutputStream.computeInt32Size(2, this.errorCode_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static CNGPCityMapResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CNGPCityMapResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CNGPCityMapResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CNGPCityMapResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CNGPCityMapResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CNGPCityMapResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CNGPCityMapResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CNGPCityMapResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CNGPCityMapResponse parseFrom(InputStream input) throws IOException {
            return (CNGPCityMapResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CNGPCityMapResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CNGPCityMapResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CNGPCityMapResponse parseDelimitedFrom(InputStream input) throws IOException {
            return (CNGPCityMapResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CNGPCityMapResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CNGPCityMapResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CNGPCityMapResponse parseFrom(CodedInputStream input) throws IOException {
            return (CNGPCityMapResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CNGPCityMapResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CNGPCityMapResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CNGPCityMapResponse prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CNGPCityMapResponse, Builder> implements CNGPCityMapResponseOrBuilder {
            private Builder() {
                super(CNGPCityMapResponse.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponseOrBuilder
            public List<CityResponse> getCitiesList() {
                return Collections.unmodifiableList(((CNGPCityMapResponse) this.instance).getCitiesList());
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponseOrBuilder
            public int getCitiesCount() {
                return ((CNGPCityMapResponse) this.instance).getCitiesCount();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponseOrBuilder
            public CityResponse getCities(int index) {
                return ((CNGPCityMapResponse) this.instance).getCities(index);
            }

            public Builder setCities(int index, CityResponse value) {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).setCities(index, value);
                return this;
            }

            public Builder setCities(int index, CityResponse.Builder builderForValue) {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).setCities(index, builderForValue);
                return this;
            }

            public Builder addCities(CityResponse value) {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).addCities(value);
                return this;
            }

            public Builder addCities(int index, CityResponse value) {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).addCities(index, value);
                return this;
            }

            public Builder addCities(CityResponse.Builder builderForValue) {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).addCities(builderForValue);
                return this;
            }

            public Builder addCities(int index, CityResponse.Builder builderForValue) {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).addCities(index, builderForValue);
                return this;
            }

            public Builder addAllCities(Iterable<? extends CityResponse> values) {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).addAllCities(values);
                return this;
            }

            public Builder clearCities() {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).clearCities();
                return this;
            }

            public Builder removeCities(int index) {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).removeCities(index);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapResponseOrBuilder
            public int getErrorCode() {
                return ((CNGPCityMapResponse) this.instance).getErrorCode();
            }

            public Builder setErrorCode(int value) {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).setErrorCode(value);
                return this;
            }

            public Builder clearErrorCode() {
                copyOnWrite();
                ((CNGPCityMapResponse) this.instance).clearErrorCode();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new CNGPCityMapResponse();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.cities_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    CNGPCityMapResponse other = (CNGPCityMapResponse) arg1;
                    this.cities_ = visitor.visitList(this.cities_, other.cities_);
                    boolean z = this.errorCode_ != 0;
                    int i = this.errorCode_;
                    done = other.errorCode_ != 0;
                    this.errorCode_ = visitor.visitInt(z, i, done, other.errorCode_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                if (!this.cities_.isModifiable()) {
                                    this.cities_ = GeneratedMessageLite.mutableCopy(this.cities_);
                                }
                                this.cities_.add((CityResponse) input.readMessage(CityResponse.parser(), extensionRegistry));
                            } else if (tag != 16) {
                                if (!input.skipField(tag)) {
                                    done = true;
                                }
                            } else {
                                this.errorCode_ = input.readInt32();
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
                        synchronized (CNGPCityMapResponse.class) {
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

        public static CNGPCityMapResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CNGPCityMapResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class CNGPCityMapFinNotify extends GeneratedMessageLite<CNGPCityMapFinNotify, Builder> implements CNGPCityMapFinNotifyOrBuilder {
        public static final int CITY_CODE_FIELD_NUMBER = 1;
        private static final CNGPCityMapFinNotify DEFAULT_INSTANCE = new CNGPCityMapFinNotify();
        private static volatile Parser<CNGPCityMapFinNotify> PARSER = null;
        public static final int RESULT_FIELD_NUMBER = 2;
        private int cityCode_ = 0;
        private int result_ = 0;

        private CNGPCityMapFinNotify() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapFinNotifyOrBuilder
        public int getCityCode() {
            return this.cityCode_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCityCode(int value) {
            this.cityCode_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCityCode() {
            this.cityCode_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapFinNotifyOrBuilder
        public int getResult() {
            return this.result_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResult(int value) {
            this.result_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResult() {
            this.result_ = 0;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.cityCode_ != 0) {
                output.writeInt32(1, this.cityCode_);
            }
            if (this.result_ != 0) {
                output.writeInt32(2, this.result_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.cityCode_ != 0 ? 0 + CodedOutputStream.computeInt32Size(1, this.cityCode_) : 0;
            if (this.result_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(2, this.result_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static CNGPCityMapFinNotify parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CNGPCityMapFinNotify) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CNGPCityMapFinNotify parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CNGPCityMapFinNotify) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CNGPCityMapFinNotify parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CNGPCityMapFinNotify) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CNGPCityMapFinNotify parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CNGPCityMapFinNotify) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CNGPCityMapFinNotify parseFrom(InputStream input) throws IOException {
            return (CNGPCityMapFinNotify) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CNGPCityMapFinNotify parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CNGPCityMapFinNotify) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CNGPCityMapFinNotify parseDelimitedFrom(InputStream input) throws IOException {
            return (CNGPCityMapFinNotify) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CNGPCityMapFinNotify parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CNGPCityMapFinNotify) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CNGPCityMapFinNotify parseFrom(CodedInputStream input) throws IOException {
            return (CNGPCityMapFinNotify) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CNGPCityMapFinNotify parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CNGPCityMapFinNotify) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CNGPCityMapFinNotify prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CNGPCityMapFinNotify, Builder> implements CNGPCityMapFinNotifyOrBuilder {
            private Builder() {
                super(CNGPCityMapFinNotify.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapFinNotifyOrBuilder
            public int getCityCode() {
                return ((CNGPCityMapFinNotify) this.instance).getCityCode();
            }

            public Builder setCityCode(int value) {
                copyOnWrite();
                ((CNGPCityMapFinNotify) this.instance).setCityCode(value);
                return this;
            }

            public Builder clearCityCode() {
                copyOnWrite();
                ((CNGPCityMapFinNotify) this.instance).clearCityCode();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CNGPCityMapFinNotifyOrBuilder
            public int getResult() {
                return ((CNGPCityMapFinNotify) this.instance).getResult();
            }

            public Builder setResult(int value) {
                copyOnWrite();
                ((CNGPCityMapFinNotify) this.instance).setResult(value);
                return this;
            }

            public Builder clearResult() {
                copyOnWrite();
                ((CNGPCityMapFinNotify) this.instance).clearResult();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new CNGPCityMapFinNotify();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    CNGPCityMapFinNotify other = (CNGPCityMapFinNotify) arg1;
                    this.cityCode_ = visitor.visitInt(this.cityCode_ != 0, this.cityCode_, other.cityCode_ != 0, other.cityCode_);
                    boolean z = this.result_ != 0;
                    int i = this.result_;
                    done = other.result_ != 0;
                    this.result_ = visitor.visitInt(z, i, done, other.result_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.cityCode_ = input.readInt32();
                            } else if (tag != 16) {
                                if (!input.skipField(tag)) {
                                    done = true;
                                }
                            } else {
                                this.result_ = input.readInt32();
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
                        synchronized (CNGPCityMapFinNotify.class) {
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

        public static CNGPCityMapFinNotify getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CNGPCityMapFinNotify> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class VehLocationInfoResp extends GeneratedMessageLite<VehLocationInfoResp, Builder> implements VehLocationInfoRespOrBuilder {
        private static final VehLocationInfoResp DEFAULT_INSTANCE = new VehLocationInfoResp();
        private static volatile Parser<VehLocationInfoResp> PARSER = null;
        public static final int XPU_LOCAT_CURSPD_FIELD_NUMBER = 9;
        public static final int XPU_LOCAT_PITCH_FIELD_NUMBER = 8;
        public static final int XPU_LOCAT_ROLL_FIELD_NUMBER = 7;
        public static final int XPU_LOCAT_S_FIELD_NUMBER = 6;
        public static final int XPU_LOCAT_THETA_FIELD_NUMBER = 5;
        public static final int XPU_LOCAT_X_FIELD_NUMBER = 2;
        public static final int XPU_LOCAT_Y_FIELD_NUMBER = 3;
        public static final int XPU_LOCAT_Z_FIELD_NUMBER = 4;
        public static final int XPU_TIMESTAMP_FIELD_NUMBER = 1;
        private long xPUTimestamp_ = 0;
        private float xPULocatX_ = 0.0f;
        private float xPULocatY_ = 0.0f;
        private float xPULocatZ_ = 0.0f;
        private float xPULocatTheta_ = 0.0f;
        private float xPULocatS_ = 0.0f;
        private float xPULocatRoll_ = 0.0f;
        private float xPULocatPitch_ = 0.0f;
        private float xPULocatCurSpd_ = 0.0f;

        private VehLocationInfoResp() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
        public long getXPUTimestamp() {
            return this.xPUTimestamp_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setXPUTimestamp(long value) {
            this.xPUTimestamp_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearXPUTimestamp() {
            this.xPUTimestamp_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
        public float getXPULocatX() {
            return this.xPULocatX_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setXPULocatX(float value) {
            this.xPULocatX_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearXPULocatX() {
            this.xPULocatX_ = 0.0f;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
        public float getXPULocatY() {
            return this.xPULocatY_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setXPULocatY(float value) {
            this.xPULocatY_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearXPULocatY() {
            this.xPULocatY_ = 0.0f;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
        public float getXPULocatZ() {
            return this.xPULocatZ_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setXPULocatZ(float value) {
            this.xPULocatZ_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearXPULocatZ() {
            this.xPULocatZ_ = 0.0f;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
        public float getXPULocatTheta() {
            return this.xPULocatTheta_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setXPULocatTheta(float value) {
            this.xPULocatTheta_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearXPULocatTheta() {
            this.xPULocatTheta_ = 0.0f;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
        public float getXPULocatS() {
            return this.xPULocatS_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setXPULocatS(float value) {
            this.xPULocatS_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearXPULocatS() {
            this.xPULocatS_ = 0.0f;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
        public float getXPULocatRoll() {
            return this.xPULocatRoll_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setXPULocatRoll(float value) {
            this.xPULocatRoll_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearXPULocatRoll() {
            this.xPULocatRoll_ = 0.0f;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
        public float getXPULocatPitch() {
            return this.xPULocatPitch_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setXPULocatPitch(float value) {
            this.xPULocatPitch_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearXPULocatPitch() {
            this.xPULocatPitch_ = 0.0f;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
        public float getXPULocatCurSpd() {
            return this.xPULocatCurSpd_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setXPULocatCurSpd(float value) {
            this.xPULocatCurSpd_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearXPULocatCurSpd() {
            this.xPULocatCurSpd_ = 0.0f;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.xPUTimestamp_ != 0) {
                output.writeUInt64(1, this.xPUTimestamp_);
            }
            if (this.xPULocatX_ != 0.0f) {
                output.writeFloat(2, this.xPULocatX_);
            }
            if (this.xPULocatY_ != 0.0f) {
                output.writeFloat(3, this.xPULocatY_);
            }
            if (this.xPULocatZ_ != 0.0f) {
                output.writeFloat(4, this.xPULocatZ_);
            }
            if (this.xPULocatTheta_ != 0.0f) {
                output.writeFloat(5, this.xPULocatTheta_);
            }
            if (this.xPULocatS_ != 0.0f) {
                output.writeFloat(6, this.xPULocatS_);
            }
            if (this.xPULocatRoll_ != 0.0f) {
                output.writeFloat(7, this.xPULocatRoll_);
            }
            if (this.xPULocatPitch_ != 0.0f) {
                output.writeFloat(8, this.xPULocatPitch_);
            }
            if (this.xPULocatCurSpd_ != 0.0f) {
                output.writeFloat(9, this.xPULocatCurSpd_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.xPUTimestamp_ != 0 ? 0 + CodedOutputStream.computeUInt64Size(1, this.xPUTimestamp_) : 0;
            if (this.xPULocatX_ != 0.0f) {
                size2 += CodedOutputStream.computeFloatSize(2, this.xPULocatX_);
            }
            if (this.xPULocatY_ != 0.0f) {
                size2 += CodedOutputStream.computeFloatSize(3, this.xPULocatY_);
            }
            if (this.xPULocatZ_ != 0.0f) {
                size2 += CodedOutputStream.computeFloatSize(4, this.xPULocatZ_);
            }
            if (this.xPULocatTheta_ != 0.0f) {
                size2 += CodedOutputStream.computeFloatSize(5, this.xPULocatTheta_);
            }
            if (this.xPULocatS_ != 0.0f) {
                size2 += CodedOutputStream.computeFloatSize(6, this.xPULocatS_);
            }
            if (this.xPULocatRoll_ != 0.0f) {
                size2 += CodedOutputStream.computeFloatSize(7, this.xPULocatRoll_);
            }
            if (this.xPULocatPitch_ != 0.0f) {
                size2 += CodedOutputStream.computeFloatSize(8, this.xPULocatPitch_);
            }
            if (this.xPULocatCurSpd_ != 0.0f) {
                size2 += CodedOutputStream.computeFloatSize(9, this.xPULocatCurSpd_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static VehLocationInfoResp parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (VehLocationInfoResp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static VehLocationInfoResp parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (VehLocationInfoResp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static VehLocationInfoResp parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (VehLocationInfoResp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static VehLocationInfoResp parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (VehLocationInfoResp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static VehLocationInfoResp parseFrom(InputStream input) throws IOException {
            return (VehLocationInfoResp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static VehLocationInfoResp parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (VehLocationInfoResp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static VehLocationInfoResp parseDelimitedFrom(InputStream input) throws IOException {
            return (VehLocationInfoResp) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static VehLocationInfoResp parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (VehLocationInfoResp) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static VehLocationInfoResp parseFrom(CodedInputStream input) throws IOException {
            return (VehLocationInfoResp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static VehLocationInfoResp parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (VehLocationInfoResp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(VehLocationInfoResp prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<VehLocationInfoResp, Builder> implements VehLocationInfoRespOrBuilder {
            private Builder() {
                super(VehLocationInfoResp.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
            public long getXPUTimestamp() {
                return ((VehLocationInfoResp) this.instance).getXPUTimestamp();
            }

            public Builder setXPUTimestamp(long value) {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).setXPUTimestamp(value);
                return this;
            }

            public Builder clearXPUTimestamp() {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).clearXPUTimestamp();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
            public float getXPULocatX() {
                return ((VehLocationInfoResp) this.instance).getXPULocatX();
            }

            public Builder setXPULocatX(float value) {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).setXPULocatX(value);
                return this;
            }

            public Builder clearXPULocatX() {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).clearXPULocatX();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
            public float getXPULocatY() {
                return ((VehLocationInfoResp) this.instance).getXPULocatY();
            }

            public Builder setXPULocatY(float value) {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).setXPULocatY(value);
                return this;
            }

            public Builder clearXPULocatY() {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).clearXPULocatY();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
            public float getXPULocatZ() {
                return ((VehLocationInfoResp) this.instance).getXPULocatZ();
            }

            public Builder setXPULocatZ(float value) {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).setXPULocatZ(value);
                return this;
            }

            public Builder clearXPULocatZ() {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).clearXPULocatZ();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
            public float getXPULocatTheta() {
                return ((VehLocationInfoResp) this.instance).getXPULocatTheta();
            }

            public Builder setXPULocatTheta(float value) {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).setXPULocatTheta(value);
                return this;
            }

            public Builder clearXPULocatTheta() {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).clearXPULocatTheta();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
            public float getXPULocatS() {
                return ((VehLocationInfoResp) this.instance).getXPULocatS();
            }

            public Builder setXPULocatS(float value) {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).setXPULocatS(value);
                return this;
            }

            public Builder clearXPULocatS() {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).clearXPULocatS();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
            public float getXPULocatRoll() {
                return ((VehLocationInfoResp) this.instance).getXPULocatRoll();
            }

            public Builder setXPULocatRoll(float value) {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).setXPULocatRoll(value);
                return this;
            }

            public Builder clearXPULocatRoll() {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).clearXPULocatRoll();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
            public float getXPULocatPitch() {
                return ((VehLocationInfoResp) this.instance).getXPULocatPitch();
            }

            public Builder setXPULocatPitch(float value) {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).setXPULocatPitch(value);
                return this;
            }

            public Builder clearXPULocatPitch() {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).clearXPULocatPitch();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.VehLocationInfoRespOrBuilder
            public float getXPULocatCurSpd() {
                return ((VehLocationInfoResp) this.instance).getXPULocatCurSpd();
            }

            public Builder setXPULocatCurSpd(float value) {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).setXPULocatCurSpd(value);
                return this;
            }

            public Builder clearXPULocatCurSpd() {
                copyOnWrite();
                ((VehLocationInfoResp) this.instance).clearXPULocatCurSpd();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new VehLocationInfoResp();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    VehLocationInfoResp other = (VehLocationInfoResp) arg1;
                    this.xPUTimestamp_ = visitor.visitLong(this.xPUTimestamp_ != 0, this.xPUTimestamp_, other.xPUTimestamp_ != 0, other.xPUTimestamp_);
                    this.xPULocatX_ = visitor.visitFloat(this.xPULocatX_ != 0.0f, this.xPULocatX_, other.xPULocatX_ != 0.0f, other.xPULocatX_);
                    this.xPULocatY_ = visitor.visitFloat(this.xPULocatY_ != 0.0f, this.xPULocatY_, other.xPULocatY_ != 0.0f, other.xPULocatY_);
                    this.xPULocatZ_ = visitor.visitFloat(this.xPULocatZ_ != 0.0f, this.xPULocatZ_, other.xPULocatZ_ != 0.0f, other.xPULocatZ_);
                    this.xPULocatTheta_ = visitor.visitFloat(this.xPULocatTheta_ != 0.0f, this.xPULocatTheta_, other.xPULocatTheta_ != 0.0f, other.xPULocatTheta_);
                    this.xPULocatS_ = visitor.visitFloat(this.xPULocatS_ != 0.0f, this.xPULocatS_, other.xPULocatS_ != 0.0f, other.xPULocatS_);
                    this.xPULocatRoll_ = visitor.visitFloat(this.xPULocatRoll_ != 0.0f, this.xPULocatRoll_, other.xPULocatRoll_ != 0.0f, other.xPULocatRoll_);
                    this.xPULocatPitch_ = visitor.visitFloat(this.xPULocatPitch_ != 0.0f, this.xPULocatPitch_, other.xPULocatPitch_ != 0.0f, other.xPULocatPitch_);
                    this.xPULocatCurSpd_ = visitor.visitFloat(this.xPULocatCurSpd_ != 0.0f, this.xPULocatCurSpd_, other.xPULocatCurSpd_ != 0.0f, other.xPULocatCurSpd_);
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
                                    } else if (tag == 8) {
                                        this.xPUTimestamp_ = input.readUInt64();
                                    } else if (tag == 21) {
                                        this.xPULocatX_ = input.readFloat();
                                    } else if (tag == 29) {
                                        this.xPULocatY_ = input.readFloat();
                                    } else if (tag == 37) {
                                        this.xPULocatZ_ = input.readFloat();
                                    } else if (tag == 45) {
                                        this.xPULocatTheta_ = input.readFloat();
                                    } else if (tag == 53) {
                                        this.xPULocatS_ = input.readFloat();
                                    } else if (tag == 61) {
                                        this.xPULocatRoll_ = input.readFloat();
                                    } else if (tag == 69) {
                                        this.xPULocatPitch_ = input.readFloat();
                                    } else if (tag != 77) {
                                        if (!input.skipField(tag)) {
                                            done2 = true;
                                        }
                                    } else {
                                        this.xPULocatCurSpd_ = input.readFloat();
                                    }
                                    done = done2;
                                } catch (IOException e) {
                                    throw new RuntimeException(new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this));
                                }
                            } catch (InvalidProtocolBufferException e2) {
                                throw new RuntimeException(e2.setUnfinishedMessage(this));
                            }
                        }
                    }
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (VehLocationInfoResp.class) {
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

        public static VehLocationInfoResp getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<VehLocationInfoResp> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class NaviConfirm extends GeneratedMessageLite<NaviConfirm, Builder> implements NaviConfirmOrBuilder {
        private static final NaviConfirm DEFAULT_INSTANCE = new NaviConfirm();
        public static final int NAVIGATION_ID_FIELD_NUMBER = 1;
        private static volatile Parser<NaviConfirm> PARSER;
        private long navigationId_ = 0;

        private NaviConfirm() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviConfirmOrBuilder
        public long getNavigationId() {
            return this.navigationId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNavigationId(long value) {
            this.navigationId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNavigationId() {
            this.navigationId_ = 0L;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.navigationId_ != 0) {
                output.writeUInt64(1, this.navigationId_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.navigationId_ != 0 ? 0 + CodedOutputStream.computeUInt64Size(1, this.navigationId_) : 0;
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static NaviConfirm parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (NaviConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static NaviConfirm parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (NaviConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static NaviConfirm parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (NaviConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static NaviConfirm parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (NaviConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static NaviConfirm parseFrom(InputStream input) throws IOException {
            return (NaviConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviConfirm parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static NaviConfirm parseDelimitedFrom(InputStream input) throws IOException {
            return (NaviConfirm) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviConfirm parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviConfirm) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static NaviConfirm parseFrom(CodedInputStream input) throws IOException {
            return (NaviConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviConfirm parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(NaviConfirm prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NaviConfirm, Builder> implements NaviConfirmOrBuilder {
            private Builder() {
                super(NaviConfirm.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviConfirmOrBuilder
            public long getNavigationId() {
                return ((NaviConfirm) this.instance).getNavigationId();
            }

            public Builder setNavigationId(long value) {
                copyOnWrite();
                ((NaviConfirm) this.instance).setNavigationId(value);
                return this;
            }

            public Builder clearNavigationId() {
                copyOnWrite();
                ((NaviConfirm) this.instance).clearNavigationId();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new NaviConfirm();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    NaviConfirm other = (NaviConfirm) arg1;
                    this.navigationId_ = visitor.visitLong(this.navigationId_ != 0, this.navigationId_, other.navigationId_ != 0, other.navigationId_);
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
                                int tag = input.readTag();
                                if (tag == 0) {
                                    done2 = true;
                                } else if (tag != 8) {
                                    if (!input.skipField(tag)) {
                                        done2 = true;
                                    }
                                } else {
                                    this.navigationId_ = input.readUInt64();
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
                        synchronized (NaviConfirm.class) {
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

        public static NaviConfirm getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NaviConfirm> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class V2_Point extends GeneratedMessageLite<V2_Point, Builder> implements V2_PointOrBuilder {
        private static final V2_Point DEFAULT_INSTANCE = new V2_Point();
        public static final int LAT_FIELD_NUMBER = 1;
        public static final int LON_FIELD_NUMBER = 2;
        private static volatile Parser<V2_Point> PARSER;
        private float lat_ = 0.0f;
        private float lon_ = 0.0f;

        private V2_Point() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.V2_PointOrBuilder
        public float getLat() {
            return this.lat_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLat(float value) {
            this.lat_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLat() {
            this.lat_ = 0.0f;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.V2_PointOrBuilder
        public float getLon() {
            return this.lon_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLon(float value) {
            this.lon_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLon() {
            this.lon_ = 0.0f;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.lat_ != 0.0f) {
                output.writeFloat(1, this.lat_);
            }
            if (this.lon_ != 0.0f) {
                output.writeFloat(2, this.lon_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.lat_ != 0.0f ? 0 + CodedOutputStream.computeFloatSize(1, this.lat_) : 0;
            if (this.lon_ != 0.0f) {
                size2 += CodedOutputStream.computeFloatSize(2, this.lon_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static V2_Point parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (V2_Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static V2_Point parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (V2_Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static V2_Point parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (V2_Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static V2_Point parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (V2_Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static V2_Point parseFrom(InputStream input) throws IOException {
            return (V2_Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static V2_Point parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (V2_Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static V2_Point parseDelimitedFrom(InputStream input) throws IOException {
            return (V2_Point) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static V2_Point parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (V2_Point) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static V2_Point parseFrom(CodedInputStream input) throws IOException {
            return (V2_Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static V2_Point parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (V2_Point) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(V2_Point prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<V2_Point, Builder> implements V2_PointOrBuilder {
            private Builder() {
                super(V2_Point.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.V2_PointOrBuilder
            public float getLat() {
                return ((V2_Point) this.instance).getLat();
            }

            public Builder setLat(float value) {
                copyOnWrite();
                ((V2_Point) this.instance).setLat(value);
                return this;
            }

            public Builder clearLat() {
                copyOnWrite();
                ((V2_Point) this.instance).clearLat();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.V2_PointOrBuilder
            public float getLon() {
                return ((V2_Point) this.instance).getLon();
            }

            public Builder setLon(float value) {
                copyOnWrite();
                ((V2_Point) this.instance).setLon(value);
                return this;
            }

            public Builder clearLon() {
                copyOnWrite();
                ((V2_Point) this.instance).clearLon();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new V2_Point();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    V2_Point other = (V2_Point) arg1;
                    this.lat_ = visitor.visitFloat(this.lat_ != 0.0f, this.lat_, other.lat_ != 0.0f, other.lat_);
                    boolean z = this.lon_ != 0.0f;
                    float f = this.lon_;
                    done = other.lon_ != 0.0f;
                    this.lon_ = visitor.visitFloat(z, f, done, other.lon_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 13) {
                                this.lat_ = input.readFloat();
                            } else if (tag != 21) {
                                if (!input.skipField(tag)) {
                                    done = true;
                                }
                            } else {
                                this.lon_ = input.readFloat();
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
                        synchronized (V2_Point.class) {
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

        public static V2_Point getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<V2_Point> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class HeightDiffData extends GeneratedMessageLite<HeightDiffData, Builder> implements HeightDiffDataOrBuilder {
        private static final HeightDiffData DEFAULT_INSTANCE = new HeightDiffData();
        public static final int DIST_TO_LINK_START_FIELD_NUMBER = 1;
        public static final int HEIGHT_DIFF_DIST_FIELD_NUMBER = 2;
        public static final int HEIGHT_DIFF_PARELLEL_FIELD_NUMBER = 4;
        public static final int HEIGHT_DIFF_STRAIGHT_FIELD_NUMBER = 3;
        private static volatile Parser<HeightDiffData> PARSER;
        private int distToLinkStart_ = 0;
        private int heightDiffDist_ = 0;
        private int heightDiffStraight_ = 0;
        private int heightDiffParellel_ = 0;

        private HeightDiffData() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.HeightDiffDataOrBuilder
        public int getDistToLinkStart() {
            return this.distToLinkStart_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDistToLinkStart(int value) {
            this.distToLinkStart_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDistToLinkStart() {
            this.distToLinkStart_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.HeightDiffDataOrBuilder
        public int getHeightDiffDist() {
            return this.heightDiffDist_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeightDiffDist(int value) {
            this.heightDiffDist_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeightDiffDist() {
            this.heightDiffDist_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.HeightDiffDataOrBuilder
        public int getHeightDiffStraight() {
            return this.heightDiffStraight_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeightDiffStraight(int value) {
            this.heightDiffStraight_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeightDiffStraight() {
            this.heightDiffStraight_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.HeightDiffDataOrBuilder
        public int getHeightDiffParellel() {
            return this.heightDiffParellel_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeightDiffParellel(int value) {
            this.heightDiffParellel_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeightDiffParellel() {
            this.heightDiffParellel_ = 0;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.distToLinkStart_ != 0) {
                output.writeInt32(1, this.distToLinkStart_);
            }
            if (this.heightDiffDist_ != 0) {
                output.writeInt32(2, this.heightDiffDist_);
            }
            if (this.heightDiffStraight_ != 0) {
                output.writeInt32(3, this.heightDiffStraight_);
            }
            if (this.heightDiffParellel_ != 0) {
                output.writeInt32(4, this.heightDiffParellel_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.distToLinkStart_ != 0 ? 0 + CodedOutputStream.computeInt32Size(1, this.distToLinkStart_) : 0;
            if (this.heightDiffDist_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(2, this.heightDiffDist_);
            }
            if (this.heightDiffStraight_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(3, this.heightDiffStraight_);
            }
            if (this.heightDiffParellel_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(4, this.heightDiffParellel_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static HeightDiffData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (HeightDiffData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeightDiffData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeightDiffData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeightDiffData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (HeightDiffData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeightDiffData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeightDiffData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeightDiffData parseFrom(InputStream input) throws IOException {
            return (HeightDiffData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HeightDiffData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeightDiffData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HeightDiffData parseDelimitedFrom(InputStream input) throws IOException {
            return (HeightDiffData) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static HeightDiffData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeightDiffData) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HeightDiffData parseFrom(CodedInputStream input) throws IOException {
            return (HeightDiffData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HeightDiffData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeightDiffData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(HeightDiffData prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<HeightDiffData, Builder> implements HeightDiffDataOrBuilder {
            private Builder() {
                super(HeightDiffData.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.HeightDiffDataOrBuilder
            public int getDistToLinkStart() {
                return ((HeightDiffData) this.instance).getDistToLinkStart();
            }

            public Builder setDistToLinkStart(int value) {
                copyOnWrite();
                ((HeightDiffData) this.instance).setDistToLinkStart(value);
                return this;
            }

            public Builder clearDistToLinkStart() {
                copyOnWrite();
                ((HeightDiffData) this.instance).clearDistToLinkStart();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.HeightDiffDataOrBuilder
            public int getHeightDiffDist() {
                return ((HeightDiffData) this.instance).getHeightDiffDist();
            }

            public Builder setHeightDiffDist(int value) {
                copyOnWrite();
                ((HeightDiffData) this.instance).setHeightDiffDist(value);
                return this;
            }

            public Builder clearHeightDiffDist() {
                copyOnWrite();
                ((HeightDiffData) this.instance).clearHeightDiffDist();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.HeightDiffDataOrBuilder
            public int getHeightDiffStraight() {
                return ((HeightDiffData) this.instance).getHeightDiffStraight();
            }

            public Builder setHeightDiffStraight(int value) {
                copyOnWrite();
                ((HeightDiffData) this.instance).setHeightDiffStraight(value);
                return this;
            }

            public Builder clearHeightDiffStraight() {
                copyOnWrite();
                ((HeightDiffData) this.instance).clearHeightDiffStraight();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.HeightDiffDataOrBuilder
            public int getHeightDiffParellel() {
                return ((HeightDiffData) this.instance).getHeightDiffParellel();
            }

            public Builder setHeightDiffParellel(int value) {
                copyOnWrite();
                ((HeightDiffData) this.instance).setHeightDiffParellel(value);
                return this;
            }

            public Builder clearHeightDiffParellel() {
                copyOnWrite();
                ((HeightDiffData) this.instance).clearHeightDiffParellel();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new HeightDiffData();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    HeightDiffData other = (HeightDiffData) arg1;
                    this.distToLinkStart_ = visitor.visitInt(this.distToLinkStart_ != 0, this.distToLinkStart_, other.distToLinkStart_ != 0, other.distToLinkStart_);
                    this.heightDiffDist_ = visitor.visitInt(this.heightDiffDist_ != 0, this.heightDiffDist_, other.heightDiffDist_ != 0, other.heightDiffDist_);
                    this.heightDiffStraight_ = visitor.visitInt(this.heightDiffStraight_ != 0, this.heightDiffStraight_, other.heightDiffStraight_ != 0, other.heightDiffStraight_);
                    boolean z = this.heightDiffParellel_ != 0;
                    int i = this.heightDiffParellel_;
                    done = other.heightDiffParellel_ != 0;
                    this.heightDiffParellel_ = visitor.visitInt(z, i, done, other.heightDiffParellel_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.distToLinkStart_ = input.readInt32();
                            } else if (tag == 16) {
                                this.heightDiffDist_ = input.readInt32();
                            } else if (tag == 24) {
                                this.heightDiffStraight_ = input.readInt32();
                            } else if (tag != 32) {
                                if (!input.skipField(tag)) {
                                    done = true;
                                }
                            } else {
                                this.heightDiffParellel_ = input.readInt32();
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
                        synchronized (HeightDiffData.class) {
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

        public static HeightDiffData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HeightDiffData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class Link extends GeneratedMessageLite<Link, Builder> implements LinkOrBuilder {
        private static final Link DEFAULT_INSTANCE = new Link();
        public static final int FORMWAY_TYPE_FIELD_NUMBER = 3;
        public static final int HAS_PARALLEL_ROAD_FIELD_NUMBER = 6;
        public static final int HEIGHT_DIFF_DATA_FIELD_NUMBER = 10;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int IS_OVER_HEAD_FIELD_NUMBER = 5;
        public static final int LANE_NUM_FIELD_NUMBER = 9;
        public static final int LENGTH_FIELD_NUMBER = 12;
        public static final int LINK_DIRECTION_FIELD_NUMBER = 7;
        public static final int LINK_TYPE_FIELD_NUMBER = 2;
        private static volatile Parser<Link> PARSER = null;
        public static final int ROAD_CLASS_FIELD_NUMBER = 4;
        public static final int ROAD_DIRECTION_FIELD_NUMBER = 8;
        public static final int V2_POINTS_FIELD_NUMBER = 11;
        private int bitField0_;
        private HeightDiffData heightDiffData_;
        private long id_ = 0;
        private int linkType_ = 0;
        private int formwayType_ = 0;
        private int roadClass_ = 0;
        private boolean isOverHead_ = false;
        private boolean hasParallelRoad_ = false;
        private int linkDirection_ = 0;
        private int roadDirection_ = 0;
        private int laneNum_ = 0;
        private Internal.ProtobufList<V2_Point> v2Points_ = emptyProtobufList();
        private double length_ = 0.0d;

        private Link() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public long getId() {
            return this.id_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setId(long value) {
            this.id_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearId() {
            this.id_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public int getLinkType() {
            return this.linkType_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLinkType(int value) {
            this.linkType_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLinkType() {
            this.linkType_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public int getFormwayType() {
            return this.formwayType_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFormwayType(int value) {
            this.formwayType_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFormwayType() {
            this.formwayType_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public int getRoadClass() {
            return this.roadClass_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRoadClass(int value) {
            this.roadClass_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRoadClass() {
            this.roadClass_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public boolean getIsOverHead() {
            return this.isOverHead_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsOverHead(boolean value) {
            this.isOverHead_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsOverHead() {
            this.isOverHead_ = false;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public boolean getHasParallelRoad() {
            return this.hasParallelRoad_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHasParallelRoad(boolean value) {
            this.hasParallelRoad_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHasParallelRoad() {
            this.hasParallelRoad_ = false;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public int getLinkDirection() {
            return this.linkDirection_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLinkDirection(int value) {
            this.linkDirection_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLinkDirection() {
            this.linkDirection_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public int getRoadDirection() {
            return this.roadDirection_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRoadDirection(int value) {
            this.roadDirection_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRoadDirection() {
            this.roadDirection_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public int getLaneNum() {
            return this.laneNum_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLaneNum(int value) {
            this.laneNum_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLaneNum() {
            this.laneNum_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public boolean hasHeightDiffData() {
            return this.heightDiffData_ != null;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public HeightDiffData getHeightDiffData() {
            return this.heightDiffData_ == null ? HeightDiffData.getDefaultInstance() : this.heightDiffData_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeightDiffData(HeightDiffData value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.heightDiffData_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeightDiffData(HeightDiffData.Builder builderForValue) {
            this.heightDiffData_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeHeightDiffData(HeightDiffData value) {
            if (this.heightDiffData_ != null && this.heightDiffData_ != HeightDiffData.getDefaultInstance()) {
                this.heightDiffData_ = HeightDiffData.newBuilder(this.heightDiffData_).mergeFrom((HeightDiffData.Builder) value).buildPartial();
            } else {
                this.heightDiffData_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeightDiffData() {
            this.heightDiffData_ = null;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public List<V2_Point> getV2PointsList() {
            return this.v2Points_;
        }

        public List<? extends V2_PointOrBuilder> getV2PointsOrBuilderList() {
            return this.v2Points_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public int getV2PointsCount() {
            return this.v2Points_.size();
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public V2_Point getV2Points(int index) {
            return this.v2Points_.get(index);
        }

        public V2_PointOrBuilder getV2PointsOrBuilder(int index) {
            return this.v2Points_.get(index);
        }

        private void ensureV2PointsIsMutable() {
            if (!this.v2Points_.isModifiable()) {
                this.v2Points_ = GeneratedMessageLite.mutableCopy(this.v2Points_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setV2Points(int index, V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointsIsMutable();
            this.v2Points_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setV2Points(int index, V2_Point.Builder builderForValue) {
            ensureV2PointsIsMutable();
            this.v2Points_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2Points(V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointsIsMutable();
            this.v2Points_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2Points(int index, V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointsIsMutable();
            this.v2Points_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2Points(V2_Point.Builder builderForValue) {
            ensureV2PointsIsMutable();
            this.v2Points_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2Points(int index, V2_Point.Builder builderForValue) {
            ensureV2PointsIsMutable();
            this.v2Points_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllV2Points(Iterable<? extends V2_Point> values) {
            ensureV2PointsIsMutable();
            AbstractMessageLite.addAll(values, this.v2Points_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearV2Points() {
            this.v2Points_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeV2Points(int index) {
            ensureV2PointsIsMutable();
            this.v2Points_.remove(index);
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
        public double getLength() {
            return this.length_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLength(double value) {
            this.length_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLength() {
            this.length_ = 0.0d;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.id_ != 0) {
                output.writeInt64(1, this.id_);
            }
            if (this.linkType_ != 0) {
                output.writeInt32(2, this.linkType_);
            }
            if (this.formwayType_ != 0) {
                output.writeInt32(3, this.formwayType_);
            }
            if (this.roadClass_ != 0) {
                output.writeInt32(4, this.roadClass_);
            }
            if (this.isOverHead_) {
                output.writeBool(5, this.isOverHead_);
            }
            if (this.hasParallelRoad_) {
                output.writeBool(6, this.hasParallelRoad_);
            }
            if (this.linkDirection_ != 0) {
                output.writeInt32(7, this.linkDirection_);
            }
            if (this.roadDirection_ != 0) {
                output.writeInt32(8, this.roadDirection_);
            }
            if (this.laneNum_ != 0) {
                output.writeInt32(9, this.laneNum_);
            }
            if (this.heightDiffData_ != null) {
                output.writeMessage(10, getHeightDiffData());
            }
            for (int i = 0; i < this.v2Points_.size(); i++) {
                output.writeMessage(11, this.v2Points_.get(i));
            }
            if (this.length_ != 0.0d) {
                output.writeDouble(12, this.length_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.id_ != 0 ? 0 + CodedOutputStream.computeInt64Size(1, this.id_) : 0;
            if (this.linkType_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(2, this.linkType_);
            }
            if (this.formwayType_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(3, this.formwayType_);
            }
            if (this.roadClass_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(4, this.roadClass_);
            }
            if (this.isOverHead_) {
                size2 += CodedOutputStream.computeBoolSize(5, this.isOverHead_);
            }
            if (this.hasParallelRoad_) {
                size2 += CodedOutputStream.computeBoolSize(6, this.hasParallelRoad_);
            }
            if (this.linkDirection_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(7, this.linkDirection_);
            }
            if (this.roadDirection_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(8, this.roadDirection_);
            }
            if (this.laneNum_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(9, this.laneNum_);
            }
            if (this.heightDiffData_ != null) {
                size2 += CodedOutputStream.computeMessageSize(10, getHeightDiffData());
            }
            for (int i = 0; i < this.v2Points_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(11, this.v2Points_.get(i));
            }
            if (this.length_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(12, this.length_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static Link parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Link parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Link parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Link parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Link parseFrom(InputStream input) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Link parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Link parseDelimitedFrom(InputStream input) throws IOException {
            return (Link) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Link parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Link) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Link parseFrom(CodedInputStream input) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Link parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Link) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Link prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Link, Builder> implements LinkOrBuilder {
            private Builder() {
                super(Link.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public long getId() {
                return ((Link) this.instance).getId();
            }

            public Builder setId(long value) {
                copyOnWrite();
                ((Link) this.instance).setId(value);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Link) this.instance).clearId();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public int getLinkType() {
                return ((Link) this.instance).getLinkType();
            }

            public Builder setLinkType(int value) {
                copyOnWrite();
                ((Link) this.instance).setLinkType(value);
                return this;
            }

            public Builder clearLinkType() {
                copyOnWrite();
                ((Link) this.instance).clearLinkType();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public int getFormwayType() {
                return ((Link) this.instance).getFormwayType();
            }

            public Builder setFormwayType(int value) {
                copyOnWrite();
                ((Link) this.instance).setFormwayType(value);
                return this;
            }

            public Builder clearFormwayType() {
                copyOnWrite();
                ((Link) this.instance).clearFormwayType();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public int getRoadClass() {
                return ((Link) this.instance).getRoadClass();
            }

            public Builder setRoadClass(int value) {
                copyOnWrite();
                ((Link) this.instance).setRoadClass(value);
                return this;
            }

            public Builder clearRoadClass() {
                copyOnWrite();
                ((Link) this.instance).clearRoadClass();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public boolean getIsOverHead() {
                return ((Link) this.instance).getIsOverHead();
            }

            public Builder setIsOverHead(boolean value) {
                copyOnWrite();
                ((Link) this.instance).setIsOverHead(value);
                return this;
            }

            public Builder clearIsOverHead() {
                copyOnWrite();
                ((Link) this.instance).clearIsOverHead();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public boolean getHasParallelRoad() {
                return ((Link) this.instance).getHasParallelRoad();
            }

            public Builder setHasParallelRoad(boolean value) {
                copyOnWrite();
                ((Link) this.instance).setHasParallelRoad(value);
                return this;
            }

            public Builder clearHasParallelRoad() {
                copyOnWrite();
                ((Link) this.instance).clearHasParallelRoad();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public int getLinkDirection() {
                return ((Link) this.instance).getLinkDirection();
            }

            public Builder setLinkDirection(int value) {
                copyOnWrite();
                ((Link) this.instance).setLinkDirection(value);
                return this;
            }

            public Builder clearLinkDirection() {
                copyOnWrite();
                ((Link) this.instance).clearLinkDirection();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public int getRoadDirection() {
                return ((Link) this.instance).getRoadDirection();
            }

            public Builder setRoadDirection(int value) {
                copyOnWrite();
                ((Link) this.instance).setRoadDirection(value);
                return this;
            }

            public Builder clearRoadDirection() {
                copyOnWrite();
                ((Link) this.instance).clearRoadDirection();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public int getLaneNum() {
                return ((Link) this.instance).getLaneNum();
            }

            public Builder setLaneNum(int value) {
                copyOnWrite();
                ((Link) this.instance).setLaneNum(value);
                return this;
            }

            public Builder clearLaneNum() {
                copyOnWrite();
                ((Link) this.instance).clearLaneNum();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public boolean hasHeightDiffData() {
                return ((Link) this.instance).hasHeightDiffData();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public HeightDiffData getHeightDiffData() {
                return ((Link) this.instance).getHeightDiffData();
            }

            public Builder setHeightDiffData(HeightDiffData value) {
                copyOnWrite();
                ((Link) this.instance).setHeightDiffData(value);
                return this;
            }

            public Builder setHeightDiffData(HeightDiffData.Builder builderForValue) {
                copyOnWrite();
                ((Link) this.instance).setHeightDiffData(builderForValue);
                return this;
            }

            public Builder mergeHeightDiffData(HeightDiffData value) {
                copyOnWrite();
                ((Link) this.instance).mergeHeightDiffData(value);
                return this;
            }

            public Builder clearHeightDiffData() {
                copyOnWrite();
                ((Link) this.instance).clearHeightDiffData();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public List<V2_Point> getV2PointsList() {
                return Collections.unmodifiableList(((Link) this.instance).getV2PointsList());
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public int getV2PointsCount() {
                return ((Link) this.instance).getV2PointsCount();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public V2_Point getV2Points(int index) {
                return ((Link) this.instance).getV2Points(index);
            }

            public Builder setV2Points(int index, V2_Point value) {
                copyOnWrite();
                ((Link) this.instance).setV2Points(index, value);
                return this;
            }

            public Builder setV2Points(int index, V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((Link) this.instance).setV2Points(index, builderForValue);
                return this;
            }

            public Builder addV2Points(V2_Point value) {
                copyOnWrite();
                ((Link) this.instance).addV2Points(value);
                return this;
            }

            public Builder addV2Points(int index, V2_Point value) {
                copyOnWrite();
                ((Link) this.instance).addV2Points(index, value);
                return this;
            }

            public Builder addV2Points(V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((Link) this.instance).addV2Points(builderForValue);
                return this;
            }

            public Builder addV2Points(int index, V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((Link) this.instance).addV2Points(index, builderForValue);
                return this;
            }

            public Builder addAllV2Points(Iterable<? extends V2_Point> values) {
                copyOnWrite();
                ((Link) this.instance).addAllV2Points(values);
                return this;
            }

            public Builder clearV2Points() {
                copyOnWrite();
                ((Link) this.instance).clearV2Points();
                return this;
            }

            public Builder removeV2Points(int index) {
                copyOnWrite();
                ((Link) this.instance).removeV2Points(index);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.LinkOrBuilder
            public double getLength() {
                return ((Link) this.instance).getLength();
            }

            public Builder setLength(double value) {
                copyOnWrite();
                ((Link) this.instance).setLength(value);
                return this;
            }

            public Builder clearLength() {
                copyOnWrite();
                ((Link) this.instance).clearLength();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Link();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.v2Points_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Link other = (Link) arg1;
                    this.id_ = visitor.visitLong(this.id_ != 0, this.id_, other.id_ != 0, other.id_);
                    this.linkType_ = visitor.visitInt(this.linkType_ != 0, this.linkType_, other.linkType_ != 0, other.linkType_);
                    this.formwayType_ = visitor.visitInt(this.formwayType_ != 0, this.formwayType_, other.formwayType_ != 0, other.formwayType_);
                    this.roadClass_ = visitor.visitInt(this.roadClass_ != 0, this.roadClass_, other.roadClass_ != 0, other.roadClass_);
                    this.isOverHead_ = visitor.visitBoolean(this.isOverHead_, this.isOverHead_, other.isOverHead_, other.isOverHead_);
                    this.hasParallelRoad_ = visitor.visitBoolean(this.hasParallelRoad_, this.hasParallelRoad_, other.hasParallelRoad_, other.hasParallelRoad_);
                    this.linkDirection_ = visitor.visitInt(this.linkDirection_ != 0, this.linkDirection_, other.linkDirection_ != 0, other.linkDirection_);
                    this.roadDirection_ = visitor.visitInt(this.roadDirection_ != 0, this.roadDirection_, other.roadDirection_ != 0, other.roadDirection_);
                    this.laneNum_ = visitor.visitInt(this.laneNum_ != 0, this.laneNum_, other.laneNum_ != 0, other.laneNum_);
                    this.heightDiffData_ = (HeightDiffData) visitor.visitMessage(this.heightDiffData_, other.heightDiffData_);
                    this.v2Points_ = visitor.visitList(this.v2Points_, other.v2Points_);
                    this.length_ = visitor.visitDouble(this.length_ != 0.0d, this.length_, other.length_ != 0.0d, other.length_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (true) {
                        boolean done2 = done;
                        if (done2) {
                            break;
                        } else {
                            try {
                                int tag = input.readTag();
                                switch (tag) {
                                    case 0:
                                        done2 = true;
                                        break;
                                    case 8:
                                        this.id_ = input.readInt64();
                                        break;
                                    case 16:
                                        this.linkType_ = input.readInt32();
                                        break;
                                    case 24:
                                        this.formwayType_ = input.readInt32();
                                        break;
                                    case 32:
                                        this.roadClass_ = input.readInt32();
                                        break;
                                    case 40:
                                        this.isOverHead_ = input.readBool();
                                        break;
                                    case 48:
                                        this.hasParallelRoad_ = input.readBool();
                                        break;
                                    case 56:
                                        this.linkDirection_ = input.readInt32();
                                        break;
                                    case 64:
                                        this.roadDirection_ = input.readInt32();
                                        break;
                                    case 72:
                                        this.laneNum_ = input.readInt32();
                                        break;
                                    case 82:
                                        HeightDiffData.Builder subBuilder = null;
                                        if (this.heightDiffData_ != null) {
                                            subBuilder = this.heightDiffData_.toBuilder();
                                        }
                                        this.heightDiffData_ = (HeightDiffData) input.readMessage(HeightDiffData.parser(), extensionRegistry);
                                        if (subBuilder == null) {
                                            break;
                                        } else {
                                            subBuilder.mergeFrom((HeightDiffData.Builder) this.heightDiffData_);
                                            this.heightDiffData_ = (HeightDiffData) subBuilder.buildPartial();
                                            break;
                                        }
                                    case 90:
                                        if (!this.v2Points_.isModifiable()) {
                                            this.v2Points_ = GeneratedMessageLite.mutableCopy(this.v2Points_);
                                        }
                                        this.v2Points_.add((V2_Point) input.readMessage(V2_Point.parser(), extensionRegistry));
                                        break;
                                    case 97:
                                        this.length_ = input.readDouble();
                                        break;
                                    default:
                                        if (!input.skipField(tag)) {
                                            done2 = true;
                                            break;
                                        } else {
                                            break;
                                        }
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
                        synchronized (Link.class) {
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

        public static Link getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Link> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class Segment extends GeneratedMessageLite<Segment, Builder> implements SegmentOrBuilder {
        private static final Segment DEFAULT_INSTANCE = new Segment();
        public static final int LENGTH_FIELD_NUMBER = 2;
        public static final int LINKS_FIELD_NUMBER = 1;
        private static volatile Parser<Segment> PARSER;
        private int bitField0_;
        private Internal.ProtobufList<Link> links_ = emptyProtobufList();
        private double length_ = 0.0d;

        private Segment() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.SegmentOrBuilder
        public List<Link> getLinksList() {
            return this.links_;
        }

        public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
            return this.links_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.SegmentOrBuilder
        public int getLinksCount() {
            return this.links_.size();
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.SegmentOrBuilder
        public Link getLinks(int index) {
            return this.links_.get(index);
        }

        public LinkOrBuilder getLinksOrBuilder(int index) {
            return this.links_.get(index);
        }

        private void ensureLinksIsMutable() {
            if (!this.links_.isModifiable()) {
                this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLinks(int index, Link value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureLinksIsMutable();
            this.links_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLinks(int index, Link.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLinks(Link value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureLinksIsMutable();
            this.links_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLinks(int index, Link value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureLinksIsMutable();
            this.links_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLinks(Link.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLinks(int index, Link.Builder builderForValue) {
            ensureLinksIsMutable();
            this.links_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllLinks(Iterable<? extends Link> values) {
            ensureLinksIsMutable();
            AbstractMessageLite.addAll(values, this.links_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLinks() {
            this.links_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeLinks(int index) {
            ensureLinksIsMutable();
            this.links_.remove(index);
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.SegmentOrBuilder
        public double getLength() {
            return this.length_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLength(double value) {
            this.length_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLength() {
            this.length_ = 0.0d;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.links_.size(); i++) {
                output.writeMessage(1, this.links_.get(i));
            }
            if (this.length_ != 0.0d) {
                output.writeDouble(2, this.length_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.links_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.links_.get(i));
            }
            if (this.length_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(2, this.length_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static Segment parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Segment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Segment parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Segment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Segment parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Segment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Segment parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Segment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Segment parseFrom(InputStream input) throws IOException {
            return (Segment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Segment parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Segment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Segment parseDelimitedFrom(InputStream input) throws IOException {
            return (Segment) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Segment parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Segment) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Segment parseFrom(CodedInputStream input) throws IOException {
            return (Segment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Segment parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Segment) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Segment prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Segment, Builder> implements SegmentOrBuilder {
            private Builder() {
                super(Segment.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.SegmentOrBuilder
            public List<Link> getLinksList() {
                return Collections.unmodifiableList(((Segment) this.instance).getLinksList());
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.SegmentOrBuilder
            public int getLinksCount() {
                return ((Segment) this.instance).getLinksCount();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.SegmentOrBuilder
            public Link getLinks(int index) {
                return ((Segment) this.instance).getLinks(index);
            }

            public Builder setLinks(int index, Link value) {
                copyOnWrite();
                ((Segment) this.instance).setLinks(index, value);
                return this;
            }

            public Builder setLinks(int index, Link.Builder builderForValue) {
                copyOnWrite();
                ((Segment) this.instance).setLinks(index, builderForValue);
                return this;
            }

            public Builder addLinks(Link value) {
                copyOnWrite();
                ((Segment) this.instance).addLinks(value);
                return this;
            }

            public Builder addLinks(int index, Link value) {
                copyOnWrite();
                ((Segment) this.instance).addLinks(index, value);
                return this;
            }

            public Builder addLinks(Link.Builder builderForValue) {
                copyOnWrite();
                ((Segment) this.instance).addLinks(builderForValue);
                return this;
            }

            public Builder addLinks(int index, Link.Builder builderForValue) {
                copyOnWrite();
                ((Segment) this.instance).addLinks(index, builderForValue);
                return this;
            }

            public Builder addAllLinks(Iterable<? extends Link> values) {
                copyOnWrite();
                ((Segment) this.instance).addAllLinks(values);
                return this;
            }

            public Builder clearLinks() {
                copyOnWrite();
                ((Segment) this.instance).clearLinks();
                return this;
            }

            public Builder removeLinks(int index) {
                copyOnWrite();
                ((Segment) this.instance).removeLinks(index);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.SegmentOrBuilder
            public double getLength() {
                return ((Segment) this.instance).getLength();
            }

            public Builder setLength(double value) {
                copyOnWrite();
                ((Segment) this.instance).setLength(value);
                return this;
            }

            public Builder clearLength() {
                copyOnWrite();
                ((Segment) this.instance).clearLength();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Segment();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.links_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Segment other = (Segment) arg1;
                    this.links_ = visitor.visitList(this.links_, other.links_);
                    this.length_ = visitor.visitDouble(this.length_ != 0.0d, this.length_, other.length_ != 0.0d, other.length_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
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
                                } else if (tag == 10) {
                                    if (!this.links_.isModifiable()) {
                                        this.links_ = GeneratedMessageLite.mutableCopy(this.links_);
                                    }
                                    this.links_.add((Link) input.readMessage(Link.parser(), extensionRegistry));
                                } else if (tag != 17) {
                                    if (!input.skipField(tag)) {
                                        done2 = true;
                                    }
                                } else {
                                    this.length_ = input.readDouble();
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
                        synchronized (Segment.class) {
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

        public static Segment getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Segment> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class Camera extends GeneratedMessageLite<Camera, Builder> implements CameraOrBuilder {
        private static final Camera DEFAULT_INSTANCE = new Camera();
        public static final int DISTANCE_FIELD_NUMBER = 3;
        private static volatile Parser<Camera> PARSER = null;
        public static final int POSITION_FIELD_NUMBER = 2;
        public static final int SPEED_FIELD_NUMBER = 4;
        public static final int TYPE_FIELD_NUMBER = 1;
        private V2_Point position_;
        private int type_ = 0;
        private long distance_ = 0;
        private int speed_ = 0;

        private Camera() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CameraOrBuilder
        public int getType() {
            return this.type_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(int value) {
            this.type_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.type_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CameraOrBuilder
        public boolean hasPosition() {
            return this.position_ != null;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CameraOrBuilder
        public V2_Point getPosition() {
            return this.position_ == null ? V2_Point.getDefaultInstance() : this.position_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPosition(V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.position_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPosition(V2_Point.Builder builderForValue) {
            this.position_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePosition(V2_Point value) {
            if (this.position_ != null && this.position_ != V2_Point.getDefaultInstance()) {
                this.position_ = V2_Point.newBuilder(this.position_).mergeFrom((V2_Point.Builder) value).buildPartial();
            } else {
                this.position_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPosition() {
            this.position_ = null;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CameraOrBuilder
        public long getDistance() {
            return this.distance_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDistance(long value) {
            this.distance_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDistance() {
            this.distance_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.CameraOrBuilder
        public int getSpeed() {
            return this.speed_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpeed(int value) {
            this.speed_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSpeed() {
            this.speed_ = 0;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.type_ != 0) {
                output.writeInt32(1, this.type_);
            }
            if (this.position_ != null) {
                output.writeMessage(2, getPosition());
            }
            if (this.distance_ != 0) {
                output.writeInt64(3, this.distance_);
            }
            if (this.speed_ != 0) {
                output.writeInt32(4, this.speed_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.type_ != 0 ? 0 + CodedOutputStream.computeInt32Size(1, this.type_) : 0;
            if (this.position_ != null) {
                size2 += CodedOutputStream.computeMessageSize(2, getPosition());
            }
            if (this.distance_ != 0) {
                size2 += CodedOutputStream.computeInt64Size(3, this.distance_);
            }
            if (this.speed_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(4, this.speed_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static Camera parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Camera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Camera parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Camera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Camera parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Camera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Camera parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Camera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Camera parseFrom(InputStream input) throws IOException {
            return (Camera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Camera parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Camera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Camera parseDelimitedFrom(InputStream input) throws IOException {
            return (Camera) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Camera parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Camera) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Camera parseFrom(CodedInputStream input) throws IOException {
            return (Camera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Camera parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Camera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Camera prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Camera, Builder> implements CameraOrBuilder {
            private Builder() {
                super(Camera.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CameraOrBuilder
            public int getType() {
                return ((Camera) this.instance).getType();
            }

            public Builder setType(int value) {
                copyOnWrite();
                ((Camera) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((Camera) this.instance).clearType();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CameraOrBuilder
            public boolean hasPosition() {
                return ((Camera) this.instance).hasPosition();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CameraOrBuilder
            public V2_Point getPosition() {
                return ((Camera) this.instance).getPosition();
            }

            public Builder setPosition(V2_Point value) {
                copyOnWrite();
                ((Camera) this.instance).setPosition(value);
                return this;
            }

            public Builder setPosition(V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((Camera) this.instance).setPosition(builderForValue);
                return this;
            }

            public Builder mergePosition(V2_Point value) {
                copyOnWrite();
                ((Camera) this.instance).mergePosition(value);
                return this;
            }

            public Builder clearPosition() {
                copyOnWrite();
                ((Camera) this.instance).clearPosition();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CameraOrBuilder
            public long getDistance() {
                return ((Camera) this.instance).getDistance();
            }

            public Builder setDistance(long value) {
                copyOnWrite();
                ((Camera) this.instance).setDistance(value);
                return this;
            }

            public Builder clearDistance() {
                copyOnWrite();
                ((Camera) this.instance).clearDistance();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.CameraOrBuilder
            public int getSpeed() {
                return ((Camera) this.instance).getSpeed();
            }

            public Builder setSpeed(int value) {
                copyOnWrite();
                ((Camera) this.instance).setSpeed(value);
                return this;
            }

            public Builder clearSpeed() {
                copyOnWrite();
                ((Camera) this.instance).clearSpeed();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Camera();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Camera other = (Camera) arg1;
                    this.type_ = visitor.visitInt(this.type_ != 0, this.type_, other.type_ != 0, other.type_);
                    this.position_ = (V2_Point) visitor.visitMessage(this.position_, other.position_);
                    this.distance_ = visitor.visitLong(this.distance_ != 0, this.distance_, other.distance_ != 0, other.distance_);
                    this.speed_ = visitor.visitInt(this.speed_ != 0, this.speed_, other.speed_ != 0, other.speed_);
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
                                    this.type_ = input.readInt32();
                                } else if (tag == 18) {
                                    V2_Point.Builder subBuilder = null;
                                    if (this.position_ != null) {
                                        subBuilder = this.position_.toBuilder();
                                    }
                                    this.position_ = (V2_Point) input.readMessage(V2_Point.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((V2_Point.Builder) this.position_);
                                        this.position_ = (V2_Point) subBuilder.buildPartial();
                                    }
                                } else if (tag == 24) {
                                    this.distance_ = input.readInt64();
                                } else if (tag != 32) {
                                    if (!input.skipField(tag)) {
                                        done2 = true;
                                    }
                                } else {
                                    this.speed_ = input.readInt32();
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
                        synchronized (Camera.class) {
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

        public static Camera getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Camera> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class ViaPointInfo extends GeneratedMessageLite<ViaPointInfo, Builder> implements ViaPointInfoOrBuilder {
        private static final ViaPointInfo DEFAULT_INSTANCE = new ViaPointInfo();
        public static final int DIRECTION_FIELD_NUMBER = 3;
        private static volatile Parser<ViaPointInfo> PARSER = null;
        public static final int PROJECTIVE_FIELD_NUMBER = 2;
        public static final int SHOW_FIELD_NUMBER = 1;
        private int direction_ = 0;
        private V2_Point projective_;
        private V2_Point show_;

        private ViaPointInfo() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.ViaPointInfoOrBuilder
        public boolean hasShow() {
            return this.show_ != null;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.ViaPointInfoOrBuilder
        public V2_Point getShow() {
            return this.show_ == null ? V2_Point.getDefaultInstance() : this.show_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setShow(V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.show_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setShow(V2_Point.Builder builderForValue) {
            this.show_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeShow(V2_Point value) {
            if (this.show_ != null && this.show_ != V2_Point.getDefaultInstance()) {
                this.show_ = V2_Point.newBuilder(this.show_).mergeFrom((V2_Point.Builder) value).buildPartial();
            } else {
                this.show_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearShow() {
            this.show_ = null;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.ViaPointInfoOrBuilder
        public boolean hasProjective() {
            return this.projective_ != null;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.ViaPointInfoOrBuilder
        public V2_Point getProjective() {
            return this.projective_ == null ? V2_Point.getDefaultInstance() : this.projective_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProjective(V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.projective_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProjective(V2_Point.Builder builderForValue) {
            this.projective_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeProjective(V2_Point value) {
            if (this.projective_ != null && this.projective_ != V2_Point.getDefaultInstance()) {
                this.projective_ = V2_Point.newBuilder(this.projective_).mergeFrom((V2_Point.Builder) value).buildPartial();
            } else {
                this.projective_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProjective() {
            this.projective_ = null;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.ViaPointInfoOrBuilder
        public int getDirection() {
            return this.direction_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDirection(int value) {
            this.direction_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDirection() {
            this.direction_ = 0;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.show_ != null) {
                output.writeMessage(1, getShow());
            }
            if (this.projective_ != null) {
                output.writeMessage(2, getProjective());
            }
            if (this.direction_ != 0) {
                output.writeInt32(3, this.direction_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.show_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getShow()) : 0;
            if (this.projective_ != null) {
                size2 += CodedOutputStream.computeMessageSize(2, getProjective());
            }
            if (this.direction_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(3, this.direction_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static ViaPointInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ViaPointInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ViaPointInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ViaPointInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ViaPointInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ViaPointInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ViaPointInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ViaPointInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ViaPointInfo parseFrom(InputStream input) throws IOException {
            return (ViaPointInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ViaPointInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ViaPointInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ViaPointInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (ViaPointInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ViaPointInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ViaPointInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ViaPointInfo parseFrom(CodedInputStream input) throws IOException {
            return (ViaPointInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ViaPointInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ViaPointInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ViaPointInfo prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ViaPointInfo, Builder> implements ViaPointInfoOrBuilder {
            private Builder() {
                super(ViaPointInfo.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.ViaPointInfoOrBuilder
            public boolean hasShow() {
                return ((ViaPointInfo) this.instance).hasShow();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.ViaPointInfoOrBuilder
            public V2_Point getShow() {
                return ((ViaPointInfo) this.instance).getShow();
            }

            public Builder setShow(V2_Point value) {
                copyOnWrite();
                ((ViaPointInfo) this.instance).setShow(value);
                return this;
            }

            public Builder setShow(V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((ViaPointInfo) this.instance).setShow(builderForValue);
                return this;
            }

            public Builder mergeShow(V2_Point value) {
                copyOnWrite();
                ((ViaPointInfo) this.instance).mergeShow(value);
                return this;
            }

            public Builder clearShow() {
                copyOnWrite();
                ((ViaPointInfo) this.instance).clearShow();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.ViaPointInfoOrBuilder
            public boolean hasProjective() {
                return ((ViaPointInfo) this.instance).hasProjective();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.ViaPointInfoOrBuilder
            public V2_Point getProjective() {
                return ((ViaPointInfo) this.instance).getProjective();
            }

            public Builder setProjective(V2_Point value) {
                copyOnWrite();
                ((ViaPointInfo) this.instance).setProjective(value);
                return this;
            }

            public Builder setProjective(V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((ViaPointInfo) this.instance).setProjective(builderForValue);
                return this;
            }

            public Builder mergeProjective(V2_Point value) {
                copyOnWrite();
                ((ViaPointInfo) this.instance).mergeProjective(value);
                return this;
            }

            public Builder clearProjective() {
                copyOnWrite();
                ((ViaPointInfo) this.instance).clearProjective();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.ViaPointInfoOrBuilder
            public int getDirection() {
                return ((ViaPointInfo) this.instance).getDirection();
            }

            public Builder setDirection(int value) {
                copyOnWrite();
                ((ViaPointInfo) this.instance).setDirection(value);
                return this;
            }

            public Builder clearDirection() {
                copyOnWrite();
                ((ViaPointInfo) this.instance).clearDirection();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ViaPointInfo();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ViaPointInfo other = (ViaPointInfo) arg1;
                    this.show_ = (V2_Point) visitor.visitMessage(this.show_, other.show_);
                    this.projective_ = (V2_Point) visitor.visitMessage(this.projective_, other.projective_);
                    boolean z = this.direction_ != 0;
                    int i = this.direction_;
                    done = other.direction_ != 0;
                    this.direction_ = visitor.visitInt(z, i, done, other.direction_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    while (!done) {
                        try {
                            try {
                                int tag = input.readTag();
                                if (tag == 0) {
                                    done = true;
                                } else if (tag == 10) {
                                    V2_Point.Builder subBuilder = null;
                                    if (this.show_ != null) {
                                        subBuilder = this.show_.toBuilder();
                                    }
                                    this.show_ = (V2_Point) input.readMessage(V2_Point.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((V2_Point.Builder) this.show_);
                                        this.show_ = (V2_Point) subBuilder.buildPartial();
                                    }
                                } else if (tag == 18) {
                                    V2_Point.Builder subBuilder2 = null;
                                    if (this.projective_ != null) {
                                        subBuilder2 = this.projective_.toBuilder();
                                    }
                                    this.projective_ = (V2_Point) input.readMessage(V2_Point.parser(), extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom((V2_Point.Builder) this.projective_);
                                        this.projective_ = (V2_Point) subBuilder2.buildPartial();
                                    }
                                } else if (tag != 24) {
                                    if (!input.skipField(tag)) {
                                        done = true;
                                    }
                                } else {
                                    this.direction_ = input.readInt32();
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
                        synchronized (ViaPointInfo.class) {
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

        public static ViaPointInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ViaPointInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class Path extends GeneratedMessageLite<Path, Builder> implements PathOrBuilder {
        public static final int CAMERAS_FIELD_NUMBER = 4;
        public static final int DATA_VERSION_FIELD_NUMBER = 1;
        private static final Path DEFAULT_INSTANCE = new Path();
        public static final int LENGTH_FIELD_NUMBER = 6;
        private static volatile Parser<Path> PARSER = null;
        public static final int SEGMENTS_FIELD_NUMBER = 2;
        public static final int TRAFFIC_LIGHTS_FIELD_NUMBER = 3;
        public static final int VIA_POINT_INFOS_FIELD_NUMBER = 5;
        private int bitField0_;
        private long dataVersion_ = 0;
        private Internal.ProtobufList<Segment> segments_ = emptyProtobufList();
        private Internal.ProtobufList<V2_Point> trafficLights_ = emptyProtobufList();
        private Internal.ProtobufList<Camera> cameras_ = emptyProtobufList();
        private Internal.ProtobufList<ViaPointInfo> viaPointInfos_ = emptyProtobufList();
        private double length_ = 0.0d;

        private Path() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public long getDataVersion() {
            return this.dataVersion_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDataVersion(long value) {
            this.dataVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDataVersion() {
            this.dataVersion_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public List<Segment> getSegmentsList() {
            return this.segments_;
        }

        public List<? extends SegmentOrBuilder> getSegmentsOrBuilderList() {
            return this.segments_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public int getSegmentsCount() {
            return this.segments_.size();
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public Segment getSegments(int index) {
            return this.segments_.get(index);
        }

        public SegmentOrBuilder getSegmentsOrBuilder(int index) {
            return this.segments_.get(index);
        }

        private void ensureSegmentsIsMutable() {
            if (!this.segments_.isModifiable()) {
                this.segments_ = GeneratedMessageLite.mutableCopy(this.segments_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegments(int index, Segment value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureSegmentsIsMutable();
            this.segments_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegments(int index, Segment.Builder builderForValue) {
            ensureSegmentsIsMutable();
            this.segments_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSegments(Segment value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureSegmentsIsMutable();
            this.segments_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSegments(int index, Segment value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureSegmentsIsMutable();
            this.segments_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSegments(Segment.Builder builderForValue) {
            ensureSegmentsIsMutable();
            this.segments_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSegments(int index, Segment.Builder builderForValue) {
            ensureSegmentsIsMutable();
            this.segments_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSegments(Iterable<? extends Segment> values) {
            ensureSegmentsIsMutable();
            AbstractMessageLite.addAll(values, this.segments_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSegments() {
            this.segments_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeSegments(int index) {
            ensureSegmentsIsMutable();
            this.segments_.remove(index);
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public List<V2_Point> getTrafficLightsList() {
            return this.trafficLights_;
        }

        public List<? extends V2_PointOrBuilder> getTrafficLightsOrBuilderList() {
            return this.trafficLights_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public int getTrafficLightsCount() {
            return this.trafficLights_.size();
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public V2_Point getTrafficLights(int index) {
            return this.trafficLights_.get(index);
        }

        public V2_PointOrBuilder getTrafficLightsOrBuilder(int index) {
            return this.trafficLights_.get(index);
        }

        private void ensureTrafficLightsIsMutable() {
            if (!this.trafficLights_.isModifiable()) {
                this.trafficLights_ = GeneratedMessageLite.mutableCopy(this.trafficLights_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTrafficLights(int index, V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureTrafficLightsIsMutable();
            this.trafficLights_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTrafficLights(int index, V2_Point.Builder builderForValue) {
            ensureTrafficLightsIsMutable();
            this.trafficLights_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addTrafficLights(V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureTrafficLightsIsMutable();
            this.trafficLights_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addTrafficLights(int index, V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureTrafficLightsIsMutable();
            this.trafficLights_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addTrafficLights(V2_Point.Builder builderForValue) {
            ensureTrafficLightsIsMutable();
            this.trafficLights_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addTrafficLights(int index, V2_Point.Builder builderForValue) {
            ensureTrafficLightsIsMutable();
            this.trafficLights_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllTrafficLights(Iterable<? extends V2_Point> values) {
            ensureTrafficLightsIsMutable();
            AbstractMessageLite.addAll(values, this.trafficLights_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTrafficLights() {
            this.trafficLights_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeTrafficLights(int index) {
            ensureTrafficLightsIsMutable();
            this.trafficLights_.remove(index);
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public List<Camera> getCamerasList() {
            return this.cameras_;
        }

        public List<? extends CameraOrBuilder> getCamerasOrBuilderList() {
            return this.cameras_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public int getCamerasCount() {
            return this.cameras_.size();
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public Camera getCameras(int index) {
            return this.cameras_.get(index);
        }

        public CameraOrBuilder getCamerasOrBuilder(int index) {
            return this.cameras_.get(index);
        }

        private void ensureCamerasIsMutable() {
            if (!this.cameras_.isModifiable()) {
                this.cameras_ = GeneratedMessageLite.mutableCopy(this.cameras_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCameras(int index, Camera value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureCamerasIsMutable();
            this.cameras_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCameras(int index, Camera.Builder builderForValue) {
            ensureCamerasIsMutable();
            this.cameras_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCameras(Camera value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureCamerasIsMutable();
            this.cameras_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCameras(int index, Camera value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureCamerasIsMutable();
            this.cameras_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCameras(Camera.Builder builderForValue) {
            ensureCamerasIsMutable();
            this.cameras_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCameras(int index, Camera.Builder builderForValue) {
            ensureCamerasIsMutable();
            this.cameras_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllCameras(Iterable<? extends Camera> values) {
            ensureCamerasIsMutable();
            AbstractMessageLite.addAll(values, this.cameras_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCameras() {
            this.cameras_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeCameras(int index) {
            ensureCamerasIsMutable();
            this.cameras_.remove(index);
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public List<ViaPointInfo> getViaPointInfosList() {
            return this.viaPointInfos_;
        }

        public List<? extends ViaPointInfoOrBuilder> getViaPointInfosOrBuilderList() {
            return this.viaPointInfos_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public int getViaPointInfosCount() {
            return this.viaPointInfos_.size();
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public ViaPointInfo getViaPointInfos(int index) {
            return this.viaPointInfos_.get(index);
        }

        public ViaPointInfoOrBuilder getViaPointInfosOrBuilder(int index) {
            return this.viaPointInfos_.get(index);
        }

        private void ensureViaPointInfosIsMutable() {
            if (!this.viaPointInfos_.isModifiable()) {
                this.viaPointInfos_ = GeneratedMessageLite.mutableCopy(this.viaPointInfos_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setViaPointInfos(int index, ViaPointInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureViaPointInfosIsMutable();
            this.viaPointInfos_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setViaPointInfos(int index, ViaPointInfo.Builder builderForValue) {
            ensureViaPointInfosIsMutable();
            this.viaPointInfos_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addViaPointInfos(ViaPointInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureViaPointInfosIsMutable();
            this.viaPointInfos_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addViaPointInfos(int index, ViaPointInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureViaPointInfosIsMutable();
            this.viaPointInfos_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addViaPointInfos(ViaPointInfo.Builder builderForValue) {
            ensureViaPointInfosIsMutable();
            this.viaPointInfos_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addViaPointInfos(int index, ViaPointInfo.Builder builderForValue) {
            ensureViaPointInfosIsMutable();
            this.viaPointInfos_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllViaPointInfos(Iterable<? extends ViaPointInfo> values) {
            ensureViaPointInfosIsMutable();
            AbstractMessageLite.addAll(values, this.viaPointInfos_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearViaPointInfos() {
            this.viaPointInfos_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeViaPointInfos(int index) {
            ensureViaPointInfosIsMutable();
            this.viaPointInfos_.remove(index);
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
        public double getLength() {
            return this.length_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLength(double value) {
            this.length_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLength() {
            this.length_ = 0.0d;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.dataVersion_ != 0) {
                output.writeUInt64(1, this.dataVersion_);
            }
            for (int i = 0; i < this.segments_.size(); i++) {
                output.writeMessage(2, this.segments_.get(i));
            }
            for (int i2 = 0; i2 < this.trafficLights_.size(); i2++) {
                output.writeMessage(3, this.trafficLights_.get(i2));
            }
            for (int i3 = 0; i3 < this.cameras_.size(); i3++) {
                output.writeMessage(4, this.cameras_.get(i3));
            }
            for (int i4 = 0; i4 < this.viaPointInfos_.size(); i4++) {
                output.writeMessage(5, this.viaPointInfos_.get(i4));
            }
            if (this.length_ != 0.0d) {
                output.writeDouble(6, this.length_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int i = 0;
            int size2 = this.dataVersion_ != 0 ? 0 + CodedOutputStream.computeUInt64Size(1, this.dataVersion_) : 0;
            for (int size3 = 0; size3 < this.segments_.size(); size3++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.segments_.get(size3));
            }
            for (int i2 = 0; i2 < this.trafficLights_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(3, this.trafficLights_.get(i2));
            }
            for (int i3 = 0; i3 < this.cameras_.size(); i3++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.cameras_.get(i3));
            }
            while (true) {
                int i4 = i;
                if (i4 >= this.viaPointInfos_.size()) {
                    break;
                }
                size2 += CodedOutputStream.computeMessageSize(5, this.viaPointInfos_.get(i4));
                i = i4 + 1;
            }
            if (this.length_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(6, this.length_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static Path parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Path) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Path parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Path) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Path parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Path) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Path parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Path) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Path parseFrom(InputStream input) throws IOException {
            return (Path) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Path parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Path) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Path parseDelimitedFrom(InputStream input) throws IOException {
            return (Path) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Path parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Path) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Path parseFrom(CodedInputStream input) throws IOException {
            return (Path) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Path parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Path) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Path prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Path, Builder> implements PathOrBuilder {
            private Builder() {
                super(Path.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public long getDataVersion() {
                return ((Path) this.instance).getDataVersion();
            }

            public Builder setDataVersion(long value) {
                copyOnWrite();
                ((Path) this.instance).setDataVersion(value);
                return this;
            }

            public Builder clearDataVersion() {
                copyOnWrite();
                ((Path) this.instance).clearDataVersion();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public List<Segment> getSegmentsList() {
                return Collections.unmodifiableList(((Path) this.instance).getSegmentsList());
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public int getSegmentsCount() {
                return ((Path) this.instance).getSegmentsCount();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public Segment getSegments(int index) {
                return ((Path) this.instance).getSegments(index);
            }

            public Builder setSegments(int index, Segment value) {
                copyOnWrite();
                ((Path) this.instance).setSegments(index, value);
                return this;
            }

            public Builder setSegments(int index, Segment.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).setSegments(index, builderForValue);
                return this;
            }

            public Builder addSegments(Segment value) {
                copyOnWrite();
                ((Path) this.instance).addSegments(value);
                return this;
            }

            public Builder addSegments(int index, Segment value) {
                copyOnWrite();
                ((Path) this.instance).addSegments(index, value);
                return this;
            }

            public Builder addSegments(Segment.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).addSegments(builderForValue);
                return this;
            }

            public Builder addSegments(int index, Segment.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).addSegments(index, builderForValue);
                return this;
            }

            public Builder addAllSegments(Iterable<? extends Segment> values) {
                copyOnWrite();
                ((Path) this.instance).addAllSegments(values);
                return this;
            }

            public Builder clearSegments() {
                copyOnWrite();
                ((Path) this.instance).clearSegments();
                return this;
            }

            public Builder removeSegments(int index) {
                copyOnWrite();
                ((Path) this.instance).removeSegments(index);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public List<V2_Point> getTrafficLightsList() {
                return Collections.unmodifiableList(((Path) this.instance).getTrafficLightsList());
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public int getTrafficLightsCount() {
                return ((Path) this.instance).getTrafficLightsCount();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public V2_Point getTrafficLights(int index) {
                return ((Path) this.instance).getTrafficLights(index);
            }

            public Builder setTrafficLights(int index, V2_Point value) {
                copyOnWrite();
                ((Path) this.instance).setTrafficLights(index, value);
                return this;
            }

            public Builder setTrafficLights(int index, V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).setTrafficLights(index, builderForValue);
                return this;
            }

            public Builder addTrafficLights(V2_Point value) {
                copyOnWrite();
                ((Path) this.instance).addTrafficLights(value);
                return this;
            }

            public Builder addTrafficLights(int index, V2_Point value) {
                copyOnWrite();
                ((Path) this.instance).addTrafficLights(index, value);
                return this;
            }

            public Builder addTrafficLights(V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).addTrafficLights(builderForValue);
                return this;
            }

            public Builder addTrafficLights(int index, V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).addTrafficLights(index, builderForValue);
                return this;
            }

            public Builder addAllTrafficLights(Iterable<? extends V2_Point> values) {
                copyOnWrite();
                ((Path) this.instance).addAllTrafficLights(values);
                return this;
            }

            public Builder clearTrafficLights() {
                copyOnWrite();
                ((Path) this.instance).clearTrafficLights();
                return this;
            }

            public Builder removeTrafficLights(int index) {
                copyOnWrite();
                ((Path) this.instance).removeTrafficLights(index);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public List<Camera> getCamerasList() {
                return Collections.unmodifiableList(((Path) this.instance).getCamerasList());
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public int getCamerasCount() {
                return ((Path) this.instance).getCamerasCount();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public Camera getCameras(int index) {
                return ((Path) this.instance).getCameras(index);
            }

            public Builder setCameras(int index, Camera value) {
                copyOnWrite();
                ((Path) this.instance).setCameras(index, value);
                return this;
            }

            public Builder setCameras(int index, Camera.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).setCameras(index, builderForValue);
                return this;
            }

            public Builder addCameras(Camera value) {
                copyOnWrite();
                ((Path) this.instance).addCameras(value);
                return this;
            }

            public Builder addCameras(int index, Camera value) {
                copyOnWrite();
                ((Path) this.instance).addCameras(index, value);
                return this;
            }

            public Builder addCameras(Camera.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).addCameras(builderForValue);
                return this;
            }

            public Builder addCameras(int index, Camera.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).addCameras(index, builderForValue);
                return this;
            }

            public Builder addAllCameras(Iterable<? extends Camera> values) {
                copyOnWrite();
                ((Path) this.instance).addAllCameras(values);
                return this;
            }

            public Builder clearCameras() {
                copyOnWrite();
                ((Path) this.instance).clearCameras();
                return this;
            }

            public Builder removeCameras(int index) {
                copyOnWrite();
                ((Path) this.instance).removeCameras(index);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public List<ViaPointInfo> getViaPointInfosList() {
                return Collections.unmodifiableList(((Path) this.instance).getViaPointInfosList());
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public int getViaPointInfosCount() {
                return ((Path) this.instance).getViaPointInfosCount();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public ViaPointInfo getViaPointInfos(int index) {
                return ((Path) this.instance).getViaPointInfos(index);
            }

            public Builder setViaPointInfos(int index, ViaPointInfo value) {
                copyOnWrite();
                ((Path) this.instance).setViaPointInfos(index, value);
                return this;
            }

            public Builder setViaPointInfos(int index, ViaPointInfo.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).setViaPointInfos(index, builderForValue);
                return this;
            }

            public Builder addViaPointInfos(ViaPointInfo value) {
                copyOnWrite();
                ((Path) this.instance).addViaPointInfos(value);
                return this;
            }

            public Builder addViaPointInfos(int index, ViaPointInfo value) {
                copyOnWrite();
                ((Path) this.instance).addViaPointInfos(index, value);
                return this;
            }

            public Builder addViaPointInfos(ViaPointInfo.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).addViaPointInfos(builderForValue);
                return this;
            }

            public Builder addViaPointInfos(int index, ViaPointInfo.Builder builderForValue) {
                copyOnWrite();
                ((Path) this.instance).addViaPointInfos(index, builderForValue);
                return this;
            }

            public Builder addAllViaPointInfos(Iterable<? extends ViaPointInfo> values) {
                copyOnWrite();
                ((Path) this.instance).addAllViaPointInfos(values);
                return this;
            }

            public Builder clearViaPointInfos() {
                copyOnWrite();
                ((Path) this.instance).clearViaPointInfos();
                return this;
            }

            public Builder removeViaPointInfos(int index) {
                copyOnWrite();
                ((Path) this.instance).removeViaPointInfos(index);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.PathOrBuilder
            public double getLength() {
                return ((Path) this.instance).getLength();
            }

            public Builder setLength(double value) {
                copyOnWrite();
                ((Path) this.instance).setLength(value);
                return this;
            }

            public Builder clearLength() {
                copyOnWrite();
                ((Path) this.instance).clearLength();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Path();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.segments_.makeImmutable();
                    this.trafficLights_.makeImmutable();
                    this.cameras_.makeImmutable();
                    this.viaPointInfos_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Path other = (Path) arg1;
                    this.dataVersion_ = visitor.visitLong(this.dataVersion_ != 0, this.dataVersion_, other.dataVersion_ != 0, other.dataVersion_);
                    this.segments_ = visitor.visitList(this.segments_, other.segments_);
                    this.trafficLights_ = visitor.visitList(this.trafficLights_, other.trafficLights_);
                    this.cameras_ = visitor.visitList(this.cameras_, other.cameras_);
                    this.viaPointInfos_ = visitor.visitList(this.viaPointInfos_, other.viaPointInfos_);
                    this.length_ = visitor.visitDouble(this.length_ != 0.0d, this.length_, other.length_ != 0.0d, other.length_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
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
                                    this.dataVersion_ = input.readUInt64();
                                } else if (tag == 18) {
                                    if (!this.segments_.isModifiable()) {
                                        this.segments_ = GeneratedMessageLite.mutableCopy(this.segments_);
                                    }
                                    this.segments_.add((Segment) input.readMessage(Segment.parser(), extensionRegistry));
                                } else if (tag == 26) {
                                    if (!this.trafficLights_.isModifiable()) {
                                        this.trafficLights_ = GeneratedMessageLite.mutableCopy(this.trafficLights_);
                                    }
                                    this.trafficLights_.add((V2_Point) input.readMessage(V2_Point.parser(), extensionRegistry));
                                } else if (tag == 34) {
                                    if (!this.cameras_.isModifiable()) {
                                        this.cameras_ = GeneratedMessageLite.mutableCopy(this.cameras_);
                                    }
                                    this.cameras_.add((Camera) input.readMessage(Camera.parser(), extensionRegistry));
                                } else if (tag == 42) {
                                    if (!this.viaPointInfos_.isModifiable()) {
                                        this.viaPointInfos_ = GeneratedMessageLite.mutableCopy(this.viaPointInfos_);
                                    }
                                    this.viaPointInfos_.add((ViaPointInfo) input.readMessage(ViaPointInfo.parser(), extensionRegistry));
                                } else if (tag != 49) {
                                    if (!input.skipField(tag)) {
                                        done2 = true;
                                    }
                                } else {
                                    this.length_ = input.readDouble();
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
                        synchronized (Path.class) {
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

        public static Path getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Path> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class NaviRoutingInfo extends GeneratedMessageLite<NaviRoutingInfo, Builder> implements NaviRoutingInfoOrBuilder {
        private static final NaviRoutingInfo DEFAULT_INSTANCE = new NaviRoutingInfo();
        public static final int NAVIGATION_ID_FIELD_NUMBER = 2;
        public static final int NAVIGATION_STATUS_FIELD_NUMBER = 1;
        private static volatile Parser<NaviRoutingInfo> PARSER = null;
        public static final int V2_POINTS_FIELD_NUMBER = 3;
        private int bitField0_;
        private int navigationStatus_ = 0;
        private long navigationId_ = 0;
        private Internal.ProtobufList<V2_Point> v2Points_ = emptyProtobufList();

        private NaviRoutingInfo() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
        public int getNavigationStatusValue() {
            return this.navigationStatus_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
        public NaviStatus getNavigationStatus() {
            NaviStatus result = NaviStatus.forNumber(this.navigationStatus_);
            return result == null ? NaviStatus.UNRECOGNIZED : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNavigationStatusValue(int value) {
            this.navigationStatus_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNavigationStatus(NaviStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.navigationStatus_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNavigationStatus() {
            this.navigationStatus_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
        public long getNavigationId() {
            return this.navigationId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNavigationId(long value) {
            this.navigationId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNavigationId() {
            this.navigationId_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
        public List<V2_Point> getV2PointsList() {
            return this.v2Points_;
        }

        public List<? extends V2_PointOrBuilder> getV2PointsOrBuilderList() {
            return this.v2Points_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
        public int getV2PointsCount() {
            return this.v2Points_.size();
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
        public V2_Point getV2Points(int index) {
            return this.v2Points_.get(index);
        }

        public V2_PointOrBuilder getV2PointsOrBuilder(int index) {
            return this.v2Points_.get(index);
        }

        private void ensureV2PointsIsMutable() {
            if (!this.v2Points_.isModifiable()) {
                this.v2Points_ = GeneratedMessageLite.mutableCopy(this.v2Points_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setV2Points(int index, V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointsIsMutable();
            this.v2Points_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setV2Points(int index, V2_Point.Builder builderForValue) {
            ensureV2PointsIsMutable();
            this.v2Points_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2Points(V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointsIsMutable();
            this.v2Points_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2Points(int index, V2_Point value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureV2PointsIsMutable();
            this.v2Points_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2Points(V2_Point.Builder builderForValue) {
            ensureV2PointsIsMutable();
            this.v2Points_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2Points(int index, V2_Point.Builder builderForValue) {
            ensureV2PointsIsMutable();
            this.v2Points_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllV2Points(Iterable<? extends V2_Point> values) {
            ensureV2PointsIsMutable();
            AbstractMessageLite.addAll(values, this.v2Points_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearV2Points() {
            this.v2Points_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeV2Points(int index) {
            ensureV2PointsIsMutable();
            this.v2Points_.remove(index);
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.navigationStatus_ != NaviStatus.NAVI_STATUS_INVALID.getNumber()) {
                output.writeEnum(1, this.navigationStatus_);
            }
            if (this.navigationId_ != 0) {
                output.writeUInt64(2, this.navigationId_);
            }
            for (int i = 0; i < this.v2Points_.size(); i++) {
                output.writeMessage(3, this.v2Points_.get(i));
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.navigationStatus_ != NaviStatus.NAVI_STATUS_INVALID.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.navigationStatus_) : 0;
            if (this.navigationId_ != 0) {
                size2 += CodedOutputStream.computeUInt64Size(2, this.navigationId_);
            }
            for (int i = 0; i < this.v2Points_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(3, this.v2Points_.get(i));
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static NaviRoutingInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (NaviRoutingInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static NaviRoutingInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (NaviRoutingInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static NaviRoutingInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (NaviRoutingInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static NaviRoutingInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (NaviRoutingInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static NaviRoutingInfo parseFrom(InputStream input) throws IOException {
            return (NaviRoutingInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviRoutingInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviRoutingInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static NaviRoutingInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (NaviRoutingInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviRoutingInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviRoutingInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static NaviRoutingInfo parseFrom(CodedInputStream input) throws IOException {
            return (NaviRoutingInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviRoutingInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviRoutingInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(NaviRoutingInfo prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NaviRoutingInfo, Builder> implements NaviRoutingInfoOrBuilder {
            private Builder() {
                super(NaviRoutingInfo.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
            public int getNavigationStatusValue() {
                return ((NaviRoutingInfo) this.instance).getNavigationStatusValue();
            }

            public Builder setNavigationStatusValue(int value) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).setNavigationStatusValue(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
            public NaviStatus getNavigationStatus() {
                return ((NaviRoutingInfo) this.instance).getNavigationStatus();
            }

            public Builder setNavigationStatus(NaviStatus value) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).setNavigationStatus(value);
                return this;
            }

            public Builder clearNavigationStatus() {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).clearNavigationStatus();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
            public long getNavigationId() {
                return ((NaviRoutingInfo) this.instance).getNavigationId();
            }

            public Builder setNavigationId(long value) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).setNavigationId(value);
                return this;
            }

            public Builder clearNavigationId() {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).clearNavigationId();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
            public List<V2_Point> getV2PointsList() {
                return Collections.unmodifiableList(((NaviRoutingInfo) this.instance).getV2PointsList());
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
            public int getV2PointsCount() {
                return ((NaviRoutingInfo) this.instance).getV2PointsCount();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoOrBuilder
            public V2_Point getV2Points(int index) {
                return ((NaviRoutingInfo) this.instance).getV2Points(index);
            }

            public Builder setV2Points(int index, V2_Point value) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).setV2Points(index, value);
                return this;
            }

            public Builder setV2Points(int index, V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).setV2Points(index, builderForValue);
                return this;
            }

            public Builder addV2Points(V2_Point value) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).addV2Points(value);
                return this;
            }

            public Builder addV2Points(int index, V2_Point value) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).addV2Points(index, value);
                return this;
            }

            public Builder addV2Points(V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).addV2Points(builderForValue);
                return this;
            }

            public Builder addV2Points(int index, V2_Point.Builder builderForValue) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).addV2Points(index, builderForValue);
                return this;
            }

            public Builder addAllV2Points(Iterable<? extends V2_Point> values) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).addAllV2Points(values);
                return this;
            }

            public Builder clearV2Points() {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).clearV2Points();
                return this;
            }

            public Builder removeV2Points(int index) {
                copyOnWrite();
                ((NaviRoutingInfo) this.instance).removeV2Points(index);
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new NaviRoutingInfo();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.v2Points_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    NaviRoutingInfo other = (NaviRoutingInfo) arg1;
                    this.navigationStatus_ = visitor.visitInt(this.navigationStatus_ != 0, this.navigationStatus_, other.navigationStatus_ != 0, other.navigationStatus_);
                    this.navigationId_ = visitor.visitLong(this.navigationId_ != 0, this.navigationId_, other.navigationId_ != 0, other.navigationId_);
                    this.v2Points_ = visitor.visitList(this.v2Points_, other.v2Points_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
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
                                    int rawValue = input.readEnum();
                                    this.navigationStatus_ = rawValue;
                                } else if (tag == 16) {
                                    this.navigationId_ = input.readUInt64();
                                } else if (tag != 26) {
                                    if (!input.skipField(tag)) {
                                        done2 = true;
                                    }
                                } else {
                                    if (!this.v2Points_.isModifiable()) {
                                        this.v2Points_ = GeneratedMessageLite.mutableCopy(this.v2Points_);
                                    }
                                    this.v2Points_.add((V2_Point) input.readMessage(V2_Point.parser(), extensionRegistry));
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
                        synchronized (NaviRoutingInfo.class) {
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

        public static NaviRoutingInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NaviRoutingInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class NaviRoutingInfoV2 extends GeneratedMessageLite<NaviRoutingInfoV2, Builder> implements NaviRoutingInfoV2OrBuilder {
        private static final NaviRoutingInfoV2 DEFAULT_INSTANCE = new NaviRoutingInfoV2();
        public static final int NAVIGATION_ID_FIELD_NUMBER = 2;
        public static final int NAVIGATION_STATUS_FIELD_NUMBER = 1;
        private static volatile Parser<NaviRoutingInfoV2> PARSER = null;
        public static final int PATH_FIELD_NUMBER = 5;
        public static final int SDK_MAJOR_VERSION_FIELD_NUMBER = 3;
        public static final int SDK_MINOR_VERSION_FIELD_NUMBER = 4;
        private Path path_;
        private int navigationStatus_ = 0;
        private long navigationId_ = 0;
        private String sdkMajorVersion_ = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
        private String sdkMinorVersion_ = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;

        private NaviRoutingInfoV2() {
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
        public int getNavigationStatusValue() {
            return this.navigationStatus_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
        public NaviStatus getNavigationStatus() {
            NaviStatus result = NaviStatus.forNumber(this.navigationStatus_);
            return result == null ? NaviStatus.UNRECOGNIZED : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNavigationStatusValue(int value) {
            this.navigationStatus_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNavigationStatus(NaviStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.navigationStatus_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNavigationStatus() {
            this.navigationStatus_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
        public long getNavigationId() {
            return this.navigationId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNavigationId(long value) {
            this.navigationId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNavigationId() {
            this.navigationId_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
        public String getSdkMajorVersion() {
            return this.sdkMajorVersion_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
        public ByteString getSdkMajorVersionBytes() {
            return ByteString.copyFromUtf8(this.sdkMajorVersion_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSdkMajorVersion(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.sdkMajorVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSdkMajorVersion() {
            this.sdkMajorVersion_ = getDefaultInstance().getSdkMajorVersion();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSdkMajorVersionBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            this.sdkMajorVersion_ = value.toStringUtf8();
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
        public String getSdkMinorVersion() {
            return this.sdkMinorVersion_;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
        public ByteString getSdkMinorVersionBytes() {
            return ByteString.copyFromUtf8(this.sdkMinorVersion_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSdkMinorVersion(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.sdkMinorVersion_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSdkMinorVersion() {
            this.sdkMinorVersion_ = getDefaultInstance().getSdkMinorVersion();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSdkMinorVersionBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            this.sdkMinorVersion_ = value.toStringUtf8();
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
        public boolean hasPath() {
            return this.path_ != null;
        }

        @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
        public Path getPath() {
            return this.path_ == null ? Path.getDefaultInstance() : this.path_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPath(Path value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.path_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPath(Path.Builder builderForValue) {
            this.path_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePath(Path value) {
            if (this.path_ != null && this.path_ != Path.getDefaultInstance()) {
                this.path_ = Path.newBuilder(this.path_).mergeFrom((Path.Builder) value).buildPartial();
            } else {
                this.path_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPath() {
            this.path_ = null;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.navigationStatus_ != NaviStatus.NAVI_STATUS_INVALID.getNumber()) {
                output.writeEnum(1, this.navigationStatus_);
            }
            if (this.navigationId_ != 0) {
                output.writeUInt64(2, this.navigationId_);
            }
            if (!this.sdkMajorVersion_.isEmpty()) {
                output.writeString(3, getSdkMajorVersion());
            }
            if (!this.sdkMinorVersion_.isEmpty()) {
                output.writeString(4, getSdkMinorVersion());
            }
            if (this.path_ != null) {
                output.writeMessage(5, getPath());
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.navigationStatus_ != NaviStatus.NAVI_STATUS_INVALID.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.navigationStatus_) : 0;
            if (this.navigationId_ != 0) {
                size2 += CodedOutputStream.computeUInt64Size(2, this.navigationId_);
            }
            if (!this.sdkMajorVersion_.isEmpty()) {
                size2 += CodedOutputStream.computeStringSize(3, getSdkMajorVersion());
            }
            if (!this.sdkMinorVersion_.isEmpty()) {
                size2 += CodedOutputStream.computeStringSize(4, getSdkMinorVersion());
            }
            if (this.path_ != null) {
                size2 += CodedOutputStream.computeMessageSize(5, getPath());
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static NaviRoutingInfoV2 parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (NaviRoutingInfoV2) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static NaviRoutingInfoV2 parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (NaviRoutingInfoV2) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static NaviRoutingInfoV2 parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (NaviRoutingInfoV2) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static NaviRoutingInfoV2 parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (NaviRoutingInfoV2) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static NaviRoutingInfoV2 parseFrom(InputStream input) throws IOException {
            return (NaviRoutingInfoV2) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviRoutingInfoV2 parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviRoutingInfoV2) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static NaviRoutingInfoV2 parseDelimitedFrom(InputStream input) throws IOException {
            return (NaviRoutingInfoV2) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviRoutingInfoV2 parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviRoutingInfoV2) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static NaviRoutingInfoV2 parseFrom(CodedInputStream input) throws IOException {
            return (NaviRoutingInfoV2) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviRoutingInfoV2 parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviRoutingInfoV2) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(NaviRoutingInfoV2 prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NaviRoutingInfoV2, Builder> implements NaviRoutingInfoV2OrBuilder {
            private Builder() {
                super(NaviRoutingInfoV2.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
            public int getNavigationStatusValue() {
                return ((NaviRoutingInfoV2) this.instance).getNavigationStatusValue();
            }

            public Builder setNavigationStatusValue(int value) {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).setNavigationStatusValue(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
            public NaviStatus getNavigationStatus() {
                return ((NaviRoutingInfoV2) this.instance).getNavigationStatus();
            }

            public Builder setNavigationStatus(NaviStatus value) {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).setNavigationStatus(value);
                return this;
            }

            public Builder clearNavigationStatus() {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).clearNavigationStatus();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
            public long getNavigationId() {
                return ((NaviRoutingInfoV2) this.instance).getNavigationId();
            }

            public Builder setNavigationId(long value) {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).setNavigationId(value);
                return this;
            }

            public Builder clearNavigationId() {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).clearNavigationId();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
            public String getSdkMajorVersion() {
                return ((NaviRoutingInfoV2) this.instance).getSdkMajorVersion();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
            public ByteString getSdkMajorVersionBytes() {
                return ((NaviRoutingInfoV2) this.instance).getSdkMajorVersionBytes();
            }

            public Builder setSdkMajorVersion(String value) {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).setSdkMajorVersion(value);
                return this;
            }

            public Builder clearSdkMajorVersion() {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).clearSdkMajorVersion();
                return this;
            }

            public Builder setSdkMajorVersionBytes(ByteString value) {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).setSdkMajorVersionBytes(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
            public String getSdkMinorVersion() {
                return ((NaviRoutingInfoV2) this.instance).getSdkMinorVersion();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
            public ByteString getSdkMinorVersionBytes() {
                return ((NaviRoutingInfoV2) this.instance).getSdkMinorVersionBytes();
            }

            public Builder setSdkMinorVersion(String value) {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).setSdkMinorVersion(value);
                return this;
            }

            public Builder clearSdkMinorVersion() {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).clearSdkMinorVersion();
                return this;
            }

            public Builder setSdkMinorVersionBytes(ByteString value) {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).setSdkMinorVersionBytes(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
            public boolean hasPath() {
                return ((NaviRoutingInfoV2) this.instance).hasPath();
            }

            @Override // android.car.hardware.xpu.XpuProtoMessage.NaviRoutingInfoV2OrBuilder
            public Path getPath() {
                return ((NaviRoutingInfoV2) this.instance).getPath();
            }

            public Builder setPath(Path value) {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).setPath(value);
                return this;
            }

            public Builder setPath(Path.Builder builderForValue) {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).setPath(builderForValue);
                return this;
            }

            public Builder mergePath(Path value) {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).mergePath(value);
                return this;
            }

            public Builder clearPath() {
                copyOnWrite();
                ((NaviRoutingInfoV2) this.instance).clearPath();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new NaviRoutingInfoV2();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    NaviRoutingInfoV2 other = (NaviRoutingInfoV2) arg1;
                    this.navigationStatus_ = visitor.visitInt(this.navigationStatus_ != 0, this.navigationStatus_, other.navigationStatus_ != 0, other.navigationStatus_);
                    this.navigationId_ = visitor.visitLong(this.navigationId_ != 0, this.navigationId_, other.navigationId_ != 0, other.navigationId_);
                    this.sdkMajorVersion_ = visitor.visitString(!this.sdkMajorVersion_.isEmpty(), this.sdkMajorVersion_, !other.sdkMajorVersion_.isEmpty(), other.sdkMajorVersion_);
                    this.sdkMinorVersion_ = visitor.visitString(!this.sdkMinorVersion_.isEmpty(), this.sdkMinorVersion_, true ^ other.sdkMinorVersion_.isEmpty(), other.sdkMinorVersion_);
                    this.path_ = (Path) visitor.visitMessage(this.path_, other.path_);
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
                                    int rawValue = input.readEnum();
                                    this.navigationStatus_ = rawValue;
                                } else if (tag == 16) {
                                    this.navigationId_ = input.readUInt64();
                                } else if (tag == 26) {
                                    String s = input.readStringRequireUtf8();
                                    this.sdkMajorVersion_ = s;
                                } else if (tag == 34) {
                                    String s2 = input.readStringRequireUtf8();
                                    this.sdkMinorVersion_ = s2;
                                } else if (tag != 42) {
                                    if (!input.skipField(tag)) {
                                        done2 = true;
                                    }
                                } else {
                                    Path.Builder subBuilder = null;
                                    if (this.path_ != null) {
                                        subBuilder = this.path_.toBuilder();
                                    }
                                    this.path_ = (Path) input.readMessage(Path.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((Path.Builder) this.path_);
                                        this.path_ = (Path) subBuilder.buildPartial();
                                    }
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
                        synchronized (NaviRoutingInfoV2.class) {
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

        public static NaviRoutingInfoV2 getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NaviRoutingInfoV2> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
