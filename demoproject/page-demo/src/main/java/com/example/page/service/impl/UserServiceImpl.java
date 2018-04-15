package com.example.page.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.page.dao.UserMapper;
import com.example.page.model.User;
import com.example.page.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	//private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Transactional
	public int deleteByPrimaryKey(String id) {
		return userMapper.deleteByPrimaryKey(id);
	}


	@Transactional
	@Override
	public int insertSelective(User record) {
		return userMapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(String id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public User selectUser(User record) {
		return userMapper.selectUser(record);
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public int count(com.example.page.model.UserPageReq record) {
		return userMapper.count(record);
	}
	
	@Override
	public List<com.example.page.model.UserPageReq> list(com.example.page.model.UserPageReq record) {
		return userMapper.userList(record);
	}
	
}
