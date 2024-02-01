package android.car.hardware.bms;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.hardware.CarEcuManager;
import android.car.hardware.XpVehicle.IXpVehicle;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class CarBmsManager extends CarEcuManager {
    public static final int BMS_AC_CHG_STOP_REASON_BMS_ERR = 2;
    public static final int BMS_AC_CHG_STOP_REASON_CCS_ABNORMAL = 3;
    public static final int BMS_AC_CHG_STOP_REASON_CHARGE_FULL = 1;
    public static final int BMS_AC_CHG_STOP_REASON_DEFAULT = 0;
    public static final int BMS_BAT_CHRG_ST_ABNORMAL_STOP = 4;
    public static final int BMS_BAT_CHRG_ST_CHARGE_FULL = 2;
    public static final int BMS_BAT_CHRG_ST_CHARGING = 1;
    public static final int BMS_BAT_CHRG_ST_DC_ERROR_STOP = 6;
    public static final int BMS_BAT_CHRG_ST_DC_NORMAL_STOP = 5;
    public static final int BMS_BAT_CHRG_ST_ERROR_STOP = 3;
    public static final int BMS_BAT_CHRG_ST_IPU_ERR = 9;
    public static final int BMS_BAT_CHRG_ST_NO_CHARGE = 0;
    public static final int BMS_BAT_CHRG_ST_OBC_STOP = 8;
    public static final int BMS_BAT_CHRG_ST_VCU_STOP = 7;
    public static final int BMS_BAT_TYPE_LFP = 1;
    public static final int BMS_BAT_TYPE_NCM = 0;
    public static final int BMS_CHARGE_MODE_100_OHM_AC_CHG = 5;
    public static final int BMS_CHARGE_MODE_1500_OHM_AC_CHG = 2;
    public static final int BMS_CHARGE_MODE_220_OHM_AC_CHG = 4;
    public static final int BMS_CHARGE_MODE_680_OHM_AC_CHG = 3;
    public static final int BMS_CHARGE_MODE_AC_CHG_HALF_LINKING = 8;
    public static final int BMS_CHARGE_MODE_CC2_ABNORMAL_VALUE = 9;
    public static final int BMS_CHARGE_MODE_CC_ABNORMAL_VALUE = 1;
    public static final int BMS_CHARGE_MODE_DC_CHG = 6;
    public static final int BMS_CHARGE_MODE_MODE_COLLISION = 10;
    public static final int BMS_CHARGE_MODE_NO_LINK = 0;
    public static final int BMS_CHARGE_MODE_ONLYLINK_EVCC = 12;
    public static final int BMS_CHARGE_MODE_SUPER_CHG = 7;
    public static final int BMS_CHARGE_MODE_V2G_LINK = 14;
    public static final int BMS_CHARGE_MODE_V2L_LINK = 11;
    public static final int BMS_CHARGE_MODE_V2V_LINK = 13;
    public static final int BMS_DC_CHG_STOP_REASON_BMS_ERR = 2;
    public static final int BMS_DC_CHG_STOP_REASON_CHARGE_FULL = 1;
    public static final int BMS_DC_CHG_STOP_REASON_DC_ERROR_STOP = 4;
    public static final int BMS_DC_CHG_STOP_REASON_DC_NORMAL_STOP = 3;
    public static final int BMS_DC_CHG_STOP_REASON_DEFAULT = 0;
    public static final int BMS_DC_CHG_STOP_REASON_VCU_STOP = 5;
    public static final int BMS_HIGHVOL_LOCK_CLOSED = 0;
    public static final int BMS_HIGHVOL_LOCK_OPEN = 1;
    private static final boolean DBG = false;
    @SystemApi
    public static final int DTC_CODE_ERROR = 3;
    @SystemApi
    public static final int DTC_CODE_NORMAL = 0;
    public static final int ID_BMS_AC_CHG_STOP_REASON = 557853721;
    public static final int ID_BMS_AC_MAX_CURRENT = 559950864;
    public static final int ID_BMS_BATT_RES = 557853709;
    public static final int ID_BMS_BATT_TEMP_MAX = 557853705;
    public static final int ID_BMS_BATT_TEMP_MAX_NUM = 557853713;
    public static final int ID_BMS_BATT_TEMP_MIN_NUM = 557853698;
    public static final int ID_BMS_BATT_VOLT = 559950863;
    public static final int ID_BMS_BAT_CHRG_ST = 557853719;
    public static final int ID_BMS_BAT_TYPE = 557853720;
    public static final int ID_BMS_BAT_WATERTEMP = 559950874;
    public static final int ID_BMS_CCS_OUT_CURRENT = 559950859;
    public static final int ID_BMS_CCS_OUT_VOLT = 559950858;
    public static final int ID_BMS_CELL_TEMP_MAX_NUM = 559950868;
    public static final int ID_BMS_CELL_TEMP_MIN_NUM = 559950869;
    public static final int ID_BMS_CELL_VOLT_MAX = 559950866;
    public static final int ID_BMS_CELL_VOLT_MAX_NUM = 557853708;
    public static final int ID_BMS_CELL_VOLT_MIN = 559950867;
    public static final int ID_BMS_CELL_VOLT_MIN_NUM = 557853697;
    public static final int ID_BMS_CHARGE_HIGHVOL_LOCKST = 557853723;
    public static final int ID_BMS_CHARGE_MODE = 557853718;
    public static final int ID_BMS_CURRENT = 559950856;
    @SystemApi
    public static final int ID_BMS_DC_CHG_CURT_OVER = 557853702;
    public static final int ID_BMS_DC_CHG_STOP_REASON = 557853703;
    public static final int ID_BMS_DISCHARGE_HIGHVOL_LOCKST = 557853724;
    @SystemApi
    public static final int ID_BMS_DTC_ERR_STOP_CURR = 557853710;
    public static final int ID_BMS_FAILURE_LVL = 557853701;
    public static final int ID_BMS_INSULATIVE_RESIST_VAL = 557853725;
    private static final String TAG = "CarBmsManager";
    private final ArraySet<Integer> mBmsPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarBmsEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarBmsManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mBmsPropertyIds = new ArraySet<>(Arrays.asList(559950864, 559950863, 559950858, 559950859, 557853701, 557853708, 557853697, 557853713, 557853698, 557853705, 559950866, 559950867, 557853709, 559950856, 557853702, 557853703, 557853710, 559950868, 559950869, 557853718, 557853719, 557853720, 557853721, 557853725, 557853724, 557853723, 559950874));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public static String getServiceName() {
        return Car.XP_BMS_SERVICE;
    }

    @Override // android.car.hardware.CarEcuManager
    @SystemApi
    protected ArraySet<Integer> getPropertyIds() {
        return this.mBmsPropertyIds;
    }

    public int getBmsFailureLvl() throws Exception {
        return this.mService.getBmsFailureLvl();
    }

    public int getVoltMaxNum() throws Exception {
        return this.mService.getBmsVoltMaxNum();
    }

    public int getVoltMinNum() throws Exception {
        return this.mService.getBmsVoltMinNum();
    }

    public int getTempMaxNum() throws Exception {
        return this.mService.getBmsHottestCellNum();
    }

    public int getTempMinNum() throws Exception {
        return this.mService.getBmsColdestCellNum();
    }

    public int getTempMax() throws Exception {
        return this.mService.getBmsMaxTemp();
    }

    public float getVoltMax() throws Exception {
        return this.mService.getBmsVoltMax();
    }

    public float getVoltMin() throws Exception {
        return this.mService.getBmsVoltMin();
    }

    public int getInsulationResistance() throws Exception {
        return this.mService.getBmsInsulationResistance();
    }

    public float getBatteryCurrent() throws Exception {
        return this.mService.getBmsBatteryCurrent();
    }

    public float getAcMaxCurrent() throws Exception {
        return this.mService.getBmsAcMaxCurrent();
    }

    @Deprecated
    public int getDtcErrorStopCurrent() throws Exception {
        return this.mService.getBmsDtcErrorStopCurrent();
    }

    @Deprecated
    public int getDtcChargeCurrentOver() throws Exception {
        return this.mService.getBmsDtcChargeCurrentOver();
    }

    public int getDcChargeStopReason() throws Exception {
        return this.mService.getBmsDcChargeStopReason();
    }

    public float getBatteryTotalVolt() throws Exception {
        return this.mService.getBmsBatteryTotalVolt();
    }

    public float getDcCurrent() throws Exception {
        return this.mService.getBmsDcCurrent();
    }

    public float getDcVolt() throws Exception {
        return this.mService.getBmsDcVolt();
    }

    public float getBmsCellTempMaxNum() throws Exception {
        return this.mService.getBmsCellTempMaxNum();
    }

    public float getBmsCellTempMinNum() throws Exception {
        return this.mService.getBmsCellTempMinNum();
    }

    public int getChargeMode() throws Exception {
        return this.mService.getBmsChargeMode();
    }

    public int getBatteryChargeStatus() throws Exception {
        return this.mService.getBmsBatteryChargeStatus();
    }

    public int getBatteryType() throws Exception {
        return this.mService.getBmsBatteryType();
    }

    public int getAcChargeStopReason() throws Exception {
        return this.mService.getBmsAcChargeStopReason();
    }

    public float getBattOutWaterTempature() throws Exception {
        return this.mService.getBattOutWaterTempature();
    }

    public int getChargeHighVoltageLockState() throws Exception {
        return this.mService.getChargeHighVoltageLockState();
    }

    public int getDischargeHighVoltageLockState() throws Exception {
        return this.mService.getDischargeHighVoltageLockState();
    }

    public int getInsulativeResistanceValue() throws Exception {
        return this.mService.getInsulativeResistanceValue();
    }
}
