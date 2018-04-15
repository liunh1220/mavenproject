package com.example.ehcache.service;

import java.util.List;

import com.example.ehcache.entity.pojo.User;

public interface IUserService {
	
	int deleteUser(String id);

    int insertUser(User record);

    User findUser(String id);

    int updateUser(User record);

    int countUser(User record);
    
    List<User> listUser(User record);
    
}
