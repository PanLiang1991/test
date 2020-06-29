package com.ckmoo.demo;

import com.ckmoo.demo.aspect.OnlyTest;
import org.springframework.stereotype.Service;

@Service
public class DemoTestAspect {

    @OnlyTest
    public void buyThings() {
        System.out.println("啊哈哈哈，我想买点东西！");
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
//        Class personClass  = Class.forName("com.ckmoo.demo.simple.Person");
//        OnlyTest ot = personClass.getMethod("eat").getAnnotation(OnlyTest.class);
//        System.out.println(ot.sex() + " " + ot.name() + " " + ot.age());

        new DemoTestAspect().buyThings();
    }
}
