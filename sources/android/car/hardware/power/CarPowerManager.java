package android.car.hardware.power;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.power.ICarPower;
import android.car.hardware.power.ICarPowerStateListener;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.Slog;
import com.android.internal.annotations.GuardedBy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
@SystemApi
/* loaded from: classes.dex */
public class CarPowerManager implements CarManagerBase {
    public static final int BOOT_REASON_DOOR_OPEN = 4;
    public static final int BOOT_REASON_DOOR_UNLOCK = 2;
    public static final int BOOT_REASON_REMOTE_START = 5;
    public static final int BOOT_REASON_TIMER = 3;
    public static final int BOOT_REASON_USER_POWER_ON = 1;
    private static final boolean DBG = false;
    private static final String TAG = "CarPowerManager";
    private Executor mExecutor;
    private CarPowerStateListener mListener;
    @GuardedBy("mLock")
    private ICarPowerStateListener mListenerToService;
    private final Object mLock = new Object();
    private final ICarPower mService;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface BootReason {
    }

    /* loaded from: classes.dex */
    public interface CarPowerStateListener {
        public static final int SHUTDOWN_CANCELLED = 0;
        public static final int SHUTDOWN_ENTER = 1;
        public static final int SUSPEND_ENTER = 2;
        public static final int SUSPEND_EXIT = 3;

        void onStateChanged(int i);
    }

    public CarPowerManager(IBinder service, Context context, Handler handler) {
        this.mService = ICarPower.Stub.asInterface(service);
    }

    public int getBootReason() throws CarNotConnectedException {
        try {
            return this.mService.getBootReason();
        } catch (RemoteException e) {
            Log.e(TAG, "Exception in getBootReason", e);
            throw new CarNotConnectedException(e);
        }
    }

    public int getCarBatteryState() throws CarNotConnectedException {
        try {
            return this.mService.getCarBatteryState();
        } catch (RemoteException e) {
            Log.e(TAG, "Exception in getBootReason", e);
            throw new CarNotConnectedException(e);
        }
    }

    public void requestShutdownOnNextSuspend() throws CarNotConnectedException {
        try {
            this.mService.requestShutdownOnNextSuspend();
        } catch (RemoteException e) {
            Log.e(TAG, "Exception in requestShutdownOnNextSuspend", e);
            throw new CarNotConnectedException(e);
        }
    }

    public void setListener(CarPowerStateListener listener, Executor executor) throws CarNotConnectedException, IllegalStateException {
        synchronized (this.mLock) {
            if (this.mListenerToService == null) {
                ICarPowerStateListener listenerToService = new ICarPowerStateListener.Stub() { // from class: android.car.hardware.power.CarPowerManager.1
                    @Override // android.car.hardware.power.ICarPowerStateListener
                    public void onStateChanged(int state, int token) throws RemoteException {
                        Slog.d(CarPowerManager.TAG, "onStateChanged, state---->" + state);
                        CarPowerManager.this.handleEvent(state, token);
                    }
                };
                try {
                    this.mService.registerListener(listenerToService);
                    this.mListenerToService = listenerToService;
                } catch (RemoteException ex) {
                    Log.e(TAG, "Could not connect: ", ex);
                    throw new CarNotConnectedException(ex);
                } catch (IllegalStateException ex2) {
                    Car.checkCarNotConnectedExceptionFromCarService(ex2);
                }
            }
            if (this.mExecutor == null && this.mListener == null) {
                this.mExecutor = executor;
                this.mListener = listener;
            } else {
                throw new IllegalStateException("Listener must be cleared first");
            }
        }
    }

    public void clearListener() {
        ICarPowerStateListener listenerToService;
        synchronized (this.mLock) {
            listenerToService = this.mListenerToService;
            this.mListenerToService = null;
            this.mListener = null;
            this.mExecutor = null;
        }
        if (listenerToService == null) {
            Log.w(TAG, "unregisterListener: listener was not registered");
            return;
        }
        try {
            Slog.i(TAG, "clearListener");
            this.mService.unregisterListener(listenerToService);
        } catch (RemoteException ex) {
            Log.e(TAG, "Failed to unregister listener", ex);
        } catch (IllegalStateException ex2) {
            Car.hideCarNotConnectedExceptionFromCarService(ex2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEvent(final int state, final int token) {
        Executor executor;
        synchronized (this.mLock) {
            executor = this.mExecutor;
        }
        if (executor != null) {
            executor.execute(new Runnable() { // from class: android.car.hardware.power.-$$Lambda$CarPowerManager$b20g9ldGXVMVhvtrNO8ZrWsD7O4
                @Override // java.lang.Runnable
                public final void run() {
                    CarPowerManager.this.handleEventInternal(state, token);
                }
            });
        } else {
            handleEventInternal(state, token);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEventInternal(int state, int token) {
        this.mListener.onStateChanged(state);
        if (state == 1 || state == 2) {
            try {
                this.mService.finished(this.mListenerToService, token);
            } catch (RemoteException e) {
                Log.e(TAG, "Exception in finished", e);
            }
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        ICarPowerStateListener listenerToService;
        synchronized (this.mLock) {
            listenerToService = this.mListenerToService;
        }
        if (listenerToService != null) {
            clearListener();
        }
    }

    public static String getServiceName() {
        return Car.POWER_SERVICE;
    }
}
