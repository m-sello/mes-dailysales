package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception;

public class MobileMoneyTakenExceedsTotalException extends RuntimeException {
    public MobileMoneyTakenExceedsTotalException() {
    }

    public MobileMoneyTakenExceedsTotalException(String message) {
        super(message);
    }

    public MobileMoneyTakenExceedsTotalException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileMoneyTakenExceedsTotalException(Throwable cause) {
        super(cause);
    }

    public MobileMoneyTakenExceedsTotalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
