package android.car.hardware.radio;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.hardware.CarEcuManager;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.XpVehicle.IXpVehicle;
import android.car.media.CarAudioManager;
import android.content.Context;
import android.hardware.automotive.vehicle.V2_0.VehicleArea;
import android.hardware.radio.ProgramList;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioTuner;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SystemApi
/* loaded from: classes.dex */
public final class CarRadioManager extends CarEcuManager {
    public static final int BAND_AM = 0;
    public static final int BAND_FM = 1;
    private static final boolean DBG = false;
    private static final String DISABLE_TUNER = "fm_enable=false";
    private static final String ENABLE_TUNER = "fm_enable=true";
    public static final int ID_RADIO_RADIO_STATUS = 1879053320;
    private static final int MODULE_INDEX = 0;
    public static final int PRIMARY_CHANNEL = 256;
    private static final String TAG = "CarRadioManager";
    private List<RadioManager.BandDescriptor> mAmFmRegionConfig;
    private final AudioManager mAudioManager;
    private final ArraySet<CarEcuManager.CarEcuEventCallback> mCallbacks;
    private final CarAudioManager mCarAudioManager;
    private final Handler mHandler;
    private RadioManager.ProgramInfo mInfo;
    private volatile boolean mIsTunerPowered;
    private RadioManager.ModuleProperties mModule;
    private ProgramList mProgramList;
    private final ProgramList.OnCompleteListener mProgramListCompleteListener;
    private final RadioManager mRadioManager;
    private final ArraySet<Integer> mRadioPropertyIds;
    private RadioTuner mRadioTuner;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarRadioEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    /* loaded from: classes.dex */
    private class CarBroadcastRadioCallback extends RadioTuner.Callback {
        private final WeakReference<CarRadioManager> mManager;

        public CarBroadcastRadioCallback(CarRadioManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        public void onProgramInfoChanged(RadioManager.ProgramInfo info) {
            if (Log.isLoggable(CarRadioManager.TAG, 3)) {
                Log.d(CarRadioManager.TAG, "Program info changed: " + info);
            }
            CarRadioManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnProgramInfoChangedEvent(info, true, true);
            }
        }

        public void onError(int status) {
            Log.e(CarRadioManager.TAG, "onError(); status: " + status);
            CarRadioManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnErrorEvent(status);
            }
        }
    }

    public List<RadioManager.BandDescriptor> getAmFmRegionConfig() {
        return this.mAmFmRegionConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnErrorEvent(int status) {
        Collection<CarEcuManager.CarEcuEventCallback> callbacks;
        synchronized (this) {
            callbacks = new ArraySet<>(this.mCallbacks);
        }
        for (CarEcuManager.CarEcuEventCallback l : callbacks) {
            l.onErrorEvent(ID_RADIO_RADIO_STATUS, status);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnProgramInfoChangedEvent(RadioManager.ProgramInfo info, boolean powerOn, boolean update) {
        Collection<CarEcuManager.CarEcuEventCallback> callbacks;
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "handleOnProgramInfoChangedEvent: " + info);
        }
        synchronized (this) {
            callbacks = new ArraySet<>(this.mCallbacks);
            if (update) {
                this.mInfo = info;
            }
        }
        RadioInfo radioInfo = new RadioInfo(info, powerOn);
        CarPropertyValue<String> value = new CarPropertyValue<>(ID_RADIO_RADIO_STATUS, VehicleArea.GLOBAL, radioInfo.toString());
        Log.d(TAG, "handleOnProgramInfoChangedEvent " + radioInfo.toString());
        for (CarEcuManager.CarEcuEventCallback l : callbacks) {
            l.onChangeEvent(value);
        }
    }

    public CarRadioManager(Car car, IBinder service, IBinder audioService, IBinder vehicleService, Context context, Handler handler) {
        super(car, service, false, TAG);
        this.mCallbacks = new ArraySet<>();
        this.mIsTunerPowered = false;
        this.mProgramListCompleteListener = new ProgramList.OnCompleteListener() { // from class: android.car.hardware.radio.-$$Lambda$CarRadioManager$JaJqLM0wB7i89Ne16tqz2dzQyO8
            public final void onComplete() {
                CarRadioManager.this.onProgramListUpdated();
            }
        };
        this.mRadioPropertyIds = new ArraySet<>(Arrays.asList(Integer.valueOf((int) ID_RADIO_RADIO_STATUS)));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
        this.mCarAudioManager = new CarAudioManager(car, audioService);
        this.mAudioManager = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
        this.mRadioManager = (RadioManager) context.getSystemService("broadcastradio");
        this.mHandler = handler;
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    public synchronized void registerCallback(CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "registerCallback");
        }
        this.mCallbacks.add(callback);
    }

    @Override // android.car.hardware.CarEcuManager
    public synchronized void unregisterCallback(CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "unregisterCallback");
        }
        this.mCallbacks.remove(callback);
    }

    @Override // android.car.hardware.CarEcuManager
    public synchronized void registerPropCallback(Collection<Integer> ids, CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        registerCallback(callback);
    }

    @Override // android.car.hardware.CarEcuManager
    public synchronized void unregisterPropCallback(Collection<Integer> ids, CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        unregisterCallback(callback);
    }

    @Override // android.car.hardware.CarEcuManager, android.car.CarManagerBase
    public void onCarDisconnected() {
        super.onCarDisconnected();
        this.mCarAudioManager.onCarDisconnected();
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mRadioPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_RADIO_SERVICE;
    }

    private void initModules() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "initModules()");
        }
        if (this.mModule != null) {
            Log.i(TAG, "Tuner Module gotten, just return");
            return;
        }
        List<RadioManager.ModuleProperties> modules = new ArrayList<>();
        int status = this.mRadioManager.listModules(modules);
        if (status != 0) {
            Log.w(TAG, "Couldn't get radio module list: " + status);
        } else if (modules.size() == 0) {
            Log.w(TAG, "No radio modules on this device");
        } else {
            this.mModule = modules.get(0);
            this.mAmFmRegionConfig = Arrays.asList(this.mModule.getBands());
        }
    }

    public void setPowerOnTunner() throws Exception {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "setPowerOnTunner() with TunerPowered: " + this.mIsTunerPowered);
        }
        if (this.mIsTunerPowered) {
            Log.w(TAG, "Radio has been powered");
            return;
        }
        initModules();
        if (this.mModule == null) {
            return;
        }
        CarBroadcastRadioCallback cb = new CarBroadcastRadioCallback(this);
        this.mRadioTuner = this.mRadioManager.openTuner(this.mModule.getId(), (RadioManager.BandConfig) null, true, cb, this.mHandler);
        if (this.mRadioTuner == null) {
            return;
        }
        this.mAudioManager.setParameters(ENABLE_TUNER);
        this.mIsTunerPowered = true;
        this.mHandler.post(new Runnable() { // from class: android.car.hardware.radio.-$$Lambda$CarRadioManager$NHeR6nJWg3mwiGypHQvcmoRZHSQ
            @Override // java.lang.Runnable
            public final void run() {
                CarRadioManager.this.lambda$setPowerOnTunner$0$CarRadioManager();
            }
        });
    }

    public /* synthetic */ void lambda$setPowerOnTunner$0$CarRadioManager() {
        handleOnProgramInfoChangedEvent(null, true, true);
    }

    public void setPowerOffTunner() throws Exception {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "setPowerOffTunner() with TunerPowered: " + this.mIsTunerPowered);
        }
        if (this.mIsTunerPowered) {
            ProgramList programList = this.mProgramList;
            if (programList != null) {
                programList.close();
                this.mProgramList = null;
            }
            RadioTuner radioTuner = this.mRadioTuner;
            if (radioTuner != null) {
                radioTuner.close();
                this.mRadioTuner = null;
            }
            this.mAudioManager.setParameters(DISABLE_TUNER);
            this.mIsTunerPowered = false;
            this.mHandler.post(new Runnable() { // from class: android.car.hardware.radio.-$$Lambda$CarRadioManager$qmJ-6TXeSHpuSZTcG4G03BhEH0w
                @Override // java.lang.Runnable
                public final void run() {
                    CarRadioManager.this.lambda$setPowerOffTunner$1$CarRadioManager();
                }
            });
        }
    }

    public /* synthetic */ void lambda$setPowerOffTunner$1$CarRadioManager() {
        handleOnProgramInfoChangedEvent(null, false, true);
    }

    public boolean requestTunerMute(boolean mute) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "requestTunerMute(" + mute + ") with TunerPowered: " + this.mIsTunerPowered);
        }
        if (this.mIsTunerPowered) {
            if (!mute) {
                this.mAudioManager.setParameters(ENABLE_TUNER);
                return true;
            }
            this.mAudioManager.setParameters(DISABLE_TUNER);
            return true;
        }
        return true;
    }

    public void setRadioSearchStationUp() throws Exception {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "setRadioSearchStationUp() with TunerPowered: " + this.mIsTunerPowered);
        }
        if (this.mIsTunerPowered) {
            this.mRadioTuner.scan(0, true);
        }
    }

    public void setRadioSearchStationDown() throws Exception {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "setRadioSearchStationDown()");
        }
        if (this.mIsTunerPowered) {
            this.mRadioTuner.scan(1, true);
        }
    }

    public ProgramList getProgramList() {
        return this.mProgramList;
    }

    public void setStartFullBandScan() throws Exception {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "setStartFullBandScan()");
        }
        if (this.mIsTunerPowered) {
            this.mProgramList = this.mRadioTuner.getDynamicProgramList((ProgramList.Filter) null);
            this.mProgramList.addOnCompleteListener(this.mProgramListCompleteListener);
        }
    }

    public void setStopFullBandScan() throws Exception {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "setStopFullBandScan()");
        }
        ProgramList programList = this.mProgramList;
        if (programList != null) {
            programList.close();
            this.mProgramList = null;
        }
    }

    public void setRadioBand(int band) throws Exception {
        this.mService.setRadioBand(band);
    }

    public void setRadioVolumePercent(int channel, int vol) throws Exception {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "setRadioVolumePercent(channel=" + channel + ", vol=" + vol + ")");
        }
        if (256 == channel) {
            int groupId = this.mCarAudioManager.getVolumeGroupIdForUsage(1);
            int maxVol = this.mCarAudioManager.getGroupMaxVolume(groupId);
            int finalVal = (maxVol * vol) / 100;
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "setRadioVolumePercent(maxVol=" + maxVol + ", finalVal=" + finalVal + ")");
            }
            this.mCarAudioManager.setGroupVolume(groupId, finalVal, 0);
        }
    }

    public void setRadioVolumeAutoFocus(int percent) throws Exception {
        this.mService.setRadioVolumeAutoFocus(percent);
    }

    public int getRadioVolumeAutoFocus() throws Exception {
        return this.mService.getRadioVolumeAutoFocus();
    }

    public void setFmVolume(int channel, int volume) throws Exception {
        this.mService.setFmVolume(channel, volume);
    }

    public void setCarExhibitionModeVol(int percent) throws Exception {
        this.mService.setCarExhibitionModeVol(percent);
    }

    public void setRadioFrequency(int band, int frequency) throws Exception {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "setRadioFrequency(band=" + band + ", frequency=" + frequency + ")");
        }
        if (frequency < 0) {
            throw new IllegalArgumentException("illegal frequency value: " + frequency);
        }
        this.mRadioTuner.tune(ProgramSelector.createAmFmSelector(band, frequency));
    }

    public int[] getRadioFrequency() throws Exception {
        return this.mService.getRadioFrequency();
    }

    public void setAudioMode(int item, int value) throws Exception {
        this.mService.setAudioMode(item, value);
    }

    public int[] getAudioMode() throws Exception {
        return this.mService.getAudioMode();
    }

    public String getRadioStatus() throws Exception {
        return this.mService.getRadioStatus();
    }

    public String getAudioDspStatus() throws Exception {
        return this.mService.getAudioDspStatus();
    }

    public void setAudioGEQParams(int band, int frequence, int liftCurve, int gain) throws Exception {
        this.mService.setAudioGEQParams(band, frequence, liftCurve, gain);
    }

    public void setAudioBalanceFader(int value1, int value2) throws Exception {
        this.mCarAudioManager.setBalanceTowardRight(value1);
        this.mCarAudioManager.setFadeTowardFront(value2);
    }

    public void setAudioParameters() throws Exception {
        this.mService.setAudioParameters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProgramListUpdated() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "onProgramListUpdated");
        }
        this.mHandler.post(new Runnable() { // from class: android.car.hardware.radio.-$$Lambda$CarRadioManager$_BxlwfQfid5BMFDscEs54eQw080
            @Override // java.lang.Runnable
            public final void run() {
                CarRadioManager.this.lambda$onProgramListUpdated$2$CarRadioManager();
            }
        });
    }

    public /* synthetic */ void lambda$onProgramListUpdated$2$CarRadioManager() {
        for (RadioManager.ProgramInfo info : this.mProgramList.toList()) {
            handleOnProgramInfoChangedEvent(info, true, false);
        }
    }
}
