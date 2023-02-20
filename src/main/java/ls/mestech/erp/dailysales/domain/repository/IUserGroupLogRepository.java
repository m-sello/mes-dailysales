package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.UserGroupLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserGroupLogRepository extends JpaRepository <UserGroupLog,Long> {
}
