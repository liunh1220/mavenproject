package com.example.hibernate.test;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.entity.vo.UserVo;
import com.example.hibernate.service.ITestService;
import com.example.hibernate.util.FrontCacheUtil;
import com.example.hibernate.util.LocalCacheUtil;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"}) 
public class BaseTest  extends AbstractJUnit4SpringContextTests  {

	@Autowired
	private ITestService userService;
	
	@Test
	public void testA(){
		/*Object o = userService.countUser(new UserVo());
		System.out.println(o);*/
		
		Object o2 = userService.findUser("1");
		System.out.println(o2);
		System.out.println("cacheUserInfo:"+FrontCacheUtil.getValue("cacheUserInfoTest1"));
		
		/*Object o3 = userService.listUser(new User());
		System.out.println("listUser:"+o3);*/
		
		//User [id=1, name=aaa, password=111111, createTime=2017-04-19 17:21:31.0]
		User insertUser = new User();
		insertUser.setName("ccc");
		insertUser.setPassword("cccccc");
		insertUser.setCreateTime(new Date());
		//userService.insertUser(insertUser);
		
	}
}

















