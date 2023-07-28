package ls.mestech.erp.dailysales.persistence;

import com.querydsl.core.types.Predicate;
import jakarta.persistence.criteria.Expression;
import ls.mestech.erp.dailysales.domain.model.User;
import ls.mestech.erp.dailysales.domain.repository.IUserRepository;
import ls.mestech.erp.dailysales.persistence.jdbc.IUserRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserRepository implements IUserRepository {
    @Autowired
    private final IUserRepositoryJdbc userRepositoryJdbc;

    public UserRepository(IUserRepositoryJdbc userRepositoryJdbc) {
        this.userRepositoryJdbc = userRepositoryJdbc;
    }


    @Override
    public User FindById(Long id) {

        return userRepositoryJdbc.findById(id).orElse(null);
    }

    @Override
    public Iterable<User> FindAll() {

        return userRepositoryJdbc.findAll();
    }

    @Override
    public Iterable<User> FindAll(Expression<Function<User, Boolean>> predicate) {

        return null;
    }

    @Override
    public Optional<User> FindOne(Expression<Function<User, Boolean>> predicate) {
        return null;
    }

    @Override
    public long count(Expression<Function<User, Boolean>> predicate) {
        return userRepositoryJdbc.count();
    }

    @Override
    public boolean exists(Expression<Function<User, Boolean>> predicate) {
        return false;
    }

    @Override
    public User Add(User user) {

        return userRepositoryJdbc.save(user);
    }

    @Override
    public User Update(User user) {
        return userRepositoryJdbc.save(user);
    }

    @Override
    public Iterable<User> AddRange(Iterable<User> users) {

        return userRepositoryJdbc.saveAll(users);
    }

    @Override
    public void Remove(User user) {

        userRepositoryJdbc.delete(user);
    }

    @Override
    public void RemoveRange(Iterable<User> users) {

        userRepositoryJdbc.deleteAll(users);
    }

}
