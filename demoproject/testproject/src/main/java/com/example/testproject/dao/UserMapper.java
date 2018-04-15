package com.example.testproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.testproject.entity.table.User;

@Repository
public interface UserMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int count(User record);
    
    List<User> userList(User record);
}