package pe.edu.upc.RsAuth.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Paolo Ortega on 23/06/2018.
 */
public class User {
    private Integer userId;
    private String userName;
    private String email;
    private String state;
    private Date creationDate;
    private Date updateDate;
    private Country country;
    private List<AccessSecurity> accessSecurities;

    private String userPassword;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<AccessSecurity> getAccessSecurities() {
        if (accessSecurities == null) accessSecurities = new ArrayList<>();
        return accessSecurities;
    }

    public void setAccessSecurities(List<AccessSecurity> accessSecurities) {
        this.accessSecurities = accessSecurities;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", country=" + country +
                ", accessSecurities=" + accessSecurities +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
