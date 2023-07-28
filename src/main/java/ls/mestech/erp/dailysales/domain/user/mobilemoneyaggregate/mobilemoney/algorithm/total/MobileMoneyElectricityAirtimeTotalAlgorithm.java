package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyElectricityAirtimeTotalAlgorithm implements IAlgorithm<MobileMoneyElectricityAirtimeTotal,BigDecimal> {
    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyElectricityAirtimeTotal input) {
        BigDecimal total = BigDecimal.valueOf(0);

        if(input.getMobileElectricityAirtime() != null){

            total = total.add(input.getElectricityAmount());
            total = total.add(input.getMobileElectricityAirtime().getBalanceAmount());
        }

        return total;
    }
}
