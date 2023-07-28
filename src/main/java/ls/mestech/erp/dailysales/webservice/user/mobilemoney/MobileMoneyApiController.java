package ls.mestech.erp.dailysales.webservice.user.mobilemoney;

import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.services.user.impl.MobileMoneyService;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command.MobileMoneyCreateCommand;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command.MobileMoneyDeleteCommand;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command.MobileMoneyUpdateCommand;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.MobileMoneyNotFoundException;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.query.MobileMoneyGetAllQuery;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.query.MobileMoneyGetByIdQuery;
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
@RequestMapping(value="api/user")
public class MobileMoneyApiController {
    @Autowired
    private final MobileMoneyService mobileMoneyService;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final MobileMoneyModelAssembler mobileMoneyModelAssembler;

    public MobileMoneyApiController(MobileMoneyService mobileMoneyService, ModelMapper modelMapper, MobileMoneyModelAssembler mobileMoneyModelAssembler) {
        this.mobileMoneyService = mobileMoneyService;
        this.modelMapper = modelMapper;
        this.mobileMoneyModelAssembler = mobileMoneyModelAssembler;
    }

    @GetMapping("/mobilemoney/{id}")
    public EntityModel<MobileMoney> GetMobileMoney(@PathVariable String id){
        MobileMoney mobileMoney = mobileMoneyService.GetById(new MobileMoneyGetByIdQuery(id));

        if(mobileMoney != null)
            return mobileMoneyModelAssembler.toModel(mobileMoney);
        else
            throw new MobileMoneyNotFoundException("Mobile Money Code:" + id + "Not Found.");
    }

    @GetMapping("/mobilemoney")
    public CollectionModel<EntityModel<MobileMoney>> GetMobileMoney(){
        List<MobileMoney> mobileMoneyResults = mobileMoneyService.GetAll(new MobileMoneyGetAllQuery());

        List<EntityModel<MobileMoney>> mobileMoney = mobileMoneyResults.stream()
                .map(mobileMoneyModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(mobileMoney,linkTo(methodOn(MobileMoneyApiController.class).GetMobileMoney()).withSelfRel());
    }

    @PostMapping("/mobilemoney")
    public ResponseEntity<?> CreateMobileMoney(@RequestBody MobileMoneyCreateCommand mobileMoneyCreateCommand){
        MobileMoney newMobileMoney = mobileMoneyService.Create(mobileMoneyCreateCommand);
        EntityModel<MobileMoney> newMobileMoneyModel = mobileMoneyModelAssembler.toModel(newMobileMoney);

        return ResponseEntity
                .created(newMobileMoneyModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(newMobileMoneyModel);
    }

    @PutMapping("/mobilemoney/{id}")
    public ResponseEntity<?> UpdateMobileMoney(@RequestBody MobileMoneyUpdateCommand mobileMoneyUpdateCommand, @PathVariable String id){
        MobileMoney mobileMoney = mobileMoneyService.GetById(new MobileMoneyGetByIdQuery(id));

        if(mobileMoney != null) {
            MobileMoney updatedMobileMoney = mobileMoneyService.Update(mobileMoneyUpdateCommand);
            EntityModel<MobileMoney> updatedMobileMoneyModel = mobileMoneyModelAssembler.toModel(updatedMobileMoney);

            return ResponseEntity
                    .created(updatedMobileMoneyModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(updatedMobileMoneyModel);
        }
        else
            throw new MobileMoneyNotFoundException("Mobile Money Code:" + id + "Not Found.");
    }

    @DeleteMapping("/mobilemoney/{id}")
    public ResponseEntity<?> DeleteMobileMoney(@PathVariable String id){
        MobileMoney mobileMoney = mobileMoneyService.GetById(new MobileMoneyGetByIdQuery(id));

        if(mobileMoney != null) {
            MobileMoneyDeleteCommand newMobileMoney = modelMapper.map(mobileMoney, MobileMoneyDeleteCommand.class);
            mobileMoneyService.Delete(newMobileMoney);

            return ResponseEntity.noContent().build();
        }
        else
            throw new MobileMoneyNotFoundException("Mobile Money Code:" + id + "Not Found.");
    }
}
