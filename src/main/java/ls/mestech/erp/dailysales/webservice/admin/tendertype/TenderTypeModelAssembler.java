package ls.mestech.erp.dailysales.webservice.admin.tendertype;

import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.webservice.admin.language.LanguageApiController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TenderTypeModelAssembler implements RepresentationModelAssembler<TenderType, EntityModel<TenderType>> {
    @Override
    public EntityModel<TenderType> toModel(TenderType tenderType) {
        return EntityModel.of(tenderType,
                linkTo(methodOn(TenderTypeApiController.class).GetTenderType(tenderType.getTenderTypeCd())).withSelfRel(),
                linkTo(methodOn(TenderTypeApiController.class).GetTenderTypes()).withRel("tenderTypes"));
    }
}
