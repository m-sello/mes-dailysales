package ls.mestech.erp.dailysales.domain.services.user.contracts;

import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.DailySalesEventApproved;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.DailySalesEventAwaitingApproval;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle.DailySalesEventDeclined;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command.*;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.query.DailySalesGetAllQuery;
import ls.mestech.erp.dailysales.domain.main.events.ResultWithDomainEvent;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.query.DailySalesGetByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IDailySalesService {
    DailySales Create(DailySalesCreateCommand command);
    DailySales Update(DailySalesUpdateCommand command);
    void Delete(DailySalesDeleteCommand command);
    ResultWithDomainEvent<DailySales, DailySalesEventApproved> Approve(DailySalesApproveCommand command);
    ResultWithDomainEvent<DailySales, DailySalesEventDeclined> Decline(DailySalesDeclineCommand command);
    ResultWithDomainEvent<DailySales, DailySalesEventAwaitingApproval> SubmitForApproval(DailySalesSubmitForApprovalCommand command);
    List<DailySales> GetAll(DailySalesGetAllQuery query);

    DailySales GetById(DailySalesGetByIdQuery query);
}
