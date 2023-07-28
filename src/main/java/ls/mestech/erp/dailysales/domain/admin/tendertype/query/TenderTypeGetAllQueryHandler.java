package ls.mestech.erp.dailysales.domain.admin.tendertype.query;

import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.domain.repository.ILanguageRepository;
import ls.mestech.erp.dailysales.domain.repository.ITenderTypeRepository;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TenderTypeGetAllQueryHandler implements IQueryHandler<TenderTypeGetAllQuery, List<TenderType>> {
    private final IUnitOfWork unitOfWork;

    public TenderTypeGetAllQueryHandler(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public List<TenderType> Handle(TenderTypeGetAllQuery tenderTypeGetAllQuery) {

        List<TenderType> target = new ArrayList<>();

        unitOfWork.TenderTypeRepository().FindAll().forEach(target::add);

        return target;
    }
}
