package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.query;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ls.mestech.erp.dailysales.domain.main.IQuery;
import ls.mestech.erp.dailysales.domain.model.DailySales;

@AllArgsConstructor
@NoArgsConstructor
public class MobileMoneyGetByIdQuery implements IQuery<DailySales> {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
