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
public final class XpuXNgpNaviInfo {

    /* loaded from: classes.dex */
    public interface Coord2DDoubleOrBuilder extends MessageLiteOrBuilder {
        double getX();

        double getY();
    }

    /* loaded from: classes.dex */
    public interface LaneInfoOrBuilder extends MessageLiteOrBuilder {
        int getBackExtenLane(int i);

        int getBackExtenLaneCount();

        List<Integer> getBackExtenLaneList();

        int getBackLane(int i);

        int getBackLaneCount();

        List<Integer> getBackLaneList();

        int getExtensionLane(int i);

        int getExtensionLaneCount();

        List<Integer> getExtensionLaneList();

        int getFrontExtenLane(int i);

        int getFrontExtenLaneCount();

        List<Integer> getFrontExtenLaneList();

        int getFrontLane(int i);

        int getFrontLaneCount();

        List<Integer> getFrontLaneList();

        int getLaneType();

        int getLinkIdx();

        Coord2DDouble getPoint();

        int getSegmentIdx();

        boolean hasPoint();
    }

    /* loaded from: classes.dex */
    public interface ManeuverInfoOrBuilder extends MessageLiteOrBuilder {
        String getManeuverData();

        ByteString getManeuverDataBytes();

        long getManeuverID();

        int getManeuverType();
    }

    /* loaded from: classes.dex */
    public interface NaviInfoOrBuilder extends MessageLiteOrBuilder {
        String getCurRouteName();

        ByteString getCurRouteNameBytes();

        String getExitInfo();

        ByteString getExitInfoBytes();

        int getExitInfoType();

        boolean getIsShowExitInfo();

        boolean getIsTightTurnShow();

        double getNextManeuverDist();

        String getNextManeuverDistDisplay();

        ByteString getNextManeuverDistDisplayBytes();

        int getNextManeuverDistUnitDisplay();

        double getNextManeuverID();

        String getNextRouteName();

        ByteString getNextRouteNameBytes();

        double getRouteRemainDist();

        String getRouteRemainDistDisplay();

        ByteString getRouteRemainDistDisplayBytes();

        int getRouteRemainDistUnitDisplay();

        double getRouteRemainTime();

        double getSegmentRemainDist();

        String getSegmentRemainDistDisplay();

        ByteString getSegmentRemainDistDisplayBytes();

        int getSegmentRemainDistUnitDisplay();

        double getSegmentRemainProgress();
    }

    /* loaded from: classes.dex */
    public interface SDNavigationOrBuilder extends MessageLiteOrBuilder {
        LaneInfo getLaneInfo();

        ManeuverInfo getManeuverInfo();

        NaviInfo getNaviInfo();

        SDNavigation.SdInfoCase getSdInfoCase();
    }

    private XpuXNgpNaviInfo() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    /* loaded from: classes.dex */
    public enum ExitInfoType implements Internal.EnumLite {
        EXIT(0),
        ENTRANCE(1),
        UNRECOGNIZED(-1);
        
        public static final int ENTRANCE_VALUE = 1;
        public static final int EXIT_VALUE = 0;
        private static final Internal.EnumLiteMap<ExitInfoType> internalValueMap = new Internal.EnumLiteMap<ExitInfoType>() { // from class: android.car.hardware.xpu.XpuXNgpNaviInfo.ExitInfoType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public ExitInfoType findValueByNumber(int number) {
                return ExitInfoType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ExitInfoType valueOf(int value) {
            return forNumber(value);
        }

        public static ExitInfoType forNumber(int value) {
            switch (value) {
                case 0:
                    return EXIT;
                case 1:
                    return ENTRANCE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ExitInfoType> internalGetValueMap() {
            return internalValueMap;
        }

        ExitInfoType(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public enum NextManeuverID implements Internal.EnumLite {
        LEFT_TURN(0),
        RIGHT_TURN(1),
        UNRECOGNIZED(-1);
        
        public static final int LEFT_TURN_VALUE = 0;
        public static final int RIGHT_TURN_VALUE = 1;
        private static final Internal.EnumLiteMap<NextManeuverID> internalValueMap = new Internal.EnumLiteMap<NextManeuverID>() { // from class: android.car.hardware.xpu.XpuXNgpNaviInfo.NextManeuverID.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public NextManeuverID findValueByNumber(int number) {
                return NextManeuverID.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static NextManeuverID valueOf(int value) {
            return forNumber(value);
        }

        public static NextManeuverID forNumber(int value) {
            switch (value) {
                case 0:
                    return LEFT_TURN;
                case 1:
                    return RIGHT_TURN;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<NextManeuverID> internalGetValueMap() {
            return internalValueMap;
        }

        NextManeuverID(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public enum UnitType implements Internal.EnumLite {
        METER(0),
        temp_UnitType_1(1),
        temp_UnitType_2(2),
        temp_UnitType_3(3),
        temp_UnitType_4(4),
        UNRECOGNIZED(-1);
        
        public static final int METER_VALUE = 0;
        private static final Internal.EnumLiteMap<UnitType> internalValueMap = new Internal.EnumLiteMap<UnitType>() { // from class: android.car.hardware.xpu.XpuXNgpNaviInfo.UnitType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public UnitType findValueByNumber(int number) {
                return UnitType.forNumber(number);
            }
        };
        public static final int temp_UnitType_1_VALUE = 1;
        public static final int temp_UnitType_2_VALUE = 2;
        public static final int temp_UnitType_3_VALUE = 3;
        public static final int temp_UnitType_4_VALUE = 4;
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static UnitType valueOf(int value) {
            return forNumber(value);
        }

        public static UnitType forNumber(int value) {
            switch (value) {
                case 0:
                    return METER;
                case 1:
                    return temp_UnitType_1;
                case 2:
                    return temp_UnitType_2;
                case 3:
                    return temp_UnitType_3;
                case 4:
                    return temp_UnitType_4;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<UnitType> internalGetValueMap() {
            return internalValueMap;
        }

        UnitType(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public enum LaneType implements Internal.EnumLite {
        NORMAL_ROAD(0),
        TOLL_STATION(1),
        UNRECOGNIZED(-1);
        
        public static final int NORMAL_ROAD_VALUE = 0;
        public static final int TOLL_STATION_VALUE = 1;
        private static final Internal.EnumLiteMap<LaneType> internalValueMap = new Internal.EnumLiteMap<LaneType>() { // from class: android.car.hardware.xpu.XpuXNgpNaviInfo.LaneType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public LaneType findValueByNumber(int number) {
                return LaneType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static LaneType valueOf(int value) {
            return forNumber(value);
        }

        public static LaneType forNumber(int value) {
            switch (value) {
                case 0:
                    return NORMAL_ROAD;
                case 1:
                    return TOLL_STATION;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<LaneType> internalGetValueMap() {
            return internalValueMap;
        }

        LaneType(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public enum LaneAction implements Internal.EnumLite {
        LaneActionAhead(0),
        LaneActionLeft(1),
        LaneActionAheadLeft(2),
        LaneActionRight(3),
        LaneActionAheadRight(4),
        LaneActionLUTurn(5),
        LaneActionLeftRight(6),
        LaneActionAheadLeftRight(7),
        LaneActionRUTurn(8),
        LaneActionAheadLUTurn(9),
        LaneActionAheadRUTurn(10),
        LaneActionLeftLUTurn(11),
        LaneActionRightRUTurn(12),
        LaneActionLeftInAheadReserved(13),
        LaneActionLeftLUturnReserved(14),
        LaneActionReserved(15),
        LaneActionAheadLeftLUTurn(16),
        LaneActionRightLUTurn(17),
        LaneActionLeftRightLUTurn(18),
        LaneActionAheadRightLUTurn(19),
        LaneActionLeftRUTurn(20),
        LaneActionBus(21),
        LaneActionEmpty(22),
        LaneActionVariable(23),
        LaneActionDedicated(24),
        LaneActionTidal(25),
        LaneActionNULL(255),
        UNRECOGNIZED(-1);
        
        public static final int LaneActionAheadLUTurn_VALUE = 9;
        public static final int LaneActionAheadLeftLUTurn_VALUE = 16;
        public static final int LaneActionAheadLeftRight_VALUE = 7;
        public static final int LaneActionAheadLeft_VALUE = 2;
        public static final int LaneActionAheadRUTurn_VALUE = 10;
        public static final int LaneActionAheadRightLUTurn_VALUE = 19;
        public static final int LaneActionAheadRight_VALUE = 4;
        public static final int LaneActionAhead_VALUE = 0;
        public static final int LaneActionBus_VALUE = 21;
        public static final int LaneActionDedicated_VALUE = 24;
        public static final int LaneActionEmpty_VALUE = 22;
        public static final int LaneActionLUTurn_VALUE = 5;
        public static final int LaneActionLeftInAheadReserved_VALUE = 13;
        public static final int LaneActionLeftLUTurn_VALUE = 11;
        public static final int LaneActionLeftLUturnReserved_VALUE = 14;
        public static final int LaneActionLeftRUTurn_VALUE = 20;
        public static final int LaneActionLeftRightLUTurn_VALUE = 18;
        public static final int LaneActionLeftRight_VALUE = 6;
        public static final int LaneActionLeft_VALUE = 1;
        public static final int LaneActionNULL_VALUE = 255;
        public static final int LaneActionRUTurn_VALUE = 8;
        public static final int LaneActionReserved_VALUE = 15;
        public static final int LaneActionRightLUTurn_VALUE = 17;
        public static final int LaneActionRightRUTurn_VALUE = 12;
        public static final int LaneActionRight_VALUE = 3;
        public static final int LaneActionTidal_VALUE = 25;
        public static final int LaneActionVariable_VALUE = 23;
        private static final Internal.EnumLiteMap<LaneAction> internalValueMap = new Internal.EnumLiteMap<LaneAction>() { // from class: android.car.hardware.xpu.XpuXNgpNaviInfo.LaneAction.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public LaneAction findValueByNumber(int number) {
                return LaneAction.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static LaneAction valueOf(int value) {
            return forNumber(value);
        }

        public static LaneAction forNumber(int value) {
            if (value != 255) {
                switch (value) {
                    case 0:
                        return LaneActionAhead;
                    case 1:
                        return LaneActionLeft;
                    case 2:
                        return LaneActionAheadLeft;
                    case 3:
                        return LaneActionRight;
                    case 4:
                        return LaneActionAheadRight;
                    case 5:
                        return LaneActionLUTurn;
                    case 6:
                        return LaneActionLeftRight;
                    case 7:
                        return LaneActionAheadLeftRight;
                    case 8:
                        return LaneActionRUTurn;
                    case 9:
                        return LaneActionAheadLUTurn;
                    case 10:
                        return LaneActionAheadRUTurn;
                    case 11:
                        return LaneActionLeftLUTurn;
                    case 12:
                        return LaneActionRightRUTurn;
                    case 13:
                        return LaneActionLeftInAheadReserved;
                    case 14:
                        return LaneActionLeftLUturnReserved;
                    case 15:
                        return LaneActionReserved;
                    case 16:
                        return LaneActionAheadLeftLUTurn;
                    case 17:
                        return LaneActionRightLUTurn;
                    case 18:
                        return LaneActionLeftRightLUTurn;
                    case 19:
                        return LaneActionAheadRightLUTurn;
                    case 20:
                        return LaneActionLeftRUTurn;
                    case 21:
                        return LaneActionBus;
                    case 22:
                        return LaneActionEmpty;
                    case 23:
                        return LaneActionVariable;
                    case 24:
                        return LaneActionDedicated;
                    case 25:
                        return LaneActionTidal;
                    default:
                        return null;
                }
            }
            return LaneActionNULL;
        }

        public static Internal.EnumLiteMap<LaneAction> internalGetValueMap() {
            return internalValueMap;
        }

        LaneAction(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public enum ExtenLaneAction implements Internal.EnumLite {
        ExtenLaneActionNULL(0),
        ExtenLaneActionNormal(1),
        ExtenLaneActionLeft(2),
        ExtenLaneActionRight(3),
        ExtenLaneActionLeftNarrow(4),
        ExtenLaneActionRightNarrow(5),
        UNRECOGNIZED(-1);
        
        public static final int ExtenLaneActionLeftNarrow_VALUE = 4;
        public static final int ExtenLaneActionLeft_VALUE = 2;
        public static final int ExtenLaneActionNULL_VALUE = 0;
        public static final int ExtenLaneActionNormal_VALUE = 1;
        public static final int ExtenLaneActionRightNarrow_VALUE = 5;
        public static final int ExtenLaneActionRight_VALUE = 3;
        private static final Internal.EnumLiteMap<ExtenLaneAction> internalValueMap = new Internal.EnumLiteMap<ExtenLaneAction>() { // from class: android.car.hardware.xpu.XpuXNgpNaviInfo.ExtenLaneAction.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.carsdk.protobuf.Internal.EnumLiteMap
            public ExtenLaneAction findValueByNumber(int number) {
                return ExtenLaneAction.forNumber(number);
            }
        };
        private final int value;

        @Override // com.android.carsdk.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ExtenLaneAction valueOf(int value) {
            return forNumber(value);
        }

        public static ExtenLaneAction forNumber(int value) {
            switch (value) {
                case 0:
                    return ExtenLaneActionNULL;
                case 1:
                    return ExtenLaneActionNormal;
                case 2:
                    return ExtenLaneActionLeft;
                case 3:
                    return ExtenLaneActionRight;
                case 4:
                    return ExtenLaneActionLeftNarrow;
                case 5:
                    return ExtenLaneActionRightNarrow;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ExtenLaneAction> internalGetValueMap() {
            return internalValueMap;
        }

        ExtenLaneAction(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes.dex */
    public enum ManeuverType implements Internal.EnumLite {
        ManeuverIconNull(0),
        ManeuverIconCar(1),
        ManeuverIconTurnLeft(2),
        ManeuverIconTurnRight(3),
        ManeuverIconSlightLeft(4),
        ManeuverIconSlightRight(5),
        ManeuverIconTurnHardLeft(6),
        ManeuverIconTurnHardRight(7),
        ManeuverIconUTurn(8),
        ManeuverIconContinue(9),
        ManeuverIconWay(10),
        ManeuverIconEntryRing(11),
        ManeuverIconLeaveRing(12),
        ManeuverIconSAPA(13),
        ManeuverIconTollGate(14),
        ManeuverIconDestination(15),
        ManeuverIconTunnel(16),
        ManeuverIconEntryLeftRing(17),
        ManeuverIconLeaveLeftRing(18),
        ManeuverIconUTurnRight(19),
        ManeuverIconSpecialContinue(20),
        ManeuverIconEntryRingLeft(21),
        ManeuverIconEntryRingRight(22),
        ManeuverIconEntryRingContinue(23),
        ManeuverIconEntryRingUTurn(24),
        ManeuverIconEntryLeftRingLeft(25),
        ManeuverIconEntryLeftRingRight(26),
        ManeuverIconEntryLeftRingContinue(27),
        ManeuverIconEntryLeftRingUTurn(28),
        ManeuverIconEntryRight(29),
        ManeuverIconMergeLeft(30),
        ManeuverIconEntryLeft(31),
        ManeuverIconMergeRight(32),
        ManeuverIconExitLeft(33),
        ManeuverIconExitRight(34),
        ManeuverIconHookTurnRight(35),
        ManeuverIconGetFerry(36),
        ManeuverIconGrtRoundAbout45(37),
        ManeuverIconGrtRoundAbout135(38),
        ManeuverIconGrtRoundAbout225(39),
        ManeuverIconGrtRoundAbout315(40),
        ManeuverIconsWaypointLeft(41),
        ManeuverIconsWaypointRight(42),
        ManeuverIconLocationLeft(43),
        ManeuverIconLocationRight(44),
        ManeuverIconStayLeft(45),
        ManeuverIconStayRight(46),
        ManeuverIconSplitLeft(65),
        ManeuverIconSplitRight(66),
        UNRECOGNIZED(-1);
        
        public static final int ManeuverIconCar_VALUE = 1;
        public static final int ManeuverIconContinue_VALUE = 9;
        public static final int ManeuverIconDestination_VALUE = 15;
        public static final int ManeuverIconEntryLeftRingContinue_VALUE = 27;
        public static final int ManeuverIconEntryLeftRingLeft_VALUE = 25;
        public static final int ManeuverIconEntryLeftRingRight_VALUE = 26;
        public static final int ManeuverIconEntryLeftRingUTurn_VALUE = 28;
        public static final int ManeuverIconEntryLeftRing_VALUE = 17;
        public static final int ManeuverIconEntryLeft_VALUE = 31;
        public static final int ManeuverIconEntryRight_VALUE = 29;
        public static final int ManeuverIconEntryRingContinue_VALUE = 23;
        public static final int ManeuverIconEntryRingLeft_VALUE = 21;
        public static final int ManeuverIconEntryRingRight_VALUE = 22;
        public static final int ManeuverIconEntryRingUTurn_VALUE = 24;
        public static final int ManeuverIconEntryRing_VALUE = 11;
        public static final int ManeuverIconExitLeft_VALUE = 33;
        public static final int ManeuverIconExitRight_VALUE = 34;
        public static final int ManeuverIconGetFerry_VALUE = 36;
        public static final int ManeuverIconGrtRoundAbout135_VALUE = 38;
        public static final int ManeuverIconGrtRoundAbout225_VALUE = 39;
        public static final int ManeuverIconGrtRoundAbout315_VALUE = 40;
        public static final int ManeuverIconGrtRoundAbout45_VALUE = 37;
        public static final int ManeuverIconHookTurnRight_VALUE = 35;
        public static final int ManeuverIconLeaveLeftRing_VALUE = 18;
        public static final int ManeuverIconLeaveRing_VALUE = 12;
        public static final int ManeuverIconLocationLeft_VALUE = 43;
        public static final int ManeuverIconLocationRight_VALUE = 44;
        public static final int ManeuverIconMergeLeft_VALUE = 30;
        public static final int ManeuverIconMergeRight_VALUE = 32;
        public static final int ManeuverIconNull_VALUE = 0;
        public static final int ManeuverIconSAPA_VALUE = 13;
        public static final int ManeuverIconSlightLeft_VALUE = 4;
        public static final int ManeuverIconSlightRight_VALUE = 5;
        public static final int ManeuverIconSpecialContinue_VALUE = 20;
        public static final int ManeuverIconSplitLeft_VALUE = 65;
        public static final int ManeuverIconSplitRight_VALUE = 66;
        public static final int ManeuverIconStayLeft_VALUE = 45;
        public static final int ManeuverIconStayRight_VALUE = 46;
        public static final int ManeuverIconTollGate_VALUE = 14;
        public static final int ManeuverIconTunnel_VALUE = 16;
        public static final int ManeuverIconTurnHardLeft_VALUE = 6;
        public static final int ManeuverIconTurnHardRight_VALUE = 7;
        public static final int ManeuverIconTurnLeft_VALUE = 2;
        public static final int ManeuverIconTurnRight_VALUE = 3;
        public static final int ManeuverIconUTurnRight_VALUE = 19;
        public static final int ManeuverIconUTurn_VALUE = 8;
        public static final int ManeuverIconWay_VALUE = 10;
        public static final int ManeuverIconsWaypointLeft_VALUE = 41;
        public static final int ManeuverIconsWaypointRight_VALUE = 42;
        private static final Internal.EnumLiteMap<ManeuverType> internalValueMap = new Internal.EnumLiteMap<ManeuverType>() { // from class: android.car.hardware.xpu.XpuXNgpNaviInfo.ManeuverType.1
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
                    return ManeuverIconNull;
                case 1:
                    return ManeuverIconCar;
                case 2:
                    return ManeuverIconTurnLeft;
                case 3:
                    return ManeuverIconTurnRight;
                case 4:
                    return ManeuverIconSlightLeft;
                case 5:
                    return ManeuverIconSlightRight;
                case 6:
                    return ManeuverIconTurnHardLeft;
                case 7:
                    return ManeuverIconTurnHardRight;
                case 8:
                    return ManeuverIconUTurn;
                case 9:
                    return ManeuverIconContinue;
                case 10:
                    return ManeuverIconWay;
                case 11:
                    return ManeuverIconEntryRing;
                case 12:
                    return ManeuverIconLeaveRing;
                case 13:
                    return ManeuverIconSAPA;
                case 14:
                    return ManeuverIconTollGate;
                case 15:
                    return ManeuverIconDestination;
                case 16:
                    return ManeuverIconTunnel;
                case 17:
                    return ManeuverIconEntryLeftRing;
                case 18:
                    return ManeuverIconLeaveLeftRing;
                case 19:
                    return ManeuverIconUTurnRight;
                case 20:
                    return ManeuverIconSpecialContinue;
                case 21:
                    return ManeuverIconEntryRingLeft;
                case 22:
                    return ManeuverIconEntryRingRight;
                case 23:
                    return ManeuverIconEntryRingContinue;
                case 24:
                    return ManeuverIconEntryRingUTurn;
                case 25:
                    return ManeuverIconEntryLeftRingLeft;
                case 26:
                    return ManeuverIconEntryLeftRingRight;
                case 27:
                    return ManeuverIconEntryLeftRingContinue;
                case 28:
                    return ManeuverIconEntryLeftRingUTurn;
                case 29:
                    return ManeuverIconEntryRight;
                case 30:
                    return ManeuverIconMergeLeft;
                case 31:
                    return ManeuverIconEntryLeft;
                case 32:
                    return ManeuverIconMergeRight;
                case 33:
                    return ManeuverIconExitLeft;
                case 34:
                    return ManeuverIconExitRight;
                case 35:
                    return ManeuverIconHookTurnRight;
                case 36:
                    return ManeuverIconGetFerry;
                case 37:
                    return ManeuverIconGrtRoundAbout45;
                case 38:
                    return ManeuverIconGrtRoundAbout135;
                case 39:
                    return ManeuverIconGrtRoundAbout225;
                case 40:
                    return ManeuverIconGrtRoundAbout315;
                case 41:
                    return ManeuverIconsWaypointLeft;
                case 42:
                    return ManeuverIconsWaypointRight;
                case 43:
                    return ManeuverIconLocationLeft;
                case 44:
                    return ManeuverIconLocationRight;
                case 45:
                    return ManeuverIconStayLeft;
                case 46:
                    return ManeuverIconStayRight;
                default:
                    switch (value) {
                        case 65:
                            return ManeuverIconSplitLeft;
                        case 66:
                            return ManeuverIconSplitRight;
                        default:
                            return null;
                    }
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
    public static final class NaviInfo extends GeneratedMessageLite<NaviInfo, Builder> implements NaviInfoOrBuilder {
        public static final int CURROUTENAME_FIELD_NUMBER = 1;
        private static final NaviInfo DEFAULT_INSTANCE = new NaviInfo();
        public static final int EXITINFOTYPE_FIELD_NUMBER = 8;
        public static final int EXITINFO_FIELD_NUMBER = 7;
        public static final int ISSHOWEXITINFO_FIELD_NUMBER = 6;
        public static final int ISTIGHTTURNSHOW_FIELD_NUMBER = 14;
        public static final int NEXTMANEUVERDISTDISPLAY_FIELD_NUMBER = 17;
        public static final int NEXTMANEUVERDISTUNITDISPLAY_FIELD_NUMBER = 18;
        public static final int NEXTMANEUVERDIST_FIELD_NUMBER = 16;
        public static final int NEXTMANEUVERID_FIELD_NUMBER = 15;
        public static final int NEXTROUTENAME_FIELD_NUMBER = 9;
        private static volatile Parser<NaviInfo> PARSER = null;
        public static final int ROUTEREMAINDISTDISPLAY_FIELD_NUMBER = 3;
        public static final int ROUTEREMAINDISTUNITDISPLAY_FIELD_NUMBER = 4;
        public static final int ROUTEREMAINDIST_FIELD_NUMBER = 2;
        public static final int ROUTEREMAINTIME_FIELD_NUMBER = 5;
        public static final int SEGMENTREMAINDISTDISPLAY_FIELD_NUMBER = 11;
        public static final int SEGMENTREMAINDISTUNITDISPLAY_FIELD_NUMBER = 12;
        public static final int SEGMENTREMAINDIST_FIELD_NUMBER = 10;
        public static final int SEGMENTREMAINPROGRESS_FIELD_NUMBER = 13;
        private String curRouteName_ = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
        private double routeRemainDist_ = 0.0d;
        private String routeRemainDistDisplay_ = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
        private int routeRemainDistUnitDisplay_ = 0;
        private double routeRemainTime_ = 0.0d;
        private boolean isShowExitInfo_ = false;
        private String exitInfo_ = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
        private int exitInfoType_ = 0;
        private String nextRouteName_ = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
        private double segmentRemainDist_ = 0.0d;
        private String segmentRemainDistDisplay_ = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
        private int segmentRemainDistUnitDisplay_ = 0;
        private double segmentRemainProgress_ = 0.0d;
        private boolean isTightTurnShow_ = false;
        private double nextManeuverID_ = 0.0d;
        private double nextManeuverDist_ = 0.0d;
        private String nextManeuverDistDisplay_ = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
        private int nextManeuverDistUnitDisplay_ = 0;

        private NaviInfo() {
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public String getCurRouteName() {
            return this.curRouteName_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public ByteString getCurRouteNameBytes() {
            return ByteString.copyFromUtf8(this.curRouteName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurRouteName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.curRouteName_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCurRouteName() {
            this.curRouteName_ = getDefaultInstance().getCurRouteName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurRouteNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            this.curRouteName_ = value.toStringUtf8();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public double getRouteRemainDist() {
            return this.routeRemainDist_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRouteRemainDist(double value) {
            this.routeRemainDist_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRouteRemainDist() {
            this.routeRemainDist_ = 0.0d;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public String getRouteRemainDistDisplay() {
            return this.routeRemainDistDisplay_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public ByteString getRouteRemainDistDisplayBytes() {
            return ByteString.copyFromUtf8(this.routeRemainDistDisplay_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRouteRemainDistDisplay(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.routeRemainDistDisplay_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRouteRemainDistDisplay() {
            this.routeRemainDistDisplay_ = getDefaultInstance().getRouteRemainDistDisplay();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRouteRemainDistDisplayBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            this.routeRemainDistDisplay_ = value.toStringUtf8();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public int getRouteRemainDistUnitDisplay() {
            return this.routeRemainDistUnitDisplay_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRouteRemainDistUnitDisplay(int value) {
            this.routeRemainDistUnitDisplay_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRouteRemainDistUnitDisplay() {
            this.routeRemainDistUnitDisplay_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public double getRouteRemainTime() {
            return this.routeRemainTime_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRouteRemainTime(double value) {
            this.routeRemainTime_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRouteRemainTime() {
            this.routeRemainTime_ = 0.0d;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public boolean getIsShowExitInfo() {
            return this.isShowExitInfo_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsShowExitInfo(boolean value) {
            this.isShowExitInfo_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsShowExitInfo() {
            this.isShowExitInfo_ = false;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public String getExitInfo() {
            return this.exitInfo_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public ByteString getExitInfoBytes() {
            return ByteString.copyFromUtf8(this.exitInfo_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExitInfo(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.exitInfo_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExitInfo() {
            this.exitInfo_ = getDefaultInstance().getExitInfo();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExitInfoBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            this.exitInfo_ = value.toStringUtf8();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public int getExitInfoType() {
            return this.exitInfoType_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExitInfoType(int value) {
            this.exitInfoType_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExitInfoType() {
            this.exitInfoType_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public String getNextRouteName() {
            return this.nextRouteName_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public ByteString getNextRouteNameBytes() {
            return ByteString.copyFromUtf8(this.nextRouteName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextRouteName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.nextRouteName_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNextRouteName() {
            this.nextRouteName_ = getDefaultInstance().getNextRouteName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextRouteNameBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            this.nextRouteName_ = value.toStringUtf8();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public double getSegmentRemainDist() {
            return this.segmentRemainDist_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentRemainDist(double value) {
            this.segmentRemainDist_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSegmentRemainDist() {
            this.segmentRemainDist_ = 0.0d;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public String getSegmentRemainDistDisplay() {
            return this.segmentRemainDistDisplay_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public ByteString getSegmentRemainDistDisplayBytes() {
            return ByteString.copyFromUtf8(this.segmentRemainDistDisplay_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentRemainDistDisplay(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.segmentRemainDistDisplay_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSegmentRemainDistDisplay() {
            this.segmentRemainDistDisplay_ = getDefaultInstance().getSegmentRemainDistDisplay();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentRemainDistDisplayBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            this.segmentRemainDistDisplay_ = value.toStringUtf8();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public int getSegmentRemainDistUnitDisplay() {
            return this.segmentRemainDistUnitDisplay_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentRemainDistUnitDisplay(int value) {
            this.segmentRemainDistUnitDisplay_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSegmentRemainDistUnitDisplay() {
            this.segmentRemainDistUnitDisplay_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public double getSegmentRemainProgress() {
            return this.segmentRemainProgress_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentRemainProgress(double value) {
            this.segmentRemainProgress_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSegmentRemainProgress() {
            this.segmentRemainProgress_ = 0.0d;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public boolean getIsTightTurnShow() {
            return this.isTightTurnShow_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsTightTurnShow(boolean value) {
            this.isTightTurnShow_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsTightTurnShow() {
            this.isTightTurnShow_ = false;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public double getNextManeuverID() {
            return this.nextManeuverID_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextManeuverID(double value) {
            this.nextManeuverID_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNextManeuverID() {
            this.nextManeuverID_ = 0.0d;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public double getNextManeuverDist() {
            return this.nextManeuverDist_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextManeuverDist(double value) {
            this.nextManeuverDist_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNextManeuverDist() {
            this.nextManeuverDist_ = 0.0d;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public String getNextManeuverDistDisplay() {
            return this.nextManeuverDistDisplay_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public ByteString getNextManeuverDistDisplayBytes() {
            return ByteString.copyFromUtf8(this.nextManeuverDistDisplay_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextManeuverDistDisplay(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.nextManeuverDistDisplay_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNextManeuverDistDisplay() {
            this.nextManeuverDistDisplay_ = getDefaultInstance().getNextManeuverDistDisplay();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextManeuverDistDisplayBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            this.nextManeuverDistDisplay_ = value.toStringUtf8();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
        public int getNextManeuverDistUnitDisplay() {
            return this.nextManeuverDistUnitDisplay_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextManeuverDistUnitDisplay(int value) {
            this.nextManeuverDistUnitDisplay_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNextManeuverDistUnitDisplay() {
            this.nextManeuverDistUnitDisplay_ = 0;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (!this.curRouteName_.isEmpty()) {
                output.writeString(1, getCurRouteName());
            }
            if (this.routeRemainDist_ != 0.0d) {
                output.writeDouble(2, this.routeRemainDist_);
            }
            if (!this.routeRemainDistDisplay_.isEmpty()) {
                output.writeString(3, getRouteRemainDistDisplay());
            }
            if (this.routeRemainDistUnitDisplay_ != 0) {
                output.writeInt32(4, this.routeRemainDistUnitDisplay_);
            }
            if (this.routeRemainTime_ != 0.0d) {
                output.writeDouble(5, this.routeRemainTime_);
            }
            if (this.isShowExitInfo_) {
                output.writeBool(6, this.isShowExitInfo_);
            }
            if (!this.exitInfo_.isEmpty()) {
                output.writeString(7, getExitInfo());
            }
            if (this.exitInfoType_ != 0) {
                output.writeInt32(8, this.exitInfoType_);
            }
            if (!this.nextRouteName_.isEmpty()) {
                output.writeString(9, getNextRouteName());
            }
            if (this.segmentRemainDist_ != 0.0d) {
                output.writeDouble(10, this.segmentRemainDist_);
            }
            if (!this.segmentRemainDistDisplay_.isEmpty()) {
                output.writeString(11, getSegmentRemainDistDisplay());
            }
            if (this.segmentRemainDistUnitDisplay_ != 0) {
                output.writeInt32(12, this.segmentRemainDistUnitDisplay_);
            }
            if (this.segmentRemainProgress_ != 0.0d) {
                output.writeDouble(13, this.segmentRemainProgress_);
            }
            if (this.isTightTurnShow_) {
                output.writeBool(14, this.isTightTurnShow_);
            }
            if (this.nextManeuverID_ != 0.0d) {
                output.writeDouble(15, this.nextManeuverID_);
            }
            if (this.nextManeuverDist_ != 0.0d) {
                output.writeDouble(16, this.nextManeuverDist_);
            }
            if (!this.nextManeuverDistDisplay_.isEmpty()) {
                output.writeString(17, getNextManeuverDistDisplay());
            }
            if (this.nextManeuverDistUnitDisplay_ != 0) {
                output.writeInt32(18, this.nextManeuverDistUnitDisplay_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.curRouteName_.isEmpty() ? 0 : 0 + CodedOutputStream.computeStringSize(1, getCurRouteName());
            if (this.routeRemainDist_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(2, this.routeRemainDist_);
            }
            if (!this.routeRemainDistDisplay_.isEmpty()) {
                size2 += CodedOutputStream.computeStringSize(3, getRouteRemainDistDisplay());
            }
            if (this.routeRemainDistUnitDisplay_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(4, this.routeRemainDistUnitDisplay_);
            }
            if (this.routeRemainTime_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(5, this.routeRemainTime_);
            }
            if (this.isShowExitInfo_) {
                size2 += CodedOutputStream.computeBoolSize(6, this.isShowExitInfo_);
            }
            if (!this.exitInfo_.isEmpty()) {
                size2 += CodedOutputStream.computeStringSize(7, getExitInfo());
            }
            if (this.exitInfoType_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(8, this.exitInfoType_);
            }
            if (!this.nextRouteName_.isEmpty()) {
                size2 += CodedOutputStream.computeStringSize(9, getNextRouteName());
            }
            if (this.segmentRemainDist_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(10, this.segmentRemainDist_);
            }
            if (!this.segmentRemainDistDisplay_.isEmpty()) {
                size2 += CodedOutputStream.computeStringSize(11, getSegmentRemainDistDisplay());
            }
            if (this.segmentRemainDistUnitDisplay_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(12, this.segmentRemainDistUnitDisplay_);
            }
            if (this.segmentRemainProgress_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(13, this.segmentRemainProgress_);
            }
            if (this.isTightTurnShow_) {
                size2 += CodedOutputStream.computeBoolSize(14, this.isTightTurnShow_);
            }
            if (this.nextManeuverID_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(15, this.nextManeuverID_);
            }
            if (this.nextManeuverDist_ != 0.0d) {
                size2 += CodedOutputStream.computeDoubleSize(16, this.nextManeuverDist_);
            }
            if (!this.nextManeuverDistDisplay_.isEmpty()) {
                size2 += CodedOutputStream.computeStringSize(17, getNextManeuverDistDisplay());
            }
            if (this.nextManeuverDistUnitDisplay_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(18, this.nextManeuverDistUnitDisplay_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static NaviInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (NaviInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static NaviInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (NaviInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static NaviInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (NaviInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static NaviInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (NaviInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static NaviInfo parseFrom(InputStream input) throws IOException {
            return (NaviInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static NaviInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (NaviInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static NaviInfo parseFrom(CodedInputStream input) throws IOException {
            return (NaviInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static NaviInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (NaviInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(NaviInfo prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NaviInfo, Builder> implements NaviInfoOrBuilder {
            private Builder() {
                super(NaviInfo.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public String getCurRouteName() {
                return ((NaviInfo) this.instance).getCurRouteName();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public ByteString getCurRouteNameBytes() {
                return ((NaviInfo) this.instance).getCurRouteNameBytes();
            }

            public Builder setCurRouteName(String value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setCurRouteName(value);
                return this;
            }

            public Builder clearCurRouteName() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearCurRouteName();
                return this;
            }

            public Builder setCurRouteNameBytes(ByteString value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setCurRouteNameBytes(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public double getRouteRemainDist() {
                return ((NaviInfo) this.instance).getRouteRemainDist();
            }

            public Builder setRouteRemainDist(double value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setRouteRemainDist(value);
                return this;
            }

            public Builder clearRouteRemainDist() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearRouteRemainDist();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public String getRouteRemainDistDisplay() {
                return ((NaviInfo) this.instance).getRouteRemainDistDisplay();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public ByteString getRouteRemainDistDisplayBytes() {
                return ((NaviInfo) this.instance).getRouteRemainDistDisplayBytes();
            }

            public Builder setRouteRemainDistDisplay(String value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setRouteRemainDistDisplay(value);
                return this;
            }

            public Builder clearRouteRemainDistDisplay() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearRouteRemainDistDisplay();
                return this;
            }

            public Builder setRouteRemainDistDisplayBytes(ByteString value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setRouteRemainDistDisplayBytes(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public int getRouteRemainDistUnitDisplay() {
                return ((NaviInfo) this.instance).getRouteRemainDistUnitDisplay();
            }

            public Builder setRouteRemainDistUnitDisplay(int value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setRouteRemainDistUnitDisplay(value);
                return this;
            }

            public Builder clearRouteRemainDistUnitDisplay() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearRouteRemainDistUnitDisplay();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public double getRouteRemainTime() {
                return ((NaviInfo) this.instance).getRouteRemainTime();
            }

            public Builder setRouteRemainTime(double value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setRouteRemainTime(value);
                return this;
            }

            public Builder clearRouteRemainTime() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearRouteRemainTime();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public boolean getIsShowExitInfo() {
                return ((NaviInfo) this.instance).getIsShowExitInfo();
            }

            public Builder setIsShowExitInfo(boolean value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setIsShowExitInfo(value);
                return this;
            }

            public Builder clearIsShowExitInfo() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearIsShowExitInfo();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public String getExitInfo() {
                return ((NaviInfo) this.instance).getExitInfo();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public ByteString getExitInfoBytes() {
                return ((NaviInfo) this.instance).getExitInfoBytes();
            }

            public Builder setExitInfo(String value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setExitInfo(value);
                return this;
            }

            public Builder clearExitInfo() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearExitInfo();
                return this;
            }

            public Builder setExitInfoBytes(ByteString value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setExitInfoBytes(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public int getExitInfoType() {
                return ((NaviInfo) this.instance).getExitInfoType();
            }

            public Builder setExitInfoType(int value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setExitInfoType(value);
                return this;
            }

            public Builder clearExitInfoType() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearExitInfoType();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public String getNextRouteName() {
                return ((NaviInfo) this.instance).getNextRouteName();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public ByteString getNextRouteNameBytes() {
                return ((NaviInfo) this.instance).getNextRouteNameBytes();
            }

            public Builder setNextRouteName(String value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setNextRouteName(value);
                return this;
            }

            public Builder clearNextRouteName() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearNextRouteName();
                return this;
            }

            public Builder setNextRouteNameBytes(ByteString value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setNextRouteNameBytes(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public double getSegmentRemainDist() {
                return ((NaviInfo) this.instance).getSegmentRemainDist();
            }

            public Builder setSegmentRemainDist(double value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setSegmentRemainDist(value);
                return this;
            }

            public Builder clearSegmentRemainDist() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearSegmentRemainDist();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public String getSegmentRemainDistDisplay() {
                return ((NaviInfo) this.instance).getSegmentRemainDistDisplay();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public ByteString getSegmentRemainDistDisplayBytes() {
                return ((NaviInfo) this.instance).getSegmentRemainDistDisplayBytes();
            }

            public Builder setSegmentRemainDistDisplay(String value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setSegmentRemainDistDisplay(value);
                return this;
            }

            public Builder clearSegmentRemainDistDisplay() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearSegmentRemainDistDisplay();
                return this;
            }

            public Builder setSegmentRemainDistDisplayBytes(ByteString value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setSegmentRemainDistDisplayBytes(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public int getSegmentRemainDistUnitDisplay() {
                return ((NaviInfo) this.instance).getSegmentRemainDistUnitDisplay();
            }

            public Builder setSegmentRemainDistUnitDisplay(int value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setSegmentRemainDistUnitDisplay(value);
                return this;
            }

            public Builder clearSegmentRemainDistUnitDisplay() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearSegmentRemainDistUnitDisplay();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public double getSegmentRemainProgress() {
                return ((NaviInfo) this.instance).getSegmentRemainProgress();
            }

            public Builder setSegmentRemainProgress(double value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setSegmentRemainProgress(value);
                return this;
            }

            public Builder clearSegmentRemainProgress() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearSegmentRemainProgress();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public boolean getIsTightTurnShow() {
                return ((NaviInfo) this.instance).getIsTightTurnShow();
            }

            public Builder setIsTightTurnShow(boolean value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setIsTightTurnShow(value);
                return this;
            }

            public Builder clearIsTightTurnShow() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearIsTightTurnShow();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public double getNextManeuverID() {
                return ((NaviInfo) this.instance).getNextManeuverID();
            }

            public Builder setNextManeuverID(double value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setNextManeuverID(value);
                return this;
            }

            public Builder clearNextManeuverID() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearNextManeuverID();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public double getNextManeuverDist() {
                return ((NaviInfo) this.instance).getNextManeuverDist();
            }

            public Builder setNextManeuverDist(double value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setNextManeuverDist(value);
                return this;
            }

            public Builder clearNextManeuverDist() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearNextManeuverDist();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public String getNextManeuverDistDisplay() {
                return ((NaviInfo) this.instance).getNextManeuverDistDisplay();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public ByteString getNextManeuverDistDisplayBytes() {
                return ((NaviInfo) this.instance).getNextManeuverDistDisplayBytes();
            }

            public Builder setNextManeuverDistDisplay(String value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setNextManeuverDistDisplay(value);
                return this;
            }

            public Builder clearNextManeuverDistDisplay() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearNextManeuverDistDisplay();
                return this;
            }

            public Builder setNextManeuverDistDisplayBytes(ByteString value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setNextManeuverDistDisplayBytes(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.NaviInfoOrBuilder
            public int getNextManeuverDistUnitDisplay() {
                return ((NaviInfo) this.instance).getNextManeuverDistUnitDisplay();
            }

            public Builder setNextManeuverDistUnitDisplay(int value) {
                copyOnWrite();
                ((NaviInfo) this.instance).setNextManeuverDistUnitDisplay(value);
                return this;
            }

            public Builder clearNextManeuverDistUnitDisplay() {
                copyOnWrite();
                ((NaviInfo) this.instance).clearNextManeuverDistUnitDisplay();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new NaviInfo();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    NaviInfo other = (NaviInfo) arg1;
                    this.curRouteName_ = visitor.visitString(!this.curRouteName_.isEmpty(), this.curRouteName_, !other.curRouteName_.isEmpty(), other.curRouteName_);
                    this.routeRemainDist_ = visitor.visitDouble(this.routeRemainDist_ != 0.0d, this.routeRemainDist_, other.routeRemainDist_ != 0.0d, other.routeRemainDist_);
                    this.routeRemainDistDisplay_ = visitor.visitString(!this.routeRemainDistDisplay_.isEmpty(), this.routeRemainDistDisplay_, !other.routeRemainDistDisplay_.isEmpty(), other.routeRemainDistDisplay_);
                    this.routeRemainDistUnitDisplay_ = visitor.visitInt(this.routeRemainDistUnitDisplay_ != 0, this.routeRemainDistUnitDisplay_, other.routeRemainDistUnitDisplay_ != 0, other.routeRemainDistUnitDisplay_);
                    this.routeRemainTime_ = visitor.visitDouble(this.routeRemainTime_ != 0.0d, this.routeRemainTime_, other.routeRemainTime_ != 0.0d, other.routeRemainTime_);
                    this.isShowExitInfo_ = visitor.visitBoolean(this.isShowExitInfo_, this.isShowExitInfo_, other.isShowExitInfo_, other.isShowExitInfo_);
                    this.exitInfo_ = visitor.visitString(!this.exitInfo_.isEmpty(), this.exitInfo_, !other.exitInfo_.isEmpty(), other.exitInfo_);
                    this.exitInfoType_ = visitor.visitInt(this.exitInfoType_ != 0, this.exitInfoType_, other.exitInfoType_ != 0, other.exitInfoType_);
                    this.nextRouteName_ = visitor.visitString(!this.nextRouteName_.isEmpty(), this.nextRouteName_, !other.nextRouteName_.isEmpty(), other.nextRouteName_);
                    this.segmentRemainDist_ = visitor.visitDouble(this.segmentRemainDist_ != 0.0d, this.segmentRemainDist_, other.segmentRemainDist_ != 0.0d, other.segmentRemainDist_);
                    this.segmentRemainDistDisplay_ = visitor.visitString(!this.segmentRemainDistDisplay_.isEmpty(), this.segmentRemainDistDisplay_, !other.segmentRemainDistDisplay_.isEmpty(), other.segmentRemainDistDisplay_);
                    this.segmentRemainDistUnitDisplay_ = visitor.visitInt(this.segmentRemainDistUnitDisplay_ != 0, this.segmentRemainDistUnitDisplay_, other.segmentRemainDistUnitDisplay_ != 0, other.segmentRemainDistUnitDisplay_);
                    this.segmentRemainProgress_ = visitor.visitDouble(this.segmentRemainProgress_ != 0.0d, this.segmentRemainProgress_, other.segmentRemainProgress_ != 0.0d, other.segmentRemainProgress_);
                    this.isTightTurnShow_ = visitor.visitBoolean(this.isTightTurnShow_, this.isTightTurnShow_, other.isTightTurnShow_, other.isTightTurnShow_);
                    this.nextManeuverID_ = visitor.visitDouble(this.nextManeuverID_ != 0.0d, this.nextManeuverID_, other.nextManeuverID_ != 0.0d, other.nextManeuverID_);
                    this.nextManeuverDist_ = visitor.visitDouble(this.nextManeuverDist_ != 0.0d, this.nextManeuverDist_, other.nextManeuverDist_ != 0.0d, other.nextManeuverDist_);
                    this.nextManeuverDistDisplay_ = visitor.visitString(!this.nextManeuverDistDisplay_.isEmpty(), this.nextManeuverDistDisplay_, !other.nextManeuverDistDisplay_.isEmpty(), other.nextManeuverDistDisplay_);
                    this.nextManeuverDistUnitDisplay_ = visitor.visitInt(this.nextManeuverDistUnitDisplay_ != 0, this.nextManeuverDistUnitDisplay_, other.nextManeuverDistUnitDisplay_ != 0, other.nextManeuverDistUnitDisplay_);
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
                                switch (tag) {
                                    case 0:
                                        done2 = true;
                                        break;
                                    case 10:
                                        String s = input.readStringRequireUtf8();
                                        this.curRouteName_ = s;
                                        break;
                                    case 17:
                                        this.routeRemainDist_ = input.readDouble();
                                        break;
                                    case 26:
                                        String s2 = input.readStringRequireUtf8();
                                        this.routeRemainDistDisplay_ = s2;
                                        break;
                                    case 32:
                                        this.routeRemainDistUnitDisplay_ = input.readInt32();
                                        break;
                                    case 41:
                                        this.routeRemainTime_ = input.readDouble();
                                        break;
                                    case 48:
                                        this.isShowExitInfo_ = input.readBool();
                                        break;
                                    case 58:
                                        String s3 = input.readStringRequireUtf8();
                                        this.exitInfo_ = s3;
                                        break;
                                    case 64:
                                        this.exitInfoType_ = input.readInt32();
                                        break;
                                    case 74:
                                        String s4 = input.readStringRequireUtf8();
                                        this.nextRouteName_ = s4;
                                        break;
                                    case 81:
                                        this.segmentRemainDist_ = input.readDouble();
                                        break;
                                    case 90:
                                        String s5 = input.readStringRequireUtf8();
                                        this.segmentRemainDistDisplay_ = s5;
                                        break;
                                    case 96:
                                        this.segmentRemainDistUnitDisplay_ = input.readInt32();
                                        break;
                                    case 105:
                                        this.segmentRemainProgress_ = input.readDouble();
                                        break;
                                    case 112:
                                        this.isTightTurnShow_ = input.readBool();
                                        break;
                                    case 121:
                                        this.nextManeuverID_ = input.readDouble();
                                        break;
                                    case 129:
                                        this.nextManeuverDist_ = input.readDouble();
                                        break;
                                    case 138:
                                        String s6 = input.readStringRequireUtf8();
                                        this.nextManeuverDistDisplay_ = s6;
                                        break;
                                    case 144:
                                        this.nextManeuverDistUnitDisplay_ = input.readInt32();
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
                        synchronized (NaviInfo.class) {
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

        public static NaviInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NaviInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class ManeuverInfo extends GeneratedMessageLite<ManeuverInfo, Builder> implements ManeuverInfoOrBuilder {
        private static final ManeuverInfo DEFAULT_INSTANCE = new ManeuverInfo();
        public static final int MANEUVERDATA_FIELD_NUMBER = 2;
        public static final int MANEUVERID_FIELD_NUMBER = 1;
        public static final int MANEUVER_TYPE_FIELD_NUMBER = 3;
        private static volatile Parser<ManeuverInfo> PARSER;
        private long maneuverID_ = 0;
        private String maneuverData_ = CarBluetoothManager.BLUETOOTH_NO_PRIORITY_DEVICE;
        private int maneuverType_ = 0;

        private ManeuverInfo() {
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.ManeuverInfoOrBuilder
        public long getManeuverID() {
            return this.maneuverID_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManeuverID(long value) {
            this.maneuverID_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearManeuverID() {
            this.maneuverID_ = 0L;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.ManeuverInfoOrBuilder
        public String getManeuverData() {
            return this.maneuverData_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.ManeuverInfoOrBuilder
        public ByteString getManeuverDataBytes() {
            return ByteString.copyFromUtf8(this.maneuverData_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManeuverData(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.maneuverData_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearManeuverData() {
            this.maneuverData_ = getDefaultInstance().getManeuverData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManeuverDataBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            this.maneuverData_ = value.toStringUtf8();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.ManeuverInfoOrBuilder
        public int getManeuverType() {
            return this.maneuverType_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManeuverType(int value) {
            this.maneuverType_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearManeuverType() {
            this.maneuverType_ = 0;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.maneuverID_ != 0) {
                output.writeInt64(1, this.maneuverID_);
            }
            if (!this.maneuverData_.isEmpty()) {
                output.writeString(2, getManeuverData());
            }
            if (this.maneuverType_ != 0) {
                output.writeInt32(3, this.maneuverType_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.maneuverID_ != 0 ? 0 + CodedOutputStream.computeInt64Size(1, this.maneuverID_) : 0;
            if (!this.maneuverData_.isEmpty()) {
                size2 += CodedOutputStream.computeStringSize(2, getManeuverData());
            }
            if (this.maneuverType_ != 0) {
                size2 += CodedOutputStream.computeInt32Size(3, this.maneuverType_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static ManeuverInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ManeuverInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ManeuverInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ManeuverInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ManeuverInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ManeuverInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ManeuverInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ManeuverInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ManeuverInfo parseFrom(InputStream input) throws IOException {
            return (ManeuverInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ManeuverInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ManeuverInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ManeuverInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (ManeuverInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ManeuverInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ManeuverInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ManeuverInfo parseFrom(CodedInputStream input) throws IOException {
            return (ManeuverInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ManeuverInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ManeuverInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ManeuverInfo prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ManeuverInfo, Builder> implements ManeuverInfoOrBuilder {
            private Builder() {
                super(ManeuverInfo.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.ManeuverInfoOrBuilder
            public long getManeuverID() {
                return ((ManeuverInfo) this.instance).getManeuverID();
            }

            public Builder setManeuverID(long value) {
                copyOnWrite();
                ((ManeuverInfo) this.instance).setManeuverID(value);
                return this;
            }

            public Builder clearManeuverID() {
                copyOnWrite();
                ((ManeuverInfo) this.instance).clearManeuverID();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.ManeuverInfoOrBuilder
            public String getManeuverData() {
                return ((ManeuverInfo) this.instance).getManeuverData();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.ManeuverInfoOrBuilder
            public ByteString getManeuverDataBytes() {
                return ((ManeuverInfo) this.instance).getManeuverDataBytes();
            }

            public Builder setManeuverData(String value) {
                copyOnWrite();
                ((ManeuverInfo) this.instance).setManeuverData(value);
                return this;
            }

            public Builder clearManeuverData() {
                copyOnWrite();
                ((ManeuverInfo) this.instance).clearManeuverData();
                return this;
            }

            public Builder setManeuverDataBytes(ByteString value) {
                copyOnWrite();
                ((ManeuverInfo) this.instance).setManeuverDataBytes(value);
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.ManeuverInfoOrBuilder
            public int getManeuverType() {
                return ((ManeuverInfo) this.instance).getManeuverType();
            }

            public Builder setManeuverType(int value) {
                copyOnWrite();
                ((ManeuverInfo) this.instance).setManeuverType(value);
                return this;
            }

            public Builder clearManeuverType() {
                copyOnWrite();
                ((ManeuverInfo) this.instance).clearManeuverType();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ManeuverInfo();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ManeuverInfo other = (ManeuverInfo) arg1;
                    this.maneuverID_ = visitor.visitLong(this.maneuverID_ != 0, this.maneuverID_, other.maneuverID_ != 0, other.maneuverID_);
                    this.maneuverData_ = visitor.visitString(!this.maneuverData_.isEmpty(), this.maneuverData_, !other.maneuverData_.isEmpty(), other.maneuverData_);
                    this.maneuverType_ = visitor.visitInt(this.maneuverType_ != 0, this.maneuverType_, other.maneuverType_ != 0, other.maneuverType_);
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
                                        this.maneuverID_ = input.readInt64();
                                    } else if (tag == 18) {
                                        String s = input.readStringRequireUtf8();
                                        this.maneuverData_ = s;
                                    } else if (tag != 24) {
                                        if (!input.skipField(tag)) {
                                            done2 = true;
                                        }
                                    } else {
                                        this.maneuverType_ = input.readInt32();
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
                        synchronized (ManeuverInfo.class) {
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

        public static ManeuverInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ManeuverInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class Coord2DDouble extends GeneratedMessageLite<Coord2DDouble, Builder> implements Coord2DDoubleOrBuilder {
        private static final Coord2DDouble DEFAULT_INSTANCE = new Coord2DDouble();
        private static volatile Parser<Coord2DDouble> PARSER = null;
        public static final int X_FIELD_NUMBER = 1;
        public static final int Y_FIELD_NUMBER = 2;
        private double x_ = 0.0d;
        private double y_ = 0.0d;

        private Coord2DDouble() {
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.Coord2DDoubleOrBuilder
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

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.Coord2DDoubleOrBuilder
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

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.x_ != 0.0d) {
                output.writeDouble(1, this.x_);
            }
            if (this.y_ != 0.0d) {
                output.writeDouble(2, this.y_);
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
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static Coord2DDouble parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Coord2DDouble) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Coord2DDouble parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Coord2DDouble) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Coord2DDouble parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Coord2DDouble) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Coord2DDouble parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Coord2DDouble) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Coord2DDouble parseFrom(InputStream input) throws IOException {
            return (Coord2DDouble) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Coord2DDouble parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Coord2DDouble) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Coord2DDouble parseDelimitedFrom(InputStream input) throws IOException {
            return (Coord2DDouble) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Coord2DDouble parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Coord2DDouble) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Coord2DDouble parseFrom(CodedInputStream input) throws IOException {
            return (Coord2DDouble) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Coord2DDouble parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Coord2DDouble) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Coord2DDouble prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Coord2DDouble, Builder> implements Coord2DDoubleOrBuilder {
            private Builder() {
                super(Coord2DDouble.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.Coord2DDoubleOrBuilder
            public double getX() {
                return ((Coord2DDouble) this.instance).getX();
            }

            public Builder setX(double value) {
                copyOnWrite();
                ((Coord2DDouble) this.instance).setX(value);
                return this;
            }

            public Builder clearX() {
                copyOnWrite();
                ((Coord2DDouble) this.instance).clearX();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.Coord2DDoubleOrBuilder
            public double getY() {
                return ((Coord2DDouble) this.instance).getY();
            }

            public Builder setY(double value) {
                copyOnWrite();
                ((Coord2DDouble) this.instance).setY(value);
                return this;
            }

            public Builder clearY() {
                copyOnWrite();
                ((Coord2DDouble) this.instance).clearY();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean done = false;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Coord2DDouble();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Coord2DDouble other = (Coord2DDouble) arg1;
                    this.x_ = visitor.visitDouble(this.x_ != 0.0d, this.x_, other.x_ != 0.0d, other.x_);
                    this.y_ = visitor.visitDouble(this.y_ != 0.0d, this.y_, other.y_ != 0.0d, other.y_);
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
                                } else if (tag == 9) {
                                    this.x_ = input.readDouble();
                                } else if (tag != 17) {
                                    if (!input.skipField(tag)) {
                                        done2 = true;
                                    }
                                } else {
                                    this.y_ = input.readDouble();
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
                        synchronized (Coord2DDouble.class) {
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

        public static Coord2DDouble getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Coord2DDouble> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class LaneInfo extends GeneratedMessageLite<LaneInfo, Builder> implements LaneInfoOrBuilder {
        public static final int BACKEXTENLANE_FIELD_NUMBER = 4;
        public static final int BACKLANE_FIELD_NUMBER = 2;
        private static final LaneInfo DEFAULT_INSTANCE = new LaneInfo();
        public static final int EXTENSIONLANE_FIELD_NUMBER = 6;
        public static final int FRONTEXTENLANE_FIELD_NUMBER = 5;
        public static final int FRONTLANE_FIELD_NUMBER = 3;
        public static final int LANETYPE_FIELD_NUMBER = 1;
        public static final int LINKIDX_FIELD_NUMBER = 9;
        private static volatile Parser<LaneInfo> PARSER = null;
        public static final int POINT_FIELD_NUMBER = 7;
        public static final int SEGMENTIDX_FIELD_NUMBER = 8;
        private int bitField0_;
        private Coord2DDouble point_;
        private int laneType_ = 0;
        private Internal.IntList backLane_ = emptyIntList();
        private Internal.IntList frontLane_ = emptyIntList();
        private Internal.IntList backExtenLane_ = emptyIntList();
        private Internal.IntList frontExtenLane_ = emptyIntList();
        private Internal.IntList extensionLane_ = emptyIntList();
        private int segmentIdx_ = 0;
        private int linkIdx_ = 0;

        private LaneInfo() {
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getLaneType() {
            return this.laneType_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLaneType(int value) {
            this.laneType_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLaneType() {
            this.laneType_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public List<Integer> getBackLaneList() {
            return this.backLane_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getBackLaneCount() {
            return this.backLane_.size();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getBackLane(int index) {
            return this.backLane_.getInt(index);
        }

        private void ensureBackLaneIsMutable() {
            if (!this.backLane_.isModifiable()) {
                this.backLane_ = GeneratedMessageLite.mutableCopy(this.backLane_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBackLane(int index, int value) {
            ensureBackLaneIsMutable();
            this.backLane_.setInt(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBackLane(int value) {
            ensureBackLaneIsMutable();
            this.backLane_.addInt(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllBackLane(Iterable<? extends Integer> values) {
            ensureBackLaneIsMutable();
            AbstractMessageLite.addAll(values, this.backLane_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBackLane() {
            this.backLane_ = emptyIntList();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public List<Integer> getFrontLaneList() {
            return this.frontLane_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getFrontLaneCount() {
            return this.frontLane_.size();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getFrontLane(int index) {
            return this.frontLane_.getInt(index);
        }

        private void ensureFrontLaneIsMutable() {
            if (!this.frontLane_.isModifiable()) {
                this.frontLane_ = GeneratedMessageLite.mutableCopy(this.frontLane_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFrontLane(int index, int value) {
            ensureFrontLaneIsMutable();
            this.frontLane_.setInt(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFrontLane(int value) {
            ensureFrontLaneIsMutable();
            this.frontLane_.addInt(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFrontLane(Iterable<? extends Integer> values) {
            ensureFrontLaneIsMutable();
            AbstractMessageLite.addAll(values, this.frontLane_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFrontLane() {
            this.frontLane_ = emptyIntList();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public List<Integer> getBackExtenLaneList() {
            return this.backExtenLane_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getBackExtenLaneCount() {
            return this.backExtenLane_.size();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getBackExtenLane(int index) {
            return this.backExtenLane_.getInt(index);
        }

        private void ensureBackExtenLaneIsMutable() {
            if (!this.backExtenLane_.isModifiable()) {
                this.backExtenLane_ = GeneratedMessageLite.mutableCopy(this.backExtenLane_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBackExtenLane(int index, int value) {
            ensureBackExtenLaneIsMutable();
            this.backExtenLane_.setInt(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBackExtenLane(int value) {
            ensureBackExtenLaneIsMutable();
            this.backExtenLane_.addInt(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllBackExtenLane(Iterable<? extends Integer> values) {
            ensureBackExtenLaneIsMutable();
            AbstractMessageLite.addAll(values, this.backExtenLane_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBackExtenLane() {
            this.backExtenLane_ = emptyIntList();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public List<Integer> getFrontExtenLaneList() {
            return this.frontExtenLane_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getFrontExtenLaneCount() {
            return this.frontExtenLane_.size();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getFrontExtenLane(int index) {
            return this.frontExtenLane_.getInt(index);
        }

        private void ensureFrontExtenLaneIsMutable() {
            if (!this.frontExtenLane_.isModifiable()) {
                this.frontExtenLane_ = GeneratedMessageLite.mutableCopy(this.frontExtenLane_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFrontExtenLane(int index, int value) {
            ensureFrontExtenLaneIsMutable();
            this.frontExtenLane_.setInt(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFrontExtenLane(int value) {
            ensureFrontExtenLaneIsMutable();
            this.frontExtenLane_.addInt(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFrontExtenLane(Iterable<? extends Integer> values) {
            ensureFrontExtenLaneIsMutable();
            AbstractMessageLite.addAll(values, this.frontExtenLane_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFrontExtenLane() {
            this.frontExtenLane_ = emptyIntList();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public List<Integer> getExtensionLaneList() {
            return this.extensionLane_;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getExtensionLaneCount() {
            return this.extensionLane_.size();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getExtensionLane(int index) {
            return this.extensionLane_.getInt(index);
        }

        private void ensureExtensionLaneIsMutable() {
            if (!this.extensionLane_.isModifiable()) {
                this.extensionLane_ = GeneratedMessageLite.mutableCopy(this.extensionLane_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExtensionLane(int index, int value) {
            ensureExtensionLaneIsMutable();
            this.extensionLane_.setInt(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addExtensionLane(int value) {
            ensureExtensionLaneIsMutable();
            this.extensionLane_.addInt(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllExtensionLane(Iterable<? extends Integer> values) {
            ensureExtensionLaneIsMutable();
            AbstractMessageLite.addAll(values, this.extensionLane_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExtensionLane() {
            this.extensionLane_ = emptyIntList();
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public boolean hasPoint() {
            return this.point_ != null;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public Coord2DDouble getPoint() {
            return this.point_ == null ? Coord2DDouble.getDefaultInstance() : this.point_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPoint(Coord2DDouble value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.point_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPoint(Coord2DDouble.Builder builderForValue) {
            this.point_ = builderForValue.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePoint(Coord2DDouble value) {
            if (this.point_ != null && this.point_ != Coord2DDouble.getDefaultInstance()) {
                this.point_ = Coord2DDouble.newBuilder(this.point_).mergeFrom((Coord2DDouble.Builder) value).buildPartial();
            } else {
                this.point_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPoint() {
            this.point_ = null;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getSegmentIdx() {
            return this.segmentIdx_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSegmentIdx(int value) {
            this.segmentIdx_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSegmentIdx() {
            this.segmentIdx_ = 0;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
        public int getLinkIdx() {
            return this.linkIdx_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLinkIdx(int value) {
            this.linkIdx_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLinkIdx() {
            this.linkIdx_ = 0;
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if (this.laneType_ != 0) {
                output.writeInt32(1, this.laneType_);
            }
            for (int i = 0; i < this.backLane_.size(); i++) {
                output.writeInt32(2, this.backLane_.getInt(i));
            }
            for (int i2 = 0; i2 < this.frontLane_.size(); i2++) {
                output.writeInt32(3, this.frontLane_.getInt(i2));
            }
            for (int i3 = 0; i3 < this.backExtenLane_.size(); i3++) {
                output.writeInt32(4, this.backExtenLane_.getInt(i3));
            }
            for (int i4 = 0; i4 < this.frontExtenLane_.size(); i4++) {
                output.writeInt32(5, this.frontExtenLane_.getInt(i4));
            }
            for (int i5 = 0; i5 < this.extensionLane_.size(); i5++) {
                output.writeInt32(6, this.extensionLane_.getInt(i5));
            }
            if (this.point_ != null) {
                output.writeMessage(7, getPoint());
            }
            if (this.segmentIdx_ != 0) {
                output.writeInt32(8, this.segmentIdx_);
            }
            if (this.linkIdx_ != 0) {
                output.writeInt32(9, this.linkIdx_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.laneType_ != 0 ? 0 + CodedOutputStream.computeInt32Size(1, this.laneType_) : 0;
            int dataSize = 0;
            for (int dataSize2 = 0; dataSize2 < this.backLane_.size(); dataSize2++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.backLane_.getInt(dataSize2));
            }
            int size3 = size2 + dataSize + (getBackLaneList().size() * 1);
            int dataSize3 = 0;
            for (int dataSize4 = 0; dataSize4 < this.frontLane_.size(); dataSize4++) {
                dataSize3 += CodedOutputStream.computeInt32SizeNoTag(this.frontLane_.getInt(dataSize4));
            }
            int size4 = size3 + dataSize3 + (getFrontLaneList().size() * 1);
            int dataSize5 = 0;
            for (int dataSize6 = 0; dataSize6 < this.backExtenLane_.size(); dataSize6++) {
                dataSize5 += CodedOutputStream.computeInt32SizeNoTag(this.backExtenLane_.getInt(dataSize6));
            }
            int size5 = size4 + dataSize5 + (getBackExtenLaneList().size() * 1);
            int dataSize7 = 0;
            for (int dataSize8 = 0; dataSize8 < this.frontExtenLane_.size(); dataSize8++) {
                dataSize7 += CodedOutputStream.computeInt32SizeNoTag(this.frontExtenLane_.getInt(dataSize8));
            }
            int size6 = size5 + dataSize7 + (getFrontExtenLaneList().size() * 1);
            int dataSize9 = 0;
            for (int i = 0; i < this.extensionLane_.size(); i++) {
                dataSize9 += CodedOutputStream.computeInt32SizeNoTag(this.extensionLane_.getInt(i));
            }
            int size7 = size6 + dataSize9 + (1 * getExtensionLaneList().size());
            if (this.point_ != null) {
                size7 += CodedOutputStream.computeMessageSize(7, getPoint());
            }
            if (this.segmentIdx_ != 0) {
                size7 += CodedOutputStream.computeInt32Size(8, this.segmentIdx_);
            }
            if (this.linkIdx_ != 0) {
                size7 += CodedOutputStream.computeInt32Size(9, this.linkIdx_);
            }
            this.memoizedSerializedSize = size7;
            return size7;
        }

        public static LaneInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (LaneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LaneInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LaneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LaneInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (LaneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LaneInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LaneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LaneInfo parseFrom(InputStream input) throws IOException {
            return (LaneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LaneInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LaneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LaneInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (LaneInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static LaneInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LaneInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LaneInfo parseFrom(CodedInputStream input) throws IOException {
            return (LaneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LaneInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LaneInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LaneInfo prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<LaneInfo, Builder> implements LaneInfoOrBuilder {
            private Builder() {
                super(LaneInfo.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getLaneType() {
                return ((LaneInfo) this.instance).getLaneType();
            }

            public Builder setLaneType(int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).setLaneType(value);
                return this;
            }

            public Builder clearLaneType() {
                copyOnWrite();
                ((LaneInfo) this.instance).clearLaneType();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public List<Integer> getBackLaneList() {
                return Collections.unmodifiableList(((LaneInfo) this.instance).getBackLaneList());
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getBackLaneCount() {
                return ((LaneInfo) this.instance).getBackLaneCount();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getBackLane(int index) {
                return ((LaneInfo) this.instance).getBackLane(index);
            }

            public Builder setBackLane(int index, int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).setBackLane(index, value);
                return this;
            }

            public Builder addBackLane(int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).addBackLane(value);
                return this;
            }

            public Builder addAllBackLane(Iterable<? extends Integer> values) {
                copyOnWrite();
                ((LaneInfo) this.instance).addAllBackLane(values);
                return this;
            }

            public Builder clearBackLane() {
                copyOnWrite();
                ((LaneInfo) this.instance).clearBackLane();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public List<Integer> getFrontLaneList() {
                return Collections.unmodifiableList(((LaneInfo) this.instance).getFrontLaneList());
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getFrontLaneCount() {
                return ((LaneInfo) this.instance).getFrontLaneCount();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getFrontLane(int index) {
                return ((LaneInfo) this.instance).getFrontLane(index);
            }

            public Builder setFrontLane(int index, int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).setFrontLane(index, value);
                return this;
            }

            public Builder addFrontLane(int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).addFrontLane(value);
                return this;
            }

            public Builder addAllFrontLane(Iterable<? extends Integer> values) {
                copyOnWrite();
                ((LaneInfo) this.instance).addAllFrontLane(values);
                return this;
            }

            public Builder clearFrontLane() {
                copyOnWrite();
                ((LaneInfo) this.instance).clearFrontLane();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public List<Integer> getBackExtenLaneList() {
                return Collections.unmodifiableList(((LaneInfo) this.instance).getBackExtenLaneList());
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getBackExtenLaneCount() {
                return ((LaneInfo) this.instance).getBackExtenLaneCount();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getBackExtenLane(int index) {
                return ((LaneInfo) this.instance).getBackExtenLane(index);
            }

            public Builder setBackExtenLane(int index, int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).setBackExtenLane(index, value);
                return this;
            }

            public Builder addBackExtenLane(int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).addBackExtenLane(value);
                return this;
            }

            public Builder addAllBackExtenLane(Iterable<? extends Integer> values) {
                copyOnWrite();
                ((LaneInfo) this.instance).addAllBackExtenLane(values);
                return this;
            }

            public Builder clearBackExtenLane() {
                copyOnWrite();
                ((LaneInfo) this.instance).clearBackExtenLane();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public List<Integer> getFrontExtenLaneList() {
                return Collections.unmodifiableList(((LaneInfo) this.instance).getFrontExtenLaneList());
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getFrontExtenLaneCount() {
                return ((LaneInfo) this.instance).getFrontExtenLaneCount();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getFrontExtenLane(int index) {
                return ((LaneInfo) this.instance).getFrontExtenLane(index);
            }

            public Builder setFrontExtenLane(int index, int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).setFrontExtenLane(index, value);
                return this;
            }

            public Builder addFrontExtenLane(int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).addFrontExtenLane(value);
                return this;
            }

            public Builder addAllFrontExtenLane(Iterable<? extends Integer> values) {
                copyOnWrite();
                ((LaneInfo) this.instance).addAllFrontExtenLane(values);
                return this;
            }

            public Builder clearFrontExtenLane() {
                copyOnWrite();
                ((LaneInfo) this.instance).clearFrontExtenLane();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public List<Integer> getExtensionLaneList() {
                return Collections.unmodifiableList(((LaneInfo) this.instance).getExtensionLaneList());
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getExtensionLaneCount() {
                return ((LaneInfo) this.instance).getExtensionLaneCount();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getExtensionLane(int index) {
                return ((LaneInfo) this.instance).getExtensionLane(index);
            }

            public Builder setExtensionLane(int index, int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).setExtensionLane(index, value);
                return this;
            }

            public Builder addExtensionLane(int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).addExtensionLane(value);
                return this;
            }

            public Builder addAllExtensionLane(Iterable<? extends Integer> values) {
                copyOnWrite();
                ((LaneInfo) this.instance).addAllExtensionLane(values);
                return this;
            }

            public Builder clearExtensionLane() {
                copyOnWrite();
                ((LaneInfo) this.instance).clearExtensionLane();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public boolean hasPoint() {
                return ((LaneInfo) this.instance).hasPoint();
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public Coord2DDouble getPoint() {
                return ((LaneInfo) this.instance).getPoint();
            }

            public Builder setPoint(Coord2DDouble value) {
                copyOnWrite();
                ((LaneInfo) this.instance).setPoint(value);
                return this;
            }

            public Builder setPoint(Coord2DDouble.Builder builderForValue) {
                copyOnWrite();
                ((LaneInfo) this.instance).setPoint(builderForValue);
                return this;
            }

            public Builder mergePoint(Coord2DDouble value) {
                copyOnWrite();
                ((LaneInfo) this.instance).mergePoint(value);
                return this;
            }

            public Builder clearPoint() {
                copyOnWrite();
                ((LaneInfo) this.instance).clearPoint();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getSegmentIdx() {
                return ((LaneInfo) this.instance).getSegmentIdx();
            }

            public Builder setSegmentIdx(int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).setSegmentIdx(value);
                return this;
            }

            public Builder clearSegmentIdx() {
                copyOnWrite();
                ((LaneInfo) this.instance).clearSegmentIdx();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.LaneInfoOrBuilder
            public int getLinkIdx() {
                return ((LaneInfo) this.instance).getLinkIdx();
            }

            public Builder setLinkIdx(int value) {
                copyOnWrite();
                ((LaneInfo) this.instance).setLinkIdx(value);
                return this;
            }

            public Builder clearLinkIdx() {
                copyOnWrite();
                ((LaneInfo) this.instance).clearLinkIdx();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new LaneInfo();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.backLane_.makeImmutable();
                    this.frontLane_.makeImmutable();
                    this.backExtenLane_.makeImmutable();
                    this.frontExtenLane_.makeImmutable();
                    this.extensionLane_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    LaneInfo other = (LaneInfo) arg1;
                    this.laneType_ = visitor.visitInt(this.laneType_ != 0, this.laneType_, other.laneType_ != 0, other.laneType_);
                    this.backLane_ = visitor.visitIntList(this.backLane_, other.backLane_);
                    this.frontLane_ = visitor.visitIntList(this.frontLane_, other.frontLane_);
                    this.backExtenLane_ = visitor.visitIntList(this.backExtenLane_, other.backExtenLane_);
                    this.frontExtenLane_ = visitor.visitIntList(this.frontExtenLane_, other.frontExtenLane_);
                    this.extensionLane_ = visitor.visitIntList(this.extensionLane_, other.extensionLane_);
                    this.point_ = (Coord2DDouble) visitor.visitMessage(this.point_, other.point_);
                    this.segmentIdx_ = visitor.visitInt(this.segmentIdx_ != 0, this.segmentIdx_, other.segmentIdx_ != 0, other.segmentIdx_);
                    boolean z = this.linkIdx_ != 0;
                    int i = this.linkIdx_;
                    done = other.linkIdx_ != 0;
                    this.linkIdx_ = visitor.visitInt(z, i, done, other.linkIdx_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    while (!done) {
                        try {
                            try {
                                int tag = input.readTag();
                                switch (tag) {
                                    case 0:
                                        done = true;
                                        break;
                                    case 8:
                                        this.laneType_ = input.readInt32();
                                        break;
                                    case 16:
                                        if (!this.backLane_.isModifiable()) {
                                            this.backLane_ = GeneratedMessageLite.mutableCopy(this.backLane_);
                                        }
                                        this.backLane_.addInt(input.readInt32());
                                        break;
                                    case 18:
                                        int length = input.readRawVarint32();
                                        int limit = input.pushLimit(length);
                                        if (!this.backLane_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                            this.backLane_ = GeneratedMessageLite.mutableCopy(this.backLane_);
                                        }
                                        while (input.getBytesUntilLimit() > 0) {
                                            this.backLane_.addInt(input.readInt32());
                                        }
                                        input.popLimit(limit);
                                        break;
                                    case 24:
                                        if (!this.frontLane_.isModifiable()) {
                                            this.frontLane_ = GeneratedMessageLite.mutableCopy(this.frontLane_);
                                        }
                                        this.frontLane_.addInt(input.readInt32());
                                        break;
                                    case 26:
                                        int length2 = input.readRawVarint32();
                                        int limit2 = input.pushLimit(length2);
                                        if (!this.frontLane_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                            this.frontLane_ = GeneratedMessageLite.mutableCopy(this.frontLane_);
                                        }
                                        while (input.getBytesUntilLimit() > 0) {
                                            this.frontLane_.addInt(input.readInt32());
                                        }
                                        input.popLimit(limit2);
                                        break;
                                    case 32:
                                        if (!this.backExtenLane_.isModifiable()) {
                                            this.backExtenLane_ = GeneratedMessageLite.mutableCopy(this.backExtenLane_);
                                        }
                                        this.backExtenLane_.addInt(input.readInt32());
                                        break;
                                    case 34:
                                        int length3 = input.readRawVarint32();
                                        int limit3 = input.pushLimit(length3);
                                        if (!this.backExtenLane_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                            this.backExtenLane_ = GeneratedMessageLite.mutableCopy(this.backExtenLane_);
                                        }
                                        while (input.getBytesUntilLimit() > 0) {
                                            this.backExtenLane_.addInt(input.readInt32());
                                        }
                                        input.popLimit(limit3);
                                        break;
                                    case 40:
                                        if (!this.frontExtenLane_.isModifiable()) {
                                            this.frontExtenLane_ = GeneratedMessageLite.mutableCopy(this.frontExtenLane_);
                                        }
                                        this.frontExtenLane_.addInt(input.readInt32());
                                        break;
                                    case 42:
                                        int length4 = input.readRawVarint32();
                                        int limit4 = input.pushLimit(length4);
                                        if (!this.frontExtenLane_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                            this.frontExtenLane_ = GeneratedMessageLite.mutableCopy(this.frontExtenLane_);
                                        }
                                        while (input.getBytesUntilLimit() > 0) {
                                            this.frontExtenLane_.addInt(input.readInt32());
                                        }
                                        input.popLimit(limit4);
                                        break;
                                    case 48:
                                        if (!this.extensionLane_.isModifiable()) {
                                            this.extensionLane_ = GeneratedMessageLite.mutableCopy(this.extensionLane_);
                                        }
                                        this.extensionLane_.addInt(input.readInt32());
                                        break;
                                    case 50:
                                        int length5 = input.readRawVarint32();
                                        int limit5 = input.pushLimit(length5);
                                        if (!this.extensionLane_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                            this.extensionLane_ = GeneratedMessageLite.mutableCopy(this.extensionLane_);
                                        }
                                        while (input.getBytesUntilLimit() > 0) {
                                            this.extensionLane_.addInt(input.readInt32());
                                        }
                                        input.popLimit(limit5);
                                        break;
                                    case 58:
                                        Coord2DDouble.Builder subBuilder = null;
                                        if (this.point_ != null) {
                                            subBuilder = this.point_.toBuilder();
                                        }
                                        this.point_ = (Coord2DDouble) input.readMessage(Coord2DDouble.parser(), extensionRegistry);
                                        if (subBuilder == null) {
                                            break;
                                        } else {
                                            subBuilder.mergeFrom((Coord2DDouble.Builder) this.point_);
                                            this.point_ = (Coord2DDouble) subBuilder.buildPartial();
                                            break;
                                        }
                                    case 64:
                                        this.segmentIdx_ = input.readInt32();
                                        break;
                                    case 72:
                                        this.linkIdx_ = input.readInt32();
                                        break;
                                    default:
                                        if (!input.skipField(tag)) {
                                            done = true;
                                            break;
                                        } else {
                                            break;
                                        }
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this));
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (LaneInfo.class) {
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

        public static LaneInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LaneInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes.dex */
    public static final class SDNavigation extends GeneratedMessageLite<SDNavigation, Builder> implements SDNavigationOrBuilder {
        private static final SDNavigation DEFAULT_INSTANCE = new SDNavigation();
        public static final int LANE_INFO_FIELD_NUMBER = 1;
        public static final int MANEUVER_INFO_FIELD_NUMBER = 3;
        public static final int NAVI_INFO_FIELD_NUMBER = 2;
        private static volatile Parser<SDNavigation> PARSER;
        private int sdInfoCase_ = 0;
        private Object sdInfo_;

        private SDNavigation() {
        }

        /* loaded from: classes.dex */
        public enum SdInfoCase implements Internal.EnumLite {
            LANE_INFO(1),
            NAVI_INFO(2),
            MANEUVER_INFO(3),
            SDINFO_NOT_SET(0);
            
            private final int value;

            SdInfoCase(int value) {
                this.value = value;
            }

            @Deprecated
            public static SdInfoCase valueOf(int value) {
                return forNumber(value);
            }

            public static SdInfoCase forNumber(int value) {
                switch (value) {
                    case 0:
                        return SDINFO_NOT_SET;
                    case 1:
                        return LANE_INFO;
                    case 2:
                        return NAVI_INFO;
                    case 3:
                        return MANEUVER_INFO;
                    default:
                        return null;
                }
            }

            @Override // com.android.carsdk.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.SDNavigationOrBuilder
        public SdInfoCase getSdInfoCase() {
            return SdInfoCase.forNumber(this.sdInfoCase_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSdInfo() {
            this.sdInfoCase_ = 0;
            this.sdInfo_ = null;
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.SDNavigationOrBuilder
        public LaneInfo getLaneInfo() {
            if (this.sdInfoCase_ == 1) {
                return (LaneInfo) this.sdInfo_;
            }
            return LaneInfo.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLaneInfo(LaneInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.sdInfo_ = value;
            this.sdInfoCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLaneInfo(LaneInfo.Builder builderForValue) {
            this.sdInfo_ = builderForValue.build();
            this.sdInfoCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeLaneInfo(LaneInfo value) {
            if (this.sdInfoCase_ == 1 && this.sdInfo_ != LaneInfo.getDefaultInstance()) {
                this.sdInfo_ = LaneInfo.newBuilder((LaneInfo) this.sdInfo_).mergeFrom((LaneInfo.Builder) value).buildPartial();
            } else {
                this.sdInfo_ = value;
            }
            this.sdInfoCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLaneInfo() {
            if (this.sdInfoCase_ == 1) {
                this.sdInfoCase_ = 0;
                this.sdInfo_ = null;
            }
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.SDNavigationOrBuilder
        public NaviInfo getNaviInfo() {
            if (this.sdInfoCase_ == 2) {
                return (NaviInfo) this.sdInfo_;
            }
            return NaviInfo.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNaviInfo(NaviInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.sdInfo_ = value;
            this.sdInfoCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNaviInfo(NaviInfo.Builder builderForValue) {
            this.sdInfo_ = builderForValue.build();
            this.sdInfoCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNaviInfo(NaviInfo value) {
            if (this.sdInfoCase_ == 2 && this.sdInfo_ != NaviInfo.getDefaultInstance()) {
                this.sdInfo_ = NaviInfo.newBuilder((NaviInfo) this.sdInfo_).mergeFrom((NaviInfo.Builder) value).buildPartial();
            } else {
                this.sdInfo_ = value;
            }
            this.sdInfoCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNaviInfo() {
            if (this.sdInfoCase_ == 2) {
                this.sdInfoCase_ = 0;
                this.sdInfo_ = null;
            }
        }

        @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.SDNavigationOrBuilder
        public ManeuverInfo getManeuverInfo() {
            if (this.sdInfoCase_ == 3) {
                return (ManeuverInfo) this.sdInfo_;
            }
            return ManeuverInfo.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManeuverInfo(ManeuverInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.sdInfo_ = value;
            this.sdInfoCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManeuverInfo(ManeuverInfo.Builder builderForValue) {
            this.sdInfo_ = builderForValue.build();
            this.sdInfoCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeManeuverInfo(ManeuverInfo value) {
            if (this.sdInfoCase_ == 3 && this.sdInfo_ != ManeuverInfo.getDefaultInstance()) {
                this.sdInfo_ = ManeuverInfo.newBuilder((ManeuverInfo) this.sdInfo_).mergeFrom((ManeuverInfo.Builder) value).buildPartial();
            } else {
                this.sdInfo_ = value;
            }
            this.sdInfoCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearManeuverInfo() {
            if (this.sdInfoCase_ == 3) {
                this.sdInfoCase_ = 0;
                this.sdInfo_ = null;
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if (this.sdInfoCase_ == 1) {
                output.writeMessage(1, (LaneInfo) this.sdInfo_);
            }
            if (this.sdInfoCase_ == 2) {
                output.writeMessage(2, (NaviInfo) this.sdInfo_);
            }
            if (this.sdInfoCase_ == 3) {
                output.writeMessage(3, (ManeuverInfo) this.sdInfo_);
            }
        }

        @Override // com.android.carsdk.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = this.sdInfoCase_ == 1 ? 0 + CodedOutputStream.computeMessageSize(1, (LaneInfo) this.sdInfo_) : 0;
            if (this.sdInfoCase_ == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, (NaviInfo) this.sdInfo_);
            }
            if (this.sdInfoCase_ == 3) {
                size2 += CodedOutputStream.computeMessageSize(3, (ManeuverInfo) this.sdInfo_);
            }
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static SDNavigation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SDNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SDNavigation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SDNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SDNavigation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SDNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SDNavigation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SDNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SDNavigation parseFrom(InputStream input) throws IOException {
            return (SDNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SDNavigation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SDNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SDNavigation parseDelimitedFrom(InputStream input) throws IOException {
            return (SDNavigation) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SDNavigation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SDNavigation) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SDNavigation parseFrom(CodedInputStream input) throws IOException {
            return (SDNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SDNavigation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SDNavigation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SDNavigation prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom((Builder) prototype);
        }

        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SDNavigation, Builder> implements SDNavigationOrBuilder {
            private Builder() {
                super(SDNavigation.DEFAULT_INSTANCE);
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.SDNavigationOrBuilder
            public SdInfoCase getSdInfoCase() {
                return ((SDNavigation) this.instance).getSdInfoCase();
            }

            public Builder clearSdInfo() {
                copyOnWrite();
                ((SDNavigation) this.instance).clearSdInfo();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.SDNavigationOrBuilder
            public LaneInfo getLaneInfo() {
                return ((SDNavigation) this.instance).getLaneInfo();
            }

            public Builder setLaneInfo(LaneInfo value) {
                copyOnWrite();
                ((SDNavigation) this.instance).setLaneInfo(value);
                return this;
            }

            public Builder setLaneInfo(LaneInfo.Builder builderForValue) {
                copyOnWrite();
                ((SDNavigation) this.instance).setLaneInfo(builderForValue);
                return this;
            }

            public Builder mergeLaneInfo(LaneInfo value) {
                copyOnWrite();
                ((SDNavigation) this.instance).mergeLaneInfo(value);
                return this;
            }

            public Builder clearLaneInfo() {
                copyOnWrite();
                ((SDNavigation) this.instance).clearLaneInfo();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.SDNavigationOrBuilder
            public NaviInfo getNaviInfo() {
                return ((SDNavigation) this.instance).getNaviInfo();
            }

            public Builder setNaviInfo(NaviInfo value) {
                copyOnWrite();
                ((SDNavigation) this.instance).setNaviInfo(value);
                return this;
            }

            public Builder setNaviInfo(NaviInfo.Builder builderForValue) {
                copyOnWrite();
                ((SDNavigation) this.instance).setNaviInfo(builderForValue);
                return this;
            }

            public Builder mergeNaviInfo(NaviInfo value) {
                copyOnWrite();
                ((SDNavigation) this.instance).mergeNaviInfo(value);
                return this;
            }

            public Builder clearNaviInfo() {
                copyOnWrite();
                ((SDNavigation) this.instance).clearNaviInfo();
                return this;
            }

            @Override // android.car.hardware.xpu.XpuXNgpNaviInfo.SDNavigationOrBuilder
            public ManeuverInfo getManeuverInfo() {
                return ((SDNavigation) this.instance).getManeuverInfo();
            }

            public Builder setManeuverInfo(ManeuverInfo value) {
                copyOnWrite();
                ((SDNavigation) this.instance).setManeuverInfo(value);
                return this;
            }

            public Builder setManeuverInfo(ManeuverInfo.Builder builderForValue) {
                copyOnWrite();
                ((SDNavigation) this.instance).setManeuverInfo(builderForValue);
                return this;
            }

            public Builder mergeManeuverInfo(ManeuverInfo value) {
                copyOnWrite();
                ((SDNavigation) this.instance).mergeManeuverInfo(value);
                return this;
            }

            public Builder clearManeuverInfo() {
                copyOnWrite();
                ((SDNavigation) this.instance).clearManeuverInfo();
                return this;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.carsdk.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new SDNavigation();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    SDNavigation other = (SDNavigation) arg1;
                    switch (other.getSdInfoCase()) {
                        case LANE_INFO:
                            done = this.sdInfoCase_ == 1;
                            this.sdInfo_ = visitor.visitOneofMessage(done, this.sdInfo_, other.sdInfo_);
                            break;
                        case NAVI_INFO:
                            done = this.sdInfoCase_ == 2;
                            this.sdInfo_ = visitor.visitOneofMessage(done, this.sdInfo_, other.sdInfo_);
                            break;
                        case MANEUVER_INFO:
                            done = this.sdInfoCase_ == 3;
                            this.sdInfo_ = visitor.visitOneofMessage(done, this.sdInfo_, other.sdInfo_);
                            break;
                        case SDINFO_NOT_SET:
                            done = this.sdInfoCase_ != 0;
                            visitor.visitOneofNotSet(done);
                            break;
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && other.sdInfoCase_ != 0) {
                        this.sdInfoCase_ = other.sdInfoCase_;
                    }
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
                                    LaneInfo.Builder subBuilder = null;
                                    if (this.sdInfoCase_ == 1) {
                                        subBuilder = ((LaneInfo) this.sdInfo_).toBuilder();
                                    }
                                    this.sdInfo_ = input.readMessage(LaneInfo.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((LaneInfo.Builder) ((LaneInfo) this.sdInfo_));
                                        this.sdInfo_ = subBuilder.buildPartial();
                                    }
                                    this.sdInfoCase_ = 1;
                                } else if (tag == 18) {
                                    NaviInfo.Builder subBuilder2 = null;
                                    if (this.sdInfoCase_ == 2) {
                                        subBuilder2 = ((NaviInfo) this.sdInfo_).toBuilder();
                                    }
                                    this.sdInfo_ = input.readMessage(NaviInfo.parser(), extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom((NaviInfo.Builder) ((NaviInfo) this.sdInfo_));
                                        this.sdInfo_ = subBuilder2.buildPartial();
                                    }
                                    this.sdInfoCase_ = 2;
                                } else if (tag != 26) {
                                    if (!input.skipField(tag)) {
                                        done = true;
                                    }
                                } else {
                                    ManeuverInfo.Builder subBuilder3 = null;
                                    if (this.sdInfoCase_ == 3) {
                                        subBuilder3 = ((ManeuverInfo) this.sdInfo_).toBuilder();
                                    }
                                    this.sdInfo_ = input.readMessage(ManeuverInfo.parser(), extensionRegistry);
                                    if (subBuilder3 != null) {
                                        subBuilder3.mergeFrom((ManeuverInfo.Builder) ((ManeuverInfo) this.sdInfo_));
                                        this.sdInfo_ = subBuilder3.buildPartial();
                                    }
                                    this.sdInfoCase_ = 3;
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this));
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (PARSER == null) {
                        synchronized (SDNavigation.class) {
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

        public static SDNavigation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SDNavigation> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
