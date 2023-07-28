package ls.mestech.erp.dailysales.domain.admin.tendertype.validation;

import ls.mestech.erp.dailysales.domain.admin.tendertype.exception.TenderTypeDuplicateException;
import ls.mestech.erp.dailysales.domain.admin.tendertype.exception.TenderTypeNullException;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenderTypeCheckDuplicatesValidation implements IValidationRule<TenderTypeCheckDuplicates> {
    @Autowired
    private final IUnitOfWork unitOfWork;

    public TenderTypeCheckDuplicatesValidation(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public void Validate(TenderTypeCheckDuplicates input) {
        if (input != null) {

            unitOfWork.TenderTypeRepository().FindAll().forEach(tenderType -> {
                if(input.getTenderTypeCd().equals(tenderType.getTenderTypeCd()))
                    throw new TenderTypeDuplicateException("Tender Type Code cannot be a duplicate");
            });
        }
        else
            throw new TenderTypeNullException("Tender Type cannot be null");
    }
}
