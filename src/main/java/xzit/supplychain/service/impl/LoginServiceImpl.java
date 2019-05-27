package xzit.supplychain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzit.supplychain.mapper.UserMapper;
import xzit.supplychain.pojo.Consumer;
import xzit.supplychain.pojo.User;
import xzit.supplychain.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Override
    public String checkAccount(User user) {
        if(user.getPassword().equals(userMapper.getPWD(user))){
            return user.getStatus()+"/"+user.getStatus();
        }else {
            return "public/error";
        }
    }

    @Override
    public String getCompanyName(String username) {
        return userMapper.getcompanyName(username);
    }

    @Override
    public String getId(User user) {
        return userMapper.getId(user);
    }

    @Override
    public int saveConsumer(Consumer consumer) {
        return userMapper.saveConsumer(consumer);
    }
}
