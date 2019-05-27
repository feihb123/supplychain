package xzit.supplychain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import xzit.supplychain.pojo.*;
import xzit.supplychain.service.WholesalerService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class WholesalerController {
    @Autowired
    WholesalerService wholesalerService;

    @RequestMapping("/wstock")
    public String stock(Model model){
        List<Producer> list = wholesalerService.showProducer();
        model.addAttribute("stock",list);
        return "wholesaler/stock";
    }
    @RequestMapping("/detail")
    public String detail(String companyName,Model model){
        List<ProductAmount> list = wholesalerService.detail(companyName);
        model.addAttribute("detail",list);
        model.addAttribute("company",companyName);
        return "wholesaler/detail";
    }
    @RequestMapping("/stockAmount")
    public String stockAmount(Model model, HttpSession session){
        int id=Integer.parseInt(session.getAttribute("wholesalerId").toString());
        List<WholesalerAmount> wholesalerAmounts=wholesalerService.findAll(id);
        model.addAttribute("list",wholesalerAmounts);
        return "wholesaler/stockAmount";
    }

    @RequestMapping("/addOrder")
    public String addOrder(WPOrder order,String companyName,HttpSession session){
        System.out.println(order);
        order.setWholesalerId(Integer.parseInt(session.getAttribute("id").toString()));
        order.setProducerId(wholesalerService.getProducerId(companyName));
        order.setTime(new Timestamp(new java.util.Date().getTime()));
        wholesalerService.addOrder(order);
        return "redirect:/wstock";
    }
    @RequestMapping("/waitIn")
    public String waitIn(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("wholesalerId").toString());
        List<WPOrder> list = wholesalerService.waitIn(id);
        model.addAttribute("waitIn",list);
        return "wholesaler/waitIn";
    }
    @RequestMapping("/waitOut")
    public String waitOut(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("wholesalerId").toString());
        List<WPOrder> list = wholesalerService.waitOut(id);
        model.addAttribute("waitOut",list);
        return "wholesaler/waitOut";
    }
    @RequestMapping("/waitPay")
    public String waitPay(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("wholesalerId").toString());
        List<WPOrder> list = wholesalerService.waitPay(id);
        model.addAttribute("waitPay",list);
        return "wholesaler/waitPay";
    }
    @RequestMapping("/cancelOrder")
    public String cancelOrder(int orderId){
        wholesalerService.cancelOrder(orderId);
        return "redirect:/waitPay";
    }
    @RequestMapping("/waitEvaluate")
    public String waitEvaluate(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("wholesalerId").toString());
        List<WPOrder> list = wholesalerService.waitEvaluate(id);
        model.addAttribute("waitEvaluate",list);
        return "wholesaler/waitEvaluate";
    }
    @RequestMapping("/endOrder")
    public String endOrder(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("wholesalerId").toString());
        List<WPOrder> list = wholesalerService.endOrder(id);
        model.addAttribute("endOrder",list);
        return "wholesaler/endOrder";
    }
    @RequestMapping("/outDeliver")
    public String outDeliver(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("wholesalerId").toString());
        List<SWOrder> list = wholesalerService.outDeliver(id);
        model.addAttribute("outDeliver",list);
        return "wholesaler/outDeliver";
    }
    @RequestMapping("/expend")
    public String expend(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("wholesalerId").toString());
        List<WPOrder> list = wholesalerService.expend(id);
        model.addAttribute("expend",list);
        return "wholesaler/expend";
    }
    @RequestMapping("/income")
    public String income(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("wholesalerId").toString());
        List<SWOrder> list = wholesalerService.income(id);
        model.addAttribute("income",list);
        return "wholesaler/income";
    }
    @RequestMapping("/ackDeliver")
    public String ackDeliver(int orderId,String productId,int amount){
       wholesalerService.ackDeliver(orderId,productId, amount);
            return "wholesaler/ackDeliverSuccess";
    }
    @RequestMapping("/layPrice")
    public String layPrice(String productId,double price){
        wholesalerService.layPrice(productId,price);
        return "redirect:/stockAmount";
    }

    @RequestMapping("/privateMessage")
    public String privateMessage(){
        return "wholesaler/privateMessage";
    }

    @RequestMapping("/evaluation")
    public String evaluation(){
        return "wholesaler/evaluation";
    }


}
