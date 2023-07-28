package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.UserLog;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserLogRepository extends IRepository<UserLog,Long> {
}
