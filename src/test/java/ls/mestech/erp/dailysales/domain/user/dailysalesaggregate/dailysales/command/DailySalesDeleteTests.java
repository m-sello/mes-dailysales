package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command;

import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.model.DailyTender;
import ls.mestech.erp.dailysales.domain.repository.IDailySalesRepository;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesCheckDuplicates;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesCheckNullValues;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesValidateCapturedDate;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesValidateDuplicateByDateCaptured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

@SpringBootTest
public class DailySalesDeleteTests {
    @MockBean
    private IUnitOfWork unitOfWork;
    @Autowired
    private ModelMapper modelMapper;
    @MockBean
    private IDailySalesRepository dailySalesRepository;

    @Autowired
    private ICommandHandler<DailySalesDeleteCommand, DailySales> dailySalesDeleteCommandCommandHandler;
    @Autowired
    private IValidationRule<DailySalesCheckNullValues> dailySalesCheckNullValuesIValidationRule;
    @Autowired
    private IValidationRule<DailySalesCheckDuplicates> dailySalesCheckDuplicatesIValidationRule;
    @Autowired
    private IValidationRule<DailySalesValidateCapturedDate> dailySalesValidateCapturedDateIValidationRule;
    @Autowired
    private IValidationRule<DailySalesValidateDuplicateByDateCaptured> dailySalesValidateDuplicateByDateCapturedIValidationRule;
    @Test
    @DisplayName("Should Delete Daily Sales")
    public void ShouldDeleteDailySales(){
        //given
        HashSet<DailyTender> dailyTenders = new HashSet<DailyTender>();
        dailyTenders.add(new DailyTender("11111",BigDecimal.valueOf(10),""));

        DailySalesDeleteCommand dailySalesDeleteCommand = new DailySalesDeleteCommand("12345",BigDecimal.valueOf(456),LocalDateTime.now() ,"No comments");
        DailySales dailySales = new DailySales(dailySalesDeleteCommand.getFloatAmount(),dailySalesDeleteCommand.getCapturedDt(),dailySalesDeleteCommand.getComments(),
                dailyTenders, new HashSet<>(), new HashSet<>());
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().FindById(dailySalesDeleteCommand.getId())).thenReturn(dailySales);

        //when
        DailySales newDailySales = dailySalesDeleteCommandCommandHandler.Handle(dailySalesDeleteCommand);

        //then
        Assertions.assertNotNull(newDailySales);
        Mockito.verify(unitOfWork.DailySalesRepository(), Mockito.times(1)).Remove(dailySales);
    }
}
