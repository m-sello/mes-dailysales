package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.query;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ls.mestech.erp.dailysales.domain.main.IQuery;
import ls.mestech.erp.dailysales.domain.model.DailySales;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class DailySalesGetByIdQuery  implements IQuery<DailySales> {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
