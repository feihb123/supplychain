package xzit.supplychain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzit.supplychain.mapper.AdminMapper;
import xzit.supplychain.pojo.Saler;
import xzit.supplychain.service.AdminService;

import java.util.List;

@Service
public  class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Saler> getSaler() {
        return adminMapper.getSaler();
    }

    public List<Saler> checkSaler() { return adminMapper.shopcheck(); }
    @Override
    public int putOff(int salerId) {
        return adminMapper.putOff(salerId);
    }
    @Override
    public int add(int salerId) {
        return adminMapper.add(salerId);
    }

    @Override
    public List<Saler> Saler() {
        return adminMapper.shopcheck();
    }

    @Override
    public List<Saler> shopcheck() {
        return adminMapper.shopcheck();
    }
}
