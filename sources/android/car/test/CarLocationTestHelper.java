package android.car.test;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/* loaded from: classes.dex */
public class CarLocationTestHelper {
    public static boolean injectLocation(Location location, Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return locationManager.injectLocation(location);
    }
}
