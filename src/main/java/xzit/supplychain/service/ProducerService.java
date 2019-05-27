package xzit.supplychain.service;

import xzit.supplychain.pojo.Product;
import xzit.supplychain.pojo.ProductAmount;
import xzit.supplychain.pojo.ProductType;
import xzit.supplychain.pojo.WPOrder;

import java.util.List;

public interface ProducerService {
    //获得制造商id
    int getProducerId(String name);
    //获得汽配类型列表
    List<String> getType(String companyName);
    //获得数量
    int getAmount(Product product);
    //生产汽配
    int manufacture(Product product);
    //查询汽配库存
    List<ProductAmount> findAll(String companyName);
    //添加流水线
    int addLine(String typeName,String companyName);
    //查询流水线
    List<ProductType> findLine(String companyName);
    //删除流水线
    int deleteLine(int typeId);
    //查询待发货订单
    List<WPOrder> toDealOrder(int id);
    //查询收货订单
    List<WPOrder> aftersaleOrder(int producerId);
    //检查数量
    int checkAmount(String productId);
    //发货
    void deliver(int orderId,String productId,int amount);
    //查询所有订单
    List<WPOrder> allOrder(int wholesalerId);
    //修改定价
    int updatePrice(String productId,double price);
    //修改订单状态
    void changeStatus(int orderId,int status);
}
