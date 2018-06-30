package pe.edu.upc.RsAuth.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.edu.upc.RsAuth.domains.User;
import pe.edu.upc.RsAuth.mappers.UserMapper;
import pe.edu.upc.RsAuth.repositories.UserDao;

import java.util.List;

/**
 * Created by Paolo Ortega on 25/06/2018.
 */
@Repository
public class MybatisUserDao implements UserDao {

    @Autowired
    UserMapper userMapper;

    @Override
    public int createUser(User user) throws Exception {
        return userMapper.createUser(user);
    }

    @Override
    public int updateUser(User user) throws Exception {
        return userMapper.updateUser(user);
    }

    @Override
    public User getUser(User user) throws Exception {
        return userMapper.getUser(user);
    }

    @Override
    public List<User> listUser(User user) throws Exception {
        return userMapper.listUser(user);
    }

    @Override
    public int deleteUser(User user) throws Exception {
        return userMapper.deleteUser(user);
    }
}
