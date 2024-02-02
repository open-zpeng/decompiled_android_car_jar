package android.car.app.menu;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SystemApi
/* loaded from: classes.dex */
public class CarMenuConstants {

    /* loaded from: classes.dex */
    public static class MenuItemConstants {
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_FIRSTITEM = 2;
        public static final String KEY_EMPTY_PLACEHOLDER = "android.car.app.menu.empty_placeholder";
        public static final String KEY_FLAGS = "android.car.app.menu.flags";
        public static final String KEY_ID = "android.car.app.menu.id";
        public static final String KEY_LEFTICON = "android.car.app.menu.leftIcon";
        public static final String KEY_REMOTEVIEWS = "android.car.app.menu.remoteViews";
        public static final String KEY_RIGHTICON = "android.car.app.menu.rightIcon";
        public static final String KEY_RIGHTTEXT = "android.car.app.menu.rightText";
        public static final String KEY_TEXT = "android.car.app.menu.text";
        public static final String KEY_TITLE = "android.car.app.menu.title";
        public static final String KEY_WIDGET = "android.car.app.menu.widget";
        public static final String KEY_WIDGET_STATE = "android.car.app.menu.widget_state";
        public static final int WIDGET_CHECKBOX = 1;
        public static final int WIDGET_TEXT_VIEW = 2;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface MenuItemFlags {
        }

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface WidgetTypes {
        }
    }
}
