package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyElectricityAirtimeTotalTakenAlgorithm implements IAlgorithm<MobileMoneyElectricityAirtimeTotalTaken,BigDecimal> {
    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyElectricityAirtimeTotalTaken input) {
        final BigDecimal[] totalTaken = {BigDecimal.valueOf(0)};

        if(input.getMobileElectricityAirtime() != null){
            input.getMobileElectricityAirtime().getMobileElectricityAirtimeTaken().forEach(mobileElectricityAirtimeTaken -> {
                totalTaken[0] = totalTaken[0].add(mobileElectricityAirtimeTaken.getAmount());
            });
        }

        return totalTaken[0];
    }
}
