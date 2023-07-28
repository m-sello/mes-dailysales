package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.query;

import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.stereotype.Service;

@Service
public class MobileMoneyGetByIdQueryHandler implements IQueryHandler<MobileMoneyGetByIdQuery, MobileMoney> {
    private final IUnitOfWork unitOfWork;

    public MobileMoneyGetByIdQueryHandler(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public MobileMoney Handle(MobileMoneyGetByIdQuery mobileMoneyGetByIdQuery) {
        return unitOfWork.MobileMoneyRepository().FindById(mobileMoneyGetByIdQuery.getId());
    }
}
