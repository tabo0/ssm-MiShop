package com.oracle.service.impl;

import com.oracle.entity.PageBean;
import com.oracle.entity.Product;
import com.oracle.mapper.ProductMapper;
import com.oracle.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductMapper productMapper;

    //无分页实现
//    @Override
//    public List<HashMap<String, Object>> getAllProduct()
//    {
//        return productMapper.getAllProduct();
//    }

    //有分页实现
    @Override
    public PageBean<HashMap<String, Object>> getAllProductByPage(int page, int pagesize, String name, int typeid)
    {
        //获取当前页数据
        List<HashMap<String, Object>> list = productMapper.getProductsByPage(page, pagesize, name, typeid);
        //创建PageBean对象封装数据
        PageBean<HashMap<String, Object>> pb = new PageBean<>();
        //封装当前页码，当前页数据
        pb.setPage(page);
        pb.setList(list);
        //获取总行数
        int rowcount = rowcount(name, typeid);
        //封装总页数
        if (rowcount % pagesize == 0) {
            pb.setPages(rowcount / pagesize);
        }
        else {
            pb.setPages(rowcount / pagesize + 1);
        }

        return pb;
    }

    //获取总行数
    public int rowcount(String name, int typeid)
    {
        return productMapper.getRowCount(name, typeid);
    }

    //删除
    @Override
    public int delProductById(int id)
    {
        return productMapper.delProductById(id);
    }

    //批量删除
    @Override
    public int delBatchProduct(int[] ids)
    {
        return productMapper.delBatchProduct(ids);
    }

    //通过id获取数据
    @Override
    public Product getProductById(int id)
    {
        return productMapper.getProductById(id);
    }

    //修改数据
    @Override
    public int updateProduct(Product product)
    {
        return productMapper.updateProduct(product);
    }

    //添加数据
    @Override
    public int addProduct(Product product)
    {
        return productMapper.addProduct(product);
    }

    @Override
    public List<Product> getFiveProducts()
    {
        return productMapper.getFiveProducts();
    }
}
