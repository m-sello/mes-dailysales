package ls.mestech.erp.dailysales.web.admin.usergroup;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserGroupController {
    @Autowired
    private final IUnitOfWork unitOfWork;

    public UserGroupController(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @GetMapping("/usergroups")
    public String GetUserGroups(){

        return "/usergroups/index";
    }
}
