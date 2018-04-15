package com.example.hibernate.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.entity.vo.UserVo;
import com.example.hibernate.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"}) 
public class BaseTest  extends AbstractJUnit4SpringContextTests  {

	@Autowired
	private IUserService userService;
	
	@Test
	public void testA(){
		int count = userService.countUser(new UserVo());
		//ResultMsg count = userService.findUser("1");
		System.out.println(count);
		
	}
}
