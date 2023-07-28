package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ls.mestech.erp.dailysales.domain.model.DailySalesLog;
import ls.mestech.erp.dailysales.domain.model.DailySalesTaken;
import ls.mestech.erp.dailysales.domain.model.DailyTender;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DailySalesSubmitForApprovalCommand {
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
}
