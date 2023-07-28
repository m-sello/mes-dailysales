package ls.mestech.erp.dailysales.persistence;

import com.querydsl.core.types.Predicate;
import jakarta.persistence.criteria.Expression;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.repository.IMobileMoneyRepository;
import ls.mestech.erp.dailysales.domain.repository.IMobileMoneyRepository;
import ls.mestech.erp.dailysales.persistence.jdbc.IMobileMoneyRepositoryJdbc;
import ls.mestech.erp.dailysales.persistence.jdbc.IMobileMoneyRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;

@Repository
public class MobileMoneyRepository implements IMobileMoneyRepository {
    @Autowired
    private final IMobileMoneyRepositoryJdbc mobileMoneyRepositoryJdbc;

    public MobileMoneyRepository(IMobileMoneyRepositoryJdbc mobileMoneyRepositoryJdbc) {
        this.mobileMoneyRepositoryJdbc = mobileMoneyRepositoryJdbc;
    }
    @Override
    public MobileMoney FindById(String id) {

        return mobileMoneyRepositoryJdbc.findById(id).orElse(null);
    }
    @Override
    public Iterable<MobileMoney> FindAll() {

        return mobileMoneyRepositoryJdbc.findAll();
    }
    @Override
    public Iterable<MobileMoney> FindAll(Expression<Function<MobileMoney, Boolean>> predicate) {

        return null;
    }

    @Override
    public Optional<MobileMoney> FindOne(Expression<Function<MobileMoney, Boolean>> predicate) {

        return null;
    }

    @Override
    public long count(Expression<Function<MobileMoney, Boolean>> predicate) {
        return mobileMoneyRepositoryJdbc.count();
    }

    @Override
    public boolean exists(Expression<Function<MobileMoney, Boolean>> predicate) {
        return false;
    }

    @Override
    public MobileMoney Add(MobileMoney language) {

        return mobileMoneyRepositoryJdbc.save(language);
    }

    @Override
    public MobileMoney Update(MobileMoney language) {
        return mobileMoneyRepositoryJdbc.save(language);
    }

    @Override
    public Iterable<MobileMoney> AddRange(Iterable<MobileMoney> languages) {

        return mobileMoneyRepositoryJdbc.saveAll(languages);
    }

    @Override
    public void Remove(MobileMoney language) {

        mobileMoneyRepositoryJdbc.delete(language);
    }

    @Override
    public void RemoveRange(Iterable<MobileMoney> languages) {

        mobileMoneyRepositoryJdbc.deleteAll(languages);
    }

    @Override
    public MobileMoney GetByDailySalesId(String dailySalesId) {
        return mobileMoneyRepositoryJdbc.findByDailySalesId(dailySalesId);
    }
}
