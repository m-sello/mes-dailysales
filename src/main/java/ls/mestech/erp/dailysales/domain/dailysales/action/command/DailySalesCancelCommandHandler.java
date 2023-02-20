package ls.mestech.erp.dailysales.domain.dailysales.action.command;

import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventCancelled;
import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventPending;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IDailySaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DailySalesCancelCommandHandler implements ICommandHandler<DailySalesCreateCommand, ResultWithDomainEvent<DailySales, DailySalesEventCancelled>> {
    @Autowired
    private final IDailySaleRepository dailySaleRepository;
    @Autowired
    private final ModelMapper modelMapper;
    ResultWithDomainEvent<DailySales, DailySalesEventPending> dailySalesEventResult;

    public DailySalesCancelCommandHandler(IDailySaleRepository dailySaleRepository, ModelMapper modelMapper, ResultWithDomainEvent<DailySales, DailySalesEventPending> dailySalesEventResult) {
        this.dailySaleRepository = dailySaleRepository;
        this.modelMapper = modelMapper;
        this.dailySalesEventResult = dailySalesEventResult;
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventCancelled> Handle(DailySalesCreateCommand dailySalesCreateCommand) {

        DailySales dailSales = modelMapper.map(dailySalesCreateCommand, DailySales.class);

        DailySalesEventCancelled createdEvent = new DailySalesEventCancelled(dailSales.getId(), dailSales.getFloatAmount(), dailySalesCreateCommand.getCapturedDt());

        return new ResultWithDomainEvent<>(dailSales,createdEvent);
    }
}
