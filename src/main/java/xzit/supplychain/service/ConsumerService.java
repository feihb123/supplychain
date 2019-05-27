package xzit.supplychain.service;

import xzit.supplychain.pojo.*;

import java.sql.Timestamp;
import java.util.List;

public interface ConsumerService {
    List<Saler> getSaler();
    List<Saler> getChatSaler(int id);
    List<ProductAmount> showSalerDetail(int id);
    String getSalerWelcome(int id);
    int addCart(ShoppingCart shoppingCart);
    List<ShoppingCart> showCarts(int cid);
    List<String> findShopName();
    int delCart(int sid);
    int subCart(int sid);
    int updateCart(int id,int amount);
    int findCartAmount(int sid);
    double findCartAmountAndPrice(int sid);
    List<ShoppingCart> findCartBySid(List<Integer> list);
    Address findDefaultAdd(int consumerId);
    int findSalerId(int sid);
    String findProductId(int sid);
    double findPrice(int salerId,String productId);
    int generateSOrder(int sid,CSOrder order);
    List<CSOrder> findAllOrders(int id);
    List<CSOrder> findOrders(int status,int id);
    int changeOrderStauts(int status,long oid);
    int deliverEvaluation(long oid,String eva);
    Consumer findConsumerById(int id);
    int savePersonalImg(String photo,int id);
    int savePersonalInfo(String nickname,String phone,String email,int id);
    int saveAppointment(int cid, int sid, Timestamp time);
    List<Appointment> findAllAppointment(int cid);
    int deleteAppointment(int aid);
    List<Saler> findSalerfuzzy(String content);
    List<ProductAmount> showFuzzyDetail(String content);
}
