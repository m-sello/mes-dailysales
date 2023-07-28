package ls.mestech.erp.dailysales.domain.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table(name = "daily_tenders")
public class DailyTender {
    @Id
    @Column("id")
    String id;

    @Column("amount")
    BigDecimal amount;

    @Column("tender_type_cd")
    private String tenderTypeCd;
    @Transient
    DailySales dailySales;
    @PersistenceCreator
    public DailyTender(String id, BigDecimal amount, String tenderTypeCd){
        this.id = id;
        this.amount = amount;
        this.tenderTypeCd = tenderTypeCd;
    }
    public DailyTender(){
    }
    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTenderTypeCd() {
        return tenderTypeCd;
    }

    public void setTenderTypeCd(String tenderTypeCd) {
        this.tenderTypeCd = tenderTypeCd;
    }

    public DailySales getDailySales() {
        return dailySales;
    }

}