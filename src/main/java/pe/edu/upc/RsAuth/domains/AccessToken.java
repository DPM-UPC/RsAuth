package pe.edu.upc.RsAuth.domains;

import java.util.Date;

public class AccessToken {
    private String token;
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
