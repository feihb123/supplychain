package xzit.supplychain.service;

import xzit.supplychain.pojo.Saler;

import java.util.List;

public interface AdminService {
    List<Saler> getSaler();
    int putOff(int salerId);
    int add(int salerId);
    List<Saler> Saler();


    List<Saler> shopcheck();
}
