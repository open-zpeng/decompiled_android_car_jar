package android.car.media;

import android.car.Car;
import android.car.CarManagerBase;
import android.car.media.ICarMedia;
import android.car.media.ICarMediaSourceListener;
import android.content.ComponentName;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class CarMediaManager extends CarManagerBase {
    private Map<MediaSourceChangedListener, ICarMediaSourceListener> mCallbackMap;
    private final ICarMedia mService;

    /* loaded from: classes.dex */
    public interface MediaSourceChangedListener {
        void onMediaSourceChanged(ComponentName componentName);
    }

    public CarMediaManager(Car car, IBinder service) {
        super(car);
        this.mCallbackMap = new HashMap();
        this.mService = ICarMedia.Stub.asInterface(service);
    }

    public synchronized ComponentName getMediaSource() {
        try {
        } catch (RemoteException e) {
            return (ComponentName) handleRemoteExceptionFromCarService(e, null);
        }
        return this.mService.getMediaSource();
    }

    public synchronized void setMediaSource(ComponentName componentName) {
        try {
            this.mService.setMediaSource(componentName);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public synchronized void registerMediaSourceListener(final MediaSourceChangedListener callback) {
        try {
            ICarMediaSourceListener binderCallback = new ICarMediaSourceListener.Stub() { // from class: android.car.media.CarMediaManager.1
                @Override // android.car.media.ICarMediaSourceListener
                public void onMediaSourceChanged(ComponentName componentName) {
                    callback.onMediaSourceChanged(componentName);
                }
            };
            this.mCallbackMap.put(callback, binderCallback);
            this.mService.registerMediaSourceListener(binderCallback);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public synchronized void unregisterMediaSourceListener(MediaSourceChangedListener callback) {
        try {
            ICarMediaSourceListener binderCallback = this.mCallbackMap.remove(callback);
            this.mService.unregisterMediaSourceListener(binderCallback);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    @Override // android.car.CarManagerBase
    public synchronized void onCarDisconnected() {
        this.mCallbackMap.clear();
    }

    public static String getServiceName() {
        return Car.CAR_MEDIA_SERVICE;
    }
}
