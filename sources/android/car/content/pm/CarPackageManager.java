package android.car.content.pm;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.content.pm.ICarPackageManager;
import android.content.ComponentName;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SystemApi
/* loaded from: classes.dex */
public final class CarPackageManager extends CarManagerBase {
    @SystemApi
    public static final int FLAG_SET_POLICY_ADD = 2;
    @SystemApi
    public static final int FLAG_SET_POLICY_REMOVE = 4;
    @SystemApi
    public static final int FLAG_SET_POLICY_WAIT_FOR_CHANGE = 1;
    private static final String TAG = "CarPackageManager";
    private final ICarPackageManager mService;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SetPolicyFlags {
    }

    public CarPackageManager(Car car, IBinder service) {
        super(car);
        this.mService = ICarPackageManager.Stub.asInterface(service);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.PACKAGE_SERVICE;
    }

    @SystemApi
    public void setAppBlockingPolicy(String packageName, CarAppBlockingPolicy policy, int flags) {
        if ((flags & 1) != 0 && Looper.getMainLooper().isCurrentThread()) {
            throw new IllegalStateException("FLAG_SET_POLICY_WAIT_FOR_CHANGE cannot be used in main thread");
        }
        try {
            this.mService.setAppBlockingPolicy(packageName, policy, flags);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void restartTask(int taskId) {
        try {
            this.mService.restartTask(taskId);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    @SystemApi
    public boolean isActivityBackedBySafeActivity(ComponentName activityName) {
        try {
            return this.mService.isActivityBackedBySafeActivity(activityName);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public void setEnableActivityBlocking(boolean enable) {
        try {
            this.mService.setEnableActivityBlocking(enable);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public boolean isActivityDistractionOptimized(String packageName, String className) {
        try {
            return this.mService.isActivityDistractionOptimized(packageName, className);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean isServiceDistractionOptimized(String packageName, String className) {
        try {
            return this.mService.isServiceDistractionOptimized(packageName, className);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }
}
