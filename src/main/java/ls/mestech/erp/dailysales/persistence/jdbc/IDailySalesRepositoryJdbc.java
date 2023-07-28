package ls.mestech.erp.dailysales.persistence.jdbc;

import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IDailySalesRepositoryJdbc extends ListCrudRepository<DailySales, String> {
    DailySales findByCapturedDt(LocalDateTime capturedDt);
}
