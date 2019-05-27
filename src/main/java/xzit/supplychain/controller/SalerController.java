package xzit.supplychain.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xzit.supplychain.pojo.*;
import xzit.supplychain.service.SalerService;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SalerController {
    @Autowired
    SalerService SalerService;

    @RequestMapping("/inventory")
    public String inventory(Model model){
        List<SalerInventory> list = SalerService.showSalerInventory();

        model.addAttribute("inventory",list);
        model.addAttribute("tttt",1);
        return "saler/inventory";
    }


    @RequestMapping("/stock")
    public String stock(Model model){
        List<WholeSaler> list = SalerService.showWholeSaler();
        model.addAttribute("stock",list);
        return "saler/stock";
    }

    @RequestMapping("/salerOutDeliver")
    public String salerOutDeliver(Model model, HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SalerOrder> list = SalerService.salerOutDeliver(id);
        model.addAttribute("salerOutDeliver",list);
        return "saler/salerOutDeliver";
    }
    //客户订单

    @RequestMapping("/salerWaitPay")
    public String salerWaitPay(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SWOrder> list = SalerService.salerWaitPay(id);
        model.addAttribute("salerWaitPay",list);
        return "saler/salerWaitPay";
    }
    //待付款



    @RequestMapping("/salerWaitIn")
    public String salerWaitIn(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SWOrder> list = SalerService.salerWaitIn(id);
        model.addAttribute("salerWaitIn",list);
        return "saler/salerWaitIn";
    }
    //待收货


    @RequestMapping("/salerReturn")
    public String salerReturn(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SWOrder> list = SalerService.salerReturn(id);
        model.addAttribute("salerReturn",list);
        return "saler/salerReturn";
    }
    //待收货


    @RequestMapping("/salerWaitEvaluate")
    public String salerWaitEvaluate(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SWOrder> list = SalerService.salerWaitEvaluate(id);
        model.addAttribute("salerWaitEvaluate",list);
        return "saler/salerWaitEvaluate";
    }
    //待评价订单


    @RequestMapping("/salerEndOrder")
    public String salerEndOrder(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SWOrder> list = SalerService.salerEndOrder(id);
        model.addAttribute("salerEndOrder",list);
        return "saler/salerEndOrder";
    }
    //已完成订单

    @RequestMapping("/salerCanceledOrder")
    public String salerCanceledOrder(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SWOrder> list = SalerService.salerCanceledOrder(id);
        model.addAttribute("salerCanceledOrder",list);
        return "saler/salerCanceledOrder";
    }
    //已完成订单

    @RequestMapping("/salerWaitOut")
    public String salerWaitOut(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SWOrder> list = SalerService.salerWaitOut(id);
        model.addAttribute("salerWaitOut",list);
        return "saler/salerWaitOut";
    }
    //代发货

    @RequestMapping("/salerDetail")
    public String salerDetail(String wholesalerId,Model model){
        List<SalerStock> list = SalerService.salerDetail(wholesalerId);
        model.addAttribute("salerDetail",list);
        model.addAttribute("wholesalerId",wholesalerId);
        return "saler/salerDetail";
    }

    @RequestMapping("/salerSubscribe")
    public String salerSubscribe(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SalerSubscribe> list = SalerService.salerSubscribe(id);
        model.addAttribute("salerSubscribe",list);
        return "saler/salerSubscribe";
    }
    //待处理预约


    @RequestMapping("/salerAcceptSubscribe")
    public String salerAcceptSubscribe(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SalerSubscribe> list = SalerService.salerAcceptSubscribe(id);
        model.addAttribute("salerAcceptSubscribe",list);
        return "saler/salerAcceptSubscribe";
    }
    //已接受预约

    @RequestMapping("/salerRejectSubscribe")
    public String salerRejectSubscribe(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SalerSubscribe> list = SalerService.salerRejectSubscribe(id);
        model.addAttribute("salerRejectSubscribe",list);
        return "saler/salerRejectSubscribe";
    }
//已拒绝预约

    @RequestMapping("/salerDiachronicSubscribe")
    public String salerDiachronicSubscribe(Model model,HttpSession session){
        int id = Integer.parseInt(session.getAttribute("salerId").toString());
        List<SalerSubscribe> list = SalerService.salerDiachronicSubscribe(id);
        model.addAttribute("salerDiachronicSubscribe",list);
        return "saler/salerDiachronicSubscribe";
    }
    //历史预约


    @RequestMapping("/changeStatus99")
    public String changeStatus99(Integer status,int orderId){
        SalerService.changeStatus99(status,orderId);
        return "redirect:/salerWaitPay";
    }
    //取消订单状态值改变

    @RequestMapping("/changeStatus04")
    public String changeStatus04(Integer status,int orderId){
        SalerService.changeStatus04(status,orderId);
        return "redirect:/salerWaitIn";
    }
    //确认收货状态值改变

    @RequestMapping("/changeStatus01")
    public String changeStatus01(Integer status,int orderId){
        SalerService.changeStatus01(status,orderId);
        return "redirect:/salerWaitPay";
    }
    //确认收货状态值改变

    @RequestMapping("/changeStatus11")
    public String changeStatus11(Integer status,int orderId){
        SalerService.changeStatus11(status,orderId);
        return "redirect:/salerWaitIn";
    }
    //确认收货状态值改变


    @RequestMapping("/salerLayPrice")
    public String salerLayPrice(String productId,double price){
        SalerService.salerLayPrice(productId,price);
        return "redirect:/inventory";
    }
    //修改价格

    @RequestMapping("/changeStatus1")
    public String changeStatus1(Integer oddNumber,int statusId){
        SalerService.changeStatus1(oddNumber,statusId);
        return "redirect:/salerSubscribe";
    }
    //修改预约表状态值

    @RequestMapping("/changeStatus2")
    public String changeStatus2(Integer oddNumber,int statusId){
        SalerService.changeStatus2(oddNumber,statusId);
        return "redirect:/salerSubscribe";
    }
    //修改预约表状态值

    @RequestMapping("/changeStatus3")
    public String changeStatus3(Integer oddNumber,int statusId){
        SalerService.changeStatus3(oddNumber,statusId);
        return "redirect:/salerAcceptSubscribe";
    }
    //修改预约表状态值


    @RequestMapping("/deleteStock")
    public String deleteStock(String productId){
        SalerService.deleteStock(productId);
        return "redirect:/inventory";
    }
    //删除库存







    @RequestMapping("/salerEvaluation")
    public String salerEvaluation(){
        return "saler/salerEvaluation";
    }
    //评价

    @RequestMapping("/salerPrivateMessage")
    public String salerPrivateMessage(){
        return "saler/salerPrivateMessage";
    }

    //留言板


    @RequestMapping("/salerAckDeliver")
    public String salerAckDeliver(int orderId,String productId,int amount){
        SalerService.salerAckDeliver(orderId,productId, amount);
        return "saler/salerAckDeliverSuccess";
    }
    //确认收货


    @RequestMapping("/salerSelectInventory")
    public String salerSelectInventory(String productName,Model model){
        List<SalerInventory> list = SalerService.salerSelectInventory(productName);
        model.addAttribute("salerSelectInventory",list);
        return "saler/salerSelectInventory";
    }

    @RequestMapping("/salerSelectWholeSaler")
    public String salerSelectWholeSaler(String userName,Model model){
        List<WholeSaler> list = SalerService.salerSelectWholeSaler(userName);
        model.addAttribute("salerSelectWholeSaler",list);
        return "saler/salerSelectWholeSaler";
    }



}


