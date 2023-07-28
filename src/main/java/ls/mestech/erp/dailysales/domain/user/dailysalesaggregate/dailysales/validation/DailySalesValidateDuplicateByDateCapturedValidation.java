package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation;

import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesNullException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DuplicateDailySalesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailySalesValidateDuplicateByDateCapturedValidation implements IValidationRule<DailySalesValidateDuplicateByDateCaptured> {

    @Autowired
    private final IUnitOfWork unitOfWork;

    public DailySalesValidateDuplicateByDateCapturedValidation(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public void Validate(DailySalesValidateDuplicateByDateCaptured dailySalesValidateDuplicateByDateCaptured) {
        if (dailySalesValidateDuplicateByDateCaptured != null) {

            if (dailySalesValidateDuplicateByDateCaptured.getCapturedDt() != null) {

                LocalDate inputDate = dailySalesValidateDuplicateByDateCaptured.getCapturedDt().toLocalDate();
                //if this is an update, ensure there is no other record with the exact same date but with different ID
                if (dailySalesValidateDuplicateByDateCaptured.getUpdateFlg()) {
                    unitOfWork.DailySalesRepository().FindAll().forEach(dailySales -> {
                        if ((inputDate.isEqual(dailySales.getCapturedDt().toLocalDate()))
                                && !dailySalesValidateDuplicateByDateCaptured.getId().equals(dailySales.getId()))
                            throw new DuplicateDailySalesException("Daily Sales Already has a record with the same Date");
                    });
                }
            }
        }
        else {
            throw new DailySalesNullException("DailySalesValidateDuplicateByDateCaptured cannot be null");
        }
    }
}
