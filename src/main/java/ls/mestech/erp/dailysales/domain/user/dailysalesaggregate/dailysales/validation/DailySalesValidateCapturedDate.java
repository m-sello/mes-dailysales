package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.validation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class DailySalesValidateCapturedDate {
    private String id;
    private LocalDateTime capturedDt;
    private Boolean updateFlg;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getUpdateFlg() {
        return updateFlg;
    }

    public void setUpdateFlg(Boolean updateFlg) {
        this.updateFlg = updateFlg;
    }

    public LocalDateTime getCapturedDt() {
        return capturedDt;
    }
    public void setCapturedDt(LocalDateTime capturedDt) {
        this.capturedDt = capturedDt;
    }
}

