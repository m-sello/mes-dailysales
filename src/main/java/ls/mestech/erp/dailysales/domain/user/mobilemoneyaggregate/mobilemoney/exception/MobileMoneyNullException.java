package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception;

public class MobileMoneyNullException extends RuntimeException{
    public MobileMoneyNullException() {
    }

    public MobileMoneyNullException(String message) {
        super(message);
    }

    public MobileMoneyNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileMoneyNullException(Throwable cause) {
        super(cause);
    }

    public MobileMoneyNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
