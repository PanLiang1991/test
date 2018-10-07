package com.muyue.springbootdemo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @Author <a href="panliang@cai-inc.com">沐月</a>
 * @Date 2018/9/25 上午12:27 Copyright (c) 2016 政采云有限公司
 */
@Data
public class UserDomain implements Serializable {

    private Integer userId;

    private String userName;

    private String password;

    private String phone;

}
