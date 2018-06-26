package pe.edu.upc.RsAuth.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.RsAuth.domains.User;
import pe.edu.upc.RsAuth.repositories.AccessSecurityDao;
import pe.edu.upc.RsAuth.repositories.UserDao;

import java.util.List;

/**
 * Created by Paolo Ortega on 4/02/2018.
 */
@Service
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    AccessSecurityDao accessSecurityDao;

    @Autowired
    UserDao userDao;

    public String getAuthToken(User user) throws Exception {
        LOGGER.debug("getAuthToken, user: {}", user);
        if (getUser(user) == null) {
            // TODO: retornar mensaje sin autorizacion
        }
        String token = "";
        // TODO: se genera token
        return token;
    }

    @Transactional
    public User createUser(User user) throws Exception {
        LOGGER.debug("createUser, user: {}", user);
        accessSecurityDao.createAccess(user.getAccessSecurities().get(0));
        userDao.createUser(user);
        return user;
    }

    public User updateUser(User user) throws Exception {
        LOGGER.debug("updateUser, user: {}", user);
        userDao.updateUser(user);
        return user;
    }

    public User getUser(User user) throws Exception {
        LOGGER.debug("getUser, user: {}", user);
        return userDao.getUser(user);
    }

    public List<User> listUser(User user) throws Exception {
        LOGGER.debug("listUser, user: {}", user);
        return userDao.listUser(user);
    }

    public void deleteUser(User user) throws Exception {
        LOGGER.debug("deleteUser, user: {}", user);
        userDao.deleteUser(user);
    }
}
