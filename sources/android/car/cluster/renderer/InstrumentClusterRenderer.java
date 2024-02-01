package android.car.cluster.renderer;

import android.annotation.SystemApi;
import android.content.Context;

@SystemApi
@Deprecated
/* loaded from: classes.dex */
public abstract class InstrumentClusterRenderer {
    private NavigationRenderer mNavigationRenderer;

    protected abstract NavigationRenderer createNavigationRenderer();

    public abstract void onCreate(Context context);

    public abstract void onStart();

    public abstract void onStop();

    public synchronized NavigationRenderer getNavigationRenderer() {
        return this.mNavigationRenderer;
    }

    public final synchronized void initialize() {
        this.mNavigationRenderer = createNavigationRenderer();
    }
}
