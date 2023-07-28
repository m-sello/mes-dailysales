package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.query;

import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DailySalesGetAllQueryHandler implements IQueryHandler<DailySalesGetAllQuery, List<DailySales>> {
    private final IUnitOfWork unitOfWork;

    public DailySalesGetAllQueryHandler(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public List<DailySales> Handle(DailySalesGetAllQuery dailySalesGetAllQuery) {

        List<DailySales> target = new ArrayList<>();

        unitOfWork.DailySalesRepository().FindAll().forEach(target::add);

        return target;
    }
}
