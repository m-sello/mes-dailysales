package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total;

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

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MobileMoneyElectricityAirtimeTotal {
    private String id;
    @NotNull(message = "Mpesa Amount cannot be blank")
    private BigDecimal mpesaAmount;
    @NotNull(message = "Ecocash Amount cannot be blank")
    private BigDecimal ecocashAmount;
    @NotNull(message = "Electricity Amount cannot be blank")
    private BigDecimal electricityAmount;
    @NotNull(message = "Airtime Amount cannot be blank")
    private BigDecimal airtimeAmount;
    @NotNull(message = "Cashplus Amount cannot be blank")
    private BigDecimal cashplusAmount;
    @NotNull(message = "Daily Sales ID cannot be blank")
    private Long dailySalesId;
    private String comments;
    private MobileMpesa mobileMpesa;
    private MobileEcocash mobileEcocash;
    private MobileCashplus mobileCashplus;
    private MobileElectricityAirtime mobileElectricityAirtime;
}
