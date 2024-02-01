package android.car.drivingstate;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.drivingstate.ICarUxRestrictionsChangeListener;
import android.car.drivingstate.ICarUxRestrictionsManager;
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
public final class CarUxRestrictionsManager implements CarManagerBase {
    private static final boolean DBG = false;
    private static final int MSG_HANDLE_UX_RESTRICTIONS_CHANGE = 0;
    private static final String TAG = "CarUxRManager";
    private static final boolean VDBG = false;
    private final Context mContext;
    private final EventCallbackHandler mEventCallbackHandler;
    private CarUxRestrictionsChangeListenerToService mListenerToService;
    private OnUxRestrictionsChangedListener mUxRListener;
    private final ICarUxRestrictionsManager mUxRService;

    /* loaded from: classes.dex */
    public interface OnUxRestrictionsChangedListener {
        void onUxRestrictionsChanged(CarUxRestrictions carUxRestrictions);
    }

    /* renamed from: android.car.drivingstate.CarUxRestrictionsManager$onUxRestrictionsChangedListener  reason: case insensitive filesystem */
    /* loaded from: classes.dex */
    public interface InterfaceC0000onUxRestrictionsChangedListener {
        void dummy();

        void onUxRestrictionsChanged(CarUxRestrictions carUxRestrictions);
    }

    public CarUxRestrictionsManager(IBinder service, Context context, Handler handler) {
        this.mContext = context;
        this.mUxRService = ICarUxRestrictionsManager.Stub.asInterface(service);
        this.mEventCallbackHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    @Override // android.car.CarManagerBase
    public synchronized void onCarDisconnected() {
        this.mListenerToService = null;
        this.mUxRListener = null;
    }

    public static String getServiceName() {
        return Car.CAR_UX_RESTRICTION_SERVICE;
    }

    public synchronized void registerListener(OnUxRestrictionsChangedListener listener) throws CarNotConnectedException, IllegalArgumentException {
        if (listener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        if (this.mUxRListener != null) {
            return;
        }
        this.mUxRListener = listener;
        try {
            if (this.mListenerToService == null) {
                this.mListenerToService = new CarUxRestrictionsChangeListenerToService(this);
            }
            this.mUxRService.registerUxRestrictionsChangeListener(this.mListenerToService);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not register a listener to CarUxRestrictionsManagerService " + e);
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Could not register a listener to CarUxRestrictionsManagerService " + e2);
            Car.checkCarNotConnectedExceptionFromCarService(e2);
        }
    }

    public synchronized void unregisterListener() throws CarNotConnectedException {
        if (this.mUxRListener == null) {
            return;
        }
        try {
            this.mUxRService.unregisterUxRestrictionsChangeListener(this.mListenerToService);
            this.mUxRListener = null;
        } catch (RemoteException e) {
            Log.e(TAG, "Could not unregister listener from Driving State Service " + e);
            throw new CarNotConnectedException(e);
        }
    }

    public CarUxRestrictions getCurrentCarUxRestrictions() throws CarNotConnectedException {
        try {
            return this.mUxRService.getCurrentUxRestrictions();
        } catch (RemoteException e) {
            Log.e(TAG, "Could not get current UX restrictions " + e);
            throw new CarNotConnectedException(e);
        }
    }

    /* loaded from: classes.dex */
    private static class CarUxRestrictionsChangeListenerToService extends ICarUxRestrictionsChangeListener.Stub {
        private final WeakReference<CarUxRestrictionsManager> mUxRestrictionsManager;

        public CarUxRestrictionsChangeListenerToService(CarUxRestrictionsManager manager) {
            this.mUxRestrictionsManager = new WeakReference<>(manager);
        }

        @Override // android.car.drivingstate.ICarUxRestrictionsChangeListener
        public void onUxRestrictionsChanged(CarUxRestrictions restrictionInfo) {
            CarUxRestrictionsManager manager = this.mUxRestrictionsManager.get();
            if (manager != null) {
                manager.handleUxRestrictionsChanged(restrictionInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUxRestrictionsChanged(CarUxRestrictions restrictionInfo) {
        this.mEventCallbackHandler.sendMessage(this.mEventCallbackHandler.obtainMessage(0, restrictionInfo));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class EventCallbackHandler extends Handler {
        private final WeakReference<CarUxRestrictionsManager> mUxRestrictionsManager;

        public EventCallbackHandler(CarUxRestrictionsManager manager, Looper looper) {
            super(looper);
            this.mUxRestrictionsManager = new WeakReference<>(manager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            CarUxRestrictionsManager mgr = this.mUxRestrictionsManager.get();
            if (mgr != null) {
                mgr.dispatchUxRChangeToClient((CarUxRestrictions) msg.obj);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchUxRChangeToClient(CarUxRestrictions restrictionInfo) {
        OnUxRestrictionsChangedListener listener;
        if (restrictionInfo == null) {
            return;
        }
        synchronized (this) {
            listener = this.mUxRListener;
        }
        if (listener != null) {
            listener.onUxRestrictionsChanged(restrictionInfo);
        }
    }

    public synchronized void registerListener(InterfaceC0000onUxRestrictionsChangedListener listener) throws CarNotConnectedException, IllegalArgumentException {
    }
}
