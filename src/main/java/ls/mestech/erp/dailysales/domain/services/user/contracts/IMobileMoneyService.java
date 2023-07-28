package ls.mestech.erp.dailysales.domain.services.user.contracts;

import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command.MobileMoneyCreateCommand;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command.MobileMoneyDeleteCommand;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command.MobileMoneyUpdateCommand;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.query.MobileMoneyGetAllQuery;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.query.MobileMoneyGetByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMobileMoneyService {
    MobileMoney Create(MobileMoneyCreateCommand command);
    MobileMoney Update(MobileMoneyUpdateCommand command);
    void Delete(MobileMoneyDeleteCommand command);
    //MobileMoney Approve(MobileMoneyApproveCommand command);
    //MobileMoney Decline(MobileMoneyDeclineCommand command);
    //MobileMoney SubmitForApproval(MobileMoneySubmitForApprovalCommand command);
    List<MobileMoney> GetAll(MobileMoneyGetAllQuery query);
    MobileMoney GetById(MobileMoneyGetByIdQuery query);
}
