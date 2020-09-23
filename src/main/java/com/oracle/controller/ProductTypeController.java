package com.oracle.controller;

import com.oracle.entity.PageBean;
import com.oracle.entity.ProductType;
import com.oracle.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductTypeController
{
    @Autowired
    private ProductTypeService productTypeService;

    //查看全部商品类型(无分页的)
//    @GetMapping("/toproducttypepage")
//    public String getProductType(Model model)
//    {
//        model.addAttribute("producttypes", productTypeService.getAllProductType());
//        return "producttypenoajax";
//    }

    //进入添加商品类型页面
    @GetMapping("/addproducttypepage")
    public String toAddProductTypePage()
    {
        return "addproducttype";
    }

    //通过id删除商品类型
    @GetMapping("delproducttype")
    public String delProductTypeById(int id)
    {
        productTypeService.deleteProductTypeById(id);
        return "redirect:/getproducttypepage";
    }

    //通过id选中需要修改的商品类型
    @GetMapping("/toupdateprotypepage")
    public String selectProductTypeById(int id, Model model)
    {
        model.addAttribute("producttype", productTypeService.selectProductTypeById(id));
        return "updateproducttype";
    }

    //修改商品类型
    @PostMapping("/updateprotype")
    public String updateProductType(ProductType productType)
    {
        productTypeService.updateProductTypeById(productType);
        return "redirect:/getproducttypepage";
    }

    //新增商品类型
    @PostMapping("/addprotype")
    public String insertProductType(ProductType productType)
    {
        productTypeService.insertProductType(productType);
        return "redirect:/getproducttypepage";
    }

    //实现分页显示
//    @GetMapping("/getproducttypepage")
//    public String getProductTypePage(@RequestParam(name="page", defaultValue = "1")int page, @RequestParam(name="typename", defaultValue = "")String typename, Model model)
//    {
//        int pagesize = 5;
//        PageBean<ProductType> producttypes = productTypeService.getProductTypePage(page, pagesize, typename);
//        model.addAttribute("producttypes", producttypes);
//        model.addAttribute("typename", typename);
//        return "producttypenoajax";
//    }

    //实现分页显示 AJAX方法
    @PostMapping("/producttype_list_ajax")
    @ResponseBody   //将数据转换成json格式
    public Map<String, Object> getProductTypeByPageAjax(@RequestParam Map<String, Object> map)
    {
        //获取当前页码、类型ID、名称
        int currentPage = Integer.parseInt((String)map.get("currentPage"));
        int typeId = Integer.parseInt((String)map.get("typeId"));
        String typeName = (String)map.get("typeName");
        //每页显示数为5
        int pageSize = 5;
        //获取分页的实体类
        PageBean pageBean = productTypeService.getAllProductTypeByPageAjax(currentPage, pageSize, typeId, typeName);
        //将实体类数据转换成map集合
        Map<String, Object> result = new HashMap<>();
        //返回给页面的数据，data对应result
        result.put("list", pageBean.getList());
        result.put("pageSize", pageSize);
        result.put("pageCount",pageBean.getPages());
        result.put("rowCount", pageBean.getRowcount());
        //返回查询条件
        result.put("tid", typeId);
        result.put("tname", typeName);

        return result;
    }

    @GetMapping("/getproducttypepage")
    public String toProductTypePage()
    {
        return "producttype";
    }
}
