package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception;

public class MobileMoneyIllegalArgumentException extends RuntimeException{
    public MobileMoneyIllegalArgumentException() {
    }

    public MobileMoneyIllegalArgumentException(String message) {
        super(message);
    }

    public MobileMoneyIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileMoneyIllegalArgumentException(Throwable cause) {
        super(cause);
    }

    public MobileMoneyIllegalArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
