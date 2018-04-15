package com.example.memcached.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.memcached.test.base.BaseTest;
import com.example.memcached.util.MemcachedUtils;

public class MainTest extends BaseTest{

    @Test
    public void testA(){ 
    	
    	ApplicationContext app =  new ClassPathXmlApplicationContext(new String[]{"spring.xml"});
    	MemcachedUtils memcachedUtils = (MemcachedUtils) app.getBean("memcachedUtils");
    	
    	boolean bl = memcachedUtils.set("runoob", "Free Education", 900);
    	
    	Object obj = memcachedUtils.get("runoob");
    	
    	System.out.println("===========================================================");  
    	
    	System.out.println(bl);  
    	System.out.println("memcachedManager.get: "+ obj);  
    	
    	System.out.println("==========================================================="); 
    }
}
