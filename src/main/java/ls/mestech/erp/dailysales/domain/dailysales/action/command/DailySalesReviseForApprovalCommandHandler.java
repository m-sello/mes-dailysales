package ls.mestech.erp.dailysales.domain.dailysales.action.command;

import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventAwaitingApproval;
import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventPending;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IDailySaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DailySalesReviseForApprovalCommandHandler implements ICommandHandler<DailySalesReviseForApprovalCommand, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval>> {
    @Autowired
    private final IDailySaleRepository dailySaleRepository;
    @Autowired
    private final ModelMapper modelMapper;
    ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> dailySalesEventResult;

    public DailySalesReviseForApprovalCommandHandler(IDailySaleRepository dailySaleRepository, ModelMapper modelMapper, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> dailySalesEventResult) {
        this.dailySaleRepository = dailySaleRepository;
        this.modelMapper = modelMapper;
        this.dailySalesEventResult = dailySalesEventResult;
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> Handle(DailySalesReviseForApprovalCommand dailySalesReviseForApprovalCommand) {

        DailySales dailSales = modelMapper.map(dailySalesReviseForApprovalCommand, DailySales.class);

        DailySalesEventAwaitingApproval revisedEvent = new DailySalesEventAwaitingApproval(dailSales.getId(), dailSales.getFloatAmount(), dailySalesReviseForApprovalCommand.getCapturedDt());

        return new ResultWithDomainEvent<>(dailSales,revisedEvent);
    }
}
