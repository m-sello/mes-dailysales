package ls.mestech.erp.dailysales.domain.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table(name = "mobile_electricity_airtime_taken")
public class MobileElectricityAirtimeTaken {
    @Id
    @Column("id")
    String id;
    @Column("amount")
    BigDecimal amount;
    @Column("comments")
    String comments;
    @Transient
    MobileElectricityAirtime mobileElectricityAirtime;
    @PersistenceCreator
    public MobileElectricityAirtimeTaken(String id, BigDecimal amount, String comments) {
        this.id = id;
        this.amount = amount;
        this.comments = comments;
    }
    public MobileElectricityAirtimeTaken(){
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public MobileElectricityAirtime getMobileElectricityAirtime() {
        return mobileElectricityAirtime;
    }
}