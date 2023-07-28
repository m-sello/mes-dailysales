package ls.mestech.erp.dailysales.domain.model;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Table(name = "mobile_money")
public class MobileMoney {
    @Id
    @Column("id")
    private String id;
    @Column("mpesa_amount")
    private BigDecimal mpesaAmount;
    @Column("ecocash_amount")
    private BigDecimal ecocashAmount;
    @Column("electricity_amount")
    private BigDecimal electricityAmount;
    @Column("airtime_amount")
    private BigDecimal airtimeAmount;
    @Column("cashplus_amount")
    private BigDecimal cashplusAmount;
    @Column("daily_sales_id")
    private String dailySalesId;
    @Column("comments")
    private String comments;
    @Version
    private Integer version;
    @Column("mobile_money_id")
    private MobileMpesa mobileMpesa;
    @Column("mobile_money_id")
    private MobileEcocash mobileEcocash;
    @Column("mobile_money_id")
    private MobileCashplus mobileCashplus;
    @Column("mobile_money_id")
    private MobileElectricityAirtime mobileElectricityAirtime;
    @MappedCollection(idColumn = "mobile_money_id", keyColumn = "id")
    final Set<MobileMoneyLog> mobileMoneyLogs = new HashSet<>();
    @PersistenceCreator
    public MobileMoney(String id, BigDecimal mpesaAmount, BigDecimal ecocashAmount, BigDecimal electricityAmount, BigDecimal airtimeAmount, BigDecimal cashplusAmount, String dailySalesId, String comments, MobileMpesa mobileMpesa, MobileEcocash mobileEcocash, MobileCashplus mobileCashplus, MobileElectricityAirtime mobileElectricityAirtime,Set<MobileMoneyLog> mobileMoneyLogs) {
        this.id = id;
        this.mpesaAmount = mpesaAmount;
        this.ecocashAmount = ecocashAmount;
        this.electricityAmount = electricityAmount;
        this.airtimeAmount = airtimeAmount;
        this.cashplusAmount = cashplusAmount;
        this.dailySalesId = dailySalesId;
        this.comments = comments;
        this.mobileMpesa = mobileMpesa;
        this.mobileEcocash = mobileEcocash;
        this.mobileCashplus = mobileCashplus;
        this.mobileElectricityAirtime = mobileElectricityAirtime;
        if(mobileMoneyLogs != null) this.mobileMoneyLogs.addAll(mobileMoneyLogs);
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

    public BigDecimal getMpesaAmount() {
        return mpesaAmount;
    }

    public void setMpesaAmount(BigDecimal mpesaAmount) {
        this.mpesaAmount = mpesaAmount;
    }

    public BigDecimal getEcocashAmount() {
        return ecocashAmount;
    }

    public void setEcocashAmount(BigDecimal ecocashAmount) {
        this.ecocashAmount = ecocashAmount;
    }

    public BigDecimal getElectricityAmount() {
        return electricityAmount;
    }

    public void setElectricityAmount(BigDecimal electricityAmount) {
        this.electricityAmount = electricityAmount;
    }

    public BigDecimal getAirtimeAmount() {
        return airtimeAmount;
    }

    public void setAirtimeAmount(BigDecimal airtimeAmount) {
        this.airtimeAmount = airtimeAmount;
    }

    public BigDecimal getCashplusAmount() {
        return cashplusAmount;
    }

    public void setCashplusAmount(BigDecimal cashplusAmount) {
        this.cashplusAmount = cashplusAmount;
    }

    public String getDailySalesId() {
        return dailySalesId;
    }

    public void setDailySalesId(String dailySalesId) {
        this.dailySalesId = dailySalesId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public MobileMpesa getMobileMpesa() {
        return mobileMpesa;
    }

    public void setMobileMpesa(MobileMpesa mobileMpesa) {
        this.mobileMpesa = mobileMpesa;
    }

    public MobileEcocash getMobileEcocash() {
        return mobileEcocash;
    }

    public void setMobileEcocash(MobileEcocash mobileEcocash) {
        this.mobileEcocash = mobileEcocash;
    }

    public MobileCashplus getMobileCashplus() {
        return mobileCashplus;
    }

    public void setMobileCashplus(MobileCashplus mobileCashplus) {
        this.mobileCashplus = mobileCashplus;
    }

    public MobileElectricityAirtime getMobileElectricityAirtime() {
        return mobileElectricityAirtime;
    }

    public void setMobileElectricityAirtime(MobileElectricityAirtime mobileElectricityAirtime) {
        this.mobileElectricityAirtime = mobileElectricityAirtime;
    }

    public Set<MobileMoneyLog> getMobileMoneyLogs() {
        return mobileMoneyLogs;
    }

    public void addMobileMoneyLog(MobileMoneyLog log) {
        mobileMoneyLogs.add(log);
    }
}