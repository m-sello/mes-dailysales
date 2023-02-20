package ls.mestech.erp.dailysales.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tender_type")
public class TenderType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tender_type_cd", nullable = false, length = 8)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tender_type_lang_tender_type_cd", nullable = false)
    private TenderTypeLang tenderTypeLangTenderTypeCd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TenderTypeLang getTenderTypeLangTenderTypeCd() {
        return tenderTypeLangTenderTypeCd;
    }

    public void setTenderTypeLangTenderTypeCd(TenderTypeLang tenderTypeLangTenderTypeCd) {
        this.tenderTypeLangTenderTypeCd = tenderTypeLangTenderTypeCd;
    }

}