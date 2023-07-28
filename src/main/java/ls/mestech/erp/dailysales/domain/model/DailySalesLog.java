package ls.mestech.erp.dailysales.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "daily_sales_log")
public class DailySalesLog {
    @Id
    @Column("id")
    private String id;

    @Column("log_dt")
    private LocalDateTime logDt;

    @Column("action")
    private String action;

    @Column("description")
    private String description;
    @Column("username")
    private String username;
    @Transient
    private DailySales dailySales;
    @PersistenceCreator
    public DailySalesLog(String id, LocalDateTime logDt, String action, String description, String username){
        this.id = id;
        this.logDt = logDt;
        this.action = action;
        this.description = description;
        this.username = username;
    }
    public DailySalesLog(){

    }
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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getLogDt() {
        return logDt;
    }

    public void setLogDt(LocalDateTime logDt) {
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
    public DailySales getDailySales() {
        return dailySales;
    }

}