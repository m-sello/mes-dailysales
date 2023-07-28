package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyMpesaTotalAlgorithm implements IAlgorithm<MobileMoneyMpesaTotal,BigDecimal> {
    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyMpesaTotal input) {
        BigDecimal total = BigDecimal.valueOf(0);

        if(input.getMobileMpesa() != null){

            total = total.add(input.getMpesaAmount());
            total = total.add(input.getMobileMpesa().getAccountAmount());
            total = total.add(input.getMobileMpesa().getCommissionAmount());
            total = total.add(input.getMobileMpesa().getFloatAmount());
        }

        return total;
    }
}
