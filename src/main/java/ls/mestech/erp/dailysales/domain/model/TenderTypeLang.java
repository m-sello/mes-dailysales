package ls.mestech.erp.dailysales.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tender_type_lang")
public class TenderTypeLang {
    @Column("tender_type_cd")
    private String tenderTypeCd;

    @Column("description")
    private String description;

    @Column("language_cd")
    private String languageCd;
    @Transient
    TenderType tenderType;
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
    public String getTenderTypeCd() {
        return tenderTypeCd;
    }

    public void setTenderTypeCd(String tenderTypeCd) {
        this.tenderTypeCd = tenderTypeCd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguageCd() {
        return languageCd;
    }

    public void setLanguageCd(String languageCd) {
        this.languageCd = languageCd;
    }
}