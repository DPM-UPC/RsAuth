package pe.edu.upc.RsAuth.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AccessToken {
    @JsonProperty(value = "token")
    private String token;
    @JsonProperty(value = "expiration")
    private Date expiration;

    public AccessToken() {
    }

    public AccessToken(String token, Date expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "token='" + token + '\'' +
                ", expiration=" + expiration +
                '}';
    }
}
