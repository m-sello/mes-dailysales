package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation;

import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesIllegalArgumentException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesNullException;
import org.springframework.stereotype.Service;

@Service
public class DailySalesCheckNullValuesValidation implements IValidationRule<DailySalesCheckNullValues> {

    public DailySalesCheckNullValuesValidation(){}
    @Override
    public void Validate(DailySalesCheckNullValues input) {
        if (input != null) {

            if (input.getFloatAmount() == null) {
                throw new DailySalesIllegalArgumentException("Float Amount cannot be null");
            }
            if (input.getCapturedDt() == null)
                throw new DailySalesIllegalArgumentException("Captured Date cannot be null");
        }
        else {
            throw new DailySalesNullException("DailySalesCheckNullValues input cannot be null");
        }
    }
}
