package android.car.intelligent;

import android.car.Car;
import android.car.CarManagerBase;
import android.car.intelligent.ICarDrivingSceneListener;
import android.car.intelligent.ICarIntelligentEngine;
import android.car.intelligent.ICarSceneListener;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Slog;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public final class CarIntelligentEngineManager extends CarManagerBase {
    public static final int CAR_DRIVING_SCENE_EXIT_REASON_AVH_ACTIVE = 4;
    public static final int CAR_DRIVING_SCENE_EXIT_REASON_GEAR_P_N = 1;
    public static final int CAR_DRIVING_SCENE_EXIT_REASON_NORMAL = 0;
    public static final int CAR_DRIVING_SCENE_EXIT_REASON_RADAR = 2;
    public static final int CAR_DRIVING_SCENE_EXIT_REASON_SPEED = 3;
    public static final int CAR_DRIVING_SCENE_NRA_LEVEL_0 = 0;
    public static final int CAR_DRIVING_SCENE_NRA_LEVEL_1 = 1;
    public static final int CAR_DRIVING_SCENE_NRA_LEVEL_2 = 2;
    public static final int CAR_DRIVING_SCENE_NRA_LEVEL_3 = 3;
    private static final boolean DBG = false;
    private static final String TAG = "CarIntelligentEngine";
    private final CarDrivingSceneListenerToService mCarDrivingSceneListenerToService;
    private final List<CarDrivingSceneListener> mCarDrivingSceneListeners;
    private final CarWelcomeSceneListenerToService mCarWelcomeSceneListenerToService;
    private final List<CarWelcomeSceneListener> mCarWelcomeSceneListeners;
    private final Handler mMainHandler;
    private final ICarIntelligentEngine mService;

    /* loaded from: classes.dex */
    public interface CarDrivingSceneListener {
        void onCarDrivingSceneChanged(CarSceneEvent carSceneEvent);
    }

    /* loaded from: classes.dex */
    public interface CarSceneListener extends CarWelcomeSceneListener {
    }

    /* loaded from: classes.dex */
    public interface CarWelcomeSceneListener {
        void onWelcomeSceneChanged(CarSceneEvent carSceneEvent);
    }

    public CarIntelligentEngineManager(Car car, IBinder service) {
        super(car);
        this.mCarDrivingSceneListeners = new CopyOnWriteArrayList();
        this.mCarWelcomeSceneListeners = new CopyOnWriteArrayList();
        this.mService = ICarIntelligentEngine.Stub.asInterface(service);
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mCarWelcomeSceneListenerToService = new CarWelcomeSceneListenerToService(this);
        this.mCarDrivingSceneListenerToService = new CarDrivingSceneListenerToService(this);
    }

    @Override // android.car.CarManagerBase
    public synchronized void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.CAR_INTELLIGENT_SERVICE;
    }

    public synchronized void registerCarSceneListener(CarSceneListener listener) {
        registerCarWelcomeSceneListener(listener);
    }

    public synchronized void registerCarWelcomeSceneListener(CarWelcomeSceneListener listener) {
        try {
            if (listener == null) {
                Slog.e(TAG, "registerCarWelcomeSceneListener(): null listener");
                throw new IllegalArgumentException("listener is null");
            }
            synchronized (this.mCarWelcomeSceneListeners) {
                if (this.mCarWelcomeSceneListeners.isEmpty()) {
                    try {
                        this.mService.registerCarSceneListener(this.mCarWelcomeSceneListenerToService);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                if (!this.mCarWelcomeSceneListeners.contains(listener)) {
                    this.mCarWelcomeSceneListeners.add(listener);
                }
                Slog.i(TAG, "registerCarWelcomeSceneListener: listener=" + listener + " mListeners.size=" + this.mCarWelcomeSceneListeners.size());
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void unregisterCarSceneListener() {
        for (CarWelcomeSceneListener listener : this.mCarWelcomeSceneListeners) {
            unregisterCarWelcomeSceneListener(listener);
        }
    }

    public synchronized void unregisterCarWelcomeSceneListener(CarWelcomeSceneListener listener) {
        try {
            if (listener == null) {
                Slog.w(TAG, "unregisterCarWelcomeSceneListener listener is null");
                throw new IllegalArgumentException("listener is null");
            }
            synchronized (this.mCarWelcomeSceneListeners) {
                this.mCarWelcomeSceneListeners.remove(listener);
                if (this.mCarWelcomeSceneListeners.isEmpty()) {
                    try {
                        this.mService.unregisterCarDrivingSceneListener(this.mCarDrivingSceneListenerToService);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                Slog.i(TAG, "unregisterCarWelcomeSceneListener: listener=" + listener + " mListeners.size=" + this.mCarWelcomeSceneListeners.size());
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void registerCarDrivingSceneListener(CarDrivingSceneListener listener) {
        try {
            if (listener == null) {
                Slog.w(TAG, "registerCarDrivingSceneListener(): null listener");
                throw new IllegalArgumentException("listener is null");
            }
            synchronized (this.mCarDrivingSceneListeners) {
                if (this.mCarDrivingSceneListeners.isEmpty()) {
                    try {
                        this.mService.registerCarDrivingSceneListener(this.mCarDrivingSceneListenerToService);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                if (!this.mCarDrivingSceneListeners.contains(listener)) {
                    this.mCarDrivingSceneListeners.add(listener);
                }
                Slog.i(TAG, "registerCarDrivingSceneListener: listener=" + listener + " mListeners.size=" + this.mCarDrivingSceneListeners.size());
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void unregisterCarDrivingSceneListener(CarDrivingSceneListener listener) {
        try {
            if (listener == null) {
                Slog.w(TAG, "unregisterCarDrivingSceneListener listener is null");
                throw new IllegalArgumentException("listener is null");
            }
            synchronized (this.mCarDrivingSceneListeners) {
                this.mCarDrivingSceneListeners.remove(listener);
                if (this.mCarDrivingSceneListeners.isEmpty()) {
                    try {
                        this.mService.unregisterCarDrivingSceneListener(this.mCarDrivingSceneListenerToService);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                Slog.i(TAG, "unregisterCarDrivingSceneListener: listener=" + listener + " mListeners.size=" + this.mCarDrivingSceneListeners.size());
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void setCarDrivingSceneNRALevel(int level) {
        if (level < 0 || level > 3) {
            throw new IllegalArgumentException("level value invalid!");
        }
        try {
            this.mService.setCarDrivingSceneNRALevel(level);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public synchronized int getCarDrivingSceneNRALevel() {
        try {
        } catch (RemoteException e) {
            e.printStackTrace();
            return 2;
        }
        return this.mService.getCarDrivingSceneNRALevel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CarWelcomeSceneListenerToService extends ICarSceneListener.Stub {
        private final WeakReference<CarIntelligentEngineManager> mCarIntelEngMgr;

        CarWelcomeSceneListenerToService(CarIntelligentEngineManager manager) {
            this.mCarIntelEngMgr = new WeakReference<>(manager);
        }

        @Override // android.car.intelligent.ICarSceneListener
        public void onWelcomeSceneChanged(CarSceneEvent event) {
            Slog.i(CarIntelligentEngineManager.TAG, "onWelcomeSceneChanged() event=" + event);
            CarIntelligentEngineManager manager = this.mCarIntelEngMgr.get();
            if (manager != null) {
                manager.handleWelcomeSceneChanged(event);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CarDrivingSceneListenerToService extends ICarDrivingSceneListener.Stub {
        private final WeakReference<CarIntelligentEngineManager> mCarIntelEngMgr;

        CarDrivingSceneListenerToService(CarIntelligentEngineManager manager) {
            this.mCarIntelEngMgr = new WeakReference<>(manager);
        }

        @Override // android.car.intelligent.ICarDrivingSceneListener
        public void onCarDrivingSceneChanged(CarSceneEvent event) {
            Slog.i(CarIntelligentEngineManager.TAG, "onCarDrivingSceneChanged() event=" + event);
            CarIntelligentEngineManager manager = this.mCarIntelEngMgr.get();
            if (manager != null) {
                manager.handleCarDrivingSceneChanged(event);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWelcomeSceneChanged(final CarSceneEvent event) {
        this.mMainHandler.post(new Runnable() { // from class: android.car.intelligent.-$$Lambda$CarIntelligentEngineManager$ov67k4jIjg0P1XFyDw2QX1W4tMo
            @Override // java.lang.Runnable
            public final void run() {
                CarIntelligentEngineManager.this.lambda$handleWelcomeSceneChanged$0$CarIntelligentEngineManager(event);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCarDrivingSceneChanged(final CarSceneEvent event) {
        this.mMainHandler.post(new Runnable() { // from class: android.car.intelligent.-$$Lambda$CarIntelligentEngineManager$SbBuKgyofaUkhPkZx-nxhoK9rjs
            @Override // java.lang.Runnable
            public final void run() {
                CarIntelligentEngineManager.this.lambda$handleCarDrivingSceneChanged$1$CarIntelligentEngineManager(event);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dispatchEventToCarWelcomeSceneClients */
    public void lambda$handleWelcomeSceneChanged$0$CarIntelligentEngineManager(CarSceneEvent event) {
        if (event != null && !this.mCarWelcomeSceneListeners.isEmpty()) {
            Slog.i(TAG, "dispatchEventToCarWelcomeSceneClients: client:" + this.mCarWelcomeSceneListeners.size());
            for (CarWelcomeSceneListener listener : this.mCarWelcomeSceneListeners) {
                if (listener != null) {
                    listener.onWelcomeSceneChanged(event);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dispatchEventToCarDrivingSceneClients */
    public void lambda$handleCarDrivingSceneChanged$1$CarIntelligentEngineManager(CarSceneEvent event) {
        if (event != null && !this.mCarDrivingSceneListeners.isEmpty()) {
            Slog.i(TAG, "dispatchEventToCarDrivingSceneClients: client:" + this.mCarDrivingSceneListeners.size());
            for (CarDrivingSceneListener listener : this.mCarDrivingSceneListeners) {
                if (listener != null) {
                    listener.onCarDrivingSceneChanged(event);
                }
            }
        }
    }
}
