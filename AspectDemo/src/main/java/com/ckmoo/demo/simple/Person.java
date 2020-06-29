package com.ckmoo.demo.simple;

import com.ckmoo.demo.aspect.OnlyTest;

public class Person {

    @OnlyTest(name = "张三",sex = "女")
    public void eat() {

    }
}
