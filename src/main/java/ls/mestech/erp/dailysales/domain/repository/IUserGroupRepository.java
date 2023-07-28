package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.UserSecurityGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserGroupRepository  extends IRepository<UserSecurityGroup,Long> {
}
