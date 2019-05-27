package xzit.supplychain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xzit.supplychain.pojo.Message;

@Mapper
@Repository
public interface MessageMapper {
    @Insert("insert into communication values(null," +
            "#{consumerId},#{salerId},#{from},#{to},#{time},#{content})")
    int store(Message message);


}
