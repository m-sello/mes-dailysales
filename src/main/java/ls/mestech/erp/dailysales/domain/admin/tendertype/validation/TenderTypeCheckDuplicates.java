package ls.mestech.erp.dailysales.domain.admin.tendertype.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ls.mestech.erp.dailysales.domain.model.TenderTypeLang;
import ls.mestech.erp.dailysales.domain.model.TenderTypeLog;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TenderTypeCheckDuplicates {
    private String tenderTypeCd;
    Boolean activeFlg;
    TenderTypeLang tenderTypeLang;
    Set<TenderTypeLog> tenderTypeLog;
}
