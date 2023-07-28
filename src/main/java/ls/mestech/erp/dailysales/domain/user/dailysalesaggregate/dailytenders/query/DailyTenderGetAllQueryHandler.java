package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailytenders.query;

import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DailyTenderGetAllQueryHandler implements IQueryHandler<DailyTenderGetAllQuery, List<TenderType>> {
    private final IUnitOfWork unitOfWork;

    public DailyTenderGetAllQueryHandler(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public List<TenderType> Handle(DailyTenderGetAllQuery tenderTypeGetAllQuery) {

        List<TenderType> target = new ArrayList<>();

        unitOfWork.TenderTypeRepository().FindAll().forEach(target::add);

        return target;
    }
}
