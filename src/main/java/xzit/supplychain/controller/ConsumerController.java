package xzit.supplychain.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xzit.supplychain.pojo.*;
import xzit.supplychain.service.ConsumerService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ConsumerController {
    @Autowired
    ConsumerService consumerService;

    public int getId(HttpSession session){
        return Integer.parseInt(session.getAttribute("id").toString());
    }

    @RequestMapping("/index")
    public String getSaler(Model model,@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "4") int size){
        PageHelper.startPage(start,size,"id asc");
        List<Saler> list = consumerService.getSaler();
        PageInfo<Saler> page = new PageInfo<>(list);

        model.addAttribute("page",page);
        return "consumer/consumer";
    }



    @RequestMapping("/design")
    public String design(){
        return "consumer/design/car";
    }

    //显示商家所卖汽配
    @RequestMapping("/shopContent")
    public String showSalerDetail(int id, Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "4") int size){
        PageHelper.startPage(start,size,"productId asc");
        List<ProductAmount> list = consumerService.showSalerDetail(id);
        PageInfo<ProductAmount> page = new PageInfo<>(list);
        model.addAttribute("page", page);
        model.addAttribute("welcome",consumerService.getSalerWelcome(id));
        model.addAttribute("salerId",id);
        return "consumer/shopContent/content";
    }
    //添加购物车
    @RequestMapping("/addCart")
    @ResponseBody
    public String addCart(int salerId,String proId,HttpSession session){

        int consumerId =  Integer.parseInt(session.getAttribute("id").toString());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setConsumerId(consumerId);
        shoppingCart.setSalerId(salerId);
        shoppingCart.setProductId(proId);
        int flag = consumerService.addCart(shoppingCart);
        if(flag == 1){
            return JSON.toJSONString("加购成功！");
        }
        return JSON.toJSONString("加购失败");
    }

    @RequestMapping("/showCarts")
    public String showCarts(Model model,HttpSession session){
        int cid = getId(session);
        List<ShoppingCart> list = consumerService.showCarts(cid);
        //按店铺分组显示
        List<String> shop = consumerService.findShopName();
        model.addAttribute("list",list);
        model.addAttribute("shop",shop);

        return "consumer/shoppingCart/cart";
    }

    @RequestMapping("/subCart")
    public String subCart(int sid){
        consumerService.subCart(sid);
        return "redirect:/showCarts";
    }
    @RequestMapping("/incCart")
    public String incCart(int sid){
        consumerService.updateCart(sid,1);
        return "redirect:/showCarts";
    }
    @RequestMapping("/delCart")
    public String delCart(int sid){
        consumerService.delCart(sid);
        return "redirect:/showCarts";
    }


    @RequestMapping("/buy")
    public String buy(String[] pros,Model model,HttpSession session){
        double sum = 0;
        List<Integer> p = new ArrayList<>();
        String product = "";
        String amount = "";
        for (String pro:pros) {
            int sid = Integer.parseInt(pro.replaceAll("\\D",""));
            p.add(sid);
            product += "@"+sid;
            amount +="@"+consumerService.findCartAmount(sid);
            sum += consumerService.findCartAmountAndPrice(sid);
        }
        List<ShoppingCart> carts = consumerService.findCartBySid(p);

        int consumerId =  Integer.parseInt(session.getAttribute("id").toString());
        Address defaultAdd = consumerService.findDefaultAdd(consumerId);

        model.addAttribute("product",product);
        model.addAttribute("amount",amount);
        model.addAttribute("carts",carts);
        model.addAttribute("sum",sum);
        model.addAttribute("address",defaultAdd);

        return "consumer/purchase/order";
    }

    //生成订单
    @RequestMapping("/generateCSOrder")
    public String generateCSOrder(MergeCSOrder mergeCSOrder,HttpSession session){
        String sid[] = mergeCSOrder.getProductId().split("@");
        String amount[] = mergeCSOrder.getAmount().split("@");
        int consumerId =  Integer.parseInt(session.getAttribute("id").toString());
        for (int i = 1;i < sid.length;i++){

            int salerId = consumerService.findSalerId(Integer.parseInt(sid[i]));
            String proId = consumerService.findProductId(Integer.parseInt(sid[i]));
            CSOrder order = new CSOrder();
            order.setOrderId(System.currentTimeMillis());
            order.setConsumerId(consumerId);
            order.setSalerId(salerId);
            order.setTime(new Timestamp(System.currentTimeMillis()));
            order.setProductId(proId);
            order.setAmount(Integer.parseInt(amount[i]));
            order.setPrice(consumerService.findPrice(salerId,proId));
            order.setAddress(mergeCSOrder.getAddress());
            order.setTel(mergeCSOrder.getTel());
            order.setStatus(1);

            consumerService.generateSOrder(Integer.parseInt(sid[i]),order);

        }
        return "consumer/purchase/success";
    }

    @RequestMapping("/allOrders")
    public String allOrders( HttpSession session,Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "7") int size){
        int consumerId =  Integer.parseInt(session.getAttribute("id").toString());
        PageHelper.startPage(start,size,"orderId desc");
        List<CSOrder> list = consumerService.findAllOrders(consumerId);
        PageInfo<CSOrder> page = new PageInfo<>(list);
        model.addAttribute("page", page);

        return "consumer/orders/allOrders";
    }

    @RequestMapping("/procedureOrders")
    public String procedureOrders(HttpSession session, Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "7") int size){
        int consumerId =  Integer.parseInt(session.getAttribute("id").toString());
        PageHelper.startPage(start,size,"orderId desc");
        List<CSOrder> list = consumerService.findOrders(2,consumerId);
        PageInfo<CSOrder> page = new PageInfo<>(list);
        model.addAttribute("page", page);
        return "consumer/orders/procedureOrders";
    }

    @RequestMapping("/evaluateOrders")
    public String evaluateOrders(HttpSession session,Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "7") int size){
        PageHelper.startPage(start,size,"orderId desc");
        int consumerId =  Integer.parseInt(session.getAttribute("id").toString());
        List<CSOrder> list = consumerService.findOrders(3,consumerId);
        PageInfo<CSOrder> page = new PageInfo<>(list);
        model.addAttribute("page", page);
        return "consumer/orders/evaluateOrders";
    }

    @RequestMapping("/afterSaleOrders")
    public String afterSaleOrders(HttpSession session,Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "7") int size){
        PageHelper.startPage(start,size,"orderId desc");
        int consumerId =  Integer.parseInt(session.getAttribute("id").toString());
        List<CSOrder> list = consumerService.findOrders(9,consumerId);
        PageInfo<CSOrder> page = new PageInfo<>(list);
        model.addAttribute("page", page);
        return "consumer/orders/afterSaleOrders";
    }

    @RequestMapping("/waitDeliverOrders")
    public String waitDeliverOrders(HttpSession session,Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "7") int size){
        PageHelper.startPage(start,size,"orderId desc");
        int consumerId =  Integer.parseInt(session.getAttribute("id").toString());
        List<CSOrder> list = consumerService.findOrders(1,consumerId);
        PageInfo<CSOrder> page = new PageInfo<>(list);
        model.addAttribute("page", page);
        return "consumer/orders/waitDeliverOrders";
    }

    @RequestMapping("/cancelCSOrder")
    public String cancelOrder(long oid){
        consumerService.changeOrderStauts(99,oid);
        return "consumer/orders/cancelSuccess";
    }

    @RequestMapping("/completeCSOrder")
    public String completeOrder(long oid){
        consumerService.changeOrderStauts(3,oid);
        return "consumer/orders/completeSuccess";
    }
    @RequestMapping("/applyForAfterSale")
    public String applyForAfterSale(long oid){
        consumerService.changeOrderStauts(9,oid);
        return "consumer/orders/ApplyForAfterSaleSuccess";
    }

    @RequestMapping("/postCSEvaluate")
    public String postCSEvaluate(long oid,String evaContent){
        consumerService.deliverEvaluation(oid,evaContent);
        return "consumer/orders/evaSuccess";
    }

    @RequestMapping("/personalInfo")
    public String personalInfo(Model model,HttpSession session){
        int id = getId(session);
        Consumer consumer = consumerService.findConsumerById(id);
        model.addAttribute("info",consumer);
        return "consumer/personal/personalInfo";
    }

    @RequestMapping("/updateInfo")
    public String updateInfo(HttpSession session,String nickname,String phone,String email){
        int id = getId(session);
        consumerService.savePersonalInfo(nickname,phone,email,id);
        return "redirect:personalInfo";
    }

    @RequestMapping("/updateImg")
    public String updateInfo(HttpSession session,@RequestParam("file") MultipartFile file){
        int id = getId(session);
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        //部署项目后该路径依然不对
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/img/";
        String fileName = id+file.getOriginalFilename();
        //文件保存路径
        File dest = new File(path + fileName);
        //文件访问路径
        String src = "img/"+fileName;
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        consumerService.savePersonalImg(src,id);
        return "redirect:personalInfo";
    }

    @RequestMapping("/consumerAppoint")
    public String consumerAppoint(Model model){
        List<Saler> list = consumerService.getSaler();
        model.addAttribute("saler",list);
        return "consumer/appoint/appoint";
    }

    @RequestMapping("/consumerAppointed")
    public String consumerAppointed(Model model,HttpSession session,@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "4") int size){
        int consumerId = getId(session);
        PageHelper.startPage(start,size,"time desc");
        List<Appointment> list = consumerService.findAllAppointment(consumerId);
        PageInfo<Appointment> page = new PageInfo<>(list);
        model.addAttribute("page", page);
        return "consumer/appoint/appointed";

    }
    @RequestMapping("/appointRequest")
    public String appointRequest(int salerId,String time,HttpSession session) throws ParseException {
        int consumerId = getId(session);

        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(time.replaceAll("T"," "));
        Timestamp ts = new Timestamp(date.getTime());
        consumerService.saveAppointment(consumerId,salerId,ts);
        return "redirect:/consumerAppointed";
    }

    @RequestMapping("/cancelAppointment")
    public String cancelAppointment(int aid){
        consumerService.deleteAppointment(aid);
        return "redirect:/consumerAppointed";
    }

    @RequestMapping("/search")
    public String search(String type,String content, Model model,@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "4") int size){

        switch (type){
            case "saler":{
                PageHelper.startPage(start,size,"id asc");
                List<Saler> list = consumerService.findSalerfuzzy(content);
                PageInfo<Saler> page = new PageInfo<>(list);
                model.addAttribute("page",page);
                model.addAttribute("content",content);
                model.addAttribute("type",type);

                return "consumer/search/searchSaler";
            }
            case "product":{
                PageHelper.startPage(start,size,"productId asc");
                List<ProductAmount> list = consumerService.showFuzzyDetail(content);
                PageInfo<ProductAmount> page = new PageInfo<>(list);
                model.addAttribute("page",page);
                model.addAttribute("content",content);
                model.addAttribute("type",type);

                return "consumer/search/searchProducts";

            }
            default:{

            }
        }
        return "redirect:/index";
    }


}
