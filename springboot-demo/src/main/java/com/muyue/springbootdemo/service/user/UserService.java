package com.muyue.springbootdemo.service.user;

import com.github.pagehelper.PageInfo;
import com.muyue.springbootdemo.model.UserDomain;

/**
 * @Author <a href="panliang@cai-inc.com">沐月</a>
 * @Date 2018/9/25 上午12:37 Copyright (c) 2016 政采云有限公司
 */
public interface UserService {

    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);

    UserDomain findUserById(Integer id);
}
