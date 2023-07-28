package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception;

public class DailySalesIllegalArgumentException extends RuntimeException{
    public DailySalesIllegalArgumentException() {
    }

    public DailySalesIllegalArgumentException(String message) {
        super(message);
    }

    public DailySalesIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public DailySalesIllegalArgumentException(Throwable cause) {
        super(cause);
    }

    public DailySalesIllegalArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
