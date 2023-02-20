package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.DailySalesComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDailySalesCommentRepository extends JpaRepository <DailySalesComment,Long> {
}
