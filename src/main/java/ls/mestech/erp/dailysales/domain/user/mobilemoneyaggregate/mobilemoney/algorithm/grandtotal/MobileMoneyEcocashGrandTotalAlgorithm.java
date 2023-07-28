package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.grandtotal;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total.MobileMoneyEcocashTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken.MobileMoneyEcocashTotalTaken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyEcocashGrandTotalAlgorithm implements IAlgorithm<MobileMoneyEcocashGrandTotal,BigDecimal> {
    @Autowired
    private final IAlgorithm<MobileMoneyEcocashTotal,BigDecimal> moneyEcocashTotalBigDecimalIAlgorithm;
    @Autowired
    private final IAlgorithm<MobileMoneyEcocashTotalTaken,BigDecimal> moneyEcocashTotalTakenBigDecimalIAlgorithm;
    @Autowired
    private final ModelMapper modelMapper;

    public MobileMoneyEcocashGrandTotalAlgorithm(IAlgorithm<MobileMoneyEcocashTotal, BigDecimal> moneyEcocashTotalBigDecimalIAlgorithm, IAlgorithm<MobileMoneyEcocashTotalTaken, BigDecimal> moneyEcocashTotalTakenBigDecimalIAlgorithm, ModelMapper modelMapper) {
        this.moneyEcocashTotalBigDecimalIAlgorithm = moneyEcocashTotalBigDecimalIAlgorithm;
        this.moneyEcocashTotalTakenBigDecimalIAlgorithm = moneyEcocashTotalTakenBigDecimalIAlgorithm;
        this.modelMapper = modelMapper;
    }

    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyEcocashGrandTotal input) {

        MobileMoneyEcocashTotal mobileMoneyEcocashTotal = modelMapper.map(input,MobileMoneyEcocashTotal.class);
        MobileMoneyEcocashTotalTaken mobileMoneyEcocashTotalTaken = modelMapper.map(input,MobileMoneyEcocashTotalTaken.class);

        BigDecimal total = moneyEcocashTotalBigDecimalIAlgorithm.Invoke(mobileMoneyEcocashTotal);
        BigDecimal totalTaken = moneyEcocashTotalTakenBigDecimalIAlgorithm.Invoke(mobileMoneyEcocashTotalTaken);

        return total.add(totalTaken);
    }
}
