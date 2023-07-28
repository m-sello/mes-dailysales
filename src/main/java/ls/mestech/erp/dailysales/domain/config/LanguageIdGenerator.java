package ls.mestech.erp.dailysales.domain.config;

import ls.mestech.erp.dailysales.domain.model.Language;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;

@Configuration
public class LanguageIdGenerator {

    @Bean
    BeforeConvertCallback<Language> beforeConvertCallbackLanguage() {

        return (language) -> {
            //Tender Type ID is user created
            language.setLanguageCd(language.getLanguageCd().toUpperCase());

            return language;
        };
    }
}
