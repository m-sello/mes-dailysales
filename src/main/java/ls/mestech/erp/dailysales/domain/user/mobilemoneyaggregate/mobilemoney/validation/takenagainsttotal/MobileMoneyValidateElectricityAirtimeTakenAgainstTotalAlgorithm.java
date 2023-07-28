package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.takenagainsttotal;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total.MobileMoneyElectricityAirtimeTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken.MobileMoneyElectricityAirtimeTotalTaken;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.MobileMoneyTakenExceedsTotalException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyValidateElectricityAirtimeTakenAgainstTotalAlgorithm implements IValidationRule<MobileMoneyValidateElectricityAirtimeTakenAgainstTotal> {
    @Autowired
    private final IAlgorithm<MobileMoneyElectricityAirtimeTotal,BigDecimal> moneyElectricityAirtimeTotalBigDecimalIAlgorithm;
    @Autowired
    private final IAlgorithm<MobileMoneyElectricityAirtimeTotalTaken,BigDecimal> moneyElectricityAirtimeTotalTakenBigDecimalIAlgorithm;
    @Autowired
    private final ModelMapper modelMapper;

    public MobileMoneyValidateElectricityAirtimeTakenAgainstTotalAlgorithm(IAlgorithm<MobileMoneyElectricityAirtimeTotal, BigDecimal> moneyElectricityAirtimeTotalBigDecimalIAlgorithm, IAlgorithm<MobileMoneyElectricityAirtimeTotalTaken, BigDecimal> moneyElectricityAirtimeTotalTakenBigDecimalIAlgorithm, ModelMapper modelMapper) {
        this.moneyElectricityAirtimeTotalBigDecimalIAlgorithm = moneyElectricityAirtimeTotalBigDecimalIAlgorithm;
        this.moneyElectricityAirtimeTotalTakenBigDecimalIAlgorithm = moneyElectricityAirtimeTotalTakenBigDecimalIAlgorithm;
        this.modelMapper = modelMapper;
    }

    @Override
    public void Validate(@NotNull MobileMoneyValidateElectricityAirtimeTakenAgainstTotal input) {

        MobileMoneyElectricityAirtimeTotal mobileMoneyElectricityAirtimeTotal = modelMapper.map(input,MobileMoneyElectricityAirtimeTotal.class);
        MobileMoneyElectricityAirtimeTotalTaken moneyElectricityAirtimeTotalTaken = modelMapper.map(input,MobileMoneyElectricityAirtimeTotalTaken.class);

        BigDecimal total = moneyElectricityAirtimeTotalBigDecimalIAlgorithm.Invoke(mobileMoneyElectricityAirtimeTotal);
        BigDecimal totalTaken = moneyElectricityAirtimeTotalTakenBigDecimalIAlgorithm.Invoke(moneyElectricityAirtimeTotalTaken);

        if(total.compareTo(totalTaken) < 0 )
            throw new MobileMoneyTakenExceedsTotalException("Electricity and Airtime taken exceeds available amount.");
    }
}
