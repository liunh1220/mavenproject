package com.example.dubboprovider.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dubboapi.entity.user.User;
import com.example.dubboprovider.dao.UserMapper;
import com.example.dubboprovider.service.user.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	//private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int deleteUser(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertUser(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectUser(User record) {
		// TODO Auto-generated method stub
		System.out.println("************** selectUser ******************");
		User u = new User();
		u.setId("1");
		u.setName("zs");
		return u;
	}

	@Override
	public int updateUser(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countUser(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> listUser(User record) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
