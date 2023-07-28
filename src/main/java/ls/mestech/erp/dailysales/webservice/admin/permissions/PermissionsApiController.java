package ls.mestech.erp.dailysales.webservice.admin.permissions;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/admin")

public class PermissionsApiController {
    @Autowired
    private final IUnitOfWork unitOfWork;

    public PermissionsApiController(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @GetMapping("/permissions")
    public String GetPermissions(){

        return "/permissions/index";
    }
}
