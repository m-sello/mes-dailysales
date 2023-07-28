package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception;

public class DailySalesCapturedDateException extends RuntimeException {
    public DailySalesCapturedDateException() {
    }

    public DailySalesCapturedDateException(String message) {
        super(message);
    }

    public DailySalesCapturedDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DailySalesCapturedDateException(Throwable cause) {
        super(cause);
    }

    public DailySalesCapturedDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
