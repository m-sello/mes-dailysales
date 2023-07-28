package ls.mestech.erp.dailysales.webservice.admin.language;

import ls.mestech.erp.dailysales.domain.model.Language;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LanguageModelAssembler implements RepresentationModelAssembler<Language, EntityModel<Language>> {
    @Override
    public EntityModel<Language> toModel(Language language) {
        return EntityModel.of(language,
                linkTo(methodOn(LanguageApiController.class).GetLanguage(language.getLanguageCd())).withSelfRel(),
                linkTo(methodOn(LanguageApiController.class).GetLanguages()).withRel("languages"));
    }
}
