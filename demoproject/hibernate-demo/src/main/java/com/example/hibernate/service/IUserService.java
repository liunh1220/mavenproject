package com.example.hibernate.service;

import java.util.List;

import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.entity.vo.UserVo;
import com.example.hibernate.util.ResultMsg;

public interface IUserService {
	
	ResultMsg deleteUser(String id);

	ResultMsg saveOrUpdatUser(User record);
	
	ResultMsg saveOrUpdateAll(List<User> record);
	
	ResultMsg insertUser(User record);

	ResultMsg updateUser(User record);
	
	ResultMsg findUser(String id);

	ResultMsg listUser(User record);

    int countUser(UserVo user);
    
    boolean isExists(String username);
    
}
