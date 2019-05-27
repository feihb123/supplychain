package xzit.supplychain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzit.supplychain.mapper.InterceptorMapper;
import xzit.supplychain.pojo.User;
import xzit.supplychain.service.InterceptorService;

@Service
public class InterceptorServiceImpl implements InterceptorService {
    @Autowired
    InterceptorMapper interceptorMapper;

    @Override
    public int intercept(User user) {
        return interceptorMapper.interceptor(user);
    }
}
