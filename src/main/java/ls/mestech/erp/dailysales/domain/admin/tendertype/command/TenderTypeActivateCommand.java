package ls.mestech.erp.dailysales.domain.admin.tendertype.command;

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
public class TenderTypeActivateCommand {
    private String tenderTypeCd;
    Boolean activeFlg;
    Set<TenderTypeLang> tenderTypeLang;
    Set<TenderTypeLog> tenderTypeLog;
}
