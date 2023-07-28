package ls.mestech.erp.dailysales.domain.config;

import ls.mestech.erp.dailysales.domain.model.*;
import ls.mestech.erp.dailysales.domain.model.DailyTender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;

import java.util.UUID;
@Configuration
public class DailySalesIdGenerator {
    @Bean
    BeforeConvertCallback<DailySales> beforeConvertCallbackDailySales() {

        return (dailySales) -> {
            if (dailySales.getId() == null) {
                dailySales.setId(UUID.randomUUID().toString());
            }
            for (DailyTender dailyTender:dailySales.getDailyTenders()) {
                if(dailyTender.getId() == null)
                    dailyTender.setId(UUID.randomUUID().toString());
            }
            for (DailySalesTaken dailySalesTaken:dailySales.getDailySalesTaken()) {
                if(dailySalesTaken.getId() == null)
                    dailySalesTaken.setId(UUID.randomUUID().toString());
            }
            for (DailySalesLog dailySalesLog:dailySales.getDailySalesLogs()) {
                if(dailySalesLog.getId() == null)
                    dailySalesLog.setId(UUID.randomUUID().toString());
            }
            return dailySales;
        };
    }
}
