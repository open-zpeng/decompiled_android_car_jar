package android.car;

/* loaded from: classes.dex */
public class CarNotConnectedException extends RuntimeException {
    private static final long serialVersionUID = 4377819074553367406L;

    public CarNotConnectedException() {
    }

    public CarNotConnectedException(String name) {
        super(name);
    }

    public CarNotConnectedException(String name, Throwable cause) {
        super(name, cause);
    }

    public CarNotConnectedException(Exception cause) {
        super(cause);
    }
}
