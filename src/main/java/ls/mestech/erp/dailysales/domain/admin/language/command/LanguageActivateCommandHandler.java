package ls.mestech.erp.dailysales.domain.admin.language.command;

import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventActivated;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.repository.ILanguageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageActivateCommandHandler implements ICommandHandler<LanguageActivateCommand, ResultWithDomainEvent<Language, LanguageEventActivated>> {
    @Autowired
    private final ILanguageRepository languageRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public LanguageActivateCommandHandler(ILanguageRepository languageRepository, ModelMapper modelMapper) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultWithDomainEvent<Language, LanguageEventActivated> Handle(LanguageActivateCommand languageActivateCommand) {
        Language language = modelMapper.map(languageActivateCommand, Language.class);


        LanguageEventActivated languageEventActivated = new LanguageEventActivated(language.getLanguageCd(), language.getName());

        return new ResultWithDomainEvent<>(language,languageEventActivated);
    }
}
