package xzit.supplychain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xzit.supplychain.pojo.User;

@Mapper
@Repository
public interface InterceptorMapper {

    @Select("select count(id) from ${status} where username = #{username} and password = #{password} and id = #{id}")
    int interceptor(User user);
}
