package ls.mestech.erp.dailysales.domain.admin.tendertype.command;

import ls.mestech.erp.dailysales.domain.admin.tendertype.validation.TenderTypeCheckNullValues;
import ls.mestech.erp.dailysales.domain.admin.tendertype.exception.TenderTypeNotFoundException;
import ls.mestech.erp.dailysales.domain.admin.tendertype.lifecycle.State;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.domain.model.TenderTypeLog;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TenderTypeUpdateCommandHandler implements ICommandHandler<TenderTypeUpdateCommand, TenderType> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IValidationRule<TenderTypeCheckNullValues> tenderTypeCheckNullValuesIValidationRule;

    public TenderTypeUpdateCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, IValidationRule<TenderTypeCheckNullValues> tenderTypeCheckNullValuesIValidationRule) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.tenderTypeCheckNullValuesIValidationRule = tenderTypeCheckNullValuesIValidationRule;
    }

    @Override
    public TenderType Handle(TenderTypeUpdateCommand tenderTypeUpdateCommand) {

        CheckNullValues(tenderTypeUpdateCommand);

        tenderTypeUpdateCommand.setTenderTypeCd(tenderTypeUpdateCommand.getTenderTypeCd().toUpperCase());

        TenderType tenderType = unitOfWork.TenderTypeRepository().FindById(tenderTypeUpdateCommand.getTenderTypeCd());

        if (tenderType != null) {

            UpdateFields(tenderType, tenderTypeUpdateCommand);

            tenderType.getTenderTypeLang().clear();

            tenderType.getTenderTypeLang().addAll(tenderTypeUpdateCommand.getTenderTypeLang());

            tenderType.addTenderTypeLog(Log());

            return unitOfWork.TenderTypeRepository().Update(tenderType);
        } else
            throw new TenderTypeNotFoundException("Tender Type Code:" + tenderType.getTenderTypeCd() + " does not exist");
    }

    private void UpdateFields(TenderType tenderType, TenderTypeUpdateCommand tenderTypeUpdateCommand) {
        tenderType.setActiveFlg(tenderTypeUpdateCommand.getActiveFlg());
    }

    private void CheckNullValues(TenderTypeUpdateCommand tenderTypeUpdateCommand) {
        TenderTypeCheckNullValues tenderTypeCheckNullValues = modelMapper.map(tenderTypeUpdateCommand, TenderTypeCheckNullValues.class);
        tenderTypeCheckNullValuesIValidationRule.Validate(tenderTypeCheckNullValues);
    }

    private TenderTypeLog Log() {
        //Add a log
        TenderTypeLog tenderTypeLog = new TenderTypeLog();

        tenderTypeLog.setLogDt(LocalDateTime.now());
        tenderTypeLog.setAction(State.UPDATED.toString());
        tenderTypeLog.setDescription("Tender Type Log Updated");
        tenderTypeLog.setUsername("mesadmin");

        return tenderTypeLog;
    }
}
