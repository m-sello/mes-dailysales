package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.currentagainsprevious;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.algorithm.DailySalesPreviousRecord;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesNotFoundException;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.grandtotal.MobileMoneyMpesaGrandTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.MobileMoneyBalanceShortageException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDayValidator implements IValidationRule<MobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDay> {

    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IAlgorithm<MobileMoneyMpesaGrandTotal, BigDecimal> moneyMpesaGrandTotalAlgorithm;
    @Autowired
    private final IAlgorithm<DailySalesPreviousRecord, DailySales> previousRecordDailySalesIAlgorithm;
    public MobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDayValidator(IUnitOfWork unitOfWork, ModelMapper modelMapper, IAlgorithm<MobileMoneyMpesaGrandTotal, BigDecimal> moneyMpesaGrandTotalAlgorithm, IAlgorithm<DailySalesPreviousRecord, DailySales> previousRecordDailySalesIAlgorithm) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.moneyMpesaGrandTotalAlgorithm = moneyMpesaGrandTotalAlgorithm;
        this.previousRecordDailySalesIAlgorithm = previousRecordDailySalesIAlgorithm;
    }

    @Override
    public void Validate(@NotNull MobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDay input) {

        DailySales dailySales = unitOfWork.DailySalesRepository().FindById(input.getDailySalesId());

        if(dailySales != null) {

            DailySales previousDailySales = previousRecordDailySalesIAlgorithm.Invoke(new DailySalesPreviousRecord(input.getDailySalesId()));

            if(previousDailySales != null){

                //Total for previous day should be less than total for current day
                MobileMoneyMpesaGrandTotal currentMoneyGrandTotal = modelMapper.map(dailySales, MobileMoneyMpesaGrandTotal.class);
                MobileMoneyMpesaGrandTotal previousMoneyGrandTotal = modelMapper.map(previousDailySales, MobileMoneyMpesaGrandTotal.class);

                BigDecimal currentDayTotal = moneyMpesaGrandTotalAlgorithm.Invoke(currentMoneyGrandTotal);
                BigDecimal previousDayTotal = moneyMpesaGrandTotalAlgorithm.Invoke(previousMoneyGrandTotal);

                if(currentDayTotal.compareTo(previousDayTotal) < 0)
                    throw new MobileMoneyBalanceShortageException("Mpesa today's balance cannot be less than previous day's balance");
            }
        }
        else
            throw new DailySalesNotFoundException("Daily Sales with ID:" + input.getDailySalesId() + " is not found");
    }
}
