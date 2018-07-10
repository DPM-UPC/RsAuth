package pe.edu.upc.RsAuth.repositories;

import pe.edu.upc.RsAuth.models.AccessSecurity;

import java.util.List;

/**
 * Created by Paolo Ortega on 23/06/2018.
 */
public interface AccessSecurityDao {

    int createAccess(AccessSecurity security) throws Exception;

    int updateAccess(AccessSecurity security) throws Exception;

    AccessSecurity getAccess(AccessSecurity security) throws Exception;

    List<AccessSecurity> listAccess(AccessSecurity security) throws Exception;

    int deleteAccess(AccessSecurity security) throws Exception;
}
