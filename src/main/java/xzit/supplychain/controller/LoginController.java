package xzit.supplychain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xzit.supplychain.pojo.Consumer;
import xzit.supplychain.pojo.Saler;
import xzit.supplychain.pojo.User;
import xzit.supplychain.pojo.WPOrder;
import xzit.supplychain.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    ProducerService producerService;
    @Autowired
    ConsumerService consumerService;
    @Autowired
    WholesalerService wholesalerService;
    @Autowired
    SalerService salerService;

    @RequestMapping("/")
    public String login(){
        return "public/login";
    }
    @RequestMapping("/register")
    public String register(){
        return "public/register/consumer";
    }

    @RequestMapping("/registerConsumer")
    public String registerConsumer(Consumer consumer){
        loginService.saveConsumer(consumer);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request, Model model){
        //model.addAttribute("puser",user);
        HttpSession session=request.getSession();
        session.setAttribute("username",user.getUsername());
        session.setAttribute("user",user);
        String id = loginService.getId(user);
        session.setAttribute("id",id);

        if("producer".equals(user.getStatus())){
            if("public/error".equals(loginService.checkAccount(user))){
                return "public/error";
            }
            session.setAttribute("companyName",loginService.getCompanyName(user.getUsername()));
            int proId = producerService.getProducerId(user.getUsername());
            session.setAttribute("proId",proId);
            List<WPOrder> list = producerService.toDealOrder(proId);

            model.addAttribute("orderToDeal",list);
            return "producer/producer";
        }
        if("consumer".equals(user.getStatus())){
            if("public/error".equals(loginService.checkAccount(user))){
                return "public/error";
            }

            return "redirect:/index";
        }
        if("wholesaler".equals(user.getStatus())){
            if("public/error".equals(loginService.checkAccount(user))){
                return "public/error";
            }
            session.setAttribute("wholesalerId",wholesalerService.getWholesaler(user.getUsername()));
        }
        if ("saler".equals(user.getStatus())) {

            session.setAttribute("salerId", salerService.getSaler(user.getUsername()));
        }

        return loginService.checkAccount(user);
    }
}
