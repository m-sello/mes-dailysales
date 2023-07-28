package ls.mestech.erp.dailysales.webservice.user.dailysales;

import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.model.Language;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DailySalesModelAssembler implements RepresentationModelAssembler<DailySales, EntityModel<DailySales>> {
    @Override
    public EntityModel<DailySales> toModel(DailySales dailySales) {
        return EntityModel.of(dailySales,
                linkTo(methodOn(DailySalesApiController.class).GetDailySales(dailySales.getId())).withSelfRel(),
                linkTo(methodOn(DailySalesApiController.class).GetDailySales()).withRel("dailysales"));
    }
}
