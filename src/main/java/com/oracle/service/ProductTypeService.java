package com.oracle.service;

import com.oracle.entity.PageBean;
import com.oracle.entity.ProductType;

import java.util.List;

public interface ProductTypeService
{
    //获取所有商品类型
    List<ProductType> getAllProductType();

    //获取总行数
    int getProductTypeRowCount(String typename);

    //获取总行数 (Ajax方法)
    int getProductTypeRowCountAjax(int typeId, String typeName);

    //通过id获取商品类型
    ProductType selectProductTypeById(int id);

    //新增商品类型
    int insertProductType(ProductType productType);

    //通过id删除商品类型
    int deleteProductTypeById(int id);

    //通过id修改商品类型
    int updateProductTypeById(ProductType productType);

    PageBean<ProductType> getProductTypePage(int page, int pagesize, String typename);

    //Ajax方法
    PageBean<ProductType> getAllProductTypeByPageAjax(int page, int pagesize, int typeId, String typeName);
}
