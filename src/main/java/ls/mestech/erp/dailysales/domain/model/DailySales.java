package ls.mestech.erp.dailysales.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "daily_sales")
public class DailySales{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "float_amount")
    private BigDecimal floatAmount;

    @Column(name = "captured_dt")
    private Instant capturedDt;

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

    public Instant getCapturedDt() {
        return capturedDt;
    }

    public void setCapturedDt(Instant capturedDt) {
        this.capturedDt = capturedDt;
    }

}