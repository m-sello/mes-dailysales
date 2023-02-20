package ls.mestech.erp.dailysales.domain.services.impl;

import ls.mestech.erp.dailysales.domain.dailysales.action.command.*;
import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.*;
import ls.mestech.erp.dailysales.domain.dailysales.action.query.DailySalesGetAllQuery;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.services.contracts.IDailySalesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DailySalesService implements IDailySalesService {
    @Autowired
    private final ICommandHandler<DailySalesCreateCommand, ResultWithDomainEvent<DailySales, DailySalesEventPending>> dailySalesCreateCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesUpdateCommand, ResultWithDomainEvent<DailySales, DailySalesEventPending>> dailySalesUpdateCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesApproveCommand, ResultWithDomainEvent<DailySales, DailySalesEventApproved>> dailySalesApproveCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesDeclineCommand, ResultWithDomainEvent<DailySales, DailySalesEventDeclined>> dailySalesDeclineCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesSubmitForApprovalCommand, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval>> dailySalesSubmitForApprovalCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesReviseForApprovalCommand, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval>> dailySalesReviseForApprovalCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesCancelCommand, ResultWithDomainEvent<DailySales, DailySalesEventCancelled>> dailySalesCancelCommandHandler;
    @Autowired

    private final IQueryHandler<DailySalesGetAllQuery, List<DailySales>> dailySalesGetAllQueryHandler;

    public DailySalesService(
            ICommandHandler<DailySalesCreateCommand, ResultWithDomainEvent<DailySales, DailySalesEventPending>> dailySalesCreateCommandHandler,
            ICommandHandler<DailySalesUpdateCommand, ResultWithDomainEvent<DailySales, DailySalesEventPending>> dailySalesUpdateCommandHandler,
            ICommandHandler<DailySalesApproveCommand, ResultWithDomainEvent<DailySales, DailySalesEventApproved>> dailySalesApproveCommandHandler,
            ICommandHandler<DailySalesDeclineCommand, ResultWithDomainEvent<DailySales, DailySalesEventDeclined>> dailySalesDeclineCommandHandler,
            ICommandHandler<DailySalesSubmitForApprovalCommand, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval>> dailySalesSubmitForApprovalCommandHandler,
            ICommandHandler<DailySalesReviseForApprovalCommand, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval>> dailySalesReviseForApprovalCommandHandler,
            ICommandHandler<DailySalesCancelCommand, ResultWithDomainEvent<DailySales, DailySalesEventCancelled>> dailySalesCancelCommandHandler,
            IQueryHandler<DailySalesGetAllQuery, List<DailySales>> dailySalesGetAllQueryHandler) {
        this.dailySalesCreateCommandHandler = dailySalesCreateCommandHandler;
        this.dailySalesUpdateCommandHandler = dailySalesUpdateCommandHandler;
        this.dailySalesApproveCommandHandler = dailySalesApproveCommandHandler;
        this.dailySalesCancelCommandHandler = dailySalesCancelCommandHandler;
        this.dailySalesDeclineCommandHandler = dailySalesDeclineCommandHandler;
        this.dailySalesSubmitForApprovalCommandHandler = dailySalesSubmitForApprovalCommandHandler;
        this.dailySalesReviseForApprovalCommandHandler = dailySalesReviseForApprovalCommandHandler;
        this.dailySalesGetAllQueryHandler = dailySalesGetAllQueryHandler;
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventPending> Create(DailySalesCreateCommand command) {
        return dailySalesCreateCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventPending> Update(DailySalesUpdateCommand command) {
        return dailySalesUpdateCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventCancelled> Cancel(DailySalesCancelCommand command) {
        return dailySalesCancelCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventDeclined> Decline(DailySalesDeclineCommand command) {
        return dailySalesDeclineCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> ReviseForApproval(DailySalesReviseForApprovalCommand command) {
        return dailySalesReviseForApprovalCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> SubmitForApproval(DailySalesSubmitForApprovalCommand command) {
        return dailySalesSubmitForApprovalCommandHandler.Handle(command);
    }

    @Override
    public List<DailySales> GetAll(DailySalesGetAllQuery query) {
        return dailySalesGetAllQueryHandler.Handle(query);
    }
}
