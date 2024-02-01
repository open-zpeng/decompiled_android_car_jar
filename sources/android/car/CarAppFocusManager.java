package android.car;

import android.annotation.SystemApi;
import android.car.CarAppFocusManager;
import android.car.IAppFocus;
import android.car.IAppFocusListener;
import android.car.IAppFocusOwnershipCallback;
import android.os.IBinder;
import android.os.RemoteException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SystemApi
/* loaded from: classes.dex */
public final class CarAppFocusManager extends CarManagerBase {
    public static final int APP_FOCUS_MAX = 2;
    public static final int APP_FOCUS_REQUEST_FAILED = 0;
    public static final int APP_FOCUS_REQUEST_SUCCEEDED = 1;
    public static final int APP_FOCUS_TYPE_NAVIGATION = 1;
    @Deprecated
    public static final int APP_FOCUS_TYPE_VOICE_COMMAND = 2;
    private final Map<OnAppFocusChangedListener, IAppFocusListenerImpl> mChangeBinders;
    private final Map<OnAppFocusOwnershipCallback, IAppFocusOwnershipCallbackImpl> mOwnershipBinders;
    private final IAppFocus mService;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AppFocusRequestResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AppFocusType {
    }

    /* loaded from: classes.dex */
    public interface OnAppFocusChangedListener {
        void onAppFocusChanged(int i, boolean z);
    }

    /* loaded from: classes.dex */
    public interface OnAppFocusOwnershipCallback {
        void onAppFocusOwnershipGranted(int i);

        void onAppFocusOwnershipLost(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CarAppFocusManager(Car car, IBinder service) {
        super(car);
        this.mChangeBinders = new HashMap();
        this.mOwnershipBinders = new HashMap();
        this.mService = IAppFocus.Stub.asInterface(service);
    }

    public void addFocusListener(OnAppFocusChangedListener listener, int appType) {
        IAppFocusListenerImpl binder;
        if (listener == null) {
            throw new IllegalArgumentException("null listener");
        }
        synchronized (this) {
            binder = this.mChangeBinders.get(listener);
            if (binder == null) {
                binder = new IAppFocusListenerImpl(listener);
                this.mChangeBinders.put(listener, binder);
            }
            binder.addAppType(appType);
        }
        try {
            this.mService.registerFocusListener(binder, appType);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void removeFocusListener(OnAppFocusChangedListener listener, int appType) {
        synchronized (this) {
            IAppFocusListenerImpl binder = this.mChangeBinders.get(listener);
            if (binder == null) {
                return;
            }
            try {
                this.mService.unregisterFocusListener(binder, appType);
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
            synchronized (this) {
                binder.removeAppType(appType);
                if (!binder.hasAppTypes()) {
                    this.mChangeBinders.remove(listener);
                }
            }
        }
    }

    public void removeFocusListener(OnAppFocusChangedListener listener) {
        synchronized (this) {
            IAppFocusListenerImpl binder = this.mChangeBinders.remove(listener);
            if (binder == null) {
                return;
            }
            try {
                for (Integer appType : binder.getAppTypes()) {
                    this.mService.unregisterFocusListener(binder, appType.intValue());
                }
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public int[] getActiveAppTypes() {
        try {
            return this.mService.getActiveAppTypes();
        } catch (RemoteException e) {
            return (int[]) handleRemoteExceptionFromCarService(e, new int[0]);
        }
    }

    public boolean isOwningFocus(OnAppFocusOwnershipCallback callback, int appType) {
        synchronized (this) {
            IAppFocusOwnershipCallbackImpl binder = this.mOwnershipBinders.get(callback);
            if (binder == null) {
                return false;
            }
            try {
                return this.mService.isOwningFocus(binder, appType);
            } catch (RemoteException e) {
                return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
            }
        }
    }

    public int requestAppFocus(int appType, OnAppFocusOwnershipCallback ownershipCallback) {
        IAppFocusOwnershipCallbackImpl binder;
        if (ownershipCallback == null) {
            throw new IllegalArgumentException("null listener");
        }
        synchronized (this) {
            binder = this.mOwnershipBinders.get(ownershipCallback);
            if (binder == null) {
                binder = new IAppFocusOwnershipCallbackImpl(ownershipCallback);
                this.mOwnershipBinders.put(ownershipCallback, binder);
            }
            binder.addAppType(appType);
        }
        try {
            return this.mService.requestAppFocus(binder, appType);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public void abandonAppFocus(OnAppFocusOwnershipCallback ownershipCallback, int appType) {
        if (ownershipCallback == null) {
            throw new IllegalArgumentException("null callback");
        }
        synchronized (this) {
            IAppFocusOwnershipCallbackImpl binder = this.mOwnershipBinders.get(ownershipCallback);
            if (binder == null) {
                return;
            }
            try {
                this.mService.abandonAppFocus(binder, appType);
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
            synchronized (this) {
                binder.removeAppType(appType);
                if (!binder.hasAppTypes()) {
                    this.mOwnershipBinders.remove(ownershipCallback);
                }
            }
        }
    }

    public void abandonAppFocus(OnAppFocusOwnershipCallback ownershipCallback) {
        synchronized (this) {
            IAppFocusOwnershipCallbackImpl binder = this.mOwnershipBinders.remove(ownershipCallback);
            if (binder == null) {
                return;
            }
            try {
                for (Integer appType : binder.getAppTypes()) {
                    this.mService.abandonAppFocus(binder, appType.intValue());
                }
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.APP_FOCUS_SERVICE;
    }

    /* loaded from: classes.dex */
    private static class IAppFocusListenerImpl extends IAppFocusListener.Stub {
        private final Set<Integer> mAppTypes;
        private final WeakReference<OnAppFocusChangedListener> mListener;
        private final WeakReference<CarAppFocusManager> mManager;

        private IAppFocusListenerImpl(CarAppFocusManager manager, OnAppFocusChangedListener listener) {
            this.mAppTypes = new HashSet();
            this.mManager = new WeakReference<>(manager);
            this.mListener = new WeakReference<>(listener);
        }

        public void addAppType(int appType) {
            this.mAppTypes.add(Integer.valueOf(appType));
        }

        public void removeAppType(int appType) {
            this.mAppTypes.remove(Integer.valueOf(appType));
        }

        public Set<Integer> getAppTypes() {
            return this.mAppTypes;
        }

        public boolean hasAppTypes() {
            return !this.mAppTypes.isEmpty();
        }

        @Override // android.car.IAppFocusListener
        public void onAppFocusChanged(final int appType, final boolean active) {
            CarAppFocusManager manager = this.mManager.get();
            final OnAppFocusChangedListener listener = this.mListener.get();
            if (manager == null || listener == null) {
                return;
            }
            manager.getEventHandler().post(new Runnable() { // from class: android.car.-$$Lambda$CarAppFocusManager$IAppFocusListenerImpl$GEkdUyrWmIsOwrPHNdFoc6BPTy8
                @Override // java.lang.Runnable
                public final void run() {
                    CarAppFocusManager.OnAppFocusChangedListener.this.onAppFocusChanged(appType, active);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    private static class IAppFocusOwnershipCallbackImpl extends IAppFocusOwnershipCallback.Stub {
        private final Set<Integer> mAppTypes;
        private final WeakReference<OnAppFocusOwnershipCallback> mCallback;
        private final WeakReference<CarAppFocusManager> mManager;

        private IAppFocusOwnershipCallbackImpl(CarAppFocusManager manager, OnAppFocusOwnershipCallback callback) {
            this.mAppTypes = new HashSet();
            this.mManager = new WeakReference<>(manager);
            this.mCallback = new WeakReference<>(callback);
        }

        public void addAppType(int appType) {
            this.mAppTypes.add(Integer.valueOf(appType));
        }

        public void removeAppType(int appType) {
            this.mAppTypes.remove(Integer.valueOf(appType));
        }

        public Set<Integer> getAppTypes() {
            return this.mAppTypes;
        }

        public boolean hasAppTypes() {
            return !this.mAppTypes.isEmpty();
        }

        @Override // android.car.IAppFocusOwnershipCallback
        public void onAppFocusOwnershipLost(final int appType) {
            CarAppFocusManager manager = this.mManager.get();
            final OnAppFocusOwnershipCallback callback = this.mCallback.get();
            if (manager == null || callback == null) {
                return;
            }
            manager.getEventHandler().post(new Runnable() { // from class: android.car.-$$Lambda$CarAppFocusManager$IAppFocusOwnershipCallbackImpl$UuCAjgDpuxMhRQhsLVGujMv9AyI
                @Override // java.lang.Runnable
                public final void run() {
                    CarAppFocusManager.OnAppFocusOwnershipCallback.this.onAppFocusOwnershipLost(appType);
                }
            });
        }

        @Override // android.car.IAppFocusOwnershipCallback
        public void onAppFocusOwnershipGranted(final int appType) {
            CarAppFocusManager manager = this.mManager.get();
            final OnAppFocusOwnershipCallback callback = this.mCallback.get();
            if (manager == null || callback == null) {
                return;
            }
            manager.getEventHandler().post(new Runnable() { // from class: android.car.-$$Lambda$CarAppFocusManager$IAppFocusOwnershipCallbackImpl$qTHeNucsOtp4FiO9TiUGyKE18eo
                @Override // java.lang.Runnable
                public final void run() {
                    CarAppFocusManager.OnAppFocusOwnershipCallback.this.onAppFocusOwnershipGranted(appType);
                }
            });
        }
    }
}
