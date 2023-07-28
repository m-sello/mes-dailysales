package ls.mestech.erp.dailysales.domain.services.user.impl;

import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.main.IQueryHandler;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.services.user.contracts.IMobileMoneyService;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command.MobileMoneyCreateCommand;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command.MobileMoneyDeleteCommand;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command.MobileMoneyUpdateCommand;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.query.MobileMoneyGetAllQuery;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.query.MobileMoneyGetByIdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileMoneyService implements IMobileMoneyService {
    @Autowired
    private final ICommandHandler<MobileMoneyCreateCommand, MobileMoney> mobileMoneyCreateCommandHandler;
    @Autowired
    private final ICommandHandler<MobileMoneyUpdateCommand, MobileMoney> mobileMoneyUpdateCommandHandler;
    @Autowired
    private final ICommandHandler<MobileMoneyDeleteCommand, MobileMoney> mobileMoneyDeleteCommandHandler;
    @Autowired
    private final IQueryHandler<MobileMoneyGetAllQuery, List<MobileMoney>> mobileMoneyGetAllQueryHandler;
    @Autowired
    private final IQueryHandler<MobileMoneyGetByIdQuery, MobileMoney> mobileMoneyGetByIdQueryHandler;

    public MobileMoneyService(ICommandHandler<MobileMoneyCreateCommand, MobileMoney> mobileMoneyCreateCommandHandler, ICommandHandler<MobileMoneyUpdateCommand, MobileMoney> mobileMoneyUpdateCommandHandler, ICommandHandler<MobileMoneyDeleteCommand, MobileMoney> mobileMoneyDeleteCommandHandler, IQueryHandler<MobileMoneyGetAllQuery, List<MobileMoney>> mobileMoneyGetAllQueryHandler, IQueryHandler<MobileMoneyGetByIdQuery, MobileMoney> mobileMoneyGetByIdQueryHandler) {
        this.mobileMoneyCreateCommandHandler = mobileMoneyCreateCommandHandler;
        this.mobileMoneyUpdateCommandHandler = mobileMoneyUpdateCommandHandler;
        this.mobileMoneyDeleteCommandHandler = mobileMoneyDeleteCommandHandler;
        this.mobileMoneyGetAllQueryHandler = mobileMoneyGetAllQueryHandler;
        this.mobileMoneyGetByIdQueryHandler = mobileMoneyGetByIdQueryHandler;
    }

    @Override
    public MobileMoney Create(MobileMoneyCreateCommand command) {
        return mobileMoneyCreateCommandHandler.Handle(command);
    }

    @Override
    public MobileMoney Update(MobileMoneyUpdateCommand command) {
        return mobileMoneyUpdateCommandHandler.Handle(command);
    }

    @Override
    public void Delete(MobileMoneyDeleteCommand command) {
        mobileMoneyDeleteCommandHandler.Handle(command);
    }

    @Override
    public List<MobileMoney> GetAll(MobileMoneyGetAllQuery query) {
        return mobileMoneyGetAllQueryHandler.Handle(query);
    }

    @Override
    public MobileMoney GetById(MobileMoneyGetByIdQuery query) {
        return mobileMoneyGetByIdQueryHandler.Handle(query);
    }
}
