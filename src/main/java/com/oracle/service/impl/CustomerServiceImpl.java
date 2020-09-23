package com.oracle.service.impl;

import com.oracle.entity.Customer;
import com.oracle.mapper.CustomerMapper;
import com.oracle.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int customerRegister(String cname, String cpass)
    {
        return customerMapper.customerRegister(cname, cpass);
    }

    @Override
    public int checkCname(String cname)
    {
        return customerMapper.checkCname(cname);
    }

    @Override
    public Customer customerLogin(String cname, String cpass)
    {
        return customerMapper.customerLogin(cname, cpass);
    }
}
