package ls.mestech.erp.dailysales.web.admin.tendertype;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.services.user.impl.DailySalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TenderTypeController {
    @Autowired
    private final DailySalesService dailySalesService;
    private final IUnitOfWork unitOfWork;

    public TenderTypeController(DailySalesService dailySalesService, IUnitOfWork unitOfWork) {
        this.dailySalesService = dailySalesService;
        this.unitOfWork = unitOfWork;
    }

    @GetMapping("/tendertype")
    public String GetTenderType(){

        return "/tendertype/index";
    }
}
