package android.car.drivingstate;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.drivingstate.ICarDrivingState;
import android.car.drivingstate.ICarDrivingStateChangeListener;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import java.lang.ref.WeakReference;

@SystemApi
/* loaded from: classes.dex */
public final class CarDrivingStateManager extends CarManagerBase {
    private static final boolean DBG = false;
    private static final int MSG_HANDLE_DRIVING_STATE_CHANGE = 0;
    private static final String TAG = "CarDrivingStateMgr";
    private static final boolean VDBG = false;
    private final ICarDrivingState mDrivingService;
    private CarDrivingStateEventListener mDrvStateEventListener;
    private final EventCallbackHandler mEventCallbackHandler;
    private CarDrivingStateChangeListenerToService mListenerToService;

    @SystemApi
    /* loaded from: classes.dex */
    public interface CarDrivingStateEventListener {
        void onDrivingStateChanged(CarDrivingStateEvent carDrivingStateEvent);
    }

    public CarDrivingStateManager(Car car, IBinder service) {
        super(car);
        this.mDrivingService = ICarDrivingState.Stub.asInterface(service);
        this.mEventCallbackHandler = new EventCallbackHandler(this, getEventHandler().getLooper());
    }

    @Override // android.car.CarManagerBase
    public synchronized void onCarDisconnected() {
        this.mListenerToService = null;
        this.mDrvStateEventListener = null;
    }

    public static String getServiceName() {
        return Car.CAR_DRIVING_STATE_SERVICE;
    }

    @SystemApi
    public synchronized void registerListener(CarDrivingStateEventListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        if (this.mDrvStateEventListener != null) {
            return;
        }
        this.mDrvStateEventListener = listener;
        try {
            if (this.mListenerToService == null) {
                this.mListenerToService = new CarDrivingStateChangeListenerToService(this);
            }
            this.mDrivingService.registerDrivingStateChangeListener(this.mListenerToService);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    @SystemApi
    public synchronized void unregisterListener() {
        if (this.mDrvStateEventListener == null) {
            return;
        }
        try {
            this.mDrivingService.unregisterDrivingStateChangeListener(this.mListenerToService);
            this.mDrvStateEventListener = null;
            this.mListenerToService = null;
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    @SystemApi
    public CarDrivingStateEvent getCurrentCarDrivingState() {
        try {
            return this.mDrivingService.getCurrentDrivingState();
        } catch (RemoteException e) {
            return (CarDrivingStateEvent) handleRemoteExceptionFromCarService(e, null);
        }
    }

    public void injectDrivingState(int drivingState) {
        CarDrivingStateEvent event = new CarDrivingStateEvent(drivingState, SystemClock.elapsedRealtimeNanos());
        try {
            this.mDrivingService.injectDrivingState(event);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    /* loaded from: classes.dex */
    private static class CarDrivingStateChangeListenerToService extends ICarDrivingStateChangeListener.Stub {
        private final WeakReference<CarDrivingStateManager> mDrvStateMgr;

        public CarDrivingStateChangeListenerToService(CarDrivingStateManager manager) {
            this.mDrvStateMgr = new WeakReference<>(manager);
        }

        @Override // android.car.drivingstate.ICarDrivingStateChangeListener
        public void onDrivingStateChanged(CarDrivingStateEvent event) {
            CarDrivingStateManager manager = this.mDrvStateMgr.get();
            if (manager != null) {
                manager.handleDrivingStateChanged(event);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDrivingStateChanged(CarDrivingStateEvent event) {
        EventCallbackHandler eventCallbackHandler = this.mEventCallbackHandler;
        eventCallbackHandler.sendMessage(eventCallbackHandler.obtainMessage(0, event));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class EventCallbackHandler extends Handler {
        private final WeakReference<CarDrivingStateManager> mDrvStateMgr;

        public EventCallbackHandler(CarDrivingStateManager manager, Looper looper) {
            super(looper);
            this.mDrvStateMgr = new WeakReference<>(manager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            CarDrivingStateManager mgr = this.mDrvStateMgr.get();
            if (mgr != null) {
                mgr.dispatchDrivingStateChangeToClient((CarDrivingStateEvent) msg.obj);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchDrivingStateChangeToClient(CarDrivingStateEvent event) {
        CarDrivingStateEventListener listener;
        if (event == null) {
            return;
        }
        synchronized (this) {
            listener = this.mDrvStateEventListener;
        }
        if (listener != null) {
            listener.onDrivingStateChanged(event);
        }
    }
}
