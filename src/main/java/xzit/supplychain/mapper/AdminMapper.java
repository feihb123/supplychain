package xzit.supplychain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import xzit.supplychain.pojo.Saler;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    @Select("select * from saler where status=1")
    List<Saler> getSaler();

    @Update("update saler set status = 9 where id = #{salerId}")
    int putOff(int salerId);

    @Select("select * from saler where status=2")
    List<Saler> shopcheck();

    @Update("update saler set status = 1 where id = #{salerId}")
    int add(int salerId);
}
