package ls.mestech.erp.dailysales.webservice.user.mobilemoney;

import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MobileMoneyModelAssembler implements RepresentationModelAssembler<MobileMoney, EntityModel<MobileMoney>> {
    @Override
    public EntityModel<MobileMoney> toModel(MobileMoney mobileMoney) {
        return EntityModel.of(mobileMoney,
                linkTo(methodOn(MobileMoneyApiController.class).GetMobileMoney(mobileMoney.getId())).withSelfRel(),
                linkTo(methodOn(MobileMoneyApiController.class).GetMobileMoney()).withRel("mobilemoney"));
    }
}
