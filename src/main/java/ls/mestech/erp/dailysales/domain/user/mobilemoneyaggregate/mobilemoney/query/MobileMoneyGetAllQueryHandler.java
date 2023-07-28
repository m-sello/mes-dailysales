package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.query;

import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MobileMoneyGetAllQueryHandler implements IQueryHandler<MobileMoneyGetAllQuery, List<MobileMoney>> {
    private final IUnitOfWork unitOfWork;

    public MobileMoneyGetAllQueryHandler(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public List<MobileMoney> Handle(MobileMoneyGetAllQuery mobileMoneyGetAllQuery) {

        List<MobileMoney> target = new ArrayList<>();

        unitOfWork.MobileMoneyRepository().FindAll().forEach(target::add);

        return target;
    }
}
