package ls.mestech.erp.dailysales.web.user.dailysales;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.services.user.impl.DailySalesService;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command.DailySalesCreateCommand;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dailysales")
public class DailySalesController {
    @Autowired
    private final DailySalesService dailySalesService;
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;

    public DailySalesController(DailySalesService dailySalesService, IUnitOfWork unitOfWork, ModelMapper modelMapper) {
        this.dailySalesService = dailySalesService;
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String GetDailySales(){

        return "/dailysales/index";
    }

    @GetMapping("/create")
    public String Create(){

        return "/dailysales/create";
    }

    @PostMapping("/create")
    public String Create(DailySalesDto dailySalesDto){

        DailySalesCreateCommand dailySalesCreateCommand = modelMapper.map(dailySalesDto, DailySalesCreateCommand.class);

        dailySalesService.Create(dailySalesCreateCommand);

        return "redirect:/dailysales";
    }

    @GetMapping("/edit")
    public String Edit(){

        return "/dailysales/edit";
    }
    @PutMapping("/edit")
    public String Edit(@RequestBody Model model){

        return "/dailysales/edit";
    }

    @GetMapping("/delete")
    public String delete(){

        return "/dailysales/delete";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody Model model){

        return "/dailysales/delete";
    }
}
