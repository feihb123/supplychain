package xzit.supplychain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzit.supplychain.mapper.WholesalerMapper;
import xzit.supplychain.pojo.*;
import xzit.supplychain.service.WholesalerService;

import java.util.List;

@Service
public class WholesalerServiceImpl implements WholesalerService {
    @Autowired
    WholesalerMapper wholesalerMapper;

    @Override
    public List<Producer> showProducer() {
        List<Producer> list = wholesalerMapper.showProducer();
        return list;
    }

    @Override
    public List<ProductAmount> detail(String companyName) {
        List<ProductAmount> list = wholesalerMapper.detail(companyName);
        return list;
    }

    @Override
    public List<WholesalerAmount> findAll(int wholesalerId) {
        return  wholesalerMapper.findAll(wholesalerId);
    }

    @Override
    public int getWholesaler(String username) {
        return wholesalerMapper.getWholesalerId(username);
    }

    @Override
    public int getProducerId(String companyName) {
        return wholesalerMapper.getProducerId(companyName);
    }

    @Override
    public List<WPOrder> waitIn(int wholesalerId) {
        return wholesalerMapper.waitIn(wholesalerId);
    }

    @Override
    public List<WPOrder> waitOut(int salerId) {
        return wholesalerMapper.waitOut(salerId);
    }

    @Override
    public List<WPOrder> waitPay(int wholesalerId) {
        return wholesalerMapper.waitPay(wholesalerId);
    }

    @Override
    public int cancelOrder(int orderId) {
        return wholesalerMapper.cancelOrder(orderId);
    }

    @Override
    public List<WPOrder> waitEvaluate(int wholesalerId) {
        return wholesalerMapper.waitEvaluate(wholesalerId);
    }

    @Override
    public List<WPOrder> endOrder(int wholesalerId) {
        return wholesalerMapper.endOrder(wholesalerId);
    }

    @Override
    public List<WPOrder> expend(int wholesalerId) {
        return wholesalerMapper.expend(wholesalerId);
    }

    @Override
    public List<SWOrder> income(int wholesalerId) {
        return wholesalerMapper.income(wholesalerId);
    }

    @Override
    public List<SWOrder> outDeliver(int salerId) {
        return wholesalerMapper.outDeliver(salerId);
    }

    @Override
    public void ackDeliver(int orderId,String productId, int amount) {
        wholesalerMapper.ackDeliver(productId, amount);
        wholesalerMapper.changeStatus(orderId,3);
    }

    @Override
    public int layPrice(String productId, double price) {
        return wholesalerMapper.layPrice(productId, price);
    }

    @Override
    public int addOrder(WPOrder order) {
        return wholesalerMapper.addOrder(order);
    }



}
