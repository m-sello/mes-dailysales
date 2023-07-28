package ls.mestech.erp.dailysales.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
public class User {
    @Id
    @Column("username")
    String id;

    @Column("email")
    String email;
    @MappedCollection
    Set<UserSecurityGroup> userSecurityGroups = new HashSet<>();
    @MappedCollection
    Set<UserLog> userLogs = new HashSet<>();
    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

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

    public Set<UserSecurityGroup> getUserGroups() {
        return userSecurityGroups;
    }

    public void addUserGroups(UserSecurityGroup userGroups) {
        this.userSecurityGroups.add(userGroups);
    }

    public Set<UserLog> getUserLogs() {
        return userLogs;
    }

    public void addUserLogs(UserLog userLogs) {
        this.userLogs.add(userLogs);
    }
}