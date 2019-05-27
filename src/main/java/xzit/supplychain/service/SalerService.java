package xzit.supplychain.service;

import xzit.supplychain.pojo.*;


import java.util.List;

public interface SalerService {



    List<SalerInventory> showSalerInventory();
    List<WholeSaler> showWholeSaler();

    int getSaler(String username);
    List<SalerOrder> salerOutDeliver(int consumerId);
    List<SWOrder> salerWaitPay(int SalerId);
    List<SWOrder> salerWaitIn(int salerId);
    List<SWOrder> salerReturn(int salerId);
    List<SWOrder> salerWaitEvaluate(int salerId);
    List<SWOrder> salerCanceledOrder(int salerId);
    List<SWOrder> salerEndOrder(int salerId);
    List<SWOrder> salerWaitOut(int salerId);
    List<SalerStock> salerDetail(String wholesalerId);
    List<SalerSubscribe> salerSubscribe(int salerId);

    List<SalerSubscribe>salerAcceptSubscribe(int salerId);

    List<SalerSubscribe>salerRejectSubscribe(int salerId);

    List<SalerSubscribe>salerDiachronicSubscribe(int salerId);
    int changeStatus99(Integer status, int orderId);
    int changeStatus04(Integer status, int orderId);
    int changeStatus01(Integer status, int orderId);
    int changeStatus11(Integer status, int orderId);
    int salerLayPrice(String productId, double price);
    int changeStatus1(Integer oddNumber, int statusId);
    int changeStatus2(Integer oddNumber, int statusId);
    int changeStatus3(Integer oddNumber, int statusId);
    int deleteStock(String productId);

    List<SalerInventory> salerSelectInventory(String productName);
    List<WholeSaler> salerSelectWholeSaler(String userName);



    void salerAckDeliver(int orderId, String productId, int amount);




    int getwholesalerId(String companyName);
}