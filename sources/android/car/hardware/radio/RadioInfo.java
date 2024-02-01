package android.car.hardware.radio;

import android.annotation.SystemApi;
import android.hardware.radio.RadioManager;
import java.util.Map;

@SystemApi
/* loaded from: classes.dex */
public class RadioInfo {
    public static final int BAND_AM = 0;
    public static final int BAND_FM = 1;
    public static final int TUNER_POWER_OFF = 0;
    public static final int TUNER_POWER_ON = 1;
    private int audio_status_callback;
    private int bassGain;
    private int freqPoint;
    private int gBanlanceLevel;
    private int gFaderLevel;
    private int heroLoudnessOn;
    private int mMuteState;
    private int mTunerLevel;
    private int midGain;
    private int pInputSource;
    private int primaryVolume;
    private int radioCurrentBand;
    private int radioCurrentFreq;
    private int state;
    private int trebleGain;
    private int tunerpower;

    public static int StringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public RadioInfo(RadioManager.ProgramInfo info, boolean powerOn) {
        this.audio_status_callback = -1;
        this.tunerpower = powerOn ? 1 : 0;
        if (info != null && info.getSelector() != null) {
            int type = info.getSelector().getProgramType();
            if (1 == type) {
                this.radioCurrentBand = 0;
            } else {
                this.radioCurrentBand = 1;
            }
            this.radioCurrentFreq = (int) info.getSelector().getFirstId(1);
            this.freqPoint = this.radioCurrentFreq;
            this.mTunerLevel = info.getSignalStrength();
            Map<String, String> mVendorInfo = info.getVendorInfo();
            if (mVendorInfo.get("audio_status_callback") != null) {
                this.audio_status_callback = StringToInt(mVendorInfo.get("audio_status_callback"));
                this.primaryVolume = StringToInt(mVendorInfo.get("primaryVolume"));
                this.radioCurrentBand = StringToInt(mVendorInfo.get("radioCurrentBand"));
                this.freqPoint = StringToInt(mVendorInfo.get("freqPoint"));
                this.pInputSource = StringToInt(mVendorInfo.get("pInputSource"));
                this.tunerpower = StringToInt(mVendorInfo.get("tunerpower"));
                this.mMuteState = StringToInt(mVendorInfo.get("mMuteState"));
                this.mTunerLevel = StringToInt(mVendorInfo.get("mTunerLevel"));
                this.state = StringToInt(mVendorInfo.get("state"));
                this.primaryVolume = StringToInt(mVendorInfo.get("primaryVolume"));
            }
        }
    }

    public RadioInfo() {
        this.audio_status_callback = -1;
    }

    public String toString() {
        return "{\"primaryVolume\":" + this.primaryVolume + ", \"radioCurrentBand\":" + this.radioCurrentBand + ", \"radioCurrentFreq\":" + this.radioCurrentFreq + ", \"freqPoint\":" + this.freqPoint + ", \"pInputSource\":" + this.pInputSource + ", \"tunerpower\":" + this.tunerpower + ", \"bassGain\":" + this.bassGain + ", \"midGain\":" + this.midGain + ", \"trebleGain\":" + this.trebleGain + ", \"gBanlanceLevel\":" + this.gBanlanceLevel + ", \"gFaderLevel\":" + this.gFaderLevel + ", \"heroLoudnessOn\":" + this.heroLoudnessOn + ", \"mMuteState\":" + this.mMuteState + ", \"mTunerLevel\":" + this.mTunerLevel + ", \"state\":" + this.state + "}";
    }
}
