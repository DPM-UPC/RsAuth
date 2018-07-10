package pe.edu.upc.RsAuth.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.edu.upc.RsAuth.mappers.AccessSecurityMapper;
import pe.edu.upc.RsAuth.models.AccessSecurity;
import pe.edu.upc.RsAuth.repositories.AccessSecurityDao;

import java.util.List;

/**
 * Created by Paolo Ortega  on 25/06/2018.
 */
@Repository
public class MybatisAccessSecurityDao implements AccessSecurityDao {

    @Autowired
    AccessSecurityMapper accessSecurityMapper;

    @Override
    public int createAccess(AccessSecurity security) throws Exception {
        return accessSecurityMapper.createAccess(security);
    }

    @Override
    public int updateAccess(AccessSecurity security) throws Exception {
        return accessSecurityMapper.updateAccess(security);
    }

    @Override
    public AccessSecurity getAccess(AccessSecurity security) throws Exception {
        return accessSecurityMapper.getAccessSecurity(security);
    }

    @Override
    public List<AccessSecurity> listAccess(AccessSecurity security) throws Exception {
        return accessSecurityMapper.listAccess(security);
    }

    @Override
    public int deleteAccess(AccessSecurity security) throws Exception {
        return accessSecurityMapper.deleteAccess(security);
    }
}
