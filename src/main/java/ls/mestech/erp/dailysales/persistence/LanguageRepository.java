package ls.mestech.erp.dailysales.persistence;

import com.querydsl.core.types.Predicate;
import jakarta.persistence.criteria.Expression;
import ls.mestech.erp.dailysales.domain.repository.ILanguageRepository;
import ls.mestech.erp.dailysales.persistence.jdbc.ILanguageRepositoryJdbc;
import ls.mestech.erp.dailysales.domain.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;
@Repository
public class LanguageRepository implements ILanguageRepository {
    @Autowired
    private final ILanguageRepositoryJdbc languageRepositoryJdbc;

    public LanguageRepository(ILanguageRepositoryJdbc languageRepositoryJdbc) {
        this.languageRepositoryJdbc = languageRepositoryJdbc;
    }


    @Override
    public Language FindById(String id) {

        return languageRepositoryJdbc.findById(id).orElse(null);
    }

    @Override
    public Iterable<Language> FindAll() {

        return languageRepositoryJdbc.findAll();
    }

    @Override
    public Iterable<Language> FindAll(Expression<Function<Language, Boolean>> predicate) {

        return null;
    }

    @Override
    public Optional<Language> FindOne(Expression<Function<Language, Boolean>> predicate) {
        return null;
    }

    @Override
    public long count(Expression<Function<Language, Boolean>> predicate) {
        return languageRepositoryJdbc.count();
    }

    @Override
    public boolean exists(Expression<Function<Language, Boolean>> predicate) {
        return false;
    }

    @Override
    public Language Add(Language language) {

        return languageRepositoryJdbc.save(language);
    }

    @Override
    public Language Update(Language language) {
        return languageRepositoryJdbc.save(language);
    }

    @Override
    public Iterable<Language> AddRange(Iterable<Language> languages) {

        return languageRepositoryJdbc.saveAll(languages);
    }

    @Override
    public void Remove(Language language) {

        languageRepositoryJdbc.delete(language);
    }

    @Override
    public void RemoveRange(Iterable<Language> languages) {

        languageRepositoryJdbc.deleteAll(languages);
    }

}
