package ls.mestech.erp.dailysales.persistence;

import com.querydsl.core.types.Predicate;
import jakarta.persistence.criteria.Expression;
import ls.mestech.erp.dailysales.domain.repository.IDailySalesRepository;
import ls.mestech.erp.dailysales.persistence.jdbc.IDailySalesRepositoryJdbc;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;
@Repository
public class DailySalesRepository implements IDailySalesRepository {
    @Autowired
    private final IDailySalesRepositoryJdbc dailySalesRepositoryJdbc;

    public DailySalesRepository(IDailySalesRepositoryJdbc dailySaleRepositoryJpa) {
        this.dailySalesRepositoryJdbc = dailySaleRepositoryJpa;
    }

    @Override
    public DailySales FindById(String id) {

        return dailySalesRepositoryJdbc.findById(id).orElse(null);
    }

    @Override
    public Iterable<DailySales> FindAll() {
        return dailySalesRepositoryJdbc.findAll();
    }

    @Override
    public Iterable<DailySales> FindAll(Expression<Function<DailySales, Boolean>> predicate) {

        return null;
    }

    @Override
    public Optional<DailySales> FindOne(Expression<Function<DailySales, Boolean>> predicate) {

        return null;
    }

    @Override
    public long count(Expression<Function<DailySales, Boolean>> predicate) {
        return dailySalesRepositoryJdbc.count();
    }

    @Override
    public boolean exists(Expression<Function<DailySales, Boolean>> predicate) {
        return false;
    }

    @Override
    public DailySales Add(DailySales dailySales) {

        return dailySalesRepositoryJdbc.save(dailySales);
    }

    @Override
    public DailySales Update(DailySales dailySales) {

        return dailySalesRepositoryJdbc.save(dailySales);
    }

    @Override
    public Iterable<DailySales> AddRange(Iterable<DailySales> dailySales) {
        return dailySalesRepositoryJdbc.saveAll(dailySales);
    }

    @Override
    public void Remove(DailySales dailySales) {
        dailySalesRepositoryJdbc.delete(dailySales);
    }

    @Override
    public void RemoveRange(Iterable<DailySales> dailySales) {
        dailySalesRepositoryJdbc.deleteAll(dailySales);
    }

    @Override
    public DailySales GetByCapturedDt(LocalDateTime capturedDt) {
        return dailySalesRepositoryJdbc.findByCapturedDt(capturedDt);
    }
}
