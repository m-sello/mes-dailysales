package ls.mestech.erp.dailysales.persistence;

import com.querydsl.core.types.Predicate;
import jakarta.persistence.criteria.Expression;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.domain.repository.ITenderTypeRepository;
import ls.mestech.erp.dailysales.persistence.jdbc.ITenderTypeRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
@Repository
public class TenderTypeRepository implements ITenderTypeRepository {
    @Autowired
    private final ITenderTypeRepositoryJdbc tenderTypeRepositoryJdbc;

    public TenderTypeRepository(ITenderTypeRepositoryJdbc tenderTypeRepositoryJdbc) {
        this.tenderTypeRepositoryJdbc = tenderTypeRepositoryJdbc;
    }

    @Override
    public TenderType FindById(String id) {
        return tenderTypeRepositoryJdbc.findById(id).orElse(null);
    }

    @Override
    public Iterable<TenderType> FindAll() {
        return tenderTypeRepositoryJdbc.findAll();
    }

    @Override
    public Iterable<TenderType> FindAll(Expression<Function<TenderType, Boolean>> predicate) {

        return null;
    }
    @Override
    public Optional<TenderType> FindOne(Expression<Function<TenderType, Boolean>> predicate) {

        return null;
    }

    @Override
    public long count(Expression<Function<TenderType, Boolean>> predicate) {
        return tenderTypeRepositoryJdbc.count();
    }

    @Override
    public boolean exists(Expression<Function<TenderType, Boolean>> predicate) {
        return false;
    }

    @Override
    public TenderType Add(TenderType tenderType) {
        return tenderTypeRepositoryJdbc.save(tenderType);
    }

    @Override
    public TenderType Update(TenderType tenderType) {
        return tenderTypeRepositoryJdbc.save(tenderType);
    }

    @Override
    public Iterable<TenderType> AddRange(Iterable<TenderType> tenderTypes) {
        return tenderTypeRepositoryJdbc.saveAll(tenderTypes);
    }

    @Override
    public void Remove(TenderType tenderType) {
        tenderTypeRepositoryJdbc.delete(tenderType);
    }

    @Override
    public void RemoveRange(Iterable<TenderType> tenderTypes) {
        tenderTypeRepositoryJdbc.deleteAll(tenderTypes);
    }


}
