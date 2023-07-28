package ls.mestech.erp.dailysales.domain.admin.language.validation;

import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageDuplicateException;
import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageNullException;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageCheckDuplicatesValidation implements IValidationRule<LanguageCheckDuplicates> {
    @Autowired
    private final IUnitOfWork unitOfWork;

    public LanguageCheckDuplicatesValidation(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public void Validate(LanguageCheckDuplicates input) {
        if (input != null) {

            unitOfWork.LanguageRepository().FindAll().forEach(language -> {
                if(input.getLanguageCd().equals(language.getLanguageCd()))
                    throw new LanguageDuplicateException("Language Code cannot be a duplicate");
            });
        }
        else
            throw new LanguageNullException("Language cannot be null");
    }
}
