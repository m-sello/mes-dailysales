package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.validation.duplicate;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.MobileMoneyNullException;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.DuplicateMobileMoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileMoneyCheckDuplicatesValidation implements IValidationRule<MobileMoneyCheckDuplicates> {

    @Autowired
    private final IUnitOfWork unitOfWork;

    public MobileMoneyCheckDuplicatesValidation(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public void Validate(@NotNull MobileMoneyCheckDuplicates input) {
        if (input != null) {

            unitOfWork.MobileMoneyRepository().FindAll().forEach(mobileMoney -> {
                if(input.getDailySalesId().compareTo(mobileMoney.getDailySalesId()) == 0
                && !input.getId().equals(mobileMoney.getId()))
                    throw new DuplicateMobileMoneyException("Mobile Money Already has a record with the same ID");
            });
        }
        else {
            throw new MobileMoneyNullException("MobileMoneyCheckDuplicates input cannot be null");
        }
    }
}
