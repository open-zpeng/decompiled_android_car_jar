package android.car;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;

/* loaded from: classes.dex */
public abstract class CarManagerBase {
    protected final Car mCar;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void onCarDisconnected();

    public CarManagerBase(Car car) {
        this.mCar = car;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getContext() {
        return this.mCar.getContext();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Handler getEventHandler() {
        return this.mCar.getEventHandler();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T handleRemoteExceptionFromCarService(RemoteException e, T returnValue) {
        return (T) this.mCar.handleRemoteExceptionFromCarService(e, (RemoteException) returnValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleRemoteExceptionFromCarService(RemoteException e) {
        this.mCar.handleRemoteExceptionFromCarService(e);
    }
}
