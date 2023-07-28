package ls.mestech.erp.dailysales.persistence.jdbc;

import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMobileMoneyRepositoryJdbc extends ListCrudRepository<MobileMoney, String> {

    MobileMoney findByDailySalesId(String dailySalesId);
}
