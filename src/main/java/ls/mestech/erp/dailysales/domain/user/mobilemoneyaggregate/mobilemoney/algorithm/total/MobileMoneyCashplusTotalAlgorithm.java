package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyCashplusTotalAlgorithm implements IAlgorithm<MobileMoneyCashplusTotal,BigDecimal> {
    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyCashplusTotal input) {
        BigDecimal total = BigDecimal.valueOf(0);

        if(input.getMobileCashplus() != null){

            total = total.add(input.getCashplusAmount());
            total = total.add(input.getMobileCashplus().getBalanceAmount());
            total = total.add(input.getMobileCashplus().getCommissionAmount());
        }

        return total;
    }
}
