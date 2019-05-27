package xzit.supplychain.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xzit.supplychain.pojo.*;

import java.util.List;

@Mapper
@Repository
public interface WholesalerMapper {
    @Select("select id,companyName,photo,location,phone from producer")
    List<Producer> showProducer();

    @Select("select product.productId,product.companyName,product.productName,product.type,producer_inventory.amount,producer_inventory.price " +
            "from product  join producer_inventory on product.productId=producer_inventory.productId " +
            "where product.companyName =#{companyName} and producer_inventory.price !=0")
    List<ProductAmount> detail(String companyName);

    @Select("select product.productId,product.companyName,product.productName,product.type,wholesaler_inventory.amount,wholesaler_inventory.price " +
            "from product,wholesaler_inventory where product.productId=wholesaler_inventory.productId " +
            "and wholesaler_inventory.wholesalerId =#{wholesalerId}")
    List<WholesalerAmount> findAll(int wholesalerId);

    @Select("select id from wholesaler where username=#{username}")
    int getWholesalerId(String username);

    @Insert("insert into wp_order values" +
            "(null,#{wholesalerId},#{producerId},#{time},#{productId},#{amount},#{price},#{address},1)")
    int addOrder(WPOrder order);

    @Select("select id from producer where companyName=#{companyName}")
    int getProducerId(String companyName);

    @Select("select * from wp_order join order_status on wp_order.status=order_status.statusId"+
            " where wholesalerId = #{wholesalerId} and status=2")
    List<WPOrder> waitIn(int wholesalerId);
    @Select("select * from wp_order join order_status on wp_order.status=order_status.statusId"+
            " where wholesalerId = #{wholesalerId} and status=1")
    List<WPOrder> waitOut(int wholesalerId);
    @Select("select * from wp_order join order_status on wp_order.status=order_status.statusId"+
            " where wholesalerId = #{wholesalerId} and status=3")
    List<WPOrder> waitEvaluate(int wholesalerId);
    @Select("select * from wp_order join order_status on wp_order.status=order_status.statusId"+
            " where wholesalerId = #{wholesalerId} and status=4")
    List<WPOrder> endOrder(int wholesalerId);
    @Select("select * from wp_order join order_status on wp_order.status=order_status.statusId"+
            " where wholesalerId = #{wholesalerId} and status=0")
    List<WPOrder> waitPay(int wholesalerId);

    @Select("select * from sw_order join order_status on sw_order.status=order_status.statusId"+
            " where salerId = #{salerId} and status=1")
    List<SWOrder> outDeliver(int salerId);

    @Update("update wp_order set status = #{status} where orderId = #{orderId}")
    void changeStatus(int orderId, int status);

    @Update("update wholesaler_inventory set amount = amount+#{amount} " +
            "where productId = #{productId}")
    void ackDeliver(String productId, int amount);

    @Select("select time,productId,amount,price,amount*price as total from wp_order where wholesalerId = #{wholesalerId}")
    List<WPOrder> expend(int wholesalerId);
    @Select("select time,productId,amount,price,amount*price as total from sw_order where wholesalerId = #{wholesalerId}")
    List<SWOrder> income(int wholesalerId);

    @Update("update wholesaler_inventory set price=#{price} where productId=#{productId}")
    int layPrice(String productId, double price);

    @Delete("delete from wp_order where orderId=#{orderId} and status=0")
    int cancelOrder(int orderId);
}
