package com.muyue.springbootdemo.controller;

import com.muyue.springbootdemo.model.UserDomain;
import com.muyue.springbootdemo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author <a href="panliang@cai-inc.com">沐月</a>
 * @Date 2018/9/25 上午12:46 Copyright (c) 2016 政采云有限公司
 */
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

}
