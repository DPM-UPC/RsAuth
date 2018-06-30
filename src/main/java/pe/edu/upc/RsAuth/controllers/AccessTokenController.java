package pe.edu.upc.RsAuth.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.RsAuth.domains.AccessToken;
import pe.edu.upc.RsAuth.domains.User;
import pe.edu.upc.RsAuth.exception.ResourceException;
import pe.edu.upc.RsAuth.services.AccessTokenService;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping(value = "/accessToken")
public class AccessTokenController {

    private static final Logger LOGGER = LogManager.getLogger(AccessTokenController.class);

    @Autowired
    AccessTokenService accessTokenService;

    @PostMapping
    public ResponseEntity<AccessToken> createGetAccessToken(@RequestBody User user) {
        //logea y si tiene exito devuelve el token
        AccessToken accessToken;
        try {
            accessToken = accessTokenService.getAuthToken(user);
        } catch (ResourceException e) {
            LOGGER.error("error recurso detectado: ", e);
            return ResponseEntity.status(e.getHttpStatus()).body(null);
        } catch (Exception e) {
            LOGGER.error("error generico detectado: ", e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok().body(accessToken);
    }

}
