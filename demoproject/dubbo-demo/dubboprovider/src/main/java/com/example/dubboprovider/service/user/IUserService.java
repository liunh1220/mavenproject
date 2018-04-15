package com.example.dubboprovider.service.user;

import java.util.List;

import com.example.dubboapi.entity.user.User;

public interface IUserService {
	
	int deleteUser(String id);

    int insertUser(User record);

    User selectUser(User record);

    int updateUser(User record);

    int countUser(User record);
    
    List<User> listUser(User record);
    
}
