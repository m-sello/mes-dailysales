package ls.mestech.erp.dailysales.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tender_type_lang")
public class TenderTypeLang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tender_type_cd", nullable = false, length = 8)
    private String id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_language_cd", nullable = false)
    private Language languageLanguageCd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguageLanguageCd() {
        return languageLanguageCd;
    }

    public void setLanguageLanguageCd(Language languageLanguageCd) {
        this.languageLanguageCd = languageLanguageCd;
    }

}