package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception;

public class DailySalesNotFoundException extends RuntimeException{
    public DailySalesNotFoundException() {
    }

    public DailySalesNotFoundException(String message) {
        super(message);
    }

    public DailySalesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DailySalesNotFoundException(Throwable cause) {
        super(cause);
    }

    public DailySalesNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
