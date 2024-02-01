package android.car.content.pm;

import android.annotation.SystemApi;
import android.app.Service;
import android.car.Car;
import android.car.content.pm.ICarAppBlockingPolicy;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

@SystemApi
/* loaded from: classes.dex */
public abstract class CarAppBlockingPolicyService extends Service {
    public static final String SERVICE_INTERFACE = "android.car.content.pm.CarAppBlockingPolicyService";
    private static final String TAG = CarAppBlockingPolicyService.class.getSimpleName();
    private final ICarAppBlockingPolicyImpl mBinder = new ICarAppBlockingPolicyImpl();
    private Handler mHandler;

    protected abstract CarAppBlockingPolicy getAppBlockingPolicy();

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        stopSelf();
        return false;
    }

    /* loaded from: classes.dex */
    private class ICarAppBlockingPolicyImpl extends ICarAppBlockingPolicy.Stub {
        private ICarAppBlockingPolicyImpl() {
        }

        @Override // android.car.content.pm.ICarAppBlockingPolicy
        public void setAppBlockingPolicySetter(ICarAppBlockingPolicySetter setter) {
            Log.i(CarAppBlockingPolicyService.TAG, "setAppBlockingPolicySetter will set policy");
            CarAppBlockingPolicy policy = CarAppBlockingPolicyService.this.getAppBlockingPolicy();
            try {
                setter.setAppBlockingPolicy(policy);
            } catch (RemoteException e) {
                Car.handleRemoteExceptionFromCarService(CarAppBlockingPolicyService.this, e);
            }
        }
    }
}
