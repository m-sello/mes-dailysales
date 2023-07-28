package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.UnsupportedStateTransitionException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.DailySalesEventApproved;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.IDomainEventState;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailySalesApproveCommandHandler implements ICommandHandler<DailySalesApproveCommand, ResultWithDomainEvent<DailySales, DailySalesEventApproved>> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IDomainEventState domainEventState;

    public DailySalesApproveCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, IDomainEventState domainEventState) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.domainEventState = domainEventState;
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventApproved> Handle(DailySalesApproveCommand dailySalesApproveCommand) {

        DailySales dailSales = modelMapper.map(dailySalesApproveCommand, DailySales.class);

        try {
            domainEventState.Update();

        } catch (UnsupportedStateTransitionException e) {
            throw new RuntimeException(e);
        }

        DailySalesEventApproved approvedEvent = (DailySalesEventApproved) domainEventState;

        return new ResultWithDomainEvent<>(dailSales,approvedEvent);
    }
}
