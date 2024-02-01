package com.android.car.internal;

import android.car.XpDebugLog;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
public abstract class SingleMessageHandler<EventType> implements Handler.Callback {
    private static final long MAX_DELIVER_MSG_INTERVAL_MS;
    private static final long MAX_DISPATCH_MSG_INTERVAL_MS;
    private static final long MAX_ON_EVENT_COST_INTERVAL_MS;
    public static final String TAG = "SingleMessageHandler";
    private final int mHandledMessageWhat;
    private final Handler mHandler;

    protected abstract void handleEvent(EventType eventtype);

    static {
        MAX_DELIVER_MSG_INTERVAL_MS = (Build.IS_USER ? Duration.ofMillis(1000L) : Duration.ofMillis(500L)).toMillis();
        MAX_DISPATCH_MSG_INTERVAL_MS = Duration.ofMillis(500L).toMillis();
        MAX_ON_EVENT_COST_INTERVAL_MS = Duration.ofMillis(100L).toMillis();
    }

    public SingleMessageHandler(Looper looper, int handledMessage) {
        this.mHandledMessageWhat = handledMessage;
        this.mHandler = new Handler(looper, this);
    }

    public SingleMessageHandler(Handler handler, int handledMessage) {
        this(handler.getLooper(), handledMessage);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        if (msg.what == this.mHandledMessageWhat) {
            Pair<Long, List<EventType>> data = (Pair) msg.obj;
            long sendMsgTimeInMs = ((Long) data.first).longValue();
            List<EventType> events = (List) data.second;
            long receiveTimeInMs = SystemClock.uptimeMillis();
            long timeCost = receiveTimeInMs - sendMsgTimeInMs;
            if (timeCost > MAX_DELIVER_MSG_INTERVAL_MS) {
                Log.w(TAG, "deliver car event msg cost too much time:" + timeCost + " ms");
            }
            events.forEach(new Consumer<EventType>() { // from class: com.android.car.internal.SingleMessageHandler.1
                @Override // java.util.function.Consumer
                public void accept(EventType event) {
                    SingleMessageHandler.this.handleEvent(event);
                }
            });
            long timeCost2 = SystemClock.uptimeMillis() - receiveTimeInMs;
            if (XpDebugLog.CAR_DEBUG) {
                Log.i(TAG, "handleMessage eventList: " + getListString(events) + " cost " + timeCost2 + " ms");
                return true;
            } else if (!Build.IS_USER && timeCost2 > MAX_DISPATCH_MSG_INTERVAL_MS) {
                Log.w(TAG, "handleMessage eventList: " + getListString(events) + " cost too much time:" + timeCost2 + " ms");
                return true;
            } else {
                return true;
            }
        }
        return true;
    }

    private String getListString(List<EventType> events) {
        if (events == null || events.size() <= 0) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(64);
        sb.append("size[");
        sb.append(events.size());
        StringBuilder builder = sb.append("]: ");
        builder.append((String) events.stream().map(new Function() { // from class: com.android.car.internal.-$$Lambda$JsVbJ5mpbRjwJuW_A3bDJMqYpF0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return obj.toString();
            }
        }).limit(5L).collect(Collectors.joining(", ")));
        String eventsDesc = builder.toString();
        return eventsDesc;
    }

    public void sendEvents(List<EventType> events) {
        long begin = SystemClock.uptimeMillis();
        Pair<Long, List<EventType>> data = new Pair<>(Long.valueOf(begin), events);
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(this.mHandledMessageWhat, data));
        if (!Build.IS_USER) {
            long timeCost = SystemClock.uptimeMillis() - begin;
            if (timeCost > MAX_ON_EVENT_COST_INTERVAL_MS) {
                Log.w(TAG, "sendEvents cost too much time:" + timeCost + " ms");
            }
        }
    }
}
