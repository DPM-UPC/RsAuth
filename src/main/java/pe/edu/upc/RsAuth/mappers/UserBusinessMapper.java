package pe.edu.upc.RsAuth.mappers;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import pe.edu.upc.RsAuth.models.User;
import pe.edu.upc.RsAuth.models.UserBusiness;

import java.util.List;

@Mapper
@Component
public interface UserBusinessMapper {

    @Results({
            @Result(property = "userBusinessId", column = "user_business_id"),
            @Result(property = "state", column = "state"),
            @Result(property = "creationDate", column = "creation_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "userIdFk", column = "user_id_fk"),
            @Result(property = "businessIdFk", column = "business_id_fk")
    })
    @Select("select b.user_business_id, b.state, b.creation_date, b.update_date, b.user_id_fk, b.business_id_fk from users_business b " +
            "where b.state=1 and user_id_fk=#{userId}")
    List<UserBusiness> listUserBusiness(User user);

    @Insert("insert into users_business (state,creation_date, user_id_fk, business_id_fk) " +
            "values(1,now(), #{userIdFk}, #{businessIdFk})")
    @Options(useGeneratedKeys = true, keyProperty = "userBusinessId", keyColumn = "user_business_id")
    int createUserBusiness(UserBusiness userBusiness);
}
