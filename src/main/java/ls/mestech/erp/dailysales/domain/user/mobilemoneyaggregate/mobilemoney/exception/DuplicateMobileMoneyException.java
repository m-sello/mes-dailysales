package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception;

public class DuplicateMobileMoneyException extends RuntimeException {
    public DuplicateMobileMoneyException() {
    }

    public DuplicateMobileMoneyException(String message) {
        super(message);
    }

    public DuplicateMobileMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateMobileMoneyException(Throwable cause) {
        super(cause);
    }

    public DuplicateMobileMoneyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
