package com.android.car.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class CarRatedFloatListeners<T> {
    private float mUpdateRate;
    private final Map<T, Float> mListenersToRate = new HashMap(4);
    protected long mLastUpdateTime = -1;

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
        if (this.mListenersToRate.isEmpty()) {
            return false;
        }
        Float updateRate = (Float) Collections.max(this.mListenersToRate.values());
        if (updateRate.floatValue() != this.mUpdateRate) {
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
        if (this.mUpdateRate < updateRate) {
            this.mUpdateRate = updateRate;
            return true;
        } else if (oldUpdateRate != null && oldUpdateRate.floatValue() == this.mUpdateRate) {
            this.mUpdateRate = ((Float) Collections.max(this.mListenersToRate.values())).floatValue();
            return false;
        } else {
            return false;
        }
    }

    public Collection<T> getListeners() {
        return this.mListenersToRate.keySet();
    }
}
