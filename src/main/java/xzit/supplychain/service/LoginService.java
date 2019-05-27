package xzit.supplychain.service;

import xzit.supplychain.pojo.Consumer;
import xzit.supplychain.pojo.User;

public interface LoginService {
    String checkAccount(User user);
    String getCompanyName(String username);
    String getId(User user);
    int saveConsumer(Consumer consumer);

}
