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
    public ArrayList<Integer> configPara = new ArrayList<>();
    public ArrayList<VehicleAreaConfig> areaConfigs = new ArrayList<>();
    public ArrayList<Integer> configArray = new ArrayList<>();
    public String configString = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != VehiclePropConfig.class) {
            return false;
        }
        VehiclePropConfig other = (VehiclePropConfig) otherObject;
        if (this.prop == other.prop && this.attribute == other.attribute && HidlSupport.deepEquals(this.configPara, other.configPara) && this.access == other.access && this.changeMode == other.changeMode && HidlSupport.deepEquals(this.areaConfigs, other.areaConfigs) && HidlSupport.deepEquals(this.configArray, other.configArray) && HidlSupport.deepEquals(this.configString, other.configString) && this.minSampleRate == other.minSampleRate && this.maxSampleRate == other.maxSampleRate) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.prop))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.attribute))), Integer.valueOf(HidlSupport.deepHashCode(this.configPara)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.access))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.changeMode))), Integer.valueOf(HidlSupport.deepHashCode(this.areaConfigs)), Integer.valueOf(HidlSupport.deepHashCode(this.configArray)), Integer.valueOf(HidlSupport.deepHashCode(this.configString)), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.minSampleRate))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.maxSampleRate))));
    }

    public final String toString() {
        return "{.prop = " + this.prop + ", .attribute = " + this.attribute + ", .configPara = " + this.configPara + ", .access = " + VehiclePropertyAccess.toString(this.access) + ", .changeMode = " + VehiclePropertyChangeMode.toString(this.changeMode) + ", .areaConfigs = " + this.areaConfigs + ", .configArray = " + this.configArray + ", .configString = " + this.configString + ", .minSampleRate = " + this.minSampleRate + ", .maxSampleRate = " + this.maxSampleRate + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(88L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<VehiclePropConfig> readVectorFromParcel(HwParcel parcel) {
        ArrayList<VehiclePropConfig> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 88, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            VehiclePropConfig _hidl_vec_element = new VehiclePropConfig();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 88);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.prop = _hidl_blob.getInt32(_hidl_offset + 0);
        this.attribute = _hidl_blob.getInt32(_hidl_offset + 4);
        int _hidl_vec_size = _hidl_blob.getInt32(_hidl_offset + 8 + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 4, _hidl_blob.handle(), _hidl_offset + 8 + 0, true);
        this.configPara.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            int _hidl_vec_element = childBlob.getInt32(_hidl_index_0 * 4);
            this.configPara.add(Integer.valueOf(_hidl_vec_element));
        }
        this.access = _hidl_blob.getInt32(_hidl_offset + 24);
        this.changeMode = _hidl_blob.getInt32(_hidl_offset + 28);
        int _hidl_vec_size2 = _hidl_blob.getInt32(_hidl_offset + 32 + 8);
        HwBlob childBlob2 = parcel.readEmbeddedBuffer(_hidl_vec_size2 * 40, _hidl_blob.handle(), _hidl_offset + 32 + 0, true);
        this.areaConfigs.clear();
        for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
            VehicleAreaConfig _hidl_vec_element2 = new VehicleAreaConfig();
            _hidl_vec_element2.readEmbeddedFromParcel(parcel, childBlob2, _hidl_index_02 * 40);
            this.areaConfigs.add(_hidl_vec_element2);
        }
        int _hidl_vec_size3 = _hidl_blob.getInt32(_hidl_offset + 48 + 8);
        HwBlob childBlob3 = parcel.readEmbeddedBuffer(_hidl_vec_size3 * 4, _hidl_blob.handle(), _hidl_offset + 48 + 0, true);
        this.configArray.clear();
        for (int _hidl_index_03 = 0; _hidl_index_03 < _hidl_vec_size3; _hidl_index_03++) {
            this.configArray.add(Integer.valueOf(childBlob3.getInt32(_hidl_index_03 * 4)));
        }
        this.configString = _hidl_blob.getString(_hidl_offset + 64);
        parcel.readEmbeddedBuffer(this.configString.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 64 + 0, false);
        this.minSampleRate = _hidl_blob.getFloat(_hidl_offset + 80);
        this.maxSampleRate = _hidl_blob.getFloat(_hidl_offset + 84);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(88);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<VehiclePropConfig> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 88);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 88);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(_hidl_offset + 0, this.prop);
        _hidl_blob.putInt32(_hidl_offset + 4, this.attribute);
        int _hidl_vec_size = this.configPara.size();
        _hidl_blob.putInt32(_hidl_offset + 8 + 8, _hidl_vec_size);
        _hidl_blob.putBool(_hidl_offset + 8 + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 4);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            childBlob.putInt32(_hidl_index_0 * 4, this.configPara.get(_hidl_index_0).intValue());
        }
        _hidl_blob.putBlob(_hidl_offset + 8 + 0, childBlob);
        _hidl_blob.putInt32(_hidl_offset + 24, this.access);
        _hidl_blob.putInt32(_hidl_offset + 28, this.changeMode);
        int _hidl_vec_size2 = this.areaConfigs.size();
        _hidl_blob.putInt32(_hidl_offset + 32 + 8, _hidl_vec_size2);
        _hidl_blob.putBool(_hidl_offset + 32 + 12, false);
        HwBlob childBlob2 = new HwBlob(_hidl_vec_size2 * 40);
        for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
            this.areaConfigs.get(_hidl_index_02).writeEmbeddedToBlob(childBlob2, _hidl_index_02 * 40);
        }
        _hidl_blob.putBlob(_hidl_offset + 32 + 0, childBlob2);
        int _hidl_vec_size3 = this.configArray.size();
        _hidl_blob.putInt32(_hidl_offset + 48 + 8, _hidl_vec_size3);
        _hidl_blob.putBool(_hidl_offset + 48 + 12, false);
        HwBlob childBlob3 = new HwBlob(_hidl_vec_size3 * 4);
        for (int _hidl_index_03 = 0; _hidl_index_03 < _hidl_vec_size3; _hidl_index_03++) {
            childBlob3.putInt32(_hidl_index_03 * 4, this.configArray.get(_hidl_index_03).intValue());
        }
        _hidl_blob.putBlob(_hidl_offset + 48 + 0, childBlob3);
        _hidl_blob.putString(_hidl_offset + 64, this.configString);
        _hidl_blob.putFloat(_hidl_offset + 80, this.minSampleRate);
        _hidl_blob.putFloat(_hidl_offset + 84, this.maxSampleRate);
    }
}
