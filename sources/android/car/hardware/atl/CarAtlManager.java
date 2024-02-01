package android.car.hardware.atl;

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
import java.util.Objects;

@SystemApi
/* loaded from: classes.dex */
public final class CarAtlManager extends CarEcuManager {
    public static final int ATL_ACTIVE = 1;
    public static final int ATL_AUTO_BRIGHTNESS_OFF = 0;
    public static final int ATL_AUTO_BRIGHTNESS_ON = 1;
    public static final int ATL_DOUBLE_COLOR = 1;
    public static final int ATL_GROUP_0 = 1;
    public static final int ATL_GROUP_1 = 2;
    public static final int ATL_GROUP_10 = 11;
    public static final int ATL_GROUP_11 = 12;
    public static final int ATL_GROUP_12 = 13;
    public static final int ATL_GROUP_13 = 14;
    public static final int ATL_GROUP_14 = 15;
    public static final int ATL_GROUP_15 = 16;
    public static final int ATL_GROUP_16 = 17;
    public static final int ATL_GROUP_17 = 18;
    public static final int ATL_GROUP_18 = 19;
    public static final int ATL_GROUP_19 = 20;
    public static final int ATL_GROUP_2 = 3;
    public static final int ATL_GROUP_20 = 21;
    public static final int ATL_GROUP_21 = 22;
    public static final int ATL_GROUP_3 = 4;
    public static final int ATL_GROUP_4 = 5;
    public static final int ATL_GROUP_5 = 6;
    public static final int ATL_GROUP_6 = 7;
    public static final int ATL_GROUP_7 = 8;
    public static final int ATL_GROUP_8 = 9;
    public static final int ATL_GROUP_9 = 10;
    public static final int ATL_LIGHT_A1 = 1;
    public static final int ATL_LIGHT_A10 = 10;
    public static final int ATL_LIGHT_A10_BIT = 512;
    public static final int ATL_LIGHT_A11 = 11;
    public static final int ATL_LIGHT_A11_BIT = 1024;
    public static final int ATL_LIGHT_A12 = 12;
    public static final int ATL_LIGHT_A12_BIT = 2048;
    public static final int ATL_LIGHT_A1_BIT = 1;
    public static final int ATL_LIGHT_A2 = 2;
    public static final int ATL_LIGHT_A2_BIT = 2;
    public static final int ATL_LIGHT_A3 = 3;
    public static final int ATL_LIGHT_A3_BIT = 4;
    public static final int ATL_LIGHT_A4 = 4;
    public static final int ATL_LIGHT_A4_BIT = 8;
    public static final int ATL_LIGHT_A5 = 5;
    public static final int ATL_LIGHT_A5_BIT = 16;
    public static final int ATL_LIGHT_A6 = 6;
    public static final int ATL_LIGHT_A6_BIT = 32;
    public static final int ATL_LIGHT_A7 = 7;
    public static final int ATL_LIGHT_A7_BIT = 64;
    public static final int ATL_LIGHT_A8 = 8;
    public static final int ATL_LIGHT_A8_BIT = 128;
    public static final int ATL_LIGHT_A9 = 9;
    public static final int ATL_LIGHT_A9_BIT = 256;
    public static final int ATL_LIGHT_B1 = 13;
    public static final int ATL_LIGHT_B10 = 22;
    public static final int ATL_LIGHT_B10_BIT = 2097152;
    public static final int ATL_LIGHT_B1_BIT = 4096;
    public static final int ATL_LIGHT_B2 = 14;
    public static final int ATL_LIGHT_B2_BIT = 8192;
    public static final int ATL_LIGHT_B3 = 15;
    public static final int ATL_LIGHT_B3_BIT = 16384;
    public static final int ATL_LIGHT_B4 = 16;
    public static final int ATL_LIGHT_B4_BIT = 32768;
    public static final int ATL_LIGHT_B5 = 17;
    public static final int ATL_LIGHT_B5_BIT = 65536;
    public static final int ATL_LIGHT_B6 = 18;
    public static final int ATL_LIGHT_B6_BIT = 131072;
    public static final int ATL_LIGHT_B7 = 19;
    public static final int ATL_LIGHT_B7_BIT = 262144;
    public static final int ATL_LIGHT_B8 = 20;
    public static final int ATL_LIGHT_B8_BIT = 524288;
    public static final int ATL_LIGHT_B9 = 21;
    public static final int ATL_LIGHT_B9_BIT = 1048576;
    public static final int ATL_NOT_ACTIVE = 0;
    public static final int ATL_NOT_READY = 0;
    public static final int ATL_PROTOCOL0 = 0;
    public static final int ATL_PROTOCOL1 = 1;
    public static final int ATL_PROTOCOL2 = 2;
    public static final int ATL_READY = 1;
    public static final int ATL_SINGLE_COLOR = 0;
    public static final int ATL_SOLUTION0 = 0;
    public static final int ATL_SOLUTION1 = 1;
    private static final boolean DBG = false;
    public static final int ID_ATLS_COLOR_FADING_CTRL = 560994318;
    public static final int ID_ATLS_LIN1_DATA = 560994324;
    public static final int ID_ATLS_LIN2_DATA = 560994322;
    public static final int ID_ATLS_LIN3_DATA = 560994323;
    @Deprecated
    public static final int ID_ATLS_SW_CTRL = 557848593;
    public static final int ID_ATL_ATLS_ALLCTRL = 560994313;
    public static final int ID_ATL_ATLS_MULTICTRL = 560994312;
    public static final int ID_ATL_ATLS_SINGLECTRL = 560994311;
    public static final int ID_ATL_AUTOBRIGHTNESSSW = 557848581;
    public static final int ID_ATL_BRIGHTNESSCFG = 557848582;
    public static final int ID_ATL_LR_POWER_ST = 557914128;
    public static final int ID_ATL_OPEN = 557848586;
    @Deprecated
    public static final int ID_ATL_POWER_REQ = 557848591;
    public static final int ID_ATL_THEMECOLORSWCFG = 557848578;
    public static final int ID_ATL_THEMEFIRSTCOLORCFG = 557848579;
    public static final int ID_ATL_THEMESECONDCOLORCFG = 557848580;
    @Deprecated
    public static final int ID_ATL_WLCOMEFINISH = 557848577;
    private static final String TAG = "CarAtlManager";
    private final ArraySet<Integer> mAtlPropertyIds;
    private final IXpVehicle mService;

    @Deprecated
    /* loaded from: classes.dex */
    public static class Atl_light_data {
        public byte bright;
        public byte color;
        public byte fade;
    }

    /* loaded from: classes.dex */
    public interface CarAtlEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarAtlManager(Car car, IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mAtlPropertyIds = new ArraySet<>(Arrays.asList(557848586, 557848578, 557848579, 557848580, 557848581, 557848582, 560994311, 560994312, 560994313, 560994318, 557848591, 557914128, 560994322, 560994323, 560994324));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public static String getServiceName() {
        return Car.XP_ATL_SERVICE;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mAtlPropertyIds;
    }

    public int getAtlOpen() throws Exception {
        return this.mService.getAtlOpen();
    }

    public void setAtlOpen(int status) throws Exception {
        this.mService.setAtlOpen(status);
    }

    @Deprecated
    public int getAtlReady() throws Exception {
        return this.mService.getAtlReady();
    }

    public int getDoubleThemeColor() throws Exception {
        return this.mService.getDoubleThemeColor();
    }

    public void setDoubleThemeColor(int status) throws Exception {
        this.mService.setDoubleThemeColor(status);
    }

    public int getThemeFirstColor() throws Exception {
        return this.mService.getThemeFirstColor();
    }

    public void setThemeFirstColor(int color) throws Exception {
        this.mService.setThemeFirstColor(color);
    }

    public int getThemeSecondColor() throws Exception {
        return this.mService.getThemeSecondColor();
    }

    public void setThemeSecondColor(int color) throws Exception {
        this.mService.setThemeSecondColor(color);
    }

    public int getAutoBrightness() throws Exception {
        return this.mService.getAutoBrightness();
    }

    public void setAutoBrightness(int status) throws Exception {
        this.mService.setAutoBrightness(status);
    }

    public int getBrightnessLevel() throws Exception {
        return this.mService.getBrightnessLevel();
    }

    public void setBrightnessLevel(int brightness) throws Exception {
        this.mService.setBrightnessLevel(brightness);
    }

    public void setTwoLightData(byte protocol, byte[] lightPosition, boolean hold, byte[] color, byte[] bright, byte[] fade) throws Exception {
        this.mService.setTwoLightData(protocol, lightPosition, hold, color, bright, fade);
    }

    public void setGroutLightData(byte groupNum, byte solution, int lightList, boolean hold, byte color, byte bright, byte fade) throws Exception {
        this.mService.setGroutLightData(groupNum, solution, lightList, hold, color, bright, fade);
    }

    public void setAllLightData(boolean hold, byte[] color, byte[] bright, byte[] fade) throws Exception {
        this.mService.setAllLightData(hold, color, bright, fade);
    }

    public void setAtlConfiguration(AtlConfiguration config) throws Exception {
        Objects.requireNonNull(config);
        this.mService.setAtlConfiguration(config.getData());
    }

    @Deprecated
    public void setPowerRequestSwitchStatus(int onOff) throws Exception {
        this.mService.setAtlPowerRequestSwitchStatus(onOff);
    }

    public int[] getLrPowerRequestSwitchStatus() throws Exception {
        return this.mService.getAtlLrPowerRequestSwitchStatus();
    }

    @Deprecated
    public void setAtlSwitchStatus(int onOff) throws Exception {
        this.mService.setAtlSwitchStatus(onOff);
    }

    @Deprecated
    public int getAtlSwitchStatus() throws Exception {
        return this.mService.getAtlSwitchStatus();
    }

    public void setAtlLin2Data(byte[] data) throws Exception {
        this.mService.setAtlLin2Data(data);
    }

    public void setAtlLin3Data(byte[] data) throws Exception {
        this.mService.setAtlLin3Data(data);
    }

    public void setAtlLin1Data(byte[] data) throws Exception {
        this.mService.setAtlLin1Data(data);
    }
}
