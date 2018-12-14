package com.example.demo.singleton;

/**
 * 有入参的单例
 * Created by Administrator on 2018/8/13.
 */
public class SingletonDemo {

    private String name = null;
    private static SingletonDemo instance;
    private SingletonDemo (String name){
        this.name = name;
    }
    public static SingletonDemo getInstance(String name){
        if(instance == null){
            synchronized(SingletonDemo.class){
                instance = new SingletonDemo(name);
            }
        }
        return instance ;
    }


    public String getName() {
        return name;
    }



}
