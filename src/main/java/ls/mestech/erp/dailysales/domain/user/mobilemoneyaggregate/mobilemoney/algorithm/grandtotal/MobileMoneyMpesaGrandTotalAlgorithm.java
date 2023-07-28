package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.grandtotal;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total.MobileMoneyMpesaTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken.MobileMoneyMpesaTotalTaken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyMpesaGrandTotalAlgorithm implements IAlgorithm<MobileMoneyMpesaGrandTotal,BigDecimal> {
    @Autowired
    private final IAlgorithm<MobileMoneyMpesaTotal,BigDecimal> moneyMpesaTotalBigDecimalIAlgorithm;
    @Autowired
    private final IAlgorithm<MobileMoneyMpesaTotalTaken,BigDecimal> moneyMpesaTotalTakenBigDecimalIAlgorithm;
    @Autowired
    private final ModelMapper modelMapper;

    public MobileMoneyMpesaGrandTotalAlgorithm(IAlgorithm<MobileMoneyMpesaTotal, BigDecimal> moneyMpesaTotalBigDecimalIAlgorithm, IAlgorithm<MobileMoneyMpesaTotalTaken, BigDecimal> moneyMpesaTotalTakenBigDecimalIAlgorithm, ModelMapper modelMapper) {
        this.moneyMpesaTotalBigDecimalIAlgorithm = moneyMpesaTotalBigDecimalIAlgorithm;
        this.moneyMpesaTotalTakenBigDecimalIAlgorithm = moneyMpesaTotalTakenBigDecimalIAlgorithm;
        this.modelMapper = modelMapper;
    }

    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyMpesaGrandTotal input) {

        MobileMoneyMpesaTotal mobileMoneyMpesaTotal = modelMapper.map(input,MobileMoneyMpesaTotal.class);
        MobileMoneyMpesaTotalTaken mobileMoneyMpesaTotalTaken = modelMapper.map(input,MobileMoneyMpesaTotalTaken.class);

        BigDecimal total = moneyMpesaTotalBigDecimalIAlgorithm.Invoke(mobileMoneyMpesaTotal);
        BigDecimal totalTaken = moneyMpesaTotalTakenBigDecimalIAlgorithm.Invoke(mobileMoneyMpesaTotalTaken);

        return total.add(totalTaken);
    }
}
