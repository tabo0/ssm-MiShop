package com.oracle.service.impl;

import com.oracle.mapper.CarShopMapper;
import com.oracle.service.CarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CarShopServiceImpl implements CarShopService
{
    @Autowired
    private CarShopMapper carShopMapper;

    @Override
    public boolean addCarShop(int customerid, int productid, int numbers)
    {
        int count = carShopMapper.getCarShopByCustomerIdAndProductId(customerid, productid);
        if (count == 0) {
            carShopMapper.addCarShop(customerid, productid, numbers);
        }
        else {
            carShopMapper.updateCarShopByCustomerIdAndProductId(customerid, productid, numbers);
        }
        return true;
    }

    @Override
    public List<HashMap<String, Object>> getCarShop(int customerid)
    {
        return carShopMapper.getCarShop(customerid);
    }

    @Override
    public int delCarShopByCidAndCustomerId(int cid, int customerid)
    {
        return carShopMapper.delCarShopByCidAndCustomerId(cid, customerid);
    }
}
