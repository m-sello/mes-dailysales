package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyCashplusTotalTakenAlgorithm implements IAlgorithm<MobileMoneyCashplusTotalTaken,BigDecimal> {
    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyCashplusTotalTaken input) {
        final BigDecimal[] totalTaken = {BigDecimal.valueOf(0)};

        if(input.getMobileCashplus() != null){
            input.getMobileCashplus().getMobileCashplusTaken().forEach(mobileCashplusTaken -> {
                totalTaken[0] = totalTaken[0].add(mobileCashplusTaken.getAmount());
            });
        }

        return totalTaken[0];
    }
}
