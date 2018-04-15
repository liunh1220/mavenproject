package com.example.dubboprovider.service.dubbo.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dubboapi.entity.user.User;
import com.example.dubboapi.service.console.user.IDubboUserService;
import com.example.dubboprovider.dao.UserMapper;

@Service
public class DubboUserServiceImpl implements IDubboUserService {

	private static final Logger logger = LoggerFactory.getLogger(DubboUserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUser(User record) {
		// TODO Auto-generated method stub
		logger.info("************** DubboUserServiceImpl.findUser() ******************");
		User u = new User();
		u.setId("1");
		u.setName("come from dubbo provider");
		u.setPassword("111111");
		u.setCreateTime(new Date());
		u.setEnabledFlag("Y");
		return u;
	}
	
}
