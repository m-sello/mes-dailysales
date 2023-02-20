package ls.mestech.erp.dailysales.dto;

import java.security.Timestamp;

public class UserGroupLog {
    private Long id;

    private Timestamp logDt;

    private String action;

    private String description;

    private UserGroup userGroupUserGroupDc;

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

    public UserGroup getUserGroupUserGroupDc() {
        return userGroupUserGroupDc;
    }

    public void setUserGroupUserGroupDc(UserGroup userGroupUserGroupDc) {
        this.userGroupUserGroupDc = userGroupUserGroupDc;
    }

}