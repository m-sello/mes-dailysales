package ls.mestech.erp.dailysales.domain.admin.language.validation;

import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageNullException;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import org.springframework.stereotype.Service;

@Service
public class LanguageCheckNullValuesValidation implements IValidationRule<LanguageCheckNullValues> {
    @Override
    public void Validate(LanguageCheckNullValues input) {
        if (input != null) {

                if (input.getLanguageCd() == null) {
                    throw new LanguageNullException("Language Code cannot be null");
                }
                if (input.getName() == null) {
                    throw new LanguageNullException("Language Name cannot be null");
                }
        }
        else
            throw new LanguageNullException("Language cannot be null");
    }
}
