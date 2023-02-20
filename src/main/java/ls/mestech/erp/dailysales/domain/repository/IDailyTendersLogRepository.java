package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.DailyTendersLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDailyTendersLogRepository extends JpaRepository <DailyTendersLog,Long> {
}
