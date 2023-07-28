package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.algorithm;

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
public class DailySalesPreviousRecord {
    @NotNull(message = "Daily Sales ID cannot be blank")
    private String dailySalesId;
}
