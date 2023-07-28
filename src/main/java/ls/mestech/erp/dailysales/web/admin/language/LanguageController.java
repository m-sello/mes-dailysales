package ls.mestech.erp.dailysales.web.admin.language;

import ls.mestech.erp.dailysales.domain.admin.language.command.LanguageCreateCommand;
import ls.mestech.erp.dailysales.domain.admin.language.command.LanguageDeleteCommand;
import ls.mestech.erp.dailysales.domain.admin.language.command.LanguageUpdateCommand;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventCreated;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventDeleted;
import ls.mestech.erp.dailysales.domain.admin.language.lifecycle.LanguageEventUpdated;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.services.admin.impl.LanguageService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(value="/admin")
public class LanguageController {
    @Autowired
    private final LanguageService languageService;
    private final IUnitOfWork unitOfWork;

    public LanguageController(LanguageService languageService, IUnitOfWork unitOfWork) {
        this.languageService = languageService;
        this.unitOfWork = unitOfWork;
    }


    @GetMapping("/language")
    public String GetLanguages(){

        //return languageService.GetAll(new LanguageGetAllQuery());
        return "/language/index";
    }

    @GetMapping("/language/{languageCd}")
    public Language GetLanguage(@PathVariable String languageCd){
        return languageService.GetByCode(languageCd);
    }

    @PostMapping("/language")
    public ResultWithDomainEvent<Language, LanguageEventCreated> CreateLanguage(@RequestBody LanguageCreateCommand languageCreateCommand){
        return languageService.Create(languageCreateCommand);
    }

    @PutMapping("language/{languageCd}")
    public ResultWithDomainEvent<Language, LanguageEventUpdated> UpdateLanguage(@RequestBody LanguageUpdateCommand languageUpdateCommand, @PathVariable String languageCd){
        if(languageService.GetByCode(languageCd) != null)
            return languageService.Update(languageUpdateCommand);
        else
            throw new ObjectNotFoundException((Object) languageCd,"Language Code Not Found");
    }

    @DeleteMapping("/language/{languageCd}")
    public ResultWithDomainEvent<Language, LanguageEventDeleted> DeleteLanguage(@RequestBody LanguageDeleteCommand languageDeleteCommand, String languageCd){
        if(languageService.GetByCode(languageCd) != null)
            return languageService.Delete(languageDeleteCommand);
        else
            throw new ObjectNotFoundException((Object) languageCd,"Language Code Not Found");
    }
}
