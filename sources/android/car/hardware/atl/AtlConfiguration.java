package android.car.hardware.atl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class AtlConfiguration {
    public static final int INVALID_COLOR = 255;
    public static final int USE_PREDEFINED_COLOR = 0;
    public static final int USE_RGB_COLOR = 1;
    private byte[] mData;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ColorType {
    }

    private AtlConfiguration(byte[] data) {
        this.mData = data;
    }

    public byte[] getData() {
        return this.mData;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private int colorType;
        private int fadingTime;
        private int intensity;
        private boolean isFadeEnabled;
        private boolean isLedEnabled;
        private boolean isLeftAtlControlEnabled;
        private boolean isRightAtlControlEnabled;
        private int rgbBlue;
        private int rgbGreen;
        private int rgbRedOrPreDefinedColor;

        public Builder isLeftAtlControlEnabled(boolean enable) {
            this.isLeftAtlControlEnabled = enable;
            return this;
        }

        public Builder isRightAtlControlEnabled(boolean enable) {
            this.isRightAtlControlEnabled = enable;
            return this;
        }

        public Builder colorType(int type) {
            this.colorType = type;
            return this;
        }

        public Builder isLedEnabled(boolean enable) {
            this.isLedEnabled = enable;
            return this;
        }

        public Builder isFadeEnabled(boolean enable) {
            this.isFadeEnabled = enable;
            return this;
        }

        public Builder fadingTime(int value) {
            this.fadingTime = value;
            return this;
        }

        public Builder intensity(int value) {
            this.intensity = value;
            return this;
        }

        public Builder rgbRedOrPreDefinedColor(int value) {
            this.rgbRedOrPreDefinedColor = value;
            return this;
        }

        public Builder rgbGreen(int value) {
            this.rgbGreen = value;
            return this;
        }

        public Builder rgbBlue(int value) {
            this.rgbBlue = value;
            return this;
        }

        public AtlConfiguration build() {
            byte[] data = new byte[8];
            data[0] = this.isLeftAtlControlEnabled ? (byte) 1 : (byte) 0;
            data[0] = (byte) (data[0] | ((byte) (this.isRightAtlControlEnabled ? 2 : 0)));
            data[1] = 0;
            data[2] = (byte) (1 & this.colorType);
            data[2] = (byte) (data[2] | ((byte) (this.isLedEnabled ? 2 : 0)));
            data[2] = (byte) (data[2] | ((byte) (this.isFadeEnabled ? 4 : 0)));
            data[3] = (byte) this.fadingTime;
            data[4] = (byte) this.intensity;
            data[5] = (byte) this.rgbRedOrPreDefinedColor;
            data[6] = (byte) this.rgbGreen;
            data[7] = (byte) this.rgbBlue;
            return new AtlConfiguration(data);
        }
    }
}
