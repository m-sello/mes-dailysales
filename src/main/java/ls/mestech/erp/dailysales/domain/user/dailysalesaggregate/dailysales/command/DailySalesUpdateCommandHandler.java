package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.admin.tendertype.lifecycle.State;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.model.DailySalesLog;
import ls.mestech.erp.dailysales.domain.model.DailyTender;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesCheckNullValues;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesValidateCapturedDate;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesValidateDuplicateByDateCaptured;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.UnsupportedStateTransitionException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.IDomainEventState;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailytenders.validation.DailyTenderCheckDuplicates;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailytenders.validation.DailyTenderCheckNullValues;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class DailySalesUpdateCommandHandler implements ICommandHandler<DailySalesUpdateCommand, DailySales> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IDomainEventState domainEventState;
    @Autowired
    private final IValidationRule<DailyTenderCheckNullValues> dailyTenderCheckNullValuesTendersValidator;
    @Autowired
    private final IValidationRule<DailyTenderCheckDuplicates> dailyTenderCheckDuplicateTendersValidator;
    @Autowired
    private final IValidationRule<DailySalesValidateDuplicateByDateCaptured> dailySalesValidateDuplicateByDateCapturedIValidationRule;
    @Autowired
    private final IValidationRule<DailySalesCheckNullValues> dailySalesCheckNullValuesValidator;
    @Autowired
    private final IValidationRule<DailySalesValidateCapturedDate> dailySalesValidateCapturedDateIValidationRule;

    public DailySalesUpdateCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, IDomainEventState domainEventState, IValidationRule<DailyTenderCheckNullValues> dailyTenderCheckNullValuesTendersValidator, IValidationRule<DailyTenderCheckDuplicates> dailyTenderCheckDuplicateTendersValidator, IValidationRule<DailySalesValidateDuplicateByDateCaptured> dailySalesValidateDuplicateByDateCapturedIValidationRule, IValidationRule<DailySalesCheckNullValues> dailySalesCheckNullValuesValidator, IValidationRule<DailySalesValidateCapturedDate> dailySalesValidateCapturedDateIValidationRule) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.domainEventState = domainEventState;
        this.dailyTenderCheckNullValuesTendersValidator = dailyTenderCheckNullValuesTendersValidator;
        this.dailyTenderCheckDuplicateTendersValidator = dailyTenderCheckDuplicateTendersValidator;
        this.dailySalesValidateDuplicateByDateCapturedIValidationRule = dailySalesValidateDuplicateByDateCapturedIValidationRule;
        this.dailySalesCheckNullValuesValidator = dailySalesCheckNullValuesValidator;
        this.dailySalesValidateCapturedDateIValidationRule = dailySalesValidateCapturedDateIValidationRule;
    }

    @Override
    public DailySales Handle(@NotNull DailySalesUpdateCommand dailySalesUpdateCommand) {

        DailySales dailSales = unitOfWork.DailySalesRepository().FindById(dailySalesUpdateCommand.getId());

        if (dailSales != null) {

            try {
                domainEventState.Create();

                UpdateFields(dailSales, dailySalesUpdateCommand);

                CheckNullValues(dailSales, dailySalesUpdateCommand.getDailyTenders());

                CheckDuplicates(dailSales);

                ValidateCapturedDate(dailSales);

                //dailSales.getDailyTenders().clear();
                //.getDailySalesTaken().clear();

                dailSales.getDailyTenders().addAll(dailySalesUpdateCommand.getDailyTenders());

                dailSales.getDailySalesTaken().addAll(dailySalesUpdateCommand.getDailySalesTaken());

                dailSales.addDailySalesLogs(Log());

                unitOfWork.DailySalesRepository().Update((dailSales));

            } catch (UnsupportedStateTransitionException e) {
                throw new RuntimeException(e);
            }

            unitOfWork.DailySalesRepository().Add(dailSales);

            return dailSales;
        }

        throw new ObjectNotFoundException(dailySalesUpdateCommand, "Daily Sales ID not found");
    }

    private void UpdateFields(DailySales dailSales, DailySalesUpdateCommand dailySalesUpdateCommand) {
        dailSales.setCapturedDt(dailySalesUpdateCommand.getCapturedDt());
        dailSales.setComments(dailySalesUpdateCommand.getComments());
        dailSales.setFloatAmount(dailySalesUpdateCommand.getFloatAmount());

        dailSales.getDailyTenders().addAll(dailySalesUpdateCommand.getDailyTenders());
        dailSales.getDailySalesTaken().addAll(dailySalesUpdateCommand.getDailySalesTaken());
    }

    private void ValidateCapturedDate(DailySales dailSales) {
        dailySalesValidateCapturedDateIValidationRule.Validate(new DailySalesValidateCapturedDate(dailSales.getId(),dailSales.getCapturedDt(),true));
    }

    private void CheckNullValues(DailySales dailSales, Set<DailyTender> dailyTenderSet) {
        CheckNullValuesDailySales(dailSales);
        CheckNullValuesDailyTenders(dailyTenderSet);
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
        DailySalesValidateDuplicateByDateCaptured dailySalesValidateDuplicateByDateCaptured = modelMapper.map(dailSales, DailySalesValidateDuplicateByDateCaptured.class);
        dailySalesValidateDuplicateByDateCaptured.setUpdateFlg(true);
        dailySalesValidateDuplicateByDateCapturedIValidationRule.Validate(dailySalesValidateDuplicateByDateCaptured);
    }

    private void CheckNullValuesDailyTenders(Set<DailyTender> dailyTenderSet) {
        DailyTenderCheckNullValues checkNullValues = new DailyTenderCheckNullValues();
        checkNullValues.setDailyTenderSet(dailyTenderSet);
        dailyTenderCheckNullValuesTendersValidator.Validate(checkNullValues);
    }
    private void CheckDuplicatesDailyTenders(DailySales dailSales) {
        DailyTenderCheckDuplicates checkDuplicates = new DailyTenderCheckDuplicates();
        checkDuplicates.setDailyTenderSet(dailSales.getDailyTenders());
        dailyTenderCheckDuplicateTendersValidator.Validate(checkDuplicates);
    }
    private DailySalesLog Log() {
        //Add a log
        DailySalesLog dailySalesLog = new DailySalesLog();

        dailySalesLog.setLogDt(LocalDateTime.now());
        dailySalesLog.setAction(State.UPDATED.toString());
        dailySalesLog.setDescription("Daily Sales Updated");
        dailySalesLog.setUsername("mesadmin");

        return dailySalesLog;
    }
}
