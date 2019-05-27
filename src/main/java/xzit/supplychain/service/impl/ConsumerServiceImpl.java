package xzit.supplychain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzit.supplychain.mapper.ConsumerMapper;
import xzit.supplychain.pojo.*;
import xzit.supplychain.service.ConsumerService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    ConsumerMapper consumerMapper;

    @Override
    public List<Saler> getSaler() {
        return consumerMapper.getSaler();
    }

    @Override
    public List<Saler> getChatSaler(int id) {
        return consumerMapper.getChatSaler(id);
    }

    @Override
    public List<ProductAmount> showSalerDetail(int id) {
        return consumerMapper.showSalerDetail(id);
    }

    @Override
    public String getSalerWelcome(int id) {
        return consumerMapper.getSalerWelcome(id);
    }

    @Override
    public List<ShoppingCart> showCarts(int cid) {
        return consumerMapper.findCarts(cid);
    }

    @Override
    public List<String> findShopName() {
        return consumerMapper.findShopName();
    }

    @Override
    public int addCart(ShoppingCart shoppingCart) {

            Object obj= consumerMapper.findCartSid(shoppingCart);

            if(null == obj){
                consumerMapper.addCart(shoppingCart);
            }else{
                consumerMapper.updateCart(Integer.parseInt(obj.toString()),1);
            }
            return 1;
    }

    @Override
    public int updateCart(int id, int amount) {
        return consumerMapper.updateCart(id,amount);
    }

    @Override
    public int delCart(int sid) {
        return consumerMapper.delCart(sid);
    }

    @Override
    public int subCart(int sid) {
        int amount = consumerMapper.findCartAmount(sid);
        if (amount != 1) {
            updateCart(sid, -1);
        } else {
            delCart(sid);
        }
        return 1;
    }

    @Override
    public int findCartAmount(int sid) {
        return consumerMapper.findCartAmount(sid);
    }
    //查询购物车总价
    @Override
    public double findCartAmountAndPrice(int sid) {
        return consumerMapper.findCartAmountAndPrice(sid);
    }
    //查询购物车选中结果集
    @Override
    public List<ShoppingCart> findCartBySid(List<Integer> list) {
        List<ShoppingCart> carts = new ArrayList<>();
        for (Integer sid:list) {
            carts.add(consumerMapper.findCartBySid(sid));
        }
        return carts;
    }
    //查找默认地址
    @Override
    public Address findDefaultAdd(int consumerId) {
        return consumerMapper.findDefaultAdd(consumerId);
    }

    @Override
    public int findSalerId(int sid) {
        return consumerMapper.findSalerId(sid);
    }

    @Override
    public String findProductId(int sid) {
        return consumerMapper.findProductId(sid);
    }

    @Override
    public double findPrice(int salerId, String productId) {
        return consumerMapper.findPrice(salerId,productId);
    }

    @Override
    public int generateSOrder(int sid,CSOrder order) {
        consumerMapper.delShoppingCartBySid(sid);
        return consumerMapper.addCSOrder(order);
    }

    @Override
    public List<CSOrder> findAllOrders(int id) {
        return consumerMapper.findAllOrders(id);
    }

    @Override
    public List<CSOrder> findOrders(int status,int id) {
        return consumerMapper.findOrders(status,id);
    }

    @Override
    public int changeOrderStauts(int status, long oid) {
        return consumerMapper.updateOrderStatus(status,oid);
    }

    /**
     * 发评论
     */
    @Override
    public int deliverEvaluation(long oid, String eva) {
        CSOrder order = consumerMapper.findOrderByOid(oid);
        consumerMapper.addCSEvaluation(order.getConsumerId(),
                order.getSalerId(),order.getProductId(),eva);
        return 0;
    }

    @Override
    public Consumer findConsumerById(int id) {
        return consumerMapper.findConsumerById(id);
    }

    @Override
    public int savePersonalImg(String photo, int id) {
        return consumerMapper.savePersonalImg(photo,id);
    }

    @Override
    public int savePersonalInfo(String nickname, String phone, String email, int id) {
        return consumerMapper.savePersonalInfo(nickname, phone, email, id);
    }

    @Override
    public int saveAppointment(int cid, int sid, Timestamp time) {
        return consumerMapper.saveAppointment(cid,sid,time);
    }

    @Override
    public List<Appointment> findAllAppointment(int cid) {
        return consumerMapper.findAllAppointment(cid);
    }

    @Override
    public int deleteAppointment(int aid) {
        return consumerMapper.deleteAppointment(aid);
    }

    @Override
    public List<Saler> findSalerfuzzy(String content) {
        return consumerMapper.findSalerfuzzy(content);
    }

    @Override
    public List<ProductAmount> showFuzzyDetail(String content) {
        return consumerMapper.showFuzzyDetail(content);
    }
}
