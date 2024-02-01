package android.car.test;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.os.IBinder;
@SystemApi
/* loaded from: classes.dex */
public class CarTestManagerBinderWrapper implements CarManagerBase {
    public final IBinder binder;

    public CarTestManagerBinderWrapper(IBinder binder) {
        this.binder = binder;
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.TEST_SERVICE;
    }
}
