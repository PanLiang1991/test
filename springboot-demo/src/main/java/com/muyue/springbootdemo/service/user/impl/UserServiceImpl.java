package com.muyue.springbootdemo.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muyue.springbootdemo.dao.UserDao;
import com.muyue.springbootdemo.model.UserDomain;
import com.muyue.springbootdemo.service.user.UserService;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @Author <a href="panliang@cai-inc.com">沐月</a>
 * @Date 2018/9/25 上午12:40 Copyright (c) 2016 政采云有限公司
 */
@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public int addUser(UserDomain user) {
        return userDao.insert(user);
    }

    /**
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可； pageNum 开始页数 pageSize 每页显示的数据条数
     */
    @Override
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }

    @Override
    public UserDomain findUserById(Integer id){
        String key = "userId_" + id;
        ValueOperations<String, UserDomain> operations = redisTemplate.opsForValue();

        //缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey) {
            UserDomain user = operations.get(key);
            log.info("UserServiceImpl.findUserById:从【缓存】中获取了用户 >> " + user.toString());
            return user;
        }
        //从DB中获取用户信息
        UserDomain user = userDao.findUserById(id);
        if (null == user) {
            return null;
        }
        //插入缓存
        operations.set(key,user,10, TimeUnit.SECONDS);
        log.info("UserServiceImpl.findUserById:从【数据库】中获取了用户 >> " + user.toString());
        return user;
    }

    @Override
    public int deleteById(Integer id) {
        UserDomain userDomain = findUserById(id);
        if (userDomain == null) {
            return 0;
        }
        int response = userDao.delete(id);
        return response;
    }

}
