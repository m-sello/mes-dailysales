package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserGroupRepository extends JpaRepository <UserGroup,Long> {
}
