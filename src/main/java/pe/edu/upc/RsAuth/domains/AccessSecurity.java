package pe.edu.upc.RsAuth.domains;

import java.util.Date;


/**
 * Created by Paolo Ortega on 23/06/2018.
 */
public class AccessSecurity {
    private Integer accessSecurityId;
    private String password;
    private Date startDate;
    private Date finalDate;
    private Integer state;
    private Date creationDate;
    private Date updateDate;

    private Integer userIdFk;

    public AccessSecurity() {
    }

    public AccessSecurity(Integer userIdFk) {
        this.userIdFk = userIdFk;
    }

    public Integer getAccessSecurityId() {
        return accessSecurityId;
    }

    public void setAccessSecurityId(Integer accessSecurityId) {
        this.accessSecurityId = accessSecurityId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(Integer userIdFk) {
        this.userIdFk = userIdFk;
    }

    @Override
    public String toString() {
        return "AccessSecurity{" +
                "accessSecurityId=" + accessSecurityId +
                ", password='" + password + '\'' +
                ", startDate=" + startDate +
                ", finalDate=" + finalDate +
                ", state=" + state +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", userIdFk=" + userIdFk +
                '}';
    }
}
