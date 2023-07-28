package ls.mestech.erp.dailysales.domain.admin.language.command;

import ls.mestech.erp.dailysales.domain.admin.language.validation.LanguageCheckDuplicates;
import ls.mestech.erp.dailysales.domain.admin.language.validation.LanguageCheckNullValues;
import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageDuplicateException;
import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageNullException;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventCreated;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.repository.ILanguageRepository;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

@SpringBootTest
public class LanguageCreateTests {

    @MockBean
    private IUnitOfWork unitOfWork;
    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private ILanguageRepository languageRepository;

    @Autowired
    private ICommandHandler<LanguageCreateCommand, ResultWithDomainEvent<Language, LanguageEventCreated>> languageCreateCommandHandler;
    @Autowired
    private IValidationRule<LanguageCheckNullValues> languageCheckNullValuesIValidationRule;
    @Autowired
    private IValidationRule<LanguageCheckDuplicates> languageCheckDuplicatesIValidationRule;

    @Test
    @DisplayName("Should Create New Language")
    public void ShouldCreateNewLanguage(){
        //given
        LanguageCreateCommand languageCreateCommand = new LanguageCreateCommand("ENG","English");
        Language language = new Language(languageCreateCommand.getLanguageCd(),languageCreateCommand.getName());
        LanguageEventCreated languageEventCreated = new LanguageEventCreated(language.getLanguageCd(),language.getName());
        LanguageCheckNullValues languageCheckNullValues = new LanguageCheckNullValues(languageCreateCommand.getLanguageCd(),languageCreateCommand.getName());
        LanguageCheckDuplicates  languageCheckDuplicates = new LanguageCheckDuplicates(languageCreateCommand.getLanguageCd(),languageCreateCommand.getName());

        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().Add(language)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,LanguageEventCreated.class)).thenReturn(languageEventCreated);
        Mockito.when(modelMapper.map(languageCreateCommand,LanguageCheckNullValues.class)).thenReturn(languageCheckNullValues);
        Mockito.when(modelMapper.map(languageCreateCommand,LanguageCheckDuplicates.class)).thenReturn(languageCheckDuplicates);

        //when
        ResultWithDomainEvent<Language, LanguageEventCreated> newLanguage = languageCreateCommandHandler.Handle(languageCreateCommand);

        //then
        Assertions.assertNotNull(newLanguage);
        Mockito.verify(unitOfWork.LanguageRepository(), Mockito.times(1)).Add(language);
    }

    @Test
    @DisplayName("Should Throw a Null Language Code Exception When Creating a New Language")
    public void ShouldThrowNullLanguageCodeExceptionWhenCreatingNewLanguage(){
        //given
        LanguageCreateCommand languageCreateCommand = new LanguageCreateCommand(null,"English");
        Language language = new Language(languageCreateCommand.getLanguageCd(),languageCreateCommand.getName());
        LanguageEventCreated languageEventCreated = new LanguageEventCreated(language.getLanguageCd(),language.getName());

        ILanguageRepository languageRepository = Mockito.mock(ILanguageRepository.class);
        //mocks
        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().Add(language)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,LanguageEventCreated.class)).thenReturn(languageEventCreated);

        Assertions.assertThrows(LanguageNullException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventCreated> newLanguage = languageCreateCommandHandler.Handle(languageCreateCommand);
        });
    }

    @Test
    @DisplayName("Should Throw a Null Name Exception When Creating a New Language")
    public void ShouldThrowNullNameExceptionWhenCreatingNewLanguage(){
        //given
        LanguageCreateCommand languageCreateCommand = new LanguageCreateCommand("ENG",null);
        Language language = new Language(languageCreateCommand.getLanguageCd(),languageCreateCommand.getName());
        LanguageEventCreated languageEventCreated = new LanguageEventCreated(language.getLanguageCd(),language.getName());

        ILanguageRepository languageRepository = Mockito.mock(ILanguageRepository.class);
        //mocks
        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().Add(language)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,LanguageEventCreated.class)).thenReturn(languageEventCreated);

        Assertions.assertThrows(LanguageNullException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventCreated> newLanguage = languageCreateCommandHandler.Handle(languageCreateCommand);
        });
    }

    @Test
    @DisplayName("Should Throw a Null Language Code and Name Exception When Creating a New Language")
    public void ShouldThrowNullExceptionWhenCreatingNewLanguage(){
        //given
        LanguageCreateCommand languageCreateCommand = new LanguageCreateCommand(null,null);
        Language language = new Language(languageCreateCommand.getLanguageCd(),languageCreateCommand.getName());
        LanguageEventCreated languageEventCreated = new LanguageEventCreated(language.getLanguageCd(),language.getName());

        ILanguageRepository languageRepository = Mockito.mock(ILanguageRepository.class);
        //mocks
        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().Add(language)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,LanguageEventCreated.class)).thenReturn(languageEventCreated);

        Assertions.assertThrows(LanguageNullException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventCreated> newLanguage = languageCreateCommandHandler.Handle(languageCreateCommand);
        });
    }
    @Test
    @DisplayName("Should Throw a Null Object Exception When Creating a New Language")
    public void ShouldThrowNullObjectExceptionWhenCreatingNewLanguage(){
        //given
        LanguageCreateCommand languageCreateCommand = null;
        Language language = Mockito.mock(Language.class);
        LanguageEventCreated languageEventCreated = Mockito.mock(LanguageEventCreated.class);

        ILanguageRepository languageRepository = Mockito.mock(ILanguageRepository.class);
        //mocks
        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().Add(language)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,LanguageEventCreated.class)).thenReturn(languageEventCreated);

        Assertions.assertThrows(LanguageNullException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventCreated> newLanguage = languageCreateCommandHandler.Handle(languageCreateCommand);
        });
    }

    @Test
    @DisplayName("Should Throw a Duplicate Exception When Creating a New Language")
    public void ShouldThrowDuplicateExceptionWhenCreatingNewLanguage(){
        //given
        LanguageCreateCommand languageCreateCommand = new LanguageCreateCommand("ENG","English");
        Language language = new Language(languageCreateCommand.getLanguageCd(),languageCreateCommand.getName());
        LanguageEventCreated languageEventCreated = new LanguageEventCreated(language.getLanguageCd(),language.getName());
        LanguageCheckNullValues languageCheckNullValues = new LanguageCheckNullValues(languageCreateCommand.getLanguageCd(),languageCreateCommand.getName());
        LanguageCheckDuplicates  languageCheckDuplicates = new LanguageCheckDuplicates(languageCreateCommand.getLanguageCd(),languageCreateCommand.getName());
        ArrayList<Language> languageList = new ArrayList<>(){{add(language);}};

        Mockito.when(unitOfWork.LanguageRepository()).thenReturn(languageRepository);
        Mockito.when(unitOfWork.LanguageRepository().Add(language)).thenReturn(language);
        Mockito.when(unitOfWork.LanguageRepository().FindAll()).thenReturn(languageList);
        Mockito.when(modelMapper.map(languageCreateCommand,Language.class)).thenReturn(language);
        Mockito.when(modelMapper.map(languageCreateCommand,LanguageEventCreated.class)).thenReturn(languageEventCreated);
        Mockito.when(modelMapper.map(languageCreateCommand,LanguageCheckNullValues.class)).thenReturn(languageCheckNullValues);
        Mockito.when(modelMapper.map(languageCreateCommand,LanguageCheckDuplicates.class)).thenReturn(languageCheckDuplicates);

        Assertions.assertThrows(LanguageDuplicateException.class, ()->{
            ResultWithDomainEvent<Language, LanguageEventCreated> newLanguage = languageCreateCommandHandler.Handle(languageCreateCommand);
        });
    }
}
