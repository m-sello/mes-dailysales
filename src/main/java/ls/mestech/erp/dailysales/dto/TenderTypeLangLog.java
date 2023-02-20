package ls.mestech.erp.dailysales.dto;

import java.security.Timestamp;

public class TenderTypeLangLog {
    private Long id;

    private Timestamp logDt;

    private String action;

    private String description;

    private User usersUsername;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getLogDt() {
        return logDt;
    }

    public void setLogDt(Timestamp logDt) {
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