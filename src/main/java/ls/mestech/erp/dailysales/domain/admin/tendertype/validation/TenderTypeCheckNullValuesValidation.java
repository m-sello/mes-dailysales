package ls.mestech.erp.dailysales.domain.admin.tendertype.validation;

import ls.mestech.erp.dailysales.domain.admin.tendertype.exception.TenderTypeNullException;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import org.springframework.stereotype.Service;

@Service
public class TenderTypeCheckNullValuesValidation implements IValidationRule<TenderTypeCheckNullValues> {
    @Override
    public void Validate(TenderTypeCheckNullValues input) {
        if (input != null) {

                if (input.getTenderTypeCd() == null) {
                    throw new TenderTypeNullException("Tender Type Code cannot be null");
                }
                if (input.getActiveFlg() == null) {
                    throw new TenderTypeNullException("Tender Type Active Flag cannot be null");
                }
        }
        else
            throw new TenderTypeNullException("Tender Type cannot be null");
    }
}
