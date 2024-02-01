package android.car.storagemonitoring;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.storagemonitoring.ICarStorageMonitoring;
import android.car.storagemonitoring.IIoStatsListener;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.car.internal.SingleMessageHandler;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SystemApi
/* loaded from: classes.dex */
public final class CarStorageMonitoringManager extends CarManagerBase {
    public static final String INTENT_EXCESSIVE_IO = "android.car.storagemonitoring.EXCESSIVE_IO";
    private static final int MSG_IO_STATS_EVENT = 0;
    public static final int PRE_EOL_INFO_NORMAL = 1;
    public static final int PRE_EOL_INFO_UNKNOWN = 0;
    public static final int PRE_EOL_INFO_URGENT = 3;
    public static final int PRE_EOL_INFO_WARNING = 2;
    public static final long SHUTDOWN_COST_INFO_MISSING = -1;
    private static final String TAG = CarStorageMonitoringManager.class.getSimpleName();
    private ListenerToService mListenerToService;
    private final Set<IoStatsListener> mListeners;
    private final SingleMessageHandler<IoStats> mMessageHandler;
    private final ICarStorageMonitoring mService;

    /* loaded from: classes.dex */
    public interface IoStatsListener {
        void onSnapshot(IoStats ioStats);
    }

    /* loaded from: classes.dex */
    private static final class ListenerToService extends IIoStatsListener.Stub {
        private final WeakReference<CarStorageMonitoringManager> mManager;

        ListenerToService(CarStorageMonitoringManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        @Override // android.car.storagemonitoring.IIoStatsListener
        public void onSnapshot(IoStats snapshot) {
            CarStorageMonitoringManager manager = this.mManager.get();
            if (manager != null) {
                manager.mMessageHandler.sendEvents(Collections.singletonList(snapshot));
            }
        }
    }

    public CarStorageMonitoringManager(Car car, IBinder service) {
        super(car);
        this.mListeners = new HashSet();
        this.mService = ICarStorageMonitoring.Stub.asInterface(service);
        this.mMessageHandler = new SingleMessageHandler<IoStats>(getEventHandler(), 0) { // from class: android.car.storagemonitoring.CarStorageMonitoringManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.android.car.internal.SingleMessageHandler
            public void handleEvent(IoStats event) {
                for (IoStatsListener listener : CarStorageMonitoringManager.this.mListeners) {
                    listener.onSnapshot(event);
                }
            }
        };
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mListeners.clear();
        this.mListenerToService = null;
    }

    public static String getServiceName() {
        return Car.STORAGE_MONITORING_SERVICE;
    }

    public int getPreEolIndicatorStatus() {
        try {
            return this.mService.getPreEolIndicatorStatus();
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public WearEstimate getWearEstimate() {
        try {
            return this.mService.getWearEstimate();
        } catch (RemoteException e) {
            return (WearEstimate) handleRemoteExceptionFromCarService(e, null);
        }
    }

    public List<WearEstimateChange> getWearEstimateHistory() {
        try {
            return this.mService.getWearEstimateHistory();
        } catch (RemoteException e) {
            return (List) handleRemoteExceptionFromCarService(e, Collections.emptyList());
        }
    }

    public List<IoStatsEntry> getBootIoStats() {
        try {
            return this.mService.getBootIoStats();
        } catch (RemoteException e) {
            return (List) handleRemoteExceptionFromCarService(e, Collections.emptyList());
        }
    }

    public long getShutdownDiskWriteAmount() {
        try {
            return this.mService.getShutdownDiskWriteAmount();
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public List<IoStatsEntry> getAggregateIoStats() {
        try {
            return this.mService.getAggregateIoStats();
        } catch (RemoteException e) {
            return (List) handleRemoteExceptionFromCarService(e, Collections.emptyList());
        }
    }

    public List<IoStats> getIoStatsDeltas() {
        try {
            return this.mService.getIoStatsDeltas();
        } catch (RemoteException e) {
            return (List) handleRemoteExceptionFromCarService(e, Collections.emptyList());
        }
    }

    public void registerListener(IoStatsListener listener) {
        try {
            if (this.mListeners.isEmpty()) {
                if (this.mListenerToService == null) {
                    this.mListenerToService = new ListenerToService(this);
                }
                this.mService.registerListener(this.mListenerToService);
            }
            this.mListeners.add(listener);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void unregisterListener(IoStatsListener listener) {
        try {
            if (this.mListeners.remove(listener) && this.mListeners.isEmpty()) {
                this.mService.unregisterListener(this.mListenerToService);
                this.mListenerToService = null;
            }
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }
}
