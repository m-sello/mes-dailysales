package ls.mestech.erp.dailysales.domain.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "daily_sales_log")
public class DailySalesLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "log_dt")
    private Instant logDt;

    @Column(name = "action", length = 16)
    private String action;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_username", nullable = false)
    private User usersUsername;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getLogDt() {
        return logDt;
    }

    public void setLogDt(Instant logDt) {
        this.logDt = logDt;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUsersUsername() {
        return usersUsername;
    }

    public void setUsersUsername(User usersUsername) {
        this.usersUsername = usersUsername;
    }

}