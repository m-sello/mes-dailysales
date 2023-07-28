package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyEcocashTotalTakenAlgorithm implements IAlgorithm<MobileMoneyEcocashTotalTaken,BigDecimal> {
    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyEcocashTotalTaken input) {
        final BigDecimal[] totalTaken = {BigDecimal.valueOf(0)};

        if(input.getMobileEcocash() != null){
            input.getMobileEcocash().getMobileEcocashTaken().forEach(mobileEcocashTaken -> {
                totalTaken[0] = totalTaken[0].add(mobileEcocashTaken.getAmount());
            });
        }

        return totalTaken[0];
    }
}
