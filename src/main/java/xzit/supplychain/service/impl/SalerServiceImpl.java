package xzit.supplychain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzit.supplychain.mapper.SalerMapper;
import xzit.supplychain.pojo.*;
import xzit.supplychain.service.SalerService;
import java.util.List;
@Service
public class SalerServiceImpl implements SalerService {
    @Autowired
    SalerMapper SalerMapper;




    @Override
    public List<SalerInventory> showSalerInventory() {
        List<SalerInventory> list = SalerMapper.showSalerInventory();

        return list;
    }
@Override
    public List<WholeSaler> showWholeSaler(){
        List<WholeSaler> list =SalerMapper.showWholeSaler();
        return list;
}

    @Override
    public int getSaler(String username) {
        return SalerMapper.getSalerId(username);
    }
    //


    @Override
    public List<SalerOrder> salerOutDeliver(int consumerId) {
        return SalerMapper.salerOutDeliver(consumerId);
    }
    //客户订单

    @Override
    public List<SWOrder> salerWaitPay(int salerId) {
        return SalerMapper.salerWaitPay(salerId);
    }
    //待付款

    @Override
    public List<SWOrder> salerReturn(int salerId) {
        return SalerMapper.salerReturn(salerId);
    }
    //待收货

    @Override
    public List<SWOrder> salerWaitIn(int salerId) {
        return SalerMapper.salerWaitIn(salerId);
    }
    //待收货

    @Override
    public List<SWOrder> salerWaitEvaluate(int salerId) {
        return SalerMapper.salerWaitEvaluate(salerId);
    }
    //待评价订单

    @Override
    public List<SWOrder> salerEndOrder(int salerId) {
        return SalerMapper.salerEndOrder(salerId);
    }
    //已完成订单


    @Override
    public List<SWOrder> salerCanceledOrder(int salerId) {
        return SalerMapper.salerCanceledOrder(salerId);
    }
    //已取消订单


    @Override
    public List<SWOrder> salerWaitOut(int salerId) {
        return SalerMapper.salerWaitOut(salerId);
    }
    //待发货

    @Override
    public List<SalerStock> salerDetail(String wholesalerId) {
        List<SalerStock> list = SalerMapper.salerDetail(wholesalerId);
        return list;
    }

    @Override
    public List<SalerSubscribe> salerSubscribe(int salerId) {
        return SalerMapper.salerSubscribe(salerId);
    }
    //预约修理

    @Override
    public List<SalerSubscribe>salerAcceptSubscribe(int salerId) {
        return SalerMapper.salerAcceptSubscribe(salerId);
    }
    //预约修理

    @Override
    public List<SalerSubscribe>salerRejectSubscribe(int salerId) {
        return SalerMapper.salerRejectSubscribe(salerId);
    }
    //预约修理

    @Override
    public List<SalerSubscribe>salerDiachronicSubscribe(int salerId) {
        return SalerMapper.salerDiachronicSubscribe(salerId);
    }
    //预约修理




    @Override
    public int salerLayPrice(String productId, double price) {
        return SalerMapper.salerLayPrice(productId, price);
    }
    //修改价格

    @Override
    public int changeStatus1(Integer oddNumber, int statusId) {
        return SalerMapper.changeStatus1(oddNumber, statusId);
    }
    //修改预约表状态

    @Override
    public int changeStatus2(Integer oddNumber, int statusId) {
        return SalerMapper.changeStatus2(oddNumber, statusId);
    }
    //修改预约表状态


    @Override
    public int changeStatus3(Integer oddNumber, int statusId) {
        return SalerMapper.changeStatus3(oddNumber, statusId);
    }
    //修改预约表状态


    @Override
    public int changeStatus99(Integer status, int orderId) {
        return SalerMapper.changeStatus99(status, orderId);
    }
    //修改取消订单状态值

    @Override
    public int changeStatus04(Integer status, int orderId) {
        return SalerMapper.changeStatus04(status, orderId);
    }
    //修改确认收货状态值

    @Override
    public int changeStatus01(Integer status, int orderId) {
        return SalerMapper.changeStatus01(status, orderId);
    }
    //修改确认付款状态值


    @Override
    public int changeStatus11(Integer status, int orderId) {
        return SalerMapper.changeStatus11(status, orderId);
    }
    //修改退货订单状态值






    @Override
    public int deleteStock(String productId) {
        return SalerMapper.deleteStock(productId);
    }

    //删除库存







    @Override
    public void salerAckDeliver(int orderId,String productId, int amount) {
        SalerMapper.salerAckDeliver(productId, amount);
        SalerMapper.changeStatus(orderId,3);
    }
    //


    @Override
    public int getwholesalerId(String companyName) {
        return SalerMapper.getwholesalerId(companyName);
    }
    //

    @Override
    public List<SalerInventory> salerSelectInventory(String productName) {
        List<SalerInventory> list=SalerMapper.salerSelectInventory(productName);
        return list;
    }


    @Override
    public List<WholeSaler> salerSelectWholeSaler(String userName) {
        List<WholeSaler> list=SalerMapper.salerSelectWholeSaler(userName);
        return list;
    }



}
