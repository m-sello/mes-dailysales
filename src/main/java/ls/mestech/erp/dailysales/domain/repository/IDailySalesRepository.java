package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.DailySales;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IDailySalesRepository extends IRepository<DailySales,String> {
    DailySales GetByCapturedDt(LocalDateTime capturedDt);
}
