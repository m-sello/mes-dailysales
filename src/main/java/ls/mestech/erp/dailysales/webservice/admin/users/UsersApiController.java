package ls.mestech.erp.dailysales.webservice.admin.users;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/admin")
public class UsersApiController {
    @Autowired
    private final IUnitOfWork unitOfWork;

    public UsersApiController(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @GetMapping("/users")
    public String GetUsers(){

        return "/users/index";
    }
}
