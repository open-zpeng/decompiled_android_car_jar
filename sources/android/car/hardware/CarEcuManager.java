package android.car.hardware;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.XpDebugLog;
import android.car.hardware.property.CarPropertyManager;
import android.car.hardware.shm.CarSharedMemoryManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.Rlog;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.annotations.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
/* loaded from: classes.dex */
public abstract class CarEcuManager implements CarManagerBase {
    private static final String AUTOPILOT_PACKAGE_NAME = "com.xiaopeng.autopilot";
    public static final int ERROR_CODE_SIGNAL_LOST = 6;
    public static final int FLAG_DO_NOT_CALLBACK_CACHED_DATA_WHEN_REGISTERING = 1;
    public static final int FLAG_IMMEDIATELY_CALLBACK_CACHED_DATA_WHEN_REGISTERING = 0;
    private static final boolean LOG_CALLBACK_COUNT = !Build.IS_USER;
    private static final Map<String, Boolean> kPackageName = new ConcurrentHashMap(1, 1.0f);
    private final CarPropertyManager mCarPropertyMgr;
    private final CarSharedMemoryManager mCarSharedMemoryMgr;
    protected final String mCduType;
    private final Context mContext;
    private final Map<Integer, AtomicLong> mIdToCallbackCountMap;
    @GuardedBy("this")
    @VisibleForTesting
    private final SparseArray<CopyOnWriteArrayList<CarEcuEventCallback>> mIdToCallbacksMap;
    @GuardedBy("mLock")
    private volatile boolean mIsPropConfigsFetched;
    private CarPropertyEventListenerToBase mListenerToBase;
    private final Object mLock;
    @GuardedBy("mLock")
    private volatile Set<Integer> mReadSupportedPropIds;
    @GuardedBy("mLock")
    private SparseArray<CarPropertyConfig> mSupportedPropConfigMaps;
    @GuardedBy("mLock")
    private volatile List<CarPropertyConfig> mSupportedPropConfigs;
    private final String mTag;

    /* loaded from: classes.dex */
    public interface CarEcuEventCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    protected abstract ArraySet<Integer> getPropertyIds();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CarPropertyEventListenerToBase implements CarPropertyManager.CarPropertyEventListener {
        private final WeakReference<CarEcuManager> mManager;

        public CarPropertyEventListenerToBase(CarEcuManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventListener
        public void onChangeEvent(CarPropertyValue value) {
            CarEcuManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnChangeEvent(value);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventListener
        public void onErrorEvent(int propertyId, int errorCode) {
            CarEcuManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnErrorEvent(propertyId, errorCode);
            }
        }
    }

    protected void handleOnChangeEvent(CarPropertyValue value) {
        CopyOnWriteArrayList<CarEcuEventCallback> callbacks;
        boolean perfLogEnable = XpDebugLog.isPerfLogEnable();
        if (perfLogEnable) {
            String str = this.mTag;
            Log.i(str, "++handleOnChangeEvent: " + value);
        }
        int propertyId = value.getPropertyId();
        if (LOG_CALLBACK_COUNT) {
            long count = this.mIdToCallbackCountMap.computeIfAbsent(Integer.valueOf(propertyId), new Function() { // from class: android.car.hardware.-$$Lambda$CarEcuManager$1MlyiV9caRjY5bThdITy6kcoRlg
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CarEcuManager.lambda$handleOnChangeEvent$0((Integer) obj);
                }
            }).incrementAndGet();
            if (count < 0) {
                this.mIdToCallbackCountMap.replace(Integer.valueOf(propertyId), new AtomicLong(1L));
                String str2 = this.mTag;
                Log.i(str2, "callback for " + XpDebugLog.getPropertyDescription(propertyId) + ", new count = 1");
            } else if (XpDebugLog.isCallBackDebugLogEnable(propertyId)) {
                String str3 = this.mTag;
                Log.i(str3, "callback for " + XpDebugLog.getPropertyDescription(propertyId) + ", count = " + count);
            }
        }
        synchronized (this) {
            callbacks = this.mIdToCallbacksMap.get(propertyId);
        }
        if (callbacks == null) {
            String str4 = this.mTag;
            Log.w(str4, "handleOnChangeEvent no callback for " + value);
            return;
        }
        Iterator<CarEcuEventCallback> it = callbacks.iterator();
        while (it.hasNext()) {
            CarEcuEventCallback l = it.next();
            if (perfLogEnable) {
                String str5 = this.mTag;
                Log.i(str5, "++++handleOnChangeEvent: " + value + " callback:" + l);
            }
            l.onChangeEvent(value);
            if (perfLogEnable) {
                String str6 = this.mTag;
                Log.i(str6, "----handleOnChangeEvent: " + value + " callback:" + l);
            }
        }
        if (perfLogEnable) {
            String str7 = this.mTag;
            Log.i(str7, "--handleOnChangeEvent: " + value);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AtomicLong lambda$handleOnChangeEvent$0(Integer p) {
        return new AtomicLong();
    }

    protected void handleOnErrorEvent(int propertyId, int errorCode) {
        CopyOnWriteArrayList<CarEcuEventCallback> callbacks;
        boolean perfLogEnable = XpDebugLog.isPerfLogEnable();
        if (perfLogEnable) {
            String str = this.mTag;
            Log.i(str, "++handleOnErrorEvent " + XpDebugLog.getPropertyDescription(propertyId) + " errorCode:" + errorCode);
        }
        synchronized (this) {
            callbacks = this.mIdToCallbacksMap.get(propertyId);
        }
        if (callbacks == null) {
            String str2 = this.mTag;
            Log.w(str2, "handleOnErrorEvent no callbacks for " + XpDebugLog.getPropertyDescription(propertyId) + " errorCode:" + errorCode);
            return;
        }
        Iterator<CarEcuEventCallback> it = callbacks.iterator();
        while (it.hasNext()) {
            CarEcuEventCallback l = it.next();
            if (perfLogEnable) {
                String str3 = this.mTag;
                Log.i(str3, "++++handleOnErrorEvent " + XpDebugLog.getPropertyDescription(propertyId) + " errorCode:" + errorCode + " callback: " + l);
            }
            l.onErrorEvent(propertyId, errorCode);
            if (perfLogEnable) {
                String str4 = this.mTag;
                Log.i(str4, "----handleOnErrorEvent " + XpDebugLog.getPropertyDescription(propertyId) + " errorCode:" + errorCode + " callback: " + l);
            }
        }
        if (perfLogEnable) {
            String str5 = this.mTag;
            Log.i(str5, "--handleOnErrorEvent propertyId: " + propertyId + " errorCode:" + errorCode);
        }
    }

    public CarEcuManager(IBinder service, Context context, Handler handler, boolean dbg, String tag) {
        this.mListenerToBase = null;
        this.mLock = new Object();
        this.mSupportedPropConfigMaps = null;
        this.mSupportedPropConfigs = null;
        this.mReadSupportedPropIds = null;
        this.mIsPropConfigsFetched = false;
        this.mCduType = Car.getXpCduType();
        this.mIdToCallbacksMap = new SparseArray<>();
        this.mIdToCallbackCountMap = new ConcurrentHashMap();
        this.mTag = tag;
        this.mCarPropertyMgr = new CarPropertyManager(service, handler, dbg, tag);
        this.mCarSharedMemoryMgr = null;
        this.mContext = context;
        kPackageName.computeIfAbsent("pkgName", new Function() { // from class: android.car.hardware.-$$Lambda$CarEcuManager$UaOtEfe-IVwWrsxbYW3LSvSbio4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                CarEcuManager carEcuManager = CarEcuManager.this;
                String str = (String) obj;
                valueOf = Boolean.valueOf(r2.mContext != null && Objects.equals(CarEcuManager.AUTOPILOT_PACKAGE_NAME, r2.mContext.getPackageName()));
                return valueOf;
            }
        });
    }

    protected Context getContext() {
        return this.mContext;
    }

    public CarEcuManager(IBinder propertyService, IBinder sharedMemoryService, Context context, Handler handler, boolean dbg, String tag) {
        this.mListenerToBase = null;
        this.mLock = new Object();
        this.mSupportedPropConfigMaps = null;
        this.mSupportedPropConfigs = null;
        this.mReadSupportedPropIds = null;
        this.mIsPropConfigsFetched = false;
        this.mCduType = Car.getXpCduType();
        this.mIdToCallbacksMap = new SparseArray<>();
        this.mIdToCallbackCountMap = new ConcurrentHashMap();
        this.mTag = tag;
        this.mCarPropertyMgr = new CarPropertyManager(propertyService, handler, dbg, tag);
        this.mCarSharedMemoryMgr = new CarSharedMemoryManager(sharedMemoryService, handler, dbg, tag);
        this.mCarSharedMemoryMgr.setSharedMemoryPropertyIds(getSharedMemoryIds());
        this.mContext = context;
        kPackageName.computeIfAbsent("pkgName", new Function() { // from class: android.car.hardware.-$$Lambda$CarEcuManager$8OafObwewPvpkdmPiuNz2wOm_ow
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                CarEcuManager carEcuManager = CarEcuManager.this;
                String str = (String) obj;
                valueOf = Boolean.valueOf(r2.mContext != null && Objects.equals(CarEcuManager.AUTOPILOT_PACKAGE_NAME, r2.mContext.getPackageName()));
                return valueOf;
            }
        });
    }

    @Deprecated
    public void registerCallback(CarEcuEventCallback callback) throws CarNotConnectedException {
        Rlog.e(this.mTag, "registerPropCallback(callback) is not recommended ", new RuntimeException("car-sdk"));
        registerPropCallback(getReadSupportedPropIds(), callback);
        if (this.mCarSharedMemoryMgr != null) {
            this.mCarSharedMemoryMgr.registerPropCallback(getSharedMemoryIds(), callback);
        }
    }

    private boolean isPropertyAllowRegister(CarPropertyConfig c) {
        return ((c.getAccess() & 1) == 0 || c.getChangeMode() == 0) ? false : true;
    }

    public void unregisterCallback(CarEcuEventCallback callback) throws CarNotConnectedException {
        unregisterPropCallback(getReadSupportedPropIds(), callback);
        if (this.mCarSharedMemoryMgr != null) {
            this.mCarSharedMemoryMgr.unregisterCallback(callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendDataWithSharedMemory(int prop, byte[] data) throws Exception {
        if (this.mCarSharedMemoryMgr != null) {
            this.mCarSharedMemoryMgr.sendData(prop, data);
        }
    }

    public void registerPropCallback(Collection<Integer> ids, CarEcuEventCallback callback) throws CarNotConnectedException {
        registerPropCallbackWithFlag(ids, callback, 0);
    }

    private static String idsToString(Collection<Integer> ids) {
        if (ids == null) {
            return "null";
        }
        Iterator<Integer> it = ids.iterator();
        if (!it.hasNext()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (true) {
            int e = it.next().intValue();
            sb.append(XpDebugLog.getPropertyDescription(e));
            if (!it.hasNext()) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(',');
            sb.append(' ');
        }
    }

    private static String configsToString(Collection<CarPropertyConfig> ids) {
        if (ids == null) {
            return "null";
        }
        Iterator<CarPropertyConfig> it = ids.iterator();
        if (!it.hasNext()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (true) {
            CarPropertyConfig e = it.next();
            sb.append(XpDebugLog.getPropertyDescription(e.getPropertyId()));
            if (!it.hasNext()) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(',');
            sb.append(' ');
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x011f, code lost:
        if (isPropertyAllowRegister(r7) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0129, code lost:
        if (tryHandleAutoPilotAppRegisterFailed(r13, r4.intValue()) == false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x012b, code lost:
        r6 = r12.mTag;
        android.telephony.Rlog.w(r6, "register prop: " + r4 + "(0x" + java.lang.Integer.toHexString(r4.intValue()) + ") is not allowed");
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0158, code lost:
        r6 = r12.mTag;
        android.util.Log.w(r6, "register prop: " + r4 + "(0x" + java.lang.Integer.toHexString(r4.intValue()) + ") is not allowed");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0185, code lost:
        r6 = false;
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0187, code lost:
        monitor-enter(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0188, code lost:
        r8 = r12.mIdToCallbacksMap.get(r4.intValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0194, code lost:
        if (r8 != null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0196, code lost:
        r8 = new java.util.concurrent.CopyOnWriteArrayList<>();
        r12.mIdToCallbacksMap.put(r4.intValue(), r8);
        r6 = true;
        r7 = r12.mListenerToBase;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01ad, code lost:
        if (r8.contains(r14) != false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01af, code lost:
        r8.add(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01b2, code lost:
        if (r0 == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01b4, code lost:
        r9 = r12.mTag;
        android.util.Log.i(r9, "registerPropCallback for registered id=" + r4 + "; desc=" + android.car.XpDebugLog.getPropertyDescription(r4.intValue()) + "; size=" + r8.size());
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01e6, code lost:
        monitor-exit(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01e7, code lost:
        if (r6 == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01e9, code lost:
        if (r7 == null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01eb, code lost:
        if (r0 == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01ed, code lost:
        r8 = r12.mTag;
        android.util.Log.i(r8, "registerPropCallback for prop " + android.car.XpDebugLog.getPropertyDescription(r4.intValue()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x020b, code lost:
        r8 = r12.mCarPropertyMgr.registerListener(r7, r4.intValue(), -r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0219, code lost:
        if (r8 != false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0223, code lost:
        if (tryHandleAutoPilotAppRegisterFailed(r13, r4.intValue()) == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0225, code lost:
        r9 = r12.mTag;
        android.telephony.Rlog.w(r9, "call registerCallback failed for prop: " + android.car.XpDebugLog.getPropertyDescription(r4.intValue()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0245, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x024e, code lost:
        if (tryHandleAutoPilotAppRegisterFailed(r13, r4.intValue()) != false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0250, code lost:
        r8 = r12.mTag;
        android.telephony.Rlog.w(r8, "call registerCallback failed for prop: " + android.car.XpDebugLog.getPropertyDescription(r4.intValue()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x026e, code lost:
        throw r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0277, code lost:
        if (tryHandleAutoPilotAppRegisterFailed(r13, r4.intValue()) == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0279, code lost:
        r8 = r12.mTag;
        android.telephony.Rlog.w(r8, "listenerToBase is null for prop " + android.car.XpDebugLog.getPropertyDescription(r4.intValue()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0298, code lost:
        r8 = r12.mTag;
        android.util.Log.w(r8, "listenerToBase is null for prop " + android.car.XpDebugLog.getPropertyDescription(r4.intValue()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x02bf, code lost:
        if (tryHandleAutoPilotAppRegisterFailed(r13, r4.intValue()) == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x02c1, code lost:
        r8 = r12.mTag;
        android.telephony.Rlog.w(r8, "won't register the registered prop " + android.car.XpDebugLog.getPropertyDescription(r4.intValue()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x02e0, code lost:
        r8 = r12.mTag;
        android.util.Log.w(r8, "won't register the registered prop " + android.car.XpDebugLog.getPropertyDescription(r4.intValue()));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void registerPropCallbackWithFlag(java.util.Collection<java.lang.Integer> r13, android.car.hardware.CarEcuManager.CarEcuEventCallback r14, int r15) throws android.car.CarNotConnectedException {
        /*
            Method dump skipped, instructions count: 883
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.car.hardware.CarEcuManager.registerPropCallbackWithFlag(java.util.Collection, android.car.hardware.CarEcuManager$CarEcuEventCallback, int):void");
    }

    private boolean tryHandleAutoPilotAppRegisterFailed(Collection<Integer> ids, int id) {
        boolean result = isMonitoredAutoPilotAppId(id);
        if (result) {
            String str = this.mTag;
            Rlog.w(str, "AutoPilot app register: " + idsToString(ids));
        }
        return result;
    }

    private void tryLogAutoPilotAppRegisterIds(Collection<Integer> ids) {
        boolean result = isMonitoredAutoPilotAppIds(ids);
        if (result) {
            String str = this.mTag;
            Rlog.w(str, "AutoPilot app register: " + idsToString(ids));
        }
    }

    private boolean isMonitoredAutoPilotAppId(int id) {
        return kPackageName.get("pkgName").booleanValue() && (id == 557847561 || id == 557852187);
    }

    private boolean isMonitoredAutoPilotAppIds(Collection<Integer> ids) {
        return ids != null && kPackageName.get("pkgName").booleanValue() && (ids.contains(557847561) || ids.contains(557852187));
    }

    public void unregisterPropCallback(Collection<Integer> ids, CarEcuEventCallback callback) throws CarNotConnectedException {
        if (ids == null) {
            Log.w(this.mTag, "unregisterPropCallback ids is null");
            throw new IllegalArgumentException("input data cannot be null");
        } else if (callback == null) {
            Log.w(this.mTag, "unregisterPropCallback callback is null");
            throw new IllegalArgumentException("input data cannot be null");
        } else {
            boolean callBackLogEnable = XpDebugLog.isCallBackDebugLogEnable(0);
            if (callBackLogEnable) {
                String str = this.mTag;
                Log.i(str, "unregisterPropCallback: " + callback);
            }
            Set<Integer> allSharedMemoryIds = getSharedMemoryIds();
            Set<Integer> sharedMemoryIds = null;
            boolean callBackDebugLogEnable = XpDebugLog.isCallBackDebugLogEnable(0);
            for (Integer id : ids) {
                if (allSharedMemoryIds != null && allSharedMemoryIds.contains(id)) {
                    if (sharedMemoryIds == null) {
                        sharedMemoryIds = new ArraySet<>(allSharedMemoryIds.size());
                    }
                    sharedMemoryIds.add(id);
                } else {
                    boolean unregister = false;
                    CarPropertyEventListenerToBase listenerToBase = null;
                    synchronized (this) {
                        CopyOnWriteArrayList<CarEcuEventCallback> callbacks = this.mIdToCallbacksMap.get(id.intValue());
                        if (callbacks == null) {
                            String str2 = this.mTag;
                            Log.i(str2, "ignore unregisterPropCallback for unregistered " + XpDebugLog.getPropertyDescription(id.intValue()));
                        } else {
                            if (callBackDebugLogEnable) {
                                String str3 = this.mTag;
                                Log.i(str3, "unregisterPropCallback for unregistered id=" + id + "; desc=" + XpDebugLog.getPropertyDescription(id.intValue()) + "; size=" + callbacks.size());
                            }
                            callbacks.remove(callback);
                            if (callbacks.isEmpty()) {
                                this.mIdToCallbacksMap.remove(id.intValue());
                                unregister = true;
                                listenerToBase = this.mListenerToBase;
                            }
                            if (unregister) {
                                if (listenerToBase != null) {
                                    if (callBackLogEnable) {
                                        String str4 = this.mTag;
                                        Log.i(str4, "unregisterPropCallback " + XpDebugLog.getPropertyDescription(id.intValue()));
                                    }
                                    this.mCarPropertyMgr.unregisterListener(listenerToBase, id.intValue());
                                } else {
                                    String str5 = this.mTag;
                                    Log.w(str5, "listenerToBase is null for " + XpDebugLog.getPropertyDescription(id.intValue()));
                                }
                            } else {
                                String str6 = this.mTag;
                                Log.w(str6, "won't unregisterPropCallback for " + XpDebugLog.getPropertyDescription(id.intValue()));
                            }
                        }
                    }
                }
            }
            synchronized (this) {
                if (this.mIdToCallbacksMap.size() == 0) {
                    this.mListenerToBase = null;
                }
            }
            if (sharedMemoryIds != null && this.mCarSharedMemoryMgr != null) {
                this.mCarSharedMemoryMgr.unregisterPropCallback(sharedMemoryIds, callback);
            }
        }
    }

    private void initSupportedPropConfigs() throws CarNotConnectedException {
        if (!this.mIsPropConfigsFetched) {
            synchronized (this.mLock) {
                if (!this.mIsPropConfigsFetched) {
                    ArraySet<Integer> propertyIds = getPropertyIds();
                    List<CarPropertyConfig> supportedPropConfigs = this.mCarPropertyMgr.getPropertyList(propertyIds);
                    int size = supportedPropConfigs.size();
                    this.mReadSupportedPropIds = new ArraySet(size);
                    if (size > 0) {
                        this.mSupportedPropConfigs = supportedPropConfigs;
                        this.mSupportedPropConfigMaps = new SparseArray<>(size);
                        for (CarPropertyConfig c : this.mSupportedPropConfigs) {
                            int propertyId = c.getPropertyId();
                            this.mSupportedPropConfigMaps.put(propertyId, c);
                            if (isPropertyAllowRegister(c)) {
                                this.mReadSupportedPropIds.add(Integer.valueOf(propertyId));
                            }
                        }
                        this.mIsPropConfigsFetched = true;
                    }
                    if (!this.mIsPropConfigsFetched) {
                        String str = this.mTag;
                        Rlog.w(str, "SupportedPropConfigs is " + configsToString(this.mSupportedPropConfigs));
                    }
                }
            }
        }
    }

    private Set<Integer> getReadSupportedPropIds() throws CarNotConnectedException {
        initSupportedPropConfigs();
        return this.mReadSupportedPropIds;
    }

    @SystemApi
    public List<CarPropertyConfig> getPropertyList() throws CarNotConnectedException {
        initSupportedPropConfigs();
        if (this.mSupportedPropConfigs == null) {
            return null;
        }
        return Collections.unmodifiableList(this.mSupportedPropConfigs);
    }

    @SystemApi
    public boolean isPropertyAvailable(int propertyId, int area) throws CarNotConnectedException {
        return this.mCarPropertyMgr.isPropertyAvailable(propertyId, area);
    }

    @SystemApi
    public boolean isPropertySupported(int propertyId) throws CarNotConnectedException {
        return this.mCarPropertyMgr.isPropertySupported(propertyId);
    }

    @SystemApi
    public boolean isPropertySupportedOrDefault(int propertyId, boolean defaultValue) {
        try {
            return this.mCarPropertyMgr.isPropertySupported(propertyId);
        } catch (CarNotConnectedException e) {
            return defaultValue;
        }
    }

    @SystemApi
    public boolean getBooleanProperty(int propertyId, int area) throws CarNotConnectedException {
        return this.mCarPropertyMgr.getBooleanProperty(propertyId, area);
    }

    @SystemApi
    public float getFloatProperty(int propertyId, int area) throws CarNotConnectedException {
        return this.mCarPropertyMgr.getFloatProperty(propertyId, area);
    }

    @SystemApi
    public int getIntProperty(int propertyId, int area) throws CarNotConnectedException {
        return this.mCarPropertyMgr.getIntProperty(propertyId, area);
    }

    @SystemApi
    public String getStringProperty(int propertyId, int area) throws CarNotConnectedException {
        return this.mCarPropertyMgr.getStringProperty(propertyId, area);
    }

    @SystemApi
    public int[] getIntVectorProperty(int propertyId, int area) throws CarNotConnectedException {
        return this.mCarPropertyMgr.getIntVectorProperty(propertyId, area);
    }

    @SystemApi
    public byte[] getByteVectorProperty(int propertyId, int area) throws CarNotConnectedException {
        return this.mCarPropertyMgr.getByteVectorProperty(propertyId, area);
    }

    @SystemApi
    public void setBooleanProperty(int propertyId, int area, boolean val) throws CarNotConnectedException {
        if (getPropertyIds().contains(Integer.valueOf(propertyId))) {
            this.mCarPropertyMgr.setBooleanProperty(propertyId, area, val);
        }
    }

    @SystemApi
    public void setFloatProperty(int propertyId, int area, float val) throws CarNotConnectedException {
        if (getPropertyIds().contains(Integer.valueOf(propertyId))) {
            this.mCarPropertyMgr.setFloatProperty(propertyId, area, val);
        }
    }

    @SystemApi
    public void setIntProperty(int propertyId, int area, int val) throws CarNotConnectedException {
        if (getPropertyIds().contains(Integer.valueOf(propertyId))) {
            this.mCarPropertyMgr.setIntProperty(propertyId, area, val);
        }
    }

    @SystemApi
    public void setStringProperty(int propertyId, int area, String val) throws CarNotConnectedException {
        if (getPropertyIds().contains(Integer.valueOf(propertyId))) {
            this.mCarPropertyMgr.setStringProperty(propertyId, area, val);
        }
    }

    @SystemApi
    public void setIntVectorProperty(int propertyId, int area, int[] val) throws CarNotConnectedException {
        if (getPropertyIds().contains(Integer.valueOf(propertyId))) {
            this.mCarPropertyMgr.setIntVectorProperty(propertyId, area, val);
        }
    }

    @SystemApi
    public void setByteVectorProperty(int propertyId, int area, byte[] val) throws CarNotConnectedException {
        if (getPropertyIds().contains(Integer.valueOf(propertyId))) {
            this.mCarPropertyMgr.setByteVectorProperty(propertyId, area, val);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        synchronized (this) {
            this.mIdToCallbacksMap.clear();
            if (this.mCarSharedMemoryMgr != null) {
                this.mCarSharedMemoryMgr.onCarDisconnected();
            }
            if (this.mCarPropertyMgr != null) {
                this.mCarPropertyMgr.onCarDisconnected();
            }
        }
    }

    protected Set<Integer> getSharedMemoryIds() {
        return null;
    }

    @SystemApi
    @Deprecated
    public void setMultiProperty(LinkedList<CarPropertyValue> props) throws Exception {
        Objects.requireNonNull(props);
        this.mCarPropertyMgr.setMultiProperties(props);
    }

    @SystemApi
    public void setMultipleProperties(List<CarPropertyValue> props) throws Exception {
        Objects.requireNonNull(props);
        this.mCarPropertyMgr.setMultiProperties(props);
    }
}
