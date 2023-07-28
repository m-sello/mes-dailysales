package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.admin.tendertype.lifecycle.State;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.messages.Messages;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.model.MobileMoneyLog;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.currentagainsprevious.MobileMoneyValidateCurrentDayIsMoreThanPreviousDay;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.takenagainsttotal.*;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.duplicate.MobileMoneyCheckDuplicates;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.MobileMoneyNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MobileMoneyUpdateCommandHandler implements ICommandHandler<MobileMoneyUpdateCommand, MobileMoney> {
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

    public MobileMoneyUpdateCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, Messages logMessages, IValidationRule<MobileMoneyCheckDuplicates> dailySalesCheckDuplicatesValidator, IValidationRule<MobileMoneyValidateCurrentDayIsMoreThanPreviousDay> mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidators, IValidationRule<MobileMoneyValidateTakenAgainstTotal> mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidator) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        LogMessages = logMessages;
        this.dailySalesCheckDuplicatesValidator = dailySalesCheckDuplicatesValidator;
        this.mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidators = mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidators;
        this.mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidator = mobileMoneyValidateCurrentDayIsMoreThanPreviousDayValidator;
    }


    @Override
    public MobileMoney Handle(@NotNull MobileMoneyUpdateCommand mobileMoneyUpdateCommand) {

        MobileMoney mobileMoney = unitOfWork.MobileMoneyRepository().FindById(mobileMoneyUpdateCommand.getId());

        if (mobileMoney != null) {

            CheckDuplicates(mobileMoney);
            
            UpdateFields(mobileMoney, mobileMoneyUpdateCommand);

            Validate(mobileMoney);

            mobileMoney.addMobileMoneyLog(Log());

            unitOfWork.MobileMoneyRepository().Add(mobileMoney);

            return mobileMoney;
        } else {
            throw new MobileMoneyNotFoundException("Mobile Money ID: " + mobileMoneyUpdateCommand.getId().toString() + " not found");
        }
    }

    private void UpdateFields(MobileMoney mobileMoney, MobileMoneyUpdateCommand mobileMoneyUpdateCommand) {
        mobileMoney.setAirtimeAmount(mobileMoneyUpdateCommand.getAirtimeAmount());
        mobileMoney.setCashplusAmount(mobileMoneyUpdateCommand.getCashplusAmount());
        mobileMoney.setEcocashAmount(mobileMoneyUpdateCommand.getEcocashAmount());
        mobileMoney.setElectricityAmount(mobileMoneyUpdateCommand.getElectricityAmount());
        mobileMoney.setMpesaAmount(mobileMoneyUpdateCommand.getMpesaAmount());

        mobileMoney.setMobileCashplus(mobileMoneyUpdateCommand.getMobileCashplus());
        mobileMoney.setMobileEcocash(mobileMoneyUpdateCommand.getMobileEcocash());
        mobileMoney.setMobileElectricityAirtime(mobileMoneyUpdateCommand.getMobileElectricityAirtime());
        mobileMoney.setMobileMpesa(mobileMoneyUpdateCommand.getMobileMpesa());

        mobileMoney.setComments(mobileMoneyUpdateCommand.getComments());
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
        mobileMoneyLog.setAction(State.UPDATED.toString());
        mobileMoneyLog.setDescription("Mobile Money Updated");
        mobileMoneyLog.setUsername("mesadmin");

        return mobileMoneyLog;
    }
}
