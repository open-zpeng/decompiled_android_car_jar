package android.car.cluster.renderer;

import android.annotation.SystemApi;
import android.app.ActivityOptions;
import android.app.Service;
import android.car.Car;
import android.car.cluster.ClusterActivityState;
import android.car.cluster.renderer.IInstrumentCluster;
import android.car.cluster.renderer.IInstrumentClusterHelper;
import android.car.cluster.renderer.IInstrumentClusterNavigation;
import android.car.cluster.renderer.InstrumentClusterRenderingService;
import android.car.hardware.property.CarPropertyManager;
import android.car.navigation.CarNavigationInstrumentCluster;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.util.LruCache;
import android.view.KeyEvent;
import com.android.internal.annotations.GuardedBy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SystemApi
/* loaded from: classes.dex */
public abstract class InstrumentClusterRenderingService extends Service {
    private static final String BITMAP_QUERY_HEIGHT = "h";
    private static final String BITMAP_QUERY_OFFLANESALPHA = "offLanesAlpha";
    private static final String BITMAP_QUERY_WIDTH = "w";
    public static final String EXTRA_BUNDLE_KEY_FOR_INSTRUMENT_CLUSTER_HELPER = "android.car.cluster.renderer.IInstrumentClusterHelper";
    private static final int IMAGE_CACHE_SIZE_BYTES = 4194304;
    @Deprecated
    private static final int NAVIGATION_STATE_EVENT_ID = 1;
    private static final String TAG = "CAR.L.CLUSTER";
    private ActivityOptions mActivityOptions;
    private ClusterActivityState mActivityState;
    @GuardedBy({"mLock"})
    private IInstrumentClusterHelper mInstrumentClusterHelper;
    @GuardedBy({"mLock"})
    private ContextOwner mNavContextOwner;
    private ComponentName mNavigationComponent;
    private RendererBinder mRendererBinder;
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());
    private final Object mLock = new Object();
    private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(4194304) { // from class: android.car.cluster.renderer.InstrumentClusterRenderingService.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    };

    public abstract NavigationRenderer getNavigationRenderer();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ContextOwner {
        final Set<String> mAuthorities;
        final Set<String> mPackageNames;
        final int mPid;
        final int mUid;

        ContextOwner(int uid, int pid, final PackageManager packageManager) {
            Set<String> emptySet;
            this.mUid = uid;
            this.mPid = pid;
            String[] packageNames = uid != 0 ? packageManager.getPackagesForUid(uid) : null;
            if (packageNames != null) {
                emptySet = Collections.unmodifiableSet(new HashSet(Arrays.asList(packageNames)));
            } else {
                emptySet = Collections.emptySet();
            }
            this.mPackageNames = emptySet;
            this.mAuthorities = Collections.unmodifiableSet((Set) this.mPackageNames.stream().map(new Function() { // from class: android.car.cluster.renderer.-$$Lambda$InstrumentClusterRenderingService$ContextOwner$G_V8CE2R_HiXVhrIYEeuqJR9Ghk
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstrumentClusterRenderingService.ContextOwner.this.lambda$new$0$InstrumentClusterRenderingService$ContextOwner(packageManager, (String) obj);
                }
            }).flatMap(new Function() { // from class: android.car.cluster.renderer.-$$Lambda$seyL25CSW2NInOydsTbSDrNW6pM
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((List) obj).stream();
                }
            }).collect(Collectors.toSet()));
        }

        public String toString() {
            return "{uid: " + this.mUid + ", pid: " + this.mPid + ", packagenames: " + this.mPackageNames + ", authorities: " + this.mAuthorities + "}";
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getAuthoritiesForPackage */
        public List<String> lambda$new$0$InstrumentClusterRenderingService$ContextOwner(PackageManager packageManager, String packageName) {
            try {
                ProviderInfo[] providers = packageManager.getPackageInfo(packageName, 8).providers;
                if (providers == null) {
                    return Collections.emptyList();
                }
                return (List) Arrays.stream(providers).map(new Function() { // from class: android.car.cluster.renderer.-$$Lambda$InstrumentClusterRenderingService$ContextOwner$sb7STAn9Q2djz0EjdJOzvyJjIRk
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        String str;
                        str = ((ProviderInfo) obj).authority;
                        return str;
                    }
                }).collect(Collectors.toList());
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("CAR.L.CLUSTER", "Package name not found while retrieving content provider authorities: " + packageName);
                return Collections.emptyList();
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
            Log.d("CAR.L.CLUSTER", "onBind, intent: " + intent);
        }
        Bundle bundle = intent.getBundleExtra(EXTRA_BUNDLE_KEY_FOR_INSTRUMENT_CLUSTER_HELPER);
        IBinder binder = null;
        if (bundle != null) {
            binder = bundle.getBinder(EXTRA_BUNDLE_KEY_FOR_INSTRUMENT_CLUSTER_HELPER);
        }
        if (binder == null) {
            Log.wtf("CAR.L.CLUSTER", "IInstrumentClusterHelper not passed through binder");
        } else {
            synchronized (this.mLock) {
                this.mInstrumentClusterHelper = IInstrumentClusterHelper.Stub.asInterface(binder);
            }
        }
        if (this.mRendererBinder == null) {
            this.mRendererBinder = new RendererBinder(getNavigationRenderer());
        }
        return this.mRendererBinder;
    }

    public void onKeyEvent(KeyEvent keyEvent) {
    }

    public void onNavigationComponentLaunched() {
    }

    public void onNavigationComponentReleased() {
    }

    private IInstrumentClusterHelper getClusterHelper() {
        IInstrumentClusterHelper iInstrumentClusterHelper;
        synchronized (this.mLock) {
            if (this.mInstrumentClusterHelper == null) {
                Log.w("mInstrumentClusterHelper still null, should wait until onBind", new RuntimeException());
            }
            iInstrumentClusterHelper = this.mInstrumentClusterHelper;
        }
        return iInstrumentClusterHelper;
    }

    protected boolean startFixedActivityModeForDisplayAndUser(Intent intent, ActivityOptions options, int userId) {
        IInstrumentClusterHelper helper = getClusterHelper();
        if (helper == null) {
            return false;
        }
        try {
            return helper.startFixedActivityModeForDisplayAndUser(intent, options.toBundle(), userId);
        } catch (RemoteException e) {
            Log.w("Remote exception from car service", e);
            return false;
        }
    }

    protected void stopFixedActivityMode(int displayId) {
        IInstrumentClusterHelper helper = getClusterHelper();
        if (helper == null) {
            return;
        }
        try {
            helper.stopFixedActivityMode(displayId);
        } catch (RemoteException e) {
            Log.w("Remote exception from car service, displayId:" + displayId, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNavigationActivity() {
        ClusterActivityState clusterActivityState;
        ContextOwner contextOwner = getNavigationContextOwner();
        if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
            Log.d("CAR.L.CLUSTER", String.format("updateNavigationActivity (mActivityOptions: %s, mActivityState: %s, mNavContextOwnerUid: %s)", this.mActivityOptions, this.mActivityState, contextOwner));
        }
        if (contextOwner == null || contextOwner.mUid == 0 || this.mActivityOptions == null || (clusterActivityState = this.mActivityState) == null || !clusterActivityState.isVisible()) {
            if (this.mNavigationComponent != null) {
                this.mNavigationComponent = null;
                onNavigationComponentReleased();
                return;
            }
            return;
        }
        ComponentName component = getNavigationComponentByOwner(contextOwner);
        if (Objects.equals(this.mNavigationComponent, component)) {
            if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                Log.d("CAR.L.CLUSTER", "Already launched component: " + component);
            }
        } else if (component == null) {
            if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                Log.d("CAR.L.CLUSTER", "No component found for owner: " + contextOwner);
            }
        } else if (!startNavigationActivity(component)) {
            if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                Log.d("CAR.L.CLUSTER", "Unable to launch component: " + component);
            }
        } else {
            this.mNavigationComponent = component;
            onNavigationComponentLaunched();
        }
    }

    private ComponentName getNavigationComponentByOwner(ContextOwner contextOwner) {
        for (String packageName : contextOwner.mPackageNames) {
            ComponentName component = getComponentFromPackage(packageName);
            if (component != null) {
                if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                    Log.d("CAR.L.CLUSTER", "Found component: " + component);
                }
                return component;
            }
        }
        return null;
    }

    private ContextOwner getNavigationContextOwner() {
        ContextOwner contextOwner;
        synchronized (this.mLock) {
            contextOwner = this.mNavContextOwner;
        }
        return contextOwner;
    }

    private ComponentName getComponentFromPackage(String packageName) {
        PackageManager packageManager = getPackageManager();
        if (packageManager.checkPermission(Car.PERMISSION_CAR_DISPLAY_IN_CLUSTER, packageName) != 0) {
            Log.i("CAR.L.CLUSTER", String.format("Package '%s' doesn't have permission %s", packageName, Car.PERMISSION_CAR_DISPLAY_IN_CLUSTER));
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN").addCategory("android.car.cluster.NAVIGATION").setPackage(packageName);
        List<ResolveInfo> resolveList = packageManager.queryIntentActivities(intent, 64);
        if (resolveList == null || resolveList.isEmpty() || resolveList.get(0).getComponentInfo() == null) {
            Log.i("CAR.L.CLUSTER", "Failed to resolve an intent: " + intent);
            return null;
        }
        return resolveList.get(0).getComponentInfo().getComponentName();
    }

    protected boolean startNavigationActivity(ComponentName component) {
        Intent intent = new Intent();
        intent.setComponent(component);
        intent.putExtra("android.car.cluster.ClusterActivityState", this.mActivityState.toBundle());
        intent.addFlags(268435456);
        try {
            startActivityAsUser(intent, this.mActivityOptions.toBundle(), UserHandle.CURRENT);
            Log.i("CAR.L.CLUSTER", String.format("Activity launched: %s (options: %s, displayId: %d)", this.mActivityOptions, intent, Integer.valueOf(this.mActivityOptions.getLaunchDisplayId())));
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w("CAR.L.CLUSTER", "Unable to find activity for intent: " + intent);
            return false;
        } catch (Exception ex) {
            Log.e("CAR.L.CLUSTER", "Error trying to launch intent: " + intent + ". Ignored", ex);
            return false;
        }
    }

    @Deprecated
    public void setClusterActivityLaunchOptions(String category, ActivityOptions activityOptions) {
        setClusterActivityLaunchOptions(activityOptions);
    }

    public void setClusterActivityLaunchOptions(ActivityOptions activityOptions) {
        this.mActivityOptions = activityOptions;
        updateNavigationActivity();
    }

    @Deprecated
    public void setClusterActivityState(String category, Bundle state) {
        setClusterActivityState(ClusterActivityState.fromBundle(state));
    }

    public void setClusterActivityState(ClusterActivityState state) {
        this.mActivityState = state;
        updateNavigationActivity();
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        synchronized (this.mLock) {
            writer.println("**" + getClass().getSimpleName() + "**");
            StringBuilder sb = new StringBuilder();
            sb.append("renderer binder: ");
            sb.append(this.mRendererBinder);
            writer.println(sb.toString());
            if (this.mRendererBinder != null) {
                writer.println("navigation renderer: " + this.mRendererBinder.mNavigationRenderer);
            }
            writer.println("navigation focus owner: " + getNavigationContextOwner());
            writer.println("activity options: " + this.mActivityOptions);
            writer.println("activity state: " + this.mActivityState);
            writer.println("current nav component: " + this.mNavigationComponent);
            writer.println("current nav packages: " + getNavigationContextOwner().mPackageNames);
            writer.println("mInstrumentClusterHelper" + this.mInstrumentClusterHelper);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RendererBinder extends IInstrumentCluster.Stub {
        private final NavigationRenderer mNavigationRenderer;

        RendererBinder(NavigationRenderer navigationRenderer) {
            this.mNavigationRenderer = navigationRenderer;
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public IInstrumentClusterNavigation getNavigationService() throws RemoteException {
            return new NavigationBinder(this.mNavigationRenderer);
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public void setNavigationContextOwner(int uid, int pid) throws RemoteException {
            if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                Log.d("CAR.L.CLUSTER", "Updating navigation ownership to uid: " + uid + ", pid: " + pid);
            }
            synchronized (InstrumentClusterRenderingService.this.mLock) {
                InstrumentClusterRenderingService.this.mNavContextOwner = new ContextOwner(uid, pid, InstrumentClusterRenderingService.this.getPackageManager());
            }
            Handler handler = InstrumentClusterRenderingService.this.mUiHandler;
            final InstrumentClusterRenderingService instrumentClusterRenderingService = InstrumentClusterRenderingService.this;
            handler.post(new Runnable() { // from class: android.car.cluster.renderer.-$$Lambda$InstrumentClusterRenderingService$RendererBinder$uL1MjyliAr2ocdJWQR7h7xKCNDo
                @Override // java.lang.Runnable
                public final void run() {
                    InstrumentClusterRenderingService.this.updateNavigationActivity();
                }
            });
        }

        public /* synthetic */ void lambda$onKeyEvent$1$InstrumentClusterRenderingService$RendererBinder(KeyEvent keyEvent) {
            InstrumentClusterRenderingService.this.onKeyEvent(keyEvent);
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public void onKeyEvent(final KeyEvent keyEvent) throws RemoteException {
            InstrumentClusterRenderingService.this.mUiHandler.post(new Runnable() { // from class: android.car.cluster.renderer.-$$Lambda$InstrumentClusterRenderingService$RendererBinder$JYIvQE6AHO4Hweem6UVjqlOMc0E
                @Override // java.lang.Runnable
                public final void run() {
                    InstrumentClusterRenderingService.RendererBinder.this.lambda$onKeyEvent$1$InstrumentClusterRenderingService$RendererBinder(keyEvent);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class NavigationBinder extends IInstrumentClusterNavigation.Stub {
        private final NavigationRenderer mNavigationRenderer;

        NavigationBinder(NavigationRenderer navigationRenderer) {
            this.mNavigationRenderer = navigationRenderer;
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
        public void onNavigationStateChanged(final Bundle bundle) throws RemoteException {
            InstrumentClusterRenderingService.this.assertClusterManagerPermission();
            assertContextOwnership();
            InstrumentClusterRenderingService.this.mUiHandler.post(new Runnable() { // from class: android.car.cluster.renderer.-$$Lambda$InstrumentClusterRenderingService$NavigationBinder$AouD2VCy5QEXfLjDn2L7OgfQzow
                @Override // java.lang.Runnable
                public final void run() {
                    InstrumentClusterRenderingService.NavigationBinder.this.lambda$onNavigationStateChanged$0$InstrumentClusterRenderingService$NavigationBinder(bundle);
                }
            });
        }

        public /* synthetic */ void lambda$onNavigationStateChanged$0$InstrumentClusterRenderingService$NavigationBinder(Bundle bundle) {
            NavigationRenderer navigationRenderer = this.mNavigationRenderer;
            if (navigationRenderer != null) {
                navigationRenderer.onEvent(1, bundle);
                this.mNavigationRenderer.onNavigationStateChanged(bundle);
            }
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
        public CarNavigationInstrumentCluster getInstrumentClusterInfo() throws RemoteException {
            InstrumentClusterRenderingService.this.assertClusterManagerPermission();
            return (CarNavigationInstrumentCluster) InstrumentClusterRenderingService.this.runAndWaitResult(new Supplier() { // from class: android.car.cluster.renderer.-$$Lambda$InstrumentClusterRenderingService$NavigationBinder$t81kYp25Quio7Jmj-_MKQt6a6bM
                @Override // java.util.function.Supplier
                public final Object get() {
                    return InstrumentClusterRenderingService.NavigationBinder.this.lambda$getInstrumentClusterInfo$1$InstrumentClusterRenderingService$NavigationBinder();
                }
            });
        }

        public /* synthetic */ CarNavigationInstrumentCluster lambda$getInstrumentClusterInfo$1$InstrumentClusterRenderingService$NavigationBinder() {
            return this.mNavigationRenderer.getNavigationProperties();
        }

        private void assertContextOwnership() {
            int uid = getCallingUid();
            int pid = getCallingPid();
            synchronized (InstrumentClusterRenderingService.this.mLock) {
                if (InstrumentClusterRenderingService.this.mNavContextOwner.mUid != uid || InstrumentClusterRenderingService.this.mNavContextOwner.mPid != pid) {
                    throw new IllegalStateException("Client {uid:" + uid + ", pid: " + pid + "} is not an owner of APP_FOCUS_TYPE_NAVIGATION " + InstrumentClusterRenderingService.this.mNavContextOwner);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assertClusterManagerPermission() {
        if (checkCallingOrSelfPermission(Car.PERMISSION_CAR_NAVIGATION_MANAGER) != 0) {
            throw new SecurityException("requires android.car.permission.CAR_NAVIGATION_MANAGER");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <E> E runAndWaitResult(final Supplier<E> supplier) {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<E> result = new AtomicReference<>();
        this.mUiHandler.post(new Runnable() { // from class: android.car.cluster.renderer.-$$Lambda$InstrumentClusterRenderingService$JweI-cTA5lii-BX7H5cYtPD9N7U
            @Override // java.lang.Runnable
            public final void run() {
                InstrumentClusterRenderingService.lambda$runAndWaitResult$0(result, supplier, latch);
            }
        });
        try {
            latch.await();
            return result.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$runAndWaitResult$0(AtomicReference result, Supplier supplier, CountDownLatch latch) {
        result.set(supplier.get());
        latch.countDown();
    }

    public Bitmap getBitmap(Uri uri) {
        try {
        } catch (Throwable e) {
            Log.e("CAR.L.CLUSTER", "Unable to fetch uri: " + uri, e);
        }
        if (uri.getQueryParameter(BITMAP_QUERY_WIDTH).isEmpty() || uri.getQueryParameter(BITMAP_QUERY_HEIGHT).isEmpty()) {
            throw new IllegalArgumentException("Uri must have 'w' and 'h' query parameters");
        }
        ContextOwner contextOwner = getNavigationContextOwner();
        if (contextOwner == null) {
            Log.e("CAR.L.CLUSTER", "No context owner available while fetching: " + uri);
            return null;
        }
        String host = uri.getHost();
        if (!contextOwner.mAuthorities.contains(host)) {
            Log.e("CAR.L.CLUSTER", "Uri points to an authority not handled by the current context owner: " + uri + " (valid authorities: " + contextOwner.mAuthorities + ")");
            return null;
        }
        int userId = UserHandle.getUserId(contextOwner.mUid);
        Uri.Builder buildUpon = uri.buildUpon();
        Uri filteredUid = buildUpon.encodedAuthority(userId + "@" + host).build();
        if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
            Log.d("CAR.L.CLUSTER", "Requesting bitmap: " + uri);
        }
        ParcelFileDescriptor fileDesc = getContentResolver().openFileDescriptor(filteredUid, "r");
        if (fileDesc != null) {
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDesc.getFileDescriptor());
            fileDesc.close();
            return bitmap;
        }
        Log.e("CAR.L.CLUSTER", "Failed to create pipe for uri string: " + uri);
        return null;
    }

    public Bitmap getBitmap(Uri uri, int width, int height) {
        return getBitmap(uri, width, height, 1.0f);
    }

    public Bitmap getBitmap(Uri uri, int width, int height, float offLanesAlpha) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be > 0");
        }
        if (offLanesAlpha < CarPropertyManager.SENSOR_RATE_ONCHANGE || offLanesAlpha > 1.0f) {
            throw new IllegalArgumentException("offLanesAlpha must be between [0, 1]");
        }
        try {
            ContextOwner contextOwner = getNavigationContextOwner();
            if (contextOwner == null) {
                Log.e("CAR.L.CLUSTER", "No context owner available while fetching: " + uri);
                return null;
            }
            Uri uri2 = uri.buildUpon().appendQueryParameter(BITMAP_QUERY_WIDTH, String.valueOf(width)).appendQueryParameter(BITMAP_QUERY_HEIGHT, String.valueOf(height)).appendQueryParameter(BITMAP_QUERY_OFFLANESALPHA, String.valueOf(offLanesAlpha)).build();
            String host = uri2.getHost();
            if (!contextOwner.mAuthorities.contains(host)) {
                Log.e("CAR.L.CLUSTER", "Uri points to an authority not handled by the current context owner: " + uri2 + " (valid authorities: " + contextOwner.mAuthorities + ")");
                return null;
            }
            int userId = UserHandle.getUserId(contextOwner.mUid);
            Uri.Builder buildUpon = uri2.buildUpon();
            Uri filteredUid = buildUpon.encodedAuthority(userId + "@" + host).build();
            Bitmap bitmap = this.mCache.get(uri2.toString());
            if (bitmap == null) {
                if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                    Log.d("CAR.L.CLUSTER", "Requesting bitmap: " + uri2);
                }
                ParcelFileDescriptor fileDesc = getContentResolver().openFileDescriptor(filteredUid, "r");
                if (fileDesc != null) {
                    Bitmap bitmap2 = BitmapFactory.decodeFileDescriptor(fileDesc.getFileDescriptor());
                    fileDesc.close();
                    return bitmap2;
                }
                Log.e("CAR.L.CLUSTER", "Failed to create pipe for uri string: " + uri2);
                if (bitmap.getWidth() != width || bitmap.getHeight() != height) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
                }
                this.mCache.put(uri2.toString(), bitmap);
            }
            return bitmap;
        } catch (Throwable e) {
            Log.e("CAR.L.CLUSTER", "Unable to fetch uri: " + uri, e);
            return null;
        }
    }
}
