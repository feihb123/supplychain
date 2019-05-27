package xzit.supplychain.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xzit.supplychain.pojo.*;

import java.util.List;

@Mapper
@Repository
public interface ProducerMapper {
    @Select("select id from producer where username = #{username{}")
    int getProducerId(String username);


    @Select("select typeName from productType where companyName=#{companyName}")
    List<String> getType(String companyName);

    @Insert("insert into product values" +
            "(#{productId},#{companyName},#{productName},#{type})")
    int createProduct(Product product);

    @Insert("insert into producer_inventory values" +
            "(#{productId},0,1)")
    int createProducerInven(Product product);

    @Select("select * from product where productId=#{productId} ")
    Product checkProduct(Product product);

    @Select("select * from product where productId=#{productId} " +
            "and companyName = #{companyName} and productName = #{productName} and type = #{type}")
    Product checkProductInven(Product product);

    @Select("select * from producer_inventory where productId=#{productId}")
    Product checkProducerInven(Product product);

    @Update("update producer_inventory set amount=amount+1 where " +
            "productId=#{productId}")
    int manufacture(Product product);

    @Select("select product.productId,product.companyName,product.productName,product.type,producer_inventory.amount,producer_inventory.price " +
            "from product  join producer_inventory on product.productId=producer_inventory.productId " +
            "where product.companyName =#{companyName}")
    List<ProductAmount> findAll(String companyName);

    @Select("select amount from producer_inventory where productId=#{productId}")
    int getAmount(Product product);

    @Select("select * from productType where  companyName=#{companyName} and typeName=#{typeName}")
    ProductType checkLine(String typeName,String companyName);
    @Insert("insert into productType values(null,#{typeName},#{companyName})")
    int addLine(String typeName,String companyName);

    @Select("select * from productType where companyName=#{companyName}")
    List<ProductType> findLine(String companyName);

    @Delete("delete from productType where typeId=#{typeId}")
    int deleteLine(int typeId);
    //修改定价
    @Update("update producer_inventory set price=#{price} where productId=#{productId}")
    int updatePrice(String productId,double price);

    //查询未处理订单
    @Select("select * from wp_order where producerId = #{producerId} and status = 1")
    List<WPOrder> toDealOrder(int producerId);
    //查询售后订单
    @Select("select * from wp_order join wholesaler on wp_order.wholesalerId=wholesaler.id where producerId = #{producerId} and status = 9")
    List<WPOrder> aftersaleOrder(int producerId);
    //查询全部订单
    @Select("select * from wp_order a,order_status b,wholesaler c " +
            "where a.status=b.statusId and a.wholesalerId=c.id and  producerId = #{producerId} ")
    List<WPOrder> allOrder(int producerId);
    //改变订单状态
    @Update("update wp_order set status = #{status} where orderId = #{orderId}")
    void changeStatus(int orderId,int status);
    //检查库存
    @Select("select amount from producer_inventory where productId = #{productId}")
    int checkAmount(String productId);

    //发货减库存
    @Update("update producer_inventory set amount = amount-#{amount} " +
            "where productId = #{productId}")
    void deliver(String productId,int amount);


}
