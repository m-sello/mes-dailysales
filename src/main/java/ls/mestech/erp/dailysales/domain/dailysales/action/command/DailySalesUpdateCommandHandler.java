package ls.mestech.erp.dailysales.domain.dailysales.action.command;

import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventPending;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IDailySaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DailySalesUpdateCommandHandler implements ICommandHandler<DailySalesUpdateCommand, ResultWithDomainEvent<DailySales, DailySalesEventPending>> {
    @Autowired
    private final IDailySaleRepository dailySaleRepository;
    @Autowired
    private final ModelMapper modelMapper;
    ResultWithDomainEvent<DailySales, DailySalesEventPending> dailySalesEventResult;

    public DailySalesUpdateCommandHandler(IDailySaleRepository dailySaleRepository, ModelMapper modelMapper, ResultWithDomainEvent<DailySales, DailySalesEventPending> dailySalesEventResult) {
        this.dailySaleRepository = dailySaleRepository;
        this.modelMapper = modelMapper;
        this.dailySalesEventResult = dailySalesEventResult;
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventPending> Handle(DailySalesUpdateCommand dailySalesUpdateCommand) {

        DailySales dailSales = modelMapper.map(dailySalesUpdateCommand, DailySales.class);

        DailySalesEventPending updatedEvent = new DailySalesEventPending(dailSales.getId(), dailSales.getFloatAmount(), dailySalesUpdateCommand.getCapturedDt());

        return new ResultWithDomainEvent<>(dailSales,updatedEvent);
    }
}
