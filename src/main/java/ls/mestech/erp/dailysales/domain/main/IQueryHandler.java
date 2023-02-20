package ls.mestech.erp.dailysales.domain.main;

public interface IQueryHandler <TQuery, TResult>{
        TResult Handle(TQuery query);
}
