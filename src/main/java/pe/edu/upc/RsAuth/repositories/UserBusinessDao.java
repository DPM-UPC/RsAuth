package pe.edu.upc.RsAuth.repositories;


import pe.edu.upc.RsAuth.models.User;
import pe.edu.upc.RsAuth.models.UserBusiness;

import java.util.List;

/**
 * Created by Paolo Ortega on 25/07/2018.
 */
public interface UserBusinessDao {

    List<UserBusiness> listUserBusiness(User user) throws Exception;

    int createUserBusiness(UserBusiness userBusiness) throws Exception;
}
