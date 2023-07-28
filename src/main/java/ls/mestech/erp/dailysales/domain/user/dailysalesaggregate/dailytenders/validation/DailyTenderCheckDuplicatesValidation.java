package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailytenders.validation;

import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.common.CommonValidations;
import ls.mestech.erp.dailysales.domain.model.DailyTender;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class DailyTenderCheckDuplicatesValidation implements IValidationRule<DailyTenderCheckDuplicates> {

    public DailyTenderCheckDuplicatesValidation(){}
    @Override
    public void Validate(DailyTenderCheckDuplicates input) {
        try {
            if (input != null) {

                Set<DailyTender> dailyTenderSet = input.getDailyTenderSet();

                List<String> dailyTenderStrings = dailyTenderSet.stream().map(DailyTender::getTenderTypeCd).collect(Collectors.toList());

                CommonValidations<String> validations = new CommonValidations<>();

                List<String> duplicates = validations.listDuplicateUsingFilterAndSetAdd(dailyTenderStrings);

                if(duplicates.size() > 1)
                    throw new DuplicateKeyException("Duplicate Daily Tenders Types are not allowed");

            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
