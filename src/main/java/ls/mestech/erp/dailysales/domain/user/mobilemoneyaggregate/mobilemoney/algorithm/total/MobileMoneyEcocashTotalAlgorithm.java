package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyEcocashTotalAlgorithm implements IAlgorithm<MobileMoneyEcocashTotal,BigDecimal> {
    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyEcocashTotal input) {
        BigDecimal total = BigDecimal.valueOf(0);

        if(input.getMobileEcocash() != null){

            total = total.add(input.getEcocashAmount());
            total = total.add(input.getMobileEcocash().getCommissionAmount());
            total = total.add(input.getMobileEcocash().getFloatAmount());
        }

        return total;
    }
}
