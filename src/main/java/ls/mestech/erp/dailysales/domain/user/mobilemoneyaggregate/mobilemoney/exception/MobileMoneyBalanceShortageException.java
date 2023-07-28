package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception;

public class MobileMoneyBalanceShortageException extends RuntimeException{
    public MobileMoneyBalanceShortageException() {
    }

    public MobileMoneyBalanceShortageException(String message) {
        super(message);
    }

    public MobileMoneyBalanceShortageException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileMoneyBalanceShortageException(Throwable cause) {
        super(cause);
    }

    public MobileMoneyBalanceShortageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
