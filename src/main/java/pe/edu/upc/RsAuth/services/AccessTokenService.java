package pe.edu.upc.RsAuth.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pe.edu.upc.RsAuth.exception.ResourceException;
import pe.edu.upc.RsAuth.models.AccessToken;
import pe.edu.upc.RsAuth.models.User;
import pe.edu.upc.RsAuth.utils.JwtUtil;

@Service
public class AccessTokenService {

    private static final Logger LOGGER = LogManager.getLogger(AccessTokenService.class);

    @Autowired
    private UserService userService;

    public AccessToken getAuthToken(User userReq) throws Exception {
        LOGGER.info("getAuthToken, userReq: {}", userReq);
        User user = userService.getUser(userReq);
        if (!validateRequest(userReq)) throw new ResourceException(HttpStatus.BAD_REQUEST, "Parametro(s) invalido(s)");

        if (!validateRequest(user) || !user.getUserPassword().equals(userReq.getUserPassword())) {
            throw new ResourceException(HttpStatus.UNAUTHORIZED, "Login incorrecto");
        }
        AccessToken accessToken = JwtUtil.getAccessToken(user.getUserName());
        LOGGER.info("token generado: {}", accessToken);
        return accessToken;
    }

    private boolean validateRequest(User user) {
        boolean result = false;
        if (user != null)
            if (user.getUserName() != null && !user.getUserName().isEmpty())
                if (user.getUserPassword() != null && !user.getUserPassword().isEmpty())
                    result = true;

        return result;
    }
}
