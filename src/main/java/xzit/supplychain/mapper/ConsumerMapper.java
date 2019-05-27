package xzit.supplychain.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xzit.supplychain.pojo.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface ConsumerMapper {
    @Select("select * from saler")
    List<Saler> getSaler();

    @Select("select distinct id,shopname,photo from  communication " +
            "join saler on communication.salerId = saler.id " +
            "where communication.consumerId = #{id} " +
            "order by communication.time desc limit 5")
    List<Saler> getChatSaler(int id);

    @Select("select productId,productName,companyName,type," +
            "amount,price,photo from saler_inventory where salerId = #{id} " +
            "and amount != 0")
    List<ProductAmount> showSalerDetail(int id);


    @Select("select welcome from saler where id = #{id} limit 1")
    String getSalerWelcome(int id);

    @Select("select amount from shoppingcart where sid = #{sid}")
    int findCartAmount(int sid);

    @Select("select sid from shoppingcart where productId = #{productId}" +
            " and salerId = #{salerId} and consumerId = #{consumerId} ")
    Object findCartSid(ShoppingCart shoppingCart);

    @Insert("insert into shoppingcart values(null,#{consumerId},#{salerId},#{productId},1)")
    int addCart(ShoppingCart shoppingCart);

    @Update("update shoppingcart set amount = amount+#{amount} where sid = #{id} ")
    int updateCart(int id,int amount);

    //查询购物车中东西
    @Select("select sid,shopname,saler_inventory.photo,productName,price,shoppingcart.amount " +
            "from shoppingcart join saler_inventory " +
            "on shoppingcart.productId = saler_inventory.productId " +
            "JOIN saler on saler.id = shoppingcart.salerId " +
            "where consumerId = #{cid}")
    List<ShoppingCart> findCarts(int cid);

    //查询购物车中商家
    @Select("select DISTINCT shopname from shoppingcart JOIN saler on saler.id = shoppingcart.salerId")
    List<String> findShopName();

    @Delete("delete from shoppingcart where sid = #{sid}")
    int delCart(int sid);

    @Select("select price*shoppingcart.amount from shoppingcart " +
            "join saler_inventory on shoppingcart.productId = saler_inventory.productId " +
            "where sid = #{sid}")
    double findCartAmountAndPrice(int sid);

    @Select("select  shopname,saler_inventory.photo,productName,price,shoppingcart.amount " +
            "from shoppingcart join saler_inventory " +
            "on shoppingcart.productId = saler_inventory.productId " +
            "JOIN saler on saler.id = shoppingcart.salerId where sid = #{sid}")
    ShoppingCart findCartBySid(int sid);


    //查找默认地址
    @Select("select * from address where consumerId = #{consumerId} and defaultAdd = 1")
    Address findDefaultAdd(int consumerId);

    //清除默认地址
    @Update("update address set defaultAdd = 0 where consumerId = #{consumerId} and defaultAdd = 1")
    int clearDefaultAdd(int consumerId);

    //设置默认地址
    @Update("update address set defaultAdd = 1 where consumerId = #{consumerId} and aid = #{aid}")
    int setDefaultAdd(int consumerId,int sid);

    //根据购物车sid获得salerId以生成订单
    @Select("select salerId from shoppingcart where sid = #{sid}")
    int findSalerId(int sid);

    //根据购物车productId获得salerId以生成订单
    @Select("select productId from shoppingcart where sid= #{sid}")
    String findProductId(int sid);

    //根据购物车salerId,productId获得价格
    @Select("select price from saler_inventory \n"+
            " where salerId = #{salerId} and productId = #{productId}")
    double findPrice(int salerId,String productId);

    //生成订单
    @Insert("insert into cs_order values(#{orderId},#{consumerId}," +
            "#{salerId},#{time},#{productId},#{amount},#{price}," +
            "#{address},#{tel},1)")
    int addCSOrder(CSOrder order);
    //生成订单同时移出购物车
    @Delete("delete from shoppingcart where sid = #{sid}")
    int delShoppingCartBySid(int sid);

    //查全部订单
    @Select("SELECT orderId,consumerId,cs_order.salerId,time,cs_order.productId,saler_inventory.productName,\n" +
            "cs_order.amount,cs_order.price,address,cs_order.tel,cs_order.status,meaning,photo\n" +
            "FROM cs_order join  order_status \n" +
            "on cs_order.`status` = order_status.statusId\n" +
            "join saler_inventory \n" +
            "on saler_inventory.productId = cs_order.productId " +
            "where consumerId = #{consumerId}")
    List<CSOrder> findAllOrders(int consumerId);

    //按状态查订单
    @Select("SELECT orderId,consumerId,cs_order.salerId,time,cs_order.productId,saler_inventory.productName,\n" +
            "cs_order.amount,cs_order.price,address,cs_order.tel,cs_order.status,meaning,photo\n" +
            "FROM cs_order join  order_status \n" +
            "on cs_order.`status` = order_status.statusId\n" +
            "join saler_inventory \n" +
            "on saler_inventory.productId = cs_order.productId\n" +
            "where status = #{status} and " +
            "consumerId = #{consumerId}")
    List<CSOrder> findOrders(int status,int consumerId);

    /**
     *改变订单状态
     */
    @Update("update cs_order set status = #{status} where orderId = #{oid}")
    int updateOrderStatus(int status,long oid);

    /**
     * 按id查询订单
     */
    @Select("select * from cs_order where orderId= #{orderId}")
    CSOrder findOrderByOid(long orderId);

    /**
     * 添加评论
     */
    @Insert("insert into evaluate values(null,#{consumerId},#{salerId},#{productId},#{eva})")
    int addCSEvaluation(int consumerId,int salerId,String productId,String eva);

    /**
     * 按id查询个人信息
     */
    @Select("select * from consumer where id = #{id}")
    Consumer findConsumerById(int id);

    @Update("update consumer set nickname = #{nickname},phone = #{phone}," +
            "email = #{email} where id = #{id}")
    int savePersonalInfo(String nickname,String phone,String email,int id);

    @Update("update consumer set photo = #{photo} where id = #{id}")
    int savePersonalImg(String photo,int id);

    /**
     * 保存预约信息
     */
    @Insert("insert into appointment values(null,#{cid},#{sid},#{time},null)")
    int saveAppointment(int cid, int sid, Timestamp time);

    /**
     * 查全部预约
     */
    @Select("SELECT aid,time,shopname,photo  FROM appointment join saler on saler.id = appointment.salerId" +
            " where consumerId = #{cid}")
    List<Appointment> findAllAppointment(int cid);

    /**
     * 取消预约
     */
    @Delete("delete from appointment where aid = #{aid}")
    int deleteAppointment(int aid);

    /**
     * 模糊查询汽配商
     */
    @Select("select * from saler where shopname like  concat(concat('%',#{content}),'%')")
    List<Saler> findSalerfuzzy(String content);
    /**
     * 模糊查询汽配商
     */
    @Select("select salerId,productId,productName,companyName,type," +
            "amount,price,photo from saler_inventory where amount != 0 and productName " +
            "like  concat(concat('%',#{content}),'%') ")
    List<ProductAmount> showFuzzyDetail(String content);


}

