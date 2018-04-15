package com.example.testproject.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mybatis.base.BaseServiceImpl;
import com.example.testproject.dao.UserMapper;
import com.example.testproject.entity.table.User;
import com.example.testproject.service.user.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

	//private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
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
		// TODO Auto-generated method stub
		return 0;
	}
	@Transactional
	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int count(User record) {
		return baseDao.count("com.example.testproject.dao.UserMapper.count", record);
	}
	@Override
	public List<User> list(User record) {
		List<User> list = baseDao.queryList("com.example.testproject.dao.UserMapper.userList", record);
		return list;
	}
	@Override
	public PageInfo<User> mapperQueryList(User record,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);// 开始分页,物理分页
		List<User> list =userMapper.userList(record);//DB操作查询数据
		PageInfo<User> pageInfo=new PageInfo<User>(list);//将查询到的数据封装到List中
		return pageInfo;
	}
}
