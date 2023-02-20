package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepositoryRepository extends JpaRepository <User,Long> {
}
