package ls.mestech.erp.dailysales.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "daily_sales_comment")
public class DailySalesComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "daily_sales_id", nullable = false)
    private DailySales dailySales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_sales_comment_id")
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

    public DailySales getDailySales() {
        return dailySales;
    }

    public void setDailySales(DailySales dailySales) {
        this.dailySales = dailySales;
    }

    public DailySalesComment getDailySalesComment() {
        return dailySalesComment;
    }

    public void setDailySalesComment(DailySalesComment dailySalesComment) {
        this.dailySalesComment = dailySalesComment;
    }

}