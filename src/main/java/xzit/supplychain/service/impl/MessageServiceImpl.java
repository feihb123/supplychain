package xzit.supplychain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzit.supplychain.mapper.MessageMapper;
import xzit.supplychain.pojo.Message;
import xzit.supplychain.service.MessageService;
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Override
    public int store(Message message) {
        return messageMapper.store(message);
    }
}
