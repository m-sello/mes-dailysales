package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.TenderTypeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITenderTypeLogRepository extends JpaRepository <TenderTypeLog,Long> {
}
