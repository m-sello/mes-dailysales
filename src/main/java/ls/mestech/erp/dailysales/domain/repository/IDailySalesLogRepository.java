package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.DailySalesLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDailySalesLogRepository extends JpaRepository <DailySalesLog,Long> {
}
