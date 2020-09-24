package com.oracle.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface CarShopMapper
{
    //添加进购物车
    int addCarShop(@Param("customerid")int customerid,
                   @Param("productid")int productid,
                   @Param("numbers")int numbers);

    //获取购物车的信息
    List<HashMap<String, Object>> getCarShop(@Param("customerid")int customerid);

    //查询购物车信息
    int getCarShopByCustomerIdAndProductId(@Param("customerid")int customerid,
                                           @Param("productid")int productid);

    //更新购物车数量
    int updateCarShopByCustomerIdAndProductId(@Param("customerid")int customerid,
                                              @Param("productid")int productid,
                                              @Param("numbers")int numbers);

    //删除购物车的信息
    int delCarShopByCidAndCustomerId(@Param("cid")int cid, @Param("customerid")int customerid);

    //修改购物车的数量
    int updateNumbers(@Param("cid")int cid, @Param("num")int num);
}