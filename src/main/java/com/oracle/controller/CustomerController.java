package com.oracle.controller;

import com.oracle.entity.Customer;
import com.oracle.service.CustomerService;
import com.oracle.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    //用户注册
    @PostMapping("/doregister")
    public String doRegister(String cname, String cpass, String yzm, HttpSession session, Model model)
    {
        //生成验证码
        String rdmCode = (String)session.getAttribute("rdmCode");

//        if () {
//           model.addAttribute("error", "输入的验证码不正确!");
//           return "register";
//        }
        customerService.customerRegister(cname, MD5Util.getMd5Str(cpass));

        //注册成功，进入登陆页面
        return "customerlogin";
    }

    //检查账号是否存在
    @PostMapping("/docheckcname")
    @ResponseBody
    public Map<String, String> doCheckCname(String cname)
    {
        int count = customerService.checkCname(cname);
        Map<String, String> map = new HashMap<>();
        if (count > 0) {
            map.put("info", "该账号已存在，请修改");
        }
        else {
            map.put("info", "该账号可以使用");
        }
        return map;
    }

    //进入登录页面
    @GetMapping("/tocustomerloginpage")
    public String toCustomerLoginPage()
    {
        return "customerlogin";
    }

    //用户登录
    @PostMapping("/customerlogin")
    public String customerLogin(String cname, String cpass, String yzm, HttpSession session, Model model)
    {
        //生成验证码
        String rdmCode = (String)session.getAttribute("rdmCode");
        if (!rdmCode.equalsIgnoreCase(yzm)) {
            model.addAttribute("error", "输入的验证码不正确！");
            return "customerlogin";
        }

        Customer customer = customerService.customerLogin(cname, MD5Util.getMd5Str(cpass));
        if (!Objects.isNull(customer)) {
            //保存信息到session
            session.setAttribute("customer", customer);
            return "redirect:/index";
        }
        else {
            model.addAttribute("error", "账号或密码错误!");
            return "customerlogin";
        }
    }

    //注销
    @GetMapping("/customerlogout")
    public String toCustomerLogout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/index";
    }
}
