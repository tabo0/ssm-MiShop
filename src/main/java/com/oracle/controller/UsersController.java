package com.oracle.controller;

import com.oracle.entity.PageBean;
import com.oracle.entity.Role;
import com.oracle.entity.Users;
import com.oracle.service.UsersService;
import com.oracle.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UsersController
{
    @Autowired  //控制器调用业务层，要定义业务层的接口，使用自动注入
    private UsersService usersService;

    //后台登录
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @PostMapping("/login")
    //uname和upass对应于login.jsp中输入账号和密码的name属性
    public String userLogin(String uname, String upass, Model model, HttpSession session)
    {
        //调用业务层
        //使用MD5Util对密码加密
        upass = MD5Util.getMd5Str(upass);
        HashMap<String, Object> users = usersService.login(uname, upass);

        //为空则账号和密码错误
        if (Objects.isNull(users)) {
            //返回登陆页面
            //错误信息对应与login.jsp的 <span style="color:red">${info}</span>
            model.addAttribute("info", "账号或密码错误!");
            return "login";
        }

        //账号和密码正确，进入main页面
        session.setAttribute("users", users);
        return "main";
    }

    //退出后台登录
    @GetMapping("/logout")
    public String userLogout(HttpSession session)
    {
        //退出时需要将登陆时封装到Session的数据清除
        session.invalidate();
        //返回登陆页面
        return "login";
    }

    //有分页实现
    @GetMapping("/getusersbypage")
    public String showUsers(@RequestParam(name="page", defaultValue="1")int page,
                            @RequestParam(name="uname", defaultValue="")String uname,
                            @RequestParam(name="roleid", defaultValue="-1")int roleid, Model model)
    {
        int pagesize = 5;
        PageBean<HashMap<String, Object>> pagebean = usersService.getAllUsersByPage(page, pagesize, uname, roleid);
        model.addAttribute("pagebean", pagebean);
        List<Role> role = usersService.getAllRole();
        model.addAttribute("rolelist", role);
        model.addAttribute("uname", uname);
        model.addAttribute("roleid", roleid);
        return "usersbypage";
    }

    //删除员工
    @GetMapping("/delusers")
    public String delUsers(int uid)
    {
        usersService.delUsers(uid);
        return "redirect:/getusersbypage";
    }

    //批量删除
    @GetMapping("/batchdelusers")
    public String delBatchUsers(int[] uids)
    {
        usersService.delBatchUsers(uids);
        return "redirect:/getusersbypage";
    }

    //进入添加员工页面
    @GetMapping("/adduserspage")
    public String toAddUsersPage(Model model)
    {
        List<Role> role = usersService.getAllRole();
        model.addAttribute("rolelist", role);
        return "addusers";
    }

    //添加员工
    @PostMapping("/addusers")
    public String addUsers(Users users)
    {
        users.setUpass(MD5Util.getMd5Str(users.getUpass()));
        usersService.addUsers(users);
        return "redirect:/getusersbypage";
    }

    //实现文件上传
    @PostMapping("/usersupload")
    @ResponseBody   //将数据转换成json格式
    public Map<String, String> uploadFile(MultipartFile upimage, HttpServletRequest request)
            throws IOException {
        //获取文件名
        String fileName = upimage.getOriginalFilename();
        //上传后的文件名(uuid + 文件扩展名)
        String realName = UUID.randomUUID().toString() + fileName.substring(fileName.indexOf("."));
        //服务器路径
        String serverPath = request.getServletContext().getRealPath("/") + "/resources/image_user/";

        Map<String, String> result = new HashMap<>();
        File file = new File(serverPath + realName);
        try {
            //上传文件
            upimage.transferTo(file);
            //返回结果
            result.put("imgurl", request.getContextPath() + "/resources/image_user/" + realName);
            result.put("imgName", realName);
        }
        catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        //json {"imgurl":"上传路径", "imgName":"文件名"}
        return result;
    }

    //根据id获取当前行的数据进入修改页面
    @GetMapping("/getusersbyid")
    public String getUsersById(int uid, Model model)
    {
        Users users = usersService.getUsersById(uid);
        model.addAttribute("users", users);
        List<Role> role = usersService.getAllRole();
        model.addAttribute("rolelist", role);
        return "updateusers";
    }

    //修改员工信息
    @PostMapping("/updateusers")
    public String updateUsers(Users users)
    {
        usersService.updateUsers(users);
        return "redirect:/getusersbypage";
    }
}
