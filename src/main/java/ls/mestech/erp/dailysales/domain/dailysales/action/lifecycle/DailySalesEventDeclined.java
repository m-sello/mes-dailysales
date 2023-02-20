package ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle;

import java.math.BigDecimal;
import java.security.Timestamp;

public class DailySalesEventDeclined implements DailySalesEvent {
    private Long id;

    private BigDecimal floatAmount;

    private Timestamp capturedDt;

    public Long getId() {
        return id;
    }

    public DailySalesEventDeclined(Long id, BigDecimal floatAmount, Timestamp capturedDt) {
        this.id = id;
        this.floatAmount = floatAmount;
        this.capturedDt = capturedDt;
    }
}
