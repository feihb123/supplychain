package xzit.supplychain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzit.supplychain.mapper.ProducerMapper;
import xzit.supplychain.pojo.Product;
import xzit.supplychain.pojo.ProductAmount;
import xzit.supplychain.pojo.ProductType;
import xzit.supplychain.pojo.WPOrder;
import xzit.supplychain.service.ProducerService;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    ProducerMapper producerMapper;
    //获得制造商id
    @Override
    public int getProducerId(String name) {
        int id = producerMapper.getProducerId(name);
        return id;
    }

    @Override
    public List<String> getType(String companyName)
    {
        return producerMapper.getType(companyName);
    }

    @Override
    public int manufacture(Product product) {
        if(null == producerMapper.checkProducerInven(product)){
            if(null == producerMapper.checkProduct(product)){
                producerMapper.createProduct(product);
            }
            producerMapper.createProducerInven(product);
            return 1;
        }else {

            if(producerMapper.checkProductInven(product) != null) {
                producerMapper.manufacture(product);
                return 1;
            }
            else return 0;
        }
    }

    @Override
    public List<ProductAmount> findAll(String companyName) {

        return producerMapper.findAll(companyName);
    }

    @Override
    public int addLine(String typeName, String companyName) {
        if(null != producerMapper.checkLine(typeName,companyName)){
            return 0;
        }else {
            producerMapper.addLine(typeName,companyName);
            return 1;
        }
    }

    @Override
    public int deleteLine(int typeId) {
        return producerMapper.deleteLine(typeId);
    }

    @Override
    public List<WPOrder> toDealOrder(int id) {
        List<WPOrder> list = producerMapper.toDealOrder(id);
        return list;
    }

    @Override
    public int checkAmount(String productId) {
        return producerMapper.checkAmount(productId);
    }

    @Override
    public List<WPOrder> aftersaleOrder(int producerId) {
        return producerMapper.aftersaleOrder(producerId);
    }

    @Override
    public void changeStatus(int orderId, int status) {
        producerMapper.changeStatus(orderId,status);
    }

    @Override
    public void deliver(int orderId,String productId, int amount) {
        producerMapper.deliver(productId,amount);
        producerMapper.changeStatus(orderId,2);

    }

    @Override
    public int updatePrice(String productId, double price) {
        return producerMapper.updatePrice(productId,price);
    }

    @Override
    public List<WPOrder> allOrder(int wholesalerId) {
        return producerMapper.allOrder(wholesalerId);
    }

    @Override
    public List<ProductType> findLine(String companyName) {
        return producerMapper.findLine(companyName);
    }

    @Override
    public int getAmount(Product product) {
        return  producerMapper.getAmount(product);
    }


}
