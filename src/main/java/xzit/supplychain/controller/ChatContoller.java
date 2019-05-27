package xzit.supplychain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xzit.supplychain.pojo.Message;
import xzit.supplychain.pojo.Saler;
import xzit.supplychain.service.ConsumerService;
import xzit.supplychain.service.MessageService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ChatContoller {
    @Autowired
    MessageService messageService;
    @Autowired
    ConsumerService consumerService;

    @RequestMapping("/chat")
    public ModelAndView chat(HttpSession session,String toid){

        ModelAndView modelAndView = new ModelAndView("consumer/chat/index");
        modelAndView.addObject("to",toid);
        int id = Integer.parseInt(session.getAttribute("id").toString());
        List<Saler> chatSaler = consumerService.getChatSaler(id);
        modelAndView.addObject("chats",chatSaler);

        return modelAndView;
    }
    @RequestMapping("/schat")
    public String salerchat(){
        return "saler/chat/index";
    }

    //响应ajax保存信息
    @RequestMapping("/message")
    public String message(HttpSession session, Message message){
        System.out.println(message.toString());
        int consumerId = Integer.parseInt(session.getAttribute("id").toString());
        Timestamp timestamp = new Timestamp(new Date().getTime());
        message.setConsumerId(consumerId);
        message.setTime(timestamp);
        messageService.store(message);
        return "consumer/chat/index";
    }

}
