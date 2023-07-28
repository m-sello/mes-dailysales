package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import org.springframework.stereotype.Repository;

@Repository
public interface ITenderTypeRepository  extends IRepository<TenderType,String> {
}
