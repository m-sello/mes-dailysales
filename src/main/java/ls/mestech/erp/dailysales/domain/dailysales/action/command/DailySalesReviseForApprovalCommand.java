package ls.mestech.erp.dailysales.domain.dailysales.action.command;

import java.math.BigDecimal;
import java.security.Timestamp;

public class DailySalesReviseForApprovalCommand {
    private Long id;

    private BigDecimal floatAmount;

    private Timestamp capturedDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFloatAmount() {
        return floatAmount;
    }

    public void setFloatAmount(BigDecimal floatAmount) {
        this.floatAmount = floatAmount;
    }

    public Timestamp getCapturedDt() {
        return capturedDt;
    }

    public void setCapturedDt(Timestamp capturedDt) {
        this.capturedDt = capturedDt;
    }
}
