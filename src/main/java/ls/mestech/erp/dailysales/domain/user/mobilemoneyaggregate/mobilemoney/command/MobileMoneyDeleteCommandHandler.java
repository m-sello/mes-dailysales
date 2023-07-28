package ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.command;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.model.MobileMoney;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.mobilemoneyaggregate.mobilemoney.exception.MobileMoneyNullException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileMoneyDeleteCommandHandler implements ICommandHandler<MobileMoneyDeleteCommand, MobileMoney>{
    @Autowired
    private final IUnitOfWork unitOfWork;
    @Autowired
    private final ModelMapper modelMapper;
    public MobileMoneyDeleteCommandHandler(IUnitOfWork unitOfWork, ModelMapper modelMapper) {
        this.unitOfWork = unitOfWork;
        this.modelMapper = modelMapper;
    }
    @Override
    public MobileMoney Handle(@NotNull MobileMoneyDeleteCommand mobileMoneyDeleteCommand) {

        MobileMoney getMobileMoney = unitOfWork.MobileMoneyRepository().FindById(mobileMoneyDeleteCommand.getId());

        if (getMobileMoney != null) {

            unitOfWork.MobileMoneyRepository().Remove(getMobileMoney);

            return new MobileMoney();
        }

        throw new MobileMoneyNullException("Mobile Money cannot be null for update to be made");
    }
}
