package ls.mestech.erp.dailysales.webservice.admin.usergroup;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/admin")
public class UserGroupApiController {
    @Autowired
    private final IUnitOfWork unitOfWork;

    public UserGroupApiController(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @GetMapping("/usergroups")
    public String GetUserGroups(){

        return "/usergroups/index";
    }
}
