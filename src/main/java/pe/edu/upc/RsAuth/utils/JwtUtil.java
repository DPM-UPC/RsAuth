package pe.edu.upc.RsAuth.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pe.edu.upc.RsAuth.models.AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Paolo Ortega on 25/06/2018.
 */
@Component
public class JwtUtil {

    private static final Logger LOGGER = LogManager.getLogger(JwtUtil.class);

    private static String SECRET;
    private static long EXPIRATION_TIME;
    private static String TOKEN_PREFIX;
    private static String REQUEST_STRING;
    static final String HEADER_STRING = "Authorization";

    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    public static AccessToken getAccessToken(Integer userId) {
        LOGGER.info("generando token a partir del user: {}", userId);

        LOGGER.debug("expiracion del token: {} segs = {} min = {} horas", EXPIRATION_TIME / 1000, EXPIRATION_TIME / 1000 / 60, EXPIRATION_TIME / 1000 / 3600);
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        LOGGER.info("token: " + token);

        //agregamos al encabezado el token
        //res.setHeader(REQUEST_STRING, TOKEN_PREFIX + " " + token);
        return new AccessToken(token, expirationDate, userId);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        String user = null;
        if (token != null) {
            // parse the token.
            try {
                user = Jwts.parser()
                        .setSigningKey(SECRET.getBytes())
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) //este metodo es el que valida
                        .getBody()
                        .getSubject();
            } catch (Exception e) {
                LOGGER.error("", e);
            }

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) :
                    null;
        }
        return null;
    }

    @Value("${auth-security.token.secretCode}")
    public void setSECRET(String secret) {
        SECRET = secret;
    }

    @Value("${auth-security.token.expirationTime}")
    public void setExpirationTime(long expirationTime) {
        EXPIRATION_TIME = expirationTime;
    }

    @Value("${auth-security.token.tokenPrefix}")
    public void setTokenPrefix(String tokenPrefix) {
        TOKEN_PREFIX = tokenPrefix;
    }

    @Value("${auth-security.token.requestString}")
    public void setHeaderString(String requestString) {
        REQUEST_STRING = requestString;
    }
}
