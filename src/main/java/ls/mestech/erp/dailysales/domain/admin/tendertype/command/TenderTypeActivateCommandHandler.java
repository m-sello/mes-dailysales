package ls.mestech.erp.dailysales.domain.admin.tendertype.command;

import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenderTypeActivateCommandHandler implements ICommandHandler<TenderTypeActivateCommand, TenderType> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;

    public TenderTypeActivateCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
    }

    @Override
    public TenderType Handle(TenderTypeActivateCommand tenderTypeActivateCommand) {
        TenderType tenderType = unitOfWork.TenderTypeRepository().FindById(tenderTypeActivateCommand.getTenderTypeCd());

        tenderType.setActiveFlg(true);

        return unitOfWork.TenderTypeRepository().Add(tenderType);
    }
}
