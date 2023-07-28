package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation;

import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesCapturedDateException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailySalesValidateCapturedDateValidation implements IValidationRule<DailySalesValidateCapturedDate> {
    @Autowired
    private final IUnitOfWork unitOfWork;

    public DailySalesValidateCapturedDateValidation(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public void Validate(DailySalesValidateCapturedDate dailySalesValidateCapturedDate){

        if (dailySalesValidateCapturedDate != null) {

            LocalDate inputDate = dailySalesValidateCapturedDate.getCapturedDt().toLocalDate();

            if (dailySalesValidateCapturedDate.getCapturedDt() != null) {

                if (inputDate.isAfter(LocalDate.now())) {
                    throw new DailySalesCapturedDateException("Captured Date cannot be in the future.");
                }
            }
        }
        else{
            throw new DailySalesNullException("Daily Sales cannot be null");
        }
    }
}
