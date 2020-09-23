package com.oracle.service.impl;

import com.oracle.entity.PageBean;
import com.oracle.entity.ProductType;
import com.oracle.mapper.ProductTypeMapper;
import com.oracle.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAllProductType() {
        return productTypeMapper.getAllProductType();
    }

    @Override
    public int getProductTypeRowCount(String typename) {
        return productTypeMapper.getProductTypeRowCount(typename);
    }

    @Override
    public int getProductTypeRowCountAjax(int typeId, String typeName) {
        return productTypeMapper.getProductTypeRowCountAjax(typeId, typeName);
    }

    @Override
    public ProductType selectProductTypeById(int id) {
        return productTypeMapper.selectProductTypeById(id);
    }

    @Override
    public int insertProductType(ProductType productType) {
        return productTypeMapper.insertProductType(productType);
    }

    @Override
    public int deleteProductTypeById(int id) {
        return productTypeMapper.deleteProductTypeById(id);
    }

    @Override
    public int updateProductTypeById(ProductType productType) {
        return productTypeMapper.updateProductTypeById(productType);
    }

    @Override
    public PageBean<ProductType> getProductTypePage(int page, int pagesize, String typename) {
        //获取当前页的数据
        List<ProductType> list = productTypeMapper.getProductTypePage(page, pagesize, typename);
        PageBean<ProductType> pageBean = new PageBean<>();
        pageBean.setList(list);
        pageBean.setPage(page);

        //获取总页数
        int rowcount = getProductTypeRowCount(typename);
        if (rowcount % pagesize == 0) {
            pageBean.setPages(rowcount / pagesize);
        } else {
            pageBean.setPages(rowcount / pagesize + 1);
        }

        return pageBean;
    }

    //Ajax方法
    @Override
    public PageBean<ProductType> getAllProductTypeByPageAjax(int page, int pagesize, int typeId, String typeName) {
        //获取当前页的数据
        List<ProductType> list = productTypeMapper.getProductTypePageAjax(page, pagesize, typeId, typeName);
        PageBean<ProductType> pageBean = new PageBean<>();
        pageBean.setList(list);
        pageBean.setPage(page);

        //获取总页数
        int rowcount = getProductTypeRowCountAjax(typeId, typeName);
        //封装总行数
        pageBean.setRowcount(rowcount);
        if (rowcount % pagesize == 0) {
            pageBean.setPages(rowcount / pagesize);
        } else {
            pageBean.setPages(rowcount / pagesize + 1);
        }

        return pageBean;
    }
}
