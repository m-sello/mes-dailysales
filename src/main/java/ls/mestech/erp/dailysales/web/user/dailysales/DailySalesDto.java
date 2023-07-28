package ls.mestech.erp.dailysales.web.user.dailysales;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DailySalesDto {
    private Long id;
    private BigDecimal floatAmount;

    private String capturedDt;
    public String comments;

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFloatAmount() {
        return floatAmount;
    }

    public void setFloatAmount(BigDecimal floatAmount) {
        this.floatAmount = floatAmount;
    }

    public String getCapturedDt() {
        return capturedDt;
    }
    public void setCapturedDt(String capturedDt) { this.capturedDt = capturedDt;}
    public Date getCapturedDateInDateFormat(){
        try{
            Date convertedDate= new SimpleDateFormat("yyyy-MM-dd").parse(this.capturedDt);

            return convertedDate;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}