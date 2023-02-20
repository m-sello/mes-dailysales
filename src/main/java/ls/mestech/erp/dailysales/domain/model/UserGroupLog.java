package ls.mestech.erp.dailysales.domain.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "user_group_log")
public class UserGroupLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "log_dt")
    private Instant logDt;

    @Column(name = "action", length = 16)
    private String action;

    @Column(name = "description")
    private Instant description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_group_user_group_dc", nullable = false)
    private UserGroup userGroupUserGroupDc;

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

    public Instant getDescription() {
        return description;
    }

    public void setDescription(Instant description) {
        this.description = description;
    }

    public UserGroup getUserGroupUserGroupDc() {
        return userGroupUserGroupDc;
    }

    public void setUserGroupUserGroupDc(UserGroup userGroupUserGroupDc) {
        this.userGroupUserGroupDc = userGroupUserGroupDc;
    }

}