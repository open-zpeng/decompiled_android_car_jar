package android.car.hardware.shm;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarApiUtil;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.XpDebugLog;
import android.car.hardware.CarEcuManager;
import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.xpsharedmemory.ISharedMemoryDataListener;
import android.car.xpsharedmemory.IXpSharedMemory;
import android.os.Build;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import com.android.car.internal.SingleRawMessageHandler;
import com.android.internal.annotations.GuardedBy;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

/* loaded from: classes.dex */
public class CarSharedMemoryManager extends CarManagerBase {
    private static final int MAX_DATA_SIZE = 10485760;
    private static final int MSG_GENERIC_EVENT = 0;
    private static final String TAG = "CarSharedMemoryManager";
    private final boolean mDbg;
    @GuardedBy({"mLock"})
    private final SingleRawMessageHandler<CarPropertyValue<?>> mHandler;
    private final HandlerThread mHandlerThread;
    private final Map<Integer, AtomicLong> mIdToCallbackCountMap;
    private final SparseArray<CopyOnWriteArrayList<CarEcuManager.CarEcuEventCallback>> mIdToCallbacksMap;
    private final IXpSharedMemory mService;
    private SharedMemoryDataListenerToService mSharedMemoryDataEventToService;
    private Set<Integer> mSharedMemoryPropertyIds;
    private volatile Map<Integer, CarPropertyConfig<?>> mSupportedPropConfigs;
    private final String mTag;
    private static final Object mLock = new Object();
    private static final Duration MAX_INTERVAL_MS = Duration.ofMillis(40);
    private static final Duration MAX_INTERVAL_MS_IN_HANDLER = Duration.ofMillis(20);

    @SystemApi
    public CarSharedMemoryManager(Car car, IBinder service, boolean dbg, String tag) {
        super(car);
        this.mIdToCallbacksMap = new SparseArray<>();
        this.mIdToCallbackCountMap = new ConcurrentHashMap();
        this.mDbg = dbg;
        this.mTag = tag;
        this.mService = IXpSharedMemory.Stub.asInterface(service);
        this.mHandlerThread = new HandlerThread("shared memory callback", -19);
        this.mHandlerThread.start();
        this.mHandler = getHandler(this.mHandlerThread.getLooper());
    }

    public static String getServiceName() {
        return Car.XP_SHARED_MEMORY_SERVICE;
    }

    public void setSharedMemoryPropertyIds(Set<Integer> sharedMemoryPropertyIds) {
        synchronized (mLock) {
            if (this.mSharedMemoryPropertyIds == null) {
                this.mSharedMemoryPropertyIds = sharedMemoryPropertyIds;
            }
        }
    }

    private SingleRawMessageHandler<CarPropertyValue<?>> getHandler(Looper looper) {
        return new SingleRawMessageHandler<CarPropertyValue<?>>(looper, 0) { // from class: android.car.hardware.shm.CarSharedMemoryManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.android.car.internal.SingleRawMessageHandler
            public void handleEvent(CarPropertyValue<?> value) {
                CopyOnWriteArrayList<CarEcuManager.CarEcuEventCallback> callbacks;
                CopyOnWriteArrayList<CarEcuManager.CarEcuEventCallback> callbacks2;
                long extraTimestamp;
                boolean perfLogEnable = XpDebugLog.isSharedMemoryPerfLogEnable();
                long timestamp = value.getTimestamp();
                long extraTimestamp2 = value.getExtraTimestamp();
                long timeCost = SystemClock.uptimeMillis() - extraTimestamp2;
                int propertyId = value.getPropertyId();
                if (timeCost > CarSharedMemoryManager.MAX_INTERVAL_MS_IN_HANDLER.toMillis()) {
                    String str = CarSharedMemoryManager.this.mTag;
                    Log.i(str, "handleEvent: " + XpDebugLog.getPropertyDescription(propertyId) + ", timestamp: " + timestamp + " app to handler cost too much time: " + timeCost + " ms");
                } else if (perfLogEnable) {
                    String str2 = CarSharedMemoryManager.this.mTag;
                    Log.i(str2, "handleEvent: " + XpDebugLog.getPropertyDescription(propertyId) + ", timestamp: " + timestamp + " app to handler cost time: " + timeCost + " ms");
                }
                if (!Build.IS_USER) {
                    CarSharedMemoryManager.this.logCallbackCount(propertyId);
                }
                synchronized (this) {
                    try {
                        try {
                            try {
                                callbacks = (CopyOnWriteArrayList) CarSharedMemoryManager.this.mIdToCallbacksMap.get(propertyId);
                            } catch (ClassCastException e) {
                                if (CarSharedMemoryManager.this.mIdToCallbacksMap.get(propertyId) != null) {
                                    String className = ((CopyOnWriteArrayList) CarSharedMemoryManager.this.mIdToCallbacksMap.get(propertyId)).getClass().getName();
                                    String str3 = CarSharedMemoryManager.this.mTag;
                                    Log.e(str3, "handleEvent cannot cast callbacks by propertyId:" + propertyId + " className:" + className + " callbacks:" + CarSharedMemoryManager.this.mIdToCallbacksMap.get(propertyId));
                                } else {
                                    String str4 = CarSharedMemoryManager.this.mTag;
                                    Log.e(str4, "handleEvent cannot cast callbacks by propertyId:" + propertyId);
                                }
                                String str5 = CarSharedMemoryManager.this.mTag;
                                Log.e(str5, "handleEvent ClassCastException, excep:" + e);
                                throw e;
                            }
                        } catch (Throwable th) {
                            e = th;
                        }
                        try {
                            if (callbacks == null) {
                                return;
                            }
                            Iterator<CarEcuManager.CarEcuEventCallback> it = callbacks.iterator();
                            while (it.hasNext()) {
                                CarEcuManager.CarEcuEventCallback l = it.next();
                                if (l == null) {
                                    String str6 = CarSharedMemoryManager.this.mTag;
                                    Log.w(str6, "++++handleEvent: " + XpDebugLog.getPropertyDescription(propertyId) + " callback is null");
                                } else {
                                    if (perfLogEnable) {
                                        String str7 = CarSharedMemoryManager.this.mTag;
                                        Log.i(str7, "++++handleEvent: " + XpDebugLog.getPropertyDescription(propertyId) + ", timestamp: " + timestamp + " callback:" + l);
                                    }
                                    long start = SystemClock.uptimeMillis();
                                    try {
                                        l.onChangeEvent(value);
                                        long timeCost2 = SystemClock.uptimeMillis() - start;
                                        if (timeCost2 > CarSharedMemoryManager.MAX_INTERVAL_MS_IN_HANDLER.toMillis()) {
                                            callbacks2 = callbacks;
                                            StringBuilder sb = new StringBuilder();
                                            extraTimestamp = extraTimestamp2;
                                            sb.append("handle: ");
                                            sb.append(XpDebugLog.getPropertyDescription(propertyId));
                                            sb.append(", timestamp:");
                                            sb.append(timestamp);
                                            sb.append(" callback cost too much time:");
                                            sb.append(timeCost2);
                                            sb.append(" ms");
                                            Log.w(SingleRawMessageHandler.TAG, sb.toString());
                                        } else {
                                            callbacks2 = callbacks;
                                            extraTimestamp = extraTimestamp2;
                                            if (perfLogEnable) {
                                                Log.i(SingleRawMessageHandler.TAG, "handle: " + XpDebugLog.getPropertyDescription(propertyId) + ", timestamp:" + timestamp + " callback cost " + timeCost2 + " ms");
                                            }
                                        }
                                        callbacks = callbacks2;
                                        extraTimestamp2 = extraTimestamp;
                                    } catch (Throwable th2) {
                                        e = th2;
                                        throw e;
                                    }
                                }
                            }
                        } catch (Throwable th3) {
                            e = th3;
                            throw e;
                        }
                    } catch (Throwable th4) {
                        e = th4;
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logCallbackCount(int propertyId) {
        long count = this.mIdToCallbackCountMap.computeIfAbsent(Integer.valueOf(propertyId), new Function() { // from class: android.car.hardware.shm.-$$Lambda$CarSharedMemoryManager$82O0eJDqwxTMBm5197uY3h5RX3Y
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CarSharedMemoryManager.lambda$logCallbackCount$0((Integer) obj);
            }
        }).incrementAndGet();
        if (count < 0) {
            this.mIdToCallbackCountMap.replace(Integer.valueOf(propertyId), new AtomicLong(1L));
            String str = this.mTag;
            Log.i(str, "callback for " + XpDebugLog.getPropertyDescription(propertyId) + ", new count = 1");
        } else if (XpDebugLog.isCallBackDebugLogEnable(0)) {
            String str2 = this.mTag;
            Log.i(str2, "callback for " + XpDebugLog.getPropertyDescription(propertyId) + ", count = " + count);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AtomicLong lambda$logCallbackCount$0(Integer p) {
        return new AtomicLong();
    }

    private void initSupportedPropConfigs() throws CarNotConnectedException {
        Collection<CarPropertyConfig> configs;
        if (this.mSupportedPropConfigs == null) {
            synchronized (mLock) {
                if (this.mSupportedPropConfigs == null && (configs = getPropertyConfigs(this.mSharedMemoryPropertyIds)) != null && !configs.isEmpty()) {
                    Map<Integer, CarPropertyConfig<?>> supportedPropConfigs = new ArrayMap<>(configs.size());
                    for (CarPropertyConfig c : configs) {
                        supportedPropConfigs.put(Integer.valueOf(c.getPropertyId()), c);
                    }
                    this.mSupportedPropConfigs = supportedPropConfigs;
                }
            }
        }
    }

    private Collection<CarPropertyConfig> getPropertyConfigs(Collection<Integer> propertyIds) throws CarNotConnectedException {
        if (propertyIds == null) {
            return null;
        }
        try {
            CarPropertyConfig[] propertyArray = this.mService.getPropertyArray();
            Set<CarPropertyConfig> configs = new ArraySet<>();
            for (CarPropertyConfig c : propertyArray) {
                if (propertyIds.contains(Integer.valueOf(c.getPropertyId()))) {
                    configs.add(c);
                }
            }
            return configs;
        } catch (RemoteException e) {
            Log.e(this.mTag, "getPropertyList exception ", e);
            throw new CarNotConnectedException(e);
        }
    }

    private Set<Integer> getSupportedPropIds() throws CarNotConnectedException {
        synchronized (mLock) {
            if (this.mSupportedPropConfigs != null) {
                return this.mSupportedPropConfigs.keySet();
            }
            return null;
        }
    }

    public void unregisterCallback(CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        unregisterPropCallback(getSupportedPropIds(), callback);
    }

    private boolean isPropertyAllowRegister(CarPropertyConfig<?> c) {
        return ((c.getAccess() & 1) == 0 || c.getChangeMode() == 0) ? false : true;
    }

    public void registerPropCallback(Collection<Integer> ids, CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        CarPropertyConfig<?> config;
        if (ids == null) {
            Log.i(this.mTag, "registerPropCallback ids is null");
        } else if (callback == null) {
            Log.i(this.mTag, "registerPropCallback callback is null");
        } else {
            if (XpDebugLog.CAR_DEBUG) {
                String str = this.mTag;
                Log.i(str, "registerPropCallback: " + callback);
            }
            synchronized (this) {
                if (this.mSharedMemoryDataEventToService == null) {
                    this.mSharedMemoryDataEventToService = new SharedMemoryDataListenerToService(this);
                }
            }
            initSupportedPropConfigs();
            if (this.mSupportedPropConfigs == null) {
                Slog.w(this.mTag, "no supported shared memory properties");
                return;
            }
            for (Integer id : ids) {
                synchronized (mLock) {
                    config = this.mSupportedPropConfigs.get(id);
                }
                if (config == null || !isPropertyAllowRegister(config)) {
                    String str2 = this.mTag;
                    Log.w(str2, "register unsupported prop: " + id + "(0x" + Integer.toHexString(id.intValue()) + ")");
                } else {
                    boolean shouldRegister = false;
                    SharedMemoryDataListenerToService dataListenerToService = null;
                    synchronized (this) {
                        CopyOnWriteArrayList<CarEcuManager.CarEcuEventCallback> callbacks = this.mIdToCallbacksMap.get(id.intValue());
                        if (callbacks == null) {
                            callbacks = new CopyOnWriteArrayList<>();
                            this.mIdToCallbacksMap.put(id.intValue(), callbacks);
                            shouldRegister = true;
                            dataListenerToService = this.mSharedMemoryDataEventToService;
                        }
                        if (!callbacks.contains(callback)) {
                            callbacks.add(callback);
                        }
                    }
                    if (shouldRegister && dataListenerToService != null) {
                        try {
                            if (XpDebugLog.CAR_DEBUG) {
                                String str3 = this.mTag;
                                Log.i(str3, "registerPropCallback for " + XpDebugLog.getPropertyDescription(id.intValue()));
                            }
                            this.mService.registerListener(id.intValue(), dataListenerToService);
                        } catch (RemoteException e) {
                            throw new CarNotConnectedException(e);
                        } catch (IllegalStateException e2) {
                            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
                        }
                    } else {
                        String str4 = this.mTag;
                        Log.w(str4, "won't registerPropCallback for " + XpDebugLog.getPropertyDescription(id.intValue()));
                    }
                }
            }
        }
    }

    public void unregisterPropCallback(Collection<Integer> ids, CarEcuManager.CarEcuEventCallback callback) throws CarNotConnectedException {
        if (ids == null) {
            Log.e(this.mTag, "ids is null");
        } else if (callback == null) {
            Log.e(this.mTag, "callback is null");
            throw new IllegalArgumentException("input data cannot be null");
        } else {
            if (XpDebugLog.CAR_DEBUG) {
                String str = this.mTag;
                Log.i(str, "unregisterPropCallback: " + callback);
            }
            for (Integer id : ids) {
                boolean unregister = false;
                SharedMemoryDataListenerToService dataListenerToService = null;
                synchronized (this) {
                    CopyOnWriteArrayList<CarEcuManager.CarEcuEventCallback> callbacks = this.mIdToCallbacksMap.get(id.intValue());
                    if (callbacks == null) {
                        String str2 = this.mTag;
                        Log.i(str2, "ignore unregisterPropCallback for unregistered " + XpDebugLog.getPropertyDescription(id.intValue()));
                    } else {
                        callbacks.remove(callback);
                        if (callbacks.isEmpty()) {
                            this.mIdToCallbacksMap.remove(id.intValue());
                            unregister = true;
                            dataListenerToService = this.mSharedMemoryDataEventToService;
                        }
                        if (unregister && dataListenerToService != null) {
                            try {
                                if (XpDebugLog.CAR_DEBUG) {
                                    String str3 = this.mTag;
                                    Log.i(str3, "unregisterPropCallback for " + XpDebugLog.getPropertyDescription(id.intValue()));
                                }
                                this.mService.unregisterListener(id.intValue(), dataListenerToService);
                            } catch (RemoteException e) {
                                throw new CarNotConnectedException(e);
                            } catch (IllegalStateException e2) {
                                CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
                            }
                        } else {
                            String str4 = this.mTag;
                            Log.w(str4, "won't unregisterPropCallback for " + XpDebugLog.getPropertyDescription(id.intValue()));
                        }
                    }
                }
            }
            synchronized (this) {
                if (this.mIdToCallbacksMap.size() == 0) {
                    this.mSharedMemoryDataEventToService = null;
                }
            }
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        synchronized (this) {
            this.mIdToCallbacksMap.clear();
            this.mSharedMemoryDataEventToService = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEvent(CarPropertyValue<?> value) {
        this.mHandler.sendEvents(value);
    }

    public void sendData(int prop, byte[] data) throws Exception {
        Objects.requireNonNull(data);
        int length = data.length;
        if (length == 0) {
            Log.e(TAG, "data size is 0");
            throw new IllegalArgumentException("data size is 0");
        } else if (length > MAX_DATA_SIZE) {
            Log.e(TAG, "data is too large");
            throw new IllegalArgumentException("data is too large");
        } else {
            try {
                ParcelFileDescriptor pfd = ParcelFileDescriptor.fromData(data, null);
                if (pfd == null) {
                    Log.e(TAG, "Cannot fetch pfd");
                } else {
                    this.mService.queueMemoryBuffer(prop, pfd, length);
                }
                if (pfd != null) {
                    $closeResource(null, pfd);
                }
            } catch (Exception e) {
                Log.e(TAG, "send data using shared memory with exception" + e.toString());
                throw new IllegalStateException(e);
            }
        }
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 == null) {
            x1.close();
            return;
        }
        try {
            x1.close();
        } catch (Throwable th) {
            x0.addSuppressed(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SharedMemoryDataListenerToService extends ISharedMemoryDataListener.Stub {
        private static final String TAG = "CarSharedMemoryManager";
        private final WeakReference<CarSharedMemoryManager> mMgr;

        SharedMemoryDataListenerToService(CarSharedMemoryManager mgr) {
            this.mMgr = new WeakReference<>(mgr);
        }

        private static byte[] readFully(FileInputStream stream, int avail) throws IOException {
            int pos = 0;
            byte[] data = new byte[avail];
            while (pos < avail) {
                int amt = stream.read(data, pos, data.length - pos);
                if (amt <= 0) {
                    break;
                }
                pos += amt;
            }
            return data;
        }

        @Override // android.car.xpsharedmemory.ISharedMemoryDataListener
        public void onDataAvailable(long receiveTimestamp, long sendTimestamp, int prop, ParcelFileDescriptor pfd, int size) {
            ParcelFileDescriptor.AutoCloseInputStream inputStream;
            CarSharedMemoryManager manager = this.mMgr.get();
            if (manager == null) {
                Log.e(TAG, "manager is null");
            } else if (pfd != null && size > 0) {
                long start = SystemClock.uptimeMillis();
                boolean perfLogEnable = XpDebugLog.isSharedMemoryPerfLogEnable();
                long timeCost = start - sendTimestamp;
                if (timeCost > CarSharedMemoryManager.MAX_INTERVAL_MS_IN_HANDLER.toMillis()) {
                    Log.i(TAG, "onDataAvailable: " + XpDebugLog.getPropertyDescription(prop) + ", timestamp: " + receiveTimestamp + " carservice to app cost too much time: " + timeCost + " ms");
                } else if (perfLogEnable) {
                    Log.i(TAG, "onDataAvailable: " + XpDebugLog.getPropertyDescription(prop) + ", timestamp: " + receiveTimestamp + " carservice to app cost time: " + timeCost + " ms");
                }
                try {
                    pfd.seekTo(0L);
                } catch (Exception e) {
                    Log.e(TAG, "seek pfd failed: " + e.getMessage());
                }
                try {
                    inputStream = new ParcelFileDescriptor.AutoCloseInputStream(pfd);
                } catch (Exception e2) {
                    e = e2;
                }
                try {
                    try {
                        byte[] bytes = readFully(inputStream, size);
                        try {
                            CarPropertyValue<byte[]> value = new CarPropertyValue<>(prop, 0, 0, receiveTimestamp, bytes);
                            long end = SystemClock.uptimeMillis();
                            value.setExtraTimestamp(end);
                            manager.handleEvent(value);
                            long timeCost2 = end - start;
                            try {
                                if (timeCost2 > CarSharedMemoryManager.MAX_INTERVAL_MS.toMillis()) {
                                    Log.i(TAG, "read shared memory: " + XpDebugLog.getPropertyDescription(prop) + ", timestamp: " + receiveTimestamp + " cost too much time: " + timeCost2 + " ms");
                                } else if (perfLogEnable) {
                                    Log.i(TAG, "read shared memory: " + XpDebugLog.getPropertyDescription(prop) + ", timestamp: " + receiveTimestamp + "  cost time: " + timeCost2 + " ms");
                                }
                                inputStream.close();
                            } catch (Throwable th) {
                            }
                        } catch (Throwable th2) {
                        }
                    } catch (Throwable th3) {
                        throw th3;
                    }
                } catch (Exception e3) {
                    e = e3;
                    Log.e(TAG, "read pfd failed: " + e.toString());
                }
            } else {
                Log.e(TAG, "invalid pfd or size:" + size);
            }
        }
    }
}
