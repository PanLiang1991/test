package com.muyue.springbootdemo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @Author <a href="panliang@cai-inc.com">沐月</a>
 * @Date 2018/9/25 上午12:27 Copyright (c) 2016 政采云有限公司
 */
@Data
public class UserDomain implements Serializable {

    private Long id;

    private Integer userId;

    private String userName;

    private String password;

    private String phone;

    public static class MyRunnable implements Runnable {
        private ThreadLocal threadLocal = new ThreadLocal();

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            System.out.println(threadLocal.get());
        }
    }

    public static void main(String[] args) throws InterruptedException{
        MyRunnable sharedRunnableInstance = new MyRunnable();
        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        thread1.start();
        System.out.println("xxxxxxxxxxx");
        thread2.start();
        Thread.currentThread().sleep(2001);
        System.out.println("==============");
    }

}
