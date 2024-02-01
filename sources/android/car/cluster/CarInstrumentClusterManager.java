package android.car.cluster;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

@SystemApi
@Deprecated
/* loaded from: classes.dex */
public class CarInstrumentClusterManager extends CarManagerBase {
    @SystemApi
    public static final String CATEGORY_NAVIGATION = "android.car.cluster.NAVIGATION";
    @SystemApi
    public static final String KEY_EXTRA_ACTIVITY_STATE = "android.car.cluster.ClusterActivityState";

    @SystemApi
    @Deprecated
    /* loaded from: classes.dex */
    public interface Callback {
        void onClusterActivityStateChanged(String str, Bundle bundle);
    }

    @SystemApi
    public void startActivity(Intent intent) {
    }

    @SystemApi
    public void registerCallback(String category, Callback callback) {
    }

    @SystemApi
    public void unregisterCallback(Callback callback) {
    }

    public CarInstrumentClusterManager(Car car, IBinder service) {
        super(car);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.CAR_INSTRUMENT_CLUSTER_SERVICE;
    }
}
