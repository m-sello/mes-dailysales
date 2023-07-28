package ls.mestech.erp.dailysales.domain.admin.tendertype.command;

import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenderTypeDeleteCommandHandler implements ICommandHandler<TenderTypeDeleteCommand, TenderType> {
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;

    public TenderTypeDeleteCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
    }

    @Override
    public TenderType Handle(TenderTypeDeleteCommand tenderTypeDeleteCommand) {
        TenderType tenderType = unitOfWork.TenderTypeRepository().FindById(tenderTypeDeleteCommand.getTenderTypeCd());

        if (tenderType != null) {

            unitOfWork.TenderTypeRepository().Remove(tenderType);
        }
        else {
            throw new ObjectNotFoundException((Object) tenderTypeDeleteCommand.getTenderTypeCd(), "Tender Type Code not found");
        }

        return tenderType;
    }
}
