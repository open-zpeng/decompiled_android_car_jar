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
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.SystemClock;
import android.telephony.Rlog;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
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
public class CarSharedMemoryManager implements CarManagerBase {
    private static final int MAX_DATA_SIZE = 10485760;
    private static final int MSG_GENERIC_EVENT = 0;
    private static final String TAG = "CarSharedMemoryManager";
    private final boolean mDbg;
    @GuardedBy("mLock")
    private final SingleRawMessageHandler<CarPropertyValue<?>> mHandler;
    private final IXpSharedMemory mService;
    private SharedMemoryDataListenerToService mSharedMemoryDataEventToService;
    private Set<Integer> mSharedMemoryPropertyIds;
    private volatile Map<Integer, CarPropertyConfig<?>> mSupportedPropConfigs;
    private final String mTag;
    private static final Object mLock = new Object();
    private static final Duration MAX_INTERVAL_MS = Duration.ofMillis(40);
    private static final Duration MAX_INTERVAL_MS_IN_HANDLER = Duration.ofMillis(20);
    private final SparseArray<CopyOnWriteArrayList<CarEcuManager.CarEcuEventCallback>> mIdToCallbacksMap = new SparseArray<>();
    private final Map<Integer, AtomicLong> mIdToCallbackCountMap = new ConcurrentHashMap();
    private final HandlerThread mHandlerThread = new HandlerThread("shared memory callback", -19);

    static /* synthetic */ Duration access$000() {
        return MAX_INTERVAL_MS_IN_HANDLER;
    }

    static /* synthetic */ void access$400(CarSharedMemoryManager x0, CarPropertyValue x1) {
        x0.handleEvent(x1);
    }

    static /* synthetic */ Duration access$500() {
        return MAX_INTERVAL_MS;
    }

    @SystemApi
    public CarSharedMemoryManager(IBinder service, Handler handler, boolean dbg, String tag) {
        this.mDbg = dbg;
        this.mTag = tag;
        this.mService = IXpSharedMemory.Stub.asInterface(service);
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
                        CopyOnWriteArrayList<CarEcuManager.CarEcuEventCallback> callbacks = (CopyOnWriteArrayList) CarSharedMemoryManager.this.mIdToCallbacksMap.get(propertyId);
                        try {
                            if (callbacks == null) {
                                return;
                            }
                            Iterator<CarEcuManager.CarEcuEventCallback> it = callbacks.iterator();
                            while (it.hasNext()) {
                                CarEcuManager.CarEcuEventCallback l = it.next();
                                if (l == null) {
                                    String str3 = CarSharedMemoryManager.this.mTag;
                                    Log.w(str3, "++++handleEvent: " + XpDebugLog.getPropertyDescription(propertyId) + " callback is null");
                                } else {
                                    if (perfLogEnable) {
                                        String str4 = CarSharedMemoryManager.this.mTag;
                                        Log.i(str4, "++++handleEvent: " + XpDebugLog.getPropertyDescription(propertyId) + ", timestamp: " + timestamp + " callback:" + l);
                                    }
                                    long start = SystemClock.uptimeMillis();
                                    try {
                                        l.onChangeEvent(value);
                                        long timeCost2 = SystemClock.uptimeMillis() - start;
                                        if (timeCost2 > CarSharedMemoryManager.MAX_INTERVAL_MS_IN_HANDLER.toMillis()) {
                                            extraTimestamp = extraTimestamp2;
                                            try {
                                                Log.w(SingleRawMessageHandler.TAG, "handle: " + XpDebugLog.getPropertyDescription(propertyId) + ", timestamp:" + timestamp + " callback cost too much time:" + timeCost2 + " ms");
                                            } catch (Throwable th) {
                                                th = th;
                                                throw th;
                                            }
                                        } else {
                                            extraTimestamp = extraTimestamp2;
                                            if (perfLogEnable) {
                                                Log.i(SingleRawMessageHandler.TAG, "handle: " + XpDebugLog.getPropertyDescription(propertyId) + ", timestamp:" + timestamp + " callback cost " + timeCost2 + " ms");
                                            }
                                        }
                                        extraTimestamp2 = extraTimestamp;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        throw th;
                                    }
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
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
                    this.mSupportedPropConfigs = new ArrayMap(configs.size());
                    for (CarPropertyConfig c : configs) {
                        this.mSupportedPropConfigs.put(Integer.valueOf(c.getPropertyId()), c);
                    }
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
                Rlog.w(this.mTag, "no supported shared memory properties");
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
                if (pfd != null) {
                    this.mService.queueMemoryBuffer(prop, pfd, length);
                } else {
                    Log.e(TAG, "Cannot fetch pfd");
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

        /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
            jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
            	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
            	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
            */
        @Override // android.car.xpsharedmemory.ISharedMemoryDataListener
        public void onDataAvailable(long r24, long r26, int r28, android.os.ParcelFileDescriptor r29, int r30) {
            /*
                Method dump skipped, instructions count: 481
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.car.hardware.shm.CarSharedMemoryManager.SharedMemoryDataListenerToService.onDataAvailable(long, long, int, android.os.ParcelFileDescriptor, int):void");
        }
    }
}
