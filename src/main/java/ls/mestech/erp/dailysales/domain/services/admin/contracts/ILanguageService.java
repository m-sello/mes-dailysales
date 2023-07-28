package ls.mestech.erp.dailysales.domain.services.admin.contracts;

import ls.mestech.erp.dailysales.domain.admin.language.command.*;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.*;
import ls.mestech.erp.dailysales.domain.admin.language.query.LanguageGetAllQuery;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.Language;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ILanguageService {
    ResultWithDomainEvent<Language, LanguageEventCreated> Create(LanguageCreateCommand command);
    ResultWithDomainEvent<Language, LanguageEventUpdated> Update(LanguageUpdateCommand command);
    ResultWithDomainEvent<Language, LanguageEventDeleted> Delete(LanguageDeleteCommand command);
    ResultWithDomainEvent<Language, LanguageEventActivated> Activate(LanguageActivateCommand command);
    ResultWithDomainEvent<Language, LanguageEventDeactivated> Deactivate(LanguageDeactivateCommand command);
    List<Language> GetAll(LanguageGetAllQuery query);

    Language GetByCode(String languageCd);
}
