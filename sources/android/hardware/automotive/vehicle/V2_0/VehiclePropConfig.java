package android.hardware.automotive.vehicle.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class VehiclePropConfig {
    public int access;
    public int attribute;
    public int changeMode;
    public float maxSampleRate;
    public float minSampleRate;
    public int prop;
    public final ArrayList<VehicleAreaConfig> areaConfigs = new ArrayList<>();
    public final ArrayList<Integer> configArray = new ArrayList<>();
    public String configString = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != VehiclePropConfig.class) {
            return false;
        }
        VehiclePropConfig other = (VehiclePropConfig) otherObject;
        if (this.prop == other.prop && this.attribute == other.attribute && this.access == other.access && this.changeMode == other.changeMode && HidlSupport.deepEquals(this.areaConfigs, other.areaConfigs) && HidlSupport.deepEquals(this.configArray, other.configArray) && HidlSupport.deepEquals(this.configString, other.configString) && this.minSampleRate == other.minSampleRate && this.maxSampleRate == other.maxSampleRate) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.prop))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.attribute))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.access))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.changeMode))), Integer.valueOf(HidlSupport.deepHashCode(this.areaConfigs)), Integer.valueOf(HidlSupport.deepHashCode(this.configArray)), Integer.valueOf(HidlSupport.deepHashCode(this.configString)), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.minSampleRate))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.maxSampleRate))));
    }

    public final String toString() {
        return "{.prop = " + this.prop + ", .attribute = " + this.attribute + ", .access = " + VehiclePropertyAccess.toString(this.access) + ", .changeMode = " + VehiclePropertyChangeMode.toString(this.changeMode) + ", .areaConfigs = " + this.areaConfigs + ", .configArray = " + this.configArray + ", .configString = " + this.configString + ", .minSampleRate = " + this.minSampleRate + ", .maxSampleRate = " + this.maxSampleRate + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(72L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<VehiclePropConfig> readVectorFromParcel(HwParcel parcel) {
        ArrayList<VehiclePropConfig> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 72, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            VehiclePropConfig _hidl_vec_element = new VehiclePropConfig();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 72);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.prop = _hidl_blob.getInt32(_hidl_offset + 0);
        this.attribute = _hidl_blob.getInt32(_hidl_offset + 4);
        this.access = _hidl_blob.getInt32(_hidl_offset + 8);
        this.changeMode = _hidl_blob.getInt32(_hidl_offset + 12);
        int _hidl_vec_size = _hidl_blob.getInt32(_hidl_offset + 16 + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 40, _hidl_blob.handle(), _hidl_offset + 16 + 0, true);
        this.areaConfigs.clear();
        int _hidl_vec_element = 0;
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            VehicleAreaConfig _hidl_vec_element2 = new VehicleAreaConfig();
            _hidl_vec_element2.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 40);
            this.areaConfigs.add(_hidl_vec_element2);
        }
        int _hidl_vec_size2 = _hidl_blob.getInt32(_hidl_offset + 32 + 8);
        HwBlob childBlob2 = parcel.readEmbeddedBuffer(_hidl_vec_size2 * 4, _hidl_blob.handle(), _hidl_offset + 32 + 0, true);
        this.configArray.clear();
        while (true) {
            int _hidl_index_02 = _hidl_vec_element;
            if (_hidl_index_02 < _hidl_vec_size2) {
                int _hidl_vec_element3 = childBlob2.getInt32(_hidl_index_02 * 4);
                this.configArray.add(Integer.valueOf(_hidl_vec_element3));
                _hidl_vec_element = _hidl_index_02 + 1;
            } else {
                this.configString = _hidl_blob.getString(_hidl_offset + 48);
                parcel.readEmbeddedBuffer(this.configString.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 48 + 0, false);
                this.minSampleRate = _hidl_blob.getFloat(_hidl_offset + 64);
                this.maxSampleRate = _hidl_blob.getFloat(_hidl_offset + 68);
                return;
            }
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(72);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<VehiclePropConfig> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 72);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 72);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(_hidl_offset + 0, this.prop);
        _hidl_blob.putInt32(_hidl_offset + 4, this.attribute);
        _hidl_blob.putInt32(_hidl_offset + 8, this.access);
        _hidl_blob.putInt32(_hidl_offset + 12, this.changeMode);
        int _hidl_vec_size = this.areaConfigs.size();
        _hidl_blob.putInt32(_hidl_offset + 16 + 8, _hidl_vec_size);
        _hidl_blob.putBool(_hidl_offset + 16 + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 40);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            this.areaConfigs.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 40);
        }
        _hidl_blob.putBlob(_hidl_offset + 16 + 0, childBlob);
        int _hidl_vec_size2 = this.configArray.size();
        _hidl_blob.putInt32(_hidl_offset + 32 + 8, _hidl_vec_size2);
        int _hidl_index_02 = 0;
        _hidl_blob.putBool(_hidl_offset + 32 + 12, false);
        HwBlob childBlob2 = new HwBlob(_hidl_vec_size2 * 4);
        while (true) {
            int _hidl_index_03 = _hidl_index_02;
            if (_hidl_index_03 >= _hidl_vec_size2) {
                _hidl_blob.putBlob(_hidl_offset + 32 + 0, childBlob2);
                _hidl_blob.putString(_hidl_offset + 48, this.configString);
                _hidl_blob.putFloat(_hidl_offset + 64, this.minSampleRate);
                _hidl_blob.putFloat(_hidl_offset + 68, this.maxSampleRate);
                return;
            }
            childBlob2.putInt32(_hidl_index_03 * 4, this.configArray.get(_hidl_index_03).intValue());
            _hidl_index_02 = _hidl_index_03 + 1;
        }
    }
}
