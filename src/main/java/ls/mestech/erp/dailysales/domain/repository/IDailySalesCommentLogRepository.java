package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.DailySalesCommentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDailySalesCommentLogRepository extends JpaRepository <DailySalesCommentLog,Long> {
}
