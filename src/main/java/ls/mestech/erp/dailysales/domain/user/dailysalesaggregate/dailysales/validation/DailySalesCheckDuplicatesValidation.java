package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation;

import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesNullException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DuplicateDailySalesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailySalesCheckDuplicatesValidation implements IValidationRule<DailySalesCheckDuplicates> {

    @Autowired
    private final IUnitOfWork unitOfWork;

    public DailySalesCheckDuplicatesValidation(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public void Validate(DailySalesCheckDuplicates input) {
        if (input != null) {

            unitOfWork.DailySalesRepository().FindAll().forEach(dailySales -> {
                if(input.getCapturedDt().compareTo(dailySales.getCapturedDt()) == 0)
                    throw new DuplicateDailySalesException("Daily Sales Already has a record with the same Date");
            });
        }
        else {
            throw new DailySalesNullException("DailySales cannot be null");
        }
    }
}
