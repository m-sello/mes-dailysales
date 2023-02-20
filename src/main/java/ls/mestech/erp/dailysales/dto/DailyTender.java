package ls.mestech.erp.dailysales.dto;


import java.math.BigDecimal;

public class DailyTender {
    private Long id;

    private BigDecimal amount;

    private TenderType tenderTypeTenderType;

    private DailySale dailySales;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TenderType getTenderTypeTenderType() {
        return tenderTypeTenderType;
    }

    public void setTenderTypeTenderType(TenderType tenderTypeTenderType) {
        this.tenderTypeTenderType = tenderTypeTenderType;
    }

    public DailySale getDailySales() {
        return dailySales;
    }

    public void setDailySales(DailySale dailySales) {
        this.dailySales = dailySales;
    }

}