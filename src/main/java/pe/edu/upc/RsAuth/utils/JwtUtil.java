package pe.edu.upc.RsAuth.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.edu.upc.RsAuth.models.AccessToken;

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

    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    public static AccessToken getAccessToken(String username) {
        LOGGER.info("generando token a partir del user: {}", username);

        LOGGER.debug("expiracion del token: {} segs = {} min = {} horas", EXPIRATION_TIME / 1000, EXPIRATION_TIME / 1000 / 60, EXPIRATION_TIME / 1000 / 3600);
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        LOGGER.info("token: " + token);

        //agregamos al encabezado el token
        //res.setHeader(REQUEST_STRING, TOKEN_PREFIX + " " + token);
        return new AccessToken(token, expirationDate);
    }

    // Método para validar el token enviado por el cliente
    /*public static Authentication getAuthentication(HttpServletRequest request) {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader(REQUEST_STRING);

        // si hay un token presente, entonces lo validamos
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) //este metodo es el que valida
                    .getBody()
                    .getSubject();

            // Recordamos que para las demás peticiones que no sean /login
            // no requerimos una autenticacion por username/password
            // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) :
                    null;
        }
        return null;
    }*/

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
