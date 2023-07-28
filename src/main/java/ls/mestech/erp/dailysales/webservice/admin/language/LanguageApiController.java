package ls.mestech.erp.dailysales.webservice.admin.language;

import ls.mestech.erp.dailysales.domain.admin.language.command.LanguageCreateCommand;
import ls.mestech.erp.dailysales.domain.admin.language.command.LanguageDeleteCommand;
import ls.mestech.erp.dailysales.domain.admin.language.command.LanguageUpdateCommand;
import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageNotFoundException;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventCreated;
import ls.mestech.erp.dailysales.domain.admin.language.query.LanguageGetAllQuery;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.services.admin.impl.LanguageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value="api/admin")
public class LanguageApiController {
    @Autowired
    private final LanguageService languageService;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final LanguageModelAssembler languageModelAssembler;

    public LanguageApiController(LanguageService languageService, ModelMapper modelMapper, LanguageModelAssembler languageModelAssembler) {
        this.languageService = languageService;
        this.modelMapper = modelMapper;
        this.languageModelAssembler = languageModelAssembler;
    }

    @GetMapping("/language/{id}")
    public EntityModel<Language> GetLanguage(@PathVariable String id){
        Language language = languageService.GetByCode(id);

        if(language != null)
            return languageModelAssembler.toModel(language);
        else
            throw new LanguageNotFoundException("Language Code:" + id + " Not Found.");
    }

    @GetMapping("/language")
    public CollectionModel<EntityModel<Language>> GetLanguages(){
        List<EntityModel<Language>> languages = languageService.GetAll(new LanguageGetAllQuery()).stream()
                .map(languageModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(languages,linkTo(methodOn(LanguageApiController.class).GetLanguages()).withSelfRel());
    }

    @PostMapping("/language")
    public ResponseEntity<?> CreateLanguage(@RequestBody LanguageCreateCommand languageCreateCommand){
        ResultWithDomainEvent<Language, LanguageEventCreated> newLanguage =  languageService.Create(languageCreateCommand);
        EntityModel<Language> newLanguageModel = languageModelAssembler.toModel(newLanguage.getResult());

        return ResponseEntity
                .created(newLanguageModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(newLanguageModel);
    }

    @PutMapping("language/{languageCd}")
    public ResponseEntity<?> UpdateLanguage(@RequestBody LanguageUpdateCommand languageUpdateCommand, @PathVariable String languageCd){
        Language language = languageService.GetByCode(languageCd);

        if(language != null) {
            EntityModel<Language> updatedLanguageModel = languageModelAssembler.toModel(languageService.Update(languageUpdateCommand).getResult());

            return ResponseEntity
                    .created(updatedLanguageModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(updatedLanguageModel);
        }
        else
            throw new LanguageNotFoundException("Language Code: " + languageCd + " Not Found.");
    }

    @DeleteMapping("/language/{languageCd}")
    public ResponseEntity<?> DeleteLanguage(@PathVariable String languageCd){
        Language language = languageService.GetByCode(languageCd);

        if(language != null) {
            LanguageDeleteCommand newLanguage = modelMapper.map(language, LanguageDeleteCommand.class);
            languageService.Delete(newLanguage);

            return ResponseEntity.noContent().build();
        }
        else
            throw new LanguageNotFoundException("Language Code: " + languageCd + " Not Found.");
    }
}
