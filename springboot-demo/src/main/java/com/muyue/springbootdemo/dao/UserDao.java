package com.muyue.springbootdemo.dao;

import com.muyue.springbootdemo.model.UserDomain;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author <a href="panliang@cai-inc.com">沐月</a>
 * @Date 2018/9/25 上午12:30 Copyright (c) 2016 政采云有限公司
 */
@Repository
public interface UserDao {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();

    UserDomain findUserById(Integer userId);

}
