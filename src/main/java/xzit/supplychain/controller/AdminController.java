package xzit.supplychain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xzit.supplychain.pojo.Saler;
import xzit.supplychain.service.AdminService;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/adminIndex")
    public String getSaler(Model model){
        List<Saler> list = adminService.getSaler();
        model.addAttribute("saler",list);
        return "admin/showShop";
    }
    @RequestMapping("/shopcheck")
    public String shopcheckSaler(Model model){
        List<Saler> list = adminService.shopcheck();
        model.addAttribute("saler",list);
        return "admin/shopcheck";
    }
    @RequestMapping("/privateMessage")
    public String privateMessage(){
        return "admin/privateMessage";
    }

    @RequestMapping("/putOff")
    public String putOff(int salerId){
        adminService.putOff(salerId);
        return "redirect:/admin/adminIndex";
    }
    @RequestMapping("/add")
    public String add(int salerId){
        adminService.add(salerId);
        return "redirect:/admin/adminIndex";
    }
}
