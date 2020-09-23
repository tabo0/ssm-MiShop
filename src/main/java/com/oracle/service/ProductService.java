package com.oracle.service;

import com.oracle.entity.PageBean;
import com.oracle.entity.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface ProductService
{
    //无分页实现
//    List<HashMap<String, Object>> getAllProduct();

    //有分页实现
    PageBean<HashMap<String, Object>> getAllProductByPage(int page, int pagesize, String name, int typeid);

    //删除商品
    int delProductById(int id);

    //批量删除
    int delBatchProduct(int[] ids);

    //根据id获取数据
    Product getProductById(int id);

    //修改数据
    int updateProduct(Product product);

    //添加数据
    int addProduct(Product product);

    List<Product> getFiveProducts();
}
