package ls.mestech.erp.dailysales.domain.admin.language.command;

import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventDeactivated;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.repository.ILanguageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageDeactivateCommandHandler implements ICommandHandler<LanguageDeactivateCommand, ResultWithDomainEvent<Language, LanguageEventDeactivated>> {
    @Autowired
    private final ILanguageRepository languageRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public LanguageDeactivateCommandHandler(ILanguageRepository languageRepository, ModelMapper modelMapper) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultWithDomainEvent<Language, LanguageEventDeactivated> Handle(LanguageDeactivateCommand languageCreateCommand) {
        Language language = modelMapper.map(languageCreateCommand, Language.class);

        LanguageEventDeactivated languageEventDeactivated = new LanguageEventDeactivated(language.getLanguageCd(), language.getName());

        return new ResultWithDomainEvent<>(language,languageEventDeactivated);
    }
}
