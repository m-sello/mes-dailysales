package ls.mestech.erp.dailysales.webservice;

import ls.mestech.erp.dailysales.domain.dailysales.action.command.DailySalesCreateCommand;
import ls.mestech.erp.dailysales.domain.dailysales.action.query.DailySalesGetAllQuery;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.services.impl.DailySalesService;
import ls.mestech.erp.dailysales.domain.utilities.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dailysales")
public class DailySalesController {
    private final DailySalesService dailySalesService;
    private final DateUtils dateUtils;

    public DailySalesController(DailySalesService dailySalesService, DateUtils dateUtils) {
        this.dailySalesService = dailySalesService;
        this.dateUtils = dateUtils;
    }

    @RequestMapping(path="/", method = RequestMethod.GET)
    public List<DailySales> GetDailySales(@RequestParam(value="date",required=false) String dateString, Model model){

        return this.dailySalesService.GetAll(new DailySalesGetAllQuery());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateDailySales(@RequestBody DailySalesCreateCommand dailySalesCreateCommand){
        this.dailySalesService.Create(dailySalesCreateCommand);
    }
}
