package android.car.test;

import android.car.Car;
import android.car.CarManagerBase;
import android.os.IBinder;

/* loaded from: classes.dex */
public class CarTestManagerBinderWrapper extends CarManagerBase {
    public final IBinder binder;

    public CarTestManagerBinderWrapper(IBinder binder) {
        super(null);
        this.binder = binder;
    }

    public CarTestManagerBinderWrapper(Car car, IBinder binder) {
        super(car);
        this.binder = binder;
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.TEST_SERVICE;
    }
}
