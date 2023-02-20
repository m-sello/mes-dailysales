package ls.mestech.erp.dailysales.domain.dailysales.action.command;

import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventPending;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IDailySaleRepository;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DailySalesCreateCommandHandler implements ICommandHandler<DailySalesCreateCommand, ResultWithDomainEvent<DailySales, DailySalesEventPending>> {
    @Autowired
    private final IDailySaleRepository dailySaleRepository;
    @Autowired
    private final ModelMapper modelMapper;
    ResultWithDomainEvent<DailySales, DailySalesEventPending> dailySalesEventResult;

    public DailySalesCreateCommandHandler(IDailySaleRepository dailySaleRepository, ModelMapper modelMapper, ResultWithDomainEvent<DailySales, DailySalesEventPending> dailySalesEventResult) {
        this.dailySaleRepository = dailySaleRepository;
        this.modelMapper = modelMapper;
        this.dailySalesEventResult = dailySalesEventResult;
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventPending> Handle(DailySalesCreateCommand dailySalesCreateCommand) {

        DailySales dailSales = modelMapper.map(dailySalesCreateCommand, DailySales.class);

        DailySalesEventPending createdEvent = new DailySalesEventPending(dailSales.getId(), dailSales.getFloatAmount(), dailySalesCreateCommand.getCapturedDt());

        return new ResultWithDomainEvent<>(dailSales,createdEvent);
    }
}
