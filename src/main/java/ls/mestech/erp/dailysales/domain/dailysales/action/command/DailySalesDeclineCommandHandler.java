package ls.mestech.erp.dailysales.domain.dailysales.action.command;

import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventDeclined;
import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventPending;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IDailySaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DailySalesDeclineCommandHandler implements ICommandHandler<DailySalesDeclineCommand, ResultWithDomainEvent<DailySales, DailySalesEventDeclined>> {
    @Autowired
    private final IDailySaleRepository dailySaleRepository;
    @Autowired
    private final ModelMapper modelMapper;
    ResultWithDomainEvent<DailySales, DailySalesEventDeclined> dailySalesEventResult;

    public DailySalesDeclineCommandHandler(IDailySaleRepository dailySaleRepository, ModelMapper modelMapper, ResultWithDomainEvent<DailySales, DailySalesEventDeclined> dailySalesEventResult) {
        this.dailySaleRepository = dailySaleRepository;
        this.modelMapper = modelMapper;
        this.dailySalesEventResult = dailySalesEventResult;
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventDeclined> Handle(DailySalesDeclineCommand dailySalesDeclineCommand) {

        DailySales dailSales = modelMapper.map(dailySalesDeclineCommand, DailySales.class);

        DailySalesEventDeclined declinedEvent = new DailySalesEventDeclined(dailSales.getId(), dailSales.getFloatAmount(), dailySalesDeclineCommand.getCapturedDt());

        return new ResultWithDomainEvent<>(dailSales,declinedEvent);
    }
}
