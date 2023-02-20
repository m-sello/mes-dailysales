package ls.mestech.erp.dailysales.domain.dailysales.action.query;

import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IDailySaleRepository;

import java.util.List;

public class DailySalesGetAllQueryHandler implements IQueryHandler<DailySalesGetAllQuery, List<DailySales>> {
    private final IDailySaleRepository dailySaleRepository;

    public DailySalesGetAllQueryHandler(IDailySaleRepository dailySaleRepository) {
        this.dailySaleRepository = dailySaleRepository;
    }

    @Override
    public List<DailySales> Handle(DailySalesGetAllQuery dailySalesGetAllQuery) {
        return dailySaleRepository.findAll();
    }
}
