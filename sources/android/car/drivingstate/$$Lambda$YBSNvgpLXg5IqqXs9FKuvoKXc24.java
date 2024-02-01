package android.car.drivingstate;

import android.car.drivingstate.CarUxRestrictionsConfiguration;
import java.util.function.Function;

/* compiled from: lambda */
/* renamed from: android.car.drivingstate.-$$Lambda$YBSNvgpLXg5IqqXs9FKuvoKXc24  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$YBSNvgpLXg5IqqXs9FKuvoKXc24 implements Function {
    public static final /* synthetic */ $$Lambda$YBSNvgpLXg5IqqXs9FKuvoKXc24 INSTANCE = new $$Lambda$YBSNvgpLXg5IqqXs9FKuvoKXc24();

    private /* synthetic */ $$Lambda$YBSNvgpLXg5IqqXs9FKuvoKXc24() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((CarUxRestrictionsConfiguration.RestrictionsPerSpeedRange) obj).getSpeedRange();
    }
}
