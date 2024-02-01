package android.car.hardware.amp;

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
@SystemApi
/* loaded from: classes.dex */
public final class CarAmpManager extends CarEcuManager {
    public static final int AMP_A2B_LINK_STATUS_NG = 2;
    public static final int AMP_A2B_LINK_STATUS_OK = 1;
    public static final int AMP_AFFECT_DYNAUDIO = 1;
    public static final int AMP_AFFECT_SURROUND = 3;
    public static final int AMP_AFFECT_XAUDIO = 2;
    public static final int AMP_DOLBY_2_0 = 0;
    public static final int AMP_DOLBY_5_1 = 2;
    public static final int AMP_DOLBY_5_1_2 = 3;
    public static final int AMP_DOLBY_5_1_4 = 4;
    public static final int AMP_DOLBY_7_1 = 5;
    public static final int AMP_DOLBY_7_1_2 = 6;
    public static final int AMP_DOLBY_7_1_4 = 1;
    @Deprecated
    public static final int AMP_EFFECT_ST_DYNAUDIO_AUTHENTIC = 0;
    @Deprecated
    public static final int AMP_EFFECT_ST_DYNAUDIO_DYNAMIC = 1;
    @Deprecated
    public static final int AMP_EFFECT_ST_DYNAUDIO_SOFT = 2;
    @Deprecated
    public static final int AMP_EFFECT_ST_DYNAUDIO_SPEECH = 3;
    @Deprecated
    public static final int AMP_EFFECT_ST_NO_COMMAND = 8;
    @Deprecated
    public static final int AMP_EFFECT_ST_X_SOUND_COMMON = 4;
    @Deprecated
    public static final int AMP_EFFECT_ST_X_SOUND_HUMAN_VOICE = 5;
    @Deprecated
    public static final int AMP_EFFECT_ST_X_SOUND_JAZZ = 6;
    @Deprecated
    public static final int AMP_EFFECT_ST_X_SOUND_ROCK = 7;
    public static final int AMP_FRONT_LEFT_TRACK = 1;
    public static final int AMP_FRONT_RIGHT_TRACK = 2;
    public static final int AMP_FRONT_TRACK = 7;
    public static final int AMP_HEADREST_LEFT_TRACK = 5;
    public static final int AMP_HEADREST_RIGHT_TRACK = 6;
    public static final int AMP_LEV_1 = 1;
    public static final int AMP_LEV_2 = 2;
    public static final int AMP_LEV_3 = 3;
    public static final int AMP_LEV_4 = 4;
    public static final int AMP_LEV_5 = 5;
    public static final int AMP_LEV_6 = 6;
    public static final int AMP_LEV_7 = 7;
    public static final int AMP_LEV_NO_COMMAND = 20;
    public static final int AMP_LEV_OFF = 0;
    @Deprecated
    public static final int AMP_MUSICSCENE_CHURCH = 3;
    public static final int AMP_MUSICSCENE_FUNCTION_HALL = 2;
    public static final int AMP_MUSICSCENE_KTV = 3;
    public static final int AMP_MUSICSCENE_MUSIC_HALL = 4;
    public static final int AMP_MUSICSCENE_OFF = 5;
    public static final int AMP_MUSICSCENE_THEATER = 1;
    public static final int AMP_REAR_LEFT_TRACK = 3;
    public static final int AMP_REAR_RIGHT_TRACK = 4;
    public static final int AMP_REAR_TRACK = 8;
    public static final int AMP_SDSSC_LEV_1 = 1;
    public static final int AMP_SDSSC_LEV_2 = 2;
    public static final int AMP_SDSSC_LEV_3 = 3;
    @Deprecated
    public static final int AMP_SDSSC_LEV_4 = 4;
    public static final int AMP_SDSSC_LEV_OFF = 0;
    public static final int AMP_SOUND_FIELD_ALL = 15;
    public static final int AMP_SOUND_FIELD_DRIVER = 2;
    public static final int AMP_SOUND_FIELD_DRIVER_LEFT_HEADREST = 13;
    public static final int AMP_SOUND_FIELD_DRIVER_RIGHT_HEADREST = 14;
    public static final int AMP_SOUND_FIELD_FRONT_MIDDLE = 12;
    public static final int AMP_SOUND_FIELD_MIDDLE = 8;
    public static final int AMP_SOUND_FIELD_OFF = 1;
    public static final int AMP_SOUND_FIELD_PASSENGER = 3;
    public static final int AMP_SOUND_FIELD_REAR_MIDDLE = 7;
    public static final int AMP_SOUND_FIELD_RL_PASSENGER = 9;
    public static final int AMP_SOUND_FIELD_RR_PASSENGER = 10;
    public static final int AMP_SOUND_FIELD_SURROUND = 11;
    public static final int AMP_SOUND_FIELD_THIRD_MIDDLE = 4;
    public static final int AMP_SOUND_STYLE_AUTHENTIC = 0;
    public static final int AMP_SOUND_STYLE_DYNAMIC = 1;
    public static final int AMP_SOUND_STYLE_MOVIE = 2;
    public static final int AMP_SOUND_STYLE_SOFT = 4;
    public static final int AMP_SOUND_STYLE_USEREQ = 3;
    public static final int AMP_STATUS_OFF = 0;
    public static final int AMP_STATUS_ON = 1;
    public static final int AMP_STYLE_DYNAUDIO_DYNAMIC = 7;
    public static final int AMP_STYLE_DYNAUDIO_HUMAN_VOICE = 8;
    public static final int AMP_STYLE_DYNAUDIO_SOFT = 6;
    public static final int AMP_STYLE_DYNAUDIO_TRUE = 5;
    public static final int AMP_STYLE_XSOUND_COMMON = 1;
    public static final int AMP_STYLE_XSOUND_HUMAN_VOICE = 2;
    public static final int AMP_STYLE_XSOUND_JAZZ = 3;
    public static final int AMP_STYLE_XSOUND_ROCK = 4;
    private static final boolean DBG = false;
    public static final int ID_AMP_A2B_LINK_STATUS = 557850649;
    public static final int ID_AMP_ALLCHANNEL_STATUS = 557916171;
    public static final int ID_AMP_ALLCHANNEL_VOLUME = 557916170;
    public static final int ID_AMP_AUDIOAFFETSW = 557850626;
    public static final int ID_AMP_BODY_CTRL = 560996353;
    @Deprecated
    public static final int ID_AMP_DOLBY_ATOMS_SW = 557850641;
    public static final int ID_AMP_DYN_3D_EFFECT = 557850644;
    @Deprecated
    public static final int ID_AMP_DYN_SDVC_LEV = 557850643;
    @Deprecated
    public static final int ID_AMP_EFFECT_ST = 557850642;
    public static final int ID_AMP_FREQ_GAIN_GROUP_CTRL = 557916184;
    @Deprecated
    public static final int ID_AMP_GROUP_SW_CTRL = 560996367;
    @Deprecated
    public static final int ID_AMP_GROUP_VOL_CTRL = 560996368;
    public static final int ID_AMP_MUTESW = 557850627;
    public static final int ID_AMP_POWER_REQ = 557849873;
    public static final int ID_AMP_SCENE = 557850630;
    public static final int ID_AMP_SDSSC_LEV_SW = 557850645;
    public static final int ID_AMP_SOUNDFIELD = 557850632;
    public static final int ID_AMP_SOUND_SOURCE_DOLBY_ST = 557850646;
    public static final int ID_AMP_SOUND_STYLE_SW = 557850647;
    public static final int ID_AMP_STYLE = 557850629;
    public static final int ID_AMP_SW_CTRL = 560996365;
    public static final int ID_AMP_VOLUME_VALUE = 557850636;
    public static final int ID_AMP_VOL_CTRL = 560996366;
    private static final String TAG = "CarAmpManager";
    private final ArraySet<Integer> mAmpPropertyIds;
    private final IXpVehicle mService;

    /* loaded from: classes.dex */
    public interface CarAmpEventCallback extends CarEcuManager.CarEcuEventCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PropertyId {
    }

    public CarAmpManager(IBinder service, IBinder vehicleService, Context context, Handler handler) {
        super(service, context, handler, false, TAG);
        this.mAmpPropertyIds = new ArraySet<>(Arrays.asList(557850629, 557850630, 557850632, 557850626, 557850627, 560996353, 557916170, 557916171, 557850636, 557849873, 560996365, 560996366, 557850644, 557850645, 557850646, 557850647, 557916184, 557850649));
        this.mService = IXpVehicle.Stub.asInterface(vehicleService);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    @Override // android.car.hardware.CarEcuManager
    protected ArraySet<Integer> getPropertyIds() {
        return this.mAmpPropertyIds;
    }

    public static String getServiceName() {
        return Car.XP_AMP_SERVICE;
    }

    public int getMusicStyle() throws Exception {
        return this.mService.getAmpMusicStyle();
    }

    public void setMusicStyle(int type) throws Exception {
        this.mService.setAmpMusicStyle(type);
    }

    public int getMusicScene() throws Exception {
        return this.mService.getAmpMusicScene();
    }

    public void setMusicScene(int type) throws Exception {
        this.mService.setAmpMusicScene(type);
    }

    public int getAmpSoundField() throws Exception {
        return this.mService.getAmpSoundFieldMode();
    }

    public void setAmpSoundField(int type) throws Exception {
        this.mService.setAmpSoundFieldMode(type);
    }

    @Deprecated
    public int getAudioAffect() throws Exception {
        return this.mService.getApmAudioEffect();
    }

    @Deprecated
    public void setAudioAffect(int type) throws Exception {
        this.mService.setApmAudioEffect(type);
    }

    public int getAmpMute() throws Exception {
        return this.mService.isAmpMute();
    }

    public void setAmpMute(int status) throws Exception {
        this.mService.setAmpMute(status);
    }

    public void setAmpChanVolSource(int channelbit, int volume, int soundSource, int activebit) throws Exception {
        this.mService.setAmpChannelVolAndSource(channelbit, volume, soundSource, activebit);
    }

    public int[] getAllChannelVolume() throws Exception {
        return this.mService.getApmAllChannelVolume();
    }

    public int[] getAllChannelSoundSource() throws Exception {
        return this.mService.getApmAllChannelSoundSource();
    }

    public void sendCduVolumeToAmp(int volume) throws Exception {
        this.mService.sendCduVolumeToAmp(volume);
    }

    public void setAmpPowerRequestSwitchStatus(int onOff) throws Exception {
        this.mService.setAmpPowerRequestSwitchStatus(onOff);
    }

    public int getAmpPowerRequestSwitchStatus() throws Exception {
        return this.mService.getAmpPowerRequestSwitchStatus();
    }

    public void setAmpChannelSwitchControlStatus(byte[] sw) throws Exception {
        this.mService.setAmpChannelSwitchControlStatus(sw);
    }

    public void setAmpChannelVolumeControlValue(byte[] vol) throws Exception {
        this.mService.setAmpChannelVolumeControlValue(vol);
    }

    @Deprecated
    public void setAmpGroupSwitchControlStatus(byte[] sw) throws Exception {
        this.mService.setAmpGroupSwitchControlStatus(sw);
    }

    @Deprecated
    public void setAmpGroupVolumeControlValue(byte[] vol) throws Exception {
        this.mService.setAmpGroupVolumeControlValue(vol);
    }

    @Deprecated
    public void setDolbyAtomsSwitchStatus(int status) throws Exception {
        this.mService.setAmpDolbyAtomsSwitchStatus(status);
    }

    @Deprecated
    public void setAmpEffectStatus(int status) throws Exception {
        this.mService.setAmpEffectStatus(status);
    }

    @Deprecated
    public int getAmpEffectStatus() throws Exception {
        return this.mService.getAmpEffectStatus();
    }

    @Deprecated
    public void setDynSdvcLevel(int level) throws Exception {
        this.mService.setAmpDynSdvcLevel(level);
    }

    @Deprecated
    public int getDynSdvcLevel() throws Exception {
        return this.mService.getAmpDynSdvcLevel();
    }

    public void setDyn3DEffectLevel(int level) throws Exception {
        this.mService.setAmpDyn3DEffectLevel(level);
    }

    public int getDyn3DEffectLevel() throws Exception {
        return this.mService.getAmpDyn3DEffectLevel();
    }

    public void setSdsscLevel(int level) throws Exception {
        this.mService.setAmpSdsscLevel(level);
    }

    public int getSdsscLevel() throws Exception {
        return this.mService.getAmpSdsscLevel();
    }

    public void setSoundSourceDolbyFormat(int type) throws Exception {
        this.mService.setAmpSoundSourceDolbyFormat(type);
    }

    public int getSoundSourceDolbyFormat() throws Exception {
        return this.mService.getAmpSoundSourceDolbyFormat();
    }

    public void setSoundStyle(int type) throws Exception {
        this.mService.setAmpSoundStyle(type);
    }

    public int getSoundStyle() throws Exception {
        return this.mService.getAmpSoundStyle();
    }

    public void setFreqGainGroupControlValue(int[] eqValue) throws Exception {
        if (eqValue.length != 10) {
            throw new IllegalArgumentException();
        }
        this.mService.setAmpFreqGainGroupControlValue(eqValue);
    }

    public int[] getFreqGainGroupControlValue() throws Exception {
        return this.mService.getAmpFreqGainGroupControlValue();
    }

    public int getA2BLinkStatus() throws Exception {
        return this.mService.getAmpA2BLinkStatus();
    }
}
