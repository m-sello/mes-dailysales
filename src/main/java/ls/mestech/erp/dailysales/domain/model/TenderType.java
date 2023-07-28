package ls.mestech.erp.dailysales.domain.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table(name = "tender_type")
public class TenderType {
    @Id
    @Column("tender_type_cd")
    private String tenderTypeCd;

    @Column("active_flg")
    Boolean activeFlg;
    @Version
    private Integer version;
    @MappedCollection(idColumn = "tender_type_cd")
    final Set<TenderTypeLang> tenderTypeLang = new HashSet<>();
    @MappedCollection(idColumn = "tender_type_cd", keyColumn = "id")
    final Set<TenderTypeLog> tenderTypeLog = new HashSet<>();
    public TenderType(){}
    @PersistenceCreator
    public TenderType(String tenderTypeCd,Boolean activeFlg,
            Set<TenderTypeLang> tenderTypeLang, Set<TenderTypeLog> tenderTypeLog){
        this.tenderTypeCd = tenderTypeCd;
        this.activeFlg = activeFlg;
        if(tenderTypeLang != null) this.tenderTypeLang.addAll(tenderTypeLang);
        if(tenderTypeLog != null) this.tenderTypeLog.addAll(tenderTypeLog);
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
    public Boolean getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(Boolean activeFlg) {
        this.activeFlg = activeFlg;
    }

    public String getTenderTypeCd() {
        return tenderTypeCd;
    }

    public void setTenderTypeCd(String tenderTpeCd) {
        this.tenderTypeCd = tenderTpeCd;
    }

    public Set<TenderTypeLog> getTenderTypeLog() {
        return tenderTypeLog;
    }

    public void addTenderTypeLog(TenderTypeLog tenderTypeLog){
        this.tenderTypeLog.add(tenderTypeLog);
    }

    public Set<TenderTypeLang> getTenderTypeLang() {
        return tenderTypeLang;
    }
    public void addTenderTypeLang(TenderTypeLang tenderTypeLang){this.tenderTypeLang.add(tenderTypeLang);}
}