package com.oracle.controller;

import com.oracle.entity.PageBean;
import com.oracle.entity.Product;
import com.oracle.entity.ProductType;
import com.oracle.service.ProductService;
import com.oracle.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ProductController
{
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    //无分页实现
//    @GetMapping("/getprobypage")
//    public String showProduct(Model model)
//    {
//        List<HashMap<String, Object>> products = productService.getAllProduct();
//        //封装数据，在productnopage.jsp页面显示数据
//        model.addAttribute("products", products);
//        return "productnopage";
//    }

    //有分页实现
    @GetMapping("/getprobypage")
    public String showProduct(@RequestParam(name="page", defaultValue = "1")int page,
                              @RequestParam(name="name", defaultValue = "")String name,
                              @RequestParam(name="typeid", defaultValue = "-1")int typeid, Model model)
    {
        //每页显示数量
        int pagesize = 5;
        //返回PageBean数据封装到PageBean属性list
        PageBean<HashMap<String, Object>> products = productService.getAllProductByPage(page, pagesize, name, typeid);
        //封装数据，在product.jsp显示
        model.addAttribute("products", products);
        //需要提前查询商品的类型，进入页面后可以直接选择商品类型
        List<ProductType> productTypes = productTypeService.getAllProductType();
        model.addAttribute("ptlist", productTypes);
        model.addAttribute("name", name);
        model.addAttribute("typeid", typeid);
        return "product";
    }

    //删除商品
    @GetMapping("/delproduct")
    public String delProductById(int id)
    {
        productService.delProductById(id);
        //重新获取数据，在product.jsp显示
        return "redirect:/getprobypage";
    }

    //进入添加商品页面
    @GetMapping("/addproductpage")
    public String toAddProductPage(Model model)
    {
        //需要提前查询商品的类型，进入页面后可以直接选择商品类型
        List<ProductType> productTypes = productTypeService.getAllProductType();
        model.addAttribute("ptlist", productTypes);
        return "addproduct";
    }

    //添加商品
    @PostMapping("/addproduct")
    public String addProduct(Product product)
    {
        //时间封装为当前时间
        product.setDate(new Date());
        productService.addProduct(product);
        return "redirect:/getprobypage";
    }

    //批量删除
    //需要获取参数：一个或多个商品的id，放到数组int[] ids
    @GetMapping("/batchdelproduct")
    public String delBatchProduct(int[] ids)
    {
        productService.delBatchProduct(ids);
        //重新获取数据，在product.jsp显示
        return "redirect:/getprobypage";
    }

    //实现文件上传
    @PostMapping("/produpload")
    @ResponseBody   //将数据转换成json格式
    public Map<String, String> uploadFile(MultipartFile upimage, HttpServletRequest request) throws IOException {
        //获取文件名
        String fileName = upimage.getOriginalFilename();
        //上传后的文件名(uuid + 文件扩展名)
        String realName = UUID.randomUUID().toString() + fileName.substring(fileName.indexOf("."));
        //服务器路径
        String serverPath = request.getServletContext().getRealPath("/") + "/resources/image_big/";

        Map<String, String> result = new HashMap<>();
        File file = new File(serverPath + realName);
        try {
            //上传文件
            upimage.transferTo(file);
            //返回结果
            result.put("imgurl", request.getContextPath() + "/resources/image_big/" + realName);
            result.put("imgName", realName);
        }
        catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        //json {"imgurl":"上传路径", "imgName":"文件名"}
        return result;
    }

    //根据id获取当前行的数据进入修改页面
    @GetMapping("/getproductbyid")
    public String getProductById(int id, Model model)
    {
        Product product = productService.getProductById(id);
        //将数据显示在页面上
        model.addAttribute("product", product);
        //获取所有商品类型
        List<ProductType> ptlist = productTypeService.getAllProductType();
        model.addAttribute("ptlist", ptlist);
        return "updateproduct";
    }

    //修改商品信息
    @PostMapping("/updateproduct")
    public String toUpdateProduct(Product product)
    {
        //修改的时间封装为当前时间
        product.setDate(new Date());
        productService.updateProduct(product);
        return "redirect:/getprobypage";
    }

    //index请求，获取5条信息，进入shop.js页面
    @GetMapping("/index")
    public String toShopPage(Model model)
    {
        List<Product> products = productService.getFiveProducts();
        model.addAttribute("products", products);
        return "shop";
    }

    //进入注册页面
    @GetMapping("/toregisterpage")
    public String toRegisterPage(Model model)
    {
        return "register";
    }

    //进入商品详细信息页面
    @GetMapping("/getproductdetail")
    public String getProductDetail(int id, Model model)
    {
        //根据id获取商品信息
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productdetail";
    }
}
