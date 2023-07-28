package ls.mestech.erp.dailysales.domain.config;

import ls.mestech.erp.dailysales.domain.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;

import java.util.UUID;

@Configuration
public class TenderTypeIdGenerator {

    @Bean
    BeforeConvertCallback<TenderType> beforeConvertCallbackTenderType() {

        return (tenderType) -> {
            //Tender Type ID is user created
            tenderType.setTenderTypeCd(tenderType.getTenderTypeCd().toUpperCase());

            for (TenderTypeLog tenderTypeLog:tenderType.getTenderTypeLog()) {
                if(String.valueOf(tenderTypeLog.getId()).isBlank())
                    tenderTypeLog.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            }
            return tenderType;
        };
    }
}
