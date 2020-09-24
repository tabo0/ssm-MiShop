package com.oracle.service;

import java.util.HashMap;
import java.util.List;

public interface CarShopService
{
    //添加进购物车
    boolean addCarShop(int customerid, int productid, int numbers);

    //获取购物车的信息
    List<HashMap<String, Object>> getCarShop(int customerid);

    //删除购物车的信息
    int delCarShopByCidAndCustomerId(int cid, int customerid);

    //修改购物车的数量
    int updateNumbers(int cid, int num);
}
