package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.DailyTender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDailyTenderRepository extends JpaRepository <DailyTender,Long> {
}
