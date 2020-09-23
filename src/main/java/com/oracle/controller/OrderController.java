package com.oracle.controller;

import com.oracle.entity.PageBean;
import com.oracle.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @GetMapping("/getorderbypage")
    public String getOrder(@RequestParam(name="page", defaultValue="1")int page, Model model)
    {
        int pagesize = 5;
        PageBean<HashMap<String, Object>> pagebean = orderService.getOrderPage(page, pagesize);
        model.addAttribute("pagebean", pagebean);
        return "orderbypage";
    }
}
