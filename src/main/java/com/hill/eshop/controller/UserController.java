package com.hill.eshop.controller;

import com.hill.eshop.annotation.Token;
import com.hill.eshop.model.User;
import com.hill.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list/{pageNum}/{pageSize}")
    public String queryAllUsers(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize, Model model){
//        // 指定允许其他域名访问
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        // 响应类型
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");
//        // 响应头设置
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");
        List<User> users = userService.getAllUsers(pageNum,pageSize);
        model.addAttribute("users",users);
        return "user/userList";
    }

    @ResponseBody
    @RequestMapping(value = "/add",produces = "application/json;charset=UTF-8")
    public String AddUser(User user){
        int result = userService.insertUser(user);
        if(result > 0)
            return "unit/addUser";
        return "501";
    }
}
