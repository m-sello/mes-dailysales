package ls.mestech.erp.dailysales.domain.main;

public interface ICommandHandler <TCommand, ResultWithDomainEvent>{
    ResultWithDomainEvent Handle(TCommand command);
}
