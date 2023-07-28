package ls.mestech.erp.dailysales.persistence.jdbc;

import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITenderTypeRepositoryJdbc extends ListCrudRepository<TenderType, String> {
}
