package ls.mestech.erp.dailysales.domain.main;

import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.utilities.IUserContext;

public class SecureCommandDecorator<TCommand> implements ICommandHandler<TCommand, ResultWithDomainEvent>{
    private final ICommandHandler<TCommand, ResultWithDomainEvent> decoratee;
    private final IUserContext userContext;

    public SecureCommandDecorator(ICommandHandler<TCommand, ResultWithDomainEvent> decoratee, IUserContext userContext) {
        this.decoratee = decoratee;
        this.userContext = userContext;
    }

    @Override
    public ResultWithDomainEvent Handle(TCommand tCommand) {
        CheckAuthorization();
        return decoratee.Handle(tCommand);
    }
    private void CheckAuthorization()
    {
        /*if (!userContext.IsInRole(pRole.Role))
        {
            throw new SecurityException("User not authorized to perform this operation");
        }*/
    }
}
