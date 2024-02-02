package com.android.car.internal;

import android.car.XpDebugLog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
/* loaded from: classes.dex */
public abstract class SingleRawMessageHandler<EventType> implements Handler.Callback {
    public static final String TAG = "SingleRawMessageHandler";
    private final int mHandledMessageWhat;
    private final Handler mHandler;

    protected abstract void handleEvent(EventType eventtype);

    public SingleRawMessageHandler(Looper looper, int handledMessage) {
        this.mHandledMessageWhat = handledMessage;
        this.mHandler = new Handler(looper, this);
    }

    public SingleRawMessageHandler(Handler handler, int handledMessage) {
        this(handler.getLooper(), handledMessage);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        if (msg.what == this.mHandledMessageWhat) {
            Object obj = msg.obj;
            if (XpDebugLog.CAR_DEBUG) {
                Log.d(TAG, "++handleMessage event: " + obj);
            }
            handleEvent(obj);
            if (XpDebugLog.CAR_DEBUG) {
                Log.d(TAG, "--handleMessage event: " + obj);
                return true;
            }
            return true;
        }
        return true;
    }

    public void sendEvents(EventType event) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(this.mHandledMessageWhat, event));
    }
}
