package ls.mestech.erp.dailysales.domain.services.admin.contracts;

import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.*;
import ls.mestech.erp.dailysales.domain.admin.tendertype.command.*;
import ls.mestech.erp.dailysales.domain.admin.tendertype.query.TenderTypeGetAllQuery;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.TenderType;

import java.util.List;

public interface ITenderTypeService {
    TenderType Create(TenderTypeCreateCommand command);
    TenderType Update(TenderTypeUpdateCommand command);
    TenderType Delete(TenderTypeDeleteCommand command);
    TenderType Activate(TenderTypeActivateCommand command);
    TenderType Deactivate(TenderTypeDeactivateCommand command);
    List<TenderType> GetAll(TenderTypeGetAllQuery query);

    TenderType GetByCode(String languageCd);
}
