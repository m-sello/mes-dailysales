package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.admin.tendertype.lifecycle.State;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.messages.Messages;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.model.MobileMoneyLog;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.currentagainsprevious.*;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.takenagainsttotal.*;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.duplicate.MobileMoneyCheckDuplicates;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.DuplicateMobileMoneyException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MobileMoneyCreateCommandHandler implements ICommandHandler<MobileMoneyCreateCommand, MobileMoney> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final Messages LogMessages;
    @Autowired
    private final IValidationRule<MobileMoneyCheckDuplicates> dailySalesCheckDuplicatesValidator;
    @Autowired
    private final IValidationRule<MobileMoneyValidateCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidators;
    @Autowired
    private final IValidationRule<MobileMoneyValidateTakenAgainstTotal> mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidator;

    public MobileMoneyCreateCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, Messages logMessages, IValidationRule<MobileMoneyCheckDuplicates> dailySalesCheckDuplicatesValidator, IValidationRule<MobileMoneyValidateCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidators, IValidationRule<MobileMoneyValidateTakenAgainstTotal> mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidator) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        LogMessages = logMessages;
        this.dailySalesCheckDuplicatesValidator = dailySalesCheckDuplicatesValidator;
        this.mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidators = mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidators;
        this.mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidator = mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidator;
    }

    @Override
    public MobileMoney Handle(@NotNull MobileMoneyCreateCommand mobileMoneyCreateCommand) {
        MobileMoney mobileMoney = modelMapper.map(mobileMoneyCreateCommand, MobileMoney.class);

        MobileMoney getMobileMoney = unitOfWork.MobileMoneyRepository().GetByDailySalesId(mobileMoneyCreateCommand.getDailySalesId());

        if(getMobileMoney == null) {

            CheckDuplicates(mobileMoney);

            Validate(mobileMoney);

            mobileMoney.addMobileMoneyLog(Log());

            unitOfWork.MobileMoneyRepository().Add(mobileMoney);
        }
        else
            throw new DuplicateMobileMoneyException("Duplicate Mobile Money Record is now allowed");

        return mobileMoney;
    }

    private void Validate(MobileMoney mobileMoney) {

        ValidateTakenAgainstTotals(mobileMoney);
        ValidatePreviousAndCurrentTotals(mobileMoney);
    }

    private void ValidateTakenAgainstTotals(MobileMoney mobileMoney) {

        MobileMoneyValidateCurrentDayIsMoreThanPreviousDay mobileMoneyAll = modelMapper.map(mobileMoney, MobileMoneyValidateCurrentDayIsMoreThanPreviousDay.class);
        mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidators.Validate(mobileMoneyAll);
    }

    private void ValidatePreviousAndCurrentTotals(MobileMoney mobileMoney) {

        MobileMoneyValidateTakenAgainstTotal mobileMoneyAll = modelMapper.map(mobileMoney, MobileMoneyValidateTakenAgainstTotal.class);
        mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidator.Validate(mobileMoneyAll);
    }

    private void CheckDuplicates(MobileMoney mobileMoney) {

        CheckDuplicatesMobileMoney(mobileMoney);
    }

    private void CheckDuplicatesMobileMoney(MobileMoney mobileMoney){
        MobileMoneyCheckDuplicates dailySalesCheckDuplicates = modelMapper.map(mobileMoney, MobileMoneyCheckDuplicates.class);
        dailySalesCheckDuplicatesValidator.Validate(dailySalesCheckDuplicates);
    }
    private MobileMoneyLog Log() {
        MobileMoneyLog mobileMoneyLog = new MobileMoneyLog();

        mobileMoneyLog.setLogDt(LocalDateTime.now());
        mobileMoneyLog.setAction(State.CREATED.toString());
        mobileMoneyLog.setDescription("New Mobile Money Created");
        mobileMoneyLog.setUsername("mesadmin");

        return mobileMoneyLog;
    }
}
