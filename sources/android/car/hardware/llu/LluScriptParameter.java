package android.car.hardware.llu;
/* loaded from: classes.dex */
public class LluScriptParameter {
    private int[] mData;

    private LluScriptParameter(int[] data) {
        this.mData = data;
    }

    public int[] getData() {
        return this.mData;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private int mEffectName;
        private int mFrontEffectDataTotalLength;
        private int mFrontEffectLoop;
        private int mFrontEffectPeriod;
        private int mFrontEffectRetain;
        private int mRearEffectDataTotalLength;
        private int mRearEffectLoop;
        private int mRearEffectPeriod;
        private int mRearEffectRetain;

        public Builder effectName(int val) {
            this.mEffectName = val;
            return this;
        }

        public Builder frontEffectPeriod(int val) {
            this.mFrontEffectPeriod = val;
            return this;
        }

        public Builder frontEffectLoop(int val) {
            this.mFrontEffectLoop = val;
            return this;
        }

        public Builder frontEffectRetain(int val) {
            this.mFrontEffectRetain = val;
            return this;
        }

        public Builder frontEffectDataTotalLength(int val) {
            this.mFrontEffectDataTotalLength = val;
            return this;
        }

        public Builder rearEffectPeriod(int val) {
            this.mRearEffectPeriod = val;
            return this;
        }

        public Builder rearEffectLoop(int val) {
            this.mRearEffectLoop = val;
            return this;
        }

        public Builder rearEffectRetain(int val) {
            this.mRearEffectRetain = val;
            return this;
        }

        public Builder rearEffectDataTotalLength(int val) {
            this.mRearEffectDataTotalLength = val;
            return this;
        }

        public LluScriptParameter build() {
            int[] data = {this.mEffectName, this.mFrontEffectPeriod, this.mFrontEffectLoop, this.mFrontEffectRetain, this.mFrontEffectDataTotalLength, this.mRearEffectPeriod, this.mRearEffectLoop, this.mRearEffectRetain, this.mRearEffectDataTotalLength};
            return new LluScriptParameter(data);
        }
    }
}
