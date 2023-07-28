package ls.mestech.erp.dailysales.domain.main;

import org.springframework.stereotype.Service;

@Service
public interface IValidationRule<TCommand>{
    void Validate(TCommand command);
}
