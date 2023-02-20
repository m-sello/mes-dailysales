package ls.mestech.erp.dailysales.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "username", nullable = false, length = 16)
    private String id;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_group_user_group_dc", nullable = false)
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