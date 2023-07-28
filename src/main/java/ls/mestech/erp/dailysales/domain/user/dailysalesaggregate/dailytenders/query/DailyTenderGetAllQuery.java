package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailytenders.query;

import ls.mestech.erp.dailysales.domain.main.IQuery;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DailyTenderGetAllQuery implements IQuery<List<TenderType>> {
}
