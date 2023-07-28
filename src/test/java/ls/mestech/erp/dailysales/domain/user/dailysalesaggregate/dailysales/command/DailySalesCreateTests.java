package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.command;

import ls.mestech.erp.dailysales.domain.main.IValidationRule;
import ls.mestech.erp.dailysales.domain.main.ICommandHandler;
import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.domain.model.DailyTender;
import ls.mestech.erp.dailysales.domain.repository.IDailySalesRepository;
import ls.mestech.erp.dailysales.domain.repository.IUnitOfWork;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesCheckDuplicates;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation.DailySalesCheckNullValues;
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
public class DailySalesCreateTests {
    @MockBean
    private IUnitOfWork unitOfWork;
    @Autowired
    private ModelMapper modelMapper;
    @MockBean
    private IDailySalesRepository dailySalesRepository;

    @Autowired
    private ICommandHandler<DailySalesCreateCommand, DailySales> dailySalesCreateCommandHandler;
    @Autowired
    private IValidationRule<DailySalesCheckNullValues> dailySalesCheckNullValuesIValidationRule;
    @Autowired
    private IValidationRule<DailySalesCheckDuplicates> dailySalesCheckDuplicatesIValidationRule;

    @Test
    @DisplayName("Should Create New Daily Sales")
    public void ShouldCreateNewDailySales(){
        //given
        HashSet<DailyTender> dailyTenders = new HashSet<DailyTender>();
        dailyTenders.add(new DailyTender("11111",BigDecimal.valueOf(10),new String()));

        DailySalesCreateCommand dailySalesCreateCommand = new DailySalesCreateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.now() ,"No comments");
        dailySalesCreateCommand.addAllDailyTenders(dailyTenders);
        DailySales dailySales = new DailySales(dailySalesCreateCommand.getFloatAmount(),
                dailySalesCreateCommand.getCapturedDt(),
                dailySalesCreateCommand.getComments(),
                dailyTenders, new HashSet<>(), new HashSet<>());
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().Add(dailySales)).thenReturn(dailySales);

        //when
        DailySales newDailySales = dailySalesCreateCommandHandler.Handle(dailySalesCreateCommand);

        //then
        Assertions.assertNotNull(newDailySales);
        //Mockito.verify(unitOfWork.DailySalesRepository(), Mockito.times(1)).Add(dailySales);
    }
    @Test
    @DisplayName("Should Create New Daily Sales With Null Daily Tenders")
    public void ShouldCreateNewDailySalesWithEmptyDailyTenders(){
        //given
        DailySalesCreateCommand dailySalesCreateCommand = new DailySalesCreateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.now() ,"No comments");
        DailySales dailySales = new DailySales(dailySalesCreateCommand.getFloatAmount(),
                dailySalesCreateCommand.getCapturedDt(),
                dailySalesCreateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().Add(dailySales)).thenReturn(dailySales);

        //when
        DailySales newDailySales = dailySalesCreateCommandHandler.Handle(dailySalesCreateCommand);

        //then
        Assertions.assertNotNull(newDailySales);
        //Mockito.verify(unitOfWork.DailySalesRepository(), Mockito.times(1)).Add(dailySales);
    }
    @Test
    @DisplayName("Should Create a New Daily Sales When Captured Date Is In The Past")
    public void ShouldCreateNewDailySalesWhenCapturedDateIsInThePast(){
        //given
        DailySalesCreateCommand dailySalesCreateCommand = new DailySalesCreateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.of(1901,1,1,1,1) ,"No comments");
        DailySales dailySales = new DailySales(dailySalesCreateCommand.getFloatAmount(),
                dailySalesCreateCommand.getCapturedDt(),
                dailySalesCreateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().Add(dailySales)).thenReturn(dailySales);

        //when
        DailySales newDailySales = dailySalesCreateCommandHandler.Handle(dailySalesCreateCommand);

        //then
        Assertions.assertNotNull(newDailySales);
    }
    @Test
    @DisplayName("Should Create a New Daily Sales When Captured Date Is The Current Date")
    public void ShouldCreateNewDailySalesWhenCapturedDateIsTheCurrentDate(){
        //given
        DailySalesCreateCommand dailySalesCreateCommand = new DailySalesCreateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.now(),"No comments");
        DailySales dailySales = new DailySales(dailySalesCreateCommand.getFloatAmount(),
                dailySalesCreateCommand.getCapturedDt(),
                dailySalesCreateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().Add(dailySales)).thenReturn(dailySales);

        //when
        DailySales newDailySales = dailySalesCreateCommandHandler.Handle(dailySalesCreateCommand);

        //then
        Assertions.assertNotNull(newDailySales);
    }
    @Test
    @DisplayName("Should Throw an Exception When Daily Sales Captured Date Is In The Future")
    public void ShouldCreateNewDailySalesWhenCapturedDateIsInTheFuture(){
        //given
        DailySalesCreateCommand dailySalesCreateCommand = new DailySalesCreateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.of(9900,1,1,1,1),"No comments");
        DailySales dailySales = new DailySales(dailySalesCreateCommand.getFloatAmount(),
                dailySalesCreateCommand.getCapturedDt(),
                dailySalesCreateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().Add(dailySales)).thenReturn(dailySales);

        //when
        //then
        Assertions.assertThrows(DailySalesCapturedDateException.class, ()->{
            dailySalesCreateCommandHandler.Handle(dailySalesCreateCommand);
        });
    }
    @Test
    @DisplayName("Should Throw Daily Sales Illegal Argument Exception When Float Amount is Null")
    public void ShouldThrowDailySalesIllegalArgumentExceptionWhenFloatAmountNull(){
        //given
        DailySalesCreateCommand dailySalesCreateCommand = new DailySalesCreateCommand("12345",null,LocalDateTime.now(),"No comments");
        DailySales dailySales = new DailySales(dailySalesCreateCommand.getFloatAmount(),
                dailySalesCreateCommand.getCapturedDt(),
                dailySalesCreateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().Add(dailySales)).thenReturn(dailySales);

        //when
        //then
        Assertions.assertThrows(DailySalesIllegalArgumentException.class, ()->{
            dailySalesCreateCommandHandler.Handle(dailySalesCreateCommand);
        });
    }
    @Test
    @DisplayName("Should Throw Daily Sales Null Argument Exception When Captured Date is Null")
    public void ShouldThrowDailySalesIllegalArgumentExceptionWhenCapturedDateNull(){
        //given
        DailySalesCreateCommand dailySalesCreateCommand = new DailySalesCreateCommand("12345",BigDecimal.valueOf(456),null,"No comments");
        DailySales dailySales = new DailySales(dailySalesCreateCommand.getFloatAmount(),
                dailySalesCreateCommand.getCapturedDt(),
                dailySalesCreateCommand.getComments(),
                null, null, null);
        dailySales.setId("12345");

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().Add(dailySales)).thenReturn(dailySales);

        //when
        //then
        Assertions.assertThrows(DailySalesIllegalArgumentException.class, ()->{
            dailySalesCreateCommandHandler.Handle(dailySalesCreateCommand);
        });
    }
    @Test
    @DisplayName("Should Throw a Duplicate Exception When Creating a Duplicate Daily Sales")
    public void ShouldThrowDuplicateExceptionWhenDuplicateDailySales(){
        //given
        HashSet<DailyTender> dailyTenders = new HashSet<DailyTender>();
        dailyTenders.add(new DailyTender("11111",BigDecimal.valueOf(10),""));

        DailySalesCreateCommand dailySalesCreateCommand = new DailySalesCreateCommand("12345",BigDecimal.valueOf(456),LocalDateTime.now() ,"No comments");
        dailySalesCreateCommand.addAllDailyTenders(dailyTenders);
        DailySales dailySales = new DailySales(dailySalesCreateCommand.getFloatAmount(),
                dailySalesCreateCommand.getCapturedDt(),
                dailySalesCreateCommand.getComments(),
                dailyTenders, new HashSet<>(), new HashSet<>());
        dailySales.setId("12345");

        ArrayList<DailySales> dailySalesList = new ArrayList<>(){{add(dailySales);}};

        Mockito.when(unitOfWork.DailySalesRepository()).thenReturn(dailySalesRepository);
        Mockito.when(unitOfWork.DailySalesRepository().Add(dailySales)).thenReturn(dailySales);
        Mockito.when(unitOfWork.DailySalesRepository().GetByCapturedDt(dailySales.getCapturedDt())).thenReturn(dailySales);

        //when
        //then
        Assertions.assertThrows(DuplicateDailySalesException.class, ()->{
            dailySalesCreateCommandHandler.Handle(dailySalesCreateCommand);
        });
    }
}
