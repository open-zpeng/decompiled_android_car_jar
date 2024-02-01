package android.car;

/* loaded from: classes.dex */
public class CarUnSupportCduTypeException extends UnsupportedOperationException {
    public CarUnSupportCduTypeException() {
    }

    public CarUnSupportCduTypeException(String s) {
        super(s);
    }

    CarUnSupportCduTypeException(Throwable cause, String msg, Object... args) {
        super(String.format(msg, args), cause);
    }
}
