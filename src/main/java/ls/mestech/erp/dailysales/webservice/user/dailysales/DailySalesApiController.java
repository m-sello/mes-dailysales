package ls.mestech.erp.dailysales.webservice.user.dailysales;

import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageNotFoundException;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.services.user.impl.DailySalesService;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command.DailySalesCreateCommand;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command.DailySalesDeleteCommand;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command.DailySalesUpdateCommand;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesNotFoundException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.query.DailySalesGetAllQuery;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.query.DailySalesGetByIdQuery;
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
public class DailySalesApiController {
    @Autowired
    private final DailySalesService dailySalesService;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final DailySalesModelAssembler dailySalesModelAssembler;

    public DailySalesApiController(DailySalesService dailySalesService, ModelMapper modelMapper, DailySalesModelAssembler dailySalesModelAssembler) {
        this.dailySalesService = dailySalesService;
        this.modelMapper = modelMapper;
        this.dailySalesModelAssembler = dailySalesModelAssembler;
    }


    @GetMapping("/dailysales/{id}")
    public EntityModel<DailySales> GetDailySales(@PathVariable String id){
        DailySales dailySales = dailySalesService.GetById(new DailySalesGetByIdQuery(id));

        if(dailySales != null)
            return dailySalesModelAssembler.toModel(dailySales);
        else
            throw new LanguageNotFoundException("Daily Sales Code:" + id + " Not Found.");
    }

    @GetMapping("/dailysales")
    public CollectionModel<EntityModel<DailySales>> GetDailySales(){
        List<DailySales> dailySalesResults = dailySalesService.GetAll(new DailySalesGetAllQuery());

        List<EntityModel<DailySales>> dailySales = dailySalesResults.stream()
                .map(dailySalesModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(dailySales,linkTo(methodOn(DailySalesApiController.class).GetDailySales()).withSelfRel());
    }

    @PostMapping("/dailysales")
    public ResponseEntity<?> CreateDailySales(@RequestBody DailySalesCreateCommand dailySalesCreateCommand){
        DailySales newDailySales = dailySalesService.Create(dailySalesCreateCommand);
        EntityModel<DailySales> newDailySalesModel = dailySalesModelAssembler.toModel(newDailySales);

        return ResponseEntity
                .created(newDailySalesModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(newDailySalesModel);
    }

    @PutMapping("/dailysales/{id}")
    public ResponseEntity<?> UpdateDailySales(@RequestBody DailySalesUpdateCommand dailySalesUpdateCommand, @PathVariable String id){
        DailySales dailySales = dailySalesService.GetById(new DailySalesGetByIdQuery(id));

        if(dailySales != null) {
            EntityModel<DailySales> updatedDailySalesModel = dailySalesModelAssembler.toModel(dailySalesService.Update(dailySalesUpdateCommand));

            return ResponseEntity
                    .created(updatedDailySalesModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(updatedDailySalesModel);
        }
        else
            throw new DailySalesNotFoundException("Daily Sales Code:" + id + " Not Found.");
    }
    @DeleteMapping("/dailysales/{id}")
    public ResponseEntity<?> DeleteDailySales(@PathVariable String id){
        DailySales dailySales = dailySalesService.GetById(new DailySalesGetByIdQuery(id));

        if(dailySales != null) {
            DailySalesDeleteCommand newDailySales = modelMapper.map(dailySales, DailySalesDeleteCommand.class);
            dailySalesService.Delete(newDailySales);

            return ResponseEntity.noContent().build();
        }
        else
            throw new DailySalesNotFoundException("Daily Sales Code:" + id + " Not Found.");
    }
}
