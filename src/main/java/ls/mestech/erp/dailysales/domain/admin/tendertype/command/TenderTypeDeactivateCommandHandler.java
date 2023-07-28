package ls.mestech.erp.dailysales.domain.admin.tendertype.command;

import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenderTypeDeactivateCommandHandler implements ICommandHandler<TenderTypeDeactivateCommand, TenderType> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;

    public TenderTypeDeactivateCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
    }

    @Override
    public TenderType Handle(TenderTypeDeactivateCommand tenderTypeCreateCommand) {
        TenderType tenderType = unitOfWork.TenderTypeRepository().FindById(tenderTypeCreateCommand.getTenderTypeCd());

        tenderType.setActiveFlg(false);

        return unitOfWork.TenderTypeRepository().Update(tenderType);
    }
}
