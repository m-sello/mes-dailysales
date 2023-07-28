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

@Table(name = "mobile_mpesa")
public class MobileMpesa {
    @Id
    @Column("id")
    String id;
    @Column("float_amount")
    BigDecimal floatAmount;
    @Column("commission_amount")
    BigDecimal commissionAmount;
    @Column("account_amount")
    BigDecimal accountAmount;
    @MappedCollection(idColumn = "mobile_mpesa_id", keyColumn = "id")
    Set<MobileMpesaTaken> mobileMpesaTaken = new HashSet<>();
    @Transient
    MobileMoney mobileMoney;
    @PersistenceCreator
    public MobileMpesa(String id, BigDecimal floatAmount, BigDecimal commissionAmount, BigDecimal accountAmount, Set<MobileMpesaTaken> mobileMpesaTaken) {
        this.id = id;
        this.floatAmount = floatAmount;
        this.commissionAmount = commissionAmount;
        this.accountAmount = accountAmount;
        this.mobileMpesaTaken = mobileMpesaTaken;
        if(mobileMpesaTaken != null) this.mobileMpesaTaken.addAll(mobileMpesaTaken);
    }
    public MobileMpesa(){
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

    public BigDecimal getFloatAmount() {
        return floatAmount;
    }

    public void setFloatAmount(BigDecimal floatAmount) {
        this.floatAmount = floatAmount;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public Set<MobileMpesaTaken> getMobileMpesaTaken() {
        return mobileMpesaTaken;
    }

    public MobileMoney getMobileMoney() {
        return mobileMoney;
    }
}