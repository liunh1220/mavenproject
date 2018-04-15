package com.example.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.example.jdbc.service.ITestService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:spring-base.xml"}) 
public class BaseTest  extends AbstractJUnit4SpringContextTests  {

	@Autowired
	private ITestService userService;
	
	@Test
	public void countUser(){
		JSONObject data = new JSONObject();
		Object o = userService.countUser(data);
		System.out.println("===================================================");
		System.out.println(o);
		System.out.println("===================================================");
		
	}
	
	@Test
	public void findUser(){
		Object o = userService.findUser("1");
		System.out.println("===================================================");
		System.out.println(o);
		System.out.println("===================================================");
		
	}
	
	@Test
	public void isExists(){
		Object o = userService.isExists("aaa");
		System.out.println("===================================================");
		System.out.println(o);
		System.out.println("===================================================");
		
	}
	
	@Test
	public void generateKey(){
		Object o = userService.generateKey();
		System.out.println("===================================================");
		System.out.println(o);
		System.out.println("===================================================");
		
	}
	
	@Test
	public void insertUser(){
		JSONObject data = new JSONObject();
		Object o = userService.insertUser(data);
		System.out.println("===================================================");
		System.out.println(o);
		System.out.println("===================================================");
		
	}
	
	@Test
	public void updateUser(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		JSONObject data = new JSONObject();
		data.put("id", "1");
		data.put("create_time", format.format(new Date()));
		
		Object o = userService.updateUser(data);
		System.out.println("===================================================");
		System.out.println(o);
		System.out.println("===================================================");
		
	}
	
	@Test
	public void deleteUser(){
		Object o = userService.deleteUser(new String[]{"2","3"});
		System.out.println("===================================================");
		System.out.println(o);
		System.out.println("===================================================");
		
	}
	
	@Test
	public void deleteUser2(){
		JSONObject data = new JSONObject();
		data.put("name", "222");
		data.put("password", "222222");
		Object o = userService.deleteUser(data);
		System.out.println("===================================================");
		System.out.println(o);
		System.out.println("===================================================");
		
	}
	
	@Test
	public void updateBatch(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Object[] arg = new Object[2];
		arg[0] = format.format(new Date());
		arg[1] = "1";
		Object[] arg2 = {format.format(new Date()),"2"};
		
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(arg);
		batchArgs.add(arg2);
		
		Object o = userService.updateUserBatch(batchArgs);
		System.out.println("===================================================");
		System.out.println(o);
		System.out.println("===================================================");
		
	}
	
	
	
	
	
}
