package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ls.mestech.erp.dailysales.domain.model.DailySalesLog;
import ls.mestech.erp.dailysales.domain.model.DailyTender;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class DailySalesCheckNullValues {
    private String id;
    @NotBlank(message = "Float Amount cannot be null")
    private BigDecimal floatAmount;
    @NotNull(message = "Captured Date cannot be null")
    private LocalDateTime capturedDt;
    private String comments;
    private Set<DailyTender> dailyTenders;
    private Set<DailySalesLog> dailySalesLogs;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getFloatAmount() {
        return floatAmount;
    }

    public void setFloatAmount(BigDecimal floatAmount) {
        this.floatAmount = floatAmount;
    }

    public LocalDateTime getCapturedDt() {
        return capturedDt;
    }

    public void setCapturedDt(LocalDateTime capturedDt) {
        this.capturedDt = capturedDt;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
