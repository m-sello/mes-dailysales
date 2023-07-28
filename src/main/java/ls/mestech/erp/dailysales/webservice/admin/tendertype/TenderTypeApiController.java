package ls.mestech.erp.dailysales.webservice.admin.tendertype;


import ls.mestech.erp.dailysales.domain.admin.tendertype.command.TenderTypeCreateCommand;
import ls.mestech.erp.dailysales.domain.admin.tendertype.command.TenderTypeDeleteCommand;
import ls.mestech.erp.dailysales.domain.admin.tendertype.command.TenderTypeUpdateCommand;
import ls.mestech.erp.dailysales.domain.admin.tendertype.exception.TenderTypeNotFoundException;
import ls.mestech.erp.dailysales.domain.admin.tendertype.query.TenderTypeGetAllQuery;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.domain.services.admin.impl.TenderTypeService;
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
public class TenderTypeApiController {
    @Autowired
    private final TenderTypeService tenderTypeService;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final TenderTypeModelAssembler tenderTypeModelAssembler;

    public TenderTypeApiController(TenderTypeService tenderTypeService, ModelMapper modelMapper, TenderTypeModelAssembler tenderTypeModelAssembler) {
        this.tenderTypeService = tenderTypeService;
        this.modelMapper = modelMapper;
        this.tenderTypeModelAssembler = tenderTypeModelAssembler;
    }

    @GetMapping("/tendertype/{id}")
    public EntityModel<TenderType> GetTenderType(@PathVariable String id){
        TenderType tenderType = tenderTypeService.GetByCode(id);

        if(tenderType != null)
            return tenderTypeModelAssembler.toModel(tenderType);
        else
            throw new TenderTypeNotFoundException("TenderType Code:" + id + " Not Found.");
    }

    @GetMapping("/tendertype")
    public CollectionModel<EntityModel<TenderType>> GetTenderTypes(){
        List<EntityModel<TenderType>> tenderTypes = tenderTypeService.GetAll(new TenderTypeGetAllQuery()).stream()
                .map(tenderTypeModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tenderTypes,linkTo(methodOn(TenderTypeApiController.class).GetTenderTypes()).withSelfRel());
    }

    @PostMapping("/tendertype")
    public ResponseEntity<?> CreateTenderType(@RequestBody TenderTypeCreateCommand tenderTypeCreateCommand){
        TenderType tenderType = tenderTypeService.Create(tenderTypeCreateCommand);

        EntityModel<TenderType> newTenderTypeModel = tenderTypeModelAssembler.toModel(tenderType);

        return ResponseEntity
                .created(newTenderTypeModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(newTenderTypeModel);
    }

    @PutMapping("tendertype/{tenderTypeCd}")
    public ResponseEntity<?> UpdateTenderType(@RequestBody TenderTypeUpdateCommand tenderTypeUpdateCommand, @PathVariable String tenderTypeCd){
        TenderType tenderType = tenderTypeService.GetByCode(tenderTypeCd);

        if(tenderType != null) {
            TenderType updatedTenderType = tenderTypeService.Update(tenderTypeUpdateCommand);
            EntityModel<TenderType> updatedTenderTypeModel = tenderTypeModelAssembler.toModel(updatedTenderType);

            return ResponseEntity
                    .created(updatedTenderTypeModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(updatedTenderTypeModel);
        }
        else
            throw new TenderTypeNotFoundException("TenderType Code:" + tenderTypeCd + " Not Found.");
    }

    @DeleteMapping("/tendertype/{tenderTypeCd}")
    public ResponseEntity<?> DeleteTenderType(@PathVariable String tenderTypeCd){
        TenderType tenderType = tenderTypeService.GetByCode(tenderTypeCd);

        if(tenderType != null) {
            TenderTypeDeleteCommand newTenderType = modelMapper.map(tenderType, TenderTypeDeleteCommand.class);
            tenderTypeService.Delete(newTenderType);

            return ResponseEntity.noContent().build();
        }
        else
            throw new TenderTypeNotFoundException("TenderType Code:" + tenderTypeCd + " Not Found.");
    }
}
