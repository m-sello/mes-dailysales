package ls.mestech.erp.dailysales.web.admin.users;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    @Autowired
    private final IUnitOfWork unitOfWork;

    public UsersController(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @GetMapping("/users")
    public String GetUsers(){

        return "/users/index";
    }
}
