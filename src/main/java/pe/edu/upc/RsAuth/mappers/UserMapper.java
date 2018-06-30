package pe.edu.upc.RsAuth.mappers;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import pe.edu.upc.RsAuth.domains.User;

import java.util.List;

/**
 * Created by Paolo Ortega on 25/06/2018.
 */
@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user (user_name,email,state,creation_date, country_id) " +
            "values(#{userName},#{email},1,now(), #{country.countryId})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    int createUser(User user);

    @Update("<script> update user <set> update_date=now() <if test=\"userName != null\">,user_name=#{userName}</if> <if test=\"email != null\">,email=#{email}</if> " +
            " </set> WHERE user_id=#{userId}</script>")
    int updateUser(User user);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "creationDate", column = "creation_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "userPassword", column = "password")
    })
    @Select("<script>  select u.user_id, u.user_name, u.email, u.state, u.creation_date, u.update_date, a.password from user u " +
            "inner join access_security a on u.user_id = a.user_id_fk " +
            "where a.state=1 and u.state=1 <if test=\"userId != null\">and user_id=#{userId}</if> <if test=\"userName != null\">and user_name=#{userName}</if> </script>")
    User getUser(User user);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "creationDate", column = "creation_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "userPassword", column = "password")
    })
    @Select("select u.user_id, u.user_name, u.email, u.state, u.creation_date, u.update_date, a.password from user u " +
            "inner join access_security a on u.user_id = a.user_id_fk " +
            "where a.state=1 and u.state=1 ")
    List<User> listUser(User user);

    @Update("update user set state=0 WHERE user_id=#{userId}")
    int deleteUser(User user);

}
