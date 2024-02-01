package android.car.hardware.condition;

import android.annotation.SystemApi;
import android.car.CarManagerBase;
import android.car.XpDebugLog;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.condition.ICarCondition;
import android.car.hardware.condition.ICarConditionEventListener;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
@SystemApi
/* loaded from: classes.dex */
public class CarConditionManager implements CarManagerBase {
    private static final String TAG = "CarConditionManager";
    private final ICarCondition mService;

    /* loaded from: classes.dex */
    public static abstract class CarConditionCallback extends ICarConditionEventListener.Stub {
        @Override // android.car.hardware.condition.ICarConditionEventListener
        public abstract void onChangeEvent(CarPropertyValue carPropertyValue);
    }

    public CarConditionManager(IBinder binder) {
        this.mService = ICarCondition.Stub.asInterface(binder);
    }

    @Deprecated
    public void registerCondition(List<Integer> propIds, CarConditionInfo condition, CarConditionCallback listener) throws RemoteException {
        registerCondition(condition, listener);
    }

    public void registerCondition(CarConditionInfo condition, CarConditionCallback listener) throws RemoteException {
        if (condition == null) {
            throw new IllegalArgumentException("CarConditionInfo can not be null");
        }
        List<Integer> propIds = new ArrayList<>();
        SparseArray limitsArray = condition.getLimitsArray();
        int size = limitsArray.size();
        for (int i = 0; i < size; i++) {
            propIds.add(Integer.valueOf(limitsArray.keyAt(i)));
        }
        if (XpDebugLog.CAR_DEBUG) {
            for (Integer num : propIds) {
                int propertyId = num.intValue();
                Log.i(TAG, "register condition: " + listener + " for " + XpDebugLog.getPropertyDescription(propertyId));
            }
        }
        this.mService.registerCondition(propIds, condition, listener);
    }

    public void unregisterCondition(CarConditionCallback listener) throws RemoteException {
        if (XpDebugLog.CAR_DEBUG) {
            Log.i(TAG, "unregister condition:" + listener);
        }
        this.mService.unregisterCondition(listener);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }
}
