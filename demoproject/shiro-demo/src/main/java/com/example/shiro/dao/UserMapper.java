package com.example.shiro.dao;

import java.util.List;

import com.example.shiro.entity.table.User;

public interface UserMapper {
	
    List<User> userList(User record);
    
}