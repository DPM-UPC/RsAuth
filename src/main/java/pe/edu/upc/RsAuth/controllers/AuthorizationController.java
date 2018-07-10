package pe.edu.upc.RsAuth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.upc.RsAuth.models.User;

//@RestController
//@Api(value="users", description="Operation pertaining to management of users")
//@RequestMapping(value = "/authorizations")
public class AuthorizationController {

    //@GetMapping
    public ResponseEntity<User> getRefreshToken(@RequestParam(value = "accessToken") String accessToken) {
        // TODO: envia el token para obtener un refresh token
        return null;
    }

}
