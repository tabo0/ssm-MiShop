package com.oracle.service;

import com.oracle.entity.PageBean;

import java.util.HashMap;

public interface OrderService
{
    PageBean<HashMap<String, Object>> getOrderPage(int page, int pagesize);
}
