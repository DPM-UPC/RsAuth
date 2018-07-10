package pe.edu.upc.RsAuth.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import pe.edu.upc.RsAuth.models.User;
import pe.edu.upc.RsAuth.models.UserBusiness;

import java.util.List;

@Mapper
@Component
public interface UserBusinessMapper {

    @Results({
            @Result(property = "userBusinessId", column = "user_business_id"),
            @Result(property = "userName", column = "business_description"),
            @Result(property = "email", column = "state"),
            @Result(property = "state", column = "creation_date"),
            @Result(property = "creationDate", column = "update_date"),
            @Result(property = "updateDate", column = "user_id_fk"),
            @Result(property = "userPassword", column = "business_id_fk")
    })
    @Select("select b.user_business_id, b.business_description, b.state, b.creation_date, b.update_date, b.user_id_fk, b.business_id_fk from users_business b " +
            "where b.state=1 and user_id_fk=#{userId}")
    List<UserBusiness> listUserBusiness(User user);
}
