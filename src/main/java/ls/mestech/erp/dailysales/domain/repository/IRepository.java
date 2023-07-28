package ls.mestech.erp.dailysales.domain.repository;

import com.querydsl.core.types.Predicate;
import jakarta.persistence.criteria.Expression;

import java.util.Optional;
import java.util.function.Function;

public interface IRepository<TEntity,TId>{
    TEntity FindById(TId id);
    Iterable<TEntity> FindAll();
    Iterable<TEntity> FindAll(Expression<Function<TEntity, Boolean>> predicate);
    Optional<TEntity> FindOne(Expression<Function<TEntity, Boolean>> predicate);
    long count(Expression<Function<TEntity, Boolean>> predicate);
    boolean exists(Expression<Function<TEntity, Boolean>> predicate);
    TEntity Add(TEntity entity);
    TEntity Update(TEntity entity);
    Iterable<TEntity> AddRange(Iterable<TEntity> entities);
    void Remove(TEntity entity);
    void RemoveRange(Iterable<TEntity> entities);
}