package com.testproject.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.testproject.entity.table.User;
import com.example.testproject.service.user.IUserService;
import com.github.pagehelper.PageInfo;
import com.testproject.test.base.BaseTest;

public class UserTest extends BaseTest{

	//private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
	
	@Autowired
    private IUserService userService;  
	
    @Test
    public void testList(){  
    	User record = new User();
        List<User> cls =  userService.list(record);
    	PageInfo<User> cls2 =  userService.mapperQueryList(record, 1, 10);
        
    	System.out.println("===========================================================");  
    	System.out.println(cls);  
    	System.out.println("===========================================================");  
    	System.out.println(cls2);  
    	System.out.println("===========================================================");  
    }

}
