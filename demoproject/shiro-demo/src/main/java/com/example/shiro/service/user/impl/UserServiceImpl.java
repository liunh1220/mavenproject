package com.example.shiro.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mybatis.base.BaseServiceImpl;
import com.example.shiro.entity.table.User;
import com.example.shiro.service.user.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.shiro.dao.UserMapper;

@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

	@Autowired
	private UserMapper UserMapper;

	@Override
	public PageInfo<User> mapperQueryList(User record,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);// 开始分页,物理分页
		List<User> list =UserMapper.userList(record);//DB操作查询数据
		PageInfo<User> pageInfo=new PageInfo<User>(list);//将查询到的数据封装到List中
		return pageInfo;
	}
	
	@Override
	public List<User> baseDaoQueryList(User record) {
		/*List<User> list = baseDao.queryList("com.example.mybatis.dao.UserMapper.userList", record);
		return list;*/
		return null;
	}

}
