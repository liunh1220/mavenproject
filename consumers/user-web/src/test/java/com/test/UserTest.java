package com.test;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.entity.table.User;
import com.ssm.service.IUserService;
import com.test.base.BaseTest;
public class UserTest extends BaseTest{

	@Autowired
    private IUserService userService;  
	
    @Test
    public void testList(){  
        PageHelper.startPage(1, 20);  
        List<User> cls =  userService.list(null);
        PageInfo<User> page = new PageInfo<User>(cls);
    	System.out.println(page);  
    }
    
    
    public static void main(String[] args) {
    	/*System.out.println(StringUtils.isBlank(String.valueOf("")));
		System.out.println(String.valueOf(null==null?null:"000000"));
		System.out.println("NULL".equalsIgnoreCase(String.valueOf(null==null?null:"000000")));
		System.out.println(StringUtils.isNotBlank("null"));
		System.out.println(StringUtils.isNotBlank(String.valueOf(null==null?null:"000000")));*/
    	
    	
	}

}
