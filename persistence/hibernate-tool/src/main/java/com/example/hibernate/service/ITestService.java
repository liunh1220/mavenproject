package com.example.hibernate.service;

import java.util.List;

import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.entity.vo.UserVo;

public interface ITestService {
	
	Object deleteUser(String id);

	Object saveOrUpdatUser(User record);
	
	Object saveOrUpdateAll(List<User> record);
	
	Object insertUser(User record);

	Object updateUser(User record);
	
	Object findUser(String id);

	Object listUser(User record);

	Object countUser(UserVo user);
    
    boolean isExists(String username);
    
}
