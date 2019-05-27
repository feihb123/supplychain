package xzit.supplychain.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xzit.supplychain.pojo.*;


import java.util.List;

@Mapper
@Repository
public interface SalerMapper {
    @Select("select productId,companyName,productName,type,amount,price from saler_inventory")
    List<SalerInventory> showSalerInventory();

    //商品库存

    @Select("select id,userName,photo,location,phone from wholesaler")
    List<WholeSaler> showWholeSaler();

    @Select("select * from saler_order join order_status on saler_order.status=order_status.statusId"+
            " where consumerId = #{consumerId} and status=1")
    List<SalerOrder> salerOutDeliver(int consumerId);

    //客户订单

    @Select("select id from saler where username=#{username}")
    int getSalerId(String username);



    @Select("select * from sw_order join order_status on sw_order.status=order_status.statusId"+
            " where salerId = #{salerId} and status=0")
    List<SWOrder> salerWaitPay(int salerId);
    //待付款订单



    @Select("select * from sw_order join order_status on sw_order.status=order_status.statusId"+
            " where salerId = #{salerId} and status=2")
    List<SWOrder> salerWaitIn(int salerId);
    //待收货订单


    @Select("select * from sw_order join order_status on sw_order.status=order_status.statusId"+
            " where salerId = #{salerId} and status=11")
    List<SWOrder> salerReturn(int salerId);
    //退换记录


    @Select("select * from sw_order join order_status on sw_order.status=order_status.statusId"+
            " where salerId = #{salerId} and status=3")
    List<SWOrder> salerWaitEvaluate(int salerId);
    //待评价订单

    @Select("select * from sw_order join order_status on sw_order.status=order_status.statusId"+
            " where salerId = #{salerId} and status=4")
    List<SWOrder> salerEndOrder(int salerId);
    //已完成订单

    @Select("select * from sw_order join order_status on sw_order.status=order_status.statusId"+
            " where salerId = #{salerId} and status=99")
    List<SWOrder> salerCanceledOrder(int salerId);
    //已取消订单

    @Select("select * from sw_order join order_status on sw_order.status=order_status.statusId"+
            " where salerId = #{salerId} and status=1")
    List<SWOrder> salerWaitOut(int salerId);
    //待发货订单

    @Select("select product.productId,product.companyName,product.productName,product.type,wholesaler_inventory.amount,wholesaler_inventory.price " +
            "from product  join wholesaler_inventory on product.productId=wholesaler_inventory.productId " +
            "where wholesaler_inventory.wholesalerId =#{wholeSalerId} and wholesaler_inventory.price !=0")
    List<SalerStock> salerDetail(String wholesalerId);

    //采购


    @Select("select * from saler_subscribe"+
            " where salerId = #{salerId} and statusId=0")
    List<SalerSubscribe> salerSubscribe(int salerId);
    //待处理预约

    @Select("select * from saler_subscribe"+
            " where salerId = #{salerId} and statusId=1")
    List<SalerSubscribe> salerAcceptSubscribe(int salerId);
//已接受预约

    @Select("select * from saler_subscribe"+
            " where salerId = #{salerId} and statusId=2")
    List<SalerSubscribe>salerRejectSubscribe(int salerId);
//已拒绝预约

    @Update("update sw_order set status = 99 where orderId = #{orderId}")
    int changeStatus99(@Param("status") Integer status, @Param("orderId") int orderId);
//取消订单状态更改


    @Update("update sw_order set status = 4 where orderId = #{orderId}")
    int changeStatus04(@Param("status") Integer status, @Param("orderId") int orderId);
    //确认收货订单状态更改

    @Update("update sw_order set status = 1 where orderId = #{orderId}")
    int changeStatus01(@Param("status") Integer status, @Param("orderId") int orderId);
    //确认付款订单状态更改

    @Update("update sw_order set status = 11 where orderId = #{orderId}")
    int changeStatus11(@Param("status") Integer status, @Param("orderId") int orderId);
    //退货订单状态更改


    @Update("update saler_subscribe set statusId = 1 where oddNumber = #{oddNumber}")
    int changeStatus1(@Param("oddNumber") Integer oddNumber, @Param("statusId") int statusId);
//预约订单状态更改

    @Update("update saler_subscribe set statusId = 2 where oddNumber = #{oddNumber}")
    int changeStatus2(@Param("oddNumber") Integer oddNumber, @Param("statusId") int statusId);
    //预约订单状态更改

    @Update("update saler_subscribe set statusId = 3 where oddNumber = #{oddNumber}")
    int changeStatus3(@Param("oddNumber") Integer oddNumber, @Param("statusId") int statusId);
//预约订单状态更改

    @Select("select * from saler_subscribe"+
            " where salerId = #{salerId} and statusId=3")
    List<SalerSubscribe>salerDiachronicSubscribe(int salerId);
    //历史预约


    @Update("update saler_inventory set price=#{price} where productId=#{productId}")
    int salerLayPrice(@Param("productId") String productId, @Param("price") double price);

    @Delete("delete from saler_inventory where productId=#{productId}")
    int deleteStock(String productId);
    //删除




    @Update("update saler_inventory set amount = amount+#{amount} " +
            "where productId = #{productId}")
    void salerAckDeliver(String productId, int amount);

    @Update("update sw_order set status = #{status} where orderId = #{orderId}")
    void changeStatus(int orderId, int status);



    @Select("select id from wholesalerId where companyName=#{companyName}")
    int getwholesalerId(String companyName);

    @Select("select productId,productName,companyName,amount,type,price from saler_inventory where productName like CONCAT(CONCAT('%',#{productName},'%'))")
    List<SalerInventory> salerSelectInventory(String productName);
//搜索框

    @Select("select id,userName,photo,location,phone from wholesaler where userName like CONCAT(CONCAT('%',#{userName},'%'))")
    List<WholeSaler> salerSelectWholeSaler(String userName);
//搜索框



}