package ls.mestech.erp.dailysales.domain.admin.language.command;

import ls.mestech.erp.dailysales.domain.admin.language.validation.LanguageCheckDuplicates;
import ls.mestech.erp.dailysales.domain.admin.language.validation.LanguageCheckNullValues;
import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageNullException;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventDeleted;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.repository.ILanguageRepository;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class LanguageDeleteTests {

    @MockBean
    private IUnitOfWork unitOfWork;
    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private ILanguageRepository languageRepository;

    @Autowired
    private ICommandHandler<LanguageDeleteCommand, ResultWithDomainEvent<Language, LanguageEventDeleted>> languageDeleteCommandHandler;
    @Autowired
    private IValidationRule<LanguageCheckNullValues> languageCheckNullValuesIValidationRule;
    @Autowired
    private IValidationRule<LanguageCheckDuplicates> languageCheckDuplicatesIValidationRule;

    @Test
    @DisplayName("Should Delete a Language")
    public void ShouldDeleteLanguage(){
        //given
        LanguageDeleteCommand languageDeleteCommand = new LanguageDeleteCommand("ENG","English");
        Language language = new Language(languageDeleteCommand.getLanguageCd(),languageDeleteCommand.getName());
        LanguageEventDeleted languageEventDeleted = new LanguageEventDeleted(language.getLanguageCd(),language.getName());
        LanguageCheckNullValues languageCheckNullValues = new LanguageCheckNullValues(languageDeleteCommand.getLanguageCd(),languageDeleteCommand.getName());
        LanguageCheckDuplicates  languageCheckDuplicates = new LanguageCheckDuplicates(languageDeleteCommand.getLanguageCd(),languageDeleteCommand.getName());

        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().FindById(language.getLanguageCd())).thenReturn(language);
        //Mockito.when(unitOfWork.LanguageRepository().Remove(language)).thenReturn(language);
        Mockito.when(modelMapper.map(languageDeleteCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageDeleteCommand,LanguageEventDeleted.class)).thenReturn(languageEventDeleted);
        Mockito.when(modelMapper.map(languageDeleteCommand,LanguageCheckNullValues.class)).thenReturn(languageCheckNullValues);
        Mockito.when(modelMapper.map(languageDeleteCommand,LanguageCheckDuplicates.class)).thenReturn(languageCheckDuplicates);

        //when
        ResultWithDomainEvent<Language, LanguageEventDeleted> newLanguage = languageDeleteCommandHandler.Handle(languageDeleteCommand);

        //then
        Assertions.assertNotNull(newLanguage);
        Mockito.verify(unitOfWork.LanguageRepository(), Mockito.times(1)).Remove(language);
    }

    @Test
    @DisplayName("Should Throw a Null Language Code Exception When Deleting a Language")
    public void ShouldThrowNullLanguageCodeExceptionWhenCreatingNewLanguage(){
        //given
        LanguageDeleteCommand languageDeleteCommand = new LanguageDeleteCommand(null,"English");
        Language language = new Language(languageDeleteCommand.getLanguageCd(),languageDeleteCommand.getName());
        LanguageEventDeleted languageEventDeleted = new LanguageEventDeleted(language.getLanguageCd(),language.getName());

        ILanguageRepository languageRepository = Mockito.mock(ILanguageRepository.class);
        //mocks
        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().Add(language)).thenReturn(language);
        Mockito.when(modelMapper.map(languageDeleteCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageDeleteCommand,LanguageEventDeleted.class)).thenReturn(languageEventDeleted);

        Assertions.assertThrows(LanguageNullException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventDeleted> newLanguage = languageDeleteCommandHandler.Handle(languageDeleteCommand);
        });
    }

    @Test
    @DisplayName("Should Throw a Null Object Exception When Deleting a Language")
    public void ShouldThrowNullObjectExceptionWhenCreatingNewLanguage(){
        //given
        LanguageDeleteCommand languageDeleteCommand = null;
        Language language = Mockito.mock(Language.class);
        LanguageEventDeleted languageEventDeleted = Mockito.mock(LanguageEventDeleted.class);

        ILanguageRepository languageRepository = Mockito.mock(ILanguageRepository.class);
        //mocks
        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().Add(language)).thenReturn(language);
        Mockito.when(modelMapper.map(languageDeleteCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageDeleteCommand,LanguageEventDeleted.class)).thenReturn(languageEventDeleted);

        Assertions.assertThrows(LanguageNullException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventDeleted> newLanguage = languageDeleteCommandHandler.Handle(languageDeleteCommand);
        });
    }
}
