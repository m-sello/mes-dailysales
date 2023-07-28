package ls.mestech.erp.dailysales.domain.services.user.impl;

import ls.mestech.erp.dailysales.domain.services.user.contracts.IDailySalesService;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.DailySalesEventApproved;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.DailySalesEventAwaitingApproval;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.DailySalesEventDeclined;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command.*;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.query.DailySalesGetAllQuery;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.query.DailySalesGetByIdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DailySalesService implements IDailySalesService {
    @Autowired
    private final ICommandHandler<DailySalesCreateCommand, DailySales> dailySalesCreateCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesUpdateCommand, DailySales> dailySalesUpdateCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesApproveCommand, ResultWithDomainEvent<DailySales, DailySalesEventApproved>> dailySalesApproveCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesDeclineCommand, ResultWithDomainEvent<DailySales, DailySalesEventDeclined>> dailySalesDeclineCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesSubmitForApprovalCommand, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval>> dailySalesSubmitForApprovalCommandHandler;
    @Autowired
    private final ICommandHandler<DailySalesDeleteCommand, DailySales> dailySalesDeleteCommandHandler;
    @Autowired
    private final IQueryHandler<DailySalesGetAllQuery, List<DailySales>> dailySalesGetAllQueryHandler;
    @Autowired
    private final IQueryHandler<DailySalesGetByIdQuery, DailySales> dailySalesGetByIdQueryHandler;

    public DailySalesService(ICommandHandler<DailySalesCreateCommand, DailySales> dailySalesCreateCommandHandler, ICommandHandler<DailySalesUpdateCommand, DailySales> dailySalesUpdateCommandHandler, ICommandHandler<DailySalesApproveCommand, ResultWithDomainEvent<DailySales, DailySalesEventApproved>> dailySalesApproveCommandHandler, ICommandHandler<DailySalesDeclineCommand, ResultWithDomainEvent<DailySales, DailySalesEventDeclined>> dailySalesDeclineCommandHandler, ICommandHandler<DailySalesSubmitForApprovalCommand, ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval>> dailySalesSubmitForApprovalCommandHandler, ICommandHandler<DailySalesDeleteCommand, DailySales> dailySalesDeleteCommandHandler, IQueryHandler<DailySalesGetAllQuery, List<DailySales>> dailySalesGetAllQueryHandler, IQueryHandler<DailySalesGetByIdQuery, DailySales> dailySalesGetByIdQueryHandler) {
        this.dailySalesCreateCommandHandler = dailySalesCreateCommandHandler;
        this.dailySalesUpdateCommandHandler = dailySalesUpdateCommandHandler;
        this.dailySalesApproveCommandHandler = dailySalesApproveCommandHandler;
        this.dailySalesDeclineCommandHandler = dailySalesDeclineCommandHandler;
        this.dailySalesSubmitForApprovalCommandHandler = dailySalesSubmitForApprovalCommandHandler;
        this.dailySalesDeleteCommandHandler = dailySalesDeleteCommandHandler;
        this.dailySalesGetAllQueryHandler = dailySalesGetAllQueryHandler;
        this.dailySalesGetByIdQueryHandler = dailySalesGetByIdQueryHandler;
    }


    @Override
    public DailySales Create(DailySalesCreateCommand command) {
        return dailySalesCreateCommandHandler.Handle(command);
    }

    @Override
    public DailySales Update(DailySalesUpdateCommand command) {
        return dailySalesUpdateCommandHandler.Handle(command);
    }

    @Override
    public void Delete(DailySalesDeleteCommand command) {
        dailySalesDeleteCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventApproved> Approve(DailySalesApproveCommand command) {
        return dailySalesApproveCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventDeclined> Decline(DailySalesDeclineCommand command) {
        return dailySalesDeclineCommandHandler.Handle(command);
    }

    @Override
    public ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> SubmitForApproval(DailySalesSubmitForApprovalCommand command) {
        return dailySalesSubmitForApprovalCommandHandler.Handle(command);
    }

    @Override
    public List<DailySales> GetAll(DailySalesGetAllQuery query) {
        return dailySalesGetAllQueryHandler.Handle(query);
    }

    @Override
    public DailySales GetById(DailySalesGetByIdQuery query) {
        return dailySalesGetByIdQueryHandler.Handle(query);
    }
}
