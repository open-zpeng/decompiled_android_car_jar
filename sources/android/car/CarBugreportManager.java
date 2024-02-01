package android.car;

import android.car.CarBugreportManager;
import android.car.ICarBugreportCallback;
import android.car.ICarBugreportService;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Objects;
import libcore.io.IoUtils;

/* loaded from: classes.dex */
public final class CarBugreportManager extends CarManagerBase {
    private final ICarBugreportService mService;

    /* loaded from: classes.dex */
    public static abstract class CarBugreportManagerCallback {
        public static final int CAR_BUGREPORT_DUMPSTATE_CONNECTION_FAILED = 3;
        public static final int CAR_BUGREPORT_DUMPSTATE_FAILED = 1;
        public static final int CAR_BUGREPORT_IN_PROGRESS = 2;
        public static final int CAR_BUGREPORT_SERVICE_NOT_AVAILABLE = 4;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface CarBugreportErrorCode {
        }

        public void onProgress(float progress) {
        }

        public void onError(int errorCode) {
        }

        public void onFinished() {
        }
    }

    /* loaded from: classes.dex */
    private static final class CarBugreportManagerCallbackWrapper extends ICarBugreportCallback.Stub {
        private final WeakReference<CarBugreportManagerCallback> mWeakCallback;
        private final WeakReference<Handler> mWeakHandler;

        CarBugreportManagerCallbackWrapper(CarBugreportManagerCallback callback, Handler handler) {
            this.mWeakCallback = new WeakReference<>(callback);
            this.mWeakHandler = new WeakReference<>(handler);
        }

        @Override // android.car.ICarBugreportCallback
        public void onProgress(final float progress) {
            final CarBugreportManagerCallback callback = this.mWeakCallback.get();
            Handler handler = this.mWeakHandler.get();
            if (handler != null && callback != null) {
                handler.post(new Runnable() { // from class: android.car.-$$Lambda$CarBugreportManager$CarBugreportManagerCallbackWrapper$SKlkbH2yc5GbUZezB8cF5ckvnkk
                    @Override // java.lang.Runnable
                    public final void run() {
                        CarBugreportManager.CarBugreportManagerCallback.this.onProgress(progress);
                    }
                });
            }
        }

        @Override // android.car.ICarBugreportCallback
        public void onError(final int errorCode) {
            final CarBugreportManagerCallback callback = this.mWeakCallback.get();
            Handler handler = this.mWeakHandler.get();
            if (handler != null && callback != null) {
                handler.post(new Runnable() { // from class: android.car.-$$Lambda$CarBugreportManager$CarBugreportManagerCallbackWrapper$6ynqvb6TW8a-N8urXMTzbjfrmsk
                    @Override // java.lang.Runnable
                    public final void run() {
                        CarBugreportManager.CarBugreportManagerCallback.this.onError(errorCode);
                    }
                });
            }
        }

        @Override // android.car.ICarBugreportCallback
        public void onFinished() {
            final CarBugreportManagerCallback callback = this.mWeakCallback.get();
            Handler handler = this.mWeakHandler.get();
            if (handler != null && callback != null) {
                Objects.requireNonNull(callback);
                handler.post(new Runnable() { // from class: android.car.-$$Lambda$xrCRTzZqIh_Vz-aS3bmIqYgze-s
                    @Override // java.lang.Runnable
                    public final void run() {
                        CarBugreportManager.CarBugreportManagerCallback.this.onFinished();
                    }
                });
            }
        }
    }

    public CarBugreportManager(Car car, IBinder service) {
        super(car);
        this.mService = ICarBugreportService.Stub.asInterface(service);
    }

    public void requestBugreport(ParcelFileDescriptor output, ParcelFileDescriptor extraOutput, CarBugreportManagerCallback callback) {
        Preconditions.checkNotNull(output);
        Preconditions.checkNotNull(extraOutput);
        Preconditions.checkNotNull(callback);
        try {
            try {
                CarBugreportManagerCallbackWrapper wrapper = new CarBugreportManagerCallbackWrapper(callback, getEventHandler());
                this.mService.requestBugreport(output, extraOutput, wrapper);
            } catch (RemoteException e) {
                handleRemoteExceptionFromCarService(e);
            }
        } finally {
            IoUtils.closeQuietly(output);
            IoUtils.closeQuietly(extraOutput);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public static String getServiceName() {
        return Car.CAR_BUGREPORT_SERVICE;
    }
}
