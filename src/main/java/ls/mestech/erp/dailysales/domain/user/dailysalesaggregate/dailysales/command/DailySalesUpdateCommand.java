package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ls.mestech.erp.dailysales.domain.model.DailySalesLog;
import ls.mestech.erp.dailysales.domain.model.DailySalesTaken;
import ls.mestech.erp.dailysales.domain.model.DailyTender;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class DailySalesUpdateCommand {
    private String id;
    @NotBlank(message = "Float Amount cannot be null")
    private BigDecimal floatAmount;
    @NotNull(message = "Captured Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime capturedDt;
    private String comments;
    private final Set<DailyTender> dailyTenders = new HashSet<>();
    private final Set<DailySalesLog> dailySalesLogs = new HashSet<>();

    private final Set<DailySalesTaken> dailySalesTaken = new HashSet<>();
    public Set<DailyTender> getDailyTenders() {
        return dailyTenders;
    }

    public Set<DailySalesLog> getDailySalesLogs() {
        return dailySalesLogs;
    }

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

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

    public void addDailyTenders(DailyTender dailyTenders) {
        this.dailyTenders.add(dailyTenders);
    }
    public void addDailySalesLogs(DailySalesLog dailySalesLog) {
        this.dailySalesLogs.add(dailySalesLog);
    }
    public void addAllDailyTenders(HashSet<DailyTender> dailyTenders) {
        this.dailyTenders.addAll(dailyTenders);
    }
    public void addAllDailySalesLogs(HashSet<DailySalesLog> dailySalesLogs){
        this.dailySalesLogs.addAll(dailySalesLogs);
    }

    public Set<DailySalesTaken> getDailySalesTaken() {
        return dailySalesTaken;
    }
}
