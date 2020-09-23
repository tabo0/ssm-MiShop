package com.oracle.mapper;

import com.oracle.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ProductMapper
{
    //无分页实现
//    List<HashMap<String, Object>> getAllProduct();

    //有分页实现
    List<HashMap<String, Object>> getProductsByPage(@Param("page")int page,
                                                    @Param("pagesize")int pagesize,
                                                    @Param("name")String name,
                                                    @Param("typeid")int typeid);

    //获取总行数
    int getRowCount(@Param("name")String name, @Param("typeid")int typeid);

    //删除商品
    int delProductById(@Param("id") int id);

    //批量删除
    int delBatchProduct(int[] ids);

    //通过id获取数据
    Product getProductById(@Param("id")int id);

    //修改数据
    int updateProduct(@Param("product")Product product);

    //添加数据
    int addProduct(@Param("product") Product product);

    List<Product> getFiveProducts();
}
