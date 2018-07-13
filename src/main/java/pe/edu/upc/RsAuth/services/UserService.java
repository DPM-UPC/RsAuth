package pe.edu.upc.RsAuth.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.RsAuth.exception.ResourceException;
import pe.edu.upc.RsAuth.models.AccessSecurity;
import pe.edu.upc.RsAuth.models.AccessToken;
import pe.edu.upc.RsAuth.models.User;
import pe.edu.upc.RsAuth.models.UserBusiness;
import pe.edu.upc.RsAuth.repositories.AccessSecurityDao;
import pe.edu.upc.RsAuth.repositories.UserBusinessDao;
import pe.edu.upc.RsAuth.repositories.UserDao;
import pe.edu.upc.RsAuth.utils.JwtUtil;

import java.util.List;

/**
 * Created by Paolo Ortega on 25/06/2018.
 */
@Service
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    AccessSecurityDao accessSecurityDao;

    @Autowired
    UserBusinessDao userBusinessDao;

    @Autowired
    UserDao userDao;

    @Transactional
    public User createUser(User user) throws Exception {
        LOGGER.debug("createUser, user: {}", user);
        if (!validateCreateRequest(user))
            throw new ResourceException(HttpStatus.BAD_REQUEST, "Parametro(s) invalido(s)");
        user.setUserPassword(user.getAccessSecurities().get(0).getPassword());
        userDao.createUser(user);
        LOGGER.debug("userId obtenido: " + user.getUserId());
        user.getAccessSecurities().get(0).setUserIdFk(user.getUserId());
        //creando acceso
        accessSecurityDao.createAccess(user.getAccessSecurities().get(0));
        //creando token
        AccessToken accessToken = JwtUtil.getAccessToken(user.getUserId());
        //creando userBusiness
        UserBusiness userBusiness = new UserBusiness();
        userBusiness.setUserIdFk(user.getUserId());
        userBusiness.setBusinessIdFk(user.getUserBusinesses().get(0).getUserBusinessId());
        userBusinessDao.createUserBusiness(userBusiness);

        //se obtiene el usuario completo
        User newUser = getUser(new User(user.getUserId()));
        newUser.setToken(accessToken.getToken());
        return newUser;
    }

    public User updateUser(User user) throws Exception {
        LOGGER.debug("updateUser, user: {}", user);
        if (!validateUpdateRequest(user))
            throw new ResourceException(HttpStatus.BAD_REQUEST, "Parametro(s) invalido(s)");
        userDao.updateUser(user);
        return user;
    }

    public User getUser(User user) throws Exception {
        LOGGER.debug("getUser, user: {}", user);
        User newUser = userDao.getUser(user);
        if (newUser == null) return null;
        AccessSecurity accessSecurity = accessSecurityDao.getAccess(new AccessSecurity(newUser.getUserId()));
        newUser.getAccessSecurities().add(accessSecurity);
        List<UserBusiness> userBusinesses = userBusinessDao.listUserBusiness(user);
        newUser.setUserBusinesses(userBusinesses);
        return newUser;
    }

    public List<User> listUser(User user) throws Exception {
        LOGGER.debug("listUser, user: {}", user);
        List<User> userList = userDao.listUser(user);
        for (User user1 : userList) {
            AccessSecurity accessSecurity = accessSecurityDao.getAccess(new AccessSecurity(user1.getUserId()));
            user1.getAccessSecurities().add(accessSecurity);
            List<UserBusiness> userBusinesses = userBusinessDao.listUserBusiness(user1);
            user1.setUserBusinesses(userBusinesses);
        }
        return userList;
    }

    public void deleteUser(User user) throws Exception {
        LOGGER.debug("deleteUser, user: {}", user);
        if (!validateDeleteRequest(user))
            throw new ResourceException(HttpStatus.BAD_REQUEST, "Parametro(s) invalido(s)");
        userDao.deleteUser(user);
    }

    public static boolean validateCreateRequest(User user) {
        boolean result = false;
        if (user != null)
                if (user.getEmail() != null && !user.getEmail().isEmpty())
                    if (user.getAccessSecurities() != null && user.getAccessSecurities().size() == 1)
                        if (user.getAccessSecurities().get(0) != null && user.getAccessSecurities().get(0).getPassword() != null && !user.getAccessSecurities().get(0).getPassword().isEmpty())
                            if (user.getCountry() != null && user.getCountry().getCountryId() != null && user.getCountry().getCountryId() > 0)
                                result = true;

        return result;
    }

    public static boolean validateUpdateRequest(User user) {
        boolean result = false;
        if (user != null)
                if (user.getEmail() != null && !user.getEmail().isEmpty())
                    result = true;

        return result;
    }

    public static boolean validateDeleteRequest(User user) {
        boolean result = false;
        if (user != null)
            if (user.getUserId() != null && user.getUserId() > 0)
                result = true;

        return result;
    }
}
