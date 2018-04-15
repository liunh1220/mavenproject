package com.example.dubboapi.service.console.user;

import com.example.dubboapi.entity.user.User;

public interface IDubboUserService {
	
    User findUser(User record);
    
}
