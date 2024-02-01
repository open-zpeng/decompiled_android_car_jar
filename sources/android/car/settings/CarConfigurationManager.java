package android.car.settings;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.settings.ICarConfigurationManager;
import android.os.IBinder;
import android.os.RemoteException;

@SystemApi
/* loaded from: classes.dex */
public class CarConfigurationManager extends CarManagerBase {
    private static final String TAG = "CarConfigurationManager";
    private final ICarConfigurationManager mConfigurationService;

    public CarConfigurationManager(Car car, IBinder service) {
        super(car);
        this.mConfigurationService = ICarConfigurationManager.Stub.asInterface(service);
    }

    public SpeedBumpConfiguration getSpeedBumpConfiguration() {
        try {
            return this.mConfigurationService.getSpeedBumpConfiguration();
        } catch (RemoteException e) {
            return (SpeedBumpConfiguration) handleRemoteExceptionFromCarService(e, null);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.CAR_CONFIGURATION_SERVICE;
    }
}
