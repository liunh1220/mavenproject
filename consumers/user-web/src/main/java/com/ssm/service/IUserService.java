package com.ssm.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.entity.table.User;

public interface IUserService {
	
	int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);
    
    User selectUser(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int count(User record);
    
    List<User> list(User record);
    
}
