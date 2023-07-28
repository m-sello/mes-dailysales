package ls.mestech.erp.dailysales.domain.main;

import org.springframework.stereotype.Service;

@Service
public interface IAlgorithm<TCommand, TResult>{
    TResult Invoke(TCommand command);
}
