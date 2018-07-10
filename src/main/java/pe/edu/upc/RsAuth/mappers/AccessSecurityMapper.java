package pe.edu.upc.RsAuth.mappers;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import pe.edu.upc.RsAuth.models.AccessSecurity;

import java.util.List;

/**
 * Created by Paolo Ortega on 25/06/2018.
 */
@Mapper
@Component
public interface AccessSecurityMapper {

    @Insert("insert into access_security (password, start_date, final_date, state, creation_date, user_id_fk) " +
            "values(#{password},now(),date_add(now(), INTERVAL 30 DAY), 1, now(), #{userIdFk})")
    @Options(useGeneratedKeys = true, keyProperty = "accessSecurityId", keyColumn = "access_security_id")
    int createAccess(AccessSecurity security);

    @Update("<script> update access_security <set> update_date=#{updateDate}, <if test=\"password != null\">password=#{password},</if> " +
            "<if test=\"startDate != null\">start_date=#{startDate},</if> <if test=\"finalDate != null\">final_date=#{finalDate},</if> " +
            " </set> WHERE access_security_id=#{access_security_id}</script>")
    int updateAccess(AccessSecurity security);

    @Results({
            @Result(property = "accessSecurityId", column = "access_security_id"),
            @Result(property = "password", column = "password"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "finalDate", column = "final_date"),
            @Result(property = "state", column = "state"),
            @Result(property = "creationDate", column = "creation_date"),
            @Result(property = "userIdFk", column = "user_id_fk"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("<script> select access_security_id, password, start_date, final_date, state, creation_date, update_date, user_id_fk " +
            "from access_security a " +
            "where a.state=1 <if test=\"accessSecurityId != null\">and access_security_id=#{accessSecurityId}</if>" +
            "<if test=\"userIdFk != null\">and user_id_fk=#{userIdFk}</if> </script>")
    AccessSecurity getAccessSecurity(AccessSecurity security);

    @Results({
            @Result(property = "accessSecurityId", column = "access_security_id"),
            @Result(property = "password", column = "password"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "finalDate", column = "final_date"),
            @Result(property = "state", column = "state"),
            @Result(property = "creationDate", column = "creation_date"),
            @Result(property = "userIdFk", column = "user_id_fk"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("select access_security_id, password, start_date, final_date, state, creation_date, update_date, user_id_fk " +
            "from access_security a " +
            "where a.state=1")
    List<AccessSecurity> listAccess(AccessSecurity security);

    @Update("update access_security_id set state=0 WHERE access_security_id=#{accessSecurityId}")
    int deleteAccess(AccessSecurity security);

}
