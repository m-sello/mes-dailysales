package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ls.mestech.erp.dailysales.domain.model.MobileCashplus;
import ls.mestech.erp.dailysales.domain.model.MobileEcocash;
import ls.mestech.erp.dailysales.domain.model.MobileElectricityAirtime;
import ls.mestech.erp.dailysales.domain.model.MobileMpesa;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MobileMoneyDeleteCommand {
    @NotNull(message = "Mobile Money ID cannot be blank")
    private String id;
    private BigDecimal mpesaAmount;
    private BigDecimal ecocashAmount;
    private BigDecimal electricityAmount;
    private BigDecimal airtimeAmount;
    private BigDecimal cashplusAmount;
    private String dailySalesId;
    private String comments;
    private MobileMpesa mobileMpesa;
    private MobileEcocash mobileEcocash;
    private MobileCashplus mobileCashplus;
    private MobileElectricityAirtime mobileElectricityAirtime;
}
