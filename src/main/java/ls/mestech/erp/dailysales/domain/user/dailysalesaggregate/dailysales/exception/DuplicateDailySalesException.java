package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception;

public class DuplicateDailySalesException extends RuntimeException {
    public DuplicateDailySalesException() {
    }

    public DuplicateDailySalesException(String message) {
        super(message);
    }

    public DuplicateDailySalesException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateDailySalesException(Throwable cause) {
        super(cause);
    }

    public DuplicateDailySalesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
