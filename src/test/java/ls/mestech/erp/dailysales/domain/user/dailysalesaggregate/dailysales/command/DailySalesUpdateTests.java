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
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesCapturedDateException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DailySalesIllegalArgumentException;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.DuplicateDailySalesException;
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
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootTest
public class DailySalesUpdateTests {
    @MockBean
    private IUnitOfWork unitOfWork;
    @Autowired
    private ModelMapper modelMapper;
    @MockBean
    private IDailySalesRepository dailySalesRepository;

    @Autowired
    private ICommandHandler<DailySalesUpdateCommand, DailySales> dailySalesUpdateCommandHandler;
    @Autowired
    private IValidationRule<DailySalesCheckNullValues> dailySalesCheckNullValuesIValidationRule;
    @Autowired
    private IValidationRule<DailySalesCheckDuplicates> dailySalesCheckDuplicatesIValidationRule;
    @Autowired
    private IValidationRule<DailySalesValidateCapturedDate> dailySalesValidateCapturedDateIValidationRule;
    @Autowired
    private IValidationRule<DailySalesValidateDuplicateByDateCaptured> dailySalesValidateDuplicateByDateCapturedIValidationRule;
    @Test
    @DisplayName("Should Update Daily Sales")
    public void ShouldUpdateDailySales(){
        //given
        HashSet<DailyTender> dailyTenders = new HashSet<DailyTender>();
        dailyTenders.add(new DailyTender("11111",BigDecimal.valueOf(10),"ENGLI"));

        DailySalesUpdateCommand dailySalesUpdateCommand = new DailySalesUpdateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.now() ,"No comments");
        dailySalesUpdateCommand.addAllDailyTenders(dailyTenders);
        DailySales dailySales = new DailySales(dailySalesUpdateCommand.getFloatAmount(),dailySalesUpdateCommand.getCapturedDt(),dailySalesUpdateCommand.getComments(),
                dailyTenders, new HashSet<>(), new HashSet<>());
        dailySales.setId("12345");

        DailySales updatedDailySales = new DailySales(dailySales.getFloatAmount().add(BigDecimal.valueOf(10000)),dailySales.getCapturedDt(),dailySales.getComments(),
                dailyTenders, new HashSet<>(), new HashSet<>());
        updatedDailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().FindById(dailySalesUpdateCommand.getId())).thenReturn(dailySales);
        Mockito.when(unitOfWork.DailySalesRepository().Update(dailySales)).thenReturn(updatedDailySales);

        //when
        DailySales newDailySales = dailySalesUpdateCommandHandler.Handle(dailySalesUpdateCommand);

        //then
        Assertions.assertNotNull(newDailySales);
        //Mockito.verify(unitOfWork.DailySalesRepository(), Mockito.times(1)).Add(dailySales);
    }
    @Test
    @DisplayName("Should Update Daily Sales With Null Daily Tenders")
    public void ShouldUpdateDailySalesWithNullDailyTenders(){
        //given
        HashSet<DailyTender> dailyTenders = null;
        //dailyTenders.add(new DailyTender(Long.valueOf(11111),BigDecimal.valueOf(10),""));

        DailySalesUpdateCommand dailySalesUpdateCommand = new DailySalesUpdateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.now() ,"No comments");
        DailySales dailySales = new DailySales(dailySalesUpdateCommand.getFloatAmount(),
                dailySalesUpdateCommand.getCapturedDt(),
                dailySalesUpdateCommand.getComments(),
                dailyTenders, null, null);
        dailySales.setId("12345");

        DailySales updatedDailySales = new DailySales(dailySales.getFloatAmount().add(BigDecimal.valueOf(10000)),dailySales.getCapturedDt(),dailySales.getComments(),
                new HashSet<>(), new HashSet<>(), new HashSet<>());
        updatedDailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().FindById(dailySalesUpdateCommand.getId())).thenReturn(dailySales);
        Mockito.when(unitOfWork.DailySalesRepository().Update(dailySales)).thenReturn(updatedDailySales);

        //when
        DailySales newDailySales = dailySalesUpdateCommandHandler.Handle(dailySalesUpdateCommand);

        //then
        Assertions.assertNotNull(newDailySales);
        Assertions.assertEquals(0, newDailySales.getDailyTenders().size());
        //Mockito.verify(unitOfWork.DailySalesRepository(), Mockito.times(1)).Add(dailySales);
    }
    @Test
    @DisplayName("Should Update Daily Sales When Captured Date Is In The Past")
    public void ShouldUpdateDailySalesWhenCapturedDateIsInThePast(){
        //given
        DailySalesUpdateCommand dailySalesUpdateCommand = new DailySalesUpdateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.of(1901,1,1,1,1) ,"No comments");
        DailySales dailySales = new DailySales(dailySalesUpdateCommand.getFloatAmount(),
                dailySalesUpdateCommand.getCapturedDt(),
                dailySalesUpdateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().FindById(dailySalesUpdateCommand.getId())).thenReturn(dailySales);
        Mockito.when(unitOfWork.DailySalesRepository().Update(dailySales)).thenReturn(dailySales);

        //when
        DailySales newDailySales = dailySalesUpdateCommandHandler.Handle(dailySalesUpdateCommand);

        //then
        Assertions.assertNotNull(newDailySales);
    }
    @Test
    @DisplayName("Should Update Daily Sales When Captured Date Is The Current Date")
    public void ShouldUpdateDailySalesWhenCapturedDateIsTheCurrentDate(){
        //given
        DailySalesUpdateCommand dailySalesUpdateCommand = new DailySalesUpdateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.now(),"No comments");
        DailySales dailySales = new DailySales(dailySalesUpdateCommand.getFloatAmount(),
                dailySalesUpdateCommand.getCapturedDt(),
                dailySalesUpdateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().FindById(dailySalesUpdateCommand.getId())).thenReturn(dailySales);
        Mockito.when(unitOfWork.DailySalesRepository().Update(dailySales)).thenReturn(dailySales);

        //when
        DailySales newDailySales = dailySalesUpdateCommandHandler.Handle(dailySalesUpdateCommand);

        //then
        Assertions.assertNotNull(newDailySales);
    }
    @Test
    @DisplayName("Should Throw an Exception When Daily Sales Captured Date Is In The Future")
    public void ShouldThrowExceptionWhenDailySalesCapturedDateIsInTheFuture(){
        //given
        DailySalesUpdateCommand dailySalesUpdateCommand = new DailySalesUpdateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.of(9900,1,1,1,1),"No comments");
        DailySales dailySales = new DailySales(dailySalesUpdateCommand.getFloatAmount(),
                dailySalesUpdateCommand.getCapturedDt(),
                dailySalesUpdateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().FindById(dailySalesUpdateCommand.getId())).thenReturn(dailySales);
        Mockito.when(unitOfWork.DailySalesRepository().Update(dailySales)).thenReturn(dailySales);

        //when
        //then
        Assertions.assertThrows(DailySalesCapturedDateException.class, ()->{
            dailySalesUpdateCommandHandler.Handle(dailySalesUpdateCommand);
        });
    }
    @Test
    @DisplayName("Should Throw Daily Sales Illegal Argument Exception When Float Amount is Null")
    public void ShouldThrowDailySalesIllegalArgumentExceptionWhenFloatAmountNull(){
        //given
        DailySalesUpdateCommand dailySalesUpdateCommand = new DailySalesUpdateCommand("12345",null,LocalDateTime.now(),"No comments");
        DailySales dailySales = new DailySales(dailySalesUpdateCommand.getFloatAmount(),
                dailySalesUpdateCommand.getCapturedDt(),
                dailySalesUpdateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().FindById(dailySalesUpdateCommand.getId())).thenReturn(dailySales);
        Mockito.when(unitOfWork.DailySalesRepository().Update(dailySales)).thenReturn(dailySales);

        //when
        //then
        Assertions.assertThrows(DailySalesIllegalArgumentException.class, ()->{
            dailySalesUpdateCommandHandler.Handle(dailySalesUpdateCommand);
        });
    }
    @Test
    @DisplayName("Should Throw Daily Sales Null Argument Exception When Captured Date is Null")
    public void ShouldThrowDailySalesIllegalArgumentExceptionWhenCapturedDateNull(){
        //given
        DailySalesUpdateCommand dailySalesUpdateCommand = new DailySalesUpdateCommand("12345",BigDecimal.valueOf(456),null,"No comments");
        DailySales dailySales = new DailySales(dailySalesUpdateCommand.getFloatAmount(),
                dailySalesUpdateCommand.getCapturedDt(),
                dailySalesUpdateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().FindById(dailySalesUpdateCommand.getId())).thenReturn(dailySales);
        Mockito.when(unitOfWork.DailySalesRepository().Update(dailySales)).thenReturn(dailySales);

        //when
        //then
        Assertions.assertThrows(DailySalesIllegalArgumentException.class, ()->{
            dailySalesUpdateCommandHandler.Handle(dailySalesUpdateCommand);
        });
    }
    @Test
    @DisplayName("Should Throw a Duplicate Exception When Creating a Duplicate Daily Sales")
    public void ShouldThrowDuplicateExceptionWhenDuplicateDailySales(){
        //given
        HashSet<DailyTender> dailyTenders = new HashSet<DailyTender>();
        dailyTenders.add(new DailyTender("11111",BigDecimal.valueOf(10),"ENGLI"));

        DailySalesUpdateCommand dailySalesUpdateCommand = new DailySalesUpdateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.now() ,"No comments");
        dailySalesUpdateCommand.addAllDailyTenders(dailyTenders);
        DailySales dailySales = new DailySales(dailySalesUpdateCommand.getFloatAmount(),
                dailySalesUpdateCommand.getCapturedDt(),
                dailySalesUpdateCommand.getComments(),
                dailyTenders, new HashSet<>(),new HashSet<>());
        dailySales.setId("12345");

        DailySales updatedDailySales = new DailySales(dailySales.getFloatAmount().add(BigDecimal.valueOf(10000)),dailySales.getCapturedDt(),dailySales.getComments(),
                new HashSet<>(), new HashSet<>(),new HashSet<>());
        updatedDailySales.setId("67890");

        ArrayList<DailySales> dailySalesList = new ArrayList<>(){{add(updatedDailySales);}};

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().FindById(dailySalesUpdateCommand.getId())).thenReturn(dailySales);
        Mockito.when(unitOfWork.DailySalesRepository().FindAll()).thenReturn(dailySalesList);
        Mockito.when(unitOfWork.DailySalesRepository().Add(dailySales)).thenReturn(dailySales);
        Mockito.when(unitOfWork.DailySalesRepository().GetByCapturedDt(dailySales.getCapturedDt())).thenReturn(dailySales);

        //when
        //then
        Assertions.assertThrows(DuplicateDailySalesException.class, ()->{
            dailySalesUpdateCommandHandler.Handle(dailySalesUpdateCommand);
        });
    }
}
