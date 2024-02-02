package android.car.drivingstate;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.drivingstate.ICarDrivingState;
import android.car.drivingstate.ICarDrivingStateChangeListener;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.lang.ref.WeakReference;
@SystemApi
/* loaded from: classes.dex */
public final class CarDrivingStateManager implements CarManagerBase {
    private static final boolean DBG = false;
    private static final int MSG_HANDLE_DRIVING_STATE_CHANGE = 0;
    private static final String TAG = "CarDrivingStateMgr";
    private static final boolean VDBG = false;
    private final Context mContext;
    private final ICarDrivingState mDrivingService;
    private CarDrivingStateEventListener mDrvStateEventListener;
    private final EventCallbackHandler mEventCallbackHandler;
    private CarDrivingStateChangeListenerToService mListenerToService;

    /* loaded from: classes.dex */
    public interface CarDrivingStateEventListener {
        void onDrivingStateChanged(CarDrivingStateEvent carDrivingStateEvent);
    }

    public CarDrivingStateManager(IBinder service, Context context, Handler handler) {
        this.mContext = context;
        this.mDrivingService = ICarDrivingState.Stub.asInterface(service);
        this.mEventCallbackHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    @Override // android.car.CarManagerBase
    public synchronized void onCarDisconnected() {
        this.mListenerToService = null;
        this.mDrvStateEventListener = null;
    }

    public static String getServiceName() {
        return Car.CAR_DRIVING_STATE_SERVICE;
    }

    public synchronized void registerListener(CarDrivingStateEventListener listener) throws CarNotConnectedException, IllegalArgumentException {
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
            Log.e(TAG, "Could not register a listener to Driving State Service " + e);
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Could not register a listener to Driving State Service " + e2);
            Car.checkCarNotConnectedExceptionFromCarService(e2);
        }
    }

    public synchronized void unregisterListener() throws CarNotConnectedException {
        if (this.mDrvStateEventListener == null) {
            return;
        }
        try {
            this.mDrivingService.unregisterDrivingStateChangeListener(this.mListenerToService);
            this.mDrvStateEventListener = null;
            this.mListenerToService = null;
        } catch (RemoteException e) {
            Log.e(TAG, "Could not unregister listener from Driving State Service " + e);
            throw new CarNotConnectedException(e);
        }
    }

    public CarDrivingStateEvent getCurrentCarDrivingState() throws CarNotConnectedException {
        try {
            return this.mDrivingService.getCurrentDrivingState();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not get current driving state " + e);
            throw new CarNotConnectedException(e);
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
        this.mEventCallbackHandler.sendMessage(this.mEventCallbackHandler.obtainMessage(0, event));
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
