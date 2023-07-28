package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.grandtotal;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total.MobileMoneyElectricityAirtimeTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken.MobileMoneyElectricityAirtimeTotalTaken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyElectricityAirtimeGrandTotalAlgorithm implements IAlgorithm<MobileMoneyElectricityAirtimeGrandTotal,BigDecimal> {
    @Autowired
    private final IAlgorithm<MobileMoneyElectricityAirtimeTotal,BigDecimal> moneyElectricityAirtimeTotalBigDecimalIAlgorithm;
    @Autowired
    private final IAlgorithm<MobileMoneyElectricityAirtimeTotalTaken,BigDecimal> moneyElectricityAirtimeTotalTakenBigDecimalIAlgorithm;
    @Autowired
    private final ModelMapper modelMapper;

    public MobileMoneyElectricityAirtimeGrandTotalAlgorithm(IAlgorithm<MobileMoneyElectricityAirtimeTotal, BigDecimal> moneyElectricityAirtimeTotalBigDecimalIAlgorithm, IAlgorithm<MobileMoneyElectricityAirtimeTotalTaken, BigDecimal> moneyElectricityAirtimeTotalTakenBigDecimalIAlgorithm, ModelMapper modelMapper) {
        this.moneyElectricityAirtimeTotalBigDecimalIAlgorithm = moneyElectricityAirtimeTotalBigDecimalIAlgorithm;
        this.moneyElectricityAirtimeTotalTakenBigDecimalIAlgorithm = moneyElectricityAirtimeTotalTakenBigDecimalIAlgorithm;
        this.modelMapper = modelMapper;
    }

    @Override
    public BigDecimal Invoke(@NotNull MobileMoneyElectricityAirtimeGrandTotal input) {

        MobileMoneyElectricityAirtimeTotal mobileMoneyElectricityAirtimeTotal = modelMapper.map(input,MobileMoneyElectricityAirtimeTotal.class);
        MobileMoneyElectricityAirtimeTotalTaken moneyElectricityAirtimeTotalTaken = modelMapper.map(input,MobileMoneyElectricityAirtimeTotalTaken.class);

        BigDecimal total = moneyElectricityAirtimeTotalBigDecimalIAlgorithm.Invoke(mobileMoneyElectricityAirtimeTotal);
        BigDecimal totalTaken = moneyElectricityAirtimeTotalTakenBigDecimalIAlgorithm.Invoke(moneyElectricityAirtimeTotalTaken);

        return total.add(totalTaken);
    }
}
