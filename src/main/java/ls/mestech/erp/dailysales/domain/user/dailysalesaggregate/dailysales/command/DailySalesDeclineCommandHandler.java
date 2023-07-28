package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.UnsupportedStateTransitionException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.DailySalesEventDeclined;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.IDomainEventState;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailySalesDeclineCommandHandler implements ICommandHandler<DailySalesDeclineCommand, ResultWithDomainEvent<DailySales, DailySalesEventDeclined>> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IDomainEventState domainEventState;

    public DailySalesDeclineCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, IDomainEventState domainEventState) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.domainEventState = domainEventState;
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventDeclined> Handle(DailySalesDeclineCommand dailySalesDeclineCommand) {

        DailySales dailSales = unitOfWork.DailySalesRepository().FindById(dailySalesDeclineCommand.getId());

        if (dailSales != null) {
            dailSales = modelMapper.map(dailySalesDeclineCommand, DailySales.class);

            try {
                domainEventState.Create();
            } catch (UnsupportedStateTransitionException e) {
                throw new RuntimeException(e);
            }

            DailySalesEventDeclined declinedEvent = (DailySalesEventDeclined) domainEventState;

            unitOfWork.DailySalesRepository().Add(dailSales);

            return new ResultWithDomainEvent<>(dailSales, declinedEvent);
        }

        throw new ObjectNotFoundException(dailySalesDeclineCommand, "Daily Sales ID not found");
    }
}
