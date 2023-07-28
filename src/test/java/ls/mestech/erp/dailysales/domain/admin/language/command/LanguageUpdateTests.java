package ls.mestech.erp.dailysales.domain.admin.language.command;

import ls.mestech.erp.dailysales.domain.admin.language.validation.LanguageCheckDuplicates;
import ls.mestech.erp.dailysales.domain.admin.language.validation.LanguageCheckNullValues;
import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageNullException;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventUpdated;
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
public class LanguageUpdateTests {

    @MockBean
    private IUnitOfWork unitOfWork;
    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private ILanguageRepository languageRepository;

    @Autowired
    private ICommandHandler<LanguageUpdateCommand, ResultWithDomainEvent<Language, LanguageEventUpdated>> languageUpdateCommandHandler;
    @Autowired
    private IValidationRule<LanguageCheckNullValues> languageCheckNullValuesIValidationRule;
    @Autowired
    private IValidationRule<LanguageCheckDuplicates> languageCheckDuplicatesIValidationRule;

    @Test
    @DisplayName("Should Update Language")
    public void ShouldUpdateLanguage(){
        //given
        LanguageUpdateCommand languageUpdateCommand = new LanguageUpdateCommand("ENG","English");
        Language language = new Language(languageUpdateCommand.getLanguageCd(),languageUpdateCommand.getName()+"_oldName");
        Language updatedlanguage = new Language(languageUpdateCommand.getLanguageCd(),languageUpdateCommand.getName()+"_oldName");
        LanguageEventUpdated languageEventUpdated = new LanguageEventUpdated(language.getLanguageCd(),language.getName());
        LanguageCheckNullValues languageCheckNullValues = new LanguageCheckNullValues(languageUpdateCommand.getLanguageCd(),languageUpdateCommand.getName());
        LanguageCheckDuplicates  languageCheckDuplicates = new LanguageCheckDuplicates(languageUpdateCommand.getLanguageCd(),languageUpdateCommand.getName());

        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().FindById(language.getLanguageCd())).thenReturn(language);
        Mockito.when(unitOfWork.LanguageRepository().Update(language)).thenReturn(updatedlanguage);
        Mockito.when(modelMapper.map(languageUpdateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageUpdateCommand,LanguageEventUpdated.class)).thenReturn(languageEventUpdated);
        Mockito.when(modelMapper.map(languageUpdateCommand,LanguageCheckNullValues.class)).thenReturn(languageCheckNullValues);
        Mockito.when(modelMapper.map(languageUpdateCommand,LanguageCheckDuplicates.class)).thenReturn(languageCheckDuplicates);

        //when
        ResultWithDomainEvent<Language, LanguageEventUpdated> newLanguage = languageUpdateCommandHandler.Handle(languageUpdateCommand);

        //then
        Assertions.assertNotNull(newLanguage);
        Assertions.assertNotEquals(language.getName(), newLanguage.getResult().getName());
    }

    @Test
    @DisplayName("Should Throw a Null Language Code Exception When Updating a Language")
    public void ShouldThrowNullLanguageCodeExceptionWhenCreatingNewLanguage(){
        //given
        LanguageUpdateCommand languageUpdateCommand = new LanguageUpdateCommand(null,"English");
        Language language = new Language(languageUpdateCommand.getLanguageCd(),languageUpdateCommand.getName());
        Language updatedlanguage = new Language(languageUpdateCommand.getLanguageCd(),languageUpdateCommand.getName()+"_oldName");
        LanguageEventUpdated languageEventUpdated = new LanguageEventUpdated(language.getLanguageCd(),language.getName());

        ILanguageRepository languageRepository = Mockito.mock(ILanguageRepository.class);
        //mocks
        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().FindById(language.getLanguageCd())).thenReturn(language);
        Mockito.when(unitOfWork.LanguageRepository().Update(language)).thenReturn(updatedlanguage);
        Mockito.when(modelMapper.map(languageUpdateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageUpdateCommand,LanguageEventUpdated.class)).thenReturn(languageEventUpdated);

        Assertions.assertThrows(LanguageNullException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventUpdated> newLanguage = languageUpdateCommandHandler.Handle(languageUpdateCommand);
        });
    }

    @Test
    @DisplayName("Should Throw a Null Name Exception When Updating a Language")
    public void ShouldThrowNullNameExceptionWhenCreatingNewLanguage(){
        //given
        LanguageUpdateCommand languageUpdateCommand = new LanguageUpdateCommand("ENG",null);
        Language language = new Language(languageUpdateCommand.getLanguageCd(),languageUpdateCommand.getName());
        Language updatedlanguage = new Language(languageUpdateCommand.getLanguageCd(),languageUpdateCommand.getName()+"_oldName");
        LanguageEventUpdated languageEventUpdated = new LanguageEventUpdated(language.getLanguageCd(),language.getName());

        ILanguageRepository languageRepository = Mockito.mock(ILanguageRepository.class);
        //mocks
        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().FindById(language.getLanguageCd())).thenReturn(language);
        Mockito.when(unitOfWork.LanguageRepository().Update(language)).thenReturn(updatedlanguage);
        Mockito.when(modelMapper.map(languageUpdateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageUpdateCommand,LanguageEventUpdated.class)).thenReturn(languageEventUpdated);

        Assertions.assertThrows(LanguageNullException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventUpdated> newLanguage = languageUpdateCommandHandler.Handle(languageUpdateCommand);
        });
    }

    @Test
    @DisplayName("Should Throw a Null Language Code and Name Exception When Updating a Language")
    public void ShouldThrowNullExceptionWhenCreatingNewLanguage(){
        //given
        LanguageUpdateCommand languageUpdateCommand = new LanguageUpdateCommand(null,null);
        Language language = new Language(languageUpdateCommand.getLanguageCd(),languageUpdateCommand.getName());
        Language updatedlanguage = new Language(languageUpdateCommand.getLanguageCd(),languageUpdateCommand.getName()+"_oldName");
        LanguageEventUpdated languageEventUpdated = new LanguageEventUpdated(language.getLanguageCd(),language.getName());

        ILanguageRepository languageRepository = Mockito.mock(ILanguageRepository.class);
        //mocks
        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().FindById(language.getLanguageCd())).thenReturn(language);
        Mockito.when(unitOfWork.LanguageRepository().Update(language)).thenReturn(updatedlanguage);
        Mockito.when(modelMapper.map(languageUpdateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageUpdateCommand,LanguageEventUpdated.class)).thenReturn(languageEventUpdated);

        Assertions.assertThrows(LanguageNullException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventUpdated> newLanguage = languageUpdateCommandHandler.Handle(languageUpdateCommand);
        });
    }
    @Test
    @DisplayName("Should Throw a Null Object Exception When Updating a Language")
    public void ShouldThrowNullObjectExceptionWhenCreatingNewLanguage(){
        //given
        LanguageUpdateCommand languageUpdateCommand = null;
        Language language = Mockito.mock(Language.class);
        Language updatedlanguage = new Language("TEST","Testing"+"_oldName");
        LanguageEventUpdated languageEventUpdated = Mockito.mock(LanguageEventUpdated.class);

        ILanguageRepository languageRepository = Mockito.mock(ILanguageRepository.class);
        //mocks
        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().FindById(language.getLanguageCd())).thenReturn(language);
        Mockito.when(unitOfWork.LanguageRepository().Update(language)).thenReturn(updatedlanguage);
        Mockito.when(modelMapper.map(languageUpdateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageUpdateCommand,LanguageEventUpdated.class)).thenReturn(languageEventUpdated);

        Assertions.assertThrows(LanguageNullException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventUpdated> newLanguage = languageUpdateCommandHandler.Handle(languageUpdateCommand);
        });
    }
}
