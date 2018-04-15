package com.example.mybatis.dao;

import java.util.List;

import com.example.mybatis.entity.table.TestUser;

public interface TestUserMapper {
	
    List<TestUser> userList(TestUser record);
    
}