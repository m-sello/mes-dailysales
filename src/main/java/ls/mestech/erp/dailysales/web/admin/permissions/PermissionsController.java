package ls.mestech.erp.dailysales.web.admin.permissions;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PermissionsController {
    @Autowired
    private final IUnitOfWork unitOfWork;

    public PermissionsController(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @GetMapping("/permissions")
    public String GetPermissions(){

        return "/permissions/index";
    }
}
