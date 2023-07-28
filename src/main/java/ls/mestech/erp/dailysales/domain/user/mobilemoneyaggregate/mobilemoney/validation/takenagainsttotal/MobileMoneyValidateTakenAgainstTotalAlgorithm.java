package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.takenagainsttotal;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.taken.MobileMoneyMpesaTotalTaken;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.algorithm.total.MobileMoneyMpesaTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.MobileMoneyTakenExceedsTotalException;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.currentagainsprevious.MobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDay;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.currentagainsprevious.MobileMoneyValidateEcocashCurrentDayIsMoreThanPreviousDay;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.currentagainsprevious.MobileMoneyValidateElectricityAirtimeCurrentDayIsMoreThanPreviousDay;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.currentagainsprevious.MobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDay;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MobileMoneyValidateTakenAgainstTotalAlgorithm implements IValidationRule<MobileMoneyValidateTakenAgainstTotal> {
    @Autowired
    private final IValidationRule<MobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDayValidator;
    @Autowired
    private final IValidationRule<MobileMoneyValidateEcocashCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateEcocashCurrentDayIsMoreThanPreviousDayValidator;
    @Autowired
    private final IValidationRule<MobileMoneyValidateElectricityAirtimeCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateElectricityAirtimeCurrentDayIsMoreThanPreviousDayValidator;
    @Autowired
    private final IValidationRule<MobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDayValidator;
    @Autowired
    private final ModelMapper modelMapper;

    public MobileMoneyValidateTakenAgainstTotalAlgorithm(IValidationRule<MobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDayValidator, IValidationRule<MobileMoneyValidateEcocashCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateEcocashCurrentDayIsMoreThanPreviousDayValidator, IValidationRule<MobileMoneyValidateElectricityAirtimeCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateElectricityAirtimeCurrentDayIsMoreThanPreviousDayValidator, IValidationRule<MobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDayValidator, ModelMapper modelMapper) {
        this.mobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDayValidator = mobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDayValidator;
        this.mobileMoneyValidateEcocashCurrentDayIsMoreThanPreviousDayValidator = mobileMoneyValidateEcocashCurrentDayIsMoreThanPreviousDayValidator;
        this.mobileMoneyValidateElectricityAirtimeCurrentDayIsMoreThanPreviousDayValidator = mobileMoneyValidateElectricityAirtimeCurrentDayIsMoreThanPreviousDayValidator;
        this.mobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDayValidator = mobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDayValidator;
        this.modelMapper = modelMapper;
    }

    @Override
    public void Validate(@NotNull MobileMoneyValidateTakenAgainstTotal input) {

        MobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDay cashPlus = modelMapper.map(input, MobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDay.class);
        MobileMoneyValidateEcocashCurrentDayIsMoreThanPreviousDay ecoCash = modelMapper.map(input, MobileMoneyValidateEcocashCurrentDayIsMoreThanPreviousDay.class);
        MobileMoneyValidateElectricityAirtimeCurrentDayIsMoreThanPreviousDay electricityAirtime = modelMapper.map(input, MobileMoneyValidateElectricityAirtimeCurrentDayIsMoreThanPreviousDay.class);
        MobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDay mpesa = modelMapper.map(input, MobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDay.class);

        mobileMoneyValidateCashplusCurrentDayIsMoreThanPreviousDayValidator.Validate(cashPlus);
        mobileMoneyValidateEcocashCurrentDayIsMoreThanPreviousDayValidator.Validate(ecoCash);
        mobileMoneyValidateElectricityAirtimeCurrentDayIsMoreThanPreviousDayValidator.Validate(electricityAirtime);
        mobileMoneyValidateMpesaCurrentDayIsMoreThanPreviousDayValidator.Validate(mpesa);
    }
}
