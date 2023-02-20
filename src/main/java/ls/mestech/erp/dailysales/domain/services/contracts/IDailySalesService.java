package ls.mestech.erp.dailysales.domain.services.contracts;

import ls.mestech.erp.dailysales.domain.dailysales.action.command.*;
import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventAwaitingApproval;
import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventPending;
import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventCancelled;
import ls.mestech.erp.dailysales.domain.dailysales.action.lifecycle.DailySalesEventDeclined;
import ls.mestech.erp.dailysales.domain.dailysales.action.query.DailySalesGetAllQuery;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;

import java.util.List;
public interface IDailySalesService {
    ResultWithDomainEvent<DailySales, DailySalesEventPending> Create(DailySalesCreateCommand command);
    ResultWithDomainEvent<DailySales, DailySalesEventPending> Update(DailySalesUpdateCommand command);
    ResultWithDomainEvent<DailySales, DailySalesEventCancelled> Cancel(DailySalesCancelCommand command);
    ResultWithDomainEvent<DailySales, DailySalesEventDeclined> Decline(DailySalesDeclineCommand command);
    ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> ReviseForApproval(DailySalesReviseForApprovalCommand command);
    ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> SubmitForApproval(DailySalesSubmitForApprovalCommand command);
    List<DailySales> GetAll(DailySalesGetAllQuery query);
}
