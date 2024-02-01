package android.car;

import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes.dex */
public class XpCarFeatures {
    private static final String AMP_UNIT_CFC_CODE = "persist.sys.xiaopeng.AMP";
    private static final String AQS_CFC_CODE = "persist.sys.xiaopeng.AQS";
    private static final String AS_CFC_CODE = "persist.sys.xiaopeng.AS";
    private static final String AS_WELCOME_CFC_CODE = "persist.sys.xiaopeng.AS_WELCOME";
    private static final String ATLS_CFC_CODE = "persist.sys.xiaopeng.ATLS";
    private static final String ATL_CEILING_CODE = "persist.sys.xiaopeng.ATL_CEILING";
    private static final String ATL_LAYOUT_TYPE_CODE = "persist.sys.xiaopeng.ATL_LAYOUT_TYPE";
    public static final int ATL_TYPE_0 = 0;
    public static final int ATL_TYPE_1 = 1;
    private static final String AUDIO_VENDOR_CFC_CODE = "persist.sys.xiaopeng.audio.vendor";
    private static final String AVM_CFC_CODE = "persist.sys.xiaopeng.AVM";
    public static final int BATTERY_IRON_LITHIUM = 1;
    public static final int BATTERY_TERNARY_LITHIUM = 0;
    private static final String BATTERY_TYPE_CFC_CODE = "persist.sys.xiaopeng.batteryType";
    private static final String BMS_CFC_CODE = "persist.sys.xiaopeng.BMS";
    public static final int BODY_COLOR_BLACK_WC = 20;
    public static final int BODY_COLOR_BLACK_WN = 19;
    public static final int BODY_COLOR_BLUE = 7;
    public static final int BODY_COLOR_BLUE_1A = 26;
    public static final int BODY_COLOR_BLUE_WL = 16;
    public static final int BODY_COLOR_BROWN = 6;
    private static final String BODY_COLOR_CFC_CODE = "persist.sys.xiaopeng.bodyColor";
    public static final int BODY_COLOR_GRAY_WE = 21;
    public static final int BODY_COLOR_GRAY_WK = 15;
    public static final int BODY_COLOR_GREEN_BLACKROOF = 10;
    public static final int BODY_COLOR_GREEN_BLACK_DN = 13;
    public static final int BODY_COLOR_GREEN_WF = 24;
    public static final int BODY_COLOR_GREEN_WH = 18;
    public static final int BODY_COLOR_GREEN_WW = 22;
    public static final int BODY_COLOR_LIGHT_BLUE_WM = 17;
    public static final int BODY_COLOR_LIGHT_GREEN_1C = 28;
    public static final int BODY_COLOR_ORANGE_1B = 27;
    public static final int BODY_COLOR_PINK_WJ = 23;
    public static final int BODY_COLOR_PURPLE_BLACKROOF = 8;
    public static final int BODY_COLOR_RED_BLACKROOF = 1;
    public static final int BODY_COLOR_SILVER = 5;
    public static final int BODY_COLOR_SILVER_BLACK_DP = 14;
    public static final int BODY_COLOR_SILVER_WG = 14;
    public static final int BODY_COLOR_STARRY_GOLD = 20;
    public static final int BODY_COLOR_STARRY_GOLD_1D = 29;
    public static final int BODY_COLOR_WHITE_1B = 12;
    public static final int BODY_COLOR_WHITE_AND_TOP_BLACK_1D = 29;
    public static final int BODY_COLOR_YELLOW_WQ = 25;
    private static final String CARFEATURE_DEBUG_CFC_FLAG = "persist.sys.xiaopeng.carfeaturedebug";
    private static final String CARPET_LIGHT_CFC_CODE = "persist.sys.xiaopeng.CARPET_LIGHT";
    public static final int CAR_SALE_REGION_CHN = 0;
    public static final int CAR_SALE_REGION_EUE = 1;
    public static final int CAR_SALE_REGION_ISR = 2;
    private static final String CDC_CONTROL_CFC_CODE = "persist.sys.xiaopeng.CDC";
    private static final String CFC_CAR_SALE_REGION = "persist.sys.xiaopeng.locVersion";
    private static final String CMS_CFC_CODE = "persist.sys.xiaopeng.CMS";
    private static final String CWC_RL_CFC_CODE = "persist.sys.xiaopeng.CWC_RL";
    private static final String CWC_RR_CFC_CODE = "persist.sys.xiaopeng.CWC_RR";
    private static final String DOLBY_CFC_CODE = "persist.sys.xiaopeng.DOLBY";
    private static final String DRIVER_AIRBAG_CFC_CODE = "persist.sys.xiaopeng.FCenter.Airbag";
    private static final String DRIVER_MASSAGE_CFC_CODE = "persist.sys.xiaopeng.MSMD_MASSG";
    private static final String DRIVEWAY_TYPE_CFC_CODE = "persist.sys.xiaopeng.driveway";
    private static final String DRIVE_WIRELESS_CHARGE_CFC_CODE = "persist.sys.xiaopeng.CWC";
    private static final String DSM_CFC_CODE = "persist.sys.xiaopeng.dsm";
    private static final String ELECTRIC_TRUNK_CFC_CODE = "persist.sys.xiaopeng.empennage";
    private static final String ELEC_SPOILER_CFC_CODE = "persist.sys.xiaopeng.Spoiler";
    private static final String ETC_CFC_CODE = "persist.sys.xiaopeng.ETC";
    private static final String EXTENDEDCARTYPE = "persist.sys.xiaopeng.extendedCarType";
    private static final String FEATURE_MCU_FACTORY_MODE = "persist.sys.xiaopeng.factory_mode";
    private static final String FKS_CFC_CODE = "persist.sys.xiaopeng.FKS";
    public static final int FOUR_WHEEL_DRIVE = 1;
    private static final String FSEAT_RHYTHM_CFC_CODE = "persist.sys.xiaopeng.FSEAT_RHYTHM";
    private static final String IMS_CFC_CODE = "persist.sys.xiaopeng.IMS";
    private static final String IMU_CFC_CODE = "persist.sys.xiaopeng.IMU";
    private static final String IMU_GPS_CFC_CODE = "persist.sys.xiaopeng.IMU_GPS";
    private static final String INNER_COLOR_CFC_CODE = "persist.sys.xiaopeng.interColor";
    public static final int INTERNATIONAL_LOCAL_VERSION = 1;
    public static final int INTER_COLOR_BLACK_0A = 10;
    public static final int INTER_COLOR_BLACK_0D = 13;
    public static final int INTER_COLOR_BLACK_0F = 15;
    public static final int INTER_COLOR_BLACK_A2 = 2;
    public static final int INTER_COLOR_BLACK_E1 = 4;
    public static final int INTER_COLOR_BLACK_E5 = 8;
    public static final int INTER_COLOR_BLACK_E6 = 9;
    public static final int INTER_COLOR_BLUEGRAY_0C = 12;
    public static final int INTER_COLOR_BROWN_C2 = 1;
    public static final int INTER_COLOR_COFFEE = 16;
    public static final int INTER_COLOR_CREAM_A5 = 14;
    public static final int INTER_COLOR_CREAM_E2 = 5;
    public static final int INTER_COLOR_GRAY_0B = 11;
    public static final int INTER_COLOR_GRAY_AH = 15;
    public static final int INTER_COLOR_GRAY_E4 = 7;
    public static final int INTER_COLOR_PURPLE = 3;
    public static final int INTER_COLOR_RED_E3 = 6;
    public static final int INTER_COLOR_YACHT_BLUE = 17;
    private static final String IPUF_CFC_CODE = "persist.sys.xiaopeng.IPUF";
    private static final String IPUR_CFC_CODE = "persist.sys.xiaopeng.IPUR";
    private static final String KEYACCOUNT_CFC_CODE = "persist.sys.xiaopeng.keyaccount";
    public static final int LEFT_RUDDER = 0;
    private static final String LIDAR_LEFT_CFC_CODE = "persist.sys.xiaopeng.Lidar_F";
    private static final String LIDAR_RIGHT_CFC_CODE = "persist.sys.xiaopeng.Lidar_R";
    private static final String LLU_CFC_CODE = "persist.sys.xiaopeng.LLU";
    private static final String LOC_VERSION_CFC_CODE = "persist.sys.xiaopeng.locVersion";
    private static final String MAKEUP_MIRROR_CFC_CODE = "persist.sys.xiaopeng.MAKEUP_MIRROR";
    public static final int MANUAL = 0;
    private static final String MCU_HARDWAREID_CFC_CODE = "persist.sys.mcu.hardwareId";
    private static final String MCU_VERSION_CFC_CODE = "persist.sys.mcu.version";
    private static final String MIRROR_CFC_CODE = "persist.sys.xiaopeng.MIRROR";
    private static final String MIRROR_MEMORY_CFC_CODE = "persist.sys.xiaopeng.MIRROR_MEMORY";
    private static final String MRR_CFC_CODE = "persist.sys.xiaopeng.MRR";
    private static final String MSB_CFC_CODE = "persist.sys.xiaopeng.MSB";
    private static final String MSMD_ADJ_BACK_CFC_CODE = "persist.sys.xiaopeng.MSMD_ADJ_BACKREST";
    private static final String MSMD_ADJ_CFC_CODE = "persist.sys.xiaopeng.MSMD_ADJ";
    private static final String MSMD_ADJ_UPDOWN_CFC_CODE = "persist.sys.xiaopeng.MSMD_ADJ_UPDOWN";
    private static final String MSMD_CFC_CODE = "persist.sys.xiaopeng.MSMD";
    private static final String MSMD_CUSHEXT_CFC_CODE = "persist.sys.xiaopeng.MSMD_CUSHEXT";
    private static final String MSMD_CUSHEXT_TILT_CFC_CODE = "persist.sys.xiaopeng.MSMD_CUSHEXT_TILT";
    private static final String MSMD_HEAT_CFC_CODE = "persist.sys.xiaopeng.MSMD_HEAT";
    private static final String MSMD_HRS_CFC_CODE = "persist.sys.xiaopeng.MSMD_HRS";
    private static final String MSMD_LSU_CFC_CODE = "persist.sys.xiaopeng.MSMD_LSU";
    private static final String MSMD_VENT_CFC_CODE = "persist.sys.xiaopeng.MSMD_VENT";
    private static final String MSMP_ADJ_BACK_CFC_CODE = "persist.sys.xiaopeng.MSMP_ADJ_BACKREST";
    private static final String MSMP_ADJ_CFC_CODE = "persist.sys.xiaopeng.MSMP_ADJ";
    private static final String MSMP_ADJ_UPDOWN_CFC_CODE = "persist.sys.xiaopeng.MSMP_ADJ_UPDOWN";
    private static final String MSMP_CFC_CODE = "persist.sys.xiaopeng.MSMP";
    private static final String MSMP_CUSHEXT_CFC_CODE = "persist.sys.xiaopeng.MSMP_CUSHEXT";
    private static final String MSMP_CUSHEXT_TILT_CFC_CODE = "persist.sys.xiaopeng.MSMP_CUSHEXT_TILT";
    private static final String MSMP_HEAT_CFC_CODE = "persist.sys.xiaopeng.MSMP_HEAT";
    private static final String MSMP_HRS_CFC_CODE = "persist.sys.xiaopeng.MSMP_HRS";
    private static final String MSMP_LSU_CFC_CODE = "persist.sys.xiaopeng.MSMP_LSU";
    private static final String MSMP_VENT_CFC_CODE = "persist.sys.xiaopeng.MSMP_VENT";
    private static final String MSM_MID_SEAT_CFC_CODE = "persist.sys.xiaopeng.MSM_MID_SEAT";
    private static final String MSM_STOW_CFC_CODE = "persist.sys.xiaopeng.MSM_STOW";
    private static final String MSM_TILTING_CFC_CODE = "persist.sys.xiaopeng.MSM_TILTING";
    private static final String MULTIPLE_TEMPERATURE_AREA_CFC_CODE = "persist.sys.xiaopeng.multiTempArea";
    public static final int NATIONAL_LOCAL_VERSION = 0;
    private static final String NFC_CFC_CODE = "persist.sys.xiaopeng.NFC";
    private static final String NGP_CFC_CODE = "persist.sys.xiaopeng.ngpType";
    public static final int NGP_TYPE_CNGP = 2;
    public static final int NGP_TYPE_HNGP = 1;
    public static final int NGP_TYPE_NONE = 0;
    public static final int NUMBER_ONE = 1;
    public static final int NUMBER_TWO = 2;
    public static final int NUMBER_ZERO = 0;
    private static final String PART_NUMBER_CFC_CODE = "sys.xiaopeng.partNumber";
    private static final String PASSENGER_AIRBAG_CFC_CODE = "persist.sys.xiaopeng.PAB.Switch";
    private static final String PASSENGER_MASSAGE_CFC_CODE = "persist.sys.xiaopeng.MSMP_MASSG";
    private static final String PASSENGER_WIRELESS_CHARGE_CFC_CODE = "persist.sys.xiaopeng.CWC_FR";
    private static final String PERFUME_UNIT_CFC_CODE = "persist.sys.xiaopeng.SFS";
    private static final String PM03_DETECT_CFC_CODE = "persist.sys.xiaopeng.PM03";
    private static final String PM25_CFC_CODE = "persist.sys.xiaopeng.pm25";
    private static final String PM25_DISPLAY_CFC_CODE = "persist.sys.xiaopeng.pm25_display";
    public static final int POWER_DRIVE = 1;
    private static final String REFRIGERATOR_UNIT_CFC_CODE = "persist.sys.xiaopeng.refrigerator";
    public static final int RIGHT_RUDDER = 1;
    private static final String RLS_CFC_CODE = "persist.sys.xiaopeng.RLS";
    private static final String RSEAT_HEAT_CFC_CODE = "persist.sys.xiaopeng.RSEAT_HEAT";
    private static final String RUDDER_TYPE_CFC_CODE = "persist.sys.xiaopeng.rudderconfig";
    private static final String RWS_CFC_CODE = "persist.sys.xiaopeng.RWS";
    private static final String SABER_LIGHT_CFC_CODE = "persist.sys.xiaopeng.SaberLight";
    private static final String SCISSORGATE_CFC_CODE = "persist.sys.xiaopeng.scissorsGate";
    private static final String SCU_CFC_CODE = "persist.sys.xiaopeng.SCU";
    private static final String SECROW_HEAD_ADJ_CFC_CODE = "persist.sys.xiaopeng.SECROW_HEAD_ADJ";
    private static final String SECROW_LT_CUSHEXT_CFC_CODE = "persist.sys.xiaopeng.SECROW_LT_CUSHEXT";
    private static final String SECROW_LT_CUSHEXT_TILT_CFC_CODE = "persist.sys.xiaopeng.SECROW_LT_CUSHEXT_TILT";
    private static final String SECROW_LT_HEAT_CFC_CODE = "persist.sys.xiaopeng.SECROW_LT_HEAT";
    private static final String SECROW_LT_LSU_CFC_CODE = "persist.sys.xiaopeng.SECROW_LT_LSU";
    private static final String SECROW_LT_MASSG_CFC_CODE = "persist.sys.xiaopeng.SECROW_LT_MASSG";
    private static final String SECROW_LT_MEMORY_CFC_CODE = "persist.sys.xiaopeng.SECROW_LT_MEMORY";
    private static final String SECROW_LT_VENT_CFC_CODE = "persist.sys.xiaopeng.SECROW_LT_VENT";
    private static final String SECROW_LT_ZEROGRAV_CFC_CODE = "persist.sys.xiaopeng.SECROW_LT_ZEROGRAV";
    private static final String SECROW_MID_HEAD_HEGIHT_CFC_CODE = "persist.sys.xiaopeng.SECROW_MID_HEAD_HEGIHT";
    private static final String SECROW_RL_HEAD_HEGIHT_CFC_CODE = "persist.sys.xiaopeng.SECROW_RL_HEAD_HEGIHT";
    private static final String SECROW_RT_CUSHEXT_CFC_CODE = "persist.sys.xiaopeng.SECROW_RT_CUSHEXT";
    private static final String SECROW_RT_CUSHEXT_TILT_CFC_CODE = "persist.sys.xiaopeng.SECROW_RT_CUSHEXT_TILT";
    private static final String SECROW_RT_HEAT_CFC_CODE = "persist.sys.xiaopeng.SECROW_RT_HEAT";
    private static final String SECROW_RT_LSU_CFC_CODE = "persist.sys.xiaopeng.SECROW_RT_LSU";
    private static final String SECROW_RT_MASSG_CFC_CODE = "persist.sys.xiaopeng.SECROW_RT_MASSG";
    private static final String SECROW_RT_MEMORY_CFC_CODE = "persist.sys.xiaopeng.SECROW_RT_MEMORY";
    private static final String SECROW_RT_VENT_CFC_CODE = "persist.sys.xiaopeng.SECROW_RT_VENT";
    private static final String SECROW_RT_ZEROGRAV_CFC_CODE = "persist.sys.xiaopeng.SECROW_RT_ZEROGRAV";
    private static final String SECROW_SEAT_HORZ_CFC_CODE = "persist.sys.xiaopeng.SECROW_SEAT_HORZ";
    private static final String SECROW_WELCOME_CFC_CODE = "persist.sys.xiaopeng.SECROW_WELCOME";
    private static final String SFM_CFC_CODE = "persist.sys.xiaopeng.SFM";
    private static final String SHC_CFC_CODE = "persist.sys.xiaopeng.SHC";
    private static final String SPC_CFC_CODE = "persist.sys.xiaopeng.SPC";
    private static final String SPEAKER_COUNT_CFC_CODE = "persist.sys.xiaopeng.SPEAKER";
    private static final String SPECIFIC_SEAT_VENT_CFC_CODE = "persist.sys.xiaopeng.SEAT_VENT";
    private static final String SRR_LEFT_FRONT_CFC_CODE = "persist.sys.xiaopeng.SRR_FL";
    private static final String SRR_LEFT_REAR_CFC_CODE = "persist.sys.xiaopeng.SRR_RL";
    private static final String SRR_RIGHT_FRONT_CFC_CODE = "persist.sys.xiaopeng.SRR_FR";
    private static final String SRR_RIGHT_REAR_CFC_CODE = "persist.sys.xiaopeng.SRR_RR";
    private static final String STEER_COL_ADJ_CFC_CODE = "persist.sys.xiaopeng.STEER_COL_ADJ";
    public static final int SUPPORT = 1;
    private static final String SVA1_CFC_CODE = "persist.sys.xiaopeng.SVA1";
    private static final String SVA2_CFC_CODE = "persist.sys.xiaopeng.SVA2";
    private static final String SWS_HEAT_CFC_CODE = "persist.sys.xiaopeng.SWS_HEAT";
    private static final String TAG = "XpCarFeatures";
    public static final int TEMP_AREA_NUMBER_1 = 1;
    public static final int TEMP_AREA_NUMBER_2 = 2;
    public static final int TEMP_AREA_NUMBER_5 = 5;
    private static final String TRUNKPOWER_CFC_CODE = "persist.sys.xiaopeng.12vPower";
    private static final String TTM_CFC_CODE = "persist.sys.xiaopeng.TTM";
    public static final int TWO_WHEEL_DRIVE = 0;
    public static final int UNSUPPORT = 0;
    public static final int VEHICLE_LEVEL_0 = 0;
    public static final int VEHICLE_LEVEL_1 = 1;
    public static final int VEHICLE_LEVEL_2 = 2;
    public static final int VEHICLE_LEVEL_3 = 3;
    public static final int VEHICLE_LEVEL_4 = 4;
    public static final int VEHICLE_LEVEL_5 = 5;
    private static final String VEHICLE_LEVEL_CFC_CODE = "persist.sys.xiaopeng.cfcVehicleLevel";
    private static final String VEHICLE_PACKAGE_CFC_CODE = "persist.sys.xiaopeng.cfcConfigCode";
    public static final int VENDOR_AUDIO_DYNAUDIO = 2;
    public static final int VENDOR_AUDIO_XP = 1;
    private static final String VIN_CODE_CFC = "persist.sys.xiaopeng.vin";
    private static final String VPM_CFC_CODE = "persist.sys.xiaopeng.VPM";
    public static final int WHEEL_HUB19_INCH_ALLOY_WIND = 91;
    public static final int WHEEL_HUB20_INCH_ALLOY_WIND = 92;
    public static final int WHEEL_HUB_18_INCH_ALLOY = 31;
    public static final int WHEEL_HUB_18_INCH_ALLOY_F = 61;
    public static final int WHEEL_HUB_19_INCH_ALLOY = 32;
    public static final int WHEEL_HUB_19_INCH_SPORT = 33;
    public static final int WHEEL_HUB_19_INCH_WIND = 1;
    public static final int WHEEL_HUB_20_INCH_ALLOY = 62;
    public static final int WHEEL_HUB_21_INCH_ALLOY = 2;
    public static final int WHEEL_HUB_21_INCH_WIND = 3;
    private static final String WHEEL_HUB_CFC_CODE = "persist.sys.xiaopeng.WHEEL_HUB";
    private static final String XPU_CFC_CODE = "persist.sys.xiaopeng.XPU";
    private static final String XSPORT_CFC_CODE = "persist.sys.xiaopeng.XSPORT";
    private static boolean FeatureFlag = false;
    public static final String sCarType = Car.getXpCduType();

    public static int hasSaberLightFeature() {
        return getCfcIntVal(SABER_LIGHT_CFC_CODE, 0);
    }

    public static int hasEmpennageFeature() {
        return getCfcIntVal(ELECTRIC_TRUNK_CFC_CODE, 0);
    }

    public static int hasMSMDVentFeature() {
        return getCfcIntVal(MSMD_VENT_CFC_CODE, 0);
    }

    public static int hasMSMPVentFeature() {
        return getCfcIntVal(MSMP_VENT_CFC_CODE, 0);
    }

    public static int hasMSMSecRowLtVentFeature() {
        return getCfcIntVal(SECROW_LT_VENT_CFC_CODE, 0);
    }

    public static int hasMSMSecRowRtVentFeature() {
        return getCfcIntVal(SECROW_RT_VENT_CFC_CODE, 0);
    }

    public static int hasDriverAirBagFeature() {
        return getCfcIntVal(DRIVER_AIRBAG_CFC_CODE, 0);
    }

    public static int hasPassengerAirBagSwitchFeature() {
        return getCfcIntVal(PASSENGER_AIRBAG_CFC_CODE, 0);
    }

    public static int hasMSMDMemoryFeature() {
        return getCfcIntVal(MSMD_CFC_CODE, 0);
    }

    public static int hasMSMPMemoryFeature() {
        return getCfcIntVal(MSMP_CFC_CODE, 0);
    }

    public static int hasATLSFeature() {
        return getCfcIntVal(ATLS_CFC_CODE, 0);
    }

    public static int getATLLayoutType() {
        return getCfcIntVal(ATL_LAYOUT_TYPE_CODE, 0);
    }

    public static int hasATLCeiLingFeature() {
        return getCfcIntVal(ATL_CEILING_CODE, 0);
    }

    public static int hasLLUFeature() {
        return getCfcIntVal(LLU_CFC_CODE, 0);
    }

    public static int hasIPUFFeature() {
        return getCfcIntVal(IPUF_CFC_CODE, 0);
    }

    public static int hasIPURFeature() {
        return getCfcIntVal(IPUR_CFC_CODE, 0);
    }

    public static int hasMSMSecRowWelcomeFeature() {
        return getCfcIntVal(SECROW_WELCOME_CFC_CODE, 0);
    }

    public static int hasAMPFeature() {
        return getCfcIntVal(AMP_UNIT_CFC_CODE, 0);
    }

    public static int hasMSBFeature() {
        return getCfcIntVal(MSB_CFC_CODE, 0);
    }

    public static int hasScissorGateFeature() {
        return getCfcIntVal(SCISSORGATE_CFC_CODE, 0);
    }

    public static int hasAutoASFeature() {
        return getCfcIntVal(AS_CFC_CODE, 0);
    }

    public static int hasCDCFeature() {
        return getCfcIntVal(CDC_CONTROL_CFC_CODE, 0);
    }

    public static int hasIMUFeature() {
        return getCfcIntVal(IMU_CFC_CODE, 0);
    }

    public static int hasXPU() {
        return getCfcIntVal(XPU_CFC_CODE, 0);
    }

    public static int hasSCU() {
        return getCfcIntVal(SCU_CFC_CODE, 0);
    }

    public static int hasAVMFeature() {
        return getCfcIntVal(AVM_CFC_CODE, 0);
    }

    public static int hasSteelAdjustFeature() {
        return getCfcIntVal(STEER_COL_ADJ_CFC_CODE, 0);
    }

    public static int hasAQSFeature() {
        return getCfcIntVal(AQS_CFC_CODE, 0);
    }

    public static int hasMirrorHeatFeature() {
        return getCfcIntVal(MIRROR_CFC_CODE, 0);
    }

    public static int hasMirrorMemoryFeature() {
        return getCfcIntVal(MIRROR_MEMORY_CFC_CODE, 0);
    }

    public static int hasMSMDHeatFeature() {
        return getCfcIntVal(MSMD_HEAT_CFC_CODE, 0);
    }

    public static int hasMSMPHeatFeature() {
        return getCfcIntVal(MSMP_HEAT_CFC_CODE, 0);
    }

    public static int hasMSMSecRowLtHeatFeature() {
        return getCfcIntVal(SECROW_LT_HEAT_CFC_CODE, 0);
    }

    public static int hasMSMSecRowRtHeatFeature() {
        return getCfcIntVal(SECROW_RT_HEAT_CFC_CODE, 0);
    }

    public static int hasMSMDLSUFeature() {
        return getCfcIntVal(MSMD_LSU_CFC_CODE, 0);
    }

    public static int hasMSMPLSUFeature() {
        return getCfcIntVal(MSMP_LSU_CFC_CODE, 0);
    }

    public static int hasMSMSecRowLtLSUFeature() {
        return getCfcIntVal(SECROW_LT_LSU_CFC_CODE, 0);
    }

    public static int hasMSMSecRowRtLSUFeature() {
        return getCfcIntVal(SECROW_RT_LSU_CFC_CODE, 0);
    }

    public static int hasMSMDSeatAngleAdjustFeature() {
        return getCfcIntVal(MSMD_CUSHEXT_TILT_CFC_CODE, 0);
    }

    public static int hasMSMSecRowSaddlesAdjustLTFeature() {
        return getCfcIntVal(SECROW_LT_CUSHEXT_TILT_CFC_CODE, 0);
    }

    public static int hasMSMSecRowSaddlesAdjustRTFeature() {
        return getCfcIntVal(SECROW_RT_CUSHEXT_TILT_CFC_CODE, 0);
    }

    public static int hasMSMDSeatLegSupportFeature() {
        return getCfcIntVal(MSMD_CUSHEXT_CFC_CODE, 0);
    }

    public static int hasMSMPSeatLegSupportFeature() {
        return getCfcIntVal(MSMP_CUSHEXT_CFC_CODE, 0);
    }

    public static int hasMSMSecRowSeatLtLegSupportFeature() {
        return getCfcIntVal(SECROW_LT_CUSHEXT_CFC_CODE, 0);
    }

    public static int hasMSMSecRowSeatRtLegSupportFeature() {
        return getCfcIntVal(SECROW_RT_CUSHEXT_CFC_CODE, 0);
    }

    public static int hasSeatRhythmFeature() {
        return getCfcIntVal(FSEAT_RHYTHM_CFC_CODE, 0);
    }

    public static int hasSFSFeature() {
        return getCfcIntVal(PERFUME_UNIT_CFC_CODE, 0);
    }

    public static int hasRefrigeratorFeature() {
        return getCfcIntVal(REFRIGERATOR_UNIT_CFC_CODE, 0);
    }

    public static int hasLidarLeft() {
        return getCfcIntVal(LIDAR_LEFT_CFC_CODE, 0);
    }

    public static int hasLidarRight() {
        return getCfcIntVal(LIDAR_RIGHT_CFC_CODE, 0);
    }

    public static int hasTTMFeature() {
        return getCfcIntVal(TTM_CFC_CODE, 0);
    }

    public static int hasMSMDMassageFeature() {
        return getCfcIntVal(DRIVER_MASSAGE_CFC_CODE, 0);
    }

    public static int hasMSMPMassageFeature() {
        return getCfcIntVal(PASSENGER_MASSAGE_CFC_CODE, 0);
    }

    public static int hasMSMSecRowLtMassageFeature() {
        return getCfcIntVal(SECROW_LT_MASSG_CFC_CODE, 0);
    }

    public static int hasMSMSecRowRtMassageFeature() {
        return getCfcIntVal(SECROW_RT_MASSG_CFC_CODE, 0);
    }

    public static int hasDolbyFeature() {
        return getCfcIntVal(DOLBY_CFC_CODE, 0);
    }

    public static int hasMakeUpMirrorFeature() {
        return getCfcIntVal(MAKEUP_MIRROR_CFC_CODE, 0);
    }

    public static int hasSpoilerFeature() {
        return getCfcIntVal(ELEC_SPOILER_CFC_CODE, 0);
    }

    public static int hasFKSFeature() {
        return getCfcIntVal(FKS_CFC_CODE, 0);
    }

    public static int hasPM0_3Feature() {
        return getCfcIntVal(PM03_DETECT_CFC_CODE, 0);
    }

    public static int getVehicleInnerColor() {
        return getCfcIntVal(INNER_COLOR_CFC_CODE, 0);
    }

    public static int getVehicleBodyColor() {
        return getCfcIntVal(BODY_COLOR_CFC_CODE, 0);
    }

    public static int getBatteryPackageType() {
        return getCfcIntVal(BATTERY_TYPE_CFC_CODE, 0);
    }

    public static int getVehicleDriveWay() {
        return getCfcIntVal(DRIVEWAY_TYPE_CFC_CODE, 0);
    }

    public static int getVehicleRudderType() {
        return getCfcIntVal(RUDDER_TYPE_CFC_CODE, 0);
    }

    public static int hasDriverWirelessChargeFeature() {
        return getCfcIntVal(DRIVE_WIRELESS_CHARGE_CFC_CODE, 0);
    }

    public static int hasPassengerWirelessChargeFeature() {
        return getCfcIntVal(PASSENGER_WIRELESS_CHARGE_CFC_CODE, 0);
    }

    public static int hasSWSHeatFeature() {
        return getCfcIntVal(SWS_HEAT_CFC_CODE, 0);
    }

    public static int hasXSportFeature() {
        return getCfcIntVal(XSPORT_CFC_CODE, 0);
    }

    public static int getTotalSpeakerCount() {
        return getCfcIntVal(SPEAKER_COUNT_CFC_CODE, 0);
    }

    public static String getPartNumber() {
        return SystemProperties.get(PART_NUMBER_CFC_CODE, "");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getWheelHubSize() {
        char c;
        String str = sCarType;
        int hashCode = str.hashCode();
        if (hashCode == 2577) {
            if (str.equals(Car.CAR_CDU_TYPE_QB_H93)) {
                c = 4;
            }
            c = 65535;
        } else if (hashCode != 79611) {
            switch (hashCode) {
                case 2566:
                    if (str.equals("Q7")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 2567:
                    if (str.equals("Q8")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 2568:
                    if (str.equals("Q9")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals(Car.CAR_CDU_TYPE_Q7_E38A)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0 || c == 1) {
            int retVal = getCfcIntVal(WHEEL_HUB_CFC_CODE, 1);
            return retVal;
        } else if (c == 2) {
            int retVal2 = getCfcIntVal(WHEEL_HUB_CFC_CODE, 31);
            return retVal2;
        } else if (c == 3) {
            int retVal3 = getCfcIntVal(WHEEL_HUB_CFC_CODE, 61);
            return retVal3;
        } else if (c != 4) {
            return 1;
        } else {
            int retVal4 = getCfcIntVal(WHEEL_HUB_CFC_CODE, 91);
            return retVal4;
        }
    }

    public static String getVinCode() {
        return SystemProperties.get(VIN_CODE_CFC, "");
    }

    public static String getMcuSoftwareVersion() {
        return SystemProperties.get(MCU_VERSION_CFC_CODE, "");
    }

    public static String getMcuHardwareId() {
        return SystemProperties.get(MCU_HARDWAREID_CFC_CODE, "");
    }

    public static int getAudioVendorType() {
        return getCfcIntVal(AUDIO_VENDOR_CFC_CODE, 0);
    }

    public static int hasMSMSecRowLtCWCFeature() {
        return getCfcIntVal(CWC_RL_CFC_CODE, 0);
    }

    public static int hasMSMSecRowRtCWCFeature() {
        return getCfcIntVal(CWC_RR_CFC_CODE, 0);
    }

    public static int hasLightCarpetFeature() {
        return getCfcIntVal(CARPET_LIGHT_CFC_CODE, 0);
    }

    public static int hasRearFoldableScreen() {
        return getCfcIntVal(SFM_CFC_CODE, 0);
    }

    public static int hasIMSFeature() {
        return getCfcIntVal(IMS_CFC_CODE, 0);
    }

    public static int hasRLSFeature() {
        return getCfcIntVal(RLS_CFC_CODE, 1);
    }

    public static int hasRWSFeature() {
        return getCfcIntVal(RWS_CFC_CODE, 0);
    }

    public static int hasPM2_5Feature() {
        String str = sCarType;
        if (((str.hashCode() == 2577 && str.equals(Car.CAR_CDU_TYPE_QB_H93)) ? (char) 0 : (char) 65535) == 0) {
            int retVal = getCfcIntVal(PM25_CFC_CODE, 0);
            return retVal;
        }
        int retVal2 = getCfcIntVal(PM25_CFC_CODE, 1);
        return retVal2;
    }

    public static int getTempAreaCount() {
        String str = sCarType;
        if (((str.hashCode() == 2577 && str.equals(Car.CAR_CDU_TYPE_QB_H93)) ? (char) 0 : (char) 65535) == 0) {
            int retVal = getCfcIntVal(MULTIPLE_TEMPERATURE_AREA_CFC_CODE, 5);
            return retVal;
        }
        int retVal2 = getCfcIntVal(MULTIPLE_TEMPERATURE_AREA_CFC_CODE, 2);
        return retVal2;
    }

    public static int hasMSMSecRowSeatAdjustFeature() {
        return getCfcIntVal(SECROW_SEAT_HORZ_CFC_CODE, 0);
    }

    public static int hasMSMSecRowHeadAdjustFeature() {
        return getCfcIntVal(SECROW_HEAD_ADJ_CFC_CODE, 0);
    }

    public static int hasMSMThirdRowBothSidesHeadAdjustFeature() {
        return getCfcIntVal(SECROW_RL_HEAD_HEGIHT_CFC_CODE, 0);
    }

    public static int hasMSMThirdRowMidHeadAdjustFeature() {
        return getCfcIntVal(SECROW_MID_HEAD_HEGIHT_CFC_CODE, 0);
    }

    public static int hasMSMSecRowLtZeroGravityFeature() {
        return getCfcIntVal(SECROW_LT_ZEROGRAV_CFC_CODE, 0);
    }

    public static int hasMSMSecRowRtZeroGravityFeature() {
        return getCfcIntVal(SECROW_RT_ZEROGRAV_CFC_CODE, 0);
    }

    public static int hasMSMSecRowLtMemoryFeature() {
        return getCfcIntVal(SECROW_LT_MEMORY_CFC_CODE, 0);
    }

    public static int hasMSMSecRowRtMemoryFeature() {
        return getCfcIntVal(SECROW_RT_MEMORY_CFC_CODE, 0);
    }

    public static int hasMsmThirdFoldStowFeature() {
        return getCfcIntVal(MSM_STOW_CFC_CODE, 0);
    }

    public static int hasMsmThirdRowSeatAdjustFeature() {
        return getCfcIntVal(MSM_TILTING_CFC_CODE, 0);
    }

    public static int hasMSMThirdRowMidSeatFeature() {
        return getCfcIntVal(MSM_MID_SEAT_CFC_CODE, 0);
    }

    public static int hasMSMDHeadRestraintFeature() {
        return getCfcIntVal(MSMD_HRS_CFC_CODE, 0);
    }

    public static int hasMSMPHeadRestraintFeature() {
        return getCfcIntVal(MSMP_HRS_CFC_CODE, 0);
    }

    public static int hasMSMDSeatFrontBackAdjustFeature() {
        return getCfcIntVal(MSMD_ADJ_CFC_CODE, 1);
    }

    public static int hasMSMPSeatFrontBackAdjustFeature() {
        return getCfcIntVal(MSMP_ADJ_CFC_CODE, 1);
    }

    public static int hasMSMPSeatUpDownAdjustFeature() {
        return getCfcIntVal(MSMP_ADJ_UPDOWN_CFC_CODE, 0);
    }

    public static int hasMSMDSeatUpDownAdjustFeature() {
        return getCfcIntVal(MSMD_ADJ_UPDOWN_CFC_CODE, 1);
    }

    public static int hasMSMDSeatBackRestAdjustFeature() {
        return getCfcIntVal(MSMD_ADJ_BACK_CFC_CODE, 1);
    }

    public static int hasMSMPSeatBackRestAdjustFeature() {
        return getCfcIntVal(MSMP_ADJ_BACK_CFC_CODE, 1);
    }

    public static boolean isFactoryMode() {
        return 1 == SystemProperties.getInt(FEATURE_MCU_FACTORY_MODE, 0);
    }

    public static int hasSHCFeature() {
        return getCfcIntVal(SHC_CFC_CODE, 0);
    }

    public static int hasPM25DisplayFeature() {
        return getCfcIntVal(PM25_DISPLAY_CFC_CODE, 1);
    }

    public static int hasSPCFeature() {
        return getCfcIntVal(SPC_CFC_CODE, 0);
    }

    public static int hasVPMFeature() {
        return getCfcIntVal(VPM_CFC_CODE, 0);
    }

    public static int hasMRRFeature() {
        return getCfcIntVal(MRR_CFC_CODE, 0);
    }

    public static int hasNFCFeature() {
        return getCfcIntVal(NFC_CFC_CODE, 0);
    }

    public static int isKeyAccountVesion() {
        return getCfcIntVal(KEYACCOUNT_CFC_CODE, 0);
    }

    public static int hasTwelveVoltPowerFeature() {
        return getCfcIntVal(TRUNKPOWER_CFC_CODE, 1);
    }

    public static int hasETCFeature() {
        return getCfcIntVal(ETC_CFC_CODE, 0);
    }

    public static int hasDSMFeature() {
        return getCfcIntVal(DSM_CFC_CODE, 1);
    }

    public static int getNgpType() {
        return getCfcIntVal(NGP_CFC_CODE, 0);
    }

    public static int getCarSaleRegionCode() {
        if (Car.isExportVersion()) {
            return getCfcIntVal("persist.sys.xiaopeng.locVersion", 0);
        }
        return 0;
    }

    public static int hasBossKeyFeature() {
        return getCfcIntVal("persist.sys.xiaopeng.bossKey", 0);
    }

    public static int hasAutoHeadLampLightFeature() {
        return getCfcIntVal("persist.sys.xiaopeng.autoLampLight", 0);
    }

    private static int getCfcIntVal(String cfcCode, int defaultVal) {
        String ret = null;
        if (!Build.IS_USER && SystemProperties.getInt(CARFEATURE_DEBUG_CFC_FLAG, 0) == 1) {
            ret = SystemProperties.get(cfcCode + ".debug", "");
            if (!TextUtils.isEmpty(ret)) {
                Log.i(TAG, " get debug cfc config:" + cfcCode + ".debug value : " + ret);
            }
        }
        if (TextUtils.isEmpty(ret)) {
            ret = SystemProperties.get(cfcCode, "");
        }
        if (!TextUtils.isEmpty(ret)) {
            try {
                Log.i(TAG, " get cfc config:" + cfcCode + " value : " + ret);
                return Integer.parseInt(ret);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultVal;
    }
}
