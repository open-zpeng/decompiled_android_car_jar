package android.car.drivingstate;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.drivingstate.ICarUxRestrictionsChangeListener;
import android.car.drivingstate.ICarUxRestrictionsManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Slog;
import com.android.internal.annotations.GuardedBy;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SystemApi
/* loaded from: classes.dex */
public final class CarUxRestrictionsManager extends CarManagerBase {
    private static final boolean DBG = false;
    private static final int MSG_HANDLE_UX_RESTRICTIONS_CHANGE = 0;
    private static final String TAG = "CarUxRManager";
    public static final String UX_RESTRICTION_MODE_BASELINE = "baseline";
    private static final boolean VDBG = false;
    private int mDisplayId;
    private final EventCallbackHandler mEventCallbackHandler;
    private CarUxRestrictionsChangeListenerToService mListenerToService;
    @GuardedBy({"this"})
    private OnUxRestrictionsChangedListener mUxRListener;
    private final ICarUxRestrictionsManager mUxRService;

    /* loaded from: classes.dex */
    public interface OnUxRestrictionsChangedListener {
        void onUxRestrictionsChanged(CarUxRestrictions carUxRestrictions);
    }

    public CarUxRestrictionsManager(Car car, IBinder service) {
        super(car);
        this.mDisplayId = -1;
        this.mUxRService = ICarUxRestrictionsManager.Stub.asInterface(service);
        this.mEventCallbackHandler = new EventCallbackHandler(this, getEventHandler().getLooper());
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mListenerToService = null;
        synchronized (this) {
            this.mUxRListener = null;
        }
    }

    public static String getServiceName() {
        return Car.CAR_UX_RESTRICTION_SERVICE;
    }

    public void registerListener(OnUxRestrictionsChangedListener listener) {
        registerListener(listener, getDisplayId());
    }

    public void registerListener(OnUxRestrictionsChangedListener listener, int displayId) {
        synchronized (this) {
            if (this.mUxRListener != null) {
                return;
            }
            this.mUxRListener = listener;
            try {
                if (this.mListenerToService == null) {
                    this.mListenerToService = new CarUxRestrictionsChangeListenerToService(this);
                }
                this.mUxRService.registerUxRestrictionsChangeListener(this.mListenerToService, displayId);
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public void unregisterListener() {
        synchronized (this) {
            if (this.mUxRListener == null) {
                return;
            }
            this.mUxRListener = null;
            try {
                this.mUxRService.unregisterUxRestrictionsChangeListener(this.mListenerToService);
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public boolean saveUxRestrictionsConfigurationForNextBoot(List<CarUxRestrictionsConfiguration> configs) {
        try {
            return this.mUxRService.saveUxRestrictionsConfigurationForNextBoot(configs);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public CarUxRestrictions getCurrentCarUxRestrictions() {
        return getCurrentCarUxRestrictions(getDisplayId());
    }

    public CarUxRestrictions getCurrentCarUxRestrictions(int displayId) {
        try {
            return this.mUxRService.getCurrentUxRestrictions(displayId);
        } catch (RemoteException e) {
            return (CarUxRestrictions) handleRemoteExceptionFromCarService(e, null);
        }
    }

    public boolean setRestrictionMode(String mode) {
        Objects.requireNonNull(mode, "mode must not be null");
        try {
            return this.mUxRService.setRestrictionMode(mode);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public String getRestrictionMode() {
        try {
            return this.mUxRService.getRestrictionMode();
        } catch (RemoteException e) {
            return (String) handleRemoteExceptionFromCarService(e, null);
        }
    }

    public boolean saveUxRestrictionsConfigurationForNextBoot(CarUxRestrictionsConfiguration config) {
        return saveUxRestrictionsConfigurationForNextBoot(Arrays.asList(config));
    }

    public List<CarUxRestrictionsConfiguration> getStagedConfigs() {
        try {
            return this.mUxRService.getStagedConfigs();
        } catch (RemoteException e) {
            return (List) handleRemoteExceptionFromCarService(e, null);
        }
    }

    public List<CarUxRestrictionsConfiguration> getConfigs() {
        try {
            return this.mUxRService.getConfigs();
        } catch (RemoteException e) {
            return (List) handleRemoteExceptionFromCarService(e, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CarUxRestrictionsChangeListenerToService extends ICarUxRestrictionsChangeListener.Stub {
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
        EventCallbackHandler eventCallbackHandler = this.mEventCallbackHandler;
        eventCallbackHandler.sendMessage(eventCallbackHandler.obtainMessage(0, restrictionInfo));
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
        if (restrictionInfo == null) {
            return;
        }
        synchronized (this) {
            if (this.mUxRListener != null) {
                this.mUxRListener.onUxRestrictionsChanged(restrictionInfo);
            }
        }
    }

    private int getDisplayId() {
        int i = this.mDisplayId;
        if (i != -1) {
            return i;
        }
        this.mDisplayId = getContext().getDisplayId();
        Slog.i(TAG, "Context returns display ID " + this.mDisplayId);
        if (this.mDisplayId == -1) {
            this.mDisplayId = 0;
            Slog.e(TAG, "Could not retrieve display id. Using default: " + this.mDisplayId);
        }
        return this.mDisplayId;
    }
}
