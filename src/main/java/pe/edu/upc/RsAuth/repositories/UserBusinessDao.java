package pe.edu.upc.RsAuth.repositories;


import pe.edu.upc.RsAuth.models.User;
import pe.edu.upc.RsAuth.models.UserBusiness;

import java.util.List;

/**
 * Created by Paolo Ortega on 25/06/2018.
 */
public interface UserBusinessDao {

    List<UserBusiness> listUserBusiness(User user) throws Exception;
}
