package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.currentagainsprevious;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.algorithm.DailySalesPreviousRecord;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesNotFoundException;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.grandtotal.MobileMoneyCashplusGrandTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.MobileMoneyBalanceShortageException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDayValidator implements IValidationRule<MobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDay> {

    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IAlgorithm<MobileMoneyCashplusGrandTotal, BigDecimal> moneyCashplusGrandTotalAlgorithm;
    @Autowired
    private final IAlgorithm<DailySalesPreviousRecord, DailySales> previousRecordDailySalesIAlgorithm;

    public MobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDayValidator(IUnitOfWork unitOfWork, ModelMapper modelMapper, IAlgorithm<MobileMoneyCashplusGrandTotal, BigDecimal> moneyCashplusGrandTotalAlgorithm, IAlgorithm<DailySalesPreviousRecord, DailySales> previousRecordDailySalesIAlgorithm) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.moneyCashplusGrandTotalAlgorithm = moneyCashplusGrandTotalAlgorithm;
        this.previousRecordDailySalesIAlgorithm = previousRecordDailySalesIAlgorithm;
    }

    @Override
    public void Validate(@NotNull MobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDay input) {

        DailySales dailySales = unitOfWork.DailySalesRepository().FindById(input.getDailySalesId());

        if(dailySales != null) {

            DailySales previousDailySales = previousRecordDailySalesIAlgorithm.Invoke(new DailySalesPreviousRecord(input.getDailySalesId()));

            if(previousDailySales != null){

                //Total for previous day should be less than total for current day
                MobileMoneyCashplusGrandTotal moneyMpesaGrandTotal = modelMapper.map(dailySales, MobileMoneyCashplusGrandTotal.class);
                MobileMoneyCashplusGrandTotal moneyMpesaGrandTotal1 = modelMapper.map(previousDailySales, MobileMoneyCashplusGrandTotal.class);

                BigDecimal currentDayTotal = moneyCashplusGrandTotalAlgorithm.Invoke(moneyMpesaGrandTotal);
                BigDecimal previousDayTotal = moneyCashplusGrandTotalAlgorithm.Invoke(moneyMpesaGrandTotal1);

                if(currentDayTotal.compareTo(previousDayTotal) < 0)
                    throw new MobileMoneyBalanceShortageException("CashPlus today's balance cannot be less than previous day's balance");
            }
        }
        else
            throw new DailySalesNotFoundException("Daily Sales with ID:" + input.getDailySalesId() + " is not found");
    }
}
