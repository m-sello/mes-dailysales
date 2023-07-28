package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.UnsupportedStateTransitionException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.DailySalesEventAwaitingApproval;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.IDomainEventState;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailySalesSubmitForApprovalCommandHandler implements ICommandHandler<DailySalesSubmitForApprovalCommand, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval>> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IDomainEventState domainEventState;

    public DailySalesSubmitForApprovalCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, IDomainEventState domainEventState) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.domainEventState = domainEventState;
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> Handle(DailySalesSubmitForApprovalCommand dailySalesSubmitForApprovalCommand) {

        DailySales dailSales = unitOfWork.DailySalesRepository().FindById(dailySalesSubmitForApprovalCommand.getId());

        if (dailSales != null) {
            dailSales = modelMapper.map(dailySalesSubmitForApprovalCommand, DailySales.class);

            try {
                domainEventState.Create();
            } catch (UnsupportedStateTransitionException e) {
                throw new RuntimeException(e);
            }

            DailySalesEventAwaitingApproval awaitingApprovalEvent = (DailySalesEventAwaitingApproval) domainEventState;

            unitOfWork.DailySalesRepository().Add(dailSales);

            return new ResultWithDomainEvent<>(dailSales, awaitingApprovalEvent);
        }

        throw new ObjectNotFoundException(dailySalesSubmitForApprovalCommand, "Daily Sales ID not found");
    }
}
