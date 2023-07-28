package ls.mestech.erp.dailysales.domain.main;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import org.springframework.stereotype.Service;

@Service
public interface ICommandHandler <TCommand, TResult>{
    TResult Handle(@NotNull TCommand command);
}
