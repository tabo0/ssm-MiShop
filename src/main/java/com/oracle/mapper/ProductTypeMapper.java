package com.oracle.mapper;

import com.oracle.entity.PageBean;
import com.oracle.entity.ProductType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductTypeMapper
{
    //获取所有商品类型
    List<ProductType> getAllProductType();

    //获取总行数
    int getProductTypeRowCount(@Param("typename") String typename);

    //获取总行数 (Ajax方法)
    int getProductTypeRowCountAjax(@Param("typeId")int typeId, @Param("typeName") String typeName);

    //通过id获取商品类型
    ProductType selectProductTypeById(@Param("id")int id);

    //新增商品类型
    int insertProductType(@Param("productType")ProductType productType);

    //通过id删除商品类型
    int deleteProductTypeById(@Param("id")int id);

    //通过id修改商品类型
    int updateProductTypeById(@Param("productType")ProductType productType);

    //分页显示
    List<ProductType> getProductTypePage(@Param("page")int page, @Param("pagesize")int pagesize, @Param("typename")String typename);

    //分页显示 (Ajax方法)
    List<ProductType> getProductTypePageAjax(@Param("page")int page, @Param("pagesize")int pagesize,
                                         @Param("typeId")int typeId, @Param("typeName")String typeName);

}
