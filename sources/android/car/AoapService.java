package android.car;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

@SystemApi
/* loaded from: classes.dex */
public abstract class AoapService extends Service {
    public static final String KEY_DEVICE = "usb-device";
    public static final String KEY_RESULT = "result";
    public static final int MSG_CAN_SWITCH_TO_AOAP = 3;
    public static final int MSG_CAN_SWITCH_TO_AOAP_RESPONSE = 4;
    public static final int MSG_NEW_DEVICE_ATTACHED = 1;
    public static final int MSG_NEW_DEVICE_ATTACHED_RESPONSE = 2;
    public static final int RESULT_DEVICE_NOT_SUPPORTED = 1;
    public static final int RESULT_DO_NOT_SWITCH_TO_AOAP = 2;
    public static final int RESULT_OK = 0;
    private static final String TAG = AoapService.class.getSimpleName();
    private boolean mBound;
    private Messenger mMessenger;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Result {
    }

    public abstract int isDeviceSupported(UsbDevice usbDevice);

    public int canSwitchToAoap(UsbDevice device) {
        return 0;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mMessenger = new Messenger(new IncomingHandler(this));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (this.mBound) {
            Log.w(TAG, "Received onBind event when the service was already bound");
        }
        this.mBound = true;
        return this.mMessenger.getBinder();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        this.mBound = false;
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.write("Bound: " + this.mBound);
    }

    /* loaded from: classes.dex */
    private static class IncomingHandler extends Handler {
        private final WeakReference<AoapService> mServiceRef;

        IncomingHandler(AoapService service) {
            super(Looper.getMainLooper());
            this.mServiceRef = new WeakReference<>(service);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            AoapService service = this.mServiceRef.get();
            if (service == null) {
                return;
            }
            Bundle data = msg.getData();
            if (data == null) {
                String str = AoapService.TAG;
                Log.e(str, "Ignoring message " + msg.what + " without data");
                return;
            }
            String str2 = AoapService.TAG;
            Log.i(str2, "Message received: " + msg.what);
            int i = msg.what;
            if (i == 1) {
                int res = service.isDeviceSupported((UsbDevice) Preconditions.checkNotNull((UsbDevice) data.getParcelable(AoapService.KEY_DEVICE)));
                if (res != 0 && res != 1) {
                    throw new IllegalArgumentException("Result can not be " + res);
                }
                sendResponse(msg.replyTo, 2, res);
            } else if (i != 3) {
                String str3 = AoapService.TAG;
                Log.e(str3, "Unknown message received: " + msg.what);
            } else {
                int res2 = service.canSwitchToAoap((UsbDevice) Preconditions.checkNotNull((UsbDevice) data.getParcelable(AoapService.KEY_DEVICE)));
                if (res2 != 0 && res2 != 1 && res2 != 2) {
                    throw new IllegalArgumentException("Result can not be " + res2);
                }
                sendResponse(msg.replyTo, 4, res2);
            }
        }

        private void sendResponse(Messenger messenger, int msg, int result) {
            try {
                messenger.send(createResponseMessage(msg, result));
            } catch (RemoteException e) {
                Log.e(AoapService.TAG, "Failed to send message", e);
            }
        }

        private Message createResponseMessage(int msg, int result) {
            Message response = Message.obtain((Handler) null, msg);
            Bundle data = new Bundle();
            data.putInt(AoapService.KEY_RESULT, result);
            response.setData(data);
            return response;
        }
    }
}
