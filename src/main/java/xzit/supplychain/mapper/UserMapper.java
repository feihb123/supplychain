package xzit.supplychain.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xzit.supplychain.pojo.Consumer;
import xzit.supplychain.pojo.User;

@Mapper
@Repository
public interface UserMapper {
    @Select("select password from ${status} where username=#{username}")
    String getPWD(User user);

    @Select("select companyName from producer where username=#{username}")
    String getcompanyName(String username);

    @Select("select id from ${status} where username=#{username}")
    String getId(User user);
    /**
     * 注册新用户（消费者）
     */
    @Insert("insert into consumer values(null,#{username},#{password},#{nickname}," +
            "null,#{phone},#{email})")
    int saveConsumer(Consumer consumer);
}
