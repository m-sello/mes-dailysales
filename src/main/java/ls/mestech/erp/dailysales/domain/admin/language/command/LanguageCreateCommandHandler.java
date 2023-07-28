package ls.mestech.erp.dailysales.domain.admin.language.command;

import ls.mestech.erp.dailysales.domain.admin.language.validation.LanguageCheckDuplicates;
import ls.mestech.erp.dailysales.domain.admin.language.validation.LanguageCheckNullValues;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventCreated;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageCreateCommandHandler implements ICommandHandler<LanguageCreateCommand, ResultWithDomainEvent<Language, LanguageEventCreated>> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IValidationRule<LanguageCheckNullValues> languageCheckNullValuesIValidationRule;
    @Autowired
    private final IValidationRule<LanguageCheckDuplicates> languageCheckDuplicatesIValidationRule;

    public LanguageCreateCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, IValidationRule<LanguageCheckNullValues> languageCheckNullValuesIValidationRule, IValidationRule<LanguageCheckDuplicates> languageCheckDuplicatesIValidationRule) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.languageCheckNullValuesIValidationRule = languageCheckNullValuesIValidationRule;
        this.languageCheckDuplicatesIValidationRule = languageCheckDuplicatesIValidationRule;
    }

    @Override
    public ResultWithDomainEvent<Language, LanguageEventCreated> Handle(LanguageCreateCommand languageCreateCommand) {

        CheckNullValues(languageCreateCommand);

        setFieldsToUpperCase(languageCreateCommand);

        CheckDuplicates(languageCreateCommand);

        Language language = modelMapper.map(languageCreateCommand, Language.class);

        LanguageEventCreated languageEventCreated = modelMapper.map(languageCreateCommand, LanguageEventCreated.class);

        Language newLanguage = unitOfWork.LanguageRepository().Add(language);

        return new ResultWithDomainEvent<>(newLanguage,languageEventCreated);
    }

    private void CheckDuplicates(LanguageCreateCommand languageCreateCommand) {
        LanguageCheckDuplicates languageCheckDuplicates = modelMapper.map(languageCreateCommand, LanguageCheckDuplicates.class);
        languageCheckDuplicatesIValidationRule.Validate(languageCheckDuplicates);
    }

    private void CheckNullValues(LanguageCreateCommand languageCreateCommand) {
        LanguageCheckNullValues languageCheckNullValues = modelMapper.map(languageCreateCommand, LanguageCheckNullValues.class);
        languageCheckNullValuesIValidationRule.Validate(languageCheckNullValues);
    }

    private void setFieldsToUpperCase(LanguageCreateCommand languageCreateCommand) {
        languageCreateCommand.setLanguageCd(languageCreateCommand.getLanguageCd().toUpperCase());
        languageCreateCommand.setName(languageCreateCommand.getName().toUpperCase());
    }
}
