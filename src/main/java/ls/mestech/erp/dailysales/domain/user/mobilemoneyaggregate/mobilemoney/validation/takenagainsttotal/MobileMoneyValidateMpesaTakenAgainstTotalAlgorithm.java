package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.takenagainsttotal;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total.MobileMoneyMpesaTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken.MobileMoneyMpesaTotalTaken;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.MobileMoneyTakenExceedsTotalException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyValidateMpesaTakenAgainstTotalAlgorithm implements IValidationRule<MobileMoneyValidateMpesaTakenAgainstTotal> {
    @Autowired
    private final IAlgorithm<MobileMoneyMpesaTotal,BigDecimal> moneyMpesaTotalBigDecimalIAlgorithm;
    @Autowired
    private final IAlgorithm<MobileMoneyMpesaTotalTaken,BigDecimal> moneyMpesaTotalTakenBigDecimalIAlgorithm;
    @Autowired
    private final ModelMapper modelMapper;

    public MobileMoneyValidateMpesaTakenAgainstTotalAlgorithm(IAlgorithm<MobileMoneyMpesaTotal, BigDecimal> moneyMpesaTotalBigDecimalIAlgorithm, IAlgorithm<MobileMoneyMpesaTotalTaken, BigDecimal> moneyMpesaTotalTakenBigDecimalIAlgorithm, ModelMapper modelMapper) {
        this.moneyMpesaTotalBigDecimalIAlgorithm = moneyMpesaTotalBigDecimalIAlgorithm;
        this.moneyMpesaTotalTakenBigDecimalIAlgorithm = moneyMpesaTotalTakenBigDecimalIAlgorithm;
        this.modelMapper = modelMapper;
    }

    @Override
    public void Validate(@NotNull MobileMoneyValidateMpesaTakenAgainstTotal input) {

        MobileMoneyMpesaTotal mobileMoneyMpesaTotal = modelMapper.map(input,MobileMoneyMpesaTotal.class);
        MobileMoneyMpesaTotalTaken mobileMoneyMpesaTotalTaken = modelMapper.map(input,MobileMoneyMpesaTotalTaken.class);

        BigDecimal total = moneyMpesaTotalBigDecimalIAlgorithm.Invoke(mobileMoneyMpesaTotal);
        BigDecimal totalTaken = moneyMpesaTotalTakenBigDecimalIAlgorithm.Invoke(mobileMoneyMpesaTotalTaken);

        if(total.compareTo(totalTaken) < 0 )
            throw new MobileMoneyTakenExceedsTotalException("Mpesa taken exceeds available amount.");
    }
}
