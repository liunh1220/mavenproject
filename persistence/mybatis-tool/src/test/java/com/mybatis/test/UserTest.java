package com.mybatis.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.mybatis.entity.table.TestUser;
import com.example.mybatis.service.user.ITestService;
import com.github.pagehelper.PageInfo;

public class UserTest extends BaseTest{

	//private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
	
	@Autowired
    private ITestService testService;  
	
    @Test
    public void testList(){  
    	TestUser record = new TestUser();
        //List<User> cls =  userService.list(record);
    	PageInfo<TestUser> cls =  testService.mapperQueryList(record, 1, 10);
        
    	System.out.println("===========================================================");  
    	System.out.println(cls);  
    	System.out.println("===========================================================");  
    }

}
