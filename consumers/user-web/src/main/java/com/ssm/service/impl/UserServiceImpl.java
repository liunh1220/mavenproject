package com.ssm.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.UserMapper;
import com.ssm.entity.table.User;
import com.ssm.service.IUserService;
@Service("userService")
public class UserServiceImpl implements IUserService {

	public SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {  
        this.sqlSession = sqlSession;  
   }  
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Transactional
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transactional
	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transactional
	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByPrimaryKey(String id) {
		return userMapper.selectByPrimaryKey(id);
	}
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}
	@Transactional
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
		List<User> list = userMapper.userList(record);
		//List<User> list = sqlSession.getSqlSessionFactory().openSession().selectList("com.ssm.dao.UserMapper.userList", record);
		return list;
	}
	@Override
	public User selectUser(User record) {
		return userMapper.selectUser(record);
	}

}
