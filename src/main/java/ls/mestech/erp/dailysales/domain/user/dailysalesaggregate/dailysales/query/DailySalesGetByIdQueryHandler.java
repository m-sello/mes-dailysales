package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.query;

import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.stereotype.Service;

@Service
public class DailySalesGetByIdQueryHandler  implements IQueryHandler<DailySalesGetByIdQuery, DailySales> {
    private final IUnitOfWork unitOfWork;

    public DailySalesGetByIdQueryHandler(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public DailySales Handle(DailySalesGetByIdQuery dailySalesGetByIdQuery) {
        return unitOfWork.DailySalesRepository().FindById(dailySalesGetByIdQuery.getId());
    }
}
