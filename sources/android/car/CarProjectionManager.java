package android.car;

import android.annotation.SystemApi;
import android.bluetooth.BluetoothDevice;
import android.car.CarProjectionManager;
import android.car.ICarProjection;
import android.car.ICarProjectionKeyEventHandler;
import android.car.ICarProjectionStatusListener;
import android.car.projection.ProjectionStatus;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.util.Preconditions;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;

@SystemApi
/* loaded from: classes.dex */
public final class CarProjectionManager extends CarManagerBase {
    public static final int KEY_EVENT_CALL_KEY_DOWN = 4;
    public static final int KEY_EVENT_CALL_LONG_PRESS_KEY_DOWN = 6;
    public static final int KEY_EVENT_CALL_LONG_PRESS_KEY_UP = 7;
    public static final int KEY_EVENT_CALL_SHORT_PRESS_KEY_UP = 5;
    public static final int KEY_EVENT_VOICE_SEARCH_KEY_DOWN = 0;
    public static final int KEY_EVENT_VOICE_SEARCH_LONG_PRESS_KEY_DOWN = 2;
    public static final int KEY_EVENT_VOICE_SEARCH_LONG_PRESS_KEY_UP = 3;
    public static final int KEY_EVENT_VOICE_SEARCH_SHORT_PRESS_KEY_UP = 1;
    public static final int NUM_KEY_EVENTS = 8;
    public static final int PROJECTION_AP_FAILED = 2;
    public static final int PROJECTION_AP_STARTED = 0;
    public static final int PROJECTION_AP_STOPPED = 1;
    @Deprecated
    public static final int PROJECTION_LONG_PRESS_VOICE_SEARCH = 2;
    @Deprecated
    public static final int PROJECTION_VOICE_SEARCH = 1;
    private static final String TAG = CarProjectionManager.class.getSimpleName();
    private static final IBinder mAccessPointProxyToken = new Binder();
    private final ICarProjectionKeyEventHandlerImpl mBinderHandler;
    private CarProjectionStatusListenerImpl mCarProjectionStatusListener;
    @GuardedBy({"mLock"})
    private BitSet mHandledEvents;
    private final Executor mHandlerExecutor;
    @GuardedBy({"mLock"})
    private final Map<ProjectionKeyEventHandler, KeyEventHandlerRecord> mKeyEventHandlers;
    private final ProjectionKeyEventHandler mLegacyListenerTranslator;
    @GuardedBy({"mLock"})
    private CarProjectionListener mListener;
    private final Object mLock;
    private ProjectionAccessPointCallbackProxy mProjectionAccessPointCallbackProxy;
    private final Set<ProjectionStatusListener> mProjectionStatusListeners;
    private final ICarProjection mService;
    private final Binder mToken;
    @GuardedBy({"mLock"})
    private int mVoiceSearchFilter;

    /* loaded from: classes.dex */
    public interface CarProjectionListener {
        void onVoiceAssistantRequest(boolean z);
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface KeyEventNum {
    }

    /* loaded from: classes.dex */
    public interface ProjectionKeyEventHandler {
        void onKeyEvent(int i);
    }

    /* loaded from: classes.dex */
    public interface ProjectionStatusListener {
        void onProjectionStatusChanged(int i, String str, List<ProjectionStatus> list);
    }

    public CarProjectionManager(Car car, IBinder service) {
        super(car);
        this.mToken = new Binder();
        this.mLock = new Object();
        this.mLegacyListenerTranslator = new ProjectionKeyEventHandler() { // from class: android.car.-$$Lambda$CarProjectionManager$BMXU8XLW9el2hlnphNAT6ETl2JM
            @Override // android.car.CarProjectionManager.ProjectionKeyEventHandler
            public final void onKeyEvent(int i) {
                CarProjectionManager.this.translateKeyEventToLegacyListener(i);
            }
        };
        this.mBinderHandler = new ICarProjectionKeyEventHandlerImpl();
        this.mKeyEventHandlers = new HashMap();
        this.mHandledEvents = new BitSet();
        this.mProjectionStatusListeners = new LinkedHashSet();
        this.mService = ICarProjection.Stub.asInterface(service);
        final Handler handler = getEventHandler();
        Objects.requireNonNull(handler);
        this.mHandlerExecutor = new Executor() { // from class: android.car.-$$Lambda$LfzJt661qZfn2w-6SYHFbD3aMy0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    public void regsiterProjectionListener(CarProjectionListener listener, int voiceSearchFilter) {
        registerProjectionListener(listener, voiceSearchFilter);
    }

    public void registerProjectionListener(CarProjectionListener listener, int voiceSearchFilter) {
        Preconditions.checkNotNull(listener, "listener cannot be null");
        synchronized (this.mLock) {
            if (this.mListener == null || this.mVoiceSearchFilter != voiceSearchFilter) {
                addKeyEventHandler(translateVoiceSearchFilter(voiceSearchFilter), this.mLegacyListenerTranslator);
            }
            this.mListener = listener;
            this.mVoiceSearchFilter = voiceSearchFilter;
        }
    }

    public void unregsiterProjectionListener() {
        unregisterProjectionListener();
    }

    public void unregisterProjectionListener() {
        synchronized (this.mLock) {
            removeKeyEventHandler(this.mLegacyListenerTranslator);
            this.mListener = null;
            this.mVoiceSearchFilter = 0;
        }
    }

    private static Set<Integer> translateVoiceSearchFilter(int voiceSearchFilter) {
        Set<Integer> rv = new ArraySet<>(Integer.bitCount(voiceSearchFilter));
        if ((voiceSearchFilter & 1) != 0) {
            rv.add(1);
        }
        if ((voiceSearchFilter & 2) != 0) {
            rv.add(2);
        }
        return rv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void translateKeyEventToLegacyListener(int keyEvent) {
        boolean fromLongPress;
        synchronized (this.mLock) {
            if (this.mListener == null) {
                return;
            }
            CarProjectionListener legacyListener = this.mListener;
            if (keyEvent == 1) {
                fromLongPress = false;
            } else if (keyEvent == 2) {
                fromLongPress = true;
            } else {
                String str = TAG;
                Log.e(str, "Unexpected key event " + keyEvent);
                return;
            }
            String str2 = TAG;
            Log.d(str2, "Voice assistant request, long-press = " + fromLongPress);
            legacyListener.onVoiceAssistantRequest(fromLongPress);
        }
    }

    public void addKeyEventHandler(Set<Integer> events, ProjectionKeyEventHandler eventHandler) {
        addKeyEventHandler(events, null, eventHandler);
    }

    public void addKeyEventHandler(Set<Integer> events, Executor executor, ProjectionKeyEventHandler eventHandler) {
        BitSet eventMask = new BitSet();
        for (Integer num : events) {
            int event = num.intValue();
            Preconditions.checkArgument(event >= 0 && event < 8, "Invalid key event");
            eventMask.set(event);
        }
        if (eventMask.isEmpty()) {
            removeKeyEventHandler(eventHandler);
            return;
        }
        if (executor == null) {
            executor = this.mHandlerExecutor;
        }
        synchronized (this.mLock) {
            KeyEventHandlerRecord record = this.mKeyEventHandlers.get(eventHandler);
            if (record == null) {
                this.mKeyEventHandlers.put(eventHandler, new KeyEventHandlerRecord(executor, eventMask));
            } else {
                record.mExecutor = executor;
                record.mSubscribedEvents = eventMask;
            }
            updateHandledEventsLocked();
        }
    }

    public void removeKeyEventHandler(ProjectionKeyEventHandler eventHandler) {
        synchronized (this.mLock) {
            KeyEventHandlerRecord record = this.mKeyEventHandlers.remove(eventHandler);
            if (record != null) {
                updateHandledEventsLocked();
            }
        }
    }

    @GuardedBy({"mLock"})
    private void updateHandledEventsLocked() {
        BitSet events = new BitSet();
        for (KeyEventHandlerRecord record : this.mKeyEventHandlers.values()) {
            events.or(record.mSubscribedEvents);
        }
        if (events.equals(this.mHandledEvents)) {
            return;
        }
        try {
            if (!events.isEmpty()) {
                String str = TAG;
                Log.d(str, "Registering handler with system for " + events);
                byte[] eventMask = events.toByteArray();
                this.mService.registerKeyEventHandler(this.mBinderHandler, eventMask);
            } else {
                Log.d(TAG, "Unregistering handler with system");
                this.mService.unregisterKeyEventHandler(this.mBinderHandler);
            }
            this.mHandledEvents = events;
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void registerProjectionRunner(Intent serviceIntent) {
        Preconditions.checkNotNull("serviceIntent cannot be null");
        synchronized (this.mLock) {
            try {
                this.mService.registerProjectionRunner(serviceIntent);
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public void unregisterProjectionRunner(Intent serviceIntent) {
        Preconditions.checkNotNull("serviceIntent cannot be null");
        synchronized (this.mLock) {
            try {
                this.mService.unregisterProjectionRunner(serviceIntent);
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.PROJECTION_SERVICE;
    }

    public void startProjectionAccessPoint(ProjectionAccessPointCallback callback) {
        Preconditions.checkNotNull(callback, "callback cannot be null");
        synchronized (this.mLock) {
            Looper looper = getEventHandler().getLooper();
            ProjectionAccessPointCallbackProxy proxy = new ProjectionAccessPointCallbackProxy(this, looper, callback);
            try {
                this.mService.startProjectionAccessPoint(proxy.getMessenger(), mAccessPointProxyToken);
                this.mProjectionAccessPointCallbackProxy = proxy;
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        }
    }

    public List<Integer> getAvailableWifiChannels(int band) {
        try {
            int[] channels = this.mService.getAvailableWifiChannels(band);
            List<Integer> channelList = new ArrayList<>(channels.length);
            for (int v : channels) {
                channelList.add(Integer.valueOf(v));
            }
            return channelList;
        } catch (RemoteException e) {
            return (List) handleRemoteExceptionFromCarService(e, Collections.emptyList());
        }
    }

    public void stopProjectionAccessPoint() {
        ProjectionAccessPointCallbackProxy proxy;
        synchronized (this.mLock) {
            proxy = this.mProjectionAccessPointCallbackProxy;
            this.mProjectionAccessPointCallbackProxy = null;
        }
        if (proxy == null) {
            return;
        }
        try {
            this.mService.stopProjectionAccessPoint(mAccessPointProxyToken);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public boolean requestBluetoothProfileInhibit(BluetoothDevice device, int profile) {
        Preconditions.checkNotNull(device, "device cannot be null");
        try {
            return this.mService.requestBluetoothProfileInhibit(device, profile, this.mToken);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean releaseBluetoothProfileInhibit(BluetoothDevice device, int profile) {
        Preconditions.checkNotNull(device, "device cannot be null");
        try {
            return this.mService.releaseBluetoothProfileInhibit(device, profile, this.mToken);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public void updateProjectionStatus(ProjectionStatus status) {
        Preconditions.checkNotNull(status, "status cannot be null");
        try {
            this.mService.updateProjectionStatus(status, this.mToken);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void registerProjectionStatusListener(final ProjectionStatusListener listener) {
        Preconditions.checkNotNull(listener, "listener cannot be null");
        synchronized (this.mLock) {
            this.mProjectionStatusListeners.add(listener);
            if (this.mCarProjectionStatusListener == null) {
                this.mCarProjectionStatusListener = new CarProjectionStatusListenerImpl();
                try {
                    this.mService.registerProjectionStatusListener(this.mCarProjectionStatusListener);
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                }
            } else {
                getEventHandler().post(new Runnable() { // from class: android.car.-$$Lambda$CarProjectionManager$7qT6feincVoa0SR_azEEG39PNts
                    @Override // java.lang.Runnable
                    public final void run() {
                        CarProjectionManager.this.lambda$registerProjectionStatusListener$0$CarProjectionManager(listener);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$registerProjectionStatusListener$0$CarProjectionManager(ProjectionStatusListener listener) {
        listener.onProjectionStatusChanged(this.mCarProjectionStatusListener.mCurrentState, this.mCarProjectionStatusListener.mCurrentPackageName, this.mCarProjectionStatusListener.mDetails);
    }

    public void unregisterProjectionStatusListener(ProjectionStatusListener listener) {
        Preconditions.checkNotNull(listener, "listener cannot be null");
        synchronized (this.mLock) {
            if (this.mProjectionStatusListeners.remove(listener) && this.mProjectionStatusListeners.isEmpty()) {
                unregisterProjectionStatusListenerFromCarServiceLocked();
            }
        }
    }

    private void unregisterProjectionStatusListenerFromCarServiceLocked() {
        try {
            this.mService.unregisterProjectionStatusListener(this.mCarProjectionStatusListener);
            this.mCarProjectionStatusListener = null;
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleProjectionStatusChanged(int state, String packageName, List<ProjectionStatus> details) {
        List<ProjectionStatusListener> listeners;
        synchronized (this.mLock) {
            listeners = new ArrayList<>(this.mProjectionStatusListeners);
        }
        for (ProjectionStatusListener listener : listeners) {
            listener.onProjectionStatusChanged(state, packageName, details);
        }
    }

    public Bundle getProjectionOptions() {
        try {
            return this.mService.getProjectionOptions();
        } catch (RemoteException e) {
            return (Bundle) handleRemoteExceptionFromCarService(e, Bundle.EMPTY);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ProjectionAccessPointCallback {
        public static final int ERROR_GENERIC = 2;
        public static final int ERROR_INCOMPATIBLE_MODE = 3;
        public static final int ERROR_NO_CHANNEL = 1;
        public static final int ERROR_TETHERING_DISALLOWED = 4;

        public void onStarted(WifiConfiguration wifiConfiguration) {
        }

        public void onStopped() {
        }

        public void onFailed(int reason) {
        }
    }

    /* loaded from: classes.dex */
    private static class ProjectionAccessPointCallbackProxy {
        private static final String LOG_PREFIX = ProjectionAccessPointCallbackProxy.class.getSimpleName() + ": ";
        private final WeakReference<CarProjectionManager> mCarProjectionManagerRef;
        private final Handler mHandler;
        private final Messenger mMessenger;

        ProjectionAccessPointCallbackProxy(CarProjectionManager manager, Looper looper, final ProjectionAccessPointCallback callback) {
            this.mCarProjectionManagerRef = new WeakReference<>(manager);
            this.mHandler = new Handler(looper) { // from class: android.car.CarProjectionManager.ProjectionAccessPointCallbackProxy.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    String str = CarProjectionManager.TAG;
                    Log.d(str, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "handle message what: " + msg.what + " msg: " + msg);
                    CarProjectionManager manager2 = (CarProjectionManager) ProjectionAccessPointCallbackProxy.this.mCarProjectionManagerRef.get();
                    if (manager2 == null) {
                        String str2 = CarProjectionManager.TAG;
                        Log.w(str2, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "handle message post GC");
                        return;
                    }
                    int i = msg.what;
                    if (i == 0) {
                        WifiConfiguration config = (WifiConfiguration) msg.obj;
                        if (config == null) {
                            String str3 = CarProjectionManager.TAG;
                            Log.e(str3, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "config cannot be null.");
                            callback.onFailed(2);
                            return;
                        }
                        callback.onStarted(config);
                    } else if (i == 1) {
                        String str4 = CarProjectionManager.TAG;
                        Log.i(str4, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "hotspot stopped");
                        callback.onStopped();
                    } else if (i != 2) {
                        String str5 = CarProjectionManager.TAG;
                        Log.e(str5, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "unhandled message.  type: " + msg.what);
                    } else {
                        int reasonCode = msg.arg1;
                        String str6 = CarProjectionManager.TAG;
                        Log.w(str6, ProjectionAccessPointCallbackProxy.LOG_PREFIX + "failed to start.  reason: " + reasonCode);
                        callback.onFailed(reasonCode);
                    }
                }
            };
            this.mMessenger = new Messenger(this.mHandler);
        }

        Messenger getMessenger() {
            return this.mMessenger;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ICarProjectionKeyEventHandlerImpl extends ICarProjectionKeyEventHandler.Stub {
        private final WeakReference<CarProjectionManager> mManager;

        private ICarProjectionKeyEventHandlerImpl(CarProjectionManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        @Override // android.car.ICarProjectionKeyEventHandler
        public void onKeyEvent(final int event) {
            String str = CarProjectionManager.TAG;
            Log.d(str, "Received projection key event " + event);
            CarProjectionManager manager = this.mManager.get();
            if (manager == null) {
                return;
            }
            List<Pair<ProjectionKeyEventHandler, Executor>> toDispatch = new ArrayList<>();
            synchronized (manager.mLock) {
                for (Map.Entry<ProjectionKeyEventHandler, KeyEventHandlerRecord> entry : manager.mKeyEventHandlers.entrySet()) {
                    if (entry.getValue().mSubscribedEvents.get(event)) {
                        toDispatch.add(Pair.create(entry.getKey(), entry.getValue().mExecutor));
                    }
                }
            }
            for (Pair<ProjectionKeyEventHandler, Executor> entry2 : toDispatch) {
                final ProjectionKeyEventHandler listener = (ProjectionKeyEventHandler) entry2.first;
                ((Executor) entry2.second).execute(new Runnable() { // from class: android.car.-$$Lambda$CarProjectionManager$ICarProjectionKeyEventHandlerImpl$MBwgMQivjMBxVP_SaFQheO04TNY
                    @Override // java.lang.Runnable
                    public final void run() {
                        CarProjectionManager.ProjectionKeyEventHandler.this.onKeyEvent(event);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class KeyEventHandlerRecord {
        Executor mExecutor;
        BitSet mSubscribedEvents;

        KeyEventHandlerRecord(Executor executor, BitSet subscribedEvents) {
            this.mExecutor = executor;
            this.mSubscribedEvents = subscribedEvents;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CarProjectionStatusListenerImpl extends ICarProjectionStatusListener.Stub {
        private String mCurrentPackageName;
        private int mCurrentState;
        private List<ProjectionStatus> mDetails;
        private final WeakReference<CarProjectionManager> mManagerRef;

        private CarProjectionStatusListenerImpl(CarProjectionManager mgr) {
            this.mDetails = new ArrayList(0);
            this.mManagerRef = new WeakReference<>(mgr);
        }

        @Override // android.car.ICarProjectionStatusListener
        public void onProjectionStatusChanged(final int projectionState, final String packageName, final List<ProjectionStatus> details) {
            final CarProjectionManager mgr = this.mManagerRef.get();
            if (mgr != null) {
                mgr.getEventHandler().post(new Runnable() { // from class: android.car.-$$Lambda$CarProjectionManager$CarProjectionStatusListenerImpl$pKBrFkhSPUT8gyMVXjB6LFXBIOM
                    @Override // java.lang.Runnable
                    public final void run() {
                        CarProjectionManager.CarProjectionStatusListenerImpl.this.lambda$onProjectionStatusChanged$0$CarProjectionManager$CarProjectionStatusListenerImpl(projectionState, packageName, details, mgr);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onProjectionStatusChanged$0$CarProjectionManager$CarProjectionStatusListenerImpl(int projectionState, String packageName, List details, CarProjectionManager mgr) {
            this.mCurrentState = projectionState;
            this.mCurrentPackageName = packageName;
            this.mDetails = Collections.unmodifiableList(details);
            mgr.handleProjectionStatusChanged(projectionState, packageName, this.mDetails);
        }
    }
}
