package android.de.meetat.API;

/**
 * @author Philipp Daniels
 */
public class NoConnectionException extends Exception {
    private static final long serialVersionUID = -4600527368043280264L;

    public NoConnectionException() {
        super();
    }

    public NoConnectionException(String detailMessage) {
        super(detailMessage);
    }

    public NoConnectionException(Throwable throwable) {
        super(throwable);
    }

    public NoConnectionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
