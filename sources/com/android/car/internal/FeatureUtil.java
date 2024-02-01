package com.android.car.internal;
/* loaded from: classes.dex */
public class FeatureUtil {
    public static void assertFeature(boolean featureFlag) {
        if (!featureFlag) {
            throw new IllegalStateException("Feature not enabled");
        }
    }
}
