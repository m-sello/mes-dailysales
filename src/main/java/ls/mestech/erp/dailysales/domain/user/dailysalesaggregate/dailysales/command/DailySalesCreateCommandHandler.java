package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command;

import ls.mestech.erp.dailysales.domain.admin.tendertype.lifecycle.State;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.messages.Messages;
import ls.mestech.erp.dailysales.domain.model.DailySalesLog;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesCheckDuplicates;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesCheckNullValues;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesValidateCapturedDate;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.UnsupportedStateTransitionException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.DailySalesEventPending;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.IDomainEventState;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DuplicateDailySalesException;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailytenders.validation.DailyTenderCheckDuplicates;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailytenders.validation.DailyTenderCheckNullValues;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DailySalesCreateCommandHandler implements ICommandHandler<DailySalesCreateCommand, DailySales> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final Messages LogMessages;
    @Autowired
    private final IDomainEventState domainEventState;
    @Autowired
    private final IValidationRule<DailyTenderCheckNullValues> dailyTenderCheckNullValuesTendersValidator;
    @Autowired
    private final IValidationRule<DailyTenderCheckDuplicates> dailyTenderCheckDuplicateTendersValidator;
    @Autowired
    private final IValidationRule<DailySalesCheckDuplicates> dailySalesCheckDuplicatesValidator;
    @Autowired
    private final IValidationRule<DailySalesCheckNullValues> dailySalesCheckNullValuesValidator;
    @Autowired
    private final IValidationRule<DailySalesValidateCapturedDate> dailySalesValidateCapturedDateIValidationRule;

    public DailySalesCreateCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, Messages logMessages, IDomainEventState domainEventState, IValidationRule<DailyTenderCheckNullValues> dailyTenderCheckNullValuesTendersValidator, IValidationRule<DailyTenderCheckDuplicates> dailyTenderCheckDuplicateTendersValidator, IValidationRule<DailySalesCheckDuplicates> dailySalesCheckDuplicatesValidator, IValidationRule<DailySalesCheckNullValues> dailySalesCheckNullValuesValidator, IValidationRule<DailySalesValidateCapturedDate> dailySalesValidateCapturedDateIValidationRule) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        LogMessages = logMessages;
        this.domainEventState = domainEventState;
        this.dailyTenderCheckNullValuesTendersValidator = dailyTenderCheckNullValuesTendersValidator;
        this.dailyTenderCheckDuplicateTendersValidator = dailyTenderCheckDuplicateTendersValidator;
        this.dailySalesCheckDuplicatesValidator = dailySalesCheckDuplicatesValidator;
        this.dailySalesCheckNullValuesValidator = dailySalesCheckNullValuesValidator;
        this.dailySalesValidateCapturedDateIValidationRule = dailySalesValidateCapturedDateIValidationRule;
    }


    @Override
    public DailySales Handle(DailySalesCreateCommand dailySalesCreateCommand) {
        DailySales dailSales = modelMapper.map(dailySalesCreateCommand, DailySales.class);

        try {
            domainEventState.Create();
            DailySales getDailySales = unitOfWork.DailySalesRepository().GetByCapturedDt(dailSales.getCapturedDt());

            if(getDailySales == null) {

                CheckNullValues(dailSales);

                CheckDuplicates(dailSales);

                ValidateCapturedDate(dailSales);

                dailSales.getDailyTenders().addAll(dailySalesCreateCommand.getDailyTenders());

                dailSales.getDailySalesTaken().addAll(dailySalesCreateCommand.getDailySalesTaken());

                dailSales.addDailySalesLogs(Log());

                unitOfWork.DailySalesRepository().Add(dailSales);
            }
            else
                throw new DuplicateDailySalesException("Duplicate Daily Sales Record");

        } catch (UnsupportedStateTransitionException e) {
            throw e;
        }

        DailySalesEventPending createdEvent = (DailySalesEventPending) domainEventState.GetCurrentState();

        return dailSales;
    }
    private void ValidateCapturedDate(DailySales dailSales) {
        dailySalesValidateCapturedDateIValidationRule.Validate(new DailySalesValidateCapturedDate(dailSales.getId(),dailSales.getCapturedDt(),false));
    }
    private void CheckNullValues(DailySales dailSales) {
        CheckNullValuesDailySales(dailSales);
        CheckNullValuesDailyTenders(dailSales);
    }
    private void CheckDuplicates(DailySales dailSales) {
        CheckDuplicatesDailySales(dailSales);
        CheckDuplicatesDailyTenders(dailSales);
    }
    private void CheckNullValuesDailySales(DailySales dailSales){
        DailySalesCheckNullValues dailySalesCheckNullValues = modelMapper.map(dailSales, DailySalesCheckNullValues.class);
        dailySalesCheckNullValuesValidator.Validate(dailySalesCheckNullValues);
    }

    private void CheckDuplicatesDailySales(DailySales dailSales) {
        DailySalesCheckDuplicates dailySalesCheckDuplicates = modelMapper.map(dailSales, DailySalesCheckDuplicates.class);
        dailySalesCheckDuplicatesValidator.Validate(dailySalesCheckDuplicates);
    }

    private void CheckNullValuesDailyTenders(DailySales dailSales) {
        DailyTenderCheckNullValues checkNullValues = new DailyTenderCheckNullValues();
        checkNullValues.setDailyTenderSet(dailSales.getDailyTenders());
        dailyTenderCheckNullValuesTendersValidator.Validate(checkNullValues);
    }
    private void CheckDuplicatesDailyTenders(DailySales dailSales) {
        DailyTenderCheckDuplicates checkDuplicates = new DailyTenderCheckDuplicates();
        checkDuplicates.setDailyTenderSet(dailSales.getDailyTenders());
        dailyTenderCheckDuplicateTendersValidator.Validate(checkDuplicates);
    }
    private DailySalesLog Log() {
        DailySalesLog dailySalesLog = new DailySalesLog();

        dailySalesLog.setLogDt(LocalDateTime.now());
        dailySalesLog.setAction(State.CREATED.toString());
        dailySalesLog.setDescription("New Daily Sales Created");
        dailySalesLog.setUsername("mesadmin");

        return dailySalesLog;
    }
}
