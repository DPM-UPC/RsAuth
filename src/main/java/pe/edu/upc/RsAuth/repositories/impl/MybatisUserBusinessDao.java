package pe.edu.upc.RsAuth.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.edu.upc.RsAuth.mappers.UserBusinessMapper;
import pe.edu.upc.RsAuth.models.User;
import pe.edu.upc.RsAuth.models.UserBusiness;
import pe.edu.upc.RsAuth.repositories.UserBusinessDao;

import java.util.List;

/**
 * Created by Paolo Ortega on 25/06/2018.
 */
@Repository
public class MybatisUserBusinessDao implements UserBusinessDao {

    @Autowired
    UserBusinessMapper userBusinessMapper;

    @Override
    public List<UserBusiness> listUserBusiness(User user) throws Exception {
        return userBusinessMapper.listUserBusiness(user);
    }

    @Override
    public int createUserBusiness(UserBusiness userBusiness) throws Exception {
        return userBusinessMapper.createUserBusiness(userBusiness);
    }
}
