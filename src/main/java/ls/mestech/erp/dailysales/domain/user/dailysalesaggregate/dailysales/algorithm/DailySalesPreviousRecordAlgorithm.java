package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.algorithm;

import jakarta.validation.constraints.NotNull;
import ls.mestech.erp.dailysales.domain.main.IAlgorithm;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DailySalesPreviousRecordAlgorithm implements IAlgorithm<DailySalesPreviousRecord, DailySales> {
    @Autowired
    private final IUnitOfWork unitOfWork;

    public DailySalesPreviousRecordAlgorithm(IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public DailySales Invoke(@NotNull DailySalesPreviousRecord input) {

        DailySales dailySales = unitOfWork.DailySalesRepository().FindById(input.getDailySalesId());

        LocalDateTime previousDate = dailySales.getCapturedDt().minusDays(1);

        DailySales previousDailySales = unitOfWork.DailySalesRepository().GetByCapturedDt(previousDate);

        return previousDailySales;
    }
}
