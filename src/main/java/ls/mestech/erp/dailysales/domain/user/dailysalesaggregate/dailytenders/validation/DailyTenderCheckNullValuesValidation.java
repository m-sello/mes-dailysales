package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailytenders.validation;

import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.model.DailyTender;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class DailyTenderCheckNullValuesValidation implements IValidationRule<DailyTenderCheckNullValues> {

    public DailyTenderCheckNullValuesValidation(){}
    @Override
    public void Validate(DailyTenderCheckNullValues dailyTenderCreateCommand) {
        try {
            if (dailyTenderCreateCommand != null) {

                Set<DailyTender> dailyTenderSet = dailyTenderCreateCommand.getDailyTenderSet();

                dailyTenderSet.forEach(dailyTender -> {
                    if (dailyTender.getAmount() == null) {
                        try {
                            throw new Exception("Amount cannot be null");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (dailyTender.getTenderTypeCd().isEmpty() || dailyTender.getTenderTypeCd().isBlank()
                        || dailyTender.getTenderTypeCd() == null)
                        try {
                            throw new Exception("Tender Code cannot be null");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                });
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
