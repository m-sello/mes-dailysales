package ls.mestech.erp.dailysales.dto;


public class TenderType {

    private String id;

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