package android.car.settings;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.settings.ICarConfigurationManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
@SystemApi
/* loaded from: classes.dex */
public class CarConfigurationManager implements CarManagerBase {
    private static final String TAG = "CarConfigurationManager";
    private final ICarConfigurationManager mConfigurationService;

    public CarConfigurationManager(IBinder service) {
        this.mConfigurationService = ICarConfigurationManager.Stub.asInterface(service);
    }

    public SpeedBumpConfiguration getSpeedBumpConfiguration() throws CarNotConnectedException {
        try {
            return this.mConfigurationService.getSpeedBumpConfiguration();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not retrieve SpeedBumpConfiguration", e);
            throw new CarNotConnectedException(e);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.CAR_CONFIGURATION_SERVICE;
    }
}
