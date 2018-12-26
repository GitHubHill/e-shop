package com.hill.eshop.controller;

import com.hill.eshop.model.User;
import com.hill.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/list/{pageNum}/{pageSize}",produces = "application/json;charset=UTF-8")
    public Object queryAllUsers(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize, HttpServletResponse response, HttpServletRequest request){
        // 指定允许其他域名访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 响应类型
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");
        return userService.queryAllUsers(pageNum,pageSize);
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
