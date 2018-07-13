package pe.edu.upc.RsAuth.mappers;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import pe.edu.upc.RsAuth.models.User;

import java.util.List;

/**
 * Created by Paolo Ortega on 25/06/2018.
 */
@Mapper
@Component
public interface UserMapper {

    @Insert("insert into users (email,state,creation_date, country_id_fk) " +
            "values(#{email},1,now(), #{country.countryId})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    int createUser(User user);

    @Update("<script> update users <set> update_date=now() <if test=\"email != null\">,email=#{email}</if> " +
            " </set> WHERE user_id=#{userId}</script>")
    int updateUser(User user);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "creationDate", column = "creation_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "userPassword", column = "password"),
            @Result(property = "country.countryId", column = "country_id"),
            @Result(property = "country.state", column = "c_state"),
            @Result(property = "country.countryName", column = "country_name")
    })
    @Select("<script>  select u.user_id, u.email, u.state, u.creation_date, u.update_date, a.password, c.country_name, c.country_id, c.state as c_state from users u " +
            "inner join access_security a on u.user_id = a.user_id_fk " +
            "inner join countries c on u.country_id_fk = c.country_id and c.state=1 " +
            "where a.state=1 and u.state=1 <if test=\"userId != null\">and user_id=#{userId}</if> <if test=\"email != null\">and email=#{email}</if> </script>")
    User getUser(User user);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "creationDate", column = "creation_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "userPassword", column = "password"),
            @Result(property = "country.countryId", column = "country_id"),
            @Result(property = "country.state", column = "c_state"),
            @Result(property = "country.countryName", column = "country_name")
    })
    @Select("select u.user_id, u.email, u.state, u.creation_date, u.update_date, a.password, c.country_name, c.country_id, c.state as c_state from users u " +
            "inner join access_security a on u.user_id = a.user_id_fk " +
            "inner join countries c on u.country_id_fk = c.country_id and c.state=1 " +
            "where a.state=1 and u.state=1 ")
    List<User> listUser(User user);

    @Update("update users set state=0 WHERE user_id=#{userId}")
    int deleteUser(User user);

}
