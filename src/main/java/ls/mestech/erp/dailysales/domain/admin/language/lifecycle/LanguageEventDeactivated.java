package ls.mestech.erp.dailysales.domain.admin.language.lifecycle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LanguageEventDeactivated implements LanguageEvent {
    private String language_cd;
    private String name;
}
