package pe.edu.upc.RsAuth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.RsAuth.domains.User;

@RestController
@RequestMapping(value = "/authorization")
public class AuthorizationController {

    @GetMapping
    public ResponseEntity<User> getRefreshToken(@RequestParam(value = "accessToken") String accessToken) {
        // TODO: envia el token para obtener un refresh token
        return null;
    }

}
