package pe.edu.upc.RsAuth.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.RsAuth.models.User;
import pe.edu.upc.RsAuth.services.UserService;

import static org.springframework.http.HttpStatus.*;

@RestController
@Api(value = "users", description = "Operations pertaining to management of users", tags = {"Users"})
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping
    @ApiOperation(value = "Create new users", response = User.class)
    public ResponseEntity<User> createUser(@ApiParam(value = "User body, containing all the information of the new user", required = true) @RequestBody User user) {
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
    @ApiOperation(value = "Update users", response = User.class)
    public ResponseEntity<User> updateUser(@ApiParam(value = "User body, containing attributes of user that must to update", required = true) @RequestBody User user) {
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
    @ApiOperation(value = "Delete users")
    public ResponseEntity deleteUser(@ApiParam(value = "The unique identifier of user", required = true) @PathVariable("user_id") Integer userId) {
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
    @ApiOperation(value = "Find a user by id", response = User.class)
    public ResponseEntity<User> getUserFrom(@ApiParam(value = "The unique identifier of user", required = true) @PathVariable(value = "user_id") Integer userId) {
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

    @GetMapping("/access_securities/")
    @ApiOperation(value = "Get user by name", response = User.class)
    public ResponseEntity<User> getUserFrom(@ApiParam(value = "The name of user", required = true) @RequestParam(value = "user_name") String userName) {
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
