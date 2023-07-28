package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import org.springframework.stereotype.Repository;

@Repository
public interface IMobileMoneyRepository extends IRepository<MobileMoney,String> {
    MobileMoney GetByDailySalesId(String dailySalesId);
}
