package ls.mestech.erp.dailysales.domain.admin.tendertype.command;

import ls.mestech.erp.dailysales.domain.admin.language.exception.LanguageNotFoundException;
import ls.mestech.erp.dailysales.domain.admin.tendertype.exception.TenderTypeDuplicateException;
import ls.mestech.erp.dailysales.domain.admin.tendertype.lifecycle.State;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.model.Language;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.domain.model.TenderTypeLang;
import ls.mestech.erp.dailysales.domain.model.TenderTypeLog;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class TenderTypeCreateCommandHandler implements ICommandHandler<TenderTypeCreateCommand, TenderType> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;

    public TenderTypeCreateCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
    }

    @Override
    public TenderType Handle(TenderTypeCreateCommand tenderTypeCreateCommand) {
        tenderTypeCreateCommand.setTenderTypeCd(tenderTypeCreateCommand.getTenderTypeCd().toUpperCase());
        String tenderTypeCd = tenderTypeCreateCommand.getTenderTypeCd();

        TenderType getTenderType = unitOfWork.TenderTypeRepository().FindById(tenderTypeCd);

        if(getTenderType == null) {
            ValidateLanguagesExistence(tenderTypeCreateCommand.getTenderTypeLang());

            TenderType tenderType = modelMapper.map(tenderTypeCreateCommand, TenderType.class);

            tenderType.getTenderTypeLang().addAll(tenderTypeCreateCommand.getTenderTypeLang());

            TenderTypeLog tenderTypeLog = Log();

            tenderType.addTenderTypeLog(tenderTypeLog);

            return unitOfWork.TenderTypeRepository().Add(tenderType);
        }
        else
            throw new TenderTypeDuplicateException("Tender Type Code:" + getTenderType.getTenderTypeCd() + " already exists");
    }

    private void ValidateLanguagesExistence(Set<TenderTypeLang> tenderTypeLanguages) {
        for (TenderTypeLang tenderTypeLang: tenderTypeLanguages
             ) {
            Language language = unitOfWork.LanguageRepository().FindById(tenderTypeLang.getLanguageCd());
            if(language == null)
                throw new LanguageNotFoundException("Language Code " + tenderTypeLang.getLanguageCd() + " does not exist");
        }
    }

    private TenderTypeLog Log() {
        //Add a log
        TenderTypeLog tenderTypeLog = new TenderTypeLog();

        tenderTypeLog.setLogDt(LocalDateTime.now());
        tenderTypeLog.setAction(State.CREATED.toString());
        tenderTypeLog.setDescription("New Tender Type Created");
        tenderTypeLog.setUsername("mesadmin");

        return tenderTypeLog;
    }
}
