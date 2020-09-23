package com.oracle.mapper;

import com.oracle.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

public interface CustomerMapper
{
    //用户注册
    int customerRegister(@Param("cname")String cname, @Param("cpass")String cpass);

    //检查账号
    int checkCname(@Param("cname")String cname);

    //登录
    Customer customerLogin(@Param("cname")String cname, @Param("cpass")String cpass);
}
