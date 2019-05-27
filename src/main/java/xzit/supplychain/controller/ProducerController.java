package xzit.supplychain.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xzit.supplychain.pojo.Product;
import xzit.supplychain.pojo.ProductAmount;
import xzit.supplychain.pojo.ProductType;
import xzit.supplychain.pojo.WPOrder;
import xzit.supplychain.service.ProducerService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProducerController {
    @Autowired
    ProducerService producerService;

    @RequestMapping(value = "/producerIndex")
    public String producerIndex(Model model,HttpSession session){
        int proId = producerService.getProducerId(session.getAttribute("username").toString());
        List<WPOrder> list = producerService.toDealOrder(proId);

        model.addAttribute("orderToDeal",list);
        return "producer/producer";
    }

    @RequestMapping(value = "/simulate")
    public String simulate(){
        return "producer/simulationProduction";
    }

    @RequestMapping("/line")
    public String line(HttpSession session, Model m,
                       @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size){
        PageHelper.startPage(start,size,"typeId asc");
        List<ProductType> line=producerService.findLine(session.getAttribute("companyName").toString());
        PageInfo<ProductType> page = new PageInfo<>(line);
        m.addAttribute("page", page);
        return "producer/addLine";
    }
    @RequestMapping("/addline")
    @ResponseBody
    public String addline(String typeName,String companyName){
        if(typeName == null){
            return "error";
        }
        if(producerService.addLine(typeName,companyName)==1){
            String json=JSON.toJSONString("生产线:"+typeName+"添加成功");
            return json;
        }else
        return "error";
    }
    @RequestMapping("/deleteLine")
    public String deleteLine(int id){
        producerService.deleteLine(id);
        return "redirect:/line";
    }
    @RequestMapping(value = "/getType",method = RequestMethod.POST)
    @ResponseBody
    public List<String> geType(String companyName)
    {
        return producerService.getType(companyName);
    }

    @RequestMapping(value = "/manufacture" ,method = RequestMethod.POST)
    @ResponseBody
    public String manufacture(Product product){

        if("".equals(product.getProductId())||"".equals(product.getProductName())||"".equals(product.getType())){
            return "error";
        }
        if(producerService.manufacture(product) == 0){
            return "error";
        };
        String time=new SimpleDateFormat("hh:mm:ss").format(new Date());
        String content=time+":此配件当前数量:"+producerService.getAmount(product);
        String json=JSON.toJSONString(content);
        return json;
    }

    @RequestMapping("/listAmount")
    public String listAmount(Model model,HttpSession session,
    @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "8") int size){
        String name=session.getAttribute("companyName").toString();
        PageHelper.startPage(start,size,"amount desc");
        List<ProductAmount> productAmounts=producerService.findAll(name);
        PageInfo<ProductAmount> page = new PageInfo<>(productAmounts);
        model.addAttribute("page",page);
        return "producer/listAmount";
    }

    @RequestMapping("/deliver")

    public String deliver(int orderId,String productId,int amount){
        if( producerService.checkAmount(productId) > amount) {
            producerService.deliver(orderId, productId, amount);
            return "producer/deliverSuccess";
        }

        return "producer/deliverFailure";
    }

    @RequestMapping("/toDealOrder")
    public String showToDealOrder(Model model,HttpSession session){
        //String username = session.getAttribute("username").toString();
        //int proId = producerService.getProducerId(username);
        int proId = Integer.parseInt(session.getAttribute("proId").toString());
        List<WPOrder> list = producerService.toDealOrder(proId);
        model.addAttribute("orderToDeal",list);
        return "producer/toDealOrder";
    }
    @RequestMapping("/allOrder")
    public String allOrder(Model model,HttpSession session,
    @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "8") int size){
        int proId = Integer.parseInt(session.getAttribute("proId").toString());
        PageHelper.startPage(start,size,"time desc");
        List<WPOrder> list = producerService.allOrder(proId);
        PageInfo<WPOrder> page = new PageInfo<>(list);
        model.addAttribute("allOrder",page);
        return "producer/allOrder";
    }
    @RequestMapping("/updatePrice")
    public String updatePrice(String productId,double price){
        producerService.updatePrice(productId,price);
        return "redirect:/listAmount";
    }
    //展示售后订单
    @RequestMapping("/proAftersale")
    public String proAftersale(HttpSession session,Model model){
        int proId = Integer.parseInt(session.getAttribute("proId").toString());
        List<WPOrder> list = producerService.aftersaleOrder(proId);
        model.addAttribute("list",list);
        return "producer/aftersaleOrder";
    }
    //售后订单处理
    @RequestMapping("/dealAftersale")
    public String proAftersale(int orderId){
        producerService.changeStatus(orderId,10);
        return "redirect:/proAftersale";
    }
}
