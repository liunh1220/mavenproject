package com.example.page.service;

import java.util.List;

import com.example.page.model.User;

public interface IUserService {
	
	int deleteByPrimaryKey(String id);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    User selectUser(User record);
    
    int updateByPrimaryKeySelective(User record);

    int count(com.example.page.model.UserPageReq record);
    
    List<com.example.page.model.UserPageReq> list(com.example.page.model.UserPageReq record);
    
}
