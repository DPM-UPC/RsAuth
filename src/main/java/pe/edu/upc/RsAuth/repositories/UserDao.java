package pe.edu.upc.RsAuth.repositories;


import pe.edu.upc.RsAuth.domains.User;

import java.util.List;

/**
 * Created by Paolo Ortega on 25/06/2018.
 */
public interface UserDao {
    int createUser(User user) throws Exception;

    int updateUser(User user) throws Exception;

    User getUser(User user) throws Exception;

    List<User> listUser(User user) throws Exception;

    int deleteUser(User user) throws Exception;
}
