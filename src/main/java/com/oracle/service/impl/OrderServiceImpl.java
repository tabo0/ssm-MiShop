package com.oracle.service.impl;

import com.oracle.entity.PageBean;
import com.oracle.mapper.OrderMapper;
import com.oracle.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageBean<HashMap<String, Object>> getOrderPage(int page, int pagesize)
    {
        List<HashMap<String, Object>> list = orderMapper.getOrderPage(page, pagesize);
        PageBean<HashMap<String, Object>> pb = new PageBean<>();
        pb.setPage(page);
        pb.setList(list);

        int rowcount = getRowCount();
        if (rowcount % pagesize == 0) {
            pb.setPages(rowcount / pagesize);
        }
        else {
            pb.setPages(rowcount / pagesize + 1);
        }

        return pb;
    }

    private int getRowCount()
    {
        return orderMapper.getRowCount();
    }
}
