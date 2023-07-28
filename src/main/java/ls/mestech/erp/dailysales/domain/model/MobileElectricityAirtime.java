package ls.mestech.erp.dailysales.domain.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Table(name = "mobile_electricity_airtime")
public class MobileElectricityAirtime {
    @Id
    @Column("id")
    String id;
    @Column("balance_amount")
    BigDecimal balanceAmount;
    @MappedCollection(idColumn = "mobile_electricity_airtime_id", keyColumn = "id")
    Set<MobileElectricityAirtimeTaken> mobileElectricityAirtimeTaken = new HashSet<>();
    @Transient
    MobileMoney mobileMoney;
    @PersistenceCreator
    public MobileElectricityAirtime(String id, BigDecimal balanceAmount, Set<MobileElectricityAirtimeTaken> mobileElectricityAirtimeTaken) {
        this.id = id;
        this.balanceAmount = balanceAmount;
        if(mobileElectricityAirtimeTaken != null) this.mobileElectricityAirtimeTaken.addAll(mobileElectricityAirtimeTaken);
    }
    public MobileElectricityAirtime(){
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

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Set<MobileElectricityAirtimeTaken> getMobileElectricityAirtimeTaken() {
        return mobileElectricityAirtimeTaken;
    }

    public MobileMoney getMobileMoney() {
        return mobileMoney;
    }
}