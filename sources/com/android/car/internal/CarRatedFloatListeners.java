package com.android.car.internal;

import android.car.hardware.property.CarPropertyManager;
import android.util.SparseArray;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class CarRatedFloatListeners<T> {
    private static final float NANOSECOND_PER_SECOND = 1.0E9f;
    private float mUpdateRate;
    private final Map<T, Float> mListenersToRate = new HashMap(4);
    private final Map<T, Long> mListenersUpdateTime = new HashMap(4);
    protected SparseArray<Long> mAreaIdToLastUpdateTime = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: protected */
    public CarRatedFloatListeners(float rate) {
        this.mUpdateRate = -2.1474836E9f;
        this.mUpdateRate = rate;
    }

    public boolean contains(T listener) {
        return this.mListenersToRate.containsKey(listener);
    }

    public float getRate() {
        return this.mUpdateRate;
    }

    public boolean remove(T listener) {
        this.mListenersToRate.remove(listener);
        this.mListenersUpdateTime.remove(listener);
        if (this.mListenersToRate.isEmpty()) {
            return false;
        }
        Float updateRate = (Float) Collections.max(this.mListenersToRate.values());
        if (Float.compare(updateRate.floatValue(), this.mUpdateRate) != 0) {
            this.mUpdateRate = updateRate.floatValue();
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return this.mListenersToRate.isEmpty();
    }

    public boolean addAndUpdateRate(T listener, float updateRate) {
        Float oldUpdateRate = this.mListenersToRate.put(listener, Float.valueOf(updateRate));
        this.mListenersUpdateTime.put(listener, 0L);
        if (this.mUpdateRate < updateRate) {
            this.mUpdateRate = updateRate;
            return true;
        } else if (oldUpdateRate != null && Float.compare(oldUpdateRate.floatValue(), this.mUpdateRate) == 0) {
            Float newUpdateRate = (Float) Collections.max(this.mListenersToRate.values());
            if (Float.compare(newUpdateRate.floatValue(), this.mUpdateRate) != 0) {
                this.mUpdateRate = newUpdateRate.floatValue();
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean needUpdateForSelectedListener(T listener, long eventTimeStamp) {
        Long nextUpdateTime = this.mListenersUpdateTime.get(listener);
        Float updateRate = this.mListenersToRate.get(listener);
        if (nextUpdateTime == null || updateRate == null) {
            return false;
        }
        if (updateRate.floatValue() == CarPropertyManager.SENSOR_RATE_ONCHANGE) {
            return true;
        }
        if (nextUpdateTime.longValue() > eventTimeStamp) {
            return false;
        }
        Float cycle = Float.valueOf(NANOSECOND_PER_SECOND / updateRate.floatValue());
        this.mListenersUpdateTime.put(listener, Long.valueOf(cycle.longValue() + eventTimeStamp));
        return true;
    }

    public boolean needUpdateForAreaId(int areaId, long eventTime) {
        long lastUpdateTime = this.mAreaIdToLastUpdateTime.get(areaId, 0L).longValue();
        if (eventTime >= lastUpdateTime) {
            this.mAreaIdToLastUpdateTime.put(areaId, Long.valueOf(eventTime));
            return true;
        }
        return false;
    }

    public Collection<T> getListeners() {
        return this.mListenersToRate.keySet();
    }
}
