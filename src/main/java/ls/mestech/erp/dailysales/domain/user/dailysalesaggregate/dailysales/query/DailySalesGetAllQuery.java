package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.query;

import ls.mestech.erp.dailysales.domain.main.IQuery;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DailySalesGetAllQuery implements IQuery<List<DailySales>> {
}
