package ls.mestech.erp.dailysales.domain.services.admin.impl;

import ls.mestech.erp.dailysales.domain.admin.language.command.*;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.*;
import ls.mestech.erp.dailysales.domain.admin.language.query.LanguageGetAllQuery;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.services.admin.contracts.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LanguageService implements ILanguageService {
    @Autowired
    private final ICommandHandler<LanguageCreateCommand, ResultWithDomainEvent<Language, LanguageEventCreated>> languageCreateCommandHandler;
    @Autowired
    private final ICommandHandler<LanguageActivateCommand, ResultWithDomainEvent<Language, LanguageEventActivated>> languageActivateCommandHandler;
    @Autowired
    private final ICommandHandler<LanguageUpdateCommand, ResultWithDomainEvent<Language, LanguageEventUpdated>> languageUpdateCommandHandler;
    @Autowired
    private final ICommandHandler<LanguageDeleteCommand, ResultWithDomainEvent<Language, LanguageEventDeleted>> languageDeleteCommandHandler;
    @Autowired
    private final ICommandHandler<LanguageDeactivateCommand, ResultWithDomainEvent<Language, LanguageEventDeactivated>> languageDeactivateCommandHandler;
    @Autowired
    private final IQueryHandler<LanguageGetAllQuery, List<Language>> languageGetAllQueryQueryHandler;
    @Autowired
    private final IUnitOfWork unitOfWork;

    public LanguageService(ICommandHandler<LanguageCreateCommand, ResultWithDomainEvent<Language, LanguageEventCreated>> languageCreateCommandHandler,
                           ICommandHandler<LanguageActivateCommand, ResultWithDomainEvent<Language, LanguageEventActivated>> languageActivateCommandHandler,
                           ICommandHandler<LanguageUpdateCommand, ResultWithDomainEvent<Language, LanguageEventUpdated>> languageUpdateCommandHandler,
                           ICommandHandler<LanguageDeleteCommand, ResultWithDomainEvent<Language, LanguageEventDeleted>> languageDeleteCommandHandler,
                           ICommandHandler<LanguageDeactivateCommand, ResultWithDomainEvent<Language, LanguageEventDeactivated>> languageDeactivateCommandHandler,
                           IQueryHandler<LanguageGetAllQuery, List<Language>> languageGetAllQueryQueryHandler, IUnitOfWork unitOfWork) {
        this.languageCreateCommandHandler = languageCreateCommandHandler;
        this.languageActivateCommandHandler = languageActivateCommandHandler;
        this.languageUpdateCommandHandler = languageUpdateCommandHandler;
        this.languageDeleteCommandHandler = languageDeleteCommandHandler;
        this.languageDeactivateCommandHandler = languageDeactivateCommandHandler;
        this.languageGetAllQueryQueryHandler = languageGetAllQueryQueryHandler;
        this.unitOfWork = unitOfWork;
    }


    @Override
    public ResultWithDomainEvent<Language, LanguageEventCreated> Create(LanguageCreateCommand command) {
        return languageCreateCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<Language, LanguageEventUpdated> Update(LanguageUpdateCommand command) {
        return languageUpdateCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<Language, LanguageEventDeleted> Delete(LanguageDeleteCommand command) {
        return languageDeleteCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<Language, LanguageEventActivated> Activate(LanguageActivateCommand command) {
        return languageActivateCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<Language, LanguageEventDeactivated> Deactivate(LanguageDeactivateCommand command) {
        return languageDeactivateCommandHandler.Handle(command);
    }

    @Override
    public List<Language> GetAll(LanguageGetAllQuery query) {
        return languageGetAllQueryQueryHandler.Handle(query);
    }

    @Override
    public Language GetByCode(String languageCd) {
        return unitOfWork.LanguageRepository().FindById(languageCd);
    }
}
