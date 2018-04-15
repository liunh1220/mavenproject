package com.example.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.dao.UserMapper;
import com.example.user.entity.User;
import com.example.user.service.IUserService;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements IUserService {

	//private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		System.out.println("************** selectUser ******************");
		User u = new User();
		u.setId("1");
		u.setName("zs");
		return u;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> list(User record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<User> mapperQueryList(User record, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
