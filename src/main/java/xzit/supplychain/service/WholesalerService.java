package xzit.supplychain.service;

import xzit.supplychain.pojo.*;

import java.util.List;

public interface WholesalerService {
     List<Producer> showProducer();
     List<ProductAmount> detail(String companyName);
     List<WholesalerAmount> findAll(int wholesalerId);
     int getWholesaler(String username);
     int addOrder(WPOrder order);
     int getProducerId(String companyName);
     List<WPOrder> waitIn(int wholesalerId);
     List<WPOrder> waitOut(int wholesalerId);
     List<WPOrder> waitPay(int wholesalerId);
     int cancelOrder(int orderId);
     List<WPOrder> waitEvaluate(int wholesalerId);
     List<WPOrder> endOrder(int wholesalerId);
     List<WPOrder> expend(int wholesalerId);
     List<SWOrder> income(int wholesalerId);
     List<SWOrder> outDeliver(int salerId);
     void ackDeliver(int orderId, String productId, int amount);
     int layPrice(String productId, double price);
}
