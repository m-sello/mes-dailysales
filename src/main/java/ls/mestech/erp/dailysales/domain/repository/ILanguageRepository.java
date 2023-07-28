package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.model.Language;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ILanguageRepository  extends IRepository<Language,String> {
}
