package ls.mestech.erp.dailysales.domain.config;

import ls.mestech.erp.dailysales.domain.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;

import java.util.UUID;

@Configuration
public class MobileMoneyIdGenerator {
    @Bean
    BeforeConvertCallback<MobileMoney> beforeConvertCallbackMobileMoney() {

        return (mobileMoney) -> {
            if (mobileMoney.getId() == null) {
                mobileMoney.setId(UUID.randomUUID().toString());
            }

            if(mobileMoney.getMobileCashplus() != null) {
                if (mobileMoney.getMobileCashplus().getId() == null) {
                    mobileMoney.getMobileCashplus().setId(UUID.randomUUID().toString());
                }

                for (MobileCashplusTaken mobileCashplusTaken:mobileMoney.getMobileCashplus().getMobileCashplusTaken()) {
                    if(mobileCashplusTaken.getId() == null)
                        mobileCashplusTaken.setId(UUID.randomUUID().toString());
                }
            }
            if(mobileMoney.getMobileEcocash() != null) {
                if (mobileMoney.getMobileEcocash().getId() == null) {
                    mobileMoney.getMobileEcocash().setId(UUID.randomUUID().toString());
                }

                for (MobileEcocashTaken mobileEcocashTaken:mobileMoney.getMobileEcocash().getMobileEcocashTaken()) {
                    if(mobileEcocashTaken.getId() == null)
                        mobileEcocashTaken.setId(UUID.randomUUID().toString());
                }
            }
            if(mobileMoney.getMobileElectricityAirtime() != null) {
                if (mobileMoney.getMobileElectricityAirtime().getId() == null) {
                    mobileMoney.getMobileElectricityAirtime().setId(UUID.randomUUID().toString());
                }

                for (MobileElectricityAirtimeTaken mobileElectricityAirtimeTaken:mobileMoney.getMobileElectricityAirtime().getMobileElectricityAirtimeTaken()) {
                    if(mobileElectricityAirtimeTaken.getId() == null)
                        mobileElectricityAirtimeTaken.setId(UUID.randomUUID().toString());
                }
            }
            if(mobileMoney.getMobileMpesa() != null) {
                if (mobileMoney.getMobileMpesa().getId() == null) {
                    mobileMoney.getMobileMpesa().setId(UUID.randomUUID().toString());
                }

                for (MobileMpesaTaken mobileMpesaTaken:mobileMoney.getMobileMpesa().getMobileMpesaTaken()) {
                    if(mobileMpesaTaken.getId() == null)
                        mobileMpesaTaken.setId(UUID.randomUUID().toString());
                }
            }

            for (MobileMoneyLog mobileMoneyLog:mobileMoney.getMobileMoneyLogs()) {
                if(mobileMoneyLog.getId() == null)
                    mobileMoneyLog.setId(UUID.randomUUID().toString());
            }
            return mobileMoney;
        };
    }
}
