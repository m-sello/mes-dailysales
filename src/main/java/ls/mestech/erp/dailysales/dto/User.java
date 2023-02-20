package ls.mestech.erp.dailysales.dto;


public class User {
    private String id;

    private String email;

    private UserGroup userGroupUserGroupDc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserGroup getUserGroupUserGroupDc() {
        return userGroupUserGroupDc;
    }

    public void setUserGroupUserGroupDc(UserGroup userGroupUserGroupDc) {
        this.userGroupUserGroupDc = userGroupUserGroupDc;
    }

}