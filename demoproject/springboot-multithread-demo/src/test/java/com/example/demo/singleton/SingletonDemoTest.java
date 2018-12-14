package com.example.demo.singleton;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/8/13.
 */
public class SingletonDemoTest {


    @Test
    public void getInstance() throws Exception {
        SingletonDemo singletonDemo = SingletonDemo.getInstance("SingletonDemo_name");

        System.out.println("singleton name: " + singletonDemo.getName());

    }

}