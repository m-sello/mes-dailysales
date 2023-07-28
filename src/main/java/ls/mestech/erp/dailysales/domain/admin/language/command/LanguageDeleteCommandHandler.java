package ls.mestech.erp.dailysales.domain.admin.language.command;

import ls.mestech.erp.dailysales.domain.admin.language.validation.LanguageCheckNullValues;
import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageNotFoundException;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventDeleted;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageDeleteCommandHandler implements ICommandHandler<LanguageDeleteCommand, ResultWithDomainEvent<Language, LanguageEventDeleted>> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IValidationRule<LanguageCheckNullValues> languageCheckNullValuesIValidationRule;

    public LanguageDeleteCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, IValidationRule<LanguageCheckNullValues> languageCheckNullValuesIValidationRule) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.languageCheckNullValuesIValidationRule = languageCheckNullValuesIValidationRule;
    }

    @Override
    public ResultWithDomainEvent<Language, LanguageEventDeleted> Handle(LanguageDeleteCommand languageDeleteCommand) {

        CheckNullValues(languageDeleteCommand);

        Language language = CheckExistence(languageDeleteCommand);

        if(language != null) {

            unitOfWork.LanguageRepository().Remove(language);

            return new ResultWithDomainEvent<>(null, null);
        }
        else {
            throw new LanguageNotFoundException("Language with the code: " + languageDeleteCommand.getLanguageCd() + " cannot be found");
        }
    }

    private Language CheckExistence(LanguageDeleteCommand languageCreateCommand) {
        return unitOfWork.LanguageRepository().FindById(languageCreateCommand.getLanguageCd());
    }

    private void CheckNullValues(LanguageDeleteCommand languageCreateCommand) {
        LanguageCheckNullValues languageCheckNullValues = modelMapper.map(languageCreateCommand, LanguageCheckNullValues.class);
        languageCheckNullValuesIValidationRule.Validate(languageCheckNullValues);
    }
}
