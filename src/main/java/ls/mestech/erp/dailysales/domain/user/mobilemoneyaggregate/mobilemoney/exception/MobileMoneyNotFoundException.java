package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception;

public class MobileMoneyNotFoundException extends RuntimeException{
    public MobileMoneyNotFoundException() {
    }

    public MobileMoneyNotFoundException(String message) {
        super(message);
    }

    public MobileMoneyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileMoneyNotFoundException(Throwable cause) {
        super(cause);
    }

    public MobileMoneyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
