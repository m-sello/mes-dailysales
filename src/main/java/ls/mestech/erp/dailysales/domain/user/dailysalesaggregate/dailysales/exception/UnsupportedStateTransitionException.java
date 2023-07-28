package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception;

public class UnsupportedStateTransitionException extends RuntimeException {
    public UnsupportedStateTransitionException() {
    }

    public UnsupportedStateTransitionException(String message) {
        super(message);
    }

    public UnsupportedStateTransitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedStateTransitionException(Throwable cause) {
        super(cause);
    }

    public UnsupportedStateTransitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
