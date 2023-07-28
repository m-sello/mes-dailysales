package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailytenders.validation;

import ls.mestech.erp.dailysales.domain.model.DailyTender;

import java.util.HashSet;
import java.util.Set;

public class DailyTenderCheckNullValues {
    Set<DailyTender> dailyTenderSet = new HashSet<>();

    public Set<DailyTender> getDailyTenderSet() {
        return dailyTenderSet;
    }

    public void setDailyTenderSet(Set<DailyTender> dailyTenderSet) {
        this.dailyTenderSet = dailyTenderSet;
    }
}
