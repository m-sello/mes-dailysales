package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.currentagainsprevious;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.takenagainsttotal.MobileMoneyValidateCashplusTakenAgainstTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.takenagainsttotal.MobileMoneyValidateEcocashTakenAgainstTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.takenagainsttotal.MobileMoneyValidateElectricityAirtimeTakenAgainstTotal;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.takenagainsttotal.MobileMoneyValidateMpesaTakenAgainstTotal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidator implements IValidationRule<MobileMoneyValidateCurrentDayIsMoreThanPreviousDay> {

    @Autowired
    private final IValidationRule<MobileMoneyValidateCashplusTakenAgainstTotal> moneyValidateCashplusTakenAgainstTotalValidator;
    @Autowired
    private final IValidationRule<MobileMoneyValidateEcocashTakenAgainstTotal> moneyValidateEcocashTakenAgainstTotalValidator;
    @Autowired
    private final IValidationRule<MobileMoneyValidateElectricityAirtimeTakenAgainstTotal> moneyValidateElectricityAirtimeTakenAgainstTotalValidator;
    @Autowired
    private final IValidationRule<MobileMoneyValidateMpesaTakenAgainstTotal> moneyValidateMpesaTakenAgainstTotalValidator;
    @Autowired
    private final ModelMapper modelMapper;

    public MobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidator(IValidationRule<MobileMoneyValidateCashplusTakenAgainstTotal> moneyValidateCashplusTakenAgainstTotalValidator, IValidationRule<MobileMoneyValidateEcocashTakenAgainstTotal> moneyValidateEcocashTakenAgainstTotalValidator, IValidationRule<MobileMoneyValidateElectricityAirtimeTakenAgainstTotal> moneyValidateElectricityAirtimeTakenAgainstTotalValidator, IValidationRule<MobileMoneyValidateMpesaTakenAgainstTotal> moneyValidateMpesaTakenAgainstTotalValidator, ModelMapper modelMapper) {
        this.moneyValidateCashplusTakenAgainstTotalValidator = moneyValidateCashplusTakenAgainstTotalValidator;
        this.moneyValidateEcocashTakenAgainstTotalValidator = moneyValidateEcocashTakenAgainstTotalValidator;
        this.moneyValidateElectricityAirtimeTakenAgainstTotalValidator = moneyValidateElectricityAirtimeTakenAgainstTotalValidator;
        this.moneyValidateMpesaTakenAgainstTotalValidator = moneyValidateMpesaTakenAgainstTotalValidator;
        this.modelMapper = modelMapper;
    }

    @Override
    public void Validate(@NotNull MobileMoneyValidateCurrentDayIsMoreThanPreviousDay input) {

        MobileMoneyValidateCashplusTakenAgainstTotal cashPlus = modelMapper.map(input, MobileMoneyValidateCashplusTakenAgainstTotal.class);
        MobileMoneyValidateEcocashTakenAgainstTotal ecoCash = modelMapper.map(input, MobileMoneyValidateEcocashTakenAgainstTotal.class);
        MobileMoneyValidateElectricityAirtimeTakenAgainstTotal electricityAirtime = modelMapper.map(input, MobileMoneyValidateElectricityAirtimeTakenAgainstTotal.class);
        MobileMoneyValidateMpesaTakenAgainstTotal mpesa = modelMapper.map(input, MobileMoneyValidateMpesaTakenAgainstTotal.class);

        moneyValidateCashplusTakenAgainstTotalValidator.Validate(cashPlus);
        moneyValidateEcocashTakenAgainstTotalValidator.Validate(ecoCash);
        moneyValidateElectricityAirtimeTakenAgainstTotalValidator.Validate(electricityAirtime);
        moneyValidateMpesaTakenAgainstTotalValidator.Validate(mpesa);
    }
}
