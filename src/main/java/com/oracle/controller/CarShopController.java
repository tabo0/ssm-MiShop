package com.oracle.controller;

import com.oracle.service.CarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class CarShopController
{
    @Autowired
    private CarShopService carShopService;

    //添加进购物车
    @GetMapping("/addcarshop")
    public String addCarShop(int customerid, int productid, int numbers)
    {
        carShopService.addCarShop(customerid, productid, numbers);
        return "redirect:/showcarshopbycustomerid?customerid=" + customerid;
    }

    //根据客户id获取购物车的信息
    @GetMapping("/showcarshopbycustomerid")
    public String showCarShop(int customerid, Model model)
    {
        List<HashMap<String, Object>> carlist = carShopService.getCarShop(customerid);
        model.addAttribute("carlist", carlist);
        return "carshop";
    }

    //删除购物车的信息
    @GetMapping("/deletecarshop")
    public String delCarShop(int cid, int customerid)
    {
        carShopService.delCarShopByCidAndCustomerId(cid, customerid);
        return "redirect:/showcarshopbycustomerid?customerid=" + customerid;
    }
}
