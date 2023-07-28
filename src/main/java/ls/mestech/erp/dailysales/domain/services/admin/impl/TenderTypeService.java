package ls.mestech.erp.dailysales.domain.services.admin.impl;

import ls.mestech.erp.dailysales.domain.admin.tendertype.command.*;
import ls.mestech.erp.dailysales.domain.admin.tendertype.query.TenderTypeGetAllQuery;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.model.TenderType;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.services.admin.contracts.ITenderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenderTypeService implements ITenderTypeService {

    @Autowired
    private final ICommandHandler<TenderTypeCreateCommand, TenderType> TenderTypeCreateCommandHandler;
    @Autowired
    private final ICommandHandler<TenderTypeActivateCommand, TenderType> TenderTypeActivateCommandHandler;
    @Autowired
    private final ICommandHandler<TenderTypeUpdateCommand, TenderType> TenderTypeUpdateCommandHandler;
    @Autowired
    private final ICommandHandler<TenderTypeDeleteCommand, TenderType> TenderTypeDeleteCommandHandler;
    @Autowired
    private final ICommandHandler<TenderTypeDeactivateCommand, TenderType> TenderTypeDeactivateCommandHandler;
    @Autowired
    private final IQueryHandler<TenderTypeGetAllQuery, List<TenderType>> TenderTypeGetAllQueryQueryHandler;
    @Autowired
    private final IUnitOfWork unitOfWork;

    public TenderTypeService(ICommandHandler<TenderTypeCreateCommand, TenderType> TenderTypeCreateCommandHandler,
                           ICommandHandler<TenderTypeActivateCommand, TenderType> TenderTypeActivateCommandHandler,
                           ICommandHandler<TenderTypeUpdateCommand, TenderType> TenderTypeUpdateCommandHandler,
                           ICommandHandler<TenderTypeDeleteCommand, TenderType> TenderTypeDeleteCommandHandler,
                           ICommandHandler<TenderTypeDeactivateCommand, TenderType> TenderTypeDeactivateCommandHandler,
                           IQueryHandler<TenderTypeGetAllQuery, List<TenderType>> TenderTypeGetAllQueryQueryHandler, IUnitOfWork unitOfWork) {
        this.TenderTypeCreateCommandHandler = TenderTypeCreateCommandHandler;
        this.TenderTypeActivateCommandHandler = TenderTypeActivateCommandHandler;
        this.TenderTypeUpdateCommandHandler = TenderTypeUpdateCommandHandler;
        this.TenderTypeDeleteCommandHandler = TenderTypeDeleteCommandHandler;
        this.TenderTypeDeactivateCommandHandler = TenderTypeDeactivateCommandHandler;
        this.TenderTypeGetAllQueryQueryHandler = TenderTypeGetAllQueryQueryHandler;
        this.unitOfWork = unitOfWork;
    }


    @Override
    public TenderType Create(TenderTypeCreateCommand command) {
        return TenderTypeCreateCommandHandler.Handle(command);
    }

    @Override
    public TenderType Update(TenderTypeUpdateCommand command) {
        return TenderTypeUpdateCommandHandler.Handle(command);
    }

    @Override
    public TenderType Delete(TenderTypeDeleteCommand command) {
        return TenderTypeDeleteCommandHandler.Handle(command);
    }

    @Override
    public TenderType Activate(TenderTypeActivateCommand command) {
        return TenderTypeActivateCommandHandler.Handle(command);
    }

    @Override
    public TenderType Deactivate(TenderTypeDeactivateCommand command) {
        return TenderTypeDeactivateCommandHandler.Handle(command);
    }

    @Override
    public List<TenderType> GetAll(TenderTypeGetAllQuery query) {
        return TenderTypeGetAllQueryQueryHandler.Handle(query);
    }

    @Override
    public TenderType GetByCode(String TenderTypeCd) {
        return unitOfWork.TenderTypeRepository().FindById(TenderTypeCd);
    }
}
