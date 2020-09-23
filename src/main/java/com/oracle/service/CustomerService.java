package com.oracle.service;

import com.oracle.entity.Customer;

public interface CustomerService
{
    //注册
    int customerRegister(String cname, String cpass);

    //检查账号
    int checkCname(String cname);

    //登录
    Customer customerLogin(String cname, String cpass);
}
