package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command;

import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesNullException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.UnsupportedStateTransitionException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.IDomainEventState;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailySalesDeleteCommandHandler implements ICommandHandler<DailySalesDeleteCommand, DailySales>{
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final IDomainEventState domainEventState;

    public DailySalesDeleteCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper, IDomainEventState domainEventState) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
        this.domainEventState = domainEventState;
    }


    @Override
    public DailySales Handle(DailySalesDeleteCommand dailySalesCancelCommand) {

        DailySales dailSales = unitOfWork.DailySalesRepository().FindById(dailySalesCancelCommand.getId());

        if (dailSales != null) {

            try {
                domainEventState.Create();
            } catch (UnsupportedStateTransitionException e) {
                throw new RuntimeException(e);
            }

            unitOfWork.DailySalesRepository().Remove(dailSales);

            return new DailySales();
        }

        throw new DailySalesNullException("Daily Sales cannot be null");
    }
}
