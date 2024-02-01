package android.car.diagnostic;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarLibLog;
import android.car.CarManagerBase;
import android.car.diagnostic.ICarDiagnostic;
import android.car.diagnostic.ICarDiagnosticEventListener;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.internal.CarPermission;
import com.android.car.internal.CarRatedListeners;
import com.android.car.internal.SingleMessageHandler;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SystemApi
/* loaded from: classes.dex */
public final class AospCarDiagnosticManager extends CarManagerBase {
    public static final int[] FRAME_TYPES = {0, 1};
    public static final int FRAME_TYPE_FREEZE = 1;
    public static final int FRAME_TYPE_LIVE = 0;
    private static final int MSG_DIAGNOSTIC_EVENTS = 0;
    private final SparseArray<CarDiagnosticListeners> mActiveListeners;
    private final SingleMessageHandler<CarDiagnosticEvent> mHandlerCallback;
    private final CarDiagnosticEventListenerToService mListenerToService;
    private final ICarDiagnostic mService;
    private final CarPermission mVendorExtensionPermission;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FrameType {
    }

    /* loaded from: classes.dex */
    public interface OnDiagnosticEventListener {
        void onDiagnosticEvent(CarDiagnosticEvent carDiagnosticEvent);
    }

    public AospCarDiagnosticManager(Car car, IBinder service) {
        super(car);
        this.mActiveListeners = new SparseArray<>();
        this.mService = ICarDiagnostic.Stub.asInterface(service);
        this.mHandlerCallback = new SingleMessageHandler<CarDiagnosticEvent>(getEventHandler().getLooper(), 0) { // from class: android.car.diagnostic.AospCarDiagnosticManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.android.car.internal.SingleMessageHandler
            public void handleEvent(CarDiagnosticEvent event) {
                CarDiagnosticListeners listeners;
                synchronized (AospCarDiagnosticManager.this.mActiveListeners) {
                    listeners = (CarDiagnosticListeners) AospCarDiagnosticManager.this.mActiveListeners.get(event.frameType);
                }
                if (listeners != null) {
                    listeners.onDiagnosticEvent(event);
                }
            }
        };
        this.mVendorExtensionPermission = new CarPermission(getContext(), Car.PERMISSION_VENDOR_EXTENSION);
        this.mListenerToService = new CarDiagnosticEventListenerToService(this);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        synchronized (this.mActiveListeners) {
            this.mActiveListeners.clear();
        }
    }

    public static String getServiceName() {
        return "diagnostic";
    }

    private void assertFrameType(int frameType) {
        if (frameType == 0 || frameType == 1) {
            return;
        }
        throw new IllegalArgumentException(String.format("%d is not a valid diagnostic frame type", Integer.valueOf(frameType)));
    }

    public boolean registerListener(OnDiagnosticEventListener listener, int frameType, int rate) {
        assertFrameType(frameType);
        synchronized (this.mActiveListeners) {
            boolean needsServerUpdate = false;
            CarDiagnosticListeners listeners = this.mActiveListeners.get(frameType);
            if (listeners == null) {
                listeners = new CarDiagnosticListeners(rate);
                this.mActiveListeners.put(frameType, listeners);
                needsServerUpdate = true;
            }
            if (listeners.addAndUpdateRate(listener, rate)) {
                needsServerUpdate = true;
            }
            if (needsServerUpdate && !registerOrUpdateDiagnosticListener(frameType, rate)) {
                return false;
            }
            return true;
        }
    }

    public void unregisterListener(OnDiagnosticEventListener listener) {
        int[] iArr;
        synchronized (this.mActiveListeners) {
            for (int frameType : FRAME_TYPES) {
                doUnregisterListenerLocked(listener, frameType);
            }
        }
    }

    private void doUnregisterListenerLocked(OnDiagnosticEventListener listener, int frameType) {
        CarDiagnosticListeners listeners = this.mActiveListeners.get(frameType);
        if (listeners != null) {
            boolean needsServerUpdate = false;
            if (listeners.contains(listener)) {
                needsServerUpdate = listeners.remove(listener);
            }
            if (listeners.isEmpty()) {
                try {
                    this.mService.unregisterDiagnosticListener(frameType, this.mListenerToService);
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                }
                this.mActiveListeners.remove(frameType);
            } else if (needsServerUpdate) {
                registerOrUpdateDiagnosticListener(frameType, listeners.getRate());
            }
        }
    }

    private boolean registerOrUpdateDiagnosticListener(int frameType, int rate) {
        try {
            return this.mService.registerOrUpdateDiagnosticListener(frameType, rate, this.mListenerToService);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public CarDiagnosticEvent getLatestLiveFrame() {
        try {
            return this.mService.getLatestLiveFrame();
        } catch (RemoteException e) {
            return (CarDiagnosticEvent) handleRemoteExceptionFromCarService(e, null);
        }
    }

    public long[] getFreezeFrameTimestamps() {
        try {
            return this.mService.getFreezeFrameTimestamps();
        } catch (RemoteException e) {
            return (long[]) handleRemoteExceptionFromCarService(e, new long[0]);
        }
    }

    public CarDiagnosticEvent getFreezeFrame(long timestamp) {
        try {
            return this.mService.getFreezeFrame(timestamp);
        } catch (RemoteException e) {
            return (CarDiagnosticEvent) handleRemoteExceptionFromCarService(e, null);
        }
    }

    public boolean clearFreezeFrames(long... timestamps) {
        try {
            return this.mService.clearFreezeFrames(timestamps);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean isLiveFrameSupported() {
        try {
            return this.mService.isLiveFrameSupported();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean isFreezeFrameNotificationSupported() {
        try {
            return this.mService.isFreezeFrameNotificationSupported();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean isGetFreezeFrameSupported() {
        try {
            return this.mService.isGetFreezeFrameSupported();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean isClearFreezeFramesSupported() {
        try {
            return this.mService.isClearFreezeFramesSupported();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean isSelectiveClearFreezeFramesSupported() {
        try {
            return this.mService.isSelectiveClearFreezeFramesSupported();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CarDiagnosticEventListenerToService extends ICarDiagnosticEventListener.Stub {
        private final WeakReference<AospCarDiagnosticManager> mManager;

        public CarDiagnosticEventListenerToService(AospCarDiagnosticManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        private void handleOnDiagnosticEvents(AospCarDiagnosticManager manager, List<CarDiagnosticEvent> events) {
            manager.mHandlerCallback.sendEvents(events);
        }

        @Override // android.car.diagnostic.ICarDiagnosticEventListener
        public void onDiagnosticEvents(List<CarDiagnosticEvent> events) {
            AospCarDiagnosticManager manager = this.mManager.get();
            if (manager != null) {
                handleOnDiagnosticEvents(manager, events);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CarDiagnosticListeners extends CarRatedListeners<OnDiagnosticEventListener> {
        CarDiagnosticListeners(int rate) {
            super(rate);
        }

        void onDiagnosticEvent(CarDiagnosticEvent event) {
            final CarDiagnosticEvent eventToDispatch;
            List<OnDiagnosticEventListener> listeners;
            long updateTime = event.timestamp;
            if (updateTime < this.mLastUpdateTime) {
                Log.w(CarLibLog.TAG_DIAGNOSTIC, "dropping old data");
                return;
            }
            this.mLastUpdateTime = updateTime;
            boolean hasVendorExtensionPermission = AospCarDiagnosticManager.this.mVendorExtensionPermission.checkGranted();
            if (hasVendorExtensionPermission) {
                eventToDispatch = event;
            } else {
                eventToDispatch = event.withVendorSensorsRemoved();
            }
            synchronized (AospCarDiagnosticManager.this.mActiveListeners) {
                listeners = new ArrayList<>(getListeners());
            }
            listeners.forEach(new Consumer<OnDiagnosticEventListener>() { // from class: android.car.diagnostic.AospCarDiagnosticManager.CarDiagnosticListeners.1
                @Override // java.util.function.Consumer
                public void accept(OnDiagnosticEventListener listener) {
                    listener.onDiagnosticEvent(eventToDispatch);
                }
            });
        }
    }
}
