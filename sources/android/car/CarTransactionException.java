package android.car;

/* loaded from: classes.dex */
public class CarTransactionException extends UnsupportedOperationException {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CarTransactionException(Throwable cause, String msg, Object... args) {
        super(String.format(msg, args), cause);
    }
}
