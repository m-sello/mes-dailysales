package ls.mestech.erp.dailysales.domain.admin.language.command;

import ls.mestech.erp.dailysales.domain.admin.language.validation.LanguageCheckNullValues;
import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageNotFoundException;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventUpdated;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageUpdateCommandHandler implements ICommandHandler<LanguageUpdateCommand, ResultWithDomainEvent<Language, LanguageEventUpdated>> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IValidationRule<LanguageCheckNullValues> languageCheckNullValuesIValidationRule;

    public LanguageUpdateCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, IValidationRule<LanguageCheckNullValues> languageCheckNullValuesIValidationRule) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.languageCheckNullValuesIValidationRule = languageCheckNullValuesIValidationRule;
    }

    @Override
    public ResultWithDomainEvent<Language, LanguageEventUpdated> Handle(LanguageUpdateCommand languageUpdateCommand) {

        CheckNullValues(languageUpdateCommand);

        setFieldsToUpperCase(languageUpdateCommand);

        Language language = CheckExistence(languageUpdateCommand);

        if(language != null) {

            UpdateFields(language, languageUpdateCommand);

            LanguageEventUpdated languageEventUpdated = modelMapper.map(language, LanguageEventUpdated.class);

            Language updatedLanguage = unitOfWork.LanguageRepository().Update(language);

            return new ResultWithDomainEvent<>(updatedLanguage, languageEventUpdated);
        }
        else {
            throw new LanguageNotFoundException("Language with the code: " + languageUpdateCommand.getLanguageCd() + " cannot be found");
        }
    }

    private void UpdateFields(Language language, LanguageUpdateCommand languageUpdateCommand) {
        language.setName(languageUpdateCommand.getName());
    }

    private Language CheckExistence(LanguageUpdateCommand languageUpdateCommand) {
        return unitOfWork.LanguageRepository().FindById(languageUpdateCommand.getLanguageCd());
    }

    private void CheckNullValues(LanguageUpdateCommand languageCreateCommand) {
        LanguageCheckNullValues languageCheckNullValues = modelMapper.map(languageCreateCommand, LanguageCheckNullValues.class);
        languageCheckNullValuesIValidationRule.Validate(languageCheckNullValues);
    }

    private void setFieldsToUpperCase(LanguageUpdateCommand languageCreateCommand) {
        languageCreateCommand.setLanguageCd(languageCreateCommand.getLanguageCd().toUpperCase());
        languageCreateCommand.setName(languageCreateCommand.getName().toUpperCase());
    }
}
