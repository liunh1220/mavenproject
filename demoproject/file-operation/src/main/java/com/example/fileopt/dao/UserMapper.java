package com.example.fileopt.dao;

import java.util.List;

import com.example.fileopt.entity.table.User;

public interface UserMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);
    
    User selectUser(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int count(User record);
    
    List<User> userList(User record);
}