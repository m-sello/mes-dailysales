package ls.mestech.erp.dailysales.domain.dailysales.action.command;

import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventAwaitingApproval;
import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventPending;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IDailySaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DailySalesSubmitForApprovalCommandHandler implements ICommandHandler<DailySalesSubmitForApprovalCommand, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval>> {
    @Autowired
    private final IDailySaleRepository dailySaleRepository;
    @Autowired
    private final ModelMapper modelMapper;
    ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> dailySalesEventResult;

    public DailySalesSubmitForApprovalCommandHandler(IDailySaleRepository dailySaleRepository, ModelMapper modelMapper, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> dailySalesEventResult) {
        this.dailySaleRepository = dailySaleRepository;
        this.modelMapper = modelMapper;
        this.dailySalesEventResult = dailySalesEventResult;
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> Handle(DailySalesSubmitForApprovalCommand dailySalesSubmitForApprovalCommand) {

        DailySales dailSales = modelMapper.map(dailySalesSubmitForApprovalCommand, DailySales.class);

        DailySalesEventAwaitingApproval submittedEvent = new DailySalesEventAwaitingApproval(dailSales.getId(), dailSales.getFloatAmount(), dailySalesSubmitForApprovalCommand.getCapturedDt());

        return new ResultWithDomainEvent<>(dailSales,submittedEvent);
    }
}
