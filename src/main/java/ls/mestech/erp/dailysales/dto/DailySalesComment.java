package ls.mestech.erp.dailysales.dto;


public class DailySalesComment {

    private Long id;

    private String comment;

    private DailySale dailySales;

    private DailySalesComment dailySalesComment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DailySale getDailySales() {
        return dailySales;
    }

    public void setDailySales(DailySale dailySales) {
        this.dailySales = dailySales;
    }

    public DailySalesComment getDailySalesComment() {
        return dailySalesComment;
    }

    public void setDailySalesComment(DailySalesComment dailySalesComment) {
        this.dailySalesComment = dailySalesComment;
    }

}