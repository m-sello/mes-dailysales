package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyMpesaTotalTakenAlgorithm implements IAlgorithm<MobileMoneyMpesaTotalTaken,BigDecimal> {
    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyMpesaTotalTaken input) {
        final BigDecimal[] totalTaken = {BigDecimal.valueOf(0)};

        if(input.getMobileMpesa() != null){
            input.getMobileMpesa().getMobileMpesaTaken().forEach(mobileMpesaTaken -> {
                totalTaken[0] = totalTaken[0].add(mobileMpesaTaken.getAmount());
            });
        }

        return totalTaken[0];
    }
}
