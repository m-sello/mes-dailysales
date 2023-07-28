package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.grandtotal;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total.MobileMoneyCashplusTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken.MobileMoneyCashplusTotalTaken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyCashplusGrandTotalAlgorithm implements IAlgorithm<MobileMoneyCashplusGrandTotal,BigDecimal> {
    @Autowired
    private final IAlgorithm<MobileMoneyCashplusTotal,BigDecimal> moneyCashplusTotalBigDecimalIAlgorithm;
    @Autowired
    private final IAlgorithm<MobileMoneyCashplusTotalTaken,BigDecimal> moneyCashplusTotalTakenBigDecimalIAlgorithm;
    @Autowired
    private final ModelMapper modelMapper;
    public MobileMoneyCashplusGrandTotalAlgorithm(IAlgorithm<MobileMoneyCashplusTotal, BigDecimal> moneyCashplusTotalBigDecimalIAlgorithm, IAlgorithm<MobileMoneyCashplusTotalTaken, BigDecimal> moneyCashplusTotalTakenBigDecimalIAlgorithm, ModelMapper modelMapper) {
        this.moneyCashplusTotalBigDecimalIAlgorithm = moneyCashplusTotalBigDecimalIAlgorithm;
        this.moneyCashplusTotalTakenBigDecimalIAlgorithm = moneyCashplusTotalTakenBigDecimalIAlgorithm;
        this.modelMapper = modelMapper;
    }

    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyCashplusGrandTotal input) {

        MobileMoneyCashplusTotal mobileMoneyCashplusTotal = modelMapper.map(input,MobileMoneyCashplusTotal.class);
        MobileMoneyCashplusTotalTaken mobileMoneyCashplusTotalTaken = modelMapper.map(input,MobileMoneyCashplusTotalTaken.class);

        BigDecimal total = moneyCashplusTotalBigDecimalIAlgorithm.Invoke(mobileMoneyCashplusTotal);
        BigDecimal totalTaken = moneyCashplusTotalTakenBigDecimalIAlgorithm.Invoke(mobileMoneyCashplusTotalTaken);

        return total.add(totalTaken);
    }
}
