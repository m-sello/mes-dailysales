package ls.mestech.erp.dailysales.domain.admin.language.query;

import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.repository.ILanguageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LanguageGetAllQueryHandler implements IQueryHandler<LanguageGetAllQuery, List<Language>> {
    private final ILanguageRepository languageRepository;

    public LanguageGetAllQueryHandler(ILanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> Handle(LanguageGetAllQuery languageGetAllQuery) {

        List<Language> target = new ArrayList<>();

        languageRepository.FindAll().forEach(target::add);

        return target;
    }
}
