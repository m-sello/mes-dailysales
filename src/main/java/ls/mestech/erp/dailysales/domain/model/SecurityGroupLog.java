package ls.mestech.erp.dailysales.domain.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "user_group_log")
public class SecurityGroupLog {
    @Id
    @Column("id")
    private Long id;

    @Column("log_dt")
    private Instant logDt;

    @Column("action")
    private String action;

    @Column("description")
    private Instant description;

    @Transient
    SecurityGroupLog securityGroupLog;
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

    public SecurityGroupLog getSecurityGroupLog() {
        return securityGroupLog;
    }
}