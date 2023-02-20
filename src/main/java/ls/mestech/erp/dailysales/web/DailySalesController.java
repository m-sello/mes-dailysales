package ls.mestech.erp.dailysales.web;

import ls.mestech.erp.dailysales.domain.dailysales.action.query.DailySalesGetAllQuery;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.services.impl.DailySalesService;
import ls.mestech.erp.dailysales.domain.utilities.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dailysales")
public class DailySalesController {
    private final DailySalesService dailySalesService;
    private final DateUtils dateUtils;

    public DailySalesController(DailySalesService dailySalesService, DateUtils dateUtils) {
        this.dailySalesService = dailySalesService;
        this.dateUtils = dateUtils;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String GetDailySales(@RequestParam(value="date",required=false) String dateString, Model model){

        List<DailySales> dailySales = this.dailySalesService.GetAll(new DailySalesGetAllQuery());
        model.addAttribute("dailySales", dailySales);

        return "dailysales";
    }

}
