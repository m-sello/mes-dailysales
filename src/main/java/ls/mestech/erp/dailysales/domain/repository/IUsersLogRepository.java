package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.UsersLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersLogRepository extends JpaRepository <UsersLog,Long> {
}
