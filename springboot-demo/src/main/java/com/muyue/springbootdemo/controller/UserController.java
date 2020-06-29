package com.muyue.springbootdemo.controller;

import com.muyue.springbootdemo.annotation.AuthCheck;
import com.muyue.springbootdemo.model.UserDomain;
import com.muyue.springbootdemo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author <a href="panliang@cai-inc.com">沐月</a>
 * @Date 2018/9/25 上午12:46 Copyright (c) 2016 政采云有限公司
 */
@Slf4j
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public int addUser(UserDomain user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Object findAllUser(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public Object findlUserById(@RequestParam("id") Integer id)
    {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public Object deleteById(@PathVariable Integer id) {
        return userService.deleteById(id);
    }

    @AuthCheck
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test(HttpServletRequest request) {
        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
//将数据存储到session中
        session.setAttribute("username", "muyue");
        session.setAttribute("userid", "12345678");
        session.setMaxInactiveInterval(60 * 20); //单位秒
        return "helloworld";
    }

    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public Object getSessionTest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = session.getId();
        String username = (String) session.getAttribute("username");
        String userid = (String) session.getAttribute("userid");

        String s = "username:" + username + "\n" + "userid:" + userid;
        String contextPath = request.getServletPath();
        log.error(contextPath);
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort();
        log.warn(basePath);

        return "<pre>"+ s+ "</pre>";
    }

}
