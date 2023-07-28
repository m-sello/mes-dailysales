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

@Table(name = "mobile_cashplus")
public class MobileCashplus {
    @Id
    @Column("id")
    String id;
    @Column("balance_amount")
    BigDecimal balanceAmount;
    @Column("commission_amount")
    BigDecimal commissionAmount;
    @MappedCollection(idColumn = "mobile_cashplus_id", keyColumn = "id")
    Set<MobileCashplusTaken> mobileCashplusTaken = new HashSet<>();
    @Transient
    MobileMoney mobileMoney;
    @PersistenceCreator
    public MobileCashplus(String id, BigDecimal balanceAmount, BigDecimal commissionAmount, Set<MobileCashplusTaken> mobileCashplusTaken) {
        this.id = id;
        this.balanceAmount = balanceAmount;
        this.commissionAmount = commissionAmount;
        if(mobileCashplusTaken != null) this.mobileCashplusTaken.addAll(mobileCashplusTaken);
    }
    public MobileCashplus(){
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

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Set<MobileCashplusTaken> getMobileCashplusTaken() {
        return mobileCashplusTaken;
    }
    public MobileMoney getMobileMoney() {
        return mobileMoney;
    }
}