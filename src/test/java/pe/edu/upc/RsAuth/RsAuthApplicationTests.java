package pe.edu.upc.RsAuth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pe.edu.upc.RsAuth.models.User;
import pe.edu.upc.RsAuth.services.AccessTokenService;
import pe.edu.upc.RsAuth.utils.JwtUtil;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RsAuthApplicationTests {

    private static final Logger LOGGER = LogManager.getLogger(RsAuthApplicationTests.class);

    @Autowired
    AccessTokenService accessTokenService;

    @Test
    public void contextLoads() {
    }

    //@Test
    public void accessTokenTest() throws Exception {

        User user = new User();
        user.setEmail("paolo@hotmail.com");
        user.setUserPassword("123456789");

        LOGGER.info(accessTokenService.getAuthToken(user));
    }

    @Test
    public void accessTokenTest1() throws Exception {
        String token = JwtUtil.getAccessToken(26).getToken();
        LOGGER.info("token: " + token);

        String user = Jwts.parser()
                .setSigningKey("SecretKeyToGenJWTs".getBytes())
                .parseClaimsJws(token) //este metodo es el que valida
                .getBody()
                .getSubject();
        LOGGER.info(user);
    }

    //@Test
    public void accessTokenTest2() throws Exception {
        String token = Jwts.builder()
                .setSubject("26")
                .setExpiration(new Date(System.currentTimeMillis() + 100_000))
                .signWith(SignatureAlgorithm.HS512, "SecretKeyToGenJWTs".getBytes())
                .compact();
        LOGGER.info("token: " + token);

        String user = Jwts.parser()
                .setSigningKey("SecretKeyToGenJWTs".getBytes())
                .parseClaimsJws(token) //este metodo es el que valida
                .getBody()
                .getSubject();
        LOGGER.info(user);
    }

    //@Test
    public void access3() {
        String compactJws = Jwts.builder()
                .setSubject("Joe")
                .signWith(SignatureAlgorithm.HS512, MacProvider.generateKey())
                .compact();
        LOGGER.info(compactJws);

        String user = Jwts.parser().setSigningKey(MacProvider.generateKey()).
                parseClaimsJws(compactJws).getBody().getSubject();

        LOGGER.info(user);

    }

}
