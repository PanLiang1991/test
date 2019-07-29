package com.monday.test.monday;

public class TestSingleton {
    public static void main(String[] args) throws Exception{
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton == singleton1);
        Class z = Class.forName("com.monday.test.monday.Singleton");
    }
}
