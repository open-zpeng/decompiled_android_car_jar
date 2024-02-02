package android.car.navigation;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarApiUtil;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.cluster.renderer.IInstrumentClusterNavigation;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
@SystemApi
/* loaded from: classes.dex */
public final class CarNavigationStatusManager implements CarManagerBase {
    private static final String TAG = "CAR.L.NAV";
    private final IInstrumentClusterNavigation mService;

    public CarNavigationStatusManager(IBinder service) {
        this.mService = IInstrumentClusterNavigation.Stub.asInterface(service);
    }

    public void sendEvent(int eventType, Bundle bundle) throws CarNotConnectedException {
        try {
            this.mService.onEvent(eventType, bundle);
        } catch (RemoteException e) {
            handleCarServiceRemoteExceptionAndThrow(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.CAR_NAVIGATION_SERVICE;
    }

    public CarNavigationInstrumentCluster getInstrumentClusterInfo() throws CarNotConnectedException {
        try {
            return this.mService.getInstrumentClusterInfo();
        } catch (RemoteException e) {
            handleCarServiceRemoteExceptionAndThrow(e);
            return null;
        }
    }

    private void handleCarServiceRemoteExceptionAndThrow(RemoteException e) throws CarNotConnectedException {
        handleCarServiceRemoteException(e);
        throw new CarNotConnectedException();
    }

    private void handleCarServiceRemoteException(RemoteException e) {
        Log.w("CAR.L.NAV", "RemoteException from car service:" + e.getMessage());
    }
}
