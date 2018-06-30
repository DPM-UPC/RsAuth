package pe.edu.upc.RsAuth.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.RsAuth.domains.User;
import pe.edu.upc.RsAuth.services.UserService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(AccessTokenController.class);

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        LOGGER.info("createUser(), user: " + user);
        User userResult;
        try {
            userResult = userService.createUser(user);
        } catch (Exception e) {
            LOGGER.error("", e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(CREATED).body(userResult);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        LOGGER.info("updateUser(), user: " + user);
        User userResult;
        try {
            userResult = userService.updateUser(user);
        } catch (Exception e) {
            LOGGER.error("", e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok().body(userResult);
    }

    @DeleteMapping("/{user_id:^[0-9]*$}")
    public ResponseEntity deleteUser(@PathVariable("user_id") Integer userId) {
        LOGGER.info("deleteUser(), userId: " + userId);
        try {
            userService.deleteUser(new User(userId));
        } catch (Exception e) {
            LOGGER.error("", e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @GetMapping("/{user_id:^[0-9]*$}")
    public ResponseEntity<User> getUserFrom(@PathVariable(value = "user_id", required = false) Integer userId) {
        LOGGER.info("getUserFrom(), userId: {}: ", userId);
        User userResult;
        User userReq = new User();
        userReq.setUserId(userId);
        try {
            userResult = userService.getUser(userReq);
            LOGGER.info("userResult: " + userResult);
        } catch (Exception e) {
            LOGGER.error("", e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
        if (userResult == null) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok().body(userResult);
    }

    @GetMapping
    public ResponseEntity<User> getUserFrom(@RequestParam(value = "user_name", required = false) String userName) {
        LOGGER.info("getUserFrom() userName: {}: ", userName);
        User userResult;
        User userReq = new User();
        userReq.setUserName(userName);
        try {
            userResult = userService.getUser(userReq);
            LOGGER.info("userResult: " + userResult);
        } catch (Exception e) {
            LOGGER.error("", e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
        if (userResult == null) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok().body(userResult);
    }


}
