package ls.mestech.erp.dailysales.domain.main;

import org.springframework.stereotype.Service;

@Service
public interface IQueryHandler <TQuery, TResult>{
        TResult Handle(TQuery query);
}
