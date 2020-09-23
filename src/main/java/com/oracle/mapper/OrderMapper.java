package com.oracle.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface OrderMapper
{
    List<HashMap<String, Object>> getOrderPage(@Param("page")int page, @Param("pagesize")int pagesize);

    int getRowCount();
}
