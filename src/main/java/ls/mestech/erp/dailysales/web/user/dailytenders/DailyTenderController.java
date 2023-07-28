package ls.mestech.erp.dailysales.web.user.dailytenders;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.services.user.impl.DailySalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DailyTenderController {
    @Autowired
    private final DailySalesService dailySalesService;
    private final IUnitOfWork unitOfWork;

    public DailyTenderController(DailySalesService dailySalesService, IUnitOfWork unitOfWork) {
        this.dailySalesService = dailySalesService;
        this.unitOfWork = unitOfWork;
    }

    @GetMapping("/dailytenders")
    public String GetDailyTenders(){

        return "/dailytenders/index";
    }
}
