package ls.mestech.erp.dailysales.persistence;

import com.querydsl.core.types.Predicate;
import jakarta.persistence.criteria.Expression;
import ls.mestech.erp.dailysales.domain.model.UserSecurityGroup;
import ls.mestech.erp.dailysales.domain.repository.IUserGroupRepository;
import ls.mestech.erp.dailysales.persistence.jdbc.IUserGroupRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserGroupRepository implements IUserGroupRepository {
    @Autowired
    private final IUserGroupRepositoryJdbc userGroupRepositoryJdbc;

    public UserGroupRepository(IUserGroupRepositoryJdbc userGroupRepositoryJdbc) {
        this.userGroupRepositoryJdbc = userGroupRepositoryJdbc;
    }


    @Override
    public UserSecurityGroup FindById(Long id) {

        return userGroupRepositoryJdbc.findById(id).orElse(null);
    }

    @Override
    public Iterable<UserSecurityGroup> FindAll() {

        return userGroupRepositoryJdbc.findAll();
    }

    @Override
    public Iterable<UserSecurityGroup> FindAll(Expression<Function<UserSecurityGroup, Boolean>> predicate) {

        return null;
    }

    @Override
    public Optional<UserSecurityGroup> FindOne(Expression<Function<UserSecurityGroup, Boolean>> predicate) {
        return null;
    }

    @Override
    public long count(Expression<Function<UserSecurityGroup, Boolean>> predicate) {
        return userGroupRepositoryJdbc.count();
    }

    @Override
    public boolean exists(Expression<Function<UserSecurityGroup, Boolean>> predicate) {
        return false;
    }

    @Override
    public UserSecurityGroup Add(UserSecurityGroup userSecurityGroup) {

        return userGroupRepositoryJdbc.save(userSecurityGroup);
    }

    @Override
    public UserSecurityGroup Update(UserSecurityGroup userSecurityGroup) {
        return userGroupRepositoryJdbc.save(userSecurityGroup);
    }

    @Override
    public Iterable<UserSecurityGroup> AddRange(Iterable<UserSecurityGroup> userSecurityGroups) {

        return userGroupRepositoryJdbc.saveAll(userSecurityGroups);
    }

    @Override
    public void Remove(UserSecurityGroup userSecurityGroup) {

        userGroupRepositoryJdbc.delete(userSecurityGroup);
    }

    @Override
    public void RemoveRange(Iterable<UserSecurityGroup> userSecurityGroups) {

        userGroupRepositoryJdbc.deleteAll(userSecurityGroups);
    }

}
