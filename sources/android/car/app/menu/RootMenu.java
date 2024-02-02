package android.car.app.menu;

import android.annotation.SystemApi;
import android.os.Bundle;
@SystemApi
/* loaded from: classes.dex */
public class RootMenu {
    private final Bundle mBundle;
    private final String mRootId;

    public RootMenu(String id) {
        this(id, null);
    }

    public RootMenu(String id, Bundle extras) {
        this.mRootId = id;
        this.mBundle = extras;
    }

    public String getId() {
        return this.mRootId;
    }

    public Bundle getBundle() {
        return new Bundle(this.mBundle);
    }
}
