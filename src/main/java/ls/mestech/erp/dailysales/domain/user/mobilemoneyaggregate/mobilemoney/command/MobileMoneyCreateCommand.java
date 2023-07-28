package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ls.mestech.erp.dailysales.domain.model.*;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MobileMoneyCreateCommand {
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
    private String dailySalesId;
    private String comments;
    private MobileMpesa mobileMpesa;
    private MobileEcocash mobileEcocash;
    private MobileCashplus mobileCashplus;
    private MobileElectricityAirtime mobileElectricityAirtime;
}
