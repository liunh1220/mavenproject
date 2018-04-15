package com.example.ehcache.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.ehcache.entity.pojo.User;
import com.example.ehcache.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"}) 
public class BaseTest  extends AbstractJUnit4SpringContextTests  {

	@Autowired
	private IUserService userService;
	
	@Test
	public void testA(){
		
		int count = userService.countUser(new User());
		System.out.println(count);
		
	}
}
