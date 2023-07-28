package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.takenagainsttotal;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total.MobileMoneyCashplusTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken.MobileMoneyCashplusTotalTaken;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.MobileMoneyTakenExceedsTotalException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyValidateCashplusTakenAgainstTotalAlgorithm implements IValidationRule<MobileMoneyValidateCashplusTakenAgainstTotal> {
    @Autowired
    private final IAlgorithm<MobileMoneyCashplusTotal,BigDecimal> moneyCashplusTotalBigDecimalIAlgorithm;
    @Autowired
    private final IAlgorithm<MobileMoneyCashplusTotalTaken,BigDecimal> moneyCashplusTotalTakenBigDecimalIAlgorithm;
    @Autowired
    private final ModelMapper modelMapper;
    public MobileMoneyValidateCashplusTakenAgainstTotalAlgorithm(IAlgorithm<MobileMoneyCashplusTotal, BigDecimal> moneyCashplusTotalBigDecimalIAlgorithm, IAlgorithm<MobileMoneyCashplusTotalTaken, BigDecimal> moneyCashplusTotalTakenBigDecimalIAlgorithm, ModelMapper modelMapper) {
        this.moneyCashplusTotalBigDecimalIAlgorithm = moneyCashplusTotalBigDecimalIAlgorithm;
        this.moneyCashplusTotalTakenBigDecimalIAlgorithm = moneyCashplusTotalTakenBigDecimalIAlgorithm;
        this.modelMapper = modelMapper;
    }

    @Override
    public void Validate(@NotNull MobileMoneyValidateCashplusTakenAgainstTotal input) {

        MobileMoneyCashplusTotal mobileMoneyCashplusTotal = modelMapper.map(input,MobileMoneyCashplusTotal.class);
        MobileMoneyCashplusTotalTaken mobileMoneyCashplusTotalTaken = modelMapper.map(input,MobileMoneyCashplusTotalTaken.class);

        BigDecimal total = moneyCashplusTotalBigDecimalIAlgorithm.Invoke(mobileMoneyCashplusTotal);
        BigDecimal totalTaken = moneyCashplusTotalTakenBigDecimalIAlgorithm.Invoke(mobileMoneyCashplusTotalTaken);

        if(total.compareTo(totalTaken) < 0 )
            throw new MobileMoneyTakenExceedsTotalException("CashPlus taken exceeds available amount.");
    }
}
