package com.example.page.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.page.model.User;


@Repository
public interface UserMapper {
	
    int deleteByPrimaryKey(String id);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);
    
    User selectUser(User record);

    int updateByPrimaryKeySelective(User record);
    
    int count(com.example.page.model.UserPageReq record);
    
    List<com.example.page.model.UserPageReq> userList(com.example.page.model.UserPageReq record);
}