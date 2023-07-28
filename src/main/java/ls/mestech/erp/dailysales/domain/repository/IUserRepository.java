package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IRepository<User,Long> {
}
