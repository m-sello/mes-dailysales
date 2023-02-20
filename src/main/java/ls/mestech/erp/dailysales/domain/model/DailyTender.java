package ls.mestech.erp.dailysales.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "daily_tenders")
public class DailyTender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tender_type_tender_type", nullable = false)
    private TenderType tenderTypeTenderType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "daily_sales_id", nullable = false)
    private DailySales dailySales;

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

    public DailySales getDailySales() {
        return dailySales;
    }

    public void setDailySales(DailySales dailySales) {
        this.dailySales = dailySales;
    }

}