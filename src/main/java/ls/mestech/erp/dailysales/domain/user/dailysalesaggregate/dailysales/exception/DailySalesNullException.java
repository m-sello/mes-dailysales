package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception;

public class DailySalesNullException extends RuntimeException{
    public DailySalesNullException() {
    }

    public DailySalesNullException(String message) {
        super(message);
    }

    public DailySalesNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public DailySalesNullException(Throwable cause) {
        super(cause);
    }

    public DailySalesNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
